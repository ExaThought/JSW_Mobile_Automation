package com.qapitol.jsw.apitest.apis.impl;

import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.pojos.order.centralCartAddToCartResponse.AddToCartRes;
import com.qapitol.jsw.apitest.pojos.order.centralCartFetchApiRequest.CentralCartFetchApiReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartFetchApiResponse.CentralCartFetchApiRes;
import com.qapitol.sauron.common.configuration.Config;
import org.testng.Assert;


import java.util.UUID;

public class CentralCartFetchApiImpl extends BaseApiImpl {

    CentralCartFetchApiRes fetchApiResponseBody;
    AddToCartRes addToCartApiResponse;

    public CentralCartFetchApiRes fetchCart(CentralCartFetchApiReq fetchRequestBody, AddToCartRes cartResponse){

//        String requestId = UUID.randomUUID().toString();
//        String apiKey = Config.getConfigProperty("XApiKey");
//        System.out.println(apiKey);
//        String apiKey = "zk9656eke06c6gp4ombsc5thknmqz3hho33xwi59g8fq12vx8oatr2fmxl3qmyvz10keukce1a1h";

        // Call The Api
        RequestApi requestApi = centralCartCcpUrl().create(RequestApi.class);


        // Request the Api
        fetchApiResponseBody=requestApi.fetchCartId(fetchRequestBody);

        addToCartApiResponse=cartResponse;

        //Validation for Add-to-cart and Fetch Cart(Both response should be same)
        validation();

        return fetchApiResponseBody;

    }

    private void validation() {
        //Check CartID
        Assert.assertEquals(fetchApiResponseBody.getData().getCartId(),addToCartApiResponse.getData().getCartId());

    }


}
