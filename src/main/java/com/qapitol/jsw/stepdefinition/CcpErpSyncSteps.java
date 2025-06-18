package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.apitest.apis.impl.CtSlcSlocreationImpl;
import com.qapitol.sauron.common.configuration.Config;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;

import java.io.IOException;

public class CcpErpSyncSteps extends AbstractWebPage {

    public static final String LOGIN_CTA_BUTTON = "loginButton";
    public static final String LOGIN_VIA_OTP = "loginViaOtp";
    public static final String LOGIN_EMAIL = "ccpLoginEmail";
    public static final String LOGIN_PASSWORD = "ccpLoginPassword";
    public static final String CCP_LOGIN_CTA = "ccpLoginCTA";
    public static final String CART_PAGE = "cartPage";
    public static final String CCP_ORDER_NUMBER = "ccpOrderNumber";
    public static final String CCP_CREATE_ORDER_CTA = "ccpCreateOrderCTA";
    public static final String ERP_PI_PO_CHECKBOX="erpPiPoCheckBox";
    public static final String ERP_BILLING_ADDRESS_CHECKBOX="erpBillingAddress";
    public static final String ERP_SPOC="erpSpoc";


    String ccpOrderNumber;

    @When("the user creates an cart from SF")
    public void the_user_creates_an_cart_from_SF() throws IOException {
        CtSlcSlocreationImpl ctSlcSloCreation=new CtSlcSlocreationImpl();
        String orderType="CT_SLC_SLO_CASH_AND_CARRY";
        ctSlcSloCreation.getSlcSloResponse(orderType);
    }
    @And("login to ccp and create an order {string} and {string}")
    public void login_to_ccp_and_create_an_order(String userName, String password){

        Grid.driver().get(Config.getConfigProperty("ccpPortalUrl"));
        Grid.driver().manage().window().maximize();
        waitAndCheckIsElementVisible(LOGIN_CTA_BUTTON);
        waitAndClickOn(LOGIN_CTA_BUTTON); // click on login CTA button
        waitAndCheckIsElementVisible(LOGIN_VIA_OTP);
        waitAndClickOn(LOGIN_VIA_OTP); //Click on Login with OTP
        waitAndCheckIsElementVisible(LOGIN_EMAIL);
        waitAndSetText(LOGIN_EMAIL, userName); // Give you Email
        waitAndCheckIsElementVisible(LOGIN_PASSWORD);
        waitAndSetText(LOGIN_PASSWORD, password);// Give the password
        waitAndCheckIsElementVisible(CCP_LOGIN_CTA);
        waitAndClickOn(CCP_LOGIN_CTA);// Login
        waitAndCheckIsElementVisible(CART_PAGE);
        waitAndClickOn(CART_PAGE);
        waitAndCheckIsElementPresent(CCP_CREATE_ORDER_CTA);
        waitAndCheckIsElementVisible(CCP_CREATE_ORDER_CTA);
        waitAndClickOn(CCP_CREATE_ORDER_CTA);
        sleep(3000);
        ccpOrderNumber = waitAndGetText(CCP_ORDER_NUMBER);
    }
    @And("logs into ERP and opens the order details page")
    public void logs_into_erp_and_opens_the_order_details_page(){
        //Open the ERP
        Grid.driver().get(Config.getConfigProperty("ErpLoginAuth"));
        Grid.driver().manage().window().maximize();
        sleep(5000);

        //Open the Order in ERP
        ccpOrderNumber = ccpOrderNumber;
        Grid.driver().get(Config.getConfigProperty("omsUrl") + "order-list/" + ccpOrderNumber);
    }
    @And("confirms the order")
    public void confirms_the_order(){
     waitAndCheckIsElementVisible(ERP_PI_PO_CHECKBOX);
     waitAndClickOn(ERP_PI_PO_CHECKBOX);
     waitAndCheckIsElementVisible(ERP_BILLING_ADDRESS_CHECKBOX);
     waitAndClickOn(ERP_BILLING_ADDRESS_CHECKBOX);
    }

}
