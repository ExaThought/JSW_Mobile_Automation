package com.qapitol.jsw.utils;

import com.qapitol.jsw.apitest.apis.impl.BaseApiImpl;
import com.qapitol.jsw.config.EnvConfig;
import com.qapitol.sauron.common.configuration.Config;
import com.qapitol.sauron.logging.SauronLogger;
import com.qapitol.sauron.logging.SimpleLogger;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static io.restassured.RestAssured.*;

public class Token extends BaseApiImpl {

    /**
     * Static logger. The inherited {@code log} from BaseApiImpl is an instance field and so is
     * unreachable from the static diagnostics below.
     */
    private static final SimpleLogger LOG = SauronLogger.getLogger();

    /** Response keys tried in order when omsTokenResponseKey is not configured. */
    private static final String[] OMS_TOKEN_RESPONSE_KEYS =
            {"id_token", "token", "jwtToken", "access_token", "accessToken", "data.token"};

    /** Refresh this many seconds before the JWT actually expires. */
    private static final long OMS_TOKEN_REFRESH_SKEW_SECONDS = 300;
    private static final long CCP_TOKEN_REFRESH_SKEW_SECONDS = 300;

    private static volatile String cachedOmsToken;
    private static volatile long cachedOmsTokenExpiryEpochSeconds;

    /**
     * CCP tokens cached per email. Keyed by email rather than held in one field because
     * getCCPToken(email, password) is also used for the secondary user - a single shared slot
     * would hand a secondary-user token to a primary-user call.
     */
    private static final ConcurrentMap<String, CachedToken> CCP_TOKEN_CACHE = new ConcurrentHashMap<>();

    /** A token plus the epoch second at which it should be considered stale. */
    private static final class CachedToken {
        private final String token;
        private final long expiryEpochSeconds;

        private CachedToken(String token, long expiryEpochSeconds) {
            this.token = token;
            this.expiryEpochSeconds = expiryEpochSeconds;
        }

        private boolean isUsable(long skewSeconds) {
            // expiry 0 means the exp claim could not be read - treat as never reusable.
            return expiryEpochSeconds > 0
                    && (System.currentTimeMillis() / 1000) < (expiryEpochSeconds - skewSeconds);
        }
    }

    public static String getCCPToken(){
        String email = UserConfig.getEmail();
        String password = Base64.getEncoder().encodeToString(UserConfig.getPassword().getBytes());
        return getCCPToken(email, password);
    }

    /**
     * CCP JWT for the given user. Cached until shortly before the token's own {@code exp} claim,
     * so a scenario that needs the token several times logs in once.
     */
    public static String getCCPToken(String email, String password){
        CachedToken cached = CCP_TOKEN_CACHE.get(email);
        if (cached != null && cached.isUsable(CCP_TOKEN_REFRESH_SKEW_SECONDS)) {
            return cached.token;
        }

        String token = requestCCPToken(email, password);
        CCP_TOKEN_CACHE.put(email, new CachedToken(token, readExpiry(token)));
        return token;
    }

    /**
     * Drops the configured user's cached CCP token so the next call logs in again.
     *
     * <p>The cache trusts the token's exp claim, but the server can revoke a token long before
     * that - every cart API call in build 280 came back 401 with an unexpired cached token while
     * the UI login for the same account worked. Callers that get a 401 drop it and retry once.
     */
    public static void invalidateCCPToken() {
        CCP_TOKEN_CACHE.remove(UserConfig.getEmail());
    }

    /** Performs the actual login call, bypassing the cache. */
    private static String requestCCPToken(String email, String password){
        baseURI=ConfigUtil.getSecretValue("ccpPortalUrl");
        String storeKey="msme";

        String requestPayload = "{"
                + "\"email\":\"" + email + "\","
                + "\"phoneNumber\":null,"
                + "\"password\":\"" + password + "\","
                + "\"storeKey\":\"" + storeKey + "\""
                + "}";


        /* NOT log().all(). This is the login call: the request body carries the email and the
         * Base64 password, and the response body IS the JWT. Logging either printed a live
         * credential into every console, every run log and every Allure report attached to one.
         *
         * log().uri() names the endpoint, which is the part worth seeing, and
         * log().ifValidationFails() prints the response ONLY when the call did not return 200 -
         * a failed login has no token in it, so the one case where the body is worth reading is
         * also the one case where it is safe to read. See fingerprintOf() below for how a token
         * is identified in logs without being disclosed. */
        return given().body(requestPayload).header("content-type","application/json")
                .when().log().uri().post(ConfigUtil.getSecretValue("ccpTokenUrl"))
                .then().log().ifValidationFails().statusCode(200)
                .extract().jsonPath().getString("jwtToken");
    }

