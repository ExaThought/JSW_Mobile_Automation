package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.LocatorReader;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.data.Data;
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

    LocatorReader locatorReader =
            new LocatorReader("locators/customerdetailsteps.yaml");

    WebDriverWait wait =
            new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));


    private static final String CCP_USER_MENU_TESTACCO = "ccpUserMenuTestacco";
    private static final String CCP_USER_MENU_PROFILE_OPTION = "ccpUserMenuProfileOption";

    private static final String PROFILE_NAME_VALUE = "profileNameValue";
    private static final String PROFILE_PHONE_VALUE = "profilePhoneValue";
    private static final String PROFILE_EMAIL_VALUE = "profileEmailValue";
    private static final String COMPANY_NAME_VALUE = "companyNameValue";
    private static final String COMPANY_ADDRESS_VALUE = "companyAddressValue";
    private static final String COMPANY_GSTIN_VALUE = "companyGSTINValue";
    private static final String COMPANY_PAN_VALUE = "companyPANValue";


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



    @And("user clicks on customer account menu")
    public void userClicksOnUserMenu() {
        clickOn(CCP_USER_MENU_TESTACCO);
    }

    @And("user able to navigate  on  customer Profile")
    public void userNavigateOnProfileOption() {
        clickOn(CCP_USER_MENU_PROFILE_OPTION);
    }



    @Then("validate Name field value in Profile details")
    public void validateNameField() {

        String actualName = getTextValue(PROFILE_NAME_VALUE);
        log.info("Successfully retrieved Name from profile: " + actualName);
        String expectedName = Data.get().get("name").toString();
        softAssert.assertEquals(actualName, expectedName,
                "Name mismatch! Expected: " + expectedName + ", but found: " + actualName +" In profile details.");
    }

    @Then("validate Phone number field value in Profile details")
    public void validatePhoneField() {

        String actualPhone = getTextValue(PROFILE_PHONE_VALUE);
        log.info("Successfully retrieved Phone number from profile: " + actualPhone);
        String expectedPhone = Data.get().get("phone_number").toString();
        softAssert.assertEquals(actualPhone, expectedPhone,
                "Phone number mismatch! Expected: " + expectedPhone + ", but found: " + actualPhone + " In profile details.");
    }

    @Then("validate Email address field value in Profile details")
    public void validateEmailField() {

        String actualEmail = getTextValue(PROFILE_EMAIL_VALUE);
        log.info("Successfully retrieved Email address from profile: " + actualEmail);
        String expectedEmail = Data.get().get("email_address").toString();
        softAssert.assertEquals(actualEmail, expectedEmail,
                "Email address mismatch! Expected: " + expectedEmail + ", but found: " + actualEmail + " In profile details.");
    }

    @Then("validate Company Name field value in Company details")
    public void validateCompanyName() {

        String companyName = getTextValue(COMPANY_NAME_VALUE);
        log.info("Successfully retrieved Company Name from profile: " + companyName);
        String expectedCompanyName = Data.get().get("company_name").toString();
        softAssert.assertEquals(companyName, expectedCompanyName,
                "Company Name mismatch! Expected: " + expectedCompanyName + ", but found: " + companyName + " In Company details.");
    }


    @Then("validate Company address field value in Company details")
    public void validateCompanyAddress() {

        String address = getTextValue(COMPANY_ADDRESS_VALUE);
        log.info("Successfully retrieved Company Address from profile: " + address);
        String expectedAddress = Data.get().get("address").toString();
        softAssert.assertEquals(address, expectedAddress,
                "Company Address mismatch! Expected: " + expectedAddress + ", but found: " + address + " In Company details.");
    }


    @Then("validate GSTIN field value in Company details")
    public void validateCompanyGSTIN() {

        String gstin = getTextValue(COMPANY_GSTIN_VALUE);
        log.info("Successfully retrieved GSTIN from profile: " + gstin);
        String expectedGSTIN = Data.get().get("gstin").toString();
        softAssert.assertEquals(gstin, expectedGSTIN,
                "GSTIN mismatch! Expected: " + expectedGSTIN + ", but found: " + gstin + " In Company details.");
    }


    @Then("validate PAN field value in Company details")
    public void validateCompanyPAN() {

        String pan = getTextValue(COMPANY_PAN_VALUE);
        log.info("Successfully retrieved PAN from profile: " + pan);
        String expectedPAN = Data.get().get("pan").toString();
        softAssert.assertEquals(pan, expectedPAN,
                "PAN mismatch! Expected: " + expectedPAN + ", but found: " + pan + " In Company details.");
    }

}

