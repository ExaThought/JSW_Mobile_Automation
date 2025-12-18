package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.sauron.common.configuration.Config;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.qapitol.jsw.utils.CustomUtills.softAssert;

public class LoginSteps extends AbstractWebPage {

    public static final String LOGIN_CTA_BUTTON = "loginButton";
    public static final String LOGIN_VIA_OTP = "loginViaOtp";
    public static final String LOGIN_EMAIL = "ccpLoginEmail";
    public static final String LOGIN_PASSWORD = "ccpLoginPassword";
    public static final String CCP_LOGIN_CTA = "ccpLoginCTA";
    public static final String CCP_LOGIN_ERROR_MESSAGE="errorMessage";
    SessionSteps sessionSteps=new SessionSteps();

    @When("user open the CCP portal")
    public void user_open_the_ccp_portal(){
        //Open the CCP
        Grid.driver().get(ConfigUtil.resolve("ccpPortalUrl"));
        Grid.driver().manage().window().maximize();
        sessionSteps.checkLoginPage();
        // click on login CTA button
        waitAndCheckIsElementClickable(3,LOGIN_CTA_BUTTON);
        waitAndClickOn(LOGIN_CTA_BUTTON);
        //Click on Login with OTP
        waitAndCheckIsElementVisible(LOGIN_VIA_OTP);
        waitAndClickOn(LOGIN_VIA_OTP);
    }
    @And("enter valid email {string}")
    public void enter_valid_email(String username){
        // Give you Email
        waitAndCheckIsElementVisible(LOGIN_EMAIL);
        waitAndSetText(LOGIN_EMAIL, username);

    }
    @And("enter valid password {string}")
    public void enter_valid_password( String password){
        // Give the password
        waitAndCheckIsElementVisible(LOGIN_PASSWORD);
        waitAndSetText(LOGIN_PASSWORD, password);
    }
    @Then("user should able to login")
    public void user_should_able_to_login(){
        // Login
        waitAndCheckIsElementVisible(CCP_LOGIN_CTA);
        waitAndClickOn(CCP_LOGIN_CTA);
        sleep(1000);
    }
    @And("enter invalid email {string}")
    public void enter_invalid_email(String username){
        // Give you Email
        waitAndCheckIsElementVisible(LOGIN_EMAIL);
        waitAndSetText(LOGIN_EMAIL, username);

    }
    @And("enter invalid password {string}")
    public void enter_invalid_password( String password){
        // Give the password
        waitAndCheckIsElementVisible(LOGIN_PASSWORD);
        waitAndSetText(LOGIN_PASSWORD, password);
    }
    @Then("user should not able to login with error message")
    public void user_should_not_able_to_login_with_error_message(){
        // Login
        waitAndCheckIsElementVisible(CCP_LOGIN_CTA);
        waitAndClickOn(CCP_LOGIN_CTA);
        waitAndCheckIsElementVisible(CCP_LOGIN_ERROR_MESSAGE);
        String FrontEndErrorMessage=waitAndGetText(CCP_LOGIN_ERROR_MESSAGE);
        softAssert.assertEquals(FrontEndErrorMessage,"Credentials are not valid. Please try again");
    }
}
