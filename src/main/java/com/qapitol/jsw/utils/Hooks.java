package com.qapitol.jsw.utils;

import com.qapitol.jsw.config.SessionBuilder;
import com.qapitol.jsw.stepdefinition.SessionSteps;
import com.qapitol.sauron.core.Grid;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.asserts.SoftAssert;

//import static com.qapitol.jsw.stepdefinition.SessionSteps.ACCOUNT_LABEL;
import static com.qapitol.sauron.web.WebActionListener.log;


public class Hooks {

    /**
     * Static so every step definition can reach it, FINAL because the state behind it is now
     * per-thread: {@link ThreadLocalSoftAssert} routes each call into the collection belonging to
     * the thread running that scenario. Reassigning this field is what used to make concurrent
     * scenarios wipe each other's failures, so the fresh-per-scenario collection is started by
     * ThreadLocalSoftAssert.startScenario() below instead of by a new object here.
     */
    public static final SoftAssert softAssert = new ThreadLocalSoftAssert();
    SessionSteps sessionSteps = new SessionSteps();

    @Before
    public void beforeScenario(Scenario scenario) {
        // The scenario's NAME, then the marker. A bare "=== SCENARIO STARTED ===" says nothing
        // about which scenario is running, which is useless the moment a run has more than one.
        System.out.println();
        System.out.println(scenario == null ? "Scenario: (unnamed)" : "Scenario: " + scenario.getName());
        System.out.println("=== SCENARIO STARTED ===");
        // Start this thread's own collection of failures for the scenario about to run. The
        // failures are reported with the file and line of each assertion (see JswSoftAssert).
        ThreadLocalSoftAssert.startScenario();
        // Drop the cached customer-profile flag so each scenario reads it for its own user
        CustomerProfileUtil.reset();
        // Same reason, for the login credentials: TestNG reuses pool threads, so without this a
        // scenario that never logs in would inherit the previous scenario's email and query the
        // wrong customer record. Cleared at the START because a scenario that fails before logging
        // in never reaches its own teardown.
        SessionSteps.clearCredentials();

    }

    @After
    public void afterScenario(Scenario scenario) {
        // A session that never started is reported as "Driver not initialized. Is
        // @WebTest/@MobileTest missing on class/method?" - Sauron's ScreenshotBddListener calls
        // Grid.driver() after every step, and with no driver its exception REPLACES the real one.
        // The annotation is not the problem and never was. Put the actual cause where whoever
        // reads the report will find it, rather than only in the console at SEVERE.
        reportSessionStartFailure(scenario);

        // Capture diagnostics FIRST. The teardown below logs out, injects an expired token and
        // refreshes, so anything captured after it would show a logged-out page, not the failure.
        if (scenario != null && scenario.isFailed()) {
            FailureDiagnostics.captureAll(scenario.getName());
        }

        // Hand this scenario's captured assertion origins to TestGroupListener, which owns the
        // end-of-scenario output. Keyed by the TestNG result rather than read from a ThreadLocal
        // in the listener, because that is the one object both sides are certain to agree on.
        SoftFailureReport.handOver(JswSoftAssert.takeFailures());

        try {
            // Assert this scenario's soft assertions - only the ones collected on this thread.
            // The AssertionError is deliberately NOT caught: it is what marks the scenario failed,
            // and the listener turns it into the single "SCENARIO FAILED" block.
            softAssert.assertAll();
        } finally {
            // API-only scenarios run without a browser session, so there is nothing to log out
            // of and Grid.driver() would throw. Skip the UI teardown entirely in that case.
            if (!hasActiveBrowser()) {
                log.info("No browser session for this scenario - skipping UI teardown");
                return;
            }
            resetSessionClientSide();
        }
    }

    /**
     * Ends THIS browser's CCP session without calling the server-side logout.
     *
     * <p>THIS IS WHAT LETS data-provider-thread-count BE GREATER THAN 1 ON ONE ACCOUNT. The
     * currently configured value is not repeated here - read it off the {@code <suite>} tag in
     * testng_bdd.xml, which is the only place it is true. An earlier revision of this line named a
     * specific number and was left behind the moment that number changed. The teardown used to
     * click the CCP Log out control. That logout is server-side, so with several scenarios signed in
     * as the same {@code <group>.user.email} the first one to finish could end a session a sibling
     * was still using - the failure the account-pool rule exists to prevent. Clearing the token and
     * cookies in the browser ends only this session; every sibling keeps its own.
     *
     * <p>Why this is safe without a pool. CCP permits concurrent sessions for one account - measured
     * on qa 7 Sep 2026, five scenarios signed in as qa_homepage_user at once across 44 scenarios
     * with zero session or auth failures. And Sauron starts each session's Chrome with
     * {@code --incognito}, so every thread already has its own cookie jar and storage; nothing is
     * shared between them client-side. The logout was the only thing reaching across sessions.
     *
     * <p>It is strictly a stronger reset than what it replaces. The previous fallback - used
     * whenever the Log out control could not be found, which the logs show happened often - injected
     * an EXPIRED token over the live one. Clearing localStorage, sessionStorage and cookies removes
     * the credential outright rather than replacing it with a stale one.
     *
     * <p>{@code SessionSteps.ccpLogOut()} is left in place and unchanged. It is still bound as the
     * {@code account logout} step for any scenario that wants to test logging out as behaviour;
     * this hook was its only caller, so no scenario changes.
     */
    private void resetSessionClientSide() {
        try {
            // Each clear is guarded separately: a page served from a context where storage access
            // throws would otherwise skip the cookie deletion below.
            ((JavascriptExecutor) Grid.driver()).executeScript(
                    "try { window.localStorage.clear(); } catch (e) {}"
                  + "try { window.sessionStorage.clear(); } catch (e) {}");
            Grid.driver().manage().deleteAllCookies();
            Grid.driver().navigate().refresh();
            log.info("Session reset in the browser - storage and cookies cleared, no server-side logout");
        } catch (Exception e) {
            // Teardown must never turn a passing scenario into a failing one. The next scenario
            // calls Grid.driver().get(ccpPortalUrl) and logs in again regardless.
            log.info("Client-side session reset did not complete: " + e.getMessage());
        }
    }

    /**
     * Writes the real browser-session-start failure into the scenario's report output, when there
     * was one. Without this the report shows only the ScreenshotBddListener's "Driver not
     * initialized / missing @WebTest" message, which is a consequence of having no driver rather
     * than a cause, and is identical for every scenario in the run.
     *
     * <p>Swallows its own errors: reporting must never turn one failure into a different one.
     */
    private void reportSessionStartFailure(Scenario scenario) {
        try {
            String failure = SessionBuilder.consumeStartFailure();
            if (failure == null || scenario == null) {
                return;
            }
            String message = "BROWSER SESSION NEVER STARTED - this is the real failure."
                    + " The \"Driver not initialized. Is @WebTest/@MobileTest missing on"
                    + " class/method?\" message shown elsewhere in this report is Sauron's"
                    + " screenshot listener reacting to the absent driver, not the cause."
                    + " Underlying: " + failure;
            // SessionSteps already logged the cause at SEVERE; this call is what carries it into
            // the report, which is where it was missing.
            scenario.log(message);
        } catch (Throwable ignored) {
            // Never let diagnostics break teardown.
        }
    }

    /**
     * True only when this scenario has a live WebDriver. Sauron throws rather than returning
     * null when no session was created, so the check has to swallow Throwable.
     */
    private boolean hasActiveBrowser() {
        try {
            return Grid.driver() != null;
        } catch (Throwable t) {
            return false;
        }
    }
}