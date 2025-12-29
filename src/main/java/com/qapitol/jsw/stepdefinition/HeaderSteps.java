package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.ChromeSslBypassUtil;
import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

public class HeaderSteps extends AbstractWebPage {

    public static final String LOGIN_CTA_BUTTON = "loginButton";
    public static final String LOGIN_VIA_OTP = "loginViaOtp";

    @When("user open the msite")
    public void user_open_the_msite() throws InterruptedException {

        // Use the EXISTING Appium driver from Grid
        AppiumDriver driver = (AppiumDriver) Grid.driver();

        // Open MSite
        driver.get(ConfigUtil.getSecretValue("ccpPortalUrl"));


        // Handle SSL warning if it appears
        ChromeSslBypassUtil.bypassSslWarning(driver);

        Thread.sleep(100000);

        // Example action after SSL bypass
        // driver.findElement(By.id(LOGIN_CTA_BUTTON)).click();
    }
}
