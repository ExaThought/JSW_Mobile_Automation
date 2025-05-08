package com.qapitol.jsw.apitest.apis.impl;


import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.pojos.order.centralCartSizeRequest.CentralCartSizeReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartSizeResponse.CentralCartSizeRes;
import org.testng.Assert;

public class CentralCartSizeImpl extends BaseApiImpl {


    public void getCartSizeResponse(CentralCartSizeReq cartSizeRequestBody, int cartSize) {


        RequestApi requestApi = centralCartCcpUrl().create(RequestApi.class);

        // Request the Api
        CentralCartSizeRes cartSizeResponseBody = requestApi.cartSize(cartSizeRequestBody);

        //Validation
        Assert.assertEquals(cartSizeResponseBody.getStatus(),"OK");
        Assert.assertEquals(cartSizeResponseBody.getData().getTotalLineItemCount(),cartSize);//Check cartSize
    }

}
