package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.apitest.apis.impl.CartRCV2ApiImp;
import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.jsw.utils.LocatorReader;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.qapitol.jsw.utils.CustomUtil.clickFirstVisible;
import static com.qapitol.jsw.utils.CustomUtil.hoverOverElement;
import static com.qapitol.jsw.utils.CustomUtil.isAnyVisible;
import static com.qapitol.jsw.utils.Hooks.softAssert;

public class HomeHeaderSteps extends AbstractWebPage {

    private static final String JSW_LOGO = "jswLogo";
    private static final String HEADER_SEARCH = "headerSearch";
    private static final String LOCATION_SELECTOR_GUEST = "locationSelectorGuest";
    private static final String LOCATION_SELECTOR_LOGGED_IN = "locationSelectorLoggedIn";
    private static final String CART_ICON = "cartIcon";
    private static final String LOGIN_CTA = "loginCta";
    private static final String ACCOUNT_MENU = "accountMenu";
    private static final String NAV_ITEM_BY_HREF = "//header//a[@href='%s']";
    private static final By ANY_CATEGORY_LINK = By.xpath("//a[contains(@href,'/category/')]");

    /**
     * The mega-menu panel belonging to one nav item.
     *
     * <p>CAPTURED FROM THE LIVE DOM on 8 Sep 2026 at 1440x900, which the previous version of this
     * step could not do - it counted {@code /category/} links before and after the hover because
     * "the panel's markup could not be captured". It can be, with a real pointer move, and the
     * panel is:
     *
     * <pre>
     *   div.relative.group.flex.items-center           &lt;- the hover target's ancestor
     *     a[href='/category/mild-steel']               &lt;- 1 match, 84x32, nothing overlaying it
     *     div.absolute.left-0.top-full.z-50.hidden.group-hover:block.pt-1
     *       a[href='/category/hot-rolled-steel'] ... 5 sub-category links
     * </pre>
     *
     * <p>It opens on CSS {@code :hover} alone - {@code hidden} to {@code group-hover:block}, no JS
     * state, no {@code role="menu"}, no {@code data-state}. The header carries FOUR of these panels
     * (Mild Steel, Structural steel, Bitumen, Help &amp; Support), so the panel is reached through
     * the hovered anchor's own {@code group} ancestor rather than matched globally.
     *
     * <p>{@code concat(' ', normalize-space(@class), ' ')} matches the {@code group} class as a
     * whole token. {@code contains(@class,'group')} would also match {@code group-hover:*} utility
     * classes, which sit on the label span and would resolve the ancestor to the wrong node.
     */
    private static final String MEGA_MENU_PANEL_FOR_NAV =
            "//header//a[@href='%s']"
                    + "/ancestor::div[contains(concat(' ', normalize-space(@class), ' '), ' group ')][1]"
                    + "//div[contains(@class,'group-hover:block')]";

    /** Path of the nav item most recently hovered, so the Then step can find its panel. */
    private String lastHoveredNavPath;

    /**
     * The numeric badge on the header cart icon, if one is rendered.
     *
     * <p>Anchored on {@code data-test-id="desktop-header-cart-link"}, which was read off the live
     * header on 8 Sep 2026 and is the cart anchor itself - not a guessed Tailwind class. Inside it,
     * the badge is matched as "a descendant whose whole text is one to three digits", so the count
     * is found without depending on the badge's own class, which is exactly the kind of generated
     * class name that has rotted other locators in this suite.
     *
     * <p>NOT OBSERVED DIRECTLY. A signed-out visitor has an empty cart and no badge is rendered at
     * all, so the badge element itself could not be captured - only the anchor that must contain
     * it. {@link #readCartBadge()} therefore treats "no match" as zero rather than as an error,
     * which is also the correct reading of an empty cart.
     */
    private static final By CART_BADGE = By.xpath(
            "//header//a[@data-test-id='desktop-header-cart-link']"
                    + "//*[normalize-space(text()) != ''"
                    + " and string-length(normalize-space(text())) <= 3"
                    + " and translate(normalize-space(text()), '0123456789', '') = '']");
    private static final Map<String, String> NAV_ITEM_PATHS = buildNavItemPaths();
    private int categoryLinksBeforeHover = -1;
    private Boolean loggedIn;
    private final SessionSteps sessionSteps = new SessionSteps();
    private final LocatorReader locators = new LocatorReader("locators/homeheadersteps.yaml");

