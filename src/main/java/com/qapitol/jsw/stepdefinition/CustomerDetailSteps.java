package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.LocatorReader;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.qapitol.jsw.utils.Hooks.softAssert;

public class CustomerDetailSteps extends AbstractWebPage {

    // ✅ Load YAML manually
    LocatorReader locatorReader =
            new LocatorReader("locators/customerdetailsteps.yaml");

    WebDriverWait wait =
            new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));


    // ================= LOCATOR KEYS =================
    private static final String CCP_USER_MENU_TESTACCO = "ccpUserMenuTestacco";
    private static final String CCP_USER_MENU_PROFILE_OPTION = "ccpUserMenuProfileOption";

    private static final String PROFILE_NAME_VALUE = "profileNameValue";
    private static final String PROFILE_PHONE_VALUE = "profilePhoneValue";
    private static final String PROFILE_EMAIL_VALUE = "profileEmailValue";
    private static final String COMPANY_NAME_VALUE = "companyNameValue";
    private static final String COMPANY_ADDRESS_VALUE = "companyAddressValue";
    private static final String COMPANY_GSTIN_VALUE = "companyGSTINValue";
    private static final String COMPANY_PAN_VALUE = "companyPANValue";


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

    @And("user clicks on customer account menu")
    public void userClicksOnUserMenu() {
        clickOn(CCP_USER_MENU_TESTACCO);
    }

    @And("user able to navigate  on  customer Profile")
    public void userNavigateOnProfileOption() {
        clickOn(CCP_USER_MENU_PROFILE_OPTION);
    }


    // ================= PROFILE VALIDATIONS =================

    @Then("user should see Name field in Profile details")
    public void validateNameField() {

        String actualName = getTextValue(PROFILE_NAME_VALUE);

        softAssert.assertFalse(actualName.isEmpty(),
                "❌ Name value is empty");

        System.out.println("✅ Name Found: " + actualName);


    }

    @Then("user should see Phone number field in Profile details")
    public void validatePhoneField() {

        String actualPhone = getTextValue(PROFILE_PHONE_VALUE);

        softAssert.assertFalse(actualPhone.isEmpty(),
                "❌ Phone number value is empty");

        System.out.println("✅ Phone Found: " + actualPhone);


    }

    @Then("user should see Email address field in Profile details")
    public void validateEmailField() {

        String actualEmail = getTextValue(PROFILE_EMAIL_VALUE);

        softAssert.assertFalse(actualEmail.isEmpty(),
                "❌ Email value is empty");

        System.out.println("✅ Email Found: " + actualEmail);


    }
    // ================= COMPANY DETAILS TEST CASES =================

    @Then("user should see Company Name in Company details")
    public void validateCompanyName() {

        String companyName = getTextValue(COMPANY_NAME_VALUE);

        softAssert.assertFalse(companyName.isEmpty(),
                "❌ Company Name is empty");

        System.out.println("✅ Company Name Found: " + companyName);


    }


    @Then("user should see Address in Company details")
    public void validateCompanyAddress() {

        String address = getTextValue(COMPANY_ADDRESS_VALUE);

        softAssert.assertFalse(address.isEmpty(),
                "❌ Address is empty");

        System.out.println("✅ Address Found: " + address);

        softAssert.assertAll();
    }


    @Then("user should see GSTIN in Company details")
    public void validateCompanyGSTIN() {

        String gstin = getTextValue(COMPANY_GSTIN_VALUE);

        softAssert.assertFalse(gstin.isEmpty(),
                "❌ GSTIN is empty");

        System.out.println("✅ GSTIN Found: " + gstin);


    }


    @Then("user should see PAN in Company details")
    public void validateCompanyPAN() {

        String pan = getTextValue(COMPANY_PAN_VALUE);

        softAssert.assertFalse(pan.isEmpty(),
                "❌ PAN is empty");

        System.out.println("✅ PAN Found: " + pan);


    }

}

