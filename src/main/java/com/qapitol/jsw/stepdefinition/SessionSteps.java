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
            /* SEVERE, not FINE. This is the real cause of the failure, and FINE is filtered out
             * of the run log - it only survives in raw surefire stdout.
             *
             * It has to be logged loudly because it does NOT reach the report on its own.
             * Sauron's ScreenshotBddListener fires on every finished step and calls
             * Grid.driver() to capture a screenshot; when the session failed to start there is
             * no driver, so the listener throws
             *     IllegalStateException("Driver not initialized. Is @WebTest/@MobileTest
             *     missing on class/method?")
             * from inside emitTestStepFinished, and THAT replaces this exception as the
             * reported test failure. The report then blames a missing @WebTest annotation and
             * says nothing about the browser never having started - which is why every scenario
             * in a run whose browser cannot start looks identical and useless.
             *
             * recordSessionStartFailure() carries the real cause to Hooks.afterScenario, which
             * writes it into the scenario's own report output. Do not remove it without
             * replacing the path from here to the report. */
            recordSessionStartFailure(e);
            log.log(Level.SEVERE, "Could not start the CCP browser session: " + e, e);
            throw new IllegalStateException(SESSION_START_DIAGNOSIS + e, e);
        }
        log.exiting();
    }

    /**
     * Prefixed to a session-start failure so the message carries its own diagnosis.
     *
     * <p>"session not created: Chrome instance exited" against a LOCAL grid is usually neither a
     * Chrome nor a chromedriver problem. Sauron randomises the hub's HTTP port per run, but Selenium
     * Grid's ZeroMQ event bus is FIXED on 4442/4443 - so nodes left behind by an earlier run keep
     * advertising on that bus, register themselves with the new hub, and the hub happily routes the
     * session to a long-dead node. Observed on 24 Aug 2026: the run spawned its node on port 28308,
     * and the hub handed the session to orphaned node 9917 from a previous run.
     */
    /** How many times to re-check for a usable driver while the session comes up. */
    private static final int SESSION_READY_ATTEMPTS = 20;
    private static final long SESSION_READY_POLL_MILLIS = 500;

    /**
     * The real reason this thread's browser session failed to start, kept until
     * {@code Hooks.afterScenario} can write it into the scenario report.
     *
     * <p>Per-thread because the suite runs {@code parallel="tests"}: a session failing in one
     * block must not be reported against a scenario in another.
     */
    private static final ThreadLocal<String> SESSION_START_FAILURE = new ThreadLocal<>();

    private static void recordSessionStartFailure(Exception e) {
        SESSION_START_FAILURE.set(e.toString());
    }

    /**
     * The session-start failure recorded on this thread, or null. Clears it, so a later scenario
     * that started its browser fine is not annotated with an earlier scenario's failure.
     */
    public static String consumeSessionStartFailure() {
        String failure = SESSION_START_FAILURE.get();
        SESSION_START_FAILURE.remove();
        return failure;
    }

    /**
     * Waits for a usable WebDriver instead of assuming the first {@code Grid.driver()} call works.
     *
     * <p>Between two scenarios the previous session is torn down and the next one is created, and
     * {@code Grid.driver()} throws {@code IllegalStateException("Driver not initialized...")} for
     * the duration of that window. A single attempt therefore fails on nothing more than timing.
     *
     * <p>Measured on Jenkins build #233: in the retry stage four scenarios failed within 131ms of
     * each other, each preceded by six "Stopping keep sessions alive periodic task" lines and
     * followed by "Added test session to the Grid" AFTER the failure - the session was arriving
     * microseconds too late. It happened only in the retry stage (0 in the main run, 22 in the
     * retry), which re-runs testng-failed.xml and so churns sessions far harder.
     *
     * <p>20 attempts x 500ms = 10 seconds. That covers the teardown window; it does NOT cover a
     * session that can never start. So if EVERY scenario in a run fails here, the cause is
     * environmental - no Chrome on the agent, or a grid that cannot serve the number of
     * concurrent sessions the suite's thread-count asks for - not this race.
     */
    private void awaitDriverReady() {
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
        log.log(Level.SEVERE, "No usable browser session after "
                + (SESSION_READY_ATTEMPTS * SESSION_READY_POLL_MILLIS / 1000) + "s", last);
        throw new IllegalStateException(SESSION_START_DIAGNOSIS + last, last);
    }

    private static final String SESSION_START_DIAGNOSIS =
            "Could not start the CCP browser session. If the underlying message is"
            + " 'session not created: Chrome instance exited' on a local grid, first suspect"
            + " orphaned selenium-server processes from an earlier run rather than a Chrome or"
            + " chromedriver version problem: Grid's ZeroMQ event bus is fixed on ports 4442/4443,"
            + " so stale nodes keep advertising there and get registered with the new hub, which"
            + " then routes the session to a dead node. Check for leftovers with"
            + " `netstat -ano | findstr \"4442 4443\"` and terminate any selenium-server java"
            + " processes that are not part of this run. Underlying failure: ";
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