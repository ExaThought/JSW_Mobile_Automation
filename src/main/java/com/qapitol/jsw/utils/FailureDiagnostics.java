package com.qapitol.jsw.utils;

import com.qapitol.sauron.core.Grid;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import static com.qapitol.sauron.web.WebActionListener.log;

/**
 * Captures what the browser looked like at the moment a scenario failed.
 *
 * Without this, a failed run only says "element not found" - which cannot distinguish a stale
 * locator from a page that legitimately had nothing to show. The screenshot and page source
 * answer that question without a second run.
 *
 * Every method swallows its own errors: diagnostics must never turn a reported failure into a
 * different, more confusing failure.
 */
public final class FailureDiagnostics {

    /** Page source can be megabytes; keep the attachment readable. */
    private static final int MAX_PAGE_SOURCE_CHARS = 500_000;

    /**
     * Allure rejects attachments created from an @After hook once the test context has closed
     * ("Could not add attachment: no test is running"), so everything is also written here.
     */
    private static final String DIAGNOSTICS_DIR = "target/diagnostics";

    private FailureDiagnostics() {
        // utility class
    }

    /**
     * Attaches screenshot, URL, page source and console logs for a failed scenario.
     *
     * Must be called BEFORE any teardown that navigates or logs out, otherwise the captured
     * state is the post-teardown page rather than the failure.
     *
     * @param scenarioName used to label the attachments in the report.
     */
    public static void captureAll(String scenarioName) {
        WebDriver driver = activeDriver();
        if (driver == null) {
            log.info("No active browser - skipping failure diagnostics for: " + scenarioName);
            return;
        }

        captureCurrentUrl(driver, scenarioName);
        captureScreenshot(driver, scenarioName);
        capturePageSource(driver, scenarioName);
        captureBrowserConsole(driver, scenarioName);
    }

    private static void captureCurrentUrl(WebDriver driver, String scenarioName) {
        try {
            String url = driver.getCurrentUrl();
            String title = driver.getTitle();
            String summary = "URL   : " + url + System.lineSeparator()
                    + "Title : " + title;
            log.info("FAILURE at URL: " + url);
            Allure.addAttachment("Failure location - " + scenarioName, "text/plain", summary, ".txt");
        } catch (Throwable t) {
            log.info("Could not capture current URL: " + t.getMessage());
        }
    }

    private static void captureScreenshot(WebDriver driver, String scenarioName) {
        try {
            if (!(driver instanceof TakesScreenshot)) {
                log.info("Driver does not support screenshots - skipping");
                return;
            }
            byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot - " + scenarioName, "image/png",
                    new ByteArrayInputStream(png), ".png");
            writeToDisk(scenarioName, "screenshot.png", png);
            log.info("Captured failure screenshot (" + png.length + " bytes)");
        } catch (Throwable t) {
            log.info("Could not capture screenshot: " + t.getMessage());
        }
    }

    private static void capturePageSource(WebDriver driver, String scenarioName) {
        try {
            String source = driver.getPageSource();
            if (source == null) {
                return;
            }
            if (source.length() > MAX_PAGE_SOURCE_CHARS) {
                source = source.substring(0, MAX_PAGE_SOURCE_CHARS)
                        + System.lineSeparator() + "... [truncated]";
            }
            Allure.addAttachment("Page source - " + scenarioName, "text/html", source, ".html");
            writeToDisk(scenarioName, "page-source.html", source.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        } catch (Throwable t) {
            log.info("Could not capture page source: " + t.getMessage());
        }
    }

    /**
     * Browser console errors and warnings. A JS error on the page often explains why an element
     * never rendered, which no Selenium message can tell you.
     */
    private static void captureBrowserConsole(WebDriver driver, String scenarioName) {
        try {
            LogEntries entries = driver.manage().logs().get(LogType.BROWSER);
            if (entries == null) {
                return;
            }
            List<String> lines = new ArrayList<>();
            for (LogEntry entry : entries) {
                lines.add("[" + entry.getLevel() + "] " + entry.getMessage());
            }
            if (lines.isEmpty()) {
                return;
            }
            String consoleText = String.join(System.lineSeparator(), lines);
            Allure.addAttachment("Browser console - " + scenarioName, "text/plain", consoleText, ".txt");
            writeToDisk(scenarioName, "console.txt", consoleText.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            log.info("Captured " + lines.size() + " browser console entries");
        } catch (Throwable t) {
            // Console logging requires the logging preference to be enabled; absence is normal.
            log.info("Browser console logs unavailable: " + t.getMessage());
        }
    }

    /**
     * Writes a diagnostic file next to the build output.
     *
     * @param scenarioName used to build a filesystem-safe file name.
     * @param suffix       e.g. "page-source.html", "screenshot.png".
     */
    private static void writeToDisk(String scenarioName, String suffix, byte[] content) {
        try {
            java.nio.file.Path dir = java.nio.file.Paths.get(DIAGNOSTICS_DIR);
            java.nio.file.Files.createDirectories(dir);
            String safeName = scenarioName == null ? "scenario"
                    : scenarioName.replaceAll("[^A-Za-z0-9._-]", "_");
            if (safeName.length() > 80) {
                safeName = safeName.substring(0, 80);
            }
            java.nio.file.Path file = dir.resolve(safeName + "__" + suffix);
            java.nio.file.Files.write(file, content);
            log.info("Diagnostics written to " + file.toAbsolutePath());
        } catch (Throwable t) {
            log.info("Could not write diagnostics to disk: " + t.getMessage());
        }
    }

    /** Sauron throws rather than returning null when no session exists, so swallow Throwable. */
    private static WebDriver activeDriver() {
        try {
            return Grid.driver();
        } catch (Throwable t) {
            return null;
        }
    }
}
