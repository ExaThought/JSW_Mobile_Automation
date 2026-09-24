package com.qapitol.jsw.config;

import com.qapitol.sauron.common.sessions.AbstractTestSession;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.sessions.WebTestSession;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

import static com.qapitol.sauron.web.WebActionListener.log;

/**
 * Everything to do with getting a usable browser onto the current thread.
 *
 * <p>Extracted from {@code SessionSteps}, which had grown to roughly 350 lines of grid plumbing
 * around a handful of login steps. The two concerns have nothing in common: a login step is about
 * the product, while everything here is about Sauron, the Selenium hub and thread lifecycle. They
 * also change for entirely different reasons - a redesigned login page and a saturated Jenkins
 * agent are unrelated events, and neither should force a reader through the other's code.
 *
 * <p>All state is static because the thing being guarded is process-wide. Two distinct scopes,
 * deliberately:
 *
 * <ul>
 *   <li>SUITE-WIDE - {@link #gridFailure} and {@link #sessionEverStarted}. A grid that never came
 *       up dooms every thread equally, so that verdict is shared.</li>
 *   <li>PER-THREAD - {@link #SESSION_START_FAILURE}. The suite runs several scenarios at once, so
 *       a shared field would report one thread's failure against another thread's scenario.</li>
 * </ul>
 *
 * <p>Entry points, in the order a caller normally wants them:
 * <pre>
 *   open(url)        session + driver + window sizing + navigate    - what a Background needs
 *   awaitDriverReady()  session + driver only                       - no navigation
 *   consumeStartFailure()  the failure text for this thread's report
 * </pre>
 */
public final class SessionBuilder {

    private SessionBuilder() {
        // utility class
    }

    // =================================================================================
    // Budgets
    // =================================================================================

    /** Hard ceiling across every attempt, so a wedged grid cannot hold a thread indefinitely. */
    private static final long SESSION_START_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(120);
    /** Poll interval while waiting for our own or a concurrent start to complete. */
    private static final long SESSION_START_POLL_MILLIS = 500;
    /** Attempts at the WHOLE start-and-verify sequence, not just startSession() invocations. */
    private static final int MAX_SESSION_ATTEMPTS = 3;
    /** Budget for one pass through all four stages. 3 x 35s + 2 x 10s backoff fits the ceiling. */
    private static final long SESSION_ATTEMPT_BUDGET_MILLIS = TimeUnit.SECONDS.toMillis(35);
    private static final long SESSION_START_BACKOFF_MILLIS = TimeUnit.SECONDS.toMillis(10);
    private static final long POISONED_SESSION_GRACE_MILLIS = TimeUnit.SECONDS.toMillis(3);

    private static final long SESSION_READY_POLL_MILLIS = 125;

    /**
     * COLD budget - nothing has started a session yet, so the hub may still be starting OR the grid
     * may be dead. Must exceed hub spawn on the slowest agent; ~41s was measured on the 2-CPU
     * Jenkins agent in build #244, so this leaves room without being open-ended.
     *
     * <p>Paid at most once per run: the latch short-circuits every later scenario. A genuinely dead
     * grid therefore costs this once, which is nothing against a 50-minute build - and in exchange
     * a slow hub no longer writes off the entire run.
     */
    private static final long COLD_BUDGET_MILLIS = 90_000;

    /**
     * WARM budget - a session HAS started in this run, so the grid demonstrably works and this
     * scenario is queuing for a browser slot rather than facing a dead grid.
     *
     * <p>Sized from Jenkins build #244: the agent reported 2 processors and Selenium sized the node
     * to 2 Chrome slots while the suite ran more scenarios than that at once. A queued scenario
     * legitimately waits far longer than the cold budget.
     */
    private static final long WARM_BUDGET_MILLIS = 120_000;

    /**
     * How many scenarios in a row may exhaust the WARM budget before the grid is declared dead.
     *
     * <p>Without this, a grid that dies mid-run after some successes would make every remaining
     * scenario pay the full warm budget. Three strikes bounds that at ~6 minutes while still
     * absorbing the single isolated timeout that build #244 turned into 43 write-offs.
     */
    private static final int WARM_FAILURES_BEFORE_LATCH = 3;

    /** Viewport used when the browser cannot be maximised, and in headless. */
    private static final Dimension DEFAULT_WINDOW = new Dimension(1920, 1080);
    public static final Duration IMPLICIT_WAIT = Duration.ofSeconds(10);

