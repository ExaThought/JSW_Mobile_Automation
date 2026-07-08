package com.qapitol.jsw.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.apis.impl.BaseApiImpl;
import com.qapitol.jsw.apitest.pojos.order.customerDetails.CustomerDetailsResponse;
import com.qapitol.jsw.apitest.pojos.order.paymentDue.PaymentDueResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static com.qapitol.sauron.core.actions.ActionListener.logger;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class ApiUtils extends BaseApiImpl
{
    public  static String jwtToken;
    public  PaymentDueResponse getPaymentDueResponse(String gstin){
        RequestApi requestApi=getOmsUrl().create(RequestApi.class);
        return requestApi.paymentDueRequest(gstin,"JOPL",3,false);
    }

    public static CustomerDetailsResponse getCustomerDetails(String email, String password){
        //Written Rest Assured code to get customer details
        RestAssured.baseURI= ConfigUtil.getSecretValue("ccpUrl");
        String token= Token.getCCPToken(email,password);
            String response= RestAssured.given().header("Access_token",token).header("content-type","application/json")
                    .when().get("customer/me/v2")
                    .then().extract().asString();

        ObjectMapper mapper=new ObjectMapper();

        try {
            return mapper.readValue(response, CustomerDetailsResponse.class);
        }
        catch (Exception e){
            throw new RuntimeException("Failed to parse CustomerDetailsResponse from API response", e);
        }

    }

    public static String getUserId(String token) {

        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("JWT token is null or empty");
        }
        try {
            String[] parts = token.split("\\.");

            if (parts.length < 2) {
                throw new IllegalArgumentException("Invalid JWT token format");
            }

            String payload = parts[1];

            String json = new String(
                    Base64.getUrlDecoder().decode(payload),
                    StandardCharsets.UTF_8
            );

            JSONObject jsonObject = new JSONObject(json);

            if (!jsonObject.has("userId")) {
                throw new IllegalArgumentException(
                        "userId not found in JWT payload"
                );
            }

            return jsonObject.getString("userId");

        } catch (IllegalArgumentException e) {
            System.out.println("Failed to parse JWT token"+ e.getMessage());
            throw e;
        }
    }

    public static void deleteAllCartItems() {
        try {
            baseURI = ConfigUtil.getSecretValue("ccpPortalUrl");
            jwtToken = Token.getCCPToken();
            //getCustomerDetails("v_nirmal.kumar@jsw.in","Welcome@1234#");
            String userId = getUserId(jwtToken);
            Response response = given()
                    .header("access_token", jwtToken)
                    .contentType(ContentType.JSON)
                    .get("v2/cart/get");

            //System.out.println(response.asString());

            Object data = response.jsonPath().get("data");
            if (data == null) {
                System.out.println("No data found, skipping delete");
                return;
            } else {

                String[] lineItemIds = response.jsonPath()
                        .getList("data.lineItemList.lineItemIdentifier.ctLineItemId")
                        .toArray(new String[0]);


                //  System.out.println("ctLineItemIds: " + lineItemIds);
                if (lineItemIds == null) {
                    System.out.println("Cart is empty");
                    return;
                } else {
                    System.out.println("cart is not empty");
                }

                // Delete one by one
                if (lineItemIds.length != 0) {
                    for (String lineItemId : lineItemIds) {

                        System.out.println("Deleting ctLineItemId: " + lineItemId);

                        Response res = given()
                                .header("access_token", jwtToken)
                                .contentType(ContentType.JSON)
                                .body(Map.of(
                                        "customerDetails", Map.of(

                                                "customerId", userId
                                        ),
                                        "cartIdentifier", Map.of(
                                                "cartType", "enquiry_v2",
                                                "cartJourneyType", "REQUIREMENTS_CART_V2"
                                        ),
                                        "lineItemIdList", Collections.singletonList(lineItemId)
                                ))
                                .post("v2/cart/delete-items")
                                .then()
                                .log().all().extract().response();
                        //System.out.println(res.body());
                    }
                } else {
                    logger.info("cart is empty");
                }
            }
        } catch (Exception e) {

        }
    }

    public static boolean isGstinNotExists(String gstin) {
        boolean result=false;
        baseURI = ConfigUtil.getSecretValue("ccpPortalUrl");

        if (jwtToken == null) {
            jwtToken = Token.getCCPToken();
        }

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("gstin", gstin);
        requestBody.put("email", "feidumasusu-9423@yopmail.com");
        requestBody.put("phoneNumber", "");
        requestBody.put("fullName", "");

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .accept("application/json")
                .header("access_token", jwtToken)
                .body(requestBody)
                .post("/customer/v1/gst/validate")
                .then()
                .statusCode(200)
                .extract()
                .response();
        if (response.asString() == null || response.asString().isEmpty()) {
            throw new RuntimeException("GST validation API returned empty response");
        }

        System.out.println("GST Validation Response: " + response.asPrettyString());

        String companyName = response.jsonPath().getString("companyName");
        String message = response.jsonPath().getString("data.message");

        // GSTIN exists -> false
        // GSTIN does not exist -> true
        if (companyName != null && !companyName.isEmpty()) {
            System.out.println("GSTIN already do not exist and company: " + companyName);
            return true;
        }

        if ("Please login to your existing account or contact support for assistance".equals(message)) {
            System.out.println("GSTIN already exists: " + gstin);
            return false;
        }

        // GSTIN does not exist
        return true;

    }
}
