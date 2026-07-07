package com.qapitol.jsw.stepdefinition;

import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static com.qapitol.jsw.pageFactory.CategoryPages.Add_TO_CART_BUTTON;
import static com.qapitol.jsw.utils.CustomUtil.*;
import static com.qapitol.jsw.utils.Hooks.softAssert;

public class pdpPlpSteps extends AbstractWebPage {

    private  final String RESET_BUTTON="resetSelectionButton";
    private final String ADD_TO_CART_BUTTON="addToCart";
    private final String ATTRIBUTES_SECTION="attributeValue";
    private final String THICKNESS = "(//span[text()='Thickness (mm)']/../following-sibling::div/button[not(@disabled)])[1]";
    private final String WIDTH = "(//span[text()='Width (mm)']/../following-sibling::div/button[not(@disabled)])[1]";
    private final String LENGTH = "(//span[text()=\"Length (mm)\"]/../following-sibling::div/button[not(@disabled)])[1]";
    private final String QANTITY_INPUT_FIELD="quantity";
    private final String CART_QUANTITY="cartPageQuantity";
    private final String BRAND_NAME="brandName";
    private final String PDP_DESCRIPTION="pdpDescription";

    @And("click on reset button in pdp page")
    public void clickOnResetButtonInPdpPage() {
        waitAndCheckIsElementVisible(RESET_BUTTON);
        waitAndCheckIsElementClickable(RESET_BUTTON);
        moveToElement(RESET_BUTTON);
        waitAndClickOn(RESET_BUTTON);
        log.info("Clicked on reset button in PDP page");
    }

    @Then("check Add to cart is disabled")
    public void checkAddToCartIsDisabled() {
        WebElement addToCart= Grid.driver().findElement(Add_TO_CART_BUTTON);
        softAssert.assertTrue(addToCart.isDisplayed(),"Add to cart button is enabled after clicking reset button");
        log.info("Add to cart button is disabled after clicking reset button");
    }

    @Then("check Add to cart is enabled")
    public void checkAddToCartIsEnabled() {

        softAssert.assertTrue( waitUntilEnabled(Add_TO_CART_BUTTON), "Add to cart button is not enabled after selecting all attributes");
         log.info("Add to cart button is enabled after selecting all attributes");
    }

    @Then("check image gallery loads on PDP page")
    public void checkImageGalleryLoadsOnPDPPage() {
        WebElement image = Grid.driver().findElement(By.xpath("//img[@class='big-image']"));
        boolean visible = image.isDisplayed();
        boolean loaded = (Boolean)((JavascriptExecutor)Grid.driver()).executeScript(
                "return arguments[0].complete && arguments[0].naturalWidth > 0;", image);

        softAssert.assertTrue(visible, "Image is not visible in PDP page");
        softAssert.assertTrue(loaded, "Image is not loaded successfully in PDP page");
            log.info("Image gallery loads successfully on PDP page");
    }


    @And("select thickness for pdp {string}")
    public void selectThickness(String thickness) {
        waits(By.xpath(THICKNESS));
        clickButton(By.xpath(THICKNESS));
        log.info("Selected thickness: ");
    }

    @And("select width {string}")
    public void selectWidth(String width) {
        waits(By.xpath(WIDTH));
        clickButton(By.xpath(WIDTH));
        log.info("Selected width: " + width);
    }

    @And("select length {string}")
    public void selectLength(String length) {
        waits(By.xpath(LENGTH));
        clickButton(By.xpath(LENGTH));
        log.info("Selected length: " + length);
    }

    @Then("check quantity enter below 10000 in PDP page")
    public void checkQuantityEnterBelow10000InPDPPage() {
        String pdpQuantity=waitAndGetText(CART_QUANTITY);

        if (pdpQuantity.equals("Qty: 1000 MT")){
            log.info("Quantity is set to 1000 when user tries to enter above 10000");
        } else {
            log.info("Quantity is set to ="+pdpQuantity +"It should not set more than 9999");
            throw new RuntimeException("Quantity is set to ="+pdpQuantity +"It should not set more than 9999");
        }

    }

    @Then("check brand name is not empty in PDP page")
    public void checkBrandNameIsNotEmptyInPDPPage() {
        String brandName=waitAndGetText(BRAND_NAME);
        Assert.assertFalse(brandName.isEmpty(), "Brand name is empty in PDP page");
        log.info("Brand name is not empty in PDP page: " + brandName);
    }

    @Then("check product description is not empty in PDP page")
    public void checkProductDescriptionIsNotEmptyInPDPPage() {
        String description = waitAndGetText(PDP_DESCRIPTION).trim();
        Assert.assertFalse(description.isEmpty(), "Product description is empty in PDP page");
        log.info("Product description is not empty in PDP page: " + description);
    }
}

