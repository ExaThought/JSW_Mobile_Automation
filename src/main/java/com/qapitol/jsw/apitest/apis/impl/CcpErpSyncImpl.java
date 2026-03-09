package com.qapitol.jsw.apitest.apis.impl;

import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.pojos.order.orderConfirm.OrderConfirmReqPayload.OrderConfirmation;
import com.qapitol.jsw.apitest.pojos.order.orderConfirm.orderConfirmResPayload.OrderConfirmationRes;
import com.qapitol.sauron.data.Data;

import java.io.IOException;
import java.util.Objects;

public class CcpErpSyncImpl extends BaseApiImpl {
    public String getOrderConfirmation(String orderNumber) throws IOException {

        //Read the request payload from default-data.yaml
        OrderConfirmation requestBody=getPayloadJsonFileAsPOJO((String) Data.get().get("ORDER_CONFIRMATION"), OrderConfirmation.class);

        //Set OrderNumber
        requestBody.setOrderNumber(orderNumber);

        RequestApi requestApi = getOmsUrl().create(RequestApi.class);
       OrderConfirmationRes response= requestApi.orderConfirmRequest(requestBody);
        if (response == null || Objects.isNull(response.getCurrentState())) {
            throw new IllegalStateException("Order confirmation response is invalid for order: " + orderNumber);
        }
      return response.getCurrentState().getOrderState();

    }
}
