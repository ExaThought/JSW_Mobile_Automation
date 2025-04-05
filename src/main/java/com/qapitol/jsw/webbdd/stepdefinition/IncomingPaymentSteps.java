package com.qapitol.jsw.webbdd.stepdefinition;

import com.qapitol.jsw.apitest.apis.impl.OrderFlowImpl;
import io.cucumber.java.en.Then;

import java.io.IOException;

public class IncomingPaymentSteps {

    @Then("Create Incoming Payment In Zoho")
    public void createIncomingPaymentInZoho() throws IOException {
        OrderFlowImpl orderFlow = new OrderFlowImpl();
        orderFlow.createIncomingPayment();
    }
}
