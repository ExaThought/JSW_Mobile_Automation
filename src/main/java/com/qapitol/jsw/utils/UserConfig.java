package com.qapitol.jsw.utils;

import com.qapitol.jsw.config.EnvConfig;
import com.qapitol.jsw.config.TestGroupContext;
import com.qapitol.sauron.common.configuration.Config;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.util.Set;

public class UserConfig {

    /**
     * Groups that may appear as a {@code test.group} suite parameter. Every entry needs matching
     * {@code <group>.user.email} / {@code <group>.user.password} keys in EVERY
     * {@code global_<env>.properties}, or the run fails at login with a null credential.
     *
     * Note "secondary" is intentionally absent: the properties define secondary.user.* but no
     * suite runs under it, and adding it here without a suite would suggest otherwise.
     */
    private static final Set<String> VALID_GROUPS =
            Set.of("homepage", "cart", "profile", "orders", "msite", "Guest", "tokencheck");

    public static String getEmail() {
        return requireGroupValue(emailKey());
    }

    public static String getPassword() {
        return requireGroupValue(passwordKey());
    }

    public static String getEmail(String group) {
        return requireGroupValue(group + ".user.email");
    }

    public static String getPassword(String group) {
        return requireGroupValue(group + ".user.password");
    }

    /**
     * A group credential, or a failure naming what is missing and where to put it.
     *
     * <p>{@link #getGroup()} validates that the group NAME is known, but nothing checked that the
     * group's credentials actually resolve. Two ways that bit:
     *
     * <ul>
     *   <li>a group in {@link #VALID_GROUPS} and in a suite XML but with no
     *       {@code <group>.user.*} keys returned null, which surfaced three frames later as a
     *       bare {@code NullPointerException} in {@code SessionSteps.ccpLogin}</li>
     *   <li>now that every password ships as {@code ${ENV_VAR:}}, an unset variable resolves to
     *       the EMPTY STRING - so the suite Base64-encodes nothing, posts a blank password and
     *       reports HTTP 401. The 401 names no key and looks like a bad account rather than
     *       absent configuration.</li>
     * </ul>
     *
     * <p>Delegates to {@link ConfigUtil#resolveOrThrow(String)} so the environment variable is
     * named by the one place that knows how the placeholder is parsed, then adds the group
     * context that call site cannot know.
     */
    private static String requireGroupValue(String key) {
        try {
            return ConfigUtil.resolveOrThrow(key);
        } catch (IllegalStateException e) {
            throw new IllegalStateException(e.getMessage()
                    + " This is a test.group credential read from "
                    + EnvConfig.propertiesFileFor(EnvConfig.current())
                    + "; every group named in a suite XML needs both <group>.user.email and"
                    + " <group>.user.password to resolve.", e);
        }
    }

    /**
     * Named accounts for the login, registration and forgot-password suites.
     *
     * <p>These exist so a feature file can name the ACCOUNT it needs rather than carry the
     * credential itself. A password committed in Gherkin is a password in every clone, every
     * diff and every CI log, and it silently pins the suite to one environment - the same
     * address does not necessarily exist on both QA and pre-prod.
     *
     * <p>Values live in {@code account.<alias>.*} in each {@code global_<env>.properties}, so
     * switching environment switches account with no change to the feature.
     */
    public static String getAccountEmail(String alias) {
        return requireAccountValue("account." + alias + ".email", alias);
    }

    public static String getAccountPassword(String alias) {
        return requireAccountValue("account." + alias + ".password", alias);
    }

    /** A deliberately wrong password, for proving that a bad credential is rejected. */
    public static String getAccountWrongPassword(String alias) {
        return requireAccountValue("account." + alias + ".wrongPassword", alias);
    }

    /** Arbitrary account-scoped value, e.g. account.existing.mobile. */
    public static String getAccountValue(String alias, String field) {
        return requireAccountValue("account." + alias + "." + field, alias);
    }

    /**
     * Fails naming the property and the environment rather than returning null.
     *
     * <p>A null credential surfaces as an unrelated "element not interactable" or a login that
     * quietly submits an empty field, neither of which points at the missing property.
     */
    private static String requireAccountValue(String key, String alias) {
        String value = ConfigUtil.getSecretValue(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                "No value for '" + key + "'. Add it to global_<env>.properties for every"
                    + " environment, or set the matching environment variable."
                    + " Account alias requested: '" + alias + "'.");
        }
        return value;
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
