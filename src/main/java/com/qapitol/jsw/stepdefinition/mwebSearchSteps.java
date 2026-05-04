package com.qapitol.jsw.stepdefinition;

import com.google.inject.Stage;
import com.qapitol.jsw.utils.LocatorReader;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

import static com.google.inject.spi.Elements.getElements;
import static com.sun.tools.xjc.util.DOMUtils.getElement;

public class mwebSearchSteps  extends AbstractWebPage {

    // YAML keys
    private static final String SEARCH_BAR = "searchBar";
    private static final String SEARCH_INPUT = "searchInput";
    private static final String SEARCH_SUGGESTIONS = "searchSuggestions";
    private static final String SEARCH_RESULTS = "searchResults";
    private static final String PRODUCT_CARD = "productCard";
    private static final String PRODUCT_NAME = "productName";
    private static final String PRODUCT_IMAGE = "productImage";
    private static final String VIEW_ALL = "viewAllButton";
    public static final String PRODUCT_CARD_PLP = "productCardplp";
    LocatorReader locatorReader = new LocatorReader("locators/mwebsearchsteps.yaml");
    SoftAssert softAssert = new SoftAssert();
    Actions actions = new Actions(Grid.driver());



    @Given("user is on msite home page")
    public void user_is_on_home_page() {
        // Navigation handled by test setup
    }

    @Then("user should see search bar on home page")
    public void verify_search_bar_visibility() {
        waitAndCheckIsElementVisible(SEARCH_BAR);
    }

    @When("user enters {string} in search bar")
    public void enter_search_keyword(String keyword) {
        waitAndClickOn(SEARCH_BAR);
        waitAndSetText(SEARCH_INPUT, keyword);
    }

    @And("user submits the search")
    public void submit_search() {
        waitAndClickOn(SEARCH_INPUT);
        actions.sendKeys(new CharSequence[]{Keys.ENTER}).build().perform();
    }

    @Then("search results should be displayed for {string}")
    public void verify_search_results(String resultType) {
        String productCardXpath = locatorReader.getXpath("elements.productCard.en_US.web");

        if (productCardXpath == null) {
            throw new RuntimeException("Failed to read XPath for productCard from YAML");
        }

        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));

        List<WebElement> products;
        try {
            products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(productCardXpath)));
        } catch (Exception e) {
            // If wait fails, treat as empty list
            products = List.of();
        }

        if (resultType.equalsIgnoreCase("invalid")) {
            if (products.isEmpty()) {
                System.out.println("No products found, as expected for invalid search.");
            } else {
                throw new AssertionError("Products found for invalid search: " + products.size());
            }
        } else {
            if (!products.isEmpty()) {
                System.out.println("Products found: " + products.size());
            } else {
                throw new AssertionError("No products found for valid search.");
            }
        }
    }

    @Then("search keyword {string} should be retained in search bar")
    public void verify_keyword_retention(String expectedKeyword) {
        // Get the XPath from YAML
        String searchInputXpath = locatorReader.getXpath("elements.searchInput.en_US.web");

        // Wait until the search input is visible
        WebElement searchInput = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchInputXpath)));

        // Get the value in the search input
        String currentKeyword = searchInput.getAttribute("value");

        // Validate the value is not empty
        if (currentKeyword == null || currentKeyword.isEmpty()) {
            throw new AssertionError("Search input is empty, expected keyword to be retained");
        }

        softAssert.assertEquals(currentKeyword, expectedKeyword,
                "Search keyword is not retained in search bar");

        softAssert.assertAll();

    }




    @Then("auto suggestions should be displayed")
    public void verify_auto_suggestions() {
        waitAndCheckIsElementVisible(PRODUCT_CARD);
    }

    @Then("auto suggestion list should not be empty")
    public void verify_suggestions_not_empty() {
        verifyListSizeGreaterThan(PRODUCT_CARD, 0);
    }

    @Then("product cards should be visible")
    public void verify_product_cards() {
        waitAndCheckIsElementVisible(PRODUCT_CARD);
    }

    @Then("each product card should have name, image")
    public void verify_product_details() {

        String productCardXpath = locatorReader.getXpath("elements.productCardplp.en_US.web");
        String productNameXpath = locatorReader.getXpath("elements.productName.en_US.web");
        String productImageXpath = locatorReader.getXpath("elements.productImage.en_US.web");

        List<WebElement> productCards =
                Grid.driver().findElements(By.xpath(productCardXpath));

        if (productCards.isEmpty()) {
            throw new AssertionError("No product cards found on page");
        }

        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < productCards.size(); i++) {
            WebElement card = productCards.get(i);

            List<WebElement> names =
                    card.findElements(By.xpath(productNameXpath));

            List<WebElement> images =
                    card.findElements(By.xpath(productImageXpath));

            softAssert.assertTrue(
                    !names.isEmpty() && names.get(0).isDisplayed(),
                    "Product name missing for card index: " + i
            );

            softAssert.assertTrue(
                    !images.isEmpty() && images.get(0).isDisplayed(),
                    "Product image missing for card index: " + i
            );
        }

        softAssert.assertAll();
    }


    @When("user clicks on a product from search results")
    public void click_product_from_results() {
        waitAndClickOn(PRODUCT_CARD);
    }

    @When("user selects a product from PLP")
    public void click_product_from_plp() {
        waitAndClickOn(PRODUCT_CARD_PLP);
    }


    @Then("user should be navigated to product detail page")
    public void verify_pdp_navigation() {
        verifyCurrentUrlContainsSoft("/product-detail/");
    }

    @When("user clicks on view all products")
    public void click_view_all() {
        waitAndClickOn(VIEW_ALL);
    }

    @Then("user should be navigated to product listing page")
    public void verify_plp_navigation() {
        verifyCurrentUrlContainsSoft("/query");
    }


    public void verifyListSizeGreaterThan(String elementKey, int minSize) {
        String xpath = locatorReader.getXpath("elements.productCard.en_US.web");
        if (xpath == null) {
            throw new RuntimeException("Failed to read XPath for: " + elementKey);
        }
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
        List<WebElement> elements;
        try {
            elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
        } catch (Exception e) {
            elements = List.of(); // treat as empty list
        }
        // Soft assertion
        softAssert.assertTrue(elements.size() > minSize,
                "Expected at least " + minSize + " elements for " + elementKey + " but found " + elements.size());
        softAssert.assertAll();
    }





    public void verifyCurrentUrlContainsSoft(String expected) {
        SoftAssert softAssert = new SoftAssert();
        String currentUrl = Grid.driver().getCurrentUrl(); // provided by AbstractWebPage
        softAssert.assertTrue(
                currentUrl.contains(expected),
                "Expected URL to contain: " + expected + " but found: " + currentUrl
        );
        softAssert.assertAll();
    }




}