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


public class LoginSteps extends AbstractWebPage {

    public static final String LOGIN_CTA_BUTTON = "loginButton";
    public static final String LOGIN_VIA_OTP = "loginViaOtp";
    public static final String LOGIN_EMAIL = "ccpLoginEmail";
    public static final String LOGIN_PASSWORD = "ccpLoginPassword";
    public static final String CCP_LOGIN_CTA = "ccpLoginCTA";
    public static final String CCP_LOGIN_ERROR_MESSAGE="errorMessage";
    public static final String CCP_INVALID_EMAIL_ERROR_MESSAGE = "invalidEmailErrorMessage";



    @When("user open the CCP portal")
    public void user_open_the_ccp_portal(){
        Grid.driver().get(ConfigUtil.getSecretValue("ccpPortalUrl"));
        Grid.driver().manage().window().maximize();
        waitAndCheckIsElementVisible(LOGIN_CTA_BUTTON);
        waitAndClickOn(LOGIN_CTA_BUTTON); // click on login CTA button
        waitAndCheckIsElementVisible(LOGIN_VIA_OTP);
        waitAndClickOn(LOGIN_VIA_OTP); //Click on Login with OTP
    }
    @And("enter valid email {string}")
    public void enter_valid_email(String username){
        waitAndCheckIsElementVisible(LOGIN_EMAIL);
        waitAndSetText(LOGIN_EMAIL, username); // Give you Email

    }
    @And("enter valid password {string}")
    public void enter_valid_password( String password){
        waitAndCheckIsElementVisible(LOGIN_PASSWORD);
        waitAndSetText(LOGIN_PASSWORD, password);// Give the password
    }
    @Then("user should able to login")
    public void user_should_able_to_login(){
        waitAndCheckIsElementVisible(CCP_LOGIN_CTA);
        waitAndClickOn(CCP_LOGIN_CTA);// Login
    }
    @And("enter invalid email {string}")
    public void enter_invalid_email(String username){
        waitAndCheckIsElementVisible(LOGIN_EMAIL);
        waitAndSetText(LOGIN_EMAIL, username); // Give you Email

    }
    @And("enter invalid password {string}")
    public void enter_invalid_password( String password){
        waitAndCheckIsElementVisible(LOGIN_PASSWORD);
        waitAndSetText(LOGIN_PASSWORD, password);// Give the password
    }
    @Then("user should not able to login with error message")
    public void user_should_not_able_to_login_with_error_message(){
        waitAndCheckIsElementVisible(CCP_LOGIN_CTA);
        waitAndClickOn(CCP_LOGIN_CTA);// Login
        waitAndCheckIsElementVisible(CCP_LOGIN_ERROR_MESSAGE);
        String FrontEndErrorMessage=waitAndGetText(CCP_LOGIN_ERROR_MESSAGE);
        softAssert.assertEquals(FrontEndErrorMessage,"Credentials are not valid. Please try again");

    }

    @Then("user should not able to  proceed to login with error message")
    public void user_should_not_able_to_login_with_error_message_due_to_invalid_email(){
        waitAndCheckIsElementVisible(CCP_LOGIN_CTA);
        waitAndCheckIsElementVisible(CCP_INVALID_EMAIL_ERROR_MESSAGE);
        String FrontEndErrorMessage=waitAndGetText(CCP_INVALID_EMAIL_ERROR_MESSAGE);
        softAssert.assertEquals(FrontEndErrorMessage,"You have entered a wrong email address");

    }

    @And("user click on continue button")
    public void userClickOnContinueButton() {
        waitAndCheckIsElementVisible(CCP_LOGIN_CTA);
        waitAndClickOn(CCP_LOGIN_CTA);// Login
    }

    @Then("continue button should be disabled")
    public void continue_button_should_be_disabled() {

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



}
