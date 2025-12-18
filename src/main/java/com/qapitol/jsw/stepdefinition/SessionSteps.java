package com.qapitol.jsw.stepdefinition;



import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.AfterAll;
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
    private static final String CCP_DROP_DOWN="ccpDropDown";
    private static final String CCP_LOG_OUT="ccpLogout";


    @Given("Go to session {string} {string} {string}")
    public void loginAndGoToSession(String sessionName,String email,String password) throws Exception {
        log.entering();
        if (sessionName.equalsIgnoreCase("ccp")) {
            // Switching to "zoho_creator" session present in the sessions.yaml
            Grid.setSessionName("ccp");
            if(!Grid.getTestSession().isStarted()) {
                log.info("Starting browser with zoho creator...");
                initCCPSession(ConfigUtil.resolve("ccpPortalUrl"),email,password);
                log.info("Started browser and navigated to Zoho creator.");
            }
        } else {
            throw new RuntimeException("No session name given. Please provide a valid session name specified in the sessions yaml file.");
        }
        log.info(String.format("Browser already running and switched to session named %s", sessionName));
        log.exiting();
    }
    public void initCCPSession(String url,String email,String password) {
        log.entering();
        try {
            Grid.driver().manage().window().maximize();
            Grid.driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            Grid.driver().get(url);
            checkLoginPage();
            ccpLogin(email,password);
        }  catch (Exception e) {
            log.log(Level.FINE, "initZohoSession failed.", e);
            throw e;
        }
        log.exiting();
    }
    public void ccpLogin(String emailId, String pass) {
        log.entering(emailId, pass);
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
        waitAndClickOn(CCP_LOGIN_CTA);// Login
        log.exiting();
    }

    @Then("account logout")
     public void ccpLogOut(){
        waitAndCheckIsElementClickable(3,CCP_DROP_DOWN);
        waitAndClickOn(CCP_DROP_DOWN);
        waitAndCheckIsElementClickable(3,CCP_LOG_OUT);
        waitAndClickOn(CCP_LOG_OUT);
        sleep(2000);
}

     public void checkLoginPage(){
        boolean condition = Grid.driver().findElement(By.xpath("//a[contains(text(),'My orders')]")).isDisplayed();
         if (condition){
            ccpLogOut();
        }
}

}


