package com.qapitol.jsw.stepdefinition;



import com.qapitol.jsw.apitest.pojos.order.customerDetails.CustomerDetailsResponse;
import com.qapitol.jsw.utils.ApiUtils;
import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.jsw.utils.UserConfig;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.*;

import java.io.IOException;
import java.util.Base64;
import java.time.Duration;
import java.util.logging.Level;

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
    public static final String CONTINUE_BTN = "//button[text()='Continue browsing']";
    public static final String CONTINUE_WITH_EMAIL_CTA = "continueWithEmailCta";
    public static final String CONTINUE_CTA = "continueCTA";
    public static final String CONFIRM_BUSINESS_DETAILS_DIALOG = "confirmBusinessDetails";
    public static final String ACCOUNT_LABEL = "accountLabel";

    public static String userEmail;
    public static String userPassword;



    @Given("Go to session {string}")
    public void loginAndGoToSession(String sessionName) throws Exception {

        log.entering();
        if (sessionName.equalsIgnoreCase("ccp")) {
            // Switching to "zoho_creator" session present in the sessions.yaml
            Grid.setSessionName("ccp");
            if(!Grid.getTestSession().isStarted()) {
                log.info("Starting browser with CCP...");
                initCCPSession(ConfigUtil.getSecretValue("ccpPortalUrl"));
                log.info("Started browser and navigated to CCP portal.");
            }
        } else {
            throw new RuntimeException("No session name given. Please provide a valid session name specified in the sessions yaml file.");
        }
        log.info(String.format("Browser already running and switched to session named %s", sessionName));
        log.exiting();
    }
    
    public void initCCPSession(String url) {
        log.entering();
        try {
            awaitDriverReady();

            // Set implicit wait first
            Grid.driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            
            // Handle window management - check if headless mode
            try {
                boolean isHeadless = ((com.qapitol.sauron.web.sessions.WebTestSession) Grid.getTestSession()).isBrowserRunHeadless();
                if (!isHeadless) {
                    // Only maximize window in non-headless mode
                    try {
                        Grid.driver().manage().window().maximize();
                        log.info("Browser window maximized successfully");
                    } catch (WebDriverException e) {
                        log.warning("maximize() failed, falling back to setSize: " + e.getMessage());
                        Grid.driver().manage().window().setSize(new Dimension(1920, 1080));
                    }
                } else {
                    log.info("Running in headless mode - skipping window maximize");
                    // Set viewport size via JavaScript for headless
                    try {
                        Grid.driver().executeScript("window.resizeTo(1920, 1080);");
                    } catch (Exception e) {
                        log.warning("Failed to set viewport via JavaScript: " + e.getMessage());
                    }
                }
            } catch (Exception e) {
                log.warning("Error checking headless mode: " + e.getMessage() + ". Attempting window size setting.");
                try {
                    Grid.driver().manage().window().setSize(new Dimension(1920, 1080));
                } catch (Exception ex) {
                    log.warning("Window size setting also failed: " + ex.getMessage());
                }
            }
            
            Grid.driver().get(url);
            log.info("Successfully navigated to URL: " + url);
            sleep(2000);  // Extra wait for page load in headless mode
           // defocusSearchOverlay();
            //Logout the user incase already logged for jenkin's run
           // if (!isElementPresent(LOGIN_CTA_BUTTON))
            {
             //   log.info("CCP User is already logged in, logging out to continue with fresh login");
               // if (isElementPresent(PAYMENT_DUE_POP_UP))
                {
                 //  waitAndCheckIsElementVisible(PAYMENT_DUE_CLOSE);
                  // waitAndClickOn(PAYMENT_DUE_CLOSE);
                }
               // ccpLogOut();
            }
        }  catch (Exception e) {
            // Do NOT re-wrap something awaitDriverReady() has already diagnosed. It throws from
            // inside this try, so the old unconditional wrap printed the whole diagnosis paragraph
            // twice for every failure.
            if (e instanceof IllegalStateException
                    && String.valueOf(e.getMessage()).startsWith(DIAGNOSIS_PREFIX)) {
                throw (IllegalStateException) e;
            }
            log.log(Level.SEVERE, "Could not start the CCP browser session: " + e, e);
            throw new IllegalStateException(diagnose(e), e);
        }
        log.exiting();
    }

    /**
     * Waits briefly for a usable WebDriver, absorbing the window in which the previous scenario's
     * session is still being torn down.
     *
     * <p>ONE SECOND, not ten. This exists for a millisecond-scale race: on Jenkins build #233 four
     * scenarios failed within 131ms of each other (21:15:50.644 / .698 / .743 / .775), each with
     * "Added test session to the Grid" arriving AFTER the failure - the session was nine
     * milliseconds late. A ten-second poll bought nothing for that and cost ten seconds per
     * scenario on the far more common case below, where no session is ever coming.
     *
     * <p>When the grid itself failed to start, no amount of polling helps and every scenario is
     * doomed, so the first failure is remembered and the rest fail immediately against it.
     */
    private static final int SESSION_READY_ATTEMPTS = 8;
    private static final long SESSION_READY_POLL_MILLIS = 125;

    /**
     * Set the first time the grid proves unusable. Later scenarios then fail in microseconds with a
     * pointer to the first failure, instead of each re-polling and re-printing the full diagnosis -
     * a 125-scenario suite used to emit the same paragraph 125 times.
     */
    private static volatile String gridFailure;

    private void awaitDriverReady() {
        if (gridFailure != null) {
            throw new IllegalStateException("Skipping the browser start: the Selenium grid already"
                    + " failed earlier in this run, so no scenario in it can obtain a session."
                    + " Fix the first failure and the rest go with it. First failure was: "
                    + gridFailure);
        }

        IllegalStateException last = null;
        for (int attempt = 1; attempt <= SESSION_READY_ATTEMPTS; attempt++) {
            try {
                Grid.driver();
                if (attempt > 1) {
                    log.info("Browser session became usable on attempt " + attempt);
                }
                return;
            } catch (IllegalStateException e) {
                last = e;
                sleep(SESSION_READY_POLL_MILLIS);
            }
        }

        String diagnosis = diagnose(last);
        gridFailure = String.valueOf(last);
        log.log(Level.SEVERE, "No usable browser session after "
                + (SESSION_READY_ATTEMPTS * SESSION_READY_POLL_MILLIS) + "ms. " + diagnosis, last);
        throw new IllegalStateException(diagnosis, last);
    }

    /** Marker so {@link #initCCPSession} can tell an already-diagnosed failure from a fresh one. */
    private static final String DIAGNOSIS_PREFIX = "Could not start the CCP browser session.";

    /**
     * Builds a session-start message whose advice matches the failure actually seen.
     *
     * <p>The previous version stated the orphaned-grid theory unconditionally, prefixed with "if the
     * underlying message is 'session not created'". For the commonest failure - "Driver not
     * initialized" - that sent readers to look at Chrome and chromedriver versions when the real
     * problem was that no session had ever been created. The two causes are unrelated and now get
     * separate advice.
     */
    private static String diagnose(Throwable cause) {
        String text = (cause == null) ? "" : String.valueOf(cause.getMessage());
        String advice;
        if (text.contains("Driver not initialized")) {
            advice = NO_SESSION_ADVICE;
        } else if (text.contains("session not created") || text.contains("Chrome instance exited")) {
            advice = ORPHANED_GRID_ADVICE;
        } else {
            advice = GENERIC_ADVICE;
        }
        return DIAGNOSIS_PREFIX + " " + advice + " Underlying failure: " + cause;
    }

    /**
     * "Driver not initialized" means no WebDriver was ever bound to this thread - Chrome did not
     * crash, it was never asked to start. Nothing in this repository starts a session:
     * {@code Grid.getTestSession().isStarted()} in loginAndGoToSession is the only reference to
     * session lifecycle anywhere in src/main, and it only READS the flag. The session is created by
     * Sauron's own listener, so that is where to look.
     */
    private static final String NO_SESSION_ADVICE =
            "No browser session was ever created for this scenario - Chrome did not crash, it was"
            + " never started, so this is NOT a Chrome or chromedriver version problem. The session"
            + " is created by Sauron's SauronWebListener.onStartTest via"
            + " LocalGridManager.spawnLocalHub, not by this suite. In the console log find"
            + " \"Executing command [java, ... selenium-server-*.jar, hub, --host, 127.0.0.1, -p,"
            + " <port>]\" and read what follows it: \"Added node ... at http://127.0.0.1:<port>\""
            + " means the hub is fine and the cause is elsewhere, while \"Timed out waiting for"
            + " 127.0.0.1 to initialize\" means the hub never came up and no scenario in this run"
            + " can get a browser.";

    /**
     * The orphaned-grid theory, now stated only when the symptom actually matches. Sauron randomises
     * the hub's HTTP port per run, but Grid's ZeroMQ event bus is FIXED on 4442/4443, so nodes left
     * behind by an earlier run keep advertising there, register with the new hub, and the hub routes
     * the session to a long-dead node. Observed 24 Aug 2026: the run spawned its node on port 28308
     * while the hub handed the session to orphaned node 9917 from a previous run.
     */
    private static final String ORPHANED_GRID_ADVICE =
            "Chrome was asked to start and exited. On a LOCAL grid suspect orphaned selenium-server"
            + " processes from an earlier run before suspecting Chrome or chromedriver versions:"
            + " Grid's ZeroMQ event bus is fixed on ports 4442/4443, so stale nodes keep advertising"
            + " there, get registered with the new hub, and the hub then routes the session to a"
            + " dead node. Check with `netstat -ano | findstr \"4442 4443\"` (Windows) or"
            + " `lsof -i:4442 -i:4443` (Linux) and terminate any selenium-server java process that"
            + " is not part of this run.";

    private static final String GENERIC_ADVICE =
            "The failure below is not one of the two known session-start causes (no session created,"
            + " or Chrome exiting on a stale local grid), so read it directly rather than assuming"
            + " either.";
    @And("ccp login with configured user")
    public void ccpLoginWithConfiguredUser() throws IOException {
        ccpLogin(UserConfig.getEmail(), UserConfig.getPassword());
        deleteAllCartItems();
    }

    @And("ccp login with {string} and {string}")
    public void ccpLogin(String emailId, String pass) throws IOException {
        log.entering(emailId, pass);
        userEmail = emailId;
        //convert given password to Base64
        String encodedPassword = Base64.getEncoder().encodeToString(pass.getBytes());
        userPassword= encodedPassword;

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
        if (!isElementPresent(LOGIN_CTA_BUTTON)){
            log.info("CCP User is already logged in, logging out to continue with fresh login");
            if (isElementPresent(PAYMENT_DUE_POP_UP)){
                waitAndCheckIsElementVisible(PAYMENT_DUE_CLOSE);
                waitAndClickOn(PAYMENT_DUE_CLOSE);
            }
        }
        refreshAndWaitForPageLoad();
        confirmBusinessDetailsIfDialogPresent();
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
        return ApiUtils.getCustomerDetails(userEmail,userPassword);
    }

    private void confirmBusinessDetailsIfDialogPresent() {
        try{
            if (isElementPresent(CONFIRM_BUSINESS_DETAILS_DIALOG)) {
                Grid.driver().findElement(By.xpath("//div[@role='dialog']/div/button")).click();
                log.info("Business details confirmation dialog was present and has been closed.");
            }
        } catch (Exception e) {
            log.info("No business details confirmation dialog was present.");
        }
    }
}