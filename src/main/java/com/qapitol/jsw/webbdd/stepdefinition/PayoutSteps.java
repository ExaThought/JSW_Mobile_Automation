package com.qapitol.jsw.webbdd.stepdefinition;

import com.qapitol.jsw.apitest.apis.impl.OrderImpl;
import com.qapitol.jsw.apitest.pojos.order.updateShipment.UpdateShipmentResp;
import com.qapitol.jsw.utils.CurrencyUtils;
import com.qapitol.jsw.utils.ExcelUtils;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.data.ContextData;
import com.qapitol.sauron.logging.SauronLogger;
import com.qapitol.sauron.logging.SimpleLogger;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Keys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.qapitol.jsw.utils.RandomStringGenerator.generateRandomString;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class PayoutSteps extends BaseSteps {
    private final SimpleLogger log = SauronLogger.getLogger();

    public static final String CASHBACK = "Cashback";
    public static final String REFUND = "Refund";
    public static final String SELLER = "Seller";
    public static final String PAYOUT_AMOUNT = "Payout amount";
    public static final String CREATED = "Created";
    public static final String ORDER_NUMBER = "Order number";
    public static final String APPROVED = "Approved";
    public static final String DOCUMENT_ID1 = "Document Id";
    public static final String SUCCESS = "Success";
    public static final String PAYOUT_NUMBER = "Payout number";
    public static final String PAYOUT_STATUS = "Payout status";
    public static final String DOCUMENT_AMOUNT = "Document amount";
    public static final String BASE_AMOUNT = "Base amount";
    public static final String TDS = "TDS";
    public static final String ZERO = "0.00";
    public static final String GST_AMOUNT = "GST amount";
    public static final String PAYOUT_TYPE = "Payout type";

    public static final String PENDING_PAYOUTS_PATH = "#Page:Payouts_Pending";
    public static final String APPROVED_PAYOUTS_PATH = "#Page:Payouts_Approved";
    public static final String EXPORT_PAYOUTS_DEUTSCHE_BANK_PATH = "#Report:Deutsche_Bank_payouts";
    public static final String IMPORT_PAYOUTS_DEUTSCHE_BANK_PATH = "#Report:All_Db_Bank_Imports";
    public static final String HISTORICAL_PAYOUTS_PATH = "#Page:Payouts_Historical";

    @Then("Get the {string} payout amount")
    public void getThePayoutAmount(String arg0) throws IOException, InterruptedException {
        log.entering();
        OrderImpl orderCreation = new OrderImpl();

        double payoutAmount = 0.0D;

        UpdateShipmentResp updateShipmentResp = getPojoFromContextData("updateShipmentResp", UpdateShipmentResp.class);
        String invoiceAmount = String.valueOf(updateShipmentResp.getShipmentInvoiceDetails().getTotalInvoicedAmount());

        switch (arg0) {
            case CASHBACK:
                if (StringUtils.isNotEmpty((String) ContextData.getDataFromKey("totalCashbackAmount"))) {
                    payoutAmount = Double.parseDouble((String) ContextData.getDataFromKey("totalCashbackAmount"));
                } else {
                    payoutAmount = orderCreation.fetchCashbackAmount();
                }
                break;
            case SELLER:
                payoutAmount = Double.parseDouble(invoiceAmount);
                break;
            case REFUND:
                int orderAmount = (Integer) ContextData.getDataFromKey("orderAmount");
                if ("Cancelled".equalsIgnoreCase((String) ContextData.getDataFromKey("orderStatus"))) {
                    payoutAmount = Double.parseDouble(CurrencyUtils.getNewInstance().convertPaisaToRupees(orderAmount));
                } else {
                    payoutAmount = Double.parseDouble(CurrencyUtils.getNewInstance().convertPaisaToRupees(orderAmount)) - Double.parseDouble(invoiceAmount);
                }
                break;
        }
        log.info(arg0 + " Payout amount --> " + payoutAmount);
        ContextData.addData("payoutAmount", payoutAmount);
//        ExtentCucumberAdapter.addTestStepLog(arg0+" Payout amount --> "+payoutAmount);
        log.exiting();
    }

    @Then("Navigate to pending payout tab and approve the {string} payout")
    public void navigateToPendingPayoutTabAndApproveThePayout(String arg0) {
        log.entering();

        if ("Seller".equalsIgnoreCase(arg0)){
            ContextData.addData("documentNumber",ContextData.getDataFromKey("invoiceNumber"));
        }else if ("Refund".equalsIgnoreCase(arg0)){
            ContextData.addData("documentNumber",ContextData.getDataFromKey("orderNumber"));
        }

        navigateToPendingPayoutsTab();
        searchStringContains(ORDER_NUMBER, (String) ContextData.getDataFromKey("orderNumber"));
        searchStringContains("Payout type", arg0);
        double payoutAmount = (double) ContextData.getDataFromKey("payoutAmount");
        validatePayoutDetails(arg0, CREATED, payoutAmount);
        popUpClose();
        clickOnPayoutCheckbox();
        clickOnAction();
        clickOnApprove();

        log.exiting();
    }

    @Then("Navigate to approved payout tab and do bank export in Deutsche bank format for {string} payout")
    public void navigateToApprovedPayoutTabAndDoBankExportInDeutscheBankFormat(String arg0) {
        log.entering();
        navigateToApprovedPayoutsTab();
        searchStringContains(PAYOUT_NUMBER, (String) ContextData.getDataFromKey("payoutNumber"));
        double payoutAmount = (double) ContextData.getDataFromKey("payoutAmount");
        validatePayoutDetails(arg0, APPROVED, payoutAmount);
        popUpClose();
        clickOnBankExportAndConfirm();
        clickOnDeutscheBankRadioButtonAndSubmit();
        log.exiting();
    }

    @Then("Navigate to export payout tab and export in XLSX format")
    public void navigateToExportPayoutTabAndExportInXLSXFormat() {
        log.entering();
        navigateToExportPayoutsDBBankTab();
        searchStringContains(DOCUMENT_ID1, (String) ContextData.getDataFromKey("documentNumber"));
        selectEntryAndClickOnExportButton();
        clickOnExportToXlsx();
        String fileName = clickOnFinalExport();
        ContextData.addData("xlsxFileName", fileName);
        log.exiting();
    }

    @Then("Open excel file add Status,UTR and Instrument Id and save as Export payouts - Deutsche bank_Updated.xlsx")
    public void openExcelFileAddStatusUTRAndInstrumentIdAndSaveAsExport() throws IOException {
        log.entering();
        updatePayoutExcel((String) ContextData.getDataFromKey("xlsxFileName"));
        log.exiting();
    }

    @Then("Navigate to Import payout status DB bank and upload the saved excel file")
    public void navigateToImportPayoutStatusDBBankAndUploadTheSavedExcelFile() {
        log.entering();
        navigateToImportPayoutsStatusDeutscheBankTab();
        importPayoutUpdatedFile((String) ContextData.getDataFromKey("xlsxFileName"));
        log.exiting();
    }

    @And("Select the entry and click on update status button")
    public void selectTheEntryAndClickOnUpdateStatusButton() {
        log.entering();
        clearSearch();
        searchStringContains(DOCUMENT_ID1, (String) ContextData.getDataFromKey("documentNumber"));
        clickOnUpdateStatus();
        log.exiting();
    }

    @Then("Navigate to Historical Payout tab and validate the entry for {string} payout")
    public void navigateToHistoricalPayoutTabAndValidateTheEntry(String arg0) {
        log.entering();
        navigateToHistoricalPayoutsTab();
        sleep(3000);
        searchStringContains(ORDER_NUMBER, (String) ContextData.getDataFromKey("orderNumber"));
        double payoutAmount = (double) ContextData.getDataFromKey("payoutAmount");
        Map<String, String> payoutData = validatePayoutDetails(arg0, SUCCESS, payoutAmount);
        clickOnTriggerLedgerEntry();
        log.exiting();
    }

    // Pending Payout tab action methods
    public void navigateToPendingPayoutsTab() {
        sleep(2000);
        zohoCreatorNavigation(PENDING_PAYOUTS_PATH);
        log.info("Navigated to Pending Payout Tab");
    }

    public Map<String, String> validatePayoutDetails(String payoutType, String payoutStatus, double amount) {
        selectRowUsingObject(getCallSearchStringContainsFunc, (String) ContextData.getDataFromKey("orderNumber"), 30);
        Map<String, String> payoutData = getDataFromWindow("dataWindowElement");
        ContextData.addData("payoutNumber", payoutData.get(PAYOUT_NUMBER));
        ContextData.addData("documentNumber",payoutData.get(DOCUMENT_NUMBER));
//        assertEquals("Payout type verification failed", payoutType + " payout", payoutData.get(PAYOUT_TYPE));
//        assertEquals("Document type verification failed", payoutType, payoutData.get(DOCUMENT_TYPE));
//        assertTrue("Document number verification failed", payoutData.get(DOCUMENT_NUMBER).contains(Context.documentNumber));
//        assertEquals("Payout status verification failed", payoutStatus, payoutData.get(PAYOUT_STATUS));
//        assertEquals("GSTIN verification failed", Context.gstin, payoutData.get(GSTIN));
//        assertEquals("Payee name", Context.customerName, payoutData.get(PAYEE_NAME));
        log.info("Payout entry details for " + payoutType + " with status as " + payoutStatus + " are --> " + payoutData);
//        ExtentCucumberAdapter.addTestStepLog("Payout details map => " + payoutType + " with status as " + payoutStatus + " are --> " + payoutData);
        String payoutNumber = payoutData.get(PAYOUT_NUMBER);
        ContextData.addData("payoutNumber", payoutNumber);

        assertEquals(payoutData.get(PAYOUT_STATUS), payoutStatus, "Payout status verification failed");
        if (payoutType.equalsIgnoreCase(CASHBACK)) {
            validateCashbackPayoutDetails(payoutData, amount);
        } else if (payoutType.equalsIgnoreCase(REFUND)) {
            validateRefundPayoutDetails(payoutData, amount);
        } else if (payoutType.equalsIgnoreCase(SELLER)) {
            validateSellerPayoutDetails(payoutData, amount);
            log.info("Seller payout validation passed");
        }
        log.info("Payout Validation successful for " + payoutType + " with status as " + payoutStatus);
//        ExtentCucumberAdapter.addTestStepLog("Payout Validation successful for " + payoutType + " with status as " + payoutStatus);
        return payoutData;
    }

    public void validateCashbackPayoutDetails(Map<String, String> payoutData, double cashbackAmount) {

        double totalCashbackAmount = Double.parseDouble((String) ContextData.getDataFromKey("totalCashbackAmount"));
//        ExtentCucumberAdapter.addTestStepLog("Customer Order Cashback Amount: " + Math.round(totalCashbackAmount * 100.0) / 100.0);
        log.info("Customer Order Cashback Amount: " + Math.round(totalCashbackAmount * 100.0) / 100.0);
        double tDSAmount = CurrencyUtils.getNewInstance().getTDSAmount(totalCashbackAmount, 10);
        String cashBackPayoutAmount = formatDecimalWithComma(String.valueOf(totalCashbackAmount - tDSAmount));
        ContextData.addData("cashBackPayoutAmount", cashBackPayoutAmount);
        assertEquals(payoutData.get(DOCUMENT_AMOUNT), formatDecimal(totalCashbackAmount), "Document amount verification failed");
        assertEquals(payoutData.get(BASE_AMOUNT), formatDecimal(totalCashbackAmount), "Base amount verification failed");
        migrateToTestNgAssertEquals("TDS amount verification failed", formatDecimal(tDSAmount), payoutData.get(TDS));
        migrateToTestNgAssertEquals("GST amount verification failed", ZERO, payoutData.get(GST_AMOUNT));
        migrateToTestNgAssertEquals("Payout amount verification failed", cashBackPayoutAmount, payoutData.get(PAYOUT_AMOUNT));
//        ExtentCucumberAdapter.addTestStepLog("TDS Amount: " + tDSAmount);
        log.info("TDS Amount: " + tDSAmount);
    }

    public void validateRefundPayoutDetails(Map<String, String> payoutData, double refundAmount) {

        migrateToTestNgAssertEquals("Payout amount verification failed", formatDecimal(String.valueOf(refundAmount)), payoutData.get(PAYOUT_AMOUNT));
//        ExtentCucumberAdapter.addTestStepLog("Customer Order Refund Amount: " + refundAmount);
        log.info("Customer Order Refund Amount: " + refundAmount);
    }

    public void validateSellerPayoutDetails(Map<String, String> payoutData, double amount) {
        double invoiceAmount = (double) ContextData.getDataFromKey("invoiceAmount");
        double baseAmount = CurrencyUtils.getNewInstance().calculateBaseAmount(invoiceAmount, 0.18);
        double tDSAmount = CurrencyUtils.getNewInstance().getTDSAmount(baseAmount, 0.1);
        double tCSAmount = CurrencyUtils.getNewInstance().getTDSAmount(baseAmount, 0.5);
        migrateToTestNgAssertEquals("payout type validation", SELLER, payoutData.get(PAYOUT_TYPE));
        migrateToTestNgAssertEquals("Document amount verification failed", toDouble(String.valueOf(invoiceAmount), 0), toDouble(payoutData.get(DOCUMENT_AMOUNT), 0), 0.01);
        migrateToTestNgAssertEquals("Base amount verification failed", baseAmount, toDouble(payoutData.get(BASE_AMOUNT), 0), 0.01);
        migrateToTestNgAssertEquals("TDS amount verification failed", tDSAmount, toDouble(payoutData.get(TDS), 0), 0.01);
        migrateToTestNgAssertEquals("TCS amount verification failed", tCSAmount, toDouble(payoutData.get("TCS (amount has been deducted for the document)"), 0), 0.01);
        migrateToTestNgAssertEquals("GST amount verification failed", baseAmount * 0.18, toDouble(payoutData.get(GST_AMOUNT), 0), 0.01);
        migrateToTestNgAssertEquals("Payout amount verification failed", toDouble(String.valueOf(invoiceAmount), 0), toDouble(payoutData.get(PAYOUT_AMOUNT), 0), 0.01);
        assertTrue("Document number verification failed", payoutData.get(DOCUMENT_NUMBER).contains((String) ContextData.getDataFromKey("invoiceNumber")));
    }

    public void clickOnPayoutCheckbox() {
        sleep(2000);
        tap("payoutCheckbox");
        log.info("Selected the Payout entry");
    }

    public void clickOnAction() {
        waitAndClickOn("actionBtn");
        log.info("Clicked on Action button after selecting payout entry");
    }

    public void clickOnApprove() {
        tap("approveBtn");
        pageRefresh();
        log.info("Clicked on payout approve button");
    }

    public void navigateToApprovedPayoutsTab() {
        zohoCreatorNavigation(APPROVED_PAYOUTS_PATH);
        log.info("Navigated to Approve Payout Tab");
    }

    public void clickOnBankExportAndConfirm() {
        clickOnPayoutCheckbox();
        tap("bankExportBtn");
        tap("exportYesBtn");
        log.info("Clicked on Bank export and confirmed");
    }

    public void clickOnDeutscheBankRadioButtonAndSubmit() {
        jsClick("deutscheBankRadioButton");
        waitAndClickOn("submitBtn");
        log.info("Clicked on Deutche Bank and submitted");
    }

    //Export payouts DB action methods
    public void navigateToExportPayoutsDBBankTab() {
        zohoCreatorNavigation(EXPORT_PAYOUTS_DEUTSCHE_BANK_PATH);
        clearSearch();
        sleep(2000);
        log.info("Navigated to export payout DB bank");
    }

    public void selectEntryAndClickOnExportButton() {
        clickOnPayoutCheckbox();
        waitAndClickOn("exportBtn");
        log.info("Selected the entry and clicked on export button");
    }

    public void clickOnExportToXlsx() {
        sleep(2000);
        waitAndClickOn("exportXlsxBtn");
        log.info("Clicked on export to xlsx");
    }

    public String clickOnFinalExport() {
        sleep(2000);
        locateElement("finalExportBtn").clear();
        String fileName = "Export payouts-DB-" + getCurrentTimeStamp();
        log.info("Export payout Filename -->" + fileName);
//        ExtentCucumberAdapter.addTestStepLog("Export payout Filename -->"+ fileName);
        waitAndSetText("finalExportBtn", fileName + Keys.ENTER);
        tap("finalExport");
        log.info("Clicked on final export button");
        sleep(5000);
        List<String> downloadedFiles = getAllDownloadedFiles();
        if (!downloadedFiles.isEmpty()) {
            for (String filePath : downloadedFiles) {
                if (filePath.contains(fileName)) {
                    fileName = filePath;
                    break;
                }
            }
        }
        return fileName;
    }

    public void updatePayoutExcel(String inputFilePath) throws IOException {
        List<String> headers = new ArrayList<>();
        headers.add("Status");//Status
        headers.add("UTR No"); //UTR No

        String utrNo = generateRandomString(8);

        Map<String, String> row = new HashMap<>();
        row.put("Status", "Success");
        row.put("UTR No", utrNo);
        List<Map<String, String>> rows = new ArrayList<>();
        rows.add(row);

        System.out.println("updatePayoutExcel inputFilePath:" + inputFilePath);
        ExcelUtils excelUtils = ExcelUtils.getNewInstance();
        excelUtils.addColumnAndValues(inputFilePath, headers, rows);
        excelUtils.copyColumnValueToAnotherCol(inputFilePath, "System Ref No.", "Instrument Refrence No");
        log.info("UTR No --> " + utrNo);
        log.info("Updated status, Ref no and UTR no details in payout excel file : " + inputFilePath);
//        ExtentCucumberAdapter.addTestStepLog("UTR No --> "+utrNo);

    }

    public void navigateToImportPayoutsStatusDeutscheBankTab() {
        sleep(2000);
        zohoCreatorNavigation(IMPORT_PAYOUTS_DEUTSCHE_BANK_PATH);
        log.info("Navigated to import payout DB bank");
    }

    public void importPayoutUpdatedFile(String fileName) {
        sleep(2000);
        waitAndClickOn("moreActionBtn");
        waitAndClickOn("importBtn");

        sleep(2000);
        Grid.driver().switchTo().frame(locateElement("sheet2Frame"));
        waitAndSetText("localStorageBtn", fileName);
        waitAndClickOn("sideCloseBtn");
        waitAndClickOn("importReportBtn");
        waitAndClickOn("importWarningBtn");
        Grid.driver().switchTo().defaultContent();
        sleep(2000);
        pageRefresh();
        sleep(2000);
        log.info("Update the file and imported it");
    }

    public void clickOnUpdateStatus() {
        clickOnPayoutCheckbox();
        waitAndClickOn("updateStatusBtn");
        log.info("Clicked on update status after importing payout entry");
    }

    public void navigateToHistoricalPayoutsTab() {
        sleep(2000);
        zohoCreatorNavigation(HISTORICAL_PAYOUTS_PATH);
        log.info("Navigated to Historical Payout Tab");
    }

    public void clickOnTriggerLedgerEntry() {
        popUpClose();
        clickOnPayoutCheckbox();
        waitAndClickOn("triggerLedgerEntry");
        sleep(1000);
        waitAndClickOn("confirmBtn");
        sleep(2000);
        log.info("Clicked on trigger ledger entry and confirm button in historical payout entry");
        pageRefresh();
    }
}
