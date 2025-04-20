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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
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
    public static final String LEDGER_STATEMENT_AR_PATH= "#Report:Accounts_Receivables_Data_Report";

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
    public static final String CREDIT_INVOICE="Credit invoice";
    public static final String AR_DOCUMENT_TYPE="Document Type";
    public static final String CUSTOMER_COMPANY_NAME="Customer company name";
    public static final String ORDER_NUMBER1="Order Number";
    public static final String AR_DOCUMENT_ID="Document ID";
    public static final String AR_DOCUMENT_AMOUNT="Document Amount";
    public static final String DOCUMENT_DATE="Document Date";
    public static final String PENDING="Pending";
    public static final String AC_STATUS="Status";
    public static final String PENDING_AMOUNT_LD="Pending amount";
    public static final String INVOICE="Invoice";

    private static Map<String, String> ledgerSummaryMapBefore;
    private static Map<String, String> ledgerSummaryMapAfterOrderPlaced;
    private static Map<String, String> ledgerSummaryMapAfterOrderInvoice;
    public static Float baseAmount;
    String ledgerId;
    private static Map<String, String> incomingPaymentBeforeOrder;
    private static Map<String, String> ledgerSummaryMapAfterDOCreated;
    private static Map<String,String> ledgerSummaryMapAfterDORejected;
    private static Map<String,String> ledgerSummaryMapAfterDOCreatedCreditByPass;

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
        syncTheCustomerLedger(arg0);
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
    public void validateLedgerBalBeforeAndAfterDoCreated(Map<String, String> before, Map<String, String> after, int advanceAmount) {

        String currentUsableLedBal = String.valueOf(Double.parseDouble(replaceComma(before.get(USABLE_LEDGER_BALANCE))) - Double.parseDouble(CurrencyUtils.getNewInstance().convertPaisaToRupees(advanceAmount))- Double.parseDouble(CurrencyUtils.getNewInstance().convertPaisaToRupees(advanceAmount)));
        String currentBlockedLedBal = String.valueOf(Double.parseDouble(replaceComma(before.get(BLOCKED_LEDGER_BALANCE))) + Double.parseDouble(CurrencyUtils.getNewInstance().convertPaisaToRupees(advanceAmount))+ Double.parseDouble(CurrencyUtils.getNewInstance().convertPaisaToRupees(advanceAmount)));
        Assert.assertEquals(after.get(ACCOUNTING_LEDGER_BALANCE),before.get(ACCOUNTING_LEDGER_BALANCE), "Available ledger balance validation failed");
        Assert.assertEquals(formatDecimal(currentUsableLedBal),replaceComma(after.get(USABLE_LEDGER_BALANCE)), "Usable ledger balance validation failed");
        Assert.assertEquals(formatDecimal(currentBlockedLedBal),replaceComma(after.get(BLOCKED_LEDGER_BALANCE)),"Blocked ledger balance validation failed");
    }
    public void validateLedgerBalBeforeAndAfterDoRejected(Map<String, String> before, Map<String, String> after, int advanceAmount) {

        String currentUsableLedBal = String.valueOf(Double.parseDouble(replaceComma(after.get(USABLE_LEDGER_BALANCE))) + Double.parseDouble(CurrencyUtils.getNewInstance().convertPaisaToRupees(advanceAmount)));
        String currentBlockedLedBal = String.valueOf(Double.parseDouble(replaceComma(after.get(BLOCKED_LEDGER_BALANCE))) - Double.parseDouble(CurrencyUtils.getNewInstance().convertPaisaToRupees(advanceAmount)));
        Assert.assertEquals(after.get(ACCOUNTING_LEDGER_BALANCE),before.get(ACCOUNTING_LEDGER_BALANCE), "Available ledger balance validation failed");
        Assert.assertEquals(formatDecimal(currentUsableLedBal),replaceComma(before.get(USABLE_LEDGER_BALANCE)), "Usable ledger balance validation failed");
        Assert.assertEquals(formatDecimal(currentBlockedLedBal),replaceComma(before.get(BLOCKED_LEDGER_BALANCE)), "Blocked ledger balance validation failed");
    }
    public void validateLedgerBalBeforeAndAfterDoCreatedForCreditBypass(Map<String, String> before, Map<String, String> after, int advanceAmount) {

        String currentUsableLedBal = String.valueOf(Double.parseDouble(replaceComma(before.get(USABLE_LEDGER_BALANCE))) - Double.parseDouble(CurrencyUtils.getNewInstance().convertPaisaToRupees(advanceAmount)));
        String currentBlockedLedBal = String.valueOf(Double.parseDouble(replaceComma(before.get(BLOCKED_LEDGER_BALANCE))) + Double.parseDouble(CurrencyUtils.getNewInstance().convertPaisaToRupees(advanceAmount)));
        Assert.assertEquals(after.get(ACCOUNTING_LEDGER_BALANCE),before.get(ACCOUNTING_LEDGER_BALANCE), "Available ledger balance validation failed");
        Assert.assertEquals(formatDecimal(currentUsableLedBal),replaceComma(after.get(USABLE_LEDGER_BALANCE)), "Usable ledger balance validation failed");
        Assert.assertEquals(formatDecimal(currentBlockedLedBal),replaceComma(after.get(BLOCKED_LEDGER_BALANCE)), "Blocked ledger balance validation failed");
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

    public Map<String, String> validateAccountReceivablesData(String cellValue) {
        Map<String, String> accountReceivablesData = getRowDataAsMap(cellValue);
        if (accountReceivablesData == null) {
            accountReceivablesData = retryIfTableDataIsNull(accountReceivablesData, cellValue);
        }
        log.info("==========Validating Invoice Data in Zoho Creator=========");
        return accountReceivablesData;
    }
    public void clickOnAccountReceivablesTab() {
        zohoCreatorNavigation(LEDGER_STATEMENT_AR_PATH);
    }


    @Then("Validate the account receivables entry under ledger statement on Zoho Creator")
    public void validateTheAccountReceivablesEntryUnderLedgerStatement() {

        clickOnAccountReceivablesTab();
        clearSearch();
        Map<String, String> accountReceivablesData = validateAccountReceivablesData((String) ContextData.getDataFromKey("invoiceNumber"));
        sleep(999);
        assertEquals(accountReceivablesData.get(AR_DOCUMENT_TYPE),CREDIT_INVOICE,"Document type validation failed");
        assertEquals(accountReceivablesData.get(CUSTOMER_COMPANY_NAME),ContextData.getDataFromKey("customerName"),"Customer Company Name validation failed");
        assertEquals(gstin, "GSTIN validation failed", accountReceivablesData.get(GSTIN));
        assertEquals(accountReceivablesData.get(ORDER_NUMBER1),ContextData.getDataFromKey("buyerOrderNumber"),"Order Number validation failed");
        assertEquals(accountReceivablesData.get(AR_DOCUMENT_ID),ContextData.getDataFromKey("invoiceNumber"),"Document Id validation failed");
        assertEquals(accountReceivablesData.get(AR_DOCUMENT_AMOUNT),(ContextData.getDataFromKey("invoiceAmount")),"Document Amount validation failed");
        assertEquals(accountReceivablesData.get(AC_STATUS),PENDING,"Document Status validation failed");
        assertEquals(accountReceivablesData.get(DOCUMENT_DATE),convertDateFormat((String)ContextData.getDataFromKey("invoiceDate")),"Document Date validation failed");

        LocalDate currentDate = LocalDate.now(); // Current Date
        LocalDate newDate = currentDate.plusDays((Long) ContextData.getDataFromKey("creditDays")); // Add 10 days to the current date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy"); // Define a custom date format
        assertEquals(accountReceivablesData.get("Due Date"),newDate.format(formatter),"Document Due Date verification failed");
        AllureCustomLogReporter.addTestStepLog("Account receivables entry validated for Credit type order in Zoho creator, with entry details--> " + accountReceivablesData);
    }

    @Then("Validate the ledger entry for Credit Order under new ledger statement on Zoho Creator")
    public void validateTheLedgerEntryForCreditOrderUnderNewLedgerStatementOnZohoCreator() {
        navigateToLedgerStatementNewTab();
        clearSearch();
        searchStringContains(DOCUMENT_ID, (String)ContextData.getDataFromKey("invoiceNumber"));
        selectRowUsingObject((String)ContextData.getDataFromKey("invoiceNumber"), 20);
        Map<String, String> ledgerData = getDataFromWindow();


        String baseAndGstAmountSum = String.valueOf(Double.parseDouble(ledgerData.get(BASE_AMOUNT).replace(",", "")) + Double.parseDouble(ledgerData.get(GST_AMOUNT).replace(",", "")));
        assertEquals(ledgerData.get(DOCUMENT_TYPE),CREDIT_INVOICE,"Document type validation failed");
        assertEquals(formatDecimalWithComma(ledgerData.get(DEBIT_AMOUNT).replace(",", "")),formatDecimalWithComma((String)ContextData.getDataFromKey("invoiceAmount")),"Debit amount validation failed");
        assertEquals(formatDecimalWithComma(ledgerData.get(PENDING_AMOUNT_LD).replace(",", "")),formatDecimalWithComma((String)ContextData.getDataFromKey("invoiceAmount")),"Pending amount validation failed");
        assertEquals(ledgerData.get(KNOCKED_OFF_AMOUNT).replace(",", ""),ZERO,"Knock off amount validation failed");
        assertEquals(ledgerData.get(CREDIT_AMOUNT).replace(",", ""),ZERO,"Credit amount validation failed");
        softAssert.assertEquals(ledgerData.get(CLEARING_STATUS),OPEN,"Clearing status validation failed");
        assertEquals(ledgerData.get(MODULE_NAME),INVOICE,"Module name validation failed");
        assertEquals(ledgerData.get(ZOHO_BOOKS_MODULE_USED),INVOICE,"Zoho Books module used validation failed");
        assertEquals(ledgerData.get(GSTIN),gstin,"GSTIN validation failed");
        assertEquals(ledgerData.get(LEDGER_STATEMENT_KEY),getLedgerStatementKey(ledgerData),"Ledger Statement Key validation failed");
        assertNotNull(ledgerData.get(CREATOR_ID_DOC));
        assertNotNull(ledgerData.get(CREATOR_ID_MASTERS));
        assertEquals(ledgerData.get(ORDER_ID),ContextData.getDataFromKey("buyerOrderNumber"),"Order id validation failed");
        assertEquals(ledgerData.get(DOCUMENT_ID),ContextData.getDataFromKey("invoiceNumber"),"Document id validation failed");
        assertEquals(formatDecimalWithComma(baseAndGstAmountSum.replace(",", "")),formatDecimalWithComma(ledgerData.get(DEBIT_AMOUNT)),"Debit amount validation failed");
        assertEquals(formatDecimalWithComma(ledgerData.get(BASE_AMOUNT).replace(",", "")),formatDecimalWithComma(baseAmount.toString()),"Base amount validation failed");
        assertEquals(formatDecimalWithComma(ledgerData.get(GST_AMOUNT).replace(",", "")),formatDecimalWithComma(String.valueOf(getGSTAmount(baseAmount, 18))),"GST amount validation failed");
        log.info("Zoho Ledger Validation for Credit Order successful");
        AllureCustomLogReporter.addTestStepLog("Ledger statement new for Credit Invoice type -->" + ledgerData);
    }

    @Then("Validate the ledger entry for Credit Order Knocked Off Amount under new ledger statement")
    public void validateTheLedgerEntryForCreditOrderKnockedOffAmountUnderNewLedgerStatement() {

        navigateToLedgerStatementNewTab();
        clearSearch();
        searchStringContains(DOCUMENT_ID, (String)ContextData.getDataFromKey("invoiceNumber"));
        selectRowUsingObject((String)ContextData.getDataFromKey("invoiceNumber"), 20);
        Map<String, String> ledgerData = getDataFromWindow("dataWindowElement");

        String baseAndGstAmountSum = String.valueOf(Double.parseDouble(ledgerData.get(BASE_AMOUNT).replace(",", "")) + Double.parseDouble(ledgerData.get(GST_AMOUNT).replace(",", "")));
        AllureCustomLogReporter.addTestStepLog("Ledger statement new for Credit Invoice type -->" + ledgerData);
        Assert.assertEquals(ledgerData.get(DOCUMENT_TYPE),CREDIT_INVOICE,"Document type validation failed");
        Assert.assertEquals(formatDecimalWithComma(ledgerData.get(DEBIT_AMOUNT).replace(",", "")),formatDecimalWithComma((String)ContextData.getDataFromKey("invoiceAmount")),"Debit amount validation failed");
        Assert.assertEquals(formatDecimalWithComma(String.valueOf(Double.parseDouble(ledgerData.get(PENDING_AMOUNT_LD).replace(",", "")) + Double.parseDouble(ledgerData.get(KNOCKED_OFF_AMOUNT).replace(",", "")))),formatDecimalWithComma((String)ContextData.getDataFromKey("invoiceAmount")),"Pending amount validation failed");
        softAssert.assertEquals(formatDecimalWithComma(ledgerData.get(KNOCKED_OFF_AMOUNT).replace(",", "")),formatDecimalWithComma((String)ContextData.getDataFromKey("advanceAmount")),"Knock off amount validation failed");
        assertEquals(ledgerData.get(CREDIT_AMOUNT).replace(",", ""),ZERO,"Credit amount validation failed");
        softAssert.assertEquals(ledgerData.get(CLEARING_STATUS),OPEN,"Clearing status validation failed");
        Assert.assertEquals(ledgerData.get(MODULE_NAME),INVOICE,"Module name validation failed");
        Assert.assertEquals(ledgerData.get(ZOHO_BOOKS_MODULE_USED),INVOICE,"Zoho Books module used validation failed");
        Assert.assertEquals(ledgerData.get(GSTIN),gstin,"GSTIN validation failed");
        Assert.assertNotNull(ledgerData.get(CREATOR_ID_DOC));
        Assert.assertNotNull(ledgerData.get(CREATOR_ID_MASTERS));
        Assert.assertEquals(ledgerData.get(LEDGER_STATEMENT_KEY),getLedgerStatementKey(ledgerData),"Ledger Statement Key validation failed");
        Assert.assertEquals(formatDecimalWithComma(ledgerData.get(BASE_AMOUNT).replace(",", "")),formatDecimalWithComma(baseAmount.toString()),"Base amount validation failed");
        Assert.assertEquals(formatDecimalWithComma(ledgerData.get(GST_AMOUNT).replace(",", "")),formatDecimalWithComma(String.valueOf(getGSTAmount(baseAmount, 18))),"GST amount validation failed");
        Assert.assertEquals(formatDecimalWithComma(baseAndGstAmountSum.replace(",", "")),formatDecimalWithComma(ledgerData.get(DEBIT_AMOUNT)),"Debit amount validation failed");
        Assert.assertEquals(ledgerData.get("Order id"),(String)ContextData.getDataFromKey("buyerOrderNumber"),"Order id validation failed");
        Assert.assertEquals(ledgerData.get(DOCUMENT_ID),(String)ContextData.getDataFromKey("invoiceNumber"),"Document id validation failed");
        log.info("Zoho Ledger Validation for Credit Order successful");
        clearFilter();
    }
    @Then("Get the incoming payment pending amount details before order creation in ledger statement for GSTIN {string}")
    public void getTheLedgerEntryForIncomingPaymentBeforeOrder(String arg0) {

        navigateToLedgerStatementNewTab();
        clearSearch();
        sortTable( "Document date", "Ascending");
        searchStringContains(GSTIN, arg0);
        searchStringContains(CLEARING_STATUS, OPEN);
        searchStringContains(DOCUMENT_TYPE, INCOMING_PAYMENT);
        selectRowUsingObject(arg0, 5);
        Map<String, String> ledgerData = getDataFromWindow();
        ledgerId = ledgerData.get(LEDGER_STATEMENT_KEY);

        AllureCustomLogReporter.addTestStepLog("Ledger_Statement_New data with GSTIN ---> " + arg0 + " And Row data as -->" + ledgerData);
        org.junit.Assert.assertEquals("Document type validation failed", INCOMING_PAYMENT, ledgerData.get(DOCUMENT_TYPE));
        incomingPaymentBeforeOrder = new HashMap<String, String>();
        incomingPaymentBeforeOrder.put(KNOCKED_OFF_AMOUNT, ledgerData.get(KNOCKED_OFF_AMOUNT));
        incomingPaymentBeforeOrder.put(PENDING_AMOUNT_LD, formatDecimalWithComma(ledgerData.get(PENDING_AMOUNT_LD)));
        log.info("Zoho Ledger incoming payment before order creation");
        clearFilter();
    }
    @Then("validate the ledger entry for incoming payment after order invoice under new ledger statement for GSTIN {string}")
    public void validateTheLedgerEntryForIncomingPaymentAfterOrderInvoice(String arg0) {

        navigateToLedgerStatementNewTab();
        clearSearch();
        searchStringContains(LEDGER_STATEMENT_KEY, ledgerId);
        selectRowUsingObject(arg0, 10);
        Map<String, String> ledgerData = getDataFromWindow();

        log.info("After Invoice Knocked off amount : " + ledgerData.get(KNOCKED_OFF_AMOUNT));
        log.info("After Invoice Pending Amount: " + formatDecimalWithComma(ledgerData.get(PENDING_AMOUNT_LD)));

        log.info("Before Order Knocked off amount : " + incomingPaymentBeforeOrder.get(KNOCKED_OFF_AMOUNT));
        log.info("Before Order Pending Amount : " + formatDecimalWithComma(incomingPaymentBeforeOrder.get(PENDING_AMOUNT_LD)));

        AllureCustomLogReporter.addTestStepLog("Ledger_Statement_New data with GSTIN ---> " + gstin + " And Row data as -->" + ledgerData);
        org.junit.Assert.assertEquals("Document type validation failed", INCOMING_PAYMENT, ledgerData.get(DOCUMENT_TYPE));
        org.junit.Assert.assertEquals("Pending Amount validation failed", formatDecimalWithComma((String)ContextData.getDataFromKey("advanceAmount")), formatDecimalWithComma(String.valueOf(Double.parseDouble(incomingPaymentBeforeOrder.get(PENDING_AMOUNT_LD).replace(",", "")) - Double.parseDouble(ledgerData.get(PENDING_AMOUNT_LD).replace(",", "")))));
        org.junit.Assert.assertEquals("Knock Off Amount validation failed", formatDecimalWithComma((String)ContextData.getDataFromKey("advanceAmount")), formatDecimalWithComma(String.valueOf(Double.parseDouble(ledgerData.get(KNOCKED_OFF_AMOUNT).replace(",", "")) - Double.parseDouble(incomingPaymentBeforeOrder.get(KNOCKED_OFF_AMOUNT).replace(",", "")))));
        log.info("Zoho Ledger incoming payment before order creation");
        clearFilter();
    }
    @Then("validate the ledger balance after DO created in ledger summary for {string} customer")
    public void validateTheLedgerBalanceAfterDOCreatedInLedgerSummaryForCustomer(String arg0) {
        log.entering();
        syncTheCustomerLedger(arg0);
        ledgerSummaryMapAfterDOCreated= getLedgerSummaryByCustomerName(arg0);
        AllureCustomLogReporter.addTestStepLog("Customer Ledger Details After DO Creation For Customer : " + arg0 + " ---> " + ledgerSummaryMapAfterDOCreated);
        Assert.assertEquals(ledgerSummaryMapBefore.get(CUSTOMER_NAME),arg0, "Validation failed for customer name in ledger summary");
        validateLedgerBalBeforeAndAfterDoCreated(ledgerSummaryMapBefore, ledgerSummaryMapAfterDOCreated, (Integer) ContextData.getDataFromKey("advanceAmountInPaisa"));
        log.info("Ledger summary details after DO created : " + ledgerSummaryMapAfterDOCreated);
        AllureCustomLogReporter.addTestStepLog("Ledger summary details after DO created : " + ledgerSummaryMapAfterDOCreated);
    }
    @Then("validate the ledger balance after DO rejected in ledger summary for {string} customer")
    public void validateTheLedgerBalanceAfterDORejectedInLedgerSummaryForCustomer(String arg0) {
        log.entering();
        syncTheCustomerLedger(arg0);
        ledgerSummaryMapAfterDORejected=getLedgerSummaryByCustomerName(arg0);
        AllureCustomLogReporter.addTestStepLog("Customer Ledger Details After DO Rejected For Customer : " + arg0 + " ---> " + ledgerSummaryMapAfterDORejected);
        Assert.assertEquals(ledgerSummaryMapBefore.get(CUSTOMER_NAME),arg0, "Validation failed for customer name in ledger summary");
        validateLedgerBalBeforeAndAfterDoRejected(ledgerSummaryMapBefore, ledgerSummaryMapAfterDORejected, (Integer)ContextData.getDataFromKey("advanceAmountInPaisa"));
        log.info("Ledger summary details after DO Rejected : " + ledgerSummaryMapAfterDORejected);
        AllureCustomLogReporter.addTestStepLog("Ledger summary details after DO Rejected : " + ledgerSummaryMapAfterDORejected);
    }
    @Then("validate the ledger balance after DO created for credit bypass in ledger summary for {string} customer")
    public void validateTheLedgerBalanceAfterDOCreatedForCreditBypassInLedgerSummaryForCustomer(String arg0) {
        log.entering();
        syncTheCustomerLedger(arg0);
        ledgerSummaryMapAfterDOCreatedCreditByPass=getLedgerSummaryByCustomerName(arg0);
        AllureCustomLogReporter.addTestStepLog("Customer Ledger Details After DO Creation For Customer : " + arg0 + " ---> " + ledgerSummaryMapAfterDOCreatedCreditByPass);
        Assert.assertEquals(ledgerSummaryMapBefore.get(CUSTOMER_NAME),arg0, "Validation failed for customer name in ledger summary");
        validateLedgerBalBeforeAndAfterDoCreatedForCreditBypass(ledgerSummaryMapBefore, ledgerSummaryMapAfterDOCreatedCreditByPass, (Integer)ContextData.getDataFromKey("advanceAmountInPaisa"));
        log.info("Ledger summary details after DO created : " + ledgerSummaryMapAfterDOCreatedCreditByPass);
        AllureCustomLogReporter.addTestStepLog("Ledger summary details after DO created : " + ledgerSummaryMapAfterDOCreatedCreditByPass);
    }
}
