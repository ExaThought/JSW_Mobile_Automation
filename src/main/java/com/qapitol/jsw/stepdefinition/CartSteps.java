package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.AllureCustomLogReporter;
import com.qapitol.jsw.utils.ConfigUtil;

import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;


import static com.qapitol.jsw.stepdefinition.BaseSteps.*;
import static com.qapitol.jsw.utils.CustomUtills.softAssert;

public class CartSteps extends AbstractWebPage {

    private static final String LOGIN_CTA_BUTTON = "loginButton";
    private static final String LOGIN_VIA_OTP = "loginViaOtp";
    private static final String LOGIN_EMAIL = "ccpLoginEmail";
    private static final String LOGIN_PASSWORD = "ccpLoginPassword";
    private static final String CCP_LOGIN_CTA = "ccpLoginCTA";
    private static final String HR_COIL_PLP="hr";
    private static final String HR_COIL = "hrCoilE250A";
    private static final String HR_THICKNESS = "thickness";
    private static final String HR_WIDTH = "width";
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
    private static final String SF_LOGIN_EMAIL="sfEmail";
    private static final String SF_LOGIN_PASSWORD="sfPassword";
    private static final String SF_LOGIN="sfLogin";
    private static final String SF_SEARCH="sfSearch";
    private static final String RCV2_CHECKOUT="rcCheckout";
    private static final String MY_REQUIREMENT_PAGE="myRequirementPage";
    private static final String REQUIREMENT_ID="requirementId";
    private static final String MILD_STEEL="mildSteel";
    private static final String COLOUR_COATED="colourCoated";
    private static final String COLOUR_COATED_PDP="colourCoatedPdp";
    private static final String THICKNESS="thickness(mm)";
    private static final String WIDTH="width(mm)";
    private static final String COATING_GRADE="coatingGrade";
    private static final String SUCCESS_PAGE_RCV2="rcCheckoutSuccessPage";
    private static final String CART_PAGE="cartPage";
    private static final String TEST_HOME="Test";
    private static final String POP_UP_BTN="closePopBtn";
    private static final String ITEM_Label="itemLabel";
    private static final String EDIT_BTN="editButton";
    private static final String DONE_BTN="doneButton";
    BaseSteps baseSteps=new BaseSteps();

    @When("user open the portal and login with valid credentials {string} {string}")
    public void user_open_the_portal_and_login_with_valid_credentials(String username, String password) throws Exception {

        Grid.driver().get(ConfigUtil.resolve("ccpPortalUrl"));
        Grid.driver().manage().window().maximize();
        Grid.driver().manage().deleteAllCookies();
        waitAndCheckIsElementVisible(LOGIN_CTA_BUTTON);
        waitAndClickOn(LOGIN_CTA_BUTTON); // click on login CTA button
        waitAndCheckIsElementVisible(LOGIN_VIA_OTP);
        waitAndClickOn(LOGIN_VIA_OTP); //Click on Login with OTP
        waitAndCheckIsElementVisible(LOGIN_EMAIL);
        waitAndSetText(LOGIN_EMAIL, username); // Give you Email
        waitAndCheckIsElementVisible(LOGIN_PASSWORD);
        waitAndSetText(LOGIN_PASSWORD, password);// Give the password
        waitAndCheckIsElementVisible(CCP_LOGIN_CTA);
        waitAndClickOn(CCP_LOGIN_CTA);// Login
    }

    @When("user open HR coil pdp")
    public void userOpenHRCoilPdp() {
//        sleep(AVG_WAIT);
        //Move the cursor to MiLd Steel
        Actions actions=new Actions(Grid.driver());
        WebElement mildSteel=Grid.driver().findElement(By.xpath("//a[contains(text(),'Mild Steel')]"));
        actions.moveToElement(mildSteel).perform();
        sleep(AVG_WAIT);
        //Move the cursor to Hot Rolled
        WebElement hotRolled=Grid.driver().findElement(By.xpath("(//a[contains(text(),'Hot rolled')])[6]"));
        actions.moveToElement(hotRolled).perform();
//        sleep(AVG_WAIT);
        //Move The cursor to Hot rolled and click
        WebElement hotRolledCoil=Grid.driver().findElement(By.xpath("(//a[contains(text(),'Hot rolled coils')])[2]"));

        actions.moveToElement(hotRolledCoil).perform();
        hotRolledCoil.click();
//        sleep(SHORT_WAIT);
        waitAndCheckIsElementPresent(HR_COIL);
        waitAndCheckIsElementClickable(HR_COIL);
        waitAndClickOn(HR_COIL);
    }

