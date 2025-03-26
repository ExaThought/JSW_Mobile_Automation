package com.qapitol.jsw.apitest.apis.impl;

import com.qapitol.jsw.apitest.apis.OrderApi;
import com.qapitol.jsw.apitest.pojos.order.confirmOrder.ConfirmOrder;
import com.qapitol.jsw.apitest.pojos.order.confirmOrder.ConfirmOrderResp;
import com.qapitol.jsw.apitest.pojos.order.createDO.CreateDo;
import com.qapitol.jsw.apitest.pojos.order.createDO.CreateDoResp;
import com.qapitol.jsw.apitest.pojos.order.createOrder.CreateOrder;
import com.qapitol.jsw.apitest.pojos.order.createOrder.CreateOrderResp;
import com.qapitol.jsw.apitest.pojos.order.createShipment.CreateShipment;
import com.qapitol.jsw.apitest.pojos.order.createShipment.CreateShipmentResp;
import com.qapitol.jsw.apitest.pojos.order.getLineItemDetails.LineItemDetails;
import com.qapitol.jsw.apitest.pojos.order.getOMSShipmentOrder.GetOMSShipmentOrderResp;
import com.qapitol.jsw.apitest.pojos.order.getOMSShipmentOrder.InvoiceDelivery;
import com.qapitol.jsw.apitest.pojos.order.getOrder.GetOMSOrderResp;
import com.qapitol.jsw.apitest.pojos.order.shipmentInvoice.ShipmentInvoice;
import com.qapitol.jsw.apitest.pojos.order.shipmentInvoice.ShipmentInvoiceResp;
import com.qapitol.jsw.apitest.pojos.order.updateShipment.UpdateShipment;
import com.qapitol.jsw.apitest.pojos.order.updateShipment.UpdateShipmentResp;
import com.qapitol.jsw.utils.CurrencyUtils;
import com.qapitol.jsw.utils.RandomStringGenerator;
import com.qapitol.sauron.data.ContextData;
import com.qapitol.sauron.data.Data;
import org.apache.commons.lang3.StringUtils;
import org.testng.asserts.SoftAssert;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;
import static org.testng.Assert.*;

public class OrderImpl extends BaseApiImpl {

    public static final String READY_FOR_DISPATCH = "Ready for dispatch";
    public static final String READY_TO_SHIP = "Ready to ship";
    public static final String DISPATCHED = "Dispatched";
    public static final String DELIVERED = "Delivered";
    public static final String INVOICED = "Invoiced";
    public static final String PLANNED = "Planned";

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    public static final String timeStamp = ZonedDateTime.now().format(formatter);

    public CreateOrderResp createOrder() throws IOException {

        // CreatePayload POJO
        CreateOrder createOrderRequestBody = getPayloadJsonFileAsPOJO((String) Data.get().get("CREATE_ORDER_PAYLOAD"), CreateOrder.class);
        ContextData.addData("advanceAmountFromPayload", createOrderRequestBody.getAdvancePayment());
        createOrderRequestBody.setSalesforceOpportunityId(RandomStringGenerator.getSalesforceOpportunityId());
        // Fetch the Order API
        OrderApi orderApi = getZohoCCPClientFactory().create(OrderApi.class);

        //Calling the create order API
        CreateOrderResp createOrderResp = orderApi.createOrderViaSLOAPI(createOrderRequestBody);
        ContextData.addData("orderNumber", createOrderResp.getOrderNumber());

        log.info("Created Order id is : " + ContextData.getDataFromKey("orderNumber"));
        return createOrderResp;
    }

