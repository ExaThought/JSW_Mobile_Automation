package com.qapitol.jsw.apitest.apis.impl;

import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.pojos.order.centralCartFetchApiRequest.CentralCartFetchApiReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartFetchApiResponse.CentralCartFetchApiRes;


import java.util.UUID;

public class CentralCartFetchApiImpl extends BaseApiImpl {

    public CentralCartFetchApiRes fetchCart(CentralCartFetchApiReq fetchRequestBody,Object cartResponse){

        String requestId = UUID.randomUUID().toString();
        String apiKey = "zk9656eke06c6gp4ombsc5thknmqz3hho33xwi59g8fq12vx8oatr2fmxl3qmyvz10keukce1a1h";

        // Call The Api
        RequestApi requestApi = ccpUrl().create(RequestApi.class);
        // Request the Api
        CentralCartFetchApiRes fetchApiResponseBody=requestApi.fetchCartId(requestId,apiKey,fetchRequestBody);

        System.out.println(cartResponse);

        return fetchApiResponseBody;

    }
}
