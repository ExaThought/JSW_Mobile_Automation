package com.qapitol.jsw.utils;

import com.qapitol.jsw.config.SessionBuilder;
import com.qapitol.sauron.common.configuration.Config;
import com.qapitol.sauron.core.Grid;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URI;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.qapitol.sauron.core.actions.ActionListener.logger;

public class CustomUtil {

    private static final int STALE_RETRY_ATTEMPTS = 3;

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
        StaleElementReferenceException lastStale = null;

        for (int attempt = 1; attempt <= STALE_RETRY_ATTEMPTS; attempt++) {
            try {
                WebElement element = firstVisible(locator);
                ((JavascriptExecutor) Grid.driver())
                        .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
                new Actions(Grid.driver()).moveToElement(element).click().perform();
                return;
            } catch (StaleElementReferenceException e) {
                lastStale = e;
                logger.log(Level.INFO, "Stale element clicking " + locator + " (attempt " + attempt
                        + " of " + STALE_RETRY_ATTEMPTS + ") - re-resolving against the new DOM.");
                pauseBetweenStaleRetries();
            }
        }

        throw new IllegalStateException("Could not click " + locator + ": the element went stale on"
                + " all " + STALE_RETRY_ATTEMPTS + " attempts. The page is re-rendering faster than"
                + " the click can land - wait for whatever is still loading before clicking.",
                lastStale);
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



