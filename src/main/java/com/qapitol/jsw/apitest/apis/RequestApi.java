package com.qapitol.jsw.apitest.apis;


import com.qapitol.jsw.apitest.pojos.order.centralCartDeleteApiRequest.CentralCartDeleteApiReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartDeleteApiResponse.CentralCartDeleteApiRes;
import com.qapitol.jsw.apitest.pojos.order.centralCartFetchApiRequest.CentralCartFetchApiReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartFetchApiResponse.CentralCartFetchApiRes;
import com.qapitol.jsw.apitest.pojos.order.centralCartRcAddToCart.CentralCartRcAddToCartReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartSizeRequest.CentralCartSizeReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartSizeResponse.CentralCartSizeRes;
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
    CentralCartDeleteApiRes deleteCartId(CentralCartDeleteApiReq deleteCartIdRequestBody);

    @POST
    @Path("v1/cart/get")
    @Produces(APPLICATION_JSON)
    CentralCartFetchApiRes fetchCartId(CentralCartFetchApiReq fetchRequestBody);

    @POST
    @Path("v1/cart/add")
    @Produces(APPLICATION_JSON)
    AddToCartRes centralCartRcAddToCart(CentralCartRcAddToCartReq centralCartRcAddToCartReq);

    @POST
    @Path("v1/cart/cart-size")
    @Produces(APPLICATION_JSON)
    CentralCartSizeRes cartSize(CentralCartSizeReq centralCartSizeReq);
}
