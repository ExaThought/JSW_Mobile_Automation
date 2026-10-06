package com.qapitol.jsw.utils;

import io.cucumber.tagexpressions.TagExpressionParser;
import io.cucumber.testng.Pickle;
import io.cucumber.testng.PickleWrapper;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import java.util.function.Consumer;

/**
 * Keeps a Jenkins TAGS run from executing a scenario under the wrong account.
 *
 * <p>The account a scenario logs in with comes from its {@code <test>} block's {@code test.group},
 * not from its tags. A TAGS value reaches Cucumber as {@code -Dcucumber.filter.tags}, which
 * replaces EVERY block's own filter - so {@code TAGS=@ledger} ran the Ledger scenarios in all six
 * blocks of testng_bdd.xml, as six different users, and only the Ledger block's emailnoti had
 * ledger data. Header-style scenarios hid the same duplication because any account passes them.
 *
 * <p>With an override active, each block's scenario list is filtered before TestNG turns it into
 * test invocations (see {@link com.qapitol.jsw.config.TestGroupListener#intercept}):
 * <ul>
 *   <li>a scenario this block OWNS - its {@code cucumber.features} contains the feature file and
 *       its own suite-XML tag filter matches the scenario's tags (no filter matches everything) -
 *       is kept and runs with this block's account;</li>
 *   <li>a scenario another enabled block owns is dropped here: it runs there, as that block's
 *       user, and is never reported here - not even as skipped;</li>
 *   <li>a scenario no enabled block owns - its block is commented out or missing - is dropped
 *       everywhere, with one warning to enable the block first.</li>
 * </ul>
 * Without an override nothing is filtered, and commented-out blocks are never mentioned.
 */
public final class TagsOverrideGuard {

    private static final String FILTER = "cucumber.filter.tags";
    private static final String FEATURES = "cucumber.features";

    /**
     * Scenarios and blocks already reported, so each message prints once rather than once per
     * block. Static, so it also spans suites in one JVM - harmless: it only silences repeats of a
     * message, never changes which block runs what.
     */
    private static final Set<String> WARNED = ConcurrentHashMap.newKeySet();

    /**
     * Where messages go: Sauron's logger (console and run log files). Looked up only when a message
     * is logged, so the ownership logic is unit-testable without the Sauron runtime, whose
     * auto-registered TestNG listeners stop a bare unit-test run from executing anything.
     */
    static volatile Consumer<String> INFO = message -> com.qapitol.sauron.web.WebActionListener.log.info(message);
    static volatile Consumer<String> WARN = message -> com.qapitol.sauron.web.WebActionListener.log.warning(message);

    private TagsOverrideGuard() {
    }

    /** The scenario rows this block should run under a TAGS override; all rows without one. */
    public static Iterator<Object[]> filter(Iterator<Object[]> rows, ITestContext context) {
        String override = overrideTags();
        if (override == null || context == null || context.getCurrentXmlTest() == null) {
            return rows;
        }
        XmlTest current = context.getCurrentXmlTest();
        List<XmlTest> blocks = allBlocks(rootOf(current.getSuite()));
        List<Object[]> kept = new ArrayList<>();
        while (rows.hasNext()) {
            Object[] row = rows.next();
            if (row == null || row.length == 0 || !(row[0] instanceof PickleWrapper)) {
                kept.add(row);                            // not a Cucumber scenario: leave it alone
                continue;
            }
            Pickle pickle = ((PickleWrapper) row[0]).getPickle();
            XmlTest owner = ownerOf(blocks, pickle.getTags(), pickle.getUri());
            if (owner == null) {
                warnUnowned(override, pickle, current.getSuite());
            } else if (sameBlock(owner, current)) {
                kept.add(row);
            }
            // else: another enabled block owns it and runs it with its own account.
        }
        return kept.iterator();
    }

    /** The -D / env override Cucumber applies on top of the suite's per-block filters, or null. */
    static String overrideTags() {
        String value = System.getProperty(FILTER);
        if (value == null || value.isBlank()) {
            value = System.getenv("CUCUMBER_FILTER_TAGS");
        }
        return (value == null || value.isBlank()) ? null : value.trim();
    }

    /** The first enabled block, in run order, that owns the scenario - or null if none does. */
    static XmlTest ownerOf(List<XmlTest> blocks, Collection<String> scenarioTags, URI scenarioUri) {
        for (XmlTest block : blocks) {
            if (contains(block, scenarioUri) && ownFilterMatches(block, scenarioTags)) {
                noteCatchAll(block);
                return block;
            }
        }
        return null;
    }

    /**
     * Logged once per block: one with no cucumber.features owns every feature file, and with no
     * filter as well it owns every scenario - so if it comes first, later blocks run nothing.
     * Consistent with the rules, but worth a line when diagnosing an empty block.
     */
    private static void noteCatchAll(XmlTest block) {
        if (isBlank(block.getParameter(FEATURES)) && WARNED.add("catch-all:" + block.getSuite().getName() + "/" + block.getName())) {
            emit(INFO, "INFO", "TAGS override: <test> '" + block.getName() + "' has no cucumber.features, so it owns every"
                    + " feature file" + (isBlank(block.getParameter(FILTER)) ? " and, with no tag filter, every scenario" : "")
                    + "; blocks after it get only what it does not claim.");
        }
    }

