package com.qapitol.jsw.config;

import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.jsw.utils.DataContractValidator;
import com.qapitol.sauron.common.configuration.AbstractConfigInitializer;
import com.qapitol.sauron.common.configuration.Config;
import org.testng.ISuite;
import org.testng.ITestContext;

import java.util.LinkedHashMap;
import java.util.Map;

public class GlobalConfigProperties extends AbstractConfigInitializer {

    private static final String COMMON_PROPERTIES = "global_common.properties";

    private static final String[] REQUIRED_KEYS = {"ccpUrl", "ccpPortalUrl", "middlewareUrl"};

    private static final String[] ERP_REQUIRED_KEYS = {"omsBaseUrl"};

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

        Map<String, String> derived = new LinkedHashMap<>();
        derived.put("env", env);
        derived.put("data.env", env);
        Config.loadProperties(derived);

        verifyLoaded(env, envProperties);

        System.out.println("=== ENVIRONMENT: " + env + " ==="
                + " (config: " + COMMON_PROPERTIES + " + " + envProperties
                + ", test data: data/" + env + "/)");
        System.out.println("    ccpUrl        = " + ConfigUtil.getSecretValue("ccpUrl"));
        System.out.println("    ccpPortalUrl  = " + ConfigUtil.getSecretValue("ccpPortalUrl"));
        System.out.println("    omsBaseUrl    = " + describe("omsBaseUrl"));
        System.out.println("    middlewareUrl = " + describe("middlewareUrl"));
        System.out.println("    sfUrl         = " + describe("sfUrl"));

        checkConfiguredTokenFreshness();
    }

    private void checkConfiguredTokenFreshness() {
        boolean failOnExpired = "true".equalsIgnoreCase(
                String.valueOf(ConfigUtil.getSecretValue("failOnExpiredToken")));

        DataContractValidator.checkTokenFreshness(
                "omsAuthToken", ConfigUtil.getSecretValue("omsAuthToken"), failOnExpired);
    }

    private void loadRequired(String fileName) {
        if (Thread.currentThread().getContextClassLoader().getResource(fileName) == null) {
            throw new IllegalStateException("Configuration file '" + fileName + "' was not found on the"
                    + " classpath. Expected it at src/test/resources/" + fileName);
        }
        Config.loadProperties(fileName);
    }

    private static String describe(String key) {
        String value = ConfigUtil.getSecretValue(key);
        return (value == null || value.trim().isEmpty())
                ? "(not configured for this env)"
                : value;
    }

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
