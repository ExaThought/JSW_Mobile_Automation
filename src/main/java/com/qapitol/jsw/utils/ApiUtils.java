package com.qapitol.jsw.utils;

import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.apis.impl.BaseApiImpl;
import com.qapitol.jsw.apitest.pojos.order.paymentDue.PaymentDueResponse;
import io.restassured.RestAssured;

public class ApiUtils extends BaseApiImpl {
    public  PaymentDueResponse getPaymentDueResponse(){
        RequestApi requestApi=getOmsUrl().create(RequestApi.class);
        return requestApi.paymentDueRequest("24AABCR4412R1Z7","JOPL",3,false);
    }

    public static String getCustomerDetails(String email, String password){
        //Written Rest Assured code to get customer details
        RestAssured.baseURI= ConfigUtil.getSecretValue("ccpUrl");
        String token= Token.getCCPToken(email,password);
            String response= RestAssured.given().header("Access_token",token).header("content-type","application/json")
                    .when().get("customer/me/v2")
                    .then().extract().jsonPath().getString("gstIn");

            return response;

    }
}
