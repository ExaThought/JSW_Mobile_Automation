package com.qapitol.jsw.utils;

import com.qapitol.sauron.common.configuration.Config;

public class ConfigUtil {

    public static String getSecretValue(String key) {
        String rawValue = Config.getConfigProperty(key);
        if (rawValue == null) {
            return null;
        }

        // If placeholder format is used: ${ENV:default}
        if (rawValue.startsWith("${") && rawValue.endsWith("}")) {
            String content = rawValue.substring(2, rawValue.length() - 1);

            // Split into ENV_VAR and default
            String[] parts = content.split(":", 2);
            String secretName = parts[0];
            String defaultValue = parts.length > 1 ? parts[1] : null;

            // A JVM system property wins, then the environment variable, then the default.
            //
            // -D is supported because the defaults are now empty: every secret has to come from
            // outside, and an OS variable is the awkward way to do that. Exporting six of them
            // before a local run is easy to get wrong, and a Jenkins job that cannot yet be
            // edited can still pass them on the Maven command line:
            //
            //     mvn test -DsuiteXmlFile=... -DQA_CART_PASSWORD=... -DOMS_TOKEN=...
            //
            // System property first so an explicit -D on the command line beats a stale exported
            // value in the shell that invoked it.
            String secretText = System.getProperty(secretName);
            if (secretText == null || secretText.isEmpty()) {
                secretText = System.getenv(secretName);
            }

            // If not present, fallback to default
            rawValue = (secretText != null && !secretText.isEmpty()) ? secretText : defaultValue;
        }
        //  Clean up: trim spaces and strip quotes
        if (rawValue != null) {
            rawValue = rawValue.trim();
            if (rawValue.startsWith("\"") && rawValue.endsWith("\"")) {
                rawValue = rawValue.substring(1, rawValue.length() - 1);
            }
        }

        return rawValue;
    }

    /**
     * Resolves the value for the given key, but throws an exception if nothing is found.
     *
     * <p>The message names the ENVIRONMENT VARIABLE to set, not just the property key. Every
     * secret in global_*.properties now ships with an empty default, so this is the failure
     * operators actually hit - and "Missing configuration for key: omsAuthToken" does not tell
     * anyone that the fix is to export OMS_TOKEN.
     */
    public static String resolveOrThrow(String key) {
        String value = getSecretValue(key);
        if (value == null || value.isEmpty()) {
            String envVar = environmentVariableFor(key);
            throw new IllegalStateException("No value for '" + key + "'."
                    + (envVar == null
                        ? " It is not defined in any global_*.properties for this environment."
                        : " Supply " + envVar + " as an environment variable or -D" + envVar
                          + "=<value> - it ships with an empty default so that secrets are"
                          + " injected rather than committed.")
                    + " See README.md \"Required secrets\".");
        }
        return value;
    }

    /**
     * The environment variable a key reads from, or null when the key is absent or is a literal.
     *
     * <p>Parses the same {@code ${ENV_VAR:default}} shape {@link #getSecretValue(String)} resolves,
     * so the two cannot disagree about which variable backs a key.
     */
    private static String environmentVariableFor(String key) {
        String rawValue = Config.getConfigProperty(key);
        if (rawValue == null || !rawValue.startsWith("${") || !rawValue.endsWith("}")) {
            return null;
        }
        String content = rawValue.substring(2, rawValue.length() - 1);
        String name = content.split(":", 2)[0].trim();
        return name.isEmpty() ? null : name;
    }
}
