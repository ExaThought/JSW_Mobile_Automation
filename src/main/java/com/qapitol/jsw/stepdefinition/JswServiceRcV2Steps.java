package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.apitest.apis.impl.JswRcV2Impl;
import com.qapitol.jsw.apitest.pojos.order.centralCartAddToCartResponse.AddToCartRes;
import io.cucumber.java.en.Given;


import java.io.IOException;

public class JswServiceRcV2Steps {

    AddToCartRes rcv2Response;
    JswRcV2Impl jswRcAddToCart;

    @Given("Request the Api for MSME from WEBSITE")
    public void requestTheApiForMsmeFromWebsite() throws IOException {
        jswRcAddToCart = new JswRcV2Impl(); //creating Object
        rcv2Response = jswRcAddToCart.getRcV2Response();// Getting the response and checking the response

    }

    @Given("Request the Api for MSME from MOBILE")
    public void requestTheApiForMsmeFromMobile() throws IOException {
        jswRcAddToCart = new JswRcV2Impl(); //creating Object
        rcv2Response = jswRcAddToCart.getRcV2Response();// Getting the response and checking the response
    }

    @Given("Request the Api for CONSTRUCT from WEBSITE")
    public void requestTheApiForConstructFromWebsite() throws IOException {
        jswRcAddToCart = new JswRcV2Impl(); //creating Object
        rcv2Response = jswRcAddToCart.getRcV2Response();// Getting the response and checking the response
    }

    @Given("Request the Api for CONSTRUCT from MOBILE")
    public void requestTheApiForConstructFromMobile() throws IOException {
        jswRcAddToCart = new JswRcV2Impl(); //creating Object
    }

    @Given("Request API and check delivery type in response")
    public void requestApiAndCheckDeliveryTypeInResponse() throws IOException {
        jswRcAddToCart = new JswRcV2Impl(); //creating Object
        rcv2Response = jswRcAddToCart.getRcV2Response();// Getting the response and checking the response
    }




}
