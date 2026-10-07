package com.qapitol.jsw.stepdefinition;



import com.qapitol.jsw.apitest.pojos.order.customerDetails.CustomerDetailsResponse;
import com.qapitol.jsw.utils.ApiUtils;
import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.jsw.utils.UserConfig;
import com.qapitol.jsw.config.SessionBuilder;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.*;

import java.io.IOException;
import java.util.Base64;
import java.time.Duration;

import static com.qapitol.jsw.utils.ApiUtils.deleteAllCartItems;
import static com.qapitol.jsw.utils.CustomUtil.*;


public  class SessionSteps extends AbstractWebPage {
    private static final String LOGIN_CTA_BUTTON = "loginButton";
    private static final String LOGIN_EMAIL = "ccpLoginEmail";
    private static final String LOGIN_PASSWORD = "ccpLoginPassword";
    private static final String CCP_LOG_OUT="ccpLogout";
    private static final By SEARCH_OVERLAY = By.cssSelector(".search-overlay");
    private static final String PAYMENT_DUE_POP_UP="paymentDuePopUp";
    private static final String PAYMENT_DUE_CLOSE ="ccpHomePagePaymentDueClose";
    private static final String LOGIN_POP_UP_CLOSE_BUTTON="loginPopUpCloseButton";
    public static final String CLOE_POPUP="//h2[text()='Confirm your business details']/preceding-sibling::div/button";
    public static final String MANAGE_POPUP="//button[text()='Manage users and permissions']";
    public static final String MANAGE_POPUP_CLOSE = "//h2[text()='Users & permissions']/../preceding-sibling::button";
    public static final String CATEGORY_IMG = "//h2[text()='Browse by categories']/../following-sibling::div//img";
    public static final String CATEGORY_NAME = "//h2[text()='Browse by categories']/../following-sibling::div//p";
    public static final String PLP_NAME = "//h1[normalize-space()='%s']";
    public static final String HOME_PAGE_LINK = "(//a[text()='Home'])[2]";
    public static final String CATEGORY_VIEW_ALL="//h2[text()='Browse by categories']/following-sibling::a";
    public static final String ALL_CATEGORY = "//h1[text()='All categories']";
    public static final String CTAEGORY_NAME_LIST = "//div[@data-slot=\"card-content\"]//h3";
    public static final String TOP_BRAND_IMG="//h2[text()='Top Brands']/../following-sibling::div//img";
    public static final String TOP_BRAND_NAME = "//h2[text()='Top Brands']/../following-sibling::div//p";
    public static final String BRAND_VIEW_ALL="//h2[text()='Top Brands']/following-sibling::a";
    public static final String ALL_BRAND = "//h1[text()='All brands']";
    public static final String POPULAR_CATEGORY_IMG = "//h2[normalize-space()='Top Brands']/parent::*/following-sibling::div//img";
    public static final String POPULAR_VIEW_ALL_LINK_NAME = "//h2[text()='Top Brands']/../../../../preceding-sibling::div//button[contains(.,'View all')]/preceding-sibling::h2";
    public static final String POPULAR_VIEW_ALL_LINK = "//h2[text()='Top Brands']/../../../../preceding-sibling::div//button[contains(.,'View all')]";
    public static final String PAYMENT_REMINDER_POPUP = "//h2[text()='Payment reminder']/../preceding-sibling::button";
    public static final String MASTER_CLASS_MSME_LINKS = "//h2[text()='MSME Masterclass']/../following-sibling::div//button";
    public static final String FORM_OUR_CUSTOMER = "//h2[text()='From our customers']/../following-sibling::div//button[@aria-label=\"Play video\"]/div";
    public static final String USER_DATA_POPUP = "//button[text()='Yes, details are correct']";
    // Same element the header steps assert on (homeheadersteps.yaml accountMenu).
    private static final String HEADER_ACCOUNT_MENU = "//header//span[normalize-space()='Account']";
    private static final int LOGIN_CONFIRM_WAIT_SECONDS = 15;
    public static final String CONTINUE_BTN = "//button[text()='Continue browsing']";
    public static final String CONTINUE_WITH_EMAIL_CTA = "continueWithEmailCta";
    public static final String CONTINUE_CTA = "continueCTA";
    public static final String ACCOUNT_LABEL = "accountLabel";
    private static final String BUSINESS_AT_A_GLANCE_POPUP = "//span[text()='Your business at a glance']";
    private static final String BUSINESS_AT_A_GLANCE_CLOSE =
            BUSINESS_AT_A_GLANCE_POPUP + "/following-sibling::button[@aria-label='Close']";

