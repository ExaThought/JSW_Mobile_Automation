package com.qapitol.jsw.apitest.apis;


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
import com.qapitol.jsw.apitest.pojos.order.ctSlcSloResponse.CtSlcSloRes;
import com.qapitol.jsw.apitest.pojos.order.jswServiceRcv2Request.Rcv2Req;
import com.qapitol.jsw.apitest.pojos.order.centralCartAddToCartResponse.AddToCartRes;


import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

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


}
