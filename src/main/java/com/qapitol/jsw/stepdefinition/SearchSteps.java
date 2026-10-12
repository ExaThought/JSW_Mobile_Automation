package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.LocatorReader;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.qapitol.jsw.utils.CustomUtil.*;
import static com.qapitol.jsw.utils.Hooks.softAssert;

public class SearchSteps extends AbstractWebPage {

    private static final String SEARCH_INPUT = "searchInput";
    private static final String SEARCH_TRIGGER = "searchTrigger";
    private static final String SEARCH_MODAL_INPUT = "searchModalInput";
    private static final String SEARCH_SUGGESTIONS = "searchSuggestions";
    private static final String PRODUCT_CARD = "productCard";
    private static final String PLP_RESULT_COUNT = "plpResultCount";
    private static final String PLP_NO_RESULTS_MESSAGE = "plpNoResultsMessage";
    private static final String PLP_ERROR_STATE = "plpErrorState";

    private static final int TIMEOUT_SECONDS = 15;

    private final LocatorReader locatorReader = new LocatorReader("locators/searchsteps.yaml");

    /**
     * Built per call. Cucumber instantiates glue classes up front, so a WebDriverWait held in a
     * field calls Grid.driver() before the scenario has a session.
     */
    private WebDriverWait waitFor() {
        return new WebDriverWait(Grid.driver(), Duration.ofSeconds(TIMEOUT_SECONDS));
    }

