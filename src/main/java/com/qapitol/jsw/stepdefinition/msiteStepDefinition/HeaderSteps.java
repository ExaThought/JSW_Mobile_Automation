package com.qapitol.jsw.stepdefinition.msiteStepDefinition;

import com.qapitol.jsw.utils.ChromeSslBypassUtil;
import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.logging.Logger;

public class HeaderSteps extends AbstractWebPage {

    public static final String JSW_LOGO = "jswLogo";
    public static final String LOGIN_VIA_OTP = "loginViaOtp";
    public static final String HELP ="helpIcon";
    public static final String LOGIN_CTA ="loginButton";
    public static final String HELP_PAGE_IDENTIFIER ="helpPage";

    AppiumDriver driver = (AppiumDriver) Grid.driver();
    SoftAssert softAssert = new SoftAssert();

    @When("user open the msite")
    public void user_open_the_msite() throws InterruptedException {
        AppiumDriver driver = (AppiumDriver) Grid.driver();
        // Open MSite
        driver.get(ConfigUtil.getSecretValue("ccpPortalUrl"));
        // Handle SSL warning if it appears
        ChromeSslBypassUtil.bypassSslWarning(driver);
    }

    @Then("the home page should load successfully")
    public void the_home_page_should_load_successfully() {
        waitAndCheckIsElementVisible(JSW_LOGO);
    }

    @And("the page title should be JSW One Guest")
    public void andThePageTitleShouldBeJSWOneGuest() {
        String expectedTitle = "JSW One";
        String actualTitle = Grid.driver().getTitle();
        softAssert.assertEquals(actualTitle, expectedTitle, "Page title should be '" + expectedTitle + "'");
    }

    @Then("the CCP logo should be visible")
    public void the_ccp_logo_should_be_visible() throws InterruptedException {
        user_open_the_msite();
        waitAndCheckIsElementVisible(JSW_LOGO);
    }

    @When("the user clicks on the CCP logo") public void the_user_clicks_on_the_ccp_logo() throws InterruptedException {
        waitAndCheckIsElementClickable(JSW_LOGO);
        waitAndClickOn(JSW_LOGO);
        waitAndCheckIsElementVisible(1000, JSW_LOGO); }


    @Then("the user should be redirected to the CCP home page")
    public void the_user_should_be_redirected_to_the_ccp_home_page() {
        Logger.getLogger("Current URL after clicking CCP logo: " + Grid.driver().getCurrentUrl());
        softAssert.assertEquals(
                Grid.driver().getCurrentUrl(),
                ConfigUtil.getSecretValue("ccpPortalUrl"),
                "User is not redirected to CCP portal URL"
        );

        softAssert.assertAll();
    }

    @Then("the Help icon or link should be visible")
    public void the_help_icon_or_link_should_be_visible() throws InterruptedException {
        user_open_the_msite();
        waitAndCheckIsElementVisible(HELP);
        softAssert.assertEquals("Help icon/link is not visible", String.valueOf(waitAndCheckIsElementClickable(HELP)));
    }

    @When("the user clicks on the Help icon or link")
    public void the_user_clicks_on_the_help_icon_or_link() {
        waitAndClickOn(HELP);
        waitAndCheckIsElementVisible(HELP_PAGE_IDENTIFIER);
    }

    @Then("the user should be redirected to the Help or Support page")
    public void the_user_should_be_redirected_to_the_help_or_support_page() {
        Assert.assertTrue(
                driver.getCurrentUrl().contains("help"),
                "Help page redirection failed"
                );
    }

    @Then("the Login CTA should be visible and enabled")
    public void the_login_cta_should_be_visible_and_enabled() throws InterruptedException {
        user_open_the_msite();
        waitAndCheckIsElementVisible(LOGIN_CTA);
    }

    @When("the user clicks on the Login CTA")
    public void the_user_clicks_on_the_login_cta() {
      waitAndCheckIsElementVisible(LOGIN_CTA);
        waitAndClickOn(LOGIN_CTA);

    }

    @Then("the user should be redirected to the Login page")
    public void the_user_should_be_redirected_to_the_login_page() {
        Assert.assertTrue(
                driver.getCurrentUrl().contains("profile"),
                "Login page redirection failed"
                );
    }
}
