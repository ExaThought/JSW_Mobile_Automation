package com.qapitol.jsw.stepdefinition;

import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.qapitol.jsw.utils.CustomUtil.getText;
import static com.qapitol.jsw.utils.CustomUtil.waits;

public class ShipmentAndOrderDocumentSteps extends AbstractWebPage {

    private static final String MY_ORDERS_TAB = "myOrdersTab";
    private static final String DELIVERED_ORDER_TAB = "deliveredOrderTab";
    private static final String VIEW_DETAILS_BUTTON = "viewDetailsButton";
    private static final String VIEW_ORDER_DOCUMENTS_BUTTON = "viewOrderDocumentsButton";
    private static final By SHIPMENT_INVOICE = By.xpath("(//div[contains(@class,'doc-name') and contains(normalize-space(), '.pdf')])[1]");

    SessionSteps sessionSteps=new SessionSteps();
    @Then("verify invoice for shipment order is displayed")
    public void verify_invoice_for_shipment_order_is_displayed() {
        try {
            log.info("Verifying shipment invoice is displayed.");

            waits(SHIPMENT_INVOICE);

            String invoiceName = getText(SHIPMENT_INVOICE);
            log.info("Fetched invoice name: " + invoiceName);

            Assert.assertTrue(
                    invoiceName != null && invoiceName.toLowerCase().contains("pdf"),
                    "Shipment invoice is not displayed or not in PDF format. Actual value: " + invoiceName
            );

            log.info("Shipment invoice verified successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying shipment invoice: " + e.getMessage());
            Assert.fail("Exception while verifying shipment invoice: " + e.getMessage());
        }
    }

}
