package com.qapitol.jsw.utils;

import com.qapitol.jsw.config.TestGroupContext;
import com.qapitol.sauron.common.configuration.Config;
import org.testng.ITestResult;
import org.testng.Reporter;

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
        String group = TestGroupContext.get();
        if (group == null || group.isBlank()) {
            group = readGroupFromCurrentTest();
        }
        if (group == null || group.isBlank()) {
            group = Config.getConfigProperty("test.group");
        }
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

    private static String readGroupFromCurrentTest() {
        try {
            ITestResult result = Reporter.getCurrentTestResult();
            if (result != null && result.getTestContext() != null
                    && result.getTestContext().getCurrentXmlTest() != null) {
                String group = result.getTestContext().getCurrentXmlTest().getParameter("test.group");
                if (group != null && !group.isBlank()) {
                    TestGroupContext.set(group);
                    return group;
                }
            }
        } catch (Throwable ignore) {
            // Reporter may not be available outside an active test method
        }
        return null;
    }
}