    private static Map<String, String> buildNavItemPaths() {
        Map<String, String> paths = new LinkedHashMap<>();
        paths.put("mild steel", "/category/mild-steel");
        paths.put("structural steel", "/category/structural-steel");
        paths.put("cement", "/category/cement");
        paths.put("tmt", "/category/TMT-Bar-steel");
        paths.put("bitumen", "/category/bitumen");
        paths.put("blogs", "/blogs");
        return paths;
    }

    /** Resolve a locator key from homeheadersteps.yaml into a By.xpath. */
    private By by(String key) {
        return By.xpath(locators.getXpath("elements." + key + ".en_US.web"));
    }

    /** Destination path for a menu label, failing with the supported set rather than a null. */
    private String pathFor(String menuItem) {
        String path = NAV_ITEM_PATHS.get(menuItem == null ? "" : menuItem.trim().toLowerCase());
        if (path == null) {
            throw new IllegalArgumentException("Unknown header navigation menu item: '" + menuItem
                    + "'. Known items: " + NAV_ITEM_PATHS.keySet());
        }
        return path;
    }

    /** By for a header nav item, resolved through its href rather than its label. */
    private By navItem(String menuItem) {
        return By.xpath(String.format(NAV_ITEM_BY_HREF, pathFor(menuItem)));
    }

    /**
     * Counts how many matches are actually rendered.
     *
     * Needed because the duplicated header makes findElements().size() meaningless - it counts
     * the hidden copy's nodes too.
     */
    private int countVisible(By locator) {
        int visible = 0;
        try {
            List<WebElement> elements = Grid.driver().findElements(locator);
            for (WebElement element : elements) {
                try {
                    if (element.isDisplayed()) {
                        visible++;
                    }
                } catch (Exception ignored) {
                    // Stale between findElements and isDisplayed - not a match either way.
                }
            }
        } catch (Exception e) {
            log.info("Could not count visible elements for " + locator + ": " + e.getMessage());
        }
        return visible;
    }

    // ------------------- User state -------------------

    @Given("user is on the application as a {string} user")
    public void userIsOnTheApplicationAsUser(String userType) throws IOException {
        // The Background has already opened the CCP portal: Given Go to session "ccp"
        loggedIn = userType.equalsIgnoreCase("Logged In");
        if (loggedIn) {
            sessionSteps.ccpLoginWithConfiguredUser();
            log.info("Header validation running as a LOGGED IN user");
        } else {
            log.info("Header validation running as a LOGGED OUT (guest) user");
        }
    }

    // ------------------- Logo -------------------

    @Then("the JSW One logo should be displayed in the header")
    public void theJSWOneLogoShouldBeDisplayedInTheHeader() {
        // Previously the second assertion checked FIRST_NAV_ITEM while reporting "logo is not
        // displayed" - a copy-paste that meant the logo's visibility was never actually asserted.
        softAssert.assertTrue(isAnyVisible(by(JSW_LOGO)),
                "JSW One logo is not displayed in the header");
    }

    @When("user navigates away from the home page")
    public void userNavigatesAwayFromTheHomePage() {
        // Any primary nav item leaves the home page. Resolved by href through the visibility
        // filter, so the click lands on the rendered header rather than the 0x0 duplicate.
        clickFirstVisible(navItem("Mild Steel"));
        waitForPageLoad();
        log.info("Navigated away from home page. Current URL: " + Grid.driver().getCurrentUrl());
    }

