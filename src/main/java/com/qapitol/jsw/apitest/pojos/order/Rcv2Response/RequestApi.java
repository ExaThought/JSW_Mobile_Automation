package com.qapitol.jsw.apitest.pojos.order.Rcv2Response;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Consumes(APPLICATION_JSON)
public interface RequestApi {

    @POST
    @Path("v2/cart/add")
    @Produces(APPLICATION_JSON)
    String createRcViaV2API();
}
