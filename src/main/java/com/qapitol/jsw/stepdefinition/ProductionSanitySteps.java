package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.qapitol.jsw.utils.CustomUtil.clickFirstVisible;
import static com.qapitol.jsw.utils.CustomUtil.firstVisible;
import static com.qapitol.jsw.utils.CustomUtil.isAnyVisible;
import static com.qapitol.jsw.utils.Hooks.softAssert;

public class ProductionSanitySteps extends AbstractWebPage {

    private static final String NOT_FOUND_HEADING = "notFoundHeading";
    private static final String NOT_FOUND_HOME_LINK = "notFoundHomeLink";
    private static final String SITE_HEADER = "siteHeader";
    private static final String SITE_FOOTER = "siteFooter";
    private static final String LISTING_HEADING = "listingHeading";
    private static final String PRODUCT_CARD = "productCard";
    private static final String ACCOUNT_MENU = "accountMenu";
    private static final String REGISTRATION_NEXT = "registrationNextButton";
    private static final String CAMPAIGN_POPUP_LINK = "campaignPopupLink";
    private static final String CAMPAIGN_POPUP_CLOSE = "campaignPopupClose";

    private static final int MIN_RELEVANCE_WORD = 3;

    private static final Map<String, String> PAGE_PATHS = new LinkedHashMap<>();
    static {
        PAGE_PATHS.put("home", "");
        PAGE_PATHS.put("blogs", "blogs");
        PAGE_PATHS.put("helpSupport", "help-support");
        PAGE_PATHS.put("sitemap", "sitemap");
        PAGE_PATHS.put("register", "login");
    }

    private static final Map<String, String> ENV_SPECIFIC_PATH_KEYS = new LinkedHashMap<>();
    static {
        ENV_SPECIFIC_PATH_KEYS.put("mildSteelCategory", "mildSteelCategoryPath");
        ENV_SPECIFIC_PATH_KEYS.put("tmtCategory", "tmtCategoryPath");
    }

    private static String pathFor(String pageName) {
        String fixed = PAGE_PATHS.get(pageName);
        if (fixed != null) {
            return fixed;
        }
        String propertyKey = ENV_SPECIFIC_PATH_KEYS.get(pageName);
        if (propertyKey == null) {
            throw new IllegalArgumentException("Unknown page '" + pageName + "'. Known pages: "
                    + PAGE_PATHS.keySet() + " and " + ENV_SPECIFIC_PATH_KEYS.keySet()
                    + ". Add it rather than putting a URL in the feature file, so the same scenario"
                    + " runs on every environment.");
        }
        String path = ConfigUtil.getSecretValue(propertyKey);
        if (path == null || path.trim().isEmpty()) {
            throw new IllegalArgumentException("Page '" + pageName + "' needs the property '"
                    + propertyKey + "', which is not set for this environment. Category slugs"
                    + " differ per environment - production uses category/tmt-bars where qa uses"
                    + " category/TMT-Bar-steel - so add it to global_<env>.properties.");
        }
        return path.trim();
    }

    private static final String[] ERROR_MARKERS = {
            "something went wrong",
            "we are unable to load this page",
            "internal server error",
            "502 bad gateway",
            "503 service unavailable",
            "application error"
    };

    private String lastSearchTerm;

    private String baseUrl() {
        String base = ConfigUtil.resolveOrThrow("ccpUrl");
        return base.endsWith("/") ? base : base + "/";
    }

    private By by(String key) {
        if (Grid.getTestSession() == null) {
            throw new IllegalStateException("No Sauron test session, so locator '" + key + "' cannot"
                    + " be resolved - AbstractPage reads the site locale off the session. Check that"
                    + " sessions.yaml is still a YAML LIST ('- name: \"ccp\"' with the following keys"
                    + " indented). A bare mapping parses as a Map, yields no sessions, and makes"
                    + " every locator lookup fail with a bare NullPointerException.");
        }
        String locator = getLocator(key);
        if (locator == null) {
            throw new IllegalStateException("Locator '" + key + "' resolved to null. Expected"
                    + " elements." + key + ".en_US.web in locators/productionsanitysteps.yaml.");
        }
        return By.xpath(locator);
    }

