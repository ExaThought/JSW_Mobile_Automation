package com.qapitol.jsw.utils;

import com.qapitol.jsw.apitest.apis.impl.BaseApiImpl;
import com.qapitol.jsw.apitest.pojo.CcpTokenRequestPayload;
import com.qapitol.sauron.data.Data;

import static io.restassured.RestAssured.*;

public class Token extends BaseApiImpl {

    public static String getCCPToken(){
        baseURI=ConfigUtil.getSecretValue("ccpPortalUrl");

        String requestPayload="{\"email\":\"testautomation01@yopmail.com\",\"phoneNumber\":null,\"password\":\"SnN3QDIwMjU=\",\"storeKey\":\"msme\"}";
        System.out.println(requestPayload);

        return given().log().all().body(requestPayload).header("content-type","application/json")
                .when().log().all().post(ConfigUtil.getSecretValue("ccpTokenUrl"))
                .then().log().all().statusCode(200).extract().jsonPath().getString("jwtToken");
    }
    public static String getCCPToken(String email, String password){
        baseURI=ConfigUtil.getSecretValue("ccpPortalUrl");
        String storeKey="msme";

        String requestPayload="{"
                + "email:" + email + ","
                + "phoneNumber:null,"
                + "password:" + password + ","
                + "storeKey:" + storeKey
                + "}";

        return given().log().all().body(requestPayload).header("content-type","application/json")
                .when().log().all().post(ConfigUtil.getSecretValue("ccpTokenUrl"))
                .then().log().all().statusCode(200).extract().jsonPath().getString("jwtToken");
    }

}
