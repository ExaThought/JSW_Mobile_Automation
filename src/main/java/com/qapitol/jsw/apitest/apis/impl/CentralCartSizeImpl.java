package com.qapitol.jsw.apitest.apis.impl;


import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.pojos.order.centralCartSizeRequest.CentralCartSizeReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartSizeResponse.CentralCartSizeRes;
import org.testng.Assert;

import java.util.Optional;

import static com.qapitol.jsw.utils.CustomUtills.softAssert;

public class CentralCartSizeImpl extends BaseApiImpl {


    public void getCartSizeResponse(CentralCartSizeReq cartSizeRequestBody, Integer cartSize) {

        RequestApi requestApi = centralCartCcpUrl().create(RequestApi.class);

        // Request the Api
        CentralCartSizeRes cartSizeResponseBody = requestApi.requestCentralCartSizeApi(cartSizeRequestBody);

        //Validation
        softAssert.assertEquals(cartSizeResponseBody.getStatus(),"OK");
        //Check cartSize
        softAssert.assertEquals(cartSizeResponseBody.getData().getTotalLineItemCount(),cartSize,"Checking CartSize");

    }

}
