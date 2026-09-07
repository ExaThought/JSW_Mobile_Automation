package com.qapitol.jsw.config;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestGroupListener implements ITestListener, ISuiteListener {

    private static final String PROJECT_PACKAGE = "com.qapitol.jsw";

    /**
     * Fires once, on the main thread, before any {@code <test>} block starts.
     *
     * <p>ISuiteListener rather than ITestListener deliberately: {@code ITestListener.onStart} runs
     * once per {@code <test>}, and with {@code parallel="tests"} those run concurrently - too late
     * and too racy to install a guard that exists to prevent a race.
     */
    @Override
    public void onStart(ISuite suite) {
        GridInstanceListGuard.apply();
    }

    /**
     * Prints where a failure originated inside project code. TestNG's default console output
     * shows the assertion message but the useful frames are buried under Cucumber/TestNG
     * internals, so the project frames are surfaced here.
     *
     * Reporting only - it never rethrows, so scenario outcomes are unaffected.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        try {
            Throwable throwable = result.getThrowable();
            if (throwable == null) {
                return;
            }

            System.out.println("[FAILED] " + describeScenario(result));
            System.out.println("    " + throwable.getClass().getSimpleName()
                    + ": " + throwable.getMessage());

            int printed = 0;
            for (StackTraceElement frame : throwable.getStackTrace()) {
                if (frame.getClassName().startsWith(PROJECT_PACKAGE)) {
                    System.out.println("    at " + frame.getFileName() + ":" + frame.getLineNumber()
                            + " (" + frame.getClassName() + "." + frame.getMethodName() + ")");
                    if (++printed == 5) {
                        break;
                    }
                }
            }
            if (printed == 0) {
                System.out.println("    (no " + PROJECT_PACKAGE + " frame in the stack trace)");
            }
        } catch (Throwable ignored) {
            // Reporting must never break the run.
        }
    }

    private String describeScenario(ITestResult result) {
        Object[] parameters = result.getParameters();
        if (parameters != null && parameters.length > 0 && parameters[0] != null) {
            return String.valueOf(parameters[0]);
        }
        return result.getName();
    }

    @Override
    public void onStart(ITestContext context) {
        String group = context.getCurrentXmlTest().getParameter("test.group");
        if (group != null && !group.isBlank()) {
            TestGroupContext.set(group);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        TestGroupContext.clear();
    }
}
