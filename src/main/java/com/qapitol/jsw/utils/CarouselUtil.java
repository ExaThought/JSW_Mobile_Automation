package com.qapitol.jsw.utils;

import com.qapitol.sauron.core.Grid;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class CarouselUtil {

    private final LocatorReader locatorReader;
    private final SoftAssert softAssert;

    public CarouselUtil(LocatorReader locatorReader, SoftAssert softAssert) {
        this.locatorReader = locatorReader;
        this.softAssert = softAssert;
    }

    /* -------------------- CONTENT VERIFICATION -------------------- */

    /**
     * Verifies that carousel items are present and visible
     * Locator keys must resolve to XPaths in YAML
     */
    public void verifyCarouselContent(List<String> locatorKeys) {

        for (String locatorKey : locatorKeys) {

            String xpath = locatorReader.getXpath(locatorKey);
            List<WebElement> items = Grid.driver().findElements(By.xpath(xpath));

            if (items.isEmpty()) {
                softAssert.fail("No carousel items found for locator key: " + locatorKey);
                continue;
            }

            for (int i = 0; i < items.size(); i++) {
                WebElement item = items.get(i);

                scrollIntoView(item);
                waitForStability();

                if (!item.isDisplayed()) {
                    softAssert.fail(
                            "Carousel item not visible. LocatorKey="
                                    + locatorKey + ", Index=" + (i + 1)
                    );
                }
            }
        }

        softAssert.assertAll();
    }

    /* -------------------- SLIDE ACCESS -------------------- */

    /**
     * Returns all carousel slides for a given XPath
     */
    public List<WebElement> getSlides(String xpath) {
        return Grid.driver().findElements(By.xpath(xpath));
    }

    /* -------------------- SLIDE CLICK -------------------- */

    /**
     * Scrolls and JS-clicks a slide.
     * JS click is mandatory for carousel items.
     */
    public void clickSlide(WebElement slide) {
        JavascriptExecutor js = (JavascriptExecutor) Grid.driver();

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                slide
        );

        js.executeScript("arguments[0].click();", slide);
        waitForStability();
    }

    /* -------------------- HELPERS -------------------- */

    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) Grid.driver())
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    private void waitForStability() {
        try {
            Thread.sleep(400);
        } catch (InterruptedException ignored) {
        }
    }
}