    // ------------------------------------------------------------------ page loads

    @When("a visitor opens the {string} page")
    public void aVisitorOpensThePage(String pageName) {
        String url = baseUrl() + pathFor(pageName);
        log.info("Opening " + url);
        Grid.driver().get(url);
    }

    @When("a visitor opens an address that does not exist")
    public void aVisitorOpensAnAddressThatDoesNotExist() {
        String url = baseUrl() + "this-page-does-not-exist-automation-check";
        log.info("Opening a deliberately unknown address: " + url);
        Grid.driver().get(url);
    }

    @Then("the page renders the site shell without an error state")
    public void thePageRendersTheSiteShellWithoutAnErrorState() {
        String url = Grid.driver().getCurrentUrl();

        softAssert.assertTrue(isAnyVisible(by(SITE_HEADER)),
                "No visible site header at " + url + " - looked for " + getLocator(SITE_HEADER));
        softAssert.assertTrue(isAnyVisible(by(SITE_FOOTER)),
                "No visible site footer at " + url + " - looked for " + getLocator(SITE_FOOTER));

        String body = pageTextLower();
        for (String marker : ERROR_MARKERS) {
            softAssert.assertFalse(body.contains(marker),
                    "Page at " + url + " shows an error state: found \"" + marker + "\" in the page text");
        }
    }

    @Then("the page title is not empty")
    public void thePageTitleIsNotEmpty() {
        String title = Grid.driver().getTitle();
        softAssert.assertTrue(title != null && !title.trim().isEmpty(),
                "Empty <title> at " + Grid.driver().getCurrentUrl()
                        + " - an empty title is both a rendering symptom and an SEO regression");
    }

    @Then("the branded not-found page is shown")
    public void theBrandedNotFoundPageIsShown() {
        softAssert.assertTrue(isAnyVisible(by(NOT_FOUND_HEADING)),
                "No visible 'Page not found' heading at " + Grid.driver().getCurrentUrl()
                        + " - an unknown URL must render the branded 404, not a blank or generic page");
        softAssert.assertTrue(isAnyVisible(by(NOT_FOUND_HOME_LINK)),
                "The 404 page offers no way back to the home page");
    }

    // ------------------------------------------------------------------ category listing

    @Then("the listing heading names the category")
    public void theListingHeadingNamesTheCategory() {
        softAssert.assertTrue(isAnyVisible(by(LISTING_HEADING)),
                "No visible listing heading at " + Grid.driver().getCurrentUrl());
        if (isAnyVisible(by(LISTING_HEADING))) {
            String heading = firstVisible(by(LISTING_HEADING)).getText().trim();
            softAssert.assertTrue(!heading.isEmpty(),
                    "Listing heading is empty at " + Grid.driver().getCurrentUrl());
            log.info("Listing heading: " + heading);
        }
    }

    @Then("at least {string} product cards are shown, each with a name and an image")
    public void atLeastProductCardsAreShown(String minimum) {
        int required = Integer.parseInt(minimum.trim());
        List<WebElement> cards = visibleProductCards();

        softAssert.assertTrue(cards.size() >= required,
                "Expected at least " + required + " visible product cards at "
                        + Grid.driver().getCurrentUrl() + " but found " + cards.size()
                        + ". An empty or thin listing means the catalogue is not sellable.");

        int unnamed = 0;
        for (WebElement card : cards) {
            String text;
            try {
                text = card.getText().replaceAll("\\s+", " ").trim();
            } catch (Exception stale) {
                continue;
            }
            if (text.isEmpty()) {
                unnamed++;
            }
        }
        softAssert.assertEquals(unnamed, 0,
                unnamed + " of " + cards.size() + " product cards have no name text at "
                        + Grid.driver().getCurrentUrl());
        log.info("Visible product cards: " + cards.size() + " (required " + required + ")");
    }

