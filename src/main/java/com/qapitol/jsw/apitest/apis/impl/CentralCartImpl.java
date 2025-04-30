package com.qapitol.jsw.apitest.apis.impl;


import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.pojos.order.Rcv2Request.Rcv2Req;
import com.qapitol.jsw.apitest.pojos.order.Rcv2Response.Rcv2Res;
import com.qapitol.sauron.data.Data;
import org.testng.Assert;


import java.io.IOException;



public class CentralCartImpl extends BaseApiImpl {

    public Rcv2Res GetRcV2Response() throws IOException {

        Rcv2Req rcv2RequestBody= getPayloadJsonFileAsPOJO((String) Data.get().get("RC_V2_REQ"), Rcv2Req.class);

        RequestApi requestApi = ccpUrl().create(RequestApi.class);

        Rcv2Res createOrderRes = requestApi.createRcViaV2API(rcv2RequestBody);
        log.info("RC V2 API successfully Triggered");
        Assert.assertEquals(createOrderRes.getStatus(),"CREATED");
         return createOrderRes;

    }

}
