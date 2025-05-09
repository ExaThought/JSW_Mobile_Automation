package com.qapitol.jsw.apitest.apis.impl;

import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.pojos.order.centralCartAddToCartResponse.AddToCartRes;
import com.qapitol.jsw.apitest.pojos.order.centralCartDeleteLineItemRequest.CentralCartDeleteLineItemReq;
import org.testng.Assert;

import static com.qapitol.jsw.utils.CustomUtills.softAssert;

public class CentralCartDeleteLineItemImpl extends BaseApiImpl {


    public void deleteLineItem(CentralCartDeleteLineItemReq requestBody, AddToCartRes addToCartResponse ){

        RequestApi requestApi = centralCartCcpUrl().create(RequestApi.class);

        //Request the Api
       AddToCartRes responseBody=requestApi.centralCartDeleteLineItem(requestBody);

       int beforeDeleteLineItemCount=addToCartResponse.getData().getLineItems().size();
       int afterDeleteLineItemCount=responseBody.getData().getLineItems().size();

       // Validation
        softAssert.assertNotEquals(beforeDeleteLineItemCount,afterDeleteLineItemCount,"Checking Delete Line Item");
        softAssert.assertEquals(beforeDeleteLineItemCount,afterDeleteLineItemCount+1,"Checking Delete Line Item");


    }
}