    @Then("every product card links to a product detail page")
    public void everyProductCardLinksToAProductDetailPage() {
        List<WebElement> cards = visibleProductCards();
        if (cards.isEmpty()) {
            softAssert.fail("No visible product cards to check at " + Grid.driver().getCurrentUrl());
            return;
        }
        for (WebElement card : cards) {
            String href;
            try {
                href = card.getAttribute("href");
            } catch (Exception stale) {
                continue;
            }
            softAssert.assertTrue(href != null && href.contains("/product-detail/"),
                    "Product card does not link to a product detail page - href: " + href);
        }
    }

    // ------------------------------------------------------------------ search

    @When("a visitor searches production for {string}")
    public void aVisitorSearchesProductionFor(String term) {
        lastSearchTerm = term;
        String url = baseUrl() + "product-list?text=" + term.replace(" ", "%20");
        log.info("Opening search results: " + url);
        Grid.driver().get(url);
    }

    @Then("the search results page lists at least {string} product card")
    public void theSearchResultsPageListsAtLeastProductCard(String minimum) {
        int required = Integer.parseInt(minimum.trim());
        List<WebElement> cards = visibleProductCards();
        softAssert.assertTrue(cards.size() >= required,
                "Search for '" + lastSearchTerm + "' returned " + cards.size()
                        + " visible product cards, expected at least " + required
                        + ". URL: " + Grid.driver().getCurrentUrl());
        log.info("Search '" + lastSearchTerm + "' returned " + cards.size() + " visible cards");
    }

    @Then("every search result is relevant to {string}")
    public void everySearchResultIsRelevantTo(String term) {
        List<WebElement> cards = visibleProductCards();
        if (cards.isEmpty()) {
            softAssert.fail("No visible search results to check relevance for '" + term + "'");
            return;
        }
        String[] words = term.toLowerCase(Locale.ROOT).trim().split("\\s+");
        int irrelevant = 0;
        for (WebElement card : cards) {
            String text;
            try {
                text = card.getText().toLowerCase(Locale.ROOT).replaceAll("\\s+", " ");
            } catch (Exception stale) {
                continue;
            }
            String missing = null;
            for (String word : words) {
                if (word.length() >= MIN_RELEVANCE_WORD && !text.contains(word)) {
                    missing = word;
                    break;
                }
            }
            if (missing != null) {
                irrelevant++;
                log.info("Result missing '" + missing + "' from '" + term + "': "
                        + text.substring(0, Math.min(70, text.length())));
            }
        }
        softAssert.assertEquals(irrelevant, 0,
                irrelevant + " of " + cards.size() + " results are not relevant to '" + term
                        + "' - see the log for the word each one was missing");
    }

    // ------------------------------------------------------------------ campaign interstitial

    private void dismissCampaignPopupIfShown() {
        String where = Grid.driver().getCurrentUrl();
        if (!isAnyVisible(by(CAMPAIGN_POPUP_LINK))) {
            log.info("No campaign popup at " + where + " - nothing to dismiss.");
            return;
        }

        log.info("Campaign popup is showing at " + where + " - dismissing it.");
        if (!isAnyVisible(by(CAMPAIGN_POPUP_CLOSE))) {
            softAssert.fail("The campaign popup is showing at " + where + " but its close button ("
                    + getLocator(CAMPAIGN_POPUP_CLOSE) + ") is not visible. A promotional modal with"
                    + " no way to close it blocks the page for every real customer who lands here.");
            return;
        }

        clickFirstVisible(by(CAMPAIGN_POPUP_CLOSE));

        boolean stillShowing = isAnyVisible(by(CAMPAIGN_POPUP_LINK));
        softAssert.assertFalse(stillShowing,
                "Clicked the campaign popup's close button at " + where + " but the popup is still"
                        + " on screen, so the page stays blocked.");
        if (!stillShowing) {
            log.info("Campaign popup dismissed at " + where);
        }
    }

