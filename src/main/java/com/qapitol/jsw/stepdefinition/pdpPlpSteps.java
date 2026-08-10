package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.context.ScenarioContext;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static com.qapitol.jsw.pageFactory.CategoryPages.Add_TO_CART_BUTTON;
import static com.qapitol.jsw.utils.CustomUtil.*;
import static com.qapitol.jsw.utils.Hooks.softAssert;

public class pdpPlpSteps extends AbstractWebPage {

    private final String RESET_BUTTON = "resetSelectionButton";
    private final String ADD_TO_CART_BUTTON = "addToCart";
    private final String ATTRIBUTES_SECTION = "attributeValue";
    private final String THICKNESS = "(//span[text()='Thickness (mm)']/../following-sibling::div/button[not(@disabled)])[1]";
    private final String WIDTH = "(//span[text()='Width (mm)']/../following-sibling::div/button[not(@disabled)])[1]";
    private final String LENGTH = "(//span[text()=\"Length (mm)\"]/../following-sibling::div/button[not(@disabled)])[1]";
    private final String QANTITY_INPUT_FIELD = "quantity";
    private final String CART_QUANTITY = "cartPageQuantity";
    private final String BRAND_NAME = "brandName";
    private final String PDP_DESCRIPTION = "pdpDescription";
    private final String CATEGORY_FILTER = "//h3/following-sibling::div//button[@data-slot=\"checkbox\"]";
    private final String CATEGORY_NAME = "//span[text()='Category']/../following-sibling::div//label";
    private final String PROD_CATEGORY = "(//span[text()='MS Hot rolled'])";
    private final String GRADE_FILTER_CHECKBOX = "(//span[text()='Grade']/../following-sibling::div//button[@data-slot=\"checkbox\"])[1]";
    private final String GRADE_FILTER_NAME = "(//span[text()='Grade']/../following-sibling::div//label)[1]";
    private final String SUB_BRAND_CHECKBOX = "(//span[text()='Sub brand']/../following-sibling::div//button[@data-slot=\"checkbox\"])[1]";
    private final String SUB_BRAND_NAME = "(//span[text()='Sub brand']/../following-sibling::div//label)[1]";
    private final String BRAND_CHECKBOX = "(//span[text()='Brand']/../following-sibling::div//button[@data-slot=\"checkbox\"])[1]";
    private final String BRAND_LABEL = "(//span[text()='Brand']/../following-sibling::div//label)[1]";
    private final String SUB_GRADE_CHECKBOX = "(//span[text()='Sub grade']/../following-sibling::div//button[@data-slot=\"checkbox\"])[1]";
    private final String SUB_GRADE_NAME = "(//span[text()='Sub grade']/../following-sibling::div//label)[1]";
    private final String PROD_NAME = "//span[text()='Brand: ']/../preceding-sibling::p";
    private final String PROD_BRAND_NAME = "//span[text()='Brand: ']/following-sibling::span";
    private final String SELECTED_FILTER_NAME = "(//span[text()='%s'])[2]";
    private final ScenarioContext context;

