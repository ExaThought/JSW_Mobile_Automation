package com.qapitol.jsw.config;

import io.cucumber.gherkin.GherkinParser;
import io.cucumber.messages.types.Envelope;
import io.cucumber.messages.types.PickleTag;
import io.cucumber.messages.types.Source;
import io.cucumber.messages.types.SourceMediaType;
import io.cucumber.tagexpressions.Expression;
import io.cucumber.tagexpressions.TagExpressionParser;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Applies an extra tag filter ({@code -Drun.tags=...}, the Jenkins TAGS parameter) to a suite
 * WITHOUT replacing each {@code <test>} block's own filter.
 *
 * <p>WHY NOT -Dcucumber.filter.tags. Cucumber applies -D system properties after the {@code <test>}
 * parameters, and any filter set there replaces the block's filters - tag AND name - so {@code TAGS=@footer} on testng_bdd.xml ran the footer once per active
 * block - five times, four of them signed in as accounts meant for other areas (each block's
 * {@code test.group} picks its user). Here each block instead runs {@code (its own tags) and (TAGS)},
 * so a scenario runs once, in the block - and as the account - that owns it.
 *
 * <p>FALLBACK. If that selects nothing in any block - TAGS names an area whose block is commented
 * out, e.g. {@code @multiUser} on testng_serial.xml - TAGS runs ONCE in the first block, across all
 * feature files, which is what an ad-hoc TAGS build did before minus the repetition. It runs as that
 * block's account. Production is still guarded per scenario by {@code ProdSafetyHooks}.
 *
 * <p>Because that account may not be the one a scenario was written for, a fallback is never
 * silent: it prints a FALLBACK banner and adds an entry to the Allure report's Environment panel
 * (allure-results/environment.properties) naming the block and its account. It is kept rather than
 * failing the run because it is the only way to run TAGS for an area whose block is commented out.
 *
 * <p>Deciding "selects nothing" needs each scenario's tags, so the block's feature files are parsed
 * once here with Cucumber's own Gherkin parser and tag-expression evaluator.
 *
 * <p>Runs once per {@code ISuite}. A master suite (testng_all.xml) applies it to each child suite,
 * but the fallback is decided once for the whole run - across every block of every child suite -
 * and taken by at most one suite, so a TAGS build never runs the fallback once per child.
 */
final class RunTagsFilter {

    static final String PROPERTY = "run.tags";

    private static final String TAGS_PARAM = "cucumber.filter.tags";
    private static final String FEATURES_PARAM = "cucumber.features";
    private static final String ALL_FEATURES = "src/test/resources/features";

    private RunTagsFilter() {
        // utility class
    }

