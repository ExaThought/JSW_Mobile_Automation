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
            // First try environment variable
            String secretText = System.getenv(secretName);

            // If not present, fallback to default
            rawValue = secretText != null ? secretText : defaultValue;
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
     */
    public static String resolveOrThrow(String key) {
        String value = getSecretValue(key);
        if (value == null || value.isEmpty()) {
            throw new IllegalStateException(" Missing configuration for key: " + key);
        }
        return value;
    }
}
