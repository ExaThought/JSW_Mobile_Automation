package com.qapitol.jsw.apitest.apis.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.pojos.order.Rcv2Request.Rcv2Req;
import com.qapitol.jsw.apitest.pojos.order.Rcv2Response.Rcv2Res;
import com.qapitol.sauron.data.Data;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.util.Collections;
import java.util.List;


public class CentralCartImpl extends BaseApiImpl {

    public void RCV2Response() throws IOException {
        List<Rcv2Req> rcv2RequestBody= Collections.singletonList(getPayloadJsonFileAsPOJO((String) Data.get().get("RC_V2_REQ"), Rcv2Req.class));

        RequestApi requestApi = ccpUrl().create(RequestApi.class);
        Rcv2Res createOrderRes = requestApi.createRcViaV2API((List<Rcv2Req>) rcv2RequestBody);
    }

}
