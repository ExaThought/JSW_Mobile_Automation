package com.qapitol.jsw.stepdefinition;

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

        // hoverOverElement resolves firstVisible(locator), scrolls that node to centre, and moves
        // the pointer with no click. Both parts matter: the header renders each nav item two to
        // four times and the first copy in document order measures 0x0 (moveToElement on a
        // zero-size box dispatches no pointerenter), and moveToElement on a node outside the
        // viewport throws Selenium's "move target out of bounds".
        //
        // The helper to avoid is moveToAction - it resolves the first match through findElement
        // AND follows its move with a click(), which would navigate to the category page instead
        // of opening the panel.
        hoverOverElement(target);
        log.info("Hovered over header navigation menu '" + menuItem + "'");
    }

    @Then("the mega-menu panel should be displayed with sub-categories")
    public void theMegaMenuPanelShouldBeDisplayedWithSubCategories() {
        if (categoryLinksBeforeHover < 0) {
            softAssert.fail("No hover was recorded before asserting the mega-menu."
                    + " The hover step must run first.");
            return;
        }

        // Detected by counting the category links the panel adds, rather than by matching a panel
        // class name. The panel's markup could not be captured (it needs a real pointer hover, and
        // synthetic mouse events do not open it), and a guessed Tailwind class would rot on the
        // next restyle exactly as the cart and footer locators did.
        int after = waitForCategoryLinkIncrease(categoryLinksBeforeHover);
        softAssert.assertTrue(after > categoryLinksBeforeHover,
                "Mega-menu did not open: visible category links stayed at " + categoryLinksBeforeHover
                        + " after hovering. If the panel IS opening on screen, its sub-category links"
                        + " do not use '/category/' hrefs - capture the panel markup and narrow"
                        + " ANY_CATEGORY_LINK accordingly.");
        log.info("Visible category links after hover: " + after);
    }

    /** Polls briefly for the panel's links to render; returns the final visible count. */
    private int waitForCategoryLinkIncrease(int baseline) {
        int latest = baseline;
        for (int attempt = 0; attempt < 10; attempt++) {
            latest = countVisible(ANY_CATEGORY_LINK);
            if (latest > baseline) {
                return latest;
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        return latest;
    }

    // ------------------- Search, location, cart -------------------

    @Then("the header search should be displayed in the header")
    public void theHeaderSearchShouldBeDisplayedInTheHeader() {
        softAssert.assertTrue(isAnyVisible(by(HEADER_SEARCH)),
                "Header search is not displayed in the header");
    }

    /**
     * The location control renders differently for each user state, so the expected markup is
     * chosen from the state the scenario established rather than from one locator: a guest gets
     * "Select location", a logged-in user gets "Deliver to".
     *
     * <p>Asserting only the variant that should be present is the point. The two are mutually
     * exclusive on QA, so an OR across both would pass even when the header shows the wrong one
     * for the current user.
     *
     * <p>Resolved with {@code isAnyVisible}, not {@code isElementVisibleJS}: the latter calls
     * findElement(), which returns the first match in document order, and that is the 0x0 copy
     * in the duplicated header (measured on QA: match[0] 0x0, match[1] 131x24). It would report
     * the control as hidden while it is plainly on screen.
     */
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

    /** Wait until the document has finished loading. */
    private void waitForPageLoad() {
        // Named: this wait is not caught, so a timeout goes straight to the report. Without a
        // message it reads "waiting for HomeHeaderSteps$$Lambda$NNNN/0x...@hash", which names
        // neither the page nor the condition.
        new WebDriverWait(Grid.driver(), Duration.ofSeconds(30))
                .withMessage("document.readyState never reached 'complete' within 30s at "
                        + Grid.driver().getCurrentUrl())
                .until(d -> "complete".equals(((JavascriptExecutor) d).executeScript("return document.readyState")));
    }
}
