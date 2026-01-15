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

    // ============ LOCATOR KEYS (YAML MAPPED) ============

    public static final String CCP_USER_MENU_TESTACCO = "ccpUserMenuTestacco";
    public static final String CCP_USER_MENU_PROFILE_OPTION = "ccpUserMenuProfileOption";
    public static final String CCP_PROFILE_ADDRESSES_TAB = "ccpProfileAddressesTab";
    public static final String CCP_PROFILE_ADDRESSES_TAB_ACTIVE = "ccpProfileAddressesTabActive";
    public static final String CCP_ADD_ADDRESS_BUTTON = "ccpAddAddressButton";
    public static final String CCP_ADDRESS_COMPANY_NAME_INPUT = "ccpAddressCompanyNameInput";
    public static final String CCP_ADDRESS_LINE1_INPUT = "ccpAddressLine1Input";
    public static final String CCP_ADDRESS_PINCODE_INPUT = "ccpAddressPinCodeInput";
    public static final String CCP_ADDRESS_CITY_INPUT = "ccpAddressCityInput";
    public static final String CCP_ADDRESS_SAVE_CHANGES_BUTTON = "ccpAddressSaveChangesButton";


    // ============ STEPS ============

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

        By addressesTab = By.xpath(
                getLocator(CCP_PROFILE_ADDRESSES_TAB)
        );

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

        By saveChangesBtn = By.xpath(
                "//button[@id='save-change' and normalize-space()='Save changes']"
        );

        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(20));

        // 1️⃣ Wait until modal button is PRESENT in DOM
        WebElement button = wait.until(
                ExpectedConditions.presenceOfElementLocated(saveChangesBtn)
        );

        // 2️⃣ Wait until button is ENABLED
        wait.until(driver -> button.isEnabled());

        // 3️⃣ Scroll into view (MODAL SAFE)
        ((JavascriptExecutor) Grid.driver())
                .executeScript("arguments[0].scrollIntoView({block:'center'});", button);

        // 4️⃣ Small pause for Vue re-render (IMPORTANT)
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}

        // 5️⃣ CLICK VIA JAVASCRIPT (MANDATORY FOR MODALS)
        ((JavascriptExecutor) Grid.driver())
                .executeScript("arguments[0].click();", button);
    }

    @Then("validate customer added address {string}")
    public void validate_customer_added_address(String companyName) {

        WebDriver driver = Grid.driver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 🔹 Dynamic locator for any company name
        By addedAddress = By.xpath(
                "//div[contains(@class,'display-company-name') and normalize-space()='" + companyName + "']"
        );

        // 🔹 Wait until present in DOM
        WebElement address = wait.until(
                ExpectedConditions.presenceOfElementLocated(addedAddress)
        );

        // 🔹 Scroll to element (important for SSR page)
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", address);

        // 🔹 ASSERTION
        softAssert.assertTrue(
                address.isDisplayed(),
                "❌ Address with company name '" + companyName + "' is NOT displayed"
        );
    }


    @Then("customer navigate company name jsw test address")
    public void customer_edits_company_name_for_jsw_test_address() {

        WebDriver driver = Grid.driver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // ✅ XPath for "jsw test" company name
        By jswCompanyName = By.xpath(
                "//div[contains(@class,'display-company-name') and normalize-space()='jsw test']"
        );

        // ✅ XPath for Edit button of ONLY jsw test address
        By jswEditButton = By.xpath(
                "//div[contains(@class,'display-company-name') and normalize-space()='jsw test']" +
                        "/ancestor::div[2]//div[normalize-space()='Edit']"
        );

        // ✅ XPath for Company input
        By companyInput = By.xpath("//input[@id='company']");

        // ✅ XPath for Save Changes button
        By saveChangesBtn = By.xpath("//button[@id='save-change']");

        // 🔹 Scroll to jsw test address
        WebElement savedAddress = wait.until(
                ExpectedConditions.visibilityOfElementLocated(jswCompanyName)
        );
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", savedAddress);

    }
    @Then("customer clicks edit for jsw test address")
    public void customer_clicks_edit_for_jsw_test_address() {

        WebDriver driver = Grid.driver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        By jswCompanyName = By.xpath(
                "//div[contains(@class,'display-company-name') and normalize-space()='jsw test']"
        );

        By jswEditButton = By.xpath(
                "//div[contains(@class,'display-company-name') and normalize-space()='jsw test']" +
                        "/ancestor::div[contains(@class,'address-list-item')]" +
                        "//div[normalize-space()='Edit']"
        );

        // 🔹 Scroll to company name
        WebElement company = wait.until(
                ExpectedConditions.visibilityOfElementLocated(jswCompanyName)
        );
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", company);

        // 🔹 Click Edit
        WebElement editBtn = wait.until(
                ExpectedConditions.elementToBeClickable(jswEditButton)
        );
        js.executeScript("arguments[0].click();", editBtn);
    }

    @Then("customer clicks delete for jsw test address")
    public void customer_clicks_delete_for_jsw_test_address() {

        WebDriver driver = Grid.driver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        By deleteIcon = By.xpath(
                "//div[contains(@class,'display-company-name') and normalize-space()='jsw test']" +
                        "/ancestor::div[contains(@class,'address')]" +
                        "//img[contains(@class,'delete-icon')]"
        );

        // 🔹 Wait for presence (SSR safe)
        WebElement delete = wait.until(
                ExpectedConditions.presenceOfElementLocated(deleteIcon)
        );

        // 🔹 Scroll (MANDATORY)
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", delete);

        // 🔹 Wait until clickable
        wait.until(ExpectedConditions.elementToBeClickable(delete));

        // 🔹 JS click (prevents ElementNotInteractableException)
        js.executeScript("arguments[0].click();", delete);
    }

    @Then("validate customer added address {string} is not visible")
    public void validate_customer_added_address_is_not_visible(String companyName) {

        WebDriver driver = Grid.driver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        By deletedAddress = By.xpath(
                "//div[contains(@class,'display-company-name') and normalize-space()='" + companyName + "']"
        );

        // 🔹 Small wait to allow backend delete + UI refresh
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 🔹 Assert element is NOT present in DOM
        boolean isAddressPresent = driver.findElements(deletedAddress).size() > 0;

        if (isAddressPresent) {
            throw new AssertionError(
                    "❌ Address with company name '" + companyName + "' is still visible after deletion"
            );
        }
    }



    // ============ COMMON SCROLL METHOD ============

    public void scrollToElement(By locator, int timeoutInSeconds) {

        WebElement element = new WebDriverWait(Grid.driver(), Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(locator));

        ((JavascriptExecutor) Grid.driver())
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
                        element
                );
    }
    @And("customer leaves company name blank")
    public void customer_leaves_company_name_blank() {

        // Make sure field is visible
        waitAndCheckIsElementVisible(CCP_ADDRESS_COMPANY_NAME_INPUT);

        // Clear any existing value
        waitAndSetText(CCP_ADDRESS_COMPANY_NAME_INPUT, "");

        // Move focus out to trigger validation (VERY IMPORTANT)
        Grid.driver().findElement(By.tagName("body")).click();
    }
    @Then("customer clicks cancel delete for jsw test address")
    public void customer_clicks_cancel_delete_for_jsw_test_address() {

        WebDriver driver = Grid.driver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // ✅ Cancel button inside delete confirmation modal
        By cancelButton = By.xpath(
                "//button[normalize-space()='Cancel' and contains(@class,'primary-btn')]"
        );

        // 🔹 Wait until Cancel button is present (modal rendered)
        WebElement cancel = wait.until(
                ExpectedConditions.presenceOfElementLocated(cancelButton)
        );

        // 🔹 Scroll into view (MANDATORY for modal)
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", cancel);

        // 🔹 Wait until clickable
        wait.until(ExpectedConditions.elementToBeClickable(cancel));

        // 🔹 JS click to avoid ElementNotInteractableException
        js.executeScript("arguments[0].click();", cancel);

        // 🔹 Ensure modal disappears
        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'modal') and contains(@class,'show')]")
                )
        );
    }

    @And("customer enter company name blank")
    public void customer_enter_company_name_blank() {

        waitAndCheckIsElementVisible(CCP_ADDRESS_COMPANY_NAME_INPUT);

        // Clear field explicitly
        waitAndSetText(CCP_ADDRESS_COMPANY_NAME_INPUT, "");

        // Trigger validation (blur)
        Grid.driver().findElement(
                By.xpath(getLocator(CCP_ADDRESS_COMPANY_NAME_INPUT))
        ).sendKeys(" ");
    }
    @Then("customer should not be able to save address")
    public void customer_should_not_be_able_to_save_address() {

        // Wait till Save button is visible in modal
        waitAndCheckIsElementVisible(CCP_ADDRESS_SAVE_CHANGES_BUTTON);

        // Fetch WebElement
        WebElement saveBtn = Grid.driver().findElement(
                By.xpath(getLocator(CCP_ADDRESS_SAVE_CHANGES_BUTTON))
        );

        // 🔹 ASSERT: button must be disabled
        softAssert.assertFalse(
                saveBtn.isEnabled(),
                " Save changes button is ENABLED, but it should be DISABLED when company name is blank"
        );

        softAssert.assertAll();
    }

}
