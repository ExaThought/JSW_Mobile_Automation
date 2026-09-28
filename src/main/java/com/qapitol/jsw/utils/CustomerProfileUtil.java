package com.qapitol.jsw.utils;

import com.qapitol.jsw.apitest.pojos.order.customerProfile.CustomerProfileResponse;
import io.qameta.allure.Allure;

import static com.qapitol.sauron.web.WebActionListener.log;

/**
 * Shared access to the customer-profile API ({@code xp-mi/public/v3/customer-profile}) for any
 * step definition class.
 *
 * Usage from any step definition:
 * <pre>
 *   import static com.qapitol.jsw.utils.CustomerProfileUtil.isTransactingCustomer;
 *   ...
 *   if (isTransactingCustomer()) { ... }
 * </pre>
 *
 * The flag is cached per thread and cleared by {@code Hooks.beforeScenario()}, so a scenario
 * makes at most one profile call no matter how many step classes read the flag, and parallel
 * {@code <test>} threads never share each other's customer.
 */
public final class CustomerProfileUtil {

    private static final ThreadLocal<Boolean> IS_TRANSACTING = new ThreadLocal<>();

    /**
     * The access_token of the user the BROWSER is signed in as, when a step captured one - e.g.
     * the jwtToken from the app's own otp/verify response. When set, profile lookups use it
     * instead of a REST login to auth/login as the configured user.
     */
    private static final ThreadLocal<String> SESSION_TOKEN = new ThreadLocal<>();

    private CustomerProfileUtil() {
        // utility class
    }

    /**
     * Clears the cached flag. Called from the Cucumber {@code @Before} hook so each scenario
     * re-reads the profile for its own logged-in user.
     */
    public static void reset() {
        IS_TRANSACTING.remove();
        SESSION_TOKEN.remove();
    }

    /**
     * Makes {@code accessToken} the identity for profile lookups until {@link #clearSessionToken()}
     * or the next scenario. Drops the cached flag, which belonged to whoever was signed in before.
     */
    public static void useSessionToken(String accessToken) {
        SESSION_TOKEN.set(accessToken);
        IS_TRANSACTING.remove();
    }

    /** Back to the configured user - call when the user whose token was captured signs out. */
    public static void clearSessionToken() {
        SESSION_TOKEN.remove();
        IS_TRANSACTING.remove();
    }

    public static boolean hasSessionToken() {
        return SESSION_TOKEN.get() != null;
    }

    /**
     * The cached flag, or a strict fetch when none is cached. Unlike {@link #isTransactingCustomer()}
     * it throws on failure rather than soft-failing and defaulting, so a caller with another way
     * to decide can fall back to it.
     */
    public static boolean cachedOrFetchIsTransactingFlag() {
        Boolean cached = IS_TRANSACTING.get();
        return cached != null ? cached : fetchIsTransactingFlag();
    }

    /**
     * Fetches {@code isTransacting} from the customer-profile API and caches it for the rest
     * of the scenario. Strict: propagates the failure if the API is unreachable, returns a
     * non-200, or omits the field.
     *
     * Use this when the API itself is what you are testing.
     */
    public static boolean fetchIsTransactingFlag() {
        String sessionToken = SESSION_TOKEN.get();
        boolean isTransacting = sessionToken != null
                ? ApiUtils.isTransacting(sessionToken)
                : ApiUtils.isTransacting();
        IS_TRANSACTING.set(isTransacting);

        log.info("isTransacting flag for the logged-in customer: " + isTransacting
                + (sessionToken != null ? " (browser session token)" : " (configured user)"));
        Allure.addAttachment("isTransacting", String.valueOf(isTransacting));
        return isTransacting;
    }

    /**
     * Returns the cached {@code isTransacting} flag, fetching it on first use.
     *
     * Deliberately non-fatal: this is safe to call from UI navigation steps shared by many
     * scenarios, so a middleware outage is reported as a soft failure rather than aborting
     * browser flows that do not depend on the flag. Defaults to {@code false} on error.
     */
    public static boolean isTransactingCustomer() {
        Boolean cached = IS_TRANSACTING.get();
        if (cached != null) {
            return cached;
        }

        try {
            return fetchIsTransactingFlag();
        } catch (Exception e) {
            IS_TRANSACTING.set(Boolean.FALSE);

            String message = "Could not read isTransacting from " + ApiUtils.customerProfileEndpoint()
                    + " - defaulting to false. Cause: " + e.getMessage();
            log.info(message);
            Allure.addAttachment("isTransacting lookup failed", message);
            if (Hooks.softAssert != null) {
                Hooks.softAssert.fail(message);
            }
            return false;
        }
    }

    /**
     * Full customer-profile payload, for steps that need more than the transacting flag.
     * Not cached - each call hits the API.
     */
    public static CustomerProfileResponse getCustomerProfile() {
        return ApiUtils.getCustomerProfile();
    }
}
