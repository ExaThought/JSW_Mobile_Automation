package com.qapitol.jsw.apitest.apis.impl;

import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.pojos.order.appTesting.AppResponse;
import com.qapitol.jsw.apitest.pojos.order.rcv2CartSizeResponse.Rcv2CartSize;
import com.qapitol.jsw.apitest.pojos.order.rcv2AddToCartRequestPayload.Rcv2AddToCartRequest;
import com.qapitol.jsw.apitest.pojos.order.rcv2DeleteLineItemRequestPayload.DeleteLineItemRequest;
import com.qapitol.jsw.apitest.pojos.order.rcv2Response.Rcv2Response;
import com.qapitol.jsw.apitest.pojos.order.updateLineItemRequestPayload.UpdateLineItemPayload;
import com.qapitol.sauron.data.Data;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.nonNull;

public class CartRCV2ApiImp extends BaseApiImpl {

    public Rcv2Response getAddToCartResponse(String payload) throws IOException {

        // Read the request payload and convert as request pojo
        Rcv2AddToCartRequest requestBody = getPayloadJsonFileAsPOJO((String) Data.get().get(payload),
                Rcv2AddToCartRequest.class);

        // Read data from Yaml.file
        String quantity = (String) Data.get().get("quantity_value");
        String customerId = (String) Data.get().get("customer_ID");

        // set the payload
        requestBody.getCustomerDetails().setCustomerId(customerId);
        if (nonNull(quantity)) {
            requestBody.getLineItems().get(0).getAttributeData().getCustomAttributes().get(0).setValue(quantity);
        }

        // Call the API
        RequestApi requestApi = ccpUrl().create(RequestApi.class);

        return requestApi.rcv2AddToCartRequest(requestBody);
    }

    public Rcv2Response getAddToCartResponseWithInvalidData(String payload) throws IOException {
        // Read the request payload and convert as request pojo
        Rcv2AddToCartRequest requestBody = getPayloadJsonFileAsPOJO((String) Data.get().get(payload),
                Rcv2AddToCartRequest.class);

        // Read data from Yaml.file
        String customerId = (String) Data.get().get("customer_ID");

        // set the payload
        requestBody.getCustomerDetails().setCustomerId(customerId);
        // Set Invalid MMID
        if (requestBody.getLineItems() != null && !requestBody.getLineItems().isEmpty()) {
            requestBody.getLineItems().get(0).setProductMMId("INVALID_MMID");
            requestBody.getLineItems().get(0).setVariantMMId("INVALID_VARIANT_MMID");
        }

        // Call the API
        RequestApi requestApi = ccpUrl().create(RequestApi.class);

        return requestApi.rcv2AddToCartRequest(requestBody);
    }

    public Rcv2Response getDeleteLineItemResponse(String payload, List<String> lineItem) throws IOException {

        // Read the request payload and convert as request pojo
        DeleteLineItemRequest requestBody = getPayloadJsonFileAsPOJO((String) Data.get().get(payload),
                DeleteLineItemRequest.class);

        // set the payload
        String customerId = (String) Data.get().get("customer_ID");
        requestBody.getCustomerDetails().setCustomerId(customerId);

        requestBody.setLineItemIdList(lineItem);

        // Call the API
        RequestApi requestApi = ccpUrl().create(RequestApi.class);

        return requestApi.rcv2DeleteLineItem(requestBody);
    }

    public Rcv2Response getUpdateLineItemResponse(String payload, Rcv2Response addToCartResponse) throws IOException {

        // Read the request payload and convert as request pojo
        UpdateLineItemPayload requestBody = getPayloadJsonFileAsPOJO((String) Data.get().get(payload),
                UpdateLineItemPayload.class);

        // set the payload
        String customerId = (String) Data.get().get("customer_ID");
        requestBody.getCustomerDetails().setCustomerId(customerId);

        String lineItemId = addToCartResponse.getData().getLineItemList().get(0).getLineItemIdentifier()
                .getCtLineItemId();
        requestBody.setLineItemId(lineItemId);
        requestBody.getLineItem().getAttributeData().getCustomAttributes().get(0).setValue("60");

        // Call the API
        RequestApi requestApi = ccpUrl().create(RequestApi.class);

        return requestApi.rcv2UpdateLineItem(requestBody);
    }

    public Rcv2Response getCartResponse() {

        // Call the API
        RequestApi requestApi = ccpUrl().create(RequestApi.class);
        return requestApi.rcv2GetCart();
    }

    public void getCartSize() {
        // Call the API
        RequestApi requestApi = ccpUrl().create(RequestApi.class);
        requestApi.rcv2CartSize();

    }

    public Rcv2Response emptyCart() throws IOException {
        Rcv2Response cart = getCartResponse();
        if (cart != null && cart.getData() != null && cart.getData().getLineItemList() != null
                && !cart.getData().getLineItemList().isEmpty()) {
            List<String> itemIds = new java.util.ArrayList<>();
            for (com.qapitol.jsw.apitest.pojos.order.rcv2Response.LineItem item : cart.getData().getLineItemList()) {
                itemIds.add(item.getLineItemIdentifier().getCtLineItemId());
            }
            return getDeleteLineItemResponse("RCV2_DELETE_LINE_ITEM", itemIds);
        }
        return cart;
    }

    public Rcv2CartSize getCartSizeResponse() {

        // call the API
        RequestApi requestApi = ccpUrl().create(RequestApi.class);
        return requestApi.rcv2CartSize();
    }

    public AppResponse getAppApiResponse(){
        RequestApi requestApi=appUrl().create(RequestApi.class);
        return requestApi.getAppResponse();

    }

}
