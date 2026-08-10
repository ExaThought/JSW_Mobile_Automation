package com.qapitol.jsw.stepdefinition;


import com.qapitol.jsw.context.ScenarioContext;
import com.qapitol.jsw.utils.CustomUtil;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static com.qapitol.jsw.pageFactory.CategoryPages.*;
import static com.qapitol.jsw.utils.CustomUtil.*;
import static com.qapitol.jsw.utils.Hooks.softAssert;

public class CartSteps extends AbstractWebPage {
    private static final String THICKNESS = "(//span[text()='Thickness (mm)']/../following-sibling::div//button)[1]";
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
    private static final String COATING_GRADE = "(//span[text()=\"Coating grade (gsm)\"]/../following-sibling::div/button)[1]";
    private static final String SUCCESS_PAGE_RCV2 = "rcCheckoutSuccessPage";
    private static final String SPANGLE_TYPE = "(//span[text()=\"Spangle type\"]/../following-sibling::div/button)[1]";
    private static final String CART_PAGE = "cartPage";
    private static final String PLP_FIRST_PRODUCT = "plpFirstProduct";
    private static final String LENGTH = "length";
    private static final String DIAMETER = "(//span[text()='Diameter (mm)']/../following-sibling::div//button)[1]";
    private static final String TMT = "tmt";
    private static final String FORM = "(//span[text()='TMT Form']/../following-sibling::div//button[@data-slot=\"button\"])[1]";
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
    private static final String UPDATE_ATTRIBUTE = "(//div[@data-side='bottom']//button[not(@disabled)]//span)[1]";
    private static final String CART_PAGE_ATTRIBUTE_VALUE = "cartPageAttributeValue";
    private static final String EDIT_CART_PAGE_ENTER_QUANTITY = "editCartPageEnterQuantity";
    private static final String CART_PAGE_PRODUCT_NAME = "cartPageProductName";
    private static final String CART_PAGE_QUANTITY = "cartPageQuantity";
    private static final String EDIT_DIAMETER_DROPDOWN = "editDiameterDropdown";
    private static final String EDIT_FORM_DROPDOWN = "editFormDropdown";
    private static final String CART_PAGE_SPANGLE = "cartPageSpangleTypeValue";
    private static final String CART_PAGE_COATING_GRADE = "cartPageCoatingGradeValue";
    private static final String CART_COUNT = "//section//p[contains(normalize-space(.), 'item')]";
    private static final String EMPTY_CART_MESSAGE = "emptyCartMessage";
    private static final String EMPTY_CART_COUNT = "emptyCartCount";
    private static final String CART_PAGE_SKU_COUNT = "cartPageSKUCount";
    private static final String DELETE_CART_TITLE = "deleteCartTitle";
    private static final String CART_SUBTOTAL = "cartSubtotal";
    private static final String CART_TAX = "cartTax";
    private static final String CART_ORDER_TOTAL = "cartOrderTotal";
    private static final By CART_ICON = By.xpath("(//span[text()='Cart'])[2]");
    private static final String DELETE_ITEMS = "(//div[@id='line-item-section']//*[text()='Delete' and not(ancestor::*[@data-index='-1'])])[1]";
    private static final String DELETE_BTN = "//button[@id=\"remove-btn\"]";
    private static final String EMPTY_CART_MSG = "//h2[text()='Your cart is empty']";
    private static final String CUSTOM_WIDTH_MSG = "//label[text()=\"Custom width (mm):\"]/../following-sibling::div//span";
    private static final String CUSTOM_WIDTH_TEXTBOX = "(//span[text()='Width (mm)']/../following-sibling::div//input)[1]";
    private static final String CUSTOM_LENGTH_MSG = "//label[text()=\"Custom length (mm):\"]/../following-sibling::div/span";
    private static final String CUSTOM_LENGTH_TEXTBOX = "//label[text()=\"Custom length (mm):\"]/following-sibling::input";
    private static final String QUANTITY_TEXTBOX = "//input[@id=\"quantity-input\"]";
    private static final String ADD_TO_CART_BTN = "//button[text()=\"Add to cart\"]";
    private static final String VIEWCART = "//button[text()=\"View cart\"]";
    private static final String JSW_LOGO = "(//a[@href=\"/\"])[1]";
    private static final String DELETE_ITEM_MSG = "//div[contains(text(),'Are you sure you want to delete this item from the cart?')]";
    private static final String ITEMS_COUNT = "//div[@id=\"cart-delivery-address\"]/following-sibling::div/div[contains(text(),'item')]";
    private static final String WIRE_COIL = "(//li//a[@href=\"/category/wire-rod-coils\"])";
    private static final String WIDTH_TEXTBOX = "(//span[text()='Width (mm)']/../following-sibling::div//input)[1]";
    private static final String WIDTH_BUTTON = "(//span[text()='Width (mm)']/../following-sibling::div//button)[1]";
    private static final String WIDTH_MSG = "//span[text()='Width (mm)']/../following-sibling::div//span";
    private static final String EDIT_DIAMETER = "(//div[text()=\"Diameter (mm)\"]/following-sibling::div[2]//div[@class=\"p-1 hover-bg\"])[1]";
    private static final String ITEM_NAME = "//h1";
    private static final String WIDTH_AVAILABLE = "(//span[text()='Width (mm)']/../following-sibling::div//button[not(contains(@class, 'disabled'))])[1]";
    private static final String COATING_GRADE_AVAILABLE = "(//span[contains(text(),'Coating grade')]/../following-sibling::div//button[not(contains(@class, 'disabled'))])[1]";
    private static final String FIRST_WIRE_ROD_PRODUCT = "(//h1[text()='Wire rods']/../../following-sibling::div/div/a/div) [1]";
    private static final By ALL_CATEGORY = By.xpath("//span[contains(@class, 'hover') and contains(text(),'All categories')]");
    private static final By MILD_STEEL_CATEGORY = By.xpath("//a[contains(@href, 'mild-steel')]");
    private final String CONFIRM_BUSINESS_DETAILS_DIALOG = "confirmBusinessDetails";
    private static final String wireCategory = "//li/a[contains(@href, 'wire-rods')]";
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
    static String cartValue;
    static String cartPdpProductName;
    public ScenarioContext context;
    Actions actions = new Actions(Grid.driver());

    public CartSteps(ScenarioContext context) {
        this.context = context;
    }

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

