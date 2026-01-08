package com.qapitol.jsw.stepdefinition;


import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.qapitol.jsw.pageFactory.CategoryPages.*;
import static com.qapitol.jsw.utils.CustomUtil.scrollingToViewAndClickUsingJS;
import static com.qapitol.jsw.utils.Hooks.softAssert;

public class CartSteps extends AbstractWebPage {
    private static final String HR_COIL_PLP="hr";
    private static final String HR_COIL = "hrCoilE250A";
    private static final String THICKNESS = "thickness";
    private static final String QUANTITY = "quantity";
    private static final String ADD_TO_CART_CTA = "addToCart";
    private static final String VIEW_CART = "viewCart";
    private static final String SUBMIT_REQUIREMENT="submitRequirement";
    private static final String DELETE_LINE_ITEM="deleteLineItem";
    private static final String DELETE_CTA_BUTTON="deleteCta";
    private static final String CEMENT_PLP="cement";
    private static final String CEMENT_PDP="cementPDP";
    private static final String CEMENT_QUANTITY="cementQuantity";
    private static final String STAINLESS_STEEL_PLP="stainlessSteelPlp";
    private static final String STAINLESS_STEEL_PDP ="stainlessSteelPDP";
    private static final String STAINLESS_STEEL_THICKNESS="stainlessSteelThickness";
    private static final String STAINLESS_STEEL_WIDTH="stainlessSteelWidth";
    private static final String STAINLESS_STEEL_LENGTH="stainlessSteelLength";
    private static final String RCV2_CHECKOUT="rcCheckout";
    private static final String MY_REQUIREMENT_PAGE="myRequirementPage";
    private static final String REQUIREMENT_ID="requirementId";
    private static final String MILD_STEEL="mildSteel";
    private static final String COLOUR_COATED="colourCoated";
    private static final String COLOUR_COATED_PDP="colourCoatedPdp";
    private static final String WIDTH="width(mm)";
    private static final String COATING_GRADE="coatingGrade";
    private static final String SUCCESS_PAGE_RCV2="rcCheckoutSuccessPage";
    private static final String CART_PAGE="cartPage";
    private static final String PLP_FIRST_PRODUCT="plpFirstProduct";
    private static final String LENGTH="length";


    Actions actions=new Actions(Grid.driver());
    String requirementId;

    @When("user open HR coil pdp")
    public void user_open_HR_coil_pdp() {
        sleep(3000);
        Actions actions = new Actions(Grid.driver());
        WebElement mildSteel = Grid.driver().findElement(By.xpath("//a[contains(., 'Mild Steel')]"));
        actions.moveToElement(mildSteel).perform();
        sleep(1000);
        WebElement hotRolled = Grid.driver().findElement(By.xpath("(//a[contains(text(),'Hot rolled')])[4]"));
        actions.moveToElement(hotRolled).perform();
        sleep(1000);
        WebElement hotRolledCoil = Grid.driver().findElement(By.xpath("(//a[contains(text(),'Hot rolled coils')])[1]"));
        actions.moveToElement(hotRolledCoil).perform();
        hotRolledCoil.click();
        sleep(1000);
        waitAndCheckIsElementPresent(HR_COIL);
        waitAndCheckIsElementClickable(HR_COIL);

        // Scroll into view to avoid sticky navbar overlay
        WebElement hrCoilElement = Grid.driver().findElement(By.xpath("//a[@title='TATA Hot Rolled Coil 2062:2011 E350A']"));
        ((JavascriptExecutor) Grid.driver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", hrCoilElement);


        // Then click
        waitAndClickOn(HR_COIL);
    }


    @And("select all the attribute and add to cart")
    public void select_all_the_attribute_and_add_to_cart() {
        waitAndCheckIsElementPresent(THICKNESS);
        waitAndClickOn(THICKNESS);

        waitAndCheckIsElementPresent(WIDTH);

        // Find the width button element explicitly
        WebElement widthButton = Grid.driver().findElement(By.xpath("//button[contains(text(),'1250')]"));

        // Scroll into view and scroll up a bit to avoid sticky footer overlap
        ((JavascriptExecutor) Grid.driver()).executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -150);", widthButton);

        // Wait until clickable (explicit wait)
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(30));
        widthButton = wait.until(ExpectedConditions.elementToBeClickable(widthButton));

        // Try clicking the width button safely
        try {
            widthButton.click();
        } catch (ElementClickInterceptedException e) {
            // If intercepted, fallback to JS click
            ((JavascriptExecutor) Grid.driver()).executeScript("arguments[0].click();", widthButton);
        }

        sleep(3000);

