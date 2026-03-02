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
import org.testng.Assert;

import java.time.Duration;

import static com.qapitol.jsw.utils.Hooks.softAssert;

public class BankDetailSteps extends AbstractWebPage {

    // ✅ Load YAML File
    LocatorReader locatorReader =
            new LocatorReader("locators/bankdetailsteps.yaml");

    WebDriverWait wait =
            new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));


    // ================= LOCATOR KEYS =================

    private static final String CCP_USER_MENU_TESTACCO = "ccpUserMenuTestacco";
    private static final String CCP_USER_MENU_PROFILE_OPTION = "ccpUserMenuProfileOption";

    private static final String BANK_ACCOUNT_DETAILS_TAB = "bankAccountDetailsTab";

    private static final String GSTIN_NAME_VALUE = "gstinNameValue";
    private static final String ACCOUNT_HOLDER_NAME_VALUE = "accountHolderNameValue";
    private static final String BANK_ACCOUNT_NUMBER_VALUE = "bankAccountNumberValue";
    private static final String IFSC_CODE_VALUE = "ifscCodeValue";


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
        waitAndCheckIsElementClickable(BANK_ACCOUNT_DETAILS_TAB);
        clickOn(BANK_ACCOUNT_DETAILS_TAB);
    }


    // ================= BANK VALIDATIONS =================

    @Then("user should see GSTIN Name in Bank details")
    public void validateGSTINNameInBankDetails() {

        String gstinName = getTextValue(GSTIN_NAME_VALUE);

        softAssert.assertFalse(gstinName.isEmpty(),
                "❌ GSTIN Name is empty");

        System.out.println("✅ GSTIN Name Found: " + gstinName);
    }

    @Then("user should see Account holder name in Bank details")
    public void validateAccountHolderNameInBankDetails() {

        String holderName = getTextValue(ACCOUNT_HOLDER_NAME_VALUE);

        softAssert.assertFalse(holderName.isEmpty(),
                "❌ Account Holder Name is empty");

        System.out.println("✅ Account Holder Name Found: " + holderName);
    }

    @Then("user should see Bank account number in Bank details")
    public void validateBankAccountNumberInBankDetails() {

        String accountNumber = getTextValue(BANK_ACCOUNT_NUMBER_VALUE);

        softAssert.assertFalse(accountNumber.isEmpty(),
                "❌ Bank Account Number is empty");

        System.out.println("✅ Bank Account Number Found: " + accountNumber);
    }

    @Then("user should see IFSC code in Bank details")
    public void validateIFSCCodeInBankDetails() {

        String ifscCode = getTextValue(IFSC_CODE_VALUE);

        softAssert.assertFalse(ifscCode.isEmpty(),
                "❌ IFSC Code is empty");

        System.out.println("✅ IFSC Code Found: " + ifscCode);


    }

    @When("user clicks on Replace bank account option")
    public void userClicksOnReplaceBankAccount() {

        clickOn("replaceBankAccountButton");

        System.out.println("✅ Clicked on Replace bank account");
    }

    @Then("user should see Enter bank account information page")
    public void userShouldSeeEnterBankAccountInformationPage() {

        String headingText = getTextValue("enterBankAccountHeading");

        Assert.assertFalse(
                headingText.isEmpty(),
                "❌ Enter bank account information heading not visible"
        );

        System.out.println("✅ Heading Found: " + headingText);
    }
}

