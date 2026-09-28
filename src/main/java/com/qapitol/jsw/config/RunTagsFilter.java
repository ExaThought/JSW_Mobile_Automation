package com.qapitol.jsw.config;

import io.cucumber.gherkin.GherkinParser;
import io.cucumber.messages.types.Envelope;
import io.cucumber.messages.types.PickleTag;
import io.cucumber.messages.types.Source;
import io.cucumber.messages.types.SourceMediaType;
import io.cucumber.tagexpressions.Expression;
import io.cucumber.tagexpressions.TagExpressionParser;
import org.testng.ISuite;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
 * <p>Deciding "selects nothing" needs each scenario's tags, so the block's feature files are parsed
 * once here with Cucumber's own Gherkin parser and tag-expression evaluator.
 *
 * <p>Runs once per {@code ISuite}. A master suite (testng_all.xml) applies it to each child suite on
 * its own, so a fallback there can run once per child suite.
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
        boolean anyBlockSelects = false;

        for (XmlTest test : tests) {
            String own = test.getParameter(TAGS_PARAM);
            String features = featuresOf(test);
            Expression ownExpr = (own == null || own.isBlank()) ? null : TagExpressionParser.parse(own);
            boolean selects = scenarioTags(features, tagsByFeatures).stream()
                    .anyMatch(tags -> (ownExpr == null || ownExpr.evaluate(tags)) && extra.evaluate(tags));
            anyBlockSelects |= selects;

            String combined = ownExpr == null ? runTags : "(" + own.trim() + ") and (" + runTags + ")";
            test.addParameter(TAGS_PARAM, combined);
            System.out.println("[run.tags] " + test.getName() + ": " + combined
                    + (selects ? "" : "  (selects nothing)"));
        }

        if (anyBlockSelects || tests.isEmpty()) {
            return;
        }

        boolean existsAnywhere = scenarioTags(ALL_FEATURES, tagsByFeatures).stream()
                .anyMatch(extra::evaluate);
        if (!existsAnywhere) {
            System.out.println("[run.tags] '" + runTags + "' matches no scenario in " + ALL_FEATURES
                    + " - nothing will run. Check the tag spelling.");
            return;
        }
        XmlTest first = tests.get(0);
        first.addParameter(TAGS_PARAM, runTags);
        first.addParameter(FEATURES_PARAM, ALL_FEATURES);
        System.out.println("[run.tags] No block of '" + suite.getName() + "' selects '" + runTags
                + "' - running it ONCE in '" + first.getName() + "' (test.group="
                + first.getParameter("test.group") + ") across " + ALL_FEATURES + ".");
    }

    private static String featuresOf(XmlTest test) {
        String features = test.getParameter(FEATURES_PARAM);
        return (features == null || features.isBlank()) ? ALL_FEATURES : features;
    }

    /** Each scenario's (pickle's) tags under the given comma-separated feature paths, cached. */
    private static List<List<String>> scenarioTags(String features, Map<String, List<List<String>>> cache) {
        return cache.computeIfAbsent(features, key -> {
            List<List<String>> result = new ArrayList<>();
            for (String part : key.split(",")) {
                String path = part.trim().replaceFirst("^classpath:", "src/test/resources/");
                if (!path.isEmpty()) {
                    result.addAll(parseAll(Paths.get(path)));
                }
            }
            return result;
        });
    }

    private static List<List<String>> parseAll(Path root) {
        if (!Files.exists(root)) {
            System.out.println("[run.tags] Feature path not found: " + root.toAbsolutePath());
            return List.of();
        }
        List<Path> files;
        try (Stream<Path> walk = Files.walk(root)) {
            files = walk.filter(p -> p.toString().endsWith(".feature")).collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("[run.tags] Could not list " + root + ": " + e.getMessage());
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
                parser.parse(source)
                        .map(Envelope::getPickle)
                        .flatMap(p -> p.map(Stream::of).orElseGet(Stream::empty))
                        .forEach(pickle -> result.add(pickle.getTags().stream()
                                .map(PickleTag::getName)
                                .collect(Collectors.toList())));
            } catch (IOException | RuntimeException e) {
                // A file Cucumber itself cannot parse fails the run later with a proper error.
                System.out.println("[run.tags] Skipped unparsable " + file + ": " + e.getMessage());
            }
        }
        return result;
    }
}
