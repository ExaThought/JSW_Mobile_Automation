package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.pageFactory.CategoryPages;
import com.qapitol.jsw.utils.LocatorReader;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import static com.qapitol.jsw.pageFactory.CategoryPages.*;
import static com.qapitol.jsw.pageFactory.CategoryPages.anglesSubCategory;
import static com.qapitol.jsw.pageFactory.CategoryPages.beamsAndColumnsSubCategory;
import static com.qapitol.jsw.pageFactory.CategoryPages.channelsSubCategory;
import static com.qapitol.jsw.pageFactory.CategoryPages.coatedSteel;
import static com.qapitol.jsw.pageFactory.CategoryPages.coldRolledCategory;
import static com.qapitol.jsw.pageFactory.CategoryPages.coldRolledCoilsSubCategory;
import static com.qapitol.jsw.pageFactory.CategoryPages.coldRolledSheetsSubCategory;
import static com.qapitol.jsw.pageFactory.CategoryPages.editEnterQuantity;
import static com.qapitol.jsw.pageFactory.CategoryPages.hotRolledCoilsSubCategory;
import static com.qapitol.jsw.pageFactory.CategoryPages.length;
import static com.qapitol.jsw.pageFactory.CategoryPages.plpFirstProduct;
import static com.qapitol.jsw.pageFactory.CategoryPages.structuralSteelCategory;
import static com.qapitol.jsw.pageFactory.CategoryPages.wireCategory;
import static com.qapitol.jsw.utils.CustomUtil.*;
import static com.qapitol.jsw.utils.Hooks.softAssert;

public class mobilecartsteps extends AbstractWebPage {
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
    private static final String CATALOGUE_MENU = "catalogueMenu";
    private static final String MSHOTROLLED_CATEGORY = "mshotrolledCategory";
    private static final String HRSHEET = "hrSheet";
    private static final String MTHICKNESS = "mthickness";
    private static final String PRICE_DETAILS = "priceDetails";
    private static final String ADD_TO_CART_MOBILE = "mobaddTocart";
    private static final String VIEW_CART_MOBILE = "mviewCart";
    private static final String GET_PRICE_NOW = "getpricenow";
    private static final String HRCOIL = "hrcoil";
    private static final String COLDROLLEDCOIL = "coldrolledcoil";

    private final JavascriptExecutor js = (JavascriptExecutor) Grid.driver();

    Actions actions = new Actions(Grid.driver());
    static String requirementId;
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
    static String mpdpthickness;


    @When("user clicks on catalogue menu")
    public void clickCatalogueMenu() {
        waitAndClickOn(CATALOGUE_MENU);
        log.info("Clicked on catalogue menu");
    }

    @And("user clicks Hot rolled category")
    public void clickHotRolledCategory() {
        waitAndClickOn(MSHOTROLLED_CATEGORY);
    }

    @And("click on HR Sheet product on msite")
    public void clickOnHRSheetProductOnMsite() {
        waitAndClickOn(HRSHEET);
    }

