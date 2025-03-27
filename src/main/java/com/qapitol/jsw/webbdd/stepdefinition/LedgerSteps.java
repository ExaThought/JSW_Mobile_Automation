package com.qapitol.jsw.webbdd.stepdefinition;

import com.qapitol.jsw.apitest.apis.impl.OrderImpl;
import com.qapitol.jsw.apitest.pojos.order.getOrder.GetOMSOrderResp;
import com.qapitol.jsw.utils.AllureCustomLogReporter;
import com.qapitol.jsw.utils.CurrencyUtils;
import com.qapitol.jsw.utils.OMSDBUtils;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.data.ContextData;
import com.qapitol.sauron.data.Data;
import com.qapitol.sauron.logging.SauronLogger;
import com.qapitol.sauron.logging.SimpleLogger;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

public class LedgerSteps extends BaseSteps {
    private final SimpleLogger log = SauronLogger.getLogger();

    //URL paths for ledger pages
    public static final String LEDGER_STATEMENT_NEW_PATH = "#Report:Ledger_statement_new_Report";
    public static final String LEDGER_SUMMARY_PATH = "#Report:All_Ledger_Summaries";

    // Options in search menu
    public static final String CASHBACK_ISSUED = "Cashback Issued";
    public static final String CASHBACK_TDS = "Cashback TDS";
    public static final String CASHBACK_PAYOUT = "Cashback payout";
    public static final String CUSTOMER_NAME = "Customer Name";

    // Validation/Assertion values
    public static final String KNOCKED_OFF_AMOUNT = "Knocked off amount";
    public static final String CREDIT_AMOUNT = "Credit amount";
    public static final String ZERO = "0.00";
    public static final String DEBIT_AMOUNT = "Debit amount";
    public static final String LED_PENDING_AMOUNT = "Pending amount";
    public static final String OPEN = "Open";
    public static final String CLEARING_STATUS = "Clearing status";
    public static final String GSTIN = "GSTIN";
    public static final String ENTITY_NAME = "Entity name";
    public static final String LEDGER_STATEMENT_KEY = "Ledger statement key";
    public static final String MODULE_NAME = "Module name";
    public static final String CN = "CN";
    public static final String CREDIT_NOTE = "Credit Note";
    public static final String ZOHO_BOOKS_MODULE_USED = "Zoho Books module used";
    public static final String CREATOR_ID_MASTERS = "Creator ID - Masters";
    public static final String CLEARED = "Cleared";
    public static final String CT = "Cashback_TDS";
    public static final String ZBM_CASHBACK_TDS = "Cashback_TDS";
    public static final String PAYOUT = "Payout";
    public static final String ZBM_CASHBACK_PAYOUT = "Refunds against Credit notes";

    @When("Create order with SLO API")
    public void createOrderWithSloApi() throws IOException, InterruptedException {
        log.entering();
        OrderImpl orderCreation = new OrderImpl();
        orderCreation.createOrder();
        new OMSDBUtils().updateSalesForceOpportunity((String) ContextData.getDataFromKey("orderNumber"));
        AllureCustomLogReporter.addTestStepLog("Order created successfully, Customer Order Number: " + (String) ContextData.getDataFromKey("orderNumber"));

        log.exiting();
    }

    @Then("validate the order details")
    public void validateTheOrderDetails() throws IOException, InterruptedException {
        log.entering();
        String orderType = (String) Data.get().get("orderType");
        String paymentType = (String) Data.get().get("paymentType");

        OrderImpl orderCreation = new OrderImpl();
        GetOMSOrderResp getOMSOrderResp = orderCreation.validateOrderDetails(orderType, paymentType);
        ContextData.addData("gstin", getOMSOrderResp.getBuyerDetails().getGstin());
        ContextData.addData("baseAMount", getOMSOrderResp.getAmounts().get("LIA").getAmount().getDisplayAmount().toString().replace(",", ""));
        log.info("Order Type: " + orderType + "And Payment Type: " + paymentType);
        AllureCustomLogReporter.addTestStepLog("Order Type: " + orderType + " And Payment Type: " + paymentType);
        log.info("Credit by pass flag -->" + getOMSOrderResp.getCreditBlockBypassFlag());
        AllureCustomLogReporter.addTestStepLog("Credit by pass flag -->" + getOMSOrderResp.getCreditBlockBypassFlag());
        log.exiting();
    }

