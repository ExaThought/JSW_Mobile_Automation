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

public class CcpRegistrationSteps extends AbstractWebPage {

    // Updated registration locator names
    public static final String REGISTRATION_CTA_BUTTON = "registrationButton";
    public static final String REGISTRATION_VIA_OTP = "registrationViaOtp";
    public static final String CCP_REGISTRATION_EMAIL = "ccpRegistrationEmail";
    public static final String CCP_REGISTRATION_PASSWORD = "ccpRegistrationPassword";
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


    @When("open the CCP registration portal")
    public void user_open_the_ccp_portal() {
        Grid.driver().get(ConfigUtil.resolve("ccpPortalUrl"));
        Grid.driver().manage().window().maximize();

        waitAndCheckIsElementVisible(REGISTRATION_CTA_BUTTON);
        waitAndClickOn(REGISTRATION_CTA_BUTTON); // click registration CTA

        waitAndCheckIsElementVisible(REGISTRATION_VIA_OTP);
        waitAndClickOn(REGISTRATION_VIA_OTP); // click Registration via OTP
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
    public void enterNewValidEmailId(String arg0) {
        waitAndCheckIsElementVisible(CCP_REGISTRATION_EMAIL);
        waitAndSetText(CCP_REGISTRATION_EMAIL, arg0);
    }

    @Then("new user continue to Registration")
    public void newUserContinueToRegistration() {
        waitAndCheckIsElementVisible(CCP_REGISTRATION_CTA);
        waitAndClickOn(CCP_REGISTRATION_CTA); // click on continue button
    }

    @And("enter full name {string} mobile number {string} and gst {string}")
    public void enterFullNameMobileNumberAndGst(String fullName, String mobile, String gstin) {
        waitAndCheckIsElementVisible(CCP_FULL_NAME);
        waitAndSetText(CCP_FULL_NAME, fullName);

        waitAndCheckIsElementVisible(CCP_MOBILE);
        waitAndSetText(CCP_MOBILE, mobile);

        waitAndCheckIsElementVisible(CCP_GSTIN);
        waitAndSetText(CCP_GSTIN, gstin);

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
        String locatorValue = getLocator(CCP_TERMS_CHECKBOX);
        WebDriver driver = Grid.driver();
        WebElement checkbox = driver.findElement(By.xpath(locatorValue));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    @Then("next button should be enabled")
    public void nextButtonShouldBeEnabled() {
        String locatorValue = getLocator(CCP_REGISTER_NEXT);
        WebDriver driver = Grid.driver();
        WebElement nextBtn = driver.findElement(By.xpath(locatorValue));

        boolean isEnabled = nextBtn.isEnabled()
                && !"true".equalsIgnoreCase(nextBtn.getAttribute("disabled"))
                && !"true".equalsIgnoreCase(nextBtn.getAttribute("aria-disabled"))
                && (nextBtn.getAttribute("class") == null || !nextBtn.getAttribute("class").toLowerCase().contains("disabled"));

        softAssert.assertTrue(isEnabled,
                "Next button is DISABLED but EXPECTED to be ENABLED!");
    }


    // Step for invalid phone number error validation
    @Then("user should not able to proceed with phone number error")
    public void userShouldNotAbleToProceedWithPhoneError() {
        waitAndCheckIsElementVisible(CCP_REGISTER_NEXT);
        waitAndCheckIsElementVisible(CCP_INVALID_PHONE_ERROR);

        String msg = waitAndGetText(CCP_INVALID_PHONE_ERROR);
        softAssert.assertEquals(msg, "Please enter correct phone number");
    }

    // Step for invalid GSTIN error validation
    @Then("user should not able to proceed with GSTIN error")
    public void userShouldNotAbleToProceedWithGSTINError() {
        waitAndCheckIsElementVisible(CCP_REGISTER_NEXT);
        waitAndCheckIsElementVisible(CCP_INVALID_GSTIN_ERROR);

        String msg = waitAndGetText(CCP_INVALID_GSTIN_ERROR);
        softAssert.assertEquals(msg, "Please enter correct GSTIN for account registration on JSW ONE MSME");
    }

    @And("user unchecks the terms and conditions checkbox")
    public void userUnchecksTheTermsAndConditionsCheckbox() {
        WebDriver driver = Grid.driver();

        // Wait for checkbox to be present and clickable
        waitAndCheckIsElementVisible(CCP_TERMS_CHECKBOX);
        waitAndCheckIsElementClickable(CCP_TERMS_CHECKBOX);

        // Find the checkbox
        WebElement checkbox = driver.findElement(By.xpath(getLocator(CCP_TERMS_CHECKBOX)));

        // Uncheck only if it is selected
        if (checkbox.isSelected()) {
            checkbox.click();
        }
    }
}






