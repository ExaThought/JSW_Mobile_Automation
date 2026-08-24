package com.qapitol.jsw.utils;

import io.qameta.allure.Allure;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static com.qapitol.sauron.web.WebActionListener.log;

/**
 * Pre-flight checks on the relationship between test data and configuration.
 *
 * These catch setup mistakes at the point they are made, rather than letting them surface much
 * later as a confusing UI failure. The motivating case: an SLC cart created via API for one
 * customerId while the browser logs in as a different customer - the cart looks empty and the
 * failure appears three steps later as "element not found" on a cashback locator.
 */
public final class DataContractValidator {

    /** Set to false in properties to downgrade the identity mismatch to a warning. */
    private static final String FAIL_ON_MISMATCH_KEY = "failOnDataContractMismatch";

    private DataContractValidator() {
        // utility class
    }

    /**
     * Verifies the customer an API request is being built for is the same customer the browser
     * will log in as. Any difference means the UI half of the scenario looks at a different
     * customer's data.
     *
     * @param apiCustomerId the customerId the request will carry (from the test-data yaml).
     * @throws IllegalStateException when they differ and failOnDataContractMismatch is not false.
     */
    public static void assertApiCustomerMatchesLoggedInUser(String apiCustomerId) {
        if (apiCustomerId == null || apiCustomerId.trim().isEmpty()) {
            log.info("No API customerId supplied - skipping data contract check");
            return;
        }

        String loggedInUserId;
        String loggedInEmail;
        try {
            loggedInEmail = UserConfig.getEmail();
            loggedInUserId = ApiUtils.getUserId(Token.getCCPToken());
        } catch (Exception e) {
            // Never block a run because the check itself could not be performed.
            log.info("Could not run the data contract check: " + e.getMessage());
            return;
        }

        if (apiCustomerId.equalsIgnoreCase(loggedInUserId)) {
            log.info("Data contract OK - API customerId matches logged-in user '" + loggedInEmail + "'");
            return;
        }

        String message = "DATA CONTRACT MISMATCH."
                + " The API request is being created for customerId '" + apiCustomerId + "'"
                + " (from the test-data yaml) but the browser logs in as '" + loggedInEmail + "'"
                + " whose userId is '" + loggedInUserId + "'."
                + " The cart will be created for one customer and opened as another, so the UI"
                + " will show an empty cart."
                + " Fix by aligning CustomerId in data/<env>/testdata/ with the <group>.user.email"
                + " for this test group.";

        log.info(message);
        Allure.addAttachment("Data contract mismatch", message);

        if (failOnMismatch()) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * Logs how long a JWT has left, and reports it as expired when it is.
     *
     * @param tokenLabel  name used in the message, e.g. "omsAuthToken".
     * @param rawToken    the JWT, with or without a leading "Bearer ".
     * @param failIfStale when true, an expired token throws instead of warning.
     * @return true when the token is still valid (or could not be parsed), false when expired.
     */
    public static boolean checkTokenFreshness(String tokenLabel, String rawToken, boolean failIfStale) {
        if (rawToken == null || rawToken.trim().isEmpty()) {
            log.info("Token '" + tokenLabel + "' is not configured - skipping freshness check");
            return true;
        }

        String jwt = rawToken.trim();
        if (jwt.toLowerCase().startsWith("bearer ")) {
            jwt = jwt.substring("bearer ".length()).trim();
        }

        Long expiryEpochSeconds = readExpiry(jwt);
        if (expiryEpochSeconds == null) {
            log.info("Could not read the exp claim of '" + tokenLabel + "' - skipping freshness check");
            return true;
        }

        long nowEpochSeconds = System.currentTimeMillis() / 1000;
        long secondsRemaining = expiryEpochSeconds - nowEpochSeconds;
        String expiryDate = java.time.Instant.ofEpochSecond(expiryEpochSeconds).toString();

        if (secondsRemaining > 0) {
            log.info("Token '" + tokenLabel + "' valid for " + (secondsRemaining / 86400)
                    + " more day(s), expires " + expiryDate);
            return true;
        }

        String message = "Token '" + tokenLabel + "' EXPIRED on " + expiryDate
                + ". Refresh it, or supply a fresh value via its environment variable"
                + " (e.g. OMS_TOKEN / OMS_LOGIN). Scenarios that depend on it will fail.";
        System.out.println("!!! " + message);
        log.info(message);

        if (failIfStale) {
            throw new IllegalStateException(message);
        }
        return false;
    }

    private static boolean failOnMismatch() {
        String configured = ConfigUtil.getSecretValue(FAIL_ON_MISMATCH_KEY);
        // Default: fail. Only an explicit "false" downgrades it to a warning.
        return configured == null || !"false".equalsIgnoreCase(configured.trim());
    }

    /** Reads the exp claim, or null when the token is not a readable JWT. */
    private static Long readExpiry(String jwt) {
        try {
            String[] parts = jwt.split("\\.");
            if (parts.length < 2) {
                return null;
            }
            String payload = new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);
            org.json.JSONObject claims = new org.json.JSONObject(payload);
            return claims.has("exp") ? claims.getLong("exp") : null;
        } catch (Exception e) {
            return null;
        }
    }
}
