package com.qapitol.jsw.apitest.apis.impl;

import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.pojos.order.centralCartDeleteApiRequest.CentralCartDeleteApiReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartDeleteApiResponse.CentralCartDeleteApiRes;

import java.io.IOException;


import static com.qapitol.jsw.utils.Hooks.softAssert;

public class CentralCartDeleteApiImpl extends BaseApiImpl {

    public void deleteCartId(CentralCartDeleteApiReq deleteApiRequestBody) throws IOException {

        // Call The Api
        RequestApi requestApi = centralCartCcpUrl().create(RequestApi.class);
        // Request the Api
        CentralCartDeleteApiRes deleteApiResponseBody = requestApi.requestCentralCartDeleteCartId(deleteApiRequestBody);

        //Verify the Delete Api Response
        softAssert.assertEquals(deleteApiResponseBody.getStatus(), "OK");
        softAssert.assertEquals(deleteApiResponseBody.getData().getStatus(), "INACTIVE");
        softAssert.assertEquals(deleteApiResponseBody.getData().getCartId(), deleteApiRequestBody.getCartId());
    }
}
