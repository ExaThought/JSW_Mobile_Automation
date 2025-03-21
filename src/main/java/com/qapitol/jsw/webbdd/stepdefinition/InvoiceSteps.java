package com.qapitol.jsw.webbdd.stepdefinition;

import com.qapitol.jsw.apitest.pojos.order.updateShipment.UpdateShipmentResp;
import com.qapitol.sauron.data.ContextData;
import com.qapitol.sauron.logging.SauronLogger;
import com.qapitol.sauron.logging.SimpleLogger;
import io.cucumber.java.en.Then;

import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class InvoiceSteps extends BaseSteps {
    private final SimpleLogger log = SauronLogger.getLogger();

    protected String INVOICE_TAB = "#Report:All_Invoices";
    public static final String INVOICE_NUMBER = "Invoice Number";

    @Then("Validate invoice amount and invoice entry under invoice on Zoho Creator")
    public void validateInvoiceAmountAndEntryData() {
        log.entering();

        zohoCreatorNavigation(INVOICE_TAB);
        clearSearch();
        UpdateShipmentResp updateShipmentResp = getPojoFromContextData("updateShipmentResp", UpdateShipmentResp.class);
        String invoiceNumber = updateShipmentResp.getShipmentInvoiceDetails().getInvoiceDetails().get(0).getInvoiceNumber();
        searchStringContains(INVOICE_NUMBER, invoiceNumber);
        selectRecordFromTable(invoiceNumber);
        Map<String, String> invoiceDataMap = getDataFromWindow("dataWindowElement");
        String invoiceAmount = invoiceDataMap.get("Invoice amount");
        log.info("Invoice Amount is: " + invoiceAmount);
        popUpClose();
        assertEquals(formatDecimal(Double.toString(updateShipmentResp.getShipmentInvoiceDetails().getTotalInvoicedAmount())),invoiceAmount);

//        ExtentCucumberAdapter.addTestStepLog("Order Invoice Amount validation is done. Invoice Number: " + Context.invoiceNumber + "\n And Invoice Amount: " + invoiceAmount);
//
//        assertTrue(invoiceDataMap.get("Customer Name").contains(Context.customerName));
//        assertEquals("Invoice Number validation failed", Context.invoiceNumber,invoiceDataMap.get("Invoice Number"));
//        assertEquals("Buyer Order Number validation failed", Context.buyerOrderNumber,invoiceDataMap.get("Order Number"));
//        assertEquals("Invoice Date validation failed", ZohoUtils.convertDateFormat(Context.invoiceDate),invoiceDataMap.get("Invoice Date"));
//        asserts.assertEquals("INVOICE CREATION TYPE validation failed", INVOICE_CREATION_TYPE,invoiceDataMap.get("Invoice Creation Type"));
//        asserts.assertEquals("Invoice Status validation failed", STATUS, invoiceDataMap.get("Status"));
//        assertEquals("INVOICE STATUS validation failed", INVOICE_STATUS,invoiceDataMap.get("Invoice Status"));
//        asserts.assertNotNull("ID validation failed", invoiceDataMap.get("ID"));
//        ExtentCucumberAdapter.addTestStepLog("Invoice Details Entry In Zoho Creator:--> " +invoiceDataMap);
//        log.info("Zoho Invoice Validation successful");
        log.exiting();
    }

    public void clickOnInvoiceTab() throws InterruptedException {
        waitAndClickOn("financeDocumentsTab");
        waitAndClickOn(10, "invoiceTab");
        pageRefresh();
        sleep(3000);
    }

//    public Map<String, String> validateInvoiceData(String cellValue) {
//        Map<String, String> invoiceData = new TableUtils(driver).getRowDataAsMap(cellValue);
//        log.info("==========Validating Invoice Data in Zoho Creator=========");
//        return invoiceData;
//    }

//    public void selectRecordFromTable(String invoiceNumber) {
//        ZohoUtils.selectRowUsingObject(driver,invoiceNumber, 20);
//    }
//
//    public String getInvoiceAmountFromOverviewWindow() {
//        return invoiceAmount.getText();
//    }

    public void selectRecordFromTable(String invoiceNumber) {
        selectRowUsingObject(invoiceNumber, 20);
    }

    @Then("Get zoho book invoice ID and validate under invoice on Zoho Creator")
    public void getZohoBookInvoiceIDAndValidateUnderInvoiceOnZohoCreator() {
        zohoCreatorNavigation(INVOICE_TAB);
        clearSearch();
        UpdateShipmentResp updateShipmentResp = getPojoFromContextData("updateShipmentResp", UpdateShipmentResp.class);
        String invoiceNumber = updateShipmentResp.getShipmentInvoiceDetails().getInvoiceDetails().get(0).getInvoiceNumber();
        searchStringContains(INVOICE_NUMBER, invoiceNumber);
        selectRecordFromTable(invoiceNumber);
        Map<String,String> invoiceWindowData = getDataFromWindow("dataWindowElement");
        ContextData.addData("BookInvoiceID", invoiceWindowData.get("Book Invoice ID"));
        log.info("Book Invoice ID:" + ContextData.getDataFromKey("BookInvoiceID"));
        assertNotNull(ContextData.getDataFromKey("BookInvoiceID"), "Book Invoice Id:");
//        ExtentCucumberAdapter.addTestStepLog("Book Invoice ID--> " +Context.BookInvoiceID);
    }
}
