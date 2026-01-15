package com.qapitol.jsw.stepdefinition;

import com.qapitol.sauron.web.AbstractWebPage;
import com.qapitol.sauron.core.Grid;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.qapitol.jsw.utils.Hooks.softAssert;

public class AddressSteps extends AbstractWebPage {

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

    @And("user clicks on Testacco user menu")
    public void userClicksOnTestaccoUserMenu() {
        waitAndCheckIsElementVisible(CCP_USER_MENU_TESTACCO);
        waitAndClickOn(CCP_USER_MENU_TESTACCO);
    }

    @And("user clicks on Profile option")
    public void userClicksOnProfileOption() {
        waitAndCheckIsElementVisible(CCP_USER_MENU_PROFILE_OPTION);
        waitAndClickOn(CCP_USER_MENU_PROFILE_OPTION);
    }

    @And("user clicks on Addresses tab")
    public void userClicksOnAddressesTab() {
        By addressesTab = By.xpath(getLocator(CCP_PROFILE_ADDRESSES_TAB));
        scrollToElement(addressesTab, 10);
        waitAndCheckIsElementClickable(CCP_PROFILE_ADDRESSES_TAB);
        waitAndClickOn(CCP_PROFILE_ADDRESSES_TAB);
    }

    @Then("verify Addresses tab is opened")
    public void verifyAddressesTabIsOpened() {
        waitAndCheckIsElementVisible(CCP_PROFILE_ADDRESSES_TAB_ACTIVE);
        softAssert.assertTrue(
                isElementPresent(CCP_PROFILE_ADDRESSES_TAB_ACTIVE),
                "Addresses tab is not active or not displayed"
        );
        softAssert.assertAll();
    }

    @And("customer clicks on Add address button")
    public void user_clicks_on_add_address_button() {
        waitAndCheckIsElementVisible(CCP_ADD_ADDRESS_BUTTON);
        waitAndClickOn(CCP_ADD_ADDRESS_BUTTON);
    }

    @And("customer enter company name {string}")
    public void enter_company_name(String companyName) {
        waitAndCheckIsElementVisible(CCP_ADDRESS_COMPANY_NAME_INPUT);
        waitAndSetText(CCP_ADDRESS_COMPANY_NAME_INPUT, companyName);
    }

    @And("customer enter address line one {string}")
    public void enter_address_line_one(String addressLine) {
        waitAndCheckIsElementVisible(CCP_ADDRESS_LINE1_INPUT);
        waitAndSetText(CCP_ADDRESS_LINE1_INPUT, addressLine);
    }

    @And("customer enter pin code {string}")
    public void enter_pin_code(String pinCode) {
        waitAndCheckIsElementVisible(CCP_ADDRESS_PINCODE_INPUT);
        waitAndSetText(CCP_ADDRESS_PINCODE_INPUT, pinCode);
    }

    @And("customer enter city {string}")
    public void enter_city(String city) {
        waitAndCheckIsElementVisible(CCP_ADDRESS_CITY_INPUT);
        waitAndSetText(CCP_ADDRESS_CITY_INPUT, city);
    }

