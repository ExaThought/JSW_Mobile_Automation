package com.qapitol.jsw.apitest.apis.impl;

import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.pojos.order.centralCartDeleteApiRequest.CentralCartDeleteApiReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartDeleteApiResponse.CentralCartDeleteApiRes;
import org.testng.Assert;

import java.io.IOException;
import java.util.UUID;

public class CentralCartDeleteApiImpl extends BaseApiImpl{

    public void deleteCartId(CentralCartDeleteApiReq deleteApiRequestBody) throws IOException {

        // Call The Api
        RequestApi requestApi = centralCartCcpUrl().create(RequestApi.class);
        // Request the Api
         CentralCartDeleteApiRes deleteApiResponseBody=requestApi.deleteCartId( deleteApiRequestBody);

         //Verify the Delete Api Response
        Assert.assertEquals(deleteApiResponseBody.getStatus(),"OK");
        Assert.assertEquals(deleteApiResponseBody.getData().getStatus(),"INACTIVE");
        Assert.assertEquals(deleteApiResponseBody.getData().getCartId(),deleteApiRequestBody.getCartId());
        Assert.assertEquals(deleteApiResponseBody.getSuccess(),true);

    }
}
