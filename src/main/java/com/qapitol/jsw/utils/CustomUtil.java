package com.qapitol.jsw.utils;

import com.qapitol.sauron.common.configuration.Config;
import com.qapitol.sauron.core.Grid;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.qapitol.sauron.core.actions.ActionListener.logger;

public class CustomUtil {

    @Deprecated
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

    public static void waitForPageLoad() {
        new WebDriverWait(Grid.driver(), Duration.ofSeconds(30))
                .withMessage("document.readyState never reached 'complete' within 30s - still at "
                        + Grid.driver().getCurrentUrl())
                .until(driver -> "complete".equals(
                        ((JavascriptExecutor) driver).executeScript("return document.readyState")));
    }

    public static By xpathOf(String locatorValue) {
        String xpath = locatorValue.startsWith("xpath=")
                ? locatorValue.substring("xpath=".length())
                : locatorValue;
        return By.xpath(xpath);
    }


    public static void waits(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), java.time.Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            logger.info("waits(): " + locator + " was not clickable within 10s - continuing. "
                    + e.getClass().getSimpleName());
        }
    }

    public static boolean waitUntilEnabled(By locator) {
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));

        return wait.withMessage("element never became enabled: " + locator + " (within 10s)")
                .until(driver -> {
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

        new WebDriverWait(Grid.driver(), Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(element));
        WebElement element1 = Grid.driver().findElement(element);

        JavascriptExecutor js = (JavascriptExecutor) Grid.driver();
        js.executeScript("arguments[0].click();", element1);
    }

    public static void jsClick(WebElement  element) {

        new WebDriverWait(Grid.driver(), Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(element));


        JavascriptExecutor js = (JavascriptExecutor) Grid.driver();
        js.executeScript("arguments[0].click();", element);
    }


    public static WebElement firstVisible(By locator) {
        return new WebDriverWait(Grid.driver(), Duration.ofSeconds(15))
                .withMessage("no VISIBLE match for " + locator
                        + " within 15s (matches may exist in the DOM but be 0x0 - the header, footer"
                        + " and nav each render 2-4 copies of every item)")
                .until(driver -> {
                    for (WebElement candidate : driver.findElements(locator)) {
                        try {
                            org.openqa.selenium.Dimension size = candidate.getSize();
                            if (candidate.isDisplayed() && size.getWidth() > 0 && size.getHeight() > 0) {
                                return candidate;
                            }
                        } catch (StaleElementReferenceException e) {
                            // React re-rendered this node mid-check; the next poll picks up the new one.
                        }
                    }
                    return null;
                });
    }

    public static boolean isAnyVisible(By locator) {
        try {
            firstVisible(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void clickFirstVisible(By locator) {
        WebElement element = firstVisible(locator);
        ((JavascriptExecutor) Grid.driver())
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        new Actions(Grid.driver()).moveToElement(element).click().perform();
    }

    public static void scrollToViewAndClick(By locator) {
        WebElement element = firstVisible(locator);
        JavascriptExecutor js = (JavascriptExecutor) Grid.driver();
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        try {
            new Actions(Grid.driver()).moveToElement(element).click().perform();
        } catch (Exception e) {
            logger.log(Level.FINEST, "Actions click intercepted - falling back to JS click", e);
            js.executeScript("arguments[0].click();", element);
        }
    }

    public static void scrollToViewAndClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Grid.driver();
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        try {
            new Actions(Grid.driver()).moveToElement(element).click().perform();
        } catch (Exception e) {
            logger.log(Level.FINEST, "Actions click intercepted - falling back to JS click", e);
            js.executeScript("arguments[0].click();", element);
        }
    }


    public static boolean isElementVisibleJS(By locator) {
        try {
            new WebDriverWait(Grid.driver(), Duration.ofSeconds(15))
                    .until(ExpectedConditions.presenceOfElementLocated(locator));
            WebElement element = Grid.driver().findElement(locator);
            Object visible = ((JavascriptExecutor) Grid.driver()).executeScript(
                    "var e = arguments[0];" +
                    "if (!e) { return false; }" +
                    "var style = window.getComputedStyle(e);" +
                    "var rect = e.getBoundingClientRect();" +
                    "return (style.display !== 'none' && style.visibility !== 'hidden'" +
                    "        && style.opacity !== '0' && rect.width > 0 && rect.height > 0);",
                    element);
            return Boolean.TRUE.equals(visible);
        } catch (Exception e) {
            logger.log(Level.FINEST, "isElementVisibleJS: not present/visible for " + locator, e);
            return false;
        }
    }

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

    public static void scrollToCenterByText(String text) {
        // XPath 1.0 has no escape syntax, so pick a quote character the text does not contain.
        String quoted = text.contains("'") ? "\"" + text + "\"" : "'" + text + "'";
        scrollToCenter(By.xpath("//div[normalize-space()=" + quoted + "]"));
    }



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

    private static final int HOVER_ATTEMPTS = 3;

    private static final String HOVER_REACHABLE_JS =
            "var el = arguments[0], r = el.getBoundingClientRect();"
          + "if (r.width === 0 || r.height === 0) return 'zero-size box';"
          + "var vw = window.innerWidth, vh = window.innerHeight;"
          + "if (!vw || !vh) return 'ok|' + Math.round(r.height) + '|viewport unmeasurable, reachability unchecked';"
          + "var cx = r.left + r.width / 2, cy = r.top + r.height / 2;"
          + "if (cx < 0 || cy < 0 || cx > vw || cy > vh)"
          + "  return 'centre is outside the ' + vw + 'x' + vh + ' viewport at ('"
          + "         + Math.round(cx) + ',' + Math.round(cy) + ')';"
          + "var top = document.elementFromPoint(cx, cy);"
          + "if (!top) return 'ok|' + Math.round(r.height) + '|no node at centre, reachability unchecked';"
          + "if (top === el || el.contains(top)) return 'ok|' + Math.round(r.height);"
          + "var id = top.getAttribute && top.getAttribute('data-test-id');"
          + "return 'covered by <' + top.tagName.toLowerCase() + '>'"
          + "  + (id ? ' data-test-id=' + id : '');";

   
    public static void hoverOverElement(By locator)
    {
        WebDriverException last = null;
        String reachability = "not checked";

        for (int attempt = 1; attempt <= HOVER_ATTEMPTS; attempt++) {
            try {
                // Re-resolved every attempt: a reference taken before the scroll can be stale by the
                // time the move runs.
                WebElement element = firstVisible(locator);
                JavascriptExecutor js = (JavascriptExecutor) Grid.driver();

                js.executeScript(
                        "arguments[0].scrollIntoView({block:'center', inline:'nearest', behavior:'instant'});",
                        element);

                reachability = String.valueOf(js.executeScript(HOVER_REACHABLE_JS, element));
                if (!reachability.startsWith("ok|")) {
                    logger.info("hover attempt " + attempt + " of " + HOVER_ATTEMPTS + " on " + locator
                            + " is not reachable at its centre (" + reachability + ")");
                    continue;
                }

                String[] ok = reachability.split("\\|");
                if (ok.length > 2) {
                    logger.warning("hoverOverElement: reachability check returned more than 2 fields: "
                            + reachability);
                }
                int height = Integer.parseInt(ok[1]);
                // Offsets are from the element's CENTRE in the W3C Actions spec, so this lands just
                // above the top edge. Safe because the element was centred first.
                int above = -(height / 2 + 12);

                new Actions(Grid.driver())
                        .moveToElement(element, 0, above)   // leave, so the next move re-enters
                        .pause(Duration.ofMillis(60))
                        .moveToElement(element)             // enter -> pointerenter fires
                        .perform();

                if (attempt > 1) {
                    logger.info("hover on " + locator + " succeeded on attempt " + attempt);
                }
                return;

            } catch (StaleElementReferenceException | MoveTargetOutOfBoundsException e) {
                last = e;
                logger.info("hover attempt " + attempt + " of " + HOVER_ATTEMPTS + " on " + locator
                        + " threw " + e.getClass().getSimpleName() + " - re-resolving");
            }
        }

        throw new IllegalStateException("could not hover " + locator + " after " + HOVER_ATTEMPTS
                + " attempts. Last reachability check: " + reachability
                + ". If this says 'covered by', a sticky header or modal is over the element - dismiss"
                + " it first. If it says 'ok', the pointer reached the element and the component did"
                + " not react, which is a product question, not a locator one.", last);
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
                .withMessage("document.readyState never reached 'complete' after a refresh of "
                        + driver.getCurrentUrl())
                .until(d -> "complete".equals(
                        ((JavascriptExecutor) d).executeScript("return document.readyState")));
    }

    public static void refreshAndWaitFor(By landmark) {
        WebDriver driver = Grid.driver();
        driver.navigate().refresh();

        WebDriverWait wait = new WebDriverWait(driver, refreshWaitTimeout());

        // 1. Page load complete (all synchronous resources done)
        wait.withMessage("document.readyState never reached 'complete' after a refresh, while"
                        + " waiting to re-find " + landmark)
                .until(d -> "complete".equals(
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

    public static void clickButton(Object target) {
        WebElement element = resolveElement(target);
        new WebDriverWait(Grid.driver(),Duration.ofMillis(1000)).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

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


    public static String getTextJS(Object target) {
        WebElement element = resolveElement(target);
        JavascriptExecutor js = (JavascriptExecutor) Grid.driver();

        Object innerText = js.executeScript("return arguments[0].innerText;", element);
        if (innerText instanceof String && !((String) innerText).trim().isEmpty()) {
            return ((String) innerText).trim();
        }

        Object textContent = js.executeScript("return arguments[0].textContent;", element);
        if (textContent instanceof String) {
            return ((String) textContent).trim();
        }

        return "";
    }


    public static String getTextResilient(Object target) {
        WebElement element = resolveElement(target);

        String rendered = element.getText();
        if (rendered != null && !rendered.trim().isEmpty()) {
            return rendered.trim();
        }

        String scripted = getTextJS(element);
        if (!scripted.isEmpty()) {
            return scripted;
        }

        String value = element.getAttribute("value");
        return value == null ? "" : value.trim();
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