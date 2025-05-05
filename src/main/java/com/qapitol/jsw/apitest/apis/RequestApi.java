package com.qapitol.jsw.apitest.apis;


import com.qapitol.jsw.apitest.pojos.order.centralCartDeleteApiRequest.CentralCartDeleteApiReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartDeleteApiResponse.CentralCartDeleteApiRes;
import com.qapitol.jsw.apitest.pojos.order.centralCartFetchApiRequest.CentralCartFetchApiReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartFetchApiResponse.CentralCartFetchApiRes;
import com.qapitol.jsw.apitest.pojos.order.rcv2Request.Rcv2Req;
import com.qapitol.jsw.apitest.pojos.order.rcv2Response.Rcv2Res;

import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Consumes(APPLICATION_JSON)
public interface RequestApi {

    @POST
    @Path("v2/cart/add")
    @Produces(APPLICATION_JSON)
    Rcv2Res createRcViaV2API(Rcv2Req rcv2Req);

    @POST
    @Path("central-cart/api/internal/v1/cart/delete")
    @Produces(APPLICATION_JSON)
    CentralCartDeleteApiRes deleteCartId(@HeaderParam("X-Request-ID") String requestId, @HeaderParam("x-api-key") String apiKey,
            CentralCartDeleteApiReq deleteCartIdRequestBody);

    @POST
    @Path("central-cart/api/internal/v1/cart/get")
    @Produces(APPLICATION_JSON)
    CentralCartFetchApiRes fetchCartId(@HeaderParam("X-Request-ID") String requestId,@HeaderParam("x-api-key") String apiKey,
                                       CentralCartFetchApiReq fetchRequestBody);
}
