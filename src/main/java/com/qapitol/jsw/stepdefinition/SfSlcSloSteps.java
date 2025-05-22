package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.apitest.apis.impl.CtSlcSlocreationImpl;
import com.qapitol.jsw.apitest.pojos.order.ctSlcSloResponse.CtSlcSloRes;
import com.qapitol.sauron.common.configuration.Config;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


import java.io.IOException;


import static com.qapitol.jsw.utils.CustomUtills.*;


public class SfSlcSloSteps extends AbstractWebPage {

    public static final String LOGIN_CTA_BUTTON = "loginButton";
    public static final String LOGIN_VIA_OTP = "loginViaOtp";
    public static final String LOGIN_EMAIL = "ccpLoginEmail";
    public static final String LOGIN_PASSWORD = "ccpLoginPassword";
    public static final String CCP_LOGIN_CTA = "ccpLoginCTA";
    public static final String CART_PAGE = "cartPage";
    public static final String CCP_CART_TOTAL_AMOUNT = "cartTotalAmount";
    public static final String ERP_PAYMENTYPE = "ErpPaymentType";
    public static final String CCP_CART_LINEITEMS_CASHBACK = "cartLineItemCashBack";
    public static final String CCP_CART_PRICE_AMOUNT = "cartPriceAmount";
    public static final String CCP_CART_TAXES_AMOUNT = "cartTaxesAmount";
    public static final String CCP_CART_DELIVERY_CHARGES_AMOUNT="cartDeliveryChargesAmount";
    public static final String CCP_CART_LINE_ITEMS_PRODUCT_AMOUNT="cartLineItemProductAmount";
    public static final String CCP_CART_LINE_ITEMS_PER_MT_Amount="cartLineItemPerMtAmount";
    public static final String CCP_CART_LINE_ITEM_TAX_PERCENTAGE="cartLineItemTaxPercentage";
    public static final String CCP_CART_LINE_ITEM_THICKNESS="cartLineItemThickness";
    public static final String CCP_CART_LINE_ITEM_LENGTH="cartLineItemLength";
    public static final String CCP_CART_LINE_ITEM_WIDTH="cartLineItemWidth";



    CtSlcSloRes response;
    @Given("Request and create and cashAndCarry distributer cart")
    public void request_and_create_and_cashAndCarry_distributer_cart() throws IOException {
        CtSlcSlocreationImpl ctSlcSlocreation = new CtSlcSlocreationImpl();
        response = ctSlcSlocreation.getSlcSloResponse();
    }

    @And("Open the CCP website and login {string} and {string}")
    public void open_the_CCP_website_and_login(String username, String password) {

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

    @And("Open the cart and verify the cart details")
    public void open_the_cart_and_verify_the_cart_details() throws InterruptedException {

        waitAndCheckIsElementPresent(CART_PAGE);
        waitAndCheckIsElementVisible(CART_PAGE);
        waitAndClickOn(CART_PAGE);//Open Cart Page

        //Cart Page verification
        // Cart summary verification
        String uiCartPriceAmount = waitAndGetText(CCP_CART_PRICE_AMOUNT);
        String cleanUpUiCartPriceAmount = uiCartPriceAmount.replaceAll("[^\\d.]", "");
        softAssert.assertEquals(Double.parseDouble(cleanUpUiCartPriceAmount), requestProductPrice,"Failing in Price Amount");

        String uiCartTaxesAmount = waitAndGetText(CCP_CART_TAXES_AMOUNT);
        String cleanUpUiCartTaxesAmount = uiCartTaxesAmount.replaceAll("[^\\d.]", "");
        softAssert.assertEquals(Double.parseDouble(cleanUpUiCartTaxesAmount), requestProductTaxes,"Failing in Taxes Amount");

        String uiCartDeliveryChargesAmount = waitAndGetText(CCP_CART_DELIVERY_CHARGES_AMOUNT);
        String cleanUpUiCartDeliveryChargesAmount = uiCartDeliveryChargesAmount.replaceAll("[^\\d.]", "");
        softAssert.assertEquals(Double.parseDouble(cleanUpUiCartDeliveryChargesAmount),requestedDeliveryCharges,"Failing in Delivery Charges");

        String uiCartTotalAmount = waitAndGetText(CCP_CART_TOTAL_AMOUNT);
        String cleanUpUiCartTotalAmount = uiCartTotalAmount.replaceAll("[^\\d.]", "");
        softAssert.assertEquals(Double.parseDouble(cleanUpUiCartTotalAmount),requestTotalAmount,"Failing in Total Amount");


        //Validate Line Item
        softAssert.assertEquals(waitAndGetText(CCP_CART_LINE_ITEMS_PRODUCT_AMOUNT),formatLineItemTotalAmount,"Failing in line Item Total Amount");
        softAssert.assertNotNull(waitAndGetText(CCP_CART_LINE_ITEMS_PER_MT_Amount),"Failing in line Item per MT Amount");
        softAssert.assertNotNull(waitAndGetText(CCP_CART_LINEITEMS_CASHBACK),"Failing in line Item CashBack Amount");
        softAssert.assertEquals(waitAndGetText(CCP_CART_LINE_ITEM_TAX_PERCENTAGE),"(excl. 18% GST)","Line Item Tax Percentage is not matching with expected value");
        int same=(int)requestQuantity;
        softAssert.assertEquals(waitAndGetText("cartLineItemQuantity"),"("+same+" MT)","Line Item Quantity is not matching with expected value");
        softAssert.assertEquals(waitAndGetText(CCP_CART_LINE_ITEM_THICKNESS),requestThickness+" mm","Line Item Thickness is not matching with expected value");
        softAssert.assertEquals(waitAndGetText(CCP_CART_LINE_ITEM_LENGTH),requestLength+" mm","Line Item Length is not matching with expected value");
        softAssert.assertEquals(waitAndGetText(CCP_CART_LINE_ITEM_WIDTH),requestWidth+" mm","Line Item Width is not matching with expected value");
        softAssert.assertNotNull(waitAndGetText("cartShipTo"),"Ship To is not present in the cart");
        softAssert.assertNotNull(waitAndGetText("cartBillTo"),"Bill To is not present in the cart");
        softAssert.assertAll();

    }

    @And("Create an order and verify the order summery page")
    public void create_an_order_and_verify_the_order_summery_page() {
    }

    @And("open the ERP and verify all the data")
    public void open_the_ERP_and_verify_all_the_data() {
    }

    @And("Create an shipment and upload the invoice")
    public void create_an_shipment_and_upload_the_invoice() {
    }

    @Then("Open the CCP and verify the shipment details")
    public void open_the_CCP_and_verify_the_shipment_details() {
    }
}
