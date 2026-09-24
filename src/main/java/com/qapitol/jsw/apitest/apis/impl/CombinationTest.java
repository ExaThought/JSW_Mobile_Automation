package com.qapitol.jsw.apitest.apis.impl;

import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.pojos.order.centralCartUpdateItemQuantityRequest.CentralCartUpdateLineItemReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartAddToCartResponse.AddToCartRes;
import com.qapitol.jsw.apitest.pojos.order.centralCartDeleteApiRequest.CentralCartDeleteApiReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartDeleteLineItemRequest.CentralCartDeleteLineItemReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartRcAddToCart.CentralCartRcAddToCartReq;
import com.qapitol.sauron.data.Data;

import java.io.IOException;
import java.util.List;

public class CombinationTest extends BaseApiImpl {

    AddToCartRes cartResponse;

    public void getAddToCartResponse(String deviceType) throws IOException {
        //Get the request Body
        CentralCartRcAddToCartReq requestBody = getPayloadJsonFileAsPOJO((String) Data.get().get("CENTRAL_CART_RC_ADD_TO_CART"), CentralCartRcAddToCartReq.class);
        requestBody.getCartIdentifier().setDeviceType(deviceType);
        RequestApi requestApi=centralCartCcpUrl().create(RequestApi.class);
        cartResponse=requestApi.rcAddToCart(requestBody);
    }

    public void getUpdateTheLineItemResponse(String deviceType) throws IOException {

        CentralCartUpdateLineItemReq requestBody = new CentralCartUpdateLineItemReq();
        requestBody = getPayloadJsonFileAsPOJO((String) Data.get().get("CENTRAL_CART_UPDATE_LINE_ITEM"), CentralCartUpdateLineItemReq.class);

        requestBody.getCartIdentifier().setDeviceType(deviceType);
        requestBody.setSfCustomerId(cartResponse.getData().getCustomerDetails().getSfCustomerId());
        requestBody.setCartId(cartResponse.getData().getCartId());
        requestBody.setLineItemId(cartResponse.getData().getLineItems().get(0).getLineItemId());
        requestBody.setQuantity(10.0);

        CentralCartUpdateLineItemImpl centralCartUpdateLineItem = new CentralCartUpdateLineItemImpl();
        centralCartUpdateLineItem.updateTheLineItem(requestBody, 10);
    }

    public void getDeleteCartResponse(String deviceType) throws IOException {
        CentralCartDeleteLineItemReq requestBody = new CentralCartDeleteLineItemReq();
        requestBody = getPayloadJsonFileAsPOJO((String) Data.get().get("CENTRAL_CART_UPDATE_LINE_ITEM"), CentralCartDeleteLineItemReq.class);

        requestBody.setCartId(cartResponse.getData().getCartId());
        requestBody.setSfCustomerId(cartResponse.getData().getCustomerDetails().getSfCustomerId());
        requestBody.setLineItemIds(List.of(cartResponse.getData().getLineItems().get(0).getLineItemId()));
        requestBody.getCartIdentifier().setDeviceType(deviceType);

        CentralCartDeleteLineItemImpl updateLineItemResponse = new CentralCartDeleteLineItemImpl();
        updateLineItemResponse.deleteLineItem(requestBody, cartResponse);

        //Delete cart
        CentralCartDeleteApiReq deleteCartRequestBody = new CentralCartDeleteApiReq();
        deleteCartRequestBody = getPayloadJsonFileAsPOJO((String) Data.get().get("CENTRAL_CART_UPDATE_LINE_ITEM"), CentralCartDeleteApiReq.class);

        deleteCartRequestBody.setCartId(cartResponse.getData().getCartId());
        deleteCartRequestBody.setSfCustomerId(cartResponse.getData().getCustomerDetails().getSfCustomerId());
        deleteCartRequestBody.getCartIdentifier().setDeviceType(deviceType);
        CentralCartDeleteApiImpl centralCartDeleteApi = new CentralCartDeleteApiImpl();
        centralCartDeleteApi.deleteCartId(deleteCartRequestBody);

    }
}
