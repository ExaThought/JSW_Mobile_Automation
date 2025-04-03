package com.qapitol.jsw.apitest.apis.impl;

import com.qapitol.jsw.apitest.apis.OrderApi;
import com.qapitol.jsw.apitest.pojos.order.confirmOrder.ConfirmOrder;
import com.qapitol.jsw.apitest.pojos.order.confirmOrder.ConfirmOrderResp;
import com.qapitol.jsw.apitest.pojos.order.createDO.CreateDo;
import com.qapitol.jsw.apitest.pojos.order.createDO.CreateDoResp;
import com.qapitol.jsw.apitest.pojos.order.createOrder.CreateOrder;
import com.qapitol.jsw.apitest.pojos.order.createOrder.CreateOrderResp;
import com.qapitol.jsw.apitest.pojos.order.createOrder.CreditDetails;
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
import com.qapitol.jsw.utils.AllureCustomLogReporter;
import com.qapitol.jsw.utils.CurrencyUtils;
import com.qapitol.jsw.utils.RandomStringGenerator;
import com.qapitol.sauron.data.ContextData;
import com.qapitol.sauron.data.Data;
import org.testng.asserts.SoftAssert;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.util.Objects.nonNull;
import static org.testng.Assert.*;

public class OrderFlowImpl extends BaseApiImpl {

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
        createOrderRequestBody.setSalesforceOpportunityId(RandomStringGenerator.getSalesforceOpportunityId());
        // Fetch the Order API
        if (nonNull((Data.get().get("orderType")))) {
            createOrderRequestBody.setOrderType((String) Data.get().get("orderType"));
        }
        if (nonNull((Data.get().get("paymentType")))) {
            createOrderRequestBody.setOrderPaymentType((String) Data.get().get("paymentType"));
        }
        if (nonNull((Data.get().get("advancePercentage")))) {
            createOrderRequestBody.setAdvancePercentage((Integer) Data.get().get("advancePercentage"));
            int advancePer = (Integer) Data.get().get("advancePercentage");
            double finalSellingPrice = (Double) Data.get().get("finalSellingPrice");
            double orderedQuantity = (Double) Data.get().get("orderedQuantity");
            createOrderRequestBody.setAdvancePayment(((double) advancePer*finalSellingPrice*orderedQuantity*10/100)*1.18);
        }
        if (nonNull((Data.get().get("customerId")))) {
            createOrderRequestBody.setCustomerId((String) Data.get().get("customerId"));
        }
        if (nonNull((Data.get().get("shippingAddressId")))) {
            createOrderRequestBody.setShippingAddressId((String) Data.get().get("shippingAddressId"));
            createOrderRequestBody.setBillingAddressId((String) Data.get().get("shippingAddressId"));
        }
        if (nonNull((Data.get().get("orderedQuantity")))) {
            double orderQuantity = ((Double) Data.get().get("orderedQuantity")*1000);
            createOrderRequestBody.getLineItems().get(0).setOrderedQuantity(orderQuantity);
            createOrderRequestBody.getLineItems().get(0).setFinalSellingPrice((Double) Data.get().get("finalSellingPrice"));
        }
//        if (nonNull((Data.get().get("LINE_ITEM_DETAILS_PAYLOAD")))){
//            ObjectMapper objectMapper = new ObjectMapper();
//            List<LineItem> lineItems = objectMapper.readValue((String) Data.get().get("LINE_ITEM_DETAILS_PAYLOAD"), new TypeReference<List<LineItem>>(){});
//            createOrderRequestBody.setLineItems(lineItems);
//        }
        if (nonNull((Data.get().get("CREDIT_DETAILS")))){
            CreditDetails creditDetails = getPayloadJsonFileAsPOJO((String) Data.get().get("CREDIT_DETAILS"), CreditDetails.class);
            createOrderRequestBody.getCustomFields().setCreditDetails(creditDetails);
        }
        if (nonNull((Data.get().get("creditLimitBypass")))){
            createOrderRequestBody.setCreditLimitBypass((boolean) Data.get().get("creditLimitBypass"));
        }
        if (nonNull(Data.get().get("cashbackRate"))){
            createOrderRequestBody.getLineItems().get(0).getCashbackDetails().setCashbackRate((double) Data.get().get("cashbackRate"));
        }

        ContextData.addData("advanceAmountFromPayload", createOrderRequestBody.getAdvancePayment());
        OrderApi orderApi = getZohoCCPClientFactory().create(OrderApi.class);

