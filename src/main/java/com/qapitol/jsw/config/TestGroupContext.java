package com.qapitol.jsw.config;

import org.testng.ITestResult;
import org.testng.Reporter;

public final class TestGroupContext {
    private static final ThreadLocal<String> CURRENT = new ThreadLocal<>();

    private TestGroupContext() {}

    public static void set(String group) {
        CURRENT.set(group);
    }

    public static String get() {
        return CURRENT.get();
    }

    public static void clear() {
        CURRENT.remove();
    }

    /**
     * The test.group of the {@code <test>} block whose scenario is running on this thread, else
     * the thread's stored group, else null.
     *
     * <p>Read the running test first. Scenarios run on TestNG's data-provider threads, which are
     * reused across {@code <test>} blocks; a group stored on one of them by an earlier block
     * survives into the next, and build #272 then ran every Ledger scenario as the Login block's
     * user (testautomation01) - in the Login block's browser profile, since
     * {@link JSWCapabilitiesBuilder} names profiles after the group. The running test's XML
     * parameter cannot be stale.
     */
    public static String resolve() {
        String group = groupOfRunningTest();
        return (group != null && !group.isBlank()) ? group : CURRENT.get();
    }

    private static String groupOfRunningTest() {
        try {
            ITestResult result = Reporter.getCurrentTestResult();
            if (result != null && result.getTestContext() != null
                    && result.getTestContext().getCurrentXmlTest() != null) {
                return result.getTestContext().getCurrentXmlTest().getParameter("test.group");
            }
        } catch (RuntimeException ignored) {
            // No active TestNG test on this thread (suite setup, an IDE main method).
        }
        return null;
    }
}
