package com.qapitol.jsw.stepdefinition;

import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static com.qapitol.jsw.pageFactory.CategoryPages.Add_TO_CART_BUTTON;

public class pdpPlpSteps extends AbstractWebPage {

    private  final String RESET_BUTTON="resetSelectionButton";
    private final String ADD_TO_CART_BUTTON="addToCart";
    private final String ATTRIBUTES_SECTION="attributeValue";
    private final String QANTITY_INPUT_FIELD="quantity";
    private final String CART_QUANTITY="cartPageQuantity";
    private final String BRAND_NAME="brandName";

    @And("click one reset button in pdp page")
    public void clickOneResetButtonInPdpPage() {
        waitAndCheckIsElementVisible(RESET_BUTTON);
        waitAndClickOn(RESET_BUTTON);
    }

    @Then("check Add to cart is disabled")
    public void checkAddToCartIsDisabled() {
        WebElement addToCart= Grid.driver().findElement(Add_TO_CART_BUTTON);
        if(addToCart.isEnabled()){
            log.info("Add to cart button is enabled after clicking reset button");
            throw new RuntimeException("Add to cart button is enabled after clicking reset button");
        }
            else {
               log.info("Add to cart button is disabled after clicking reset button");
            }

    }

    @And("select thickness {string}")
    public void selectThickness(String thickness) {
        waitAndCheckIsElementVisible(ATTRIBUTES_SECTION);
        waitAndClickOn(ATTRIBUTES_SECTION,thickness);
        log.info("Selected thickness: " + thickness);
    }

    @And("select width {string}")
    public void selectWidth(String width) {
        waitAndCheckIsElementVisible(ATTRIBUTES_SECTION);
        waitAndClickOn(ATTRIBUTES_SECTION, width);
        log.info("Selected width: " + width);
    }

    @And("select length {string}")
    public void selectLength(String length) {
        waitAndCheckIsElementVisible(ATTRIBUTES_SECTION);
        waitAndClickOn(ATTRIBUTES_SECTION, length);
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
}