    /**
     * The configured deliberately-expired CCP token, checked to actually be expired.
     *
     * <p>The token-expiry scenarios assert that the app rejects a dead session. If the configured
     * token is still valid the app correctly does nothing, and every one of those scenarios fails
     * blaming the product - build #233 lost 6 that way, plus the re-login steps that only run after
     * the redirect they were waiting for. The token also silently ages back into validity whenever
     * someone refreshes it, so this has to be checked at the point of use rather than reviewed once.
     *
     * @throws IllegalStateException if the token's {@code exp} claim is in the future, naming the
     *                               date and the property to replace.
     */
    public static String getExpiredCcpToken() {
        String token = ConfigUtil.resolveOrThrow("ccpExpiredToken");
        logExpiredTokenDiagnostics(token);

        long now = System.currentTimeMillis() / 1000;
        long exp = readExpiry(token);
        long tokenExpireAt = readTokenExpireAt(token);

        /* Either claim being in the past counts as expired, because the two consumers read
         * DIFFERENT claims and disagree about this very token:
         *
         *   exp           - the standard numeric claim, what the API gateway enforces
         *   tokenExpireAt - a JSW-specific string claim, what the WEB APP enforces
         *
         * Verified against QA on 2026-08-25 with the committed token (exp 2026-09-23, i.e. future;
         * tokenExpireAt 2023/09/23, i.e. past): injecting it into localStorage and reloading made
         * the app DELETE the token and redirect to /login. So the UI scenarios work with this
         * token while an exp-only guard rejects it - the guard was blocking scenarios that pass.
         *
         * Only when BOTH are in the future is the token genuinely unusable for these scenarios.
         */
        boolean expiredByExp = exp > 0 && exp < now;
        boolean expiredByTokenExpireAt = tokenExpireAt > 0 && tokenExpireAt < now;

        if (!expiredByExp && !expiredByTokenExpireAt) {
            throw new IllegalStateException("ccpExpiredToken is NOT expired by either claim - exp is "
                    + (exp == 0 ? "absent" : Instant.ofEpochSecond(exp).toString())
                    + " and tokenExpireAt is "
                    + (tokenExpireAt == 0 ? "absent/unparseable" : Instant.ofEpochSecond(tokenExpireAt).toString())
                    + ", both in the future. The token-expiry scenarios assert that the app rejects a"
                    + " dead session, so with a live token the app correctly does nothing and every"
                    + " one of them fails. Replace CCP_EXPIRED_TOKEN (or ccpExpiredToken in "
                    + EnvConfig.propertiesFileFor(EnvConfig.current())
                    + ") with a genuinely expired JWT.");
        }

        /* One claim past and the other future is usable, but only for one surface - so say which,
         * rather than letting the other surface fail looking like a product bug. */
        if (expiredByTokenExpireAt && !expiredByExp) {
            LOG.info("ccpExpiredToken is expired by tokenExpireAt but its exp claim is still in the"
                    + " future (" + Instant.ofEpochSecond(exp) + "). The WEB APP will reject it, so"
                    + " the UI token-expiry scenarios work; an API called with it may still answer"
                    + " 200 rather than 401.");
        } else if (expiredByExp && !expiredByTokenExpireAt) {
            LOG.info("ccpExpiredToken is expired by exp but its tokenExpireAt claim is still in the"
                    + " future. APIs will reject it, but the web app may keep the session alive.");
        }
        return token;
    }

