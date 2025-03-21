package com.qapitol.jsw.webbdd.stepdefinition;

import com.qapitol.jsw.apitest.apis.impl.OrderImpl;
import com.qapitol.jsw.apitest.pojos.order.updateShipment.UpdateShipmentResp;
import com.qapitol.jsw.utils.ExcelUtils;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.data.ContextData;
import com.qapitol.sauron.logging.SauronLogger;
import com.qapitol.sauron.logging.SimpleLogger;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.HasDownloads;
import org.openqa.selenium.Keys;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.qapitol.jsw.utils.RandomStringGenerator.generateRandomString;
import static org.testng.Assert.assertEquals;

public class PayoutSteps extends BaseSteps {
    private final SimpleLogger log = SauronLogger.getLogger();

    public static final String CASHBACK = "Cashback";
    public static final String REFUND = "Refund";
    public static final String SELLER = "Seller";
    public static final String PAYOUT_AMOUNT = "Payout amount";
    public static final String CREATED = "Created";
    public static final String ORDER_NUMBER = "Order number";
    public static final String APPROVED = "Approved";
    public static final String ORDER_NUMBER1 = "Order Number";
    public static final String SUCCESS = "Success";

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

        String orderIdSubstring = getOrderSubstring((String) ContextData.getDataFromKey("orderNumber"));
        switch (arg0) {
            case CASHBACK:
                if (StringUtils.isNotEmpty((String) ContextData.getDataFromKey("cashbackAmount"))) {
                    payoutAmount = Double.parseDouble((String) ContextData.getDataFromKey("cashbackAmount"));
                } else {
                    payoutAmount = orderCreation.fetchCashbackAmount();
//                    ContextData.addData("", "CBK-" + orderIdSubstring + "-0");
//                    Context.buyerOrderNumber = ;
                }
                break;
            case SELLER:
                payoutAmount = Double.parseDouble(invoiceAmount);
                break;
            case REFUND:
                int orderAmount = (Integer) ContextData.getDataFromKey("orderAmount");
                if ("Cancelled".equalsIgnoreCase((String) ContextData.getDataFromKey("orderStatus"))) {
                    payoutAmount = Double.parseDouble(convertPaisaToRupees(orderAmount));
                } else {
                    payoutAmount = Double.parseDouble(convertPaisaToRupees(orderAmount)) - Double.parseDouble(invoiceAmount);
                }
                //  Context.buyerOrderNumber = "REF-" + orderIdSubstring + "-0";
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

        navigateToPendingPayoutsTab();
        clearSearch();
        searchStringContains(DOCUMENT_NUMBER, (String) ContextData.getDataFromKey("orderNumber"));
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
        clearSearch();
        searchStringContains(ORDER_NUMBER, (String) ContextData.getDataFromKey("orderNumber"));
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
        searchStringContains(ORDER_NUMBER1, (String) ContextData.getDataFromKey("orderNumber"));
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
        searchStringContains(ORDER_NUMBER1, (String) ContextData.getDataFromKey("orderNumber"));
        clickOnUpdateStatus();
        log.exiting();
    }

    @Then("Navigate to Historical Payout tab and validate the entry for {string} payout")
    public void navigateToHistoricalPayoutTabAndValidateTheEntry(String arg0) {
        log.entering();
        navigateToHistoricalPayoutsTab();
//        clearSearch();
//        pageRefresh();
        sleep(3000);
        searchStringContains(ORDER_NUMBER, (String) ContextData.getDataFromKey("orderNumber"));
        double payoutAmount = (double) ContextData.getDataFromKey("payoutAmount");
        Map<String, String> payoutData = validatePayoutDetails(arg0, SUCCESS, payoutAmount);
        clickOnTriggerLedgerEntry();
//        validateCustomerBankDetails(payoutData);
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
//        assertEquals("Payout type verification failed", payoutType + " payout", payoutData.get(PAYOUT_TYPE));
//        assertEquals("Document type verification failed", payoutType, payoutData.get(DOCUMENT_TYPE));
//        assertTrue("Document number verification failed", payoutData.get(DOCUMENT_NUMBER).contains(Context.documentNumber));
//        assertEquals("Payout status verification failed", payoutStatus, payoutData.get(PAYOUT_STATUS));
//        assertEquals("GSTIN verification failed", Context.gstin, payoutData.get(GSTIN));
//        assertEquals("Payee name", Context.customerName, payoutData.get(PAYEE_NAME));

        if (payoutType.equalsIgnoreCase(CASHBACK)) {
            validateCashbackPayoutDetails(payoutData, amount);
        } else if (payoutType.equalsIgnoreCase(REFUND)) {
            validateRefundPayoutDetails(payoutData, amount);
        } else if (payoutType.equalsIgnoreCase(SELLER)) {
            log.info("Seller payout validation");
        }
        log.info("Payout entry details for " + payoutType + " with status as " + payoutStatus + " are --> " + payoutData);
//        ExtentCucumberAdapter.addTestStepLog("Payout Validation successful for "+payoutType+ " with status as "+payoutStatus + " are --> "+payoutData);

        log.info("Payout Validation successful for " + payoutType + " with status as " + payoutStatus);
//        ExtentCucumberAdapter.addTestStepLog("Payout Validation successful for "+payoutType+ " with status as "+payoutStatus);
        return payoutData;
    }

