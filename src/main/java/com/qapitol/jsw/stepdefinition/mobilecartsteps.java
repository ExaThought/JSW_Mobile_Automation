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
import java.util.ArrayList;
import java.util.List;

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
    private static final String THICKNESS_DROPDOWN = "thicknessDropdown";
    private static final String WIDTH_DROPDOWN = "widthDropdown";
    private static final String M_WIDTH = "mWidth";
    private static final String PLP_PRODUCT = "plpProduct";
    private static final String CATEGORY_PRODUCT = "categoryProduct";
    private static final String LENGTH_DROPDOWN = "lengthDropdown";
    private static final String DELETEOK = "deleteok";
    private static final String EDIT_BUTTON = "editButton";
    private static final String LOADER_SPINNER = "loaderSpinner";
    private static final String LINE_ITEM_SUBTOTAL = "lineItemSubtotal";
    private static final String CART_SUBTOTAL_CONTAINER = "cartSubtotalContainer";
    private static final String LINE_ITEM_PRICE_PER_UNIT = "lineItemPricePerUnit";
    private static final String EDIT_ACTION_CTA = "editActionCta";
    private static final String EDIT_BUTTON_DYNAMIC = "editButtonDynamic";
    private static final String EDIT_THICKNESS_BTN = "editThicknessBtn";
    private static final String EDIT_WIDTH_BTN = "editWidthBtn";
    private static final String EDIT_LENGTH_BTN = "editLengthBtn";
    private static final String EDIT_QTY_INPUT = "editQtyInput";
    private static final String QUANTITY_INPUT = "quantityInput";
    private static final String SELF_PICKUP = "selfPickup";
    private static final String DELETE_ACTION_CTA = "deleteActionCta";
    private static final String LINE_ITEM = "lineItem";
    private static final String LINE_ITEM_ATTRIBUTE = "lineItemAttribute";
    private static final String SHIPPING_CHARGE = "shippingCharge";
    private static final String TAX_AMOUNT = "taxAmount";
    private static final String DISCOUNT_AMOUNT = "discountAmount";
    private static final String PRICE_PER_UNIT_LOCATOR = "pricePerUnitLocator";
    private static final String LINE_SUBTOTAL_LOCATOR = "lineSubtotalLocator";
    private static final String COATING_GRADE_DROPDOWN = "coatingGradeDropdown";
    private static final String SPANGLE_TYPE_DROPDOWN = "spangleTypeDropdown";
    private static final String DIAMETER_DROPDOWN = "diameterDropdown";
    private static final String FLANGE_WIDTH_DROPDOWN = "flangeWidthDropdown";
    private static final String WEB_DEPTH_DROPDOWN = "webDepthDropdown";
    private static final String SECTIONAL_WEIGHT_DROPDOWN = "sectionalWeightDropdown";
    private static final String CUT_LENGTH_DROPDOWN = "cutLengthDropdown";
    private static final String TMTQUANTATITY = "tmtQuanttatity";
    private static final String UBEND = "ubend";

    private final LocatorReader locatorReader = new LocatorReader("locators/mobilecartsteps.yaml");
    private final JavascriptExecutor js = (JavascriptExecutor) Grid.driver();

    private By byFor(String key) {
        String raw = locatorReader.getXpath("elements." + key + ".en_US.web");
        if (raw == null)
            raw = locatorReader.getXpath(key);
        if (raw != null && raw.startsWith("css="))
            return By.cssSelector(raw.substring(4));
        return By.xpath(raw != null ? raw : "//" + key);
    }

    private By byFor(String key, String param) {
        String raw = locatorReader.getXpath("elements." + key + ".en_US.web");
        if (raw == null)
            raw = locatorReader.getXpath(key);
        if (raw != null)
            raw = raw.replace("{0}", param);
        if (raw != null && raw.startsWith("css="))
            return By.cssSelector(raw.substring(4));
        return By.xpath(raw != null ? raw : "//" + key);
    }

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
    static String pdpFlangeWidth;
    static String pdpWebDepth;
    static String pdpSectionalWeight;
    static String pdpCutLength;

    public static List<String> pdpSelectedAttributes = new ArrayList<>();

    @When("user clicks on catalogue menu")
    public void clickCatalogueMenu() {
        try {
            waitAndClickOn(CATALOGUE_MENU);
            log.info("Clicked on catalogue menu");
        } catch (Exception e) {
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
        } catch (Exception e) {
            log.info("Failed to click on category: " + category);
            throw new RuntimeException("Failed to click on category: " + category, e);
        }
    }

    @When("scroll down to {string} section")
    public void scrollToCategory(String category) {
        try {
            // WebElement categoryElement = Grid.driver().findElement(
            // By.xpath("//p[normalize-space()='" + category + "']")
            // );
            WebElement categoryElement = Grid.driver().findElement(
                    By.xpath("//h3[contains(normalize-space(),'" + category + "')]"));
            ((JavascriptExecutor) Grid.driver()).executeScript(
                    "arguments[0].scrollIntoView(true);", categoryElement);

            log.info("Scrolled to category section: " + category);

        } catch (Exception e) {
            throw new RuntimeException("Failed to scroll to category: " + category, e);
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

    /*
     * @And("select thickness {string}")
     * public void selectAttributesForHRSheetOnMsite(String thickness) {
     * try {
     * sleep(1500);
     * // Thickness options are inline buttons — scroll section into view then click
     * // value
     * waitAndCheckIsElementClickable(THICKNESS_DROPDOWN);
     * waitAndClickOn(THICKNESS_DROPDOWN);
     * sleep(500);
     * waitAndCheckIsElementClickable(ATTRIBUTE, thickness);
     * waitAndClickOn(ATTRIBUTE, thickness);
     * 
     * log.info("Successfully selected thickness: " + thickness + " mm");
     * // pdpThickness = thickness;
     * } catch (Exception e) {
     * log.info("Failed to select thickness");
     * throw new RuntimeException("Failed to select thickness", e);
     * }
     * }
     */

    @And("select thickness {string}")
    public void selectAttributesForHRSheetOnMsite(String thickness) {
        try {
            sleep(1500);
            // thicknessDropdown exists in YAML → will use dropdown path, select first
            // option
            String selected = smartSetAttribute("Thickness (mm)", "thicknessDropdown", null, thickness);
            pdpThickness = selected;
            storeAttribute("Thickness", selected);
        } catch (Exception e) {
            throw new RuntimeException("Failed to select thickness", e);
        }
    }

    @And("click edit on mobile cart")
    public void mclickEdit() {
        try {
            // Wait for any loader to disappear
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(byFor(LOADER_SPINNER)));

            // Find the first Edit CTA and scroll it into view above the sticky footer
            WebElement editBtn = wait.until(
                    ExpectedConditions.presenceOfElementLocated(byFor(EDIT_BUTTON)));
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", editBtn);
            sleep(500);

            // Click using JS to avoid interception by sticky elements
            wait.until(ExpectedConditions.elementToBeClickable(editBtn));
            js.executeScript("arguments[0].click();", editBtn);
            log.info("Clicked Edit button on mobile cart");
        } catch (Exception e) {
            log.info("Failed to click Edit on mobile cart");
            throw new RuntimeException("Failed to click Edit on mobile cart", e);
        }
    }

    /**
     * Core smart attribute setter — detects input type and acts accordingly
     * For dropdowns: selects FIRST option (ignores passed value)
     * For text inputs: types the passed value
     */
    private String smartSetAttribute(String attributeLabel, String dropdownKey, String textInputXpath, String value) {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));

            // -------------------------
            // 1️⃣ Handle text input
            // -------------------------
            if (textInputXpath != null) {
                WebElement input = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(textInputXpath)));
                input.clear();
                input.sendKeys(value);
                System.out.println("Typed " + attributeLabel + " : " + value);
                return value; // return typed value
            }

            // -------------------------
            // 2️⃣ Handle dropdown
            // -------------------------
            String dropdownXpath = locatorReader.getXpath("elements." + dropdownKey + ".en_US.web");
            dropdownXpath = dropdownXpath.replace("xpath=", "");

            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXpath)));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", dropdown);
            dropdown.click();

            WebElement dropdownContainer = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[contains(@class,'bottom') or contains(@class,'sheet')]")));

            List<WebElement> options = dropdownContainer.findElements(By.xpath(".//span[normalize-space()]"));
            if (options.isEmpty())
                throw new RuntimeException("No options found for attribute: " + attributeLabel);

            String selectedValue = null;

            if (value != null && !value.isEmpty()) {
                // Try to select the value from feature file
                for (WebElement option : options) {
                    if (option.getText().trim().equals(value)) {
                        js.executeScript("arguments[0].scrollIntoView({block:'center'});", option);
                        js.executeScript("arguments[0].click();", option);
                        selectedValue = option.getText().trim();
                        break;
                    }
                }
                if (selectedValue == null) {
                    throw new RuntimeException("Dropdown option not found: " + value + " for " + attributeLabel);
                }
            } else {
                // Fallback: pick first option if no value provided
                WebElement firstOption = options.get(0);
                js.executeScript("arguments[0].scrollIntoView({block:'center'});", firstOption);
                js.executeScript("arguments[0].click();", firstOption);
                selectedValue = firstOption.getText().trim();
            }

            System.out.println("Selected " + attributeLabel + " : " + selectedValue);

            // Wait until dropdown closes
            wait.until(ExpectedConditions.invisibilityOf(dropdownContainer));

            return selectedValue;

        } catch (Exception e) {
            throw new RuntimeException("Failed to set attribute: " + attributeLabel, e);
        }
    }

    private void storeAttribute(String label, String value) {
        // Skip storing if value is null or blank — prevents empty entries like
        // "Thickness: "
        if (value == null || value.trim().isEmpty()) {
            System.out.println("Skipping storeAttribute for '" + label + "' — value is empty");
            return;
        }
        pdpSelectedAttributes.removeIf(s -> s.startsWith(label + ":"));
        pdpSelectedAttributes.add(label + ": " + value);
    }

    @And("click M-site add to cart")
    public void clickMSiteAddToCart() {
        try {
            sleep(1000);
            hideKeyboard();
            waitAndCheckIsElementClickable(ADD_TO_CART_MOBILE);
            waitAndClickOn(ADD_TO_CART_MOBILE);
            log.info("successfully clicked on M-site add to cart button");
        } catch (Exception e) {
            log.info("Failed to click on add to cart button on M-site");
            throw new RuntimeException("Failed to click on add to cart button", e);
        }
    }

    @And("enter width {string}")
    public void enterWidthMsite(String width) {
        try {
            sleep(1000);
            // widthDropdown exists in YAML → dropdown
            String selected = smartSetAttribute("Width (mm)", "widthDropdown", null, width);
            pdpWidth = selected;
            storeAttribute("Width", selected);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set width", e);
        }
    }

    @And("select diameter {string}")
    public void enterDiameterMsite(String diameter) {
        try {
            sleep(1000);
            String selected = smartSetAttribute("Diameter (mm)", "diameterDropdown", null, diameter);
            pdpDiameter = selected;
            storeAttribute("Diameter", selected);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set diameter", e);
        }
    }

    @And("select Flange Width {string}")
    public void selectFlangeWidth(String flangeWidth) {
        try {
            sleep(1000);
            String selected = smartSetAttribute("Flange width", "flangeWidthDropdown", null, flangeWidth);
            pdpFlangeWidth = selected;
            storeAttribute("Flange Width", selected);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set flange width", e);
        }
    }

    @And("enter Web Depth {string}")
    public void enterWebDepth(String webDepth) {
        try {
            sleep(1000);
            String selected = smartSetAttribute("Web depth", "webDepthDropdown", null, webDepth);
            pdpWebDepth = selected;
            storeAttribute("Web Depth", selected);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set web depth", e);
        }
    }

    @And("enter sectional weigth {string}")
    public void enterSectionalWeight(String sectionalWeight) {
        try {
            WebElement lengthDropdown = Grid.driver().findElement(
                    By.xpath("//label[contains(text(),'Flange width')]/following-sibling::button"));
            ((JavascriptExecutor) Grid.driver()).executeScript("arguments[0].scrollIntoView(true);", lengthDropdown);
            sleep(2000);
            waitAndCheckIsElementClickable(SECTIONAL_WEIGHT_DROPDOWN);
            String selected = smartSetAttribute("Sectional weight", "sectionalWeightDropdown", null, sectionalWeight);
            pdpSectionalWeight = selected;
            storeAttribute("Sectional Weight", selected);
        } catch (Exception e) {
            throw new RuntimeException("Failed to select sectional weight on M-site", e);
        }
    }

    @And("enter cutlength {string}")
    public void enterCutLength(String cutLength) {
        try {
            sleep(1000);
            String selected = smartSetAttribute("Cut length", "cutLengthDropdown", null, cutLength);
            pdpCutLength = selected;
            storeAttribute("Cut Length", selected);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set cut length", e);
        }
    }

    @And("enter length {string}")
    public void enterLengthMsite(String length) {
        try {
            sleep(1500);
            WebElement lengthDropdown = Grid.driver().findElement(
                    By.xpath("//label[contains(text(),'Thickness (mm)')]//following-sibling::button"));
            ((JavascriptExecutor) Grid.driver()).executeScript("arguments[0].scrollIntoView(true);", lengthDropdown);
            // Length options are inline buttons — scroll section into view then click value
            waitAndCheckIsElementClickable(LENGTH_DROPDOWN);
            String selected = smartSetAttribute("Length (mm)", "lengthDropdown", null, length);
            pdpLength = selected;
            storeAttribute("Length", selected);
        } catch (Exception e) {
            log.info("Failed to select length on M-site");
            throw new RuntimeException("Failed to select length on M-site", e);
        }
    }

    @And("select coating grade {string}")
    public void selectCoatingGrade(String coatingGrade) {
        try {
            sleep(1000);
            String selected = smartSetAttribute("Coating grade", "coatingGradeDropdown", null, coatingGrade);
            storeAttribute("Coating Grade", selected);
        } catch (Exception e) {
            throw new RuntimeException("Failed to select coating grade", e);
        }
    }

    @And("select spangle type {string}")
    public void selectSpangleType(String spangleType) {
        try {
            sleep(1000);
            String selected = smartSetAttribute("Spangle type", "spangleTypeDropdown", null, spangleType);
            storeAttribute("Spangle Type", selected);
        } catch (Exception e) {
            throw new RuntimeException("Failed to select spangle type", e);
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
        try {
            waitAndCheckIsElementClickable(PLP_PRODUCT, coil);
            waitAndClickOn(PLP_PRODUCT, coil);
            log.info("Clicked on CR coil product on msite");
        } catch (Exception e) {
            log.info("Failed to click on CR product on msite");
            throw new RuntimeException("Failed to click on CR product on msite", e);
        }
    }

    @When("user clicks on view cart")
    public void clickViewCart() {
        waitAndClickOn(VIEW_CART_MOBILE);
    }

    @Then("product name should match PDP")
    public void validateProductName() {
        waitAndCheckIsElementClickable(CART_PAGE_PRODUCT_NAME);
        sleep(2000);
        List<WebElement> productNames = Grid.driver().findElements(byFor(CART_PAGE_PRODUCT_NAME));
        String cartName = productNames.get(productNames.size() - 1).getText().trim();
        Assert.assertEquals(cartName, pdpProductName, "Product name mismatch!");
    }

    @Then("capture product name from PDP")
    public void captureProductNameFromPDP() {

        pdpProductName = waitAndGetText(PDP_PRODUCT_NAME).trim();

        System.out.println("Captured PDP Product Name: " + pdpProductName);
    }

    @Then("quantity should match entered quantity")
    public void validateQuantity() {
        List<WebElement> quantities = Grid.driver().findElements(byFor(CART_PAGE_QUANTITY));
        String cartQty = quantities.get(quantities.size() - 1).getText();
        Assert.assertTrue(cartQty.contains(pdpQuantity), "Quantity mismatch!");
    }

    @Then("capture selected attributes from PDP")
    public void captureSelectedAttributesFromPDP() {
        pdpSelectedAttributes.clear();

        try {
            // Thickness
            String thickness = waitAndGetText("mthickness");
            if (thickness != null && !thickness.trim().isEmpty())
                pdpSelectedAttributes.add("Thickness: " + thickness.trim());

            // Width
            String width = waitAndGetText("mWidth");
            if (width != null && !width.trim().isEmpty())
                pdpSelectedAttributes.add("Width: " + width.trim());

            // Length (if exists)
            String length = waitAndGetText("mLength");
            if (length != null && !length.trim().isEmpty())
                pdpSelectedAttributes.add("Length: " + length.trim());

            // Quantity
            String qty = waitAndGetText("quantity");
            if (qty != null && !qty.trim().isEmpty())
                pdpSelectedAttributes.add("Quantity: " + qty.trim());

            System.out.println("PDP Selected Attributes: " + pdpSelectedAttributes);

        } catch (Exception e) {
            throw new RuntimeException("Failed to capture PDP selected attributes", e);
        }
    }

    @Then("empty cart message should be displayed")
    public void validateEmptyCart() {
        waitAndCheckIsElementVisible("emptyCartMessage");
    }

    public double extractPrice(String priceText) {
        return Double.parseDouble(
                priceText.replaceAll("[^0-9.]", ""));
    }
    /*
     * @Then("capture selected attributes from PDP")
     * public void captureSelectedAttributesFromPDP() {
     *
     * pdpSelectedAttributes.clear();
     *
     * List<WebElement> attributes = Grid.driver().findElements(
     * By.xpath("//div[contains(@class,'selected-attribute')]")
     * );
     *
     * for (WebElement attr : attributes) {
     * String value = attr.getText().trim();
     *
     * if (!value.isEmpty()) {
     * pdpSelectedAttributes.add(value);
     * }
     * }
     *
     * System.out.println("PDP Selected Attributes: " + pdpSelectedAttributes);
     * }
     */

    @Then("cart subtotal should equal sum of line subtotals")
    public void cartSubtotalShouldEqualSumOfLineSubtotals() {
        try {
            // 1. Locate all line subtotal elements
            List<WebElement> lineSubtotalElements = Grid.driver().findElements(
                    byFor(LINE_ITEM_SUBTOTAL));

            // 2. Sum up the line subtotals
            double calculatedSubtotal = 0.0;
            for (WebElement elem : lineSubtotalElements) {
                String text = elem.getText().replaceAll("[^0-9.]", "");
                calculatedSubtotal += Double.parseDouble(text);
            }

            System.out.println("Calculated Subtotal from line items: " + calculatedSubtotal);

            // 3. Get displayed cart subtotal
            WebElement cartSubtotalElem = Grid.driver().findElement(
                    byFor(CART_SUBTOTAL_CONTAINER));
            double displayedSubtotal = Double.parseDouble(
                    cartSubtotalElem.getText().replaceAll("[^0-9.]", ""));
            System.out.println("Displayed Cart Subtotal: " + displayedSubtotal);

            // 4. Assert equality
            Assert.assertEquals(displayedSubtotal, calculatedSubtotal, 0.01,
                    "Cart subtotal does not match sum of line subtotals!");

        } catch (Exception e) {
            throw new RuntimeException("Failed to validate cart subtotal", e);
        }
    }

    @Then("price per unit should be displayed")
    public void pricePerUnitShouldBeDisplayed() {

        // 1. Locate all line items' price per unit elements
        List<WebElement> priceElements = Grid.driver().findElements(
                byFor(LINE_ITEM_PRICE_PER_UNIT));

        // 2. Validate each line item's price is visible and not empty
        for (WebElement priceEl : priceElements) {
            String priceText = priceEl.getText().trim();

            Assert.assertTrue(priceEl.isDisplayed(), "Price per unit is not visible");
            Assert.assertFalse(priceText.isEmpty(), "Price per unit is empty");
            System.out.println("Price per unit: " + priceText);
        }
    }

    @And("user edits all attributes in cart")
    public void editAllCartAttributes() {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));

            // Get a fresh count of Edit CTAs
            List<WebElement> editCtaList = Grid.driver().findElements(byFor(EDIT_ACTION_CTA));
            int totalItems = editCtaList.size();
            log.info("Total cart items to edit: " + totalItems);

            for (int i = 0; i < totalItems; i++) {

                // Re-fetch Edit buttons fresh each iteration (DOM may have changed after modal
                // closes)
                List<WebElement> editBtns = Grid.driver().findElements(byFor(EDIT_ACTION_CTA));
                WebElement editBtn = editBtns.get(i);

                // Scroll the Edit button to center — avoids sticky header/footer interception
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", editBtn);
                sleep(600);

                // Wait until clickable then JS-click (avoids ElementClickInterceptedException)
                wait.until(ExpectedConditions.elementToBeClickable(editBtn));
                js.executeScript("arguments[0].click();", editBtn);
                log.info("Opened Edit modal for cart item #" + (i + 1));

                // Wait for the edit modal to appear (Done button is a reliable anchor)
                wait.until(ExpectedConditions.visibilityOfElementLocated(byFor(SAVE_EDIT)));
                sleep(400);

                // Edit Quantity (available for all product types)
                try {
                    WebElement qtyInput = wait.until(
                            ExpectedConditions
                                    .visibilityOfElementLocated(byFor(EDIT_QTY_INPUT, String.valueOf(i + 1))));
                    qtyInput.clear();
                    qtyInput.sendKeys(pdpQuantity);
                    log.info("Updated quantity to: " + pdpQuantity + " for item #" + (i + 1));
                } catch (Exception qe) {
                    log.info("Quantity field not found for item #" + (i + 1) + ", skipping.");
                }

                // Click Done to save
                waitAndClickOn(SAVE_EDIT);
                sleep(800); // wait for modal to close and DOM to settle
                log.info("Saved edits for cart item #" + (i + 1));
            }

            log.info("All cart items edited successfully.");
        } catch (Exception e) {
            log.info("Failed to edit cart attributes");
            throw new RuntimeException("Failed to edit cart attributes", e);
        }
    }

    @Then("line subtotal should update correctly")
    public void lineSubtotalShouldUpdateCorrectly() {
        // Get price per unit for the item
        double pricePerUnit = extractPrice(waitAndGetText(PRICE_PER_UNIT_LOCATOR));

        // Get updated quantity from the input
        WebElement qtyInput = Grid.driver().findElement(byFor(QUANTITY_INPUT));
        int qty = Integer.parseInt(qtyInput.getAttribute("value"));

        // Calculate expected subtotal
        double expectedSubtotal = pricePerUnit * qty;

        // Get actual line subtotal
        double actualSubtotal = extractPrice(waitAndGetText(LINE_SUBTOTAL_LOCATOR));

        Assert.assertEquals(actualSubtotal, expectedSubtotal, 0.01, "Line subtotal did not update correctly");
    }

    @When("user deletes all cart items")
    public void userDeletesAllCartItems() {
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));

        // Wait for and scroll to self-pickup anchor
        WebElement selfPickup = wait.until(
                ExpectedConditions.visibilityOfElementLocated(byFor(SELF_PICKUP)));
        ((JavascriptExecutor) Grid.driver())
                .executeScript("arguments[0].scrollIntoView(true);", selfPickup);

        while (true) {

            List<WebElement> deleteButtons = Grid.driver()
                    .findElements(byFor(DELETE_ACTION_CTA));

            if (deleteButtons.isEmpty())
                break;

            WebElement firstDelete = deleteButtons.get(0);

            wait.until(ExpectedConditions.elementToBeClickable(firstDelete)).click();

            waitAndClickOn(DELETEOK);

            // Wait until that delete button disappears (important)
            wait.until(ExpectedConditions.stalenessOf(firstDelete));
        }
    }

    @Then("no line items should be visible")
    public void noLineItemsShouldBeVisible() {
        List<WebElement> lineItems = Grid.driver().findElements(byFor(LINE_ITEM));

        for (WebElement item : lineItems) {
            Assert.assertFalse(item.isDisplayed(), "Found visible line item when none expected");
        }

        Assert.assertTrue(lineItems.isEmpty(), "Expected no line items but found some");
    }

    @Then("selected attributes should match PDP")
    public void selectedAttributesShouldMatchPDP() {

        // IMPORTANT: byFor(LINE_ITEM) uses contains(@class,'line-item') which also
        // matches
        // line-item-attribute, line-item-subtotal etc. Use word-boundary match instead.
        List<WebElement> lineItems = Grid.driver().findElements(
                By.xpath("//div[contains(concat(' ', normalize-space(@class), ' '), ' line-item ')]"));
        if (lineItems.isEmpty()) {
            throw new RuntimeException("No line items found in cart");
        }
        WebElement lastLineItem = lineItems.get(lineItems.size() - 1);

        // Scope the attribute search to the last line item only
        List<WebElement> attributeElements = lastLineItem.findElements(
                By.xpath(".//div[contains(@class,'line-item-attribute')]"));

        System.out.println("Last line item tag/class: " + lastLineItem.getAttribute("class"));
        System.out.println("Attribute elements found in last line item: " + attributeElements.size());

        List<String> cartAttributes = new ArrayList<>();

        for (WebElement el : attributeElements) {
            String text = el.getText()
                    .toLowerCase()
                    .replace("(mm)", "")
                    .replaceAll("\\s+", "")
                    .trim();

            cartAttributes.add(text);
        }

        System.out.println("Cart Attributes: " + cartAttributes);
        System.out.println("PDP Attributes: " + pdpSelectedAttributes);

        for (String expected : pdpSelectedAttributes) {

            // New (fixed): extract only the value after the colon
            String valueOnly = expected.contains(":")
                    ? expected.substring(expected.indexOf(":") + 1)
                    : expected;

            String normalizedExpected = valueOnly // e.g. " 1.6"
                    .toLowerCase()
                    .replace("(mm)", "")
                    .replaceAll("\\s+", "") // → "1.6"
                    .trim();

            // Skip comparison if the expected value is empty (no attribute to verify)
            if (normalizedExpected.isEmpty()) {
                System.out.println("Skipping empty attribute check for: " + expected);
                continue;
            }

            boolean matchFound = false;

            for (String cartAttr : cartAttributes) {
                if (cartAttr.contains(normalizedExpected)) {
                    matchFound = true;
                    break;
                }
            }

            Assert.assertTrue(matchFound,
                    "Attribute value missing in cart: " + expected + " (looking for: '" + normalizedExpected + "')");
        }
    }

    @And("click on ubend")
    public void clickOnUbend() {
        try {
            waitAndCheckIsElementClickable(UBEND);
            waitAndClickOn(UBEND);
            log.info("Successfully clicked on U-Bend option");
        } catch (Exception e) {
            log.info("Failed to click on U-Bend option");
            throw new RuntimeException("Failed to click on U-Bend option", e);
        }
    }

    @And("enter TMT quantity {string}")
    public void enterTmtQuantity(String quantity) {
        try {
            sleep(1000);
            waitAndCheckIsElementClickable(TMTQUANTATITY);
            waitAndSetText(TMTQUANTATITY, quantity);
            log.info("Successfully entered TMT quantity: " + quantity);
            pdpQuantity = quantity;
        } catch (Exception e) {
            log.info("Failed to enter TMT quantity");
            throw new RuntimeException("Failed to enter TMT quantity", e);
        }
    }
}