    public GetOMSOrderResp validateOrderDetails(String orderType, String paymentType) {
        // Fetch the Order API
        OrderApi orderApi = getZohoOMSApiClientFactory().create(OrderApi.class);

        //Calling the get order API
        GetOMSOrderResp getOrderResp = orderApi.getJOMSOrder((String) ContextData.getDataFromKey("orderNumber"));
        assertEquals(ContextData.getDataFromKey("orderNumber"), getOrderResp.getOrderNumber(), "Order id mismatching");

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals("Order state not in placed or created state", getOrderResp.getOrderStatus(), "Placed");
        assertTrue(getOrderResp.getIsZohoCustomer(), "Not a Zoho Customer");
        assertTrue(getOrderResp.getPaymentType().contains(paymentType), "Payment type is not correct");
        assertNotEquals(getOrderResp.getPaymentStatus().trim(), "PENDING", "Order payment status is Pending ");
        assertEquals(getOrderResp.getCategory(), orderType.trim(), "Order type is not correct");
        ContextData.addData("orderAmount", getOrderResp.getOrderAmount().getCentAmount());
        ContextData.addData("advanceAmountInPaisa", getOrderResp.getAmounts().get("ADP").getAmount().getCentAmount());

        log.info("Buyer name --> " + getOrderResp.getBuyerDetails().getName() + " And Seller name --> " + getOrderResp.getSellerDetails().getName());
//        ExtentCucumberAdapter.addTestStepLog("Buyer name --> " + jsonPath.get("buyerDetails.name") + " And Seller name --> " + jsonPath.get("childOrders[0].sellerName"));
        log.info("Order status --> " + getOrderResp.getOrderStatus() + " And Payment status --> " + getOrderResp.getPaymentStatus() + " And Advance percentage --> " + getOrderResp.getAdvancePercentage());
//        ExtentCucumberAdapter.addTestStepLog("Order status --> " + jsonPath.get("orderStatus") + " And Payment status --> " + jsonPath.get("paymentStatus") + " And Advance percentage --> " + jsonPath.get("advancePercentage"));
        log.info("Total Order Amount --> " + CurrencyUtils.getNewInstance().convertPaisaToRupees(getOrderResp.getOrderAmount().getCentAmount()) + "And Advance payment of order amount -->" + CurrencyUtils.getNewInstance().convertPaisaToRupees(getOrderResp.getAmounts().get("ADP").getAmount().getCentAmount()));
//        ExtentCucumberAdapter.addTestStepLog("Total Order Amount --> " + CurrencyUtils.getNewInstance().convertPaisaToRupees(Context.orderAmount) + " And Advance payment of order amount -->" + CurrencyUtils.getNewInstance().convertPaisaToRupees(Context.advanceAmountInPaisa));
//
        return getOrderResp;
    }

    public GetOMSOrderResp getSellerOrderNumber() {
        // Fetch the Order API
        OrderApi orderApi = getZohoOMSApiClientFactory().create(OrderApi.class);

        //Calling the get order API
        GetOMSOrderResp getOrderResp = orderApi.getJOMSOrder((String) ContextData.getDataFromKey("orderNumber"));
        ContextData.addData("sellerOrderNumber", getOrderResp.getChildOrders().get(0).getSellerOrderNumber());
        ContextData.addData("buyerOrderId", getOrderResp.getId());
        ContextData.addData("customerName", getOrderResp.getBuyerDetails().getName());

        return getOrderResp;
    }

    public void getShipmentForOrderNumber() {
        // Fetch the Order API
        OrderApi orderApi = getZohoOMSApiClientFactory().create(OrderApi.class);

        //Calling the get order API
        GetOMSShipmentOrderResp getOMSShipmentOrderResp = orderApi.getJOMSShipmentOrder((String) ContextData.getDataFromKey("orderNumber"));
        ContextData.addData("sellerOrderId", getOMSShipmentOrderResp.getSellerShipmentsInfo().get(0).getSellerOrderId());
        ContextData.addData("lineItemId", getOMSShipmentOrderResp.getSellerShipmentsInfo().get(0).getLineItems().get(0).getLineItemId());

        assertNotEquals(ContextData.getDataFromKey("lineItemId"), 0, "Line item sync failed ");
        assertNotEquals(ContextData.getDataFromKey("sellerOrderId"), 0, "seller order creation failed ");
    }

