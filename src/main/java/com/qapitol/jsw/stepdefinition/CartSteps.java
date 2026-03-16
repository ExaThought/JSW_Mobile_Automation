package com.qapitol.jsw.stepdefinition;


import com.qapitol.jsw.pageFactory.CategoryPages;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static com.qapitol.jsw.pageFactory.CategoryPages.*;
import static com.qapitol.jsw.utils.CustomUtil.scrollingToViewAndClickUsingJS;
import static com.qapitol.jsw.utils.CustomUtil.waitUntilElementDisappears;
import static com.qapitol.jsw.utils.Hooks.softAssert;

public class CartSteps extends AbstractWebPage {
    private static final String THICKNESS = "thickness";
    private static final String QUANTITY = "quantity";
    private static final String ADD_TO_CART_CTA = "addToCart";
    private static final String VIEW_CART = "viewCart";
    private static final String SUBMIT_REQUIREMENT = "submitRequirement";
    private static final String DELETE_LINE_ITEM = "deleteLineItem";
    private static final String EDIT_LINE_ITEM = "editLineItem";
    private static final String DELETE_CTA_BUTTON = "deleteCta";
    private static final String CEMENT_PLP = "cement";
    private static final String CEMENT_PDP = "cementPDP";
    private static final String CEMENT_QUANTITY = "cementQuantity";
    private static final String STAINLESS_STEEL_PLP = "stainlessSteelPlp";
    private static final String STAINLESS_STEEL_PDP = "stainlessSteelPDP";
    private static final String STAINLESS_STEEL_THICKNESS = "stainlessSteelThickness";
    private static final String STAINLESS_STEEL_WIDTH = "stainlessSteelWidth";
    private static final String STAINLESS_STEEL_LENGTH = "stainlessSteelLength";
    private static final String RCV2_CHECKOUT = "rcCheckout";
    private static final String MY_REQUIREMENT_PAGE = "myRequirementPage";
    private static final String REQUIREMENT_ID = "requirementId";
    private static final String MILD_STEEL = "mildSteel";
    private static final String COLOUR_COATED = "colourCoated";
    private static final String PDP_COATED_STEEL_SHEET = "colourCoatedPdp";
    private static final String WIDTH = "width(mm)";
    private static final String COATING_GRADE = "coatingGrade";
    private static final String SUCCESS_PAGE_RCV2 = "rcCheckoutSuccessPage";
    private static final String CART_PAGE = "cartPage";
    private static final String PLP_FIRST_PRODUCT = "plpFirstProduct";
    private static final String LENGTH = "length";
    private static final String DIAMETER = "diameter";
    private static final String TMT = "tmt";
    private static final String FORM = "form";
    private static final String LEG_LENGTH = "legLength";
    private static final String FLANGE_WIDTH = "flangeWidth";
    private static final String WEB_DEPTH = "webDepth";
    private static final String SECTION_WEIGHT = "sectionalWeight";
    private static final String EDIT_QUANTITY = "editQuantity";
    private static final String SAVE_EDIT = "editSave";
    private static final String EDIT_WIDTH_DROPDOWN = "editWidthDropdown";
    private static final String EDIT_THICKNESS_DROPDOWN = "editThicknessDropdown";
    private static final String EDIT_LENGTH_DROPDOWN = "editLengthDropdown";
    private static final String EDIT_WIDTH = "editWidth";
    private static final String EDIT_THICKNESS = "editThickness";
    private static final String EDIT_LENGTH = "editLength";
    private static final String ATTRIBUTE = "attributeValue";
    private static final String PDP_PRODUCT_NAME = "pdpProductName";
    private static final String COATED_STEEL = "coatedSteel";
    private static final String GALVANIZED_SHEET = "galvanizedSheet";
    private static final String UPDATE_ATTRIBUTE = "updateAttribute";
    private static final String CART_PAGE_ATTRIBUTE_VALUE = "cartPageAttributeValue";
    private static final String EDIT_CART_PAGE_ENTER_QUANTITY = "editCartPageEnterQuantity";
    private static final String CART_PAGE_PRODUCT_NAME = "cartPageProductName";
    private static final String CART_PAGE_QUANTITY = "cartPageQuantity";
    private static final String EDIT_DIAMETER_DROPDOWN = "editDiameterDropdown";
    private static final String EDIT_FORM_DROPDOWN = "editFormDropdown";
    private static final String CART_PAGE_SPANGLE = "cartPageSpangleTypeValue";
    private static final String CART_PAGE_COATING_GRADE = "cartPageCoatingGradeValue";
    private static final String CART_COUNT = "cartCount";
    private static final String EMPTY_CART_MESSAGE = "emptyCartMessage";
    private static final String EMPTY_CART_COUNT = "emptyCartCount";
    private static final String CART_PAGE_SKU_COUNT = "cartPageSKUCount";
    private static final String DELETE_CART_TITLE = "deleteCartTitle";


    Actions actions = new Actions(Grid.driver());
    static String oldRequirementId;
    static String newRequirementId;
    static String pdpThickness;
    static String pdpWidth;
    static String pdpLength;
    static String pdpQuantity;
    static String pdpProductName;
    static String updatedThickness;
    static String updatedWidth;
    static String updatedLength;
    static String updatedQuantity;
    static String cartProductName;
    static String cartAttributeValue;
    static String afterUpdateCartAttributeValue;
    static String cartPageQuantity;
    static String pdpDiameter;
    static String pdpForm;
    static String updatedDiameter;
    static String pdpCoatingGrade;
    static String pdpSpangleType;
    static String cartPageSpangle;
    static String cartPageCoatingGrade;
    static int skuCount;

    @And("user will add Stainless Steel product to cart")
    public void user_will_add_stainless_steel_product_to_cart() {
        sleep(2000);
        waitAndCheckIsElementPresent(STAINLESS_STEEL_PLP);
        waitAndClickOn(STAINLESS_STEEL_PLP);
        sleep(1000);
        waitAndCheckIsElementPresent(STAINLESS_STEEL_PDP);
        waitAndClickOn(STAINLESS_STEEL_PDP);
        sleep(1000);
        waitAndCheckIsElementPresent(STAINLESS_STEEL_THICKNESS);
        waitAndClickOn(STAINLESS_STEEL_THICKNESS);
        waitAndCheckIsElementPresent(STAINLESS_STEEL_WIDTH);
        waitAndClickOn(STAINLESS_STEEL_WIDTH);
        waitAndCheckIsElementPresent(STAINLESS_STEEL_LENGTH);
        waitAndClickOn(STAINLESS_STEEL_LENGTH);
        sleep(1000);
        waitAndCheckIsElementPresent(QUANTITY);
        waitAndSetText(QUANTITY, "10");
        sleep(2000);
        waitAndCheckIsElementPresent(ADD_TO_CART_CTA);
        waitAndClickOn(ADD_TO_CART_CTA);
        sleep(5000);
        waitAndCheckIsElementPresent(VIEW_CART);
        waitAndClickOn(VIEW_CART);
        softAssert.assertTrue(waitAndCheckIsElementPresent(SUBMIT_REQUIREMENT));
        sleep(3000);
    }

    @Then("check user able to delete line item from cart")
    public void check_ser_able_to_delete_line_item_from_cart() {
        waitAndCheckIsElementPresent(DELETE_LINE_ITEM);
        waitAndClickOn(DELETE_LINE_ITEM);
        waitAndCheckIsElementPresent(DELETE_CTA_BUTTON);
        waitAndClickOn(DELETE_CTA_BUTTON);
    }

