package com.qapitol.jsw.stepdefinition;

import com.qapitol.sauron.common.configuration.Config;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static com.qapitol.jsw.utils.Hooks.softAssert;

public class TokenSteps extends AbstractWebPage {

    private final String MILD_STEEL_TITLE = "mildSteelTitle";
    private final String CART_PAGE="cartPage";
    private final String MY_REQUIREMENT_PAGE="myRequirementPage";
    private final String MY_ORDER_PAGE="myOrderPage";
    private final String ORDER_SUMMARY_PAGE="orderSummaryPage";

    @When("user set expired token in local storage")
    public void user_set_expired_token_in_local_storage() {
        String expiredToken = Config.getConfigProperty("ccpExpiredToken");
        JavascriptExecutor js = (JavascriptExecutor) Grid.driver();
        // Inject expired/invalid token
        js.executeScript("window.localStorage.setItem('token', arguments[0]);", expiredToken);
        log.info("Expired token successfully set in local storage");
    }

    @An("user refresh the page")
    public void user_refresh_the_page() {
        Grid.driver().navigate().refresh();
        log.info("Page refreshed after setting expired token");
    }

    @Then("verify the user is logged out and navigate to login page")
    public void verify_the_user_is_logged_out_and_navigate_to_login_page() {

        softAssert.assertTrue(waitAndGetText(MILD_STEEL_TITLE).contains("Mild Steel"), "User is not logged out or did not navigate to login page for token expiry.");
        log.info("User navigate to Guest user home Page after token expiry  and product are visible");

        WebElement loginPageElement = Grid.driver().findElement(By.xpath("//button[@id='login-cta']"));
        if (loginPageElement.isDisplayed()) {
            log.info("Login page is displayed, user is logged out");
            loginPageElement.click();
            String currentUrl = Grid.driver().getCurrentUrl();
            if (currentUrl.contains("/login")) {
                log.info("User successfully navigated to login page after token expiry");
            } else {
                throw new AssertionError("User is not logged out or did not navigate to login page.");
            }
            //I need to verify Mobile and Email is Logij Page after token expiry
        }
    }

    @When("user open the cart page")
    public void user_open_the_cart_page() {
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
}
