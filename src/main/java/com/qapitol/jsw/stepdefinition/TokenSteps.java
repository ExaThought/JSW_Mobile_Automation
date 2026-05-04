package com.qapitol.jsw.stepdefinition;

import com.qapitol.sauron.common.configuration.Config;
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

import static com.qapitol.jsw.utils.CustomUtil.scrollingToViewAndClickUsingJS;
import static com.qapitol.jsw.utils.Hooks.softAssert;

public class TokenSteps extends AbstractWebPage {

    private final String MILD_STEEL_TITLE = "mildSteelTitle";
    private final String CART_PAGE="cartPage";
    private final String MY_REQUIREMENT_PAGE="myRequirementPage";
    private final String MY_ORDER_PAGE="myOrderPage";
    private final String ORDER_SUMMARY_PAGE="orderSummaryPage";
    private final String SEARCH_BOX="searchBox";
    private final String SEARCH_INPUT="searchInput";
    private final String HOME_PAGE_REGISTER_CTA="homePageRegisterCta";
    private final String CONTINUE_WITH_EMAIL="continueWithEmailCta";
    private static final String LOGIN_VIA_OTP = "loginViaOtp";
    private static final String LOGIN_EMAIL = "ccpLoginEmail";
    private static final String LOGIN_PASSWORD = "ccpLoginPassword";
    private static final String CCP_LOGIN_CTA = "ccpLoginCTA";
    private static final String LEDGER_PAGE="ledgerPage";
    private static final String TRACK_MATERIAL_PAGE="trackMaterialPage";


    private String pageName;
    private String url;
    @When("user set expired token in local storage")
    public void user_set_expired_token_in_local_storage() {
        String expiredToken = Config.getConfigProperty("ccpExpiredToken");
        JavascriptExecutor js = (JavascriptExecutor) Grid.driver();
        // Inject expired/invalid token
        js.executeScript("window.localStorage.setItem('token', arguments[0]);", expiredToken);
        js.executeScript(
                "document.cookie = 'token=" + expiredToken + "; path=/; Secure; SameSite=Strict';"
        );
        log.info("Expired token successfully set in local storage");
    }

    @An("user refresh the page")
    public void user_refresh_the_page() {
        url= Grid.driver().getCurrentUrl();
        Grid.driver().navigate().refresh();
        log.info("Page refreshed after setting expired token");
    }

    @Then("verify the user is navigate to Guest home page")
    public void verify_the_user_is_navigate_to_guest_home_page() {

        softAssert.assertTrue(waitAndGetText(MILD_STEEL_TITLE).toLowerCase().contains("mild steel"), "User is not logged out or did not navigate to login page for token expiry from "+pageName);
        softAssert.assertTrue(waitAndCheckIsElementVisible(HOME_PAGE_REGISTER_CTA),"User is not logged out or did not navigate to login page for token expiry from "+pageName);
        log.info("User navigate to Guest user home Page after token expiry  and product are visible");

        WebElement loginPageElement = Grid.driver().findElement(By.xpath("//button[@id='login-cta']"));
        if (loginPageElement.isDisplayed()) {
            log.info("Login page is displayed, user is logged out");
            loginPageElement.click();
            sleep(1000);
            String currentUrl = Grid.driver().getCurrentUrl();
            if (currentUrl.contains("/login")) {
                log.info("User successfully navigated to login page after token expiry");
            } else {
                throw new AssertionError("User did not navigate to login page after token expiry, current URL: " + currentUrl +" from "+pageName);
            }
        }
    }

    @Then("verify the user is logged out and navigate to login page")
    public void verify_the_user_is_logged_out_and_navigate_to_login_page() {
       softAssert.assertTrue(waitAndCheckIsElementVisible(CONTINUE_WITH_EMAIL),"User is not logged out or did not navigate to login page for token expiry from "+pageName);
       log.info("User navigate to login Page after token expiry and continue with email button is visible");
    }

    @When("user open the cart page")
    public void user_open_the_cart_page() {
        pageName="Cart Page";
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
        pageName="Search Page";
        waitAndCheckIsElementClickable(SEARCH_BOX);
        waitAndClickOn(SEARCH_BOX);
        waitAndCheckIsElementVisible(SEARCH_INPUT);
        waitAndSetText(SEARCH_INPUT, product);
        Actions actions = new Actions(Grid.driver());
        actions.sendKeys(Keys.ENTER).perform();
        log.info("User searched for "+product+" successfully");

    }

    @Then("verify the user is able to login successfully")
    public void verify_the_user_is_able_to_login_successfully() {

        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
        boolean result = false;
        try {
            result = wait.until(ExpectedConditions.urlToBe(url));
        } catch (Exception e) {
            result = false;
        }
        softAssert.assertTrue(result,"User is not navigated to same page after login from "+pageName);
        log.info("User is navigated to same page after login from "+pageName);
    }

    @And("user again login with {string} and {string}")
    public void user_again_login(String emailId, String pass) {
        waitAndCheckIsElementVisible(LOGIN_VIA_OTP);
        waitAndClickOn(LOGIN_VIA_OTP); //Click on Login with OTP
        waitAndCheckIsElementVisible(LOGIN_EMAIL);
        waitAndSetText(LOGIN_EMAIL, emailId); // Give you Email
        waitAndCheckIsElementVisible(CCP_LOGIN_CTA);
        waitAndClickOn(CCP_LOGIN_CTA);
        waitAndCheckIsElementVisible(LOGIN_PASSWORD);
        waitAndSetText(LOGIN_PASSWORD, pass);// Give the password
        waitAndCheckIsElementVisible(CCP_LOGIN_CTA);
        waitAndClickOn(CCP_LOGIN_CTA);// Login to continue
    }

    @When("user open the ledger page")
    public void user_open_the_ledger_page() {
        pageName="Ledger Page";
        String locator = getLocator(LEDGER_PAGE);
        scrollingToViewAndClickUsingJS(Grid.driver().findElement(By.xpath(locator)));
        waitAndCheckIsElementClickable(LEDGER_PAGE);
        waitAndClickOn(LEDGER_PAGE);
        log.info("User navigated to ledger page successfully to test token expiry in ledger page");
    }

    @When("user open the Track Material page")
    public void user_open_the_track_material_page() {
        pageName = "Track Material Page";
        String locator = getLocator(TRACK_MATERIAL_PAGE);
        scrollingToViewAndClickUsingJS(Grid.driver().findElement(By.xpath(locator)));
        waitAndCheckIsElementClickable(TRACK_MATERIAL_PAGE);
        waitAndClickOn(TRACK_MATERIAL_PAGE);
        log.info("User navigated to Track Material page successfully to test token expiry in Track Material page");
    }
}
