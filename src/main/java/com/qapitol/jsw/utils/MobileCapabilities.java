package com.qapitol.jsw.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.Getter;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class MobileCapabilities {

    /**
     * -- GETTER --
     *  Get the UiAutomator2Options object for custom use
     */
    @Getter
    private final UiAutomator2Options options;
    private AndroidDriver driver;

    /**
     * Constructor to create UiAutomator2Options for Chrome
     * @param chromedriverDir Path to folder containing matching ChromeDriver
     */
    public MobileCapabilities(String chromedriverDir) {

        options = new UiAutomator2Options();

        // Basic Appium settings
        options.setAutomationName("UiAutomator2");
        options.setPlatformName("Android");
        options.withBrowserName("Chrome");

        // Set ChromeDriver directory
        if (chromedriverDir != null && !chromedriverDir.trim().isEmpty()) {
            File dir = new File(chromedriverDir);
            if (!dir.exists() || !dir.isDirectory()) {
                throw new IllegalArgumentException("Invalid ChromeDriver directory: " + chromedriverDir);
            }
            options.setChromedriverExecutableDir(chromedriverDir);
        }

        // Chrome options for SSL bypass
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.addArguments(
                "--ignore-certificate-errors",
                "--no-first-run",
                "--disable-translate",
                "--disable-popup-blocking",
                "--disable-notifications",
                "--disable-infobars",
                "--test-type"
        );

        // Attach ChromeOptions to Appium
        options.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
    }

    public MobileCapabilities(UiAutomator2Options options) {
        this.options = options;
    }

    /**
     * Launch AndroidDriver
     * @param appiumServerUrl URL of Appium server, e.g., http://127.0.0.1:4723/wd/hub
     * @return AndroidDriver instance
     */
    public AndroidDriver getDriver(String appiumServerUrl) {
        if (driver == null) {
            try {
                driver = new AndroidDriver(new URL(appiumServerUrl), options);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                throw new RuntimeException("Invalid Appium server URL: " + appiumServerUrl);
            }
        }
        return driver;
    }

    /**
     * Quit the driver
     */
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}