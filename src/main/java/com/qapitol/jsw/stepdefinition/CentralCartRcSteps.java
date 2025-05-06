package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.apitest.apis.impl.CentralCartRcImpl;
import com.qapitol.jsw.apitest.apis.impl.JswRcV2Impl;
import com.qapitol.jsw.apitest.pojos.order.centralCartAddToCartResponse.AddToCartRes;
import io.cucumber.java.en.Given;

import java.io.IOException;

public class CentralCartRcSteps {
    AddToCartRes centralCartRcResponse;
    CentralCartRcImpl centralCartRcV2;

    @Given("Request the Central Cart Api for MSME from WEBSITE")
    public void requestTheCentralCartApiForMsmeFromWebsite() throws IOException {
        centralCartRcV2 = new CentralCartRcImpl(); //creating Object
        centralCartRcResponse = centralCartRcV2.getCentralCartRcAddToCartResponse();// Getting the response and checking the response
        centralCartRcV2.nonNullValidationForResponse();//NonNull validation for response
        centralCartRcV2.validationForRCV2Api();// RCV2 validation
        centralCartRcV2.getCartSize();// Check the Cart Size
        centralCartRcV2.fetchRcCart();// Fetch the cart
        centralCartRcV2.deleteCartID();//Delete the cart
    }

    @Given("Request the Central Cart Api for MSME from MOBILE")
    public void requestTheCentralCartApiForMsmeFromMobile() throws IOException {
        JswRcV2Impl centralCartRcV2 = new JswRcV2Impl(); //creating Object
        centralCartRcResponse = centralCartRcV2.getRcV2Response();// Getting the response and checking the response
        centralCartRcV2.nonNullValidationForResponse();//NonNull validation for response
        centralCartRcV2.validationForRCV2Api();// RCV2 validation
        centralCartRcV2.fetchRcCart();// Fetch the cart
        centralCartRcV2.deleteCartID();//Delete the cart
    }

    @Given("Request the Central Cart Api for CONSTRUCT from WEBSITE")
    public void requestTheCentralCartApiForConstructFromWebsite() throws IOException {
        JswRcV2Impl centralCartRcV2 = new JswRcV2Impl(); //creating Object
        centralCartRcResponse = centralCartRcV2.getRcV2Response();// Getting the response and checking the response
        centralCartRcV2.nonNullValidationForResponse();//NonNull validation for response
        centralCartRcV2.validationForRCV2Api();// RCV2 validation
        centralCartRcV2.fetchRcCart();// Fetch the cart
        centralCartRcV2.deleteCartID();//Delete the cart
    }

    @Given("Request the Central Cart Api for CONSTRUCT from MOBILE")
    public void requestTheCentralCartApiForConstructFromMobile() throws IOException {
        JswRcV2Impl centralCartRcV2 = new JswRcV2Impl(); //creating Object
        centralCartRcResponse = centralCartRcV2.getRcV2Response();// Getting the response and checking the response
        centralCartRcV2.nonNullValidationForResponse();//NonNull validation for response
        centralCartRcV2.validationForRCV2Api();// RCV2 validation
        centralCartRcV2.fetchRcCart();// Fetch the cart
        centralCartRcV2.deleteCartID();//Delete the cart
    }

    @Given("Request Central Cart API and check delivery type in response")
    public void requestCentralCartApiAndCheckDeliveryTypeInResponse() throws IOException {
        JswRcV2Impl centralCartRcV2 = new JswRcV2Impl(); //creating Object
        centralCartRcResponse = centralCartRcV2.getRcV2Response();// Getting the response and checking the response
        centralCartRcV2.nonNullValidationForResponse();//NonNull validation for response
        centralCartRcV2.validationForRCV2Api();// RCV2 validation
        centralCartRcV2.fetchRcCart();// Fetch the cart
        centralCartRcV2.deleteCartID();//Delete the cart
    }


}