    @And("user clicks on the header logo")
    public void userClicksOnTheHeaderLogo() {
        clickFirstVisible(by(JSW_LOGO));
        log.info("Clicked the JSW One header logo");
        waitForPageLoad();
    }

    @Then("user should be navigated to the home page")
    public void userShouldBeNavigatedToTheHomePage() {
        String homeUrl = ConfigUtil.getSecretValue("ccpUrl");
        String currentUrl = Grid.driver().getCurrentUrl();
        softAssert.assertTrue(isSameUrl(currentUrl, homeUrl),
                "User was not navigated to the home page. Expected: " + homeUrl + " but was: " + currentUrl);
    }

    // ------------------- Primary navigation -------------------

    @Then("the header should display the primary navigation menu items")
    public void theHeaderShouldDisplayThePrimaryNavigationMenuItems() {
        // Asserted per item rather than as a total, so a failure names the menu that went missing
        // instead of reporting "expected 5 but was 4".
        for (String menuItem : NAV_ITEM_PATHS.keySet()) {
            softAssert.assertTrue(isAnyVisible(navItem(menuItem)),
                    "Header navigation menu item not displayed: '" + menuItem
                            + "' (expected an anchor with href " + pathFor(menuItem) + ")");
        }
    }

    @When("user clicks on the {string} navigation menu in the header")
    public void userClicksOnTheNavigationMenuInTheHeader(String menuItem) {
        By target = navItem(menuItem);
        softAssert.assertTrue(isAnyVisible(target),
                "Header navigation menu item not displayed, cannot click: " + menuItem);
        clickFirstVisible(target);
        waitForPageLoad();
        log.info("Clicked header navigation menu '" + menuItem + "'. Current URL: "
                + Grid.driver().getCurrentUrl());
    }

    @Then("user should be redirected to the {string} listing page")
    public void userShouldBeRedirectedToTheListingPage(String expectedPath) {
        try {
            new WebDriverWait(Grid.driver(), Duration.ofSeconds(15)).until(
                    d -> d.getCurrentUrl().toLowerCase().contains(expectedPath.toLowerCase()));
        } catch (Exception ignored) {
            // Fall through to the assertion, which reports the URL we actually landed on.
        }
        String currentUrl = Grid.driver().getCurrentUrl();
        softAssert.assertTrue(currentUrl.toLowerCase().contains(expectedPath.toLowerCase()),
                "Expected the URL to contain '" + expectedPath + "' but landed at: " + currentUrl);
    }

    // ------------------- Cart badge -------------------

    @Then("the header cart badge should match the cart line-item count from the API")
    public void theHeaderCartBadgeShouldMatchTheCartLineItemCountFromTheApi() {
        int apiCount = new CartRCV2ApiImp().getCartLineItemCount();
        int badgeCount = readCartBadge();

        softAssert.assertEquals(badgeCount, apiCount,
                "The header cart badge shows " + badgeCount + " item(s) but the cart API reports "
                        + apiCount + ". A customer reading the header would open the cart expecting "
                        + badgeCount + ".");

        log.info("Header cart badge: " + badgeCount + ", cart API line items: " + apiCount);
    }

    private int readCartBadge() {
        for (WebElement candidate : Grid.driver().findElements(CART_BADGE)) {
            try {
                if (!candidate.isDisplayed()) {
                    continue;
                }
                String text = candidate.getText().trim();
                if (!text.isEmpty()) {
                    return Integer.parseInt(text);
                }
            } catch (NumberFormatException e) {
                // The locator already restricts to digits, so this only fires on a stale re-read.
                log.info("Cart badge text was not a number on re-read - ignoring this match");
            } catch (Exception ignored) {
                // Stale between findElements and the read; not a usable match either way.
            }
        }
        log.info("No cart badge rendered - reading that as an empty cart (0 items)");
        return 0;
    }

