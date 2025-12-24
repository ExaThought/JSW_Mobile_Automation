package com.qapitol.jsw.stepdefinition;


import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.qapitol.jsw.utils.Hooks.softAssert;


public class ForgetPasswordSteps  extends AbstractWebPage {

    public static final String LOGIN_CTA_BUTTON = "loginButton";
    public static final String LOGIN_VIA_OTP = "loginViaOtp";
    public static final String LOGIN_EMAIL = "ccpLoginEmail";
    public static final String LOGIN_PASSWORD = "ccpLoginPassword";
    public static final String CCP_LOGIN_CTA = "ccpLoginCTA";
    public static final String CCP_LOGIN_ERROR_MESSAGE = "errorMessage";
    public static final String CCP_INVALID_EMAIL_ERROR_MESSAGE = "invalidEmailErrorMessage";
    public static final String FORGOT_PASSWORD_LINK = "forgotPasswordLink";
    public static final String FORGOT_PASSWORD_SUCCESS_MSG = "forgetPasswordSuccessMessage";


    @When("user open the CCP portal website")
    public void user_open_the_ccp_portal_web() {
        Grid.driver().get(ConfigUtil.getSecretValue("ccpPortalUrl"));
        Grid.driver().manage().window().maximize();
        waitAndCheckIsElementVisible(LOGIN_CTA_BUTTON);
        waitAndClickOn(LOGIN_CTA_BUTTON); // click on login CTA button
        waitAndCheckIsElementVisible(LOGIN_VIA_OTP);
        waitAndClickOn(LOGIN_VIA_OTP); //Click on Login with OTP
    }

    @And("enter invalid email id {string}")
    public void enterInvalidEmailId(String arg0) {
            waitAndCheckIsElementVisible(LOGIN_EMAIL);
            waitAndSetText(LOGIN_EMAIL, arg0); // Give you Email
    }

    @And("user click on the continue button")
    public void userClickOnTheContinueButton() {
        waitAndCheckIsElementVisible(CCP_LOGIN_CTA);
        waitAndClickOn(CCP_LOGIN_CTA);// Login
    }



    @Then("user should not able to  proceed to login with  proper error message")
    public void userShouldNotAbleToProceedToLoginWithProperErrorMessage() {
        waitAndCheckIsElementVisible(CCP_LOGIN_CTA);
        waitAndCheckIsElementVisible(CCP_INVALID_EMAIL_ERROR_MESSAGE);
        String FrontEndErrorMessage=waitAndGetText(CCP_INVALID_EMAIL_ERROR_MESSAGE);
        softAssert.assertEquals(FrontEndErrorMessage,"You have entered a wrong email address");

    }

    @Then("continue button should be disabled for user")
    public void continueButtonShouldBeDisabledForUser() {
        // Fetch raw XPath from YAML (returns String)
        String locatorValue = getLocator(CCP_LOGIN_CTA);

        // Convert to Selenium By
        By continueBtnLocator = By.xpath(locatorValue);

        // Wait until visible using your framework
        waitAndCheckIsElementVisible(CCP_LOGIN_CTA);


        WebDriver driver = Grid.driver();
        WebElement continueBtn = driver.findElement(continueBtnLocator);

        // Check attributes for disabled state
        String disabledAttr = continueBtn.getAttribute("disabled");
        String ariaDisabled = continueBtn.getAttribute("aria-disabled");
        String classAttr = continueBtn.getAttribute("class");

        boolean attrDisabled = disabledAttr != null &&
                (disabledAttr.equalsIgnoreCase("true") || disabledAttr.equalsIgnoreCase("disabled"));

        boolean ariaAttr = ariaDisabled != null &&
                (ariaDisabled.equalsIgnoreCase("true") || ariaDisabled.equalsIgnoreCase("disabled"));

        boolean classDisabled = classAttr != null &&
                classAttr.toLowerCase().contains("disabled");

        boolean finalState = !continueBtn.isEnabled() || attrDisabled || ariaAttr || classDisabled;

        softAssert.assertTrue(
                finalState,
                "Continue button is ENABLED but EXPECTED to be DISABLED!"
        );
    }

    @And("User enter valid email {string}")
    public void userEnterValidEmail(String arg0) {
        waitAndCheckIsElementVisible(LOGIN_EMAIL);
        waitAndSetText(LOGIN_EMAIL, arg0); // Give you Email

    }
    @And("user click on forget password link")
    public void userClickOnForgetPasswordLink() {
        waitAndCheckIsElementVisible(FORGOT_PASSWORD_LINK);
        waitAndClickOn(FORGOT_PASSWORD_LINK);
    }

    @And("user click on submit button")
    public void userClickOnSubmitButton() {
        // Wait until Submit button is visible
        waitAndCheckIsElementVisible(LOGIN_VIA_OTP);

        // Click on Submit button
        waitAndClickOn(LOGIN_VIA_OTP);
    }

    @Then("user should see forget password link sent message")
    public void userShouldSeeForgetPasswordLinkSentMessage() {
        // Wait until success message is visible
        waitAndCheckIsElementVisible(FORGOT_PASSWORD_SUCCESS_MSG);

        // Get the text from the message element
        String actualMessage = waitAndGetText(FORGOT_PASSWORD_SUCCESS_MSG);

        // Validate the message
        softAssert.assertEquals(
                actualMessage,
                "Password reset link has been sent to your registered email address",
                "Forget password success message is not displayed correctly!"
        );
    }
}



