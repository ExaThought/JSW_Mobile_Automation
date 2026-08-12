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

    private CustomerProfileUtil() {
        // utility class
    }

    /**
     * Clears the cached flag. Called from the Cucumber {@code @Before} hook so each scenario
     * re-reads the profile for its own logged-in user.
     */
    public static void reset() {
        IS_TRANSACTING.remove();
    }

    /**
     * Fetches {@code isTransacting} from the customer-profile API and caches it for the rest
     * of the scenario. Strict: propagates the failure if the API is unreachable, returns a
     * non-200, or omits the field.
     *
     * Use this when the API itself is what you are testing.
     */
    public static boolean fetchIsTransactingFlag() {
        boolean isTransacting = ApiUtils.isTransacting();
        IS_TRANSACTING.set(isTransacting);

        log.info("isTransacting flag for the logged-in customer: " + isTransacting);
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
