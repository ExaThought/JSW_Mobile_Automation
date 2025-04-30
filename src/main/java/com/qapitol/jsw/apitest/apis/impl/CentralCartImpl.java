package com.qapitol.jsw.apitest.apis.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.pojos.order.Rcv2Request.Rcv2Req;
import com.qapitol.jsw.apitest.pojos.order.Rcv2Response.Rcv2Res;
import com.qapitol.jsw.apitest.pojos.order.createOrder.CreateOrder;
import com.qapitol.sauron.data.Data;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;


public class CentralCartImpl extends BaseApiImpl {

    public Rcv2Res RCV2Response() throws IOException {

        Rcv2Req rcv2RequestBody= getPayloadJsonFileAsPOJO((String) Data.get().get("RC_V2_REQ"), Rcv2Req.class);

        RequestApi requestApi = ccpUrl().create(RequestApi.class);

        Rcv2Res createOrderRes = requestApi.createRcViaV2API(rcv2RequestBody);
        return createOrderRes;
    }

}