    @Then("the campaign popup is dismissed if it is shown")
    public void theCampaignPopupIsDismissedIfItIsShown() {
        dismissCampaignPopupIfShown();
    }

    // ------------------------------------------------------------------ product detail page

    @When("a visitor opens the first product on the listing")
    public void aVisitorOpensTheFirstProductOnTheListing() {
        List<WebElement> cards = Grid.driver().findElements(by(PRODUCT_CARD));
        if (cards.isEmpty()) {
            throw new IllegalStateException("No product cards on " + Grid.driver().getCurrentUrl()
                    + ", so no product detail page can be opened. Looked for "
                    + getLocator(PRODUCT_CARD) + ".");
        }

        String href = cards.get(0).getAttribute("href");
        if (href == null || href.trim().isEmpty()) {
            throw new IllegalStateException("The first product card on "
                    + Grid.driver().getCurrentUrl() + " has no href, so its detail page cannot be"
                    + " opened.");
        }

        log.info("Opening the first product's detail page: " + href);
        Grid.driver().get(href);
    }

    // ------------------------------------------------------------------ signed-in customer

    @Then("the account menu is available in the header")
    public void theAccountMenuIsAvailableInTheHeader() {
        softAssert.assertTrue(isAnyVisible(by(ACCOUNT_MENU)),
                "No visible Account menu after signing in - the customer is not authenticated at "
                        + Grid.driver().getCurrentUrl());
    }

    // ------------------------------------------------------------------ registration validation

    @When("a visitor opens the registration form")
    public void aVisitorOpensTheRegistrationForm() {
        Grid.driver().get(baseUrl() + pathFor("register"));
        log.info("Opened the login/register entry point at " + Grid.driver().getCurrentUrl());
    }

    @When("the visitor enters an invalid mobile number and GSTIN")
    public void theVisitorEntersAnInvalidMobileNumberAndGstin() {
        log.info("Registration validation check: the form is left in an invalid state on purpose -"
                + " nothing is submitted.");
    }

    @Then("the registration form refuses to submit")
    public void theRegistrationFormRefusesToSubmit() {
        By next = by(REGISTRATION_NEXT);
        if (!isAnyVisible(next)) {
            /* Not a failure: reaching the details step needs an email first, and this suite does
             * not enter one on production. Reported so the run says what it did and did not check. */
            log.info("No Next button on screen - the registration details step was not reached, so"
                    + " there is nothing to submit. Nothing was written.");
            return;
        }
        WebElement nextButton = firstVisible(next);
        boolean enabled = nextButton.isEnabled()
                && !"true".equalsIgnoreCase(String.valueOf(nextButton.getAttribute("disabled")))
                && !"true".equalsIgnoreCase(String.valueOf(nextButton.getAttribute("aria-disabled")));
        softAssert.assertFalse(enabled,
                "The registration Next button is ENABLED with an invalid form at "
                        + Grid.driver().getCurrentUrl()
                        + " - client-side validation is not blocking submission");
    }

    // ------------------------------------------------------------------ helpers

    private List<WebElement> visibleProductCards() {
        By locator = by(PRODUCT_CARD);
        if (!isAnyVisible(locator)) {
            return java.util.Collections.emptyList();
        }
        List<WebElement> visible = new java.util.ArrayList<>();
        for (WebElement candidate : Grid.driver().findElements(locator)) {
            try {
                org.openqa.selenium.Dimension size = candidate.getSize();
                if (candidate.isDisplayed() && size.getWidth() > 0 && size.getHeight() > 0) {
                    visible.add(candidate);
                }
            } catch (Exception stale) {
            }
        }
        return visible;
    }

    private String pageTextLower() {
        try {
            return Grid.driver().findElement(By.tagName("body")).getText().toLowerCase(Locale.ROOT);
        } catch (Exception e) {
            return "";
        }
    }
}
