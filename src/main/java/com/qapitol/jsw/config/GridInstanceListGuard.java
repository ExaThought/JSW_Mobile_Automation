package com.qapitol.jsw.config;

import com.qapitol.sauron.core.Grid;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Works around a data race inside {@code com.qapitol.sauron.core.Grid} that only appears when the
 * suite runs with {@code thread-count} greater than 1.
 *
 * <h2>The bug</h2>
 * Sauron 4.0.6 registers every per-thread {@code Grid} in a plain {@code ArrayList}, and it does so
 * from inside the {@code ThreadLocal} initialiser - i.e. on each worker thread, the first time that
 * thread touches {@code Grid}. Decompiled from {@code core-4.0.6-SNAPSHOT.jar}:
 *
 * <pre>
 * globalGridInstances = new ArrayList&lt;&gt;();
 * instance = ThreadLocal.withInitial(() -&gt; {
 *     Grid g = new Grid();
 *     globalGridInstances.add(g);   // unsynchronised, called from every worker thread
 *     return g;
 * });
 * </pre>
 *
 * Concurrent {@code ArrayList.add} may lose entries, leave nulls, or throw
 * {@code ArrayIndexOutOfBoundsException} while it grows its backing array.
 *
 * <h2>What was actually measured</h2>
 * Against {@code core-4.0.6-SNAPSHOT.jar}, 64 threads calling {@code Grid.getInstance()} at once:
 *
 * <ul>
 *   <li>unguarded - 60, 61, 61, 63, 63 entries landed out of 64, across five runs. Lost writes
 *       reproduce every time.</li>
 *   <li>guarded - 64 of 64, five runs, no thread failed.</li>
 * </ul>
 *
 * <p>Be clear about what that does and does not justify. <strong>The lost writes are harmless</strong>:
 * nothing reads this list. {@code getAllGridInstances()} has no caller in this repository and none
 * in any Sauron 4.0.6 jar, so it is write-only. The failure mode that would actually hurt is the
 * throw - it happens <em>inside</em> the {@code ThreadLocal} initialiser, so {@code Grid.getInstance()}
 * and therefore {@code Grid.driver()} fail on that thread, and the scenario dies at ramp-up with an
 * {@code ArrayIndexOutOfBoundsException} naming nothing about the test. That throw was <em>not</em>
 * observed in the runs above; it is a documented hazard of unsynchronised {@code ArrayList.add},
 * not something reproduced here, and 64 threads is far above the 5 this suite would realistically
 * use.
 *
 * <p>So treat this as cheap insurance against a low-probability, high-confusion failure, not as a
 * fix for something known to be biting. The honest summary: one line of reflection removes a real
 * race whose only observed symptom is currently inconsequential.
 *
 * <h2>What this does</h2>
 * Replaces the field with a {@link Collections#synchronizedList} wrapper around its current
 * contents, once, on the main thread, before any worker thread exists. Safe because the
 * {@code ThreadLocal} supplier re-reads the field on every call ({@code getstatic} immediately
 * before {@code List.add} in the bytecode), so later adds go through the wrapper.
 *
 * <p>It never throws. If the field is renamed or removed by a future Sauron release, this logs a
 * warning and the suite runs exactly as it does today.
 *
 * <h2>Delete this class when</h2>
 * Sauron makes {@code globalGridInstances} thread-safe, or drops it. Check with:
 * {@code javap -p -c -cp core-<version>.jar com.qapitol.sauron.core.Grid}. If the static
 * initialiser no longer calls {@code ArrayList.<init>} for that field, this guard is dead weight.
 */
public final class GridInstanceListGuard {

    /** Sauron's private static registry of per-thread Grid instances. */
    private static final String FIELD_NAME = "globalGridInstances";

    private static final AtomicBoolean APPLIED = new AtomicBoolean(false);

    private GridInstanceListGuard() {
    }

    /**
     * Makes Sauron's Grid registry thread-safe. Idempotent, and safe to call when it cannot help.
     *
     * <p>Must run before any worker thread calls {@code Grid.getInstance()}, which is why it is
     * driven from {@code ISuiteListener.onStart} rather than {@code ITestListener.onStart} - the
     * latter fires once per {@code <test>} block, and with {@code parallel="tests"} those fire
     * concurrently, which would race this guard itself.
     */
    public static void apply() {
        if (!APPLIED.compareAndSet(false, true)) {
            return;
        }
        try {
            Field field = Grid.class.getDeclaredField(FIELD_NAME);
            field.setAccessible(true);

            if (Modifier.isFinal(field.getModifiers())) {
                // Not the case in 4.0.6, but a future release could make it final, and rather than
                // fight that reflectively we leave the field alone and say so.
                warn("Grid." + FIELD_NAME + " is final in this Sauron build - leaving it alone.");
                return;
            }

            Object current = field.get(null);
            if (!(current instanceof List)) {
                warn("Grid." + FIELD_NAME + " is not a List ("
                        + (current == null ? "null" : current.getClass().getName())
                        + ") - the guard no longer matches the framework.");
                return;
            }

            @SuppressWarnings("rawtypes")
            List existing = (List) current;

            // Reading `existing` unsynchronised is safe here and only here: this runs on the main
            // thread at suite start, before TestNG has created a worker.
            @SuppressWarnings({"unchecked", "rawtypes"})
            List replacement = Collections.synchronizedList(new ArrayList(existing));

            field.set(null, replacement);
            System.out.println("[grid-guard] Grid." + FIELD_NAME
                    + " wrapped in a synchronized list (" + existing.size()
                    + " existing entr" + (existing.size() == 1 ? "y" : "ies")
                    + "). Guards a Sauron 4.0.6 race that surfaces at thread-count > 1.");

        } catch (NoSuchFieldException e) {
            warn("Grid has no field '" + FIELD_NAME + "' in this Sauron build - the race this"
                    + " guarded is probably fixed upstream. This class can likely be deleted.");
        } catch (Throwable t) {
            // Never break the run for a defensive guard.
            warn("could not apply: " + t.getClass().getSimpleName() + ": " + t.getMessage());
        }
    }

    private static void warn(String message) {
        System.out.println("[grid-guard] WARNING - " + message);
        System.out.println("[grid-guard] Running unguarded. At thread-count 1 this is harmless;"
                + " above it, a worker may fail at start-up with ArrayIndexOutOfBoundsException"
                + " from inside Grid's ThreadLocal initialiser. See GridInstanceListGuard.");
    }
}
