package com.qapitol.jsw.apitest.apis.impl;

import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.pojos.order.centralCartAddToCartResponse.AddToCartRes;
import com.qapitol.jsw.apitest.pojos.order.centralCartFetchApiRequest.CentralCartFetchApiReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartFetchApiResponse.CentralCartFetchApiRes;
import org.testng.Assert;




public class CentralCartFetchApiImpl extends BaseApiImpl {

    CentralCartFetchApiRes fetchApiResponseBody;

    public CentralCartFetchApiRes fetchCart(CentralCartFetchApiReq fetchRequestBody, AddToCartRes cartResponse){

        // Call The Api
        RequestApi requestApi = centralCartCcpUrl().create(RequestApi.class);

        // Request the Api
        fetchApiResponseBody=requestApi.fetchCartId(fetchRequestBody);

        //Validation for Add-to-cart and Fetch Cart(Both response should be same)
        validation(cartResponse);

        return fetchApiResponseBody;
    }

    private void validation(AddToCartRes  addToCartApiResponse) {
        //Check CartID
        Assert.assertEquals(fetchApiResponseBody.getData().getCartId(),addToCartApiResponse.getData().getCartId());

    }


}
