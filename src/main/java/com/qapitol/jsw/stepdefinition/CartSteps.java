package com.qapitol.jsw.stepdefinition;


import com.qapitol.jsw.apitest.apis.impl.CartRCV2ApiImp;
import com.qapitol.jsw.context.ScenarioContext;
import com.qapitol.jsw.utils.CustomUtil;
import com.qapitol.jsw.utils.CustomerProfileUtil;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.qapitol.jsw.pageFactory.CategoryPages.*;
import static com.qapitol.jsw.utils.CustomUtil.*;
import static com.qapitol.jsw.utils.Hooks.softAssert;

public class CartSteps extends AbstractWebPage {
    private static final String THICKNESS = "(//span[text()='Thickness (mm)']/../following-sibling::div//button[not(contains(@class, 'disabled'))])[1]";
    private static final String WIDTH_BUTTON = "(//span[text()='Width (mm)']/../following-sibling::div//button[not(contains(@class, 'disabled'))]) [1]";
    private static final String LENGTH_BUTTON = "(//span[text()='Length (mm)']/../following-sibling::div//button[not(contains(@class, 'disabled'))]) [1]";
    private static final String QUANTITY = "quantity";
    private static final String SUBMIT_REQUIREMENT = "submitRequirement";
    private static final String DELETE_LINE_ITEM = "deleteLineItem";
    private static final String EDIT_LINE_ITEM = "editLineItem";
    private static final String DELETE_CTA_BUTTON = "deleteCta";
    private static final String CEMENT_PLP = "cement";
    private static final String MY_REQUIREMENT_PAGE = "myRequirementPage";
    private static final String REQUIREMENT_ID = "requirementId";
    private static final String PLP_FIRST_PRODUCT = "plpFirstProduct";
    private static final String LENGTH = "length";
    private static final String DIAMETER = "(//span[text()='Diameter (mm)']/../following-sibling::div//button[not(contains(@class, 'disabled'))])[1]";
    private static final String TMT_PRODUCT = "tmt";
    private static final String FORM = "(//span[text()='TMT Form']/../following-sibling::div//button[not(contains(@class, 'disabled'))])[1]";
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
    private static final String PDP_PRODUCT_NAME = "pdpProductName";
    private static final String GALVANIZED_SHEET = "galvanizedSheet";
    private static final String UPDATE_ATTRIBUTE = "(//div[@data-side='bottom']//button[not(@disabled)]//span)[1]";
    private static final String CART_PAGE_ATTRIBUTE_VALUE = "cartPageAttributeValue";
    private static final String CART_PAGE_PRODUCT_NAME = "cartPageProductName";
    private static final String CART_PAGE_QUANTITY = "cartPageQuantity";
    private static final String EDIT_DIAMETER_DROPDOWN = "editDiameterDropdown";
    private static final String EDIT_FORM_DROPDOWN = "editFormDropdown";
    private static final String CART_COUNT = "//section//p[contains(normalize-space(.), 'item')]";
    private static final String CART_PAGE_SKU_COUNT = "cartPageSKUCount";
    private static final String CART_SUBTOTAL = "cartSubtotal";
    private static final String CART_TAX = "cartTax";
    private static final String CART_ORDER_TOTAL = "cartOrderTotal";
    private static final By CART_ICON = By.xpath("(//span[text()='Cart'])[2]");
    private static final String DELETE_ITEMS = "(//div[@id='line-item-section']//*[text()='Delete' and not(ancestor::*[@data-index='-1'])])[1]";
    private static final String DELETE_BTN = "//button[@id='remove-btn']";
    private static final String EMPTY_CART_MSG = "//h2[text()='Your cart is empty']";
    private static final String CUSTOM_LENGTH_MSG = "//label[text()=\"Custom length (mm):\"]/../following-sibling::div/span";
    private static final String CUSTOM_LENGTH_TEXTBOX = "//label[text()=\"Custom length (mm):\"]/following-sibling::input";
    private static final String QUANTITY_TEXTBOX = "//input[@id='quantity-input']";
    private static final String ADD_TO_CART_BTN = "//button[text()='Add to cart']";
    private static final int OPTIONAL_ATTRIBUTE_WAIT_SECONDS = 5;
    private static final int DELETE_CONFIRM_WAIT_SECONDS = 10;
    private static final int EXTRA_DELETE_ATTEMPTS = 2;
    private static final int DELETE_SETTLE_POLLS = 20;
    private static final String VIEWCART = "//button[text()='View cart']";
    private static final String JSW_LOGO = "(//a[@href=\"/\"])[1]";
    private static final String ITEMS_COUNT = "//div[@id='cart-delivery-address']/following-sibling::div/div[contains(text(),'item')]";
    private static final String WIRE_COIL = "(//li//a[@href='/category/wire-rod-coils'])";
    private static final String ITEM_NAME = "//h1";
    private static final By ALL_CATEGORY = By.xpath("//span[contains(@class, 'hover') and contains(text(),'All categories')]");
    private static final By MILD_STEEL_CATEGORY = By.xpath("//a[contains(@href, 'mild-steel')]/span");
    private static final By BITUMEN_CATEGORY = By.xpath("//a[contains(@href, 'bitumen')]/span");
    private static final String wireCategory = "//li/a[contains(@href, 'wire-rods')]";
    private static final String hotRolledSheetsSubCategory = "//li/a[contains(@href, 'hot-rolled-sheets')]";
    private static final String hotRolledCoilsSubCategory = "//li/a[contains(@href, 'hot-rolled-coils')]";
    private static final String coldRolledSheetsSubCategory = "//li/a[contains(@href, 'cold-rolled-steel-sheet') or contains(@href, 'steel-cold-rolled-sheet')]";
    private static final String coldRolledCoilsSubCategory = "//li/a[contains(@href, 'cold-rolled-coils')]";
    private static final String galvanizedSheetCategory = "//li/a[contains(@href, 'coated-gi-sheet') or contains(@href, 'coated-galvanized-sheets')]";
    public static final String msHotRolledCategory = "//li/a[contains(@href,'hot-rolled-steel')]/span";
    public static final String msColdRolledCategory = "//li/a[contains(@href,'cold-rolled-steel')]/span";
    public static final String coatedSteelCategory = "(//a[contains(@href,'/category/steel-coated')])[1]";
    public static final By quantityTextbox = By.xpath("//input[@id='quantity-input']");
    public static final By quantityLabel = By.xpath("//label[contains(normalize-space(), 'Specify quantity')]");
    public static final String pipesAndTubesCategory = "//a[contains(@href,'/category/pipes-and-tubes')]/span";
    public static final String hollowSectionsSubCategory = "//a[contains(@href,'/category/hollow-sections')]/span";
    public static final String viscosityGradeBitumenCategory = "//a[contains(@href,'/category/viscosity-grade-bitumen')]/span";
    public static final String gradeBulkBitumenSubCategory = "//a[contains(@href,'/category/viscosity-grade-bulk-bitumen')]/span";
    public static final String ACCOUNT_TAB = "accountTab";
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