    @And("click on HR Coil product on msite")
    public void clickOnHRCoilProductOnMsite() {
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
        WebElement hrCoilElement = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(HRCOIL)));
        hrCoilElement.click();
    }

    @And("user clicks Cold rolled category")
    public void clickOnHcoldrolledOnMsite() {
        waitAndClickOn(COLDROLLEDCOIL);
    }

    private void scrollToElemnt(String headerText) {
        // Delegate to CustomUtil
        scrollToCenter(headerText);
    }

    private void scrollAboveSticy(WebElement element) {
        // Deprecated: Use CustomUtil.scrollAboveSticky(String xpath) instead
        int y = element.getLocation().getY();
        int offset = 180;
        js.executeScript(
                "window.scrollTo(0, arguments[0] - arguments[1]);",
                y, offset);
    }

    @And("select thickness {string} width {string} length {string} quantity {string} for HR Sheet on msite")
    public void selectAttributesForHRSheetOnMsite(String thickness,
            String width,
            String length,
            String quantity) {

        waitAndClickOn(PRICE_DETAILS);

        try {
            // ===== THICKNESS =====
            scrollToElemnt("Thickness (mm)");
            waitAndClickOn(ATTRIBUTE, thickness);
            pdpThickness = thickness;

            // ===== WIDTH =====
            scrollToElemnt("Width (mm)");
            waitAndClickOn(ATTRIBUTE, width);
            pdpWidth = width;

            // ===== LENGTH =====
            scrollToElemnt("Length (mm)");
            waitAndClickOn(ATTRIBUTE, length);
            pdpLength = length;

            // ===== QUANTITY =====
            Thread.sleep(1000);
            scrollToElemnt("Specify quantity (In number of sheets)");
            waitAndSetText(QUANTITY, quantity);
            pdpQuantity = quantity;

        } catch (Exception e) {
            throw new RuntimeException("Failed to select HR Sheet attributes", e);
        }
    }

    @And("select thickness {string} width {string} quantity {string} for HR Coil on msite")
    public void selectAttributesForHRcoilOnMsite(String thickness,
                                                  String width,
                                                  String length,
                                                  String quantity) {

        System.out.println("entered");

        waitAndClickOn(PRICE_DETAILS);

        try {
            // ===== THICKNESS =====
            scrollToElemnt("Thickness (mm)");
            waitAndClickOn(ATTRIBUTE, thickness);
            pdpThickness = thickness;

            // ===== WIDTH =====
            scrollToElemnt("Width (mm)");
            waitAndClickOn(ATTRIBUTE, width);
            pdpWidth = width;

            // ===== QUANTITY =====
            Thread.sleep(1000);
            scrollToElemnt("Specify quantity (In number of sheets)");
            waitAndSetText(QUANTITY, quantity);
            pdpQuantity = quantity;

        } catch (Exception e) {
            throw new RuntimeException("Failed to select HR Sheet attributes", e);
        }
    }

    @And("add to cart on mobile")
    public void addToCartonmobile() {
        try {
            pdpProductName = waitAndGetText(PDP_PRODUCT_NAME);
            log.info("Product name " + pdpProductName + " is fetched from PDP");
            if (pdpProductName.isEmpty()) {
                log.info("Product name is empty on PDP");
            }
        } catch (Exception e) {
            log.info("Failed to get product name from PDP");
        }
        try {
            sleep(1000);
            waitAndCheckIsElementClickable(ADD_TO_CART_MOBILE);
            waitAndClickOn(ADD_TO_CART_MOBILE);
            log.info("Clicked on add to cart button");
        } catch (Exception e) {
            throw new RuntimeException("Failed to click on add to cart button", e);
        }
    }

    @And("click view cart on mobile")
    public void clickViewCartonmobile() {
        By viewCartBy = By.xpath("//div[normalize-space()='View cart']");

        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));

        WebElement viewCart = wait.until(
                ExpectedConditions.visibilityOfElementLocated(viewCartBy));

        // scroll into center (important for sticky footer)
        ((JavascriptExecutor) Grid.driver()).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", viewCart);

        // JS click (critical)
        ((JavascriptExecutor) Grid.driver()).executeScript(
                "arguments[0].click();", viewCart);
    }

    @Then("Verify the Attribute values for HR Coil in cart page on msite")
    public void verifyTheAttributeValuesForHRCoilinCartPagemobile() {

        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));



        WebElement qtyElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(CART_PAGE_QUANTITY)));

        cartPageQuantity = qtyElement.getText();

        softAssert.assertEquals(cartPageQuantity, "Qty: " + pdpQuantity + " MT");

        cartAttributeValue = waitAndGetText(CART_PAGE_ATTRIBUTE_VALUE);

        softAssert.assertFalse(cartAttributeValue.isEmpty(),
                "Attribute value is not displayed in cart page");

        softAssert.assertEquals(cartAttributeValue,
                pdpThickness + " x " + pdpWidth + " mm",
                "Attribute value mismatch");

        softAssert.assertTrue(
                isElementPresent(CART_PAGE_PRODUCT_NAME, pdpProductName),
                "Product name mismatch");
    }

    @And("click edit on mobile cart")
    public void mclickEdit() {
        By editBtn = By.xpath("(//div[normalize-space()='Edit'])[1]");

        // Wait for loader to disappear
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loader")));

        // Use centralized safe click utility
        safeClick(editBtn);
    }

    // Scroll methods moved to CustomUtil - use scrollToCenter(),
    // scrollAboveSticky(), safeClick()

}