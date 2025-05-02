package com.qapitol.jsw.apitest.apis.impl;


import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.pojos.order.Rcv2Request.Rcv2Req;
import com.qapitol.jsw.apitest.pojos.order.Rcv2Response.Rcv2Res;
import com.qapitol.sauron.data.Data;
import org.testng.Assert;

import java.io.IOException;

import static java.util.Objects.nonNull;


public class CentralCartImpl extends BaseApiImpl {
  private  String deviceType;
  private   String storeFront;
  private  String cartType;
  private  String cartJourneyType;
  private  String deliveryType;
  private  Rcv2Req rcv2RequestBody;
  private  Rcv2Res apiResponse;


    public String getRcV2Response() throws IOException {

        // Load request body from JSON file
         rcv2RequestBody= getPayloadJsonFileAsPOJO((String) Data.get().get("RC_V2_REQ"), Rcv2Req.class);

        // Read values from YAML/test data
         deviceType = (String) Data.get().get("device_type");
         storeFront= (String) Data.get().get("store_front");
         cartType=(String) Data.get().get("cart_type");
         cartJourneyType=(String) Data.get().get("cart_journey_type");
         deliveryType=(String) Data.get().get("delivery_type");

        // Set request payload values from test data
        if (nonNull(deviceType)) {
            rcv2RequestBody.getCartIdentifier().setDeviceType(deviceType);
        }
        if(nonNull(storeFront)){
            rcv2RequestBody.getCartIdentifier().setStoreFront(storeFront);
        }
        if(nonNull(cartType)){
            rcv2RequestBody.getCartIdentifier().setCartType(cartType);
        }
        if(nonNull(cartJourneyType)){
            rcv2RequestBody.getCartIdentifier().setCartJourneyType(cartJourneyType);
        }
        if (nonNull(deliveryType)){
            rcv2RequestBody.getShippingDetails().setDeliveryType(deliveryType);
        }

        // Call the API
        RequestApi requestApi = ccpUrl().create(RequestApi.class);
        apiResponse = requestApi.createRcViaV2API(rcv2RequestBody);
        log.info("RC V2 API successfully Triggered ");

        // Log Cart ID
        String cartId=apiResponse.getData().getCartId();//Reading CartID from response
       log.info("CartID =" +cartId);

        // Validate request vs response
        validationForRCV2Api();

        // sending cartId
        return cartId;
    }

    public void validationForRCV2Api(){
        // Verify response status
        Assert.assertEquals(apiResponse.getStatus(),"CREATED");

        // validation for cart Identifier
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

        // Validate quantity
        int requestedPurchasedQuantity=rcv2RequestBody.getLineItems().get(0).getPurchasedQuantity().getQuantity();//Reading requested purchase quantity
        Double responsePurchasedQuantity=apiResponse.getData().getLineItems().get(0).getPurchasedQuantity().getQuantity(); //Reading response purchase quantity
        Assert.assertEquals(requestedPurchasedQuantity,responsePurchasedQuantity);
        log.info("Purchase Quantity validation is working fine ="+ responsePurchasedQuantity);

        // Validate delivery type
        String requestedDeliveryType= rcv2RequestBody.getShippingDetails().getDeliveryType();
        String responseDeliveryType=apiResponse.getData().getShippingDetails().getDeliveryType();
        Assert.assertEquals(requestedDeliveryType,responseDeliveryType);
        log.info("Delivery Type validation is working fine ="+ responseDeliveryType);


    }

}