    @And("user checkout the RCV2")
    public void user_checkout_the_rcv2() {
        sleep(2000);
        waitAndCheckIsElementPresent(MY_REQUIREMENT_PAGE);
        waitAndClickOn(MY_REQUIREMENT_PAGE);
        waitAndCheckIsElementPresent(REQUIREMENT_ID);
        oldRequirementId = waitAndGetText(REQUIREMENT_ID);
        waitAndCheckIsElementPresent(CART_PAGE);
        waitAndClickOn(CART_PAGE);
        sleep(5000);
        waitAndCheckIsElementPresent(RCV2_CHECKOUT);
        waitAndClickOn(RCV2_CHECKOUT);
        sleep(2000);
        softAssert.assertTrue(isElementPresent(SUCCESS_PAGE_RCV2), "After checkout, RCV2 success page is not displayed");
        waitAndCheckIsElementPresent(MY_REQUIREMENT_PAGE);
        waitAndClickOn(MY_REQUIREMENT_PAGE);
        waitAndCheckIsElementPresent(REQUIREMENT_ID);
        newRequirementId = waitAndGetText(REQUIREMENT_ID);

    }

    @When("move the mouse to Mild steel category")
    public void moveTheMouseToMildSteelCategory() {
        //Reading locator from page factory
        try {
            actions.moveToElement(Grid.driver().findElement(By.xpath(mildSteelCategory))).perform();
            log.info("cursor moved to mild steel category");
        } catch (Exception e) {
            throw new RuntimeException("mouse failed to move to mild steel category", e);

        }

    }

    @And("move to Hot rolled category")
    public void moveToHotRolledCategory() {
        //Reading locator from page factory
        try {
            actions.moveToElement(Grid.driver().findElement(By.xpath(hotRolledCategory))).perform();
            log.info("cursor moved to hot rolled category");
        } catch (Exception e) {
            throw new RuntimeException("mouse failed to move to hot rolled category", e);

        }
    }

    @And("click on HR Sheet product")
    public void clickOnHRSheetProduct() {
        //Reading locator from page factory
        try {
            actions.moveToElement(Grid.driver().findElement(By.xpath(hotRolledSheetsSubCategory))).click().perform();
            log.info("Clicked on hot rolled sheet sub category");
        } catch (Exception e) {
            throw new RuntimeException("Failed to click on hot rolled sheet sub category", e);
        }
    }

    @And("click on HR Coil product")
    public void clickOnHRCoilProduct() {
        //Reading locator from page factory
        try {
            actions.moveToElement(Grid.driver().findElement(By.xpath(hotRolledCoilsSubCategory))).click().perform();
            log.info("Clicked on hot rolled coil sub category");
        } catch (Exception e) {
            log.info("Failed to click on hot rolled coil sub category");
            throw new RuntimeException("Failed to click on hot rolled coil sub category", e);
        }
    }

    @And("open HR Coil plp {string} product")
    public void openHRCoilPdp(String product) {
        try {
            WebElement locator = Grid.driver().findElement(By.xpath(plpFirstProduct));
            scrollingToViewAndClickUsingJS(locator);
            waitAndClickOn(PLP_FIRST_PRODUCT, product);
            log.info("Opened HR Coil PDP page");
        } catch (Exception e) {
            log.info("Failed to click on  hot rolled coil category");
            throw new RuntimeException("Failed to click on hot rolled coil category", e);
        }
    }

    @And("select thickness {string} width {string} quantity {string} for HR Coil")
    public void selectAllTheHrCoilAttribute(String thickness, String width, String quantity) {

        try {
            if (isElementPresent(ATTRIBUTE, thickness)) {
                waitAndCheckIsElementClickable(ATTRIBUTE, thickness);
                waitAndClickOn(ATTRIBUTE, thickness);
                pdpThickness = thickness;
                log.info("Thickness value " + thickness + " mm is selected for hot rolled coil");
            } else {
                log.info("Thickness value " + thickness + " is not available for selection, selecting available thickness value");
                Allure.addAttachment("Thickness value not available", "Thickness value " + thickness + " is not available for selection, selecting available thickness value");
                waitAndCheckIsElementClickable(THICKNESS);
                waitAndClickOn(THICKNESS, "2");
                pdpThickness = waitAndGetText(THICKNESS, "2");
            }
        } catch (Exception e) {
            log.info("Failed to click hot rolled coil thickness");
            throw new RuntimeException("Failed to click hot rolled coil thickness", e);
        }

        try {
            if (isElementPresent(ATTRIBUTE, width)) {
                waitAndCheckIsElementClickable(ATTRIBUTE, width);
                waitAndClickOn(ATTRIBUTE, width);
                pdpWidth = width;
            } else {
                log.info("Width value 2mm is not available for selection, selecting available width value");
                Allure.addAttachment("Width value not available", "Width value 2mm is not available for selection, selecting available width value");
                waitAndCheckIsElementClickable(WIDTH);
                waitAndClickOn(WIDTH, "2");
                pdpWidth = waitAndGetText(WIDTH, "2");
            }
        } catch (Exception e) {
            log.info("Failed to click hot rolled coil width");
            throw new RuntimeException("Failed to click hot rolled coil width", e);
        }

        try {
            waitAndCheckIsElementClickable(QUANTITY);
            waitAndSetText(QUANTITY, quantity);
            pdpQuantity = quantity;
        } catch (Exception e) {
            log.info("Failed to enter quantity for hot rolled coil");
            throw new RuntimeException("Failed to enter quantity for hot rolled coil", e);
        }

    }

    @And("select same attribute values")
    public void selectSameAttributeValues() {
        try {
            waitAndCheckIsElementClickable(ATTRIBUTE, pdpThickness);
            waitAndClickOn(ATTRIBUTE, pdpThickness);
            log.info("Selected same thickness value " + pdpThickness + " mm for hot rolled coil");
        } catch (Exception e) {
            log.info("Failed to select same thickness value for hot rolled coil");
            throw new RuntimeException("Failed to select same thickness value for hot rolled coil", e);
        }

        try {
            waitAndCheckIsElementClickable(ATTRIBUTE, pdpWidth);
            waitAndClickOn(ATTRIBUTE, pdpWidth);
            log.info("Selected same width value " + pdpWidth + " mm for hot rolled coil");
        } catch (Exception e) {
            log.info("Failed to select same width value for hot rolled coil");
            throw new RuntimeException("Failed to select same width value for hot rolled coil", e);
        }

        try {
            waitAndCheckIsElementClickable(QUANTITY);
            waitAndSetText(QUANTITY, pdpQuantity);
            int quantityValue = Integer.parseInt(pdpQuantity) * 2;
            pdpQuantity = String.valueOf(quantityValue);
            log.info("Entered same quantity " + pdpQuantity + " for hot rolled coil");
        } catch (Exception e) {
            log.info("Failed to enter same quantity for hot rolled coil");
            throw new RuntimeException("Failed to enter same quantity for hot rolled coil", e);
        }
    }

    @Then("Verify the Attribute values for HR Coil in cart page")
    public void verifyTheAttributeValuesForHRCoilinCartPage() {
        //verify quantity in cart page
        cartPageQuantity = waitAndGetText(CART_PAGE_QUANTITY);
        softAssert.assertEquals(cartPageQuantity, "Qty: " + pdpQuantity + " MT");

        softAssert.assertEquals(waitAndGetText(CART_COUNT), "1", "Cart count is not updated in cart icon after adding product to cart");

        cartAttributeValue = waitAndGetText(CART_PAGE_ATTRIBUTE_VALUE);
        softAssert.assertFalse(cartAttributeValue.isEmpty(), "Attribute value is not displayed in cart page");
        softAssert.assertEquals(cartAttributeValue, pdpThickness + " x " + pdpWidth + " mm", "Attribute value in cart page is not matching with selected attribute value in PDP");

        softAssert.assertTrue(isElementPresent(CART_PAGE_PRODUCT_NAME, pdpProductName), "Product name in cart page is not matching with product name in PDP or product name is not displayed in cart page");

    }

    @And("open HR Sheet plp {string} product")
    public void openHRSheetPdp(String product) {
        WebElement locator = Grid.driver().findElement(By.xpath(plpFirstProduct));
        scrollingToViewAndClickUsingJS(locator);
        try {
            waitAndCheckIsElementClickable(PLP_FIRST_PRODUCT);
            waitAndClickOn(PLP_FIRST_PRODUCT, product);
            log.info("Opened HR Sheet PDP page");
        } catch (Exception e) {
            throw new RuntimeException("Failed to click on first product of hot rolled sheet category", e);
        }
    }