    @Then("Get cashback amount and quantity details")
    public void getCashbackAmountAndQuantityDetails() throws IOException, InterruptedException {
        log.entering();
        OrderImpl orderCreation = new OrderImpl();
        double cashbackAmount = orderCreation.fetchCashbackAmount();
        double totalCashbackAmount = cashbackAmount * Double.parseDouble((String) ContextData.getDataFromKey("orderQuantity"));
        totalCashbackAmount = Math.round(totalCashbackAmount * 100.0) / 100.0;
        ContextData.addData("totalCashbackAmount", totalCashbackAmount);
        AllureCustomLogReporter.addTestStepLog("Customer Order Cashback Amount Per Unit: " + cashbackAmount);
        AllureCustomLogReporter.addTestStepLog("Customer Order Total Cashback Amount: " + totalCashbackAmount);
        log.exiting();
    }

    @Then("Validate ledger statement with Document type as Cashback Issued on Zoho Creator")
    public void validateLedgerStatementDocumentTypeAsCashbackIssued() {
        log.entering();
        SoftAssert softAssert = new SoftAssert();
        navigateToLedgerStatementNewTab();
        clearSearch();
        String buyerOrderNumber = (String) ContextData.getDataFromKey("orderNumber");
        searchStringContains(ORDER_ID, (String) ContextData.getDataFromKey("orderNumber"));
        searchStringContains(DOCUMENT_TYPE, CASHBACK_ISSUED);

        selectRowUsingObject((String) ContextData.getDataFromKey("orderNumber"), 15);
        Map<String, String> ledgerData = getDataFromWindow("dataWindowElement");
        double totalCashbackAmount = (double) ContextData.getDataFromKey("totalCashbackAmount");
        String CashBackIssuedCreditAmount = formatDecimalWithComma(String.valueOf(totalCashbackAmount));
        String CashBackIssuedPendingAmount = formatDecimalWithComma(String.valueOf(Double.parseDouble(CashBackIssuedCreditAmount) - Double.parseDouble(ledgerData.get(KNOCKED_OFF_AMOUNT))));
        String knockedOffAmount = formatDecimalWithComma(String.valueOf(Double.parseDouble(CashBackIssuedCreditAmount) - Double.parseDouble(CashBackIssuedPendingAmount)));
        assertEquals(ledgerData.get(DOCUMENT_TYPE), CASHBACK_ISSUED, "Document type verification failed");
        assertNotNull(ledgerData.get(DOCUMENT_ID), "Document id verification failed");
        assertEquals(ledgerData.get(CREDIT_AMOUNT), CashBackIssuedCreditAmount, "Credit amount(Cashback amount) verification failed");
        assertTrue("Order id verification failed", ledgerData.get(ORDER_ID).contains(buyerOrderNumber));
        assertEquals(ledgerData.get(CREDIT_AMOUNT), CashBackIssuedCreditAmount, "Credit amount(Cashback amount) verification failed");
        assertEquals(ledgerData.get(DEBIT_AMOUNT), ZERO, "Debit amount verification failed");
        softAssert.assertEquals(ledgerData.get(KNOCKED_OFF_AMOUNT), knockedOffAmount, "Knocked off amount verification failed");
        softAssert.assertEquals(ledgerData.get(LED_PENDING_AMOUNT), formatDecimalWithComma(CashBackIssuedPendingAmount), "Pending amount verification failed");
        softAssert.assertEquals(ledgerData.get(CLEARING_STATUS), OPEN, "Clearing status verification failed");
        assertEquals(ledgerData.get(GSTIN), (String) ContextData.getDataFromKey("gstin"), "GSTIN verification failed");
        assertEquals(ledgerData.get(ENTITY_NAME), (String) ContextData.getDataFromKey("customerName"), "Entity name verification failed");
        assertEquals(ledgerData.get(LEDGER_STATEMENT_KEY), getLedgerStatementKey(ledgerData), "Ledger Statement Key validation failed");
        assertEquals(ledgerData.get(MODULE_NAME), CN, "Module name verification failed");
        assertEquals(ledgerData.get(ZOHO_BOOKS_MODULE_USED), CREDIT_NOTE, "Zoho book Module used verification failed");
        assertNotNull(ledgerData.get(CREATOR_ID_DOC), "Creator ID - Documents should not be null");
        assertNotNull(ledgerData.get(CREATOR_ID_MASTERS), "Creator ID - Master should not be null");
        AllureCustomLogReporter.addTestStepLog("Cashback Issued details: " + ledgerData);
        ContextData.addData("documentNumber", ledgerData.get(DOCUMENT_ID));
        log.info("Cashback Issued details documentNumber:" + ledgerData.get(DOCUMENT_ID));
        clearFilter();
        log.exiting();
    }

