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

    /** Scenarios already warned about, so the warning prints once rather than once per block. */
    private static final Set<String> WARNED = ConcurrentHashMap.newKeySet();

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
                return block;
            }
        }
        return null;
    }

    private static void warnUnowned(String override, Pickle pickle, XmlSuite suite) {
        String file = pickle.getUri() == null ? "?" : pickle.getUri().getPath();
        String key = file + ":" + pickle.getLine();
        if (!WARNED.add(key)) {
            return;
        }
        String shortFile = file.contains("/features/") ? file.substring(file.indexOf("/features/") + 1) : file;
        System.out.println("WARNING: TAGS '" + override + "' selects \"" + pickle.getName() + "\" (" + shortFile
                + ":" + pickle.getLine() + ", " + pickle.getTags() + "), but no enabled <test> block in "
                + new java.io.File(String.valueOf(rootOf(suite).getFileName())).getName()
                + " runs it - its block is commented out or missing."
                + " Enable the block first; it was not run.");
    }

    private static boolean ownFilterMatches(XmlTest block, Collection<String> scenarioTags) {
        String filter = block.getParameter(FILTER);
        if (filter == null || filter.isBlank()) {
            return true;                                  // an unfiltered block runs every scenario
        }
        return TagExpressionParser.parse(filter).evaluate(new ArrayList<>(scenarioTags));
    }

    /** Whether the block's cucumber.features (comma-separated paths) includes the feature file. */
    private static boolean contains(XmlTest block, URI scenarioUri) {
        String features = block.getParameter(FEATURES);
        if (features == null || features.isBlank() || scenarioUri == null) {
            return true;
        }
        String scenarioPath = normalise(scenarioUri.getPath() != null ? scenarioUri.getPath() : scenarioUri.toString());
        for (String raw : features.split(",")) {
            String path = raw.trim();
            int line = path.lastIndexOf(':');             // "file.feature:12" selects one scenario's file
            if (line > 1 && path.substring(line + 1).matches("\\d+")) {
                path = path.substring(0, line);
            }
            path = normalise(path);
            if (path.startsWith("classpath:")) {
                path = path.substring("classpath:".length());
            }
            if (!path.isEmpty() && scenarioPath.contains(path)) {
                return true;
            }
        }
        return false;
    }

    private static String normalise(String path) {
        String p = path.replace('\\', '/');
        while (p.startsWith("./")) {
            p = p.substring(2);
        }
        return p.endsWith("/") ? p.substring(0, p.length() - 1) : p;
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