    private static final ThreadLocal<String> USER_EMAIL = new ThreadLocal<>();
    private static final ThreadLocal<String> USER_PASSWORD = new ThreadLocal<>();

    /** The email this thread's scenario logged in with, or null if it has not logged in. */
    public static String userEmail() {
        return USER_EMAIL.get();
    }

    /** The Base64-encoded password this thread's scenario logged in with, or null. */
    public static String userPassword() {
        return USER_PASSWORD.get();
    }

    public static void clearCredentials() {
        USER_EMAIL.remove();
        USER_PASSWORD.remove();
    }



    @Given("Go to session {string}")
    public void loginAndGoToSession(String sessionName) throws Exception {

        log.entering();
        if (!sessionName.equalsIgnoreCase("ccp")) {
            throw new RuntimeException("No session name given. Please provide a valid session name"
                    + " specified in the sessions yaml file.");
        }
        Grid.setSessionName("ccp");

        SessionBuilder.open(ConfigUtil.getSecretValue("ccpPortalUrl"));

        log.info(String.format("Browser ready and switched to session named %s", sessionName));
        log.exiting();
    }

    @And("ccp login with configured user")
    public void ccpLoginWithConfiguredUser() throws IOException {
        ccpLogin(UserConfig.getEmail(), UserConfig.getPassword());
        if (UserConfig.isGroup("ledger")) {
            closeBusinessAtAGlancePopupIfPresent();
        }
        deleteAllCartItems();
        closeBlockingDialogsWithin(10);
    }

    /** The ledger account lands on a "Your business at a glance" popup after login; close it. */
    private void closeBusinessAtAGlancePopupIfPresent() {
        try {
            new org.openqa.selenium.support.ui.WebDriverWait(Grid.driver(), Duration.ofSeconds(5))
                    .until(org.openqa.selenium.support.ui.ExpectedConditions
                            .visibilityOfElementLocated(By.xpath(BUSINESS_AT_A_GLANCE_POPUP)));
            clickButton(By.xpath(BUSINESS_AT_A_GLANCE_CLOSE));
            log.info("Closed 'Your business at a glance' popup");
        } catch (TimeoutException e) {
            log.info("'Your business at a glance' popup not shown within 5 seconds");
        }
    }

    @And("ccp login with {string} and {string}")
    public void ccpLogin(String emailId, String pass) throws IOException {
        log.entering(emailId, pass);
        USER_EMAIL.set(emailId);
        //convert given password to Base64
        String encodedPassword = Base64.getEncoder().encodeToString(pass.getBytes());
        USER_PASSWORD.set(encodedPassword);

        if(waitAndCheckIsElementVisible(LOGIN_POP_UP_CLOSE_BUTTON)){
            waitAndCheckIsElementClickable(LOGIN_POP_UP_CLOSE_BUTTON);
            waitAndClickOn(LOGIN_POP_UP_CLOSE_BUTTON);
        }
        log.info("Landing in home page");
        pageRefresh();
        sleep(3000);
        waitAndCheckIsElementVisible(LOGIN_CTA_BUTTON);
        waitAndClickOn(LOGIN_CTA_BUTTON);
        log.info("successfully clicked Login CTA button");
        waitAndCheckIsElementVisible(CONTINUE_WITH_EMAIL_CTA);
        waitAndClickOn(CONTINUE_WITH_EMAIL_CTA);
        waitAndCheckIsElementVisible(LOGIN_EMAIL);
        waitAndSetText(LOGIN_EMAIL, emailId);
        log.info("successfully entered Email");
        waitAndCheckIsElementVisible(CONTINUE_CTA);
        waitAndClickOn(CONTINUE_CTA);
        waitAndCheckIsElementVisible(LOGIN_PASSWORD);
        log.info("successfully entered to password");
        waitAndSetText(LOGIN_PASSWORD, pass);
        waitAndCheckIsElementVisible(CONTINUE_CTA);
        waitAndClickOn(CONTINUE_CTA);
        log.info("successfully clicked on Login button");
        sleep(1000);
        try {
            if (isDisplayed(By.xpath(USER_DATA_POPUP))) {

                sleep(500);
                if (isDisplayed(By.xpath(USER_DATA_POPUP))) {
                    clickButton(By.xpath(USER_DATA_POPUP));

                }
                waits(By.xpath(CONTINUE_BTN));
                clickButton(By.xpath(CONTINUE_BTN));
                pageRefresh();
            }
        }catch (Exception e)
        {
            pageRefresh();
        }
        // QA opens the "Your business at a glance" spotlight straight after login, over the
        // payment-due popup's close button below (10 logins in the 7 Oct 2026 allure run).
        closeBlockingDialogsWithin(BLOCKING_DIALOG_QUICK_WAIT_SECONDS);
        if (!isElementPresent(LOGIN_CTA_BUTTON)){
            log.info("CCP User is already logged in");
            if (isElementPresent(PAYMENT_DUE_POP_UP)){
                waitAndCheckIsElementVisible(PAYMENT_DUE_CLOSE);
                waitAndClickOn(PAYMENT_DUE_CLOSE);
            }
        }
        refreshAndWaitForPageLoad();
        closeBlockingDialogsWithin(10);
        assertLoggedIn(emailId);
    }