    public CartSteps(ScenarioContext context) {
        this.context = context;
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

    @Then("Verify the doubled quantity for HR Coil in cart page with cart count {string}")
    public void verifyDoubledQuantityForHrCoilInCartPage(String count) {
        verifyMergedHrCoilLineItem(2, count);
    }

    private void verifyMergedHrCoilLineItem(int timesAdded, String count) {
        int pdpQuantity = parseQuantity(context.pdpQuantity, "quantity entered on the HR Coil PDP");
        context.cartPageQuantity = waitAndGetText(CART_PAGE_QUANTITY);
        int cartQuantity = parseQuantity(context.cartPageQuantity, "quantity shown in the cart");
        int expectedQuantity = pdpQuantity * timesAdded;

        softAssert.assertEquals(cartQuantity, expectedQuantity,
                "HR Coil was added " + timesAdded + " times at " + pdpQuantity + " MT each, so the"
                        + " merged line item should hold " + expectedQuantity + " MT - the cart shows '"
                        + context.cartPageQuantity + "'");

        softAssert.assertEquals(getItemCount(getText(By.xpath(CART_COUNT))), count,
                "Cart count is not '" + count + "' after adding the same HR Coil " + timesAdded
                        + " times - the cart created a duplicate line item instead of merging");

        context.cartAttributeValue = waitAndGetText(CART_PAGE_ATTRIBUTE_VALUE);
        softAssert.assertFalse(context.cartAttributeValue.isEmpty(), "Attribute value is not displayed in cart page");
        softAssert.assertEquals(context.cartAttributeValue, context.pdpThickness + " x " + context.pdpWidth + " mm", "Attribute value in cart page is not matching with selected attribute value in PDP");

        softAssert.assertTrue(context.cartPdpProductName.equals(context.pdpProductName), "Product name in cart page is not matching with product name in PDP or product name is not displayed in cart page");
        log.info("Verified the merged HR Coil line item: " + cartQuantity + " MT across " + timesAdded + " adds");

        try {
            jsClick(Grid.driver().findElement(By.xpath("(//section/div/div/div/button[text()='Delete']) [position() < 2]")));
            jsClick(Grid.driver().findElement(By.xpath("//p[contains(normalize-space(.), 'delete this item')]/following-sibling::div/button[normalize-space(.)='Delete']")));
        } catch (Exception e) {
            log.info("Could not delete the cart line item during cleanup: " + e.getMessage());
        }
    }

    private int parseQuantity(String text, String description) {
        String digits = (text == null) ? "" : text.replaceAll("[^0-9]", "");
        if (digits.isEmpty()) {
            softAssert.fail("Could not read a numeric " + description + " from '" + text + "'");
            return -1;
        }
        return Integer.parseInt(digits);
    }

    private static final By ANY_PRODUCT_CARD = By.xpath("//a[contains(@href,'/product-detail')]");

    @And("click on first product")
    public void clickOnFirstProduct() {
        if (!isAnyVisible(ANY_PRODUCT_CARD)) {
            throw new IllegalStateException("No product card is visible on "
                    + Grid.driver().getCurrentUrl() + " after waiting, so there is no first product"
                    + " to open. Looked for " + ANY_PRODUCT_CARD + ". Either the listing is empty on"
                    + " this environment or the page did not finish loading.");
        }

        clickFirstVisible(ANY_PRODUCT_CARD);
        log.info("Clicked the first product - now at " + Grid.driver().getCurrentUrl());
    }

    @And("add to cart")
    public void addToCart() {
        waitUntilElementIsClickable(addToCart);
        scrollToCenter(By.xpath(ADD_TO_CART_BTN));
        jsClick(By.xpath(ADD_TO_CART_BTN));
        log.info("Clicked on add to cart button");
    }

    @And("click view cart")
    public void clickViewCart() {
        try {
            waits(By.xpath(VIEWCART));
            jsClick(By.xpath(VIEWCART));
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

    @Given("empty the cart via API")
    public void emptyTheCartViaApi() {
        CartRCV2ApiImp cartApi = new CartRCV2ApiImp();
        int deleted;
        try {
            deleted = cartApi.emptyCartForLoggedInUser();
        } catch (Exception e) {
            throw new RuntimeException("Failed to empty the cart through the cart API before the"
                    + " scenario - the cart count assertions cannot be trusted", e);
        }

        int remaining = cartApi.getCartLineItemCount();
        String summary = "Line items deleted: " + deleted + ", remaining: " + remaining;
        log.info(deleted == 0
                ? "Cart was already empty before the scenario"
                : "Emptied the cart via API - " + summary);
        Allure.addAttachment("Cart cleanup before scenario", summary);

        Assert.assertEquals(remaining, 0,
                "Cart still holds " + remaining + " line item(s) after the API cleanup, so this"
                        + " scenario would start with a dirty cart");
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

    @Then("verify the Attribute values for Grade bulk bitumen in cart page with cart count {string}")
    public void verifyTheAttributeValuesForGradeBulkBitumenInCartPage(String count) {
        //Verify product name in cart page for cement
        softAssert.assertTrue(context.cartPdpProductName.equals(context.pdpProductName), "Product name in cart page is not matching with product name in PDP or product name is not displayed in cart page");

        waits(By.xpath(CART_COUNT));
        String fetchedCount = CustomUtil.getItemCount(CustomUtil.getText(By.xpath(CART_COUNT)));
        softAssert.assertEquals(fetchedCount, count, "Cart count is not updated in cart icon after adding product to cart");
    }

    @Then("verify the Attribute values for HR Sheet in cart page with cart count {string}")
    public void verifyTheAttributeValuesForHRSheetInCartPage(String count) {

        context.cartAttributeValue = waitAndGetText(CART_PAGE_ATTRIBUTE_VALUE);
        softAssert.assertFalse(context.cartAttributeValue.isEmpty(), "Attribute value is not displayed in cart page");
        softAssert.assertEquals(context.cartAttributeValue, context.pdpThickness + " x " + context.pdpWidth + " x " + context.pdpLength + " mm", "Attribute value in cart page is not matching with selected attribute value in PDP");
        log.info((context.pdpProductName) + "   " + context.cartPdpProductName);
        softAssert.assertTrue(context.cartPdpProductName.equals(context.pdpProductName), "Product name in cart page is not matching with product name in PDP or product name is not displayed in cart page");

        waits(By.xpath(CART_COUNT));
        String fetchedCount = CustomUtil.getItemCount(CustomUtil.getText(By.xpath(CART_COUNT)));
        softAssert.assertEquals(fetchedCount, count, "Cart count is not updated in cart icon after adding product to cart");

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
            boolean transactingCustomer = CustomerProfileUtil.fetchIsTransactingFlag();
            log.info("isTransacting for the logged-in customer: " + transactingCustomer);

            confirmBusinessDetailsIfDialogPresent();
            if(transactingCustomer){
                refreshAndWaitFor(ALL_CATEGORY);
                confirmBusinessDetailsIfDialogPresent();
                hoverOverElement(ALL_CATEGORY);
            }
            waitAndClickOn(CEMENT_PLP);
            log.info("Opened cement PLP page");
        } catch (Exception e) {
            log.fine("Failed to open PLP Cement");
            throw new RuntimeException("Failed to open PLP Cement", e);
        }
    }

    @And("enter quantity {string} for {string}")
    public void enterQuantityForProductOnPDP(String quantity, String product) {
        try {
            context.cartPdpProductName = getText(By.xpath(ITEM_NAME));
            jsClick(By.xpath("//input[@id='quantity-input']"));
            waitAndSetText(QUANTITY, quantity);
            log.info("Quantity " + quantity + " is entered");
            context.pdpQuantity = quantity;
        } catch (Exception e) {
            log.info("Failed to enter quantity " + quantity + " for product " + product);
            throw new RuntimeException("Failed to enter quantity " + quantity + " for product " + product, e);
        }
    }

    @And("click on Coated steel category")
    public void clickOnCoatedSteelCategory() {
        try {
            clickFirstVisible(By.xpath(coatedSteel));
            log.info("cursor moved to Coated Steel");
        } catch (Exception e) {
            throw new RuntimeException("mouse failed to move to coated steel", e);

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

    @Then("verify the Attribute values for Coated Steel Sheet in cart page")
    public void verifyTheAttributeValuesForCoatedSteelSheetInCartPage() {
        context.cartAttributeValue = waitAndGetText(CART_PAGE_ATTRIBUTE_VALUE);
        softAssert.assertFalse(context.cartAttributeValue.isEmpty(), "Attribute value is not displayed in cart page");
        softAssert.assertEquals(context.cartAttributeValue, context.pdpThickness + " x " + context.pdpWidth + " x " + context.pdpLength + " mm", "Attribute value in cart page is not matching with selected attribute value in PDP");
        softAssert.assertTrue(context.cartPdpProductName.equals(context.pdpProductName), "Product name in cart page is not matching with product name in PDP or product name is not displayed in cart page");
    }

    @And("click on Wire product")
    public void clickOnWireProductCapitalised() {
        clickOnWireProduct();
    }

    @And("click on wire product")
    public void clickOnWireProduct() {
        try {
            //Reading locator from page factory
            waits(By.xpath(wireCategory));
            clickFirstVisible(By.xpath(wireCategory));
            log.info("Clicked on Wire category");
        } catch (Exception e) {
            log.info("Failed to click on Wire category");
            throw new RuntimeException("Failed to click on Wire category", e);
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
            boolean transactingCustomer = CustomerProfileUtil.fetchIsTransactingFlag();
        log.info("isTransacting for the logged-in customer: " + transactingCustomer);

        confirmBusinessDetailsIfDialogPresent();
        if(transactingCustomer){
            refreshAndWaitFor(ALL_CATEGORY);
            confirmBusinessDetailsIfDialogPresent();
            hoverOverElement(ALL_CATEGORY);
        }
            waitAndClickOn(TMT_PRODUCT);
            log.info("Clicked on TMT roduct");
        } catch (Exception e) {
            log.info("Failed to click on TMT product");
            throw new RuntimeException("Failed to click on TMT product", e);
        }
    }

    @And("click on TMT plp {string} product")
    public void openTMTPdp(String product) {
        try {
            WebElement locator = Grid.driver().findElement(By.xpath(PLP_FIRST_PRODUCT));
            clickButton(locator);
            log.info("Opened TMT PDP page");
        } catch (Exception e) {
            log.info("Failed to open TMT Rebar PDP");
            throw new RuntimeException("Failed to open TMT Rebar PDP", e);
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

    @Then("verify the Attribute values for TMT in cart page")
    public void verifyTheAttributeValuesForTmtInCartPage() {
        softAssert.assertTrue(
                context.cartPdpProductName.toLowerCase().contains(context.pdpProductName.toLowerCase()),
                "TMT product name in cart page is not matching with product name in PDP - PDP shows '"
                        + context.cartPdpProductName + "', cart shows '" + context.pdpProductName + "'");

        //verify quantity in cart page
        context.cartPageQuantity = waitAndGetText(CART_PAGE_QUANTITY);
        softAssert.assertEquals(context.cartPageQuantity, "Qty: " + context.pdpQuantity + " MT",
                "Quantity in cart page is not matching with the quantity entered on the TMT PDP");

        //verify diameter and TMT form in cart page
        List<String> cartAttributeTexts = readCartAttributeTexts();
        context.cartAttributeValue = String.join(" | ", cartAttributeTexts);
        softAssert.assertFalse(cartAttributeTexts.isEmpty(),
                "No attribute value is displayed in cart page for TMT");
        assertCartAttributeShown(cartAttributeTexts, context.pdpDiameter, "diameter");
        assertCartAttributeShown(cartAttributeTexts, context.pdpForm, "TMT form");

        //verify cart count in cart icon
        waits(By.xpath(CART_COUNT));
        softAssert.assertEquals(getItemCount(getText(By.xpath(CART_COUNT))), "1",
                "Cart count is not updated in cart icon after adding the TMT product to cart");
    }

    @Then("Verify the doubled quantity for TMT in cart page with cart count {string}")
    public void verifyDoubledQuantityForTmtInCartPage(String count) {
        int timesAdded = 2;
        int pdpQuantity = parseQuantity(context.pdpQuantity, "quantity entered on the TMT PDP");
        context.cartPageQuantity = waitAndGetText(CART_PAGE_QUANTITY);
        int cartQuantity = parseQuantity(context.cartPageQuantity, "quantity shown in the cart");
        int expectedQuantity = pdpQuantity * timesAdded;

        softAssert.assertEquals(cartQuantity, expectedQuantity,
                "TMT was added " + timesAdded + " times at " + pdpQuantity + " MT each, so the merged"
                        + " line item should hold " + expectedQuantity + " MT - the cart shows '"
                        + context.cartPageQuantity + "'");

        softAssert.assertEquals(getItemCount(getText(By.xpath(CART_COUNT))), count,
                "Cart count is not '" + count + "' after adding the same TMT product " + timesAdded
                        + " times - the cart created a duplicate line item instead of merging");

        softAssert.assertTrue(
                context.cartPdpProductName.toLowerCase().contains(context.pdpProductName.toLowerCase()),
                "TMT product name in cart page is not matching with product name in PDP - PDP shows '"
                        + context.cartPdpProductName + "', cart shows '" + context.pdpProductName + "'");

        List<String> cartAttributeTexts = readCartAttributeTexts();
        context.cartAttributeValue = String.join(" | ", cartAttributeTexts);
        softAssert.assertFalse(cartAttributeTexts.isEmpty(),
                "No attribute value is displayed in cart page for the merged TMT line item");
        assertCartAttributeShown(cartAttributeTexts, context.pdpDiameter, "diameter");
        assertCartAttributeShown(cartAttributeTexts, context.pdpForm, "TMT form");

        log.info("Verified the merged TMT line item: " + cartQuantity + " MT across "
                + timesAdded + " adds");
    }

    private List<String> readCartAttributeTexts() {
        By locator = byForLocatorKey(CART_PAGE_ATTRIBUTE_VALUE);
        waits(locator);
        List<String> texts = new ArrayList<>();
        for (WebElement element : Grid.driver().findElements(locator)) {
            String text = element.getText();
            if (text != null && !text.trim().isEmpty()) {
                texts.add(text.trim());
            }
        }
        return texts;
    }

    private By byForLocatorKey(String key) {
        return xpathOf(getLocator(key));
    }

    private void assertCartAttributeShown(List<String> cartTexts, String selectedValue, String attributeName) {
        if (selectedValue == null || selectedValue.trim().isEmpty()) {
            softAssert.fail("No " + attributeName + " was captured on the TMT PDP, so the value shown"
                    + " in the cart cannot be verified");
            return;
        }
        String expected = selectedValue.trim();
        boolean shown = false;
        for (String text : cartTexts) {
            if (containsAsWholeWord(text, expected)) {
                shown = true;
                break;
            }
        }
        softAssert.assertTrue(shown,
                "Selected " + attributeName + " '" + expected + "' is not shown in the cart."
                        + " Cart attribute values found: " + cartTexts);
    }

    @When("move the mouse to Structural category")
    public void moveTheMouseToStructuralCategory() {
        //Reading locator from page factory
        hoverOverElement(By.xpath(structuralSteelCategory));
    }

    @And("click on Angles product")
    public void clickOnAnglesProduct() {
        //Reading locator from page factory
        clickFirstVisible(By.xpath(anglesSubCategory));
    }

    @And("open Angles pdp")
    public void openAnglesPdp() {
        scrollToCenter(By.xpath(PLP_FIRST_PRODUCT));
        waitAndCheckIsElementClickable(PLP_FIRST_PRODUCT);
        waitAndClickOn(PLP_FIRST_PRODUCT, "1");
    }

    @And("select all the Angles attribute")
    public void selectAllTheAnglesAttribute() {
        waitAndCheckIsElementClickable(LEG_LENGTH);
        waitAndClickOn(LEG_LENGTH, "1");

        waitAndCheckIsElementClickable(THICKNESS);
        waitAndClickOn(THICKNESS, "1");

        scrollToCenter(By.xpath(length));
        waitAndCheckIsElementClickable(LENGTH);
        waitAndClickOn(LENGTH, "1");

        waitAndCheckIsElementClickable(QUANTITY);
        waitAndSetText(QUANTITY, "50");

    }

    @And("click on Channels product")
    public void clickOnChannelsProduct() {
        //Reading locator from page factory
        clickFirstVisible(By.xpath(channelsSubCategory));
    }

    @And("open Channels pdp")
    public void openChannelsPdp() {
        scrollToCenter(By.xpath(PLP_FIRST_PRODUCT));
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

        scrollToCenter(By.xpath(length));
        waitAndCheckIsElementClickable(LENGTH);
        waitAndClickOn(LENGTH, "1");

        waitAndCheckIsElementClickable(QUANTITY);
        waitAndSetText(QUANTITY, "50");
    }

    @And("click on Beams & Columns product")
    public void clickOnBeamsColumnsProduct() {
        //Reading locator from page factory
        clickFirstVisible(By.xpath(beamsAndColumnsSubCategory));
    }

    @And("open Beams & Columns pdp")
    public void openBeamsColumnsPdp() {
        scrollToCenter(By.xpath(PLP_FIRST_PRODUCT));
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

        scrollToCenter(By.xpath(length));
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
                if (isDisplayed(By.xpath(UPDATE_ATTRIBUTE))) {
                    clickButton(By.xpath(UPDATE_ATTRIBUTE));
                }
                context.updatedDiameter = getText(By.xpath(UPDATE_ATTRIBUTE));
                log.info("Given update diameter " + context.updatedDiameter + "mm is not available for selection, selecting available update diameter value");
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
        int startingCount = lineItemCount();
        log.info("Cart holds " + startingCount + " line item(s); deleting them one by one");

        int attemptCap = startingCount + EXTRA_DELETE_ATTEMPTS;
        int attempts = 0;

        while (lineItemCount() > 0 && attempts < attemptCap) {
            attempts++;
            int before = lineItemCount();

            if (!deleteOneLineItem()) {
                log.info("Stopping after " + attempts + " attempt(s) - the Delete control or its"
                        + " confirmation dialog did not appear. The empty-cart assertion below"
                        + " is what reports this.");
                break;
            }

            waitForFewerLineItems(before);
            log.info("Deleted " + attempts + " of " + startingCount + " line item(s); "
                    + lineItemCount() + " remaining");
        }

        int remaining = lineItemCount();
        softAssert.assertTrue(waitForEmptyCartMessage(),
                "The cart does not show the empty-cart message after deleting the line items"
                        + " - started with " + startingCount + ", " + remaining + " still on the page"
                        + " after " + attempts + " delete attempt(s)");
    }

    private boolean deleteOneLineItem() {
        By deleteButton = byForLocatorKey(DELETE_LINE_ITEM);
        try {
            if (Grid.driver().findElements(deleteButton).isEmpty()) {
                log.info("No Delete control present on the cart line item");
                return false;
            }
            scrollToCenter(deleteButton);
            clickWithJsFallback(deleteButton, "cart line item Delete", DELETE_CONFIRM_WAIT_SECONDS);
        } catch (Exception e) {
            log.info("Failed to click Delete on the cart line item: " + e.getMessage());
            return false;
        }
        try {
            By confirm = byForLocatorKey(DELETE_CTA_BUTTON);
            if (waitForPresence(confirm, DELETE_CONFIRM_WAIT_SECONDS)) {
                scrollToCenter(confirm);
                clickWithJsFallback(confirm, "delete confirmation", DELETE_CONFIRM_WAIT_SECONDS);
            } else {
                log.info("No delete confirmation dialog appeared - treating the first click as final");
            }
        } catch (Exception e) {
            log.info("Failed to confirm the deletion: " + e.getMessage());
            return false;
        }
        return true;
    }

    private int lineItemCount() {
        try {
            return Grid.driver().findElements(byForLocatorKey(DELETE_LINE_ITEM)).size();
        } catch (Exception e) {
            return 0;
        }
    }

    private void waitForFewerLineItems(int before) {
        for (int attempt = 0; attempt < DELETE_SETTLE_POLLS && lineItemCount() >= before; attempt++) {
            sleep(500);
        }
    }

    private boolean waitForEmptyCartMessage() {
        for (int attempt = 0; attempt < 20; attempt++) {
            try {
                if (!Grid.driver().findElements(By.xpath(EMPTY_CART_MSG)).isEmpty()) {
                    return true;
                }
            } catch (Exception ignored) {
            }
            sleep(500);
        }
        return false;
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
        waitAndClickOn(ACCOUNT_TAB);
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

    @When("move the mouse to Mild steel category")
    public void moveTheMouseToMildSteelCategoryUnquoted() {
        moveTheMouseToCategory("Mild steel");
    }

    @When("move the mouse to {string} category")
    public void moveTheMouseToCategory(String category) {
        boolean transactingCustomer = CustomerProfileUtil.fetchIsTransactingFlag();
        log.info("isTransacting for the logged-in customer: " + transactingCustomer);

        confirmBusinessDetailsIfDialogPresent();
        if(transactingCustomer){
            refreshAndWaitFor(ALL_CATEGORY);
            confirmBusinessDetailsIfDialogPresent();
            hoverOverElement(ALL_CATEGORY);
        }

        if (category.equalsIgnoreCase("mild steel")) {
            confirmBusinessDetailsIfDialogPresent();
            waits(MILD_STEEL_CATEGORY);
            hoverOverElement(MILD_STEEL_CATEGORY);
            log.info("cursor moved to mild steel category");
            return;
        } else {
            By categoryLocator = getCategoryLocator(category);
            waits(categoryLocator);
            hoverOverElement(categoryLocator);
            log.info("cursor moved to category: " + category);
        }
    }

    @And("click on {string} product")
    public void clickOnProduct(String product) {
        confirmBusinessDetailsIfDialogPresent();
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
            case "galvanized sheet":
                clickGalvanizedSheetProduct();
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
            clickFirstVisible(locator);
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

    private void clickGalvanizedSheetProduct() {
        try {
            waitAndClickOn(GALVANIZED_SHEET);
            log.info("Clicked on Galvanized steel sheet product");
        } catch (Exception e) {
            log.info("Failed to click on Galvanized steel sheet product");
            throw new RuntimeException("Failed to click on Galvanized steel sheet product", e);
        }
    }

    private By getCategoryLocator(String category) {
        switch (category.trim().toLowerCase()) {
            case "mild steel":
                return MILD_STEEL_CATEGORY;
            case "hot rolled":
                return By.xpath(msHotRolledCategory);
            case "cold rolled":
                return By.xpath(msColdRolledCategory);
            case "galvanized sheet":
                return By.xpath(galvanizedSheetCategory);
            case "wire":
                return By.xpath(wireCategory);
            default:
                throw new IllegalArgumentException("Unknown category: " + category);
        }
    }

    private static final String HOT_ROLLED_LISTING_PATH = "/category/mild-steel/hot-rolled-steel";

    @When("move the mouse to Mild steel category and to sub category Hot rolled")
    public void moveTheMouseToMildSteelCategoryAndToSubCategoryHotRolled() {
        confirmBusinessDetailsIfDialogPresent();
        try {
            boolean transactingCustomer = CustomerProfileUtil.fetchIsTransactingFlag();
            log.info("isTransacting for the logged-in customer: " + transactingCustomer);
            if (transactingCustomer) {
                refreshAndWaitFor(ALL_CATEGORY);
                hoverOverElement(ALL_CATEGORY);
            }
            hoverOverMildSteelCategory(By.xpath(msHotRolledCategory));
            log.info("cursor moved to subcategory 'Hot rolled' under mild steel");
        } catch (Exception e) {
            String target = siteOrigin() + HOT_ROLLED_LISTING_PATH;
            log.info("Could not open the Mild steel > Hot rolled flyout (" + e.getClass().getSimpleName()
                    + ": " + e.getMessage() + "). Falling back to " + target
                    + " so the scenario continues.");
            Grid.driver().get(target);
            waitForPageLoad();
            log.info("Landed on the Hot rolled listing at " + Grid.driver().getCurrentUrl());
        }
    }

    /* Bitumen is NOT in the header any more. Measured on qa, 23 Sep 2026: zero anchors in <header>
     * whose href or text mentions bitumen, and zero anywhere on the home page. The category itself
     * is alive and only reachable by URL:
     *     /category/viscosity-grade-bitumen       -> 12 products
     *     /category/viscosity-grade-bulk-bitumen  ->  9 products
     * This is the same removal that took Bitumen out of the header nav assertions in Header.feature.
     * So the hover here cannot succeed - there is nothing to hover - and the fallback is the path. */
    private static final String VISCOSITY_GRADE_BITUMEN_LISTING_PATH = "/category/viscosity-grade-bitumen";
    private static final String GRADE_BULK_BITUMEN_LISTING_PATH = "/category/viscosity-grade-bulk-bitumen";

    @When("move the mouse to Bitumen category and to sub category Viscosity grade bitumen")
    public void moveTheMouseToBitumenCategoryAndToSubCategoryViscosityGradeBitumen() {
        confirmBusinessDetailsIfDialogPresent();
        try {
            boolean transactingCustomer = CustomerProfileUtil.fetchIsTransactingFlag();
            log.info("isTransacting for the logged-in customer: " + transactingCustomer);
            if (transactingCustomer) {
                refreshAndWaitFor(ALL_CATEGORY);
                hoverOverElement(ALL_CATEGORY);
            }
            hoverOverBitumenCategory();
            waits(By.xpath(viscosityGradeBitumenCategory));
            hoverOverElement(By.xpath(viscosityGradeBitumenCategory));
            log.info("cursor moved to subcategory 'Viscosity grade bitumen' under bitumen");
        } catch (Exception e) {
            String target = siteOrigin() + VISCOSITY_GRADE_BITUMEN_LISTING_PATH;
            log.info("Could not open the Bitumen > Viscosity grade bitumen flyout ("
                    + e.getClass().getSimpleName() + ": " + e.getMessage() + "). Bitumen is no"
                    + " longer in the header, so falling back to " + target);
            Grid.driver().get(target);
            waitForPageLoad();
            log.info("Landed on the Viscosity grade bitumen listing at " + Grid.driver().getCurrentUrl());
        }
    }

    @And("click on Hollow sections product")
    public void clickOnHollowSectionsProduct() {
        if (openHotRolledSubCategoryByUrl("hollow-sections", "Hollow sections")) {
            return;
        }
        jsClick(By.xpath(hollowSectionsSubCategory));
    }

     @And("click on Grade bulk bitumen product")
    public void clickOnGradeBulkBitumenProduct() {
        if (openHotRolledSubCategoryByUrl("grade-bulk-bitumen", "Grade bulk bitumen")) {
            return;
        }
        try {
            jsClick(By.xpath(gradeBulkBitumenSubCategory));
        } catch (Exception e) {
            // Reached when the previous step fell back to the listing URL: the flyout is not open,
            // so the sub-category anchor is not on the page to click.
            String target = siteOrigin() + GRADE_BULK_BITUMEN_LISTING_PATH;
            log.info("Could not click the Grade bulk bitumen sub-category ("
                    + e.getClass().getSimpleName() + "). Falling back to " + target);
            Grid.driver().get(target);
            waitForPageLoad();
        }
    }

    /**
     * Opens a Hot rolled sub-listing by URL when we are already sitting on the Hot rolled listing
     * (the flyout is not reachable from there).
     *
     * <p>The third-level listing is verified before it is accepted. The site has been pruning that
     * level of the taxonomy: on qa, 23 Sep 2026, {@code .../hot-rolled-steel/hot-rolled-coils}
     * still answers 200 but renders the "We couldn't find that page" shell with zero product
     * cards, while {@code .../hot-rolled-sheets} and {@code .../hollow-sections} still carry 60
     * and 6. Nothing on the site links to a third-level category any more.
     *
     * <p>Because the dead URL answers 200 rather than 404, navigating to it looked like success
     * and the failure only surfaced one step later as "No product card is visible ... so there is
     * no first product to open" - which reads like an empty catalogue rather than a removed page.
     * That cost two PDP scenarios in build 256.
     *
     * <p>So: navigate, and if the destination has no product cards, fall back to the parent Hot
     * rolled listing, which still carries every product the sub-listings used to split up
     * (including "JSW Steel Hot Rolled Coils IS 2062:2011 E250A"). Returns true either way - the
     * caller's job is done, we are on a listing with products on it.
     */
    private boolean openHotRolledSubCategoryByUrl(String segment, String label) {
        return openSubCategoryByUrl(HOT_ROLLED_LISTING_PATH, segment, label);
    }

    private boolean openSubCategoryByUrl(String listingPath, String segment, String label) {
        if (!listingPath.equals(currentPath())) {
            return false;
        }
        String parent = siteOrigin() + listingPath;
        String target = parent + "/" + segment;
        log.info("On the Hot rolled listing rather than the flyout, so opening " + label
                + " by URL instead of clicking: " + target);
        Grid.driver().get(target);
        waitForPageLoad();

        if (!isAnyVisible(ANY_PRODUCT_CARD)) {
            log.info("The " + label + " sub-listing at " + target + " rendered no product cards,"
                    + " so that third-level category no longer exists. Falling back to the parent"
                    + " listing at " + parent + ", which still carries its products.");
            Grid.driver().get(parent);
            waitForPageLoad();
        }
        return true;
    }

    @And("click on HR Sheet product")
    public void selectHRSheetProduct() {
        if (openHotRolledSubCategoryByUrl("hot-rolled-sheets", "HR Sheet")) {
            return;
        }
        jsClick(By.xpath(hotRolledSheetsSubCategory));
    }

    @And("click on HR Coil product")
    public void selectHRCoilProduct() {
        if (openHotRolledSubCategoryByUrl("hot-rolled-coils", "HR Coil")) {
            return;
        }
        jsClick(By.xpath(hotRolledCoilsSubCategory));
    }

    private static final Map<String, String> CATEGORY_PRODUCTS = buildCategoryProducts();

    private static Map<String, String> buildCategoryProducts() {
        Map<String, String> products = new LinkedHashMap<>();
        products.put("hr sheet", "SAIL Hot Rolled Sheets IS 2062:2011 E350C");
        products.put("hr coil", "JSW Steel Hot Rolled Coils IS 2062:2011 E250A");
        products.put("hrpo sheet", "Neotrex Silveron+ Hot Rolled Pickled and Oiled Sheet 1079:2017 1040");
        products.put("wire rod coils", "JSW Steel Wire Rod Coils IS 7887:1992 SAE1010");
        products.put("galvanized sheet", "JSW Steel Galvanized Steel Sheets IS 277:2018 GP250");
        products.put("cement", "JSW Ordinary Portland Cement 43");
        products.put("tmt", "JSW One FE 550 TMT Rebar");
        products.put("pipes and tubes", "One Helix Circular Hollow Section IS 1161 YSt 210 Black");
        products.put("bitumen", "Standard IS 73:2013 VG40 Bitumen (Bulk)");
        return products;
    }

    @And("click on the product for {string} category")
    public void clickOnTheProductForCategory(String category) {
        String productName = productNameFor(category);
        By card = productCardByName(productName);

        log.info("Looking for '" + productName + "' (" + category + ") on "
                + Grid.driver().getCurrentUrl());

        if (!scrollUntilVisible(card)) {
            throw new IllegalStateException("Product '" + productName + "' for category '" + category
                    + "' was not found on " + Grid.driver().getCurrentUrl()
                    + " after scrolling to the bottom of the listing. Either the listing does not"
                    + " carry that product on this environment, or its displayed name has changed -"
                    + " the match is on the name shown on the card, exactly.");
        }

        scrollToCenter(card);
        clickFirstVisible(card);
        context.cartPdpProductName = productName;
        log.info("Clicked '" + productName + "' - now at " + Grid.driver().getCurrentUrl());
    }

    private String productNameFor(String category) {
        String product = CATEGORY_PRODUCTS.get(normaliseCategory(category));
        if (product == null) {
            throw new IllegalArgumentException("No product configured for category '" + category
                    + "'. Known categories: " + CATEGORY_PRODUCTS.keySet()
                    + ". Add it to CATEGORY_PRODUCTS rather than putting a product name in the"
                    + " feature file, so the name lives in one place when the catalogue changes.");
        }
        return product;
    }

    private static String normaliseCategory(String category) {
        if (category == null) {
            return "";
        }
        String value = category.trim();
        int lastSeparator = value.lastIndexOf('>');
        if (lastSeparator >= 0) {
            value = value.substring(lastSeparator + 1);
        }
        return value.trim().replaceAll("\\s+", " ").toLowerCase(Locale.ROOT);
    }

    private By productCardByName(String productName) {
        return By.xpath("//a[contains(@href,'/product-detail/')]"
                + "[.//*[normalize-space(text())=" + xpathLiteral(productName) + "]]");
    }

    private static final String COLD_ROLLED_LISTING_PATH = "/category/mild-steel/cold-rolled-steel";

    /**
     * Mirrors the Hot rolled step's fallback, which Cold rolled did not have.
     *
     * <p>Build 256 lost "Verify reset in pdp page." here: "could not open Mild steel &gt;
     * //li/a[contains(@href,'cold-rolled-steel')]/span by either a chained pointer hover or a
     * synthetic one". The locator is correct - that anchor is present in the header as LI &gt; A
     * &gt; SPAN, verified on qa 23 Sep 2026 - so this is the flyout failing to open, not a missing
     * element. There is no reason to lose the scenario over it when the listing has a URL.
     */
    @When("move the mouse to Mild steel category and to sub category Cold rolled")
    public void moveTheMouseToMildSteelCategoryAndToSubCategoryColdRolled() {
        confirmBusinessDetailsIfDialogPresent();
        try {
            boolean transactingCustomer = CustomerProfileUtil.fetchIsTransactingFlag();
            log.info("isTransacting for the logged-in customer: " + transactingCustomer);
            if (transactingCustomer) {
                refreshAndWaitFor(ALL_CATEGORY);
                hoverOverElement(ALL_CATEGORY);
            }
            hoverOverMildSteelCategory(By.xpath(msColdRolledCategory));
            log.info("cursor moved to subcategory 'Cold rolled' under mild steel");
        } catch (Exception e) {
            String target = siteOrigin() + COLD_ROLLED_LISTING_PATH;
            log.info("Could not open the Mild steel > Cold rolled flyout (" + e.getClass().getSimpleName()
                    + ": " + e.getMessage() + "). Falling back to " + target
                    + " so the scenario continues.");
            Grid.driver().get(target);
            waitForPageLoad();
            log.info("Landed on the Cold rolled listing at " + Grid.driver().getCurrentUrl());
        }
    }

    @And("click on CR Coil product")
    public void selectCRCoilProduct() {
        if (openSubCategoryByUrl(COLD_ROLLED_LISTING_PATH, "cold-rolled-coils", "CR Coil")) {
            return;
        }
        jsClick(By.xpath(coldRolledCoilsSubCategory));
    }

    @And("click on CR Sheet product")
    public void selectCRSheetProduct() {
        if (openSubCategoryByUrl(COLD_ROLLED_LISTING_PATH, "cold-rolled-sheets", "CR Sheet")) {
            return;
        }
        scrollToCenter(By.xpath(coldRolledSheetsSubCategory));
        jsClick(By.xpath(coldRolledSheetsSubCategory));
    }

    /* NOT /category/steel-coated, which is what the header anchor points at: that redirects to
     * /category/mild-steel/coated-steel, which renders zero product cards. The live listing is
     * the one below, with 18 - measured on qa, 23 Sep 2026. */
    private static final String COATED_STEEL_LISTING_PATH = "/category/mild-steel/steel-coated";

    @When("move the mouse to Mild steel category and to sub category Coated steel")
    public void moveTheMouseToMildSteelCategoryAndToSubCategoryCoatedSteel() {
        confirmBusinessDetailsIfDialogPresent();
        try {
            boolean transactingCustomer = CustomerProfileUtil.fetchIsTransactingFlag();
            log.info("isTransacting for the logged-in customer: " + transactingCustomer);
            if (transactingCustomer) {
                refreshAndWaitFor(ALL_CATEGORY);
                hoverOverElement(ALL_CATEGORY);
            }
            hoverOverMildSteelCategory(By.xpath(coatedSteelCategory));
            log.info("cursor moved to subcategory 'Coated steel' under mild steel");
        } catch (Exception e) {
            String target = siteOrigin() + COATED_STEEL_LISTING_PATH;
            log.info("Could not open the Mild steel > Coated steel flyout (" + e.getClass().getSimpleName()
                    + ": " + e.getMessage() + "). Falling back to " + target
                    + " so the scenario continues.");
            Grid.driver().get(target);
            waitForPageLoad();
            log.info("Landed on the Coated steel listing at " + Grid.driver().getCurrentUrl());
        }
    }

    @And("click on Galvanized sheet product")
    public void selectGalvanizedSteelSheetProduct() {
        jsClick(By.xpath(galvanizedSheetCategory));
    }

    private void hoverOverMildSteelCategory(By subCategory) {
        if (hoverChain(MILD_STEEL_CATEGORY, subCategory)) {
            log.info("cursor moved through mild steel to '" + subCategory + "'");
            return;
        }

        log.info("chained hover did not reach '" + subCategory + "' - trying a synthetic hover");
        if (hoverUntilVisible(MILD_STEEL_CATEGORY, subCategory)) {
            hoverOverElement(subCategory);
            log.info("cursor moved to '" + subCategory + "' after a synthetic hover");
            return;
        }

        throw new IllegalStateException("could not open Mild steel > " + subCategory
                + " by either a chained pointer hover or a synthetic one. If the log above says the"
                + " pointer reached Mild steel and nothing opened, the flyout is CSS :hover driven"
                + " and something is taking the pointer off it - look for a scroll or an overlay,"
                + " not for more synthetic events.");
    }

    private void hoverOverBitumenCategory() {
        hoverOverElement(BITUMEN_CATEGORY);
        log.info("cursor moved to bitumen category");
    }

    @And("select diameter, form, quantity {string} for TMT")
    public void selectAllTheTMTRebarAttribute(String quantity) {
        selectProductAttributes("TMT", quantity);
    }

    @And("select diameter and quantity {string} for Wire")
    public void selectAllTheWireAttribute(String quantity) {
        selectProductAttributes("Wire", quantity);
    }

    @And("select thickness, width, length and quantity {string} for Coated Sheet")
    public void selectAllTheCoatedSteelSheetAttribute(String quantity) {
        selectProductAttributes("Coated Sheet", quantity);
    }

    @And("select thickness, width, length, quantity {string} for CR Sheet")
    public void selectAllTheCRSheetAttribute(String quantity) {
        selectProductAttributes("CR Sheet", quantity);
    }

    @And("select thickness, width, quantity {string} for CR Coil")
    public void selectAllTheCRCoilAttribute(String quantity) {
        selectProductAttributes("CR Coil", quantity);
    }

    @And("select thickness, width, length, quantity {string} for HR Sheet")
    public void selectAllTheHrAttribute(String quantity) {
        // HR Sheet scenarios have always used a fixed quantity of 2 and expect a 2-SKU cart.
        selectProductAttributes("HR Sheet", quantity);
    }

    @And("select thickness, width, quantity {string} for HR Coil")
    public void selectAllTheHrCoilAttribute(String quantity) {
        selectProductAttributes("HR Coil", quantity);
    }

    @And("enter quantity {string} in PDP without verifying the committed value")
    public void enterQuantityInPdpWithoutVerifyingTheCommittedValue(String quantity) {
        waits(By.xpath(QUANTITY_TEXTBOX));
        scrollToCenter(By.xpath(QUANTITY_TEXTBOX));
        WebElement quantityInput = Grid.driver().findElement(By.xpath(QUANTITY_TEXTBOX));
        jsClick(quantityInput);

        quantityInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        enterText(By.xpath(QUANTITY_TEXTBOX), quantity);
        quantityInput.sendKeys(Keys.ENTER);
        confirmBusinessDetailsIfDialogPresent();
        jsClick(quantityLabel);

        String held = settledValueOf(By.xpath(QUANTITY_TEXTBOX));
        log.info("Entered quantity '" + quantity + "' with verification disabled - field now holds '"
                + held + "'. Not asserted: this step is for values the PDP is expected to reject.");
        Allure.addAttachment("Quantity entered without verification",
                "Requested: " + quantity
                        + "\nField holds after commit: '" + held + "'"
                        + "\nNo quantity assertion performed - the next step asserts the Add to cart state.");
    }

    private enum PdpAttribute {
        DIAMETER,
        FORM,
        THICKNESS,
        WIDTH,
        LENGTH,
        CUSTOM_LENGTH,
        LENGTH_OR_CUSTOM,
        COATING_GRADE,
        SPANGLE_TYPE
    }

    private static final Map<String, List<PdpAttribute>> PRODUCT_ATTRIBUTES = buildProductAttributes();

    private static Map<String, List<PdpAttribute>> buildProductAttributes() {
        Map<String, List<PdpAttribute>> map = new LinkedHashMap<>();

        // Long products - sized by diameter, not by sheet dimensions.
        map.put("tmt", Arrays.asList(PdpAttribute.FORM, PdpAttribute.DIAMETER));
        map.put("tmtrebar", Arrays.asList(PdpAttribute.FORM,PdpAttribute.DIAMETER));
        map.put("wire", Collections.singletonList(PdpAttribute.DIAMETER));

        // Flat products - thickness and width always, length varies by product.
        map.put("coatedsheet", Arrays.asList(PdpAttribute.THICKNESS, PdpAttribute.WIDTH, PdpAttribute.LENGTH_OR_CUSTOM, PdpAttribute.COATING_GRADE, PdpAttribute.SPANGLE_TYPE));
        map.put("coatedsteelsheet", Arrays.asList(PdpAttribute.THICKNESS, PdpAttribute.WIDTH, PdpAttribute.LENGTH_OR_CUSTOM, PdpAttribute.COATING_GRADE, PdpAttribute.SPANGLE_TYPE));
        map.put("crsheet", Arrays.asList(PdpAttribute.THICKNESS, PdpAttribute.WIDTH, PdpAttribute.LENGTH));
        map.put("coldrolledsheet", Arrays.asList(PdpAttribute.THICKNESS, PdpAttribute.WIDTH, PdpAttribute.LENGTH));
        map.put("hrsheet", Arrays.asList(PdpAttribute.THICKNESS, PdpAttribute.WIDTH, PdpAttribute.LENGTH));
        map.put("hotrolledsheet", Arrays.asList(PdpAttribute.THICKNESS, PdpAttribute.WIDTH, PdpAttribute.CUSTOM_LENGTH));

        // Coils - no length, they are sold by weight.
        map.put("crcoil", Arrays.asList(PdpAttribute.THICKNESS, PdpAttribute.WIDTH));
        map.put("coldrolledcoil", Arrays.asList(PdpAttribute.THICKNESS, PdpAttribute.WIDTH));
        map.put("hrcoil", Arrays.asList(PdpAttribute.THICKNESS, PdpAttribute.WIDTH));
        map.put("hotrolledcoil", Arrays.asList(PdpAttribute.THICKNESS, PdpAttribute.WIDTH));

        return map;
    }

    @And("select attributes for {string} with quantity {string}")
    public void selectProductAttributes(String productName, String quantity) {
        List<PdpAttribute> attributes = PRODUCT_ATTRIBUTES.get(normaliseProductName(productName));
        if (attributes == null) {
            throw new IllegalArgumentException("No PDP attribute profile for product '" + productName
                    + "'. Known products: " + PRODUCT_ATTRIBUTES.keySet());
        }

        context.cartPdpProductName = getText(By.xpath(ITEM_NAME));
        log.info("Selecting attributes " + attributes + " for '" + productName
                + "' on PDP '" + context.cartPdpProductName + "'");

        for (PdpAttribute attribute : attributes) {
            switch (attribute) {
                case FORM:
                    context.pdpForm = selectFirstEnabledOption(FORM, "form", productName);
                    break;
                case DIAMETER:
                    context.pdpDiameter = selectFirstEnabledOption(DIAMETER, "diameter", productName);
                    break;
                case THICKNESS:
                    context.pdpThickness = selectFirstEnabledOption(THICKNESS, "thickness", productName);
                    break;
                case WIDTH:
                    context.pdpWidth = selectFirstEnabledOption(WIDTH_BUTTON, "width", productName);
                    break;
                case LENGTH:
                    context.pdpLength = selectFirstEnabledOption(LENGTH_BUTTON, "length", productName);
                    break;
                case CUSTOM_LENGTH:
                    context.pdpLength = enterCustomLengthIfPresent(productName);
                    break;
                case LENGTH_OR_CUSTOM:
                    context.pdpLength = waitForOptionAvailable(LENGTH_BUTTON)
                            ? selectFirstEnabledOption(LENGTH_BUTTON, "length", productName)
                            : enterCustomLengthIfPresent(productName);
                    break;
                case COATING_GRADE:
                    context.pdpCoatingGrade = selectFirstEnabledOption(COATING_GRADE, "coating grade", productName);
                    break;
                case SPANGLE_TYPE:
                    context.pdpSpangleType = selectFirstEnabledOption(SPANGLE_TYPE, "spangle type", productName);
                    break;
                default:
                    throw new IllegalStateException("Unhandled PDP attribute: " + attribute);
            }
        }
        sleep(1000);
        enterQuantityUsingText(quantity, productName);
        confirmBusinessDetailsIfDialogPresent();
        jsClick(quantityLabel);
    }

    private String enterCustomLengthIfPresent(String productName) {
        if (!waitForElementPresent(CUSTOM_LENGTH_TEXTBOX, OPTIONAL_ATTRIBUTE_WAIT_SECONDS)) {
            String message = "No custom length field on the PDP for '" + productName
                    + "' after waiting " + OPTIONAL_ATTRIBUTE_WAIT_SECONDS + "s - skipping it.";
            log.info(message);
            Allure.addAttachment("Custom length skipped for " + productName, message);
            return null;
        }
        return enterCustomLength(productName);
    }

    private boolean waitForOptionAvailable(String optionXpath) {
        return pollFor(() -> isFirstMatchInteractable(By.xpath(optionXpath)),
                OPTIONAL_ATTRIBUTE_WAIT_SECONDS);
    }

    private String selectFirstEnabledOption(String optionXpath, String attributeName, String productName) {
        try {
            waits(By.xpath(optionXpath));
            scrollToCenter(By.xpath(optionXpath));
            jsClick(By.xpath(optionXpath));
            String selected = getText(By.xpath(optionXpath));
            log.info("Selected " + attributeName + " '" + selected + "' for " + productName);
            Allure.addAttachment(attributeName + " selected for " + productName,
                    "Selected " + attributeName + ": " + selected);
            return selected;
        } catch (Exception e) {
            log.info("Failed to select " + attributeName + " for " + productName);
            throw new RuntimeException("Failed to select " + attributeName + " for " + productName, e);
        }
    }

    private String enterCustomLength(String productName) {
        try {
            waits(By.xpath(CUSTOM_LENGTH_MSG));
            scrollToCenter(By.xpath(CUSTOM_LENGTH_MSG));
            String hint = getText(By.xpath(CUSTOM_LENGTH_MSG));
            String lengthValue = String.valueOf(CustomUtil.generateValueFromText(hint));

            waits(By.xpath(CUSTOM_LENGTH_TEXTBOX));
            WebElement lengthInput = Grid.driver().findElement(By.xpath(CUSTOM_LENGTH_TEXTBOX));
            scrollToCenter(By.xpath(CUSTOM_LENGTH_TEXTBOX));
            jsClick(lengthInput);
            setValueJS(lengthInput, lengthValue);
            lengthInput.sendKeys(Keys.ENTER);

            log.info("Entered custom length '" + lengthValue + " mm' for " + productName);
            Allure.addAttachment("Custom length entered for " + productName,
                    "Custom length: " + lengthValue + " mm (from hint: " + hint + ")");
            return lengthValue;
        } catch (Exception e) {
            log.info("Failed to enter custom length for " + productName);
            throw new RuntimeException("Failed to enter custom length for " + productName, e);
        }
    }

    private void enterQuantityUsingText(String quantity, String productName) {
        try {
            waits(By.xpath(QUANTITY_TEXTBOX));
            scrollToCenter(By.xpath(QUANTITY_TEXTBOX));
            WebElement quantityInput = Grid.driver().findElement(By.xpath(QUANTITY_TEXTBOX));
            jsClick(quantityInput);

            quantityInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            enterText(By.xpath(QUANTITY_TEXTBOX), quantity);
            quantityInput.sendKeys(Keys.ENTER);

            String expected = truncateToMaxLength(quantityInput, quantity);
            String accepted = readCommittedQuantity(expected);
            context.pdpQuantity = accepted;

            assertQuantityAccepted(accepted, quantity, expected, productName);

            log.info("Entered quantity '" + quantity + "' for " + productName
                    + " - PDP holds '" + accepted + "' after commit");
            Allure.addAttachment("Quantity entered for " + productName,
                    "Requested: " + quantity + "\nPDP value after commit: " + accepted);
        } catch (Exception e) {
            log.info("Failed to enter quantity '" + quantity + "' for " + productName);
            throw new RuntimeException("Failed to enter quantity for " + productName, e);
        }
    }

    private String readCommittedQuantity(String expected) {
        String value = "";
        for (int attempt = 0; attempt < 10; attempt++) {
            String raw = Grid.driver().findElement(By.xpath(QUANTITY_TEXTBOX)).getAttribute("value");
            value = (raw == null) ? "" : raw.replaceAll("[^0-9]", "");
            if (expected.equals(value)) {
                return value;
            }
            sleep(300);
        }
        return value;
    }

    private void assertQuantityAccepted(String accepted, String requested, String expected, String productName) {
        if (expected.equals(accepted)) {
            return;
        }
        if (!requested.equals(expected)) {
            softAssert.fail("Quantity field for " + productName + " declares a maxlength that should"
                    + " turn '" + requested + "' into '" + expected + "', but it holds '" + accepted + "'");
            return;
        }
        if (!accepted.isEmpty() && requested.startsWith(accepted)) {
            String message = "PDP capped the quantity for " + productName + " - requested '"
                    + requested + "', field holds '" + accepted + "'. Treated as the field's own"
                    + " length limit, which Pdp.feature asserts separately.";
            log.info(message);
            Allure.addAttachment("Quantity capped by the PDP for " + productName, message);
            return;
        }
        softAssert.fail("PDP did not accept the quantity for " + productName + " - the field holds '"
                + accepted + "' after entering '" + requested + "'");
    }

    private static String normaliseProductName(String productName) {
        if (productName == null) {
            return "";
        }
        return productName.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9]", "");
    }
}