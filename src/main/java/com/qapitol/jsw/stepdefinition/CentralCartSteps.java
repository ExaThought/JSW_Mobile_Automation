package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.apitest.apis.impl.CentralCartImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.io.IOException;

public class CentralCartSteps {

    String cartId;

    @Given("Request the Api for MSME from WEBSITE")
    public void requestTheApiForMsmeFromWebsite() throws IOException {
        CentralCartImpl centralCart=new CentralCartImpl(); //creating Object
       cartId= centralCart.GetRcV2Response();// Getting the response and checking the response
    }
     @Then("Delete the Cart")
     public void deleteTheCart(){

     }

    @Given("Request the Api for MSME from MOBILE")
    public void requestTheApiForMsmeFromMobile() throws IOException {
        CentralCartImpl centralCart=new CentralCartImpl(); //creating Object
        cartId= centralCart.GetRcV2Response();// Getting the response and checking the response
    }

    @Given("Request the Api for CONSTRUCT from WEBSITE")
    public void requestTheApiForConstructFromWebsite() throws IOException {
        CentralCartImpl centralCart=new CentralCartImpl(); //creating Object
        cartId= centralCart.GetRcV2Response();// Getting the response and checking the response
    }

    @Given("Request the Api for CONSTRUCT from MOBILE")
    public void requestTheApiForConstructFromMobile() throws IOException {
        CentralCartImpl centralCart=new CentralCartImpl(); //creating Object
        cartId= centralCart.GetRcV2Response();// Getting the response and checking the response
    }

    @Given("Request API and check delivery type in response")
    public void requestApiAndCheckDeliveryTypeInResponse() throws IOException {
        CentralCartImpl centralCart=new CentralCartImpl(); //creating Object
        cartId= centralCart.GetRcV2Response();// Getting the response and checking the response
    }


}
