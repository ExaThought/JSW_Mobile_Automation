package com.qapitol.jsw.stepdefinition.msiteStepDefinition;

import com.qapitol.jsw.utils.ChromeSslBypassUtil;
import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.jsw.utils.LocatorReader;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.logging.Logger;

public class HeaderSteps extends AbstractWebPage {

    // ---------- YAML Keys ----------
    private static final String JSW_LOGO = "jswLogo";
    private static final String HELP = "helpIcon";
    private static final String LOGIN_CTA = "loginButton";
    private static final String HELP_PAGE_IDENTIFIER = "helpPage";

    private static final Logger logger =
            Logger.getLogger(HeaderSteps.class.getName());

    private final LocatorReader locatorReader =
            new LocatorReader("locators/msite/headersteps.yaml");

    private final SoftAssert softAssert = new SoftAssert();
    private final AppiumDriver driver = (AppiumDriver) Grid.driver();

    // ------------------- Helpers -------------------

    private WebElement getElement(String key) {
        String xpath = locatorReader.getXpath("elements." + key + ".en_US.web");

        if (xpath == null || xpath.isEmpty()) {
            throw new RuntimeException("XPath not found for key: " + key);
        }

        return Grid.driver().findElement(By.xpath(xpath));
    }

    private void waitForVisible(String key) {
        waitUntilElementIsPresent(
                String.valueOf(By.xpath(locatorReader.getXpath("elements." + key + ".en_US.web"))),
                RuntimeException.class,
                10
        );
    }

    private void click(String key) {
        waitForVisible(key);
        getElement(key).click();
    }

    // ------------------- Steps -------------------

    @When("user open the msite")
    public void user_open_the_msite() {
        driver.get(ConfigUtil.getSecretValue("ccpPortalUrl"));
        ChromeSslBypassUtil.bypassSslWarning(driver);
    }

    @Then("the home page should load successfully")
    public void the_home_page_should_load_successfully() {
        waitForVisible(JSW_LOGO);
    }

    @And("the page title should be JSW One Guest")
    public void the_page_title_should_be_jsw_one_guest() {
        softAssert.assertEquals(
                driver.getTitle(),
                "JSW One",
                "Page title mismatch"
        );
        softAssert.assertAll();
    }

    @Then("the CCP logo should be visible")
    public void the_ccp_logo_should_be_visible() {
        waitForVisible(JSW_LOGO);
        Assert.assertTrue(getElement(JSW_LOGO).isDisplayed(), "JSW logo not visible");
    }

    @When("the user clicks on the CCP logo")
    public void the_user_clicks_on_the_ccp_logo() {
        click(JSW_LOGO);
    }

    @Then("the user should be redirected to the CCP home page")
    public void the_user_should_be_redirected_to_the_ccp_home_page() {
        logger.info("Current URL: " + driver.getCurrentUrl());

        Assert.assertEquals(
                driver.getCurrentUrl(),
                ConfigUtil.getSecretValue("ccpPortalUrl"),
                "User not redirected to CCP portal"
        );
    }

    @Then("the Help icon or link should be visible")
    public void the_help_icon_or_link_should_be_visible() {
        waitForVisible(HELP);
    }

    @When("the user clicks on the Help icon or link")
    public void the_user_clicks_on_the_help_icon_or_link() throws InterruptedException {
        waitAndCheckIsElementVisible(HELP);
        waitAndCheckIsElementClickable(HELP);
        clickHelpIcon();
        waitAndCheckIsElementVisible(HELP_PAGE_IDENTIFIER);
    }

    private void clickHelpIcon() {
        String svgXpath = locatorReader.getXpath("elements.helpIcon.en_US.web");

        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));

        WebElement svg = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(svgXpath))
        );

        // Try clicking parent (preferred)
        WebElement clickable = svg.findElement(By.xpath("ancestor::button | parent::*"));

        wait.until(ExpectedConditions.elementToBeClickable(clickable));

        ((JavascriptExecutor) Grid.driver())
                .executeScript("arguments[0].scrollIntoView({block:'center'});", clickable);

        clickable.click();
    }



    @Then("the user should be redirected to the Help or Support page")
    public void the_user_should_be_redirected_to_the_help_or_support_page() {
        waitForHelpPage();
        Assert.assertTrue(
                driver.getCurrentUrl().contains("help"),
                "Help page redirection failed"
        );
    }

    private void waitForHelpPage() {
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));

        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("help"),
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath(locatorReader.getXpath("elements.helpPage.en_US.web"))
                )
        ));
    }


    @Then("the Login CTA should be visible and enabled")
    public void the_login_cta_should_be_visible_and_enabled() {
        waitForVisible(LOGIN_CTA);
        Assert.assertTrue(getElement(LOGIN_CTA).isEnabled(), "Login CTA not enabled");
    }

    @When("the user clicks on the Login CTA")
    public void the_user_clicks_on_the_login_cta() {
        click(LOGIN_CTA);
    }

    @Then("the user should be redirected to the Login page")
    public void the_user_should_be_redirected_to_the_login_page() {
        Assert.assertTrue(
                driver.getCurrentUrl().contains("profile"),
                "Login page redirection failed"
        );
    }
}
