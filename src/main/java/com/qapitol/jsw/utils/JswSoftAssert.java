package com.qapitol.jsw.utils;

import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

/**
 * SoftAssert that reports WHERE each failure happened.
 *
 * TestNG's SoftAssert collects failures silently and only prints the aggregated messages when
 * assertAll() runs at the end of the scenario - by which point the originating file and line
 * are gone. onAssertFailure() is invoked at the assertion site, so the stack trace captured
 * here still contains the step-definition frame.
 *
 * Purely additive: it logs and delegates to super, so assertion behaviour, failure collection
 * and assertAll() semantics are unchanged.
 */
public class JswSoftAssert extends SoftAssert {

    private static final String PROJECT_PACKAGE = "com.qapitol.jsw";

    @Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
        super.onAssertFailure(assertCommand, ex);
        try {
            System.out.println("[SOFT ASSERT FAILED] at " + originOfFailure()
                    + System.lineSeparator() + "    reason: " + ex.getMessage());
        } catch (Throwable ignored) {
            // Reporting must never break the assertion flow.
        }
    }

    /**
     * First frame belonging to this project, i.e. the step definition or util that made the
     * assertion, formatted as {@code File.java:123 (Class.method)}.
     */
    static String originOfFailure() {
        for (StackTraceElement frame : new Throwable().getStackTrace()) {
            String className = frame.getClassName();
            if (className.startsWith(PROJECT_PACKAGE) && !isSoftAssertPlumbing(className)) {
                return frame.getFileName() + ":" + frame.getLineNumber()
                        + " (" + simpleName(className) + "." + frame.getMethodName() + ")";
            }
        }
        return "unknown location";
    }

    /**
     * True for this project's own soft-assert machinery, which sits between the step definition
     * and the failure and would otherwise be reported as the origin. Covers
     * {@link ThreadLocalSoftAssert} and its nested per-scenario subclass, both of which appear on
     * the stack of every assertion since the shared handle started forwarding per thread.
     */
    private static boolean isSoftAssertPlumbing(String className) {
        return className.equals(JswSoftAssert.class.getName())
                || className.startsWith(ThreadLocalSoftAssert.class.getName());
    }

    private static String simpleName(String className) {
        int lastDot = className.lastIndexOf('.');
        return (lastDot < 0) ? className : className.substring(lastDot + 1);
    }
}
