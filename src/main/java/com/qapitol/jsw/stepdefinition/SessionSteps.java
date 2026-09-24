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
    public static final String CONTINUE_BTN = "//button[text()='Continue browsing']";
    public static final String CONTINUE_WITH_EMAIL_CTA = "continueWithEmailCta";
    public static final String CONTINUE_CTA = "continueCTA";
    public static final String ACCOUNT_LABEL = "accountLabel";

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
            log.info("CCP User is already logged in");
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
            throw new IllegalStateException("getCustomerDetail() was called before this scenario"
                    + " logged in, so there are no credentials to query the customer record with."
                    + " Add a 'ccp login with ...' step to the scenario's Background.");
        }
        return ApiUtils.getCustomerDetails(email, userPassword());
    }

}