    /**
     * Page zoom applied once the window has been sized. Chrome starts every automation profile at
     * 100%; this brings it to 90% so more of the page fits the viewport.
     *
     * <p>This is CSS zoom on the document element, not Chrome's own Ctrl+- browser zoom - the
     * W3C WebDriver protocol has no command for the latter. The visible effect is the same, but
     * it lives in the DOCUMENT, so it is discarded by every navigation and every refresh. That is
     * why {@link #applyZoom()} is public and is re-applied after navigation rather than set once.
     */
    private static final String ZOOM_LEVEL = "90%";

    // =================================================================================
    // State
    // =================================================================================

    private static volatile String gridFailure;

    /**
     * Why the grid was latched, in words, for the skip message. Stating the condition that fired
     * matters: "failed for 3 scenarios in a row (or never came up at all)" reads as though three
     * scenarios failed even in the cold case, where only the first one did.
     */
    private static volatile String gridFailureReason;

    /**
     * True once ANY scenario in this run has obtained a usable driver.
     *
     * <p>This is what separates "the grid never came up" from "this scenario could not get a slot
     * in time". Build #244 created 131 sessions and passed 58 scenarios over 34 healthy minutes,
     * then one scenario exhausted its wait, the latch fired suite-wide, and the remaining 43 were
     * written off against a grid that was still working.
     */
    private static volatile boolean sessionEverStarted;

    /**
     * Consecutive scenarios that have exhausted the WARM budget. Reset by any success.
     *
     * <p>Atomic, not a volatile int: several scenarios can time out at the same moment, and
     * {@code ++} on a volatile field is a read-modify-write that silently loses increments under
     * exactly that concurrency.
     */
    private static final AtomicInteger CONSECUTIVE_WARM_FAILURES = new AtomicInteger();

    /**
     * The session-start failure for the scenario running on THIS thread, read back by
     * {@code Hooks} so the real cause reaches the Allure report rather than only the console.
     */
    private static final ThreadLocal<String> SESSION_START_FAILURE = new ThreadLocal<>();

    // =================================================================================
    // Public entry points
    // =================================================================================

    /**
     * Brings up a usable browser on this thread and navigates it to {@code url}.
     *
     * <p>This is the whole Background-level concern in one call: session, driver, window sizing,
     * navigation. Callers get a browser sitting on the page, or a diagnosed exception.
     */
    /**
     * Post-condition for {@link #open(String)}: a Sauron test session must be bound to the thread
     * that is about to run the steps.
     *
     * <p>Without this the failure surfaces much later as a bare {@code NullPointerException} with
     * no message, thrown from inside Sauron:
     *
     * <pre>
     *   java.lang.NullPointerException
     *     at AbstractPage.lambda$new$0(AbstractPage.java:249)
     *     at Page.getLocator(Page.java:31)
     *     at AbstractPage.getLocator(AbstractPage.java:149)
     * </pre>
     *
     * <p>Line 249 is the {@code supplySiteLocale} supplier, which does
     * {@code Grid.getTestSession().getSiteLocale()} with no null check. {@code getTestSession()}
     * resolves through a ThreadLocal - {@code getTestSession(getSessionName())} - and returns null
     * whenever no session has been STARTED on the executing thread. Binding the name alone is not
     * enough: {@code Grid.setSessionName("ccp")} followed by {@code Grid.getTestSession()} still
     * returns null, verified directly, because the lookup needs a started session, not a name.
     *
     * <p>So the NPE is neither a missing locator file nor a null {@code page} - both were checked
     * and are fine. It means "no browser session on this thread", and the only reason it reads as
     * a locator problem is that resolving a locator is the first thing that touches the session.
     *
     * <p>In the 22 Sep 2026 run this accounted for 16 of 22 failures, every one of them in the
     * contiguous tail: once the condition appeared, no later scenario recovered.
     */
    private static void requireBoundTestSession() {
        if (Grid.getTestSession() == null) {
            String diagnosis = DIAGNOSIS_PREFIX
                    + " No Sauron test session is bound to thread '" + Thread.currentThread().getName()
                    + "' (session name '" + Grid.getSessionName() + "'). The driver is up, but"
                    + " Grid.getTestSession() is null, so the first locator lookup would throw a"
                    + " bare NullPointerException from AbstractPage.lambda$new$0 instead of"
                    + " reporting this. Check that the scenario ran 'Given Go to session \"ccp\"'"
                    + " on this thread and that nothing tore the session down mid-scenario.";
            recordSessionStartFailure(diagnosis);
            log.log(Level.SEVERE, diagnosis);
            throw new IllegalStateException(diagnosis);
        }
    }