    @And("select thickness {string} width {string} length {string} quantity {string} for HR Sheet")
    public void selectAllTheHrAttribute(String thickness, String width, String length, String quantity) {

        //Select HR Sheet thickness
        try {
            if (isElementPresent(ATTRIBUTE, thickness)) {
                waitAndCheckIsElementClickable(ATTRIBUTE, thickness);
                waitAndClickOn(ATTRIBUTE, thickness);
                pdpThickness = thickness;
                log.info("Thickness value " + thickness + "mm is selected for hot rolled sheet");
            } else {
                log.info("Thickness value " + thickness + "mm is not available for selection, selecting available thickness value");
                Allure.addAttachment("Thickness value not available", "Thickness value " + thickness + "mm is not available for selection, selecting available thickness value");
                waitAndCheckIsElementClickable(THICKNESS);
                waitAndClickOn(THICKNESS, "2");
                pdpThickness = waitAndGetText(THICKNESS, "2");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to click hot rolled sheet thickness", e);
        }

        //Select HR Sheet width
        try {
            if (isElementPresent(ATTRIBUTE, width)) {
                waitAndCheckIsElementClickable(ATTRIBUTE, width);
                waitAndClickOn(ATTRIBUTE, width);
                pdpWidth = width;
                log.info("Width value " + width + "mm is selected for hot rolled sheet");
            } else {
                log.info("Width value " + width + "mm is not available for selection, selecting available width value");
                Allure.addAttachment("Width value not available", "Width value " + width + "mm is not available for selection, selecting available width value");
                waitAndCheckIsElementClickable(WIDTH);
                waitAndClickOn(WIDTH, "1");
                pdpWidth = waitAndGetText(WIDTH, "1");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to click hot rolled sheet width", e);
        }

        //Scroll to length attribute and select length
        WebElement locator = Grid.driver().findElement(By.xpath(CategoryPages.length));
        scrollingToViewAndClickUsingJS(locator);
        try {
            if (isElementPresent(ATTRIBUTE, length)) {
                waitAndCheckIsElementClickable(ATTRIBUTE, length);
                waitAndClickOn(ATTRIBUTE, length);
                pdpLength = length;
                log.info("Length value " + length + "mm is selected for hot rolled sheet");
            } else {
                log.info("Length value " + length + "mm is not available for selection, selecting available length value");
                Allure.addAttachment("Length value not available", "Length value " + length + "mm is not available for selection, selecting available length value");
                waitAndCheckIsElementClickable(LENGTH);
                waitAndClickOn(LENGTH, "1");
                pdpLength = waitAndGetText(LENGTH, "1");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to click hot rolled sheet length", e);
        }

        //Enter quantity for HR Sheet
        try {
            waitAndCheckIsElementClickable(QUANTITY);
            waitAndSetText(QUANTITY, quantity);
            pdpQuantity = quantity;
            log.info("Quantity " + quantity + " is entered for hot rolled sheet");
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter quantity for hot rolled sheet", e);
        }

    }

    @And("add to cart")
    public void addToCart() {

        //Copy PDP produuct Name
        try {
            pdpProductName = waitAndGetText(PDP_PRODUCT_NAME);
            log.info("Product name " + pdpProductName + " is fetched from PDP");
            if (pdpProductName.isEmpty()) {
                log.info("Product name is empty on PDP");
                softAssert.assertTrue(false, "Product name is empty on PDP");
            }
        } catch (Exception e) {
            log.info("Failed to get product name from PDP");
        }

        //Click Add-to-cart button
        try {
            sleep(1000);
            waitAndCheckIsElementClickable(ADD_TO_CART_CTA);
            waitAndClickOn(ADD_TO_CART_CTA);
            log.info("Clicked on add to cart button");
            skuCount = skuCount + 1;
        } catch (Exception e) {
            throw new RuntimeException("Failed to click on add to cart button", e);
        }
    }

    @And("click view cart")
    public void clickViewCart() {

        //Click View Cart
        try {
            waitAndCheckIsElementClickable(VIEW_CART);
            waitAndClickOn(VIEW_CART);
            log.info("Clicked on view cart button");
        } catch (Exception e) {
            throw new RuntimeException("Failed to click View Cart button", e);
        }
        Assert.assertTrue(waitAndCheckIsElementPresent(SUBMIT_REQUIREMENT), "Did not open the cart page after clicking on view cart");
    }

    @Then("verify the Attribute values for Cement in cart page")
    public void verifyTheAttributeValuesForCementInCartPage() {
        //Verify product name in cart page for cement
        softAssert.assertTrue(isElementPresent(CART_PAGE_PRODUCT_NAME, pdpProductName), "Product name in cart page is not matching with product name in PDP or product name is not displayed in cart page");

        softAssert.assertEquals(waitAndGetText(CART_COUNT), "1", "Cart count is not updated in cart icon after adding product to cart");
        //Verify the quantity in cart page for cement
        cartPageQuantity = waitAndGetText(CART_PAGE_QUANTITY);
        softAssert.assertEquals(cartPageQuantity, "Qty: " + pdpQuantity + " bags", "Quantity in cart page is not matching with selected quantity in PDP or quantity is not displayed in cart page");
    }

    @Then("verify the Attribute values for HR Sheet in cart page")
    public void verifyTheAttributeValuesForHRSheetInCartPage() {

        cartAttributeValue = waitAndGetText(CART_PAGE_ATTRIBUTE_VALUE);
        softAssert.assertFalse(cartAttributeValue.isEmpty(), "Attribute value is not displayed in cart page");
        softAssert.assertEquals(cartAttributeValue, pdpThickness + " x " + pdpWidth + " x " + pdpLength + " mm", "Attribute value in cart page is not matching with selected attribute value in PDP");
        softAssert.assertTrue(isElementPresent(CART_PAGE_PRODUCT_NAME, pdpProductName), "Product name in cart page is not matching with product name in PDP or product name is not displayed in cart page");
        softAssert.assertEquals(waitAndGetText(CART_COUNT), String.valueOf(skuCount), "Cart count is not updated in cart icon after adding product to cart");

    }

    @Then("Delete the line item from cart")
    public void deleteTheLineItemFromCart() {

        //Delete the line item from cart
        try {
            waitAndClickOn(DELETE_LINE_ITEM);
            if (isElementPresent(DELETE_CTA_BUTTON)) {
                waitAndCheckIsElementClickable(DELETE_CTA_BUTTON);
                waitAndClickOn(DELETE_CTA_BUTTON);
                softAssert.assertTrue(isElementPresent(EMPTY_CART_MESSAGE, "After deleting line item, empty cart message is not displayed"));
                softAssert.assertEquals(waitAndGetText(EMPTY_CART_COUNT), "", "Cart count is not updated in cart icon after deleting product from cart");
                log.info("Deleted successfully the line item from cart");
            }
        } catch (Exception e) {
            log.info("Failed to delete line item from cart");
            throw new RuntimeException("Failed to delete line item from cart", e);
        }
    }

    @And("move to Cold rolled category")
    public void moveToColdRolledCategory() {
        //Move mouse to Cold rolled category
        actions.moveToElement(Grid.driver().findElement(By.xpath(coldRolledCategory))).perform();
    }

    @And("click on CR Coil product")
    public void clickOnCRCoilProduct() {
        //Move mouse to Cold rolled coil sub category and click on it
        actions.moveToElement(Grid.driver().findElement(By.xpath(coldRolledCoilsSubCategory))).click().perform();
    }

    @And("open CR Coil pdp {string} product")
    public void openCRCoilPdp(String product) {
        //Scroll and click on it to open CR Coil PDP
        WebElement locator = Grid.driver().findElement(By.xpath(plpFirstProduct));
        scrollingToViewAndClickUsingJS(locator);
        waitAndCheckIsElementClickable(PLP_FIRST_PRODUCT, product);
        waitAndClickOn(PLP_FIRST_PRODUCT, product);
    }

    @And("select thickness {string} width {string} quantity {string} for CR Coil")
    public void selectAllTheCRCoilAttribute(String thickness, String width, String quantity) {

        //Select thickness for CR Coil
        try {
            if (isElementPresent(ATTRIBUTE, thickness)) {
                waitAndCheckIsElementClickable(ATTRIBUTE, thickness);
                waitAndClickOn(ATTRIBUTE, thickness);
                log.info("Thickness value " + thickness + " mm is selected for cold rolled coil");
                pdpThickness = thickness;
            } else {
                log.info("Thickness value " + thickness + " mm is not available for selection, selecting available thickness value");
                Allure.addAttachment("Thickness value not available", "Thickness value " + thickness + "mm is not available for selection, selecting available thickness value");
                waitAndCheckIsElementClickable(THICKNESS);
                waitAndClickOn(THICKNESS, "2");
                pdpThickness = waitAndGetText(THICKNESS, "2");
            }
        } catch (Exception e) {
            log.info("Failed to click cold rolled coil thickness");
            throw new RuntimeException("Failed to click cold rolled coil thickness", e);
        }

        //Select width for CR Coil
        try {
            if (isElementPresent(ATTRIBUTE, width)) {
                waitAndCheckIsElementClickable(ATTRIBUTE, width);
                waitAndClickOn(ATTRIBUTE, width);
                log.info("Width value " + width + " mm is selected for cold rolled coil");
                pdpWidth = width;
            } else {
                log.info("Width value " + width + "mm is not available for selection, selecting available width value");
                Allure.addAttachment("Width value not available", "Width value " + width + "mm is not available for selection, selecting available width value");
                waitAndCheckIsElementClickable(WIDTH);
                waitAndClickOn(WIDTH, "1");
                pdpWidth = waitAndGetText(WIDTH, "1");
            }
        } catch (Exception e) {
            log.info("Failed to click cold rolled coil width");
            throw new RuntimeException("Failed to click cold rolled coil width", e);
        }

        //Enter quantity for CR Coil
        try {
            waitAndCheckIsElementClickable(QUANTITY);
            waitAndSetText(QUANTITY, "50");
            log.info("Quantity " + quantity + " is entered for cold rolled coil");
        } catch (Exception e) {
            log.info("Failed to enter quantity for cold rolled coil");
            throw new RuntimeException("Failed to enter quantity for cold rolled coil", e);
        }
    }

    @Then("verify the Attribute values for CR coil in cart page")
    public void verifyTheAttributeValuesForCRCoilinCartPage() {

        //verify quantity in cart page
        cartPageQuantity = waitAndGetText(CART_PAGE_QUANTITY);
        softAssert.assertEquals(cartPageQuantity, "Qty: " + pdpQuantity + " MT");

        //verify cart attribute value for CR Coil
        cartAttributeValue = waitAndGetText(CART_PAGE_ATTRIBUTE_VALUE);
        softAssert.assertFalse(cartAttributeValue.isEmpty(), "Attribute value is not displayed in cart page");
        softAssert.assertEquals(cartAttributeValue, pdpThickness + " x " + pdpWidth + " mm", "Attribute value in cart page is not matching with selected attribute value in PDP");
        softAssert.assertEquals(waitAndGetText(CART_COUNT), "1", "Cart count is not updated in cart icon after adding product to cart");
        //verify product name in cart page for CR Coil
        softAssert.assertTrue(isElementPresent(CART_PAGE_PRODUCT_NAME, pdpProductName), "Product name in cart page is not matching with product name in PDP or product name is not displayed in cart page");

    }

    @And("verify the updated Attribute values for CR coil in cart page")
    public void verifyTheUpdatedAttributeValuesForCRCoilinCartPage() {
        //verify updated quantity in cart page
        cartPageQuantity = waitAndGetText(CART_PAGE_QUANTITY);
        softAssert.assertEquals(cartPageQuantity, "Qty: " + pdpQuantity + " MT");

        //verify updated cart attribute value for CR Coil
        afterUpdateCartAttributeValue = waitAndGetText(CART_PAGE_ATTRIBUTE_VALUE);
        softAssert.assertEquals(afterUpdateCartAttributeValue, updatedThickness + " x " + updatedWidth + " mm", "After update, Attribute value in cart page is not matching with selected attribute value in edit line item");

    }

    @And("click on CR Sheet product")
    public void clickOnCRSheetProduct() {
        //Reading locator from page factory
        actions.moveToElement(Grid.driver().findElement(By.xpath(coldRolledSheetsSubCategory))).click().perform();
    }

    @And("open CR Sheet pdp")
    public void openCRSheetPdp() {
        WebElement locator = Grid.driver().findElement(By.xpath(plpFirstProduct));
        scrollingToViewAndClickUsingJS(locator);
        waitAndClickOn(PLP_FIRST_PRODUCT, "1");
    }

    @And("select thickness {string} width {string} length {string} quantity {string} for CR Sheet")
    public void selectAllTheCRSheetAttribute(String thickness, String width, String length, String quantity) {

        try {
            if (isElementPresent(ATTRIBUTE, thickness)) {
                waitAndCheckIsElementClickable(ATTRIBUTE, thickness);
                waitAndClickOn(ATTRIBUTE, thickness);
                log.info("Thickness value " + thickness + "mm is selected for cold rolled sheet");
                pdpThickness = thickness;
            } else {
                log.info("Thickness value " + thickness + "mm is not available for selection, selecting available thickness value");
                Allure.addAttachment("Thickness value not available", "Thickness value " + thickness + "mm is not available for selection, selecting available thickness value");
                waitAndCheckIsElementClickable(THICKNESS);
                waitAndClickOn(THICKNESS, "2");
                pdpThickness = waitAndGetText(THICKNESS, "2");
            }
        } catch (Exception e) {
            log.info("Failed to click cold rolled sheet thickness");
            throw new RuntimeException("Failed to click cold rolled sheet thickness", e);
        }

        try {
            if (isElementPresent(ATTRIBUTE, width)) {
                waitAndCheckIsElementClickable(ATTRIBUTE, width);
                waitAndClickOn(ATTRIBUTE, width);
                log.info("Width value " + width + "mm is selected for cold rolled sheet");
                pdpWidth = width;
            } else {
                log.info("Width value " + width + "mm is not available for selection, selecting available width value");
                Allure.addAttachment("Width value not available", "Width value " + width + "mm is not available for selection, selecting available width value");
                waitAndCheckIsElementClickable(WIDTH);
                waitAndClickOn(WIDTH, "1");
                pdpWidth = waitAndGetText(WIDTH, "1");
            }
        } catch (Exception e) {
            log.info("Failed to click cold rolled sheet width");
            throw new RuntimeException("Failed to click cold rolled sheet width", e);
        }

        WebElement locator = Grid.driver().findElement(By.xpath(length));
        scrollingToViewAndClickUsingJS(locator);
        try {
            if (isElementPresent(ATTRIBUTE, length)) {
                waitAndCheckIsElementClickable(ATTRIBUTE, length);
                waitAndClickOn(ATTRIBUTE, length);
                log.info("Length value " + length + "mm is selected for cold rolled sheet");
                pdpLength = length;
            } else {
                log.info("Length value " + length + "mm is not available for selection, selecting available length value");
                Allure.addAttachment("Length value not available", "Length value " + length + "mm is not available for selection, selecting available length value");
                waitAndCheckIsElementClickable(LENGTH);
                waitAndClickOn(LENGTH, "1");
                pdpLength = waitAndGetText(LENGTH, "1");
            }
        } catch (Exception e) {
            log.info("Failed to click cold rolled sheet length");
            throw new RuntimeException("Failed to click cold rolled sheet length", e);
        }


        try {
            waitAndCheckIsElementClickable(QUANTITY);
            waitAndSetText(QUANTITY, quantity);
            log.info("Quantity " + quantity + " is entered for cold rolled sheet");
            pdpQuantity = quantity;
        } catch (Exception e) {
            log.info("Failed to enter quantity for cold rolled sheet");
            throw new RuntimeException("Failed to enter quantity for cold rolled sheet", e);
        }
    }

    @Then("verify the Attribute values for CR Sheet in cart page")
    public void verifyTheAttributeValuesForCRSheetInCartPage() {
        cartAttributeValue = waitAndGetText(CART_PAGE_ATTRIBUTE_VALUE);
        softAssert.assertFalse(cartAttributeValue.isEmpty(), "Attribute value is not displayed in cart page");
        softAssert.assertEquals(cartAttributeValue, pdpThickness + " x " + pdpWidth + " x " + pdpLength + " mm", "Attribute value in cart page is not matching with selected attribute value in PDP");
        softAssert.assertTrue(isElementPresent(CART_PAGE_PRODUCT_NAME, pdpProductName), "Product name in cart page is not matching with product name in PDP or product name is not displayed in cart page");
        softAssert.assertEquals(waitAndGetText(CART_COUNT), "1", "Cart count is not updated in cart icon after adding product to cart");
    }

    @And("verify the updated Attribute values for CR Sheet in cart page")
    public void verifyTheUpdatedAttributeValuesForCRSheetInCartPage() {
        afterUpdateCartAttributeValue = waitAndGetText(CART_PAGE_ATTRIBUTE_VALUE);
        softAssert.assertEquals(afterUpdateCartAttributeValue, updatedThickness + " x " + updatedWidth + " x " + updatedLength + " mm", "After update, Attribute value in cart page is not matching with selected attribute value in edit line item");

    }

    @When("click on Cement product")
    public void clickOnCementProduct() {
        try {
            waitAndClickOn(CEMENT_PLP);
            log.info("Opened cement PLP page");
        } catch (Exception e) {
            log.fine("Failed to open PLP Cement");
            throw new RuntimeException("Failed to open PLP Cement", e);
        }
    }

    @And("open Cement pdp {string} product")
    public void openCementPdp(String product) {
        try {
            waitAndCheckIsElementClickable(CEMENT_PDP, product);
            waitAndClickOn(CEMENT_PDP, product);
        } catch (Exception e) {
            log.info("Failed to open PDP cement page");
            throw new RuntimeException("Failed to open PDP cement page", e);
        }
    }

    @And("enter quantity {string} for Cement")
    public void selectAllTheCementAttribute(String quantity) {
        try {
            waitAndCheckIsElementClickable(CEMENT_QUANTITY);
            waitAndSetText(CEMENT_QUANTITY, quantity);
            log.info("Quantity " + quantity + " is entered for cement");
            pdpQuantity = quantity;
        } catch (Exception e) {
            log.info("Failed to enter quantity for cement");
            throw new RuntimeException("Failed to enter quantity for cement", e);
        }
    }

    @And("click to Coated steel category")
    public void clickToCoatedSteelCategory() {
        try {
            actions.moveToElement(Grid.driver().findElement(By.xpath(coatedSteel))).perform();
            log.info("cursor moved to Coated Steel");
        } catch (Exception e) {
            throw new RuntimeException("mouse failed to move to coated steel", e);

        }
    }

    @And("click on Coated steel sheet product")
    public void clickOnCoatedSteelSheetProduct() {
        try {
            waitAndClickOn(GALVANIZED_SHEET);
            log.info("Click on galvanized sheet");
        } catch (Exception e) {
            log.info("Failed to click galvanized sheet");
            throw new RuntimeException("Failed to click galvanized sheet", e);
        }
    }

    @And("open Coated steel sheet pdp {string} product")
    public void openCoatedSteelSheetPDP(String product) {
        try {
            sleep(3000);
            waitAndClickOn(PLP_FIRST_PRODUCT, product);
            log.info("Clicked on first product of Coated steel sheet category");
        } catch (Exception e) {
            throw new RuntimeException("Failed to click on first product of Coated steel sheet category", e);
        }
    }

    @And("select thickness {string} width {string} length {string} coating grade {string} spangle type {string} quantity {string} for Colour Coated Sheet")
    public void selectAllTheColourCoatedSheetAttribute(String thickness, String width, String length, String coatingGrade, String spangleType, String quantity) {
        try {
            if (isElementPresent(ATTRIBUTE, thickness)) {
                waitAndClickOn(ATTRIBUTE, thickness);
                pdpThickness = thickness;
                log.fine("Thickness value " + thickness + "mm is selected for Colour Coated Sheet");
            } else {
                log.info("Thickness value " + thickness + "mm is not available for selection, selecting available thickness value");
                Allure.addAttachment("Thickness value not available", "Thickness value " + thickness + "mm is not available for selection, selecting available thickness value");
                waitAndCheckIsElementClickable(THICKNESS);
                waitAndClickOn(THICKNESS, "2");
                pdpThickness = waitAndGetText(THICKNESS, "2");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Colour Coated Sheet thickness", e);
        }


        try {
            if (isElementPresent(ATTRIBUTE, width)) {
                waitAndClickOn(ATTRIBUTE, width);
                pdpWidth = width;
                log.info("Width value " + width + "mm is selected for Colour Coated Sheet");
            } else {
                log.info("Width value " + width + "mm is not available for selection, selecting available width value");
                Allure.addAttachment("Width value not available", "Width value " + width + "mm is not available for selection, selecting available width value");
                waitAndCheckIsElementClickable(WIDTH);
                waitAndClickOn(WIDTH, "1");
                pdpWidth = waitAndGetText(WIDTH, "1");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Colour Coated Sheet Width", e);
        }


        WebElement locator = Grid.driver().findElement(By.xpath(CategoryPages.length));
        scrollingToViewAndClickUsingJS(locator);
        try {
            if (isElementPresent(ATTRIBUTE, length)) {
                waitAndClickOn(ATTRIBUTE, length);
                pdpLength = length;
                log.info("Length value " + length + "mm is selected for Colour Coated Sheet");
            } else {
                log.info("Length value " + length + "mm is not available for selection, selecting available length value");
                Allure.addAttachment("Length value not available", "Length value " + length + "mm is not available for selection, selecting available length value");
                waitAndCheckIsElementClickable(LENGTH);
                waitAndClickOn(LENGTH, "1");
                pdpLength = waitAndGetText(LENGTH, "1");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Colour Coated Sheet Length", e);
        }


        try {
            if (isElementPresent(ATTRIBUTE, coatingGrade)) {
                waitAndClickOn(ATTRIBUTE, coatingGrade);
                log.info("Coating Grade value " + coatingGrade + " is selected for Colour Coated Sheet");
                pdpCoatingGrade = coatingGrade;
            } else {
                log.info("Coating Grade value " + coatingGrade + " is not available for selection, selecting available coating grade value");
                Allure.addAttachment("Coating Grade value not available", "Coating Grade value " + coatingGrade + " is not available for selection, selecting available coating grade value");
                waitAndClickOn(COATING_GRADE, "1");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Colour Coated Sheet Coating Grade", e);
        }

        try {
            if (isElementPresent(ATTRIBUTE, spangleType)) {
                waitAndClickOn(ATTRIBUTE, spangleType);
                log.info("Spangle Type value " + spangleType + " is selected for Colour Coated Sheet");
                pdpSpangleType = spangleType;
            } else {
                log.info("Spangle Type value " + spangleType + " is not available for selection, selecting available spangle type value");
                Allure.addAttachment("Spangle Type value not available", "Spangle Type value " + spangleType + " is not available for selection, selecting available spangle type value");
//                waitAndClickOn(SPANGLE_TYPE, "1");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Colour Coated Sheet Spangle Type", e);
        }


        try {
            waitAndCheckIsElementClickable(QUANTITY);
            waitAndSetText(QUANTITY, quantity);
            pdpQuantity = quantity;
            log.info("Quantity " + quantity + " is entered for Colour Coated Sheet");
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter Quantity for Colour Coated Sheet", e);
        }
    }

    @Then("verify the Attribute values for Coated Steel Sheet in cart page")
    public void verifyTheAttributeValuesForCoatedSteelSheetInCartPage() {
        cartAttributeValue = waitAndGetText(CART_PAGE_ATTRIBUTE_VALUE);
        softAssert.assertFalse(cartAttributeValue.isEmpty(), "Attribute value is not displayed in cart page");
        softAssert.assertEquals(cartAttributeValue, pdpThickness + " x " + pdpWidth + " x " + pdpLength + " mm", "Attribute value in cart page is not matching with selected attribute value in PDP");
        softAssert.assertTrue(isElementPresent(CART_PAGE_PRODUCT_NAME, pdpProductName), "Product name in cart page is not matching with product name in PDP or product name is not displayed in cart page");

        cartPageSpangle = waitAndGetText(CART_PAGE_SPANGLE);
        softAssert.assertEquals(cartPageSpangle, "Spangle type: " + pdpSpangleType, "Spangle type value in cart page is not matching with selected spangle type value in PDP or spangle type value is not displayed in cart page");

        cartPageCoatingGrade = waitAndGetText(CART_PAGE_COATING_GRADE);
        softAssert.assertEquals(cartPageCoatingGrade, "Coating grade: " + pdpCoatingGrade + " GSM", "Coating grade value in cart page is not matching with selected coating grade value in PDP or coating grade value is not displayed in cart page");
        softAssert.assertEquals(waitAndGetText(CART_COUNT), "1", "Cart count is not updated in cart icon after adding product to cart");
    }

    @And("click on Wire product")
    public void clickOnWireProduct() {
        try {
            //Reading locator from page factory
            actions.moveToElement(Grid.driver().findElement(By.xpath(wireCategory))).click().perform();
            log.info("Clicked on Wire category");
        } catch (Exception e) {
            log.info("Failed to click on Wire category");
            throw new RuntimeException("Failed to click on Wire category", e);
        }
    }

    @And("open Wire pdp")
    public void openWirePdp() {
        WebElement locator = Grid.driver().findElement(By.xpath(plpFirstProduct));
        scrollingToViewAndClickUsingJS(locator);
        try {
            waitAndCheckIsElementClickable(PLP_FIRST_PRODUCT);
            waitAndClickOn(PLP_FIRST_PRODUCT, "1");
        } catch (Exception e) {
            log.info("Failed to click on Wire category");
            throw new RuntimeException("Failed to click on Wire category", e);
        }
    }

    @And("select diameter {string} quantity {string} for Wire")
    public void selectAllTheWireAttribute(String diameter, String quantity) {
        //Select diameter for Wire
        try {
            if (isElementPresent(ATTRIBUTE, diameter)) {
                waitAndClickOn(ATTRIBUTE, diameter);
                log.info("Diameter value " + diameter + "mm is selected for Wire");
                pdpDiameter = diameter;
            } else {
                log.info("Diameter value " + diameter + "mm is not available for selection, selecting available diameter value");
                Allure.addAttachment("Diameter value not available", "Diameter value " + diameter + "mm is not available for selection, selecting available diameter value");
                waitAndClickOn(DIAMETER, "1");
                pdpDiameter = waitAndGetText(DIAMETER, "1");
            }
        } catch (Exception e) {
            log.info("Failed to click diameter in Wire PDP page");
            throw new RuntimeException("Failed to click diameter in Wire PDP page", e);
        }

        //Enter quantity for Wire
        try {
            waitAndCheckIsElementClickable(QUANTITY);
            waitAndSetText(QUANTITY, quantity);
            log.info("Enter quantity " + quantity + " for Wire");
            pdpQuantity = quantity;
        } catch (Exception e) {
            log.info("Failed to enter quantity for Wire");
            throw new RuntimeException("Failed to enter quantity for Wire", e);
        }
    }

    @Then("verify the Attribute values for Wire in cart page")
    public void verifyTheAttributeValuesForWireInCartPage() {
        //verify quantity in cart page
        cartPageQuantity = waitAndGetText(CART_PAGE_QUANTITY);
        softAssert.assertEquals(cartPageQuantity, "Qty: " + pdpQuantity + " MT");

        softAssert.assertEquals(waitAndGetText(CART_COUNT), "1", "Cart count is not updated in cart icon after adding product to cart");

        //verify diameter in cart page
        cartAttributeValue = waitAndGetText(CART_PAGE_ATTRIBUTE_VALUE);
        softAssert.assertFalse(cartAttributeValue.isEmpty(), "Attribute value is not displayed in cart page");
        softAssert.assertEquals(cartAttributeValue, pdpDiameter + " mm", "Attribute value in cart page is not matching with selected attribute value in PDP");

        //verify product Name in cart Page
        softAssert.assertTrue(isElementPresent(CART_PAGE_PRODUCT_NAME, pdpProductName, "Wire Product Name is not matching in cart page or product name is not displayed in cart page"));
    }

    @And("verify the updated Attribute values for Wire in cart page")
    public void verifyTheUpdatedAttributeValuesForWireInCartPage() {
        //verify quantity in cart page
        cartPageQuantity = waitAndGetText(CART_PAGE_QUANTITY);
        softAssert.assertEquals(cartPageQuantity, "Qty: " + pdpQuantity + " MT");

        afterUpdateCartAttributeValue = waitAndGetText(CART_PAGE_ATTRIBUTE_VALUE);
        softAssert.assertEquals(afterUpdateCartAttributeValue, updatedDiameter + " mm", "After update, Attribute value in cart page is not matching with selected attribute value in edit line item");
    }

    @When("click on TMT product")
    public void clickOnTMTProduct() {
        try {
            waitAndClickOn(TMT);
            log.info("Clicked on TMT category");
        } catch (Exception e) {
            log.info("Failed to click on TMT category");
            throw new RuntimeException("Failed to click on TMT category", e);
        }
    }

    @And("click on TMT plp {string} product")
    public void openTMTPdp(String product) {
        try {
            WebElement locator = Grid.driver().findElement(By.xpath(plpFirstProduct));
            scrollingToViewAndClickUsingJS(locator);
            waitAndClickOn(PLP_FIRST_PRODUCT, product);
            log.info("Opened TMT PDP page");
        } catch (Exception e) {
            log.info("Failed to open TMT Rebar PDP");
            throw new RuntimeException("Failed to open TMT Rebar PDP", e);
        }
    }

    @And("select diameter {string} form {string} quantity {string} for TMT")
    public void selectAllTheTMTRebarAttribute(String diameter, String form, String quantity) {

        //Select diameter for TMT
        try {
            if (isElementPresent(ATTRIBUTE, diameter)) {
                waitAndClickOn(ATTRIBUTE, diameter);
                log.info("Diameter value " + diameter + "mm is selected for TMT");
                pdpDiameter = diameter;
            } else {
                log.info("Diameter value " + diameter + "mm is not available for selection, selecting available diameter value");
                waitAndClickOn(DIAMETER, "1");
                pdpDiameter = waitAndGetText(DIAMETER, "1");
            }
        } catch (Exception e) {
            log.info("Failed to select TMT diameter");
            throw new RuntimeException("Failed to select TMT diameter", e);
        }

        //Select form for TMT
        try {
            if (isElementPresent(ATTRIBUTE, form)) {
                waitAndClickOn(ATTRIBUTE, form);
                log.info("Form value " + form + " Selected for TMT");
                pdpForm = form;
            } else {
                log.info("Form value " + form + "mm is not available for selection, selecting available form value");
                waitAndClickOn(FORM, "1");
                pdpForm = waitAndGetText(FORM, "1");

            }
        } catch (Exception e) {
            log.info("Failed to click form in TMT PDP page");
            throw new RuntimeException("Failed to click form in TMT PDP page", e);
        }

        //Enter quantity for TMT
        try {
            waitAndCheckIsElementClickable(QUANTITY);
            waitAndSetText(QUANTITY, quantity);
            log.info("Enter TMT Quantity in PDP page ");
            pdpQuantity = quantity;
        } catch (Exception e) {
            log.info("Failed to enter TMT Quantity in PDP page");
            throw new RuntimeException("Failed to enter TMT Quantity in PDP page", e);
        }
    }

    @Then("verify the Attribute values for TMT in cart page")
    public void verifyTheAttributeValuesForTmtInCartPage() {
        //verify product Name in cart Page
        softAssert.assertTrue(isElementPresent(CART_PAGE_PRODUCT_NAME, pdpProductName, "TMT Product Name is not matching in cart page or product name is not displayed in cart page"));

        //verify quantity in cart page
        cartPageQuantity = waitAndGetText(CART_PAGE_QUANTITY);
        softAssert.assertEquals(cartPageQuantity, "Qty: " + pdpQuantity + " MT");

        softAssert.assertEquals(waitAndGetText(CART_COUNT), String.valueOf(skuCount), "Cart count is not updated in cart icon after adding product to cart");

    }

    @When("move the mouse to Structural category")
    public void moveTheMouseToStructuralCategory() {
        //Reading locator from page factory
        actions.moveToElement(Grid.driver().findElement(By.xpath(structuralSteelCategory))).perform();
    }

    @And("click on Angles product")
    public void clickOnAnglesProduct() {
        //Reading locator from page factory
        actions.moveToElement(Grid.driver().findElement(By.xpath(anglesSubCategory))).click().perform();
    }

    @And("open Angles pdp")
    public void openAnglesPdp() {
        WebElement locator = Grid.driver().findElement(By.xpath(plpFirstProduct));
        scrollingToViewAndClickUsingJS(locator);
        waitAndCheckIsElementClickable(PLP_FIRST_PRODUCT);
        waitAndClickOn(PLP_FIRST_PRODUCT, "1");
    }

    @And("select all the Angles attribute")
    public void selectAllTheAnglesAttribute() {
        waitAndCheckIsElementClickable(LEG_LENGTH);
        waitAndClickOn(LEG_LENGTH, "1");

        waitAndCheckIsElementClickable(THICKNESS);
        waitAndClickOn(THICKNESS, "1");

        WebElement locator = Grid.driver().findElement(By.xpath(length));
        scrollingToViewAndClickUsingJS(locator);
        waitAndCheckIsElementClickable(LENGTH);
        waitAndClickOn(LENGTH, "1");

        waitAndCheckIsElementClickable(QUANTITY);
        waitAndSetText(QUANTITY, "50");

    }

    @And("click on Channels product")
    public void clickOnChannelsProduct() {
        //Reading locator from page factory
        actions.moveToElement(Grid.driver().findElement(By.xpath(channelsSubCategory))).click().perform();
    }

    @And("open Channels pdp")
    public void openChannelsPdp() {
        WebElement locator = Grid.driver().findElement(By.xpath(plpFirstProduct));
        scrollingToViewAndClickUsingJS(locator);
        waitAndClickOn(PLP_FIRST_PRODUCT, "1");
    }

    @And("select all the Channels attribute")
    public void selectAllTheChannelsAttribute() {
        waitAndCheckIsElementClickable(FLANGE_WIDTH);
        waitAndClickOn(FLANGE_WIDTH, "1");

        waitAndCheckIsElementClickable(WEB_DEPTH);
        waitAndClickOn(WEB_DEPTH, "1");

        waitAndCheckIsElementClickable(SECTION_WEIGHT);
        waitAndClickOn(SECTION_WEIGHT, "1");

        WebElement locator = Grid.driver().findElement(By.xpath(length));
        scrollingToViewAndClickUsingJS(locator);
        waitAndCheckIsElementClickable(LENGTH);
        waitAndClickOn(LENGTH, "1");

        waitAndCheckIsElementClickable(QUANTITY);
        waitAndSetText(QUANTITY, "50");
    }

    @And("click on Beams & Columns product")
    public void clickOnBeamsColumnsProduct() {
        //Reading locator from page factory
        actions.moveToElement(Grid.driver().findElement(By.xpath(beamsAndColumnsSubCategory))).click().perform();
    }

    @And("open Beams & Columns pdp")
    public void openBeamsColumnsPdp() {
        WebElement locator = Grid.driver().findElement(By.xpath(plpFirstProduct));
        scrollingToViewAndClickUsingJS(locator);
        waitAndClickOn(PLP_FIRST_PRODUCT, "1");
    }

    @And("select all the Beams & Columns attribute")
    public void selectAllTheBeamsColumnsAttribute() {
        waitAndCheckIsElementClickable(FLANGE_WIDTH);
        waitAndClickOn(FLANGE_WIDTH, "1");

        waitAndCheckIsElementClickable(WEB_DEPTH);
        waitAndClickOn(WEB_DEPTH, "1");

        waitAndCheckIsElementClickable(SECTION_WEIGHT);
        waitAndClickOn(SECTION_WEIGHT, "1");

        WebElement locator = Grid.driver().findElement(By.xpath(length));
        scrollingToViewAndClickUsingJS(locator);
        waitAndCheckIsElementClickable(LENGTH);
        waitAndClickOn(LENGTH, "1");

        waitAndCheckIsElementClickable(QUANTITY);
        waitAndSetText(QUANTITY, "50");
    }

    @And("click edit")
    public void clickEdit() {
        try {
            waitAndClickOn(EDIT_LINE_ITEM);
            log.info("Clicked on edit line item");
            sleep(1000);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click on edit line item", e);
        }
    }

    @And("update Quantity {string}")
    public void updateQuantity(String updateQuantity) {
        try {
            sleep(500);
            waitAndCheckIsElementClickable(EDIT_QUANTITY, updateQuantity);
            clearTextBox(EDIT_QUANTITY);
            waitAndSetText(EDIT_QUANTITY, updateQuantity);
            updatedQuantity = updateQuantity;
            log.info("Updated quantity from " + pdpQuantity + " to " + updateQuantity + " in edit line item for " + pdpProductName);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update the Quantity for " + pdpProductName, e);
        }
    }

    @And("Save edit changes")
    public void saveEditChanges() {
        try {
            waitAndClickOn(SAVE_EDIT);
            log.info("clicked on save edit button");
        } catch (Exception e) {
            throw new RuntimeException("Save edit button is not clickable", e);
        }
        waitUntilElementDisappears(editEnterQuantity);
    }

    @And("Verify the updated Attribute values for HR Coil in cart page")
    public void verifyTheUpdatedAttributeValuesForHRCoilinCartPage() {
        //verify quantity in cart page
        cartPageQuantity = waitAndGetText(CART_PAGE_QUANTITY);
        softAssert.assertEquals(cartPageQuantity, "Qty: " + pdpQuantity + " MT");

        afterUpdateCartAttributeValue = waitAndGetText(CART_PAGE_ATTRIBUTE_VALUE);
        softAssert.assertEquals(afterUpdateCartAttributeValue, updatedThickness + " x " + updatedWidth + " mm", "After update, Attribute value in cart page is not matching with selected attribute value in edit line item");


    }

    @Then("verify the updated Attribute values for Cement in cart page")
    public void verifyTheUpdatedAttributeValuesForCementInCartPage() {
        //Verify the updated quantity in cart page
        cartPageQuantity = waitAndGetText(CART_PAGE_QUANTITY);
        softAssert.assertEquals(cartPageQuantity, "Qty: " + updatedQuantity + " bags", "After update, Quantity in cart page is not matching with updated quantity in edit line item or quantity is not displayed in cart page");
    }

    @And("verify the updated Attribute values for HR Sheet in cart page")
    public void verifyTheUpdatedAttributeValuesForHRSheetInCartPage() {
        afterUpdateCartAttributeValue = waitAndGetText(CART_PAGE_ATTRIBUTE_VALUE);
        softAssert.assertEquals(afterUpdateCartAttributeValue, updatedThickness + " x " + updatedWidth + " x " + updatedLength + " mm", "After update, Attribute value in cart page is not matching with selected attribute value in edit line item");
    }

    @And("update Thickness to {string} dropdown")
    public void updateThickness(String updateThickness) {
        try {
            waitAndClickOn(EDIT_THICKNESS_DROPDOWN);
            log.info("Clicked on edit thickness dropdown");
            sleep(2000);
            if (isElementPresent(UPDATE_ATTRIBUTE, updateThickness)) {
                waitAndCheckIsElementClickable(UPDATE_ATTRIBUTE, updateThickness);
                waitAndClickOn(UPDATE_ATTRIBUTE, updateThickness);
                updatedThickness = updateThickness;
                log.info("Updated thickness from " + pdpThickness + "mm to " + updateThickness + "mm in edit line item");
            } else {
                log.info("Given update thickness " + updateThickness + "mm is not available for selection, selecting available update thickness value");
                Allure.addAttachment("Update Thickness value not available", "Given update thickness " + updateThickness + "mm is not available for selection, selecting available update thickness value");
                waitAndCheckIsElementClickable(EDIT_THICKNESS, "3");
                updatedThickness = waitAndGetText(EDIT_THICKNESS, "3");
                waitAndClickOn(EDIT_THICKNESS, "3");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to update thickness in edit line item", e);
        }
    }

    @And("update Width to {string} dropdown")
    public void updateWidth(String updateWidth) {
        try {
            waitAndClickOn(EDIT_WIDTH_DROPDOWN);
            log.info("Clicked on edit width dropdown");
            if (isElementPresent(UPDATE_ATTRIBUTE, updateWidth)) {
                waitAndCheckIsElementClickable(UPDATE_ATTRIBUTE, updateWidth);
                waitAndClickOn(UPDATE_ATTRIBUTE, updateWidth);
                updatedWidth = updateWidth;
                log.info("Updated width from" + pdpWidth + "mm to " + updateWidth + "mm in edit line item");
            } else {
                log.info("Given update width " + updateWidth + "mm is not available for selection, selecting available update width value");
                waitAndCheckIsElementClickable(EDIT_WIDTH, "2");
                updatedWidth = waitAndGetText(EDIT_WIDTH, "2");
                waitAndClickOn(EDIT_WIDTH, "2");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to update width in edit line item", e);
        }
    }

    @And("update Length to {string} dropdown")
    public void updateLength(String updateLength) {
        try {
            waitAndClickOn(EDIT_LENGTH_DROPDOWN);
            log.info("Clicked on edit length dropdown");
            if (isElementPresent(UPDATE_ATTRIBUTE, updateLength)) {
                waitAndCheckIsElementClickable(UPDATE_ATTRIBUTE, updateLength);
                waitAndClickOn(UPDATE_ATTRIBUTE, updateLength);
                updatedLength = updateLength;
                log.info("Updated length from " + pdpLength + "mm to " + updateLength + "mm in edit line item");
            } else {
                log.info("Given update length " + updateLength + "mm is not available for selection, selecting available update length value");
                Allure.addAttachment("Update Length value not available", "Given update length " + updateLength + "mm is not available for selection, selecting available update length value");
                waitAndCheckIsElementClickable(EDIT_LENGTH, "5");
                updatedLength = waitAndGetText(EDIT_LENGTH, "5");
                waitAndClickOn(EDIT_LENGTH, "5");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to update length in edit line item", e);
        }
    }

    @And("update Diameter to {string}")
    public void updateDiameter(String updateDiameter) {
        try {
            waitAndClickOn(EDIT_DIAMETER_DROPDOWN);
            log.info("Clicked on edit Diameter dropdown");
            boolean result = isElementPresent(UPDATE_ATTRIBUTE, updateDiameter);
            if (isElementPresent(UPDATE_ATTRIBUTE, updateDiameter)) {
                waitAndClickOn(UPDATE_ATTRIBUTE, updateDiameter);
                updatedDiameter = updateDiameter;
                log.info("Updated diameter from " + pdpDiameter + "mm to " + updateDiameter + "mm in edit line item");
            } else {
                log.info("Given update diameter " + updateDiameter + "mm is not available for selection, selecting available update diameter value");
                Allure.addAttachment("Update Length value not available", "Given update length " + updateDiameter + "mm is not available for selection, selecting available update length value");
                updatedDiameter = waitAndGetText(EDIT_LENGTH, "5");
                waitAndClickOn(EDIT_LENGTH, "5");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to update Diameter in edit line item", e);
        }
    }

    @And("update Form to {string}")
    public void updateForm(String updateForm) {
        try {
            waitAndClickOn(EDIT_FORM_DROPDOWN);
            log.info("Clicked on edit Form dropdown");
            if (isElementPresent(UPDATE_ATTRIBUTE, updateForm)) {
                waitAndClickOn(UPDATE_ATTRIBUTE, updateForm);
                updatedDiameter = updateForm;
                log.info("Updated Form from " + pdpForm + "mm to " + updateForm + "mm in edit line item");
            } else {
                log.info("Given update form " + updateForm + "mm is not available for selection, selecting available update Form value");
                Allure.addAttachment("Update Length value not available", "Given update Form " + updateForm + "mm is not available for selection, selecting available update Form value");
                updatedDiameter = waitAndGetText(EDIT_LENGTH, "2");
                waitAndClickOn(EDIT_LENGTH, "2");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to update Form in edit line item", e);
        }
    }

    @And("verify the updated Attribute values for TMT in cart page")
    public void verifyTheUpdatedAttributeValuesForTMTinCartPage() {
        //verify quantity in cart page
        cartPageQuantity = waitAndGetText(CART_PAGE_QUANTITY);
        softAssert.assertEquals(cartPageQuantity, "Qty: " + updatedQuantity + " MT", "Quantity is not updated for TMT");

    }

    @When("click cart icon")
    public void clickCartIcon() {
        waitAndClickOn(EMPTY_CART_COUNT);
        log.info("Clicked on cart icon with empty cart");
    }

    @Then("check the empty cart page display with message {string}")
    public void checkTheEmptyCartPageDisplayWithMessage(String expectedMessage) {
        try {
            String actualMessage = waitAndGetText(EMPTY_CART_MESSAGE);
            softAssert.assertEquals(actualMessage, expectedMessage, "Empty cart message is not matching with expected message or empty cart message is not displayed");
            log.info("Empty cart message is displayed and matching with expected message: " + expectedMessage);
        } catch (Exception e) {
            log.info("Failed to verify empty cart message");
            throw new RuntimeException("Failed to verify empty cart message", e);
        }
    }

    @Then("Verify the cart page SKU and cart icon count and delete one by one")
    public void verifyTheCartPageSKUAndCartIconCountAndDeleteOneByOne() {

        String skuCount = waitAndGetText(CART_PAGE_SKU_COUNT);
        int expectedSkuCount = Integer.parseInt(skuCount.split(" ")[0]);
        System.out.println(skuCount + "========================" + expectedSkuCount);
        int i = 0;
        while (i < expectedSkuCount) {
            waitAndClickOn(DELETE_LINE_ITEM);
            Boolean deleteCartTitleFlag = true;
            if (isElementPresent(DELETE_CTA_BUTTON) && deleteCartTitleFlag) {
                waitAndCheckIsElementClickable(DELETE_CTA_BUTTON);
                waitAndClickOn(DELETE_CTA_BUTTON);
                log.info("Deleted successfully the line item from cart");

                String locator = getLocator(DELETE_CART_TITLE);

                WebElement deleteCartTitle = Grid.driver().findElement(By.xpath(locator));
                System.out.println(deleteCartTitle.getText());
                WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
                deleteCartTitleFlag = wait.until(
                        ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator))
                );
            }
            i++;
        }


    }

}
