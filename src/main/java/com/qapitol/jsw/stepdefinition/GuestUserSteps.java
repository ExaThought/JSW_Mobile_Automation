package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.Collections;
import java.util.List;

import static com.qapitol.jsw.utils.CustomUtil.clickFirstVisible;
import static com.qapitol.jsw.utils.CustomUtil.isAnyVisible;
import static com.qapitol.jsw.utils.CustomUtil.xpathOf;
import static com.qapitol.jsw.utils.Hooks.softAssert;

public class GuestUserSteps extends AbstractWebPage {

    private final String ACTIVE_ORDER = "activeOrders";
    private final String LOGIN_REGISTER_PAGE = "loginRegistrationPage";
    private final String MY_REQUIREMENT = "myRequirements";
    private final String MY_ORDERS = "myOrders";
    private final String LOGIN_PAGE = "loginPage";
    private static final String MASTERCLASS_SECTION = "masterclassSection";
    private static final String MASTERCLASS_PLAY_BUTTON = "masterclassPlayButton";
    private static final String MASTERCLASS_PLAYER_FRAME = "masterclassPlayerFrame";
    private static final String MASTERCLASS_VIDEO_SOURCE = "playedVideo";
    private static final String BLOGS_PAGE_HEADING = "blogsPageVerification";
    private static final String BLOGS_PAGE_ARTICLE_LINKS = "blogsPageArticleLinks";

    private static final String SELECT_LOCATION_BUTTON = "selectLocationButton";
    private static final String DELIVERY_LOCATION_MODAL = "deliveryLocationModal";
    private static final String DELIVERY_PINCODE_INPUT = "deliveryLocationPincodeInput";
    private static final String DELIVERY_LOCATION_CLOSE_BUTTON = "deliveryLocationCloseButton";
    private static final String DELIVERY_LOCATION_CHECK_BUTTON = "deliveryLocationCheckButton";
    private static final String HEADER_LOCATION_LABEL = "headerLocationLabel";
    private static final int LOCATION_MODAL_WAIT_SECONDS = 10;
    String pageName;

    /* No shared `wait` field. It called Grid.driver() at construction time, which binds the wait to
     * whichever session was current when Cucumber built this glue instance - so after a session
     * switch it would still drive the old session's driver, and if the field initialiser ran before
     * any session existed Grid.driver() throws rather than returning null. Every wait below is
     * built inside the method that uses it, so it resolves the calling thread's current driver. */
    HomepageBlogSteps homepageBlogSteps = new HomepageBlogSteps();

