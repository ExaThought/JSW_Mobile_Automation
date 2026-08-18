package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.ApiUtils;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.Duration;

import static com.qapitol.jsw.utils.CustomUtil.*;
import static com.qapitol.jsw.utils.Hooks.softAssert;

public class CcpRegistrationSteps extends AbstractWebPage {

    // Updated registration locator names
    public static final String CCP_REGISTRATION_EMAIL = "ccpRegistrationEmail";
    // CCP_REGISTRATION_PASSWORD ("ccpRegistrationPassword") removed: never referenced, and no such
    // key exists in ccpregistrationsteps.yaml. Adding the key would have created a dead locator.
    public static final String CCP_REGISTRATION_CTA = "ccpRegistrationCTA";
    public static final String CCP_REGISTRATION_ERROR_MESSAGE = "registrationErrorMessage";
    public static final String CCP_INVALID_REGISTRATION_EMAIL_ERROR = "invalidRegistrationEmailErrorMessage";
    public static final String CCP_FULL_NAME = "fullName";
    public static final String CCP_MOBILE = "mobile";
    public static final String CCP_GSTIN = "gstin";
    public static final String CCP_REGISTER_NEXT = "registerNext";
    public static final String CCP_TERMS_CHECKBOX = "termsCheckbox";
    public static final String CCP_INVALID_PHONE_ERROR = "invalidPhoneNumberErrorMessage";
    public static final String CCP_INVALID_GSTIN_ERROR = "invalidGSTINErrorMessage";
    public static final String CCP_ReGISTER_CTA = "registrationButton";
    // CCP_ReGISTER_vIA_OTP ("registrationViaOtp") removed for the same reason: declaration only,
    // no matching key. If a registration-via-OTP step is written later, add the key then.
    private static final By SEARCH_OVERLAY = By.cssSelector(".search-overlay");
    private static final String CHECKBOX="checkBox";
    private static final String LOGIN_CTA_BUTTON = "//button[text()='Login']";
    private static final String CONTINUE_EMAIL_BTN = "//button[contains(text(), 'Continue with email')]";
    private static final String ACCOUNT_ALREADY_EXISTS_ERROR_MSG="//span[@id=\"aae-desktop-title\"]";
    private static final String GST_INFO_ICON = "//div[@class=\"gstin-success-icon\"]";


    @And("user open the CCP web portal")
    public void user_open_the_ccp_web_portal() {
        try {
            log.info("Opening the CCP web portal.");

            waits(By.xpath(LOGIN_CTA_BUTTON));

            softAssert.assertTrue(
                    isDisplayed(By.xpath(LOGIN_CTA_BUTTON)),
                    "Login CTA button is not displayed OR Login CTA text is not correct"
            );

            clickButton(By.xpath(LOGIN_CTA_BUTTON));
            log.info("Clicked on Login CTA button.");

            waits(By.xpath(CONTINUE_EMAIL_BTN));

            softAssert.assertTrue(
                    isDisplayed(By.xpath(CONTINUE_EMAIL_BTN)),
                    "Continue with Email button is not displayed."
            );

            clickButton(By.xpath(CONTINUE_EMAIL_BTN));
            log.info("Clicked on Continue with Email button.");

            log.info("Successfully navigated to the CCP web portal.");

        } catch (Exception e) {
            log.info("Exception occurred while opening the CCP web portal: " + e.getMessage());
            softAssert.fail("Failed to open the CCP web portal: " + e.getMessage());
        }
    }
    public void waitForSearchOverlayToDisappear() {
        try {
            new org.openqa.selenium.support.ui.WebDriverWait(Grid.driver(), Duration.ofSeconds(10))
                    .until(driver -> {
                        try {
                            return driver.findElements(SEARCH_OVERLAY).isEmpty()
                                    || !driver.findElement(SEARCH_OVERLAY).isDisplayed();
                        } catch (Exception e) {
                            return true; // overlay not present in DOM
                        }
                    });
        } catch (Exception ignored) {
        }
    }
    @And("enter new invalid email id {string}")

    public void enter_invalid_email(String username) {
        waitAndCheckIsElementVisible(CCP_REGISTRATION_EMAIL);
        waitAndSetText(CCP_REGISTRATION_EMAIL, username);
    }

