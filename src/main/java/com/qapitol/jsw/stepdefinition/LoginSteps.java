package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.jsw.utils.UserConfig;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.qapitol.jsw.utils.CustomUtil.clickButton;
import static com.qapitol.jsw.utils.CustomUtil.isAnyVisible;
import static com.qapitol.jsw.utils.CustomUtil.waits;
import static com.qapitol.jsw.utils.Hooks.softAssert;


public class LoginSteps extends AbstractWebPage {

    public static final String LOGIN_CTA_BUTTON = "loginButton";
    public static final String LOGIN_VIA_OTP = "loginViaOtp";
    public static final String LOGIN_EMAIL = "ccpLoginEmail";
    public static final String LOGIN_PASSWORD = "ccpLoginPassword";
    public static final String CCP_LOGIN_CTA = "ccpLoginCTA";
    public static final String CCP_LOGIN_ERROR_MESSAGE="errorMessage";
    public static final String CCP_INVALID_EMAIL_ERROR_MESSAGE = "invalidEmailErrorMessage";
    public static final String CCP_LOGOUT = "(//*[contains(text(),'Logout')])";
    public static final String CCP_ACCOUNT_BTN = "(//span[text()='Account'])[2]";
    /** Header state used to prove login and logout actually happened. */
    public static final String ACCOUNT_MENU = "accountMenu";
    public static final String HEADER_LOGIN_CTA = "headerLoginCta";
    // No CONTINUE_CTA constant: the login "Continue" submit button is CCP_LOGIN_CTA above, and a
    // second key for one element is how locator files drift. "continue button should be disabled"
    // already asserts against CCP_LOGIN_CTA, so the click has to use the same locator or the two
    // steps can pass and fail about different buttons.



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
        sleep(1000);
        pageRefresh();
        sleep(2000);
        waitAndCheckIsElementVisible(LOGIN_CTA_BUTTON);
        waitAndClickOn(LOGIN_CTA_BUTTON); // click on login CTA button
        waitAndCheckIsElementVisible(LOGIN_VIA_OTP);
        waitAndClickOn(LOGIN_VIA_OTP); //Click on Login with OTP
        waitAndCheckIsElementVisible(LOGIN_EMAIL);
        waitAndSetText(LOGIN_EMAIL, username); // Give you Email

    }
    @And("enter valid password {string}")
    public void enter_valid_password( String password){
        // The password itself is deliberately not logged - it ended up in every run log and in
        // every Allure report attached to it.
        log.info("User enters the password");
        waitAndCheckIsElementVisible(LOGIN_PASSWORD);
        waitAndSetText(LOGIN_PASSWORD, password);// Give the password
    }
    @Then("user should able to login")
    public void user_should_able_to_login(){
        log.info("User clicks on login CTA");
        waitAndCheckIsElementVisible(CCP_LOGIN_CTA);
        waitAndClickOn(CCP_LOGIN_CTA);
        // Clicking is not the assertion. Previously this step ended here, so a login that failed
        // - wrong password, server error, modal never closing - still reported as passed.
        verifyLoggedIn();
    }

    @Then("user should be logged in")
    public void user_should_be_logged_in() {
        verifyLoggedIn();
    }

    @Then("user should be logged out")
    public void user_should_be_logged_out() {
        softAssert.assertTrue(waitForVisible(HEADER_LOGIN_CTA),
                "Login call-to-action is not shown in the header, so the user is not logged out");
        softAssert.assertFalse(isAnyVisible(getBy(ACCOUNT_MENU)),
                "Account menu is still shown in the header after logout");
    }

    /**
     * Logged-in state is asserted from BOTH directions: the account menu appears and the Login
     * call-to-action goes away. Either one alone can be satisfied by a half-rendered header.
     */
    private void verifyLoggedIn() {
        softAssert.assertTrue(waitForVisible(ACCOUNT_MENU),
                "Account menu is not shown in the header, so the user is not logged in");
        softAssert.assertFalse(isAnyVisible(getBy(HEADER_LOGIN_CTA)),
                "Login call-to-action is still shown in the header after a successful login");
    }

    /** By for a locator key in loginsteps.yaml. */
    private By getBy(String key) {
        return By.xpath(getLocator(key));
    }

    /**
     * Polls for a header element to render.
     *
     * <p>isAnyVisible, not waitAndCheckIsElementVisible: the header is rendered twice and the
     * first copy measures 0x0, so anything resolving the first match reports it as hidden.
     */
    private boolean waitForVisible(String key) {
        By locator = getBy(key);
        for (int attempt = 0; attempt < 30; attempt++) {
            if (isAnyVisible(locator)) {
                return true;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return false;
    }

    /**
     * A password field must not reveal what was typed. Cheap to check and the kind of regression
     * a redesign introduces without anyone noticing.
     */
    @Then("the password should be masked")
    public void the_password_should_be_masked() {
        waitAndCheckIsElementVisible(LOGIN_PASSWORD);
        WebElement password = Grid.driver().findElement(getBy(LOGIN_PASSWORD));
        softAssert.assertEquals(password.getAttribute("type"), "password",
                "Password field is not masked - the typed value is readable on screen");
    }

    /**
     * The session must survive a reload. A login held only in memory logs the user out on
     * refresh, which no click-through test would notice.
     */
    @Then("the user should still be logged in after a page refresh")
    public void the_user_should_still_be_logged_in_after_refresh() {
        Grid.driver().navigate().refresh();
        softAssert.assertTrue(waitForVisible(ACCOUNT_MENU),
                "User was logged out by a page refresh - the session did not persist");
    }
    @And("enter invalid email {string}")
    public void enter_invalid_email(String username){
        waitAndCheckIsElementVisible(LOGIN_CTA_BUTTON);
        waitAndClickOn(LOGIN_CTA_BUTTON); // click on login CTA button
        waitAndCheckIsElementVisible(LOGIN_VIA_OTP);
        waitAndClickOn(LOGIN_VIA_OTP); //Click on Login with OTP
        waitAndCheckIsElementVisible(LOGIN_EMAIL);
        waitAndSetText(LOGIN_EMAIL, username); // Give you Email
        log.info("User enters the invalid email and pwd");
    }
    @And("enter invalid password {string}")
    public void enter_invalid_password( String password){
        waitAndCheckIsElementVisible(LOGIN_PASSWORD);
        waitAndSetText(LOGIN_PASSWORD, password);// Give the password
        log.info("User enters the invalid pwd");
    }
    @Then("user should not able to login with error message")
    public void user_should_not_able_to_login_with_error_message(){
        waitAndCheckIsElementVisible(CCP_LOGIN_CTA);
        waitAndClickOn(CCP_LOGIN_CTA);// Login
        waitAndCheckIsElementVisible(CCP_LOGIN_ERROR_MESSAGE);
        String FrontEndErrorMessage=waitAndGetText(CCP_LOGIN_ERROR_MESSAGE);
        softAssert.assertTrue(FrontEndErrorMessage.contains("Credentials are not valid. Please try again"));
        log.info("should not able to login with error message");
    }

    @Then("user should not able to  proceed to login with error message")
    public void user_should_not_able_to_login_with_error_message_due_to_invalid_email(){
        waitAndCheckIsElementVisible(CCP_LOGIN_CTA);
        waitAndCheckIsElementVisible(CCP_INVALID_EMAIL_ERROR_MESSAGE);
        String FrontEndErrorMessage=waitAndGetText(CCP_INVALID_EMAIL_ERROR_MESSAGE);
        softAssert.assertEquals(FrontEndErrorMessage,"You have entered a wrong email address");
        log.info("user should not able to  proceed to login with error message");
    }

    // ------------------- Configured credentials -------------------
    // The feature names the ACCOUNT; the value comes from account.<alias>.* in
    // global_<env>.properties. Nothing secret appears in Gherkin, and switching -Denv switches
    // account without touching the feature.

    @And("enter the email for the {string} account")
    public void enter_the_email_for_the_account(String alias) {
        String email = UserConfig.getAccountEmail(alias);
        log.info("Entering the configured email for account '" + alias + "'");
        enter_valid_email(email);
    }

    @And("enter the password for the {string} account")
    public void enter_the_password_for_the_account(String alias) {
        // Not logged: the previous step logged the password in clear text on every run.
        log.info("Entering the configured password for account '" + alias + "'");
        waitAndCheckIsElementVisible(LOGIN_PASSWORD);
        waitAndSetText(LOGIN_PASSWORD, UserConfig.getAccountPassword(alias));
    }

    @And("enter the wrong password for the {string} account")
    public void enter_the_wrong_password_for_the_account(String alias) {
        log.info("Entering the configured WRONG password for account '" + alias + "'");
        waitAndCheckIsElementVisible(LOGIN_PASSWORD);
        waitAndSetText(LOGIN_PASSWORD, UserConfig.getAccountWrongPassword(alias));
    }

    @And("user click on continue button")
    public void userClickOnContinueButton() {
        waitAndCheckIsElementVisible(CCP_LOGIN_CTA);
        waitAndClickOn(CCP_LOGIN_CTA);
        log.info("clicked Continue button");
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


    @Then("User click on logout button")
    public void userClickOnLogoutButton() {
        waits(By.xpath(CCP_ACCOUNT_BTN));
        clickButton(By.xpath(CCP_ACCOUNT_BTN));
        clickButton(By.xpath(CCP_LOGOUT));
        log.info("clicked logout button");
        // Same reasoning as the login step: clicking is not the assertion. Without this a logout
        // that left the session intact reported as passed.
        user_should_be_logged_out();
    }
}
