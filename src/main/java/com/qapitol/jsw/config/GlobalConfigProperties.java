package com.qapitol.jsw.config;

import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.jsw.utils.DataContractValidator;
import com.qapitol.sauron.common.configuration.AbstractConfigInitializer;
import com.qapitol.sauron.common.configuration.Config;
import org.testng.ISuite;
import org.testng.ITestContext;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Entry point for configuration. Runs once per suite, before any test, and decides which
 * environment's values the whole run will use.
 *
 * Load order (Sauron's Config uses setProperty, so the last write wins):
 * <ol>
 *   <li>{@code global_common.properties} - values identical across environments</li>
 *   <li>{@code global_<env>.properties}  - environment specific, overrides common</li>
 *   <li>{@code env} / {@code data.env}   - derived, so test data resolves from data/&lt;env&gt;/</li>
 * </ol>
 *
 * Environment variables still win over everything, because {@code ConfigUtil.getSecretValue}
 * resolves the {@code ${VAR:default}} placeholders at read time. That is what lets Jenkins inject
 * secrets without touching any file.
 */
public class GlobalConfigProperties extends AbstractConfigInitializer {

    private static final String COMMON_PROPERTIES = "global_common.properties";

    /** Any one of these missing means the env file did not load - fail fast rather than run with nulls. */
    private static final String[] REQUIRED_KEYS = {"ccpUrl", "ccpPortalUrl", "omsBaseUrl", "middlewareUrl"};

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public void initialize(ITestContext iTestContext) {
        String group = iTestContext.getCurrentXmlTest().getParameter("test.group");
        if (group != null && !group.isBlank()) {
            TestGroupContext.set(group);
        }
    }

    @Override
    public void initialize(ISuite iSuite) {
        String env = EnvConfig.resolve(iSuite);
        String envProperties = EnvConfig.propertiesFileFor(env);

        loadRequired(COMMON_PROPERTIES);
        loadRequired(envProperties);

        // Derived values, so nothing else has to know how the environment was chosen.
        // data.env drives which data/<env>/ directory Sauron reads test data from.
        Map<String, String> derived = new LinkedHashMap<>();
        derived.put("env", env);
        derived.put("data.env", env);
        Config.loadProperties(derived);

        verifyLoaded(env, envProperties);

        // Printed via ConfigUtil so the ${VAR:default} placeholders are shown resolved - i.e. the
        // URLs actually used, including any Jenkins environment-variable overrides.
        System.out.println("=== ENVIRONMENT: " + env + " ==="
                + " (config: " + COMMON_PROPERTIES + " + " + envProperties
                + ", test data: data/" + env + "/)");
        System.out.println("    ccpUrl        = " + ConfigUtil.getSecretValue("ccpUrl"));
        System.out.println("    ccpPortalUrl  = " + ConfigUtil.getSecretValue("ccpPortalUrl"));
        System.out.println("    omsBaseUrl    = " + ConfigUtil.getSecretValue("omsBaseUrl"));
        System.out.println("    middlewareUrl = " + ConfigUtil.getSecretValue("middlewareUrl"));
        System.out.println("    sfUrl         = " + ConfigUtil.getSecretValue("sfUrl"));

        checkConfiguredTokenFreshness();
    }

    /**
     * Reports the age of the statically configured tokens at suite start.
     *
     * A warning by default rather than a hard failure, because these tokens only matter to the
     * ERP scenarios - blocking a UI-only run over a stale ERP token would be worse than the
     * problem. Set failOnExpiredToken=true to escalate (recommended for the Jenkins ERP job).
     */
    private void checkConfiguredTokenFreshness() {
        boolean failOnExpired = "true".equalsIgnoreCase(
                String.valueOf(ConfigUtil.getSecretValue("failOnExpiredToken")));

        DataContractValidator.checkTokenFreshness(
                "omsAuthToken", ConfigUtil.getSecretValue("omsAuthToken"), failOnExpired);
    }

    /**
     * Config.loadProperties swallows a missing file - it logs "Failed to loadProperties" and
     * carries on, leaving every property null. Checking the classpath first turns that silent
     * failure into an immediate, readable one.
     */
    private void loadRequired(String fileName) {
        if (Thread.currentThread().getContextClassLoader().getResource(fileName) == null) {
            throw new IllegalStateException("Configuration file '" + fileName + "' was not found on the"
                    + " classpath. Expected it at src/test/resources/" + fileName);
        }
        Config.loadProperties(fileName);
    }

    /** Second guard: confirms the properties actually landed in Config, not just that a file existed. */
    private void verifyLoaded(String env, String envProperties) {
        for (String key : REQUIRED_KEYS) {
            if (!Config.checkPropertyExists(key)) {
                throw new IllegalStateException("Property '" + key + "' is missing after loading "
                        + COMMON_PROPERTIES + " and " + envProperties + " for env '" + env + "'."
                        + " Check that " + envProperties + " defines it.");
            }
        }
    }
}
