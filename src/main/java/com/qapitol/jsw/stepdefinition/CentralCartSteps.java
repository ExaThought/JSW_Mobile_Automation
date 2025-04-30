package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.apitest.apis.impl.CentralCartImpl;
import io.cucumber.java.en.Given;

import java.io.IOException;

public class CentralCartSteps {

    @Given("Request the API and read Response")
    public void requestTheApiAndReadResponse() throws IOException {
        CentralCartImpl centralCart=new CentralCartImpl();
        centralCart.GetRcV2Response();
    }
}