    private By by(String key) {
        return By.xpath(locatorReader.getXpath("elements." + key + ".en_US.web"));
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    /** Visible elements for a locator, never throwing - an empty list means "none rendered". */
    private List<WebElement> visibleElements(By locator) {
        List<WebElement> visible = new ArrayList<>();
        try {
            for (WebElement element : Grid.driver().findElements(locator)) {
                try {
                    if (element.isDisplayed()) {
                        visible.add(element);
                    }
                } catch (Exception ignored) {
                    // stale between find and isDisplayed - not a match either way
                }
            }
        } catch (Exception e) {
            log.info("Could not read elements for " + locator + ": " + e.getMessage());
        }
        return visible;
    }

    /** Trimmed visible text for a locator, or null when it does not resolve. */
    private String safeText(String key) {
        List<WebElement> found = visibleElements(by(key));
        if (found.isEmpty()) {
            return null;
        }
        try {
            return found.get(0).getText().trim();
        } catch (Exception e) {
            return null;
        }
    }

    /** Polls until at least one element is visible, so a slow render is not read as "none". */
    private boolean waitForAnyVisible(By locator) {
        for (int attempt = 0; attempt < TIMEOUT_SECONDS * 2; attempt++) {
            if (!visibleElements(locator).isEmpty()) {
                return true;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return false;
    }

    // ---------------- SEARCH BAR ----------------

    @Then("user should see search bar on home page for web")
    public void verify_search_bar_visibility_for_web() {
        softAssert.assertTrue(waitForAnyVisible(by(SEARCH_INPUT)),
                "Search control is not displayed in the header");
    }

    // ---------------- ENTER KEYWORD ----------------

    @When("user enters {string} in search bar for web")
    public void enter_search_keyword_for_web(String keyword) {
        closeBlockingDialogsWithin(10);
        log.info("Entering search keyword: " + keyword);
        clickButton(by(SEARCH_TRIGGER));
        By modalInput = by(SEARCH_MODAL_INPUT);
        clickButton(modalInput);
        enterText(modalInput, keyword);
    }

    /** Opens the modal, types the keyword and submits, landing on /product-list?text=... */
    @When("user searches for {string} from the header for web")
    public void search_and_submit_for_web(String keyword) {
        // No dialog wait here: enter_search_keyword_for_web waits for it right before its click,
        // and a second wait costs the full 10s whenever the dialog never opens.
        enter_search_keyword_for_web(keyword);
        By modalInput = by(SEARCH_MODAL_INPUT);
        waitFor().until(ExpectedConditions.elementToBeClickable(modalInput)).sendKeys(Keys.ENTER);
        log.info("Submitted search for '" + keyword + "'. URL: " + Grid.driver().getCurrentUrl());
    }

    // ---------------- SUGGESTIONS ----------------

    /** Visible suggestion texts inside the search modal. */
    private List<String> suggestionTexts() {
        List<String> texts = new ArrayList<>();
        for (WebElement element : visibleElements(by(SEARCH_SUGGESTIONS))) {
            try {
                String text = element.getText();
                if (!isBlank(text)) {
                    texts.add(text.trim());
                }
            } catch (Exception ignored) {
                // element went stale as the suggestion list re-rendered
            }
        }
        return texts;
    }

    @Then("search results should be displayed for {string} for web")
    public void verify_search_results_for_web(String keyword) {
        waitForAnyVisible(by(SEARCH_SUGGESTIONS));
        List<String> suggestions = suggestionTexts();
        log.info("Suggestions for '" + keyword + "': " + suggestions);

        if (suggestions.isEmpty()) {
            softAssert.fail("No search suggestions were displayed for '" + keyword + "'");
            return;
        }
        for (String suggestion : suggestions) {
            softAssert.assertTrue(
                    suggestion.toLowerCase(Locale.ROOT).contains(keyword.toLowerCase(Locale.ROOT)),
                    "Search suggestion '" + suggestion + "' is not relevant to the keyword '"
                            + keyword + "'");
        }
    }

    @Then("no search suggestions should be displayed for {string} for web")
    public void verify_no_search_results_for_web(String keyword) {
        List<String> suggestions = suggestionTexts();
        softAssert.assertTrue(suggestions.isEmpty(),
                "Expected no suggestions for the non-matching keyword '" + keyword
                        + "' but got: " + suggestions);
    }

    @Then("auto suggestions should be displayed for web")
    public void verify_auto_suggestions_for_web() {
        softAssert.assertTrue(waitForAnyVisible(by(SEARCH_SUGGESTIONS)),
                "Auto-suggestion list is not displayed");
    }

    @Then("auto suggestion list should not be empty for web")
    public void verify_auto_suggestions_not_empty_for_web() {
        waitForAnyVisible(by(SEARCH_SUGGESTIONS));
        List<String> suggestions = suggestionTexts();
        softAssert.assertFalse(suggestions.isEmpty(),
                "Auto-suggestion list is empty");
    }

    // ---------------- KEYWORD RETENTION ----------------

    @Then("search keyword {string} should be retained in search bar for web")
    public void verify_keyword_retention_for_web(String expectedKeyword) {
        By modalInput = by(SEARCH_MODAL_INPUT);
        List<WebElement> inputs = visibleElements(modalInput);
        if (inputs.isEmpty()) {
            softAssert.fail("Search input is not displayed, so keyword retention cannot be checked");
            return;
        }
        String actual = inputs.get(0).getAttribute("value");
        softAssert.assertEquals(actual, expectedKeyword,
                "Search keyword was not retained in the search bar");
    }

    // ---------------- SUGGESTION CARDS ----------------

    @Then("product cards should be visible for web")
    public void verify_product_cards_visible_for_web() {
        softAssert.assertTrue(waitForAnyVisible(by(SEARCH_SUGGESTIONS)),
                "No product cards are visible in the search results");
    }

    @Then("each product card should have name, image for web")
    public void verify_product_card_details_for_web() {
        waitForAnyVisible(by(SEARCH_SUGGESTIONS));
        List<WebElement> cards = visibleElements(by(SEARCH_SUGGESTIONS));
        if (cards.isEmpty()) {
            softAssert.fail("No search result cards were displayed, so their contents cannot be checked");
            return;
        }
        int index = 0;
        for (WebElement card : cards) {
            index++;
            String name;
            try {
                name = card.getText();
            } catch (Exception e) {
                name = null;
            }
            softAssert.assertFalse(isBlank(name), "Search result card " + index + " has no name");
            softAssert.assertFalse(card.findElements(By.xpath(".//img")).isEmpty(),
                    "Search result card " + index + " ('" + name + "') has no image");
        }
    }

    // ---------------- RESULTS PAGE ----------------

    @Then("the search results page should be displayed for {string} for web")
    public void verify_results_page_for_web(String keyword) {
        String url = Grid.driver().getCurrentUrl();
        softAssert.assertTrue(url.contains("/product-list"),
                "Expected the search results page but landed at: " + url);
        softAssert.assertTrue(url.toLowerCase(Locale.ROOT).contains(keyword.toLowerCase(Locale.ROOT)),
                "Search results URL does not carry the keyword '" + keyword + "': " + url);
    }

    @Then("the search result count should reference {string} for web")
    public void verify_result_count_references_keyword(String keyword) {
        waitForAnyVisible(by(PLP_RESULT_COUNT));
        String countText = safeText(PLP_RESULT_COUNT);
        if (isBlank(countText)) {
            softAssert.fail("The search results page shows no result-count heading for '" + keyword + "'");
            return;
        }
        log.info("Result count heading: " + countText);
        softAssert.assertTrue(countText.toLowerCase(Locale.ROOT).contains(keyword.toLowerCase(Locale.ROOT)),
                "Result-count heading '" + countText + "' does not reference the keyword '" + keyword + "'");
    }

    @Then("every search result should be relevant to {string} for web")
    public void verify_results_relevant_for_web(String keyword) {
        waitForAnyVisible(by(PRODUCT_CARD));
        List<WebElement> cards = visibleElements(by(PRODUCT_CARD));
        if (cards.isEmpty()) {
            softAssert.fail("No product cards on the search results page for '" + keyword + "'");
            return;
        }
        for (WebElement card : cards) {
            String text;
            try {
                text = card.getText().replaceAll("\\s+", " ").trim();
            } catch (Exception e) {
                continue;
            }
            softAssert.assertTrue(
                    text.toLowerCase(Locale.ROOT).contains(keyword.toLowerCase(Locale.ROOT)),
                    "Search result '" + (text.length() > 60 ? text.substring(0, 60) : text)
                            + "' is not relevant to the keyword '" + keyword + "'");
        }
    }

    @Then("the search results page should show a no-results message for {string} for web")
    public void verify_no_results_message_for_web(String keyword) {
        List<WebElement> cards = visibleElements(by(PRODUCT_CARD));
        softAssert.assertTrue(cards.isEmpty(),
                "Expected no product cards for the non-matching keyword '" + keyword
                        + "' but found " + cards.size());

        if (!visibleElements(by(PLP_ERROR_STATE)).isEmpty()) {
            softAssert.fail("Searching for the non-matching keyword '" + keyword
                    + "' rendered the generic error page (\"Something went wrong!\") instead of a"
                    + " no-results empty state. This is a product defect, not a locator problem.");
            return;
        }
        softAssert.assertTrue(waitForAnyVisible(by(PLP_NO_RESULTS_MESSAGE)),
                "No 'no results' message was shown for the non-matching keyword '" + keyword + "'");
    }

    // ---------------- NAVIGATION ----------------

    @When("user clicks on a product from search results for web")
    public void click_product_from_results_for_web() {
        waitFor().until(ExpectedConditions.elementToBeClickable(by(SEARCH_SUGGESTIONS))).click();
    }

    @When("user clicks on view all products for web")
    public void click_view_all_products_for_web() {
        waitFor().until(ExpectedConditions.elementToBeClickable(by(SEARCH_MODAL_INPUT)))
                .sendKeys(Keys.ENTER);
    }

    @When("user selects a product from PLP for web")
    public void click_product_from_plp_for_web() {
        waitAndClickOn(PRODUCT_CARD);
    }

    @Then("user should be navigated to product detail page for web")
    public void verify_pdp_navigation_for_web() {
        String url = Grid.driver().getCurrentUrl();
        softAssert.assertTrue(url.contains("/product-detail"),
                "Not navigated to the product detail page. Current URL: " + url);
    }

    @Then("user should be navigated to product listing page for web")
    public void verify_plp_navigation_for_web() {
        String url = Grid.driver().getCurrentUrl();
        softAssert.assertTrue(url.contains("/product-list"),
                "Not navigated to the product listing page. Current URL: " + url);
    }
}