    public static void open(String url) {
        log.entering();
        try {
            awaitDriverReady();
            requireBoundTestSession();
            Grid.driver().manage().timeouts().implicitlyWait(IMPLICIT_WAIT);
            sizeWindow();
            Grid.driver().get(url);
            log.info("Successfully navigated to URL: " + url);
            awaitDocumentComplete();
            // sizeWindow() already zoomed, but that was on about:blank - this navigation replaced
            // the document and reset it to 100%. This is the call that actually leaves the
            // Background sitting on a 90% page.
            applyZoom();
        } catch (Exception e) {
            // Already diagnosed AND recorded by awaitDriverReady, which throws from inside this
            // try. Re-wrapping printed the whole diagnosis paragraph twice.
            if (e instanceof IllegalStateException
                    && String.valueOf(e.getMessage()).startsWith(DIAGNOSIS_PREFIX)) {
                throw (IllegalStateException) e;
            }
            String diagnosis = diagnose(e);
            recordSessionStartFailure(diagnosis);
            log.log(Level.SEVERE, diagnosis, e);
            throw new IllegalStateException(diagnosis, e);
        }
        log.exiting();
    }

    /** True when this thread already holds a started session with a usable driver. */
    public static boolean isReady() {
        AbstractTestSession session = Grid.getTestSession();
        return session != null && session.isStarted() && isDriverUsable(session.getDriver());
    }

    /**
     * The session-start failure recorded on this thread, or null. Clears it, so a later scenario
     * that started its browser fine is not annotated with an earlier scenario's failure.
     */
    public static String consumeStartFailure() {
        String failure = SESSION_START_FAILURE.get();
        SESSION_START_FAILURE.remove();
        return failure;
    }

    // =================================================================================
    // Driver readiness - the passive budget, latch and diagnosis
    // =================================================================================

    /**
     * Waits for a usable WebDriver on a budget that depends on what the grid has already proved.
     *
     * <p>TWO BUDGETS, because "no driver yet" has two completely different causes and one timeout
     * cannot serve both: a COLD run may be waiting on a hub that is still spawning, while a WARM
     * one is queuing for a browser slot behind other scenarios. See the budget constants above.
     *
     * <p>The suite-wide latch is conditional for the same reason. A grid that never came up dooms
     * every scenario and should short-circuit the rest of the run; a single warm timeout does not.
     */
    public static void awaitDriverReady() {
        if (gridFailure != null) {
            String skipped = "Skipping the browser start: " + gridFailureReason
                    + " so the grid is treated as dead for the rest of this run. Fix the first"
                    + " failure and the rest go with it. First failure was: " + gridFailure;
            // Recorded as well as thrown - otherwise every scenario after the first shows the
            // bare "Driver not initialized" message in the report with no pointer to the cause.
            recordSessionStartFailure(skipped);
            throw new IllegalStateException(skipped);
        }

        /* Actively start the session before falling back to polling.
         *
         * After the latch check, so a run with a dead grid short-circuits instead of paying the
         * full start budget per scenario. Its failure is caught rather than propagated, leaving
         * the budget/latch logic below as the single authority on when a grid is declared dead -
         * letting it throw straight out would bypass WARM_FAILURES_BEFORE_LATCH and turn one
         * queuing loss into a whole-run write-off.
         *
         * On success the poll loop finds a driver on its first attempt, so nothing is paid twice. */
        try {
            ensureSessionStarted();
        } catch (RuntimeException e) {
            log.log(Level.WARNING, "Active session start did not produce a driver; falling back to"
                    + " the polling budget, which decides whether this is fatal. Cause: "
                    + e.getMessage(), e);
        }

        // A demonstrably working grid gets the queuing budget; an unproven one waits for the hub.
        boolean warm = sessionEverStarted;
        long budgetMillis = warm ? WARM_BUDGET_MILLIS : COLD_BUDGET_MILLIS;
        long deadline = System.currentTimeMillis() + budgetMillis;

        if (!warm) {
            // Said out loud, because this is the one wait in the run that can look like a hang:
            // the first scenario blocks here while Sauron spawns the hub, with nothing else in the
            // log to explain the pause.
            log.info("No browser session has started in this run yet - waiting up to "
                    + (COLD_BUDGET_MILLIS / 1000) + "s for the Selenium hub to come up. Sauron"
                    + " spawns it lazily on the first session request, so this wait is expected"
                    + " once per run.");
        }

        IllegalStateException last = null;
        int attempt = 0;
        do {
            attempt++;
            try {
                Grid.driver();
                // Order matters: record the success BEFORE returning, so the next scenario to queue
                // gets the warm budget even if this one is the only session that ever worked.
                sessionEverStarted = true;
                CONSECUTIVE_WARM_FAILURES.set(0);
                if (attempt > 1) {
                    log.info("Browser session became usable on attempt " + attempt
                            + " (" + (warm ? "warm" : "cold") + " budget)");
                }
                return;
            } catch (IllegalStateException e) {
                last = e;
                pause(SESSION_READY_POLL_MILLIS);
            }
        } while (System.currentTimeMillis() < deadline);

        String diagnosis = diagnose(last);
        // Both, and not redundant: SESSION_START_FAILURE carries the full diagnosis into THIS
        // scenario's report, gridFailure short-circuits every later scenario in the run.
        recordSessionStartFailure(diagnosis);
        latchIfDead(warm, budgetMillis, last);

        log.log(Level.SEVERE, "No usable browser session after " + budgetMillis + "ms ("
                + (warm ? "warm" : "cold") + " budget, " + attempt + " attempts). " + diagnosis, last);
        throw new IllegalStateException(diagnosis, last);
    }

