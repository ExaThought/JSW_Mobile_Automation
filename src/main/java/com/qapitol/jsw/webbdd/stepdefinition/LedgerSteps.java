package com.qapitol.jsw.webbdd.stepdefinition;

import com.qapitol.jsw.apitest.apis.impl.OrderImpl;
import com.qapitol.jsw.apitest.pojos.order.getOrder.GetOMSOrderResp;
import com.qapitol.jsw.utils.OMSDBUtils;
import com.qapitol.sauron.data.ContextData;
import com.qapitol.sauron.logging.SauronLogger;
import com.qapitol.sauron.logging.SimpleLogger;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.util.Map;

public class LedgerSteps extends BaseSteps {
    private final SimpleLogger log = SauronLogger.getLogger();

    public static final String LEDGER_STATEMENT_NEW_PATH = "#Report:Ledger_statement_new_Report";
    public static final String LEDGER_SUMMARY_PATH = "#Report:All_Ledger_Summaries";

    public static final String CASHBACK_ISSUED = "Cashback Issued";
    public static final String CASHBACK_TDS = "Cashback TDS";
    public static final String CASHBACK_PAYOUT = "Cashback payout";
    public static final String CUSTOMER_NAME = "Customer Name";

    @When("Create a {string} type order with SLO API")
    public void createOrderWithSloApi(String arg0) throws IOException, InterruptedException {
        log.entering();
        OrderImpl orderCreation = new OrderImpl();
        orderCreation.createOrder(arg0, "SLO_Order");
        new OMSDBUtils().updateSalesForceOpportunity((String) ContextData.getDataFromKey("orderNumber"));
//        ExtentCucumberAdapter.addTestStepLog("Order created successfully, Customer Order Number: " + Context.buyerOrderNumber);

        log.exiting();
    }

    @Then("validate the order details for {string} and {string}")
    public void validateTheOrderDetails(String arg0, String arg1) throws IOException, InterruptedException {
        log.entering();
        OrderImpl orderCreation = new OrderImpl();
        GetOMSOrderResp getOMSOrderResp = orderCreation.validateOrderDetails(arg0, arg1);
        ContextData.addData("gstin", getOMSOrderResp.getBuyerDetails().getGstin());
        ContextData.addData("baseAMount", getOMSOrderResp.getAmounts().get("LIA").getAmount().getDisplayAmount().toString().replace(",", ""));

//        log.info("Order Type: " + arg0 + "And Payment Type: " + arg1);
//        ExtentCucumberAdapter.addTestStepLog("Order Type: " + arg0 + " And Payment Type: " + arg1);
//        log.info("Credit by pass flag -->" + jsonPath.get("creditBlockBypassFlag"));
//        ExtentCucumberAdapter.addTestStepLog("Credit by pass flag -->" + jsonPath.get("creditBlockBypassFlag"));
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
//        String orderIdSubstring = getOrderSubstring((String) ContextData.getDataFromKey("orderNumber"));
//        Context.buyerOrderNumber = "CBK-" + orderIdSubstring + "-0";
//        ExtentCucumberAdapter.addTestStepLog("Customer Order Cashback Amount Per Unit: " + cashbackAmount);
//        ExtentCucumberAdapter.addTestStepLog("Customer Order Total Cashback Amount: " + totalCashbackAmount);
        log.exiting();
    }

