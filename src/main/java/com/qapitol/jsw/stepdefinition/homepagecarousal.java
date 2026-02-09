package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.CarouselUtil;
import com.qapitol.jsw.utils.LocatorReader;
import com.qapitol.sauron.core.Grid;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class homepagecarousal {

    private static final Logger logger = LoggerFactory.getLogger(homepagecarousal.class);

    private final LocatorReader locatorReader = new LocatorReader("locators/msite/homepagecarousal.yaml");
    private final SoftAssert softAssert = new SoftAssert();
    private final CarouselUtil carouselUtil = new CarouselUtil(locatorReader, softAssert);

    private final String firstCarouselSlidesKey = "elements.firstCarouselSlidesXpath.en_US.web";

    @Then("the first homepage carousel should have visible slides")
    public void first_homepage_carousel_should_have_visible_slides() {
        String slidesXpath = locatorReader.getXpath(firstCarouselSlidesKey);
        List<WebElement> slides = waitForSlides(slidesXpath);

        for (WebElement slide : slides) {
            scrollIntoView(slide);
            waitForElementVisible(slide);
            softAssert.assertTrue(slide.isDisplayed(), "Slide not visible: " + slide.getAttribute("alt"));
            logger.info("Slide visible: {}", slide.getAttribute("alt"));
        }

        softAssert.assertAll();
    }

    @When("the user clicks on every slide in the first homepage carousel")
    public void user_clicks_on_every_slide_in_first_homepage_carousel() {
        String homepageUrl = Grid.driver().getCurrentUrl();
        String slidesXpath = locatorReader.getXpath(firstCarouselSlidesKey);

        List<WebElement> slides = waitForSlides(slidesXpath);

        for (int i = 0; i < slides.size(); i++) {
            // Re-fetch slides each iteration to avoid stale element
            slides = waitForSlides(slidesXpath);
            if (i >= slides.size()) break;

            WebElement slide = slides.get(i);
            String slideAlt = slide.getAttribute("alt");

            scrollIntoView(slide);
            waitForElementClickable(slide);

            // Click the slide
            carouselUtil.clickSlide(slide);

            // Wait for page load completely
            waitForPageLoad();

            String newUrl = Grid.driver().getCurrentUrl();
            String pageTitle = Grid.driver().getTitle();

            if (homepageUrl.equals(newUrl)) {
                logger.warn("Slide '{}' did not navigate anywhere. Still on homepage.", slideAlt);
            } else {
                logger.info("Slide '{}' navigated to: {} | Page Title: {}", slideAlt, newUrl, pageTitle);
            }

            // Navigate back
            Grid.driver().navigate().back();
            waitForPageLoad();
        }

        softAssert.assertAll();
    }

    /** Wait dynamically until slides are present */
    private List<WebElement> waitForSlides(String xpath) {
        Instant end = Instant.now().plus(Duration.ofSeconds(15));
        List<WebElement> slides;
        do {
            slides = carouselUtil.getSlides(xpath);
            if (!slides.isEmpty()) return slides;
        } while (Instant.now().isBefore(end));

        throw new AssertionError("No slides found after waiting: " + xpath);
    }

    /** Wait dynamically until element is visible */
    private void waitForElementVisible(WebElement element) {
        Instant end = Instant.now().plus(Duration.ofSeconds(15));
        do {
            try {
                if (element.isDisplayed()) return;
            } catch (Exception ignored) {}
        } while (Instant.now().isBefore(end));

        throw new AssertionError("Element not visible after waiting: " + element.getAttribute("alt"));
    }

    /** Wait dynamically until element is clickable */
    private void waitForElementClickable(WebElement element) {
        Instant end = Instant.now().plus(Duration.ofSeconds(15));
        do {
            try {
                if (element.isDisplayed() && element.isEnabled()) return;
            } catch (Exception ignored) {}
        } while (Instant.now().isBefore(end));

        throw new AssertionError("Element not clickable after waiting: " + element.getAttribute("alt"));
    }

    /** Scroll horizontally and vertically to bring element in view */
    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) Grid.driver()).executeScript(
                "arguments[0].scrollIntoView({inline:'center', block:'nearest'});", element
        );
    }

    /** Wait dynamically until page is fully loaded */
    private void waitForPageLoad() {
        Instant end = Instant.now().plus(Duration.ofSeconds(15));
        do {
            Object readyState = ((JavascriptExecutor) Grid.driver())
                    .executeScript("return document.readyState");
            if ("complete".equals(readyState)) return;
        } while (Instant.now().isBefore(end));
    }
}
