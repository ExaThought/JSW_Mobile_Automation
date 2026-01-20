package com.qapitol.jsw.stepdefinition;

import com.qapitol.sauron.web.AbstractWebPage;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class msiteloginSteps extends AbstractWebPage {

    private final LoginSteps ls = new LoginSteps();

    public static final String LOGIN_BUTTON = "loginButton";
    public static final String LOGIN_VIA_OTP = "loginViaOtp";
    public static final String PROCEED_TO_LOGIN = "loginNowButton";
    public static final String LOGIN_EMAIL = "ccpLoginEmail";
    public static final String LOGIN_PASSWORD = "ccpLoginPassword";
    public static final String CCP_LOGIN_CTA = "ccpLoginCTA";
    public static final String ENTERED_EMAIL = "emailTextBox";

    @When("user waits for login button")
    public void user_waits_for_login() throws InterruptedException {
        // Click login button
        waitAndCheckIsElementVisible(LOGIN_BUTTON);
        waitAndClickOn(LOGIN_BUTTON);

        // Click “Login now” button
        waitAndCheckIsElementVisible(PROCEED_TO_LOGIN);
        waitAndClickOn(PROCEED_TO_LOGIN);
       Thread.sleep(5000);
        // Wait for OTP option
        waitAndCheckIsElementVisible(LOGIN_VIA_OTP);
        waitAndClickOn(LOGIN_VIA_OTP);


    }

    @And("enter valid email for msite{string}")
    public void enter_valid_email(String username) throws InterruptedException {
        // Wait
        // Click the in
        // put to focus

        Thread.sleep(5000);
        waitAndClickOn(LOGIN_EMAIL);

        // Wait for OTP option
       /* ls.waitAndCheckIsElementVisible(LOGIN_VIA_OTP);
        ls.waitAndClickOn(LOGIN_VIA_OTP);*/

        // Small pause to ensure keyboard/input is ready


        // Set the email text using framework method
        waitAndSetText(LOGIN_EMAIL, username);

        // Optional: click outside the input to dismiss keyboard/popups
        waitAndClickOn(LOGIN_BUTTON); // or any safe element outside input

        System.out.println("Email entered successfully: " + username);
    }



    @And("enter valid password for msite {string}")
    public void enter_valid_password( String password){
        waitAndCheckIsElementVisible(LOGIN_PASSWORD);
        waitAndSetText(LOGIN_PASSWORD, password);// Give the password
    }
    @Then("user should able to login on msite")
    public void user_should_able_to_login(){
        waitAndCheckIsElementVisible(CCP_LOGIN_CTA);

        waitAndClickOn(CCP_LOGIN_CTA);// Login
    }

    @And("user click on continue button on msite")
    public void userClickOnContinueButton() {
        System.out.println("entered");
        waitAndCheckIsElementVisible(CCP_LOGIN_CTA);
        waitAndClickOn(CCP_LOGIN_CTA);// Login
    }
}
