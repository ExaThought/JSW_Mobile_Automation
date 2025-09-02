package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.apitest.apis.impl.CtSlcSlocreationImpl;
import com.qapitol.jsw.apitest.pojos.order.ctSlcSloResponse.CtSlcSloRes;
import com.qapitol.jsw.utils.AllureCustomLogReporter;
import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.sauron.common.configuration.Config;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;


import java.io.IOException;

import static com.qapitol.jsw.apitest.apis.impl.CtSlcSlocreationImpl.*;
import static com.qapitol.jsw.utils.CustomUtills.*;


public class SfSlcSloSteps extends AbstractWebPage {

    public static final String LOGIN_CTA_BUTTON = "loginButton";
    public static final String LOGIN_VIA_OTP = "loginViaOtp";
    public static final String LOGIN_EMAIL = "ccpLoginEmail";
    public static final String LOGIN_PASSWORD = "ccpLoginPassword";
    public static final String CCP_LOGIN_CTA = "ccpLoginCTA";
    public static final String CART_PAGE = "cartPage";
    public static final String ERP_PAYMENTYPE = "ErpPaymentType";
    public static final String CCP_CART_LINEITEMS_CASHBACK = "cartLineItemCashBack";
    public static final String CCP_CART_LINE_ITEMS_PRODUCT_AMOUNT = "cartLineItemProductAmount";
    public static final String CCP_CART_LINE_ITEM_ATTRIBUTES = "cartLineItemAttributes";
    public static final String CCP_ORDER_NUMBER = "ccpOrderNumber";
    public static final String CCP_CREATE_ORDER_CTA = "ccpCreateOrderCTA";
    public static final String ERP_ORDER_Number = "erpOrderNumber";
    public static final String ERP_ORDER_CATEGORY = "erpOrderCategory";
    public static final String ERP_MMID = "erpMMID";
    public static final String ERP_SKU_DETAILS = "erpSKUDetails";
    public static final String ERP_CASHBACK = "erpCashBack";
    public static final String ERP_ORDER_PAYMENT_TYPE = "erpOrderPaymentType";
    public static final String ERP_ORDER_QUANTITY = "erpOrderQuantity";
    public static final String ERP_TOTAL_AMOUNT = "erpTotalAmount";
    public static final String ERP_DELIVERY_TYPE = "erpDeliveryType";
    CtSlcSloRes response;
    String ccpOrderNumber;
    String orderType;
    CtSlcSlocreationImpl ctSlcSlocreation;

    @When("Request and create an cashAndCarry distributer cart")
    public void request_and_create_an_cashAndCarry_distributer_cart() throws IOException {
        ctSlcSlocreation = new CtSlcSlocreationImpl();
        orderType="CT_SLC_SLO_CASH_AND_CARRY";
        response = ctSlcSlocreation.getSlcSloResponse(orderType);
    }
    @When("Request and create an cashAndCarryAdvance distributer cart")
    public void request_and_create_an_cashAndCarryAdvance_distributer_cart() throws IOException {
        ctSlcSlocreation = new CtSlcSlocreationImpl();
        orderType="CT_SLC_CASH_AND_CARRY_ADVANCE";
        response=ctSlcSlocreation.getSlcSloResponse(orderType);
    }
    @When("Request and create an credit distributer cart")
    public void request_and_create_an_credit_distributer_cart() throws IOException {
        ctSlcSlocreation = new CtSlcSlocreationImpl();
        orderType="CT_SLC_CREDIT";
        response=ctSlcSlocreation.getSlcSloResponse(orderType);
    }
    @When("Request and create an cash and carry withAdvance direct cart")
    public void request_and_create_an_cash_and_carry_withAdvance_direct_cart() throws IOException {
        ctSlcSlocreation = new CtSlcSlocreationImpl();
        orderType="CT_SLC_CASH_AND_CARRY_ADVANCE";
        response=ctSlcSlocreation.getSlcSloResponse(orderType);
    }
    @When("Request and create an cash and carry ZeroAdvance direct cart")
    public void request_and_create_an_cash_and_carry_ZeroAdvance_direct_cart() throws IOException {
        ctSlcSlocreation = new CtSlcSlocreationImpl();
        orderType="CT_SLC_CASH_AND_CARRY_ADVANCE";
        response=ctSlcSlocreation.getSlcSloResponse(orderType);
    }
    @When("Request and create an Credit direct cart")
    public void request_and_create_an_credit_direct_cart() throws IOException {
        ctSlcSlocreation = new CtSlcSlocreationImpl();
        orderType="CT_SLC_CREDIT";
        response=ctSlcSlocreation.getSlcSloResponse(orderType);
    }

