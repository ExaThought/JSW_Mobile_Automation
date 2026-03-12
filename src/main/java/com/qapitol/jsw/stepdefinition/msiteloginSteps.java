package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.LocatorReader;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static com.qapitol.jsw.utils.Hooks.softAssert;


public class msiteloginSteps extends AbstractWebPage {

    private final LoginSteps ls = new LoginSteps();

    private static final String LOGIN_BUTTON = "loginButton";
    private static final String LOGIN_CTA = "loginIcon";
    private static final String LOGIN_VIA_OTP = "loginViaOtp";
    private static final String PROCEED_TO_LOGIN = "loginToContinue";
    private static final String EMAIL_TAB = "emailInput";
    private static final String LOGIN_PASSWORD = "ccpLoginPassword";
    private static final String CONTINUE_BUTTON = "continueButton";
    private static final String EMAIL_INPUT = "emailInput";
    private static final String PROFILE_ICON = "profileIcon";
    private static final String SUCCESSLOGIN = "continueToHome";
    private static final String CCP_LOGIN_ERROR_MESSAGE="errorMessage";
    private static final String CCP_INVALID_EMAIL_ERROR_MESSAGE = "invalidEmailErrorMessage";

    LocatorReader locatorReader = new LocatorReader("locators/msiteloginsteps.yaml");

    @When("user clicks on login button")
    public void click_login() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(20));
        waitAndCheckIsElementClickable(10,LOGIN_CTA);
        waitAndCheckIsElementVisible(LOGIN_CTA);
        waitAndClickOn(LOGIN_CTA);
        waitAndCheckIsElementClickable(10,LOGIN_BUTTON);
        waitAndCheckIsElementVisible(LOGIN_BUTTON);
        waitAndClickOn(LOGIN_BUTTON);
    }

    @And("user clicks on login floating button on mobile")
    public void click_login_floating_button_on_mobile() {
        waitAndCheckIsElementClickable(10,LOGIN_BUTTON);
        waitAndCheckIsElementVisible(LOGIN_BUTTON);
        waitAndClickOn(LOGIN_BUTTON);
    }

    @When("user enters email {string}")
    public void enter_email(String email) {
        waitAndCheckIsElementVisible(LOGIN_VIA_OTP);
        waitAndClickOn(LOGIN_VIA_OTP);
        waitAndCheckIsElementVisible(EMAIL_INPUT);
        clickUsingActions(locatorReader.getXpath("elements.emailInput.en_US.web"));

        // waitAndCheckIsElementVisible(EMAIL_INPUT);
        waitAndSetText(EMAIL_INPUT, email);

    }

    public void clickUsingActions(String xpath) {
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))
        );

        new Actions(Grid.driver())
                .moveToElement(element)
                .click()
                .perform();
    }


    @When("user submits login")
    public void submit_login() {
        waitAndCheckIsElementVisible(SUCCESSLOGIN);
        waitAndClickOn(SUCCESSLOGIN);
    }

    @When("user clicks on proceed to login")
    public void click_proceed_to_login() throws InterruptedException {
        Thread.sleep(5000);
        waitAndCheckIsElementVisible(SUCCESSLOGIN);
        waitAndClickOn(SUCCESSLOGIN);
    }

    @And("user enters password {string}")
    public void enter_password(String password) {
        if(password == null || password.isEmpty()){
            return; // skip this step
        }
        waitAndCheckIsElementVisible(LOGIN_PASSWORD);
        waitAndSetText(LOGIN_PASSWORD,password);
    }


    @Then("login error message should be displayed")
    public void login_error_message_should_be_displayed() {
        waitAndCheckIsElementVisible(CCP_LOGIN_ERROR_MESSAGE);
        String msg = waitAndGetText(CCP_LOGIN_ERROR_MESSAGE);

        softAssert.assertEquals(
                msg,
                "Credentials are not valid. Please try again"
        );

    }


    @Then("invalid email error message should be displayed")
    public void invalid_email_error_message_should_be_displayed() {
        waitAndCheckIsElementVisible(CCP_INVALID_EMAIL_ERROR_MESSAGE);
        String msg = waitAndGetText(CCP_INVALID_EMAIL_ERROR_MESSAGE);

        softAssert.assertEquals(
                msg,
                "You have entered a wrong email address"
        );

    }

    // ---------- CONTINUE BUTTON STATE ----------

    @Then("continue button should be disabled on msite")
    public void continue_button_should_be_disabled() {

        // Get Continue button xpath directly from YAML
        String continueBtnXpath =
                locatorReader.getXpath("elements.continueButton.en_US.web");

        // Wait until Continue button is visible
        waitAndCheckIsElementVisible(CONTINUE_BUTTON);

        WebElement continueButton =
                Grid.driver().findElement(By.xpath(continueBtnXpath));

        boolean isDisabled =
                !continueButton.isEnabled() ||
                        "true".equalsIgnoreCase(continueButton.getAttribute("disabled")) ||
                        "true".equalsIgnoreCase(continueButton.getAttribute("aria-disabled")) ||
                        (continueButton.getAttribute("class") != null &&
                                continueButton.getAttribute("class").toLowerCase().contains("disabled"));

        softAssert.assertTrue(
                isDisabled,
                "Continue button is ENABLED but expected to be DISABLED"
        );
    }

    @Then("user should be logged in successfully via email")
    public void verify_login_success() {
        waitAndCheckIsElementVisible(PROFILE_ICON);
        softAssert.assertTrue(
                Grid.driver().getCurrentUrl().contains("/profile"),
                "User not navigated to profile page after email login"
        );

    }
}