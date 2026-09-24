package com.qapitol.jsw.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.apis.impl.BaseApiImpl;
import com.qapitol.jsw.apitest.pojos.order.customerDetails.CustomerDetailsResponse;
import com.qapitol.jsw.apitest.pojos.order.customerProfile.CustomerProfileResponse;
import com.qapitol.jsw.apitest.pojos.order.paymentDue.PaymentDueResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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
                    System.out.println("cart was not empty");
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
                                .log().ifValidationFails().extract().response();
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

        String companyName = response.jsonPath().getString("companyName");
        String message = response.jsonPath().getString("data.message");

        // GSTIN exists -> false
        // GSTIN does not exist -> true
        if (companyName != null && !companyName.isEmpty()) {
            logger.info("GSTIN " + gstin + " is not registered (company: " + companyName + ")");
            return true;
        }

        if ("Please login to your existing account or contact support for assistance".equals(message)) {
            logger.info("GSTIN " + gstin + " is already registered");
            return false;
        }

        // GSTIN does not exist
        return true;

    }

    public static String generateNotRegisteredMobileNumber() {

        String mobile;

        do {
            mobile = generateMobileNumber(); // Generates a 10-digit number starting with 6-9
        } while (isMobileAlreadyRegistered(mobile));

        return mobile;
    }
    public static String generateMobileNumber() {

        ThreadLocalRandom random = ThreadLocalRandom.current();

        StringBuilder mobile = new StringBuilder();
        mobile.append(random.nextInt(6, 10)); // 6-9

        for (int i = 0; i < 9; i++) {
            mobile.append(random.nextInt(10));
        }

        return mobile.toString();
    }

    public static boolean isMobileAlreadyRegistered(String mobileNumber) {
        baseURI = ConfigUtil.getSecretValue("ccpPortalUrl");
        if (jwtToken == null) {
            jwtToken = Token.getCCPToken();
        }
        Response response=null;
        try {

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("fullName", "Automation User");
            requestBody.put("phoneNumber", mobileNumber);
            requestBody.put("email", "automation" + System.currentTimeMillis() + "@yopmail.com");
            requestBody.put("gstin", "07AAACB1534F4Z5");

             response = RestAssured
                    .given()
                    .contentType(ContentType.JSON)
                    .header("access_token", jwtToken)
                    .body(requestBody)
                    .post("/customer/validate/registration-details").then().statusCode(200).extract().response();

            response.then().statusCode(200);


        } catch (Exception e) {

        }
        return !response.jsonPath().getBoolean("success");
    }

    public static Map<String, String> fetchUserByMobile(String mobileNumber) {


        // The CONFIGURED user's token, the same identity every other call in this class uses.
        // This used to log in as secondary.user.*, a second account that existed only to serve
        // these two lookups: it was never the customer under test, had to be kept alive in every
        // environment, and its failures surfaced as errors about a user the feature never named.
        // fetch-user is scoped to the caller's own account, so the caller's token is the right one.
        String jwtToken = Token.getCCPToken();

        Response response=null;
        Map<String, String> sfIds = new HashMap<>();
        try {
            // log().ifValidationFails(), never log().all(): the request carries the JWT in its
            // authorization header and the response carries the user's record, so logging either
            // unconditionally printed a live token on every call.
             response = given()
                    .header("authorization", jwtToken)
                    .queryParam("includePermission", true)
                    .queryParam("phoneNumber", mobileNumber)
                    .when()
                    .get(ConfigUtil.getSecretValue("middlewareUrl")+"xp-mi/public/v1/fetch-user")
                    .then()
                    .statusCode(200).log().ifValidationFails()
                    .extract()
                    .response();

            String contactSfid = response.jsonPath().getString("data.user.contactSfid");
            String customerSfId = response.jsonPath().getString("data.customerSfId");


            sfIds.put("contactSfid", contactSfid);
            sfIds.put("customerSfId", customerSfId);
        }catch (Exception e)
        {

        }

        return sfIds;
    }

    public static boolean verifyContactSfid(String customerSfId, String expectedContactSfid)
    {
        baseURI = ConfigUtil.getSecretValue("sfUrl");
        if (jwtToken == null) {
            jwtToken = Token.getCCPToken();
        }
        Response response=null;
        boolean found=false;
        try {
            Thread.sleep(3000);
             response = given()
                    .header("x-api-key", "test")
                    .header("Content-Type", "application/json")
                    .when()
                    .get("account-master/users/v1/" + customerSfId)
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            List<Map<String, Object>> users = response.jsonPath().getList("data");
             found = users.stream()
                    .anyMatch(user -> expectedContactSfid.equals(user.get("contactSfId")));
        }catch (Exception e)
        {

        }
        return found;

    }

    /**
     * True when the user on this mobile number holds NO departments and NO permissions.
     *
     * <p>Renamed from {@code isSecondaryUserDeleted}, which named neither what it asks nor what it
     * answers. It does not check deletion, and nothing about it is specific to a "secondary" user:
     * it reads {@code data.departments} and {@code data.permissions} off fetch-user and reports
     * whether both are absent.
     *
     * <p>That is why the same call serves two callers that sound unrelated - a user who was just
     * DELETED and a user who was never created both come back with neither field. So it answers
     * "does this number have any access to the account", and the caller decides what that means.
     *
     * <p>Returns false on any transport failure, so a network problem reads as "still has access"
     * rather than as a silent pass.
     */
    public static boolean hasNoDepartmentsOrPermissions(String mobileNumber)
    {


        // The CONFIGURED user's token, the same identity every other call in this class uses.
        // This used to log in as secondary.user.*, a second account that existed only to serve
        // these two lookups: it was never the customer under test, had to be kept alive in every
        // environment, and its failures surfaced as errors about a user the feature never named.
        // fetch-user is scoped to the caller's own account, so the caller's token is the right one.
        String jwtToken = Token.getCCPToken();

        try {
            Thread.sleep(3000);
            Response response = RestAssured
                    .given()
                    .header("authorization", jwtToken)
                    .queryParam("includePermission", true)
                    .queryParam("phoneNumber", mobileNumber)
                    .get(ConfigUtil.getSecretValue("middlewareUrl")+"xp-mi/public/v1/fetch-user").then().statusCode(200).extract().response();

            if (response.statusCode() != 200) {
                return false;
            }

            return response.jsonPath().get("data.departments") == null
                    && response.jsonPath().get("data.permissions") == null;

        } catch (Exception e) {
            logger.info("Failed to fetch user details: " + e.getMessage());
            return false;
        }
    }

    // ---------------------------------------------------------------------
    // Experience-middleware : customer profile (v3)
    // ---------------------------------------------------------------------

    private static final String CUSTOMER_PROFILE_PATH = "xp-mi/public/v3/customer-profile";

    /**
     * Builds the fully-qualified customer-profile endpoint for whichever environment is
     * currently configured via the `middlewareUrl` property:
     *   preprod -> https://experience-middleware.preprod.jswonemsme.com/xp-mi/public/v3/customer-profile
     *   qa      -> https://experience-middleware.qa.jswone.in/xp-mi/public/v3/customer-profile
     */
    public static String customerProfileEndpoint() {
        String baseUrl = ConfigUtil.resolveOrThrow("middlewareUrl");
        if (!baseUrl.endsWith("/")) {
            baseUrl = baseUrl + "/";
        }
        return baseUrl + CUSTOMER_PROFILE_PATH;
    }

    /**
     * GET {middlewareUrl}xp-mi/public/v3/customer-profile using the token minted by
     * {@link Token#getCCPToken()} (test-group user from the properties file).
     *
     * Mirrors the browser request exactly:
     *   -H 'accept: application/json, text/plain, *&#47;*'
     *   -H 'access_token: &lt;jwt&gt;'
     *
     * @return the raw RestAssured response, so callers can assert on status code as well as body.
     */
    public static Response fetchCustomerProfile() {
        return fetchCustomerProfile(Token.getCCPToken());
    }

    /**
     * Same as {@link #fetchCustomerProfile()} but with a caller-supplied token, for negative
     * cases such as an expired or tampered access_token.
     */
    public static Response fetchCustomerProfile(String accessToken) {
        String endpoint = customerProfileEndpoint();
        String acceptHeader = "application/json, text/plain, */*";
        io.restassured.specification.RequestSpecification spec = RestAssured.given()
                .baseUri(endpoint)
                .header("accept", acceptHeader)
                .header("access_token", accessToken);
        Response response = spec.when()
                .get()
                .then().log().ifError()
                .extract().response();

        logger.info("GET " + endpoint + " -> HTTP " + response.getStatusCode());
        return response;
    }

    /**
     * Typed variant - asserts HTTP 200 and deserializes into {@link CustomerProfileResponse}.
     *
     * @throws IllegalStateException if the call does not return 200, so a middleware failure
     *                               surfaces at the point of failure rather than as a later NPE.
     */
    public static CustomerProfileResponse getCustomerProfile() {
        return getCustomerProfile(Token.getCCPToken());
    }

    /**
     * {@link #getCustomerProfile()} for a caller-supplied access_token - e.g. the jwtToken the
     * browser received from otp/verify - so no REST login to auth/login is made.
     */
    public static CustomerProfileResponse getCustomerProfile(String accessToken) {
        Response response = fetchCustomerProfile(accessToken);

        if (response.getStatusCode() != 200) {
            throw new IllegalStateException("customer-profile API returned HTTP "
                    + response.getStatusCode() + " for " + customerProfileEndpoint()
                    + " | body: " + response.asString());
        }

        try {
            return new ObjectMapper().readValue(response.asString(), CustomerProfileResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse CustomerProfileResponse from: "
                    + response.asString(), e);
        }
    }

    /**
     * Reads the {@code isTransacting} flag from the customer-profile response.
     *
     * The field is located by key rather than by a fixed path, so it is found wherever the
     * middleware nests it inside {@code data}.
     *
     * @throws IllegalStateException if the field is absent from the response, so a contract
     *                               change is reported instead of silently defaulting to false.
     */
    public static boolean isTransacting() {
        return isTransacting(Token.getCCPToken());
    }

    /** {@link #isTransacting()} for a caller-supplied access_token. */
    public static boolean isTransacting(String accessToken) {
        CustomerProfileResponse profile = getCustomerProfile(accessToken);
        Boolean isTransacting = profile.findFirstBoolean("isTransacting");

        if (isTransacting == null) {
            throw new IllegalStateException("'isTransacting' not found in the customer-profile "
                    + "response from " + customerProfileEndpoint() + " | data: " + profile.getData());
        }

        logger.info("isTransacting = " + isTransacting);
        return isTransacting;
    }

}
