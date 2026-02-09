package com.qapitol.jsw.stepdefinition.msiteStepDefinition;

import com.qapitol.jsw.utils.LocatorReader;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static com.sun.tools.xjc.util.DOMUtils.getElement;

public class BrowseByCategoriesSteps extends AbstractWebPage {

    private final LocatorReader locatorReader;

    // Pass YAML path from step definition
    public BrowseByCategoriesSteps() {
        this.locatorReader = new LocatorReader("locators/msite/browsebycategoriessteps.yaml");
    }


    @Then("the category tile {string} should be visible")
    public void the_category_tile_should_be_visible(String category) throws InterruptedException {
        waitAndCheckIsElementVisible(getCategoryKey(category));
        WebElement element = getCategoryElement(getCategoryKey(category));
        Assert.assertTrue(element.isDisplayed(), "Category tile not visible: " + category);

        // Scroll the element into view after visibility check
        scrollToCategoryTile(getCategoryKey(category));
    }

    @Then("the category tile {string} should be clickable")
    public void the_category_tile_should_be_clickable(String category) {
        WebElement element = getCategoryElement(getCategoryKey(category));
        Assert.assertTrue(element.isEnabled(), "Category tile not clickable: " + category);
    }

    @When("the user clicks on category tile {string}")
    public void the_user_clicks_on_category_tile(String category) {
        WebElement element = scrollToCategoryTile(getCategoryKey(category));

        // JS click ensures off-screen or partially hidden elements are clickable
        ((JavascriptExecutor) Grid.driver())
                .executeScript("arguments[0].click();", element);
    }

    @Then("the user should be redirected to the correct category page")
    public void the_user_should_be_redirected_to_the_correct_category_page() {
        String url = Grid.driver().getCurrentUrl();
        Assert.assertTrue(
                url.contains("category") || url.contains("listing"),
                "User not redirected to category listing page. Current URL: " + url
        );
    }

    // ------------- Helper Methods -------------

    /** Maps feature name to YAML key */
    private String getCategoryKey(String category) {
        switch (category.trim().toLowerCase()) {
            case "cement": return "cementCategory";
            case "bitumen": return "bitumenCategory";
            case "ms cold rolled": return "msColdRolledCategory";
            case "tmt": return "tmtCategory";
            case "coated steel": return "coatedSteelCategory";
            case "structural steel": return "structuralSteelCategory";
            case "ms hot rolled": return "msHotRolledCategory";
            case "ss hot rolled": return "ssHotRolledCategory";
            case "mild steel": return "mildSteelCategory";
            case "stainless steel": return "stainlessSteelCategory";
            default:
                throw new IllegalArgumentException("Unknown category: " + category);
        }
    }


    private WebElement getCategoryElement(String categoryKey) {
        String yamlPath = "elements." + categoryKey + ".en_US.web";
        String xpath = locatorReader.getXpath(yamlPath);

        if (xpath == null || xpath.isEmpty()) {
            throw new RuntimeException("XPath not found for category key: " + categoryKey
                    + " (YAML path: " + yamlPath + ")");
        }

        return Grid.driver().findElement(By.xpath(xpath));
    }

    /** Scrolls the category tile into view using JS */
    private WebElement scrollToCategoryTile(String categoryKey) {
        WebElement element = getCategoryElement(categoryKey);
        ((JavascriptExecutor) Grid.driver())
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        return element;
    }
}
