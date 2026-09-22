package com.qapitol.jsw.utils;

import org.testng.ITestResult;
import org.testng.Reporter;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Carries a scenario's soft-assertion failures from the {@code @After} hook, where they are
 * captured, to the TestNG listener that prints the end-of-scenario block.
 *
 * <p>WHY A HANDOVER AT ALL. The two halves of the report live in different places and neither can
 * do the other's job:
 *
 * <ul>
 *   <li>the file and line of a soft assertion exist only at the assertion itself, so
 *       {@link JswSoftAssert} captures them there;</li>
 *   <li>the throwable that failed the scenario exists only on TestNG's {@code ITestResult}, which
 *       the Cucumber hook cannot see - it is set after the hook returns.</li>
 * </ul>
 *
 * <p>Keyed by {@code ITestResult} rather than by thread. The hook and the listener do run on the
 * same thread today, but that is an implementation detail of TestNG's invoker; the result object
 * is the one thing both sides are definitionally looking at.
 *
 * <p>Entries are removed when read, and a scenario that raises no soft failures stores nothing,
 * so the map does not grow over a run.
 */
public final class SoftFailureReport {

    private static final Map<ITestResult, List<String>> PENDING = new ConcurrentHashMap<>();

    private SoftFailureReport() {
        // utility class
    }

    /** Stores this scenario's failures against the TestNG result currently in flight. */
    static void handOver(List<String> failures) {
        if (failures == null || failures.isEmpty()) {
            return;
        }
        try {
            ITestResult result = Reporter.getCurrentTestResult();
            if (result != null) {
                PENDING.put(result, failures);
            }
        } catch (Throwable ignored) {
            // Reporting must never break teardown.
        }
    }

    /** The failures recorded for this result, removed as they are read. Never null. */
    public static List<String> take(ITestResult result) {
        if (result == null) {
            return Collections.emptyList();
        }
        List<String> failures = PENDING.remove(result);
        return failures == null ? Collections.emptyList() : failures;
    }

    /** Drops anything stored for a result, for the passing path. */
    public static void discard(ITestResult result) {
        if (result != null) {
            PENDING.remove(result);
        }
    }
}
