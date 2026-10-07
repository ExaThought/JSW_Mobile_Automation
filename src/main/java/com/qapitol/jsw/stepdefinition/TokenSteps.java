package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.Token;
import com.qapitol.jsw.utils.UserConfig;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.qapitol.jsw.utils.CustomUtil.*;
import static com.qapitol.jsw.utils.Hooks.softAssert;

public class TokenSteps extends AbstractWebPage {

    private static final String LOGIN_EMAIL = "ccpLoginEmail";
    private static final String LOGIN_PASSWORD = "ccpLoginPassword";
    private static final String LEDGER_PAGE = "ledgerPage";
    private static final String TRACK_MATERIAL_PAGE = "trackMaterialPage";
    private final String CART_PAGE = "cartPage";
    private final String MY_REQUIREMENT_PAGE = "myRequirementPage";
    private final String MY_ORDER_PAGE = "myOrderPage";
    private final String ORDER_SUMMARY_PAGE = "orderSummaryPage";
    private final String CONTINUE_WITH_EMAIL = "continueWithEmailCta";
    private final String TMT_PRODUCT = "tmtProduct";
    private final String LOGIN_CTA = "loginButton";
    private final String USER_MENU_PROFILE_OPTION = "profileDetails";
    private final String ACCOUNT_TAB = "(//span[text()='Account'])[2]";
    private static final By SEARCH_BOX_LOCATOR = By.xpath("//span[text()='Search']/../following-sibling::button");
    private static final By SEARCH_INPUT_LOCATOR = By.xpath("//input[@data-slot='input']");
    public static final String CONTINUE_CTA = "continueCTA";
    private String pageName;
    private String url;

    @When("user set expired token in local storage")
    public void user_set_expired_token_in_local_storage() {
        String expiredToken = Token.getExpiredCcpToken();

        JavascriptExecutor js = Grid.driver();
        js.executeScript("window.localStorage.setItem('token', arguments[0]);", expiredToken);
        log.info("Expired token set in localStorage");

        setExpiredTokenCookie(expiredToken);
        sleep(2000); // Give the browser a moment to write the cookie before refreshing
    }

    private void setExpiredTokenCookie(String expiredToken) {
        try {
            boolean secureOrigin = Grid.driver().getCurrentUrl().toLowerCase().startsWith("https:");
            Cookie expiredCookie = new Cookie.Builder("token", expiredToken)
                    .path("/")
                    .isSecure(secureOrigin)
                    .sameSite("Strict")
                    .build();

            Grid.driver().manage().deleteCookieNamed("token");
            Grid.driver().manage().addCookie(expiredCookie);

            Cookie written = Grid.driver().manage().getCookieNamed("token");
            if (written == null) {
                log.info("The 'token' cookie is not readable after addCookie - the browser rejected"
                        + " it, or the app does not use one. localStorage still carries the expired"
                        + " token, which is what drives the logout.");
            } else {
                log.info("Expired token set in the 'token' cookie (secure=" + secureOrigin + ")");
            }
        } catch (Exception e) {
            log.info("Could not set the 'token' cookie through the WebDriver API: " + e.getMessage()
                    + ". Continuing - the expired token is already in localStorage.");
        }
    }

    @An("user refresh the page")
    public void user_refresh_the_page() {
        url = Grid.driver().getCurrentUrl();
        Grid.driver().navigate().refresh();
        sleep(2000); // Give the browser a moment to refresh and re-render the page
        Grid.driver().navigate().refresh();
        log.info("Page refreshed after setting expired token");
    }

