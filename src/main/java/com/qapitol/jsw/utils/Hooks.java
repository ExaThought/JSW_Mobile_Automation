package com.qapitol.jsw.utils;

import com.qapitol.jsw.stepdefinition.SessionSteps;
import com.qapitol.sauron.core.Grid;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
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
        // Assert all soft assertions at the end of each scenario
        softAssert.assertAll();

    }
    @AfterAll
    public static void afterScenarioCleanup() {
        System.out.println("=== AFTER ALL SCENARIOS: DRIVER QUIT ===");

        if (Grid.driver() != null) {
            try {
                Grid.driver().quit();
            } catch (Exception e) {
                System.out.println("Driver already closed.");
            }
        }
    }
}