        waitAndCheckIsElementPresent(QUANTITY);
        waitAndSetText(QUANTITY, "10");
    }

    @And("user will add cement product to cart")
    public void user_will_add_cement_product_to_cart(){
        sleep(2000);
       waitAndCheckIsElementPresent(CEMENT_PLP);
       waitAndClickOn(CEMENT_PLP);
       waitAndCheckIsElementPresent(CEMENT_PDP);
       waitAndClickOn(CEMENT_PDP);
       sleep(2000);
       waitAndCheckIsElementPresent(CEMENT_QUANTITY);
       waitAndSetText(CEMENT_QUANTITY,"120");
       sleep(2000);
       waitAndCheckIsElementPresent(ADD_TO_CART_CTA);
       waitAndClickOn(ADD_TO_CART_CTA);
        sleep(2000);
        waitAndCheckIsElementPresent(VIEW_CART);
       waitAndClickOn(VIEW_CART);
       softAssert.assertTrue(waitAndCheckIsElementPresent(SUBMIT_REQUIREMENT));

    }
    @And("user will add Stainless Steel product to cart")
    public void user_will_add_stainless_steel_product_to_cart(){
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

    @Then("check user able to add the product to cart page")
    public void check_user_able_to_add_the_product_to_cart_page() {
        sleep(2000);
        waitAndCheckIsElementPresent(ADD_TO_CART_CTA);
        waitAndClickOn(ADD_TO_CART_CTA);
        sleep(3000);
        waitAndCheckIsElementPresent(VIEW_CART);
        waitAndClickOn(VIEW_CART);
        softAssert.assertTrue(waitAndCheckIsElementPresent(SUBMIT_REQUIREMENT));
    }
    @Then("check user able to delete line item from cart")
    public void check_ser_able_to_delete_line_item_from_cart(){
        sleep(2000);
        waitAndCheckIsElementPresent(DELETE_LINE_ITEM);
        waitAndClickOn(DELETE_LINE_ITEM);
        sleep(2000);
        waitAndCheckIsElementPresent(DELETE_CTA_BUTTON);
        waitAndClickOn(DELETE_CTA_BUTTON);
    }
    @And("check user able to add the product to cart without pricing")
    public void check_user_able_to_add_the_product_to_cart_without_pricing(){
        sleep(2000);
        waitAndCheckIsElementPresent(MILD_STEEL);
        Actions actions=new Actions(Grid.driver());
        WebElement mildSteel=Grid.driver().findElement(By.xpath("//a[contains(text(),'Mild Steel')]"));
        actions.moveToElement(mildSteel).perform();
        sleep(1000);
        waitAndCheckIsElementPresent(COLOUR_COATED);
        waitAndClickOn(COLOUR_COATED);
        sleep(1000);
        waitAndCheckIsElementPresent(COLOUR_COATED_PDP);
        waitAndClickOn(COLOUR_COATED_PDP);
        waitAndCheckIsElementPresent(THICKNESS);
        waitAndClickOn(THICKNESS);
        waitAndCheckIsElementPresent(WIDTH);
        waitAndClickOn(WIDTH);
        sleep(2000);
        waitAndCheckIsElementPresent(COATING_GRADE);
        waitAndClickOn(COATING_GRADE);
        sleep(1000);
        WebElement dropdown = Grid.driver().findElement(By.xpath("//div[@id='custom-dropdown']"));
        dropdown.click();
        sleep(1000);
        WebElement option = Grid.driver().findElement(By.xpath("//li[contains(text(),'Sky Blue')]"));
        option.click();
        sleep(1000);
        waitAndCheckIsElementPresent(QUANTITY);
        waitAndSetText(QUANTITY, "10");
        sleep(1000);
        waitAndCheckIsElementPresent(ADD_TO_CART_CTA);
        waitAndClickOn(ADD_TO_CART_CTA);
        sleep(5000);
        waitAndCheckIsElementPresent(VIEW_CART);
        waitAndClickOn(VIEW_CART);
        softAssert.assertTrue(waitAndCheckIsElementPresent(SUBMIT_REQUIREMENT));
    }
    @And("user checkout the RCV2")
    public void user_checkout_the_rcv2(){
        sleep(2000);
        waitAndCheckIsElementPresent(MY_REQUIREMENT_PAGE);
        waitAndClickOn(MY_REQUIREMENT_PAGE);
        waitAndCheckIsElementPresent(REQUIREMENT_ID);
        requirementId=waitAndGetText(REQUIREMENT_ID);
        System.out.println(requirementId);
        waitAndCheckIsElementPresent(CART_PAGE);
        waitAndClickOn(CART_PAGE);
        sleep(5000);
        waitAndCheckIsElementPresent(RCV2_CHECKOUT);
        waitAndClickOn(RCV2_CHECKOUT);
        sleep(2000);
        waitAndCheckIsElementPresent(SUCCESS_PAGE_RCV2);
        waitAndCheckIsElementPresent(MY_REQUIREMENT_PAGE);
        waitAndClickOn(MY_REQUIREMENT_PAGE);
        waitAndCheckIsElementPresent(REQUIREMENT_ID);
        requirementId=waitAndGetText(REQUIREMENT_ID);
        System.out.println(requirementId);

    }

    @When("move the mouse to Mild steel category")
    public void moveTheMouseToMildSteelCategory() {
        //Reading locator from page factory
        actions.moveToElement(Grid.driver().findElement(By.xpath(mildSteelCategory))).perform();
    }

    @And("move to Hot rolled category")
    public void moveToHotRolledCategory() {
        //Reading locator from page factory
        actions.moveToElement(Grid.driver().findElement(By.xpath(hotRolledCategory))).perform();
    }

    @And("click on HR Sheet product")
    public void clickOnHRSheetProduct() {
        //Reading locator from page factory
        actions.moveToElement(Grid.driver().findElement(By.xpath(hotRolledSheetsSubCategory))).click().perform();
    }

    @And("click on HR Coil product")
    public void clickOnHRCoilProduct() {
        //Reading locator from page factory
        actions.moveToElement(Grid.driver().findElement(By.xpath(hotRolledCoilsSubCategory))).click().perform();
    }

    @And("open HR Coil pdp")
    public void openHRCoilPdp() {
        WebElement locator=Grid.driver().findElement(By.xpath(plpFirstProduct));
        scrollingToViewAndClickUsingJS(locator);
        waitAndCheckIsElementClickable(PLP_FIRST_PRODUCT);
        waitAndClickOn(PLP_FIRST_PRODUCT);
    }

    @And("select all the HR Coil attribute")
    public void selectAllTheHrCoilAttribute() {
        waitAndCheckIsElementClickable(THICKNESS);
        waitAndClickOn(THICKNESS,"2");

        waitAndCheckIsElementClickable(WIDTH);
        waitAndClickOn(WIDTH,"2");

        waitAndCheckIsElementClickable(QUANTITY);
        waitAndSetText(QUANTITY, "50");

    }

    @And("open HR Sheet pdp")
    public void openHRSheetPdp() {
        WebElement locator=Grid.driver().findElement(By.xpath(plpFirstProduct));
        scrollingToViewAndClickUsingJS(locator);
        waitAndCheckIsElementClickable(PLP_FIRST_PRODUCT);
        waitAndClickOn(PLP_FIRST_PRODUCT);
    }

    @And("select all the HR Sheet attribute")
    public void selectAllTheHrAttribute() {
        waitAndCheckIsElementClickable(THICKNESS);
        waitAndClickOn(THICKNESS,"2");

        waitAndCheckIsElementClickable(WIDTH);
        waitAndClickOn(WIDTH,"1");

        WebElement locator=Grid.driver().findElement(By.xpath(length));
        scrollingToViewAndClickUsingJS(locator);
        waitAndCheckIsElementClickable(LENGTH);
        waitAndClickOn(LENGTH,"1");

        waitAndCheckIsElementClickable(QUANTITY);
        waitAndSetText(QUANTITY, "100");

    }

    @And("add to cart")
    public void addToCart() {
        sleep(1000);
        waitAndCheckIsElementClickable(ADD_TO_CART_CTA);
        waitAndClickOn(ADD_TO_CART_CTA);
    }

    @And("click view cart")
    public void clickViewCart() {
        waitAndCheckIsElementClickable(VIEW_CART);
        waitAndClickOn(VIEW_CART);
    }

    @Then("Delete the line item from cart")
    public void deleteTheLineItemFromCart() {
        waitAndCheckIsElementClickable(DELETE_LINE_ITEM);
        waitAndClickOn(DELETE_LINE_ITEM);
        waitAndCheckIsElementClickable(DELETE_CTA_BUTTON);
        waitAndClickOn(DELETE_CTA_BUTTON);
    }

    @And("move to Cold rolled category")
    public void moveToColdRolledCategory() {
        //Reading locator from page factory
        actions.moveToElement(Grid.driver().findElement(By.xpath(coldRolledCategory))).perform();
    }

    @And("click on CR Coil product")
    public void clickOnCRCoilProduct() {
        //Reading locator from page factory
        actions.moveToElement(Grid.driver().findElement(By.xpath(coldRolledCoilsSubCategory))).click().perform();
    }

    @And("open CR Coil pdp")
    public void openCRCoilPdp() {
        WebElement locator=Grid.driver().findElement(By.xpath(plpFirstProduct));
        scrollingToViewAndClickUsingJS(locator);
        waitAndCheckIsElementClickable(PLP_FIRST_PRODUCT);
        waitAndClickOn(PLP_FIRST_PRODUCT);
    }

    @And("select all the CR Coil attribute")
    public void selectAllTheCRCoilAttribute() {
        waitAndCheckIsElementClickable(THICKNESS);
        waitAndClickOn(THICKNESS, "2");

        waitAndCheckIsElementClickable(WIDTH);
        waitAndClickOn(WIDTH, "2");

        waitAndCheckIsElementClickable(QUANTITY);
        waitAndSetText(QUANTITY, "50");
    }




}
