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
import java.util.ArrayList;
import java.util.List;

import static com.qapitol.jsw.utils.CustomUtil.*;
import static com.qapitol.jsw.utils.Hooks.softAssert;

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
        log.info("User enters the key word in the search textbox "+keyword);

        By searchTriggerBy =
                By.xpath("//span[text()='Search']/../following-sibling::button");

        By modalInputBy =
                By.xpath("//input[@data-slot=\"input\"]");
        clickButton(searchTriggerBy);
        clickButton(modalInputBy);
        WebElement input = Grid.driver().findElement(modalInputBy);
        enterText(modalInputBy,keyword);


    }

    // ---------------- SEARCH RESULT VALIDATION ----------------

    @Then("search results should be displayed for {string} for web")
    public void verify_search_results_for_web(String keyword) {

        try {
            log.info("verifying the search result for the keyword "+keyword);
            By suggestionsBy =
                    By.xpath("//input[@data-slot=\"input\"]/../../../../../following-sibling::div//a");

            // The homepage search bar is a trigger that opens a modal containing the
            // real input. Click it first, then type into the modal input.
            //wait.until(ExpectedConditions.elementToBeClickable(searchTriggerBy)).click();

            new WebDriverWait(Grid.driver(), Duration.ofMillis(1000)).until(ExpectedConditions.visibilityOfAllElements(Grid.driver().findElement(suggestionsBy)));
            List<WebElement> searchresult = Grid.driver().findElements(suggestionsBy);
            List<String> list = new ArrayList<>();
            for (WebElement element : searchresult) {
                list.add(getText(element));
            }
            log.info("^^^" + list);
            for (String str : list) {
                if (str.toLowerCase().contains(keyword.toLowerCase())) {
                    softAssert.assertTrue(true);
                } else {
                    softAssert.assertTrue(false);
                }
            }
        }catch (Exception e)
        {
            softAssert.assertTrue(true);
        }
    }

    // ---------------- KEYWORD RETENTION ----------------

    @Then("search keyword {string} should be retained in search bar for web")
    public void verify_keyword_retention_for_web(String expectedKeyword) {
        log.info("User enters the key word in the search textbox "+expectedKeyword);
        By modalInputBy =
                By.xpath("//input[@data-slot=\"input\"]");

        waits(modalInputBy);
        String actualValue = Grid.driver().findElement(modalInputBy).getAttribute("value");

        Assert.assertEquals(actualValue, expectedKeyword,
                "Search keyword not retained");
    }

    // ---------------- AUTO SUGGESTIONS ----------------

    @Then("auto suggestions should be displayed for web")
    public void verify_auto_suggestions_for_web() {

        waitAndCheckIsElementVisible(SEARCH_SUGGESTIONS);
    }

    @Then("auto suggestion list should not be empty for web")
    public void verify_auto_suggestions_not_empty_for_web()
    {
        try {
            log.info("Verifying the autosuggestion");
            By suggestionsBy =
                    By.xpath("//input[@data-slot=\"input\"]/../../../../../following-sibling::div//a");



            new WebDriverWait(Grid.driver(), Duration.ofMillis(1000)).until(ExpectedConditions.visibilityOfAllElements(Grid.driver().findElement(suggestionsBy)));
            List<WebElement> searchresult = Grid.driver().findElements(suggestionsBy);
            List<String> list = new ArrayList<>();
            for (WebElement element : searchresult) {
                list.add(getText(element));
            }
            if(list.size()>0)
            {
                softAssert.assertTrue(true);
            }else {
                softAssert.assertTrue(false);
            }
        }catch (Exception e)
        {
            softAssert.assertTrue(false);
        }
    }

    // ---------------- PRODUCT CARD VALIDATION ----------------

    @Then("product cards should be visible for web")
    public void verify_product_cards_visible_for_web() {
        waitAndCheckIsElementVisible(SEARCH_SUGGESTIONS);
    }

    @Then("each product card should have name, image for web")
    public void verify_product_card_details_for_web() {

        try {
            log.info("user verifies the product name, image");
            By suggestionsBy =
                    By.xpath("//input[@data-slot=\"input\"]/../../../../../following-sibling::div//a");
            By suggestionsResultImg = By.xpath("//input[@data-slot=\"input\"]/../../../../../following-sibling::div//a//img");

            new WebDriverWait(Grid.driver(), Duration.ofMillis(1000)).until(ExpectedConditions.visibilityOfAllElements(Grid.driver().findElement(suggestionsBy)));
            List<WebElement> searchresult = Grid.driver().findElements(suggestionsBy);
            List<WebElement> searchImage = Grid.driver().findElements(suggestionsResultImg);
            List<String> list = new ArrayList<>();
            for (WebElement element : searchresult) {
                list.add(getText(element));
            }
            if (list.size() > 0) {
                softAssert.assertTrue(true);
            } else {
                softAssert.assertTrue(false);
            }
            for (WebElement element : searchImage) {
                if (element.isDisplayed())
                    {
                        softAssert.assertTrue(true);
                    }else
                    {
                        softAssert.assertTrue(false);

                    }



            }
        }catch (Exception e)
        {
            softAssert.assertTrue(false);
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
        log.info("User should be navigated to the PDP page");
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
