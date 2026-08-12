package com.qapitol.jsw.utils;

import com.qapitol.jsw.stepdefinition.SessionSteps;
import com.qapitol.sauron.common.configuration.Config;
import com.qapitol.sauron.core.Grid;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.asserts.SoftAssert;

//import static com.qapitol.jsw.stepdefinition.SessionSteps.ACCOUNT_LABEL;
import static com.qapitol.sauron.web.WebActionListener.log;


public class Hooks {

    // Make SoftAssert static so it can be accessed globally
    public static SoftAssert softAssert;
    SessionSteps sessionSteps = new SessionSteps();

    @Before
    public void beforeScenario() {
        System.out.println("=== SCENARIO STARTED ===");
        // Initialize a new SoftAssert object before every scenario.
        // JswSoftAssert is a drop-in subclass that also prints the file and line of each failure.
        softAssert = new JswSoftAssert();
        // Drop the cached customer-profile flag so each scenario reads it for its own user
        CustomerProfileUtil.reset();

    }

    @After
    public void afterScenario() {
        try{
        System.out.println("=== SCENARIO ENDED ===");
        // Assert all soft assertions at the end of each scenario
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
                    String expiredToken = Config.getConfigProperty("ccpExpiredToken");
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