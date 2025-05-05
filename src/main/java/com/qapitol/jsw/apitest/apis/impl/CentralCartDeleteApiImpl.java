package com.qapitol.jsw.apitest.apis.impl;

import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.pojos.order.centralCartDeleteApiRequest.CentralCartDeleteApiReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartDeleteApiResponse.CentralCartDeleteApiRes;
import org.testng.Assert;

import java.io.IOException;
import java.util.UUID;

public class CentralCartDeleteApiImpl extends BaseApiImpl{

    public void deleteCartId(CentralCartDeleteApiReq deleteApiRequestBody) throws IOException {

        //Generate random RequestId(We need to pass in request header)
        String requestId = UUID.randomUUID().toString();
        String apiKey = "zk9656eke06c6gp4ombsc5thknmqz3hho33xwi59g8fq12vx8oatr2fmxl3qmyvz10keukce1a1h";

        // Call The Api
        RequestApi requestApi = ccpUrl().create(RequestApi.class);
        // Request the Api
         CentralCartDeleteApiRes deleteApiResponseBody=requestApi.deleteCartId(requestId, apiKey, deleteApiRequestBody);

         //Verify the Delete Api Response
        Assert.assertEquals(deleteApiResponseBody.getStatus(),"OK");
        Assert.assertEquals(deleteApiResponseBody.getData().getStatus(),"INACTIVE");
        Assert.assertEquals(deleteApiResponseBody.getData().getCartId(),deleteApiRequestBody.getCartId());
        Assert.assertEquals(deleteApiResponseBody.getSuccess(),true);

    }
}