    /**
     * Declares the grid dead only when the evidence supports it.
     *
     * <p>A single warm timeout is a queuing loss, not a dead grid - latching on it is what cost
     * build #244 forty-three scenarios while 131 sessions were being served.
     */
    private static void latchIfDead(boolean warm, long budgetMillis, IllegalStateException last) {
        if (!warm) {
            gridFailureReason = "no scenario in this run ever obtained a browser session, and the"
                    + " first to try waited " + (budgetMillis / 1000) + "s for the hub,";
            gridFailure = String.valueOf(last);
            return;
        }
        // Captured, not re-read: another thread can reset this to 0 between the increment and the
        // log, which would print a count that did not choose this branch.
        int warmFailures = CONSECUTIVE_WARM_FAILURES.incrementAndGet();
        if (warmFailures >= WARM_FAILURES_BEFORE_LATCH) {
            gridFailureReason = warmFailures + " scenarios in a row failed to get a session"
                    + " after the grid had already served at least one,";
            gridFailure = String.valueOf(last);
            log.log(Level.SEVERE, warmFailures + " scenarios in a row could not obtain a session"
                    + " despite the grid having worked earlier - treating it as dead for the rest"
                    + " of this run.");
        } else {
            log.log(Level.SEVERE, "Scenario could not obtain a browser session within "
                    + budgetMillis + "ms, but the grid has served sessions in this run, so it is"
                    + " NOT being latched. Consecutive warm failures: " + warmFailures + " of "
                    + WARM_FAILURES_BEFORE_LATCH + ". Most likely this scenario queued for a"
                    + " browser slot: check whether thread-count x data-provider-thread-count"
                    + " exceeds the agent's Chrome slots.");
        }
    }

    // =================================================================================
    // Active session start - four ordered gates
    // =================================================================================