    @Then("user should not able to proceed to Registration with error message")
    public void userShouldNotAbleToProceedToRegistrationWithErrorMessage() {
        waitAndCheckIsElementVisible(CCP_REGISTRATION_CTA);
        waitAndCheckIsElementVisible(CCP_INVALID_REGISTRATION_EMAIL_ERROR);

        String msg = waitAndGetText(CCP_INVALID_REGISTRATION_EMAIL_ERROR);
        softAssert.assertEquals(msg, "You have entered a wrong email address");
    }


    @Then("continue button should be disable")
    public void continueButtonShouldBeDisabled() {

        String locatorValue = getLocator(CCP_REGISTRATION_CTA);
        By continueBtnLocator = By.xpath(locatorValue);

        waitAndCheckIsElementVisible(CCP_REGISTRATION_CTA);

        WebDriver driver = Grid.driver();
        WebElement continueBtn = driver.findElement(continueBtnLocator);

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

        softAssert.assertTrue(finalState,
                "Continue button is ENABLED but EXPECTED to be DISABLED!");
    }

    @And("enter new valid email id {string}")
    public void enterNewValidEmailId(String email) {
        try {
            log.info("Entering new valid email ID: " + email);

            waitAndCheckIsElementVisible(CCP_REGISTRATION_EMAIL);

            waitAndSetText(CCP_REGISTRATION_EMAIL, email);

            log.info("Successfully entered the new valid email ID.");

        } catch (Exception e) {
            log.info("Exception occurred while entering the email ID: " + e.getMessage());
            softAssert.fail("Failed to enter the email ID: " + e.getMessage());
        }
    }

    @Then("new user continue to Registration")
    public void newUserContinueToRegistration() {
        try {
            log.info("Clicking on Continue button to proceed with registration.");

            waitAndCheckIsElementVisible(CCP_REGISTRATION_CTA);

            waitAndClickOn(CCP_REGISTRATION_CTA);

            log.info("Successfully clicked on Continue button.");

        } catch (Exception e) {
            log.info("Exception occurred while clicking Continue button: " + e.getMessage());
            softAssert.fail("Failed to click Continue button: " + e.getMessage());
        }
    }

    @And("enter full name {string} mobile number {string} and gst {string}")
    public void enterFullNameMobileNumberAndGst(String fullName, String mobile, String gstin) {
        try {
            log.info("Entering registration details.");

            waitAndCheckIsElementVisible(CCP_FULL_NAME);
            waitAndSetText(CCP_FULL_NAME, fullName);
            log.info("Entered full name: " + fullName);

            waitAndCheckIsElementVisible(CCP_GSTIN);



            if (gstin == null || gstin.isEmpty() || gstin.equals("INVALIDGSTIN")) {

                waitAndSetText(CCP_GSTIN, gstin);
                log.info("Entered invalid GSTIN for negative scenario: " + gstin);

            }  else if (ApiUtils.isGstinNotExists(gstin)) {

                waitAndSetText(CCP_GSTIN, gstin);
                log.info("Entered new GSTIN: " + gstin);

            } else {

                log.info("GSTIN already exists, not entering: " + gstin);
                softAssert.fail("Provided GSTIN already exists: " + gstin);
            }

            waitAndCheckIsElementVisible(CCP_MOBILE);
            waitAndSetText(CCP_MOBILE, mobile);
            log.info("Entered mobile number: " + mobile);

            log.info("Registration details entered successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while entering registration details: " + e.getMessage());
            softAssert.fail("Failed to enter registration details: " + e.getMessage());
        }
    }
    @And("enter full name {string} mobile number {string} and existing gst {string}")
    public void enterFullNameMobileNumberAndExistingGst(String fullName, String mobile, String gstin) {
        try {
            log.info("Entering registration details.");

            waitAndCheckIsElementVisible(CCP_FULL_NAME);
            waitAndSetText(CCP_FULL_NAME, fullName);
            log.info("Entered full name: " + fullName);

            waitAndCheckIsElementVisible(CCP_GSTIN);



            waitAndSetText(CCP_GSTIN, gstin);


            waitAndCheckIsElementVisible(CCP_MOBILE);
            waitAndSetText(CCP_MOBILE, mobile);
            log.info("Entered mobile number: " + mobile);

            log.info("Registration details entered successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while entering registration details: " + e.getMessage());
            softAssert.fail("Failed to enter registration details: " + e.getMessage());
        }
    }