    @Then("Validate ledger statement with Document type as Cashback TDS on Zoho Creator")
    public void validateLedgerStatementDocumentTypeAsCashbackTDS() {
        log.entering();
        navigateToLedgerStatementNewTab();
        clearSearch();
        String buyerOrderNumber = (String) ContextData.getDataFromKey("orderNumber");
        searchStringContains(ORDER_ID, buyerOrderNumber);
        searchStringContains(DOCUMENT_TYPE, CASHBACK_TDS);
        selectRowUsingObject((String) ContextData.getDataFromKey("orderNumber"), 10);
        Map<String, String> ledgerData = getDataFromWindow("dataWindowElement");

        double totalCashbackAmount = (double) ContextData.getDataFromKey("totalCashbackAmount");
        String CashBackTDSDebitAmount = formatDecimalWithComma(String.valueOf(CurrencyUtils.getNewInstance().getTDSAmount(totalCashbackAmount, 10)));
        String CashBackTDSPendingAmount = formatDecimalWithComma(String.valueOf(Double.parseDouble(CashBackTDSDebitAmount) - Double.parseDouble(ledgerData.get(KNOCKED_OFF_AMOUNT))));
        String knockedOffAmount = formatDecimalWithComma(String.valueOf(Double.parseDouble(CashBackTDSDebitAmount) - Double.parseDouble(CashBackTDSPendingAmount)));
        assertEquals(ledgerData.get(DOCUMENT_TYPE), CASHBACK_TDS, "Document type verification failed");
        assertTrue("Order id verification failed", ledgerData.get(ORDER_ID).contains(buyerOrderNumber));
        assertEquals(ledgerData.get(CREDIT_AMOUNT), ZERO, "Credit (Cashback) amount verification failed");
        assertEquals(ledgerData.get(DEBIT_AMOUNT), CashBackTDSDebitAmount, "Debit amount verification failed");
        assertEquals(ledgerData.get(KNOCKED_OFF_AMOUNT), knockedOffAmount,"Knocked off amount verification failed");
        assertEquals(ledgerData.get(LED_PENDING_AMOUNT), ZERO, "Pending amount verification failed");
        assertEquals(ledgerData.get(CLEARING_STATUS), CLEARED, "Clearing status verification failed");
        assertEquals(ledgerData.get(GSTIN), (String) ContextData.getDataFromKey("gstin"), "GSTIN verification failed");
        assertEquals(ledgerData.get(ENTITY_NAME), (String) ContextData.getDataFromKey("customerName"),"Entity name");
        assertEquals(ledgerData.get(LEDGER_STATEMENT_KEY), getLedgerStatementKey(ledgerData),"Ledger Statement Key validation failed");
        assertEquals(ledgerData.get(MODULE_NAME), CT,"Module name verification failed");
        assertEquals(ledgerData.get(ZOHO_BOOKS_MODULE_USED), ZBM_CASHBACK_TDS, "Zoho book Module used verification failed");
        assertNotNull(ledgerData.get(CREATOR_ID_DOC), "Creator ID - Documents should not be null");
        assertNotNull(ledgerData.get(CREATOR_ID_MASTERS), "Creator ID - Master should not be null");
        AllureCustomLogReporter.addTestStepLog("Cashback TDS details: " + ledgerData);
        clearFilter();
        log.exiting();
    }

