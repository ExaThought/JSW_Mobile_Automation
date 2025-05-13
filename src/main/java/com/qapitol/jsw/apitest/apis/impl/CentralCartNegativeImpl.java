package com.qapitol.jsw.apitest.apis.impl;

import com.qapitol.jsw.apitest.pojos.order.centralCartAddToCartResponse.AddToCartRes;
import com.qapitol.jsw.apitest.pojos.order.centralCartSlcAddToCartRequest.CentralCartSlcAddToCartReq;
import com.qapitol.sauron.data.Data;

import java.io.IOException;

public class CentralCartNegativeImpl extends BaseApiImpl {

    private AddToCartRes cartResponse;
    private CentralCartSlcAddToCartReq addToCartRequest;
    CentralCartAddToCartApiImpl centralCartAddToCartApi=new CentralCartAddToCartApiImpl();


    public AddToCartRes getCartTypeAndCartJourneyTypeResponse(String cartType, String cartJourneyType) throws IOException {

        //Load the Request Body
         addToCartRequest=getPayloadJsonFileAsPOJO((String) Data.get().get("CENTRAL_CART_SLC_ADD_TO_CART"),CentralCartSlcAddToCartReq.class);

        //Set cartType and cartJourneyType
        addToCartRequest.getCartIdentifier().setCartJourneyType(cartJourneyType);
        addToCartRequest.getCartIdentifier().setCartType(cartType);
        cartResponse=centralCartAddToCartApi.getAddToCartResponse(addToCartRequest);
        int responseErrorCode=cartResponse.getError().getCode();
        if(cartResponse.getData()!=null){
            String cartId=cartResponse.getData().getCartId();
            System.out.println("CartId="+cartId);
        }
        return cartResponse;
    }

    public AddToCartRes getResponseForWrongStoreFront(String storeFront) throws IOException {
        //Load the Request Body
         addToCartRequest=getPayloadJsonFileAsPOJO((String) Data.get().get("CENTRAL_CART_SLC_ADD_TO_CART"),CentralCartSlcAddToCartReq.class);

        //Set StoreFront
        addToCartRequest.getCartIdentifier().setStoreFront(storeFront);
        cartResponse=centralCartAddToCartApi.getAddToCartResponse(addToCartRequest);
        return cartResponse;
    }

    public AddToCartRes getDeliveryTypeResponse(String wrongDeliveryType) throws IOException {

        //Load the Request Body
        addToCartRequest=getPayloadJsonFileAsPOJO((String) Data.get().get("CENTRAL_CART_SLC_ADD_TO_CART"),CentralCartSlcAddToCartReq.class);

        // set wrong delivery Type
        addToCartRequest.getShippingDetails().setDeliveryType(wrongDeliveryType);

        //Request API
        cartResponse=centralCartAddToCartApi.getAddToCartResponse(addToCartRequest);
        int wrongDeliveryTypeErrorCode=cartResponse.getError().getCode();

        // Set delivery Type as null
        addToCartRequest.getShippingDetails().setDeliveryType(null);
        // Request APi
        cartResponse=centralCartAddToCartApi.getAddToCartResponse(addToCartRequest);
        int nullDeliveryTypeErrorCode=cartResponse.getError().getCode();

        //Check both wrong delivery Type and Null are equal
        if(wrongDeliveryTypeErrorCode!=1000){
         throw new IOException("Able to create cart with Wrong DeliveryType");
        }
        else if (nullDeliveryTypeErrorCode!=1000) {
            throw new IOException("Able to create cart with Null DeliveryType");
        }
        return cartResponse;
    }

    public AddToCartRes getWrongProductMMIDResponse(String wrongProductMMID) throws IOException {

            // Load the Request Body
            addToCartRequest = getPayloadJsonFileAsPOJO((String) Data.get().get("CENTRAL_CART_SLC_ADD_TO_CART"), CentralCartSlcAddToCartReq.class);

            // Set Wrong ProductMMID
            addToCartRequest.getLineItems().get(0).setProductMmid(wrongProductMMID);

            // Get Add to Cart Response
            cartResponse = centralCartAddToCartApi.getAddToCartResponse(addToCartRequest);

        return cartResponse;
    }
}
