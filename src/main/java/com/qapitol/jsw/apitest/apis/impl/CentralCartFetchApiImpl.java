package com.qapitol.jsw.apitest.apis.impl;

import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.pojos.order.centralCartAddToCartResponse.AddToCartRes;
import com.qapitol.jsw.apitest.pojos.order.centralCartFetchApiRequest.CentralCartFetchApiReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartFetchApiResponse.CentralCartFetchApiRes;

import static com.qapitol.jsw.utils.Hooks.softAssert;


public class CentralCartFetchApiImpl extends BaseApiImpl {

    CentralCartFetchApiRes fetchApiResponseBody;

    public void fetchCart(CentralCartFetchApiReq fetchRequestBody, AddToCartRes cartResponse){

        // Call The Api
        RequestApi requestApi = centralCartCcpUrl().create(RequestApi.class);

        // Request the Api
        fetchApiResponseBody=requestApi.requestCentralCartFetchCartId(fetchRequestBody);

        //Validation for Add-to-cart and Fetch Cart(Both response should be same)
        validation(cartResponse);

    }

    private void validation(AddToCartRes  addToCartApiResponse) {
        //Check CartID
        softAssert.assertEquals(fetchApiResponseBody.getData().getCartId(),addToCartApiResponse.getData().getCartId());
    }


}
