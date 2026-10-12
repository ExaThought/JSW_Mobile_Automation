package com.qapitol.jsw.utils;

import com.qapitol.jsw.apitest.apis.impl.CartRCV2ApiImp;

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

    /**
     * Empties the logged-in user's cart after login.
     *
     * <p>Goes through {@link CartRCV2ApiImp#emptyCartOnce()}: one cart/get and one bulk delete,
     * instead of a cart/get here and another one in "empty the cart via API" afterwards. Failures
     * are logged, not thrown - as before, a cleanup problem must not fail the login itself; the
     * cart steps that need an empty cart assert it.
     */
    public static void deleteAllCartItems() {
        try {
            baseURI = ConfigUtil.getSecretValue("ccpPortalUrl");
            // Other helpers here read jwtToken and relied on this method having set it.
            jwtToken = Token.getCCPToken();
            CartRCV2ApiImp.CartCleanup cleanup = new CartRCV2ApiImp().emptyCartOnce();
            logger.info(cleanup.deleted == 0
                    ? "Cart was empty after login"
                    : "Emptied the cart after login - deleted " + cleanup.deleted
                            + " line item(s), " + cleanup.remaining + " remaining");
        } catch (Exception e) {
            logger.info("Could not empty the cart after login: " + e.getClass().getSimpleName()
                    + ": " + e.getMessage());
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

    /** Headers carrying service credentials; masked in anything RestAssured logs. */
    private static final String[] CREDENTIAL_HEADERS = {"x-api-key", "Authorization", "client_id"};

    /**
     * The current RestAssured config with the credential headers blacklisted, so a request or
     * response that gets logged - {@code log().ifError()} today, {@code log().all()} if someone
     * widens it - prints them as "[ BLACKLISTED ]". Built from the current global config so any
     * other setting on it is kept.
     */
    private static io.restassured.config.RestAssuredConfig redactedLogging() {
        io.restassured.config.RestAssuredConfig current = RestAssured.config();
        return current.logConfig(current.getLogConfig().blacklistHeader(CREDENTIAL_HEADERS[0],
                java.util.Arrays.copyOfRange(CREDENTIAL_HEADERS, 1, CREDENTIAL_HEADERS.length)));
    }

    /**
     * A PAN or GSTIN with all but its first and last two characters masked, for logs and
     * failure messages: 27AADCL2102L1ZQ -> 27***********ZQ. A GSTIN carries the PAN in
     * characters 3-12, so it is masked as well; the state code survives to show which
     * customer's data a log line is about.
     */
    public static String maskId(String id) {
        if (id == null || id.length() <= 4) {
            return "****";
        }
        return id.substring(0, 2) + "*".repeat(id.length() - 4) + id.substring(id.length() - 2);
    }

    /** {@code body} with every "pan" / "gstin" / "gstIn" string value masked (see {@link #maskId}). */
    public static String maskIdsInJson(String body) {
        if (body == null) {
            return null;
        }
        java.util.regex.Matcher m = java.util.regex.Pattern
                .compile("(\"(?:pan|gstin|gstIn|sellerPan)\"\\s*:\\s*\")([^\"]*)(\")").matcher(body);
        StringBuilder out = new StringBuilder();
        while (m.find()) {
            m.appendReplacement(out, java.util.regex.Matcher.quoteReplacement(m.group(1) + maskId(m.group(2)) + m.group(3)));
        }
        m.appendTail(out);
        return out.toString();
    }

    /**
     * OMS due payments for a customer - with {@code dueLater=true}, the data behind the ledger's
     * "Due later" tab. Authenticates with the service {@code omsInternalApiKey}, not a user token.
     */
    public static Response fetchDuePayments(String gstin, String entityName, int dueInDays, boolean dueLater) {
        String endpoint = ConfigUtil.resolveOrThrow("omsBaseUrl")
                + "joms/api/internal/v1/customer-due-payments-list/" + gstin;
        Response response = RestAssured.given()
                .baseUri(endpoint)
                .config(redactedLogging())
                .header("x-api-key", ConfigUtil.resolveOrThrow("omsInternalApiKey"))
                .queryParam("entityName", entityName)
                .queryParam("dueInDays", dueInDays)
                .queryParam("limit", 10000)
                .queryParam("offset", 0)
                .queryParam("dueLater", dueLater)
                .when()
                .get()
                .then().log().ifError()
                .extract().response();

        logger.info("GET " + endpoint.replace(gstin, maskId(gstin)) + " entityName=" + entityName
                + " dueInDays=" + dueInDays + " dueLater=" + dueLater + " -> HTTP " + response.getStatusCode());
        return response;
    }

    /**
     * Ledger service transactions and balance for a customer - the data behind the ledger's
     * "Usable ledger balance" tab. Authenticates with the ledger service client id and key.
     */
    public static Response fetchLedgerTransactions(String gstin, String appName) {
        String endpoint = ConfigUtil.resolveOrThrow("omsBaseUrl")
                + "ledger-service/api/internal/v1/fetch-ledger-transaction";
        Response response = RestAssured.given()
                .baseUri(endpoint)
                .config(redactedLogging())
                .header("client_id", ConfigUtil.resolveOrThrow("ledgerServiceClientId"))
                .header("Authorization", ConfigUtil.resolveOrThrow("ledgerServiceAuthorization"))
                .queryParam("gstin", gstin)
                .queryParam("appName", appName)
                .when()
                .get()
                .then().log().ifError()
                .extract().response();

        logger.info("GET " + endpoint + " gstin=" + maskId(gstin) + " appName=" + appName
                + " -> HTTP " + response.getStatusCode());
        return response;
    }

    /**
     * OMS credit info for a customer - the data behind the ledger's "Usable credit limit".
     * Authenticates with the OMS token ({@link Token#getOmsAuthorizationHeader()}): an OMS
     * service login when one is configured, else the environment's {@code omsAuthToken}.
     */
    public static Response fetchCreditInfoByPan(String pan, String gstin) {
        String endpoint = ConfigUtil.resolveOrThrow("omsBaseUrl") + "joms/api/external/v2/getCreditInfoByPan";
        Response response = RestAssured.given()
                .baseUri(endpoint)
                .config(redactedLogging())
                .header("accept", "application/json, text/plain, */*")
                .header("authorization", Token.getOmsAuthorizationHeader())
                .queryParam("pan", pan)
                .queryParam("gstin", gstin)
                .when()
                .get()
                .then().log().ifError()
                .extract().response();

        logger.info("GET " + endpoint + " pan=" + maskId(pan) + " gstin=" + maskId(gstin)
                + " -> HTTP " + response.getStatusCode());
        return response;
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
