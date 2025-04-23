package com.qapitol.jsw.apitest.apis;

import com.qapitol.jsw.apitest.pojos.order.confirmOrder.ConfirmOrder;
import com.qapitol.jsw.apitest.pojos.order.confirmOrder.ConfirmOrderResp;
import com.qapitol.jsw.apitest.pojos.order.createDO.CreateDo;
import com.qapitol.jsw.apitest.pojos.order.createDO.CreateDoResp;
import com.qapitol.jsw.apitest.pojos.order.createIncomingPayment.CreateIncomingPayment;
import com.qapitol.jsw.apitest.pojos.order.createOrder.CreateOrder;
import com.qapitol.jsw.apitest.pojos.order.createOrder.CreateOrderResp;
import com.qapitol.jsw.apitest.pojos.order.createShipment.CreateShipment;
import com.qapitol.jsw.apitest.pojos.order.createShipment.CreateShipmentResp;
import com.qapitol.jsw.apitest.pojos.order.getCreditDetails.GetCreditDetailsResp;
import com.qapitol.jsw.apitest.pojos.order.getLineItemDetails.LineItemDetails;
import com.qapitol.jsw.apitest.pojos.order.getOMSShipmentOrder.GetOMSShipmentOrderResp;
import com.qapitol.jsw.apitest.pojos.order.getOrder.GetOMSOrderResp;
import com.qapitol.jsw.apitest.pojos.order.rejectDo.RejectDO;
import com.qapitol.jsw.apitest.pojos.order.rejectDo.RejectDOResp;
import com.qapitol.jsw.apitest.pojos.order.shipmentInvoice.ShipmentInvoice;
import com.qapitol.jsw.apitest.pojos.order.shipmentInvoice.ShipmentInvoiceResp;
import com.qapitol.jsw.apitest.pojos.order.updateShipment.UpdateShipment;
import com.qapitol.jsw.apitest.pojos.order.updateShipment.UpdateShipmentResp;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Consumes(APPLICATION_JSON)
public interface OrderApi {
    @POST
    @Path("/data-sync/import/sales-opp")
    @Produces(APPLICATION_JSON)
    CreateOrderResp createOrderViaSLOAPI(CreateOrder createOrder);


    @GET
    @Path("oms/order/v2/number/{orderNumber}")
    @Produces("application/x-ndjson")
    GetOMSOrderResp getJOMSOrder(@PathParam("orderNumber") String orderNumber);

    @GET
    @Path("oms/shipment/order-number/{orderNumber}")
    @Produces(APPLICATION_JSON)
    GetOMSShipmentOrderResp getJOMSShipmentOrder(@PathParam("orderNumber") String orderNumber);

    @PUT
    @Path("joms/api/external/v1/order/order-update")
    @Produces(APPLICATION_JSON)
    ConfirmOrderResp putConfirmOrder(ConfirmOrder confirmOrder);

    @POST
    @Path("oms/plant-supply")
    @Produces(APPLICATION_JSON)
//    Response createDO(CreateDo createDo);
    CreateDoResp createDO(CreateDo createDo);

    @POST
    @Path("oms/plant-supply/status/release")
    @Produces(APPLICATION_JSON)
    Response createDORelease(@QueryParam("psId") int psId);

    @POST
    @Path("oms/shipment")
    @Produces(APPLICATION_JSON)
    CreateShipmentResp createShipment(CreateShipment createShipment);

    @PUT
    @Path("oms/shipment")
    @Produces(APPLICATION_JSON)
    UpdateShipmentResp updateShipment(UpdateShipment updateShipment);

    @POST
    @Path("joms/api/external/v1/shipment/post-invoice")
    @Produces(APPLICATION_JSON)
    ShipmentInvoiceResp createShipmentInvoice(ShipmentInvoice shipmentInvoice);

    @GET
    @Path("oms/line-item/order-number/{orderNumber}")
    @Produces(APPLICATION_JSON)
    List<LineItemDetails> getLineItemDetails(@PathParam("orderNumber") String orderNumber);

    @POST
    @Path("Incoming_Payments")
    @Produces(APPLICATION_JSON)
    Response createIncomingPayment(@QueryParam("publickey") String publicKey, CreateIncomingPayment createIncomingPayment);

    @GET
    @Path("joms/api/external/v2/getCreditInfoByPan")
    @Produces(APPLICATION_JSON)
    GetCreditDetailsResp getCreditDetails(@QueryParam("pan") String pan, @QueryParam("gstin") String gstin);

    @PUT
    @Path("oms/plant-supply")
    @Produces(APPLICATION_JSON)
    RejectDOResp rejectDO(RejectDO rejectDO);
}
