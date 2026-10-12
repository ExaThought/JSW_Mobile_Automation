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

    /** The MoEngage {@code user_role} derived from the profile's roles, cached per scenario. */
    private static final ThreadLocal<String> MOE_USER_ROLE = new ThreadLocal<>();

    /** The profile the MoEngage lookups read, fetched once per scenario. */
    private static final ThreadLocal<CustomerProfileResponse> MOE_PROFILE = new ThreadLocal<>();

    /**
     * customer-profile role to the {@code user_role} MoEngage events carry. Only the mappings that
     * have been observed are listed; a profile with none of them fails loudly in
     * {@link #moEngageUserRole()} so the missing one gets added here rather than guessed.
     */
    private static final java.util.Map<String, String> ROLE_TO_MOE_USER_ROLE =
            java.util.Map.of("PRIMARY_USER", "primary");

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
        MOE_USER_ROLE.remove();
        MOE_PROFILE.remove();
        SESSION_TOKEN.remove();
    }

    /**
     * Makes {@code accessToken} the identity for profile lookups until {@link #clearSessionToken()}
     * or the next scenario. Drops the cached flag, which belonged to whoever was signed in before.
     */
    public static void useSessionToken(String accessToken) {
        SESSION_TOKEN.set(accessToken);
        IS_TRANSACTING.remove();
        MOE_USER_ROLE.remove();
        MOE_PROFILE.remove();
    }

    /** Back to the configured user - call when the user whose token was captured signs out. */
    public static void clearSessionToken() {
        SESSION_TOKEN.remove();
        IS_TRANSACTING.remove();
        MOE_USER_ROLE.remove();
        MOE_PROFILE.remove();
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
     * The {@code user_role} MoEngage events should carry for the logged-in user, read from the
     * {@code roles} array of the customer-profile response (for example
     * {@code ["PRIMARY_USER", "ORDER_MATERIAL_MANAGEMENT"]} gives {@code "primary"}). Cached for
     * the scenario. Strict: throws if the API fails, has no {@code roles}, or none of the roles
     * has a mapping in {@code ROLE_TO_MOE_USER_ROLE}.
     */
    public static String moEngageUserRole() {
        String cached = MOE_USER_ROLE.get();
        if (cached != null) {
            return cached;
        }
        CustomerProfileResponse profile = moEngageProfile();
        // Every "roles" array anywhere in the response, not the first: the payload can hold an empty
        // one (for example on a nested object) ahead of the user's own.
        java.util.List<Object> roles = new java.util.ArrayList<>();
        collectRoles(profile.getData(), roles);
        if (roles.isEmpty()) {
            // Some accounts come back with roles=[] (qa_pdpplp_user on pre-prod, 5 Oct 2026) while the
            // app still reports them as primary; contactType is what says so.
            Object contactType = profile.findFirst("contactType");
            if (contactType != null && !String.valueOf(contactType).isBlank()) {
                String fallback = String.valueOf(contactType).trim().toLowerCase();
                log.info("customer-profile roles is empty; using contactType \"" + contactType
                        + "\" -> MoEngage user_role \"" + fallback + "\"");
                MOE_USER_ROLE.set(fallback);
                return fallback;
            }
            throw new IllegalStateException("The customer-profile response from " + ApiUtils.customerProfileEndpoint()
                    + " has no non-empty 'roles' array and no contactType | data: " + profile.getData());
        }
        for (Object role : roles) {
            String mapped = ROLE_TO_MOE_USER_ROLE.get(String.valueOf(role));
            if (mapped != null) {
                log.info("customer-profile roles " + roles + " -> MoEngage user_role \"" + mapped + "\"");
                MOE_USER_ROLE.set(mapped);
                return mapped;
            }
        }
        throw new IllegalStateException("None of the customer-profile roles " + roles
                + " has a MoEngage user_role mapping. Add one to ROLE_TO_MOE_USER_ROLE in CustomerProfileUtil.");
    }

    private static CustomerProfileResponse moEngageProfile() {
        CustomerProfileResponse profile = MOE_PROFILE.get();
        if (profile == null) {
            String sessionToken = SESSION_TOKEN.get();
            profile = sessionToken != null
                    ? ApiUtils.getCustomerProfile(sessionToken)
                    : ApiUtils.getCustomerProfile();
            MOE_PROFILE.set(profile);
        }
        return profile;
    }

    /**
     * A field of the customer-profile response as text, wherever it sits in {@code data}. Strict:
     * throws when the field is missing or blank, so an expectation is never silently skipped.
     */
    public static String profileValue(String key) {
        Object value = moEngageProfile().findFirst(key);
        if (value == null || String.valueOf(value).isBlank()) {
            throw new IllegalStateException("customer-profile response from " + ApiUtils.customerProfileEndpoint()
                    + " has no value for '" + key + "'");
        }
        return String.valueOf(value);
    }

    /** The MoEngage {@code user_type}: the profile's {@code contactType} in lower case ("Primary" gives "primary"). */
    public static String moEngageUserType() {
        return profileValue("contactType").trim().toLowerCase();
    }

    private static void collectRoles(Object node, java.util.List<Object> into) {
        if (node instanceof java.util.Map) {
            for (java.util.Map.Entry<?, ?> e : ((java.util.Map<?, ?>) node).entrySet()) {
                if ("roles".equals(e.getKey()) && e.getValue() instanceof java.util.List) {
                    into.addAll((java.util.List<?>) e.getValue());
                } else {
                    collectRoles(e.getValue(), into);
                }
            }
        } else if (node instanceof java.util.List) {
            for (Object item : (java.util.List<?>) node) {
                collectRoles(item, into);
            }
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