    @And("select all the attribute and add to cart")
    public void selectAllTheAttributeAndAddToCart() {
        //Select Thickness
        waitAndCheckIsElementPresent(HR_THICKNESS);
        waitAndClickOn(HR_THICKNESS);
        //Enter Width
        waitAndCheckIsElementPresent(HR_WIDTH);
        waitAndClickOn(HR_WIDTH);
        sleep(AVG_WAIT);
        //Enter Quantity
        waitAndCheckIsElementPresent(QUANTITY);
        waitAndSetText(QUANTITY, "10");
    }
    @And("user will add cement product to cart")
    public void userWillAddCementProductToCart(){
//        sleep(AVG_WAIT);
        //Open cement PLP page
       waitAndCheckIsElementPresent(CEMENT_PLP);
       waitAndClickOn(CEMENT_PLP);
       //Open Cement PDP page
       waitAndCheckIsElementPresent(CEMENT_PDP);
       waitAndClickOn(CEMENT_PDP);
       sleep(AVG_WAIT);
       //Enter Number of bags
       waitAndCheckIsElementPresent(CEMENT_QUANTITY);
       waitAndSetText(CEMENT_QUANTITY,"120");
       sleep(AVG_WAIT);
       //click Add-to-cart
       waitAndCheckIsElementPresent(ADD_TO_CART_CTA);
       waitAndClickOn(ADD_TO_CART_CTA);
        sleep(AVG_WAIT);
        //Click View cart
        waitAndCheckIsElementPresent(VIEW_CART);
       waitAndClickOn(VIEW_CART);
       softAssert.assertTrue(waitAndCheckIsElementPresent(SUBMIT_REQUIREMENT));

    }
    @And("user will add Stainless Steel product to cart")
    public void userWillAddStainlessSteelProductToCart(){
//        sleep(AVG_WAIT);
        //Open stainless steel PLP
        waitAndCheckIsElementPresent(STAINLESS_STEEL_PLP);
        waitAndClickOn(STAINLESS_STEEL_PLP);
        sleep(SHORT_WAIT);
        //open stainless steel PDP
        waitAndCheckIsElementPresent(STAINLESS_STEEL_PDP);
        waitAndClickOn(STAINLESS_STEEL_PDP);
//        sleep(SHORT_WAIT);
        //Select Thickness
        waitAndCheckIsElementPresent(STAINLESS_STEEL_THICKNESS);
        waitAndClickOn(STAINLESS_STEEL_THICKNESS);
        //Enter Width
        waitAndCheckIsElementPresent(STAINLESS_STEEL_WIDTH);
        waitAndClickOn(STAINLESS_STEEL_WIDTH);
        //Enter Length
        waitAndCheckIsElementPresent(STAINLESS_STEEL_LENGTH);
        waitAndClickOn(STAINLESS_STEEL_LENGTH);
        sleep(SHORT_WAIT);
        //Enter Quantity
        waitAndCheckIsElementPresent(QUANTITY);
        waitAndSetText(QUANTITY, "10");
        sleep(AVG_WAIT);
        //Click Add-to-cart
        waitAndCheckIsElementPresent(ADD_TO_CART_CTA);
        waitAndClickOn(ADD_TO_CART_CTA);
        sleep(LONG_WAIT);
        //Click View cart
        waitAndCheckIsElementPresent(VIEW_CART);
        waitAndClickOn(VIEW_CART);
        softAssert.assertTrue(waitAndCheckIsElementPresent(SUBMIT_REQUIREMENT));
//        sleep(AVG_WAIT);
    }

