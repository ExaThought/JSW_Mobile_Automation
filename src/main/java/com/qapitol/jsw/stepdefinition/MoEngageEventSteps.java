package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.UserConfig;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.lightbody.bmp.BrowserMobProxy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.qapitol.jsw.utils.MoEngageUtil.captureMoEngageEvents;
import static com.qapitol.jsw.utils.MoEngageUtil.startProxy;

public class MoEngageEventSteps {

    private static final String LOGIN = "loginButton";
    private static final String LOGIN_OTP = "loginViaOtp";
    private static final String LOGIN_EMAIL = "ccpLoginEmail";
    private static final String LOGIN_PASSWORD = "ccpLoginPassword";
    private static final String LOGIN_CTA = "ccpLoginCTA";

    private BrowserMobProxy proxy;
    private WebDriver driver;

    @When("User open the website")
    public void user_open_the_website() {
        // Start proxy and driver
        var pair = startProxy();
        driver = pair.driver;
        proxy = pair.proxy;

        driver.get("https://one.qa.jswone.in/");
        driver.manage().window().maximize();
    }

    @And("click login with Email")
    public void click_login_with_email() {
        driver.findElement(By.xpath("//button[@id='login-cta']")).click();
        driver.findElement(By.xpath("//button[@id='login-via-otp']")).click();
//        waitAndCheckIsElementVisible(LOGIN);
//        waitAndClickOn(LOGIN);
//        waitAndCheckIsElementVisible(LOGIN_OTP);
//        waitAndClickOn(LOGIN_OTP);
    }

    @And("Enter Email-ID with configured user")
    public void enter_email_with_configured_user() {
        enter_email(UserConfig.getEmail());
    }

    @And("Enter Email-ID {string}")
    public void enter_email(String email) {
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
//        waitAndCheckIsElementVisible(LOGIN_EMAIL);
//        waitAndSetText(LOGIN_EMAIL, email);
    }

    @And("Enter the Password with configured user")
    public void enter_the_password_with_configured_user() {
        enter_the_password(UserConfig.getPassword());
    }

    @And("Enter the Password {string}")
    public void enter_the_password(String password) {
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
//        waitAndCheckIsElementVisible(LOGIN_PASSWORD);
//        waitAndSetText(LOGIN_PASSWORD, password);
//        sleep(2000);
    }

    @Then("Check Mo-Engage event is capture for login")
    public void check_mo_engage_event_is_capture_for_login() {
//        waitAndCheckIsElementVisible(LOGIN_CTA);
//        waitAndClickOn(LOGIN_CTA);

        driver.findElement(By.xpath("//button[@id='login-to-continue']")).click();

        captureMoEngageEvents(proxy);

//        sleep(5000);
        proxy.stop();
        driver.quit();
    }
}