    @Then("verify the user is navigate to Guest home page")
    public void verify_the_user_is_navigate_to_guest_home_page() {
        By loginCta = By.xpath("//button[text()='Login']");
        String urlAfterExpiry = Grid.driver().getCurrentUrl();

        if (urlAfterExpiry.contains("/login")) {
            log.info("Token expiry redirected straight to the login page: " + urlAfterExpiry);
            softAssert.assertTrue(isAnyVisible(By.xpath(getLocator(CONTINUE_WITH_EMAIL))),
                    "Redirected to " + urlAfterExpiry + " after token expiry from " + pageName
                            + ", but the auth modal did not open");
            return;
        }

        // Still on a guest page: a VISIBLE Login button is the evidence of being logged out.
        softAssert.assertTrue(isAnyVisible(loginCta),
                "Not logged out after token expiry from " + pageName + " - no visible Login button"
                        + " and the URL is still " + urlAfterExpiry);
        if (!isAnyVisible(loginCta)) {
            return;
        }

        clickFirstVisible(loginCta);
        softAssert.assertTrue(isAnyVisible(By.xpath(getLocator(CONTINUE_WITH_EMAIL))),
                "Clicking Login after token expiry from " + pageName + " did not open the auth"
                        + " modal - 'Continue with email' never became visible. URL: "
                        + Grid.driver().getCurrentUrl());
    }

    @Then("verify the user is logged out and navigate to login page")
    public void verify_the_user_is_logged_out_and_navigate_to_login_page() {
        softAssert.assertTrue(waitAndCheckIsElementVisible(CONTINUE_WITH_EMAIL) || waitAndCheckIsElementVisible(LOGIN_CTA), "User is not logged out or did not navigate to login page for token expiry from " + pageName);
        log.info("User navigate to login Page after token expiry and continue with email button is visible");
    }

    @When("user open the cart page")
    public void user_open_the_cart_page() {
        pageName = "Cart Page";
        waitAndCheckIsElementClickable(CART_PAGE);
        waitAndClickOn(CART_PAGE);
        log.info("User navigated to cart page successfully to test token expiry in cart page");
    }

    @When("user open the my requirement page")
    public void user_open_the_my_requirement_page() {
        waitAndCheckIsElementClickable(MY_REQUIREMENT_PAGE);
        waitAndClickOn(MY_REQUIREMENT_PAGE);
        log.info("User navigated to my requirement page successfully to test token expiry in my requirement page");
    }

    @When("user open the my order page")
    public void user_open_the_my_order_page() {
        waitAndCheckIsElementClickable(MY_ORDER_PAGE);
        waitAndClickOn(MY_ORDER_PAGE);
        log.info("User navigated to my order page successfully to test token expiry in my order page");
    }

    @When("user open the order summary page")
    public void user_open_the_order_summary_page() {
        waitAndCheckIsElementClickable(MY_ORDER_PAGE);
        waitAndClickOn(MY_ORDER_PAGE);
        log.info("User navigated to my order page successfully to test token expiry in my order page");
        waitAndCheckIsElementClickable(ORDER_SUMMARY_PAGE);
        waitAndClickOn(ORDER_SUMMARY_PAGE);
        log.info("User navigated to order summary page successfully to test token expiry in order summary page");
    }

    @When("user search for {string}")
    public void user_search_for(String product) {
        pageName = "Search Page";
        clickFirstVisible(SEARCH_BOX_LOCATOR);
        new WebDriverWait(Grid.driver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated(SEARCH_INPUT_LOCATOR));
        new Actions(Grid.driver()).sendKeys(product).sendKeys(Keys.ENTER).perform();
        log.info("User searched for " + product + " successfully");
    }

    @Then("verify the user is able to login successfully")
    public void verify_the_user_is_able_to_login_successfully() {
        closeBlockingDialogsWithin(BLOCKING_DIALOG_QUICK_WAIT_SECONDS);
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
        WebElement logo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/']")));
        jsClick(logo);
        boolean result = false;
        try {
            result = wait.until(ExpectedConditions.urlToBe(url));
        } catch (Exception e) {
            result = false;
        }
        softAssert.assertTrue(result, "User is not navigated to same page after login from " + pageName);
        log.info("User is navigated to same page after login from " + pageName);
    }

    @And("user again login with configured user")
    public void user_again_login_with_configured_user() {
        clickFirstVisible(By.xpath("//button[text()='Login']"));
        user_again_login(UserConfig.getEmail(), UserConfig.getPassword());
    }

