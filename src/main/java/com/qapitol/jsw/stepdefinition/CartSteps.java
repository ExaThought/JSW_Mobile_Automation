package com.qapitol.jsw.stepdefinition;


import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import static com.qapitol.jsw.pageFactory.CategoryPages.*;
import static com.qapitol.jsw.utils.CustomUtil.scrollingToViewAndClickUsingJS;
import static com.qapitol.jsw.utils.CustomUtil.waits;
import static com.qapitol.jsw.utils.Hooks.softAssert;

public class CartSteps extends AbstractWebPage {
    private static final String THICKNESS = "thickness";
    private static final String QUANTITY = "quantity";
    private static final String ADD_TO_CART_CTA = "addToCart";
    private static final String VIEW_CART = "viewCart";
    private static final String SUBMIT_REQUIREMENT="submitRequirement";
    private static final String DELETE_LINE_ITEM="deleteLineItem";
    private static final String EDIT_LINE_ITEM="editLineItem";
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
    private static final String DIAMETER="diameter";
    private static final String TMT="tmt";
    private static final String FORM="form";
    private static final String LEG_LENGTH="legLength";
    private static final String FLANGE_WIDTH="flangeWidth";
    private static final String WEB_DEPTH="webDepth";
    private static final String SECTION_WEIGHT="sectionalWeight";
    private static final String EDIT_QUANTITY="editQuantity";
    private static final String SAVE_EDIT="editSave";
    private static final String EDIT_WIDTH_DROPDOWN="editWidthDropdown";
    private static final String EDIT_THICKNESS_DROPDOWN="editThicknessDropdown";
    private static final String EDIT_LENGTH_DROPDOWN="editLengthDropdown";
    private static final String EDIT_WIDTH="editWidth";
    private static final String EDIT_THICKNESS="editThickness";
    private static final String EDIT_LENGTH="editLength";


    Actions actions=new Actions(Grid.driver());
    String requirementId;

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

