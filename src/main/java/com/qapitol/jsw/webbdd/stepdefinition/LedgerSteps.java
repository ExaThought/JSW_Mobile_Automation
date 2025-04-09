package com.qapitol.jsw.webbdd.stepdefinition;

import com.qapitol.jsw.utils.AllureCustomLogReporter;
import com.qapitol.jsw.utils.CurrencyUtils;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.data.ContextData;
import com.qapitol.sauron.data.Data;
import com.qapitol.sauron.logging.SauronLogger;
import com.qapitol.sauron.logging.SimpleLogger;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.Map;

import static com.qapitol.jsw.utils.AllureCustomLogReporter.addTestStepLog;
import static com.qapitol.jsw.webbdd.stepdefinition.IncomingPaymentSteps.*;
import static java.util.Objects.nonNull;
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
    public static final String ACCOUNTING_LEDGER_BALANCE = "Accounting Ledger Balance";
    public static final String USABLE_LEDGER_BALANCE = "Usable Ledger Balance";
    public static final String BLOCKED_LEDGER_BALANCE = "Blocked Balance";

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

    private static Map<String, String> ledgerSummaryMapBefore;
    private static Map<String, String> ledgerSummaryMapAfterOrderPlaced;
    private static Map<String, String> ledgerSummaryMapAfterOrderInvoice;

    SoftAssert softAssert = new SoftAssert();

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
        addTestStepLog("Cashback Issued details: " + ledgerData);
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
        assertEquals(ledgerData.get(KNOCKED_OFF_AMOUNT), knockedOffAmount, "Knocked off amount verification failed");
        assertEquals(ledgerData.get(LED_PENDING_AMOUNT), ZERO, "Pending amount verification failed");
        assertEquals(ledgerData.get(CLEARING_STATUS), CLEARED, "Clearing status verification failed");
        assertEquals(ledgerData.get(GSTIN), (String) ContextData.getDataFromKey("gstin"), "GSTIN verification failed");
        assertEquals(ledgerData.get(ENTITY_NAME), (String) ContextData.getDataFromKey("customerName"), "Entity name");
        assertEquals(ledgerData.get(LEDGER_STATEMENT_KEY), getLedgerStatementKey(ledgerData), "Ledger Statement Key validation failed");
        assertEquals(ledgerData.get(MODULE_NAME), CT, "Module name verification failed");
        assertEquals(ledgerData.get(ZOHO_BOOKS_MODULE_USED), ZBM_CASHBACK_TDS, "Zoho book Module used verification failed");
        assertNotNull(ledgerData.get(CREATOR_ID_DOC), "Creator ID - Documents should not be null");
        assertNotNull(ledgerData.get(CREATOR_ID_MASTERS), "Creator ID - Master should not be null");
        addTestStepLog("Cashback TDS details: " + ledgerData);
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
        addTestStepLog("Cashback Payouts details in ledger statement: " + ledgerData);
        log.info("Cashback Payouts details in ledger statement: " + ledgerData);
        clearFilter();
        log.exiting();
    }

    @Then("Navigate to ledger summary tab, click on Retry and validate the freeze status as {string} for {string} customer")
    public void syncTheCustomerLedgerAndValidateTheFreezeStatus(String freezeStatus, String customerName) {
        log.entering();
        syncTheCustomerLedger(customerName);
        validateTheLedgerFreezeStatus(customerName, freezeStatus);
        log.exiting();
    }

    public void validateTheLedgerFreezeStatus(String customerName, String freezeStatus) {
        SoftAssert softAssert = new SoftAssert();
        waitUntilLoaderInvisible();
        selectRowUsingObject(getCallSearchStringContainsFunc, customerName, 5);
        Map<String, String> ledgerSummaryData = getDataFromWindow("dataWindowElement");
        softAssert.assertEquals("Ledger summary verification failed:", freezeStatus, ledgerSummaryData.get("Freeze status"));
        log.info("Ledger summary freeze status:" + ledgerSummaryData.get("Freeze status"));
        addTestStepLog("Ledger summary freeze status:" + ledgerSummaryData.get("Freeze status"));
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
        sleep(999);
    }

    public void navigateToLedgerSummary() {
        Grid.setSessionName("zoho_creator");
        zohoCreatorNavigation(LEDGER_SUMMARY_PATH);
        log.info("Navigated to Ledger Summary Page");
        addTestStepLog("Navigated to Ledger Summary Page");
    }

    @Then("validate the ledger entry, knocked off amount and clearing status on Zoho Creator")
    public void validateTheLedgerAndIncomingPaymentEntriesKnockedOfAmountAndClearingStatus() {
        log.entering();
        SoftAssert softAssert = new SoftAssert();
        navigateToLedgerStatementNewTab();
        clearSearch();
        searchStringContains(DOCUMENT_ID, (String) ContextData.getDataFromKey("invoiceNumber"));
        selectRowUsingObject((String) ContextData.getDataFromKey("invoiceNumber"), 10);
        Map<String, String> ledgerAI = getDataFromWindow("dataWindowElement");
        addTestStepLog("Ledger_Statement data with document id --->  And Row data as -->" + ledgerAI);
        assertNotNull(ledgerAI.get("Entity ID"));
        Assert.assertEquals(ledgerAI.get(DOCUMENT_TYPE), ADVANCE_INVOICE);
        Assert.assertEquals(ContextData.getDataFromKey("orderNumber"), ledgerAI.get(ORDER_ID));

        Assert.assertEquals((double) ContextData.getDataFromKey("invoicedBaseAmount"), Double.parseDouble(ledgerAI.get(BASE_AMOUNT).replace(",", "")));
        Assert.assertEquals((double) ContextData.getDataFromKey("invoicedTaxAmount"), Double.parseDouble(ledgerAI.get(GST_AMOUNT).replace(",", "")));
        Assert.assertEquals((double) ContextData.getDataFromKey("invoiceAmount"), Double.parseDouble(ledgerAI.get(DEBIT_AMOUNT).replace(",", "")));
        Assert.assertEquals((double) ContextData.getDataFromKey("invoicedBaseAmount") + (double) ContextData.getDataFromKey("invoicedTaxAmount"), (double) ContextData.getDataFromKey("invoiceAmount"));
        assertNotNull(ledgerAI.get(CREATOR_ID_MASTERS));
        assertNotNull(ledgerAI.get(ZOHO_BOOKS_ID));
        assertNotNull(ledgerAI.get(CREATOR_ID_DOC));
        softAssert.assertEquals((double) ContextData.getDataFromKey("invoiceAmount"), Double.parseDouble(ledgerAI.get(KNOCKED_OFF_AMOUNT).replace(",", "")));
        softAssert.assertEquals(CLEARED, ledgerAI.get(CLEARING_STATUS));
        log.exiting();
    }

    public Map<String, String> fetchLedgerSummary(String customerNameValue) {

        navigateToLedgerSummary();
        // clearFilter();
        log.info("Buyer or customer details for customer name:- " + customerNameValue);
        //  searchStringContains(CUSTOMER_NAME, customerNameValue);
        return getLedgerSummaryByCustomerName(customerNameValue);

    }

    public Map<String, String> getLedgerSummaryByCustomerName(String customerNameValue) {
        Map<String, String> tableData = getRowDataAsMap(customerNameValue);
        if (tableData == null) {
            tableData = retryIfTableDataIsNull(tableData, customerNameValue);
        }
        return tableData;
    }

    @When("Check customer ledger balance before in ledger summary for {string} customer")
    public void checkCustomerLedgerBalanceBeforeInLedgerSummary(String arg0) {

        log.entering();
        ledgerSummaryMapBefore = fetchLedgerSummary(arg0);
        addTestStepLog("Customer Ledger Details Before Order Creation For Customer : " + arg0 + " ---> " + ledgerSummaryMapBefore);
        log.info("Customer Ledger Details Before Order Creation For Customer : " + arg0 + " ---> " + ledgerSummaryMapBefore);
        log.exiting();
    }

    @Then("validate the ledger balance after order placed in ledger summary for {string} customer")
    public void validateTheLedgerBalanceAfterOrderPlacedInLedgerSummaryForCustomer(String arg0) {
        log.entering();
        syncTheCustomerLedger(arg0);
        ledgerSummaryMapAfterOrderPlaced = getLedgerSummaryByCustomerName(arg0);
        addTestStepLog("Customer Ledger Details After Order Creation For Customer : " + arg0 + " ---> " + ledgerSummaryMapAfterOrderPlaced);
        assertTrue("Validation failed for customer name in ledger summary", ledgerSummaryMapBefore.get(CUSTOMER_NAME).contains(arg0));
        validateLedgerBalBeforeAndAfterOrderPlaced(ledgerSummaryMapBefore, ledgerSummaryMapAfterOrderPlaced, (Integer) ContextData.getDataFromKey("orderAmount"));
        log.info("Ledger summary details after order placed : " + ledgerSummaryMapAfterOrderPlaced);
        log.exiting();
    }

    public void validateLedgerBalBeforeAndAfterOrderPlaced(Map<String, String> before, Map<String, String> after, int orderAmount) {

        String expectedUsableLedBal = String.valueOf(Double.parseDouble(replaceComma(before.get(USABLE_LEDGER_BALANCE))) - Double.parseDouble(CurrencyUtils.getNewInstance().convertPaisaToRupees(orderAmount)));
        String expectedBlockedLedBal = String.valueOf(Double.parseDouble(replaceComma(before.get(BLOCKED_LEDGER_BALANCE))) + Double.parseDouble(CurrencyUtils.getNewInstance().convertPaisaToRupees(orderAmount)));
        assertEquals(before.get(ACCOUNTING_LEDGER_BALANCE), after.get(ACCOUNTING_LEDGER_BALANCE), "Available ledger balance validation failed");
        softAssert.assertEquals(replaceComma(after.get(USABLE_LEDGER_BALANCE)), formatDecimal(expectedUsableLedBal), "Usable ledger balance validation failed");
        assertEquals(replaceComma(after.get(BLOCKED_LEDGER_BALANCE)), formatDecimal(expectedBlockedLedBal), "Blocked ledger balance validation failed");
    }

    public void validateLedgerBalBeforeAndAfterIncomingPaymentTagging(Map<String, String> before, Map<String, String> after, int amount) {
        String currentAccLedgerBal = String.valueOf(Double.parseDouble(replaceComma(after.get(ACCOUNTING_LEDGER_BALANCE))) - Double.parseDouble(CurrencyUtils.getNewInstance().convertPaisaToRupees(amount)));
        String currentBlockedLedBal = String.valueOf(Double.parseDouble(replaceComma(after.get(BLOCKED_LEDGER_BALANCE))) - Double.parseDouble(CurrencyUtils.getNewInstance().convertPaisaToRupees(amount)));
        assertEquals(replaceComma(before.get(ACCOUNTING_LEDGER_BALANCE)), formatDecimal(currentAccLedgerBal), "Available ledger balance validation failed");
        assertEquals(before.get(USABLE_LEDGER_BALANCE), after.get(USABLE_LEDGER_BALANCE), "Usable ledger balance validation failed");
        assertEquals(replaceComma(before.get(BLOCKED_LEDGER_BALANCE)), formatDecimal(currentBlockedLedBal), "Blocked ledger balance validation failed");

    }

    @Then("validate the ledger entry for incoming payment under new ledger statement")
    public void validateTheLedgerEntryForIncomingPayment() {
        log.entering();
        String gstin = (String) ContextData.getDataFromKey("buyerGstin");
        String paymentId = (String) ContextData.getDataFromKey("paymentId");
        navigateToLedgerStatementNewTab();
        clearSearch();
        searchStringContains(DOCUMENT_ID, paymentId);
        selectRowUsingObject(paymentId, 5);
        Map<String, String> ledgerData = getDataFromWindow();

        AllureCustomLogReporter.addTestStepLog("Ledger_Statement_New data with document id ---> " + paymentId + " And Row data as -->" + ledgerData);
        assertEquals(ledgerData.get(DOCUMENT_TYPE), INCOMING_PAYMENT, "Document type validation failed");
        assertEquals(CurrencyUtils.convertPaisaToRupeeWithCommaFormat((int) ContextData.getDataFromKey("orderAmount")), formatDecimalWithComma(ledgerData.get(CREDIT_AMOUNT)), "Credited Amount validation failed");
        assertEquals(ledgerData.get(KNOCKED_OFF_AMOUNT), ZERO, "Knock off amount validation failed");
        assertEquals(ledgerData.get(DEBIT_AMOUNT), ZERO, "Debit amount validation failed");
        assertEquals(CurrencyUtils.convertPaisaToRupeeWithCommaFormat((int) ContextData.getDataFromKey("orderAmount")), formatDecimalWithComma(ledgerData.get(LED_PENDING_AMOUNT)), "Pending Amount validation failed");
        assertEquals(ledgerData.get(MODULE_NAME), PAYMENTS_RECEIVED, "Module name validation failed");
        assertEquals(ledgerData.get(ZOHO_BOOKS_MODULE_USED), PAYMENTS_RECEIVED2, "Zoho Books module used validation failed");
        assertEquals(gstin, ledgerData.get(GSTIN), "GSTIN validation failed");
        assertEquals(getLedgerStatementKey(ledgerData), ledgerData.get(LEDGER_STATEMENT_KEY), "Ledger Statement Key validation failed");
        assertNotNull(ledgerData.get(CREATOR_ID_DOC));
        assertNotNull(ledgerData.get(CREATOR_ID_MASTERS));
        log.info("Zoho Ledger Validation for incoming payment auto tagging successful");
        clearFilter();
        log.exiting();
    }

    @And("Check customer ledger balance after incoming payment in ledger summary for {string} and validate")
    public void checkCustomerLedgerBalanceAfterInLedgerSummaryAndValidate(String arg0) {
        log.entering();
        syncTheCustomerLedger(arg0);
        Map<String, String> afterLedgerSummaryMap = getLedgerSummaryByCustomerName(arg0);
        AllureCustomLogReporter.addTestStepLog("Customer Ledger Details After Incoming Payment Successful For Customer : " + arg0 + " ---> " + afterLedgerSummaryMap);

        int amount = 0;
        if (nonNull(Data.get().get("incomingPaymentAmount"))) {
            amount = (int) Data.get().get("incomingPaymentAmount");
        } else if (nonNull(ContextData.getDataFromKey("orderAmount"))) {
            amount = (int) ContextData.getDataFromKey("orderAmount");
        }
        assertTrue("Validation failed for customer name in ledger summary", ledgerSummaryMapBefore.get(CUSTOMER_NAME).contains(arg0));
        validateLedgerBalBeforeAndAfterIncomingPaymentTagging(ledgerSummaryMapBefore, afterLedgerSummaryMap, amount);
        log.info("Ledger summary details after incoming payment : " + afterLedgerSummaryMap);
        log.exiting();
    }

}
