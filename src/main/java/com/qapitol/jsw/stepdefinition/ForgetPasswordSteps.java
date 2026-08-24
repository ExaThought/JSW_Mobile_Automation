package com.qapitol.jsw.stepdefinition;

import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.qapitol.jsw.utils.CustomUtil.jsClick;
import static com.qapitol.jsw.utils.Hooks.softAssert;

public class ForgetPasswordSteps  extends AbstractWebPage {

    public static final String LOGIN_VIA_OTP = "loginViaOtp";
    public static final String LOGIN_EMAIL = "ccpLoginEmail";
    public static final String CCP_LOGIN_CTA = "ccpLoginCTA";
    public static final String CCP_LOGIN_ERROR_MESSAGE = "errorMessage";
    public static final String CCP_INVALID_EMAIL_ERROR_MESSAGE = "invalidEmailErrorMessage";
    public static final String FORGOT_PASSWORD_LINK = "forgotPasswordLink";
    public static final String FORGOT_PASSWORD_SUCCESS_MSG = "forgetPasswordSuccessMessage";
    public static final String SUBMIT_CTA = "submitCTA";
    public static final String CONTINUE_CTA = "continueCTA";


    @And("enter invalid email id {string}")
    public void enterInvalidEmailId(String email) {
        try {
            log.info("Entering invalid email address.");
            waitAndCheckIsElementVisible(LOGIN_EMAIL);
            waitAndSetText(LOGIN_EMAIL, email);
            log.info("Successfully entered invalid email address.");
        } catch (Exception e) {
            log.info("Exception occurred while entering invalid email address: " + e.getMessage());
            softAssert.fail("Failed to enter invalid email address: " + e.getMessage());
        }
    }

    @And("user click on the continue button")
    public void userClickOnTheContinueButton() {
        try {
            log.info("Clicking on Continue/Login button.");
            waitAndCheckIsElementVisible(CONTINUE_CTA);
            waitAndClickOn(CONTINUE_CTA);
            log.info("Successfully clicked on Continue/Login button.");
        } catch (Exception e) {
            log.info("Exception occurred while clicking Continue/Login button: " + e.getMessage());
            softAssert.fail("Failed to click Continue/Login button: " + e.getMessage());
        }
    }

    @Then("user should not able to proceed to login with proper error message")
    public void userShouldNotAbleToProceedToLoginWithProperErrorMessage() {
        try {
            log.info("Verifying invalid email error message during login.");
            waitAndCheckIsElementVisible(CONTINUE_CTA);
            waitAndClickOn(CONTINUE_CTA);
            waitAndCheckIsElementVisible(CCP_INVALID_EMAIL_ERROR_MESSAGE);
            String actualErrorMessage = waitAndGetText(CCP_INVALID_EMAIL_ERROR_MESSAGE);
            String expectedErrorMessage = "You have entered a wrong email address";
            log.info("Actual invalid email error message: " + actualErrorMessage);
            softAssert.assertEquals(
                    actualErrorMessage,
                    expectedErrorMessage,
                    "Invalid email error message mismatch. Expected: "
                            + expectedErrorMessage + " but found: " + actualErrorMessage
            );
            log.info("Invalid email error message verified successfully.");
        } catch (Exception e) {
            log.info("Exception occurred while verifying invalid email error message: " + e.getMessage());
            softAssert.fail("Failed to verify invalid email error message: " + e.getMessage());
        }
    }

    @Then("continue button should be disabled for user")
    public void continueButtonShouldBeDisabledForUser() {
        // Fetch raw XPath from YAML (returns String)
        String locatorValue = getLocator(CONTINUE_CTA);
        // Convert to Selenium By
        By continueBtnLocator = By.xpath(locatorValue);
        // Wait until visible using your framework
        waitAndCheckIsElementVisible(CONTINUE_CTA);
        jsClick(continueBtnLocator);
        WebElement continueBtn = Grid.driver().findElement(continueBtnLocator);

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
    public void userEnterValidEmail(String email) {
        try {
            log.info("Entering valid email address.");
            waitAndCheckIsElementVisible(LOGIN_EMAIL);
            waitAndSetText(LOGIN_EMAIL, email);
            log.info("Successfully entered the email address.");
        } catch (Exception e) {
            log.info("Exception occurred while entering the email address: " + e.getMessage());
            softAssert.fail("Failed to enter the email address: " + e.getMessage());
        }
    }
    /**
     * Enters the email for a named account instead of a literal address.
     *
     * <p>Lets the two forgot-password paths - a registered address and an unregistered one -
     * share a Scenario Outline whose Examples table holds account ALIASES, not addresses.
     */
    @And("enter the email for the {string} account on forget password")
    public void enterTheEmailForTheAccount(String alias) {
        log.info("Entering the configured email for account '" + alias + "'");
        userEnterValidEmail(com.qapitol.jsw.utils.UserConfig.getAccountEmail(alias));
    }

    @And("user click on forget password link")
    public void userClickOnForgetPasswordLink() {
        try {
            log.info("Clicking on Forgot Password link.");
            waitAndCheckIsElementVisible(FORGOT_PASSWORD_LINK);
            waitAndClickOn(FORGOT_PASSWORD_LINK);
            log.info("Successfully clicked on Forgot Password link.");
        } catch (Exception e) {
            log.info("Exception occurred while clicking Forgot Password link: " + e.getMessage());
            softAssert.fail("Failed to click Forgot Password link: " + e.getMessage());
        }
    }

    @And("user click on submit button")
    public void userClickOnSubmitButton() {
        try {
            log.info("Clicking on Submit button.");
            waitAndCheckIsElementVisible(SUBMIT_CTA);
            waitAndClickOn(SUBMIT_CTA);
            log.info("Successfully clicked on Submit button.");
        } catch (Exception e) {
            log.info("Exception occurred while clicking Submit button: " + e.getMessage());
            softAssert.fail("Failed to click Submit button: " + e.getMessage());
        }
    }

    @Then("user should see forget password link sent message")
    public void userShouldSeeForgetPasswordLinkSentMessage() {
        try {
            log.info("Verifying Forgot Password success message.");
            waitAndCheckIsElementVisible(FORGOT_PASSWORD_SUCCESS_MSG);
            String actualMessage = waitAndGetText(FORGOT_PASSWORD_SUCCESS_MSG);
            String expectedMessage = "Password reset link has been sent to your registered email address";
            log.info("Actual success message: " + actualMessage);
            softAssert.assertEquals(
                    actualMessage,
                    expectedMessage,
                    "Forgot Password success message mismatch. Expected: " + expectedMessage
                            + " but found: " + actualMessage
            );
            log.info("Forgot Password success message verified successfully.");
        } catch (Exception e) {
            log.info("Exception occurred while verifying Forgot Password success message: " + e.getMessage());
            softAssert.fail("Failed to verify Forgot Password success message: " + e.getMessage());
        }
    }
}