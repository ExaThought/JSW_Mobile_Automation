package com.qapitol.jsw.webbdd.stepdefinition;

import com.qapitol.jsw.apitest.apis.impl.OrderFlowImpl;
import com.qapitol.jsw.apitest.apis.impl.OrderImpl;
import com.qapitol.jsw.apitest.pojos.order.createDO.CreateDoResp;
import com.qapitol.jsw.apitest.pojos.order.getOrder.GetOMSOrderResp;
import com.qapitol.jsw.apitest.pojos.order.updateShipment.UpdateShipmentResp;
import com.qapitol.jsw.utils.AllureCustomLogReporter;
import com.qapitol.jsw.utils.OMSDBUtils;
import com.qapitol.sauron.data.ContextData;
import com.qapitol.sauron.data.Data;
import com.qapitol.sauron.logging.SauronLogger;
import com.qapitol.sauron.logging.SimpleLogger;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.util.Map;

public class OrderFlowSteps extends BaseSteps {
    private final SimpleLogger log = SauronLogger.getLogger();

    @When("Create order with SLO API")
    public void createOrderWithSloApi() throws IOException {
        log.entering();
        OrderFlowImpl orderFlow = new OrderFlowImpl();
        orderFlow.createOrder();
        new OMSDBUtils().updateSalesForceOpportunity((String) ContextData.getDataFromKey("orderNumber"));
        AllureCustomLogReporter.addTestStepLog("Order created successfully, Customer Order Number: " + (String) ContextData.getDataFromKey("orderNumber"));
        log.exiting();
    }

    @Then("validate the order details")
    public void validateTheOrderDetails() {
        log.entering();
        String orderType = (String) Data.get().get("orderType");
        String paymentType = (String) Data.get().get("paymentType");

        OrderFlowImpl orderCreation = new OrderFlowImpl();
        GetOMSOrderResp getOMSOrderResp = orderCreation.validateOrderDetails(orderType, paymentType);
        ContextData.addData("gstin", getOMSOrderResp.getBuyerDetails().getGstin());
        ContextData.addData("baseAMount", getOMSOrderResp.getAmounts().get("LIA").getAmount().getDisplayAmount().toString().replace(",", ""));
        log.info("Order Type: " + orderType + "And Payment Type: " + paymentType);
        AllureCustomLogReporter.addTestStepLog("Order Type: " + orderType + " And Payment Type: " + paymentType);
        log.info("Credit by pass flag -->" + getOMSOrderResp.getCreditBlockBypassFlag());
        AllureCustomLogReporter.addTestStepLog("Credit by pass flag -->" + getOMSOrderResp.getCreditBlockBypassFlag());
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
        AllureCustomLogReporter.addTestStepLog("Customer Order Cashback Amount Per Unit: " + cashbackAmount);
        AllureCustomLogReporter.addTestStepLog("Customer Order Total Cashback Amount: " + totalCashbackAmount);
        log.exiting();
    }

    @Then("Confirm the order using JOMS API")
    public void confirmOrderUsingJOMS() throws IOException {
        log.entering();
        OrderFlowImpl orderCreation = new OrderFlowImpl();
        orderCreation.getSellerOrderNumber();
        orderCreation.getShipmentForOrderNumber();
        orderCreation.confirmOrderUsingJOMS();
        orderCreation.validateTheOrderStatus("Confirmed");
        Map<String, Object> map = new OMSDBUtils().getCategory((String) ContextData.getDataFromKey("orderNumber"));
        ContextData.addData("orderCategory", map);
        log.exiting();
    }

    @Then("Create and Release DO")
    public void createAndReleaseDo() throws IOException {
        log.entering();
        OrderFlowImpl orderCreation = new OrderFlowImpl();
        CreateDoResp createDoResp = orderCreation.createDO();

        double plannedQuantity = createDoResp.getPsLineItemsDetails().get(0).getPlannedQuantity();
        log.info("DO created with quantity --> " + plannedQuantity);

        sleep(5000);
        orderCreation.releaseDO(createDoResp);

        log.info("DO released with quantity --> " + plannedQuantity);
        AllureCustomLogReporter.addTestStepLog("DO created and released with quantity --> " + plannedQuantity + "MT");
        log.exiting();
    }

    @Then("Create shipment")
    public void createShipment() throws IOException, InterruptedException {
        log.entering();
        OrderFlowImpl orderCreation = new OrderFlowImpl();
        orderCreation.createShipment();
        String buyerOrderNumber = (String) ContextData.getDataFromKey("orderNumber");
        AllureCustomLogReporter.addTestStepLog("For Order " + buyerOrderNumber + " Shipment is Created");
        log.exiting();
    }

