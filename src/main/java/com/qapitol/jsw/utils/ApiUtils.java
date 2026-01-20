package com.qapitol.jsw.utils;

import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.apis.impl.BaseApiImpl;
import com.qapitol.jsw.apitest.pojos.order.paymentDue.PaymentDueResponse;

public class ApiUtils extends BaseApiImpl {
    public  PaymentDueResponse getPaymentDueResponse(){
        RequestApi requestApi=getOmsUrl().create(RequestApi.class);
        return requestApi.paymentDueRequest("24AABCR4412R1Z7","JOPL",3,false);
    }
}