    /**
     * Fails the login step when the header does not show the Account menu afterwards.
     *
     * <p>Nothing above checks the outcome, so a rejected password or a session that did not stick
     * passed this step and surfaced later as an unrelated assertion - on pre-prod, 25 Sep 2026,
     * "Account menu is not displayed in the header" in the header suite.
     */
    private void assertLoggedIn(String emailId) {
        // Any visible match, not the first: the header renders the label twice, an aria-hidden
        // copy first and the visible one after it, so a first-match check never sees it.
        if (pollFor(() -> isRenderedNow(By.xpath(HEADER_ACCOUNT_MENU)), LOGIN_CONFIRM_WAIT_SECONDS)) {
            log.info("CCP login confirmed for " + emailId + " - Account menu is in the header");
            return;
        }
        boolean loginStillOffered = isElementPresent(LOGIN_CTA_BUTTON);
        throw new IllegalStateException("CCP login did not complete for " + emailId + ": no Account"
                + " menu in the header after " + LOGIN_CONFIRM_WAIT_SECONDS + "s"
                + (loginStillOffered ? " and the Login button is still shown" : "")
                + ", at " + Grid.driver().getCurrentUrl() + ". Check this account's password for"
                + " the environment, or whether the site rejected the login.");
    }

    @And("open Login page to enter email and password")
    public void openLoginPageToEnterEmailAndPassword() {
        if(waitAndCheckIsElementVisible(LOGIN_POP_UP_CLOSE_BUTTON)){
            waitAndCheckIsElementClickable(LOGIN_POP_UP_CLOSE_BUTTON);
            waitAndClickOn(LOGIN_POP_UP_CLOSE_BUTTON);
        }
        waitAndCheckIsElementVisible(LOGIN_CTA_BUTTON);
        waitAndClickOn(LOGIN_CTA_BUTTON);
        log.info("successfully clicked Login CTA button");
        waitAndCheckIsElementVisible(CONTINUE_WITH_EMAIL_CTA);
        waitAndClickOn(CONTINUE_WITH_EMAIL_CTA);
    }


    @Then("account logout")
     public  void ccpLogOut(){
        waitAndCheckIsElementVisible(ACCOUNT_LABEL);
        waitAndCheckIsElementClickable(3,ACCOUNT_LABEL);
        waitAndClickOn(ACCOUNT_LABEL);
        waitAndCheckIsElementVisible(CCP_LOG_OUT);
        waitAndCheckIsElementClickable(3,CCP_LOG_OUT);
        waitAndClickOn(CCP_LOG_OUT);
}

    public void defocusSearchOverlay() {
        try {
            Grid.driver().findElement(By.tagName("body")).click();
        } catch (Exception ignored) {
        }
    }
    public void waitForSearchOverlayToDisappear() {
        try {
            new org.openqa.selenium.support.ui.WebDriverWait(Grid.driver(), Duration.ofSeconds(10))
                    .until(driver -> {
                        try {
                            return driver.findElements(SEARCH_OVERLAY).isEmpty()
                                    || !driver.findElement(SEARCH_OVERLAY).isDisplayed();
                        } catch (Exception e) {
                            return true; // overlay not present in DOM
                        }
                    });
        } catch (Exception ignored) {
            log.info("Search Overlay Is not showing");
        }
    }

    public CustomerDetailsResponse getCustomerDetail(){
        String email = userEmail();
        if (email == null) {
            throw new IllegalStateException("getCustomerDetail() was called before this scenario"
                    + " logged in, so there are no credentials to query the customer record with."
                    + " Add a 'ccp login with ...' step to the scenario's Background.");
        }
        return ApiUtils.getCustomerDetails(email, userPassword());
    }

}