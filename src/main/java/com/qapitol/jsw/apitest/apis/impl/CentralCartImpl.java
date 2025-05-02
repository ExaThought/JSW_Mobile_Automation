package com.qapitol.jsw.apitest.apis.impl;


import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.pojos.order.Rcv2Request.Rcv2Req;
import com.qapitol.jsw.apitest.pojos.order.Rcv2Response.Rcv2Res;
import com.qapitol.sauron.data.Data;
import org.testng.Assert;

import java.io.IOException;

import static java.util.Objects.nonNull;


public class CentralCartImpl extends BaseApiImpl {

    public String GetRcV2Response() throws IOException {

        Rcv2Req rcv2RequestBody= getPayloadJsonFileAsPOJO((String) Data.get().get("RC_V2_REQ"), Rcv2Req.class);

        String deviceType = (String) Data.get().get("device_type");  //Reading deviceType from centralCartRcV2.yaml
        String storeFront= (String) Data.get().get("store_front"); //Reading StoreFront from centralCartRcV2.yaml
        String cartType=(String) Data.get().get("cart_type");//Reading cartType from centralCartRcV2.yaml
        String cartJourneyType=(String) Data.get().get("cart_journey_type");//Reading cartJourneyType from centralCartRcV2.yaml
        String deliveryType=(String) Data.get().get("delivery_type");//Reading deliveryType from centralCartRcV2.yaml
        if (nonNull(deviceType)) {
            rcv2RequestBody.getCartIdentifier().setDeviceType(deviceType);// set deviceType in request payload
        }
        if(nonNull(storeFront)){
            rcv2RequestBody.getCartIdentifier().setStoreFront(storeFront); // Set StoreFront in request payload
        }
        if(nonNull(cartType)){
            rcv2RequestBody.getCartIdentifier().setCartType(cartType); // Set CartType in request payload
        }
        if(nonNull(cartJourneyType)){
            rcv2RequestBody.getCartIdentifier().setCartJourneyType(cartJourneyType); // Set CartJourney in request payload
        }
        if (nonNull(deliveryType)){
            rcv2RequestBody.getShippingDetails().getDeliveryType(); //set deliveryType in request
        }

        RequestApi requestApi = ccpUrl().create(RequestApi.class);
        Rcv2Res apiResponse = requestApi.createRcViaV2API(rcv2RequestBody);
        log.info("RC V2 API successfully Triggered ");

        String cartId=apiResponse.getData().getCartId();//Reading CartID from response
       log.info("CartID =" +cartId);

        //Verifying the response data
        Assert.assertEquals(apiResponse.getStatus(),"CREATED");//Checking the response body status
        if(nonNull(deviceType)) {
            Assert.assertEquals(apiResponse.getData().getCartIdentifier().getDeviceType(), deliveryType);
            log.info("Device Type validation is working fine =" + deliveryType);
        }
        if(nonNull(storeFront)) {
            Assert.assertEquals(apiResponse.getData().getCartIdentifier().getStoreFront(), storeFront);
            log.info("Store Front validation is working fine ="+ storeFront);
        }
        if(nonNull(cartType)) {
            Assert.assertEquals(apiResponse.getData().getCartIdentifier().getCartType(), cartType);
            log.info("Cart Type validation is working fine ="+ cartType);
        }
        if(nonNull(cartJourneyType)) {
            Assert.assertEquals(apiResponse.getData().getCartIdentifier().getCartJourneyType(),cartJourneyType);
            log.info("Cart Journey Type validation is working fine ="+ cartJourneyType);
        }

        //Verify requested quantity and response quantity
        int requestedPurchasedQuantity=rcv2RequestBody.getLineItems().get(0).getPurchasedQuantity().getQuantity();//Reading requested purchase quantity
        Double responsePurchasedQuantity=apiResponse.getData().getLineItems().get(0).getPurchasedQuantity().getQuantity(); //Reading response purchase quantity
        Assert.assertEquals(requestedPurchasedQuantity,responsePurchasedQuantity);
        log.info("Purchase Quantity validation is working fine ="+ responsePurchasedQuantity);

        //Verify Requested deliveryType and Response DeliveryType
        String requestedDeliveryType= rcv2RequestBody.getShippingDetails().getDeliveryType();
        String responseDeliveryType=apiResponse.getData().getShippingDetails().getDeliveryType();
        Assert.assertEquals(requestedDeliveryType,responseDeliveryType);
        log.info("Delivery Type validation is working fine ="+ responseDeliveryType);

        return cartId;// sending cartId

    }

}
