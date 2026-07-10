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
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.util.Base64;



import java.time.Duration;
import java.util.logging.Level;

import static com.qapitol.jsw.utils.ApiUtils.deleteAllCartItems;
import static com.qapitol.jsw.utils.CustomUtil.*;


public  class SessionSteps extends AbstractWebPage {
    private static final String LOGIN_CTA_BUTTON = "loginButton";
    private static final String LOGIN_VIA_OTP = "loginViaOtp";
    private static final String LOGIN_EMAIL = "ccpLoginEmail";
    private static final String LOGIN_PASSWORD = "ccpLoginPassword";
    private static final String CCP_LOGIN_CTA = "ccpLoginCTA";
    public static final String CCP_DROP_DOWN="ccpDropDown";
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
            log.log(Level.FINE, "initZohoSession failed.", e);
            throw e;
        }
        log.exiting();
    }
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
        waitAndCheckIsElementVisible(LOGIN_VIA_OTP);
        waitAndClickOn(LOGIN_VIA_OTP);
        waitAndCheckIsElementVisible(LOGIN_EMAIL);
        waitAndSetText(LOGIN_EMAIL, emailId);
        log.info("successfully entered Email");
        waitAndCheckIsElementVisible(CCP_LOGIN_CTA);
        waitAndClickOn(CCP_LOGIN_CTA);
        waitAndCheckIsElementVisible(LOGIN_PASSWORD);
        log.info("successfully entered to password");
        waitAndSetText(LOGIN_PASSWORD, pass);
        waitAndCheckIsElementVisible(CCP_LOGIN_CTA);
        waitAndClickOn(CCP_LOGIN_CTA);
        log.info("successfully clicked on Login button");
//        sleep(100);
//        if(isDisplayed(By.xpath(CLOE_POPUP)))
//        {
//            clickButton(By.xpath(CLOE_POPUP));
//        }
//        sleep(100);
//        if(isDisplayed(By.xpath(MANAGE_POPUP_CLOSE)))
//        {
//            clickButton(By.xpath(MANAGE_POPUP_CLOSE));
//        }
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
        //
//        if(isDisplayed(By.xpath(PAYMENT_REMINDER_POPUP)))
//        {
//            clickButton(By.xpath(PAYMENT_REMINDER_POPUP));
//        }
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
        waitAndCheckIsElementVisible(LOGIN_VIA_OTP);
        waitAndClickOn(LOGIN_VIA_OTP);
    }


    @Then("account logout")
     public  void ccpLogOut(){
        waitAndCheckIsElementVisible(CCP_DROP_DOWN);
        waitAndCheckIsElementClickable(3,CCP_DROP_DOWN);
        waitAndClickOn(CCP_DROP_DOWN);
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


}