    @And("user again login with {string} and {string}")
    public void user_again_login(String emailId, String pass) {
        waitAndCheckIsElementVisible(CONTINUE_WITH_EMAIL);
        waitAndClickOn(CONTINUE_WITH_EMAIL); //Click on Login with OTP
        waitAndCheckIsElementVisible(LOGIN_EMAIL);
        waitAndSetText(LOGIN_EMAIL, emailId); // Give you Email
        waitAndCheckIsElementVisible(CONTINUE_CTA);
        waitAndClickOn(CONTINUE_CTA);
        waitAndCheckIsElementVisible(LOGIN_PASSWORD);
        waitAndSetText(LOGIN_PASSWORD, pass);// Give the password
        waitAndCheckIsElementVisible(CONTINUE_CTA);
        waitAndClickOn(CONTINUE_CTA);// Login to continue
        Grid.driver().navigate().refresh();
        waitUntilElementIsNotPresent(LOGIN_CTA);
    }

    @When("user open the ledger page")
    public void user_open_the_ledger_page() {
        pageName = "Ledger Page";
        closeBlockingDialogsWithin(BLOCKING_DIALOG_QUICK_WAIT_SECONDS);
        WebElement account_label = Grid.driver().findElement(By.xpath(ACCOUNT_TAB));
        softAssert.assertTrue(account_label.isDisplayed(), "Account section is not displayed");
        jsClick(account_label);
        String locator = getLocator(LEDGER_PAGE);
        scrollToCenter(By.xpath(locator));
        waitAndCheckIsElementClickable(LEDGER_PAGE);
        waitAndClickOn(LEDGER_PAGE);
        log.info("User navigated to ledger page successfully to test token expiry in ledger page");
    }

    @When("user open the Track Material page")
    public void user_open_the_track_material_page() {
        pageName = "Track Material Page";
        closeBlockingDialogsWithin(BLOCKING_DIALOG_QUICK_WAIT_SECONDS);
        WebElement account_label = Grid.driver().findElement(By.xpath(ACCOUNT_TAB));
        softAssert.assertTrue(account_label.isDisplayed(), "Account section is not displayed");
        jsClick(account_label);
        String locator = getLocator(TRACK_MATERIAL_PAGE);
        scrollToCenter(By.xpath(locator));
        waitAndCheckIsElementClickable(TRACK_MATERIAL_PAGE);
        waitAndClickOn(TRACK_MATERIAL_PAGE);
        log.info("User navigated to Track Material page successfully to test token expiry in Track Material page");
    }

    @When("user clicks on TMT product")
    public void userClicksOnTMTProduct() {
        scrollToCenter(By.xpath("//h2[text()='TMT']"));
        waitAndClickOn(TMT_PRODUCT);
    }

    @Then("verify the user is logged out")
    public void verifyTheUserIsLoggedOut() {
        softAssert.assertTrue(isElementPresent(LOGIN_CTA) || isElementPresent(CONTINUE_WITH_EMAIL), "User is not logged out");
    }

    @And("user navigate to login page and again login with configured user")
    public void userNavigateTOLoginPageAndAgainLoginWithConfiguredUser() {
        By loginCta = By.xpath(getLocator(LOGIN_CTA));
        if (isAnyVisible(loginCta)) {
            clickFirstVisible(loginCta);
        } else {
            log.info("No visible Login button - the app already redirected to the login page with "
                    + "the auth modal open, so there is nothing to click");
        }
        user_again_login(UserConfig.getEmail(), UserConfig.getPassword());
    }

    @Then("verify user is able to login successfully")
    public void verifyUserIsAbleToLoginSuccessfully() {
        closeBlockingDialogsWithin(BLOCKING_DIALOG_QUICK_WAIT_SECONDS);
        WebElement account_label = Grid.driver().findElement(By.xpath(ACCOUNT_TAB));
        softAssert.assertTrue(account_label.isDisplayed(), "Account section is not displayed");
        jsClick(account_label);
        softAssert.assertTrue(isElementPresent(USER_MENU_PROFILE_OPTION));
    }

}