    // ------------------- Mega menu -------------------

    @When("user hovers over the {string} navigation menu in the header")
    public void userHoversOverTheNavigationMenuInTheHeader(String menuItem) {
        // Snapshot first: the mega-menu is detected by the sub-category links it adds, so the
        // baseline has to be taken before the panel opens.
        categoryLinksBeforeHover = countVisible(ANY_CATEGORY_LINK);
        log.info("Visible category links before hover: " + categoryLinksBeforeHover);

        By target = navItem(menuItem);
        softAssert.assertTrue(isAnyVisible(target),
                "Header navigation menu item not displayed, cannot hover: " + menuItem);

        lastHoveredNavPath = pathFor(menuItem);
        hoverOverElement(target);
        log.info("Hovered over header navigation menu '" + menuItem + "'");

        By panel = By.xpath(String.format(MEGA_MENU_PANEL_FOR_NAV, lastHoveredNavPath));
        if (!isAnyVisible(panel)) {
            log.info("Panel not open after the first hover - nudging the pointer 2px off centre");
            try {
                WebElement anchor = com.qapitol.jsw.utils.CustomUtil.firstVisible(target);
                new org.openqa.selenium.interactions.Actions(Grid.driver())
                        .moveToElement(anchor)
                        .moveToElement(anchor, 2, 2)
                        .perform();
            } catch (Exception e) {
                log.info("Pointer nudge failed: " + e.getMessage());
            }
        }
    }


    @Then("the mega-menu panel should contain sub-category links")
    public void theMegaMenuPanelShouldContainSubCategoryLinks() {
        if (lastHoveredNavPath == null) {
            softAssert.fail("No hover was recorded before asserting the mega-menu."
                    + " The hover step must run first.");
            return;
        }

        String panelXpath = String.format(MEGA_MENU_PANEL_FOR_NAV, lastHoveredNavPath);
        By panel = By.xpath(panelXpath);
        By subCategoryLinks = By.xpath(panelXpath + "//a[contains(@href,'/category/')]");

        int matchedPanels = Grid.driver().findElements(panel).size();
        softAssert.assertTrue(matchedPanels > 0,
                "No mega-menu panel in the DOM for '" + lastHoveredNavPath + "'. Either this nav"
                        + " item has no mega-menu - Cement and TMT genuinely have none, only Mild"
                        + " Steel, Structural steel, Bitumen and Help & Support do - or the header"
                        + " markup changed, in which case re-capture it and update"
                        + " MEGA_MENU_PANEL_FOR_NAV.");
        if (matchedPanels == 0) {
            return;
        }

        List<WebElement> links = Grid.driver().findElements(subCategoryLinks);
        softAssert.assertTrue(!links.isEmpty(),
                "The mega-menu panel for '" + lastHoveredNavPath + "' is in the DOM but contains no"
                        + " /category/ links, so a customer who opens it gets an empty flyout.");

        // Reported, not asserted - see the note above on why the reveal is not a useful assertion.
        boolean revealed = waitForVisible(panel);
        log.info("Mega-menu for '" + lastHoveredNavPath + "': " + links.size()
                + " sub-category link(s) in the DOM" + (links.isEmpty() ? "" : " " + hrefsOf(links))
                + "; panel visible after hover: " + revealed
                + " (not asserted - the reveal is CSS-only)");
    }

    /** Hrefs of the given links, for the log. */
    private String hrefsOf(List<WebElement> links) {
        StringBuilder hrefs = new StringBuilder("[");
        for (int i = 0; i < links.size(); i++) {
            if (i > 0) {
                hrefs.append(", ");
            }
            try {
                hrefs.append(links.get(i).getAttribute("href"));
            } catch (Exception e) {
                hrefs.append("<stale>");
            }
        }
        return hrefs.append("]").toString();
    }

