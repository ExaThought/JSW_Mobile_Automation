package com.qapitol.jsw.utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PermissionPopupHandler {

    private final AndroidDriver driver;

    public PermissionPopupHandler(AndroidDriver driver) {
        this.driver = driver;
    }

    /**
     * Switch to native context and dismiss common Android permission popups.
     */
    public void dismissPopup() {
        try {
            String originalContext = driver.getContext();

            // Switch to native context
            driver.context("NATIVE_APP");

            // Find all buttons on native permission dialogs
            List<WebElement> buttons = driver.findElements(By.className("android.widget.Button"));

            for (WebElement button : buttons) {
                String text = button.getText();
                if (text != null && (
                        text.equalsIgnoreCase("Allow while using the app") ||
                                text.equalsIgnoreCase("Allow this time") ||
                                text.equalsIgnoreCase("Allow") ||
                                text.equalsIgnoreCase("Deny") ||
                                text.equalsIgnoreCase("Never allow") ||
                                text.equalsIgnoreCase("Don’t allow") ||
                                text.equalsIgnoreCase("Don't allow")
                )) {
                    System.out.println("Dismissing popup with button: " + text);
                    button.click();
                    break; // Only click the first relevant button
                }
            }

            // Switch back to original context (likely WEBVIEW)
            driver.context(originalContext);

        } catch (Exception e) {
            System.out.println("No permission popup found or error: " + e.getMessage());
        }
    }
}