    @Then("check user able to delete line item from cart")
    public void check_ser_able_to_delete_line_item_from_cart(){
        waitAndCheckIsElementPresent(DELETE_LINE_ITEM);
        waitAndClickOn(DELETE_LINE_ITEM);
        waitAndCheckIsElementPresent(DELETE_CTA_BUTTON);
        waitAndClickOn(DELETE_CTA_BUTTON);
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
        sleep(1000);
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

    @And("open HR Coil plp {string} product")
    public void openHRCoilPdp(String product) {
        WebElement locator=Grid.driver().findElement(By.xpath(plpFirstProduct));
        scrollingToViewAndClickUsingJS(locator);
        waitAndClickOn(PLP_FIRST_PRODUCT,product);
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

    @And("open HR Sheet plp {string} product")
    public void openHRSheetPdp(String product ) {
        WebElement locator=Grid.driver().findElement(By.xpath(plpFirstProduct));
        scrollingToViewAndClickUsingJS(locator);
        waitAndCheckIsElementClickable(PLP_FIRST_PRODUCT);
        waitAndClickOn(PLP_FIRST_PRODUCT,product);
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
        softAssert.assertTrue(waitAndCheckIsElementPresent(SUBMIT_REQUIREMENT));
    }

    @Then("Delete the line item from cart")
    public void deleteTheLineItemFromCart() {
        sleep(2000);
        waitAndClickOn(DELETE_LINE_ITEM);
        if (isElementPresent(DELETE_CTA_BUTTON)) {
            waitAndCheckIsElementClickable(DELETE_CTA_BUTTON);
            waitAndClickOn(DELETE_CTA_BUTTON);
        }
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
        waitAndClickOn(PLP_FIRST_PRODUCT,"1");
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

    @And("click on CR Sheet product")
    public void clickOnCRSheetProduct() {
        //Reading locator from page factory
        actions.moveToElement(Grid.driver().findElement(By.xpath(coldRolledSheetsSubCategory))).click().perform();
    }

    @And("open CR Sheet pdp")
    public void openCRSheetPdp() {
        WebElement locator=Grid.driver().findElement(By.xpath(plpFirstProduct));
        scrollingToViewAndClickUsingJS(locator);
        waitAndClickOn(PLP_FIRST_PRODUCT,"1");
    }

    @And("select all the CR Sheet attribute")
    public void selectAllTheCRSheetAttribute() {
        waitAndCheckIsElementClickable(THICKNESS);
        waitAndClickOn(THICKNESS, "2");

        waitAndCheckIsElementClickable(WIDTH);
        waitAndClickOn(WIDTH, "1");

        WebElement locator = Grid.driver().findElement(By.xpath(length));
        scrollingToViewAndClickUsingJS(locator);
        waitAndCheckIsElementClickable(LENGTH);
        waitAndClickOn(LENGTH, "1");

        waitAndCheckIsElementClickable(QUANTITY);
        waitAndSetText(QUANTITY, "100");
    }

    @When("click on Cement product")
    public void clickOnCementProduct() {
        waitAndCheckIsElementClickable(CEMENT_PLP);
        waitAndClickOn(CEMENT_PLP);
    }
    @And("open Cement pdp")
    public void openCementPdp() {
        waitAndCheckIsElementClickable(CEMENT_PDP);
        waitAndClickOn(CEMENT_PDP,"1");
    }

    @And("select all the Cement attribute")
    public void selectAllTheCementAttribute() {
        waitAndCheckIsElementClickable(CEMENT_QUANTITY);
        waitAndSetText(CEMENT_QUANTITY, "200");
    }

    @And("click to Colour Coated category")
    public void clickToColourCoatedCategory() {
        waitAndCheckIsElementPresent(COLOUR_COATED);
        waitAndClickOn(COLOUR_COATED);
    }

    @And("open Colour Coated pdp")
    public void openColourCoatedPdp() {
        waitAndCheckIsElementClickable(COLOUR_COATED_PDP);
        waitAndClickOn(COLOUR_COATED_PDP,"1");
    }

    @And("select all the Colour Coated Sheet attribute")
    public void selectAllTheColourCoatedSheetAttribute() {
        waitAndCheckIsElementPresent(THICKNESS);
        waitAndClickOn(THICKNESS, "2");

        waitAndCheckIsElementPresent(WIDTH);
        waitAndClickOn(WIDTH, "1");

        WebElement locator=Grid.driver().findElement(By.xpath(coatingGrade));
        scrollingToViewAndClickUsingJS(locator);
        waitAndCheckIsElementPresent(COATING_GRADE);
        waitAndClickOn(COATING_GRADE,"1");

        WebElement dropdown = Grid.driver().findElement(By.xpath("//div[@id='custom-dropdown']"));
        dropdown.click();

        WebElement option = Grid.driver().findElement(By.xpath("//li[contains(text(),'Sky Blue')]"));
        option.click();

        waitAndCheckIsElementClickable(QUANTITY);
        waitAndSetText(QUANTITY, "100");
    }

    @And("click on Wire product")
    public void clickOnWireProduct(){
        //Reading locator from page factory
        actions.moveToElement(Grid.driver().findElement(By.xpath(wireCategory))).click().perform();
    }

    @And("open Wire pdp")
    public void openWirePdp(){
        WebElement locator=Grid.driver().findElement(By.xpath(plpFirstProduct));
        scrollingToViewAndClickUsingJS(locator);
        waitAndCheckIsElementClickable(PLP_FIRST_PRODUCT);
        waitAndClickOn(PLP_FIRST_PRODUCT,"1");
    }

    @And("select all the Wire attribute")
    public void selectAllTheWireAttribute() {
        waitAndCheckIsElementClickable(DIAMETER);
        waitAndClickOn(DIAMETER, "1");
        waitAndCheckIsElementClickable(QUANTITY);
        waitAndSetText(QUANTITY, "50");
    }

    @When("click on TMT product")
    public void clickOnTMTProduct() {
        waitAndCheckIsElementClickable(TMT);
        waitAndClickOn(TMT);
    }

    @And("open TMT Rebar pdp")
    public void openTMTRebarPdp() {
        WebElement locator=Grid.driver().findElement(By.xpath(plpFirstProduct));
        scrollingToViewAndClickUsingJS(locator);
        waitAndCheckIsElementClickable(PLP_FIRST_PRODUCT);
        waitAndClickOn(PLP_FIRST_PRODUCT,"4");
    }

    @And("select all the TMT Rebar attribute")
    public void selectAllTheTMTRebarAttribute() {
        waitAndCheckIsElementClickable(DIAMETER);
        waitAndClickOn(DIAMETER, "1");

        waitAndCheckIsElementClickable(FORM);
        waitAndClickOn(FORM, "1");

        waitAndCheckIsElementClickable(QUANTITY);
        waitAndSetText(QUANTITY, "100");
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
        WebElement locator=Grid.driver().findElement(By.xpath(plpFirstProduct));
        scrollingToViewAndClickUsingJS(locator);
        waitAndCheckIsElementClickable(PLP_FIRST_PRODUCT);
        waitAndClickOn(PLP_FIRST_PRODUCT,"1");
    }

    @And("select all the Angles attribute")
    public void selectAllTheAnglesAttribute() {
     waitAndCheckIsElementClickable(LEG_LENGTH);
        waitAndClickOn(LEG_LENGTH, "1");

        waitAndCheckIsElementClickable(THICKNESS);
        waitAndClickOn(THICKNESS, "1");

        WebElement locator=Grid.driver().findElement(By.xpath(length));
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
        WebElement locator=Grid.driver().findElement(By.xpath(plpFirstProduct));
        scrollingToViewAndClickUsingJS(locator);
        waitAndClickOn(PLP_FIRST_PRODUCT,"1");
    }

    @And("select all the Channels attribute")
    public void selectAllTheChannelsAttribute() {
        waitAndCheckIsElementClickable(FLANGE_WIDTH);
        waitAndClickOn(FLANGE_WIDTH,"1");

        waitAndCheckIsElementClickable(WEB_DEPTH);
        waitAndClickOn(WEB_DEPTH, "1");

        waitAndCheckIsElementClickable(SECTION_WEIGHT);
        waitAndClickOn(SECTION_WEIGHT, "1");

        WebElement locator=Grid.driver().findElement(By.xpath(length));
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
        waitAndClickOn(PLP_FIRST_PRODUCT,"1");
    }

    @And("select all the Beams & Columns attribute")
    public void selectAllTheBeamsColumnsAttribute() {
        waitAndCheckIsElementClickable(FLANGE_WIDTH);
        waitAndClickOn(FLANGE_WIDTH,"1");

        waitAndCheckIsElementClickable(WEB_DEPTH);
        waitAndClickOn(WEB_DEPTH, "1");

        waitAndCheckIsElementClickable(SECTION_WEIGHT);
        waitAndClickOn(SECTION_WEIGHT, "1");

        WebElement locator=Grid.driver().findElement(By.xpath(length));
        scrollingToViewAndClickUsingJS(locator);
        waitAndCheckIsElementClickable(LENGTH);
        waitAndClickOn(LENGTH, "1");

        waitAndCheckIsElementClickable(QUANTITY);
        waitAndSetText(QUANTITY, "50");
    }

    @And("click edit")
    public void clickEdit() {
        waitAndCheckIsElementClickable(EDIT_LINE_ITEM);
        waitAndClickOn(EDIT_LINE_ITEM);
        sleep(1000);
    }

    @And("edit Quantity")
    public void editQuantity() {
        sleep(500);
        clearTextBox(EDIT_QUANTITY);
        waitAndSetText(EDIT_QUANTITY, "75");
    }

    @And("Save edit changes")
    public void saveEditChanges() {
        waitAndCheckIsElementClickable(SAVE_EDIT);
        waitAndClickOn(SAVE_EDIT);
    }

    @And("update Thickness to {string} dropdown")
    public void updateThickness(String updateThickness) {
        waitAndClickOn(EDIT_THICKNESS_DROPDOWN);
        waitAndClickOn(EDIT_THICKNESS, updateThickness);
    }

    @And("update Width to {string} dropdown")
    public void updateWidth(String updateWidth) {
        waitAndClickOn(EDIT_WIDTH_DROPDOWN);
        waitAndClickOn(EDIT_WIDTH, updateWidth);
    }

//    @And("update Length to {string} dropdown")
//    public void updateLength(String updateLength) {
//        waitAndClickOn(EDIT_LENGTH_DROPDOWN);
//        waitAndClickOn(EDIT_LENGTH, updateLength);
//    }
    @And("update Length to {string} dropdown")
    public void updateLength(String updateLength) {
//        WebElement locator=Grid.driver().findElement(By.xpath("//div[@id='custom-dropdown']"));
//        scrollingToViewAndClickUsingJS(locator);
        waitAndClickOn(EDIT_LENGTH_DROPDOWN);
        waitAndClickOn(EDIT_LENGTH, updateLength);
    }

}