    public void confirmOrderUsingJOMS() throws IOException {

        String orderNumber = (String) ContextData.getDataFromKey("orderNumber");
        String sellerOrderNumber = (String) ContextData.getDataFromKey("sellerOrderNumber");

        // Access the Default payload from the YAML test data file
//        Map<Object, Object> values = (Map<Object, Object>) Data.get().get(ContextData.getDataFromKey("orderPaymentType"));
//        Map<Object, Object> createOrderPayload = (Map<Object, Object>) values.get(ContextData.getDataFromKey("sloOrder"));

        ConfirmOrder confirmOrder = getPayloadJsonFileAsPOJO((String) Data.get().get("CONFIRM_ORDER_PAYLOAD"), ConfirmOrder.class);
        confirmOrder.setOrderNumber(orderNumber);
        confirmOrder.getChildOrders().get(0).setSellerOrderNumber(sellerOrderNumber);

        // Fetch the Order API
        OrderApi orderApi = getZohoOMSApiClientFactory().create(OrderApi.class);

        //Calling the get order API
        ConfirmOrderResp confirmOrderResp = orderApi.putConfirmOrder(confirmOrder);
        assertNotNull(confirmOrderResp, "unable to confirm the order");
    }

    public void validateTheOrderStatus(String status) throws IOException, InterruptedException {
        String orderNumber = (String) ContextData.getDataFromKey("orderNumber");
        // Fetch the Order API
        OrderApi orderApi = getZohoOMSApiClientFactory().create(OrderApi.class);

        //Calling the get order API
        GetOMSOrderResp getOrderResp = orderApi.getJOMSOrder(orderNumber);
        assertEquals(orderNumber, getOrderResp.getOrderNumber(), "Order id mismatching");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals("Order state not in placed or created state", getOrderResp.getOrderStatus(), status);
        ContextData.addData("orderStatus", getOrderResp.getOrderStatus());
        log.info("Order status --> " + status + " for order number : " + orderNumber);
//        ExtentCucumberAdapter.addTestStepLog("Order status --> " + status + " for order number : " + Context.buyerOrderNumber);
    }

    public CreateDoResp createDO() throws IOException {

        // Access the Default payload from the YAML test data file
        CreateDo createDo = getPayloadJsonFileAsPOJO((String) Data.get().get("CREATE_DO_PAYLOAD"), CreateDo.class);
        createDo.setCustomerOrderId(ContextData.getDataFromKey("buyerOrderId"));
        createDo.getPsLineItemsDetails().get(0).setLineItemId((Integer) ContextData.getDataFromKey("lineItemId"));

        if(nonNull((Data.get().get("requestAmount")))) {
            createDo.setRequestAmount((Double) Data.get().get("requestAmount"));
        }
        if(nonNull((Data.get().get("plannedQuantity")))) {
            createDo.getPsLineItemsDetails().get(0).setPlannedQuantity((String) Data.get().get("plannedQuantity"));
        }
        if(nonNull((Data.get().get("totalLineItemQuantity")))) {
            createDo.getPsLineItemsDetails().get(0).setTotalLineItemQuantity((Double) Data.get().get("totalLineItemQuantity"));
        }
        if(nonNull((Data.get().get("sellerAddress")))) {
            createDo.setSellerAddress((String) Data.get().get("sellerAddress"));
        }
        if(nonNull((Data.get().get("vendorLocationDisplayName")))) {
            createDo.getSellerAddressMetadata().setVendorLocationDisplayName((String) Data.get().get("vendorLocationDisplayName"));
        }
        if(nonNull((Data.get().get("sellerCompanyName")))) {
            createDo.setSellerCompanyName((String) Data.get().get("sellerCompanyName"));
        }

        // Fetch the Order API
        OrderApi orderApi = getZohoOMSApiClientFactory().create(OrderApi.class);

        // Call the create DO API
        CreateDoResp createDoResp = orderApi.createDO(createDo);
        return createDoResp;
    }

    public void releaseDO(CreateDoResp createDoResp) {
        int psId = createDoResp.getPsId();

        // Fetch the Order API
        OrderApi orderApi = getZohoOMSApiClientFactory().create(OrderApi.class);

        Response statusMsg = orderApi.createDORelease(psId);
        assertEquals(statusMsg.getStatus(), 200, "unable to release do");
        log.info("Release do response : " + statusMsg.getEntity().toString());
    }