    static void apply(ISuite suite) {
        String runTags = System.getProperty(PROPERTY);
        if (runTags == null || runTags.isBlank() || suite == null || suite.getXmlSuite() == null) {
            return;
        }
        runTags = runTags.trim();
        // Cucumber reads -D system properties AFTER the <test> parameters, and a source that sets
        // ANY filter (tags, name or lines) replaces every filter from the sources before it. So
        // either of these wipes the per-block filters written below.
        for (String override : new String[] {TAGS_PARAM, "cucumber.filter.name"}) {
            if (System.getProperty(override) != null) {
                System.out.println("[run.tags] -D" + override + " is also set. Cucumber applies it over"
                        + " every block's filter, so " + PROPERTY + " has no effect in this run and"
                        + " each block selects by -D" + override + " alone.");
            }
        }

        Expression extra = TagExpressionParser.parse(runTags);
        List<XmlTest> tests = suite.getXmlSuite().getTests();
        Map<String, List<List<String>>> tagsByFeatures = new HashMap<>();
        // Files whose scenarios could not be read. Their scenarios are missing from every
        // "selects" answer below, so with any entry here "selects nothing" may be wrong.
        Set<String> unreadable = new LinkedHashSet<>();

        for (XmlTest test : tests) {
            boolean selects = blockSelects(test, extra, tagsByFeatures, unreadable);
            String own = test.getParameter(TAGS_PARAM);
            String combined = (own == null || own.isBlank()) ? runTags : "(" + own.trim() + ") and (" + runTags + ")";
            test.addParameter(TAGS_PARAM, combined);
            System.out.println("[run.tags] " + test.getName() + ": " + combined
                    + (selects ? "" : unreadable.isEmpty()
                       ? "  (selects nothing)"
                       : "  (selects nothing in the feature files that could be read)"));
        }

        RunDecision decision = decisionFor(suite.getXmlSuite());
        if (tests.isEmpty() || !decision.fallbackNeeded(suite.getXmlSuite(), runTags, extra, tagsByFeatures, unreadable)
                || !decision.claim()) {
            return;
        }
        XmlTest first = tests.get(0);
        first.addParameter(TAGS_PARAM, runTags);
        first.addParameter(FEATURES_PARAM, ALL_FEATURES);
        String note = "'" + runTags + "' matched no block in this run, so it ran ONCE in '" + first.getName()
                + "' of suite '" + suite.getName() + "' as test.group=" + first.getParameter("test.group")
                + " across " + ALL_FEATURES + ". Scenarios owned by another area ran as THIS block's account -"
                + " a failure may be the account, not the product.";
        System.out.println("[run.tags] ==================== FALLBACK ====================");
        System.out.println("[run.tags] " + note);
        System.out.println("[run.tags] ==================================================");
        recordFallbackInAllure(note);
    }

    /** Whether any scenario under the block's features matches both its own filter and run.tags. */
    private static boolean blockSelects(XmlTest test, Expression extra, Map<String, List<List<String>>> cache,
                                        Set<String> unreadable) {
        String own = test.getParameter(TAGS_PARAM);
        Expression ownExpr = (own == null || own.isBlank()) ? null : TagExpressionParser.parse(own);
        return scenarioTags(featuresOf(test), cache, unreadable).stream()
                .anyMatch(tags -> (ownExpr == null || ownExpr.evaluate(tags)) && extra.evaluate(tags));
    }

    /*
     * The fallback is decided for the WHOLE RUN, not per suite. A master suite (testng_all.xml)
     * calls apply() once per child suite; deciding per suite let every child that selected nothing
     * fall back on its own, running the same scenarios once per child, each as a different account -
     * and a child fell back even when a sibling's block selected the scenarios properly.
     *
     * So the first call looks at every <test> block of every suite in the run, decides once, and
     * the answer is kept on a RunDecision; only the first suite to claim it takes the fallback.
     *
     * The decision is keyed by the run's top-level XmlSuite (by identity - XmlSuite overrides
     * equals), not held in static fields. Two suite trees in one JVM - a second TestNG run, or a
     * test harness - each get their own decision instead of inheriting the first one's, and suites
     * of one tree running in parallel (suite-thread-pool-size) share one synchronized decision.
     *
     * The key holds the suite WEAKLY, so an entry goes away once its suite tree is garbage
     * collected, and a long-lived JVM running many suites does not keep one entry per run forever.
     * TestNG gives no reliable "whole tree finished" callback to remove it from instead.
     * WeakHashMap cannot be used: it compares keys with equals/hashCode, which XmlSuite derives from
     * its (mutable) contents, so two different runs could share an entry. SuiteKey compares by
     * identity. RunDecision holds no reference back to the suite, so it cannot keep its key alive.
     */
    private static final Map<SuiteKey, RunDecision> DECISIONS = new HashMap<>();
    private static final ReferenceQueue<XmlSuite> COLLECTED = new ReferenceQueue<>();

    private static RunDecision decisionFor(XmlSuite suite) {
        synchronized (DECISIONS) {
            for (Reference<? extends XmlSuite> gone; (gone = COLLECTED.poll()) != null; ) {
                DECISIONS.remove(gone);
            }
            return DECISIONS.computeIfAbsent(new SuiteKey(rootOf(suite), COLLECTED), key -> new RunDecision());
        }
    }

