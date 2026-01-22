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
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class SearchSteps extends AbstractWebPage {

    // YAML keys (ONLY those that exist)
    private static final String SEARCH_INPUT = "searchInput";
    private static final String SEARCH_SUGGESTIONS = "searchSuggestions";
    private static final String PRODUCT_CARD = "productCard";
    private static final String PRODUCT_NAME = "productName";
    private static final String PRODUCT_IMAGE = "productImage";
    private static final String VIEW_ALL = "viewAllButton";

    LocatorReader locatorReader = new LocatorReader("locators/searchsteps.yaml");
    SoftAssert softAssert = new SoftAssert();
    WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));

    // ---------------- SEARCH ----------------

    @Then("user should see search bar on home page")
    public void verify_search_bar_visibility() {
        waitAndCheckIsElementVisible(SEARCH_INPUT);
    }



    @When("user enters {string} in search bar")
    public void enter_search_keyword(String keyword) {

        By searchInputBy = By.xpath(locatorReader.getXpath("elements.searchInput.en_US.web"));
        By suggestionsBy = By.xpath(locatorReader.getXpath("elements.searchSuggestions.en_US.web"));

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(searchInputBy));



        // Set value directly via JS (Vue-reactive)
        ((JavascriptExecutor) Grid.driver())
                .executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));", input, keyword);

        // Wait for suggestions
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(suggestionsBy));
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(suggestionsBy, 0));
        } catch (Exception e) {
            // no suggestions for invalid keywords
        }
    }



    @And("user submits the search")
    public void submit_search() {

        By searchInputBy = By.xpath(locatorReader.getXpath("elements.searchInput.en_US.web"));
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(searchInputBy));

        // Trigger Enter key via JS to ensure Vue reacts
        ((JavascriptExecutor) Grid.driver())
                .executeScript(
                        "var e = new KeyboardEvent('keydown', {key:'Enter', bubbles:true}); arguments[0].dispatchEvent(e);",
                        searchInput
                );
    }

    @Then("search results should be displayed for {string}")
    public void verify_search_results(String resultType) {

        String productCardXpath = locatorReader.getXpath("elements.productCard.en_US.web");
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));

        List<WebElement> products;
        try {
            products = wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(productCardXpath))
            );
        } catch (Exception e) {
            products = List.of();
        }

        if (resultType.equalsIgnoreCase("invalid")) {
            softAssert.assertTrue(
                    products.isEmpty(),
                    "Products found for invalid search"
            );
        } else {
            softAssert.assertTrue(
                    !products.isEmpty(),
                    "No products found for valid search"
            );
        }

        softAssert.assertAll();
    }

    @Then("search keyword {string} should be retained in search bar")
    public void verify_keyword_retention(String expectedKeyword) {

        WebElement searchInput = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(locatorReader.getXpath("elements.searchInput.en_US.web"))
                ));

        String actualValue = searchInput.getAttribute("value");

        softAssert.assertEquals(
                actualValue,
                expectedKeyword,
                "Search keyword is not retained"
        );

        softAssert.assertAll();
    }

    // ---------------- AUTO SUGGESTIONS ----------------

    @Then("auto suggestions should be displayed")
    public void verify_auto_suggestions() {
        waitAndCheckIsElementVisible(SEARCH_SUGGESTIONS);
    }

    @Then("auto suggestion list should not be empty")
    public void verify_auto_suggestions_not_empty() {

        String suggestionXpath = locatorReader.getXpath("elements.searchSuggestions.en_US.web");

        List<WebElement> suggestions = Grid.driver()
                .findElements(By.xpath(suggestionXpath));

        softAssert.assertTrue(
                suggestions.size() > 0,
                "Auto suggestion list is empty"
        );

        softAssert.assertAll();
    }

    // ---------------- PRODUCT VALIDATION ----------------

    @Then("product cards should be visible")
    public void verify_product_cards_visible() {
        waitAndCheckIsElementVisible(PRODUCT_CARD);
    }

    @Then("each product card should have name, image")
    public void verify_product_card_details() {

        String cardXpath = locatorReader.getXpath("elements.productCard.en_US.web");
        String nameXpath = locatorReader.getXpath("elements.productName.en_US.web");
        String imageXpath = locatorReader.getXpath("elements.productImage.en_US.web");

        List<WebElement> cards = Grid.driver().findElements(By.xpath(cardXpath));

        softAssert.assertTrue(!cards.isEmpty(), "No product cards found");

        for (WebElement card : cards) {
            softAssert.assertTrue(
                    card.findElements(By.xpath(nameXpath)).size() > 0,
                    "Product name missing"
            );
            softAssert.assertTrue(
                    card.findElements(By.xpath(imageXpath)).size() > 0,
                    "Product image missing"
            );
        }

        softAssert.assertAll();
    }

    // ---------------- NAVIGATION ----------------

    @When("user clicks on a product from search results")
    public void click_product_from_results() {
        waitAndClickOn(PRODUCT_CARD);
    }

    @Then("user should be navigated to product detail page")
    public void verify_pdp_navigation() {
        softAssert.assertTrue(
                Grid.driver().getCurrentUrl().contains("/product-detail"),
                "Not navigated to PDP page"
        );
        softAssert.assertAll();
    }

    @When("user clicks on view all products")
    public void click_view_all_products() {
        waitAndClickOn(VIEW_ALL);
    }
    @When("user selects a product from PLP")
    public void click_product_from_plp() {
        waitAndClickOn(PRODUCT_CARD);
    }

    @Then("user should be navigated to product listing page")
    public void verify_plp_navigation() {
        softAssert.assertTrue(
                Grid.driver().getCurrentUrl().contains("/query"),
                "Not navigated to PLP page"
        );
        softAssert.assertAll();
    }
}
