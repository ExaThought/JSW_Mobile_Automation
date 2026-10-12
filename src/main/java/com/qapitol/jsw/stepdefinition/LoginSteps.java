package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.jsw.utils.MoEngageEventCapture;
import com.qapitol.jsw.utils.NetworkCapture;
import com.qapitol.jsw.utils.UserConfig;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

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
    private static final String LOGOUT_BUTTON_XPATH =
            "//button[@data-test-id='desktop-header-menu-item-logout']";
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

    // ------------------- Mobile number + OTP login -------------------

    /** The send-OTP call and where the code sits in it - the same ones MultiUserSteps reads. */
    private static final String OTP_SEND_URL = "/auth/api/v1/construct/otp/send";
    private static final String OTP_JSON_PATH = "data.otp";
    private static final String LOGIN_PHONE_INPUT = "loginPhoneInput";
    private static final String MOBILE_CONTINUE_CTA = "mobileContinueCta";
    private static final String LOGIN_OTP_INPUT = "loginOtpInput";
    private static final String OTP_CONTINUE_CTA = "otpContinueCta";
    private static final String OTP_ERROR_MESSAGE = "otpErrorMessage";
    private static final String MOBILE_ERROR_MESSAGE = "mobileErrorMessage";
    private static final String REGISTER_NOW_HEADING = "registerNowHeading";
    private static final String ACCOUNT_PROFILE_DETAILS_LINK = "accountProfileDetailsLink";
    private static final String PERSONAL_DETAILS_SECTION = "personalDetailsSection";
    private static final String PROFILE_NAME_VALUE = "profileNameValue";
    private static final String PROFILE_PHONE_VALUE = "profilePhoneValue";
    private static final String PROFILE_EMAIL_VALUE = "profileEmailValue";
    private static final String PROFILE_PATH = "my-profile";
    private static final String AUTH_MODAL_CLOSE_BUTTON = "authModalCloseButton";
    private static final String LOGIN_MAIN_HEADING = "loginMainHeading";
    private static final String LOGIN_TAGLINE = "loginTagline";
    private static final String LOGIN_MOBILE_LABEL = "loginMobileLabel";
    private static final String LOGIN_OR_SEPARATOR = "loginOrSeparator";
    private static final String LOGIN_CONTINUE_WITH_EMAIL_BUTTON = "loginContinueWithEmailButton";
    private static final String LOGIN_DIALOG_LOGO = "loginDialogLogo";
    private static final String REGISTER_ACCOUNT_SUBTITLE = "registerAccountSubtitle";
    private static final int REGISTRATION_WAIT_SECONDS = 15;
    private static final int LOGIN_SUCCESS_EVENT_WAIT_SECONDS = 45;
    private static final String GET_OTP_BUTTON = "getOtpButton";
    private static final String INCORRECT_OTP_MESSAGE = "Incorrect OTP. Please enter correct OTP";

    /**
     * Opens the login modal and types the mobile number.
     *
     * <p>{@code NetworkCapture.install()} sits after the modal opens and before Continue is
     * pressed: the shim lives on {@code window} so a page load wipes it, opening the modal is a
     * state change rather than a navigation, and the send-OTP call has to be made after it is in
     * place. {@code clear()} drops anything from earlier in the scenario.
     */
    @And("enter the mobile number {string}")
    public void enter_the_mobile_number(String mobile) {
        // A page that needs login can open the login dialog itself; clicking the header Login
        // over it would hit the dialog's backdrop. Open it only when it is not already showing.
        if (isAnyVisible(getBy(LOGIN_PHONE_INPUT))) {
            log.info("Login dialog is already open - not clicking the header Login button");
        } else {
            waitAndCheckIsElementVisible(LOGIN_CTA_BUTTON);
            waitAndClickOn(LOGIN_CTA_BUTTON);
            log.info("Opened the login dialog from the header Login button");
        }
        NetworkCapture.install();
        NetworkCapture.clear();
        MoEngageEventCapture.install();
        MoEngageEventCapture.clear();
        waitAndCheckIsElementVisible(LOGIN_PHONE_INPUT);
        waitAndSetText(LOGIN_PHONE_INPUT, mobile);
        log.info("Entered the mobile number");
    }

    /**
     * A random 10-digit number whose first digit is 6-9, from the generator the multi-user flow
     * uses. It makes no API call, so the number is not checked for being registered - for the
     * wrong-OTP flow that is fine, the app sends an OTP to any valid-looking number.
     */
    @And("enter a randomly generated mobile number")
    public void enter_a_randomly_generated_mobile_number() {
        enter_the_mobile_number(com.qapitol.jsw.utils.ApiUtils.generateMobileNumber());
    }

    /**
     * A random number that the app does not know: generated, then checked against the registered
     * mobile numbers, so the scenario cannot land on the login of an existing account. Needs the
     * same API access the multi-user flow's unregistered-number generator uses.
     */
    @And("enter a randomly generated unregistered mobile number")
    public void enter_a_randomly_generated_unregistered_mobile_number() {
        enter_the_mobile_number(com.qapitol.jsw.utils.ApiUtils.generateNotRegisteredMobileNumber());
    }

    @And("user clicks continue to get the OTP")
    public void user_clicks_continue_to_get_the_otp() {
        waitAndCheckIsElementVisible(MOBILE_CONTINUE_CTA);
        waitAndClickOn(MOBILE_CONTINUE_CTA);
        log.info("clicked Continue on the mobile number screen");
    }

    /** Reads the OTP from the send-OTP response the browser received and types it in. */
    @And("enter the OTP captured from the construct otp API")
    public void enter_the_otp_captured_from_the_construct_otp_api() {
        String otp = interceptedOtp();
        waitAndCheckIsElementVisible(LOGIN_OTP_INPUT);
        waitAndSetText(LOGIN_OTP_INPUT, otp);
        // Length only - an OTP in the run log is an OTP in the Allure report.
        log.info("Entered the captured OTP (" + otp.length() + " digits)");
    }

    /**
     * Types an OTP that is NOT the one the app sent. Derived from the intercepted one - every digit
     * shifted by one - so it has the same length and format and is guaranteed to differ, which a
     * hard-coded value could not be (it would be the right code one run in 10,000).
     */
    @And("enter an OTP different from the captured one")
    public void enter_an_otp_different_from_the_captured_one() {
        String otp = interceptedOtp();
        StringBuilder wrong = new StringBuilder();
        for (char digit : otp.toCharArray()) {
            wrong.append((char) ('0' + ((digit - '0') + 1) % 10));
        }
        waitAndCheckIsElementVisible(LOGIN_OTP_INPUT);
        waitAndSetText(LOGIN_OTP_INPUT, wrong.toString());
        log.info("Entered an OTP that differs from the captured one (" + wrong.length() + " digits)");
    }

    private String interceptedOtp() {
        String otp = NetworkCapture.awaitJsonValue(OTP_SEND_URL, OTP_JSON_PATH);
        if (otp == null || !otp.matches("\\d{4,8}")) {
            throw new AssertionError("The response from " + OTP_SEND_URL + " carries a "
                    + OTP_JSON_PATH + " that is not a 4-8 digit code, so it cannot be entered.");
        }
        return otp;
    }

    /** Clicks Continue on the OTP screen and nothing else - for the wrong-OTP flow. */
    @And("user clicks continue on the OTP screen")
    public void user_clicks_continue_on_the_otp_screen() {
        waitAndCheckIsElementVisible(OTP_CONTINUE_CTA);
        waitAndClickOn(OTP_CONTINUE_CTA);
        log.info("clicked Continue on the OTP screen");
    }

    /**
     * The field's own error, a {@code <p data-test-id="login-mobile-input-error">} under the input.
     * It appears as the number is typed - "Please enter a valid phone number" for fewer than 10
     * digits, "Invalid Mobile Number" for 10 digits starting below 6 - so no click is needed.
     */
    @Then("the mobile number error message should be {string}")
    public void the_mobile_number_error_message_should_be(String expected) {
        waitAndCheckIsElementVisible(MOBILE_ERROR_MESSAGE);
        softAssert.assertEquals(waitAndGetText(MOBILE_ERROR_MESSAGE).trim(), expected,
                "Wrong error message under the mobile number field");
        user_should_be_logged_out();
    }

    /**
     * Clicks Get OTP when the app lets it be clicked. For fewer than 10 digits the app keeps the
     * button disabled (pointer-events-none), so a click would only fail on that; the field error
     * is already showing then. For 10 digits starting below 6 the button is enabled and clicked.
     */
    @And("user tries to continue with the mobile number")
    public void user_tries_to_continue_with_the_mobile_number() {
        waitAndCheckIsElementVisible(GET_OTP_BUTTON);
        WebElement button = Grid.driver().findElement(getBy(GET_OTP_BUTTON));
        if (button.isEnabled()) {
            button.click();
            log.info("clicked Get OTP");
        } else {
            log.info("Get OTP is disabled for this number - not clicked");
        }
    }

    /**
     * An unregistered number must land on registration, not on the home page. Both registration
     * markers are waited for explicitly (15s) because the redirect follows the verify call, and
     * the header must not show the Account menu - that would mean the user was logged in.
     */
    @Then("the user should be redirected to the registration page")
    public void the_user_should_be_redirected_to_the_registration_page() {
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(
                Grid.driver(), java.time.Duration.ofSeconds(REGISTRATION_WAIT_SECONDS));
        for (String key : new String[]{REGISTER_NOW_HEADING, REGISTER_ACCOUNT_SUBTITLE}) {
            try {
                wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(getBy(key)));
            } catch (org.openqa.selenium.TimeoutException e) {
                softAssert.fail("'" + getLocator(key) + "' is not visible " + REGISTRATION_WAIT_SECONDS
                        + "s after verifying the OTP, so the user was not taken to registration."
                        + " Current URL: " + Grid.driver().getCurrentUrl());
            }
        }
        softAssert.assertFalse(isAnyVisible(getBy(ACCOUNT_MENU)),
                "Account menu is shown, so the unregistered number was logged in instead of registered");
    }

    @Then("the incorrect OTP error message should be shown")
    public void the_incorrect_otp_error_message_should_be_shown() {
        waitAndCheckIsElementVisible(OTP_ERROR_MESSAGE);
        softAssert.assertEquals(waitAndGetText(OTP_ERROR_MESSAGE).trim(), INCORRECT_OTP_MESSAGE,
                "Wrong error message after entering an incorrect OTP");
        user_should_be_logged_out();
    }

    /**
     * Verifies the OTP, then checks the login_success MoEngage event the login fires.
     *
     * <p>The event is validated straight after the click, as PR-3039's email login does: the
     * capture buffer lives in the page and a reload would drop it. The mobile spec checks `type`
     * and `source` for presence only - see data/moengage/login_success_mobile.yaml.
     */
    @And("user clicks continue to verify the OTP")
    public void user_clicks_continue_to_verify_the_otp() {
        user_clicks_continue_on_the_otp_screen();
        awaitLoginSuccessEvent();
        MoEngageEventCapture.validateEventFromYaml("login_success", "login_success_mobile",
                java.util.Collections.emptyMap());
        verifyLoggedIn();
    }

    /**
     * Gives the SDK longer than validateEventFromYaml's built-in 15s to report login_success. On QA
     * it has arrived 10-15s after the OTP click and, on 12 Oct 2026, later than 15s, failing the
     * check with the event simply not there yet. Waiting here instead of changing that timeout
     * keeps MoEngageEventCapture identical to PR-3039. A timeout is not reported here: the
     * validation that follows reports it, with the events that were seen.
     */
    private void awaitLoginSuccessEvent() {
        long start = System.currentTimeMillis();
        try {
            MoEngageEventCapture.awaitEvent("login_success", LOGIN_SUCCESS_EVENT_WAIT_SECONDS);
            log.info("MoEngage login_success was tracked " + (System.currentTimeMillis() - start) / 1000
                    + "s after clicking Continue on the OTP screen");
        } catch (IllegalStateException e) {
            log.info("MoEngage login_success was NOT tracked within " + LOGIN_SUCCESS_EVENT_WAIT_SECONDS
                    + "s of clicking Continue on the OTP screen");
        }
    }

    /**
     * After logging in from a page that needed login, the user must be taken back to that page,
     * not left on the home page. Waits up to 15s, because the redirect follows the verify call.
     * Home is the root path, including the "/?forbidden=1" a guest is bounced to.
     */
    @Then("They are NOT dropped back on Home.")
    public void they_are_not_dropped_back_on_home() {
        long deadline = System.currentTimeMillis() + REGISTRATION_WAIT_SECONDS * 1000L;
        String url = Grid.driver().getCurrentUrl();
        while (isHomeUrl(url) && System.currentTimeMillis() < deadline) {
            sleep(500);
            url = Grid.driver().getCurrentUrl();
        }
        boolean onHome = isHomeUrl(url);
        log.info("After login the user is on " + url + (onHome ? " - the HOME page" : " - not the home page"));
        if (onHome) {
            // Hard stop, not a soft assert: every later step checks the page the user should have
            // been returned to, so on Home they all fail too and bury this cause under follow-on
            // failures (8 of them in the 12 Oct 2026 run).
            throw new AssertionError("After login the user was dropped back on the home page (" + url
                    + ") instead of the page they opened before logging in");
        }
    }

    private static boolean isHomeUrl(String url) {
        String path = java.net.URI.create(url).getPath();
        return path == null || path.isEmpty() || "/".equals(path);
    }

    // ------------------- Profile, logout and the browser back button -------------------

    @And("user opens Profile details from the Account menu")
    public void user_opens_profile_details_from_the_account_menu() {
        waits(By.xpath(CCP_ACCOUNT_BTN));
        clickButton(By.xpath(CCP_ACCOUNT_BTN));
        clickButton(getBy(ACCOUNT_PROFILE_DETAILS_LINK));
        boolean opened = waitForVisible(PERSONAL_DETAILS_SECTION);
        log.info("Opened Profile details - URL " + Grid.driver().getCurrentUrl()
                + (opened ? "" : " (the 'Personal details' section did NOT appear)"));
        softAssert.assertTrue(opened && Grid.driver().getCurrentUrl().contains(PROFILE_PATH),
                "Profile details did not open. Current URL: " + Grid.driver().getCurrentUrl());
    }

    /**
     * The profile must belong to the account that just logged in: the email shown is the one the
     * login used, and name and phone are filled in (phone as 10 digits, with or without +91).
     */
    @Then("the profile should show the details of the {string} account")
    public void the_profile_should_show_the_details_of_the_account(String alias) {
        String expectedEmail = UserConfig.getAccountEmail(alias);
        String email = profileText(PROFILE_EMAIL_VALUE);
        String name = profileText(PROFILE_NAME_VALUE);
        String phone = profileText(PROFILE_PHONE_VALUE);
        String phoneDigits = phone == null ? "" : phone.replaceAll("\\D", "");
        if (phoneDigits.length() == 12 && phoneDigits.startsWith("91")) {
            phoneDigits = phoneDigits.substring(2);
        }
        log.info("Profile shows name '" + name + "', email '" + email + "', phone ending "
                + (phoneDigits.length() >= 4 ? phoneDigits.substring(phoneDigits.length() - 4) : "(none)"));

        softAssert.assertTrue(email != null && email.equalsIgnoreCase(expectedEmail),
                "Profile email '" + email + "' is not the email the '" + alias + "' account logged in with");
        softAssert.assertTrue(name != null && !name.isBlank(), "Name is not displayed on the profile page");
        softAssert.assertTrue(phoneDigits.matches("[6-9]\\d{9}"),
                "Phone number on the profile page is not a valid 10-digit mobile number: '" + phone + "'");
    }

    private String profileText(String key) {
        try {
            return Grid.driver().findElement(getBy(key)).getText().trim();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return null;
        }
    }

    @When("user presses the browser back button")
    public void user_presses_the_browser_back_button() {
        String before = Grid.driver().getCurrentUrl();
        Grid.driver().navigate().back();
        // Back can first show the previous page from the browser cache and only then let the app
        // check the session and redirect, so give it time before the next step reads the page.
        sleep(3000);
        log.info("Pressed the browser back button: " + before + " -> " + Grid.driver().getCurrentUrl());
    }

    /**
     * After logout, Back must not show anything that belongs to the logged-in user: the header has
     * Login rather than the Account menu, and the profile's personal details are not on screen. On
     * QA (12 Oct 2026) Back lands on the "Login or Register" dialog rather than the home page; the
     * scenario checks that dialog with its own step.
     */
    @Then("the user should not be returned to a logged-in screen")
    public void the_user_should_not_be_returned_to_a_logged_in_screen() {
        String url = Grid.driver().getCurrentUrl();
        boolean accountMenu = isAnyVisible(getBy(ACCOUNT_MENU));
        boolean loginCta = waitForVisible(HEADER_LOGIN_CTA);
        boolean personalDetails = isAnyVisible(getBy(PERSONAL_DETAILS_SECTION));
        log.info("After Back the browser is on " + url + " - Account menu " + (accountMenu ? "SHOWN" : "not shown")
                + ", Login button " + (loginCta ? "shown" : "NOT shown")
                + ", profile personal details " + (personalDetails ? "SHOWN" : "not shown"));

        softAssert.assertFalse(accountMenu,
                "Back after logout shows the Account menu, so it returned to a logged-in screen (" + url + ")");
        softAssert.assertTrue(loginCta,
                "Back after logout does not show the Login button, so the user does not look logged out (" + url + ")");
        softAssert.assertFalse(personalDetails,
                "Back after logout shows the profile's personal details (" + url + ")");
    }

    /**
     * The "Login or Register" dialog, checked part by part so a failure names the missing piece:
     * logo, tagline, heading, close button, an EMPTY mobile number field (nothing from the
     * logged-out session prefilled), a disabled Get OTP, the "or" separator and an enabled
     * Continue with email.
     */
    @Then("the Login or Register page should be displayed")
    public void the_login_or_register_page_should_be_displayed() {
        boolean heading = waitForVisible(LOGIN_MAIN_HEADING);
        String headingText = heading ? Grid.driver().findElement(getBy(LOGIN_MAIN_HEADING)).getText().trim() : null;
        log.info("Login dialog heading is " + (heading ? "'" + headingText + "'" : "NOT displayed"));
        softAssert.assertEquals(headingText, "Login or Register", "The 'Login or Register' heading is not displayed");
        if (!heading) {
            return;
        }

        checkShown(LOGIN_TAGLINE, "The tagline 'Your one-stop digital marketplace for all your industrial needs'");
        checkShown(LOGIN_DIALOG_LOGO, "The JSW One MSME logo");
        checkShown(AUTH_MODAL_CLOSE_BUTTON, "The close (X) button");
        checkShown(LOGIN_MOBILE_LABEL, "The 'Mobile number' field label");
        checkShown(LOGIN_OR_SEPARATOR, "The 'or' separator");

        boolean mobile = checkShown(LOGIN_PHONE_INPUT, "The mobile number field");
        if (mobile) {
            String value = Grid.driver().findElement(getBy(LOGIN_PHONE_INPUT)).getAttribute("value");
            log.info("Mobile number field is " + (value == null || value.isEmpty() ? "empty" : "PREFILLED"));
            softAssert.assertTrue(value == null || value.isEmpty(),
                    "The mobile number field is prefilled on the login dialog after logout");
        }
        if (checkShown(GET_OTP_BUTTON, "The 'Get OTP' button")) {
            boolean enabled = Grid.driver().findElement(getBy(GET_OTP_BUTTON)).isEnabled();
            log.info("'Get OTP' is " + (enabled ? "ENABLED" : "disabled") + " with no number entered");
            softAssert.assertFalse(enabled, "'Get OTP' is enabled before a mobile number is entered");
        }
        if (checkShown(LOGIN_CONTINUE_WITH_EMAIL_BUTTON, "The 'Continue with email' button")) {
            boolean enabled = Grid.driver().findElement(getBy(LOGIN_CONTINUE_WITH_EMAIL_BUTTON)).isEnabled();
            log.info("'Continue with email' is " + (enabled ? "enabled" : "DISABLED"));
            softAssert.assertTrue(enabled, "'Continue with email' is disabled on the login dialog");
        }
    }

    /** Asserts one element of the login dialog is visible, logging the result; returns it. */
    private boolean checkShown(String key, String description) {
        boolean shown = isAnyVisible(getBy(key));
        log.info(description + " is " + (shown ? "displayed" : "NOT displayed"));
        softAssert.assertTrue(shown, description + " is not displayed on the login dialog");
        return shown;
    }

    // ------------------- Login abandoned -------------------

    /**
     * Opens the login dialog from the header, with MoEngage capture armed and emptied first.
     *
     * <p>The page is reloaded once after install(): the capture only sees the SDK's events when it
     * is in place BEFORE the SDK starts, i.e. when it runs at the start of a page load (install()
     * registers it for every new page). Installed on a page that has already loaded it captures
     * nothing - the 12 Oct 2026 run saw no events at all, not even login_initiated. Scenarios that
     * reload anyway (a login navigates) never hit this.
     */
    @When("the guest user clicks the header Login button")
    public void the_guest_user_clicks_the_header_login_button() {
        MoEngageEventCapture.install();
        Grid.driver().navigate().refresh();
        waitAndCheckIsElementVisible(LOGIN_CTA_BUTTON);
        MoEngageEventCapture.install();
        log.info("Reloaded the page so MoEngage capture is in place before the SDK starts");
        MoEngageEventCapture.clear();
        waitAndClickOn(LOGIN_CTA_BUTTON);
        boolean open = waitForVisible(LOGIN_MAIN_HEADING);
        log.info("Clicked the header Login button - the 'Login or Register' dialog is "
                + (open ? "open" : "NOT open"));
        if (!open) {
            throw new AssertionError("The 'Login or Register' dialog did not open after clicking Login");
        }
    }

    @And("the guest user closes the login dialog")
    public void the_guest_user_closes_the_login_dialog() {
        clickButton(getBy(AUTH_MODAL_CLOSE_BUTTON));
        boolean closed = false;
        for (int attempt = 0; attempt < 20 && !closed; attempt++) {
            closed = !isAnyVisible(getBy(LOGIN_MAIN_HEADING));
            if (!closed) {
                sleep(500);
            }
        }
        log.info("Clicked the login dialog's close (X) button - the dialog is " + (closed ? "closed" : "STILL OPEN"));
        softAssert.assertTrue(closed, "The login dialog is still open after clicking its close button");
    }

    /**
     * login_abandoned must fire with the attributes in data/moengage/login_abandoned.yaml.
     *
     * <p>The app sends no abandonment-stage attribute (QA, 12 Oct 2026), so the stage is reported in
     * the log rather than asserted: if an attribute naming a stage appears later, its value is
     * logged; until then the log says it is missing.
     */
    @Then("the login_abandoned event should fire for the closed login dialog")
    public void the_login_abandoned_event_should_fire_for_the_closed_login_dialog() {
        MoEngageEventCapture.validateEventFromYaml("login_abandoned");
        List<MoEngageEventCapture.Event> fired = MoEngageEventCapture.events("login_abandoned");
        if (fired.isEmpty()) {
            return;  // validateEventFromYaml has already reported it missing
        }
        if (fired.size() > 1) {
            log.info("login_abandoned fired " + fired.size() + " times for one close of the dialog");
        }
        org.json.JSONObject attributes = fired.get(fired.size() - 1).attributes;
        String stageKey = null;
        for (String key : attributes.keySet()) {
            if (key.toLowerCase().contains("stage")) {
                stageKey = key;
                break;
            }
        }
        log.info(stageKey == null
                ? "login_abandoned carries NO abandonment-stage attribute - attributes sent: " + attributes.keySet()
                : "login_abandoned abandonment stage: " + stageKey + " = '" + attributes.opt(stageKey) + "'");
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


    /**
     * Logout is the last item of the Account dropdown, which is taller than the browser window:
     * it sits below the fold, and a click at its coordinates lands outside the viewport or on
     * whatever is drawn there (ElementClickInterceptedException at the Logout item). Scroll it
     * into view first.
     */
    private void clickLogout() {
        WebDriver driver = Grid.driver();
        for (WebElement candidate : driver.findElements(By.xpath(CCP_LOGOUT))) {
            if (candidate.isDisplayed()) {
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({block:'center'});", candidate);
                break;
            }
        }
        try {
            clickButton(By.xpath(CCP_LOGOUT));
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            // The dropdown is fixed-position and still partly off-screen in a short window, so a
            // native click can be intercepted even after scrolling. The item is a plain button;
            // clicking it through the DOM does the same thing the user's click would.
            log.info("Logout click was intercepted - clicking it through the DOM instead");
            WebElement logout = driver.findElement(By.xpath(LOGOUT_BUTTON_XPATH));
            ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", logout);
        }
    }

    @Then("User click on logout button")
    public void userClickOnLogoutButton() {
        waits(By.xpath(CCP_ACCOUNT_BTN));
        clickButton(By.xpath(CCP_ACCOUNT_BTN));
        clickLogout();
        log.info("clicked logout button");
        // Same reasoning as the login step: clicking is not the assertion. Without this a logout
        // that left the session intact reported as passed.
        user_should_be_logged_out();
    }
}
