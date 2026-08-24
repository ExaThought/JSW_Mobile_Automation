package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.LocatorReader;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.qapitol.jsw.utils.CustomUtil.*;
import static com.qapitol.jsw.utils.Hooks.softAssert;

public class BankDetailSteps extends AbstractWebPage {

    private static final String CCP_USER_MENU_TESTACCO = "ccpUserMenuTestacco";
    private static final String CCP_USER_MENU_PROFILE_OPTION = "ccpUserMenuProfileOption";


    // ================= LOCATOR KEYS =================
    private static final String BANK_ACCOUNT_DETAILS_TAB = "bankAccountDetailsTab";
    private static final String GSTIN_NAME_VALUE = "gstinNameValue";
    private static final String ACCOUNT_HOLDER_NAME_VALUE = "accountHolderNameValue";
    private static final String BANK_ACCOUNT_NUMBER_VALUE = "bankAccountNumberValue";
    private static final String IFSC_CODE_VALUE = "ifscCodeValue";
    private static final String EMPTY_BANK_DETAILS_TITLE = "emptyBankDetailsTitle";
    private static final String ADD_BANK_DETAILS_CTA_BUTTON = "addBankDetailsCTAButton";
    private static final String BANK_DETAILS_POP_UP_TITLE = "bankDetailsPopUpTitle";
    private static final String REPLACE_BANK_ACCOUNT = "//button[normalize-space()='Replace bank account']";
    private static final String ENTER_BANK_ACCOUNT_HEADING = "//h2[normalize-space()='Enter bank account information']";
    // ✅ Load YAML File
    LocatorReader locatorReader =
            new LocatorReader("locators/bankdetailsteps.yaml");
    WebDriverWait wait =
            new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));


    // ================= COMMON METHODS =================

    public WebElement getElement(String key) {
        String xpath = locatorReader.getXpath("elements." + key + ".en_US.web");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public void clickOn(String key) {
        getElement(key).click();
    }

    public String getTextValue(String key) {
        return getElement(key).getText().trim();
    }


    // ================= NAVIGATION =================

    @And("user clicks on customer account menu bar")
    public void userClicksOnCustomerAccountMenuBar() {
        waitAndCheckIsElementClickable(CCP_USER_MENU_TESTACCO);
        clickOn(CCP_USER_MENU_TESTACCO);
    }

    @And("user able to navigate on customer Profile section")
    public void userNavigatesToCustomerProfileSection() {
        waitAndCheckIsElementClickable(CCP_USER_MENU_PROFILE_OPTION);
        clickOn(CCP_USER_MENU_PROFILE_OPTION);
    }

    @And("user clicks on Bank account details tab")
    public void userClicksOnBankAccountDetailsTab() {
        try {
            log.info("Clicking on Bank Account Details tab.");

            waitAndCheckIsElementClickable(BANK_ACCOUNT_DETAILS_TAB);


            clickOn(BANK_ACCOUNT_DETAILS_TAB);

            log.info("Successfully clicked on Bank Account Details tab.");

        } catch (Exception e) {
            log.info("Exception occurred while clicking Bank Account Details tab: " + e.getMessage());
            softAssert.fail("Failed to click Bank Account Details tab: " + e.getMessage());
        }
    }


    // ================= BANK VALIDATIONS =================

    @Then("user should see GSTIN Name in Bank details")
    public void validateGSTINNameInBankDetails() {
        try {
            log.info("Verifying GSTIN Name in Bank Details.");

            String gstinName = getTextValue(GSTIN_NAME_VALUE);

            log.info("GSTIN Name: " + gstinName);

            softAssert.assertFalse(
                    gstinName == null || gstinName.trim().isEmpty(),
                    "GSTIN Name is empty."
            );

            log.info("GSTIN Name verified successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying GSTIN Name: " + e.getMessage());
            softAssert.fail("Exception while verifying GSTIN Name: " + e.getMessage());
        }
    }

    @Then("user should see Account holder name in Bank details")
    public void validateAccountHolderNameInBankDetails() {
        try {
            log.info("Verifying Account Holder Name in Bank Details.");

            String holderName = getTextValue(ACCOUNT_HOLDER_NAME_VALUE);

            log.info("Account Holder Name: " + holderName);

            softAssert.assertFalse(
                    holderName == null || holderName.trim().isEmpty(),
                    "Account Holder Name is empty."
            );

            log.info("Account Holder Name verified successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying Account Holder Name: " + e.getMessage());
            softAssert.fail("Exception while verifying Account Holder Name: " + e.getMessage());
        }
    }

    @Then("user should see Bank account number in Bank details")
    public void validateBankAccountNumberInBankDetails() {
        try {
            log.info("Verifying Bank Account Number in Bank Details.");

            String accountNumber = getTextValue(BANK_ACCOUNT_NUMBER_VALUE);

            log.info("Bank Account Number: " + accountNumber);

            softAssert.assertFalse(
                    accountNumber == null || accountNumber.trim().isEmpty(),
                    "Bank Account Number is empty."
            );

            log.info("Bank Account Number verified successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying Bank Account Number: " + e.getMessage());
            softAssert.fail("Exception while verifying Bank Account Number: " + e.getMessage());
        }
    }

    @Then("user should see IFSC code in Bank details")
    public void validateIFSCCodeInBankDetails() {
        try {
            log.info("Verifying IFSC Code in Bank Details.");

            String ifscCode = getTextValue(IFSC_CODE_VALUE);

            log.info("IFSC Code: " + ifscCode);

            softAssert.assertFalse(
                    ifscCode == null || ifscCode.trim().isEmpty(),
                    "IFSC Code is empty."
            );

            log.info("IFSC Code verified successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying IFSC Code: " + e.getMessage());
            softAssert.fail("Exception while verifying IFSC Code: " + e.getMessage());
        }
    }

    @When("user clicks on Replace bank account option")
    public void userClicksOnReplaceBankAccount() {
        try {
            log.info("Clicking on Replace Bank Account option.");

            waits(By.xpath(REPLACE_BANK_ACCOUNT));
            clickButton(By.xpath(REPLACE_BANK_ACCOUNT));

            log.info("Successfully clicked on Replace Bank Account option.");

        } catch (Exception e) {
            log.info("Exception occurred while clicking Replace Bank Account option: " + e.getMessage());
            softAssert.fail("Failed to click Replace Bank Account option: " + e.getMessage());
        }
    }

    @Then("user should see Enter bank account information page")
    public void userShouldSeeEnterBankAccountInformationPage() {
        try {
            log.info("Verifying Enter Bank Account Information popup is displayed.");

            String headingText = getText(By.xpath(ENTER_BANK_ACCOUNT_HEADING));

            log.info("Popup heading: " + headingText);

            softAssert.assertFalse(
                    headingText == null || headingText.trim().isEmpty(),
                    "Enter Bank Account Information heading is not visible."
            );

            log.info("Enter Bank Account Information page verified successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying Enter Bank Account Information page: " + e.getMessage());
            softAssert.fail("Exception while verifying Enter Bank Account Information page: " + e.getMessage());
        }
    }

    @Then("verify bank details title")
    public void verifyBankDetailsTitle() {
        log.info("Verifying Bank Details title.");
        waitAndCheckIsElementPresent(EMPTY_BANK_DETAILS_TITLE);
        softAssert.assertTrue(waitAndCheckIsElementPresent(ADD_BANK_DETAILS_CTA_BUTTON), "Add a bank account CTA is not present");
        softAssert.assertEquals(getTextValue(ADD_BANK_DETAILS_CTA_BUTTON), "Add a bank account", "CTA Text is not matching");

    }

    @And("click on CTA button")
    public void clickOnCtaButton() {
        waitAndCheckIsElementClickable(ADD_BANK_DETAILS_CTA_BUTTON);
        waitAndClickOn(ADD_BANK_DETAILS_CTA_BUTTON);
        log.info("Clicked Add Bank Details CTA button");
    }

    @Then("Verify the pop-up")
    public void verifyThePopUp() {
        try {
            log.info("Verifying Bank Details pop-up.");

            waitAndCheckIsElementPresent(BANK_DETAILS_POP_UP_TITLE);

            String actualTitle = waitAndGetText(BANK_DETAILS_POP_UP_TITLE);
            String expectedTitle = "Enter bank account information";

            log.info("Actual pop-up title: " + actualTitle);

            softAssert.assertEquals(
                    actualTitle,
                    expectedTitle,
                    "Bank Details Pop-up title is not matching. Expected: " + expectedTitle + " but found: " + actualTitle
            );

            log.info("Bank Details pop-up title verified successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying pop-up: " + e.getMessage());
            softAssert.fail("Exception while verifying Bank Details pop-up: " + e.getMessage());
        }
    }

}