    /** Number of live run decisions - for checking that collected runs are dropped. */
    static int decisionCount() {
        synchronized (DECISIONS) {
            for (Reference<? extends XmlSuite> gone; (gone = COLLECTED.poll()) != null; ) {
                DECISIONS.remove(gone);
            }
            return DECISIONS.size();
        }
    }

    /** A weak, identity-compared reference to a run's top-level suite. */
    private static final class SuiteKey extends WeakReference<XmlSuite> {
        private final int hash;

        SuiteKey(XmlSuite suite, ReferenceQueue<XmlSuite> queue) {
            super(suite, queue);
            this.hash = System.identityHashCode(suite);
        }

        @Override
        public int hashCode() {
            return hash;
        }

        /** Same object, or both still pointing at the same suite. A cleared key equals only itself. */
        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SuiteKey)) {
                return false;
            }
            XmlSuite mine = get();
            return mine != null && mine == ((SuiteKey) other).get();
        }
    }

    private static XmlSuite rootOf(XmlSuite suite) {
        XmlSuite root = suite;
        while (root.getParentSuite() != null) {
            root = root.getParentSuite();
        }
        return root;
    }

    /** One run's fallback decision: whether it is needed, and whether a suite has taken it. */
    private static final class RunDecision {
        private Boolean needed;
        private boolean claimed;

        synchronized boolean fallbackNeeded(XmlSuite current, String runTags, Expression extra,
                                            Map<String, List<List<String>>> cache, Set<String> unreadable) {
            if (needed == null) {
                needed = decide(current, runTags, extra, cache, unreadable);
            }
            return needed;
        }

        /** True for exactly one caller per run: the suite that runs the fallback. */
        synchronized boolean claim() {
            if (claimed) {
                return false;
            }
            claimed = true;
            return true;
        }
    }

    private static boolean decide(XmlSuite current, String runTags, Expression extra,
                                  Map<String, List<List<String>>> cache, Set<String> unreadable) {
        // A block already processed carries "(own) and (run.tags)", which selects exactly what
        // "own" did together with run.tags, so mixing processed and unprocessed blocks is safe.
        boolean anyBlockSelects = false;
        for (XmlTest test : testsOfRun(current)) {
            anyBlockSelects |= blockSelects(test, extra, cache, unreadable);
        }
        if (anyBlockSelects) {
            return false;
        }
        boolean existsAnywhere = scenarioTags(ALL_FEATURES, cache, unreadable).stream()
                .anyMatch(extra::evaluate);
        if (!unreadable.isEmpty()) {
            // Neither the fallback nor "nothing will run" can be decided from a partial scan: the
            // matching scenarios may be in the files that failed. Keep each block's combined
            // filter, which is what the run would do with no fallback, and let Cucumber report
            // the broken file when it parses it.
            System.out.println("[run.tags] Could not read " + unreadable.size() + " feature file(s): "
                    + unreadable + ". Without their scenarios it cannot be told whether '" + runTags
                    + "' selects anything, so the single-block fallback was NOT applied and each block"
                    + " keeps its combined filter. Fix the file(s) above and re-run.");
            return false;
        }
        if (!existsAnywhere) {
            System.out.println("[run.tags] '" + runTags + "' matches no scenario in " + ALL_FEATURES
                    + " - nothing will run. Check the tag spelling.");
            return false;
        }
        return true;
    }

    /** Every {@code <test>} block in the run: the top-level suite and all its child suites. */
    private static List<XmlTest> testsOfRun(XmlSuite suite) {
        List<XmlTest> all = new ArrayList<>();
        collectTests(rootOf(suite), all);
        return all;
    }

    private static void collectTests(XmlSuite suite, List<XmlTest> out) {
        out.addAll(suite.getTests());
        for (XmlSuite child : suite.getChildSuites()) {
            collectTests(child, out);
        }
    }

    /**
     * Puts the fallback on the Allure report's Overview (Environment panel), not only in the console
     * log, so whoever reads the report sees that the run was widened and under which account.
     * Merged into any existing environment.properties. Reporting only - a write failure never
     * fails the run.
     */
    private static void recordFallbackInAllure(String note) {
        Path dir = Paths.get(System.getProperty("allure.results.directory", "allure-results"));
        Path file = dir.resolve("environment.properties");
        Properties environment = new Properties();
        try {
            Files.createDirectories(dir);
            if (Files.exists(file)) {
                try (Reader in = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
                    environment.load(in);
                }
            }
            environment.setProperty("run.tags FALLBACK", note);
            try (Writer out = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
                environment.store(out, null);
            }
        } catch (IOException | RuntimeException e) {
            System.out.println("[run.tags] Could not record the fallback in " + file.toAbsolutePath()
                    + " (" + e.getMessage() + ") - it is in the console log above only.");
        }
    }

    private static String featuresOf(XmlTest test) {
        String features = test.getParameter(FEATURES_PARAM);
        return (features == null || features.isBlank()) ? ALL_FEATURES : features;
    }

    /**
     * Each scenario's (pickle's) tags under the given comma-separated feature paths, cached. Any
     * file or directory that could not be read is added to {@code unreadable}.
     */
    private static List<List<String>> scenarioTags(String features, Map<String, List<List<String>>> cache,
                                                   Set<String> unreadable) {
        return cache.computeIfAbsent(features, key -> {
            List<List<String>> result = new ArrayList<>();
            for (String part : key.split(",")) {
                String path = part.trim().replaceFirst("^classpath:", "src/test/resources/");
                if (!path.isEmpty()) {
                    result.addAll(parseAll(Paths.get(path), unreadable));
                }
            }
            return result;
        });
    }

    private static List<List<String>> parseAll(Path root, Set<String> unreadable) {
        if (!Files.exists(root)) {
            System.out.println("[run.tags] Feature path not found: " + root.toAbsolutePath());
            unreadable.add(root.toString());
            return List.of();
        }
        List<Path> files;
        try (Stream<Path> walk = Files.walk(root)) {
            files = walk.filter(p -> p.toString().endsWith(".feature")).collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("[run.tags] Could not list " + root + ": " + e.getMessage());
            unreadable.add(root.toString());
            return List.of();
        }
        GherkinParser parser = GherkinParser.builder()
                .includeSource(false)
                .includeGherkinDocument(false)
                .includePickles(true)
                .build();
        List<List<String>> result = new ArrayList<>();
        for (Path file : files) {
            try {
                String text = new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
                Envelope source = Envelope.of(new Source(file.toString(), text,
                        SourceMediaType.TEXT_X_CUCUMBER_GHERKIN_PLAIN));
                // A Gherkin syntax error does not throw: it comes back as a parse-error envelope
                // alongside whatever pickles could still be built, so it is checked for here.
                List<Envelope> envelopes = parser.parse(source).collect(Collectors.toList());
                for (Envelope envelope : envelopes) {
                    envelope.getParseError().ifPresent(error -> {
                        System.out.println("[run.tags] Parse error in " + file + ": " + error.getMessage());
                        unreadable.add(file.toString());
                    });
                    envelope.getPickle().ifPresent(pickle -> result.add(pickle.getTags().stream()
                            .map(PickleTag::getName)
                            .collect(Collectors.toList())));
                }
            } catch (IOException | RuntimeException e) {
                // Recorded, not just logged: apply() must not take the fallback or report
                // "nothing will run" from a scan that is missing this file's scenarios.
                System.out.println("[run.tags] Could not parse " + file + ": " + e.getMessage());
                unreadable.add(file.toString());
            }
        }
        return result;
    }
}
