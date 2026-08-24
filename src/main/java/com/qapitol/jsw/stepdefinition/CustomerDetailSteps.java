package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.ConfigUtil;
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
import java.util.regex.Pattern;

import static com.qapitol.jsw.utils.Hooks.softAssert;

public class CustomerDetailSteps extends AbstractWebPage {

    private final LocatorReader locatorReader =
            new LocatorReader("locators/customerdetailsteps.yaml");

    private static final int ELEMENT_TIMEOUT_SECONDS = 15;

    private static final String CCP_USER_MENU_TESTACCO = "ccpUserMenuTestacco";
    private static final String CCP_USER_MENU_PROFILE_OPTION = "ccpUserMenuProfileOption";

    private static final String PERSONAL_DETAILS_SECTION = "personalDetailsSection";
    private static final String COMPANY_DETAILS_SECTION = "companyDetailsSection";

    private static final String PROFILE_NAME_VALUE = "profileNameValue";
    private static final String PROFILE_PHONE_VALUE = "profilePhoneValue";
    private static final String PROFILE_EMAIL_VALUE = "profileEmailValue";
    private static final String COMPANY_NAME_VALUE = "companyNameValue";
    private static final String COMPANY_ADDRESS_VALUE = "companyAddressValue";
    private static final String COMPANY_GSTIN_VALUE = "companyGSTINValue";
    private static final String COMPANY_PAN_VALUE = "companyPANValue";

    /** 15 chars: 2 state digits, the 10-char PAN, 1 entity digit, a literal Z, 1 checksum char. */
    private static final Pattern GSTIN_PATTERN =
            Pattern.compile("^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z][0-9A-Z]Z[0-9A-Z]$");
    /** 10 chars: 5 letters, 4 digits, 1 letter. */
    private static final Pattern PAN_PATTERN = Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[A-Za-z]{2,}$");

    private WebDriverWait elementWait() {
        return new WebDriverWait(Grid.driver(), Duration.ofSeconds(ELEMENT_TIMEOUT_SECONDS));
    }

    private By by(String key) {
        return By.xpath(locatorReader.getXpath("elements." + key + ".en_US.web"));
    }

    public WebElement getElement(String key) {
        return elementWait().until(ExpectedConditions.visibilityOfElementLocated(by(key)));
    }

    public void clickOn(String key) {
        getElement(key).click();
    }

    public String getTextValue(String key) {
        return getElement(key).getText().trim();
    }

