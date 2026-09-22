package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.NetworkCapture;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Gherkin wrapper over {@link NetworkCapture}, for reading a server-generated value out of the
 * application's own API response and typing it into the UI.
 *
 * <p>These steps take a RAW locator - an xpath or a css selector - rather than a locator key.
 * That is deliberate. Sauron resolves a locator key against the yaml named for the class that
 * asks ({@code loginsteps.yaml} for {@link LoginSteps}), so a key passed to a step defined HERE
 * would be looked up in {@code networkcapturesteps.yaml} and not found. Inside an existing step
 * class the key works normally - call {@link NetworkCapture} directly there and keep using
 * {@code waitAndSetText(YOUR_KEY, value)}.
 */
public class NetworkCaptureSteps extends AbstractWebPage {

    /** Wait for the target field, in seconds. */
    private static final int FIELD_WAIT_SECONDS = 20;

    /**
     * Built per call. Cucumber instantiates glue classes up front, so a WebDriverWait held in a
     * field would capture whatever driver existed at construction time rather than this
     * scenario's. Same reason as {@code SearchSteps.waitFor()}.
     */
    private WebDriverWait waitFor() {
        return new WebDriverWait(Grid.driver(), Duration.ofSeconds(FIELD_WAIT_SECONDS));
    }

    @Given("start capturing API responses")
    public void startCapturingApiResponses() {
        NetworkCapture.install();
    }

    /**
     * Drops everything captured so far, keeping the shim in place. Put this immediately before
     * the action that triggers the call you care about, so an earlier response with the same URL
     * cannot be read back by mistake - which is how a re-sent OTP ends up returning the first one.
     */
    @Given("clear captured API responses")
    public void clearCapturedApiResponses() {
        NetworkCapture.clear();
    }

    /**
     * Waits for a response whose URL contains {@code urlContains}, pulls {@code jsonPath} out of
     * it, and types the result into {@code locator}.
     *
     * <pre>{@code
     * And enter "data.otp" from API "/otp/generate" into "//input[@id='otp']"
     * }</pre>
     */
    @And("enter {string} from API {string} into {string}")
    public void enterValueFromApiInto(String jsonPath, String urlContains, String locator) {
        String value = NetworkCapture.awaitJsonValue(urlContains, jsonPath);
        WebElement field = waitFor().until(ExpectedConditions.elementToBeClickable(toBy(locator)));
        field.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        field.sendKeys(value);
    }

    /**
     * Same, but spreads the value one character per field - the common OTP layout where each
     * digit has its own input. {@code locator} must match ALL the boxes.
     *
     * <pre>{@code
     * And enter "data.otp" from API "/otp/generate" one character per field "//input[@data-otp]"
     * }</pre>
     */
    @And("enter {string} from API {string} one character per field {string}")
    public void enterValueFromApiPerField(String jsonPath, String urlContains, String locator) {
        String value = NetworkCapture.awaitJsonValue(urlContains, jsonPath);
        By by = toBy(locator);
        waitFor().until(ExpectedConditions.presenceOfElementLocated(by));
        List<WebElement> boxes = Grid.driver().findElements(by);
        if (boxes.size() < value.length()) {
            throw new IllegalStateException("Got a " + value.length() + "-character value but \""
                    + locator + "\" matches only " + boxes.size() + " field(s).");
        }
        for (int i = 0; i < value.length(); i++) {
            WebElement box = boxes.get(i);
            box.clear();
            box.sendKeys(String.valueOf(value.charAt(i)));
        }
    }

    /**
     * Accepts what the locator yaml files already mix: an {@code xpath=} prefix, a bare xpath, or
     * a css selector. Same rule as {@code CartSteps.byForLocatorKey}.
     */
    private static By toBy(String locator) {
        String raw = locator.startsWith("xpath=") ? locator.substring("xpath=".length()) : locator;
        boolean looksLikeXpath = raw.startsWith("//") || raw.startsWith("(") || raw.startsWith("./");
        return looksLikeXpath ? By.xpath(raw) : By.cssSelector(raw);
    }
}
