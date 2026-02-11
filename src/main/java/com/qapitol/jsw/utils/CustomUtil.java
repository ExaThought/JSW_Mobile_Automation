package com.qapitol.jsw.utils;

import com.qapitol.sauron.core.Grid;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Level;

import static com.qapitol.sauron.core.actions.ActionListener.logger;

public class CustomUtil {
    public static void scrollingToViewAndClickUsingJS(WebElement locator) {
        try {
            JavascriptExecutor js = Grid.driver();
            js.executeScript("arguments[0].scrollIntoView(true);", locator);
        } catch (Exception e) {
            logger.log(Level.FINEST, "clickUsingJS", e);
        }
    }

    public static void waits(WebElement locator, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitUntilElementDisappears(By locator) {
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Scrolls element to center of viewport (safe from sticky headers/footers)
     */
    public static void scrollToCenter(By locator) {
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        JavascriptExecutor js = (JavascriptExecutor) Grid.driver();
        js.executeScript("arguments[0].scrollIntoView({block:'center', inline:'nearest'});", element);

        try {
            Thread.sleep(500); // small pause for mobile render
        } catch (InterruptedException ignored) {
        }
    }

    /**
     * Scrolls element to center of viewport using dynamic XPath
     */
    public static void scrollToCenter(String xpathText) {
        By by = By.xpath("//div[normalize-space()='" + xpathText + "']");
        scrollToCenter(by);
    }

    /**
     * Scrolls element above sticky footer/header with configurable offset
     * 
     * @param xpath XPath string to locate element
     */
    public static void scrollAboveSticky(String xpath) {
        WebDriver driver = Grid.driver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

        int y = element.getLocation().getY();
        int offset = 180; // height of sticky footer

        js.executeScript(
                "window.scrollTo(0, arguments[0] - arguments[1]);",
                y, offset);
    }

    /**
     * Safe click for mobile: scrolls to center, moves up slightly, then clicks
     * Handles sticky footers/headers and uses JS fallback
     */
    public static void safeClick(By locator) {
        WebDriver driver = Grid.driver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        // Scroll to center
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", element);

        // Move up slightly to avoid sticky footer
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -200);");

        // Wait until clickable
        wait.until(ExpectedConditions.elementToBeClickable(locator));

        try {
            element.click();
        } catch (Exception e) {
            // Fallback JS click (mobile safe)
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

}
