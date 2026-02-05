package com.qapitol.jsw.stepdefinition;



import com.qapitol.jsw.utils.ApiUtils;
import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;



import java.time.Duration;
import java.util.logging.Level;


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
    private static final String PAYMENT_DUE_CLOOSE="ccpHomePagePaymentDueClose";

    public static String userEmail;
    public static String userPassword;



    @Given("Go to session {string}")
    public void loginAndGoToSession(String sessionName) throws Exception {
        log.entering();
        if (sessionName.equalsIgnoreCase("ccp")) {
            // Switching to "zoho_creator" session present in the sessions.yaml
            Grid.setSessionName("ccp");
            if(!Grid.getTestSession().isStarted()) {
                log.info("Starting browser with zoho creator...");
                initCCPSession(ConfigUtil.getSecretValue("ccpPortalUrl"));
                log.info("Started browser and navigated to Zoho creator.");
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
            Grid.driver().manage().window().maximize();
            Grid.driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            Grid.driver().get(url);
            sleep(1000);
            defocusSearchOverlay();
            sleep(2000);
            //Logout the user incase already logged for jenkin's run
            if (!isElementPresent(LOGIN_CTA_BUTTON)) {
                log.info("CCP User is already logged in, logging out to continue with fresh login");
                if (isElementPresent(PAYMENT_DUE_POP_UP)){
                   waitAndCheckIsElementVisible(PAYMENT_DUE_CLOOSE);
                   waitAndClickOn(PAYMENT_DUE_CLOOSE);
                }
                ccpLogOut();
            }
        }  catch (Exception e) {
            log.log(Level.FINE, "initZohoSession failed.", e);
            throw e;
        }
        log.exiting();
    }
    @And("ccp login with {string} and {string}")
    public void ccpLogin(String emailId, String pass) {
        log.entering(emailId, pass);
        userEmail = emailId;
        userPassword= "SnN3QDIwMjU=";
//        waitForSearchOverlayToDisappear();
        waitAndCheckIsElementVisible(LOGIN_CTA_BUTTON);
        waitAndClickOn(LOGIN_CTA_BUTTON); // click on login CTA button
        waitAndCheckIsElementVisible(LOGIN_VIA_OTP);
        waitAndClickOn(LOGIN_VIA_OTP); //Click on Login with OTP
        waitAndCheckIsElementVisible(LOGIN_EMAIL);
        waitAndSetText(LOGIN_EMAIL, emailId); // Give you Email
        waitAndCheckIsElementVisible(CCP_LOGIN_CTA);
        waitAndClickOn(CCP_LOGIN_CTA);
        waitAndCheckIsElementVisible(LOGIN_PASSWORD);
        waitAndSetText(LOGIN_PASSWORD, pass);// Give the password
        waitAndCheckIsElementVisible(CCP_LOGIN_CTA);
        waitAndClickOn(CCP_LOGIN_CTA);// Login to continue
        log.exiting();
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
        }
    }

    public String getCustomerDetail(){
        return ApiUtils.getCustomerDetails(userEmail,userPassword);
    }


}


