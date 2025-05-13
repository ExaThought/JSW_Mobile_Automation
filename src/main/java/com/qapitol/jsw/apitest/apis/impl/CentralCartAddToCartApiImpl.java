package com.qapitol.jsw.apitest.apis.impl;

import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.pojos.order.centralCartAddToCartResponse.AddToCartRes;
import com.qapitol.jsw.apitest.pojos.order.centralCartSlcAddToCartRequest.CentralCartSlcAddToCartReq;

import javax.ws.rs.BadRequestException;

public class CentralCartAddToCartApiImpl extends BaseApiImpl {

    public AddToCartRes getAddToCartResponse(CentralCartSlcAddToCartReq cartRequestBody){

        AddToCartRes cartResponse;
        try {
            RequestApi requestApi=centralCartCcpUrl().create(RequestApi.class);
            cartResponse=requestApi.slcAddToCart(cartRequestBody);
            return cartResponse;

        } catch (BadRequestException e) {

             cartResponse=e.getResponse().readEntity(AddToCartRes.class);
            return cartResponse;
        }
    }
}