    /**
     * The {@code tokenExpireAt} claim as epoch seconds, or 0 when absent or unparseable.
     *
     * <p>A JSW-specific claim in {@code yyyy/MM/dd HH:mm:ss} form rather than an epoch, and it is
     * what the web app enforces - so it cannot be ignored in favour of the standard {@code exp}.
     * Parsed as UTC: the claim carries no zone, and treating it as local time would shift the
     * verdict by the agent's offset, which is how a token flips between "expired" and "valid"
     * depending on which machine runs the suite.
     */
    static long readTokenExpireAt(String jwt) {
        JSONObject claims = readClaims(jwt);
        if (!claims.has("tokenExpireAt")) {
            return 0;
        }
        try {
            return java.time.LocalDateTime
                    .parse(claims.getString("tokenExpireAt").trim(),
                           java.time.format.DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))
                    .toEpochSecond(java.time.ZoneOffset.UTC);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * True when a JWT's {@code exp} claim is in the past. A token whose claim cannot be read counts
     * as expired, because the server will reject it either way.
     */
    public static boolean isExpired(String jwt) {
        long exp = readExpiry(jwt);
        return exp <= 0 || exp < System.currentTimeMillis() / 1000;
    }

    /**
     * Drops cached CCP tokens, forcing the next call to log in again. Use when a scenario needs a
     * genuinely fresh token, or after changing the configured user mid-run.
     */
    public static void clearCcpTokenCache() {
        CCP_TOKEN_CACHE.clear();
    }

    /** Drops the cached token for one user only. */
    public static void clearCcpTokenCache(String email) {
        CCP_TOKEN_CACHE.remove(email);
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
     *
     * <p>Public only so CartRCV2ApiImp can show a token's expiry in its redacted request log.
     * It decodes without verifying the signature - never use it to decide whether to trust a token.
     */
    public static long readExpiry(String jwt) {
        JSONObject claims = readClaims(jwt);
        try {
            return claims.has("exp") ? claims.getLong("exp") : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    /** A JWT's payload claims, or an empty object when the token cannot be decoded. */
    static JSONObject readClaims(String jwt) {
        try {
            String[] parts = jwt.split("\\.");
            if (parts.length < 2) {
                return new JSONObject();
            }
            String payload = new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);
            return new JSONObject(payload);
        } catch (Exception e) {
            return new JSONObject();
        }
    }

    // =====================================================================================
    // DEBUG - identifies WHICH ccpExpiredToken a run actually used.
    //
    // Added because the value differs by environment in a way that is invisible in the report:
    // locally it is the committed default in global_<env>.properties, while on Jenkins the
    // CCP_EXPIRED_TOKEN credential binding overrides it. That exact split is what made the
    // omsAuthToken diagnosis wrong for a whole investigation - the committed default was valid
    // while the value Jenkins injected had expired two months earlier.
    //
    // The token itself is deliberately NOT logged. It is a live credential and run logs are
    // attached to Allure reports and mailed out. What is logged is enough to identify it - the
    // claims, plus a short SHA-256 prefix to compare two runs - and useless as a credential.
    // =====================================================================================

    private static void logExpiredTokenDiagnostics(String token) {
        JSONObject claims = readClaims(token);
        long exp = readExpiry(token);
        long nowSeconds = System.currentTimeMillis() / 1000;

        /* Runs BEFORE getExpiredCcpToken's expiry guard throws, which is the whole point: the
         * failing case is when you most need to see which token was used. The raw value is
         * printed under tokenTrace.showFullToken so this class and TokenTrace cannot disagree
         * about whether tokens are disclosed. */
        StringBuilder report = new StringBuilder("ccpExpiredToken in use:")
                .append("\n  source        : ").append(resolvedSourceOf("ccpExpiredToken"))
                .append("\n  fingerprint   : ").append(fingerprintOf(token))
                .append("\n  length        : ").append(token == null ? 0 : token.length()).append(" chars");

        if (claims.isEmpty()) {
            report.append("\n  claims        : COULD NOT DECODE - this is not a readable JWT");
        } else {
            report.append("\n  exp           : ").append(exp == 0 ? "absent" : exp + " (" + Instant.ofEpochSecond(exp) + ")");
            if (exp > 0) {
                long ageSeconds = nowSeconds - exp;
                report.append("\n  status        : ")
                        .append(ageSeconds > 0
                                ? "EXPIRED " + (ageSeconds / 86400) + " day(s) ago - usable for the token-expiry scenarios"
                                : "STILL VALID for " + (-ageSeconds / 86400) + " more day(s) - the app will NOT log the user out");
            }
            for (String claim : new String[]{"tokenExpireAt", "tokenCreatedAt", "userId", "sfCustomerId", "userType", "storeKey"}) {
                if (claims.has(claim)) {
                    report.append("\n  ").append(String.format("%-14s", claim)).append(": ").append(claims.get(claim));
                }
            }
        }

        LOG.info(report.toString());
        try {
            Allure.addAttachment("ccpExpiredToken diagnostics", report.toString());
        } catch (Exception e) {
            // Attaching is a convenience; never let it break the run it is diagnosing.
            LOG.info("Could not attach the token diagnostics to Allure: " + e.getMessage());
        }
    }

    /**
     * Which of the three sources {@code ConfigUtil} actually took the value from.
     *
     * <p>Mirrors {@code getSecretValue}'s precedence - system property, then OS environment
     * variable, then the committed default - so the label matches what really happened rather
     * than what the properties file appears to say.
     */
    private static String resolvedSourceOf(String key) {
        String raw = Config.getConfigProperty(key);
        if (raw == null) {
            return "property '" + key + "' is not defined at all";
        }
        if (!(raw.startsWith("${") && raw.endsWith("}"))) {
            return "literal value in " + EnvConfig.propertiesFileFor(EnvConfig.current());
        }
        String varName = raw.substring(2, raw.length() - 1).split(":", 2)[0];
        if (System.getProperty(varName) != null) {
            return "-D" + varName + " system property";
        }
        if (System.getenv(varName) != null) {
            return "OS environment variable " + varName + " (this is what Jenkins credential bindings set)";
        }
        return "committed default in " + EnvConfig.propertiesFileFor(EnvConfig.current())
                + " (" + varName + " is not set)";
    }

    /**
     * Short, non-reversible identifier so two runs' tokens can be compared without logging either.
     * Public only so CartRCV2ApiImp can name a credential in its redacted request log.
     */
    public static String fingerprintOf(String token) {
        if (token == null || token.isBlank()) {
            return "<empty>";
        }
        try {
            byte[] digest = java.security.MessageDigest.getInstance("SHA-256")
                    .digest(token.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                hex.append(String.format("%02x", digest[i]));
            }
            return "sha256:" + hex;
        } catch (Exception e) {
            return "<could not hash: " + e.getMessage() + ">";
        }
    }

}
