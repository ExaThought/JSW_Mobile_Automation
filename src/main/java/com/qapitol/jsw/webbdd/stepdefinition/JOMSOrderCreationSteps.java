package com.qapitol.jsw.webbdd.stepdefinition;

import com.qapitol.jsw.apitest.apis.impl.OrderImpl;
import com.qapitol.jsw.apitest.pojos.order.createDO.CreateDoResp;
import com.qapitol.jsw.apitest.pojos.order.updateShipment.UpdateShipmentResp;
import com.qapitol.jsw.utils.OMSDBUtils;
import com.qapitol.sauron.data.ContextData;
import com.qapitol.sauron.logging.SauronLogger;
import com.qapitol.sauron.logging.SimpleLogger;
import io.cucumber.java.en.Then;

import java.io.IOException;
import java.util.Map;

public class JOMSOrderCreationSteps extends BaseSteps {
    private final SimpleLogger log = SauronLogger.getLogger();

    @Then("Confirm the order using JOMS API")
    public void confirmOrderUsingJOMS() throws IOException, InterruptedException{
        log.entering();
        OrderImpl orderCreation = new OrderImpl();
        orderCreation.getSellerOrderNumber();
        orderCreation.getShipmentForOrderNumber();
        orderCreation.confirmOrderUsingJOMS();
        orderCreation.validateTheOrderStatus("Confirmed");
        Map<String, Object> map = new OMSDBUtils().getCategory((String) ContextData.getDataFromKey("orderNumber"));
        ContextData.addData("orderCategory", map);
        log.exiting();
    }

    @Then("Create and Release DO")
    public void createAndReleaseDo() throws IOException, InterruptedException {
        log.entering();
        OrderImpl orderCreation = new OrderImpl();
        CreateDoResp createDoResp = orderCreation.createDO();
        sleep(5000);
        orderCreation.releaseDO(createDoResp);
//        jomsOrderCreationUtil.createAndReleaseDO();
        log.exiting();
    }

    @Then("Create shipment")
    public void createShipment() throws IOException, InterruptedException{
        log.entering();
        OrderImpl orderCreation = new OrderImpl();
        orderCreation.createShipment();
//        ExtentCucumberAdapter.addTestStepLog("For Order " + Context.buyerOrderNumber + " Shipment is Created");
        log.exiting();
    }

    @Then("Update shipment & make shipment invoice")
    public void updateShipmentAndInvoiced() throws IOException, InterruptedException{
        log.entering();

        OrderImpl orderCreation = new OrderImpl();
        UpdateShipmentResp updateShipmentResp = orderCreation.updateShipment();
        sleep(20000);
        setPojoToContextData("updateShipmentResp",updateShipmentResp);
//        Context.invoiceNumber = response.getBody().jsonPath().get("shipmentInvoiceDetails.invoiceDetails[0].invoiceNumber");
//        Context.invoiceDate = response.getBody().jsonPath().get("shipmentInvoiceDetails.invoiceDetails[0].invoiceDate");
//        Context.invoiceDueDate = response.getBody().jsonPath().get("shipmentInvoiceDetails.invoiceDetails[0].invoiceDueDate");
//        Context.invoiceAmount = response.getBody().jsonPath().get("shipmentInvoiceDetails.totalInvoicedAmount").toString();
//        ExtentCucumberAdapter.addTestStepLog("Invoice Number : " + Context.invoiceNumber + " And Invoice Date: " + Context.invoiceDate + " And Invoice Amount: " + Context.invoiceAmount);
//        System.out.println("shipment invoiced response printx = " + response.getBody().asString() + "printz");
//        totalInvoicedAmount = response.getBody().jsonPath().getDouble("shipmentInvoiceDetails.totalInvoicedAmount");
//        invoicedBaseAmount = response.getBody().jsonPath().getDouble("shipmentInvoiceDetails.invoicedBaseAmount");
//        invoicedTaxAmount = response.getBody().jsonPath().getDouble("shipmentInvoiceDetails.invoicedTaxAmount");
//        invoicedTCSAmount = response.getBody().jsonPath().getDouble("shipmentInvoiceDetails.invoicedTCSAmount");
//        return response.getBody().jsonPath().get("number");
//        ExtentCucumberAdapter.add
//        TestStepLog("Shipment with id " + shipmentNumber + " is Invoiced");
//        ExtentCucumberAdapter.addTestStepLog("Shipment with id " + shipmentNumber + " is Invoiced");
        log.exiting();
    }

    @Then("Post invoice to Zoho")
    public void postInvoiceToZoho() throws IOException, InterruptedException{
        log.entering();
        UpdateShipmentResp updateShipmentResp = getPojoFromContextData("updateShipmentResp", UpdateShipmentResp.class);
        String shipmentNumber = updateShipmentResp.getNumber();
        OrderImpl orderCreation = new OrderImpl();
        orderCreation.postInvoiceToZoho(shipmentNumber);
//        log.info("Customer Order Number: {}", Context.buyerOrderNumber);
//        log.info("Shipment Number: {}", shipmentNumber);
//        ExtentCucumberAdapter.addTestStepLog("Order with id " + Context.buyerOrderNumber + ", Invoice number--> " +Context.invoiceNumber+ " is posted to Zoho for Shipment Number: " + shipmentNumber );
        log.exiting();
    }
}
