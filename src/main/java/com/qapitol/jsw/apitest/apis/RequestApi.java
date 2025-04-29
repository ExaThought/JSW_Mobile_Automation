package com.qapitol.jsw.apitest.apis;



import com.qapitol.jsw.apitest.pojos.order.Rcv2Request.Rcv2Req;
import com.qapitol.jsw.apitest.pojos.order.Rcv2Response.Rcv2Res;

import javax.ws.rs.*;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Consumes(APPLICATION_JSON)
public interface RequestApi {

    @POST
    @Path("v2/cart/add")
    @Produces(APPLICATION_JSON)
    Rcv2Res createRcViaV2API(List<Rcv2Req> rcv2Req);
}
