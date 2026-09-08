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
import java.util.concurrent.atomic.AtomicInteger;
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

    /**
     * The credentials the CURRENT SCENARIO logged in with, per thread.
     *
     * <p>These were {@code public static String}, which is a cross-scenario data leak as soon as
     * more than one scenario is in flight. The suite runs data-provider-thread-count scenarios
     * concurrently inside a single {@code <test>} block, so: thread A logs in as X, thread B logs in
     * as Y and overwrites both fields, then A calls {@link #getCustomerDetail()} and receives Y's
     * customer record - and asserts X's UI against it.
     *
     * <p>It fails silently rather than loudly. Nothing throws; the wrong customer is simply compared,
     * and it only becomes visible when the two accounts happen to differ on an asserted field. The
     * Profile block's "Validate personal details/company details against the customer record"
     * scenarios are exactly this comparison, and they run in the same block.
     *
     * <p>Same fix as {@link #SESSION_START_FAILURE} above and {@code ThreadLocalSoftAssert}: keep one
     * shared handle, store the value per thread. Cleared by {@link #clearCredentials()} from
     * {@code Hooks.beforeScenario} so a pooled TestNG thread cannot serve a stale value to a later
     * scenario that never logged in.
     */
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

    /**
     * Drops this thread's credentials. Called at the start of every scenario, not the end: a
     * scenario that fails before logging in never reaches its own teardown, and TestNG hands the
     * same pool thread to the next scenario.
     */
    public static void clearCredentials() {
        USER_EMAIL.remove();
        USER_PASSWORD.remove();
    }



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
            // Already diagnosed AND already recorded by awaitDriverReady(), which throws from
            // inside this try. Re-wrapping printed the whole diagnosis paragraph twice.
            if (e instanceof IllegalStateException
                    && String.valueOf(e.getMessage()).startsWith(DIAGNOSIS_PREFIX)) {
                throw (IllegalStateException) e;
            }
            String diagnosis = diagnose(e);
            recordSessionStartFailure(diagnosis);
            log.log(Level.SEVERE, diagnosis, e);
            throw new IllegalStateException(diagnosis, e);
        }
        log.exiting();
    }

    /**
     * Waits for a usable WebDriver on a budget that depends on what the grid has already proved.
     *
     * <p>TWO BUDGETS, because "no driver yet" has two completely different causes and the same
     * timeout cannot serve both:
     *
     * <ul>
     *   <li>COLD - nothing has started a session yet, so the grid may be dead. One second, for a
     *       millisecond-scale race: on Jenkins build #233 four scenarios failed within 131ms of
     *       each other (21:15:50.644 / .698 / .743 / .775), each with "Added test session to the
     *       Grid" arriving AFTER the failure - the session was nine milliseconds late. A ten-second
     *       poll bought nothing for that and cost ten seconds per scenario when no session was ever
     *       coming.</li>
     *   <li>WARM - a session has already started, so the grid works and this scenario is queuing
     *       for a browser slot. Minutes, not milliseconds. Build #244 is the case: one second was
     *       applied to a scenario waiting its turn behind 2 Chrome slots, and it lost.</li>
     * </ul>
     *
     * <p>The suite-wide latch is likewise conditional. A grid that never came up dooms every
     * scenario and should short-circuit the rest of the run - but a single warm timeout does not,
     * and treating it as fatal is exactly what turned build #244's one queuing loss into 44 broken
     * scenarios while the grid went on serving 131 sessions.
     */
    private static final long SESSION_READY_POLL_MILLIS = 125;

    /**
     * COLD budget - nothing has started a session yet, so the grid may genuinely be dead. Kept at
     * one second for the reason above: fail fast rather than pay a long poll per scenario.
     */
    private static final long COLD_BUDGET_MILLIS = 1_000;

    /**
     * WARM budget - a session HAS started in this run, so the grid demonstrably works and this
     * scenario is queuing for a browser slot rather than facing a dead grid.
     *
     * <p>Sized from Jenkins build #244. The agent reported "Detected 2 available processors" and
     * Selenium sized the node to "Adding Chrome ... 2 times", while the suite runs
     * data-provider-thread-count scenarios at once. At 5 scenarios against 2 slots, three of them
     * must wait for a slot, and a scenario holds its browser for minutes - so a queued scenario
     * legitimately waits far longer than the cold budget. One second was never a queuing timeout.
     */
    private static final long WARM_BUDGET_MILLIS = 120_000;

    /**
     * How many scenarios in a row may exhaust the WARM budget before the grid is declared dead.
     *
     * <p>Without this, a grid that dies mid-run after some successes would make every remaining
     * scenario pay the full warm budget - 100 scenarios x 120s is hours of a build spent waiting
     * for something that is never coming. Three strikes bounds that at ~6 minutes while still
     * absorbing the single isolated timeout that build #244 turned into 43 write-offs.
     */
    private static final int WARM_FAILURES_BEFORE_LATCH = 3;

    private static volatile String gridFailure;

    /**
     * True once ANY scenario in this run has obtained a usable driver.
     *
     * <p>This is what separates "the grid never came up" from "this scenario could not get a slot
     * in time", and build #244 is why it exists. That run created 131 sessions and passed 58
     * scenarios over 34 healthy minutes; then at 11:11:05 one scenario exhausted its 1000ms wait,
     * {@link #gridFailure} latched suite-wide, and the remaining 43 scenarios were written off
     * against a grid that was still working. The hub was fine throughout - one "Added node", zero
     * "Timed out waiting for 127.0.0.1 to initialize".
     */
    private static volatile boolean sessionEverStarted;

    /**
     * Consecutive scenarios that have exhausted the WARM budget. Reset by any success.
     *
     * <p>Atomic, not a volatile int: up to data-provider-thread-count scenarios can time out at the
     * same moment, and {@code ++} on a volatile field is a read-modify-write that silently loses
     * increments under exactly that concurrency.
     */
    private static final AtomicInteger CONSECUTIVE_WARM_FAILURES = new AtomicInteger();

    /**
     * The session-start failure for the scenario running on THIS thread, read back by
     * {@code Hooks.reportSessionStartFailure} so the real cause reaches the Allure report rather
     * than only the console.
     *
     * <p>Thread-local, not static: testng_bdd.xml runs at thread-count 5, so a shared field would
     * let one block's failure be reported against another block's scenario. That is the opposite of
     * {@link #gridFailure}, which is deliberately suite-wide because a grid that never started
     * dooms every thread equally.
     */
    private static final ThreadLocal<String> SESSION_START_FAILURE = new ThreadLocal<>();

    /** Records the text that should appear in the report for this scenario's session failure. */
    private static void recordSessionStartFailure(String failure) {
        SESSION_START_FAILURE.set(failure);
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

    private void awaitDriverReady() {
        if (gridFailure != null) {
            String skipped = "Skipping the browser start: the Selenium grid failed for "
                    + WARM_FAILURES_BEFORE_LATCH + " scenarios in a row (or never came up at all),"
                    + " so it is treated as dead for the rest of this run. Fix the first failure and"
                    + " the rest go with it. First failure was: " + gridFailure;
            // Recorded as well as thrown - otherwise every scenario after the first shows the
            // bare "Driver not initialized" message in the report with no pointer to the cause.
            recordSessionStartFailure(skipped);
            throw new IllegalStateException(skipped);
        }

        // A demonstrably working grid gets the long budget; an unproven one fails fast.
        boolean warm = sessionEverStarted;
        long budgetMillis = warm ? WARM_BUDGET_MILLIS : COLD_BUDGET_MILLIS;
        long deadline = System.currentTimeMillis() + budgetMillis;

        IllegalStateException last = null;
        int attempt = 0;
        do {
            attempt++;
            try {
                Grid.driver();
                // Order matters: record the success BEFORE returning, so the next scenario to queue
                // gets the warm budget even if this one is the only session that ever worked.
                sessionEverStarted = true;
                CONSECUTIVE_WARM_FAILURES.set(0);
                if (attempt > 1) {
                    log.info("Browser session became usable on attempt " + attempt
                            + " (" + (warm ? "warm" : "cold") + " budget)");
                }
                return;
            } catch (IllegalStateException e) {
                last = e;
                sleep(SESSION_READY_POLL_MILLIS);
            }
        } while (System.currentTimeMillis() < deadline);

        String diagnosis = diagnose(last);
        // Both, and they are not redundant: SESSION_START_FAILURE carries the full diagnosis into
        // THIS scenario's report, gridFailure short-circuits every later scenario in the run.
        recordSessionStartFailure(diagnosis);

        /* Latch only when the grid has not proved itself, or has stopped working for several
         * scenarios running. A single warm timeout is a queuing loss, not a dead grid - latching on
         * it is what cost build #244 forty-three scenarios while 131 sessions were being served. */
        if (!warm) {
            gridFailure = String.valueOf(last);
        } else {
            // Captured, not re-read: another thread can reset this to 0 between the increment and
            // the log, which would print a count that did not choose this branch.
            int warmFailures = CONSECUTIVE_WARM_FAILURES.incrementAndGet();
            if (warmFailures >= WARM_FAILURES_BEFORE_LATCH) {
                gridFailure = String.valueOf(last);
                log.log(Level.SEVERE, warmFailures + " scenarios in a row could not obtain a session"
                        + " despite the grid having worked earlier - treating it as dead for the rest"
                        + " of this run.");
            } else {
                log.log(Level.SEVERE, "Scenario could not obtain a browser session within "
                        + budgetMillis + "ms, but the grid has served sessions in this run, so it is"
                        + " NOT being latched. Consecutive warm failures: " + warmFailures + " of "
                        + WARM_FAILURES_BEFORE_LATCH + ". Most likely this scenario queued for a"
                        + " browser slot: check whether thread-count x data-provider-thread-count"
                        + " exceeds the agent's Chrome slots.");
            }
        }

        log.log(Level.SEVERE, "No usable browser session after " + budgetMillis + "ms ("
                + (warm ? "warm" : "cold") + " budget, " + attempt + " attempts). " + diagnosis, last);
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
        String email = userEmail();
        if (email == null) {
            // Was a silent wrong-customer comparison when these were static: this thread would read
            // whichever scenario logged in last, on any thread. Now it is a named failure.
            throw new IllegalStateException("getCustomerDetail() was called before this scenario"
                    + " logged in, so there are no credentials to query the customer record with."
                    + " Add a 'ccp login with ...' step to the scenario's Background.");
        }
        return ApiUtils.getCustomerDetails(email, userPassword());
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