    /**
     * Brings this thread's session to a usable state through four ORDERED gates, retrying the
     * whole sequence up to {@link #MAX_SESSION_ATTEMPTS} times before failing.
     *
     * <pre>
     *   Stage 1  session bound to this thread  - no retry; a missing session is configuration
     *   Stage 2  session STARTED               - start it, or wait out a concurrent start
     *   Stage 3  driver ASSIGNED               - getDriver() non-null
     *   Stage 4  driver USABLE                 - it still holds a session id
     * </pre>
     *
     * <p>The ordering is the point. Stage 3 cannot be judged before stage 2 settles, because a
     * driver that is merely late looks identical to one that will never arrive. Stage 4 exists
     * because a non-null driver is not a working one: a RemoteWebDriver whose browser died keeps
     * its reference and fails on the first command instead, which surfaces mid-scenario as a
     * locator or timeout error rather than as the session problem it actually is.
     *
     * <p>Each failed attempt DISCARDS the started flag before retrying. Without that, stage 2
     * short-circuits on the stale flag and attempts 2 and 3 re-check the same broken session -
     * three attempts that are really one.
     *
     * <p>THREADS: nothing here creates or gates a thread. TestNG and Sauron have already created
     * this one before any step runs. What is gated is the SCENARIO, which does not proceed until
     * this thread's session and driver are both verified.
     */
    static void ensureSessionStarted() {
        // Stage 1, deliberately outside the retry loop: an unbound session is a sessions.yaml or
        // runner problem, and retrying it three times only delays the message by 100 seconds.
        AbstractTestSession session = Grid.getTestSession();
        if (session == null) {
            throw new IllegalStateException("No Sauron test session is bound to '" + Grid.getSessionName()
                    + "'. Verify sessions.yaml and that the scenario runs under SauronCucumberTest.");
        }

        // Fast path: already good. This is every scenario after the first on a warm thread.
        if (session.isStarted() && isDriverUsable(session.getDriver())) {
            return;
        }

        long hardDeadline = System.currentTimeMillis() + SESSION_START_TIMEOUT_MILLIS;
        Exception lastFailure = null;
        String lastStage = "not attempted";
        int poisonedResets = 0;

        for (int attempt = 1; attempt <= MAX_SESSION_ATTEMPTS; attempt++) {
            if (System.currentTimeMillis() >= hardDeadline) {
                lastStage = "hard timeout reached before attempt " + attempt;
                break;
            }
            if (attempt > 1) {
                // The usual trigger is the Selenium client read-timeout firing because the node is
                // saturated; retrying immediately just times out again. Let it drain first.
                log.info(String.format("Session '%s' not usable; retrying the full start sequence"
                                + " (attempt %d of %d) after %ds.", session.getSessionName(), attempt,
                        MAX_SESSION_ATTEMPTS, TimeUnit.MILLISECONDS.toSeconds(SESSION_START_BACKOFF_MILLIS)));
                pause(SESSION_START_BACKOFF_MILLIS);
            }

            long attemptDeadline = Math.min(
                    System.currentTimeMillis() + SESSION_ATTEMPT_BUDGET_MILLIS, hardDeadline);

            try {
                // ---- Stage 2: session started ------------------------------------------------
                if (!awaitSessionStarted(session, attemptDeadline, attempt)) {
                    lastStage = "stage 2 - session never reported started";
                    poisonedResets += discardSession(session, "it never reached the started state");
                    continue;
                }

                /* On Chrome, "CdpVersionFinder WARNING Unable to find CDP implementation matching
                 * <major>" in system-err is EXPECTED and harmless - do not chase it as a cause.
                 *
                 * WebTestSession always attempts getDevTools() on Chrome/Edge, and Selenium 4.23.0
                 * ships CDP v125/v126/v127 only, so anything newer finds no match and the attempt
                 * throws. Sauron swallows it, and setWebDriver() has already run by then, so the
                 * session is unaffected. Nothing in this repo needs CDP: HAR capture is never
                 * enabled, no step grants permissions through it, and NetworkCapture deliberately
                 * uses a window.fetch/XHR shim instead - see its class javadoc.
                 *
                 * It cannot be fixed by bumping Selenium either. Sauron is compiled against
                 * org.openqa.selenium.devtools.v127.*, and 4.25.0 is the last release shipping
                 * selenium-devtools-v127, which caps CDP support at Chrome 129. Raising it any
                 * further needs Sauron rebuilt against a current Selenium.
                 *
                 * Checked against the 22 Sep 2026 run: the warning appears in 16 of 22 failures
                 * but 0 of 72 passes, which looks damning until you notice it tracks new-session
                 * creation, not outcome - and 4 of the NullPointerException failures carry no
                 * warning at all. The NPEs are a null AbstractPage.page (no locator file resolved),
                 * which is a separate defect. */
                // ---- Stage 3: driver assigned ------------------------------------------------
                RemoteWebDriver driver = awaitDriverAssigned(session, attemptDeadline);
                if (driver == null) {
                    lastStage = "stage 3 - session started but no driver was ever assigned";
                    poisonedResets += discardSession(session, "it is marked started but holds no driver."
                            + " Sauron's createDriverInstance() swallowed the real failure - see 'Failed"
                            + " to Sel4 Augment driver or start devtools' in sauron-detailed.log");
                    continue;
                }

                // ---- Stage 4: driver usable --------------------------------------------------
                if (!isDriverUsable(driver)) {
                    lastStage = "stage 4 - driver assigned but holds no session id (browser died)";
                    poisonedResets += discardSession(session, "its driver has no session id");
                    continue;
                }

                if (attempt > 1) {
                    log.info(String.format("Session '%s' became usable on attempt %d of %d.",
                            session.getSessionName(), attempt, MAX_SESSION_ATTEMPTS));
                }
                return;
            } catch (Exception e) {
                lastFailure = e;
                lastStage = "threw during attempt " + attempt + ": " + e.getMessage();
                log.log(Level.WARNING, "Session start attempt " + attempt + " of " + MAX_SESSION_ATTEMPTS
                        + " failed: " + e.getMessage(), e);
                poisonedResets += discardSession(session, "the start attempt threw");
            }
        }

        // One last look - a concurrent start may have landed while we were giving up.
        if (session.isStarted() && isDriverUsable(session.getDriver())) {
            return;
        }
        throw new IllegalStateException(String.format(
                "Browser/driver could not be initialized for session '%s' after %d attempt(s)"
                        + " (%d poisoned-session reset(s)) within %ds. Last state: %s."
                        + " When no cause is attached, grep sauron-detailed.log for 'Failed to Sel4"
                        + " Augment driver or start devtools' - Sauron logs the real reason there"
                        + " at Level.ALL.",
                session.getSessionName(), MAX_SESSION_ATTEMPTS, poisonedResets,
                TimeUnit.MILLISECONDS.toSeconds(SESSION_START_TIMEOUT_MILLIS), lastStage),
                lastFailure);
    }