    @Then("customer clicks on Save changes button")
    public void customer_clicks_on_save_changes_button() {

        By saveChangesBtn = By.xpath("//button[@id='save-change' and normalize-space()='Save changes']");
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(20));

        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(saveChangesBtn));
        wait.until(driver -> button.isEnabled());

        ((JavascriptExecutor) Grid.driver())
                .executeScript("arguments[0].scrollIntoView({block:'center'});", button);

        try { Thread.sleep(500); } catch (InterruptedException ignored) {}

        ((JavascriptExecutor) Grid.driver())
                .executeScript("arguments[0].click();", button);
    }

    @Then("validate customer added address {string}")
    public void validate_customer_added_address(String companyName) {

        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) Grid.driver();

        By addedAddress = By.xpath(
                "//div[contains(@class,'display-company-name') and normalize-space()='" + companyName + "']"
        );

        WebElement address = wait.until(ExpectedConditions.presenceOfElementLocated(addedAddress));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", address);

        softAssert.assertTrue(
                address.isDisplayed(),
                "Address with company name '" + companyName + "' is NOT displayed"
        );
    }

    @Then("customer navigate company name jsw test address")
    public void customer_edits_company_name_for_jsw_test_address() {

        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) Grid.driver();

        By jswCompanyName = By.xpath(
                "//div[contains(@class,'display-company-name') and normalize-space()='jsw test']"
        );

        WebElement savedAddress = wait.until(
                ExpectedConditions.visibilityOfElementLocated(jswCompanyName)
        );
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", savedAddress);
    }

    @Then("customer clicks edit for jsw test address")
    public void customer_clicks_edit_for_jsw_test_address() {

        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) Grid.driver();

        By jswCompanyName = By.xpath(
                "//div[contains(@class,'display-company-name') and normalize-space()='jsw test']"
        );

        By jswEditButton = By.xpath(
                "//div[contains(@class,'display-company-name') and normalize-space()='jsw test']" +
                        "/ancestor::div[contains(@class,'address-list-item')]//div[normalize-space()='Edit']"
        );

        WebElement company = wait.until(ExpectedConditions.visibilityOfElementLocated(jswCompanyName));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", company);

        WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(jswEditButton));
        js.executeScript("arguments[0].click();", editBtn);
    }

    @Then("customer clicks delete for jsw test address")
    public void customer_clicks_delete_for_jsw_test_address() {

        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) Grid.driver();

        By deleteIcon = By.xpath(
                "//div[contains(@class,'display-company-name') and normalize-space()='jsw test']" +
                        "/ancestor::div[contains(@class,'address')]//img[contains(@class,'delete-icon')]"
        );

        WebElement delete = wait.until(ExpectedConditions.presenceOfElementLocated(deleteIcon));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", delete);
        wait.until(ExpectedConditions.elementToBeClickable(delete));
        js.executeScript("arguments[0].click();", delete);
    }

    @Then("validate customer added address {string} is not visible")
    public void validate_customer_added_address_is_not_visible(String companyName) {

        By deletedAddress = By.xpath(
                "//div[contains(@class,'display-company-name') and normalize-space()='" + companyName + "']"
        );

        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        boolean isAddressPresent = Grid.driver().findElements(deletedAddress).size() > 0;

        if (isAddressPresent) {
            throw new AssertionError(
                    "Address with company name '" + companyName + "' is still visible after deletion"
            );
        }
    }

    public void scrollToElement(By locator, int timeoutInSeconds) {
        WebElement element = new WebDriverWait(Grid.driver(), Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(locator));

        ((JavascriptExecutor) Grid.driver())
                .executeScript("arguments[0].scrollIntoView({block:'center', inline:'nearest'});", element);
    }

    @And("customer leaves company name blank")
    public void customer_leaves_company_name_blank() {
        waitAndCheckIsElementVisible(CCP_ADDRESS_COMPANY_NAME_INPUT);
        waitAndSetText(CCP_ADDRESS_COMPANY_NAME_INPUT, "");
        Grid.driver().findElement(By.tagName("body")).click();
    }

    @Then("customer clicks cancel delete for jsw test address")
    public void customer_clicks_cancel_delete_for_jsw_test_address() {

        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) Grid.driver();

        By cancelButton = By.xpath(
                "//button[normalize-space()='Cancel' and contains(@class,'primary-btn')]"
        );

        WebElement cancel = wait.until(ExpectedConditions.presenceOfElementLocated(cancelButton));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", cancel);
        wait.until(ExpectedConditions.elementToBeClickable(cancel));
        js.executeScript("arguments[0].click();", cancel);

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'modal') and contains(@class,'show')]")
                )
        );
    }

    @Then("customer clicks proceed button for delete jsw test address")
    public void customer_clicks_proceed_button_for_delete_jsw_test_address() {

        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) Grid.driver();

        By proceedButton = By.xpath(
                "//button[normalize-space()='Proceed' and contains(@class,'primary-outline-btn')]"
        );

        WebElement proceed = wait.until(ExpectedConditions.presenceOfElementLocated(proceedButton));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", proceed);
        wait.until(ExpectedConditions.elementToBeClickable(proceed));
        js.executeScript("arguments[0].click();", proceed);

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'modal') and contains(@class,'show')]")
                )
        );
    }

    @And("customer enter company name blank")
    public void customer_enter_company_name_blank() {

        waitAndCheckIsElementVisible(CCP_ADDRESS_COMPANY_NAME_INPUT);
        waitAndSetText(CCP_ADDRESS_COMPANY_NAME_INPUT, "");

        Grid.driver().findElement(
                By.xpath(getLocator(CCP_ADDRESS_COMPANY_NAME_INPUT))
        ).sendKeys(" ");
    }

    @Then("customer should not be able to save address")
    public void customer_should_not_be_able_to_save_address() {

        waitAndCheckIsElementVisible(CCP_ADDRESS_SAVE_CHANGES_BUTTON);

        WebElement saveBtn = Grid.driver().findElement(
                By.xpath(getLocator(CCP_ADDRESS_SAVE_CHANGES_BUTTON))
        );

        softAssert.assertFalse(
                saveBtn.isEnabled(),
                "Save changes button is ENABLED but should be DISABLED"
        );

        softAssert.assertAll();
    }
}