    public static void scrollToCenterByText(String text) {
        scrollToCenter(By.xpath("//div[normalize-space()=" + xpathLiteral(text) + "]"));
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

    private static final Duration HOVER_REVEAL_TIMEOUT = Duration.ofSeconds(3);

    private static final String FORCE_HOVER_JS =
            "var el = arguments[0], r = el.getBoundingClientRect();"
          + "var opts = {bubbles:true, cancelable:true, view:window,"
          + "            clientX: r.left + r.width / 2, clientY: r.top + r.height / 2};"
          + "['pointerover','pointerenter','mouseover','mouseenter','mousemove']"
          + "  .forEach(function (type) {"
          + "     var evt = type.indexOf('pointer') === 0 && window.PointerEvent"
          + "               ? new PointerEvent(type, opts) : new MouseEvent(type, opts);"
          + "     el.dispatchEvent(evt);"
          + "  });";

    public static void forceHover(By locator) {
        WebElement element = firstVisible(locator);
        JavascriptExecutor js = (JavascriptExecutor) Grid.driver();
        js.executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'nearest', behavior:'instant'});",
                element);
        js.executeScript(FORCE_HOVER_JS, element);
        logger.info("forceHover dispatched a synthetic pointer-enter sequence on " + locator);
    }

    public static boolean hoverUntilVisible(By hoverTarget, By revealed) {
        try {
            hoverOverElement(hoverTarget);
            if (visibleWithin(revealed, HOVER_REVEAL_TIMEOUT)) {
                return true;
            }
            logger.info("real hover on " + hoverTarget + " did not reveal " + revealed + " within "
                    + HOVER_REVEAL_TIMEOUT.getSeconds() + "s - falling back to forceHover");
        } catch (RuntimeException e) {
            logger.info("real hover on " + hoverTarget + " failed (" + e.getClass().getSimpleName()
                    + ": " + e.getMessage() + ") - falling back to forceHover");
        }

        try {
            forceHover(hoverTarget);
        } catch (RuntimeException e) {
            logger.warning("forceHover on " + hoverTarget + " failed: " + e);
            return false;
        }

        boolean opened = visibleWithin(revealed, HOVER_REVEAL_TIMEOUT);
        if (!opened) {
            logger.warning("neither a real nor a synthetic hover on " + hoverTarget + " revealed "
                    + revealed + ". If this opens on CSS :hover alone, forceHover cannot open it -"
                    + " only a real pointer can, so treat this as a pointer/overlay problem rather"
                    + " than adding more synthetic events.");
        }
        return opened;
    }

    private static final Duration HOVER_SETTLE = Duration.ofMillis(200);

    private static final String SCROLL_INTO_VIEW_IF_NEEDED_JS =
            "var el = arguments[0], r = el.getBoundingClientRect();"
          + "var vh = window.innerHeight, vw = window.innerWidth;"
          + "if (vh && vw && r.top >= 0 && r.left >= 0 && r.bottom <= vh && r.right <= vw)"
          + "  return 'already in view';"
          + "el.scrollIntoView({block:'center', inline:'nearest', behavior:'instant'});"
          + "return 'scrolled';";

    public static boolean hoverChain(By... targets) {
        if (targets == null || targets.length == 0) {
            return false;
        }

        for (int i = 0; i < targets.length; i++) {
            By target = targets[i];
            Duration budget = (i == 0) ? Duration.ofSeconds(15) : HOVER_REVEAL_TIMEOUT;

            if (!visibleWithin(target, budget)) {
                logger.warning("hoverChain stopped at step " + (i + 1) + " of " + targets.length
                        + ": " + target + " was not visible within " + budget.getSeconds() + "s."
                        + (i == 0 ? " The chain never started."
                                  : " The level above it either did not open, or opened and closed"
                                    + " again before the pointer arrived."));
                return false;
            }

            try {
                WebElement element = firstVisible(target);
                if (i == 0) {
                    Object outcome = ((JavascriptExecutor) Grid.driver())
                            .executeScript(SCROLL_INTO_VIEW_IF_NEEDED_JS, element);
                    if (!"already in view".equals(outcome)) {
                        element = firstVisible(target);
                    }
                }
                new Actions(Grid.driver()).moveToElement(element).pause(HOVER_SETTLE).perform();
            } catch (StaleElementReferenceException e) {
                new Actions(Grid.driver())
                        .moveToElement(firstVisible(target)).pause(HOVER_SETTLE).perform();
            } catch (MoveTargetOutOfBoundsException e) {
                logger.warning("hoverChain could not move to " + target + " at step " + (i + 1)
                        + " of " + targets.length + ": " + e.getMessage());
                return false;
            }
        }
        return true;
    }

    private static boolean visibleWithin(By locator, Duration timeout) {
        try {
            return new WebDriverWait(Grid.driver(), timeout).until(driver -> {
                for (WebElement candidate : driver.findElements(locator)) {
                    try {
                        Dimension size = candidate.getSize();
                        if (candidate.isDisplayed() && size.getWidth() > 0 && size.getHeight() > 0) {
                            return true;
                        }
                    } catch (StaleElementReferenceException e) {
                    }
                }
                return false;
            });
        } catch (TimeoutException e) {
            return false;
        }
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


    public static String generateAutoGenName() {
        int randomNum = ThreadLocalRandom.current().nextInt(100000, 1000000);
        return "Auto gen" + randomNum;
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
        StaleElementReferenceException lastStale = null;

        for (int attempt = 1; attempt <= STALE_RETRY_ATTEMPTS; attempt++) {
            try {
                WebElement element = resolveElement(target);
                new WebDriverWait(Grid.driver(), Duration.ofMillis(1000))
                        .until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                return;
            } catch (StaleElementReferenceException e) {
                lastStale = e;

                if (target instanceof WebElement) {
                    throw new IllegalStateException("The element passed to clickButton went stale and"
                            + " cannot be re-resolved, because a WebElement carries no locator to"
                            + " search with. Pass the By instead of a pre-found element so the click"
                            + " can find the re-rendered node.", e);
                }

                logger.log(Level.INFO, "Stale element clicking " + target + " (attempt " + attempt
                        + " of " + STALE_RETRY_ATTEMPTS + ") - the page re-rendered mid-click."
                        + " Re-resolving and trying again.");
                pauseBetweenStaleRetries();
            }
        }

        throw new IllegalStateException("Could not click " + target + ": the element went stale on"
                + " all " + STALE_RETRY_ATTEMPTS + " attempts. The page is re-rendering faster than"
                + " the click can land - wait for whatever is still loading before clicking.",
                lastStale);
    }

    private static void pauseBetweenStaleRetries() {
        pause(300);
    }

    private static void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    public static String siteOrigin() {
        URI current = URI.create(Grid.driver().getCurrentUrl());
        return current.getScheme() + "://" + current.getAuthority();
    }

    public static String currentPath() {
        String path = URI.create(Grid.driver().getCurrentUrl()).getPath();
        if (path == null) {
            return "";
        }
        return path.length() > 1 && path.endsWith("/") ? path.substring(0, path.length() - 1) : path;
    }

    public static String xpathLiteral(String value) {
        if (!value.contains("'")) {
            return "'" + value + "'";
        }
        StringBuilder concat = new StringBuilder("concat(");
        String[] parts = value.split("'", -1);
        for (int i = 0; i < parts.length; i++) {
            if (i > 0) {
                concat.append(", \"'\", ");
            }
            concat.append("'").append(parts[i]).append("'");
        }
        return concat.append(")").toString();
    }

    public static boolean containsAsWholeWord(String text, String value) {
        if (text == null || value == null || value.isEmpty()) {
            return false;
        }
        String prefix = Character.isLetterOrDigit(value.charAt(0)) ? "\\b" : "";
        String suffix = Character.isLetterOrDigit(value.charAt(value.length() - 1)) ? "\\b" : "";
        return Pattern.compile(prefix + Pattern.quote(value) + suffix, Pattern.CASE_INSENSITIVE)
                .matcher(text).find();
    }

    public static boolean scrollUntilVisible(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) Grid.driver();
        long previousOffset = -1;

        for (int pass = 0; pass < 30; pass++) {
            if (isAnyVisible(locator)) {
                return true;
            }
            long offset = ((Number) js.executeScript(
                    "window.scrollBy(0, window.innerHeight * 0.8); return window.pageYOffset;"))
                    .longValue();
            if (offset == previousOffset) {
                pause(500);
                return isAnyVisible(locator);
            }
            previousOffset = offset;
            pause(400);
        }
        return isAnyVisible(locator);
    }

    public static void clickWithJsFallback(By locator, String description, int seconds) {
        try {
            new WebDriverWait(Grid.driver(), Duration.ofSeconds(seconds))
                    .until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (Exception e) {
            logger.log(Level.INFO, "Native click on the " + description + " was blocked ("
                    + e.getClass().getSimpleName() + ") - retrying with a JS click");
            jsClick(locator);
        }
    }

    public static boolean waitForPresence(By locator, int seconds) {
        return pollFor(() -> {
            try {
                return !Grid.driver().findElements(locator).isEmpty();
            } catch (Exception ignored) {
                return false;
            }
        }, seconds);
    }

    /**
     * Explicitly waits up to {@code seconds} for {@code condition}; false if it never holds.
     *
     * <p>The implicit wait is off for the duration: with it on, each check against an absent
     * element blocks for the full implicit wait, so the timeout could not end the wait on time.
     */
    public static boolean pollFor(BooleanSupplier condition, int seconds) {
        WebDriver driver = Grid.driver();
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                    .pollingEvery(Duration.ofMillis(250))
                    .until(d -> condition.getAsBoolean());
        } catch (TimeoutException e) {
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(SessionBuilder.IMPLICIT_WAIT);
        }
    }

    public static boolean isFirstMatchInteractable(By locator) {
        try {
            java.util.List<WebElement> matches = Grid.driver().findElements(locator);
            return !matches.isEmpty() && matches.get(0).isDisplayed() && matches.get(0).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public static String truncateToMaxLength(WebElement element, String value) {
        try {
            String maxLength = element.getAttribute("maxlength");
            if (maxLength != null && maxLength.matches("\\d+")) {
                int limit = Integer.parseInt(maxLength);
                if (limit > 0 && value.length() > limit) {
                    return value.substring(0, limit);
                }
            }
        } catch (Exception e) {
            logger.log(Level.INFO, "Could not read maxlength off " + element + ": " + e.getMessage());
        }
        return value;
    }

    public static String settledValueOf(By locator) {
        String previous = null;
        for (int attempt = 0; attempt < 8; attempt++) {
            String raw;
            try {
                raw = Grid.driver().findElement(locator).getAttribute("value");
            } catch (StaleElementReferenceException e) {
                raw = null;
            }
            String current = (raw == null) ? "" : raw.trim();
            if (current.equals(previous)) {
                return current;
            }
            previous = current;
            pause(250);
        }
        return previous == null ? "" : previous;
    }

    private static final By BUSINESS_DETAILS_DIALOG = By.xpath(
            "//div[@role='dialog']/h2[contains(text(), 'Personalize your procurement')]");

    private static final By BUSINESS_DETAILS_DIALOG_BUTTON =
            By.xpath("//div[@role='dialog']/div/button");

    /**
     * How long callers without a reason to wait longer give the dialog to appear. Short because
     * most calls find nothing, and every call that finds nothing waits this long.
     */
    public static final int BUSINESS_DETAILS_QUICK_WAIT_SECONDS = 2;

    /**
     * Waits up to {@code seconds} for the business details dialog and closes it if it opens.
     * Returns whether it was open.
     *
     * <p>Pre-prod opens it after the page has loaded, again on every refresh, so an instant check
     * misses it and it then covers whatever the next step reads or clicks.
     */
    public static boolean confirmBusinessDetailsDialogWithin(int seconds) {
        if (!pollFor(() -> isRenderedNow(BUSINESS_DETAILS_DIALOG), seconds)) {
            return false;
        }
        try {
            clickFirstVisible(BUSINESS_DETAILS_DIALOG_BUTTON);
            logger.log(Level.INFO, "Business details confirmation dialog was present and has been closed.");
            return true;
        } catch (Exception e) {
            logger.log(Level.INFO, "Business details dialog was showing but could not be closed: "
                    + e.getClass().getSimpleName());
            return false;
        }
    }

    public static boolean isRenderedNow(By locator) {
        try {
            for (WebElement candidate : Grid.driver().findElements(locator)) {
                try {
                    if (candidate.isDisplayed()) {
                        return true;
                    }
                } catch (StaleElementReferenceException ignored) {
                }
            }
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean waitForElementPresent(String xpath, int seconds) {
        return pollFor(() -> {
            try {
                java.util.List<WebElement> found = Grid.driver().findElements(By.xpath(xpath));
                return !found.isEmpty() && found.get(0).isDisplayed();
            } catch (Exception e) {
                return false;
            }
        }, seconds);
    }

    public static boolean isDisplayed(Object target) {
        for (int attempt = 1; attempt <= STALE_RETRY_ATTEMPTS; attempt++) {
            try {
                WebElement element = resolveElement(target);
                new WebDriverWait(Grid.driver(), Duration.ofMillis(1000))
                        .until(ExpectedConditions.visibilityOf(element));
                return element.isDisplayed();
            } catch (StaleElementReferenceException e) {
                if (target instanceof WebElement) {
                    return false;
                }
                pauseBetweenStaleRetries();
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    private static <T> T withStaleRetry(Object target, Supplier<T> read) {
        StaleElementReferenceException lastStale = null;

        for (int attempt = 1; attempt <= STALE_RETRY_ATTEMPTS; attempt++) {
            try {
                return read.get();
            } catch (StaleElementReferenceException e) {
                lastStale = e;

                if (target instanceof WebElement) {
                    throw new IllegalStateException("The element passed here went stale and cannot be"
                            + " re-resolved, because a WebElement carries no locator to search with."
                            + " Pass the By instead of a pre-found element.", e);
                }

                logger.log(Level.INFO, "Stale element reading " + target + " (attempt " + attempt
                        + " of " + STALE_RETRY_ATTEMPTS + ") - re-resolving against the new DOM.");
                pauseBetweenStaleRetries();
            }
        }

        throw new IllegalStateException("Could not read " + target + ": the element went stale on all "
                + STALE_RETRY_ATTEMPTS + " attempts.", lastStale);
    }

    public static String getText(Object target) {
        return withStaleRetry(target, () -> resolveElement(target).getText().trim());
    }


    public static String getTextJS(Object target) {
        return withStaleRetry(target, () -> {
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
        });
    }


    public static String getTextResilient(Object target) {
        return withStaleRetry(target, () -> readAllTextSources(target));
    }

    private static String readAllTextSources(Object target) {
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

        return "Auto gen" + randomString.substring(0, 6);
    }
    public static String generateEmail() {
        return "autogen" + System.currentTimeMillis() + "@yopmail.com";
    }

}