    /**
     * Waits for the document to finish loading, naming the page in the timeout.
     *
     * <p>Neither caller catches this, so the message reaches the report. A bare
     * {@code wait.until(webDriver -> ...)} reported only
     * {@code waiting for GuestUserSteps$$Lambda$NNNN/0x...@hash}, which names neither the URL
     * being loaded nor the condition being waited on.
     */
    private void waitForPageLoad() {
        new WebDriverWait(Grid.driver(), Duration.ofSeconds(30))
                .withMessage("document.readyState never reached 'complete' within 30s for '"
                        + pageName + "' - still at " + Grid.driver().getCurrentUrl())
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));
    }

    @Given("user open the CCP portal as guest user")
    public void userOpenTheCCPPortalAsGuestUser() {
        String url = ConfigUtil.getSecretValue("ccpUrl");
        log.info("Opening the portal as a guest: " + url);
        Grid.driver().get(url);
        Grid.driver().manage().window().maximize();
        log.info("Portal opened as a guest, current URL: " + Grid.driver().getCurrentUrl());
    }

    @When("User clicks on Login button")
    public void userClicksOnLoginButton() {
        sleep(1000);
        WebElement LOGIN_BUTTON = Grid.driver().findElement(By.xpath("//button[text()='Login']"));
        JavascriptExecutor js = Grid.driver();
        js.executeScript("arguments[0].scrollIntoView(true);", LOGIN_BUTTON);
        js.executeScript("arguments[0].click();", LOGIN_BUTTON);
    }

    @Then("User should be redirected to Login or Register")
    public void userShouldBeRedirectedToLoginOrRegister() {
        // Local wait, not the shared `wait` field: withMessage() mutates the instance it is called
        // on, so setting a message on the field would attach this text to every later use of it.
        waitForPageLoad();
        if (isElementPresent(LOGIN_PAGE)) {
            softAssert.assertTrue(isElementPresent(LOGIN_REGISTER_PAGE), "User is not prompted to Login or Register when trying to access " + pageName + " page as Guest User");
        }
    }

    @Then("Active order section should not be displayed")
    public void activeOrderSectionShouldNotBeDisplayed() {
        softAssert.assertFalse(isElementPresent(ACTIVE_ORDER), "Active order section is displayed for Guest User");
    }

    @When("user give direct {string}")
    public void userGiveDirectUrl(String pageName) {
        this.pageName = pageName;
        Grid.driver().get(ConfigUtil.getSecretValue(pageName));
        Grid.driver().manage().window().maximize();
    }

    @Then("user should be prompted to Login or Register")
    public void userShouldBePromptedToLoginOrRegister() {
        //Wait until the page is loaded completely before verifying the presence of Login/Register page elements to avoid flaky test execution
        waitForPageLoad();
        if (isElementPresent(LOGIN_PAGE)) {
            softAssert.assertTrue(isElementPresent(LOGIN_REGISTER_PAGE), "User is not prompted to Login or Register when trying to access " + pageName + " page as Guest User");
        }
    }

    @Then("user should not able to see {string} section")
    public void userShouldNotAbleToSeeSection(String sectionName) {
        if (sectionName.equalsIgnoreCase("My Requirements")) {
            softAssert.assertFalse(isElementPresent(MY_REQUIREMENT), sectionName + " section is displayed for Guest User");
        } else if (sectionName.equalsIgnoreCase("My Orders")) {
            softAssert.assertFalse(isElementPresent(MY_ORDERS), sectionName + " section is displayed for Guest User");
        }
    }

    @Then("MSME Masterclass video section should be displayed in home page and able to play")
    public void MsmeMasterclassVideoSectionShouldBeDisplayedInHomePageAndAbleToPlay() {
        softAssert.assertTrue(waitAndCheckIsElementVisible(MASTERCLASS_SECTION),
                "MSME Masterclass section is not displayed on the home page");

        clickFirstVisible(xpathOf(getLocator(MASTERCLASS_PLAY_BUTTON)));

        By playerFrame = xpathOf(getLocator(MASTERCLASS_PLAYER_FRAME));
        new WebDriverWait(Grid.driver(), Duration.ofSeconds(50))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(playerFrame));
        try {
            softAssert.assertTrue(waitAndCheckIsElementPresent(MASTERCLASS_VIDEO_SOURCE),
                    "MSME Masterclass video is not played on clicking the video thumbnail -"
                            + " no video source rendered inside the player frame");
            log.info("MSME Masterclass video is played");
        } finally {
            Grid.driver().switchTo().defaultContent();
        }
    }

    @And("clicks on the Blogs label of the top navbar")
    public void userClicksOnTheBlogsLabelOfTheTopNavbar() {
        //homepageBlogSteps.userNavigateToBlog();
        WebElement Blogs = Grid.driver().findElement(By.xpath("//a[normalize-space()='Blogs' or contains(@href,'/blogs')]"));
        Blogs.click();
    }

    /**
     * Two separate defects made this step incapable of running.
     *
     * <p>The XPath carried a stray {@code "']"}, so the driver rejected it as an invalid selector -
     * meaning this step had never once executed. And {@code visibilityOfAllElements} was handed a
     * findElements() list resolved BEFORE the wait started; that overload never re-queries the DOM,
     * so an empty first look could never recover no matter how long it waited.
     *
     * <p>Both are gone: the page is identified by a curated locator key and the blog count uses the
     * locator-based overload, which re-queries on every poll.
     */
    @Then("Blogs Page should be displayed with multiple blogs")
    public void BlogsPageShouldBeDisplayedWithMultipleBlogs() {
        // isAnyVisible, not waitAndCheckIsElementVisible: the heading renders twice on /blogs and
        // the second copy measures 0x0, so a first-match visibility check is one DOM-order change
        // away from reporting a visible heading as hidden.
        softAssert.assertTrue(isAnyVisible(xpathOf(getLocator(BLOGS_PAGE_HEADING))),
                "Blogs page did not load - the 'Blogs' heading is not shown");

        By articleLinks = xpathOf(getLocator(BLOGS_PAGE_ARTICLE_LINKS));
        List<WebElement> blogs;
        try {
            blogs = new WebDriverWait(Grid.driver(), Duration.ofSeconds(30))
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(articleLinks));
        } catch (TimeoutException e) {
            blogs = Collections.emptyList();
        }
        softAssert.assertTrue(blogs.size() > 1,
                "Blogs page should list more than one blog but found " + blogs.size()
                        + " matching " + articleLinks);
    }

    // ------------------- Delivery location dialog -------------------

    /**
     * Clicks "Select location" by the search bar. A guest gets the "Enter delivery PIN Code"
     * dialog (QA, 12 Oct 2026) - Chrome's own location alert never appears, because
     * JSWCapabilitiesBuilder starts every Chrome with geolocation blocked.
     */
    @When("the guest user clicks on Select location near the search bar")
    public void the_guest_user_clicks_on_select_location_near_the_search_bar() {
        clickFirstVisible(xpathOf(getLocator(SELECT_LOCATION_BUTTON)));
        log.info("Clicked 'Select location' in the header");
    }

    @Then("the delivery PIN code dialog should be displayed")
    public void the_delivery_pin_code_dialog_should_be_displayed() {
        new WebDriverWait(Grid.driver(), Duration.ofSeconds(LOCATION_MODAL_WAIT_SECONDS))
                .withMessage("The delivery PIN code dialog did not open after clicking Select location")
                .until(ExpectedConditions.visibilityOfElementLocated(xpathOf(getLocator(DELIVERY_LOCATION_MODAL))));
        log.info("The delivery PIN code dialog is open (no Chrome location alert - geolocation is blocked)");
        boolean pinInput = isAnyVisible(xpathOf(getLocator(DELIVERY_PINCODE_INPUT)));
        log.info("PIN code input in the dialog is " + (pinInput ? "visible" : "NOT visible"));
        softAssert.assertTrue(pinInput, "The PIN code input is not shown in the delivery PIN code dialog");
    }

    @When("the guest user closes the delivery PIN code dialog")
    public void the_guest_user_closes_the_delivery_pin_code_dialog() {
        clickFirstVisible(xpathOf(getLocator(DELIVERY_LOCATION_CLOSE_BUTTON)));
        log.info("Clicked the close (X) button of the delivery PIN code dialog");
    }

    /** Closing without choosing anything must change nothing: the dialog is gone, no location is set. */
    @Then("the delivery PIN code dialog should be closed and Select location should still be shown")
    public void the_delivery_pin_code_dialog_should_be_closed_and_select_location_still_shown() {
        boolean closed = false;
        try {
            closed = new WebDriverWait(Grid.driver(), Duration.ofSeconds(LOCATION_MODAL_WAIT_SECONDS))
                    .until(ExpectedConditions.invisibilityOfElementLocated(
                            xpathOf(getLocator(DELIVERY_LOCATION_MODAL))));
        } catch (TimeoutException ignored) {
        }
        log.info("Delivery PIN code dialog is " + (closed ? "closed" : "STILL OPEN") + " after clicking X");
        softAssert.assertTrue(closed, "The delivery PIN code dialog is still open after clicking its close button");
        boolean stillSelectLocation = isAnyVisible(xpathOf(getLocator(SELECT_LOCATION_BUTTON)));
        log.info("Header " + (stillSelectLocation ? "still shows 'Select location' - no location was set"
                : "no longer shows 'Select location' - the location changed"));
        softAssert.assertTrue(stillSelectLocation,
                "'Select location' is no longer shown in the header, so closing the dialog changed the location");
    }

    @When("the guest user enters {string} in the PIN code field and clicks Check")
    public void the_guest_user_enters_pin_code_and_clicks_check(String pinCode) {
        WebElement input = Grid.driver().findElement(xpathOf(getLocator(DELIVERY_PINCODE_INPUT)));
        input.clear();
        input.sendKeys(pinCode);
        log.info("Entered PIN code " + pinCode + " in the delivery PIN code dialog");
        // Check is disabled until a full PIN code is typed; wait for it to enable rather than
        // clicking a disabled button, which fails as an intercepted click.
        new WebDriverWait(Grid.driver(), Duration.ofSeconds(LOCATION_MODAL_WAIT_SECONDS))
                .withMessage("The Check button did not enable after entering PIN code " + pinCode)
                .until(ExpectedConditions.elementToBeClickable(xpathOf(getLocator(DELIVERY_LOCATION_CHECK_BUTTON))));
        clickFirstVisible(xpathOf(getLocator(DELIVERY_LOCATION_CHECK_BUTTON)));
        log.info("Clicked Check in the delivery PIN code dialog");
    }

    /**
     * After Check the dialog closes and the header shows the location in place of "Select
     * location". Matched on the exact label the app renders, e.g. "Mumbai cit.. 400051".
     */
    @Then("the delivery PIN code dialog should be closed and the header should show {string}")
    public void the_dialog_should_be_closed_and_the_header_should_show(String label) {
        boolean closed = false;
        try {
            closed = new WebDriverWait(Grid.driver(), Duration.ofSeconds(LOCATION_MODAL_WAIT_SECONDS))
                    .until(ExpectedConditions.invisibilityOfElementLocated(
                            xpathOf(getLocator(DELIVERY_LOCATION_MODAL))));
        } catch (TimeoutException ignored) {
        }
        log.info("Delivery PIN code dialog is " + (closed ? "closed" : "STILL OPEN") + " after clicking Check");
        softAssert.assertTrue(closed, "The delivery PIN code dialog is still open after clicking Check");

        By labelLocator = xpathOf(String.format(getLocator(HEADER_LOCATION_LABEL), label));
        boolean shown = false;
        long deadline = System.currentTimeMillis() + LOCATION_MODAL_WAIT_SECONDS * 1000L;
        while (!shown && System.currentTimeMillis() < deadline) {
            shown = isAnyVisible(labelLocator);
            if (!shown) {
                sleep(500);
            }
        }
        log.info("Header " + (shown ? "shows '" + label + "'" : "does NOT show '" + label + "'"));
        softAssert.assertTrue(shown, "The header does not show '" + label + "' after setting the PIN code");

        boolean selectLocationGone = !isAnyVisible(xpathOf(getLocator(SELECT_LOCATION_BUTTON)));
        log.info("'Select location' is " + (selectLocationGone ? "no longer shown" : "STILL shown") + " in the header");
        softAssert.assertTrue(selectLocationGone,
                "'Select location' is still shown in the header after setting the PIN code");
    }
}
