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




public class msiteloginSteps extends AbstractWebPage {

    private final LoginSteps ls = new LoginSteps();

    public static final String LOGIN_BUTTON = "loginButton";
    private static final String LOGIN_CTA = "loginIcon";
    public static final String LOGIN_VIA_OTP = "loginViaOtp";
    public static final String PROCEED_TO_LOGIN = "loginToContinue";
    public static final String EMAIL_TAB = "emailInput";
    public static final String LOGIN_PASSWORD = "ccpLoginPassword";
    public static final String CONTINUE_BUTTON = "continueButton";
    public static final String EMAIL_INPUT = "emailInput";
    public static final String LOGIN_TO_CONTINUE = "loginToContinue";
    public static final String PROFILE_ICON = "profileIcon";
    public static final String CCP_LOGIN_ERROR_MESSAGE="errorMessage";
    public static final String CCP_INVALID_EMAIL_ERROR_MESSAGE = "invalidEmailErrorMessage";
   SoftAssert softAssert = new SoftAssert();

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

    @When("user enters email {string}")
    public void enter_email(String email) {
        waitAndCheckIsElementVisible(LOGIN_VIA_OTP);
        waitAndClickOn(LOGIN_VIA_OTP);
        waitAndCheckIsElementVisible(EMAIL_INPUT);
        clickUsingActions(locatorReader.getXpath("elements.emailInput.en_US.web"));

       // waitAndCheckIsElementVisible(EMAIL_INPUT);
        waitAndSetText(EMAIL_INPUT, email);
        System.out.println("entered");
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
       waitAndCheckIsElementVisible(CONTINUE_BUTTON);
        waitAndClickOn(CONTINUE_BUTTON);
    }

    @When("user clicks on proceed to login")
    public void click_proceed_to_login() {
        waitAndCheckIsElementVisible(LOGIN_TO_CONTINUE);
        waitAndClickOn(LOGIN_TO_CONTINUE);
    }

    @And("user enters password {string}")
    public void enter_password(String password) {
        if(password == null || password.isEmpty()){
            System.out.println("Skipping password entry as password is empty");
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
        softAssert.assertAll();
    }


    @Then("invalid email error message should be displayed")
    public void invalid_email_error_message_should_be_displayed() {
        waitAndCheckIsElementVisible(CCP_INVALID_EMAIL_ERROR_MESSAGE);
        String msg = waitAndGetText(CCP_INVALID_EMAIL_ERROR_MESSAGE);

        softAssert.assertEquals(
                msg,
                "You have entered a wrong email address"
        );
        softAssert.assertAll();
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

        softAssert.assertAll();
    }



    @Then("user should be logged in successfully via email")
    public void verify_login_success() {
        waitAndCheckIsElementVisible(PROFILE_ICON);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(
                Grid.driver().getCurrentUrl().contains("/profile"),
                "User not navigated to profile page after email login"
        );
        softAssert.assertAll();
    }
}
