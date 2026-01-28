package com.qapitol.jsw.stepdefinition;

import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;

import static com.qapitol.jsw.utils.Hooks.softAssert;



public class CustomerDetailSteps extends AbstractWebPage {

    // ================= LOCATOR KEYS =================

    private static final String CCP_USER_MENU_TESTACCO = "ccpUserMenuTestacco";
    private static final String CCP_USER_MENU_PROFILE_OPTION = "ccpUserMenuProfileOption";

    private static final String PROFILE_NAME_VALUE = "profileNameValue";
    private static final String PROFILE_PHONE_VALUE = "profilePhoneValue";
    private static final String PROFILE_EMAIL_VALUE = "profileEmailValue";


    // ================= NAVIGATION =================

    @And("user clicks on customer account menu")
    public void userClicksOnUserMenu() {

        waitAndCheckIsElementVisible(CCP_USER_MENU_TESTACCO);
        waitAndClickOn(CCP_USER_MENU_TESTACCO);
    }

    @And("user able to navigate  on  customer Profile")
    public void userNavigateOnProfileOption() {

        waitAndCheckIsElementVisible(CCP_USER_MENU_PROFILE_OPTION);
        waitAndClickOn(CCP_USER_MENU_PROFILE_OPTION);
    }


    // ================= PROFILE VALIDATIONS =================

    @Then("user should see Name field in Profile details")
    public void validateNameField() {

        waitAndCheckIsElementVisible(PROFILE_NAME_VALUE);

        String actualName = getText(PROFILE_NAME_VALUE).trim();

        softAssert.assertFalse(actualName.isEmpty(),
                "❌ Name value is empty");

        System.out.println("✅ Name Found: " + actualName);

        softAssert.assertAll();
    }


    @Then("user should see Phone number field in Profile details")
    public void validatePhoneField() {

        waitAndCheckIsElementVisible(PROFILE_PHONE_VALUE);

        String actualPhone = getText(PROFILE_PHONE_VALUE).trim();

        softAssert.assertFalse(actualPhone.isEmpty(),
                "❌ Phone number value is empty");

        System.out.println("✅ Phone Found: " + actualPhone);

        softAssert.assertAll();
    }


    @Then("user should see Email address field in Profile details")
    public void validateEmailField() {

        waitAndCheckIsElementVisible(PROFILE_EMAIL_VALUE);

        String actualEmail = getText(PROFILE_EMAIL_VALUE).trim();

        softAssert.assertFalse(actualEmail.isEmpty(),
                "❌ Email value is empty");

        System.out.println("✅ Email Found: " + actualEmail);

        softAssert.assertAll();
    }
    public String getText(String locatorKey) {

        // Wait until element is visible
        waitAndCheckIsElementVisible(locatorKey);

        // Fetch WebElement using your framework method
        WebElement element = getWebElement(locatorKey);

        // Null check
        if (element == null) {
            throw new RuntimeException("❌ Element not found for key: " + locatorKey);
        }

        // Return element text
        return element.getText();
    }

}
