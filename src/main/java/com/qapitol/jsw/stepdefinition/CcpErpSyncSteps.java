package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.apitest.apis.impl.CcpErpSyncImpl;
import com.qapitol.jsw.apitest.apis.impl.CtSlcSlocreationImpl;
import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.jsw.utils.Token;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.io.IOException;

import static com.qapitol.jsw.utils.CustomUtil.clickButton;
import static com.qapitol.jsw.utils.CustomUtil.waits;

public class CcpErpSyncSteps extends AbstractWebPage {

    public static final String LOGIN_CTA_BUTTON = "loginButton";
    public static final String LOGIN_VIA_OTP = "loginViaOtp";
    public static final String LOGIN_EMAIL = "ccpLoginEmail";
    public static final String LOGIN_PASSWORD = "ccpLoginPassword";
    public static final String CCP_LOGIN_CTA = "ccpLoginCTA";
    public static final String CART_PAGE = "(//span[text()=\"Cart\"])[2]";
    public static final String CCP_ORDER_NUMBER = "ccpOrderNumber";
    public static final String CCP_CREATE_ORDER_CTA = "ccpCreateOrderCTA";
    public static final String CCP_MY_ORDER_PAGE="ccpMyOrderPage";
    public static final String ORDER_CONFIRMATION ="orderConfirmStatus";
    SessionSteps sessionSteps=new SessionSteps();
    String ccpOrderNumber;
    String userName;
    String password;

    @When("create cash and carry cart from SF")
    public void create_cash_and_carry_cart_from_sf() throws IOException {
        //Create SLC
        CtSlcSlocreationImpl ctSlcSloCreation=new CtSlcSlocreationImpl();
        String orderType="CT_SLC_SLO_CASH_AND_CARRY";
        ctSlcSloCreation.getSlcSloResponse(orderType);
    }

    @And("open the cart page and create order")
    public void open_the_cart_page_and_create_order(){
        //open cart page\
        sleep(3000);
        pageRefresh();
        sleep(3000);
        waits(By.xpath(CART_PAGE));
        clickButton(By.xpath(CART_PAGE));
        //confirm order CTA
        waitAndCheckIsElementPresent(CCP_CREATE_ORDER_CTA);
        waitAndCheckIsElementVisible(CCP_CREATE_ORDER_CTA);
        waitAndClickOn(CCP_CREATE_ORDER_CTA);
        sleep(3000);
        //Copy order number
        ccpOrderNumber = waitAndGetText(CCP_ORDER_NUMBER);
    }
    @And("logs into ERP and opens the order details page")
    public void logs_into_erp_and_opens_the_order_details_page(){
        //Open the ERP
        Grid.driver().get(Token.getErpLoginUrl());
        Grid.driver().manage().window().maximize();
        sleep(5000);

        //Open the Order in ERP
        Grid.driver().get(ConfigUtil.getSecretValue("omsUrl") + "order-list/" + ccpOrderNumber);
    }
    @And("confirms the order in ERP and verify in CCP")
    public void confirms_the_order_in_erp_and_verify_in_ccp() throws IOException, InterruptedException {
        CcpErpSyncImpl ccpErpSync=new CcpErpSyncImpl();
        String orderConfirmStatus=ccpErpSync.getOrderConfirmation(ccpOrderNumber);
        Assert.assertEquals(orderConfirmStatus,"Confirmed");
        Grid.driver().navigate().refresh();

        //Login to CCP and check order status
        Grid.driver().get(ConfigUtil.getSecretValue("ccpPortalUrl"));
        Grid.driver().manage().window().maximize();
        sleep(2000);
        waitAndCheckIsElementPresent(CCP_MY_ORDER_PAGE);
        waitAndClickOn(CCP_MY_ORDER_PAGE);
        sleep(2000);
        JavascriptExecutor js=(JavascriptExecutor)Grid.driver();
        js.executeScript("window.scrollBy(0,500)");
        WebElement orderSummaryLink=Grid.driver().findElement(By.xpath("//span[contains(text(),'"+ccpOrderNumber+"')]//parent::div//following-sibling::a"));
        sleep(2000);
        orderSummaryLink.click();
        sleep(2000);
        waitAndCheckIsElementPresent(ORDER_CONFIRMATION);
        String orderStatus= waitAndGetText(ORDER_CONFIRMATION);
        Assert.assertEquals(orderStatus,"Your order is confirmed","Failing in order confirm status");

    }
    @When("create cash and carry cart advance from SF")
    public void create_cash_and_carry_cart_advance_from_sf() throws IOException {
        //create SLC
        CtSlcSlocreationImpl ctSlcSloCreation=new CtSlcSlocreationImpl();
        String orderType="CT_SLC_SLO_CASH_AND_CARRY_ADVANCE";
        ctSlcSloCreation.getSlcSloResponse(orderType);
    }

}
