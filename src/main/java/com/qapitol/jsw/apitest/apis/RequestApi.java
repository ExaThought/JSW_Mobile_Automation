package com.qapitol.jsw.apitest.apis;


import com.qapitol.jsw.apitest.pojos.order.appTesting.AppResponse;
import com.qapitol.jsw.apitest.pojos.order.orderConfirm.OrderConfirmReqPayload.OrderConfirmation;
import com.qapitol.jsw.apitest.pojos.order.orderConfirm.orderConfirmResPayload.OrderConfirmationRes;
import com.qapitol.jsw.apitest.pojos.order.paymentDue.PaymentDueResponse;
import com.qapitol.jsw.apitest.pojos.order.rcv2CartSizeResponse.Rcv2CartSize;
import com.qapitol.jsw.apitest.pojos.order.centralCartUpdateItemQuantityRequest.CentralCartUpdateLineItemReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartDeleteApiRequest.CentralCartDeleteApiReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartDeleteApiResponse.CentralCartDeleteApiRes;
import com.qapitol.jsw.apitest.pojos.order.centralCartDeleteLineItemRequest.CentralCartDeleteLineItemReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartFetchApiRequest.CentralCartFetchApiReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartFetchApiResponse.CentralCartFetchApiRes;
import com.qapitol.jsw.apitest.pojos.order.centralCartRcAddToCart.CentralCartRcAddToCartReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartSizeRequest.CentralCartSizeReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartSizeResponse.CentralCartSizeRes;
import com.qapitol.jsw.apitest.pojos.order.centralCartSlcAddToCartRequest.CentralCartSlcAddToCartReq;
import com.qapitol.jsw.apitest.pojos.order.ctSlcSloRequestPayload.CtSlcSloRequestPayload;
import com.qapitol.jsw.apitest.pojos.order.customerProfile.CustomerProfileResponse;
import com.qapitol.jsw.apitest.pojos.order.ctSlcSloResponse.CtSlcSloRes;
import com.qapitol.jsw.apitest.pojos.order.jswServiceRcv2Request.Rcv2Req;
import com.qapitol.jsw.apitest.pojos.order.centralCartAddToCartResponse.AddToCartRes;
import com.qapitol.jsw.apitest.pojos.order.rcv2AddToCartRequestPayload.Rcv2AddToCartRequest;
import com.qapitol.jsw.apitest.pojos.order.rcv2DeleteLineItemRequestPayload.DeleteLineItemRequest;
import com.qapitol.jsw.apitest.pojos.order.rcv2Response.Rcv2Response;
import com.qapitol.jsw.apitest.pojos.order.updateLineItemRequestPayload.UpdateLineItemPayload;


import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.MediaType.WILDCARD;

@Consumes(APPLICATION_JSON)
public interface RequestApi {


    @POST
    @Path("v2/cart/add")
    @Produces(APPLICATION_JSON)
     AddToCartRes createRcViaV2API(Rcv2Req rcv2Req);

    @POST
    @Path("v1/cart/delete")
    @Produces(APPLICATION_JSON)
    CentralCartDeleteApiRes requestCentralCartDeleteCartId(CentralCartDeleteApiReq deleteCartIdRequestBody);

    @POST
    @Path("v1/cart/get")
    @Produces(APPLICATION_JSON)
    CentralCartFetchApiRes requestCentralCartFetchCartId(CentralCartFetchApiReq fetchRequestBody);

    @POST
    @Path("v1/cart/add")
    @Produces(APPLICATION_JSON)
    AddToCartRes rcAddToCart(CentralCartRcAddToCartReq centralCartRcAddToCartReq);

    @POST
    @Path("v1/cart/cart-size")
    @Produces(APPLICATION_JSON)
    CentralCartSizeRes requestCentralCartSizeApi(CentralCartSizeReq centralCartSizeReq);

    @POST
    @Path("v1/cart/update/line-item-quantity")
    @Produces(APPLICATION_JSON)
    AddToCartRes requestUpdateLineItemApi(CentralCartUpdateLineItemReq updateLineItemRequestBody);


    @POST
    @Path("v1/cart/delete/line-items")
    @Produces(APPLICATION_JSON)
    AddToCartRes centralCartDeleteLineItem(CentralCartDeleteLineItemReq requestBody);

    @POST
    @Path("v1/cart/add")
    @Produces(APPLICATION_JSON)
    AddToCartRes slcAddToCart(CentralCartSlcAddToCartReq addToCartReq);

    @POST
    @Path("data-sync/import/sales-opp")
    @Produces(APPLICATION_JSON)
    CtSlcSloRes requestCtCashAndCarrySlcSlo(CtSlcSloRequestPayload requestBody);

    @POST
    @Path("v2/cart/add")
    @Produces(APPLICATION_JSON)
    Rcv2Response rcv2AddToCartRequest(Rcv2AddToCartRequest requestBody);

    @POST
    @Path("v2/cart/delete-items")
    @Produces(APPLICATION_JSON)
    Rcv2Response rcv2DeleteLineItem(DeleteLineItemRequest requestBody);

    @POST
    @Path("v2/cart/update-line-item")
    @Produces(APPLICATION_JSON)
    Rcv2Response rcv2UpdateLineItem(UpdateLineItemPayload requestBody);

    @GET
    @Path("v2/cart/get")
    @Produces(APPLICATION_JSON)
    Rcv2Response rcv2GetCart();

    @GET
    @Path("v2/cart/size")
    @Produces(APPLICATION_JSON)
    Rcv2CartSize rcv2CartSize();

    @POST
    @Path("v2/cart/delete-items")
    @Produces(APPLICATION_JSON)
    Rcv2Response rcv2DeleteCart();

    @PUT
    @Path("joms/api/external/v1/order/order-update")
    @Produces(APPLICATION_JSON)
    OrderConfirmationRes orderConfirmRequest(OrderConfirmation requestBody);

    @GET
    @Path("jswretail/v1/seller")
    @Produces(APPLICATION_JSON)
    AppResponse getAppResponse();

    /**
     * Experience-middleware customer profile.
     * Base URL comes from the `middlewareUrl` property, so this one declaration serves both:
     *   https://experience-middleware.preprod.jswonemsme.com/xp-mi/public/v3/customer-profile
     *   https://experience-middleware.qa.jswone.in/xp-mi/public/v3/customer-profile
     * The `access_token` header is injected by LoginApiImpl via Token.getCCPToken().
     */
    @GET
    @Path("xp-mi/public/v3/customer-profile")
    @Produces({APPLICATION_JSON, TEXT_PLAIN, WILDCARD})
    CustomerProfileResponse getCustomerProfile();

    @GET
    @Path("joms/api/internal/v1/customer-balance-and-due-summary/{gstNumber}")
    @Produces(APPLICATION_JSON)
    PaymentDueResponse paymentDueRequest(@PathParam("gstNumber") String gstNumber,
                                         @QueryParam("entityName") String entityName,
                                         @QueryParam("dueInDays") int dueInDays,
                                         @QueryParam("dueLater") boolean dueLater);
}