    /**
     * Stage 2. True once the session reports started within this attempt's budget.
     *
     * <p>Calls {@code startSession()} only when nobody else is mid-start. Under thread-count &gt; 1
     * two threads reach here together, and {@code startSession()} on a session that is already
     * starting returns immediately without doing anything - so a second caller would read that as
     * a failed start when it was simply still in flight.
     */
    private static boolean awaitSessionStarted(AbstractTestSession session, long deadline, int attempt) {
        boolean invoked = false;
        while (System.currentTimeMillis() < deadline) {
            if (session.isStarted()) {
                return true;
            }
            if (session.isSessionStarting()) {
                pause(SESSION_START_POLL_MILLIS);        // another thread owns it; wait it out
                continue;
            }
            if (invoked) {
                pause(SESSION_START_POLL_MILLIS);        // ours is in flight; give it time to land
                continue;
            }
            log.info(String.format("Starting session '%s' (attempt %d of %d).",
                    session.getSessionName(), attempt, MAX_SESSION_ATTEMPTS));
            session.startSession();
            invoked = true;
        }
        return session.isStarted();
    }

    /**
     * Stage 3. Polls for the driver reference within this attempt's budget.
     *
     * <p>The grace period matters: {@code AbstractTestSession.driver} is not volatile, so a driver
     * assigned by the starting thread is not guaranteed to be visible here at once. Declaring the
     * session poisoned on the first null read would discard a perfectly good browser.
     *
     * @return the driver, or null once the session has settled with nothing assigned.
     */
    private static RemoteWebDriver awaitDriverAssigned(AbstractTestSession session, long deadline) {
        long graceUntil = Math.min(System.currentTimeMillis() + POISONED_SESSION_GRACE_MILLIS, deadline);
        while (System.currentTimeMillis() < deadline) {
            RemoteWebDriver driver = session.getDriver();
            if (driver != null) {
                return driver;
            }
            if (System.currentTimeMillis() >= graceUntil && !session.isSessionStarting()) {
                return null;                             // settled and still nothing - poisoned
            }
            pause(SESSION_START_POLL_MILLIS);
        }
        return session.getDriver();
    }