    @And("remove checkbox")
    public void removeCheckbox() {
        waitAndCheckIsElementVisible(CHECKBOX);
        waitAndCheckIsElementClickable(CHECKBOX);
        waitAndClickOn(CHECKBOX);
        log.info("Successfully clicked on checkbox to uncheck it");
    }

    @Then("next button should be disabled")
    public void nextButtonShouldBeDisabled() {
        String locatorValue = getLocator(CCP_REGISTER_NEXT);
        By nextBtnLocator = By.xpath(locatorValue);

        waitAndCheckIsElementVisible(CCP_REGISTER_NEXT);

        WebDriver driver = Grid.driver();
        WebElement nextBtn = driver.findElement(nextBtnLocator);

        String disabledAttr = nextBtn.getAttribute("disabled");
        String ariaDisabled = nextBtn.getAttribute("aria-disabled");
        String classAttr = nextBtn.getAttribute("class");

        boolean attrDisabled = disabledAttr != null &&
                (disabledAttr.equalsIgnoreCase("true") || disabledAttr.equalsIgnoreCase("disabled"));

        boolean ariaAttr = ariaDisabled != null &&
                (ariaDisabled.equalsIgnoreCase("true") || ariaDisabled.equalsIgnoreCase("disabled"));

        boolean classDisabled = classAttr != null &&
                classAttr.toLowerCase().contains("disabled");

        boolean finalState = !nextBtn.isEnabled() || attrDisabled || ariaAttr || classDisabled;

        softAssert.assertTrue(finalState,
                "Next button is ENABLED but EXPECTED to be DISABLED!");
    }

    @When("user clicks on terms and conditions checkbox")
    public void userClicksOnTermsAndConditionsCheckbox() {
        try {
            log.info("Selecting the Terms and Conditions checkbox.");

            waitAndCheckIsElementVisible(CCP_TERMS_CHECKBOX);
            waitAndCheckIsElementClickable(CCP_TERMS_CHECKBOX);

            WebDriver driver = Grid.driver();
            WebElement checkbox = driver.findElement(By.xpath(getLocator(CCP_TERMS_CHECKBOX)));

            if (!checkbox.isSelected()) {
                checkbox.click();
                log.info("Terms and Conditions checkbox selected successfully.");
            } else {
                log.info("Terms and Conditions checkbox is already selected.");
            }

            softAssert.assertTrue(
                    checkbox.isSelected(),
                    "Terms and Conditions checkbox is not selected."
            );

            log.info("Verified Terms and Conditions checkbox is selected.");

        } catch (Exception e) {
            log.info("Exception occurred while selecting the Terms and Conditions checkbox: " + e.getMessage());
            softAssert.fail("Failed to select the Terms and Conditions checkbox: " + e.getMessage());
        }
    }

    @Then("next button should be enabled")
    public void nextButtonShouldBeEnabled() {
        try {
            log.info("Verifying Next button is enabled.");

            waitAndCheckIsElementVisible(CCP_REGISTER_NEXT);

            WebElement nextButton = Grid.driver().findElement(By.xpath(getLocator(CCP_REGISTER_NEXT)));

            boolean isEnabled = nextButton.isEnabled()
                    && !"true".equalsIgnoreCase(nextButton.getAttribute("disabled"))
                    && !"true".equalsIgnoreCase(nextButton.getAttribute("aria-disabled"))
                    && (nextButton.getAttribute("class") == null
                    || !nextButton.getAttribute("class").toLowerCase().contains("disabled"));

            log.info("Next button enabled status: " + isEnabled);

            softAssert.assertTrue(
                    isEnabled,
                    "Next button is disabled but was expected to be enabled."
            );

            log.info("Verified Next button is enabled successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying Next button state: " + e.getMessage());
            softAssert.fail("Failed to verify Next button is enabled: " + e.getMessage());
        }
    }


