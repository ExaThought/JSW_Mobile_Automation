package com.qapitol.jsw.apitest.apis.impl;

import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.pojos.order.centralCartUpdateItemQuantityRequest.CentralCartUpdateLineItemReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartAddToCartResponse.AddToCartRes;

import static com.qapitol.jsw.utils.Hooks.softAssert;

public class CentralCartUpdateLineItemImpl extends BaseApiImpl{

    public void updateTheLineItem(CentralCartUpdateLineItemReq updateLineItemRequestBody, double quantityIncrease){

        //Set URL and Token
        RequestApi requestApi=centralCartCcpUrl().create(RequestApi.class);

        //Request the API
        AddToCartRes updateLineItemResponseBody=requestApi.requestUpdateLineItemApi(updateLineItemRequestBody);
        softAssert.assertEquals(updateLineItemResponseBody.getStatus(),"OK"); //Check Response Status
////        softAssert.assertEquals(updateLineItemResponseBody.getData().getLineItems().get(0).getPurchasedQuantity(),quantityIncrease);//Need to develop this validation



    }

}