        //Calling the create order API
        CreateOrderResp createOrderResp = orderApi.createOrderViaSLOAPI(createOrderRequestBody);
        ContextData.addData("orderNumber", createOrderResp.getOrderNumber());
        ContextData.addData("orderedQuantity", createOrderRequestBody.getLineItems().get(0).getOrderedQuantity());
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
        assertEquals(getOrderResp.getCategory(), orderType.trim().toUpperCase(), "Order type is not correct");
        ContextData.addData("orderAmount", getOrderResp.getOrderAmount().getCentAmount());
        ContextData.addData("advanceAmountInPaisa", getOrderResp.getAmounts().get("ADP").getAmount().getCentAmount());

        log.info("Buyer name --> " + getOrderResp.getBuyerDetails().getName() + " And Seller name --> " + getOrderResp.getSellerDetails().getName());
        AllureCustomLogReporter.addTestStepLog("Buyer name --> " + getOrderResp.getBuyerDetails().getName() + " And Seller name --> " + getOrderResp.getSellerDetails().getName());
        log.info("Order status --> " + getOrderResp.getOrderStatus() + " And Payment status --> " + getOrderResp.getPaymentStatus() + " And Advance percentage --> " + getOrderResp.getAdvancePercentage());
        AllureCustomLogReporter.addTestStepLog("Order status --> " + getOrderResp.getOrderStatus() + " And Payment status --> " + getOrderResp.getPaymentStatus() + " And Advance percentage --> " + getOrderResp.getAdvancePercentage());
        log.info("Total Order Amount --> " + CurrencyUtils.getNewInstance().convertPaisaToRupees(getOrderResp.getOrderAmount().getCentAmount()) + "And Advance payment of order amount -->" + CurrencyUtils.getNewInstance().convertPaisaToRupees(getOrderResp.getAmounts().get("ADP").getAmount().getCentAmount()));
        AllureCustomLogReporter.addTestStepLog("Total Order Amount --> " + CurrencyUtils.getNewInstance().convertPaisaToRupees(getOrderResp.getOrderAmount().getCentAmount()) + " And Advance payment of order amount -->" + CurrencyUtils.getNewInstance().convertPaisaToRupees(getOrderResp.getAmounts().get("ADP").getAmount().getCentAmount()));

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
        ContextData.addData("sellerName",getOrderResp.getChildOrders().get(0).getSellerName());
        return getOrderResp;
    }

    public void getShipmentForOrderNumber() {
        // Fetch the Order API
        OrderApi orderApi = getZohoOMSApiClientFactory().create(OrderApi.class);

        //Calling the get order API
        GetOMSShipmentOrderResp getOMSShipmentOrderResp = orderApi.getJOMSShipmentOrder((String) ContextData.getDataFromKey("orderNumber"));
        ContextData.addData("sellerOrderId", getOMSShipmentOrderResp.getSellerShipmentsInfo().get(0).getSellerOrderId());
        ContextData.addData("lineItemId", getOMSShipmentOrderResp.getSellerShipmentsInfo().get(0).getLineItems().get(0).getLineItemId());
        ContextData.addData("sellerGstin", getOMSShipmentOrderResp.getSellerShipmentsInfo().get(0).getSellerInfo().getGstin());


        assertNotEquals(ContextData.getDataFromKey("lineItemId"), 0, "Line item sync failed ");
        assertNotEquals(ContextData.getDataFromKey("sellerOrderId"), 0, "seller order creation failed ");
    }

    public void confirmOrderUsingJOMS() throws IOException {

        String orderNumber = (String) ContextData.getDataFromKey("orderNumber");
        String sellerOrderNumber = (String) ContextData.getDataFromKey("sellerOrderNumber");

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
        AllureCustomLogReporter.addTestStepLog("Order status --> " + status + " for order number : " + orderNumber);
    }

    public CreateDoResp createDO() throws IOException {

        // Access the Default payload from the YAML test data file
        CreateDo createDo = getPayloadJsonFileAsPOJO((String) Data.get().get("CREATE_DO_PAYLOAD"), CreateDo.class);
        createDo.setCustomerOrderId(ContextData.getDataFromKey("buyerOrderId"));
        createDo.getPsLineItemsDetails().get(0).setLineItemId((Integer) ContextData.getDataFromKey("lineItemId"));
        if (nonNull((Data.get().get("doQuantity")))) {
            createDo.getPsLineItemsDetails().get(0).setPlannedQuantity((Double) Data.get().get("doQuantity"));
            if (nonNull(Data.get().get("finalSellingPrice"))){
                createDo.setRequestAmount((Double) Data.get().get("doQuantity")*(Double) Data.get().get("finalSellingPrice")*10*1.18);
            }else {
                createDo.setRequestAmount((Double) ContextData.getDataFromKey("orderAmount")/100);
            }
            createDo.getPsLineItemsDetails().get(0).setTotalLineItemQuantity((Double) ContextData.getDataFromKey("orderedQuantity"));

        }
        createDo.setSellerAddress((String) ContextData.getDataFromKey("sellerAddress"));
        createDo.getSellerAddressMetadata().setVendorLocationDisplayName((String) ContextData.getDataFromKey("sellerAddress"));
        createDo.setSellerCompanyName((String) ContextData.getDataFromKey("sellerAddress"));

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
        createShipment.setSellerGstin((String) ContextData.getDataFromKey("sellerGstin"));
        if (nonNull(Data.get().get("shipmentQuantity"))) {
            createShipment.getLineItemsDetails().get(0).getPlannedDelivery().setQuantity(Double.valueOf((String) Data.get().get("shipmentQuantity")));
        }

        // Fetch the Order API
        OrderApi orderApi = getZohoOMSApiClientFactory().create(OrderApi.class);

        // Call the create DO API
        CreateShipmentResp createShipmentResp = orderApi.createShipment(createShipment);

        assertNotNull(createShipmentResp, "unable to create the shipment");
        ContextData.addData("shipmentId", createShipmentResp.getId());
        ContextData.addData("shipmentNumber", createShipmentResp.getNumber());
        ContextData.addData("shipmentTotalAmount", createShipmentResp.getShipmentInvoiceDetails().getShipmentValue());
        AllureCustomLogReporter.addTestStepLog("Shipment Id --> " + createShipmentResp.getId() + " and quantity --> " + createShipmentResp.getLineItemsDetails().get(0).getPlannedDelivery().getQuantity());
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
            updateShipment.setSellerGstin((String) ContextData.getDataFromKey("sellerGstin"));
            updateShipment.setInvoiceDate(timeStamp);
            updateShipment.setActualDispatchDate(timeStamp);
            updateShipment.setActualDeliveryDate(timeStamp);
            updateShipment.getCurrentState().setState(prev);
            updateShipment.setId(ContextData.getDataFromKey("shipmentId"));
            updateShipment.setSellerOrderId(ContextData.getDataFromKey("sellerOrderId"));

            if (nonNull(Data.get().get("shipmentQuantity"))) {
                updateShipment.getLineItemsDetails().get(0).getInvoiceDelivery().setQuantity(Double.valueOf((String) Data.get().get("shipmentQuantity")));
                if (nonNull(Data.get().get("finalSellingPrice"))) {
                    double invoiceBaseAmount= Double.valueOf((String) Data.get().get("shipmentQuantity"))*(Double) Data.get().get("finalSellingPrice")*10;
                    updateShipment.getShipmentInvoiceDetails().setInvoicedBaseAmount(invoiceBaseAmount);
                    updateShipment.getShipmentInvoiceDetails().setTotalInvoicedAmount(invoiceBaseAmount*1.18);
                    updateShipment.getShipmentInvoiceDetails().setInvoicedTaxAmount(invoiceBaseAmount*0.18);
                }
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

    public Integer getInvoiceDeliveryIdAndStatus() throws IOException, InterruptedException {
        Integer invoiceDeliveryId = 0;
        String status = "";
        int retryCount = 0;
        // Fetch the Order API
        OrderApi orderApi = getZohoOMSApiClientFactory().create(OrderApi.class);
        while ((!status.equalsIgnoreCase("INV_PROCESSED")) && retryCount < 10) {

            //Calling the get order API
            GetOMSShipmentOrderResp getOMSShipmentOrderResp = orderApi.getJOMSShipmentOrder((String) ContextData.getDataFromKey("orderNumber"));
            InvoiceDelivery invoiceDelivery = getOMSShipmentOrderResp.getSellerShipmentsInfo().get(0).getShipmentDetails().get(0).getLineItemsDetails().get(0).getInvoiceDelivery();
            status = getOMSShipmentOrderResp.getSellerShipmentsInfo().get(0).getShipmentDetails().get(0).getInvoicePostingStatus();
            log.info("\n checking JOMS INVOICE posting status....\n" + status);
            if (nonNull(invoiceDelivery)) {
                return getOMSShipmentOrderResp.getSellerShipmentsInfo().get(0).getShipmentDetails().get(0).getLineItemsDetails().get(0).getInvoiceDelivery().getId();
            }

            retryCount++;
        }
        return invoiceDeliveryId;
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
        AllureCustomLogReporter.addTestStepLog("Cashback Amount Per Unit: " + cashbackAmount + " " + "and Order Quantity : " + orderQuantity);
        return cashbackAmount;
    }

    public UpdateShipmentResp updateShipmentReadyToShip() throws IOException, InterruptedException {

      //  String prev = PLANNED;
        String status = READY_TO_SHIP;
        String uuid = RandomStringGenerator.RandomInteger();
        UpdateShipmentResp response = null;
        Integer temp = getInvoiceDeliveryId();
// Access the Default payload from the YAML test data file

            UpdateShipment updateShipment = getPayloadJsonFileAsPOJO((String) Data.get().get("UPDATE_SHIPMENT"), UpdateShipment.class);
            updateShipment.setCustomerOrderId(ContextData.getDataFromKey("buyerOrderId"));
            updateShipment.setSellerGstin((String) ContextData.getDataFromKey("sellerGstin"));
            updateShipment.setInvoiceDate(timeStamp);
            updateShipment.setActualDispatchDate(timeStamp);
            updateShipment.setActualDeliveryDate(timeStamp);
            updateShipment.getCurrentState().setState(status);
            updateShipment.setId(ContextData.getDataFromKey("shipmentId"));
            updateShipment.setSellerOrderId(ContextData.getDataFromKey("sellerOrderId"));

            if (nonNull(Data.get().get("shipmentQuantity"))) {
                updateShipment.getLineItemsDetails().get(0).getInvoiceDelivery().setQuantity((Double) Data.get().get("shipmentQuantity"));
                if (nonNull(Data.get().get("finalSellingPrice"))) {
                    double invoiceBaseAmount= (Double) Data.get().get("shipmentQuantity")*(Double) Data.get().get("finalSellingPrice")*10;
                    updateShipment.getShipmentInvoiceDetails().setInvoicedBaseAmount(invoiceBaseAmount);
                    updateShipment.getShipmentInvoiceDetails().setTotalInvoicedAmount(invoiceBaseAmount*1.18);
                    updateShipment.getShipmentInvoiceDetails().setInvoicedTaxAmount(invoiceBaseAmount*0.18);
                }
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
          //  prev = status;

            response = callShipmentUpdateApi(updateShipment);
            System.out.println("UpdateShipment:" + response);
            assertNotNull(response, "unable to update the shipment");
            setPojoToContextData("updateShipmentResp", response);

        return response;
    }

    public void updateShipmentDelivered() throws IOException, InterruptedException {
        String uuid = RandomStringGenerator.RandomInteger();
        UpdateShipmentResp response = null;
        Thread.sleep(44444);
        Integer temp = getInvoiceDeliveryIdAndStatus();

        for (String status : List.of(DISPATCHED, DELIVERED)) {

            UpdateShipment updateShipment = getPayloadJsonFileAsPOJO((String) Data.get().get("UPDATE_SHIPMENT"), UpdateShipment.class);
            updateShipment.setCustomerOrderId(ContextData.getDataFromKey("buyerOrderId"));
            updateShipment.setSellerGstin((String) ContextData.getDataFromKey("sellerGstin"));
            updateShipment.setInvoiceDate(timeStamp);
            updateShipment.setActualDispatchDate(timeStamp);
            updateShipment.setActualDeliveryDate(timeStamp);
            updateShipment.getCurrentState().setState(status);
            updateShipment.setId(ContextData.getDataFromKey("shipmentId"));
            updateShipment.setSellerOrderId(ContextData.getDataFromKey("sellerOrderId"));

            if (nonNull(Data.get().get("shipmentQuantity"))) {
                updateShipment.getLineItemsDetails().get(0).getInvoiceDelivery().setQuantity((Double) Data.get().get("shipmentQuantity"));
                if (nonNull(Data.get().get("finalSellingPrice"))) {
                    double invoiceBaseAmount= (Double) Data.get().get("shipmentQuantity")*(Double) Data.get().get("finalSellingPrice")*10;
                    updateShipment.getShipmentInvoiceDetails().setInvoicedBaseAmount(invoiceBaseAmount);
                    updateShipment.getShipmentInvoiceDetails().setTotalInvoicedAmount(invoiceBaseAmount*1.18);
                    updateShipment.getShipmentInvoiceDetails().setInvoicedTaxAmount(invoiceBaseAmount*0.18);
                }
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
           // prev = status;

            response = callShipmentUpdateApi(updateShipment);
            System.out.println("UpdateShipment:" + response);
            assertNotNull(response, "unable to update the shipment");

        }
    }
}
