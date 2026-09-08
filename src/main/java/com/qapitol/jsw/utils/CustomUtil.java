package com.qapitol.jsw.utils;

import com.qapitol.sauron.common.configuration.Config;
import com.qapitol.sauron.core.Grid;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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
    /**
     * Scrolls the element into view. Despite the name it does NOT click, and it swallows every
     * exception, so a failure here is silent.
     *
     * <p>Retained as-is because ~13 call sites in CartSteps and TokenSteps were written against
     * this behaviour and pass today; changing it would turn those into double-clicks. For a
     * scroll that actually clicks, use {@link #scrollToViewAndClick(By)}.
     *
     * @param locator element to scroll into view
     * @deprecated misleading name - use {@link #scrollToViewAndClick(By)} to scroll and click,
     *             or call this only where a scroll alone is intended.
     */
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

    /**
     * By for a locator value read out of a {@code locators/*.yaml} file.
     *
     * <p>Those files mix both conventions - some entries carry an {@code xpath=} prefix and some do
     * not - and {@code By.xpath} would take the prefix as part of the expression. Call as
     * {@code xpathOf(getLocator(KEY))} from any step class.
     */
    public static By xpathOf(String locatorValue) {
        String xpath = locatorValue.startsWith("xpath=")
                ? locatorValue.substring("xpath=".length())
                : locatorValue;
        return By.xpath(xpath);
    }

    /**
     * Waits up to 10s for the element to be clickable. Non-fatal by design - many callers use it as
     * a settle before an interaction that has its own wait.
     *
     * <p>The timeout is logged rather than swallowed silently. Previously the catch block was empty,
     * so a caller that then failed on its own shorter wait reported that shorter timeout as the
     * cause: the category hover steps in build #233 read "tried for 2 second(s)" when 10 seconds had
     * already elapsed here unnoticed.
     */
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

        // Named, for the same reason as firstVisible: a lambda condition makes WebDriverWait
        // report the JVM's synthetic class rather than anything a reader can act on.
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

    /**
     * First rendered element matching the locator.
     *
     * <p>The header and navigation render each element twice - once collapsed to 0x0 and once
     * visible - so findElement() returns the invisible copy. ExpectedConditions does not help here:
     * visibilityOfElementLocated() re-checks only that same first match until it times out, and
     * visibilityOfAllElementsLocatedBy() demands every match be visible, which the 0x0 duplicate
     * never is. Hence the explicit filter below.
     *
     * @param locator locator that may resolve to both hidden and visible copies
     * @return the first match that is displayed with a non-zero bounding box
     * @throws org.openqa.selenium.TimeoutException if no match becomes visible within 15 seconds
     */
    public static WebElement firstVisible(By locator) {
        /* withMessage, because the condition is a lambda.
         *
         * WebDriverWait names the condition in its timeout text, and for a lambda that name is the
         * JVM's synthetic class - "waiting for CustomUtil$$Lambda$1082/0x00000008007e1c40@4e9034cb".
         * In the 3 Sep 2026 run that was 24 of 36 failure messages: the locator that actually timed
         * out appeared nowhere in the report, so every one of them had to be traced by reading this
         * source. isAnyVisible and clickFirstVisible both delegate here, so this one message covers
         * all three call paths.
         *
         * The "matches may exist but be 0x0" wording is deliberate: that is the case this method
         * exists for, and distinguishing "no element" from "no VISIBLE element" is usually the
         * whole question. */
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

    /**
     * True when at least one match for the locator is actually rendered.
     *
     * <p>Use instead of {@code isDisplayed(By)} wherever a locator can resolve to hidden duplicates:
     * isDisplayed() inspects only the first match, so it reports false while a visible copy exists.
     * Never throws - a locator that matches nothing returns false after the wait elapses.
     *
     * @param locator locator that may resolve to both hidden and visible copies
     * @return true if a rendered match was found within the wait window
     */
    public static boolean isAnyVisible(By locator) {
        try {
            firstVisible(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Clicks the first rendered element matching the locator, using a real Actions click.
     *
     * <p>Prefer this over jsClick for anything in the header or category navigation. A real click
     * produces genuine pointer events, which the Radix-based components this app is built on
     * require - they listen on pointerdown, and a JavaScript .click() never fires that.
     *
     * @param locator locator that may resolve to both hidden and visible copies
     */
    public static void clickFirstVisible(By locator) {
        WebElement element = firstVisible(locator);
        ((JavascriptExecutor) Grid.driver())
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        new Actions(Grid.driver()).moveToElement(element).click().perform();
    }

    /**
     * Scrolls the first rendered match into view and clicks it, falling back to a JavaScript
     * click if the real click is intercepted.
     *
     * <p>Use this rather than {@link #scrollingToViewAndClickUsingJS(WebElement)}, which despite
     * its name only scrolls.
     *
     * @param locator locator of the element to scroll to and click
     */
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

    /**
     * Scrolls the given element into view and clicks it, falling back to a JavaScript
     * click if the real click is intercepted. This overload accepts a resolved WebElement.
     *
     * @param element the element to scroll to and click
     */
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

    /**
     * Visibility check performed via the JavaScript executor (computed style + bounding rect)
     * instead of Selenium's isDisplayed(). Waits for the element to be present in the DOM, then
     * asks the browser whether it is actually rendered and visible. Returns false if the element
     * is absent or not visible (never throws).
     */
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
     * Scrolls the div whose visible text equals {@code text} to the centre of the viewport.
     *
     * <p>The argument is TEXT CONTENT, not an XPath. It used to be an overload of
     * {@link #scrollToCenter(By)} named scrollToCenter(String), which made it the silent winner of
     * overload resolution whenever a caller passed a String XPath constant - producing selectors
     * like {@code //div[normalize-space()='//li/a[contains(@href, 'x')]']}, which is not valid
     * XPath and throws InvalidSelectorException. Renaming it removes that trap.
     *
     * @param text exact visible text of the target div, after whitespace normalisation
     */
    public static void scrollToCenterByText(String text) {
        // XPath 1.0 has no escape syntax, so pick a quote character the text does not contain.
        String quoted = text.contains("'") ? "\"" + text + "\"" : "'" + text + "'";
        scrollToCenter(By.xpath("//div[normalize-space()=" + quoted + "]"));
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

    /**
     * Moves the mouse over the first RENDERED match, so a hover-driven menu actually opens.
     *
     * <p>This used to resolve the element with {@code findElement}, i.e. the first match in document
     * order. The CCP header is rendered twice and the first copy measures 0x0 (measured on QA - see
     * homeheadersteps.yaml), so the Actions hover landed on a zero-size box and produced no hover at
     * all. Every category mega-menu step then timed out looking for sub-category links that were
     * never going to render: build #233 lost 31 scenarios to exactly this.
     *
     * <p>{@code visibilityOfElementLocated} did not save it either - that condition also inspects
     * only the first match, which is why the old 2-second wait was the thing that threw.
     *
     * <p>The element is scrolled to centre first: {@code moveToElement} on a node outside the
     * viewport is a "move target out of bounds" error in Selenium 4.
     */
    public static void hoverOverElement(By locator)
    {
        WebElement element = firstVisible(locator);

        ((JavascriptExecutor) Grid.driver())
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);

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

    /**
     * Reads an element's text through the JavaScript executor instead of {@code WebElement.getText()}.
     *
     * <p><b>Why this exists.</b> Selenium's {@code getText()} returns only text the browser is
     * currently RENDERING, so it comes back empty for an element that is {@code display:none} or
     * otherwise not laid out - even when the text sits in the DOM. Reading it through the DOM
     * instead is how you get at a value that is present but not painted.
     *
     * <p>Tries {@code innerText} first and falls back to {@code textContent}. What separates the two
     * is measured, not assumed - CCP's home page at 1440x900 renders {@code //header} twice:
     *
     * <pre>
     *   copy 0    0 x 0        innerText 422    textContent 422
     *   copy 1    1425 x 120   innerText 158    textContent 447
     * </pre>
     *
     * <p>Two things follow, and both matter when choosing a method:
     *
     * <ul>
     *   <li><b>On an element with no layout box, {@code innerText} is {@code textContent}.</b> The
     *       HTML spec says so, and copy 0's identical 422/422 is that rule. There is no need for a
     *       fallback to reach a hidden element's text - {@code innerText} already gives it.</li>
     *   <li><b>On a rendered element the two disagree sharply</b> - 158 against 447. The extra 289
     *       characters are inside closed menus. So {@code textContent} will satisfy a
     *       {@code contains(...)} assertion using text that NO customer can see, which is a false
     *       pass, not a robust one.</li>
     * </ul>
     *
     * <p>Hence the order: {@code innerText} is what a person reads on a visible element and is
     * already the full DOM text on a hidden one, which makes it right in both cases;
     * {@code textContent} is only the safety net for markup where {@code innerText} yields nothing.
     * Returns "" rather than null when the element has no text at all.
     *
     * <p>Where the question is "can the customer SEE this text", none of these is the answer - use
     * {@link #getText(Object)} on {@link #firstVisible(By)}, so the visible copy is the one read.
     *
     * @param target By or WebElement, as {@link #resolveElement(Object)} accepts
     * @return the element's text, trimmed, or "" when it has none
     */
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

    /**
     * Reads an element's text through every mechanism that can carry one, in order of fidelity.
     *
     * <p>Selenium {@code getText()}, then {@code innerText}/{@code textContent} via
     * {@link #getTextJS(Object)}, then the {@code value} attribute - which is where an
     * {@code <input>} keeps its text, since inputs have no child text node at all and return "" from
     * all three of the preceding calls.
     *
     * <p>Extracted from a private copy in MProfileSteps so the four-way cascade is written once.
     * Prefer the narrowest method that answers your question: {@link #getText(Object)} when the text
     * must be visible, {@link #getTextJS(Object)} when it need only be in the DOM, and this when you
     * are reading a field whose markup you do not control.
     *
     * @param target By or WebElement, as {@link #resolveElement(Object)} accepts
     * @return the first non-empty text found, trimmed, or "" if every source is empty
     */
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