    public void createShipment() throws IOException {
        // Access the Default payload from the YAML test data file

        CreateShipment createShipment = getPayloadJsonFileAsPOJO((String) Data.get().get("CREATE_SHIPMENT"), CreateShipment.class);
        createShipment.setPlannedDispatchDate(LocalDateTime.now().toString());
        createShipment.setSellerOrderId(ContextData.getDataFromKey("sellerOrderId"));
        createShipment.getLineItemsDetails().get(0).setLineitemId(ContextData.getDataFromKey("lineItemId"));

        if(nonNull((Data.get().get("plannedQuantity")))) {
            createShipment.getLineItemsDetails().get(0).getPlannedDelivery().setQuantity(Double.valueOf((String) Data.get().get("plannedQuantity")));
        }

        // Fetch the Order API
        OrderApi orderApi = getZohoOMSApiClientFactory().create(OrderApi.class);

        // Call the create DO API
        CreateShipmentResp createShipmentResp = orderApi.createShipment(createShipment);

        assertNotNull(createShipmentResp, "unable to create the shipment");
        ContextData.addData("shipmentId", createShipmentResp.getId());
//        ExtentCucumberAdapter.addTestStepLog("Shipment Id --> "+response.getBody().jsonPath().get("number") +" and quantity --> "+response.getBody().jsonPath().get("lineItemsDetails[0].plannedDelivery.quantity"));
    }

    public UpdateShipmentResp updateShipment() throws IOException {
        String prev = PLANNED;
        String uuid = RandomStringGenerator.RandomInteger();
        UpdateShipmentResp response = null;
        for (String status : List.of(READY_FOR_DISPATCH, DISPATCHED, DELIVERED, INVOICED)) {
            Integer temp = getInvoiceDeliveryId();
// Access the Default payload from the YAML test data file

            UpdateShipment updateShipment = getPayloadJsonFileAsPOJO((String) Data.get().get("UPDATE_SHIPMENT"), UpdateShipment.class);
            updateShipment.setCustomerOrderId(ContextData.getDataFromKey("buyerOrderId"));
            updateShipment.setInvoiceDate(timeStamp);
            updateShipment.setActualDispatchDate(timeStamp);
            updateShipment.setActualDeliveryDate(timeStamp);
            updateShipment.getCurrentState().setState(prev);
            updateShipment.setId(ContextData.getDataFromKey("shipmentId"));
            updateShipment.setSellerOrderId(ContextData.getDataFromKey("sellerOrderId"));

            if(nonNull((Data.get().get("taxType")))) {
                updateShipment.getLineItemsDetails().get(0).setTaxType((String) Data.get().get("taxType"));
            }
            if(nonNull((Data.get().get("invoicedBaseAmount")))) {
                updateShipment.getShipmentInvoiceDetails().setInvoicedBaseAmount((double) Data.get().get("invoicedBaseAmount"));
            }
            if(nonNull((Data.get().get("invoicedTaxAmount")))) {
                updateShipment.getShipmentInvoiceDetails().setInvoicedTaxAmount((double) Data.get().get("invoicedTaxAmount"));
            }
            if(nonNull((Data.get().get("totalInvoicedAmount")))) {
                updateShipment.getShipmentInvoiceDetails().setTotalInvoicedAmount((double) Data.get().get("totalInvoicedAmount"));
            }

            if (temp != null) {
                updateShipment.getLineItemsDetails().get(0).getInvoiceDelivery().setId(temp);
                updateShipment.getLineItemsDetails().get(0).getInvoiceDelivery().setInvoicedDeliveryDate(timeStamp);
                updateShipment.getLineItemsDetails().get(0).getInvoiceDelivery().setInvoiceNumber(uuid);
                updateShipment.getLineItemsDetails().get(0).setLineitemId((Integer) ContextData.getDataFromKey("lineItemId"));

                updateShipment.setInvoiceDate(timeStamp);
            } else {
                updateShipment.getLineItemsDetails().get(0).getInvoiceDelivery().setId(null);
                updateShipment.getLineItemsDetails().get(0).getInvoiceDelivery().setInvoicedDeliveryDate(null);
                updateShipment.getLineItemsDetails().get(0).getInvoiceDelivery().setInvoiceNumber(uuid);
                updateShipment.getLineItemsDetails().get(0).setLineitemId((Integer) ContextData.getDataFromKey("lineItemId"));
            }
            updateShipment.getCurrentState().setState(status);
            prev = status;

            response = callShipmentUpdateApi(updateShipment);
            System.out.println("UpdateShipment:" + response);
            assertNotNull(response, "unable to update the shipment");
        }
        return response;
//        sleep(20000);
    }

