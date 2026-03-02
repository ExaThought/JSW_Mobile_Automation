package com.qapitol.jsw.stepdefinition;

import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class ShipmentAndOrderDocumentSteps extends AbstractWebPage {

    private static final String MY_ORDERS_TAB = "myOrdersTab";
    private static final String DELIVERED_ORDER_TAB = "deliveredOrderTab";
    private static final String VIEW_DETAILS_BUTTON = "viewDetailsButton";
    private static final String VIEW_ORDER_DOCUMENTS_BUTTON = "viewOrderDocumentsButton";

    SessionSteps sessionSteps=new SessionSteps();
    @Then("verify invoice for shipment order is displayed")
    public void verify_invoice_for_shipment_order_is_displayed() {

        waitAndCheckIsElementVisible("shipmentInvoiceDocument");

        String invoiceName = waitAndGetText("shipmentInvoiceDocument").trim();

        Assert.assertTrue(invoiceName.contains("invoice"),
                "Shipment invoice is not displayed");
    }

}
