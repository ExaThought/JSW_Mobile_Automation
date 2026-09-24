package com.qapitol.jsw.stepdefinition.msiteStepDefinition;

import com.qapitol.jsw.utils.LocatorReader;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ViewAllNavigationSteps extends AbstractWebPage {

    private final LocatorReader locatorReader;



    public ViewAllNavigationSteps() {
        this.locatorReader = new LocatorReader("locators/msite/viewallnavigationsteps.yaml");
    }

    // ------------------ STEPS ------------------

    @Then("the View All CTA should be visible for {string}")
    public void the_view_all_cta_should_be_visible_for_category(String category) {
        String categoryKey = getCategoryKey(category);
        WebElement viewAll = scrollToViewAll(categoryKey);
        Assert.assertTrue(viewAll.isDisplayed(),
                "View All CTA not visible for category: " + category);
    }

    @Then("the View All CTA should be clickable for {string}")
    public void the_view_all_cta_should_be_clickable_for_category(String category) {
        WebElement viewAll = getViewAllElement(getCategoryKey(category));
        Assert.assertTrue(viewAll.isEnabled(),
                "View All CTA not clickable for category: " + category);
    }

    @When("the user clicks on View All for {string}")
    public void the_user_clicks_on_view_all_for_category(String category) {
        WebElement viewAll = scrollToViewAll(getCategoryKey(category));
        ((JavascriptExecutor) Grid.driver())
                .executeScript("arguments[0].click();", viewAll);
    }

    @Then("the user should be redirected to the correct PLP for {string}")
    public void the_user_should_be_redirected_to_correct_plp(String category) {
        String currentUrl = Grid.driver().getCurrentUrl();
        String plpHeadingXpath =
                locatorReader.getXpath("elements.msHotRolledPLP.en_US.web");
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));

        WebElement heading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(plpHeadingXpath)
                )
        );
        String plpHeading = Grid.driver()
                .findElement(By.xpath(plpHeadingXpath))
                .getText()
                .trim();
        Assert.assertTrue(
                plpHeading.equalsIgnoreCase(category)
                        || plpHeading.toLowerCase().contains(category.toLowerCase()),
                "Incorrect PLP opened for category: " + category +
                        " | PLP Heading: " + plpHeading +
                        " | Current URL: " + currentUrl
        );
    }

    @Then("the PLP heading should match {string}")
    public void the_plp_heading_should_match_category(String category) {
        String headingXpath = locatorReader.getXpath("elements.plpHeading.en_US.web");
        WebElement heading = Grid.driver().findElement(By.xpath(headingXpath));

        Assert.assertTrue(
                heading.getText().toLowerCase().contains(category.toLowerCase()),
                "PLP heading mismatch. Expected category: " + category +
                        " | Actual heading: " + heading.getText()
        );
    }

    // ------------------ HELPERS ------------------

    /** Maps category name from feature file to YAML key */
    private String getCategoryKey(String category) {
        switch (category.trim().toLowerCase()) {
            case "ms cold rolled":
                return "msColdRolledViewAll";
            case "ms hot rolled":
                return "msHotRolledViewAll";
            case "tmt":
                return "tmtViewAll";
            case "cement":
                return "cementViewAll";
            default:
                throw new IllegalArgumentException("Unknown category: " + category);
        }
    }



    private WebElement getViewAllElement(String categoryKey) {
        String yamlPath = "elements." + categoryKey + ".en_US.web";
        String xpath = locatorReader.getXpath(yamlPath);

        if (xpath == null || xpath.isEmpty()) {
            throw new RuntimeException("XPath not found for View All CTA: " + categoryKey);
        }
        return Grid.driver().findElement(By.xpath(xpath));
    }

    /** Scroll View All CTA into viewport */
    private WebElement scrollToViewAll(String categoryKey) {
        WebElement element = getViewAllElement(categoryKey);
        ((JavascriptExecutor) Grid.driver())
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        return element;
    }
}
