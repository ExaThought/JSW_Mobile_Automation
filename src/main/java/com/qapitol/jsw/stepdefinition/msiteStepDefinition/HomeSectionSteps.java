package com.qapitol.jsw.stepdefinition.msiteStepDefinition;

import com.qapitol.jsw.utils.CarouselUtil;
import com.qapitol.jsw.utils.LocatorReader;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static com.qapitol.jsw.utils.Hooks.softAssert;
import static com.sun.tools.xjc.util.DOMUtils.getElement;

public class HomeSectionSteps
        extends AbstractWebPage {

    private final LocatorReader locatorReader;
    public static final String section_content = "fromOurCustomerssectionContent";
    // carousel_image ("carousalConent") removed: the key was misspelled and never referenced.
    // The real key, ImagecarouselContent, is already used directly in the carouselLocatorKeys
    // list below, so this constant was a superseded leftover.

    FooterSteps fs = new FooterSteps();

    // Pass YAML path from step definition
    public HomeSectionSteps () {
        this.locatorReader = new LocatorReader("locators/msite/homesectionsteps.yaml");
    }


    @Then("the section  {string} should be visible")
    public void the_section_tile_should_be_visible(String section) throws InterruptedException {
        waitAndCheckIsElementVisible(getsectionKey(section));
        WebElement element = getsectionElement(getsectionKey(section));
        Assert.assertTrue(element.isDisplayed(), "section tile not visible: " + section);

        // Scroll the element into view after visibility check
        scrollTosectionTile(getsectionKey(section));
    }

    @Then("the section should have content")
    public void the_section_should_have_content() {

        List<String> carouselLocatorKeys = List.of(
                "elements.ImagecarouselContent.en_US.web",
                "elements.msmeMasterclassCarousal.en_US.web"
        );

        CarouselUtil carouselUtil =
                new CarouselUtil(locatorReader, softAssert);

        carouselUtil.verifyCarouselContent(carouselLocatorKeys);
    }


    private String extractSectionNameFromKey(String key) {
        // Example: extract from the key string for friendly name
        if (key.contains("fromOurCustomers")) {
            return "From our customers";
        } else if (key.contains("msmeMasterclass")) {
            return "MSME Masterclass";
        } else {
            return key; // fallback
        }}




    // ------------- Helper Methods -------------

    /** Maps feature name to YAML key */
    private String getsectionKey(String section) {
        switch (section.trim().toLowerCase()) {
            case "from our customers":
                return "fromOurCustomerssection";
            case "msme masterclass":
                return "msmeMasterclasssection";
            default:
                throw new IllegalArgumentException("Unknown section: " + section);
        }
    }



    private WebElement getsectionElement(String sectionKey) {
        String yamlPath = "elements." + sectionKey + ".en_US.web";
        String xpath = locatorReader.getXpath(yamlPath);

        if (xpath == null || xpath.isEmpty()) {
            throw new RuntimeException("XPath not found for section key: " + sectionKey
                    + " (YAML path: " + yamlPath + ")");
        }

        return Grid.driver().findElement(By.xpath(xpath));
    }

    /** Scrolls the section tile into view using JS */
    private WebElement scrollTosectionTile(String sectionKey) {
        WebElement element = getsectionElement(sectionKey);
        ((JavascriptExecutor) Grid.driver())
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        return element;
    }
}
