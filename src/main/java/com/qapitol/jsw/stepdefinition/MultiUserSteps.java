package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.config.SessionBuilder;
import com.qapitol.jsw.context.ScenarioContext;
import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.jsw.utils.CustomerProfileUtil;
import com.qapitol.jsw.utils.NetworkCapture;
import com.qapitol.jsw.utils.UserConfig;
import com.qapitol.jsw.utils.YopmailInbox;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static com.qapitol.jsw.utils.CustomUtil.enterText;
import static com.qapitol.jsw.utils.CustomUtil.generateEmail;
import static com.qapitol.jsw.utils.CustomUtil.generateFirstName;
import static com.qapitol.jsw.utils.ApiUtils.generateMobileNumber;
import static com.qapitol.jsw.utils.CustomUtil.getText;
import static com.qapitol.jsw.utils.CustomUtil.isAnyVisible;
import static com.qapitol.jsw.utils.CustomUtil.isDisplayed;
import static com.qapitol.jsw.utils.CustomUtil.waits;
import static com.qapitol.jsw.utils.Hooks.softAssert;

public class MultiUserSteps extends AbstractWebPage {

    private static final String LOGIN_CTA_BUTTON = "loginButton";
    private static final String CONTINUE_WITH_EMAIL_CTA = "continueWithEmailCta";
    private static final String LOGIN_EMAIL = "ccpLoginEmail";
    private static final String LOGIN_PASSWORD = "ccpLoginPassword";
    private static final String CONTINUE_CTA = "ccpLoginCTA";
    private static final String LOGIN_ERROR_MESSAGE = "errorMessage";
    private static final String ACCOUNT_MENU = "accountMenu";
    private static final String HEADER_LOGIN_CTA = "headerLoginCta";
    public static final String CCP_LOGOUT = "(//*[contains(text(),'Logout')])";
    /**
     * The Account menu's Logout: CCP_LOGOUT minus the profile page's own sidebar Logout button,
     * which opens a confirmation dialog rather than logging out from the menu.
     */
    private static final String MENU_LOGOUT = "//*[contains(text(),'Logout')]"
            + "[not(ancestor-or-self::*[@data-test-id='profile-logout-button'])]";
    public static final String CCP_ACCOUNT_BTN = "//a[contains(@href, '/cart-detail')]/following-sibling::div/div/span";
    private static final String UsersPermissionsTab = "usersPermissionsTab";
    private static final String ACCOUNT_PROFILE_DETAILS_LINK = "accountProfileDetailsLink";
    private static final String PROFILE_NAME_VALUE = "profileNameValue";
    private static final String PROFILE_PHONE_VALUE = "profilePhoneValue";
    private static final String PROFILE_EMAIL_VALUE = "profileEmailValue";
    private static final String USERS_PERMISSIONS_HEADING = "usersPermissionsHeading";
    private static final String PRIMARY_USER_TAG = "primaryUserTag";
    private static final String USER_EMAIL_ON_PAGE = "userEmailOnPage";

    // --- Add-user / invitation form ---
    private static final String ADD_USER_BUTTON = "addUserButton";
    private static final String ADD_USER_DIALOG_TITLE = "addUserDialogTitle";
    private static final String ADD_USER_NAME = "addUserName";
    private static final String ADD_USER_MOBILE = "addUserMobile";
    private static final String ADD_USER_EMAIL = "addUserEmail";
    private static final String DEPARTMENT_DROPDOWN = "departmentDropdown";
    private static final String DEPARTMENT_OPTION = "departmentOption";
    private static final String SELECTED_DEPARTMENT_CHIP = "selectedDepartmentChip";
    private static final String ORDER_MANAGEMENT_TOGGLE = "orderManagementToggle";
    private static final String ROLE_TOGGLE = "roleToggle";

    /**
     * The three Roles &amp; permissions toggles, spelled exactly as the screen prints them.
     *
     * <p>The list is what makes "exactly these roles" enforceable: the invite step walks all three
     * and drives each to on or off, so a role the scenario did not ask for cannot be left enabled
     * by a form default. Add a fourth role to the product and it belongs here first.
     */
    private static final List<String> ALL_ROLES = Arrays.asList(
            "Order & materials management",
            "Finance & account management",
            "Promotional notifications");
    private static final String ADD_USER_SUBMIT = "addUserSubmit";

    // --- Row controls and state ---
    private static final String VIEW_EDIT_FOR_MOBILE = "viewEditButtonForMobile";
    private static final String DELETE_USER_BUTTON = "deleteUserButton";
    private static final String CONFIRM_DELETE_BUTTON = "confirmDeleteButton";
    private static final String USER_CARD_FOR_EMAIL = "userCardForEmail";
    private static final String STATUS_FOR_USER = "statusForUser";
    private static final String STATUS_FOR_EMAIL = "statusForEmail";
    private static final String PERMISSIONS_FOR_EMAIL = "permissionsForEmail";

    // --- Invitation acceptance / expiry pages ---
    private static final String INVITATION_SETUP_HEADING = "invitationSetupHeading";
    private static final String INVITATION_SETUP_EMAIL_FIELD = "invitationSetupEmailField";
    private static final String INVITATION_EXPIRED_MESSAGE = "invitationExpiredMessage";
    private static final String REQUEST_NEW_INVITATION_GUIDANCE = "requestNewInvitationGuidance";
    private static final String RESEND_INVITATION_BUTTON = "resendInvitationButton";
    private static final String ACCEPT_INVITE_IN_EMAIL = "acceptInviteInEmail";
    private static final String PERMISSIONS_GRANTED_HEADING = "permissionsGrantedHeading";
    private static final String GRANTED_ROLE_NAMES = "grantedRoleNames";
    private static final String ACCEPT_INVITE_CONTINUE = "acceptInviteContinue";
    private static final String LOGIN_PHONE_INPUT = "loginPhoneInput";
    private static final String GET_OTP_BUTTON = "getOtpButton";
    private static final String OTP_DIALOG_TITLE = "otpDialogTitle";
    private static final String OTP_INPUT = "otpInput";
    private static final String WHATSAPP_CONSENT = "whatsappConsent";
    private static final String TERMS_CONSENT = "termsConsent";
    private static final String VERIFY_OTP_BUTTON = "verifyOtpButton";
    private static final String LOGOUT_POPUP = "logoutPopup";
    private static final String CONFIRM_LOGOUT_BUTTON = "confirmLogoutButton";
    private static final String HEADER_LOGO = "headerLogo";
    /** Times the Account menu is (re)opened before a menu click gives up. */
    private static final int ACCOUNT_MENU_ATTEMPTS = 3;
    /** How long to wait for an item to show in a freshly opened Account menu. */
    private static final long ACCOUNT_MENU_ITEM_WAIT_MS = 5000;
    private static final String ROLES_ERROR_MESSAGE = "rolesErrorMessage";
    private static final String PERMISSION_ALERT = "permissionAlert";
    private static final String PERMISSION_ALERT_TEXT = "permissionAlertText";
    private static final String NO_CART_PERMISSION_MESSAGE =
            "You don't have permission to add items to the cart. Contact your admin for help.";
    private static final String ANY_USER_CARD_EDIT_BUTTON = "anyUserCardEditButton";
    private static final String AUTOGEN_USER_CARD_EDIT_BUTTON = "autogenUserCardEditButton";
    private static final String USER_MODAL_REMOVE_USER_BUTTON = "userModalRemoveUserButton";
    private static final String DELETE_USER_CONFIRM_BUTTON = "deleteUserConfirmButton";
    /** Seconds to wait for the user list to drop by one after a delete is confirmed. */
    private static final int CLEANUP_DELETE_WAIT_SECONDS = 15;
    /** The user cards load after the tab opens; wait up to 10s for them (20 polls of 500ms). */
    private static final int USER_CARDS_LOAD_ATTEMPTS = 20;
    /** Failed deletes in a row after which the cleanup stops trying and lets the scenario run. */
    private static final int CLEANUP_MAX_CONSECUTIVE_FAILURES = 3;
    private static final String PAYMENT_REMINDER_HEADING = "paymentReminderHeading";
    private static final String PAYMENT_DUE_NOW_VALUE = "paymentDueNowValue";
    private static final String PAYMENT_REMINDER_CLOSE_BUTTON = "paymentReminderCloseButton";
    /** The payment reminder opens shortly after login; absent after this long means it is not coming. */
    private static final int PAYMENT_REMINDER_ATTEMPTS = 10;

    /**
     * The send-OTP call, matched on its PATH so it holds across environments - the host differs
     * between qa.msme and preprod, the path does not.
     */
    private static final String OTP_SEND_URL = "/auth/api/v1/construct/otp/send";

    /** Where the OTP sits in that response. Confirmed against QA, so it is read, not guessed. */
    private static final String OTP_JSON_PATH = "data.otp";

    /**
     * The verify-OTP call the app makes when Verify is pressed, and where its response carries the
     * signed-in user's token - the value the middleware expects in the access_token header.
     */
    private static final String OTP_VERIFY_URL = "/auth/api/v1/construct/otp/verify";
    private static final String JWT_JSON_PATH = "data.jwtToken";
    private static final int OTP_VERIFY_TIMEOUT_SECONDS = 15;
    private static final String INVITATION_SETUP_PASSWORD = "invitationSetupPassword";
    private static final String INVITATION_SETUP_CONFIRM_PASSWORD = "invitationSetupConfirmPassword";
    private static final String INVITATION_SETUP_SUBMIT = "invitationSetupSubmit";

    /**
     * The department the invitee is put in, which follows the roles: Operations Manager when
     * Order & materials management is granted, Finance & Accounting Manager otherwise - including
     * an invitation with no roles at all. See {@link #departmentFor(List)}.
     */
    private static final String ORDER_ROLE = "Order & materials management";
    private static final String OPERATIONS_DEPARTMENT = "Operations Manager";
    private static final String FINANCE_DEPARTMENT = "Finance & Accounting Manager";

    /** Budget for waiting out a transient overlay, and for finding a control at all. */
    private static final int CLICK_WAIT_SECONDS = 10;

    /** Header polling budget: 30 attempts x 500ms. */
    private static final int VISIBILITY_ATTEMPTS = 30;
    private static final long VISIBILITY_INTERVAL_MS = 500;

    /**
     * The invitee for this scenario - name, mobile and email.
     *
     * <p>Injected by Cucumber's PicoContainer, the same instance {@code UserPermissionStep}
     * receives, so a scenario that mixes steps from both classes works on ONE invitee. It is also
     * per-scenario, which a field on this class would not be: step classes are instantiated per
     * scenario but the thread is reused, and a static field would let one scenario inherit the
     * previous invitee and delete a user it never created.
     */
    private final ScenarioContext context;

    public MultiUserSteps(ScenarioContext context) {
        this.context = context;
    }

    /**
     * Set once the leftover autogen users have been cleared in this scenario. Cucumber builds a
     * new step instance per scenario, so this resets itself; it stops a SECOND Add user in the
     * same scenario from deleting the invitee that scenario itself just created.
     */
    private boolean leftoverAutogenUsersCleared;

    /**
     * The accept link found in the invitation email.
     *
     * <p>ThreadLocal because scenarios run concurrently at thread-count &gt; 1 and this is the one
     * piece of invitation state ScenarioContext has no field for. Set when the email is read, so
     * the step that opens it does not have to reopen the mailbox - every extra mailbox visit is
     * another chance for Yopmail to present a CAPTCHA.
     */
    private static final ThreadLocal<String> INVITATION_LINK = new ThreadLocal<>();

    /** Expired invitation loaded from invitation.expired.* for the @invitationExpired scenarios. */
    private String expiredInvitationLink;
    private String expiredInvitationEmail;

    // ------------------------------- Login form -------------------------------

    @And("the primary user enters the email for the {string} account")
    public void the_primary_user_enters_the_email_for_the_account(String alias) {
        String email = UserConfig.getAccountEmail(alias);
        // To the CONSOLE, not just the logger: which account a multi-user run signed in as is the
        // first thing anyone asks when a scenario fails, and the feature names only the alias.
        announce("ACCOUNT", alias + " = " + email);
        openLoginForm();
        waitAndCheckIsElementVisible(LOGIN_EMAIL);
        waitAndSetText(LOGIN_EMAIL, email);
    }

    @And("the primary user clicks on continue")
    public void the_primary_user_clicks_on_continue() {
        waitAndCheckIsElementVisible(CONTINUE_CTA);
        clickWhenReady(CONTINUE_CTA, "Continue");
        log.info("Clicked Continue");
    }

    @And("the primary user enters the password for the {string} account")
    public void the_primary_user_enters_the_password_for_the_account(String alias) {
        log.info("Entering the configured password for account '" + alias + "'");
        waitAndCheckIsElementVisible(LOGIN_PASSWORD);
        waitAndSetText(LOGIN_PASSWORD, UserConfig.getAccountPassword(alias));
    }

    @And("the primary user enters the wrong password for the {string} account")
    public void the_primary_user_enters_the_wrong_password_for_the_account(String alias) {
        log.info("Entering the configured WRONG password for account '" + alias + "'");
        waitAndCheckIsElementVisible(LOGIN_PASSWORD);
        waitAndSetText(LOGIN_PASSWORD, UserConfig.getAccountWrongPassword(alias));
    }

