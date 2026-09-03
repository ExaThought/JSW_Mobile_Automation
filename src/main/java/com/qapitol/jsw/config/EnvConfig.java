package com.qapitol.jsw.config;

import org.testng.ISuite;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Resolves which environment the run targets. This is the first thing that happens in a run -
 * every URL, token and test-data directory is derived from it.
 *
 * Resolution order (first non-blank wins):
 * <ol>
 *   <li>{@code -Denv=<value>} JVM system property - what Jenkins and local Maven runs use</li>
 *   <li>{@code ENV} operating-system environment variable - convenient for containers/agents</li>
 *   <li>{@code <parameter name="env" value="..."/>} in the TestNG suite XML</li>
 *   <li>{@link #DEFAULT_ENV} ("qa")</li>
 * </ol>
 *
 * An unrecognised value fails the run immediately rather than silently falling back, because a
 * typo would otherwise load no properties at all and every URL would resolve to null.
 */
public final class EnvConfig {

    public static final String DEFAULT_ENV = "qa";

    /** Add an entry here (and a matching global_<env>.properties) to support a new environment. */
    private static final Set<String> SUPPORTED_ENVS =
            new LinkedHashSet<>(Arrays.asList("qa", "preprod", "prod"));

    private static final String SYSTEM_PROPERTY = "env";
    private static final String ENVIRONMENT_VARIABLE = "ENV";
    private static final String SUITE_PARAMETER = "env";

    private static volatile String resolvedEnv;

    private EnvConfig() {
        // utility class
    }

    /**
     * Resolves the environment and caches it for the rest of the JVM run.
     *
     * @param suite the TestNG suite, used only for the third precedence level. May be null.
     * @throws IllegalStateException if the resolved value is not a supported environment.
     */
    public static String resolve(ISuite suite) {
        String env = firstNonBlank(
                System.getProperty(SYSTEM_PROPERTY),
                System.getenv(ENVIRONMENT_VARIABLE),
                suiteParameter(suite),
                DEFAULT_ENV);

        env = env.trim().toLowerCase(Locale.ROOT);

        if (!SUPPORTED_ENVS.contains(env)) {
            throw new IllegalStateException("Unsupported env '" + env + "'. Supported values: "
                    + SUPPORTED_ENVS + ". Pass it as -Denv=<value>, the ENV variable, or an 'env'"
                    + " parameter in the TestNG suite XML.");
        }

        resolvedEnv = env;
        return env;
    }

    /**
     * The environment resolved for this run. Falls back to {@link #DEFAULT_ENV} when called before
     * the suite initializer has run.
     */
    public static String current() {
        String env = resolvedEnv;
        return (env == null) ? DEFAULT_ENV : env;
    }

    /** Name of the properties file holding this environment's values. */
    public static String propertiesFileFor(String env) {
        return "global_" + env + ".properties";
    }

    public static Set<String> supportedEnvs() {
        return SUPPORTED_ENVS;
    }

    private static String suiteParameter(ISuite suite) {
        if (suite == null || suite.getXmlSuite() == null) {
            return null;
        }
        return suite.getXmlSuite().getParameter(SUITE_PARAMETER);
    }

    private static String firstNonBlank(String... values) {
        for (String value : values) {
            if (value != null && !value.trim().isEmpty()) {
                return value;
            }
        }
        return DEFAULT_ENV;
    }
}
