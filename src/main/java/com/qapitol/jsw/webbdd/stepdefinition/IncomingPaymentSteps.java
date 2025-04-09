package com.qapitol.jsw.webbdd.stepdefinition;

import com.qapitol.jsw.apitest.apis.impl.OrderFlowImpl;
import com.qapitol.jsw.apitest.pojos.order.shipmentInvoice.Data;
import com.qapitol.jsw.utils.AllureCustomLogReporter;
import com.qapitol.jsw.utils.CurrencyUtils;
import com.qapitol.sauron.data.ContextData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class IncomingPaymentSteps extends BaseSteps{

//  Path Constants
    public static final String INC_PAYMENT_HISTORICAL_TAB = "#Page:Incoming_payments1?Status=historical";
    public static final String INC_PAYMENT_PENDING_TAB = "#Page:Incoming_payments1?Status=pending";

    public static final String INCOMING_PAYMENT = "Incoming payment";
    public static final String PAYMENTS_RECEIVED = "Payments_Received";
    public static final String PAYMENTS_RECEIVED2 = "Payments received";
    public static final String PAYMENT_TAGGED_BY = "Payment tagged by";
    public static final String BANK_PAYMENT_ID = "Bank Payment ID";
    public static final String TRANSACTION_STATUS = "Transaction Status";
    public static final String TAGGED = "Tagged";
    public static final String TAG_STATUS = "Tag Status";
    public static String paymentId = null;
    public static String gstin = null;

    @Then("Create Incoming Payment In Zoho")
    public void createIncomingPaymentInZoho() throws IOException {
        log.entering();
        OrderFlowImpl orderFlow = new OrderFlowImpl();
        paymentId = orderFlow.createIncomingPayment();
        log.exiting();
    }

    @Then("validate the incoming payment details in zoho creator")
    public void validateTheIncomingPaymentDetailsInZohoCreator() {

        log.entering();
        gstin = (String) ContextData.getDataFromKey("buyerGstin");
        navigateToIncomingPaymentHistoryTab();
        clearSearch();
        searchStringContains(BANK_PAYMENT_ID, paymentId);
        selectRowUsingObject(gstin, 1);
        Map<String, String> incomingPaymentData = getDataFromWindow();

        AllureCustomLogReporter.addTestStepLog("Incoming payment successful with value ---> " + incomingPaymentData);
        popUpClose();
        assertEquals(paymentId, incomingPaymentData.get(BANK_PAYMENT_ID), "Payment id validation failed");
        assertEquals(CurrencyUtils.convertPaisaToRupeeWithCommaFormat((int) ContextData.getDataFromKey("orderAmount")), incomingPaymentData.get(AMOUNT), "Amount validation failed");
        assertEquals(gstin, incomingPaymentData.get(GSTIN), "GSTIN validation failed");
        assertEquals(incomingPaymentData.get(TRANSACTION_STATUS), SUCCESS, "Transaction Status validation failed");
        assertEquals(incomingPaymentData.get(TAG_STATUS), TAGGED, "Tag Status validation failed");
        log.info("Zoho Incoming payment Validation for incoming payment auto tagging successful");
        log.exiting();
    }

    public void navigateToIncomingPaymentHistoryTab() {
        sleep(999);
        zohoCreatorNavigation(INC_PAYMENT_HISTORICAL_TAB);
    }
    public void navigateToIncomingPaymentsPendingTab() {
        zohoCreatorNavigation(INC_PAYMENT_PENDING_TAB);
    }

    @And("tag the incoming payment manually to customer ledger using gstin for {string}")
    public void tagTheIncomingPaymentManuallyToCustomerLedgerUsingGstinFor(String arg0) {
        log.entering();
        paymentId = "pay_vQWEqTESTPayID0126";
        navigateToIncomingPaymentsPendingTab();
        manualIncomingPaymentTag(paymentId, arg0);
        AllureCustomLogReporter.addTestStepLog("Incoming payment manually tagged to customer : " + arg0);
        log.exiting();
    }

    public void manualIncomingPaymentTag(String dynamicValue, String customerName) {
//        String xpath = String.format("tagButton", dynamicValue);
//        WebElement tabButton=locateElement("tagButton", dynamicValue);
        waitAndClickOn("tagButton", dynamicValue);
        waitUntilElementIsClickable("searchDropDown");
        sleep(999);
        waitAndClickOn("searchDropDown");
      //  String searchBar = String.format("searchBar", customerName);
        waitAndSetText("searchBar", customerName + Keys.ENTER);
        sleep(999);
        waitAndClickOn("saveButton");
//        searchBar.sendKeys(customerName);
//        SeleniumHelper.staticWait(1000);
//        searchBar.sendKeys(Keys.ENTER);
//        SeleniumHelper.staticWait(1000);
//        driverHelper.click(saveButton, "save button");

    }
}
