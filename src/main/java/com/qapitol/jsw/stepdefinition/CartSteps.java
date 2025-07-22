package com.qapitol.jsw.stepdefinition;

import com.qapitol.sauron.common.configuration.Config;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.qapitol.jsw.utils.CustomUtills.softAssert;

public class CartSteps extends AbstractWebPage {

    private static final String LOGIN_CTA_BUTTON = "loginButton";
    private static final String LOGIN_VIA_OTP = "loginViaOtp";
    private static final String LOGIN_EMAIL = "ccpLoginEmail";
    private static final String LOGIN_PASSWORD = "ccpLoginPassword";
    private static final String CCP_LOGIN_CTA = "ccpLoginCTA";
    private static final String HR_COIL = "hrCoilE250A";
    private static final String THICKNESS = "thickness";
    private static final String WIDTH = "width";
    private static final String QUANTITY = "quantity";
    private static final String ADD_TO_CART_CTA = "addToCart";
    private static final String VIEW_CART = "viewCart";
    private static final String SUBMIT_REQUIREMENT="submitRequirement";
    private static final String DELETE_LINE_ITEM="deleteLineItem";
    private static final String DELETE_CTA_BUTTON="deleteCta";
    private static final String CEMENT_PLP="cement";
    private static final String CEMENT_PDP="cementPDP";
    private static final String CEMENT_QUANTITY="cementQuantity";
    private static final String STAINLESS_STEEL_PLP="stainlessSteelPlp";
    private static final String STAINLESS_STEEL_PDP ="stainlessSteelPDP";
    private static final String STAINLESS_STEEL_THICKNESS="stainlessSteelThickness";
    private static final String STAINLESS_STEEL_WIDTH="stainlessSteelWidth";
    private static final String STAINLESS_STEEL_LENGTH="stainlessSteelLength";
    private static final String SF_LOGIN_EMAIL="sfEmail";
    private static final String SF_LOGIN_PASSWORD="sfPassword";
    private static final String SF_LOGIN="sfLogin";
    private static final String SF_SEARCH="sfSearch";
    private static final String RCV2_CHECKOUT="rcCheckout";
    private static final String MY_REQUIREMENT_PAGE="myRequirementPage";
    private static final String REQUIREMENT_ID="requirementId";


    String requirementId;

    @When("user open the portal and login with valid credentials {string} {string}")
    public void user_open_the_portal_and_login_with_valid_credentials(String username, String password) {
        Grid.driver().get(Config.getConfigProperty("ccpPortalUrl"));
        Grid.driver().manage().window().maximize();
        waitAndCheckIsElementVisible(LOGIN_CTA_BUTTON);
        waitAndClickOn(LOGIN_CTA_BUTTON); // click on login CTA button
        waitAndCheckIsElementVisible(LOGIN_VIA_OTP);
        waitAndClickOn(LOGIN_VIA_OTP); //Click on Login with OTP
        waitAndCheckIsElementVisible(LOGIN_EMAIL);
        waitAndSetText(LOGIN_EMAIL, username); // Give you Email
        waitAndCheckIsElementVisible(LOGIN_PASSWORD);
        waitAndSetText(LOGIN_PASSWORD, password);// Give the password
        waitAndCheckIsElementVisible(CCP_LOGIN_CTA);
        waitAndClickOn(CCP_LOGIN_CTA);// Login
    }