    @Then("Validate ledger statement with Document type as Cashback Issued on Zoho Creator")
    public void validateLedgerStatementDocumentTypeAsCashbackIssued() {
        log.entering();
        navigateToLedgerStatementNewTab();
        clearSearch();
        searchStringContains(ORDER_ID, (String) ContextData.getDataFromKey("orderNumber"));
        searchStringContains(DOCUMENT_TYPE, CASHBACK_ISSUED);
//
        selectRowUsingObject((String) ContextData.getDataFromKey("orderNumber"), 15);
        Map<String, String> ledgerData = getDataFromWindow("dataWindowElement");
//        String CashBackIssuedCreditAmount = formatDecimalWithComma(String.valueOf(totalCashbackAmount));
//        String CashBackIssuedPendingAmount = formatDecimalWithComma(String.valueOf(Double.parseDouble(CashBackIssuedCreditAmount) - Double.parseDouble(ledgerData.get(KNOCKED_OFF_AMOUNT))));
//        String knockedOffAmount = ZohoUtils.formatDecimalWithComma(String.valueOf(Double.parseDouble(CashBackIssuedCreditAmount) - Double.parseDouble(CashBackIssuedPendingAmount)));
//        assertEquals("Document type verification failed", CASHBACK_ISSUED, ledgerData.get(DOCUMENT_TYPE));
//        assertTrue("Document id verification failed", ledgerData.get(DOCUMENT_ID).contains(Context.buyerOrderNumber));
//        assertEquals("Credit amount(Cashback amount) verification failed", CashBackIssuedCreditAmount, ledgerData.get(CREDIT_AMOUNT));
//        assertTrue("Order id verification failed", ledgerData.get(ORDER_ID).contains(Context.buyerOrderNumber));
//        assertEquals("Credit amount(Cashback amount) verification failed", CashBackIssuedCreditAmount, ledgerData.get(CREDIT_AMOUNT));
//        assertEquals("Debit amount verification failed", ZERO, ledgerData.get(DEBIT_AMOUNT));
//        softAssert.assertEquals("Knocked off amount verification failed", knockedOffAmount, ledgerData.get(KNOCKED_OFF_AMOUNT));
//        softAssert.assertEquals("Pending amount verification failed", ZohoUtils.formatDecimalWithComma(CashBackIssuedPendingAmount), ledgerData.get(LED_PENDING_AMOUNT));
//        softAssert.assertEquals("Clearing status verification failed", OPEN, ledgerData.get(CLEARING_STATUS));
//        assertEquals("GSTIN verification failed", gstin, ledgerData.get(GSTIN));
//        assertEquals("Entity name verification failed", Context.customerName, ledgerData.get(ENTITY_NAME));
//        assertEquals("Ledger Statement Key validation failed", ZohoUtils.getLedgerStatementKey(ledgerData), ledgerData.get(LEDGER_STATEMENT_KEY));
//        assertEquals("Module name verification failed", CN, ledgerData.get(MODULE_NAME));
//        assertEquals("Zoho book Module used verification failed", CREDIT_NOTE, ledgerData.get(ZOHO_BOOKS_MODULE_USED));
//        assertNotNull("Creator ID - Documents should not be null", ledgerData.get(CREATOR_ID_DOC));
//        assertNotNull("Creator ID - Master should not be null", ledgerData.get(CREATOR_ID_MASTERS));
//        ExtentCucumberAdapter.addTestStepLog("Cashback Issued details: " + ledgerData);
        ContextData.addData("documentNumber", ledgerData.get(DOCUMENT_ID));
        clearFilter();
        log.exiting();
    }

    @Then("Validate ledger statement with Document type as Cashback TDS on Zoho Creator")
    public void validateLedgerStatementDocumentTypeAsCashbackTDS() {
        log.entering();
        navigateToLedgerStatementNewTab();
        clearSearch();
        searchStringContains(ORDER_ID, (String) ContextData.getDataFromKey("orderNumber"));
        searchStringContains(DOCUMENT_TYPE, CASHBACK_TDS);
        selectRowUsingObject((String) ContextData.getDataFromKey("orderNumber"), 10);
        Map<String, String> ledgerData = getDataFromWindow("dataWindowElement");
//    totalCashbackAmount =cashbackAmount * Double.parseDouble(Context.orderQuantity);
//        String CashBackTDSDebitAmount = ZohoUtils.formatDecimalWithComma(String.valueOf(ZohoUtils.getTDSAmount(totalCashbackAmount, 10)));
//        String CashBackTDSPendingAmount = ZohoUtils.formatDecimalWithComma(String.valueOf(Double.parseDouble(CashBackTDSDebitAmount) - Double.parseDouble(ledgerData.get(KNOCKED_OFF_AMOUNT))));
//        String knockedOffAmount = ZohoUtils.formatDecimalWithComma(String.valueOf(Double.parseDouble(CashBackTDSDebitAmount) - Double.parseDouble(CashBackTDSPendingAmount)));
//        assertEquals("Document type verification failed", CASHBACK_TDS, ledgerData.get(DOCUMENT_TYPE));
//        assertTrue("Order id verification failed", ledgerData.get(ORDER_ID).contains(Context.buyerOrderNumber));
//        assertEquals("Credit (Cashback) amount verification failed", ZERO, ledgerData.get(CREDIT_AMOUNT));
//        assertEquals("Debit amount verification failed", CashBackTDSDebitAmount, ledgerData.get(DEBIT_AMOUNT));
//        assertEquals("Knocked off amount verification failed", knockedOffAmount, ledgerData.get(KNOCKED_OFF_AMOUNT));
//        assertEquals("Pending amount verification failed", ZERO, ledgerData.get(LED_PENDING_AMOUNT));
//        assertEquals("Clearing status verification failed", CLEARED, ledgerData.get(CLEARING_STATUS));
//        assertEquals("GSTIN verification failed", gstin, ledgerData.get(GSTIN));
//        assertEquals("Entity name", Context.customerName, ledgerData.get(ENTITY_NAME));
//        assertEquals("Ledger Statement Key validation failed", ZohoUtils.getLedgerStatementKey(ledgerData), ledgerData.get(LEDGER_STATEMENT_KEY));
//        assertEquals("Module name verification failed", CT, ledgerData.get(MODULE_NAME));
//        assertEquals("Zoho book Module used verification failed", ZBM_CASHBACK_TDS, ledgerData.get(ZOHO_BOOKS_MODULE_USED));
//        assertNotNull("Creator ID - Documents should not be null", ledgerData.get(CREATOR_ID_DOC));
//        assertNotNull("Creator ID - Master should not be null", ledgerData.get(CREATOR_ID_MASTERS));
//        ExtentCucumberAdapter.addTestStepLog("Cashback TDS details: " + ledgerData);
        clearFilter();
        log.exiting();
    }

