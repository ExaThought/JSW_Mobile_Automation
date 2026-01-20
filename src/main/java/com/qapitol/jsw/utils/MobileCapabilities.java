package com.qapitol.jsw.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MobileCapabilities {

    private UiAutomator2Options options;
    private AndroidDriver driver;

    public MobileCapabilities(String chromedriverDir) {

        options = new UiAutomator2Options();
        options.setAutomationName("UiAutomator2");
        options.setPlatformName("Android");
        options.withBrowserName("Chrome");

        // ✅ Auto grant Android permissions
        options.setCapability("autoGrantPermissions", true);

        // ✅ Set ChromeDriver directory if provided
        if (chromedriverDir != null && !chromedriverDir.trim().isEmpty()) {
            File dir = new File(chromedriverDir);
            if (!dir.exists() || !dir.isDirectory()) {
                throw new IllegalArgumentException("Invalid ChromeDriver directory: " + chromedriverDir);
            }
            options.setChromedriverExecutableDir(chromedriverDir);
        }

        // ✅ Chrome preferences (THIS IS THE KEY PART)
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.geolocation", 1); // 1 = Allow
        prefs.put("profile.default_content_setting_values.notifications", 1);
        prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
        prefs.put("profile.default_content_setting_values.media_stream_mic", 1);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.setAcceptInsecureCerts(true);

        // ✅ Correct way to attach ChromeOptions in Appium
        options.setCapability("chromeOptions", chromeOptions);
    }

    public MobileCapabilities() {

    }

    public  AndroidDriver getDriver(String appiumServerUrl) {
        if (driver == null) {
            try {
                driver = new AndroidDriver(new URL(appiumServerUrl), options);
            } catch (MalformedURLException e) {
                throw new RuntimeException("Invalid Appium server URL: " + appiumServerUrl, e);
            }
        }
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
