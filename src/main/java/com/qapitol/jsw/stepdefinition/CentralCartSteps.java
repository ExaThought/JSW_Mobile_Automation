package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.apitest.apis.impl.CentralCartRcV2Impl;
import com.qapitol.jsw.apitest.pojos.order.rcv2Response.Rcv2Res;
import io.cucumber.java.en.Given;


import java.io.IOException;

public class CentralCartSteps {

    Rcv2Res rcv2Response;

    @Given("Request the Api for MSME from WEBSITE")
    public void requestTheApiForMsmeFromWebsite() throws IOException {
        CentralCartRcV2Impl centralCartRcV2 = new CentralCartRcV2Impl(); //creating Object
        rcv2Response = centralCartRcV2.getRcV2Response();// Getting the response and checking the response
        centralCartRcV2.nonNullValidationForResponse();//NonNull validation for response
        centralCartRcV2.validationForRCV2Api();// RCV2 validation
        centralCartRcV2.fetchRcCart();// Fetch the cart
        centralCartRcV2.deleteCartID();//Delete the cart
    }

    @Given("Request the Api for MSME from MOBILE")
    public void requestTheApiForMsmeFromMobile() throws IOException {
        CentralCartRcV2Impl centralCartRcV2 = new CentralCartRcV2Impl(); //creating Object
        rcv2Response = centralCartRcV2.getRcV2Response();// Getting the response and checking the response
        centralCartRcV2.nonNullValidationForResponse();//NonNull validation for response
        centralCartRcV2.validationForRCV2Api();// RCV2 validation
        centralCartRcV2.fetchRcCart();// Fetch the cart
        centralCartRcV2.deleteCartID();//Delete the cart
    }

    @Given("Request the Api for CONSTRUCT from WEBSITE")
    public void requestTheApiForConstructFromWebsite() throws IOException {
        CentralCartRcV2Impl centralCartRcV2 = new CentralCartRcV2Impl(); //creating Object
        rcv2Response = centralCartRcV2.getRcV2Response();// Getting the response and checking the response
        centralCartRcV2.nonNullValidationForResponse();//NonNull validation for response
        centralCartRcV2.validationForRCV2Api();// RCV2 validation
        centralCartRcV2.fetchRcCart();// Fetch the cart
        centralCartRcV2.deleteCartID();//Delete the cart
    }

    @Given("Request the Api for CONSTRUCT from MOBILE")
    public void requestTheApiForConstructFromMobile() throws IOException {
        CentralCartRcV2Impl centralCartRcV2 = new CentralCartRcV2Impl(); //creating Object
        rcv2Response = centralCartRcV2.getRcV2Response();// Getting the response and checking the response
        centralCartRcV2.nonNullValidationForResponse();//NonNull validation for response
        centralCartRcV2.validationForRCV2Api();// RCV2 validation
        centralCartRcV2.fetchRcCart();// Fetch the cart
        centralCartRcV2.deleteCartID();//Delete the cart
    }

    @Given("Request API and check delivery type in response")
    public void requestApiAndCheckDeliveryTypeInResponse() throws IOException {
        CentralCartRcV2Impl centralCartRcV2 = new CentralCartRcV2Impl(); //creating Object
        rcv2Response = centralCartRcV2.getRcV2Response();// Getting the response and checking the response
        centralCartRcV2.nonNullValidationForResponse();//NonNull validation for response
        centralCartRcV2.validationForRCV2Api();// RCV2 validation
        centralCartRcV2.fetchRcCart();// Fetch the cart
        centralCartRcV2.deleteCartID();//Delete the cart
    }


}