    @And("Open the CCP website and login {string} and {string}")
    public void open_the_CCP_website_and_login(String username, String password) {

        Grid.driver().get(ConfigUtil.resolve("ccpPortalUrl"));
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

        //Validate Line Item
        softAssert.assertEquals(waitAndGetText(CCP_CART_LINE_ITEMS_PRODUCT_AMOUNT), formatLineItemTotalAmount, "Failing in line Item Total Amount");
        softAssert.assertEquals(waitAndGetText(CCP_CART_LINEITEMS_CASHBACK),"₹"+(int)requestedCashBack+"/MT", "Failing in line Item CashBack");
        softAssert.assertEquals(waitAndGetText(CCP_CART_LINE_ITEM_ATTRIBUTES), "("+requestThickness+" x "+requestLength+" x "+requestWidth+" mm"+")", "Line Item Attributes is not matching with expected value");
        softAssert.assertNotNull(waitAndGetText("cartShipTo"), "Ship To is not present in the cart");
        softAssert.assertNotNull(waitAndGetText("cartBillTo"), "Bill To is not present in the cart");
        waitAndCheckIsElementPresent(CCP_CREATE_ORDER_CTA);
        waitAndCheckIsElementVisible(CCP_CREATE_ORDER_CTA);
        waitAndClickOn(CCP_CREATE_ORDER_CTA);

    }

    @And("Create an order and verify the order summery page")
    public void create_an_order_and_verify_the_order_summery_page() {

        sleep(3000);
        ccpOrderNumber = waitAndGetText(CCP_ORDER_NUMBER);
        AllureCustomLogReporter.addTestStepLog(requestOrderType+" "+orderType+" order number="+ccpOrderNumber);
    }
    @When("Request and create an cashAndCarry distributor order")
    public void request_and_create_an_cashAndCarry_distributor_order() throws IOException {
        ctSlcSlocreation = new CtSlcSlocreationImpl();
        orderType="CT_SLC_CASH_AND_CARRY";
        response = ctSlcSlocreation.getSlcSloResponse(orderType);
        ccpOrderNumber=String.valueOf(response.getOrderNumber());
    }
    @When("Request and create an cashAndCarryAdvance distributor order")
    public void request_and_create_an_cashAndCarryAdvance_distributor_order() throws IOException {
        ctSlcSlocreation = new CtSlcSlocreationImpl();
        orderType="CT_SLC_CASH_AND_CARRY_ADVANCE";
        response = ctSlcSlocreation.getSlcSloResponse(orderType);
        ccpOrderNumber=String.valueOf(response.getOrderNumber());
    }
    @When("Request and create an credit distributor order")
    public void request_and_create_an_credit_distributor_order() throws IOException {
        ctSlcSlocreation = new CtSlcSlocreationImpl();
        orderType="CT_SLC_CREDIT";
        response = ctSlcSlocreation.getSlcSloResponse(orderType);
        ccpOrderNumber=String.valueOf(response.getOrderNumber());
    }
    @When("Request and create an cash and carry withAdvance direct order")
    public void request_and_create_an_cash_and_carry_withAdvance_direct_order() throws IOException {
        ctSlcSlocreation = new CtSlcSlocreationImpl();
        orderType="CT_SLC_CASH_AND_CARRY_ADVANCE";
        response = ctSlcSlocreation.getSlcSloResponse(orderType);
        ccpOrderNumber=String.valueOf(response.getOrderNumber());
    }
    @When("Request and create an cash and carry ZeroAdvance direct order")
    public void request_and_create_an_cash_and_carry_zeroAdvance_direct_order() throws IOException {
        ctSlcSlocreation = new CtSlcSlocreationImpl();
        orderType="CT_SLC_CASH_AND_CARRY_ADVANCE";
        response = ctSlcSlocreation.getSlcSloResponse(orderType);
        ccpOrderNumber=String.valueOf(response.getOrderNumber());
    }
    @When("Request and create an Credit direct order")
    public void request_and_create_an_credit_direct_order() throws IOException {
        ctSlcSlocreation = new CtSlcSlocreationImpl();
        orderType="CT_SLC_CREDIT";
        response = ctSlcSlocreation.getSlcSloResponse(orderType);
        ccpOrderNumber=String.valueOf(response.getOrderNumber());
    }

