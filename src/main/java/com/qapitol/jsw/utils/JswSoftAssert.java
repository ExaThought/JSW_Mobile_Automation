package com.qapitol.jsw.utils;

import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

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

    /** Project frames reported per failure: the assertion site plus its callers. */
    private static final int MAX_FRAMES = 4;

    /**
     * This thread's failures, captured AT THE ASSERTION and printed once at the end of the
     * scenario by {@code TestGroupListener}.
     *
     * <p>Collected here rather than read off the error that {@code assertAll()} throws, because
     * that error's stack trace points at {@code assertAll} in the {@code @After} hook - the step
     * definition that actually failed is long gone from it. {@code onAssertFailure} runs at the
     * assertion site, which is the only moment the real line number exists.
     */
    private static final ThreadLocal<List<String>> FAILURES =
            ThreadLocal.withInitial(ArrayList::new);

    @Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
        super.onAssertFailure(assertCommand, ex);
        try {
            FAILURES.get().add(describeFailure(ex));
        } catch (Throwable ignored) {
            // Reporting must never break the assertion flow.
        }
    }

    /** Starts a fresh collection for the scenario about to run on this thread. */
    static void resetFailures() {
        FAILURES.remove();
    }

    /**
     * Whether this thread's scenario has collected a soft failure yet. Does NOT detach them - the
     * {@code @After} hook asks this before capturing diagnostics, then reports them as usual.
     */
    public static boolean hasFailures() {
        return !FAILURES.get().isEmpty();
    }

    /** This thread's failures so far, detached so nothing can be reported twice. */
    public static List<String> takeFailures() {
        List<String> collected = new ArrayList<>(FAILURES.get());
        FAILURES.remove();
        return collected;
    }

    /** One failure: the reason, then the step definition line and who called it. */
    private static String describeFailure(AssertionError ex) {
        StringBuilder text = new StringBuilder(String.valueOf(ex.getMessage()));
        String label = "at";
        for (String frame : projectFrames(new Throwable().getStackTrace())) {
            text.append(System.lineSeparator()).append("      ").append(label).append(' ').append(frame);
            label = "from";                       // first frame failed; the rest reference it
        }
        return text.toString();
    }

    /**
     * This project's frames, nearest first, as {@code File.java:123 (Class.method)}.
     *
     * <p>Only project frames: a full trace here is thirty lines of TestNG, Cucumber and reflection
     * around the one line anybody needs.
     */
    public static List<String> projectFrames(StackTraceElement[] trace) {
        List<String> frames = new ArrayList<>();
        for (StackTraceElement frame : trace) {
            String className = frame.getClassName();
            if (!className.startsWith(PROJECT_PACKAGE) || isSoftAssertPlumbing(className)) {
                continue;
            }
            frames.add(frame.getFileName() + ":" + frame.getLineNumber()
                    + " (" + simpleName(className) + "." + frame.getMethodName() + ")");
            if (frames.size() == MAX_FRAMES) {
                break;
            }
        }
        return frames;
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