    /**
     * Stage 4. A driver reference is not proof of a working browser.
     *
     * <p>{@code getSessionId()} is local state, so this costs no network round-trip - unlike, say,
     * {@code getWindowHandle()}, which would be a real command against the very browser that may
     * be broken. It returns null once the session has been quit or was never established.
     */
    private static boolean isDriverUsable(RemoteWebDriver driver) {
        if (driver == null) {
            return false;
        }
        try {
            return driver.getSessionId() != null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Clears the started flag so the next attempt provisions a fresh session rather than
     * re-checking the broken one.
     *
     * @return 1 when a flag was actually cleared, for the reset count in the final message.
     */
    private static int discardSession(AbstractTestSession session, String why) {
        if (!session.isStarted()) {
            return 0;
        }
        log.warning(String.format("Discarding session '%s' because %s. Clearing the started flag so"
                + " the next attempt provisions a fresh one.", session.getSessionName(), why));
        session.setSessionStarted(false);
        return 1;
    }

    // =================================================================================
    // Window
    // =================================================================================

    /**
     * Sizes the browser, tolerating every way this can fail on a CI agent.
     *
     * <p>Headless Chrome has no window manager, so {@code maximize()} is meaningless there and can
     * throw; a headed browser on an agent with no display can fail the same way. Each fallback is
     * therefore guarded separately rather than the whole block being wrapped once - a failure to
     * maximise should still leave a sized viewport.
     */
    private static void sizeWindow() {
        try {
            boolean headless = Grid.getTestSession() instanceof WebTestSession
                    && ((WebTestSession) Grid.getTestSession()).isBrowserRunHeadless();
            if (headless) {
                log.info("Running in headless mode - skipping window maximize");
                try {
                    Grid.driver().executeScript("window.resizeTo("
                            + DEFAULT_WINDOW.getWidth() + ", " + DEFAULT_WINDOW.getHeight() + ");");
                } catch (Exception e) {
                    log.warning("Failed to set viewport via JavaScript: " + e.getMessage());
                }
                applyZoom();
                return;
            }
            try {
                Grid.driver().manage().window().maximize();
                log.info("Browser window maximized successfully");
            } catch (WebDriverException e) {
                log.warning("maximize() failed, falling back to setSize: " + e.getMessage());
                Grid.driver().manage().window().setSize(DEFAULT_WINDOW);
            }
            // After sizing, never before: zoom is relative to the viewport, so applying it to a
            // window that is about to be resized would be measured against the wrong dimensions.
            applyZoom();
        } catch (Exception e) {
            log.warning("Error checking headless mode: " + e.getMessage() + ". Attempting window size setting.");
            try {
                Grid.driver().manage().window().setSize(DEFAULT_WINDOW);
                applyZoom();
            } catch (Exception ex) {
                log.warning("Window size setting also failed: " + ex.getMessage());
            }
        }
    }

    /**
     * Sets the page zoom to {@link #ZOOM_LEVEL}.
     *
     * <p>Call this after ANY navigation or refresh. The zoom is a style on the document element,
     * so loading a new document resets it to 100% - a scenario that refreshes mid-flow is back at
     * 100% from that point on unless it re-applies.
     *
     * <p>Never throws. A failed zoom is a cosmetic problem and must not turn into a scenario
     * failure that reads like a product defect.
     */
    /** How long a navigated document is given to finish loading before {@link #applyZoom()} runs. */
    private static final Duration DOCUMENT_READY_TIMEOUT = Duration.ofSeconds(30);

    /**
     * Waits for the navigated document to finish loading, before the zoom is applied to it.
     *
     * <p>Replaces a fixed {@code pause(2000)} that sat between {@code driver.get(url)} and
     * {@link #applyZoom()}, commented "headless in particular needs a beat after navigation". A
     * fixed sleep after a call that already blocks is the wrong shape twice over: it waits when
     * there is nothing to wait for, and it does not wait long enough when there is.
     *
     * <p>WHAT IT IS ACTUALLY PROTECTING. {@code applyZoom()} sets a style on
     * {@code document.documentElement} and never throws - a failure is a logged warning. So if it
     * runs against a document the navigation is about to replace, the zoom is set on a doomed
     * document, silently lost, and the Background carries on at 100% instead of
     * {@link #ZOOM_LEVEL}. Nothing fails; the page is just the wrong size for the rest of the
     * scenario. Waiting for {@code readyState == 'complete'} is what makes the zoom land on the
     * document that survives.
     *
     * <p>IT DOES NOT INTRODUCE A NEW FAILURE. The sleep it replaces could not fail, so neither does
     * this: a timeout is logged and the run continues, exactly as before. The gain is on both sides
     * of the common case - {@code get()} already blocks until the load event under
     * {@code pageLoadStrategy: normal}, so this usually returns immediately and gives back the two
     * seconds every scenario was paying, while a document that genuinely never completes now says
     * so by name instead of being silently under-waited.
     */
    private static void awaitDocumentComplete() {
        try {
            new WebDriverWait(Grid.driver(), DOCUMENT_READY_TIMEOUT)
                    .withMessage("document.readyState never reached 'complete' within "
                            + DOCUMENT_READY_TIMEOUT.getSeconds() + "s after navigating to "
                            + Grid.driver().getCurrentUrl())
                    .until(d -> "complete".equals(
                            ((JavascriptExecutor) d).executeScript("return document.readyState")));
        } catch (Exception e) {
            // Deliberately non-fatal - see the Javadoc. The zoom below may be lost, which is
            // cosmetic, and turning that into a session-start failure would report a page-load
            // problem as a browser problem.
            log.warning("Document did not report complete after navigation, continuing anyway: "
                    + e.getMessage());
        }
    }

    public static void applyZoom() {
        try {
            // documentElement, not body: body is null until the document has a body element, which
            // it is not on about:blank or during an early navigation.
            Grid.driver().executeScript(
                    "document.documentElement.style.zoom = arguments[0];", ZOOM_LEVEL);
            log.info("Page zoom set to " + ZOOM_LEVEL);
        } catch (Exception e) {
            log.warning("Could not set page zoom to " + ZOOM_LEVEL + ": " + e.getMessage());
        }
    }

    // =================================================================================
    // Diagnosis
    // =================================================================================

    /** Marker so {@link #open} can tell an already-diagnosed failure from a fresh one. */
    private static final String DIAGNOSIS_PREFIX = "Could not start the CCP browser session.";

    /** Records the text that should appear in the report for this scenario's session failure. */
    private static void recordSessionStartFailure(String failure) {
        SESSION_START_FAILURE.set(failure);
    }

    /**
     * Builds a session-start message whose advice matches the failure actually seen.
     *
     * <p>An earlier version stated the orphaned-grid theory unconditionally. For the commonest
     * failure - "Driver not initialized" - that sent readers to check Chrome and chromedriver
     * versions when the real problem was that no session had ever been created. The two causes are
     * unrelated and get separate advice.
     */
    private static String diagnose(Throwable cause) {
        String text = (cause == null) ? "" : String.valueOf(cause.getMessage());
        String advice;
        if (text.contains("Driver not initialized")) {
            advice = NO_SESSION_ADVICE;
        } else if (text.contains("session not created") || text.contains("Chrome instance exited")) {
            advice = ORPHANED_GRID_ADVICE;
        } else {
            advice = GENERIC_ADVICE;
        }
        return DIAGNOSIS_PREFIX + " " + advice + " Underlying failure: " + cause;
    }

    private static final String NO_SESSION_ADVICE =
            "No browser session was ever created for this scenario - Chrome did not crash, it was"
            + " never started, so this is NOT a Chrome or chromedriver version problem. The session"
            + " is created by Sauron's SauronWebListener.onStartTest via"
            + " LocalGridManager.spawnLocalHub, not by this suite. In the console log find"
            + " \"Executing command [java, ... selenium-server-*.jar, hub, --host, 127.0.0.1, -p,"
            + " <port>]\" and read what follows it: \"Added node ... at http://127.0.0.1:<port>\""
            + " means the hub is fine and the cause is elsewhere, while \"Timed out waiting for"
            + " 127.0.0.1 to initialize\" means the hub never came up and no scenario in this run"
            + " can get a browser.";

    private static final String ORPHANED_GRID_ADVICE =
            "Chrome was asked to start and exited. On a LOCAL grid suspect orphaned selenium-server"
            + " processes from an earlier run before suspecting Chrome or chromedriver versions:"
            + " Grid's ZeroMQ event bus is fixed on ports 4442/4443, so stale nodes keep advertising"
            + " there, get registered with the new hub, and the hub then routes the session to a"
            + " dead node. Check with `netstat -ano | findstr \"4442 4443\"` (Windows) or"
            + " `lsof -i:4442 -i:4443` (Linux) and terminate any selenium-server java process that"
            + " is not part of this run.";

    private static final String GENERIC_ADVICE =
            "The failure below is not one of the two known session-start causes (no session created,"
            + " or Chrome exiting on a stale local grid), so read it directly rather than assuming"
            + " either.";

    /**
     * Sleep without the checked exception, restoring the interrupt flag.
     *
     * <p>Local rather than borrowed from a page-object base class: this class deliberately does not
     * extend AbstractWebPage, because inheriting page helpers is what let session plumbing grow
     * inside a step-definition class in the first place.
     */
    private static void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