    @Then("the primary user password field should be masked")
    public void the_primary_user_password_field_should_be_masked() {
        waitAndCheckIsElementVisible(LOGIN_PASSWORD);
        WebElement password = Grid.driver().findElement(getBy(LOGIN_PASSWORD));
        softAssert.assertEquals(password.getAttribute("type"), "password",
                "Password field is not masked - the typed value is readable on screen");
    }

    // ------------------------------ Session state ------------------------------

    @And("the primary user should be logged in")
    public void the_primary_user_should_be_logged_in() {
        waitAndCheckIsElementVisible(CONTINUE_CTA);
        clickWhenReady(CONTINUE_CTA, "Continue");
        log.info("Submitted the login form");
        verifyLoggedIn();
        capturePrimaryPaymentReminder();
    }

    @Then("the primary user should still be logged in after a page refresh")
    public void the_primary_user_should_still_be_logged_in_after_a_page_refresh() {
        Grid.driver().navigate().refresh();
        SessionBuilder.applyZoom();
        softAssert.assertTrue(waitForVisible(ACCOUNT_MENU),
                "Primary user was logged out by a page refresh - the session did not persist");
    }

    @Then("the primary user logs out")
    public void the_primary_user_logs_out() {
        SessionBuilder.applyZoom();
        waits(By.xpath(CCP_ACCOUNT_BTN));
        clickInAccountMenu(By.xpath(MENU_LOGOUT), "Logout");
        log.info("Clicked logout");
        the_primary_user_should_be_logged_out();
    }

    @And("the primary user should be logged out")
    public void the_primary_user_should_be_logged_out() {
        softAssert.assertTrue(waitForVisible(HEADER_LOGIN_CTA),
                "Login call-to-action is not shown in the header, so the primary user is not"
                        + " logged out");
        softAssert.assertTrue(waitForHidden(ACCOUNT_MENU),
                "Account menu is still shown in the header after logout");
    }

    // -------------------------- Users & permissions tab --------------------------

    @And("navigate to the User and permissions tab")
    public void navigate_to_the_user_and_permissions_tab() {
        clickWhenReady(By.xpath(CCP_ACCOUNT_BTN), "header Account menu");
        clickWhenReady(UsersPermissionsTab, "Users & permissions tab");
        SessionBuilder.applyZoom();
        if (!waitForVisible(USERS_PERMISSIONS_HEADING)) {
            throw new AssertionError("The Users & permissions tab did not open - the heading is not visible");
        }
        log.info("Users & permissions tab is open");
    }

    /**
     * Takes the alias as a step parameter rather than holding it in a constant, matching every
     * other getAccountEmail call site and UserConfig's stated intent that "a feature file can
     * name the ACCOUNT it needs".
     *
     * <p>The constant it replaced also tripped ci/check-locator-keys.js: that gate treats every
     * identifier-valued String constant in a step class as a locator key, so "primaryUser" was
     * reported as a missing key in multiusersteps.yaml. An account alias is not a locator, and
     * the fix is for it not to live here - not to add a fake entry to the locator file.
     */
    @Then("user should see the Primary user tag on the {string} account")
    public void user_should_see_the_primary_user_tag_on_the_account(String alias) {
        String email = UserConfig.getAccountEmail(alias);
        softAssert.assertTrue(isAnyVisible(byFormatted(USER_EMAIL_ON_PAGE, email)),
                "The primary user '" + email + "' is not listed on the Users & permissions tab");
        softAssert.assertTrue(waitForVisible(PRIMARY_USER_TAG),
                "No 'Primary user' tag is shown on the Users & permissions tab, so the signed-in"
                        + " account '" + email + "' is not labelled as the primary user");
        log.info("Primary user tag verified for " + email);
    }

    @Then("the primary user should not be able to log in with an error message")
    public void the_primary_user_should_not_be_able_to_log_in_with_an_error_message() {
        waitAndCheckIsElementVisible(CONTINUE_CTA);
        clickWhenReady(CONTINUE_CTA, "Continue");
        waitAndCheckIsElementVisible(LOGIN_ERROR_MESSAGE);
        String error = waitAndGetText(LOGIN_ERROR_MESSAGE);
        softAssert.assertTrue(error.contains("Credentials are not valid. Please try again"),
                "Expected the invalid-credentials error, but the page showed: " + error);
        log.info("Login was rejected with an error message, as expected");
    }

    // --------------------------------- Helpers ---------------------------------

    private void openLoginForm() {
        sleep(1000);
        pageRefresh();
        sleep(2000);
        // The refresh loaded a new document, which reset the zoom set during session start.
        SessionBuilder.applyZoom();
        waitAndCheckIsElementVisible(LOGIN_CTA_BUTTON);
        clickWhenReady(LOGIN_CTA_BUTTON, "header Login");
        waitAndCheckIsElementVisible(CONTINUE_WITH_EMAIL_CTA);
        clickWhenReady(CONTINUE_WITH_EMAIL_CTA, "Continue with email");
    }

    private void verifyLoggedIn() {
        softAssert.assertTrue(waitForVisible(ACCOUNT_MENU),
                "Account menu is not shown in the header, so the primary user is not logged in");
        softAssert.assertTrue(waitForHidden(HEADER_LOGIN_CTA),
                "Login call-to-action is still shown in the header after a successful login");
    }

    /**
     * Reads "Payments due now" from the primary user's payment reminder popup, if one opened, and
     * closes it. The popup is optional - a primary user with nothing due gets none - so its
     * absence is logged, not failed. A value is only overwritten by a later login that shows one,
     * so a second primary login without the popup keeps the amount for the invitee's check.
     */
    private void capturePrimaryPaymentReminder() {
        if (!waitForVisible(PAYMENT_REMINDER_HEADING, PAYMENT_REMINDER_ATTEMPTS)) {
            log.info("No payment reminder popup for the primary user");
            return;
        }
        context.primaryPaymentDueNow = readPaymentDueNow();
        log.info("Primary user's payment reminder: Payments due now = '" + context.primaryPaymentDueNow + "'");
        closePaymentReminder();
    }

    /**
     * The additional user acts on the primary user's account, so they must be shown the same
     * amount due. Soft assertions throughout, and the popup is closed whenever it is open, so a
     * mismatch is reported without blocking the rest of the scenario.
     */
    private void verifyInviteeSeesPrimaryPaymentReminder() {
        boolean shown = waitForVisible(PAYMENT_REMINDER_HEADING, PAYMENT_REMINDER_ATTEMPTS);
        if (context.primaryPaymentDueNow == null) {
            log.info("The primary user had no payment reminder, so there is no amount to compare"
                    + (shown ? " - closing the additional user's popup" : ""));
            if (shown) {
                closePaymentReminder();
            }
            return;
        }
        softAssert.assertTrue(shown, "The primary user was shown a payment reminder of '"
                + context.primaryPaymentDueNow + "' but the additional user was not shown one after"
                + " signing in. Current URL: " + currentUrl());
        if (!shown) {
            return;
        }
        String inviteeAmount = readPaymentDueNow();
        softAssert.assertEquals(inviteeAmount, context.primaryPaymentDueNow,
                "The additional user's 'Payments due now' does not match the primary user's");
        log.info("Additional user's payment reminder: Payments due now = '" + inviteeAmount
                + "' (primary user: '" + context.primaryPaymentDueNow + "')");
        closePaymentReminder();
    }

    private String readPaymentDueNow() {
        if (!waitForVisible(PAYMENT_DUE_NOW_VALUE)) {
            softAssert.fail("The payment reminder popup is open but shows no 'Payments due now' value");
            return null;
        }
        return Grid.driver().findElement(getBy(PAYMENT_DUE_NOW_VALUE)).getText()
                .replaceAll("\\s+", " ").trim();
    }

    private void closePaymentReminder() {
        clickWhenReady(PAYMENT_REMINDER_CLOSE_BUTTON, "payment reminder close");
        softAssert.assertTrue(waitForHidden(PAYMENT_REMINDER_HEADING),
                "The payment reminder popup is still open after clicking its close button");
    }