    @And("open the ERP and verify all the data")
    public void open_the_ERP_and_verify_all_the_data() {
        //Open the ERP
        Grid.driver().get(ConfigUtil.resolve("ErpLoginAuth"));
        Grid.driver().manage().window().maximize();
        sleep(5000);

        //Open the Order in ERP
        ccpOrderNumber = ccpOrderNumber;
        Grid.driver().get(ConfigUtil.resolve("omsUrl") + "order-list/" + ccpOrderNumber);

        //Total Amount validation
        String erpTotalAmount = waitAndGetText(ERP_TOTAL_AMOUNT);
        String cleanUpErpTotalAmount = erpTotalAmount.replaceAll("[^\\d.]", "");
        softAssert.assertEquals(Double.parseDouble(cleanUpErpTotalAmount), requestTotalAmount, "Failing in Total Amount in ERP");

        //Qauntity validation
        String erpOrderQuantity = waitAndGetText(ERP_ORDER_QUANTITY);
        String cleanUpErpOrderQuantity = erpOrderQuantity.split(" ")[0];
        softAssert.assertEquals(Double.parseDouble(cleanUpErpOrderQuantity), requestQuantity, "Failing in Order Quantity in ERP");

       //cashback Validation
        String erpCashBack = waitAndGetText(ERP_CASHBACK);
        String cleanUpErpCashBack = erpCashBack.replaceAll("[^\\d.]", "");
        softAssert.assertEquals(Double.parseDouble(cleanUpErpCashBack), requestedCashBack, "Failing in CashBack in ERP");
        //Nodel and Non nodel Validation
        softAssert.assertTrue(requestNodelorNonNodel.contains(waitAndGetText(ERP_ORDER_PAYMENT_TYPE)), "Failing in Order Payment Type in ERP");

        //Other validation
        softAssert.assertEquals(waitAndGetText(ERP_ORDER_Number), ccpOrderNumber, "Failing Order Number in ERP");
        softAssert.assertEquals(waitAndGetText(ERP_PAYMENTYPE), requestPaymentType, "Failing Payment Type in ERP");
        softAssert.assertEquals(waitAndGetText(ERP_ORDER_CATEGORY), requestOrderType.toUpperCase(), " Failing in Order Type in ERP");
        softAssert.assertNotNull(waitAndGetText(ERP_MMID), "Failing in MMID in ERP");
        softAssert.assertNotNull(waitAndGetText(ERP_SKU_DETAILS), "Failing in SKU Details in ERP");
        softAssert.assertEquals(waitAndGetText(ERP_DELIVERY_TYPE), requestDeliveryType, "Failing in Delivery Type in ERP");
        softAssert.assertAll();
    }
}