    // Step for invalid phone number error validation
    @Then("user should not able to proceed with phone number error")
    public void userShouldNotAbleToProceedWithPhoneError() {
        try {
            log.info("Verifying invalid phone number error message.");

            waitAndCheckIsElementVisible(CCP_REGISTER_NEXT);
            waitAndCheckIsElementVisible(CCP_INVALID_PHONE_ERROR);

            String actualMessage = waitAndGetText(CCP_INVALID_PHONE_ERROR);
            String expectedMessage = "Please enter correct phone number";

            log.info("Actual phone number error message: " + actualMessage);

            softAssert.assertEquals(
                    actualMessage,
                    expectedMessage,
                    "Phone number error message mismatch. Expected: " + expectedMessage + " but found: " + actualMessage
            );

            log.info("Phone number error message verified successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying phone number error message: " + e.getMessage());
            softAssert.fail("Exception while verifying phone number error message: " + e.getMessage());
        }
    }

    // Step for invalid GSTIN error validation
    @Then("user should not able to proceed with GSTIN error")
    public void userShouldNotAbleToProceedWithGSTINError() {
        try {
            log.info("Verifying invalid GSTIN error message.");

            waitAndCheckIsElementVisible(CCP_INVALID_GSTIN_ERROR);

            String actualMessage = waitAndGetText(CCP_INVALID_GSTIN_ERROR);
            String expectedMessage = "Please enter correct GSTIN for account registration on JSW ONE MSME";

            log.info("Actual GSTIN error message: " + actualMessage);

            softAssert.assertEquals(
                    actualMessage,
                    expectedMessage,
                    "GSTIN error message mismatch. Expected: " + expectedMessage + " but found: " + actualMessage
            );

            log.info("GSTIN error message verified successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying GSTIN error message: " + e.getMessage());
            softAssert.fail("Exception while verifying GSTIN error message: " + e.getMessage());
        }
    }


    @And("user unchecks the terms and conditions checkbox")
    public void userUnchecksTheTermsAndConditionsCheckbox() {
        try {
            log.info("Unchecking the Terms and Conditions checkbox.");

            WebDriver driver = Grid.driver();

            waitAndCheckIsElementVisible(CCP_TERMS_CHECKBOX);
            waitAndCheckIsElementClickable(CCP_TERMS_CHECKBOX);

            WebElement checkbox = driver.findElement(By.xpath(getLocator(CCP_TERMS_CHECKBOX)));

            if (checkbox.isSelected()) {
                checkbox.click();
                log.info("Terms and Conditions checkbox was selected and has been unchecked.");
            } else {
                log.info("Terms and Conditions checkbox is already unchecked.");
            }

            softAssert.assertFalse(
                    checkbox.isSelected(),
                    "Terms and Conditions checkbox is still selected."
            );

            log.info("Verified Terms and Conditions checkbox is unchecked.");

        } catch (Exception e) {
            log.info("Exception occurred while unchecking the Terms and Conditions checkbox: " + e.getMessage());
            softAssert.fail("Failed to uncheck the Terms and Conditions checkbox: " + e.getMessage());
        }
    }

    @Then("User click on Next Button")
    public void userClickOnNextButton() {
        waits(By.xpath(GST_INFO_ICON));
        if(isDisplayed(By.xpath(GST_INFO_ICON))) {
            waitAndClickOn(CCP_REGISTER_NEXT);
        }else {
            log.info("error in gst");
        }
        log.info("Click on next button");
    }

    @Then("User verifies the error message")
    public void userVerifiesTheErrorMessage() {
        try {
            log.info("Verifying 'Account already exists' error message.");

            waits(By.xpath(ACCOUNT_ALREADY_EXISTS_ERROR_MSG));

            String actualErrorMessage = getText(By.xpath(ACCOUNT_ALREADY_EXISTS_ERROR_MSG));
            log.info("Actual error message: " + actualErrorMessage);

            softAssert.assertTrue(
                    actualErrorMessage.contains("Account already exists"),
                    "Expected error message 'Account already exists' was not displayed. Actual message: "
                            + actualErrorMessage
            );

            log.info("Verified 'Account already exists' error message successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying the error message: " + e.getMessage());
            softAssert.fail("Exception while verifying the error message: " + e.getMessage());
        }
    }
}






