package com.qapitol.jsw.config;

import com.qapitol.jsw.utils.JswSoftAssert;
import com.qapitol.jsw.utils.SoftFailureReport;
import com.qapitol.jsw.utils.TagsOverrideGuard;
import org.testng.IDataProviderInterceptor;
import org.testng.IDataProviderMethod;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.util.Iterator;
import java.util.List;

public class TestGroupListener implements ITestListener, ISuiteListener, IDataProviderInterceptor {

    /**
     * Under a Jenkins TAGS override, keeps only the scenarios this {@code <test>} block owns, so
     * each one runs once and with its own block's account; scenarios whose block is commented out
     * are dropped with a warning. Removed before TestNG creates invocations, so they never appear
     * in the report or console - not even as skipped. No override, no change. See
     * {@link TagsOverrideGuard}. Registered here because every suite that has more than one
     * {@code <test>} block already lists this listener.
     */
    @Override
    public Iterator<Object[]> intercept(Iterator<Object[]> original, IDataProviderMethod dataProviderMethod,
                                        ITestNGMethod method, ITestContext iTestContext) {
        return TagsOverrideGuard.filter(original, iTestContext);
    }

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
        // Also here for the same reason: every block's filter must be rewritten before the first
        // block reads it.
        RunTagsFilter.apply(suite);
        // After RunTagsFilter, which can replace a block's filter outright.
        EnvTagsFilter.apply(suite);
    }

    /**
     * Prints the cause of a failure and closes the scenario block.
     *
     * <p>The whole end-of-scenario output is here and nowhere else: one CAUSE section, then one
     * SCENARIO FAILED line. Two kinds of failure reach this method and they are reported from
     * different places, because only one of them carries a usable stack trace:
     *
     * <ul>
     *   <li>SOFT assertion failures. The throwable is the aggregate one {@code assertAll()} threw
     *       in the {@code @After} hook, and its trace points at that hook - useless. The real
     *       lines were captured at each assertion by {@link JswSoftAssert} and arrive through
     *       {@link SoftFailureReport}.</li>
     *   <li>HARD exceptions thrown by a step. Nothing intercepts these, so the throwable's own
     *       trace is the right source; only this project's frames are printed.</li>
     * </ul>
     *
     * <p>Reporting only - it never rethrows, so scenario outcomes are unaffected.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        try {
            List<String> softFailures = SoftFailureReport.take(result);
            if (!softFailures.isEmpty()) {
                for (String failure : softFailures) {
                    System.out.println("[CAUSE] " + failure);
                }
            } else {
                printThrowable(result.getThrowable());
            }
        } catch (Throwable ignored) {
            // Reporting must never break the run.
        } finally {
            System.out.println("=== SCENARIO FAILED ===");
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        SoftFailureReport.discard(result);
        System.out.println("=== SCENARIO PASSED ===");
    }

    /**
     * Skipped is neither passed nor failed, and staying silent about it would leave a scenario
     * block with no closing line at all - which reads as a run that hung.
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        SoftFailureReport.discard(result);
        System.out.println("=== SCENARIO SKIPPED ===");
    }

    /** The exception message, then the step definition line and who called it. */
    private void printThrowable(Throwable throwable) {
        if (throwable == null) {
            return;
        }
        System.out.println("[CAUSE] " + throwable.getClass().getSimpleName()
                + ": " + throwable.getMessage());

        List<String> frames = JswSoftAssert.projectFrames(throwable.getStackTrace());
        String label = "at";
        for (String frame : frames) {
            System.out.println("      " + label + " " + frame);
            label = "from";                       // first frame failed; the rest reference it
        }
        if (frames.isEmpty()) {
            System.out.println("      (no " + PROJECT_PACKAGE + " frame in the stack trace)");
        }
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
