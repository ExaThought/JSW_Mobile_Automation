package com.qapitol.jsw.utils;

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
    public void beforeScenario() {
        System.out.println("=== SCENARIO STARTED ===");
        // Start this thread's own collection of failures for the scenario about to run. The
        // failures are reported with the file and line of each assertion (see JswSoftAssert).
        ThreadLocalSoftAssert.startScenario();
        // Drop the cached customer-profile flag so each scenario reads it for its own user
        CustomerProfileUtil.reset();

    }

    @After
    public void afterScenario(Scenario scenario) {
        // Capture diagnostics FIRST. The teardown below logs out, injects an expired token and
        // refreshes, so anything captured after it would show a logged-out page, not the failure.
        if (scenario != null && scenario.isFailed()) {
            FailureDiagnostics.captureAll(scenario.getName());
        }

        try{
        System.out.println("=== SCENARIO ENDED ===");
        // Assert this scenario's soft assertions - only the ones collected on this thread.
        softAssert.assertAll();
        } catch (RuntimeException e) {
        e.printStackTrace();
        }
        finally {
            // API-only scenarios run without a browser session, so there is nothing to log out
            // of and Grid.driver() would throw. Skip the UI teardown entirely in that case.
            if (!hasActiveBrowser()) {
                log.info("No browser session for this scenario - skipping UI teardown");
                return;
            }
            try {
                sessionSteps.ccpLogOut();
            } catch (Exception e) {
                log.info("No log out is found");
                try {
                    String expiredToken = ConfigUtil.getSecretValue("ccpExpiredToken");
                    JavascriptExecutor js = (JavascriptExecutor) Grid.driver();
                    // Inject expired/invalid token
                    js.executeScript("window.localStorage.setItem('token', arguments[0]);", expiredToken);
                    Grid.driver().navigate().refresh();
                } catch (Exception ex) {
                    log.info("Could not reset the session token after a failed logout: " + ex.getMessage());
                }
            }
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