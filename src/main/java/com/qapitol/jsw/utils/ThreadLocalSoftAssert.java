package com.qapitol.jsw.utils;

import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

/**
 * The soft assert behind {@link Hooks#softAssert}: ONE shared handle, but a SEPARATE collection
 * of failures per thread.
 *
 * <p>WHY THIS EXISTS. {@code Hooks.softAssert} used to be a mutable {@code public static} field
 * holding one {@link JswSoftAssert} for the whole JVM: {@code @Before} replaced it and
 * {@code @After} called {@code assertAll()} on it. That is safe only while exactly one scenario
 * is in flight. With {@code parallel="tests"} and a thread-count above 1, several scenarios run
 * at once and they all see the same object, so:
 *
 * <ul>
 *   <li>one scenario's {@code @Before} REPLACES the instance another scenario is still
 *       collecting into, and every failure gathered so far is discarded;</li>
 *   <li>{@code assertAll()} in one scenario's {@code @After} reports - and fails on - failures
 *       raised by a different scenario, on a different thread, in a different browser;</li>
 *   <li>TestNG's {@code SoftAssert} keeps its failures in a plain {@code LinkedHashMap}, which
 *       concurrent writers can corrupt outright.</li>
 * </ul>
 *
 * <p>The visible symptom is the dangerous one: failures attributed to the wrong scenario, or
 * silently lost, i.e. a green run that is not green. This class removes that by making the state
 * per-thread while leaving the field itself shared, so the ~590 existing
 * {@code softAssert.assertTrue(...)} call sites keep working untouched. TestNG runs each
 * {@code <test>} block on its own thread and Cucumber runs the scenario on that same thread, so
 * "per thread" is "per scenario in flight".
 *
 * <p>Assertions raised on a thread other than the one running the scenario (an async callback,
 * say) land in that thread's own collection, which nothing calls {@code assertAll()} on. Nothing
 * in this suite asserts off-thread today; if that changes, such a failure would be dropped rather
 * than misreported.
 */
public final class ThreadLocalSoftAssert extends SoftAssert {

    /** The calling thread's own collection of failures. */
    private static final ThreadLocal<ScenarioSoftAssert> CURRENT =
            ThreadLocal.withInitial(ScenarioSoftAssert::new);

    /**
     * Starts a fresh collection for the scenario about to run on this thread - the direct
     * replacement for the old {@code softAssert = new JswSoftAssert()} in {@code @Before}.
     */
    static void startScenario() {
        CURRENT.set(new ScenarioSoftAssert());
        // Also drop any failure descriptions left on this worker thread. TestNG reuses threads,
        // so without this a scenario could report the previous one's failures as its own.
        JswSoftAssert.resetFailures();
    }

    /**
     * Every {@code assertTrue}/{@code assertEquals}/{@code fail}/... inherited from TestNG's
     * {@code Assertion} funnels through here, so forwarding this one method is enough to route
     * the whole API into the calling thread's collection.
     */
    @Override
    protected void doAssert(IAssert<?> assertCommand) {
        CURRENT.get().doAssert(assertCommand);
    }

    @Override
    public void assertAll() {
        takeCurrent().assertAll();
    }

    @Override
    public void assertAll(String message) {
        takeCurrent().assertAll(message);
    }

    /**
     * The thread's collection, detached before it is asserted on. Detaching first means the
     * {@code AssertionError} that {@code assertAll()} throws cannot leave a spent collection
     * behind for the next scenario that TestNG happens to run on this same worker thread.
     */
    private static ScenarioSoftAssert takeCurrent() {
        ScenarioSoftAssert scenario = CURRENT.get();
        CURRENT.remove();
        return scenario;
    }

    /**
     * One scenario's failures, with the file-and-line reporting of {@link JswSoftAssert}.
     *
     * <p>The only thing it adds is visibility: {@code doAssert} is {@code protected} in
     * {@code org.testng.asserts.SoftAssert}, and Java lets a subclass reach a protected member
     * of another package only through a reference of its OWN type - so the shared
     * {@code ThreadLocalSoftAssert} above could not forward into a plain {@code JswSoftAssert}.
     * Widening the override to {@code public} here opens exactly that one door. Behaviour is
     * unchanged: it calls {@code super}.
     */
    private static final class ScenarioSoftAssert extends JswSoftAssert {
        @Override
        public void doAssert(IAssert<?> assertCommand) {
            super.doAssert(assertCommand);
        }
    }
}
