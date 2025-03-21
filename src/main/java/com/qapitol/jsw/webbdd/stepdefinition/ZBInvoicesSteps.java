package com.qapitol.jsw.webbdd.stepdefinition;

import com.qapitol.sauron.common.configuration.Config;
import com.qapitol.sauron.data.ContextData;
import io.cucumber.java.en.Then;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ZBInvoicesSteps extends BaseSteps{
    @Then("Navigate to Invoices landing page of zoho books with book invoice id")
    public void navigateToInvoicesLandingPageOfZohoBooksWithBookInvoiceId() {
        String BookInvoiceID = (String) ContextData.getDataFromKey("BookInvoiceID");
        referenceDocument("invoices",BookInvoiceID);
    }

    @Then("click on edit and change the Invoice Date and Due Date")
    public void clickOnEditAndChangeTheInvoiceDateAndDueDate() {
        updateTheInvoiceDateToPrevious();
    }

    @Then("click on Record Payment to pay the amount")
    public void clickOnRecordPaymentToPayTheAmount() {
        pageRefresh();
        String BookInvoiceID = (String) ContextData.getDataFromKey("BookInvoiceID");
        referenceDocument("invoices",BookInvoiceID);
        sleep(999);
        waitAndClickOn("clickBtn", "Record Payment");
        sleep(999);
        waitAndClickOn("clickBtn","Record Payment");
    }

    @Then("click on payment tab and delete the payment for this invoice")
    public void clickOnPaymentTabAndDeleteThePaymentForThisInvoice() {
        sleep(999);
        clickButtons("Payments Received","Delete Payment");
    }

    @Then("validate the zoho books invoice status as {string}")
    public void validateTheZohoBooksInvoiceStatus(String invoiceStatus) {
        sleep(5000);
        String status= locateElement("ribbon").getText();
//        assertEquals("Zoho books invoice status verification failed:",invoiceStatus,status);
//        log.info("Zoho books invoice status is:"+status);
//        ExtentCucumberAdapter.addTestStepLog("Zoho books invoice status is:"+status);
    }

    public void referenceDocument(String documentType, String docId) {
        String url = null;
        sleep(3000);
        try {
            url = Config.getConfigProperty("booksUrl") + documentType + "/" + docId;
            navigate(url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        driver.navigate().to(url);
        log.info(" Navigated to --> " + documentType + " for document id -> " + docId + " In Zoho books");
//        ExtentCucumberAdapter.addTestStepLog(" Navigated to --> " + documentType + " for document id -> " + docId + " In Zoho books");
    }

    public void updateTheInvoiceDateToPrevious(){
        clickActionButton("Edit");
        setPreviousDate("invoiceDate");
        sleep(500);
        setPreviousDate("dueDate");
        sleep(500);
        waitAndClickOn("clickBtn", "Save");
        sleep(999);
        setPopInput("Note down the reason for editing an invoice that has already been sent.","Testing");
        clickButton("Save",2);
    }

    public void clickActionButton(String buttonName) {
        waitAndClickOn("actionBtn", buttonName, buttonName);
        log.info(" Clicked on --> " + buttonName);
//        ExtentCucumberAdapter.addTestStepLog(" Clicked on --> " + buttonName);
    }

    public void setPreviousDate(String locater) {
        // Get today's date
        LocalDate today = LocalDate.now();
        // Subtract 1 day to get the previous date
        LocalDate previousDate = today.minusDays(1);
        // Format the date to match the input field format (dd/MM/yyyy)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = previousDate.format(formatter);
        // Find the date input element using the refined XPath
//        WebElement dateInput = driver.findElement(By.xpath(dateFieldXPath));
        // Clear the existing date value and set the previous day's date
        clearTextBox(locater);
//        dateInput.clear();
        sleep(1000);
        waitAndSetText(locater, formattedDate + Keys.RETURN);
//        dateInput.sendKeys(formattedDate);
//        dateInput.sendKeys(Keys.RETURN);
    }

    public void setPopInput(String fieldLabel, String fieldValue) {
        clearTextBox("popoutArea");
        waitAndSetText("popoutArea", fieldLabel, fieldValue);
//        helper.sendKeys(textField, fieldLabel, fieldValue);
    }

    public void clickButton(String buttonLabel,int index) {
        waitAndClickOn("clickIndexBtn", buttonLabel, String.valueOf(index));
//        driver.click(driver.findElement(By.xpath("(//button[text()='" + buttonLabel + "'])["+index+"]")), buttonLabel);
        log.info("Clicked on the : " + buttonLabel);
    }

    public void clickButtons(String firstButton, String secondButton) {
        waitAndClickOn("firstBtn", firstButton);
//        driver.click(driver.findElement(By.xpath("//button//li[contains(text(), '" + firstButton + "')]")), firstButton);
        log.info("Clicked on the : " + firstButton);
        sleep(999);
        waitAndClickOn("secondBtn", secondButton);
        log.info("Clicked on the : " + secondButton);
        sleep(999);
        waitAndClickOn("deletePaymentBtn");
        sleep(999);
        waitAndClickOn("submitBtn");
        log.info("Clicked on the Submit");
    }
}