    @When("user open HR coil pdp")
    public void user_open_HR_coil_pdp() {
        sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) Grid.driver();
        js.executeScript("window.scrollBy(0,900);");
        sleep(2000);
        waitAndCheckIsElementPresent(HR_COIL);
        waitAndCheckIsElementClickable(HR_COIL);
        waitAndClickOn(HR_COIL);
    }

    @And("select all the attribute and add to cart")
    public void select_all_the_attribute_and_add_to_cart() {
        waitAndCheckIsElementPresent(THICKNESS);
        waitAndClickOn(THICKNESS);
        waitAndCheckIsElementPresent(WIDTH);
        waitAndClickOn(WIDTH);
        sleep(3000);
        waitAndCheckIsElementPresent(QUANTITY);
        waitAndSetText(QUANTITY, "10");
    }
    @And("user will add cement product to cart")
    public void user_will_add_cement_product_to_cart(){
       waitAndCheckIsElementPresent(CEMENT_PLP);
       waitAndClickOn(CEMENT_PLP);
       waitAndCheckIsElementPresent(CEMENT_PDP);
       waitAndClickOn(CEMENT_PDP);
       waitAndCheckIsElementPresent(CEMENT_QUANTITY);
       waitAndSetText(CEMENT_QUANTITY,"120");
       sleep(2000);
       waitAndCheckIsElementPresent(ADD_TO_CART_CTA);
       waitAndClickOn(ADD_TO_CART_CTA);
       waitAndCheckIsElementPresent(VIEW_CART);
       waitAndClickOn(VIEW_CART);
       softAssert.assertTrue(waitAndCheckIsElementPresent(SUBMIT_REQUIREMENT));
       sleep(3000);
    }
    @And("user will add Stainless Steel product to cart")
    public void user_will_add_stainless_steel_product_to_cart(){
        waitAndCheckIsElementPresent(STAINLESS_STEEL_PLP);
        waitAndClickOn(STAINLESS_STEEL_PLP);
        waitAndCheckIsElementPresent(STAINLESS_STEEL_PDP);
        waitAndClickOn(STAINLESS_STEEL_PDP);
        waitAndCheckIsElementPresent(STAINLESS_STEEL_THICKNESS);
        waitAndClickOn(STAINLESS_STEEL_THICKNESS);
        waitAndCheckIsElementPresent(STAINLESS_STEEL_WIDTH);
        waitAndClickOn(STAINLESS_STEEL_WIDTH);
        waitAndCheckIsElementPresent(STAINLESS_STEEL_LENGTH);
        waitAndClickOn(STAINLESS_STEEL_LENGTH);
        sleep(2000);
        waitAndCheckIsElementPresent(ADD_TO_CART_CTA);
        waitAndClickOn(ADD_TO_CART_CTA);
        waitAndCheckIsElementPresent(VIEW_CART);
        waitAndClickOn(VIEW_CART);
        softAssert.assertTrue(waitAndCheckIsElementPresent(SUBMIT_REQUIREMENT));
        sleep(3000);

    }

    @Then("check user able to add the product to cart page")
    public void check_user_able_to_add_the_product_to_cart_page() {
        sleep(2000);
        waitAndCheckIsElementPresent(ADD_TO_CART_CTA);
        waitAndClickOn(ADD_TO_CART_CTA);
        waitAndCheckIsElementPresent(VIEW_CART);
        waitAndClickOn(VIEW_CART);
        softAssert.assertTrue(waitAndCheckIsElementPresent(SUBMIT_REQUIREMENT));
        sleep(3000);
        softAssert.assertAll();
    }
    @Then("check user able to delete line item from cart")
    public void check_ser_able_to_delete_line_item_from_cart(){
        waitAndCheckIsElementPresent(DELETE_LINE_ITEM);
        waitAndClickOn(DELETE_LINE_ITEM);
        sleep(2000);
        waitAndCheckIsElementPresent(DELETE_CTA_BUTTON);
        waitAndClickOn(DELETE_CTA_BUTTON);
        sleep(5000);
    }
    @And("user checkout the RCV2")
    public void user_checkout_the_rcv2(){
        waitAndCheckIsElementPresent(RCV2_CHECKOUT);
        waitAndClickOn(RCV2_CHECKOUT);
        waitAndCheckIsElementPresent(MY_REQUIREMENT_PAGE);
        waitAndClickOn(MY_REQUIREMENT_PAGE);
        waitAndCheckIsElementPresent(REQUIREMENT_ID);
        requirementId=waitAndGetText(REQUIREMENT_ID);

    }
    @Then("verify the opportunity in SF {string} {string}")
    public void verify_the_opportunity_in_sf(String sfEmail, String sfPassword) {
        Grid.driver().get(Config.getConfigProperty("sfUrl"));
        waitAndCheckIsElementPresent(SF_LOGIN_EMAIL);
        waitAndSetText(SF_LOGIN_EMAIL, sfEmail);
        waitAndCheckIsElementPresent(SF_LOGIN_PASSWORD);
        waitAndSetText(SF_LOGIN_PASSWORD, sfPassword);
        waitAndCheckIsElementPresent(SF_LOGIN);
        waitAndClickOn(SF_LOGIN);
    }
    


}