    @Then("check user able to add the product to cart page")
    public void checkUserAbleToAddTheProductToCartPage() {
        sleep(AVG_WAIT);
        waitAndCheckIsElementPresent(ADD_TO_CART_CTA);
        waitAndClickOn(ADD_TO_CART_CTA);
        sleep(AVG_WAIT);
        waitAndCheckIsElementPresent(VIEW_CART);
        waitAndClickOn(VIEW_CART);
        softAssert.assertTrue(waitAndCheckIsElementPresent(SUBMIT_REQUIREMENT));
    }
    @Then("check user able to delete line item from cart")
    public void checkUserAbleToDeleteLineItemFromCart(){
//        sleep(AVG_WAIT);
        //click delete
        waitAndCheckIsElementPresent(DELETE_LINE_ITEM);
        waitAndClickOn(DELETE_LINE_ITEM);
//        sleep(AVG_WAIT);
        //click delete CTA button
        waitAndCheckIsElementPresent(DELETE_CTA_BUTTON);
        waitAndClickOn(DELETE_CTA_BUTTON);
        log.info("Item deleted successfully");
//        sleep(LONG_WAIT);
    }
    @And("check user able to add the product to cart without pricing")
    public void checkUserAbleToAddTheProductToCartWithoutPricing(){
        sleep(AVG_WAIT);
        //Move the cursor to Mild Steel
        waitAndCheckIsElementPresent(MILD_STEEL);
        Actions actions=new Actions(Grid.driver());
        WebElement mildSteel=Grid.driver().findElement(By.xpath("//a[contains(text(),'Mild Steel')]"));
        actions.moveToElement(mildSteel).perform();
//        sleep(SHORT_WAIT);
        //click to Color coated
        waitAndCheckIsElementPresent(COLOUR_COATED);
        waitAndClickOn(COLOUR_COATED);
//        sleep(SHORT_WAIT);
        //click on color coated PDP
        waitAndCheckIsElementPresent(COLOUR_COATED_PDP);
        waitAndClickOn(COLOUR_COATED_PDP);
        //select thickness
        waitAndCheckIsElementPresent(THICKNESS);
        waitAndClickOn(THICKNESS);
        //Enter With
        waitAndCheckIsElementPresent(WIDTH);
        waitAndClickOn(WIDTH);
        //Select Coated Grade
        waitAndCheckIsElementPresent(COATING_GRADE);
        waitAndClickOn(COATING_GRADE);
        sleep(SHORT_WAIT);
        WebElement dropdown = Grid.driver().findElement(By.xpath("//div[@id='custom-dropdown']"));
        dropdown.click();
        sleep(SHORT_WAIT);
        WebElement option = Grid.driver().findElement(By.xpath("//li[contains(text(),'Sky Blue')]"));
        option.click();
        sleep(SHORT_WAIT);
        //Enter Quantity
        waitAndCheckIsElementPresent(QUANTITY);
        waitAndSetText(QUANTITY, "10");
        sleep(SHORT_WAIT);
        //click Add-to-cart
        waitAndCheckIsElementPresent(ADD_TO_CART_CTA);
        waitAndClickOn(ADD_TO_CART_CTA);
        sleep(LONG_WAIT);
        //Click View cart
        waitAndCheckIsElementPresent(VIEW_CART);
//        sleep(AVG_WAIT);
        waitAndClickOn(VIEW_CART);
        softAssert.assertTrue(waitAndCheckIsElementPresent(SUBMIT_REQUIREMENT));
    }
    @And("user checkout the RCV2")
    public void userCheckoutTheRcv2(){
//        sleep(AVG_WAIT);
        //Open My requirement page
        waitAndCheckIsElementPresent(MY_REQUIREMENT_PAGE);
        waitAndClickOn(MY_REQUIREMENT_PAGE);
        //copay the Requirement ID
        waitAndCheckIsElementPresent(REQUIREMENT_ID);
        String oldeRquirementId=waitAndGetText(REQUIREMENT_ID);
        //Open the cart page
        waitAndCheckIsElementPresent(CART_PAGE);
        waitAndClickOn(CART_PAGE);
        sleep(LONG_WAIT);
        //click cart Checkout
        waitAndCheckIsElementPresent(RCV2_CHECKOUT);
        waitAndClickOn(RCV2_CHECKOUT);
//        sleep(AVG_WAIT);
        //check the success page
        waitAndCheckIsElementPresent(SUCCESS_PAGE_RCV2);
        //open the requirement page
        waitAndCheckIsElementPresent(MY_REQUIREMENT_PAGE);
        waitAndClickOn(MY_REQUIREMENT_PAGE);
        waitAndCheckIsElementPresent(REQUIREMENT_ID);
        String neweRquirementId=waitAndGetText(REQUIREMENT_ID);
        log.info("Old Requirement id"+oldeRquirementId);
        log.info("New Requirement id"+neweRquirementId);
        AllureCustomLogReporter.addTestStepLog("Old Requirement id "+oldeRquirementId + "New Requirement id"+ neweRquirementId);
        if (oldeRquirementId.equalsIgnoreCase(neweRquirementId)) {
            log.info("Requirement ID is same. New Requirement not created.");
            AllureCustomLogReporter.addTestStepLog("Requirement ID is same. New Requirement not created.");
            Assert.fail("Requirement ID is same as old one — new requirement not created.");
        } else {
            log.info("New Requirement ID created successfully. ID: " + neweRquirementId);
            AllureCustomLogReporter.addTestStepLog("New Requirement ID created successfully. ID: " + neweRquirementId);
        }
    }

    @Then("close the payment reminder pop-up")
    public void closeThePaymentReminderPopUp() {
//        sleep(AVG_WAIT);
        waitAndCheckIsElementPresent(POP_UP_BTN);
        waitAndClickOn(POP_UP_BTN);
        log.info("Pop-up button closed");
        AllureCustomLogReporter.addTestStepLog("Pop-up button closed");
    }

    @And("calculate the number of line items in the cart")
    public void calculateTheNumberOfLineItemsInTheCart() {
//        sleep(AVG_WAIT);
        waitAndCheckIsElementPresent(ITEM_Label);
        WebElement itemCount=Grid.driver().findElement(By.xpath("//div[contains(text(), 'item(s)')]"));
        String countText = itemCount.getText();
        int count = Integer.parseInt(countText.replaceAll("\\D+",""));
        log.info("Item Count in the cart page is-->: " + count);
        AllureCustomLogReporter.addTestStepLog("Item Count in the cart page is-->:" + count);
        sleep(SHORT_WAIT);
    }

    @Then("validate the user is able to edit the the cart page and update the quantity or attribute")
    public void validateTheUserIsAbleToEditTheTheCartPageA() {
        waitAndClickOn(EDIT_BTN);
        sleep(SHORT_WAIT);
        //change the quantity and insert new quantity
        baseSteps.clearAndEnterValue("//input[@class='typography-h6 typography-font-medium']","3");
        waitAndClickOn(DONE_BTN);
        log.info("Item edited successfully");
        sleep(AVG_WAIT);

    }
}
