package com.qapitol.jsw.utils;

import com.qapitol.jsw.stepdefinition.SessionSteps;
import com.qapitol.sauron.core.Grid;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.testng.asserts.SoftAssert;



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
        System.out.println("=== AFTER SCENARIO END ===");
        try {
            // Assert all soft assertions
            if (softAssert != null) {
                softAssert.assertAll();
            }
        } finally {
            // ALWAYS clean up browser (even if assertion fails)
            if (Grid.driver() != null) {
                Grid.driver().manage().deleteAllCookies();
                Grid.driver().quit();
            }
        }
    }
}
