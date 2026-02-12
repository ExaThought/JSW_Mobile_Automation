package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.pageFactory.CategoryPages;
import com.qapitol.jsw.utils.LocatorReader;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.mobile.pages.AbstractMobilePage;
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

public class mobilecartsteps extends AbstractMobilePage {
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
    private static final String M_LENGTH = "mLength";
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
    private static final String MTHICKNESS = "mthickness";
    private static final String PRICE_DETAILS = "priceDetails";
    private static final String ADD_TO_CART_MOBILE = "mobaddTocart";
    private static final String VIEW_CART_MOBILE = "mviewCart";
    private static final String GET_PRICE_NOW = "getpricenow";
    private static final String THICKNESS_DROPDOWN="thicknessDropdown";
    private static final String M_WIDTH="mWidth";
    private static final String PLP_PRODUCT="plpProduct";
    private static final String CATEGORY_PRODUCT="categoryProduct";

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
        try {
            waitAndClickOn(CATALOGUE_MENU);
            log.info("Clicked on catalogue menu");
        }
        catch (Exception e) {
            log.info("Failed to click on catalogue menu");
            throw new RuntimeException("Failed to click on catalogue menu", e);
        }
    }

    @And("click on {string}")
    public void clickOnCategory(String category) {
        try {
            waitAndCheckIsElementClickable(CATEGORY_PRODUCT, category);
            waitAndClickOn(CATEGORY_PRODUCT, category);
            log.info("Clicked on category: " + category);
        }
        catch (Exception e)
        { log.info("Failed to click on category: " + category);
            throw new RuntimeException("Failed to click on category: " + category, e);
        }
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

    @And("select thickness {string}")
    public void selectAttributesForHRSheetOnMsite(String thickness) {
        try {
            sleep(2000);
            // Select thickness for HR Coil
            waitAndCheckIsElementClickable(THICKNESS_DROPDOWN);
            waitAndClickOn(THICKNESS_DROPDOWN);
            log.info("Successfully clicked on thickness dropdown");
            waitAndCheckIsElementClickable(ATTRIBUTE, thickness);
            waitAndClickOn(ATTRIBUTE, thickness);
            log.info("Successfully selected thickness: " +thickness+" mm");
            pdpThickness = thickness;

        } catch (Exception e) {
            log.info("Failed to select thickness for HR Sheet on msite");
            throw new RuntimeException("Failed to Select thickness", e);
        }
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

    @And("click M-site add to cart")
    public void clickMSiteAddToCart() {
        try {
            sleep(1000);
            hideKeyboard();
            waitAndCheckIsElementClickable(ADD_TO_CART_MOBILE);
            waitAndClickOn(ADD_TO_CART_MOBILE);
            log.info("successfully clicked on M-site add to cart button");
        }
        catch (Exception e) {
            log.info("Failed to click on add to cart button on M-site");
            throw new RuntimeException("Failed to click on add to cart button", e);
        }
    }

    @And("enter width {string}")
    public void enterWidthMsite(String width) {
        try {
            // Enter width
            waitAndCheckIsElementClickable(M_WIDTH);
            waitAndSetText(M_WIDTH, width);
            log.info("Successfully Entered width: " +width+" mm on M-site");
            pdpWidth = width;
        } catch (Exception e) {
            throw new RuntimeException("Failed to select width on M-site", e);
        }
    }

       @And("enter length {string}")
        public void enterLengthMsite (String length){
            try {
                // Enter length
                waitAndCheckIsElementClickable(M_LENGTH);
                waitAndSetText(M_LENGTH, length);
                log.info("Successfully Entered length: " + length + " mm on M-site");
                pdpLength = length;
            } catch (Exception e) {
                log.info("Failed to select length on M-site");
                throw new RuntimeException("Failed to select length on M-site", e);
            }
        }

        @And("enter quantity {string}")
    public void enterQuantityMsite(String quantity) {
            try {
                // Enter quantity
                sleep(1000);
                waitAndCheckIsElementClickable(QUANTITY);
                waitAndSetText(QUANTITY, quantity);
                log.info("Successfully Entered quantity: " + quantity + " MT on M-site");
                pdpQuantity = quantity;
            } catch (Exception e) {
                log.info("Failed to enter quantity on M-site");
                throw new RuntimeException("Failed to enter quantity on M-site", e);
            }
        }

        @And("click on {string} product on msite")
        public void clickOnProductOnMsite(String coil) {
            try{
                waitAndCheckIsElementClickable(PLP_PRODUCT, coil);
                waitAndClickOn(PLP_PRODUCT, coil);
                log.info("Clicked on CR coil product on msite");
            }
            catch (Exception e) {
                log.info("Failed to click on CR product on msite");
                throw new RuntimeException("Failed to click on CR product on msite", e);
            }
        }

    }
