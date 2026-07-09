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
//        clickOn(CCP_USER_MENU_TESTACCO);
        waitAndCheckIsElementClickable(CCP_USER_MENU_TESTACCO);
        waitAndClickOn(CCP_USER_MENU_TESTACCO);
    }

    @And("user able to navigate  on  customer Profile")
    public void userNavigateOnProfileOption() {
        clickOn(CCP_USER_MENU_PROFILE_OPTION);
    }



    @Then("validate Name field value in Profile details")
    public void validateNameField() {

        sleep(1000);
        String actualName = getTextValue(PROFILE_NAME_VALUE);
        log.info("Successfully retrieved Name from profile: " + actualName);
        softAssert.assertTrue(actualName!=null);
    }

    @Then("validate Phone number field value in Profile details")
    public void validatePhoneField() {
        sleep(1000);
        String actualPhone = getTextValue(PROFILE_PHONE_VALUE);
        log.info("Successfully retrieved Phone number from profile: " + actualPhone);
       softAssert.assertTrue(actualPhone!=null);
    }

    @Then("validate Email address field value in Profile details")
    public void validateEmailField() {
        sleep(1000);
        String actualEmail = getTextValue(PROFILE_EMAIL_VALUE);
        log.info("Successfully retrieved Email address from profile: " + actualEmail);
        softAssert.assertTrue(actualEmail!=null);
    }

    @Then("validate Company Name field value in Company details")
    public void validateCompanyName() {
        sleep(1000);
        String companyName = getTextValue(COMPANY_NAME_VALUE);
        log.info("Successfully retrieved Company Name from profile: " + companyName);
        softAssert.assertTrue(companyName!=null);
    }


    @Then("validate Company address field value in Company details")
    public void validateCompanyAddress() {
        sleep(1000);
        String address = getTextValue(COMPANY_ADDRESS_VALUE);
        log.info("Successfully retrieved Company Address from profile: " + address);
         softAssert.assertTrue(address!=null);
    }


    @Then("validate GSTIN field value in Company details")
    public void validateCompanyGSTIN() {
        sleep(1000);
        String gstin = getTextValue(COMPANY_GSTIN_VALUE);
        log.info("Successfully retrieved GSTIN from profile: " + gstin);
       softAssert.assertTrue(gstin!=null);
    }


    @Then("validate PAN field value in Company details")
    public void validateCompanyPAN() {
        sleep(1000);
        String pan = getTextValue(COMPANY_PAN_VALUE);
        log.info("Successfully retrieved PAN from profile: " + pan);
        softAssert.assertTrue(pan!=null);
    }

}