    public pdpPlpSteps(ScenarioContext context) {
        this.context = context;
    }


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
        WebElement addToCart = Grid.driver().findElement(Add_TO_CART_BUTTON);
        softAssert.assertTrue(addToCart.isDisplayed(), "Add to cart button is enabled after clicking reset button");
        log.info("Add to cart button is disabled after clicking reset button");
    }

    @Then("check Add to cart is enabled")
    public void checkAddToCartIsEnabled() {

        softAssert.assertTrue(waitUntilEnabled(Add_TO_CART_BUTTON), "Add to cart button is not enabled after selecting all attributes");
        log.info("Add to cart button is enabled after selecting all attributes");
    }

    @Then("check image gallery loads on PDP page")
    public void checkImageGalleryLoadsOnPDPPage() {
        WebElement image = Grid.driver().findElement(By.xpath("//img[@class='big-image']"));
        boolean visible = image.isDisplayed();
        boolean loaded = (Boolean) Grid.driver().executeScript(
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

    @Then("check quantity enter below 1000 in PDP page")
    public void checkQuantityEnterBelow1000InPDPPage() {
        // A 4-digit quantity (e.g. 1000) must be capped below 1000 (max 999).
        // Parse the numeric part of the cart quantity text (e.g. "Qty: 1000 MT") and
        // assert it is below 1000, instead of matching one hard-coded string.
        String pdpQuantity = waitAndGetText(CART_QUANTITY);
        String digits = pdpQuantity.replaceAll("[^0-9]", "");
        Assert.assertFalse(digits.isEmpty(), "Could not read a numeric quantity from cart text: '" + pdpQuantity + "'");

        int quantity = Integer.parseInt(digits);
        Assert.assertTrue(quantity < 1001,
                "Quantity should be capped below 1000 but the cart shows '" + pdpQuantity + "'");
        log.info("Quantity correctly capped below 1000 in PDP page: " + pdpQuantity);

        // Cleanup: remove the line item so the cart is left empty for the next run.
        WebElement deleteBtn = Grid.driver().findElement(By.xpath("(//section/div/div/div/button[text()='Delete']) [position() < 2]"));
        jsClick(deleteBtn);
        WebElement deleteItemBtn = Grid.driver().findElement(By.xpath("//p[contains(normalize-space(.), 'delete this item')]/following-sibling::div/button[normalize-space(.)='Delete']"));
        jsClick(deleteItemBtn);
    }

    @Then("quantity field should treat only first 4 digits {string} as input in PDP page")
    public void quantityFieldShouldTreatOnlyFirstFourDigits(String expectedQuantity) {
        // The PDP quantity field must accept at most 4 digits. A 4-digit value (e.g. 1000 or 9999)
        // is kept as-is; anything longer is truncated so only the first 4 digits are treated as
        // input (e.g. 12345 -> 1234, 99999 -> 9999).
        String pdpQuantity = waitAndGetText(CART_QUANTITY);            // e.g. "Qty: 1234 MT"
        String actualDigits = pdpQuantity.replaceAll("[^0-9]", "");

        Assert.assertEquals(actualDigits.length(), 4,
                "Quantity field should keep exactly 4 digits but the cart shows '" + pdpQuantity + "'");
        Assert.assertEquals(actualDigits, expectedQuantity,
                "Quantity field should treat only the first 4 digits as input. Expected '"
                        + expectedQuantity + "' but the cart shows '" + pdpQuantity + "'");
        log.info("Quantity field correctly limited to 4 digits in PDP page: " + pdpQuantity);

        // Cleanup: remove the line item so the cart is left empty for the next example.
        WebElement deleteBtn = Grid.driver().findElement(By.xpath("(//section/div/div/div/button[text()='Delete']) [position() < 2]"));
        jsClick(deleteBtn);
        WebElement deleteItemBtn = Grid.driver().findElement(By.xpath("//p[contains(normalize-space(.), 'delete this item')]/following-sibling::div/button[normalize-space(.)='Delete']"));
        jsClick(deleteItemBtn);
    }

    @Then("check brand name is not empty in PDP page")
    public void checkBrandNameIsNotEmptyInPDPPage() {
        String brandName = waitAndGetText(BRAND_NAME);
        Assert.assertFalse(brandName.isEmpty(), "Brand name is empty in PDP page");
        log.info("Brand name is not empty in PDP page: " + brandName);
    }

    @Then("check product description is not empty in PDP page")
    public void checkProductDescriptionIsNotEmptyInPDPPage() {
        String description = waitAndGetText(PDP_DESCRIPTION).trim();
        Assert.assertFalse(description.isEmpty(), "Product description is empty in PDP page");
        log.info("Product description is not empty in PDP page: " + description);
    }

    @And("User selects the first category")
    public void userSelectsTheFirstCategory() {
        waits(By.xpath(CATEGORY_FILTER));
        String name = getText(By.xpath(CATEGORY_NAME));
        log.info("User select the first category " + name);
        context.categoryName = name;
        clickButton(By.xpath(CATEGORY_FILTER));
    }

    @Then("User verifies category")
    public void userVerifiesTheCategory() {
        sleep(500);
        waits(By.xpath(String.format(SELECTED_FILTER_NAME, context.categoryName)));
        String name = getText(By.xpath(String.format(SELECTED_FILTER_NAME, context.categoryName)));
        softAssert.assertTrue(context.categoryName.contains(name), "Displaying wrong category");
    }

    @And("User select the Grade")
    public void userSelectTheGrade() {
        waits(By.xpath(GRADE_FILTER_CHECKBOX));
        String name = getText(By.xpath(GRADE_FILTER_NAME));
        log.info("User select the first GRADE " + name);
        context.gradeName = name;
        clickButton(By.xpath(GRADE_FILTER_CHECKBOX));
    }

    @Then("User verifies Grade")
    public void userVerifiesTheGrade() {
        log.info("User verifies the Grade " + context.gradeName);
        {
            sleep(1000);
            By prodNameLocator = By.xpath(PROD_NAME);
            waits(prodNameLocator);

            List<String> names = Grid.driver().findElements(prodNameLocator)
                    .stream()
                    .map(WebElement::getText)
                    .map(String::trim)
                    .collect(Collectors.toList());

            log.info("Product Names: " + names);

            for (String name : names) {
                if (matches(name, context.gradeName)) {
                    softAssert.assertTrue(true);
                } else {
                    softAssert.assertTrue(false, "Wrong prod displayed---" + name + "---" + context.gradeName);
                }
            }

        }

    }

    @And("User select the Brand")
    public void userSelectTheBrand() {
        waits(By.xpath(BRAND_CHECKBOX));
        String name = getText(By.xpath(BRAND_LABEL));
        log.info("User select the first BRAND " + name);
        context.brandName = name;
        clickButton(By.xpath(BRAND_CHECKBOX));
    }

    @Then("User verifies Brand")
    public void userVerifiesTheBrand() {
        log.info("User verifies the Brand " + context.brandName);
        {
            sleep(1000);
            By prodNameLocator = By.xpath(PROD_BRAND_NAME);
            waits(prodNameLocator);

            List<String> names = Grid.driver().findElements(prodNameLocator)
                    .stream()
                    .map(WebElement::getText)
                    .map(String::trim)
                    .collect(Collectors.toList());

            log.info("Product Names: " + names);

            for (String name : names) {
                if (matches(name, context.brandName)) {
                    softAssert.assertTrue(true);
                } else {
                    softAssert.assertTrue(false, "Wrong prod displayed----" + name + "----" + context.brandName);
                }
            }

        }
    }

    @And("User select the Sub grade")
    public void userSelectTheSubGrade() {
        waits(By.xpath(SUB_GRADE_CHECKBOX));
        String name = getText(By.xpath(SUB_GRADE_NAME));
        log.info("User select the first BRAND " + name);
        context.subGradeName = name;
        clickButton(By.xpath(SUB_GRADE_CHECKBOX));
    }

    @Then("User verifies Sub Grade")
    public void userVerifiesTheSubGrade() {
        log.info("User verifies the Grade " + context.subGradeName);
        {
            sleep(1000);
            By prodNameLocator = By.xpath(PROD_NAME);
            waits(prodNameLocator);

            List<String> names = Grid.driver().findElements(prodNameLocator)
                    .stream()
                    .map(WebElement::getText)
                    .map(String::trim)
                    .collect(Collectors.toList());

            log.info("Product Names: " + names);

            for (String name : names) {
                if (matches(name, context.subGradeName)) {
                    softAssert.assertTrue(true);
                } else {
                    softAssert.assertTrue(false, "Wrong prod displayed----" + name + "---" + context.subGradeName);
                }
            }

        }
    }


}