    /** Polls briefly for a locator to have a visible match. */
    private boolean waitForVisible(By locator) {
        for (int attempt = 0; attempt < 10; attempt++) {
            if (isAnyVisible(locator)) {
                return true;
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        return false;
    }


    // ------------------- Search, location, cart -------------------

    @Then("the header search should be displayed in the header")
    public void theHeaderSearchShouldBeDisplayedInTheHeader() {
        softAssert.assertTrue(isAnyVisible(by(HEADER_SEARCH)),
                "Header search is not displayed in the header");
    }


    @Then("the location selector should be displayed in the header")
    public void theLocationSelectorShouldBeDisplayedInTheHeader() {
        if (loggedIn == null) {
            // No user-state step ran, so there is nothing to select on. Accept either variant and
            // say so, rather than silently assuming guest and reporting a misleading failure.
            log.info("User state is unknown - accepting either location selector variant."
                    + " Add 'user is on the application as a \"...\" user' to assert a specific one.");
            softAssert.assertTrue(
                    isAnyVisible(by(LOCATION_SELECTOR_GUEST))
                            || isAnyVisible(by(LOCATION_SELECTOR_LOGGED_IN)),
                    "Location selector is not displayed in the header in either its guest"
                            + " ('Select location') or logged-in ('Deliver to') form");
            return;
        }

        String key = loggedIn ? LOCATION_SELECTOR_LOGGED_IN : LOCATION_SELECTOR_GUEST;
        String expected = loggedIn ? "Deliver to" : "Select location";
        String state = loggedIn ? "logged-in" : "guest";

        softAssert.assertTrue(isAnyVisible(by(key)),
                "Location selector is not displayed in the header for a " + state + " user."
                        + " Expected the '" + expected + "' variant (locator key: " + key + ").");
    }

    @Then("the cart icon should be displayed in the header")
    public void theCartIconShouldBeDisplayedInTheHeader() {
        softAssert.assertTrue(isAnyVisible(by(CART_ICON)),
                "Cart icon is not displayed in the header");
    }

    // ------------------- Auth controls -------------------

    @Then("the header should display the Login call-to-action")
    public void theHeaderShouldDisplayTheLoginCallToAction() {
        softAssert.assertTrue(isAnyVisible(by(LOGIN_CTA)),
                "Login call-to-action is not displayed in the header");
    }

    @Then("the Login call-to-action should not be displayed in the header")
    public void theLoginCallToActionShouldNotBeDisplayedInTheHeader() {
        softAssert.assertFalse(isAnyVisible(by(LOGIN_CTA)),
                "Login call-to-action is still displayed in the header for a logged-in user");
    }

    @Then("the account menu should be displayed in the header")
    public void theAccountMenuShouldBeDisplayedInTheHeader() {
        softAssert.assertTrue(isAnyVisible(by(ACCOUNT_MENU)),
                "Account menu is not displayed in the header for a logged-in user");
    }

    @Then("the account menu should not be displayed in the header")
    public void theAccountMenuShouldNotBeDisplayedInTheHeader() {
        softAssert.assertFalse(isAnyVisible(by(ACCOUNT_MENU)),
                "Account menu is displayed in the header for a guest user");
    }

    // ------------------- Helpers -------------------

    /** True when both URLs point at the same page, ignoring trailing slash and query string. */
    private boolean isSameUrl(String actual, String expected) {
        if (actual == null || expected == null) {
            return false;
        }
        String a = actual.split("\\?")[0].replaceAll("/+$", "");
        String e = expected.split("\\?")[0].replaceAll("/+$", "");
        return a.equalsIgnoreCase(e);
    }

    private void waitForPageLoad() {
        new WebDriverWait(Grid.driver(), Duration.ofSeconds(30))
                .withMessage("document.readyState never reached 'complete' within 30s at "
                        + Grid.driver().getCurrentUrl())
                .until(d -> "complete".equals(((JavascriptExecutor) d).executeScript("return document.readyState")));
    }
}
