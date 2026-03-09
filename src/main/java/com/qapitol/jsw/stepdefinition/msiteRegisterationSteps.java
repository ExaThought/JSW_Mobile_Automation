package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.LocatorReader;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.qapitol.jsw.utils.Hooks.softAssert;

public class msiteRegisterationSteps  extends AbstractWebPage {


    LocatorReader locatorReader =
            new LocatorReader("locators/msiteregisterationsteps.yaml");

    // -------- Locator Keys --------
    private static final String REGISTRATION_FORM = "registrationForm";
    private static final String FULL_NAME_INPUT = "fullNameInput";
    private static final String MOBILE_INPUT = "mobileInput";
    private static final String GST_INPUT = "gstInput";
    private static final String TERMS_CHECKBOX = "termsCheckbox";
    private static final String NEXT_BUTTON = "nextButton";

    private static final String INVALID_EMAIL_ERROR = "invalidEmailError";
    private static final String PHONE_ERROR = "phoneError";
    private static final String GST_ERROR = "gstError";

    // -------- Registration Page --------

    @Then("new user continue to Registration on msite")
    public void new_user_continue_to_registration() {
        waitAndCheckIsElementVisible(REGISTRATION_FORM);
        softAssert.assertTrue(
                isElementPresent(REGISTRATION_FORM),
                "Registration form is not displayed"
        );
    }

    @And("enter full name {string} mobile number {string} and gst {string} on msite")
    public void enter_registration_details(String fullName, String mobile, String gst) {

        waitAndCheckIsElementVisible(REGISTRATION_FORM);

        if (fullName != null && !fullName.isEmpty()) {
            waitAndCheckIsElementVisible(FULL_NAME_INPUT);
            waitAndSetText(FULL_NAME_INPUT, fullName);
        }

        if (mobile != null && !mobile.isEmpty()) {
            waitAndCheckIsElementVisible(MOBILE_INPUT);
            waitAndSetText(MOBILE_INPUT, mobile);
        }

        if (gst != null && !gst.isEmpty()) {
            waitAndCheckIsElementVisible(GST_INPUT);
            waitAndSetText(GST_INPUT, gst);
        }
    }

    @And("enter details with full name {string} mobile number {string} and gst {string} on msite")
    public void registration_details(String fullName, String mobile, String gst) {

        waitAndCheckIsElementVisible(REGISTRATION_FORM);

        if (fullName != null && !fullName.isEmpty()) {
            waitAndCheckIsElementVisible(FULL_NAME_INPUT);
            waitAndSetText(FULL_NAME_INPUT, fullName);
        }

        if (mobile != null && !mobile.isEmpty()) {
            waitAndCheckIsElementVisible(MOBILE_INPUT);
            waitAndSetText(MOBILE_INPUT, mobile);
        }

        if (gst != null && !gst.isEmpty()) {
            waitAndCheckIsElementVisible(GST_INPUT);
            waitAndSetText(GST_INPUT, gst);
        }
    }


    @When("user clicks on terms and conditions checkbox on msite")
    public void user_clicks_terms_checkbox() {
        waitAndCheckIsElementVisible(TERMS_CHECKBOX);
        waitAndClickOn(TERMS_CHECKBOX);
    }

    // -------- Button State --------

    @Then("next button should be enabled on msite")
    public void next_button_should_be_enabled() {
        waitAndCheckIsElementVisible(NEXT_BUTTON);
        softAssert.assertTrue(
                isButtonEnabled(NEXT_BUTTON),
                "Next button is disabled but expected to be enabled"
        );
    }

    @Then("next button should be disabled on msite")
    public void next_button_should_be_disabled() {
        waitAndCheckIsElementVisible(NEXT_BUTTON);
        softAssert.assertFalse(
                isButtonEnabled(NEXT_BUTTON),
                "Next button is enabled but expected to be disabled"
        );
    }

    // -------- Validation Errors --------

    @Then("user should not be able to proceed to Registration with error message")
    public void user_should_not_proceed_with_registration_error() {
        String errorXpath =
                locatorReader.getXpath("elements.invalidEmailError.en_US.web");


        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));

        WebElement errorElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(errorXpath))
        );

        String msg = errorElement.getText().trim();

        softAssert.assertEquals(
                msg,
                "You have entered a wrong email address"
        );



    }

    @Then("user should not be able to proceed with {string} error")
    public void user_should_not_proceed_with_specific_error(String errorType) {

        if (errorType.equalsIgnoreCase("phone number")) {
            waitAndCheckIsElementVisible(PHONE_ERROR);
            softAssert.assertTrue(
                    isElementPresent(PHONE_ERROR),
                    "Phone number validation error not displayed"
            );

        } else if (errorType.equalsIgnoreCase("GSTIN")) {
            waitAndCheckIsElementVisible(GST_ERROR);
            softAssert.assertTrue(
                    isElementPresent(GST_ERROR),
                    "GSTIN validation error not displayed"
            );
        }
    }

    // -------- Helper Methods --------

    private boolean isButtonEnabled(String locatorKey) {
        String xpath = locatorReader.getXpath("elements." + locatorKey + ".en_US.web");
        WebElement button = Grid.driver().findElement(By.xpath(xpath));

        return button.isEnabled()
                && !"true".equalsIgnoreCase(button.getAttribute("disabled"))
                && !"true".equalsIgnoreCase(button.getAttribute("aria-disabled"));
    }

}
