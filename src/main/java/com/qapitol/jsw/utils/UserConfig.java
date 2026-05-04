package com.qapitol.jsw.utils;

import com.qapitol.sauron.common.configuration.Config;

import java.util.Set;

public class UserConfig {

    private static final Set<String> VALID_GROUPS = Set.of("homepage", "cart", "profile", "orders", "msite");

    public static String getEmail() {
        return ConfigUtil.getSecretValue(emailKey());
    }

    public static String getPassword() {
        return ConfigUtil.getSecretValue(passwordKey());
    }

    public static String getEmail(String group) {
        return ConfigUtil.getSecretValue(group + ".user.email");
    }

    public static String getPassword(String group) {
        return ConfigUtil.getSecretValue(group + ".user.password");
    }

    private static String getGroup() {
        String group = Config.getConfigProperty("test.group");
        if (group == null || group.isBlank()) {
            throw new IllegalStateException(
                "test.group parameter is not set in testng XML. Valid values: " + VALID_GROUPS
            );
        }
        if (!VALID_GROUPS.contains(group)) {
            throw new IllegalStateException(
                "Unknown test.group '" + group + "'. Valid values: " + VALID_GROUPS
            );
        }
        return group;
    }

    private static String emailKey() {
        return getGroup() + ".user.email";
    }

    private static String passwordKey() {
        return getGroup() + ".user.password";
    }
}
