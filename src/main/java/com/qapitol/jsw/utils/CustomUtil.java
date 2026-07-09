package com.qapitol.jsw.utils;

import com.qapitol.sauron.common.configuration.Config;
import com.qapitol.sauron.core.Grid;
import io.appium.java_client.AppiumBy;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.qapitol.jsw.utils.ApiUtils.getCustomerDetails;
import static com.qapitol.jsw.utils.ApiUtils.jwtToken;
import static com.qapitol.sauron.core.actions.ActionListener.logger;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

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

    public static void waits(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), java.time.Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        }catch (Exception e)
        {

        }
    }

    public static boolean waitUntilEnabled(By locator) {
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));

        return wait.until(driver -> {
            WebElement element = driver.findElement(locator);
            return element.isEnabled();
        });
    }



    public static void enterText(By locator,String data)
    {
        WebDriverWait wait = new WebDriverWait(Grid.driver(), java.time.Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        Grid.driver().findElement(locator).sendKeys(data);
    }

    public static void waitUntilElementDisappears(By locator) {
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void jsClick(By  element) {

        new WebDriverWait(Grid.driver(), Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(element));
        WebElement element1 = Grid.driver().findElement(element);

        JavascriptExecutor js = (JavascriptExecutor) Grid.driver();
        js.executeScript("arguments[0].click();", element1);
    }

    public static void jsClick(WebElement  element) {

        new WebDriverWait(Grid.driver(), Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(element));


        JavascriptExecutor js = (JavascriptExecutor) Grid.driver();
        js.executeScript("arguments[0].click();", element);
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



    public static void doubleClickAction(By locator) {
        new WebDriverWait(Grid.driver(), Duration.ofMillis(1000)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebElement element = Grid.driver().findElement(locator);

        new Actions(Grid.driver())
                .doubleClick(element).click()
                .perform();
    }

    public static void hoverOverElement(By locator)
    {

        new WebDriverWait(Grid.driver(),Duration.ofMillis(2000)).until(ExpectedConditions.visibilityOfElementLocated(locator));


        WebElement element = Grid.driver().findElement(locator);

        new Actions(Grid.driver())
                .moveToElement(element)
                .perform();
    }

    public static void moveToAction(By locator)
    {
        new WebDriverWait(Grid.driver(),Duration.ofMillis(1000)).until(ExpectedConditions.visibilityOfElementLocated(locator));

        WebElement element = Grid.driver().findElement(locator);

        new Actions(Grid.driver())
                .moveToElement(element).click()
                .perform();
    }

    public static void moveToAction(WebElement locator)
    {
        new WebDriverWait(Grid.driver(),Duration.ofMillis(1000)).until(ExpectedConditions.visibilityOf(locator));



        new Actions(Grid.driver())
                .moveToElement(locator).click()
                .perform();
    }

    public static int generateValueFromText(String text) {
        Pattern pattern = Pattern.compile("(\\d+)\\s*to\\s*(\\d+)");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            int min = Integer.parseInt(matcher.group(1));
            int max = Integer.parseInt(matcher.group(2));

            return new Random().nextInt(max - min + 1) + min;
        }

        throw new IllegalArgumentException("No valid range found in text: " + text);
    }
    public static String getItemCount(String text) {
        Matcher matcher = Pattern.compile("(\\d+)").matcher(text);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1))+"";
        }

        throw new IllegalArgumentException("No number found in: " + text);
    }

    public static int getSheetCount(String text) {
        Pattern pattern = Pattern.compile("\\((\\d+)\\s*Sheets\\)");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }

        throw new IllegalArgumentException("Sheet count not found in: " + text);
    }

    public static String generateAutoGenName() {
        int randomNum = ThreadLocalRandom.current().nextInt(100000, 1000000);
        return "Autogen" + randomNum;
    }


    public static String getDateMonthsBack(int months) {
        return LocalDate.now()
                .minusMonths(months)
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public static String getCurrentDate() {
        return LocalDate.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public static void setValueJS(WebElement element, String value) {
        ((JavascriptExecutor) Grid.driver()).executeScript(
                "arguments[0].removeAttribute('readonly');" +
                        "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                element,
                value
        );
    }

    public static By getDynamicXpath(String locator,int index) {
        String xpath = String.format(
                locator,
                index
        );
        return By.xpath(xpath);
    }

    private static Duration refreshWaitTimeout() {
        String configured = Config.getConfigProperty("refresh.wait.timeout.seconds");
        if (configured != null && !configured.isBlank()) {
            try {
                return Duration.ofSeconds(Long.parseLong(configured.trim()));
            } catch (NumberFormatException e) {
                logger.log(Level.WARNING, "Invalid refresh.wait.timeout.seconds='" + configured + "', using default 20s");
            }
        }
        return Duration.ofSeconds(20);
    }
    /** Refresh and wait for document ready only — for call sites whose
     *  landmark is a sauron yaml key (resolved by waitAnd* methods, not By). */
    public static void refreshAndWaitForPageLoad() {
        WebDriver driver = Grid.driver();
        driver.navigate().refresh();
        new WebDriverWait(driver, refreshWaitTimeout())
                .until(d -> "complete".equals(
                        ((JavascriptExecutor) d).executeScript("return document.readyState")));
    }

    public static void refreshAndWaitFor(By landmark) {
        WebDriver driver = Grid.driver();
        driver.navigate().refresh();

        WebDriverWait wait = new WebDriverWait(driver, refreshWaitTimeout());

        // 1. Page load complete (all synchronous resources done)
        wait.until(d -> "complete".equals(
                ((JavascriptExecutor) d).executeScript("return document.readyState")));

        // 2. The element the caller actually needs next — this is what
        //    proves the SPA has re-rendered, readyState alone is not enough
        wait.until(ExpectedConditions.visibilityOfElementLocated(landmark));
    }
    public static void refreshAndWaitFor(String locator) {
        refreshAndWaitFor(toBy(locator));
    }

    // Locator constants in this project are a mix of xpath and css strings
    private static By toBy(String locator) {
        String trimmed = locator.trim();
        if (trimmed.startsWith("/") || trimmed.startsWith("(") || trimmed.startsWith("./")) {
            return By.xpath(trimmed);
        }
        return By.cssSelector(trimmed);
    }


    private static String normalize(String text) {
        return text == null
                ? ""
                : text.toUpperCase().replaceAll("[^A-Z0-9]", "");
    }

    public static boolean matches(String productName, String searchText) {
        return normalize(productName).toLowerCase().contains(normalize(searchText).toLowerCase());
    }
    /**
     * Resolves a supported element target into a WebElement.
     *
     * @param target supported element reference (By, WebElement, String XPath)
     * @return resolved WebElement
     * @throws IllegalArgumentException when target type is not supported
     */
    public static WebElement resolveElement(Object target) {
        if (target instanceof WebElement) {
            return (WebElement) target;
        }

        if (target instanceof By) {
            return Grid.driver().findElement((By) target);
        }

        throw new IllegalArgumentException(
                "Unsupported type: " + target.getClass().getSimpleName());
    }
    /**
     * @param target element identifier. Supported types are By, WebElement, and String XPath.
     * @throws IllegalArgumentException if an unsupported target type is provided
     */

    public static void clickButton(Object target) {
        WebElement element = resolveElement(target);
        new WebDriverWait(Grid.driver(),Duration.ofMillis(1000)).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    /**
     * Checks whether a UI element is displayed.
     *
     * <p>Supported target types:
     * <ul>
     *   <li>By - Selenium locator</li>
     *   <li>WebElement - already located element</li>
     *   <li>String - XPath expression</li>
     * </ul>
     *
     * @param target element reference
     * @return true if element exists and is visible, otherwise false
     */
    public static boolean isDisplayed(Object target) {
        try {
            WebElement element= resolveElement(target);

                new WebDriverWait(Grid.driver(),Duration.ofMillis(1000)).until(ExpectedConditions.visibilityOf(element));

                if(element.isDisplayed())
                {
                    return true;
                }else {
                    return false;
                }
            }catch (Exception e)
            {
                return  false;
            }
    }

    public static String getText(Object target) {
        return resolveElement(target).getText().trim();
    }


    public static String generateFirstName() {
        String randomString = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .replaceAll("[0-9]", "");

        return "Autogen" + randomString.substring(0, 6);
    }
    public static String generateEmail() {
        return "autogen" + System.currentTimeMillis() + "@yopmail.com";
    }

}