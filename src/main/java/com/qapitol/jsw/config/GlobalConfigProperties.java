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

    /**
     * Proves the env file actually loaded. Deliberately limited to keys EVERY environment must
     * define, because that is all this check can honestly tell you: a missing one means the file
     * did not land, not that some feature is unconfigured.
     */
    private static final String[] REQUIRED_KEYS = {"ccpUrl", "ccpPortalUrl", "middlewareUrl"};

    /**
     * Additionally required wherever the suite can reach ERP.
     *
     * <p>NOT required on prod, and that is deliberate rather than an oversight.
     * global_prod.properties omits every order-creation and ERP endpoint so that a scenario which
     * must never run against production dies on missing configuration instead of placing a real,
     * invoiced order - one of the three barriers in front of that suite.
     *
     * <p>Demanding omsBaseUrl for every env made the production sanity suite unstartable: it threw
     * from CoreListener.onStart, before a single scenario, with "Property 'omsBaseUrl' is missing
     * after loading global_common.properties and global_prod.properties". None of its six @P0
     * scenarios touches OMS - they are page loads, the 404 page, catalogue contents, search,
     * sign-in and registration validation - so the check failed a run for lacking config it would
     * never read.
     *
     * <p>middlewareUrl used to sit here for the same reason, but prod now configures it: the
     * customer-profile API is a read-only GET of the signed-in customer's own profile, so unlike
     * ERP it is safe against production. It is back in REQUIRED_KEYS above, which keeps the
     * load-check as strong as it can honestly be.
     */
    private static final String[] ERP_REQUIRED_KEYS = {"omsBaseUrl"};

    /** The one environment that deliberately ships without ERP and middleware endpoints. */
    private static final String PROD_ENV = "prod";

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
        // "not configured" rather than a bare null: on prod these are absent by design, and a
        // banner reading "omsBaseUrl = null" invites someone to helpfully fill it in.
        System.out.println("    omsBaseUrl    = " + describe("omsBaseUrl"));
        System.out.println("    middlewareUrl = " + describe("middlewareUrl"));
        System.out.println("    sfUrl         = " + describe("sfUrl"));

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

    /** A property's value for the startup banner, or a note when it is deliberately unset. */
    private static String describe(String key) {
        String value = ConfigUtil.getSecretValue(key);
        return (value == null || value.trim().isEmpty())
                ? "(not configured for this env)"
                : value;
    }

    /** Second guard: confirms the properties actually landed in Config, not just that a file existed. */
    private void verifyLoaded(String env, String envProperties) {
        requireAll(REQUIRED_KEYS, env, envProperties, "");
        if (!PROD_ENV.equalsIgnoreCase(env)) {
            requireAll(ERP_REQUIRED_KEYS, env, envProperties,
                    " ERP endpoints are required on every environment except prod, which"
                    + " deliberately ships without them.");
        }
    }

    private void requireAll(String[] keys, String env, String envProperties, String extra) {
        for (String key : keys) {
            if (!Config.checkPropertyExists(key)) {
                throw new IllegalStateException("Property '" + key + "' is missing after loading "
                        + COMMON_PROPERTIES + " and " + envProperties + " for env '" + env + "'."
                        + " Check that " + envProperties + " defines it." + extra);
            }
        }
    }
}
