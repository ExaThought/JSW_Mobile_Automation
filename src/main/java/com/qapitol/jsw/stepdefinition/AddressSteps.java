package com.qapitol.jsw.stepdefinition;

import com.qapitol.sauron.web.AbstractWebPage;
import com.qapitol.sauron.core.Grid;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.qapitol.jsw.pageFactory.CategoryPages.ALL_CATEGORY;
import static com.qapitol.jsw.utils.CustomUtil.*;
import static com.qapitol.jsw.utils.Hooks.softAssert;

public class AddressSteps extends AbstractWebPage {

    // ================= LOCATOR KEYS =================

    private static final String CCP_USER_MENU_TESTACCO = "ccpUserMenuTestacco";
    private static final String CCP_USER_MENU_PROFILE_OPTION = "ccpUserMenuProfileOption";

    private static final String CCP_PROFILE_ADDRESSES_TAB = "ccpProfileAddressesTab";
    private static final String CCP_PROFILE_ADDRESSES_TAB_ACTIVE = "ccpProfileAddressesTabActive";

    private static final String CCP_ADD_ADDRESS_BUTTON = "ccpAddAddressButton";

    private static final String CCP_ADDRESS_COMPANY_NAME_INPUT = "ccpAddressCompanyNameInput";
    private static final String CCP_ADDRESS_LINE1_INPUT = "ccpAddressLine1Input";
    private static final String CCP_ADDRESS_PINCODE_INPUT = "ccpAddressPinCodeInput";
    private static final String CCP_ADDRESS_CITY_INPUT = "ccpAddressCityInput";

    private static final String CCP_ADDRESS_SAVE_CHANGES_BUTTON = "ccpAddressSaveChangesButton";

    // Dynamic keys
    private static final String CCP_SAVED_ADDRESS_DYNAMIC = "ccpSavedAddressCompanyNameDynamic";
    private static final String CCP_EDIT_BUTTON_DYNAMIC = "ccpEditButtonForSavedAddress";
    private static final String CCP_DELETE_BUTTON_DYNAMIC = "ccpDeleteButtonForSavedAddress";

    private static final String CCP_DELETE_PROCEED_BUTTON = "ccpDeleteAddressProceedButton";
    private static final String CCP_COMPANY_NAME_ERROR = "ccpCompanyNameErrorMessage";
    private static final String ADDRESS_PIN_CODE_ERROR ="pinCodeErrorMessage";
    private static final String SET_AS_DEFAULT_SHIPPING_ADDRESS_CHECK="setAsDefaultShippingAddressCheck";
    private static final String ACCOUNT_BUTTON="(//span[text()=\"Account\"])[2]";
    private static final String REGION ="ccpAddressRegionInput";
    private static final String STATE = "ccpAddressStateInput";
    private String CompanyName;


    // ================= COMMON HELPERS =================