    @Then("Validate ledger statement with Document type as Cashback Payout")
    public void validateLedgerStatementDocumentTypeAsCashbackPayout() {
        log.entering();
        SoftAssert softAssert = new SoftAssert();

        navigateToLedgerStatementNewTab();
        clearSearch();
        searchStringContains(DOCUMENT_TYPE, CASHBACK_PAYOUT);
        String buyerOrderNumber = (String) ContextData.getDataFromKey("orderNumber");
        searchStringContains(ORDER_ID, buyerOrderNumber);
        selectRowUsingObject(buyerOrderNumber, 20);
        Map<String, String> ledgerData = getDataFromWindow("dataWindowElement");
        double cashbackAmount = (double) ContextData.getDataFromKey("cashbackAmount");
        String orderQuantity = (String) ContextData.getDataFromKey("orderQuantity");

        double totalCashbackAmount = cashbackAmount * Double.parseDouble(orderQuantity);
        String CashBackPayoutDebitAmount = formatDecimalWithComma(String.valueOf(totalCashbackAmount - CurrencyUtils.getNewInstance().getTDSAmount(totalCashbackAmount, 10)));
        String cashBackPayoutDebitAmount = (String) ContextData.getDataFromKey("cashBackPayoutAmount");
        assertEquals(ledgerData.get(DOCUMENT_TYPE), CASHBACK_PAYOUT, "Document type verification failed");
        assertTrue("Order id verification failed", ledgerData.get(ORDER_ID).contains(buyerOrderNumber));
        assertEquals(ledgerData.get(CREDIT_AMOUNT), ZERO, "Credit (Cashback) amount verification failed");
        assertEquals(ledgerData.get(DEBIT_AMOUNT), CashBackPayoutDebitAmount, "Debit amount verification failed");
        softAssert.assertEquals(ledgerData.get(KNOCKED_OFF_AMOUNT), ZERO, "Knocked off amount verification failed");
        softAssert.assertEquals(ledgerData.get(LED_PENDING_AMOUNT), ZERO, "Pending amount verification failed");
        softAssert.assertEquals(ledgerData.get(CLEARING_STATUS), CLEARED, "Clearing status verification failed");
        assertEquals(ledgerData.get(GSTIN), (String) ContextData.getDataFromKey("gstin"), "GSTIN verification failed");
        assertEquals(ledgerData.get(ENTITY_NAME), (String) ContextData.getDataFromKey("customerName"), "Entity name");
        assertEquals(ledgerData.get(LEDGER_STATEMENT_KEY), getLedgerStatementKey(ledgerData), "Ledger Statement Key validation failed");
        assertEquals(ledgerData.get(MODULE_NAME), PAYOUT, "Module name verification failed");
        assertEquals(ledgerData.get(ZOHO_BOOKS_MODULE_USED), ZBM_CASHBACK_PAYOUT, "Zoho book Module used verification failed");
        assertNotNull(ledgerData.get(CREATOR_ID_DOC), "Creator ID - Documents should not be null");
        assertNotNull(ledgerData.get(CREATOR_ID_MASTERS), "Creator ID - Master should not be null");
        AllureCustomLogReporter.addTestStepLog("Cashback Payouts details in ledger statement: " + ledgerData);
        log.info("Cashback Payouts details in ledger statement: " + ledgerData);
        clearFilter();
        log.exiting();
    }

    @Then("Navigate to ledger summary tab, click on Retry and validate the freeze status as {string} for {string} customer")
    public void syncTheCustomerLedgerAndValidateTheFreezeStatus(String freezeStatus, String customerName) {
        syncTheCustomerLedger(customerName);
        validateTheLedgerFreezeStatus(customerName, freezeStatus);
    }

    public void validateTheLedgerFreezeStatus(String customerName, String freezeStatus) {
        SoftAssert softAssert = new SoftAssert();
        waitUntilLoaderInvisible();
        selectRowUsingObject(getCallSearchStringContainsFunc, customerName, 5);
        Map<String, String> ledgerSummaryData = getDataFromWindow("dataWindowElement");
        softAssert.assertEquals("Ledger summary verification failed:",freezeStatus,ledgerSummaryData.get("Freeze status"));
        log.info("Ledger summary freeze status:"+ledgerSummaryData.get("Freeze status"));
        AllureCustomLogReporter.addTestStepLog("Ledger summary freeze status:"+ledgerSummaryData.get("Freeze status"));
    }

    public void navigateToLedgerStatementNewTab() {
        log.entering();
        zohoCreatorNavigation(LEDGER_STATEMENT_NEW_PATH);
//        waitForLoaderToDisappear();
        log.exiting();
    }

    public void syncTheCustomerLedger(String customerName) {
        navigateToLedgerSummary();
        clearSearch();
        searchStringContains(CUSTOMER_NAME, customerName);
        tap("retryBtn");
        waitUntilLoaderInvisible();
        sleep(5555);
    }

    public void navigateToLedgerSummary() {
        Grid.setSessionName("zoho_creator");
        zohoCreatorNavigation(LEDGER_SUMMARY_PATH);
        log.info("Navigated to Ledger Summary Page");
        AllureCustomLogReporter.addTestStepLog("Navigated to Ledger Summary Page");
    }

}
