package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.jsw.utils.Token;
import com.qapitol.jsw.utils.UserConfig;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import org.openqa.selenium.By;
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
    private final String CONFIRM_BUSINESS_DETAILS_DIALOG = "confirmBusinessDetails";
    public static final String CONTINUE_CTA = "continueCTA";
    private String pageName;
    private String url;

    @When("user set expired token in local storage")
    public void user_set_expired_token_in_local_storage() {
        String expiredToken = Token.getExpiredCcpToken();
        JavascriptExecutor js = Grid.driver();
        // Inject expired/invalid token
        js.executeScript("window.localStorage.setItem('token', arguments[0]);", expiredToken);
        js.executeScript(
                "document.cookie = 'token=" + expiredToken + "; path=/; Secure; SameSite=Strict';"
        );
        log.info("Expired token successfully set in local storage");
    }

    @An("user refresh the page")
    public void user_refresh_the_page() {
        url = Grid.driver().getCurrentUrl();
        Grid.driver().navigate().refresh();
        log.info("Page refreshed after setting expired token");
    }

    @Then("verify the user is navigate to Guest home page")
    public void verify_the_user_is_navigate_to_guest_home_page() {
        softAssert.assertTrue(waitAndCheckIsElementVisible(LOGIN_CTA), "User is not logged out or did not navigate to login page for token expiry from " + pageName);
        log.info("User navigate to Guest user home Page after token expiry and product are visible");

        WebElement loginPageElement = Grid.driver().findElement(By.xpath("//button[text()='Login']"));
        if (loginPageElement.isDisplayed()) {
            log.info("Login page is displayed, user is logged out");
            loginPageElement.click();
            sleep(1000);
            String currentUrl = Grid.driver().getCurrentUrl();
            if (currentUrl.contains("/login")) {
                log.info("User successfully navigated to login page after token expiry");
            } else {
                throw new AssertionError("User did not navigate to login page after token expiry, current URL: " + currentUrl + " from " + pageName);
            }
        }
    }

    @Then("verify the user is logged out and navigate to login page")
    public void verify_the_user_is_logged_out_and_navigate_to_login_page() {
        softAssert.assertTrue(waitAndCheckIsElementVisible(CONTINUE_WITH_EMAIL), "User is not logged out or did not navigate to login page for token expiry from " + pageName);
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
        // The header renders two search widgets with identical classes and the first is 0x0, so
        // jsClick/findElement resolve the invisible copy and time out. clickFirstVisible filters
        // every match by rendered state and clicks the one the user actually sees.
        clickFirstVisible(SEARCH_BOX_LOCATOR);
        // The overlay input autofocuses when the search opens and is only 1px tall, so send keys
        // to the focused field instead of clicking it.
        new WebDriverWait(Grid.driver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated(SEARCH_INPUT_LOCATOR));
        new Actions(Grid.driver()).sendKeys(product).sendKeys(Keys.ENTER).perform();
        log.info("User searched for " + product + " successfully");
    }

    @Then("verify the user is able to login successfully")
    public void verify_the_user_is_able_to_login_successfully() {
        // The business-details dialog can appear after a token refresh and would intercept the logo
        // click below. Its sibling step verifyUserIsAbleToLoginSuccessfully() already guards for it.
        confirmBusinessDetailsIfDialogPresent();
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
        WebElement LOGIN_BUTTON = Grid.driver().findElement(By.xpath("//button[text()='Login']"));
        JavascriptExecutor js = Grid.driver();
        js.executeScript("arguments[0].click();", LOGIN_BUTTON);
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
        sleep(2000);
        Grid.driver().navigate().refresh();
        waitUntilElementIsNotPresent(LOGIN_CTA);
    }

    @When("user open the ledger page")
    public void user_open_the_ledger_page() {
        pageName = "Ledger Page";
        confirmBusinessDetailsIfDialogPresent();
        WebElement account_label = Grid.driver().findElement(By.xpath(ACCOUNT_TAB));
        softAssert.assertTrue(account_label.isDisplayed(), "Account section is not displayed");
        jsClick(account_label);
        String locator = getLocator(LEDGER_PAGE);
        scrollingToViewAndClickUsingJS(Grid.driver().findElement(By.xpath(locator)));
        waitAndCheckIsElementClickable(LEDGER_PAGE);
        waitAndClickOn(LEDGER_PAGE);
        log.info("User navigated to ledger page successfully to test token expiry in ledger page");
    }

    @When("user open the Track Material page")
    public void user_open_the_track_material_page() {
        pageName = "Track Material Page";
        confirmBusinessDetailsIfDialogPresent();
        WebElement account_label = Grid.driver().findElement(By.xpath(ACCOUNT_TAB));
        softAssert.assertTrue(account_label.isDisplayed(), "Account section is not displayed");
        jsClick(account_label);
        String locator = getLocator(TRACK_MATERIAL_PAGE);
        scrollingToViewAndClickUsingJS(Grid.driver().findElement(By.xpath(locator)));
        waitAndCheckIsElementClickable(TRACK_MATERIAL_PAGE);
        waitAndClickOn(TRACK_MATERIAL_PAGE);
        log.info("User navigated to Track Material page successfully to test token expiry in Track Material page");
    }

    @When("user clicks on TMT product")
    public void userClicksOnTMTProduct() {
        // Same trap as CartSteps.selectCRSheetProduct(): this is an XPath, so it must be wrapped in
        // By.xpath(). As a bare String it built //div[normalize-space()='//h2[text()='TMT']'] and
        // threw InvalidSelectorException on the nested quotes.
        scrollToCenter(By.xpath("//h2[text()='TMT']"));
        waitAndClickOn(TMT_PRODUCT);
    }

    @Then("verify the user is logged out")
    public void verifyTheUserIsLoggedOut() {
        softAssert.assertTrue(isElementPresent(LOGIN_CTA) || isElementPresent(CONTINUE_WITH_EMAIL), "User is not logged out");
    }

    @And("user navigate to login page and again login with configured user")
    public void userNavigateTOLoginPageAndAgainLoginWithConfiguredUser() {
        if(isElementPresent(LOGIN_CTA)) {
            waitAndClickOn(LOGIN_CTA);
        }
        user_again_login(UserConfig.getEmail(), UserConfig.getPassword());
    }

    @Then("verify user is able to login successfully")
    public void verifyUserIsAbleToLoginSuccessfully() {
        confirmBusinessDetailsIfDialogPresent();
        WebElement account_label = Grid.driver().findElement(By.xpath(ACCOUNT_TAB));
        softAssert.assertTrue(account_label.isDisplayed(), "Account section is not displayed");
        jsClick(account_label);
        softAssert.assertTrue(isElementPresent(USER_MENU_PROFILE_OPTION));
    }

    private void confirmBusinessDetailsIfDialogPresent() {
        try{
            if (isElementPresent(CONFIRM_BUSINESS_DETAILS_DIALOG)) {
                Grid.driver().findElement(By.xpath("//div[@role='dialog']/div/button")).click();
                log.info("Business details confirmation dialog was present and has been closed.");
            }
        } catch (Exception e) {
            log.info("No business details confirmation dialog was present.");
        }
    }
}