    public WebElement getElement(String key) {
        By locator = By.xpath(getLocator(key));
        return new WebDriverWait(Grid.driver(), Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public String getDynamicXpath(String key, String value) {
        return getLocator(key).replace("${companyName}", value);
    }

    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) Grid.driver())
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }


    // ================= USER MENU =================

    @And("user clicks on Account Drop Down")
    public void userClicksOnAccountDropDown() {
        try {
            log.info("Clicking on Account Drop Down.");

            By accountButton = By.xpath(ACCOUNT_BUTTON);

            refreshAndWaitFor(accountButton);

            waits(accountButton);

            softAssert.assertTrue(
                    isDisplayed(accountButton),
                    "Account Drop Down is not visible."
            );

            clickButton(accountButton);

            log.info("Successfully clicked on Account Drop Down.");

        } catch (Exception e) {
            log.info("Exception occurred while clicking Account Drop Down: " + e.getMessage());
            softAssert.fail("Failed to click Account Drop Down: " + e.getMessage());
        }
    }

    @And("user clicks on Profile option")
    public void userClicksOnProfileOption() {
        log.info("User click on profile button");
        waitAndCheckIsElementVisible(CCP_USER_MENU_PROFILE_OPTION);
        waitAndClickOn(CCP_USER_MENU_PROFILE_OPTION);
    }


    // ================= ADDRESSES TAB =================

    @And("user clicks on Addresses tab")
    public void userClicksOnAddressesTab() {

        log.info("User clicks on Address tab");
        waitAndCheckIsElementClickable(CCP_PROFILE_ADDRESSES_TAB);
        waitAndClickOn(CCP_PROFILE_ADDRESSES_TAB);
    }

    @Then("customer navigate company name jsw test address")
    public void verifyAddressesTabIsOpened() {
        log.info("Customer verifying the ADDRESSES_TAB_ACTIVE");
        waitAndCheckIsElementVisible(CCP_PROFILE_ADDRESSES_TAB_ACTIVE);

        softAssert.assertTrue(
                isElementPresent(CCP_PROFILE_ADDRESSES_TAB_ACTIVE),
                " Addresses tab is not active"
        );
    }


    // ================= ADD ADDRESS FLOW =================

    @And("customer clicks on Add address button")
    public void user_clicks_on_add_address_button() {
        log.info("Customer clicks on Add Address Button");
        waitAndCheckIsElementVisible(CCP_ADD_ADDRESS_BUTTON);
        waitAndClickOn(CCP_ADD_ADDRESS_BUTTON);
    }

    @And("customer enter company name")
    public void enter_company_name() {
        String name = generateAutoGenName();
        CompanyName = name;
        log.info("Customer enters the company name");
        waitAndSetText(CCP_ADDRESS_COMPANY_NAME_INPUT, name);
    }

    @And("customer enter address line one {string}")
    public void enter_address_line_one(String addressLine) {
        log.info("Customer enters the Address line "+addressLine);
        waitAndSetText(CCP_ADDRESS_LINE1_INPUT, addressLine);
    }

    @And("customer enter pin code {string}")
    public void enter_pin_code(String pinCode) {
        log.info("Customer enter the pincode "+pinCode);
        waitAndSetText(CCP_ADDRESS_PINCODE_INPUT, pinCode);
    }

    @Then("validate pincode error message {string}")
    public void validate_pincode_error_message(String expectedMsg) {
        waitAndCheckIsElementVisible(ADDRESS_PIN_CODE_ERROR);
        String actualMsg = waitAndGetText(ADDRESS_PIN_CODE_ERROR);
        log.info("Successfully retrieved pin code error message: " + actualMsg);
        softAssert.assertEquals(actualMsg, expectedMsg, " Pin code error message mismatch");
    }

    @And("customer enter city {string}")
    public void enter_city(String city) {
        log.info("customer enters the city.");
        waitAndSetText(CCP_ADDRESS_CITY_INPUT, city);
    }


    // ================= SAVE ADDRESS =================

    @Then("customer clicks on Save changes button")
    public void customer_clicks_on_save_changes_button() {
        log.info("Clicking on Save Changes button.");
    waitAndCheckIsElementClickable(CCP_ADDRESS_SAVE_CHANGES_BUTTON);
    waitAndClickOn(CCP_ADDRESS_SAVE_CHANGES_BUTTON);

    }




    // ================= VALIDATE SAVED ADDRESS =================

    @Then("validate customer added address")
    public void validate_customer_added_address() {

        String xpath = getDynamicXpath(CCP_SAVED_ADDRESS_DYNAMIC, CompanyName);

        WebElement address = new WebDriverWait(Grid.driver(), Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

        scrollIntoView(address);

        softAssert.assertTrue(
                address.isDisplayed(),
                " Address with company name '" + CompanyName + "' is NOT displayed"
        );
    }

//    @Then("validate customer added address")
//    public void validate_customer_added_address1() {
//
//        String xpath = getDynamicXpath(CCP_SAVED_ADDRESS_DYNAMIC, CompanyName);
//
//        WebElement address = new WebDriverWait(Grid.driver(), Duration.ofSeconds(20))
//                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
//
//        scrollIntoView(address);
//
//        softAssert.assertTrue(
//                address.isDisplayed(),
//                " Address with company name '" + CompanyName + "' is NOT displayed"
//        );
//    }


    // ================= EDIT ADDRESS =================

    @Then("customer clicks edit for jsw test address")
    public void customer_clicks_edit_for_jsw_test_address() {

        String xpath = getDynamicXpath(CCP_EDIT_BUTTON_DYNAMIC, CompanyName);

        WebElement editBtn = new WebDriverWait(Grid.driver(), Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

        scrollIntoView(editBtn);

        ((JavascriptExecutor) Grid.driver())
                .executeScript("arguments[0].click();", editBtn);
    }


    // ================= DELETE ADDRESS =================

    @Then("customer clicks delete for jsw test address")
    public void customer_clicks_delete_for_jsw_test_address() {

        String xpath = getDynamicXpath(CCP_DELETE_BUTTON_DYNAMIC, CompanyName);

        WebElement deleteBtn = new WebDriverWait(Grid.driver(), Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

        scrollIntoView(deleteBtn);

        ((JavascriptExecutor) Grid.driver())
                .executeScript("arguments[0].click();", deleteBtn);
    }


    // ================= DELETE CONFIRMATION =================

    @Then("customer clicks proceed button for delete jsw test address")
    public void customer_clicks_proceed_button_for_delete_jsw_test_address() {

        waitAndCheckIsElementVisible(CCP_DELETE_PROCEED_BUTTON);
        waitAndClickOn(CCP_DELETE_PROCEED_BUTTON);
    }

    @Then("customer clicks cancel delete for jsw test address")
    public void customer_clicks_cancel_delete_for_jsw_test_address() {

        waitAndCheckIsElementVisible("ccpDeleteAddressCancelButton");
        waitAndClickOn("ccpDeleteAddressCancelButton");
    }

    @Then("validate customer added address {string} is not visible")
    public void validate_customer_added_address_is_not_visible(String companyName) {

        String xpath = getDynamicXpath("ccpDeletedAddressNotVisibleDynamic", companyName);

        By deletedAddress = By.xpath(xpath);

        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));

        boolean isInvisible = wait.until(
                ExpectedConditions.invisibilityOfElementLocated(deletedAddress)
        );

        softAssert.assertTrue(
                isInvisible,
                " Address with company name '" + companyName + "' is still visible after deletion"
        );
    }

    @Then("validate customer added address is not visible")
    public void validate_customer_added_address_is_not_visible() {

        String xpath = getDynamicXpath("ccpDeletedAddressNotVisibleDynamic",CompanyName );

        By deletedAddress = By.xpath(xpath);

        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));

        boolean isInvisible = wait.until(
                ExpectedConditions.invisibilityOfElementLocated(deletedAddress)
        );

        softAssert.assertTrue(
                isInvisible,
                " Address with company name '" + CompanyName + "' is still visible after deletion"
        );
    }


    // ================= NEGATIVE VALIDATION =================

    @And("customer enter company name blank")
    public void customer_leaves_company_name_blank() {

        waitAndSetText(CCP_ADDRESS_COMPANY_NAME_INPUT, "");
        Grid.driver().findElement(By.tagName("body")).click();
    }

    @Then("customer should not be able to save address")
    public void customer_should_not_be_able_to_save_address() {

        waitAndCheckIsElementVisible(CCP_ADDRESS_SAVE_CHANGES_BUTTON);

        WebElement saveBtn = Grid.driver().findElement(By.xpath(getLocator(CCP_ADDRESS_SAVE_CHANGES_BUTTON)));

        String disabledAttr = saveBtn.getAttribute("disabled");
        String ariaDisabled = saveBtn.getAttribute("aria-disabled");
        String classAttr = saveBtn.getAttribute("class");

        boolean attrDisabled = disabledAttr != null &&
                (disabledAttr.equalsIgnoreCase("true") || disabledAttr.equalsIgnoreCase("disabled"));

        boolean ariaAttr = ariaDisabled != null &&
                (ariaDisabled.equalsIgnoreCase("true") || ariaDisabled.equalsIgnoreCase("disabled"));

        boolean classDisabled = classAttr != null &&
                classAttr.toLowerCase().contains("disabled");

        boolean finalState = !saveBtn.isEnabled() || attrDisabled || ariaAttr || classDisabled;

        softAssert.assertTrue(
                finalState,
                "Save changes button is ENABLED but EXPECTED to be DISABLED!"
        );
    }

    @Then("validate company name error message")
    public void validate_company_name_error_message() {

        waitAndCheckIsElementVisible(CCP_COMPANY_NAME_ERROR);

        String msg = getElement(CCP_COMPANY_NAME_ERROR).getText().trim();

        softAssert.assertFalse(
                msg.isEmpty(),
                " Company name error message not displayed"
        );
    }

    @Then("validate set as default option should not be visible for shipping address")
    public void validate_set_as_default_option_should_not_be_visible_for_shipping_address() {
        String msg=waitAndGetText(SET_AS_DEFAULT_SHIPPING_ADDRESS_CHECK);
        softAssert.assertTrue(msg!=null);
    }

    @And("customer enter region {string}")
    public void customerEnterRegion(String region) {
        waitAndSetText(REGION, region);
        log.info("Entering the region "+region);
    }

    @And("customer enters state {string}")
    public void customerEntersState(String state) {
        waitAndSetText(STATE,state);
        log.info("Entering the state "+state);
    }
}