    private String safeText(String key) {
        try {
            return getTextValue(key);
        } catch (Exception e) {
            log.info("Profile field not readable for locator key '" + key + "': " + e.getMessage());
            return null;
        }
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    /** Expected value from data/&lt;env&gt;/testdata/customerDetails.yaml, or null when absent. */
    private String expected(String dataKey) {
        try {
            Object value = Data.get().get(dataKey);
            return value == null ? null : String.valueOf(value).trim();
        } catch (Exception e) {
            log.info("No test data available for key '" + dataKey + "': " + e.getMessage());
            return null;
        }
    }

    private void assertField(String locatorKey, String dataKey, String label) {
        String actual = safeText(locatorKey);
        softAssert.assertFalse(isBlank(actual),
                label + " is not displayed on the profile page");
        if (isBlank(actual)) {
            return;
        }
        log.info("Profile " + label + " = '" + actual + "'");

        String expected = expected(dataKey);
        if (isBlank(expected)) {
            // Presence-only is still a real check; say so rather than implying a comparison ran.
            log.info("No expected " + label + " in the test data (key '" + dataKey
                    + "') - asserted presence only");
            return;
        }
        softAssert.assertEquals(actual, expected,
                label + " does not match the customer record in customerDetails.yaml");
    }

    // ------------------- Navigation -------------------

    @And("user clicks on customer account menu")
    public void userClicksOnUserMenu() {
        waitAndCheckIsElementClickable(CCP_USER_MENU_TESTACCO);
        waitAndClickOn(CCP_USER_MENU_TESTACCO);
    }

    @And("user able to navigate  on  customer Profile")
    public void userNavigateOnProfileOption() {
        clickOn(CCP_USER_MENU_PROFILE_OPTION);
    }

    @Then("the customer profile page should be displayed")
    public void theCustomerProfilePageShouldBeDisplayed() {
        String currentUrl = Grid.driver().getCurrentUrl();
        String configured = ConfigUtil.getSecretValue("profileUrl");
        log.info("Profile page URL: " + currentUrl + " (configured: " + configured + ")");
        softAssert.assertTrue(currentUrl != null && currentUrl.toLowerCase().contains("my-profile"),
                "Profile page did not open. Expected a URL containing 'my-profile' but was: " + currentUrl);
    }

    @Then("the {string} section should be displayed on the profile page")
    public void theSectionShouldBeDisplayedOnTheProfilePage(String sectionName) {
        String key = "Company details".equalsIgnoreCase(sectionName)
                ? COMPANY_DETAILS_SECTION : PERSONAL_DETAILS_SECTION;
        softAssert.assertFalse(isBlank(safeText(key)),
                "'" + sectionName + "' section is not displayed on the profile page");
    }

    // ------------------- Personal details -------------------

    @Then("validate Name field value in Profile details")
    public void validateNameField() {
        assertField(PROFILE_NAME_VALUE, "name", "Name");
    }

    @Then("validate Phone number field value in Profile details")
    public void validatePhoneField() {
        assertField(PROFILE_PHONE_VALUE, "phone_number", "Phone number");
    }

    @Then("validate Email address field value in Profile details")
    public void validateEmailField() {
        assertField(PROFILE_EMAIL_VALUE, "email_address", "Email address");
    }

    @Then("the displayed email address should be well formed")
    public void theDisplayedEmailAddressShouldBeWellFormed() {
        String email = safeText(PROFILE_EMAIL_VALUE);
        if (isBlank(email)) {
            softAssert.fail("Email address is not displayed, so its format cannot be checked");
            return;
        }
        softAssert.assertTrue(EMAIL_PATTERN.matcher(email).matches(),
                "Email address '" + email + "' is not a well formed address");
    }

    @Then("the displayed phone number should be well formed")
    public void theDisplayedPhoneNumberShouldBeWellFormed() {
        String phone = safeText(PROFILE_PHONE_VALUE);
        if (isBlank(phone)) {
            softAssert.fail("Phone number is not displayed, so its format cannot be checked");
            return;
        }
        String digits = phone.replaceAll("\\D", "");
        softAssert.assertTrue(digits.length() >= 10,
                "Phone number '" + phone + "' has only " + digits.length()
                        + " digits - an Indian mobile number needs at least 10");
    }

    // ------------------- Company details -------------------

    @Then("validate Company Name field value in Company details")
    public void validateCompanyName() {
        assertField(COMPANY_NAME_VALUE, "company_name", "Company name");
    }

    @Then("validate Company address field value in Company details")
    public void validateCompanyAddress() {
        assertField(COMPANY_ADDRESS_VALUE, "address", "Company address");
    }

    @Then("validate GSTIN field value in Company details")
    public void validateCompanyGSTIN() {
        assertField(COMPANY_GSTIN_VALUE, "gstin", "GSTIN");
    }

    @Then("validate PAN field value in Company details")
    public void validateCompanyPAN() {
        assertField(COMPANY_PAN_VALUE, "pan", "PAN");
    }

    @Then("the displayed GSTIN should be a valid GST number")
    public void theDisplayedGstinShouldBeValid() {
        String gstin = safeText(COMPANY_GSTIN_VALUE);
        if (isBlank(gstin)) {
            softAssert.fail("GSTIN is not displayed, so its format cannot be checked");
            return;
        }
        softAssert.assertEquals(gstin.length(), 15,
                "GSTIN '" + gstin + "' should be 15 characters");
        softAssert.assertTrue(GSTIN_PATTERN.matcher(gstin).matches(),
                "GSTIN '" + gstin + "' does not match the GST format"
                        + " (2 state digits, 10-char PAN, entity digit, 'Z', checksum)");
    }

    @Then("the displayed PAN should be a valid PAN")
    public void theDisplayedPanShouldBeValid() {
        String pan = safeText(COMPANY_PAN_VALUE);
        if (isBlank(pan)) {
            softAssert.fail("PAN is not displayed, so its format cannot be checked");
            return;
        }
        softAssert.assertEquals(pan.length(), 10, "PAN '" + pan + "' should be 10 characters");
        softAssert.assertTrue(PAN_PATTERN.matcher(pan).matches(),
                "PAN '" + pan + "' does not match the PAN format (5 letters, 4 digits, 1 letter)");
    }

    @Then("the displayed GSTIN should contain the displayed PAN")
    public void theDisplayedGstinShouldContainTheDisplayedPan() {
        String gstin = safeText(COMPANY_GSTIN_VALUE);
        String pan = safeText(COMPANY_PAN_VALUE);
        if (isBlank(gstin) || isBlank(pan)) {
            softAssert.fail("GSTIN and PAN must both be displayed to cross-check them"
                    + " (GSTIN='" + gstin + "', PAN='" + pan + "')");
            return;
        }
        if (gstin.length() < 12) {
            softAssert.fail("GSTIN '" + gstin + "' is too short to contain a PAN");
            return;
        }
        softAssert.assertEquals(gstin.substring(2, 12), pan,
                "GSTIN '" + gstin + "' embeds PAN '" + gstin.substring(2, 12)
                        + "' but the PAN field shows '" + pan + "' - these must be the same company");
    }
}
