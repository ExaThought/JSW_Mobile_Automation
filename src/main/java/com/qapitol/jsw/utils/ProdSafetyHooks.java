package com.qapitol.jsw.utils;

import com.qapitol.jsw.config.EnvConfig;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.util.Collection;

/**
 * Refuses to run a scenario against PRODUCTION unless it is explicitly marked {@code @prodSafe}.
 *
 * <h2>Why a hook and not just a tag filter</h2>
 * The production suite already selects by {@code @productionSanity}, but a tag filter is only as
 * good as the command that carries it. Every one of these bypasses it:
 * <ul>
 *   <li>{@code -Dcucumber.filter.tags=...} on the Maven line, overriding the suite's own filter</li>
 *   <li>a hand-edited {@code <parameter name="cucumber.filter.tags">} in any suite XML</li>
 *   <li>running {@code testng_bdd.xml} with {@code -Denv=prod}</li>
 *   <li>an IDE "run this scenario" click</li>
 * </ul>
 * This hook sits INSIDE the scenario, so none of those reach the browser. It is the last barrier
 * before a test touches a real customer's data.
 *
 * <h2>Deny by default - why the 275 scenarios in this repo need no tagging</h2>
 * The rule is inverted on purpose: a scenario is unsafe for production unless it says otherwise.
 * Tagging the ~235 order-creating, cart-writing and account-creating scenarios as UNSAFE would
 * mean every new scenario is production-eligible from the moment it is written, and one forgotten
 * tag places a real, invoiced order. With deny-by-default a new scenario is inert on production
 * until somebody deliberately marks it {@code @prodSafe} - and that decision shows up in review.
 *
 * <h2>What qualifies as prodSafe</h2>
 * READ-ONLY. It may browse, search, sign in and read pages. It must not create, modify or delete
 * anything a customer or the business can see: no orders, no carts, no addresses, no bank details,
 * no registrations, no password resets, no ERP or DB writes.
 *
 * <h2>Failure, not skip</h2>
 * A blocked scenario FAILS rather than skipping. A skip on production reads as "nothing to do" and
 * disappears into a green report; a failure names the scenario and the missing tag, which is the
 * only outcome that gets noticed and fixed.
 */
public class ProdSafetyHooks {

    /** Scenarios carrying this tag are permitted to run against production. */
    public static final String PROD_SAFE_TAG = "@prodSafe";

    /** The environment this guard applies to. */
    private static final String PRODUCTION = "prod";

    /**
     * Runs before anything else in the scenario.
     *
     * <p>{@code order = 0} because Cucumber runs lower orders first and the default is 10000: this
     * has to fire before {@link Hooks#beforeScenario()} opens a session or resets state, otherwise
     * a browser is already pointed at production by the time the guard speaks.
     */
    @Before(order = 0)
    public void refuseUnsafeScenarioOnProduction(Scenario scenario) {
        if (!PRODUCTION.equalsIgnoreCase(EnvConfig.current())) {
            return;
        }

        Collection<String> tags = scenario.getSourceTagNames();
        if (tags != null && tags.contains(PROD_SAFE_TAG)) {
            return;
        }

        throw new IllegalStateException(
                "BLOCKED ON PRODUCTION: '" + scenario.getName() + "' is not tagged "
                        + PROD_SAFE_TAG + ".\n"
                        + "  Scenario tags : " + (tags == null || tags.isEmpty() ? "(none)" : tags) + "\n"
                        + "  Feature       : " + scenario.getUri() + "\n"
                        + "\n"
                        + "  Production runs only scenarios explicitly marked " + PROD_SAFE_TAG + ".\n"
                        + "  Add that tag ONLY if the scenario is strictly read-only - it must not\n"
                        + "  create, modify or delete an order, cart, address, bank detail, customer\n"
                        + "  account, password reset, ERP record or database row.\n"
                        + "  If it writes anything, it does not belong on production at all.");
    }
}
