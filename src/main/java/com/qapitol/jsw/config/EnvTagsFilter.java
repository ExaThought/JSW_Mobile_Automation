package com.qapitol.jsw.config;

import org.testng.ISuite;
import org.testng.xml.XmlTest;

import java.util.stream.Collectors;

/**
 * Leaves scenarios tagged for another environment ({@code @qaOnly}, {@code @preprodOnly},
 * {@code @prodOnly}) out of the run altogether, so they never reach the report.
 *
 * <p>Hooks.skipIfForAnotherEnv used to be the only guard, and it skips a scenario after Cucumber has
 * selected it - a pre-prod footer run reported 13 skipped QA rows that were correct to leave out
 * but read as something wrong. Excluding them in the tag filter means Cucumber never selects them.
 *
 * <p>Applied to each {@code <test>} block's own filter and, because Cucumber lets it replace those,
 * to {@code -Dcucumber.filter.tags} when that is set. Must run after {@link RunTagsFilter}, which
 * rewrites the block filters and can replace one outright. The hook stays as a backstop for any
 * route that bypasses both.
 */
final class EnvTagsFilter {

    private static final String TAGS_PARAM = "cucumber.filter.tags";

    private EnvTagsFilter() {
        // utility class
    }

    static void apply(ISuite suite) {
        if (suite == null || suite.getXmlSuite() == null) {
            return;
        }
        String env = EnvConfig.resolve(suite);
        String exclusion = EnvConfig.supportedEnvs().stream()
                .filter(other -> !other.equals(env))
                .map(other -> "not @" + other + "Only")
                .collect(Collectors.joining(" and "));
        if (exclusion.isEmpty()) {
            return;
        }

        for (XmlTest test : suite.getXmlSuite().getTests()) {
            test.addParameter(TAGS_PARAM, withExclusion(test.getParameter(TAGS_PARAM), exclusion));
        }

        excludeFromOverride(exclusion);
        System.out.println("[env.tags] env '" + env + "': every block also selects '" + exclusion + "'");
    }

    /** -Dcucumber.filter.tags exactly as passed on the command line, before this class touched it. */
    private static String commandLineOverride;
    private static boolean commandLineOverrideCaptured;

    /**
     * Rewrites the JVM-wide -Dcucumber.filter.tags from the command-line value, never from its
     * current value. A master suite (testng_all.xml) reaches here once per child suite, and suites
     * may start on different threads; rebuilding from the snapshot means a second call cannot stack
     * a second exclusion onto the first, whatever has been wrapped around the value in between.
     * The env is locked for the run by {@link EnvConfig#resolve}, so every call writes the same value.
     */
    private static synchronized void excludeFromOverride(String exclusion) {
        if (!commandLineOverrideCaptured) {
            commandLineOverride = System.getProperty(TAGS_PARAM);
            commandLineOverrideCaptured = true;
        }
        if (commandLineOverride != null && !commandLineOverride.isBlank()) {
            System.setProperty(TAGS_PARAM, withExclusion(commandLineOverride, exclusion));
        }
    }

    /**
     * Appends the exclusion to one block's filter. Each {@code <test>} block belongs to exactly one
     * suite and RunTagsFilter has already finished with it, so the exclusion is always the last
     * term here and the suffix check only has to guard a repeated call on the same suite.
     */
    private static String withExclusion(String tags, String exclusion) {
        if (tags == null || tags.isBlank()) {
            return exclusion;
        }
        String trimmed = tags.trim();
        return trimmed.endsWith(" and " + exclusion) ? trimmed : "(" + trimmed + ") and " + exclusion;
    }
}
