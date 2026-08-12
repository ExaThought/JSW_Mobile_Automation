package com.qapitol.jsw.utils;

import com.qapitol.jsw.apitest.apis.impl.BaseApiImpl;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static io.restassured.RestAssured.*;

public class Token extends BaseApiImpl {

    /** Response keys tried in order when omsTokenResponseKey is not configured. */
    private static final String[] OMS_TOKEN_RESPONSE_KEYS =
            {"id_token", "token", "jwtToken", "access_token", "accessToken", "data.token"};

    /** Refresh this many seconds before the JWT actually expires. */
    private static final long OMS_TOKEN_REFRESH_SKEW_SECONDS = 300;

    private static volatile String cachedOmsToken;
    private static volatile long cachedOmsTokenExpiryEpochSeconds;

    public static String getCCPToken(){
        String email = UserConfig.getEmail();
        String password = Base64.getEncoder().encodeToString(UserConfig.getPassword().getBytes());
        return getCCPToken(email, password);
    }
    public static String getCCPToken(String email, String password){
        baseURI=ConfigUtil.getSecretValue("ccpPortalUrl");
        String storeKey="msme";

        String requestPayload = "{"
                + "\"email\":\"" + email + "\","
                + "\"phoneNumber\":null,"
                + "\"password\":\"" + password + "\","
                + "\"storeKey\":\"" + storeKey + "\""
                + "}";


        return given().log().all().body(requestPayload).header("content-type","application/json")
                .when().log().all().post(ConfigUtil.getSecretValue("ccpTokenUrl"))
                .then().log().all().statusCode(200).extract().jsonPath().getString("jwtToken");
    }

    // ---------------------------------------------------------------------
    // OMS / ERP
    // ---------------------------------------------------------------------

    /**
     * OMS JWT for the configured service account. Cached until shortly before it expires, so a
     * suite logs in once rather than per request.
     *
     * Every value is resolved through ConfigUtil - nothing is hardcoded:
     *   omsBaseUrl            base URL of the OMS service
     *   omsTokenUrl           login endpoint path, e.g. api/authenticate
     *   oms.user.email        service account username
     *   oms.user.password     service account password
     */
    public static String getOmsToken() {
        String cached = cachedOmsToken;
        if (cached != null && System.currentTimeMillis() / 1000
                < cachedOmsTokenExpiryEpochSeconds - OMS_TOKEN_REFRESH_SKEW_SECONDS) {
            return cached;
        }

        synchronized (Token.class) {
            cached = cachedOmsToken;
            if (cached != null && System.currentTimeMillis() / 1000
                    < cachedOmsTokenExpiryEpochSeconds - OMS_TOKEN_REFRESH_SKEW_SECONDS) {
                return cached;
            }

            String token = getOmsToken(
                    ConfigUtil.resolveOrThrow("oms.user.email"),
                    ConfigUtil.resolveOrThrow("oms.user.password"));

            cachedOmsToken = token;
            cachedOmsTokenExpiryEpochSeconds = readExpiry(token);
            return token;
        }
    }

    /**
     * Logs in to OMS with the supplied credentials and returns the raw JWT (no "Bearer " prefix).
     *
     * @throws IllegalStateException if the login call does not return 200, or if no token field
     *                               can be found in the response body.
     */
    public static String getOmsToken(String username, String password) {
        String omsBaseUrl = ConfigUtil.resolveOrThrow("omsBaseUrl");
        String omsTokenUrl = ConfigUtil.resolveOrThrow("omsTokenUrl");

        String requestPayload = new JSONObject()
                .put("username", username)
                .put("password", password)
                .put("rememberMe", true)
                .toString();

        // Per-request baseUri instead of the shared static baseURI, which other utils mutate.
        Response response = RestAssured.given()
                .baseUri(omsBaseUrl)
                .header("content-type", "application/json")
                .body(requestPayload)
                .when().log().uri()
                .post(omsTokenUrl)
                .then().log().ifError()
                .extract().response();

        if (response.getStatusCode() != 200) {
            throw new IllegalStateException("OMS login failed for '" + username + "' at "
                    + omsBaseUrl + omsTokenUrl + " - HTTP " + response.getStatusCode()
                    + " | body: " + response.asString());
        }

        String token = extractToken(response);
        if (token == null || token.isBlank()) {
            throw new IllegalStateException("OMS login succeeded but no token field was found in "
                    + "the response. Set 'omsTokenResponseKey' to the correct field. Body: "
                    + response.asString());
        }
        return token;
    }

    /**
     * Ready-to-use Authorization header value, i.e. {@code Bearer <jwt>}.
     *
     * Falls back to the statically configured {@code omsAuthToken} when the login properties are
     * not set, so existing runs keep working until the OMS login endpoint is configured.
     */
    public static String getOmsAuthorizationHeader() {
        if (isOmsLoginConfigured()) {
            return "Bearer " + getOmsToken();
        }
        return ConfigUtil.resolveOrThrow("omsAuthToken");
    }

    /**
     * ERP UI login URL - the token-in-query-string redirect the browser steps navigate to.
     *
     * Falls back to the statically configured {@code ErpLoginAuth} when the login properties are
     * not set.
     */
    public static String getErpLoginUrl() {
        if (!isOmsLoginConfigured()) {
            return ConfigUtil.resolveOrThrow("ErpLoginAuth");
        }

        String omsBaseUrl = ConfigUtil.resolveOrThrow("omsBaseUrl");
        if (!omsBaseUrl.endsWith("/")) {
            omsBaseUrl = omsBaseUrl + "/";
        }
        return omsBaseUrl + ConfigUtil.resolveOrThrow("omsErpRedirectPath") + getOmsToken();
    }

    /** True when both OMS login credentials and the login endpoint are configured. */
    public static boolean isOmsLoginConfigured() {
        return isSet("omsTokenUrl") && isSet("oms.user.email") && isSet("oms.user.password");
    }

    /** Clears the cached OMS token, forcing the next call to log in again. */
    public static void clearOmsTokenCache() {
        synchronized (Token.class) {
            cachedOmsToken = null;
            cachedOmsTokenExpiryEpochSeconds = 0;
        }
    }

    private static boolean isSet(String key) {
        String value = ConfigUtil.getSecretValue(key);
        return value != null && !value.isBlank();
    }

    private static String extractToken(Response response) {
        String configuredKey = ConfigUtil.getSecretValue("omsTokenResponseKey");
        if (configuredKey != null && !configuredKey.isBlank()) {
            return response.jsonPath().getString(configuredKey.trim());
        }
        for (String key : OMS_TOKEN_RESPONSE_KEYS) {
            String token = response.jsonPath().getString(key);
            if (token != null && !token.isBlank()) {
                return token;
            }
        }
        return null;
    }

    /**
     * Reads the {@code exp} claim so the cache knows when to refresh. Returns 0 when the claim
     * cannot be read, which makes the next call log in again rather than reuse a stale token.
     */
    private static long readExpiry(String jwt) {
        try {
            String[] parts = jwt.split("\\.");
            if (parts.length < 2) {
                return 0;
            }
            String payload = new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);
            JSONObject claims = new JSONObject(payload);
            return claims.has("exp") ? claims.getLong("exp") : 0;
        } catch (Exception e) {
            return 0;
        }
    }

}