        try {
            refreshAndWaitFor(ALL_CATEGORY);
            hoverOverElement(ALL_CATEGORY);
            waits(MILD_STEEL_CATEGORY);
            hoverOverElement(MILD_STEEL_CATEGORY);
            log.info("cursor moved to mild steel category");
        } catch (Exception e) {
            throw new RuntimeException("mouse failed to move to mild steel category", e);

        }


    }

    @And("move to Hot rolled category")
    public void moveToHotRolledCategory() {


        try {
            hoverOverElement(By.xpath(msHotRolled));
            log.info("Click in MS Hot Rolled");

        } catch (Exception e) {
            throw new RuntimeException("mouse failed to move to hot rolled category", e);

        }
    }

    private void clickOnHRSheetProduct() {
        //Reading locator from page factory
        try {
            hoverOverElement(By.xpath(hotRolledCategory));
            clickButton(By.xpath(hotRolledCategory));
            log.info("cursor moved to hot rolled category");
        } catch (Exception e) {
            throw new RuntimeException("Failed to click on hot rolled sheet sub category", e);
        }
    }

    private void clickOnHRCoilProduct() {
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
            sleep(3000);
            waits(By.xpath(plpFirstProduct));
            clickButton(By.xpath(plpFirstProduct));
            log.info("Opened HR Coil PDP page");
        } catch (Exception e) {
            log.info("Failed to click on  hot rolled coil category");
            throw new RuntimeException("Failed to click on hot rolled coil category", e);
        }
    }

    @And("select thickness, width, quantity {string} for HR Coil")
    public void selectAllTheHrCoilAttribute(String quantity) {

        // Select the first available thickness value on the HR Coil PDP.
        try {
            context.cartPdpProductName = getText(By.xpath(ITEM_NAME));
            waits(By.xpath(THICKNESS));
            clickButton(By.xpath(THICKNESS));
            context.pdpThickness = getText(By.xpath(THICKNESS));
            log.info("Selected thickness value '" + context.pdpThickness + "' for HR Coil");
            Allure.addAttachment("Thickness selected", "Selected thickness value: " + context.pdpThickness);
        } catch (Exception e) {
            log.info("Failed to select thickness for HR Coil");
            throw new RuntimeException("Failed to select thickness for HR Coil", e);
        }

        // Select the first available width value on the HR Coil PDP.
        try {
            waits(By.xpath(WIDTH_BUTTON));
            clickButton(By.xpath(WIDTH_BUTTON));
            context.pdpWidth = getText(By.xpath(WIDTH_BUTTON));
            log.info("Selected width value '" + context.pdpWidth + "' for HR Coil");
            Allure.addAttachment("Width selected", "Selected width value: " + context.pdpWidth);
        } catch (Exception e) {
            log.info("Failed to select width for HR Coil");
            throw new RuntimeException("Failed to select width for HR Coil", e);
        }

        // Enter the quantity on the HR Coil PDP.
        try {
            waitAndCheckIsElementClickable(QUANTITY);
            waitAndSetText(QUANTITY, quantity);
            context.pdpQuantity = quantity;
            log.info("Entered quantity '" + quantity + "' for HR Coil");
        } catch (Exception e) {
            log.info("Failed to enter quantity '" + quantity + "' for HR Coil");
            throw new RuntimeException("Failed to enter quantity for HR Coil", e);
        }
    }

    @And("select same attribute values")
    public void selectSameAttributeValues() {
        try {
            waitAndCheckIsElementClickable(ATTRIBUTE, pdpThickness);
            waitAndClickOn(ATTRIBUTE, pdpThickness);
            log.info("Selected same thickness value " + context.pdpThickness + " mm for hot rolled coil");
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
            waitAndSetText(QUANTITY, context.pdpQuantity);
            int quantityValue = Integer.parseInt(pdpQuantity) * 2;
            context.pdpQuantity = String.valueOf(quantityValue);
            log.info("Entered same quantity " + context.pdpQuantity + " for hot rolled coil");
            Grid.driver().findElement(By.xpath(QUANTITY)).sendKeys(Keys.ENTER);
            Grid.driver().findElement(By.xpath(QUANTITY)).sendKeys(Keys.ENTER);
        } catch (Exception e) {
            log.info("Failed to enter same quantity for hot rolled coil");
            throw new RuntimeException("Failed to enter same quantity for hot rolled coil", e);
        }
    }

     @Then("Verify the Attribute values for HR Coil in cart page with cart count {string}")
    public void verifyTheAttributeValuesForHRCoilinCartPage(String count) {
        //verify quantity in cart page
        context.cartPageQuantity = waitAndGetText(CART_PAGE_QUANTITY);
        softAssert.assertEquals( context.cartPageQuantity, "Qty: " +  context.pdpQuantity + " MT");

        softAssert.assertEquals(String.valueOf(getItemCount(getText(By.xpath(CART_COUNT)))), count, "Cart count is not updated in cart icon after adding product to cart");

        context.cartAttributeValue = waitAndGetText(CART_PAGE_ATTRIBUTE_VALUE);
        softAssert.assertFalse( context.cartAttributeValue.isEmpty(), "Attribute value is not displayed in cart page");
        softAssert.assertEquals( context.cartAttributeValue,  context.pdpThickness + " x " +  context.pdpWidth + " mm", "Attribute value in cart page is not matching with selected attribute value in PDP");

        softAssert.assertTrue( context.cartPdpProductName.equals( context.pdpProductName), "Product name in cart page is not matching with product name in PDP or product name is not displayed in cart page");
        log.info("Verifies the cart attributes");

    }

    @Then("Verify attribute values for HR Coil in cart page with cart count {string}")
    public void verifyAttributeValuesForHRCoilinCartPage(String count) {
        //verify quantity in cart page - extract the quantity from the cart text, double it, then validate
        context.cartPageQuantity = waitAndGetText(CART_PAGE_QUANTITY);
        int extractedCartQty = Integer.parseInt(context.cartPageQuantity.replaceAll("[^0-9]", ""));
        int doubledCartQty = extractedCartQty * 2;
        String expectedDoubledQuantity = "Qty: " + (Integer.parseInt(context.pdpQuantity.replaceAll("[^0-9]", "")) * 2) + " MT";
        softAssert.assertEquals("Qty: " + doubledCartQty + " MT", expectedDoubledQuantity,
                "Doubled cart quantity does not match the expected doubled quantity for cart text '" + context.cartPageQuantity + "'");

        softAssert.assertEquals(getItemCount(getText(By.xpath(CART_COUNT))), count, "Cart count is not updated in cart icon after adding product to cart");

        context.cartAttributeValue = waitAndGetText(CART_PAGE_ATTRIBUTE_VALUE);
        softAssert.assertFalse(context.cartAttributeValue.isEmpty(), "Attribute value is not displayed in cart page");
        softAssert.assertEquals(context.cartAttributeValue, context.pdpThickness + " x " + context.pdpWidth + " mm", "Attribute value in cart page is not matching with selected attribute value in PDP");

        softAssert.assertTrue(context.cartPdpProductName.equals(context.pdpProductName), "Product name in cart page is not matching with product name in PDP or product name is not displayed in cart page");
        log.info("Verifies the cart attributes");
        // Cleanup: remove the line item so the cart is left empty for the next run.
        WebElement deleteBtn = Grid.driver().findElement(By.xpath("(//section/div/div/div/button[text()='Delete']) [position() < 2]"));
        jsClick(deleteBtn);
        WebElement deleteItemBtn = Grid.driver().findElement(By.xpath("//p[contains(normalize-space(.), 'delete this item')]/following-sibling::div/button[normalize-space(.)='Delete']"));
        jsClick(deleteItemBtn);
    }

    @And("open HR Sheet plp {string} product")
    public void openHRSheetPdp(String product) {


        //scrollingToViewAndClickUsingJS(locator);
        try {
            WebElement locator = Grid.driver().findElement(By.xpath(plpFirstProduct));
            clickButton(locator);
            log.info("Opened HR Sheet PDP page");
        } catch (Exception e) {
            throw new RuntimeException("Failed to click on first product of hot rolled sheet category", e);
        }
    }

    @And("select thickness, width, length, quantity, for HR Sheet")
    public void selectAllTheHrAttribute() {

        // Select the first available thickness value on the HR Sheet PDP.
        try {
            context.cartPdpProductName = getText(By.xpath(ITEM_NAME));
            clickButton(By.xpath(THICKNESS));
            context.pdpThickness = getText(By.xpath(THICKNESS));
            log.info("Selected thickness value '" + context.pdpThickness + " mm' for HR Sheet");
            Allure.addAttachment("Thickness selected", "Selected thickness value: " + context.pdpThickness + " mm");
        } catch (Exception e) {
            log.info("Failed to select thickness for HR Sheet");
            throw new RuntimeException("Failed to select thickness for HR Sheet", e);
        }

        // Enter a valid custom width on the HR Sheet PDP.
        try {
            waits(By.xpath(WIDTH_AVAILABLE));
            clickButton(By.xpath(WIDTH_AVAILABLE));
            context.pdpWidth = getText(By.xpath(WIDTH_AVAILABLE));
            log.info("Selected width value '" + context.pdpWidth + "' for HR Coil");
            Allure.addAttachment("Width selected", "Selected width value: " + context.pdpWidth);
        } catch (Exception e) {
            log.info("Failed to select width for HR Coil");
            throw new RuntimeException("Failed to select width for HR Coil", e);
        }

        // Scroll to the length attribute and enter a valid custom length.
        WebElement locator = Grid.driver().findElement(By.xpath(CUSTOM_LENGTH_MSG));
        scrollingToViewAndClickUsingJS(locator);
        try {
            waits(By.xpath(CUSTOM_LENGTH_MSG));
            String value = getText(By.xpath(CUSTOM_LENGTH_MSG));
            String lengthValue = String.valueOf(CustomUtil.generateValueFromText(value));
            waits(By.xpath(CUSTOM_LENGTH_TEXTBOX));
            enterText(By.xpath(CUSTOM_LENGTH_TEXTBOX), lengthValue);
            WebElement element = Grid.driver().findElement(By.xpath(CUSTOM_LENGTH_TEXTBOX));
            element.sendKeys(Keys.ENTER);
            context.pdpLength = lengthValue;
            log.info("Entered custom length value '" + context.pdpLength + " mm' for HR Sheet");
        } catch (Exception e) {
            log.info("Failed to enter custom length for HR Sheet");
            throw new RuntimeException("Failed to enter length for HR Sheet", e);
        }

        // Enter the quantity for HR Sheet.
        try {
            waits(By.xpath(QUANTITY_TEXTBOX));
            WebElement qty = Grid.driver().findElement(By.xpath(QUANTITY_TEXTBOX));
            enterText(By.xpath(QUANTITY_TEXTBOX), "2");
            context.pdpQuantity = "2";
            qty.sendKeys(Keys.ENTER);
            skuCount = 2;
            log.info("Entered quantity '2' for HR Sheet");
        } catch (Exception e) {
            log.info("Failed to enter quantity for HR Sheet");
            throw new RuntimeException("Failed to enter quantity for HR Sheet", e);
        }
    }

    @And("add to cart")
    public void addToCart() {

        //Copy PDP produuct Name
        try {
            sleep(1000);
            waits(By.xpath(ADD_TO_CART_BTN));
            WebElement addToCart = Grid.driver().findElement(By.xpath(ADD_TO_CART_BTN));
            clickButton(addToCart);

        } catch (Exception e) {
            log.info("Failed to click on add to cart");
        }

        //Click Add-to-cart button
        try {

            scrollToCenter(By.xpath(ADD_TO_CART_BTN));
            clickButton(By.xpath(ADD_TO_CART_BTN));
            sleep(500);
            //  clickButton(By.xpath(ADD_TO_CART_BTN));
            log.info("Clicked on add to cart button");
            //skuCount = 1;
            context.skuCount = 1;
        } catch (Exception e) {
            log.info("Failed to click on add to cart button" + e.getMessage());
        }
    }

    @And("click view cart")
    public void clickViewCart() {

        //Click View Cart
        try {
            waits(By.xpath(VIEWCART));
            clickButton(By.xpath(VIEWCART));
            log.info("Clicked on view cart button");
            context.pdpProductName = waitAndGetText(PDP_PRODUCT_NAME);
            log.info("Product name " + context.pdpProductName + " is fetched from PDP");
            if (context.pdpProductName.isEmpty()) {
                log.info("Product name is empty on PDP");
                softAssert.assertTrue(false, "Product name is empty on PDP");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to click View Cart button", e);
        }
        Assert.assertTrue(waitAndCheckIsElementPresent(SUBMIT_REQUIREMENT), "Did not open the cart page after clicking on view cart");
    }

    @Then("verify the Attribute values for Cement in cart page")
    public void verifyTheAttributeValuesForCementInCartPage() {
        //Verify product name in cart page for cement
        softAssert.assertTrue(context.cartPdpProductName.equals(context.pdpProductName), "Product name in cart page is not matching with product name in PDP or product name is not displayed in cart page");

        waits(By.xpath(CART_COUNT));
        String cementCartCount = CustomUtil.getItemCount(CustomUtil.getText(By.xpath(CART_COUNT)));
        softAssert.assertEquals(cementCartCount, "1", "Cart count is not updated in cart icon after adding product to cart");
        //Verify the quantity in cart page for cement
        context.cartPageQuantity = waitAndGetText(CART_PAGE_QUANTITY);
        softAssert.assertEquals(context.cartPageQuantity, "Qty: " + context.pdpQuantity + " bags", "Quantity in cart page is not matching with selected quantity in PDP or quantity is not displayed in cart page");
    }

    @Then("verify the Attribute values for HR Sheet in cart page with cart count {string}")
    public void verifyTheAttributeValuesForHRSheetInCartPage(String count) {

        context.cartAttributeValue = waitAndGetText(CART_PAGE_ATTRIBUTE_VALUE);
        softAssert.assertFalse(context.cartAttributeValue.isEmpty(), "Attribute value is not displayed in cart page");
        softAssert.assertEquals(context.cartAttributeValue, context.pdpThickness + " x " + context.pdpWidth + " x " + context.pdpLength + " mm", "Attribute value in cart page is not matching with selected attribute value in PDP");
        log.info((context.pdpProductName) + "   " + context.cartPdpProductName);
        softAssert.assertTrue(context.cartPdpProductName.equals(context.pdpProductName), "Product name in cart page is not matching with product name in PDP or product name is not displayed in cart page");

        waits(By.xpath(CART_COUNT));
        String count1 = CustomUtil.getItemCount(CustomUtil.getText(By.xpath(CART_COUNT)));
        softAssert.assertEquals(count1, count, "Cart count is not updated in cart icon after adding product to cart");

    }

    @Then("Delete the line item from cart")
    public void deleteTheLineItemFromCart() {

        //Delete the line item from cart
        try {
            waitAndClickOn(DELETE_LINE_ITEM);
            if (isElementPresent(DELETE_CTA_BUTTON)) {
                waitAndCheckIsElementClickable(DELETE_CTA_BUTTON);
                waitAndClickOn(DELETE_CTA_BUTTON);
                new WebDriverWait(Grid.driver(), Duration.ofMillis(1000)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMPTY_CART_MSG)));
                String actualMessage = Grid.driver().findElement(By.xpath(EMPTY_CART_MSG)).getText();
                softAssert.assertEquals(actualMessage, "Your cart is empty", "Empty cart message is not matching with expected message or empty cart message is not displayed");
                log.info("Empty cart message is displayed and matching with expected message: " + "Your cart is empty");

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

    private void clickOnCRCoilProduct() {
        //Move mouse to Cold rolled coil sub category and click on it
        actions.moveToElement(Grid.driver().findElement(By.xpath(coldRolledCoilsSubCategory))).click().perform();
    }

    @And("open CR Coil pdp {string} product")
    public void openCRCoilPdp(String product) {
        //Scroll and click on it to open CR Coil PDP
        WebElement locator = Grid.driver().findElement(By.xpath(plpFirstProduct));
        clickButton(locator);
    }

    @And("select thickness, width, quantity {string} for CR Coil")
    public void selectAllTheCRCoilAttribute(String quantity) {


        try {

            {
                cartPdpProductName = getText(By.xpath(ITEM_NAME));
                waits(By.xpath(THICKNESS));
                clickButton(By.xpath(THICKNESS));
                pdpThickness = getText(By.xpath(THICKNESS));
                log.info("Thickness value " + pdpThickness + " mm is not available for selection, selecting available thickness value");
                Allure.addAttachment("Thickness value not available", "Thickness value " + pdpThickness + "mm is not available for selection, selecting available thickness value");
            }
        } catch (Exception e) {
            log.info("Failed to click cold rolled coil thickness");
            throw new RuntimeException("Failed to click cold rolled coil thickness", e);
        }

        //Select width for CR Coil
        try {
            waits(By.xpath(WIDTH_AVAILABLE));
            clickButton(By.xpath(WIDTH_AVAILABLE));
            context.pdpWidth = getText(By.xpath(WIDTH_AVAILABLE));
            log.info("Selected width value '" + context.pdpWidth + "' for HR Coil");
            Allure.addAttachment("Width selected", "Selected width value: " + context.pdpWidth);
        } catch (Exception e) {
            log.info("Failed to select width for HR Coil");
            throw new RuntimeException("Failed to select width for HR Coil", e);
        }

        //Enter quantity for CR Coil
        try {
            waitAndCheckIsElementClickable(QUANTITY);
            waitAndSetText(QUANTITY, quantity);
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
        context.cartPageQuantity = waitAndGetText(CART_PAGE_QUANTITY);
        softAssert.assertEquals(context.cartPageQuantity, "Qty: " + context.pdpQuantity + " MT");

        //verify updated cart attribute value for CR Coil
        context.afterUpdateCartAttributeValue = waitAndGetText(CART_PAGE_ATTRIBUTE_VALUE);
        softAssert.assertEquals(context.afterUpdateCartAttributeValue, context.updatedThickness + " x " + context.updatedWidth + " mm", "After update, Attribute value in cart page is not matching with selected attribute value in edit line item");

    }

    private void clickOnCRSheetProduct() {
        //Reading locator from page factory
        actions.moveToElement(Grid.driver().findElement(By.xpath(coldRolledSheetsSubCategory))).click().perform();
    }

    @And("open CR Sheet pdp")
    public void openCRSheetPdp() {
        WebElement locator = Grid.driver().findElement(By.xpath(plpFirstProduct));
        clickButton(locator);
    }

    @And("select thickness, width, length, quantity {string} for CR Sheet")
    public void selectAllTheCRSheetAttribute(String quantity) {

        try {

            {
                context.cartPdpProductName = getText(By.xpath(ITEM_NAME));
                waits(By.xpath(THICKNESS));
                clickButton(By.xpath(THICKNESS));
                context.pdpThickness = getText(By.xpath(THICKNESS));
                log.info("Thickness value " + context.pdpThickness + "mm is not available for selection, selecting available thickness value");
                Allure.addAttachment("Thickness value not available", "Thickness value " + context.pdpThickness + "mm is not available for selection, selecting available thickness value");
            }
        } catch (Exception e) {
            log.info("Failed to click cold rolled sheet thickness");
            throw new RuntimeException("Failed to click cold rolled sheet thickness", e);
        }

        try {
            waits(By.xpath(WIDTH_AVAILABLE));
            clickButton(By.xpath(WIDTH_AVAILABLE));
            context.pdpWidth = getText(By.xpath(WIDTH_AVAILABLE));
            log.info("Selected width value '" + context.pdpWidth + "' for HR Coil");
            Allure.addAttachment("Width selected", "Selected width value: " + context.pdpWidth);
        } catch (Exception e) {
            log.info("Failed to select width for HR Coil");
            throw new RuntimeException("Failed to select width for HR Coil", e);
        }

        try {

            {

                waits(By.xpath(CUSTOM_LENGTH_MSG));
                String value = getText(By.xpath(CUSTOM_LENGTH_MSG));
                String lengthValue = String.valueOf(CustomUtil.generateValueFromText(value));
                log.info("Enter the Custom length value  " + lengthValue);
                waits(By.xpath(CUSTOM_LENGTH_TEXTBOX));
                enterText(By.xpath(CUSTOM_LENGTH_TEXTBOX), lengthValue);
                WebElement element = Grid.driver().findElement(By.xpath(CUSTOM_LENGTH_TEXTBOX));
                element.sendKeys(Keys.ENTER);
                log.info("entering the custom length");
                context.pdpLength = lengthValue;
                log.info("Length value " + context.pdpLength + "mm is not available for selection, selecting available length value");
                Allure.addAttachment("Length value not available", "Length value " + context.pdpLength + "mm is not available for selection, selecting available length value");

            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to click cold rolled sheet length", e);
        }


        try {
            waitAndCheckIsElementClickable(QUANTITY);
            waitAndSetText(QUANTITY, quantity);
            log.info("Quantity " + quantity + " is entered for cold rolled sheet");
            context.pdpQuantity = quantity;
        } catch (Exception e) {
            log.info("Failed to enter quantity for cold rolled sheet");
            throw new RuntimeException("Failed to enter quantity for cold rolled sheet", e);
        }
    }

    @Then("verify the Attribute values for CR Sheet in cart page")
    public void verifyTheAttributeValuesForCRSheetInCartPage() {
        context.cartAttributeValue = waitAndGetText(CART_PAGE_ATTRIBUTE_VALUE);
        softAssert.assertFalse(context.cartAttributeValue.isEmpty(), "Attribute value is not displayed in cart page");
        softAssert.assertEquals(context.cartAttributeValue, context.pdpThickness + " x " + context.pdpWidth + " x " + context.pdpLength + " mm", "Attribute value in cart page is not matching with selected attribute value in PDP");
        softAssert.assertTrue(context.cartPdpProductName.equals(context.pdpProductName), "Product name in cart page is not matching with product name in PDP or product name is not displayed in cart page");
        softAssert.assertEquals(getItemCount(getText(By.xpath(CART_COUNT))), "1", "Cart count is not updated in cart icon after adding product to cart");
    }

    @And("verify the updated Attribute values for CR Sheet in cart page")
    public void verifyTheUpdatedAttributeValuesForCRSheetInCartPage() {
        context.afterUpdateCartAttributeValue = waitAndGetText(CART_PAGE_ATTRIBUTE_VALUE);
        softAssert.assertEquals(context.afterUpdateCartAttributeValue, context.updatedThickness + " x " + context.updatedWidth + " x " + context.updatedLength + " mm", "After update, Attribute value in cart page is not matching with selected attribute value in edit line item");

    }

    @When("click on Cement product")
    public void clickOnCementProduct() {
        try {
            sleep(4000);
            pageRefresh();
            sleep(4000);
            new WebDriverWait(Grid.driver(), Duration.ofMillis(1000)).until(ExpectedConditions.visibilityOfElementLocated(ALL_CATEGORY));
            actions.moveToElement(Grid.driver().findElement(ALL_CATEGORY)).perform();
            waitAndClickOn(CEMENT_PLP);
            log.info("Opened cement PLP page");
        } catch (Exception e) {
            log.fine("Failed to open PLP Cement");
            throw new RuntimeException("Failed to open PLP Cement", e);
        }
    }

    @And("open Cement pdp product")
    public void openCementPdp() {
        try {
            // The step has no index, so open the first Cement product listed on the PLP.
            waitAndCheckIsElementClickable(PLP_FIRST_PRODUCT, "1");
            waitAndClickOn(PLP_FIRST_PRODUCT, "1");
            log.info("Opened the first Cement product PDP");
        } catch (Exception e) {
            log.info("Failed to open Cement PDP page");
            throw new RuntimeException("Failed to open Cement PDP page", e);
        }
    }

    @And("enter quantity {string} for Cement")
    public void selectAllTheCementAttribute(String quantity) {
        try {
            context.cartPdpProductName = getText(By.xpath(ITEM_NAME));
            waitAndCheckIsElementClickable(CEMENT_QUANTITY);
            waitAndSetText(CEMENT_QUANTITY, quantity);
            log.info("Quantity " + quantity + " is entered for cement");
            context.pdpQuantity = quantity;
        } catch (Exception e) {
            log.info("Failed to enter quantity for cement");
            throw new RuntimeException("Failed to enter quantity for cement", e);
        }
    }

    @And("click to Coated steel category")
    public void clickToCoatedSteelCategory() {
        try {
            actions.moveToElement(Grid.driver().findElement(By.xpath(coatedSteel))).click().perform();
            log.info("cursor moved to Coated Steel");
        } catch (Exception e) {
            throw new RuntimeException("mouse failed to move to coated steel", e);

        }
    }

    private void clickOnCoatedSteelSheetProduct() {
        try {
            clickButton(GALVANIZED_SHEET);
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

    @And("select thickness, width, length, coating grade, spangle type, quantity {string} for Coated Sheet")
    public void selectAllTheColourCoatedSheetAttribute(String quantity) {
        try {

            {
                context.cartPdpProductName = getText(By.xpath(ITEM_NAME));
                waits(By.xpath(THICKNESS));
                clickButton(By.xpath(THICKNESS));
                context.pdpThickness = getText(By.xpath(THICKNESS));
                log.info("Thickness value " + context.pdpThickness + "mm is not available for selection, selecting available thickness value");
                Allure.addAttachment("Thickness value not available", "Thickness value " + context.pdpThickness + "mm is not available for selection, selecting available thickness value");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Coated Sheet thickness", e);
        }


        try {

            {
                clickButton(By.xpath(WIDTH_AVAILABLE));
                context.pdpWidth = getText(By.xpath(WIDTH_AVAILABLE));
                log.info("Width value " + context.pdpWidth + "mm is not available for selection, selecting available width value");
                Allure.addAttachment("Width value not available", "Width value " + context.pdpWidth + "mm is not available for selection, selecting available width value");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Coated Sheet Width", e);
        }


        try {

            {

                waits(By.xpath(CUSTOM_LENGTH_MSG));
                String value = getText(By.xpath(CUSTOM_LENGTH_MSG));
                String lengthValue = String.valueOf(CustomUtil.generateValueFromText(value));
                log.info("Enter the Custom length value  " + lengthValue);
                waits(By.xpath(CUSTOM_LENGTH_TEXTBOX));
                enterText(By.xpath(CUSTOM_LENGTH_TEXTBOX), lengthValue);
                WebElement element = Grid.driver().findElement(By.xpath(CUSTOM_LENGTH_TEXTBOX));
                element.sendKeys(Keys.ENTER);
                log.info("entering the custom length");
                context.pdpLength = lengthValue;
                log.info("Length value " + context.pdpLength + "mm is not available for selection, selecting available length value");
                Allure.addAttachment("Length value not available", "Length value " + context.pdpLength + "mm is not available for selection, selecting available length value");

            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Coated Sheet Length", e);
        }

        try {
            waitAndCheckIsElementClickable(QUANTITY);
            waitAndSetText(QUANTITY, quantity);
            context.pdpQuantity = quantity;
            log.info("Quantity " + context.pdpQuantity + " is entered for Colour Coated Sheet");
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter Quantity for Colour Coated Sheet", e);
        }
    }

    @Then("verify the Attribute values for Coated Steel Sheet in cart page")
    public void verifyTheAttributeValuesForCoatedSteelSheetInCartPage() {
        context.cartAttributeValue = waitAndGetText(CART_PAGE_ATTRIBUTE_VALUE);
        softAssert.assertFalse(context.cartAttributeValue.isEmpty(), "Attribute value is not displayed in cart page");
        softAssert.assertEquals(context.cartAttributeValue, context.pdpThickness + " x " + context.pdpWidth + " x " + context.pdpLength + " mm", "Attribute value in cart page is not matching with selected attribute value in PDP");
        softAssert.assertTrue(context.cartPdpProductName.equals(context.pdpProductName), "Product name in cart page is not matching with product name in PDP or product name is not displayed in cart page");
    }

    @And("click on Wire product")
    public void clickOnWireProduct() {
        try {
            //Reading locator from page factory
            waits(By.xpath(wireCategory));
            actions.moveToElement(Grid.driver().findElement(By.xpath(wireCategory))).click().perform();
            log.info("Clicked on Wire category");
        } catch (Exception e) {
            log.info("Failed to click on Wire category");
            throw new RuntimeException("Failed to click on Wire category", e);
        }
    }

    @And("open Wire pdp")
    public void openWirePdp() {
        WebElement locator = Grid.driver().findElement(By.xpath(FIRST_WIRE_ROD_PRODUCT));
        scrollingToViewAndClickUsingJS(locator);
        try {
            jsClick(locator);
            log.info("Clicked on Wire product");
        } catch (Exception e) {
            log.info("Failed to open Wire PDP");
            throw new RuntimeException("Failed to open Wire PDP", e);
        }
    }

    @And("select diameter {string} quantity {string} for Wire")
    public void selectAllTheWireAttribute(String diameter, String quantity) {
        //Select diameter for Wire
        try {

            {
                context.cartPdpProductName = getText(By.xpath(ITEM_NAME));

                clickButton(By.xpath(DIAMETER));
                String diameterValue = getText(By.xpath(DIAMETER));
                // waitAndClickOn(WIDTH, "2");
                context.pdpDiameter = diameterValue;
                log.info("Diameter value " + context.pdpDiameter + "mm is  available for selection, selecting available diameter value");
                Allure.addAttachment("Diameter value not available", "Diameter value " + context.pdpDiameter + "mm is not available for selection, selecting available diameter value");
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
            context.pdpQuantity = quantity;
        } catch (Exception e) {
            log.info("Failed to enter quantity for Wire");
            throw new RuntimeException("Failed to enter quantity for Wire", e);
        }
    }

    @Then("verify the Attribute values for Wire in cart page")
    public void verifyTheAttributeValuesForWireInCartPage() {
        //verify quantity in cart page
        context.cartPageQuantity = waitAndGetText(CART_PAGE_QUANTITY);
        softAssert.assertEquals(context.cartPageQuantity, "Qty: " + context.pdpQuantity + " MT", "Quantity is not matching");
        waits(By.xpath(CART_COUNT));
        softAssert.assertEquals(getItemCount(getText(By.xpath(CART_COUNT))), "1", "Cart count is not updated in cart icon after adding product to cart");

        //verify diameter in cart page
        context.cartAttributeValue = waitAndGetText(CART_PAGE_ATTRIBUTE_VALUE);
        softAssert.assertFalse(context.cartAttributeValue.isEmpty(), "Attribute value is not displayed in cart page");
        softAssert.assertEquals(context.cartAttributeValue, context.pdpDiameter + " mm", "Attribute value in cart page is not matching with selected attribute value in PDP");

        //verify product Name in cart Page
        softAssert.assertTrue(context.cartPdpProductName.toLowerCase().contains(context.pdpProductName.toLowerCase()), "Wire Product Name is not matching in cart page or product name is not displayed in cart page");
    }

    @And("verify the updated Attribute values for Wire in cart page")
    public void verifyTheUpdatedAttributeValuesForWireInCartPage() {
        //verify quantity in cart page
        context.cartPageQuantity = waitAndGetText(CART_PAGE_QUANTITY);
        softAssert.assertEquals(context.cartPageQuantity, "Qty: " + context.pdpQuantity + " MT");

        context.afterUpdateCartAttributeValue = waitAndGetText(CART_PAGE_ATTRIBUTE_VALUE);
        softAssert.assertEquals(context.afterUpdateCartAttributeValue, context.updatedDiameter + " mm", "After update, Attribute value in cart page is not matching with selected attribute value in edit line item");
    }

    @When("click on TMT product")
    public void clickOnTMTProduct() {
        try {
            new WebDriverWait(Grid.driver(), Duration.ofMillis(1000)).until(ExpectedConditions.visibilityOfElementLocated(ALL_CATEGORY));
            actions.moveToElement(Grid.driver().findElement(ALL_CATEGORY)).perform();
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
            clickButton(locator);
            log.info("Opened TMT PDP page");
        } catch (Exception e) {
            log.info("Failed to open TMT Rebar PDP");
            throw new RuntimeException("Failed to open TMT Rebar PDP", e);
        }
    }

    @And("select diameter, form, quantity {string} for TMT")
    public void selectAllTheTMTRebarAttribute(String quantity) {

        //Select diameter for TMT
        try {

            {
                context.cartPdpProductName = getText(By.xpath(ITEM_NAME));
                clickButton(By.xpath(DIAMETER));
                String diameterValue = getText(By.xpath(DIAMETER));
                context.pdpDiameter = diameterValue;
                sleep(1000);
                log.info("Diameter value " + context.pdpDiameter + "mm is not available for selection, selecting available diameter value");
            }
        } catch (Exception e) {
            log.info("Failed to select TMT diameter");
            throw new RuntimeException("Failed to select TMT diameter", e);
        }

        //Select form for TMT
        try {

            {
                waits(By.xpath(FORM));
                clickButton(By.xpath(FORM));
                context.pdpForm = getText(By.xpath(FORM));
                log.info("Form value " + context.pdpForm + "mm is not available for selection, selecting available form value");
                sleep(1000);

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
            context.pdpQuantity = quantity;
        } catch (Exception e) {
            log.info("Failed to enter TMT Quantity in PDP page");
            throw new RuntimeException("Failed to enter TMT Quantity in PDP page", e);
        }
    }

    @Then("verify the Attribute values for TMT in cart page with cart count {string}")
    public void verifyTheAttributeValuesForTmtInCartPage(String count) {
        //verify product Name in cart Page
        softAssert.assertTrue((context.cartPdpProductName).toLowerCase().contains(context.pdpProductName.toLowerCase()), "TMT Product Name is not matching in cart page or product name is not displayed in cart page");

        //verify quantity in cart page
        context.cartPageQuantity = waitAndGetText(CART_PAGE_QUANTITY);
        softAssert.assertEquals(context.cartPageQuantity, "Qty: " + context.pdpQuantity + " MT");
        log.info(getItemCount(getText(By.xpath(CART_COUNT))));
        softAssert.assertEquals((getItemCount(getText(By.xpath(CART_COUNT)))), String.valueOf(count), "Cart count is not updated in cart icon after adding product to cart");

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
            context.updatedQuantity = updateQuantity;
            log.info("Updated quantity from " + context.pdpQuantity + " to " + updateQuantity + " in edit line item for " + context.pdpProductName);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update the Quantity for " + context.pdpProductName, e);
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
        context.cartPageQuantity = waitAndGetText(CART_PAGE_QUANTITY);
        softAssert.assertEquals(context.cartPageQuantity, "Qty: " + context.updatedQuantity + " MT");

        context.afterUpdateCartAttributeValue = waitAndGetText(CART_PAGE_ATTRIBUTE_VALUE);
        softAssert.assertEquals(context.afterUpdateCartAttributeValue, context.updatedThickness + " x " + context.updatedWidth + " mm", "After update, Attribute value in cart page is not matching with selected attribute value in edit line item");


    }

    @Then("verify the updated Attribute values for Cement in cart page")
    public void verifyTheUpdatedAttributeValuesForCementInCartPage() {
        //Verify the updated quantity in cart page
        context.cartPageQuantity = waitAndGetText(CART_PAGE_QUANTITY);
        softAssert.assertEquals(context.cartPageQuantity, "Qty: " + context.updatedQuantity + " bags", "After update, Quantity in cart page is not matching with updated quantity in edit line item or quantity is not displayed in cart page");
    }

    @And("verify the updated Attribute values for HR Sheet in cart page")
    public void verifyTheUpdatedAttributeValuesForHRSheetInCartPage() {
        context.afterUpdateCartAttributeValue = waitAndGetText(CART_PAGE_ATTRIBUTE_VALUE);
        softAssert.assertEquals(context.afterUpdateCartAttributeValue, context.updatedThickness + " x " + context.updatedWidth + " x " + context.updatedLength + " mm", "After update, Attribute value in cart page is not matching with selected attribute value in edit line item");
    }

    @And("update Thickness to dropdown")
    public void updateThickness() {
        try {
            waitAndClickOn(EDIT_THICKNESS_DROPDOWN);
            log.info("Clicked on edit thickness dropdown");
            sleep(2000);
            if (isDisplayed(By.xpath(UPDATE_ATTRIBUTE))) {
                clickButton(By.xpath(UPDATE_ATTRIBUTE));
                context.updatedThickness = getText(By.xpath(UPDATE_ATTRIBUTE));
                log.info("Updated thickness from " + context.pdpThickness + "mm to " + context.updatedThickness + "mm in edit line item");
            } else {
                log.info("Given update thickness " + context.updatedThickness + "mm is not available for selection, selecting available update thickness value");
                Allure.addAttachment("Update Thickness value not available", "Given update thickness " + context.updatedThickness + "mm is not available for selection, selecting available update thickness value");
                waitAndCheckIsElementClickable(EDIT_THICKNESS, "3");
                context.updatedThickness = waitAndGetText(EDIT_THICKNESS, "3");
                waitAndClickOn(EDIT_THICKNESS, "3");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to update thickness in edit line item", e);
        }
    }

    @And("update Width to dropdown")
    public void updateWidth() {
        try {
            waitAndClickOn(EDIT_WIDTH_DROPDOWN);
            log.info("Clicked on edit width dropdown");
            if (isDisplayed(By.xpath(UPDATE_ATTRIBUTE))) {
                clickButton(By.xpath(UPDATE_ATTRIBUTE));
                context.updatedWidth = getText(By.xpath(UPDATE_ATTRIBUTE));

                log.info("Updated width from" + context.pdpWidth + "mm to " + context.updatedWidth + "mm in edit line item");
            } else {
                log.info("Given update width " + context.updatedWidth + "mm is not available for selection, selecting available update width value");
                waitAndCheckIsElementClickable(EDIT_WIDTH, "2");
                context.updatedWidth = waitAndGetText(EDIT_WIDTH, "2");
                waitAndClickOn(EDIT_WIDTH, "2");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to update width in edit line item", e);
        }
    }

    @And("update Length to dropdown")
    public void updateLength() {
        try {
            waitAndClickOn(EDIT_LENGTH_DROPDOWN);
            log.info("Clicked on edit length dropdown");
            if (isDisplayed(By.xpath(UPDATE_ATTRIBUTE))) {
                clickButton(By.xpath(UPDATE_ATTRIBUTE));
                context.updatedLength = getText(By.xpath(UPDATE_ATTRIBUTE));
                log.info("Updated length from " + context.pdpLength + "mm to " + context.updatedLength + "mm in edit line item");
            } else {
                log.info("Given update length " + updatedLength + "mm is not available for selection, selecting available update length value");
                Allure.addAttachment("Update Length value not available", "Given update length " + context.updatedLength + "mm is not available for selection, selecting available update length value");
                waitAndCheckIsElementClickable(EDIT_LENGTH);
                context.updatedLength = waitAndGetText(EDIT_LENGTH);
                waitAndClickOn(EDIT_LENGTH);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to update length in edit line item", e);
        }
    }

    @And("update Diameter")
    public void updateDiameter() {
        try {
            waitAndClickOn(EDIT_DIAMETER_DROPDOWN);
            log.info("Clicked on edit Diameter dropdown");

            {
                if (isDisplayed(By.xpath(UPDATE_ATTRIBUTE))) {
                    clickButton(By.xpath(UPDATE_ATTRIBUTE));
                }
                context.updatedDiameter = getText(By.xpath(UPDATE_ATTRIBUTE));

                log.info("Given update diameter " + context.updatedDiameter + "mm is not available for selection, selecting available update diameter value");


            }
        } catch (Exception e) {
            Allure.addAttachment("Update Length value not available", "Given update length " + context.updatedDiameter + "mm is not available for selection, selecting available update length value");

            throw new RuntimeException("Failed to update Diameter in edit line item", e);
        }
    }

    @And("update Form to {string}")
    public void updateForm(String updateForm) {
        try {
            waitAndClickOn(EDIT_FORM_DROPDOWN);
            log.info("Clicked on edit Form dropdown");
            if (isDisplayed(By.xpath(UPDATE_ATTRIBUTE))) {
                clickButton(By.xpath(UPDATE_ATTRIBUTE));
                updatedDiameter = getText(By.xpath(UPDATE_ATTRIBUTE));
                log.info("Updated Form from " + pdpForm + "mm to " + updatedDiameter + "mm in edit line item");
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
        context.cartPageQuantity = waitAndGetText(CART_PAGE_QUANTITY);
        softAssert.assertEquals(context.cartPageQuantity, "Qty: " + context.updatedQuantity + " MT", "Quantity is not updated for TMT");

    }

    @When("click cart icon")
    public void clickCartIcon() {
        refreshAndWaitFor(CART_ICON);
        clickButton(CART_ICON);
        log.info("Clicked on cart icon");
    }

    @Then("check the empty cart page display with message {string}")
    public void checkTheEmptyCartPageDisplayWithMessage(String expectedMessage) {


        try {
            try {
                new WebDriverWait(Grid.driver(), Duration.ofMillis(1000)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMPTY_CART_MSG)));


            } catch (Exception e) {
                try {
                    log.info("Cart is not empty");
                    sleep(1000);
                    waits(By.xpath(ITEMS_COUNT));
                    String value = getItemCount(getText(By.xpath(ITEMS_COUNT)));

                    System.out.println("the item count is " + value);
                    for (int i = 0; i < Integer.parseInt(value); i++) {
                        try {

                            waits(By.xpath(DELETE_ITEMS));

                            sleep(1000);
                            jsClick(By.xpath(DELETE_ITEMS));
                            sleep(1000);

                            waits(By.xpath(DELETE_BTN));
                            jsClick(By.xpath(DELETE_BTN));
                            sleep(1000);
                        } catch (Exception e1) {
                            System.out.println(e1);
                        }
                    }
                } catch (Exception e3) {
                    System.out.println(e3);
                }


            } finally {

                new WebDriverWait(Grid.driver(), Duration.ofMillis(1000)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMPTY_CART_MSG)));
                String actualMessage = Grid.driver().findElement(By.xpath(EMPTY_CART_MSG)).getText();
                softAssert.assertEquals(actualMessage, expectedMessage, "Empty cart message is not matching with expected message or empty cart message is not displayed");
                log.info("Empty cart message is displayed and matching with expected message: " + expectedMessage);
            }
        } catch (Exception e) {
            log.info("Failed to verify empty cart message" + e.getMessage());
            throw new RuntimeException("Failed to verify empty cart message", e);
        }
    }

    @Then("Verify the cart page SKU and cart icon count and delete one by one")
    public void verifyTheCartPageSKUAndCartIconCountAndDeleteOneByOne() {

        String skuCount = waitAndGetText(CART_PAGE_SKU_COUNT);
        int expectedSkuCount = Integer.parseInt(skuCount.split(" ")[0]);
        log.info(skuCount + "========================" + expectedSkuCount);
        int i = 0;
        while (i < expectedSkuCount) {
            waitAndClickOn(DELETE_LINE_ITEM);
            Boolean deleteCartTitleFlag = true;
            if (isElementPresent(DELETE_CTA_BUTTON) && deleteCartTitleFlag) {
                waitAndCheckIsElementClickable(DELETE_CTA_BUTTON);
                waitAndClickOn(DELETE_CTA_BUTTON);
                if(i > 1){
                    log.info(i+" items Deleted successfully the line item from cart");
                } else {
                    log.info(i+" item Deleted successfully the line item from cart");
                }
            }
            sleep(2000);
            i++;
        }
    }

    @And("User navigate to HomePage")
    public void userNavigateToHomePage() {
        clickButton(By.xpath(JSW_LOGO));
    }

    @And("click on Wire coil")
    public void clickOnWireCoil() {
        waits(By.xpath(WIRE_COIL));
        clickButton(By.xpath(WIRE_COIL));

    }

    @And("submit the requirement to create an opportunity")
    public void submitTheRequirementToCreateAnOpportunity() {
        waitAndCheckIsElementPresent(SUBMIT_REQUIREMENT);
        waitAndCheckIsElementClickable(SUBMIT_REQUIREMENT);
        waitAndClickOn(SUBMIT_REQUIREMENT);
        log.info("Clicked on 'Submit requirement' to create an opportunity from the cart");
        sleep(3000);
    }

    @Then("verify the opportunity is created with a valid requirement id")
    public void verifyTheOpportunityIsCreatedWithAValidRequirementId() {
        // After submitting the cart, the requirement (opportunity) is listed under My requirements.
        waitAndCheckIsElementPresent(MY_REQUIREMENT_PAGE);
        waitAndClickOn(MY_REQUIREMENT_PAGE);
        waitAndCheckIsElementPresent(REQUIREMENT_ID);
        String requirementId = waitAndGetText(REQUIREMENT_ID).trim();
        softAssert.assertFalse(requirementId.isEmpty(),
                "Opportunity was not created - requirement id is not displayed under My requirements");
        log.info("Opportunity created successfully with requirement id: " + requirementId);
    }

    @Then("verify the cart price summary shows a valid order total")
    public void verifyTheCartPriceSummaryShowsAValidOrderTotal() {
        softAssert.assertTrue(waitAndCheckIsElementVisible(CART_SUBTOTAL),
                "Subtotal is not displayed in the cart price summary");
        softAssert.assertTrue(waitAndCheckIsElementVisible(CART_TAX),
                "Tax / GST is not displayed in the cart price summary");
        String orderTotal = waitAndGetText(CART_ORDER_TOTAL);
        String amountDigits = orderTotal.replaceAll("[^0-9]", "");
        softAssert.assertFalse(amountDigits.isEmpty(),
                "Order total is not a valid amount in the cart price summary: '" + orderTotal + "'");
        log.info("Cart price summary displayed with order total: " + orderTotal);
    }

    @Then("cart should retain the items after page refresh")
    public void cartShouldRetainTheItemsAfterPageRefresh() {
        String skuBefore = waitAndGetText(CART_PAGE_SKU_COUNT);
        log.info("Cart SKU count before refresh: " + skuBefore);
        Grid.driver().navigate().refresh();
        sleep(3000);
        waitAndCheckIsElementPresent(SUBMIT_REQUIREMENT);
        String skuAfter = waitAndGetText(CART_PAGE_SKU_COUNT);
        softAssert.assertEquals(skuAfter, skuBefore,
                "Cart did not persist after refresh - SKU count changed from '" + skuBefore + "' to '" + skuAfter + "'");
        log.info("Cart persisted after refresh. SKU count: " + skuAfter);
    }

    @When("move the mouse to {string} category")
    public void moveTheMouseToCategory(String category) {
        if(isElementPresent(CONFIRM_BUSINESS_DETAILS_DIALOG)) {
            Grid.driver().findElement(By.xpath("//div[@role='dialog']/div/button")).click();
        }
        refreshAndWaitFor(ALL_CATEGORY);
        hoverOverElement(ALL_CATEGORY);

        if (category.equalsIgnoreCase("mild steel")) {
            waits(MILD_STEEL_CATEGORY);
            hoverOverElement(MILD_STEEL_CATEGORY);
            log.info("cursor moved to mild steel category");
            return;
        }

        if (isMildSteelSubCategory(category)) {
            waits(MILD_STEEL_CATEGORY);
            hoverOverElement(MILD_STEEL_CATEGORY);

            By subcategoryLocator = getCategoryLocator(category);
            waits(subcategoryLocator);
            hoverOverElement(subcategoryLocator);
            log.info("cursor moved to subcategory '" + category + "' under mild steel");
            return;
        }

        By categoryLocator = getCategoryLocator(category);
        waits(categoryLocator);
        hoverOverElement(categoryLocator);
        log.info("cursor moved to category: " + category);
    }

    @And("click on {string} product")
    public void clickOnProduct(String product) {
        if(isElementPresent(CONFIRM_BUSINESS_DETAILS_DIALOG)) {
            Grid.driver().findElement(By.xpath("//div[@role='dialog']/div/button")).click();
        }
        switch (product.trim().toLowerCase()) {
            case "hr sheet":
                clickHRSheetProduct();
                break;
            case "hr coil":
                clickHRCoilProduct();
                break;
            case "cr sheet":
                clickCRSheetProduct();
                break;
            case "cr coil":
                clickCRCoilProduct();
                break;
            case "coated steel sheet":
                clickCoatedSteelSheetProduct();
                break;
            case "wire":
                clickWireProduct();
                break;
            default:
                throw new IllegalArgumentException("Unsupported product for generic click: " + product);
        }
    }

    private void clickMildSteelSubCategory(By locator, String productName) {
        try {
            waits(locator);
            actions.moveToElement(Grid.driver().findElement(locator)).click().perform();
            log.info("Clicked on " + productName + " product");
        } catch (Exception e) {
            log.info("Failed to click on " + productName + " product");
            throw new RuntimeException("Failed to click on " + productName + " product", e);
        }
    }

    private void clickHRSheetProduct() {
        clickMildSteelSubCategory(By.xpath(hotRolledSheetsSubCategory), "HR Sheet");
    }

    private void clickHRCoilProduct() {
        clickMildSteelSubCategory(By.xpath(hotRolledCoilsSubCategory), "HR Coil");
    }

    private void clickCRSheetProduct() {
        clickMildSteelSubCategory(By.xpath(coldRolledSheetsSubCategory), "CR Sheet");
    }

    private void clickCRCoilProduct() {
        clickMildSteelSubCategory(By.xpath(coldRolledCoilsSubCategory), "CR Coil");
    }

    private void clickWireProduct() {
        clickMildSteelSubCategory(By.xpath(wireCategory), "Wire");
    }

    private void clickCoatedSteelSheetProduct() {
        try {
            waitAndClickOn(GALVANIZED_SHEET);
            log.info("Clicked on Coated steel sheet product");
        } catch (Exception e) {
            log.info("Failed to click on Coated steel sheet product");
            throw new RuntimeException("Failed to click on Coated steel sheet product", e);
        }
    }

    private boolean isMildSteelSubCategory(String category) {
        switch (category.trim().toLowerCase()) {
            case "hot rolled":
            case "cold rolled":
            case "coated steel":
            case "wire":
                return true;
            default:
                return false;
        }
    }

    private By getCategoryLocator(String category) {
        switch (category.trim().toLowerCase()) {
            case "mild steel":
                return MILD_STEEL_CATEGORY;
            case "hot rolled":
                return By.xpath(msHotRolled);
            case "cold rolled":
                return By.xpath(coldRolledCategory);
            case "coated steel":
                return By.xpath(coatedSteel);
            case "wire":
                return By.xpath(wireCategory);
            default:
                throw new IllegalArgumentException("Unknown category: " + category);
        }
    }
}