    @Then("Update shipment & make shipment invoice")
    public void updateShipmentAndInvoiced() throws IOException {
        log.entering();

        OrderImpl orderCreation = new OrderImpl();
        UpdateShipmentResp updateShipmentResp = orderCreation.updateShipment();
        sleep(20000);
        setPojoToContextData("updateShipmentResp", updateShipmentResp);
        ContextData.addData("invoiceNumber", updateShipmentResp.getShipmentInvoiceDetails().getInvoiceDetails().get(0).getInvoiceNumber());
        ContextData.addData("invoiceDate", updateShipmentResp.getShipmentInvoiceDetails().getInvoiceDetails().get(0).getInvoiceDate());
        ContextData.addData("invoiceDueDate", updateShipmentResp.getShipmentInvoiceDetails().getInvoiceDetails().get(0).getInvoiceDueDate());
        ContextData.addData("invoiceAmount", updateShipmentResp.getShipmentInvoiceDetails().getTotalInvoicedAmount());

        AllureCustomLogReporter.addTestStepLog("Invoice Number : " + ContextData.getDataFromKey("invoiceNumber") + " And Invoice Date: " + ContextData.getDataFromKey("invoiceDate") + " And Invoice Amount: " + ContextData.getDataFromKey("invoiceAmount"));
        ContextData.addData("invoicedBaseAmount", updateShipmentResp.getShipmentInvoiceDetails().getInvoicedBaseAmount());
        ContextData.addData("invoicedTaxAmount", updateShipmentResp.getShipmentInvoiceDetails().getInvoicedTaxAmount());
        ContextData.addData("invoicedTCSAmount", updateShipmentResp.getShipmentInvoiceDetails().getInvoicedTCSAmount());
        String shipmentNumber = updateShipmentResp.getNumber();
        AllureCustomLogReporter.addTestStepLog("Shipment with id " + shipmentNumber + " is Invoiced");
        log.exiting();
    }

    @Then("Post invoice to Zoho")
    public void postInvoiceToZoho() {
        log.entering();
        UpdateShipmentResp updateShipmentResp = getPojoFromContextData("updateShipmentResp", UpdateShipmentResp.class);
        String shipmentNumber = updateShipmentResp.getNumber();
        OrderFlowImpl orderCreation = new OrderFlowImpl();
        orderCreation.postInvoiceToZoho(shipmentNumber);

        String buyerOrderNumber = (String) ContextData.getDataFromKey("orderNumber");
        log.info(String.format("Customer Order Number: {%s}", buyerOrderNumber));
        log.info(String.format("Shipment Number: {%s}", shipmentNumber));
        AllureCustomLogReporter.addTestStepLog("Order with id " + buyerOrderNumber + ", Invoice number--> " + ContextData.getDataFromKey("invoiceNumber") + " is posted to Zoho for Shipment Number: " + shipmentNumber);
        log.exiting();
    }

    @Then("Update shipment and post Invoice to Zoho")
    public void updateShipmentAndPostInvoice() throws IOException, InterruptedException {
        log.entering();

        OrderFlowImpl orderFlow = new OrderFlowImpl();
        UpdateShipmentResp updateShipmentResp = orderFlow.updateShipmentReadyToShip();
        orderFlow.postInvoiceToZoho(updateShipmentResp.getNumber());
        orderFlow.updateShipmentDelivered();

        ContextData.addData("invoiceNumber", updateShipmentResp.getShipmentInvoiceDetails().getInvoiceDetails().get(0).getInvoiceNumber());
        ContextData.addData("invoiceDate", updateShipmentResp.getShipmentInvoiceDetails().getInvoiceDetails().get(0).getInvoiceDate());
        ContextData.addData("invoiceDueDate", updateShipmentResp.getShipmentInvoiceDetails().getInvoiceDetails().get(0).getInvoiceDueDate());
        ContextData.addData("invoiceAmount", updateShipmentResp.getShipmentInvoiceDetails().getTotalInvoicedAmount());

        AllureCustomLogReporter.addTestStepLog("Invoice Number : " + ContextData.getDataFromKey("invoiceNumber") + " And Invoice Date: " + ContextData.getDataFromKey("invoiceDate") + " And Invoice Amount: " + ContextData.getDataFromKey("invoiceAmount"));
        ContextData.addData("invoicedBaseAmount", updateShipmentResp.getShipmentInvoiceDetails().getInvoicedBaseAmount());
        ContextData.addData("invoicedTaxAmount", updateShipmentResp.getShipmentInvoiceDetails().getInvoicedTaxAmount());
        ContextData.addData("invoicedTCSAmount", updateShipmentResp.getShipmentInvoiceDetails().getInvoicedTCSAmount());
        String shipmentNumber = updateShipmentResp.getNumber();
        AllureCustomLogReporter.addTestStepLog("Shipment with id " + shipmentNumber + " is Invoiced");
        log.exiting();
    }
}
