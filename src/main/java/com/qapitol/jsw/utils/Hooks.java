package com.qapitol.jsw.utils;

import com.qapitol.jsw.stepdefinition.SessionSteps;
import com.qapitol.sauron.common.configuration.Config;
import com.qapitol.sauron.core.Grid;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import lombok.extern.java.Log;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.asserts.SoftAssert;

import static com.qapitol.jsw.stepdefinition.SessionSteps.CCP_DROP_DOWN;
import static com.qapitol.sauron.web.WebActionListener.log;


public class Hooks {

    // Make SoftAssert static so it can be accessed globally
    public static SoftAssert softAssert;
    SessionSteps sessionSteps = new SessionSteps();

    @Before
    public void beforeScenario() {
        System.out.println("=== BEFORE SCENARIO START ===");
        // Initialize a new SoftAssert object before every scenario
        softAssert = new SoftAssert();

    }

    @After
    public void afterScenario() {
        try{
        System.out.println("=== AFTER SCENARIO END ===");
        // Assert all soft assertions at the end of each scenario
        softAssert.assertAll();
    } catch (RuntimeException e) {
        e.printStackTrace();
        }
        finally {
            try {
                sessionSteps.ccpLogOut();
            } catch (Exception e) {
                log.info("No log out is found");
                String expiredToken = Config.getConfigProperty("ccpExpiredToken");
                JavascriptExecutor js = (JavascriptExecutor) Grid.driver();
                // Inject expired/invalid token
                js.executeScript("window.localStorage.setItem('token', arguments[0]);", expiredToken);
                Grid.driver().navigate().refresh();
            }

        }
    }
}
