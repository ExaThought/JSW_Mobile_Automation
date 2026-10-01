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

        String override = System.getProperty(TAGS_PARAM);
        if (override != null && !override.isBlank()) {
            System.setProperty(TAGS_PARAM, withExclusion(override, exclusion));
        }
        System.out.println("[env.tags] env '" + env + "': every block also selects '" + exclusion + "'");
    }

    /** Appends the exclusion once - a master suite (testng_all.xml) reaches here per child suite. */
    private static String withExclusion(String tags, String exclusion) {
        if (tags == null || tags.isBlank()) {
            return exclusion;
        }
        String trimmed = tags.trim();
        return trimmed.endsWith(" and " + exclusion) ? trimmed : "(" + trimmed + ") and " + exclusion;
    }
}
