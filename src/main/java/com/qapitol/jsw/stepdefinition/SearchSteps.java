package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.LocatorReader;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class SearchSteps extends AbstractWebPage {

    // YAML keys
    private static final String SEARCH_INPUT = "searchInput";
    private static final String SEARCH_MODAL_INPUT = "searchModalInput";
    private static final String SEARCH_SUGGESTIONS = "searchSuggestions";
    private static final String PRODUCT_CARD = "productCard";
    private static final String PRODUCT_NAME = "productName";
    private static final String PRODUCT_IMAGE = "productImage";

    LocatorReader locatorReader =
            new LocatorReader("locators/searchsteps.yaml");

    WebDriverWait wait =
            new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));

    // ---------------- SEARCH BAR ----------------

    @Then("user should see search bar on home page for web")
    public void verify_search_bar_visibility_for_web() {
        waitAndCheckIsElementVisible(SEARCH_INPUT);
    }

    // ---------------- ENTER KEYWORD ----------------

    @When("user enters {string} in search bar for web")
    public void enter_search_keyword_for_web(String keyword) {

        By searchTriggerBy =
                By.xpath(locatorReader.getXpath("elements.searchInput.en_US.web"));

        By modalInputBy =
                By.xpath(locatorReader.getXpath("elements.searchModalInput.en_US.web"));

        By suggestionsBy =
                By.xpath(locatorReader.getXpath("elements.searchSuggestions.en_US.web"));

        // The homepage search bar is a trigger that opens a modal containing the
        // real input. Click it first, then type into the modal input.
        wait.until(ExpectedConditions.elementToBeClickable(searchTriggerBy)).click();

        WebElement input =
                wait.until(ExpectedConditions.elementToBeClickable(modalInputBy));

        // Vue reactive input set on the modal input
        ((JavascriptExecutor) Grid.driver()).executeScript(
                "arguments[0].focus();" +
                "arguments[0].value = arguments[1];" +
                "arguments[0].dispatchEvent(new Event('input', {bubbles: true}));",
                input,
                keyword
        );

        // Wait for suggestions (valid keywords)
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(suggestionsBy));
        } catch (Exception ignored) {}
    }

    // ---------------- SEARCH RESULT VALIDATION ----------------

    @Then("search results should be displayed for {string} for web")
    public void verify_search_results_for_web(String resultType) {

        // Count suggestion rows inside the search popup, not any
        // /product-detail link on the underlying homepage.
        String suggestionXpath =
                locatorReader.getXpath("elements.searchSuggestions.en_US.web");

        List<WebElement> suggestions =
                Grid.driver().findElements(By.xpath(suggestionXpath));

        if (resultType.equalsIgnoreCase("invalid")) {
            Assert.assertTrue(suggestions.isEmpty(),
                    "Suggestions found for invalid search");
        } else {
            Assert.assertTrue(!suggestions.isEmpty(),
                    "No suggestions found for valid search");
        }
    }

    // ---------------- KEYWORD RETENTION ----------------

    @Then("search keyword {string} should be retained in search bar for web")
    public void verify_keyword_retention_for_web(String expectedKeyword) {

        WebElement searchInput =
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(locatorReader.getXpath("elements.searchInput.en_US.web"))
                ));

        String actualValue = searchInput.getAttribute("value");

        Assert.assertEquals(actualValue, expectedKeyword,
                "Search keyword not retained");
    }

    // ---------------- AUTO SUGGESTIONS ----------------

    @Then("auto suggestions should be displayed for web")
    public void verify_auto_suggestions_for_web() {
        waitAndCheckIsElementVisible(SEARCH_SUGGESTIONS);
    }

    @Then("auto suggestion list should not be empty for web")
    public void verify_auto_suggestions_not_empty_for_web() {

        String suggestionXpath =
                locatorReader.getXpath("elements.searchSuggestions.en_US.web");

        List<WebElement> suggestions =
                Grid.driver().findElements(By.xpath(suggestionXpath));

        Assert.assertTrue(suggestions.size() > 0,
                "Auto suggestion list is empty");
    }

    // ---------------- PRODUCT CARD VALIDATION ----------------

    @Then("product cards should be visible for web")
    public void verify_product_cards_visible_for_web() {
        waitAndCheckIsElementVisible(SEARCH_SUGGESTIONS);
    }

    @Then("each product card should have name, image for web")
    public void verify_product_card_details_for_web() {

        // Iterate suggestion rows in the search popup; their inner DOM is
        // span.suggestion-text + img.suggestion-image, not p.typography + img.
        String cardXpath =
                locatorReader.getXpath("elements.searchSuggestions.en_US.web");

        String nameXpath =
                locatorReader.getXpath("elements.searchResultName.en_US.web");

        String imageXpath =
                locatorReader.getXpath("elements.searchResultImage.en_US.web");

        List<WebElement> cards =
                Grid.driver().findElements(By.xpath(cardXpath));

        Assert.assertTrue(!cards.isEmpty(),
                "No suggestion cards found");

        for (WebElement card : cards) {

            Assert.assertTrue(
                    card.findElements(By.xpath(nameXpath)).size() > 0,
                    "Suggestion name missing"
            );

            Assert.assertTrue(
                    card.findElements(By.xpath(imageXpath)).size() > 0,
                    "Suggestion image missing"
            );
        }
    }

    // ---------------- NAVIGATION ----------------

    @When("user clicks on a product from search results for web")
    public void click_product_from_results_for_web() {
        // Click the first suggestion row inside the search popup, not any
        // generic product-detail link elsewhere on the page.
        By suggestionsBy =
                By.xpath(locatorReader.getXpath("elements.searchSuggestions.en_US.web"));

        WebElement firstSuggestion =
                wait.until(ExpectedConditions.elementToBeClickable(suggestionsBy));

        firstSuggestion.click();
    }

    @Then("user should be navigated to product detail page for web")
    public void verify_pdp_navigation_for_web() {

        Assert.assertTrue(
                Grid.driver().getCurrentUrl().contains("/product-detail"),
                "Not navigated to PDP page"
        );
    }

    @When("user clicks on view all products for web")
    public void click_view_all_products_for_web() {
        // The redesigned search modal has no "view all" link; pressing Enter
        // on the modal input submits the query and navigates to PLP
        // (/product-list?text=<keyword>).
        By modalInputBy =
                By.xpath(locatorReader.getXpath("elements.searchModalInput.en_US.web"));

        WebElement input =
                wait.until(ExpectedConditions.elementToBeClickable(modalInputBy));

        input.sendKeys(Keys.ENTER);
    }

    @When("user selects a product from PLP for web")
    public void click_product_from_plp_for_web() {
        waitAndClickOn(PRODUCT_CARD);
    }

    @Then("user should be navigated to product listing page for web")
    public void verify_plp_navigation_for_web() {

        Assert.assertTrue(
                Grid.driver().getCurrentUrl().contains("/product-list"),
                "Not navigated to PLP page"
        );
    }
}