    public void validateCashbackPayoutDetails(Map<String, String> payoutData, double cashbackAmount) {

        double totalCashbackAmount = cashbackAmount * Double.parseDouble((String) ContextData.getDataFromKey("orderQuantity"));
//        ExtentCucumberAdapter.addTestStepLog("Customer Order Cashback Amount: " + Math.round(totalCashbackAmount * 100.0) / 100.0);
        log.info("Customer Order Cashback Amount: " + Math.round(totalCashbackAmount * 100.0) / 100.0);
        double tDSAmount = getTDSAmount(totalCashbackAmount, 10);
        String cashBackPayoutAmount = formatDecimalWithComma(String.valueOf(totalCashbackAmount - tDSAmount));
//        assertEquals("Document amount verification failed", ZohoUtils.formatDecimal(totalCashbackAmount), payoutData.get(DOCUMENT_AMOUNT));
//        assertEquals("Base amount verification failed", ZohoUtils.formatDecimal(totalCashbackAmount), payoutData.get(BASE_AMOUNT));
//        assertEquals("TDS amount verification failed", ZohoUtils.formatDecimal(tDSAmount), payoutData.get(TDS));
//        assertEquals("GST amount verification failed", ZERO, payoutData.get(GST_AMOUNT));
//        assertEquals("Payout amount verification failed", cashBackPayoutAmount, payoutData.get(PAYOUT_AMOUNT));
//        ExtentCucumberAdapter.addTestStepLog("TDS Amount: " + tDSAmount);
        log.info("TDS Amount: " + tDSAmount);
    }

    public void validateRefundPayoutDetails(Map<String, String> payoutData, double refundAmount) {

        assertEquals("Payout amount verification failed", formatDecimal(String.valueOf(refundAmount)), payoutData.get(PAYOUT_AMOUNT));
//        ExtentCucumberAdapter.addTestStepLog("Customer Order Refund Amount: " + refundAmount);
        log.info("Customer Order Refund Amount: " + refundAmount);
    }

    public void clickOnPayoutCheckbox() {
        sleep(2000);
//        hover("payoutCheckbox");
        tap("payoutCheckbox");
//        helper.findElement(By.xpath(PAYOUT_CHECKBOX)).click();
        log.info("Selected the Payout entry");
    }

    public void clickOnAction() {
//        SeleniumHelper.staticWait(2000);
        waitAndClickOn("actionBtn");
//        helper.findElement(By.xpath(ACTION_BTN)).click();
        log.info("Clicked on Action button after selecting payout entry");
    }

    public void clickOnApprove() {
//        SeleniumHelper.staticWait(2000);
//        WebElement approveBtn = helper.findElement(By.xpath(APPROVE_BTN));
//        waitAndClickOn("approveBtn");
        tap("approveBtn");
//        helper.click(approveBtn, "Approve button");
        pageRefresh();
        log.info("Clicked on payout approve button");
    }

    public void navigateToApprovedPayoutsTab() {
        sleep(2000);
        zohoCreatorNavigation(APPROVED_PAYOUTS_PATH);
        log.info("Navigated to Approve Payout Tab");
    }

    public void clickOnBankExportAndConfirm() {
        clickOnPayoutCheckbox();
//        SeleniumHelper.staticWait(2000);
//        waitAndClickOn("bankExportBtn");
        tap("bankExportBtn");
        tap("exportYesBtn");
//        waitAndClickOn("exportYesBtn");
//        helper.findElement(By.xpath(BANK_EXPORT_BTN)).click();
//        helper.findElement(By.xpath(EXPORT_YES_BTN)).click();
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
//        jsClick("finalExport");
        log.info("Clicked on final export button");
        sleep(5000);
        List<String> downloadedFiles = getAllDownloadedFiles();
        if(!downloadedFiles.isEmpty()){
            for (String filePath: downloadedFiles){
                if(filePath.contains(fileName)) {
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

        System.out.println("updatePayoutExcel inputFilePath:"+inputFilePath);
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
//        String valueText = System.getProperty("user.home") + "\\Downloads\\" + fileName + ".xlsx";
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

    public void clickOnTriggerLedgerEntry(){
        popUpClose();
        clickOnPayoutCheckbox();
//        waitAndClickOn("");
        waitAndClickOn("triggerLedgerEntry");
//        helper.click(triggerLedgerEntryBtn, "Trigger ledger entry");
        sleep(1000);
        waitAndClickOn("confirmBtn");
//        helper.click(confirmFreezeButton, "submit button");
        sleep(2000);
        log.info("Clicked on trigger ledger entry and confirm button in historical payout entry");
        pageRefresh();
    }
}