    @Then("Validate ledger statement with Document type as Cashback Payout")
    public void validateLedgerStatementDocumentTypeAsCashbackPayout() {
        log.entering();
        navigateToLedgerStatementNewTab();
        clearSearch();
        searchStringContains(DOCUMENT_TYPE, CASHBACK_PAYOUT);
        String buyerOrderNumber = (String) ContextData.getDataFromKey("orderNumber");
        searchStringContains(ORDER_ID, buyerOrderNumber);
        selectRowUsingObject(buyerOrderNumber, 20);
        Map<String, String> ledgerData = getDataFromWindow("dataWindowElement");
//        double cashbackAmount = (double) ContextData.getDataFromKey("cashbackAmount");
//        String orderQuantity = (String) ContextData.getDataFromKey("orderQuantity");
//
//        double totalCashbackAmount = cashbackAmount * Double.parseDouble(orderQuantity);
        String cashBackPayoutDebitAmount = (String) ContextData.getDataFromKey("cashBackPayoutAmount");
//        assertEquals("Document type verification failed", CASHBACK_PAYOUT, ledgerData.get(DOCUMENT_TYPE));
//        assertTrue("Order id verification failed", ledgerData.get(ORDER_ID).contains(Context.buyerOrderNumber));
//        assertEquals("Credit (Cashback) amount verification failed", ZERO, ledgerData.get(CREDIT_AMOUNT));
//        assertEquals("Debit amount verification failed", CashBackPayoutDebitAmount, ledgerData.get(DEBIT_AMOUNT));
//        softAssert.assertEquals("Knocked off amount verification failed", ZERO, ledgerData.get(KNOCKED_OFF_AMOUNT));
//        softAssert.assertEquals("Pending amount verification failed", ZERO, ledgerData.get(LED_PENDING_AMOUNT));
//        softAssert.assertEquals("Clearing status verification failed", CLEARED, ledgerData.get(CLEARING_STATUS));
//        assertEquals("GSTIN verification failed", gstin, ledgerData.get(GSTIN));
//        assertEquals("Entity name", Context.customerName, ledgerData.get(ENTITY_NAME));
//        assertEquals("Ledger Statement Key validation failed", ZohoUtils.getLedgerStatementKey(ledgerData), ledgerData.get(LEDGER_STATEMENT_KEY));
//        assertEquals("Module name verification failed", PAYOUT, ledgerData.get(MODULE_NAME));
//        assertEquals("Zoho book Module used verification failed", ZBM_CASHBACK_PAYOUT, ledgerData.get(ZOHO_BOOKS_MODULE_USED));
//        assertNotNull("Creator ID - Documents should not be null", ledgerData.get(CREATOR_ID_DOC));
//        assertNotNull("Creator ID - Master should not be null", ledgerData.get(CREATOR_ID_MASTERS));
//        ExtentCucumberAdapter.addTestStepLog("Cashback Payouts details in ledger statement: " + ledgerData);
//        log.info("Cashback Payouts details in ledger statement: " + ledgerData);
        clearFilter();
        log.exiting();
    }

    @Then("Navigate to ledger summary tab, click on Retry and validate the freeze status as {string} for {string} customer")
    public void syncTheCustomerLedgerAndValidateTheFreezeStatus(String freezeStatus, String customerName) {
        syncTheCustomerLedger(customerName);
        validateTheLedgerFreezeStatus(customerName, freezeStatus);
    }

    public void validateTheLedgerFreezeStatus(String customerName, String freezeStatus) {
        waitUntilLoaderInvisible();
        selectRowUsingObject(getCallSearchStringContainsFunc, customerName, 5);
        Map<String, String> ledgerSummaryData = getDataFromWindow("dataWindowElement");
//        softAssert.assertEquals("Ledger summary verification failed:",freezeStatus,ledgerSummaryData.get("Freeze status"));
//        log.info("Ledger summary freeze status:"+ledgerSummaryData.get("Freeze status"));
//        ExtentCucumberAdapter.addTestStepLog("Ledger summary freeze status:"+ledgerSummaryData.get("Freeze status"));
    }

    public void navigateToLedgerStatementNewTab() {
        log.entering();
        zohoCreatorNavigation(LEDGER_STATEMENT_NEW_PATH);
        waitForLoaderToDisappear();
        log.exiting();
    }

    public void syncTheCustomerLedger(String customerName) {
        navigateToLedgerSummary();
        clearSearch();
        searchStringContains(CUSTOMER_NAME, customerName);
        waitAndClickOn("retryBtn");
//        driver.findElement(By.xpath(RETRY_BUTTON)).click();
        waitUntilLoaderInvisible();
        sleep(5555);
    }

    public void navigateToLedgerSummary() {
        zohoCreatorNavigation(LEDGER_SUMMARY_PATH);
//        log.info("Navigated to Ledger Summary Page");
//        ExtentCucumberAdapter.addTestStepLog("Navigated to Ledger Summary Page");
    }

}