    public Integer getInvoiceDeliveryId() {
        // Fetch the Order API
        OrderApi orderApi = getZohoOMSApiClientFactory().create(OrderApi.class);

        //Calling the get order API
        GetOMSShipmentOrderResp getOMSShipmentOrderResp = orderApi.getJOMSShipmentOrder((String) ContextData.getDataFromKey("orderNumber"));
        InvoiceDelivery invoiceDelivery = getOMSShipmentOrderResp.getSellerShipmentsInfo().get(0).getShipmentDetails().get(0).getLineItemsDetails().get(0).getInvoiceDelivery();
        if (nonNull(invoiceDelivery)) {
            return getOMSShipmentOrderResp.getSellerShipmentsInfo().get(0).getShipmentDetails().get(0).getLineItemsDetails().get(0).getInvoiceDelivery().getId();
        }
        return null;
    }

    public UpdateShipmentResp callShipmentUpdateApi(UpdateShipment updateShipment) {
        System.out.println("updateShipment Request:" + updateShipment.toString());
        // Fetch the Order API
        OrderApi orderApi = getZohoOMSApiClientFactory().create(OrderApi.class);

        //Calling the get order API
        return orderApi.updateShipment(updateShipment);
    }

    public void postInvoiceToZoho(String shipmentNumber) {
        // Fetch the Order API
        OrderApi orderApi = getZohoOMSApiClientFactory().create(OrderApi.class);

        ShipmentInvoice shipmentInvoice = new ShipmentInvoice();
        shipmentInvoice.setShipmentNumber(shipmentNumber);
        ShipmentInvoiceResp shipmentInvoiceResp = orderApi.createShipmentInvoice(shipmentInvoice);
        assertNotNull(shipmentInvoiceResp, "unable to post the invoice");
        setPojoToContextData("shipmentInvoice", shipmentInvoiceResp);
    }

    /**
     * Fetch the cashback amount per unit and order quantity by making a GET request to the OMS API.
     *
     * @return The cashback amount (in integer form) retrieved from the API response.
     * @throws IOException          If an input or output exception occurs while making the request.
     * @throws InterruptedException If the current thread is interrupted while waiting.
     * @throws AssertionError       If the assertion for the response status code fails, indicating that the
     *                              order details could not be successfully retrieved from the OMS.
     */
    public double fetchCashbackAmount() {
        // Fetch the Order API
        OrderApi orderApi = getZohoOMSApiClientFactory().create(OrderApi.class);

        List<LineItemDetails> lineItemDetails = orderApi.getLineItemDetails((String) ContextData.getDataFromKey("orderNumber"));
        double cashbackAmount = 0.0D;
        if (nonNull(lineItemDetails.get(0).getCashbackDetails())) {
            cashbackAmount = lineItemDetails.get(0).getCashbackDetails().getCashbackRate();
        } else {
            ContextData.addData("cashbackAmount", cashbackAmount);
        }
        String orderQuantity = lineItemDetails.get(0).getProductInfo().getPrimaryQuantity().replace(" MT", "");
        cashbackAmount = lineItemDetails.get(0).getCashbackDetails().getCashbackRate();
        ContextData.addData("orderQuantity", orderQuantity);
        ContextData.addData("cashbackAmount", cashbackAmount);

        log.info("Cashback Amount Per Unit: " + cashbackAmount);
        log.info("Order Quantity : " + orderQuantity);
//        ExtentCucumberAdapter.addTestStepLog("Cashback Amount Per Unit: " + cashbackAmount +" "+ "and Order Quantity : " + Context.orderQuantity);
        return cashbackAmount;
    }
}