    private static void warnUnowned(String override, Pickle pickle, XmlSuite suite) {
        String file = pickle.getUri() == null ? "?" : String.valueOf(pickle.getUri().getPath() != null
                ? pickle.getUri().getPath() : pickle.getUri());
        String key = file + ":" + pickle.getLine();
        if (!WARNED.add(key)) {
            return;
        }
        String shortFile = file.contains("/features/") ? file.substring(file.indexOf("/features/") + 1) : file;
        // Logger, not System.out, so it reaches the run's log files as well as the console. An Allure
        // step is not possible here: the data provider runs before any test exists.
        emit(WARN, "WARNING", "TAGS '" + override + "' selects \"" + pickle.getName() + "\" (" + shortFile
                + ":" + pickle.getLine() + ", " + pickle.getTags() + "), but no enabled <test> block in "
                + new java.io.File(String.valueOf(rootOf(suite).getFileName())).getName()
                + " runs it - its block is commented out or missing."
                + " Enable the block first; it was not run.");
    }

    /**
     * Whether the block's own tag filter selects the scenario; no filter selects everything. A
     * malformed filter fails naming the block and its text - otherwise TestNG reports only a
     * data-provider failure.
     */
    static boolean ownFilterMatches(XmlTest block, Collection<String> scenarioTags) {
        String filter = block.getParameter(FILTER);
        if (isBlank(filter)) {
            return true;
        }
        try {
            return TagExpressionParser.parse(filter).evaluate(new ArrayList<>(scenarioTags));
        } catch (RuntimeException e) {
            throw new IllegalStateException("<test> '" + block.getName() + "' in " + block.getSuite().getName()
                    + " has an invalid cucumber.filter.tags \"" + filter + "\": " + e.getMessage(), e);
        }
    }

    /** Whether any entry of the block's cucumber.features (comma-separated) covers the feature file. */
    static boolean contains(XmlTest block, URI scenarioUri) {
        String features = block.getParameter(FEATURES);
        if (isBlank(features) || scenarioUri == null) {
            return true;
        }
        String scenarioPath = scenarioUri.getPath() != null ? scenarioUri.getPath() : scenarioUri.toString();
        for (String entry : features.split(",")) {
            if (pathCovers(entry, scenarioPath)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Whether one cucumber.features entry covers the scenario's feature file, on PATH BOUNDARIES:
     * a file entry must be the file itself or a trailing path of it ("Login.feature" does not claim
     * "SocialLogin.feature"), and a directory entry must be a whole directory of the path
     * ("features/Homepage" does not claim "features/HomepageExtra/..."). Handles "./", "\",
     * "classpath:", absolute and relative paths, and a "file.feature:12" line selector.
     */
    static boolean pathCovers(String entry, String scenarioPath) {
        String path = clean(entry);
        int colon = path.lastIndexOf(':');                // "file.feature:12" selects one scenario's file
        if (colon > 1 && path.substring(colon + 1).matches("\\d+")) {
            path = path.substring(0, colon);
        }
        if (path.isEmpty()) {
            return false;
        }
        String scenario = "/" + clean(scenarioPath);
        if (path.endsWith(".feature")) {
            return scenario.endsWith("/" + path);
        }
        return scenario.contains("/" + path + "/");
    }

    /** Forward slashes, no "classpath:" / "./" / leading or trailing "/". */
    private static String clean(String path) {
        String p = path == null ? "" : path.trim().replace('\\', '/');
        if (p.startsWith("classpath:")) {
            p = p.substring("classpath:".length());
        }
        while (p.startsWith("./") || p.startsWith("/")) {
            p = p.substring(p.startsWith("./") ? 2 : 1);
        }
        while (p.endsWith("/")) {
            p = p.substring(0, p.length() - 1);
        }
        return p;
    }

    /**
     * Sends a message to its sink, falling back to the console if the logger cannot be used. This
     * runs inside TestNG's data-provider interceptor, where a thrown exception is reported as a
     * failed test - reporting must never be what fails a run. LinkageError covers a missing or
     * broken Sauron class (NoClassDefFoundError, ExceptionInInitializerError); other Errors such as
     * OutOfMemoryError are not swallowed.
     */
    private static void emit(Consumer<String> sink, String level, String message) {
        try {
            sink.accept(message);
        } catch (Exception | LinkageError unavailable) {
            System.out.println(level + ": " + message);
        }
    }

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    private static boolean sameBlock(XmlTest a, XmlTest b) {
        return a == b || (a.getName().equals(b.getName()) && a.getSuite().getName().equals(b.getSuite().getName()));
    }

    private static XmlSuite rootOf(XmlSuite suite) {
        XmlSuite root = suite;
        while (root.getParentSuite() != null) {
            root = root.getParentSuite();
        }
        return root;
    }

    /** Every enabled <test> of the root suite and its included suites, in run order. */
    static List<XmlTest> allBlocks(XmlSuite suite) {
        List<XmlTest> blocks = new ArrayList<>(suite.getTests());
        for (XmlSuite child : suite.getChildSuites()) {
            blocks.addAll(allBlocks(child));
        }
        return blocks;
    }
}