    /** Polls until nothing matching the key is displayed; returns at once when it already is not. */
    private boolean waitForHidden(String key) {
        By locator = getBy(key);
        for (int attempt = 0; attempt < VISIBILITY_ATTEMPTS; attempt++) {
            if (!isVisibleNow(locator)) {
                return true;
            }
            try {
                Thread.sleep(VISIBILITY_INTERVAL_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return false;
    }

    private boolean waitForVisible(String key) {
        return waitForVisible(key, VISIBILITY_ATTEMPTS);
    }

    /**
     * Whether anything matching the locator is displayed RIGHT NOW, without waiting.
     *
     * <p>The polling helpers above must not use {@code CustomUtil.isAnyVisible}: that waits up to
     * 15s for a match, and the session's 10s implicit wait stalls every {@code findElements} that
     * matches nothing - so each "is it gone yet?" poll cost 15-25s instead of 500ms. The implicit
     * wait is dropped for this one lookup and restored afterwards.
     */
    private boolean isVisibleNow(By locator) {
        WebDriver driver = Grid.driver();
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        try {
            for (WebElement candidate : driver.findElements(locator)) {
                try {
                    if (candidate.isDisplayed() && candidate.getSize().getWidth() > 0
                            && candidate.getSize().getHeight() > 0) {
                        return true;
                    }
                } catch (RuntimeException ignored) {
                    // stale or detached - try the next match
                }
            }
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(SessionBuilder.IMPLICIT_WAIT);
        }
    }

    private boolean waitForVisible(String key, int attempts) {
        By locator = getBy(key);
        for (int attempt = 0; attempt < attempts; attempt++) {
            if (isVisibleNow(locator)) {
                return true;
            }
            try {
                Thread.sleep(VISIBILITY_INTERVAL_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return false;
    }

    /**
     * The email as it appears inside a user card's {@code data-test-id}.
     *
     * <p>The page builds {@code user-card-<email>} with every '@' and '.' turned into '-', so
     * {@code autogen1789647156095@yopmail.com} becomes
     * {@code user-card-autogen1789647156095-yopmail-com}.
     *
     * <p>The WHOLE address is slugified, not just the part before the '@'. The generated invitees
     * are all @yopmail.com today, so hardcoding the suffix would work and would break silently the
     * first time an invitee comes from anywhere else - a locator that quietly matches nothing, and
     * an empty role list that reads as "the product granted no roles".
     */
    static String userCardTestId(String email) {
        if (email == null) {
            return "";
        }
        return email.trim().toLowerCase().replace('@', '-').replace('.', '-');
    }

    /**
     * One line on the console, in the same style as the scenario markers.
     *
     * <p>System.out rather than {@code log.info}: the logger and the console are different
     * channels, and the scenario block that TestGroupListener prints is built from System.out.
     * Deliberately terse and used sparingly - this suite's console is meant to carry the scenario
     * name, the account it ran as, and the result.
     */
    private static void announce(String label, String value) {
        System.out.println("[" + label + "] " + value);
    }

    /** By for a locator key in multiusersteps.yaml. */
    private By getBy(String key) {
        return By.xpath(getLocator(key));
    }

    /** By for a locator key whose xpath carries a %s placeholder, e.g. an email address. */
    private By byFormatted(String key, Object value) {
        return By.xpath(String.format(getLocator(key), value));
    }

    // =============================================================================
    // Invitations to additional users
    // (features/multiUser/InvitationToAdditionalUsers.feature)
    // =============================================================================

    @Given("the additional user to be invited does not exist in the system")
    public void the_additional_user_to_be_invited_does_not_exist_in_the_system() {
        context.secondaryUserFullName = generateFirstName();
        // generateMobileNumber, NOT generateNotRegisteredMobileNumber: the latter calls the
        // registration-check API, which logs in over REST to get a token. See the note below.
        context.secondaryUserMobileNo = generateMobileNumber();
        context.secondaryUserEmail = generateEmail();
        // The invitee is generated, so this address exists nowhere else - without it on the
        // console there is no way to tell afterwards which mailbox to open or which row was left
        // behind on the Users & permissions tab.
        announce("INVITEE", context.secondaryUserEmail
                + " (" + context.secondaryUserFullName + ", mobile " + context.secondaryUserMobileNo + ")");
    }

    /*
     * WHY THIS STEP MAKES NO API CALLS.
     *
     * It used to do two, both of them REST logins to auth/login for a bearer token, fired the
     * moment the Users & permissions tab finished opening:
     *
     *   generateNotRegisteredMobileNumber() -> isMobileAlreadyRegistered() -> Token.getCCPToken()
     *   hasNoDepartmentsOrPermissions()            -> Token.getCCPToken(a second account)
     *
     * Neither earned its place here:
     *
     *   - The second authenticated as a SEPARATE account kept solely for these lookups, which
     *     was unverified on pre-prod. A 500 from that login failed the scenario for a reason with
     *     nothing to do with invitations, and named an account nobody had configured for this
     *     feature. That account has since been removed from the framework entirely.
     *   - Both asked the server to confirm something the run already guarantees. The email is
     *     CustomUtil.generateEmail(), which is millisecond-timestamped, and the mobile is ten
     *     random digits starting 6-9. A collision needs the same millisecond or one chance in a
     *     billion.
     *   - The product itself is the better check anyway. A duplicate mobile is rejected by the Add
     *     user form with "A user with this mobile number already exists", so the invitation would
     *     fail at "the invitation should be sent to the additional user" - on screen, where the
     *     scenario can see it, rather than via a token call three steps earlier.
     */

    /**
     * Fills and submits the Add user form for the generated invitee.
     *
     * <p>This step always switches on Order & materials management, so the department is always
     * Operations Manager - the one that goes with that role (see {@link #departmentFor(List)}).
     */
    @When("the primary user invites the additional user")
    public void the_primary_user_invites_the_additional_user() {
        requireInvitee();
        waits(getBy(ADD_USER_BUTTON));
        deleteLeftoverAutogenUsers();
        clickWhenReady(ADD_USER_BUTTON, "Add user");
        softAssert.assertTrue(isDisplayed(getBy(ADD_USER_DIALOG_TITLE)),
                "The Add user dialog did not open, so no invitation can be sent");

        enterText(getBy(ADD_USER_NAME), context.secondaryUserFullName);
        enterText(getBy(ADD_USER_MOBILE), context.secondaryUserMobileNo);
        softAssert.assertTrue(isDisplayed(getBy(ADD_USER_EMAIL)),
                "The Add user dialog has no email field, so there is nothing to invite");
        enterText(getBy(ADD_USER_EMAIL), context.secondaryUserEmail);

        By department = byFormatted(DEPARTMENT_OPTION, OPERATIONS_DEPARTMENT);
        waits(getBy(DEPARTMENT_DROPDOWN));
        clickWhenReady(DEPARTMENT_DROPDOWN, "Department dropdown");
        waits(department);
        clickWhenReady(department, "department \"" + OPERATIONS_DEPARTMENT + "\"");
        clickWhenReady(DEPARTMENT_DROPDOWN, "Department dropdown (collapse)");
        clickWhenReady(ORDER_MANAGEMENT_TOGGLE, "Order & materials management toggle");

        log.info("Submitted the invitation for " + context.secondaryUserEmail);
        sleep(2000);
    }

    /**
     * Invites the generated user with EXACTLY the roles listed, and no others.
     *
     * <p>Every one of the three toggles is set explicitly - the ones the table omits are driven
     * OFF rather than left alone. A combination test that only switched on what it wanted would
     * pass while silently inheriting whatever the form defaulted to, which is the one thing these
     * scenarios exist to rule out.
     */
    @When("the primary user invites the additional user with the roles:")
    public void the_primary_user_invites_the_additional_user_with_the_roles(DataTable table) {
        inviteWithRoles(table.asList());
    }

    /**
     * Invites with every role switched off.
     *
     * <p>A step of its own because a Gherkin data table cannot be empty - "no roles" has no table
     * to write. It is the same code path with an empty list, so the two cannot drift.
     */
    @When("the primary user invites the additional user with no roles")
    public void the_primary_user_invites_the_additional_user_with_no_roles() {
        inviteWithRoles(Collections.emptyList());
    }

    private void inviteWithRoles(List<String> wanted) {
        requireInvitee();
        openAddUserFormForInvitee(departmentFor(wanted));

        for (String role : ALL_ROLES) {
            setRoleToggle(role, wanted.contains(role));
        }
        log.info("Inviting " + context.secondaryUserEmail
                + (wanted.isEmpty() ? " with NO roles" : " with roles " + wanted));

        reportAddUserFormState();
        clickWhenReady(ADD_USER_SUBMIT, "Add user (submit)");
        sleep(2000);
    }

    /**
     * With every role switched off, pressing Add user must show the Notifications section's
     * guidance to enable 'Manage orders' or 'Manage finances'.
     *
     * <p>Present and visible are checked separately so a failure says which: a message rendered
     * but hidden is a different defect from one never rendered at all. Soft assertions, so the
     * scenario's later steps still report on what happened next.
     */
    @Then("the Notifications section should ask to enable Manage orders or Manage finances")
    public void the_notifications_section_should_ask_to_enable_manage_orders_or_manage_finances() {
        By message = getBy(ROLES_ERROR_MESSAGE);
        boolean visible = waitForVisible(ROLES_ERROR_MESSAGE);
        boolean present = visible || !Grid.driver().findElements(message).isEmpty();
        softAssert.assertTrue(present, "With no roles selected, Add user did not show \"Enable at"
                + " least one from 'Manage orders' or 'Manage finances'\" in the Notifications"
                + " section - the message is not on the page. Current URL: " + currentUrl());
        softAssert.assertTrue(visible, "The \"Enable at least one from 'Manage orders' or 'Manage"
                + " finances'\" message is on the page but not visible in the Notifications section.");
        if (visible) {
            log.info("Notifications section shows: Enable at least one from 'Manage orders' or"
                    + " 'Manage finances'");
        }
    }

    /**
     * Without Order & materials management the PDP explains why Add to cart is disabled, in an
     * alert. Present and wording are checked separately, so a failure says whether the alert is
     * missing or says something else; the text is compared with its whitespace normalised. Soft
     * assertions, so the scenario still reports what comes after.
     */
    @Then("the PDP should show the no permission to add to cart alert")
    public void the_pdp_should_show_the_no_permission_to_add_to_cart_alert() {
        boolean present = waitForVisible(PERMISSION_ALERT);
        softAssert.assertTrue(present, "No alert (//div[@role='alert']) is shown on the PDP, so the"
                + " additional user is not told why Add to cart is disabled. Current URL: " + currentUrl());
        if (!present) {
            return;
        }
        String actual = isVisibleNow(getBy(PERMISSION_ALERT_TEXT))
                ? Grid.driver().findElement(getBy(PERMISSION_ALERT_TEXT)).getText()
                : null;
        String normalised = actual == null ? null : actual.replaceAll("\\s+", " ").trim();
        softAssert.assertEquals(normalised, NO_CART_PERMISSION_MESSAGE,
                "The PDP alert does not carry the no-permission message for Add to cart");
        log.info("PDP alert: '" + normalised + "'");
    }

    /**
     * A no-roles Add user is refused: the dialog stays open and nobody is added to the list.
     *
     * <p>"Stays open" is watched for a few seconds rather than checked once, so a dialog that
     * closes a moment after the validation message renders - i.e. a submit that went through -
     * still fails. The list check is by the invitee's mobile, which exists nowhere else because it
     * was generated for this scenario.
     */
    @Then("the Add user dialog should stay open and the additional user should not be added")
    public void the_add_user_dialog_should_stay_open_and_the_additional_user_should_not_be_added() {
        requireInvitee();
        boolean stayedOpen = isVisibleNow(getBy(ADD_USER_DIALOG_TITLE));
        long until = System.currentTimeMillis() + 3000;
        while (stayedOpen && System.currentTimeMillis() < until) {
            sleep(500);
            stayedOpen = isVisibleNow(getBy(ADD_USER_DIALOG_TITLE));
        }
        softAssert.assertTrue(stayedOpen, "The Add user dialog closed after submitting with no roles,"
                + " so the form accepted an invitation it should have refused");
        softAssert.assertTrue(isVisibleNow(getBy(ADD_USER_SUBMIT)),
                "The Add user dialog no longer shows its Add user button after the refused submit");

        By invitee = byFormatted(VIEW_EDIT_FOR_MOBILE, context.secondaryUserMobileNo);
        softAssert.assertFalse(isVisibleNow(invitee), "A user with the invitee's mobile "
                + context.secondaryUserMobileNo + " is listed on the Users & permissions tab, so the"
                + " no-roles invitation was created despite the validation message");
        announce("INVITEE", "no-roles invitation refused for " + context.secondaryUserEmail
                + " - dialog " + (stayedOpen ? "stayed open" : "CLOSED"));
    }

    /**
     * The invited user must carry no roles at all.
     *
     * <p>Distinct from "no active permissions yet", which is about an invitation not having been
     * accepted. This one is about what the primary user CHOSE: nothing. If the row shows a role
     * here, the form granted one that nobody asked for.
     */
    @Then("the additional user should be assigned no roles")
    public void the_additional_user_should_be_assigned_no_roles() {
        requireInvitee();
        List<String> granted = rolesShownForInvitee();
        softAssert.assertTrue(granted.isEmpty(),
                "The additional user was invited with every role switched off, but the row shows: "
                        + granted + ". A role nobody selected has been granted.");
    }

    /**
     * The invited user's roles must be exactly the set requested.
     *
     * <p>Set equality, not "contains": an extra role granted at invitation time is a privilege the
     * primary user did not ask for, and an assertion that only checked the wanted ones were
     * present would not see it.
     */
    @Then("the additional user should be assigned exactly the roles:")
    public void the_additional_user_should_be_assigned_exactly_the_roles(DataTable table) {
        requireInvitee();
        Set<String> expected = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        expected.addAll(table.asList());

        Set<String> actual = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        actual.addAll(rolesShownForInvitee());

        softAssert.assertEquals(actual.toString(), expected.toString(),
                "The roles on the invited user's row do not match the ones chosen at invitation."
                        + " Expected " + expected + " but the row shows " + actual + ".");
        log.info("Roles for " + context.secondaryUserFullName + ": " + actual);
    }

    /** The non-empty role chips shown on the invitee's row. */
    private List<String> rolesShownForInvitee() {
        return Grid.driver().findElements(
                        byFormatted(PERMISSIONS_FOR_EMAIL, userCardTestId(context.secondaryUserEmail)))
                .stream()
                .map(WebElement::getText)
                .map(text -> text == null ? "" : text.trim())
                .filter(text -> !text.isEmpty())
                .collect(Collectors.toList());
    }

    /**
     * Deletes every user whose email contains "autogen" - the invitees earlier runs generated -
     * from the Users & permissions tab, whatever their status (Invited, Active or Expired).
     *
     * <p>Runs at the start of a scenario's FIRST Add user, not in an After hook, on purpose: the
     * previous run's invitees stay on the account until the next run starts, so they can be
     * checked by hand in between. Once per scenario (see {@link #leftoverAutogenUsersCleared}), so
     * a later Add user in the same scenario cannot delete that scenario's own invitee.
     *
     * <p>One user at a time: open its View/Edit, Remove user, confirm, then wait for the count of
     * autogen cards to drop by one before the next. The configured expired-invitation address is
     * skipped, because that scenario relies on it still being listed.
     *
     * <p>BEST EFFORT, NEVER PART OF THE RESULT. This is housekeeping, not the behaviour under
     * test, so nothing here asserts or throws: a user that cannot be deleted, a button that never
     * appears or a count that does not drop is logged as a warning, the page is put back on the
     * Users & permissions tab, and the scenario carries on to its own Add user exactly as if the
     * cleanup had not run. A card that failed is skipped rather than retried, and the cleanup gives
     * up after {@link #CLEANUP_MAX_CONSECUTIVE_FAILURES} failures in a row, so a broken delete
     * costs the run a bounded amount of time.
     */
    private void deleteLeftoverAutogenUsers() {
        if (leftoverAutogenUsersCleared) {
            return;
        }
        leftoverAutogenUsersCleared = true;
        try {
            deleteLeftoverAutogenUsersBestEffort();
        } catch (RuntimeException | AssertionError e) {
            log.warning("Cleanup of leftover autogen users stopped early (" + e.getClass().getSimpleName()
                    + ": " + e.getMessage() + ") - continuing with the scenario.");
            restoreUsersTabAfterCleanup();
        }
    }

    private void deleteLeftoverAutogenUsersBestEffort() {
        // The cards load after the tab: give the list a moment to render before counting it, so
        // "none found" means none rather than "not loaded yet". An account with no users at all
        // costs this wait once.
        waitForVisible(ANY_USER_CARD_EDIT_BUTTON, USER_CARDS_LOAD_ATTEMPTS);
        String keep = protectedAutogenCardTestId();

        List<WebElement> leftovers = autogenEditButtons(keep);
        if (leftovers.isEmpty()) {
            log.info("No autogen users left by earlier runs");
            return;
        }
        int total = leftovers.size();
        announce("CLEANUP", total + " autogen user(s) left by earlier runs - deleting them");

        Set<String> skipped = new TreeSet<>();
        int deleted = 0;
        int consecutiveFailures = 0;
        while (true) {
            WebElement edit = firstNotSkipped(leftovers, skipped);
            if (edit == null) {
                break;
            }
            int before = leftovers.size();
            String card = testIdOf(edit);
            try {
                clickWhenReady(edit, "View/Edit for " + card);
                clickWhenReady(USER_MODAL_REMOVE_USER_BUTTON, "Remove user");
                clickWhenReady(DELETE_USER_CONFIRM_BUTTON, "Yes, delete");
                leftovers = awaitAutogenCountBelow(before, keep);
            } catch (RuntimeException | AssertionError e) {
                log.warning("Could not delete leftover user " + card + " (" + e.getClass().getSimpleName()
                        + ": " + e.getMessage() + ") - skipping it.");
                leftovers = null;
            }

            if (leftovers != null && leftovers.size() < before) {
                deleted++;
                consecutiveFailures = 0;
                log.info("Deleted leftover user " + card + " (" + leftovers.size() + " left)");
                continue;
            }

            if (leftovers != null) {
                log.warning("Deleting leftover user " + card + " did not reduce the autogen user"
                        + " count (still " + leftovers.size() + ") - skipping it.");
            }
            skipped.add(card);
            restoreUsersTabAfterCleanup();
            if (++consecutiveFailures >= CLEANUP_MAX_CONSECUTIVE_FAILURES) {
                log.warning("Giving up on the cleanup after " + consecutiveFailures
                        + " failed deletes in a row.");
                break;
            }
            leftovers = autogenEditButtons(keep);
        }
        announce("CLEANUP", "deleted " + deleted + " of " + total + " autogen user(s)"
                + (skipped.isEmpty() ? "" : "; could not delete " + skipped));
    }

    private static WebElement firstNotSkipped(List<WebElement> buttons, Set<String> skipped) {
        for (WebElement button : buttons) {
            if (!skipped.contains(testIdOf(button))) {
                return button;
            }
        }
        return null;
    }

    private static String testIdOf(WebElement button) {
        try {
            return String.valueOf(button.getAttribute("data-test-id"));
        } catch (RuntimeException e) {
            return "(unreadable card)";
        }
    }

    /**
     * Puts the page back where the scenario expects it after a failed delete: a refresh closes any
     * modal left open (the URL keeps ?tab=users-permissions), and if Add user is still not on
     * screen the tab is reopened. Never throws - if even this fails, the scenario's own Add user
     * step reports the real state of the page.
     */
    private void restoreUsersTabAfterCleanup() {
        try {
            pageRefresh();
            SessionBuilder.applyZoom();
            if (!waitForVisible(ADD_USER_BUTTON, USER_CARDS_LOAD_ATTEMPTS)) {
                navigate_to_the_user_and_permissions_tab();
            }
        } catch (RuntimeException | AssertionError e) {
            log.warning("Could not restore the Users & permissions tab after the cleanup: "
                    + e.getMessage());
        }
    }

    /** Displayed autogen View/Edit buttons, minus the protected card. */
    private List<WebElement> autogenEditButtons(String keepTestIdSuffix) {
        WebDriver driver = Grid.driver();
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        try {
            return driver.findElements(getBy(AUTOGEN_USER_CARD_EDIT_BUTTON)).stream()
                    .filter(button -> {
                        try {
                            if (!button.isDisplayed()) {
                                return false;
                            }
                            String id = button.getAttribute("data-test-id");
                            return keepTestIdSuffix == null || id == null
                                    || !id.endsWith(keepTestIdSuffix);
                        } catch (RuntimeException e) {
                            return false;      // stale - re-read on the next count
                        }
                    })
                    .collect(Collectors.toList());
        } finally {
            driver.manage().timeouts().implicitlyWait(SessionBuilder.IMPLICIT_WAIT);
        }
    }

    /**
     * Polls until fewer than {@code before} autogen cards are listed. If the list has not updated
     * in time, refreshes once and counts again - a delete the server applied but the list did not
     * re-render is still a delete.
     */
    private List<WebElement> awaitAutogenCountBelow(int before, String keep) {
        long deadline = System.currentTimeMillis() + CLEANUP_DELETE_WAIT_SECONDS * 1000L;
        List<WebElement> now = autogenEditButtons(keep);
        while (now.size() >= before && System.currentTimeMillis() < deadline) {
            sleep(500);
            now = autogenEditButtons(keep);
        }
        if (now.size() >= before) {
            pageRefresh();
            SessionBuilder.applyZoom();
            waitForVisible(ANY_USER_CARD_EDIT_BUTTON, USER_CARDS_LOAD_ATTEMPTS);
            now = autogenEditButtons(keep);
        }
        return now;
    }

    /** Card id suffix of the configured expired-invitation address, or null when none is set. */
    private static String protectedAutogenCardTestId() {
        String email = ConfigUtil.getSecretValue("invitation.expired.email");
        return (email == null || email.trim().isEmpty()) ? null : userCardTestId(email);
    }

    /** Operations Manager when the roles include Order & materials management, else Finance & Accounting Manager. */
    static String departmentFor(List<String> roles) {
        boolean order = roles != null && roles.stream()
                .anyMatch(role -> role != null && role.trim().equalsIgnoreCase(ORDER_ROLE));
        return order ? OPERATIONS_DEPARTMENT : FINANCE_DEPARTMENT;
    }

    /** Opens Add user and fills the identity fields and department for the generated invitee. */
    private void openAddUserFormForInvitee(String department) {
        waits(getBy(ADD_USER_BUTTON));
        deleteLeftoverAutogenUsers();
        clickWhenReady(ADD_USER_BUTTON, "Add user");
        softAssert.assertTrue(isDisplayed(getBy(ADD_USER_DIALOG_TITLE)),
                "The Add user dialog did not open, so no invitation can be sent");

        enterText(getBy(ADD_USER_NAME), context.secondaryUserFullName);
        enterText(getBy(ADD_USER_MOBILE), context.secondaryUserMobileNo);
        softAssert.assertTrue(isDisplayed(getBy(ADD_USER_EMAIL)),
                "The Add user dialog has no email field, so there is nothing to invite");
        enterText(getBy(ADD_USER_EMAIL), context.secondaryUserEmail);

        selectDepartment(department);
    }

    /**
     * Picks a department, scoping the option to the popover the trigger actually owns.
     *
     * <p>WHY THE SCOPING MATTERS. The plain {@code //span[text()='<department>']/preceding-sibling::button}
     * is not unique on this page: a selected department also renders as a CHIP under a trigger, so
     * with any existing user listed the click landed on a chip's button instead of an option in the
     * list. Nothing failed at the time - the department simply stayed "Select", the Add user submit
     * stayed {@code disabled}, and the scenario died 30 lines later on a bare
     * NullPointerException from Sauron's click. The 18 Sep run is exactly that.
     *
     * <p>{@code aria-controls} on the trigger names the popover's element id, which is generated
     * per render, so it is read at runtime rather than written into the locator file.
     *
     * <p>The selection is then VERIFIED. A department that silently fails to take is the whole
     * cause of that NPE, and one assertion here turns it into a sentence.
     */
    private void selectDepartment(String department) {
        clickWhenReady(DEPARTMENT_DROPDOWN, "Department dropdown");

        String popoverId = presence(getBy(DEPARTMENT_DROPDOWN), "Department dropdown")
                .getAttribute("aria-controls");
        By option;
        if (popoverId == null || popoverId.isBlank()) {
            log.warning("The department trigger exposes no aria-controls, so the option cannot be"
                    + " scoped to its popover - falling back to a page-wide match, which can hit a"
                    + " selected-department chip instead of an option.");
            option = byFormatted(DEPARTMENT_OPTION, department);
        } else {
            option = By.xpath("//*[@id='" + popoverId + "']//span[text()='" + department
                    + "']/preceding-sibling::button");
        }

        clickWhenReady(option, "department \"" + department + "\"");
        closeDepartmentDropdown();

        // Asserted on the CHIP, not the trigger: see selectedDepartmentChip in the locator file.
        softAssert.assertTrue(isAnyVisible(byFormatted(SELECTED_DEPARTMENT_CHIP, department)),
                "No chip for \"" + department + "\" appeared under the Department field, so the"
                        + " selection did not take. The Add user submit stays disabled while the"
                        + " form is incomplete.");
        log.info("Department selected: " + department);
    }

    /**
     * Closes the department popover with ESCAPE, and proves it closed.
     *
     * <p>The popover staying open after a pick is CORRECT - it is a multi-select, so it waits for
     * you to choose more. It just has to be dismissed before anything underneath can be touched.
     *
     * <p>ESCAPE rather than a click, for three reasons:
     *
     * <ul>
     *   <li>It is the dismissal the component implements. Radix closes the topmost dismissable
     *       layer on Escape, so the popover goes and the Add user dialog beneath it stays.</li>
     *   <li>It cannot activate anything. Closing by clicking the Add user submit worked only
     *       because Radix dismisses on any OUTSIDE click - and the moment the form became valid
     *       that same click would submit the invitation early, before the role toggles were set.
     *       A dismissal that doubles as a submit is a bug waiting for the form to pass
     *       validation.</li>
     *   <li>It needs no coordinates, so nothing can be intercepted by the popover itself.</li>
     * </ul>
     *
     * <p>Falls back to clicking the trigger - the other legitimate toggle - and fails loudly if
     * neither works, rather than leaving an open popover over the rest of the form.
     */
    private void closeDepartmentDropdown() {
        if (!isDropdownExpanded()) {
            return;
        }
        new Actions(Grid.driver()).sendKeys(Keys.ESCAPE).perform();
        if (!awaitDropdownClosed()) {
            log.info("Escape did not close the department popover - toggling it with the trigger");
            clickWhenReady(DEPARTMENT_DROPDOWN, "Department dropdown (collapse)");
            if (!awaitDropdownClosed()) {
                throw new AssertionError("The department popover is still open after Escape and"
                        + " after clicking its trigger. Everything below it on the Add user form is"
                        + " unreachable while it is up.");
            }
        }
        // Escape closes the TOPMOST layer. If the popover was already closed when it was pressed,
        // the Add user dialog is the topmost one and takes the Escape instead - which would look
        // like a locator failure several steps later.
        softAssert.assertTrue(isDisplayed(getBy(ADD_USER_DIALOG_TITLE)),
                "Closing the department popover also closed the Add user dialog, so the invitation"
                        + " cannot be completed.");
    }

    /** True while the department popover is showing, read from the trigger's own ARIA state. */
    private boolean isDropdownExpanded() {
        try {
            WebElement trigger = Grid.driver().findElement(getBy(DEPARTMENT_DROPDOWN));
            return "true".equalsIgnoreCase(trigger.getAttribute("aria-expanded"))
                    || "open".equalsIgnoreCase(trigger.getAttribute("data-state"));
        } catch (Exception e) {
            return false;
        }
    }

    private boolean awaitDropdownClosed() {
        long deadline = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < deadline) {
            if (!isDropdownExpanded()) {
                return true;
            }
            sleep(200);
        }
        return false;
    }

    /**
     * Prints the Add user form's state to the console.
     *
     * <p>Called immediately before the submit click because that click is where this scenario
     * keeps dying, and the failure it produces - a message-less NullPointerException from inside
     * Sauron - says nothing about which field left the form invalid. The page source cannot
     * answer it either: these are React controlled inputs, so a typed value lives in the DOM
     * PROPERTY and never appears in the serialised HTML that FailureDiagnostics captures.
     */
    private void reportAddUserFormState() {
        try {
            Object state = ((JavascriptExecutor) Grid.driver()).executeScript(
                    "function v(id){var e=document.getElementById(id);return e?(e.value||'(empty)'):'(absent)';}"
                  + "var chips=[].map.call("
                  + "  document.querySelectorAll('[data-test-id=\"user-form-remove-department-button\"]'),"
                  + "  function(b){return b.previousElementSibling ? b.previousElementSibling.textContent : '?';});"
                  + "var toggles=[].map.call(document.querySelectorAll('p'), function(p){return p;})"
                  + "  .filter(function(p){return ['Order & materials management','Finance & account management',"
                  + "     'Promotional notifications'].indexOf(p.textContent.trim())>-1;})"
                  + "  .map(function(p){var b=p.parentElement.nextElementSibling;"
                  + "     return p.textContent.trim()+'='+(b?b.getAttribute('aria-checked'):'?');});"
                  + "var submits=[].map.call(document.querySelectorAll('button'), function(b){return b;})"
                  + "  .filter(function(b){return b.textContent.trim()==='Add user';})"
                  + "  .map(function(b){return (b.getAttribute('data-test-id')||'?')+(b.disabled?':DISABLED':':enabled');});"
                  + "return 'name='+v('add-user-name')+' | mobile='+v('add-user-mobile')"
                  + "  +' | email='+v('add-user-email')+' | departments='+JSON.stringify(chips)"
                  + "  +' | roles='+JSON.stringify(toggles)+' | submit='+JSON.stringify(submits);");
            announce("ADD USER FORM", String.valueOf(state));
        } catch (Exception e) {
            log.warning("Could not read the Add user form state: " + e.getMessage());
        }
    }

    /**
     * Drives one Roles &amp; permissions toggle to the requested state.
     *
     * <p>Reads the current state first and clicks only when it differs. The existing add-user
     * steps click unconditionally, which is safe for one toggle from a known-off form but not for
     * combinations: a department that pre-enables a role would be switched OFF by a blind click,
     * and the scenario would then assert the opposite of what it asked for.
     */
    private void setRoleToggle(String role, boolean on) {
        By toggle = byFormatted(ROLE_TOGGLE, role);
        if (!isAnyVisible(toggle)) {
            softAssert.assertTrue(false, "The '" + role + "' role toggle is not on the Add user"
                    + " form, so this combination cannot be set up");
            return;
        }
        WebElement element = Grid.driver().findElement(toggle);
        Boolean current = toggleState(element);
        if (current == null) {
            // Neither aria-checked nor data-state is exposed, so state cannot be read. Clicking
            // blind here is how a combination silently becomes its own inverse - say so instead.
            softAssert.assertTrue(false, "Cannot read the on/off state of the '" + role + "'"
                    + " toggle: it exposes neither aria-checked nor data-state. Update"
                    + " MultiUserSteps.toggleState with the attribute this control actually uses.");
            return;
        }
        if (current != on) {
            clickWhenReady(toggle, "'" + role + "' role toggle");
            log.info("Turned " + (on ? "ON" : "OFF") + " the '" + role + "' role");
        }
    }

    /** True/false for a switch, or null when the control exposes no readable state. */
    private Boolean toggleState(WebElement element) {
        String aria = element.getAttribute("aria-checked");
        if (aria != null && !aria.isBlank()) {
            return Boolean.parseBoolean(aria);
        }
        String state = element.getAttribute("data-state");
        if (state != null && !state.isBlank()) {
            return "checked".equalsIgnoreCase(state) || "on".equalsIgnoreCase(state);
        }
        return null;
    }

    @Then("the invitation should be sent to the additional user")
    public void the_invitation_should_be_sent_to_the_additional_user() {
        requireInvitee();
        softAssert.assertTrue(
                isAnyVisible(byFormatted(USER_CARD_FOR_EMAIL, userCardTestId(context.secondaryUserEmail))),
                "The invited user '" + context.secondaryUserEmail + "' has no card on the"
                        + " Users & permissions tab, so the invitation was not accepted by the form");
    }

    /**
     * The status badge must say what the feature claims, not merely exist.
     *
     * <p>Parameterised because the wording is the product's to choose: UserPermissionStep only
     * ever asserted that this element was displayed, so nothing in this repo records whether it
     * reads "Invitation sent", "Pending" or "Invited". A wrong guess is a one-word Gherkin edit.
     */
    @Then("the additional user should be listed with the {string} status")
    public void the_additional_user_should_be_listed_with_the_status(String expected) {
        requireInvitee();
        By status = byFormatted(STATUS_FOR_USER, context.secondaryUserFullName);
        if (!isAnyVisible(status)) {
            softAssert.assertTrue(false, "No status badge is shown for the invited user '"
                    + context.secondaryUserFullName + "', so '" + expected + "' cannot be verified");
            return;
        }
        String actual = getText(status);
        softAssert.assertTrue(actual != null && actual.toLowerCase().contains(expected.toLowerCase()),
                "Expected the invited user's status to read '" + expected + "' but it reads '"
                        + actual + "'");
        log.info("Invitation status for " + context.secondaryUserFullName + ": " + actual);
    }

    /**
     * An invitation defers account creation, so the invitee must not be active yet.
     *
     * <p>Asserted from the invitee's row on the Users & permissions tab: until the invitation is
     * accepted the row must not read Active. Not by attempting a UI login - that would navigate the
     * browser away from this tab, and every step after it, including the cleanup that deletes this
     * user, would fail on a page it was never looking at. And not through fetch-user: that needs a
     * REST login to auth/login as the configured user, which pre-prod rejects with a 500 and which
     * failed the scenario for a reason unrelated to invitations.
     */
    @Then("the additional user should not be able to sign in until the invitation is accepted")
    public void the_additional_user_should_not_be_able_to_sign_in_yet() {
        requireInvitee();
        By status = byFormatted(STATUS_FOR_USER, context.secondaryUserFullName);
        if (!isAnyVisible(status)) {
            softAssert.fail("No status badge is shown for the invited user '"
                    + context.secondaryUserFullName + "', so it cannot be confirmed that they are"
                    + " not yet able to sign in");
            return;
        }
        String actual = getText(status);
        boolean active = actual != null && actual.trim().toLowerCase().matches("(.*\\W)?active(\\W.*)?");
        softAssert.assertFalse(active, "The invited user '" + context.secondaryUserFullName
                + "' is already listed as '" + actual + "' before accepting the invitation, so the"
                + " invitation activated the account instead of waiting to be accepted");
        log.info("Invited user is not active yet - status reads '" + actual + "'");
    }

    @Then("the additional user should have no active permissions yet")
    public void the_additional_user_should_have_no_active_permissions_yet() {
        requireInvitee();
        List<String> granted = rolesShownForInvitee();
        softAssert.assertTrue(granted.isEmpty(),
                "The invited user's invitation is still pending, but these permissions are already"
                        + " shown as active: " + granted);
    }

    @Then("the primary user deletes the additional user")
    public void the_primary_user_deletes_the_additional_user() {
        requireInvitee();
        By viewEdit = byFormatted(VIEW_EDIT_FOR_MOBILE, context.secondaryUserMobileNo);
        waits(viewEdit);
        clickWhenReady(viewEdit, "view/edit invited user");
        waits(getBy(DELETE_USER_BUTTON));
        clickWhenReady(DELETE_USER_BUTTON, "Delete user");
        waits(getBy(CONFIRM_DELETE_BUTTON));
        clickWhenReady(CONFIRM_DELETE_BUTTON, "Yes, delete");
        pageRefresh();
        SessionBuilder.applyZoom();
        sleep(1000);
        softAssert.assertFalse(isDisplayed(viewEdit),
                "The invited user " + context.secondaryUserMobileNo + " is still listed after"
                        + " being deleted, so this account is left dirty for the next run");
        log.info("Cleaned up the invited user " + context.secondaryUserEmail);
    }

    // ------------------------------- Invitation email -------------------------------

    @Then("the additional user should receive the invitation email within {int} minutes")
    public void the_additional_user_should_receive_the_invitation_email_within_minutes(int minutes) {
        requireInvitee();
        boolean delivered = YopmailInbox.waitForMail(
                context.secondaryUserEmail, Duration.ofMinutes(minutes));
        softAssert.assertTrue(delivered,
                "No invitation email reached " + context.secondaryUserEmail + " within " + minutes
                        + " minute(s). Check the failure screenshot for a Yopmail CAPTCHA before"
                        + " reporting this as a missing notification.");
    }

    @Then("the invitation email should name the inviting company")
    public void the_invitation_email_should_name_the_inviting_company() {
        requireInvitee();
        String body = YopmailInbox.latestMailText(context.secondaryUserEmail);
        // "JSW" rather than the customer's own company name: the account's registered name is not
        // known to this scenario, and an invitation that does not identify its sender at all is
        // the failure actually worth catching.
        softAssert.assertTrue(body != null && body.toUpperCase().contains("JSW"),
                "The invitation email does not identify the sender - no 'JSW' anywhere in the"
                        + " message body, so the recipient cannot tell who invited them");
    }

    /**
     * The mail must carry the link the next step opens, so the href is captured here rather than
     * read twice - opening a second copy of the mailbox would be a second chance for Yopmail to
     * show a CAPTCHA.
     */
    @Then("the invitation email should carry a link to accept the invitation")
    public void the_invitation_email_should_carry_a_link_to_accept_the_invitation() {
        requireInvitee();
        String link = YopmailInbox.latestMailFirstLink(context.secondaryUserEmail);
        INVITATION_LINK.set(link);
        softAssert.assertTrue(link != null,
                "The invitation email to " + context.secondaryUserEmail + " contains no link to"
                        + " accept the invitation, so the recipient has no way to act on it");
    }

    @When("the additional user opens the invitation link from the email")
    public void the_additional_user_opens_the_invitation_link_from_the_email() {
        String link = INVITATION_LINK.get();
        if (link == null) {
            link = YopmailInbox.latestMailFirstLink(requireInvitee().secondaryUserEmail);
        }
        if (link == null) {
            throw new AssertionError("There is no invitation link to open: none was found in the"
                    + " email to " + context.secondaryUserEmail + ".");
        }
        log.info("Opening the invitation link");
        Grid.driver().get(link);
        SessionBuilder.applyZoom();
    }

    @Then("the additional user should be asked to set up their account")
    public void the_additional_user_should_be_asked_to_set_up_their_account() {
        softAssert.assertTrue(waitForVisible(INVITATION_SETUP_HEADING),
                "The invitation link did not open an account-setup screen. Current URL: "
                        + currentUrl());
    }

    @Then("the account setup form should be prefilled with the invited email address")
    public void the_account_setup_form_should_be_prefilled_with_the_invited_email_address() {
        requireInvitee();
        if (!isAnyVisible(getBy(INVITATION_SETUP_EMAIL_FIELD))) {
            softAssert.assertTrue(false, "The account-setup form has no email field, so it cannot"
                    + " be prefilled with " + context.secondaryUserEmail);
            return;
        }
        String value = Grid.driver().findElement(getBy(INVITATION_SETUP_EMAIL_FIELD))
                .getAttribute("value");
        softAssert.assertEquals(value, context.secondaryUserEmail,
                "The account-setup form is prefilled with the wrong address - an invitee who can"
                        + " change this field could claim a different account");
    }

    // =============================================================================
    // Accepting the invitation as the invited user, through Yopmail
    // =============================================================================

    /**
     * Discards the primary user's window and opens Yopmail in a clean one.
     *
     * <p>A new window is opened and the old one closed, rather than simply navigating away.
     * Navigating keeps the primary user's cookies and localStorage, and the acceptance flow that
     * follows would then run inside a session belonging to somebody else - which is not what an
     * invitee's browser looks like and can authenticate the wrong user.
     *
     * <p>The driver itself must survive, so the order is open-then-close: closing the only window
     * first would end the session and every step after this would report "no such window".
     */
    @And("the invited user closes the primary user window and opens Yopmail")
    public void the_invited_user_closes_the_primary_user_window_and_opens_yopmail() {
        requireInvitee();
        WebDriver driver = Grid.driver();
        String primaryWindow = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.WINDOW);
        String invitee = driver.getWindowHandle();
        try {
            driver.switchTo().window(primaryWindow);
            driver.close();
        } catch (Exception e) {
            log.warning("Could not close the primary user window: " + e.getMessage());
        }
        driver.switchTo().window(invitee);

        /* MAXIMIZE. A window opened with newWindow() gets Chrome's default size, not the size
         * SessionBuilder maximised at session start - that applied to the window just closed. The
         * short viewport is not cosmetic here: the accept-invite screens put their call-to-action
         * at the foot of a `flex-1 overflow-y-auto` container, and the shorter the window the
         * further outside that container's visible region the button's centre point sits, which is
         * exactly what made Verify report "element click intercepted". */
        try {
            driver.manage().window().maximize();
        } catch (Exception e) {
            log.warning("Could not maximize the invited user's window: " + e.getMessage());
        }

        YopmailInbox.openSite();
        SessionBuilder.applyZoom();
        announce("INVITEE", "opened Yopmail for " + context.secondaryUserEmail);
    }

    @And("the invited user opens the Yopmail inbox for the invited address")
    public void the_invited_user_opens_the_yopmail_inbox() {
        requireInvitee();
        YopmailInbox.openInboxByTyping(context.secondaryUserEmail);
    }

    @And("the invited user refreshes the Yopmail inbox")
    public void the_invited_user_refreshes_the_yopmail_inbox() {
        YopmailInbox.refreshInbox();
        softAssert.assertTrue(YopmailInbox.messageCount() > 0,
                "The Yopmail inbox for " + context.secondaryUserEmail + " is still empty after a"
                        + " refresh, so the invitation email has not arrived. Check the failure"
                        + " screenshot for a CAPTCHA before reporting a missing notification.");
    }

    /**
     * Opens the newest message and leaves the driver inside Yopmail's reading frame.
     *
     * <p>Everything located by the next two steps lives in that frame, not in the top document -
     * which is why they must run in order and why {@link #leaveTheMailBody} exists.
     */
    @And("the invited user opens the first email")
    public void the_invited_user_opens_the_first_email() {
        boolean opened = YopmailInbox.openFirstMailAndFocusBody();
        if (!opened) {
            throw new AssertionError("No email could be opened in the Yopmail inbox for "
                    + context.secondaryUserEmail + ", so the invitation cannot be accepted.");
        }
    }

    @And("the invited user scrolls to the Accept invite button in the email")
    public void the_invited_user_scrolls_to_the_accept_invite_button() {
        WebElement accept = acceptInviteButton();
        ((JavascriptExecutor) Grid.driver())
                .executeScript("arguments[0].scrollIntoView({block:'center'});", accept);
        sleep(500);
        softAssert.assertTrue(accept.isDisplayed(),
                "The Accept invite call-to-action is in the email but not visible after scrolling");
    }

    /**
     * Clicks Accept invite and follows it out of the mail frame.
     *
     * <p>The link opens in a new window - it is an email, so its anchor targets a blank one. The
     * driver is left on that window, outside every Yopmail frame, which is where the account-setup
     * page is.
     */
    @When("the invited user clicks Accept invite")
    public void the_invited_user_clicks_accept_invite() {
        WebDriver driver = Grid.driver();
        Set<String> before = driver.getWindowHandles();

        clickWhenReady(acceptInviteButton(), "Accept invite");
        sleep(3000);
        YopmailInbox.leaveMailBody();

        for (String handle : driver.getWindowHandles()) {
            if (!before.contains(handle)) {
                driver.switchTo().window(handle);
                log.info("Accept invite opened a new window - switched to it");
                break;
            }
        }
        SessionBuilder.applyZoom();
    }

    /**
     * Confirms the accept-invite page loaded, and arms the response capture.
     *
     * <p>{@code NetworkCapture.install()} lives here, not in a step of its own, because of the one
     * rule that utility has: the shim is a wrapper around {@code window.fetch} stored on
     * {@code window}, so a full page load wipes it. This step runs immediately AFTER the last
     * navigation and before the Continue click that sends the OTP, which is the only window in
     * which installing it works. Installing earlier looks fine and captures nothing.
     */
    @Then("the invited user should land on the accept invite page")
    public void the_invited_user_should_land_on_the_accept_invite_page() {
        softAssert.assertTrue(currentUrl().contains("accept-invite"),
                "Accept invite did not open the accept-invite page. Current URL: " + currentUrl());
        softAssert.assertTrue(waitForVisible(PERMISSIONS_GRANTED_HEADING),
                "The accept-invite page has no 'Permissions granted' section. Current URL: "
                        + currentUrl());
        NetworkCapture.install();
        NetworkCapture.clear();
    }

    /**
     * The roles shown to the invitee must be the ones the primary user chose.
     *
     * <p>This is the assertion that the choice survived the whole invitation: the Users &amp;
     * permissions row is written by the same form that set it, whereas this screen is rendered
     * from the invitation the server actually stored.
     */
    @Then("the accept invite page should show exactly the granted roles:")
    public void the_accept_invite_page_should_show_exactly_the_granted_roles(DataTable table) {
        Set<String> expected = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        expected.addAll(table.asList());
        Set<String> actual = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        actual.addAll(grantedRolesOnAcceptPage());
        softAssert.assertEquals(actual.toString(), expected.toString(),
                "The accept-invite page shows different roles from the ones chosen at invitation."
                        + " Expected " + expected + " but the page shows " + actual + ".");
    }

    @Then("the accept invite page should show no granted roles")
    public void the_accept_invite_page_should_show_no_granted_roles() {
        List<String> shown = grantedRolesOnAcceptPage();
        softAssert.assertTrue(shown.isEmpty(),
                "The additional user was invited with no roles, but the accept-invite page shows: "
                        + shown);
    }

    @And("the invited user scrolls to the Continue button")
    public void the_invited_user_scrolls_to_the_continue_button() {
        WebElement continueButton = Grid.driver().findElement(getBy(ACCEPT_INVITE_CONTINUE));
        ((JavascriptExecutor) Grid.driver())
                .executeScript("arguments[0].scrollIntoView({block:'center'});", continueButton);
        sleep(500);
        softAssert.assertTrue(continueButton.isDisplayed(),
                "The Continue button is on the accept-invite page but not visible after scrolling");
    }

    /** Same scroll-then-click as Verify: Continue sits in the same overflow-y-auto panel. */
    @When("the invited user clicks Continue")
    public void the_invited_user_clicks_continue() {
        clickWhenReady(ACCEPT_INVITE_CONTINUE, "Continue");
    }

    /**
     * The OTP dialog must be addressed to the number given at invitation.
     *
     * <p>Accepts either the full number or its last four digits: the dialog usually masks the
     * middle. Last-four still identifies the invitee, because the number was generated for this
     * scenario and no other row on the account carries it.
     */
    @Then("the OTP popup should show the mobile number given at invitation")
    public void the_otp_popup_should_show_the_mobile_number_given_at_invitation() {
        requireInvitee();
        if (!waitForVisible(OTP_DIALOG_TITLE)) {
            throw new AssertionError("The 'Enter the OTP' dialog did not open after Continue, so"
                    + " there is nowhere to enter the code. Current URL: " + currentUrl());
        }
        String dialogText = Grid.driver().findElement(By.tagName("body")).getText();
        String mobile = context.secondaryUserMobileNo;
        String lastFour = mobile.length() >= 4 ? mobile.substring(mobile.length() - 4) : mobile;

        boolean full = dialogText.contains(mobile);
        boolean masked = dialogText.contains(lastFour);
        softAssert.assertTrue(full || masked,
                "The OTP dialog does not show the number the invitation was sent to (" + mobile
                        + "), so the code is going somewhere else.");
        if (!full && masked) {
            log.info("The OTP dialog shows the invited number masked; matched on the last four digits");
        }
    }

    /**
     * Reads the OTP out of the send-OTP response and types it in.
     *
     * <p>The code is taken from the application's OWN response rather than from a mailbox or an
     * SMS gateway, which is what {@link NetworkCapture} exists for - see the install() note on the
     * landing step above for why the ordering matters.
     */
    @And("the invited user enters the intercepted OTP")
    public void the_invited_user_enters_the_intercepted_otp() {
        enterInterceptedOtp();
    }

    /** Reads the OTP out of the send-OTP response and types it into the code field. */
    private void enterInterceptedOtp() {
        String otp = interceptedOtp();
        By locator = getBy(OTP_INPUT);
        if (!waitForVisible(OTP_INPUT)) {
            throw new AssertionError("No OTP field on screen, so the intercepted code cannot be"
                    + " entered.");
        }
        List<WebElement> boxes = Grid.driver().findElements(locator);
        if (boxes.size() > 1) {
            // One box per digit.
            for (int i = 0; i < otp.length() && i < boxes.size(); i++) {
                boxes.get(i).sendKeys(String.valueOf(otp.charAt(i)));
            }
        } else {
            boxes.get(0).sendKeys(otp);
        }
        // Length only, never the code itself - an OTP in the run log is an OTP in the Allure
        // report attached to it. Same rule NetworkCapture follows.
        log.info("Entered the intercepted OTP (" + otp.length() + " digits)");
    }

    // ============================================================================
    // The additional user signs in for themselves, by phone + OTP
    // ============================================================================

    @And("the page is refreshed after logout")
    public void the_page_is_refreshed_after_logout() {
        pageRefresh();
        sleep(2000);
        SessionBuilder.applyZoom();
    }

    /**
     * Opens the login modal, types the invitee's mobile and asks for a code.
     *
     * <p>{@code NetworkCapture.install()} sits between opening the modal and pressing Get OTP, and
     * that position is the whole trick: the shim lives on {@code window}, so the page refresh in
     * the previous step wiped the copy installed during acceptance. Opening the modal is a React
     * state change rather than a navigation, so this is the last safe moment before the call that
     * has to be captured. {@code clear()} then drops the OTP captured at acceptance - without it
     * the read below would return the FIRST code and log in with a stale one.
     */
    @When("the additional user signs in with their phone number")
    public void the_additional_user_signs_in_with_their_phone_number() {
        requireInvitee();
        clickWhenReady(LOGIN_CTA_BUTTON, "header Login");
        NetworkCapture.install();
        NetworkCapture.clear();

        if (!waitForVisible(LOGIN_PHONE_INPUT)) {
            throw new AssertionError("The login modal has no phone number field, so the additional"
                    + " user cannot sign in by OTP. Current URL: " + currentUrl());
        }
        waitAndSetText(LOGIN_PHONE_INPUT, context.secondaryUserMobileNo);
        announce("INVITEE", "signing in with mobile " + context.secondaryUserMobileNo);
        clickWhenReady(GET_OTP_BUTTON, "Get OTP");
    }

    @And("the additional user enters the OTP received on login")
    public void the_additional_user_enters_the_otp_received_on_login() {
        enterInterceptedOtp();
    }

    @And("the additional user clicks Verify to sign in")
    public void the_additional_user_clicks_verify_to_sign_in() {
        clickWhenReady(VERIFY_OTP_BUTTON, "Verify");
        captureInviteeSessionToken();
        sleep(3000);
        softAssert.assertTrue(waitForVisible(ACCOUNT_MENU),
                "The additional user is not signed in after verifying the OTP - no account menu in"
                        + " the header. Current URL: " + currentUrl());
        announce("INVITEE", "signed in as " + context.secondaryUserEmail);
        verifyInviteeSeesPrimaryPaymentReminder();
    }

    /**
     * Returns to the portal home page and confirms the session survived it.
     *
     * <p>The assertion is the point: a navigation that quietly dropped the session would leave
     * the scenario "finished" on a logged-out home page, which looks like a pass.
     */
    @And("the additional user returns to the homepage")
    public void the_additional_user_returns_to_the_homepage() {
        Grid.driver().get(ConfigUtil.getSecretValue("ccpPortalUrl"));
        SessionBuilder.applyZoom();
        sleep(2000);
        softAssert.assertTrue(waitForVisible(ACCOUNT_MENU),
                "The additional user was signed out by returning to the homepage");
    }

    /**
     * Scrolls the Verify button into view WITHOUT clicking it.
     *
     * <p>Runs before the consents rather than after, which looks out of order but is the point:
     * the two consent checkboxes sit directly above Verify in the same
     * {@code overflow-y-auto} panel, so bringing Verify to the middle of that panel brings them
     * with it. Clicking them while they are near the panel's edge is what put the terms checkbox
     * under the {@code absolute inset-0} modal backdrop on the 18 Sep run.
     */
    @And("the invited user scrolls to the Verify button")
    public void the_invited_user_scrolls_to_the_verify_button() {
        WebElement verify = presence(getBy(VERIFY_OTP_BUTTON), "Verify");
        scrollIntoView(verify);
        softAssert.assertTrue(verify.isDisplayed(),
                "The Verify button is on the OTP dialog but not visible after scrolling to it");
        log.info("Scrolled to Verify - the consent checkboxes are now in view with it");
    }

    @And("the invited user accepts the WhatsApp and terms consents")
    public void the_invited_user_accepts_the_whatsapp_and_terms_consents() {
        clickWhenReady(WHATSAPP_CONSENT, "WhatsApp consent checkbox");
        clickWhenReady(TERMS_CONSENT, "Terms consent checkbox");
        log.info("Accepted the WhatsApp and terms consents");
    }

    /**
     * Scrolls the Verify button into view, then clicks it.
     *
     * <p>The scroll is required, not defensive. Verify sits at the foot of a
     * {@code flex-1 overflow-y-auto} container, and Selenium clicks an element's CENTRE POINT: with
     * the button below the container's visible region that point lands on the scroll container,
     * which is what the run reported - "element click intercepted ... Other element would receive
     * the click: div.flex-1.overflow-y-auto". Nothing was overlaying the button; it simply was not
     * where the click went.
     */
    @And("the invited user clicks Verify")
    public void the_invited_user_clicks_verify() {
        clickWhenReady(VERIFY_OTP_BUTTON, "Verify");
        captureInviteeSessionToken();
        sleep(3000);
        announce("INVITEE", "verified and activated " + context.secondaryUserEmail);
    }

    /**
     * Scrolls an element to the middle of whatever scrolls it, waits for it to be clickable, then
     * clicks - falling back to a scripted click only if the real one is still intercepted.
     *
     * <p>{@code scrollIntoView({block:'center'})} rather than a window scroll or a keyboard END:
     * these call-to-actions live inside a {@code overflow-y-auto} panel, not in the page's own
     * scroll, and only scrollIntoView walks the nearest scrollable ancestor.
     *
     * <p>The scripted fallback is LOGGED at warning rather than done silently. A JS click bypasses
     * real hit-testing, so it would happily click a button genuinely hidden behind a modal or a
     * cookie banner - which is a product defect worth failing on. If this warning shows up in a
     * run, the click is landing somewhere unexpected and deserves a look rather than a shrug.
     */
    /** A yaml locator key. Keeps Sauron's click, so self-healing still applies. */
    /**
     * Clicks the header logo to land on the home page before opening the Account menu, so the menu
     * is opened from a known page rather than whatever state the previous step left behind.
     */
    private void clickHeaderLogo() {
        clickWhenReady(HEADER_LOGO, "header logo");
        waitForDocumentReady();
    }

    /**
     * Opens the header Account menu and clicks an item in it, surviving the menu closing on its own.
     *
     * <p>WHY THIS EXISTS - the 24 Sep 17:32 run. The logo click moved the app from /my-profile to
     * the home page; the Account menu was opened while that page was still loading, the Logout
     * item was found, and then the home page re-rendered the header, which closed the menu and
     * detached the item. {@code clickWhenReady} kept polling the dead element for 10s and failed
     * with "displayed=false", although nothing about Logout itself was wrong.
     *
     * <p>So the item is looked up afresh on every attempt, never held across a wait, and a menu
     * that closed is simply opened again. Account is only clicked when the menu is not already
     * open - judged by Profile details, which only the menu has, not by the item itself: the
     * profile page has a Logout button of its own - because clicking it on an open menu toggles
     * the menu shut.
     */
    private void clickInAccountMenu(By item, String description) {
        By account = By.xpath(CCP_ACCOUNT_BTN);
        By menuOnlyItem = getBy(ACCOUNT_PROFILE_DETAILS_LINK);
        RuntimeException lastFailure = null;
        for (int attempt = 1; attempt <= ACCOUNT_MENU_ATTEMPTS; attempt++) {
            if (!isVisibleNow(menuOnlyItem)) {
                clickWhenReady(account, "header Account menu");
            }
            WebElement target = awaitFirstVisible(item, ACCOUNT_MENU_ITEM_WAIT_MS);
            if (target == null) {
                log.warning("Attempt " + attempt + ": the Account menu did not show " + description
                        + " - it may have closed on a re-render. Opening it again.");
                continue;
            }
            try {
                clickWhenReady(target, description);
                return;
            } catch (RuntimeException e) {
                // Typically StaleElementReferenceException: the menu re-rendered between finding
                // the item and clicking it.
                lastFailure = e;
                log.warning("Attempt " + attempt + ": could not click " + description + " ("
                        + e.getClass().getSimpleName() + ") - opening the Account menu again.");
                sleep(500);
            }
        }
        throw new AssertionError("Could not click " + description + " in the header Account menu"
                + " after " + ACCOUNT_MENU_ATTEMPTS + " attempts - the menu kept closing or the item"
                + " never appeared. Current URL: " + currentUrl(), lastFailure);
    }

    /** Closes the Account menu if it is open, so the next step starts from a closed header. */
    private void closeAccountMenu() {
        By menuItem = getBy(ACCOUNT_PROFILE_DETAILS_LINK);
        if (!isVisibleNow(menuItem)) {
            return;
        }
        new Actions(Grid.driver()).sendKeys(Keys.ESCAPE).perform();
        if (!waitForHidden(ACCOUNT_PROFILE_DETAILS_LINK)) {
            // Escape did not close it - the toggle does.
            clickWhenReady(By.xpath(CCP_ACCOUNT_BTN), "header Account menu (close)");
        }
    }

    /** The first displayed match, polled for up to {@code timeoutMs}; null when none appears. */
    private WebElement awaitFirstVisible(By locator, long timeoutMs) {
        long deadline = System.currentTimeMillis() + timeoutMs;
        WebDriver driver = Grid.driver();
        do {
            driver.manage().timeouts().implicitlyWait(Duration.ZERO);
            try {
                for (WebElement candidate : driver.findElements(locator)) {
                    try {
                        if (candidate.isDisplayed()) {
                            return candidate;
                        }
                    } catch (RuntimeException ignored) {
                        // stale - try the next match
                    }
                }
            } finally {
                driver.manage().timeouts().implicitlyWait(SessionBuilder.IMPLICIT_WAIT);
            }
            sleep(250);
        } while (System.currentTimeMillis() < deadline);
        return null;
    }

    /** Waits for the document to finish loading, so the header is not re-rendered mid-click. */
    private void waitForDocumentReady() {
        try {
            new WebDriverWait(Grid.driver(), Duration.ofSeconds(CLICK_WAIT_SECONDS)).until(d ->
                    "complete".equals(((JavascriptExecutor) d).executeScript("return document.readyState")));
        } catch (RuntimeException e) {
            log.warning("The page did not report readyState=complete within " + CLICK_WAIT_SECONDS
                    + "s - continuing; the Account menu click retries if the header re-renders.");
        }
        sleep(1000);
    }

    private void clickWhenReady(String locatorKey, String description) {
        WebElement element = presence(getBy(locatorKey), description);
        scrollIntoView(element);
        requireClickable(element, description);
        String blocker = awaitUnobstructed(element);
        try {
            waitAndClickOn(locatorKey);
        } catch (ElementClickInterceptedException e) {
            scriptedClick(element, description, blocker, e);
        }
        log.info("Clicked " + description);
    }

    /**
     * Waits for a control to become enabled, and says so plainly when it does not.
     *
     * <p>This exists because of how a disabled control fails otherwise. Sauron's
     * {@code waitAndClickOn} waits for CLICKABLE, a disabled button never becomes clickable, so it
     * returns null and {@code .click()} on that null throws
     * {@code NullPointerException} with no message from inside the framework - which says nothing
     * about the button, the form, or why. A disabled submit almost always means the form above it
     * is incomplete, so that is what the message points at.
     */
    private void requireClickable(WebElement element, String description) {
        long deadline = System.currentTimeMillis() + (CLICK_WAIT_SECONDS * 1000L);
        Boolean enabled = null;
        while (System.currentTimeMillis() < deadline) {
            if (!isVisible(element)) {
                sleep(250);
                continue;
            }
            enabled = isEnabled(element);
            if (Boolean.TRUE.equals(enabled)) {
                // Re-checked at the LAST moment, not once at the start. A form that validates its
                // mobile or email against the server flips its submit back to disabled after a
                // round trip, so an early check can pass and the click still land on a disabled
                // button - which is how the 18 Sep run reached Sauron's null and threw an NPE with
                // this guard already in place.
                sleep(300);
                if (Boolean.TRUE.equals(isEnabled(element)) && isVisible(element)) {
                    return;
                }
                continue;
            }
            sleep(250);
        }
        throw new AssertionError("The " + description + " control is not clickable after "
                + CLICK_WAIT_SECONDS + "s: displayed=" + isVisible(element)
                + ", enabled=" + (enabled == null ? "unreadable" : enabled)
                + ". A control that is present but NOT DISPLAYED is usually the hidden half of a"
                + " desktop/mobile pair, so the locator is matching the wrong twin. A displayed but"
                + " DISABLED submit means the form above it is incomplete.");
    }

    /**
     * True/false for a control's enabled state, or null when it cannot be read.
     *
     * <p>Re-resolved from the locator on every call rather than held as a WebElement: a React
     * re-render replaces the node, and a stale reference threw here. The old version caught that
     * and returned "enabled", which defeated the whole guard - it has to say "unknown" instead.
     */
    private boolean isVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private Boolean isEnabled(WebElement element) {
        try {
            String disabled = element.getAttribute("disabled");
            return element.isEnabled() && (disabled == null || "false".equalsIgnoreCase(disabled));
        } catch (Exception e) {
            return null;
        }
    }

    /** A raw locator, for the handful of controls held as xpath constants rather than yaml keys. */
    private void clickWhenReady(By locator, String description) {
        WebElement element = presence(locator, description);
        scrollIntoView(element);
        requireClickable(element, description);
        String blocker = awaitUnobstructed(element);
        try {
            new WebDriverWait(Grid.driver(), Duration.ofSeconds(CLICK_WAIT_SECONDS))
                    .until(ExpectedConditions.elementToBeClickable(element))
                    .click();
        } catch (ElementClickInterceptedException e) {
            scriptedClick(element, description, blocker, e);
        }
        log.info("Clicked " + description);
    }

    private void clickWhenReady(WebElement element, String description) {
        scrollIntoView(element);
        String blocker = awaitUnobstructed(element);
        try {
            new WebDriverWait(Grid.driver(), Duration.ofSeconds(CLICK_WAIT_SECONDS))
                    .until(ExpectedConditions.elementToBeClickable(element))
                    .click();
        } catch (ElementClickInterceptedException e) {
            scriptedClick(element, description, blocker, e);
        }
        log.info("Clicked " + description);
    }

    /**
     * The element to click - preferring the one that is actually DISPLAYED when a locator matches
     * several.
     *
     * <p>This app renders desktop and mobile variants of the same control and hides one with CSS
     * ({@code users-permissions-add-user-button} and {@code -desktop},
     * {@code user-modal-submit-button} and {@code user-form-add-button}). A locator that matches
     * both, or worse an indexed one like {@code (//button[text()='Add user'])[2]}, can hand back
     * the hidden twin - which is present and enabled, so every check passes, and only Sauron's
     * wait-for-clickable notices, by returning null and throwing an NPE with no message.
     *
     * <p>When nothing is displayed the first match is returned anyway, so
     * {@link #requireClickable} reports "hidden" against a real element rather than timing out
     * with nothing to name.
     */
    private WebElement presence(By locator, String description) {
        List<WebElement> matches;
        try {
            new WebDriverWait(Grid.driver(), Duration.ofSeconds(CLICK_WAIT_SECONDS))
                    .until(ExpectedConditions.presenceOfElementLocated(locator));
            matches = Grid.driver().findElements(locator);
        } catch (RuntimeException e) {
            throw new AssertionError("The " + description + " control never appeared, so it cannot"
                    + " be clicked. Current URL: " + currentUrl());
        }
        for (WebElement candidate : matches) {
            try {
                if (candidate.isDisplayed()) {
                    return candidate;
                }
            } catch (Exception ignored) {
                // stale or detached - try the next match
            }
        }
        if (matches.size() > 1) {
            log.warning("None of the " + matches.size() + " elements matching the " + description
                    + " locator is displayed - using the first, which will fail the visibility"
                    + " check with a message rather than a NullPointerException.");
        }
        return matches.get(0);
    }

    /**
     * Brings an element to the middle of whatever scrolls it.
     *
     * <p>{@code scrollIntoView} rather than a window scroll or a keyboard END: these controls sit
     * inside {@code overflow-y-auto} panels, not the page's own scroll, and only scrollIntoView
     * walks the nearest scrollable ancestor. This is what fixes the FIRST kind of interception -
     * Selenium clicks an element's centre point, and a button below its panel's visible region has
     * a centre point that lands on the panel instead.
     */
    private void scrollIntoView(WebElement element) {
        try {
            ((JavascriptExecutor) Grid.driver()).executeScript(
                    "arguments[0].scrollIntoView({block:'center', inline:'nearest'});", element);
            sleep(400);
        } catch (Exception e) {
            log.warning("Could not scroll the element into view: " + e.getMessage());
        }
    }

    /**
     * Waits until nothing else is on top of the element's click point.
     *
     * <p>This is the SECOND kind of interception, and scrolling cannot fix it: a modal backdrop is
     * {@code absolute inset-0}, so it covers the viewport wherever you scroll to. The run on
     * 18 Sep hit exactly this - the terms checkbox reported
     * {@code Other element would receive the click: button.absolute.inset-0.bg-black/40
     * aria-label="Close modal"} - because clicking the WhatsApp consent re-rendered the dialog and
     * the backdrop was hit-testable while it settled.
     *
     * <p>Selenium's {@code elementToBeClickable} does NOT test for occlusion - it checks displayed
     * and enabled only - so the wait has to ask the browser what is actually at the point, with
     * {@code document.elementFromPoint}.
     *
     * @return a description of whatever was still on top when the wait ran out, or null if clear.
     */
    private String awaitUnobstructed(WebElement element) {
        String blocker = null;
        long deadline = System.currentTimeMillis() + (CLICK_WAIT_SECONDS * 1000L);
        while (System.currentTimeMillis() < deadline) {
            blocker = topElementAt(element);
            if (blocker == null || blocker.isEmpty()) {
                return null;
            }
            sleep(250);
        }
        log.warning("Something is still over the click point after " + CLICK_WAIT_SECONDS
                + "s: " + blocker);
        return blocker;
    }

    /** "" when the element itself is at its own click point, otherwise what is on top. */
    private String topElementAt(WebElement element) {
        try {
            Object result = ((JavascriptExecutor) Grid.driver()).executeScript(
                    "var el = arguments[0];"
                  + "var r = el.getBoundingClientRect();"
                  + "if (r.width === 0 || r.height === 0) { return 'the element has zero size'; }"
                  + "var top = document.elementFromPoint(r.left + r.width / 2, r.top + r.height / 2);"
                  + "if (!top) { return 'nothing is at the click point - it is off screen'; }"
                  // An ancestor at the point is fine: a click there still reaches this element.
                  + "if (top === el || el.contains(top) || top.contains(el)) { return ''; }"
                  + "return (top.getAttribute('aria-label') || top.className || top.tagName);",
                    element);
            return result == null ? "" : String.valueOf(result);
        } catch (Exception e) {
            return "";                       // cannot tell - let the real click decide
        }
    }

    /**
     * Last resort. A scripted click bypasses hit-testing, so it would happily press a control
     * genuinely hidden behind a modal - which is a product defect worth failing on. Hence the
     * warning naming what was in the way, rather than a silent retry.
     */
    private void scriptedClick(WebElement element, String description, String blocker,
                               ElementClickInterceptedException cause) {
        log.warning("The " + description + " control was still intercepted after scrolling to it"
                + " and waiting for the click point to clear"
                + (blocker == null ? "" : " (in the way: " + blocker + ")")
                + " - falling back to a scripted click. " + cause.getMessage());
        ((JavascriptExecutor) Grid.driver()).executeScript("arguments[0].click();", element);
    }

    /** The role names listed in the Permissions granted section. */
    private List<String> grantedRolesOnAcceptPage() {
        return Grid.driver().findElements(getBy(GRANTED_ROLE_NAMES)).stream()
                .map(WebElement::getText)
                .map(text -> text == null ? "" : text.trim())
                .filter(text -> !text.isEmpty())
                .collect(Collectors.toList());
    }

    /**
     * The OTP from {@code data.otp} of the send-OTP response.
     *
     * <p>One exact path, with no fallback scan for "some 4-8 digit number in the body". A fallback
     * cannot tell an OTP from a request id, a timestamp or a phone number, so on the day the
     * response shape changes it would type the wrong value and the failure would land on the
     * Verify button - pointing at OTP validation rather than at this read.
     *
     * <p>The digit check is the same reasoning: {@code data.otp} present but non-numeric means the
     * contract changed, and saying so here beats a puzzling "invalid OTP" on screen.
     */
    /**
     * Reads the additional user's own jwtToken from the app's otp/verify response and reads their
     * isTransacting flag from the middleware with it.
     *
     * <p>Read from the response the BROWSER received rather than by calling otp/verify again with
     * the otp, the requestId from otp/send and the phone number: the browser has just made exactly
     * that call, and an OTP is single-use, so a second verify would either be rejected or spend
     * the code the browser's sign-in depends on. The capture shim installed before Get OTP sees
     * the response, and the read starts straight after the click - before the app can navigate
     * and wipe the shim.
     *
     * <p>Soft failures only: the sign-in itself is asserted by the step, and a missing token must
     * not stop the scenario. Without one, category steps fall back to reading the header.
     */
    private void captureInviteeSessionToken() {
        String token;
        try {
            token = NetworkCapture.awaitJsonValue(OTP_VERIFY_URL, JWT_JSON_PATH, OTP_VERIFY_TIMEOUT_SECONDS);
        } catch (IllegalStateException e) {
            softAssert.fail("Could not read " + JWT_JSON_PATH + " from the app's " + OTP_VERIFY_URL
                    + " response, so the additional user's isTransacting cannot be read from the"
                    + " middleware. " + e.getMessage());
            return;
        }
        CustomerProfileUtil.useSessionToken(token);
        try {
            boolean transacting = CustomerProfileUtil.fetchIsTransactingFlag();
            announce("INVITEE", "isTransacting = " + transacting + " for " + context.secondaryUserEmail);
        } catch (RuntimeException | AssertionError e) {
            softAssert.fail("customer-profile did not return isTransacting for the additional user's"
                    + " own jwtToken: " + e.getMessage());
        }
    }

    private String interceptedOtp() {
        String otp = NetworkCapture.awaitJsonValue(OTP_SEND_URL, OTP_JSON_PATH);
        if (otp == null || !otp.matches("\\d{4,8}")) {
            throw new AssertionError("The response from " + OTP_SEND_URL + " carries a "
                    + OTP_JSON_PATH + " that is not a 4-8 digit code (" + (otp == null ? 0 : otp.length())
                    + " chars), so it cannot be entered as an OTP.");
        }
        return otp;
    }

    /**
     * Sets the invited user's password and finishes activation.
     *
     * <p>The password comes from {@code invitation.newUser.password} rather than being generated:
     * an invitee whose password nobody recorded cannot be signed in as afterwards, which is the
     * only way to prove the activation actually worked.
     */
    @And("the invited user completes the account setup")
    public void the_invited_user_completes_the_account_setup() {
        requireInvitee();
        if (!waitForVisible(INVITATION_SETUP_PASSWORD)) {
            throw new AssertionError("Accept invite did not open a password form. Current URL: "
                    + currentUrl());
        }
        String password = ConfigUtil.resolveOrThrow("invitation.newUser.password");
        waitAndSetText(INVITATION_SETUP_PASSWORD, password);
        if (isAnyVisible(getBy(INVITATION_SETUP_CONFIRM_PASSWORD))) {
            waitAndSetText(INVITATION_SETUP_CONFIRM_PASSWORD, password);
        }
        clickWhenReady(INVITATION_SETUP_SUBMIT, "account setup submit");
        sleep(3000);
        announce("INVITEE", "completed account setup for " + context.secondaryUserEmail);
    }

    // ------------------- The activated user's own view of the account -------------------

    @And("the additional user opens Account and then Profile details")
    public void the_additional_user_opens_account_and_then_profile_details() {
        clickWhenReady(By.xpath(CCP_ACCOUNT_BTN), "header Account menu");
        clickWhenReady(ACCOUNT_PROFILE_DETAILS_LINK, "Profile details");
        sleep(2000);
        SessionBuilder.applyZoom();
    }

    /**
     * The profile must show what the primary user typed into the invitation.
     *
     * <p>Asserted one field at a time rather than as a single "all three present" check: a name
     * that survived and a mobile that did not are different defects, and a combined assertion
     * would report whichever failed first as though the whole profile were wrong.
     *
     * <p>Matched against the page text rather than specific fields because all three values are
     * generated per scenario and so are unique on the page - an incidental match is not possible.
     */
    @Then("the profile should show the name given at invitation")
    public void the_profile_should_show_the_name_given_at_invitation() {
        assertProfileField(PROFILE_NAME_VALUE, context.secondaryUserFullName, "Name");
    }

    @Then("the profile should show the email given at invitation")
    public void the_profile_should_show_the_email_given_at_invitation() {
        assertProfileField(PROFILE_EMAIL_VALUE, context.secondaryUserEmail, "Email address");
    }

    /**
     * Phone is compared on DIGITS only: the invitation carries a bare 10-digit number while the
     * profile renders it as "+91 9876543210", so a literal comparison would fail on formatting
     * and say nothing about whether the right number was stored.
     */
    @Then("the profile should show the phone number given at invitation")
    public void the_profile_should_show_the_phone_number_given_at_invitation() {
        requireInvitee();
        String actual = profileField(PROFILE_PHONE_VALUE);
        if (actual == null || actual.trim().isEmpty()) {
            softAssert.assertTrue(false, "Phone number is not displayed on the profile page");
            return;
        }
        String actualDigits = actual.replaceAll("\\D", "");
        String expectedDigits = context.secondaryUserMobileNo.replaceAll("\\D", "");
        softAssert.assertTrue(actualDigits.endsWith(expectedDigits),
                "The profile shows phone '" + actual + "' but the invitation was sent to "
                        + context.secondaryUserMobileNo);
    }

    /**
     * One personal-detail field against what the invitation carried.
     *
     * <p>THE EXPECTED VALUES COME FROM THE INVITATION, not from a data file. That is the one place
     * these scenarios differ from the customer-profile feature, and it is deliberate: the invitee
     * is created by this very run, so the run already knows the truth. It also removes that
     * feature's critical unstated coupling - no requirement that the signed-in account happens to
     * be the customer some environment-scoped yaml describes, and nothing to re-point when the
     * environment changes.
     *
     * <p>The COMPANY details are the opposite case and do come from a data file: an additional
     * user inherits the primary account's company, which this run did not create and which differs
     * per environment.
     */
    private void assertProfileField(String locatorKey, String expected, String label) {
        requireInvitee();
        String actual = profileField(locatorKey);
        softAssert.assertFalse(actual == null || actual.trim().isEmpty(),
                label + " is not displayed on the additional user's profile page");
        if (actual == null || actual.trim().isEmpty()) {
            return;
        }
        softAssert.assertEquals(actual.trim(), expected,
                label + " on the profile does not match what was entered at invitation");
        log.info("Profile " + label + " = '" + actual.trim() + "'");
    }

    private String profileField(String locatorKey) {
        try {
            return getText(getBy(locatorKey));
        } catch (Exception e) {
            log.info("Profile field '" + locatorKey + "' not readable: " + e.getMessage());
            return null;
        }
    }

    /**
     * An additional user must not be offered user management.
     *
     * <p>Opens the menu first and asserts on what is INSIDE it. Checking the page without opening
     * it would pass for the wrong reason - the entry is absent from a closed menu however the
     * permission is configured, so the assertion would hold even if the product did offer it.
     */
    @Then("the account dropdown should not offer Users and permissions")
    public void the_account_dropdown_should_not_offer_users_and_permissions() {
        clickWhenReady(By.xpath(CCP_ACCOUNT_BTN), "header Account menu");
        // Wait for an item every user has, so "absent" means absent from a rendered menu rather
        // than a menu that has not opened yet - then look once, without waiting for it to appear.
        waitForVisible(ACCOUNT_PROFILE_DETAILS_LINK);
        softAssert.assertFalse(isVisibleNow(getBy(UsersPermissionsTab)),
                "The account menu offers 'Users & permissions' to an additional user, so they can"
                        + " reach user management that belongs to the primary user.");
        log.info("'Users & permissions' is absent from the additional user's account menu");
        closeAccountMenu();
    }

    /**
     * Logs the additional user out. Same controls as the primary user's logout - the header is the
     * same header - but named separately so a failure says WHO was being logged out.
     */
    @And("the additional user logs out")
    public void the_additional_user_logs_out() {
        clickHeaderLogo();
        clickInAccountMenu(By.xpath(MENU_LOGOUT), "Logout");
        // if(isElementPresent(LOGOUT_POPUP)) {
        //    clickWhenReady(CONFIRM_LOGOUT_BUTTON, "Confirm Logout");
        //     log.info("The additional user logged out pop up appeared and log out button was clicked");
        // } else {
        //     log.warning("The additional user clicked Logout but the header Login CTA did not appear"
        //             + " within 5s, so the logout may have failed. Current URL: " + currentUrl());
        // }
        softAssert.assertTrue(waitForVisible(HEADER_LOGIN_CTA),
                "The additional user is still signed in after logging out, so the primary user"
                        + " cannot sign in to check the status.");
        // The captured token belongs to the user who just signed out; profile lookups go back to
        // the configured user.
        CustomerProfileUtil.clearSessionToken();
        announce("INVITEE", "logged out " + context.secondaryUserEmail);
    }

    /**
     * Returns the browser to the CCP portal so the primary user can sign in again.
     *
     * <p>Needed because this scenario deliberately threw away the primary user's window: the login
     * steps start by refreshing and clicking the header Login call-to-action, which only exist on
     * the portal.
     */
    @And("the browser returns to the CCP portal")
    public void the_browser_returns_to_the_ccp_portal() {
        YopmailInbox.leaveMailBody();
        Grid.driver().get(ConfigUtil.getSecretValue("ccpPortalUrl"));
        SessionBuilder.applyZoom();
        sleep(2000);
        // First portal load of the session the acceptance sign-in created - checked here rather
        // than after Verify because this navigation would discard a popup opened on the accept page.
        verifyInviteeSeesPrimaryPaymentReminder();
    }

    /** The Accept invite element inside the currently focused mail frame. */
    private WebElement acceptInviteButton() {
        By locator = getBy(ACCEPT_INVITE_IN_EMAIL);
        if (!isAnyVisible(locator)) {
            throw new AssertionError("The invitation email to " + context.secondaryUserEmail
                    + " has no 'Accept invite' call-to-action, so there is nothing to accept."
                    + " If the template words it differently, update the acceptInviteInEmail"
                    + " locator - the page source of the mail is in target/diagnostics.");
        }
        return Grid.driver().findElement(locator);
    }

    /** Explicit escape from Yopmail's frames, for a scenario that stops mid-mailbox. */
    @And("the invited user leaves the email body")
    public void leaveTheMailBody() {
        YopmailInbox.leaveMailBody();
    }

    // ------------------------------ Expired invitation ------------------------------

    /**
     * Loads an invitation that is ALREADY past its window, from invitation.expired.* .
     *
     * <p>Nothing here ages an invitation out: a real expiry window is measured in days, so the
     * only honest options are seeded data or waiting, and waiting is not an option. This mirrors
     * ccpExpiredToken, which the token-expiry scenarios use for exactly the same reason.
     *
     * <p>resolveOrThrow, not getSecretValue: both keys ship empty, and an empty link would
     * navigate the browser to the bare profile URL and fail the assertions below as though the
     * product had not expired the invitation.
     */
    @Given("an invitation that has already expired")
    public void an_invitation_that_has_already_expired() {
        expiredInvitationLink = ConfigUtil.resolveOrThrow("invitation.expired.link");
        expiredInvitationEmail = ConfigUtil.resolveOrThrow("invitation.expired.email");
        announce("INVITEE", expiredInvitationEmail + " (configured expired invitation)");
    }

    @When("the additional user opens the expired invitation link")
    public void the_additional_user_opens_the_expired_invitation_link() {
        if (expiredInvitationLink == null) {
            throw new AssertionError("No expired invitation has been loaded - the scenario is"
                    + " missing the 'an invitation that has already expired' step.");
        }
        Grid.driver().get(expiredInvitationLink);
        SessionBuilder.applyZoom();
    }

    @Then("the invitation should be rejected as expired")
    public void the_invitation_should_be_rejected_as_expired() {
        softAssert.assertTrue(waitForVisible(INVITATION_EXPIRED_MESSAGE),
                "Opening an expired invitation did not say it had expired. Current URL: "
                        + currentUrl());
    }

    @Then("the additional user should not be able to set up an account from the expired link")
    public void the_additional_user_should_not_be_able_to_set_up_an_account_from_the_expired_link() {
        softAssert.assertTrue(waitForHidden(INVITATION_SETUP_EMAIL_FIELD),
                "An expired invitation still offers the account-setup form, so a lapsed invite can"
                        + " be used to create an account");
    }

    @Then("the additional user should be told how to request a new invitation")
    public void the_additional_user_should_be_told_how_to_request_a_new_invitation() {
        softAssert.assertTrue(isAnyVisible(getBy(REQUEST_NEW_INVITATION_GUIDANCE)),
                "The expired-invitation screen does not tell the recipient how to get a new one,"
                        + " leaving them with a dead end");
    }

    @Then("the invited user should be listed with the {string} status")
    public void the_invited_user_should_be_listed_with_the_status(String expected) {
        if (expiredInvitationEmail == null) {
            throw new AssertionError("No expired invitation has been loaded - the scenario is"
                    + " missing the 'an invitation that has already expired' step.");
        }
        By status = byFormatted(STATUS_FOR_EMAIL, expiredInvitationEmail);
        if (!isAnyVisible(status)) {
            softAssert.assertTrue(false, "No invitation status is shown for "
                    + expiredInvitationEmail + " on the Users & permissions tab, so '" + expected
                    + "' cannot be verified. The row may not be listed at all once an invitation"
                    + " lapses - if that is the product's behaviour, this scenario is wrong.");
            return;
        }
        String actual = getText(status);
        softAssert.assertTrue(actual != null && actual.toLowerCase().contains(expected.toLowerCase()),
                "Expected the lapsed invitation to read '" + expected + "' but it reads '"
                        + actual + "'");
    }

    /**
     * Asserts the control is OFFERED, and does not press it.
     *
     * <p>Clicking Resend sends a real email to a real address from the test account. The scenario
     * claims the primary user "should be able to" resend, and presence plus enabled state is that
     * claim; actually sending is a separate behaviour that deserves its own scenario and its own
     * mailbox assertion.
     */
    @Then("the primary user should be able to resend the invitation to that user")
    public void the_primary_user_should_be_able_to_resend_the_invitation() {
        By resend = getBy(RESEND_INVITATION_BUTTON);
        if (!isAnyVisible(resend)) {
            softAssert.assertTrue(false, "No Resend control is offered for the lapsed invitation,"
                    + " so the primary user cannot recover from an expiry");
            return;
        }
        softAssert.assertTrue(Grid.driver().findElement(resend).isEnabled(),
                "The Resend control for the lapsed invitation is disabled");
    }

    // --------------------------------- Invitee state ---------------------------------

    /** Fails naming the missing step rather than letting a null address reach a locator. */
    private ScenarioContext requireInvitee() {
        if (context.secondaryUserEmail == null || context.secondaryUserMobileNo == null) {
            throw new AssertionError("No invitee exists for this scenario. Add the step 'the"
                    + " additional user to be invited does not exist in the system' before any"
                    + " step that invites or inspects one.");
        }
        return context;
    }

    private String currentUrl() {
        try {
            return Grid.driver().getCurrentUrl();
        } catch (Exception e) {
            return "unavailable";
        }
    }
}
