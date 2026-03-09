package com.qapitol.jsw.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ChromeSslBypassUtil {

    // Android Chrome native resource IDs
    private static final By ADVANCED_BUTTON =
            By.id("com.android.chrome:id/advanced_button");

    private static final By PROCEED_LINK =
            By.id("com.android.chrome:id/proceed_link");

    public static void bypassSslWarning(AppiumDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));

        try {
            // Wait & click "Advanced"
            wait.until(ExpectedConditions.elementToBeClickable(ADVANCED_BUTTON))
                    .click();

            // Wait & click "Proceed to site"
            wait.until(ExpectedConditions.elementToBeClickable(PROCEED_LINK))
                    .click();

            System.out.println("SSL warning bypassed on Android Chrome");

        } catch (Exception e) {
            // No SSL warning page
            System.out.println("No SSL warning detected");
        }
    }
}
