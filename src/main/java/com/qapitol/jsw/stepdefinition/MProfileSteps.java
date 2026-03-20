package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.LocatorReader;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

import static com.qapitol.jsw.utils.Hooks.softAssert;

public class MProfileSteps extends AbstractWebPage {

        private static final Logger log = LoggerFactory.getLogger(MProfileSteps.class);
        private final LocatorReader locatorReader = new LocatorReader("locators/mprofilesteps.yaml");
        private final JavascriptExecutor js = (JavascriptExecutor) Grid.driver();

        // ── Locator Constants ─────────────────────────────────────────────────────
        private static final String PROFILE_ICON              = "profileIcon";
        private static final String PROFILE_DETAILS_OPTION    = "profileDetailsOption";
        private static final String PROFILE_PAGE_TITLE        = "profilePageTitle";

        // Personal
        private static final String PERSONAL_DETAILS_SECTION  = "personalDetailsSection";
        private static final String PROFILE_NAME_LABEL        = "profileNameLabel";
        private static final String PROFILE_NAME_VALUE        = "profileNameValue";
        private static final String PROFILE_PHONE_LABEL       = "profilePhoneLabel";
        private static final String PROFILE_PHONE_VALUE       = "profilePhoneValue";
        private static final String PROFILE_EMAIL_LABEL       = "profileEmailLabel";
        private static final String PROFILE_EMAIL_VALUE       = "profileEmailValue";

        // Company
        private static final String COMPANY_DETAILS_SECTION   = "companyDetailsSection";
        private static final String COMPANY_NAME_VALUE        = "companyNameValue";
        private static final String COMPANY_ADDRESS_LABEL     = "companyAddressLabel";
        private static final String COMPANY_ADDRESS_VALUE     = "companyAddressValue";
        private static final String COMPANY_GSTIN_LABEL       = "companyGstinLabel";
        private static final String COMPANY_GSTIN_VALUE       = "companyGstinValue";
        private static final String COMPANY_PAN_LABEL         = "companyPanLabel";
        private static final String COMPANY_PAN_VALUE         = "companyPanValue";

        // Banking
        private static final String BANKING_DETAILS_SECTION   = "bankingDetailsSection";
        private static final String BANK_NAME_LABEL           = "bankNameLabel";
        private static final String BANK_NAME_VALUE           = "bankNameValue";
        private static final String BENEFICIARY_NAME_LABEL    = "beneficiaryNameLabel";
        private static final String BENEFICIARY_NAME_VALUE    = "beneficiaryNameValue";
        private static final String ACCOUNT_NUMBER_LABEL      = "accountNumberLabel";
        private static final String ACCOUNT_NUMBER_VALUE      = "accountNumberValue";
        private static final String IFSC_CODE_LABEL           = "ifscCodeLabel";
        private static final String IFSC_CODE_VALUE           = "ifscCodeValue";

        // Tabs
        private static final String PROFILE_DETAILS_TAB       = "profileDetailsTab";
        private static final String ADDRESSES_TAB             = "addressesTab";
        private static final String BANK_ACCOUNT_DETAILS_TAB  = "bankAccountDetailsTab";
        private static final String PAYMENT_REMINDER_CLOSE_BTN = "paymentReminderCloseBtn";


    // ─────────────────────────────────────────────────────────────────────────
        // Navigation
        // ─────────────────────────────────────────────────────────────────────────


    @When("user scrolls to personal details section")
    public void userScrollsToPersonalDetailsSection() {
        scrollToElement(PERSONAL_DETAILS_SECTION);
        log.info("Scrolled to personal details section");
    }

    @When("user scrolls to company details section")
    public void userScrollsToCompanyDetailsSection() {
        scrollToElement(COMPANY_DETAILS_SECTION);
        log.info("Scrolled to company details section");
    }

        @When("user clicks on profile icon in header")
        public void userClicksOnProfileIcon() {
            try {
                sleep(2500); // Wait for session/page to stabilize after login
                // Wait for the icon, then click it using JS as it might be an SVG inside a button
                By by = byFor(PROFILE_ICON);
                WebElement el = new WebDriverWait(Grid.driver(), Duration.ofSeconds(15))
                        .until(ExpectedConditions.presenceOfElementLocated(by));
                
                // Try normal click first
                try {
                    el.click();
                } catch (Exception e) {
                    // Try JS click
                    js.executeScript("arguments[0].click();", el);
                }
                
                sleep(2000);
                log.info("Clicked profile icon in header");
            } catch (Exception e) {
                log.error("Failed to click profile icon in header: {}", e.getMessage());
                // Fallback: try navigating directly to /profile
                log.info("Attempting direct navigation to /profile as fallback...");
                Grid.driver().get("https://qa-ssr.msme.jswone.in/profile");
                sleep(2000);
            }
        }

        @And("user clicks on profile details option")
        public void userClicksOnProfileDetailsOption() {
            scrollToElementAndClick(PROFILE_DETAILS_OPTION);
            sleep(2000);
            log.info("Clicked Profile details option");

        }

        @When("user navigates to profile page")
        public void userNavigatesToProfilePage() {
            Grid.driver().get("https://qa-ssr.msme.jswone.in/my-profile?tab=profile");
            sleep(2000);
            log.info("Navigated to profile page");
        }

    @And("user closes payment reminder popup if visible")
    public void closePaymentReminderPopupIfVisible() {
        try {
            sleep(1500);
            By by = byFor(PAYMENT_REMINDER_CLOSE_BTN);
            List<WebElement> closeBtns = Grid.driver().findElements(by);
            if (!closeBtns.isEmpty() && closeBtns.get(0).isDisplayed()) {
                js.executeScript("arguments[0].click();", closeBtns.get(0));
                sleep(1000);
                log.info("Payment reminder popup closed");
            } else {
                log.info("Payment reminder popup not visible or not displayed — skipping");
            }
        } catch (Exception e) {
            log.warn("Could not close payment reminder popup: {}", e.getMessage());
        }
    }

        // ─────────────────────────────────────────────────────────────────────────
        // Page Load
        // ─────────────────────────────────────────────────────────────────────────

        @Then("profile details page should load successfully")
        public void profileDetailsPageShouldLoad() {
            try {
                WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));
                wait.until(ExpectedConditions.visibilityOfElementLocated(byFor(PROFILE_PAGE_TITLE)));
                String url = Grid.driver().getCurrentUrl();
                softAssert.assertTrue(
                        url.contains("my-profile") || url.contains("profile"),
                        "URL does not contain profile. Actual: " + url);
                log.info("Profile details page loaded. URL: {}", url);
            } catch (Exception e) {
                throw new RuntimeException("Profile details page did not load", e);
            }
        }

        // ─────────────────────────────────────────────────────────────────────────
        // Personal Details
        // ─────────────────────────────────────────────────────────────────────────

        @Then("personal details section should be visible")
        public void personalDetailsSectionShouldBeVisible() {
            waitAndCheckIsElementVisible(PERSONAL_DETAILS_SECTION);
            softAssert.assertTrue(
                    isElementPresent(PERSONAL_DETAILS_SECTION),
                    "Personal details section not visible");
            log.info("Personal details section is visible");
        }

        @And("user name should be displayed")
        public void userNameShouldBeDisplayed() {
            softAssert.assertTrue(
                    isElementPresent(PROFILE_NAME_LABEL),
                    "Name label not visible");
            String name = waitAndGetNonEmptyText(PROFILE_NAME_VALUE, 10);
            softAssert.assertFalse(
                    name == null || name.trim().isEmpty(),
                    "Name value is empty");
            log.info("Name displayed: {}", name);
        }

        @And("phone number should be displayed")
        public void phoneNumberShouldBeDisplayed() {
            waitAndCheckIsElementVisible(PROFILE_PHONE_LABEL);
            softAssert.assertTrue(
                    isElementPresent(PROFILE_PHONE_LABEL),
                    "Phone number label not visible");
            String phone = waitAndGetNonEmptyText(PROFILE_PHONE_VALUE, 10);
            softAssert.assertTrue(
                    phone != null && phone.trim().startsWith("+91"),
                    "Phone number does not start with +91. Actual: " + phone);
            log.info("Phone number displayed: {}", phone);
        }

        @And("email address should be displayed")
        public void emailAddressShouldBeDisplayed() {
            waitAndCheckIsElementVisible(PROFILE_EMAIL_LABEL);
            softAssert.assertTrue(
                    isElementPresent(PROFILE_EMAIL_LABEL),
                    "Email label not visible");
            String email = waitAndGetNonEmptyText(PROFILE_EMAIL_VALUE, 10);
            softAssert.assertTrue(
                    email != null && email.contains("@"),
                    "Email does not contain @. Actual: " + email);
            log.info("Email displayed: {}", email);
        }

        // ─────────────────────────────────────────────────────────────────────────
        // Company Details
        // ─────────────────────────────────────────────────────────────────────────

        @Then("company details section should be visible")
        public void companyDetailsSectionShouldBeVisible() {
            waitAndCheckIsElementVisible(COMPANY_DETAILS_SECTION);
            softAssert.assertTrue(
                    isElementPresent(COMPANY_DETAILS_SECTION),
                    "Company details section not visible");
            log.info("Company details section is visible");
        }

        @And("company name should be displayed")
        public void companyNameShouldBeDisplayed() {
            String name = waitAndGetNonEmptyText(COMPANY_NAME_VALUE, 10);
            softAssert.assertFalse(
                    name == null || name.trim().isEmpty(),
                    "Company name is empty");
            log.info("Company name displayed: {}", name);
        }

        @And("company address should be displayed")
        public void companyAddressShouldBeDisplayed() {
            waitAndCheckIsElementVisible(COMPANY_ADDRESS_LABEL);
            softAssert.assertTrue(
                    isElementPresent(COMPANY_ADDRESS_LABEL),
                    "Company address label not visible");
            String address = waitAndGetNonEmptyText(COMPANY_ADDRESS_VALUE, 10);
            softAssert.assertFalse(
                    address == null || address.trim().isEmpty(),
                    "Company address is empty");
            log.info("Company address displayed: {}", address);
        }

        @And("company gstin should be displayed")
        public void companyGstinShouldBeDisplayed() {
            waitAndCheckIsElementVisible(COMPANY_GSTIN_LABEL);
            String gstin = waitAndGetNonEmptyText(COMPANY_GSTIN_VALUE, 10);
            softAssert.assertFalse(
                    gstin == null || gstin.trim().isEmpty(),
                    "GSTIN value is empty");
            if (gstin != null) {
                softAssert.assertEquals(
                        gstin.trim().length(), 15,
                        "GSTIN length is not 15. Actual: " + gstin);
            }
            log.info("GSTIN displayed: {}", gstin);
        }

        @And("company pan should be displayed")
        public void companyPanShouldBeDisplayed() {
            waitAndCheckIsElementVisible(COMPANY_PAN_LABEL);
            String pan = waitAndGetNonEmptyText(COMPANY_PAN_VALUE, 10);
            softAssert.assertFalse(
                    pan == null || pan.trim().isEmpty(),
                    "PAN value is empty");
            if (pan != null) {
                softAssert.assertEquals(
                        pan.trim().length(), 10,
                        "PAN length is not 10. Actual: " + pan);
            }
            log.info("PAN displayed: {}", pan);
        }

        // ─────────────────────────────────────────────────────────────────────────
        // Banking Details
        // ─────────────────────────────────────────────────────────────────────────

        @And("user scrolls to banking details section")
        public void userScrollsToBankingDetailsSection() {
            scrollToElement(BANKING_DETAILS_SECTION);
            log.info("Scrolled to banking details section");
        }

        @Then("banking details section should be visible")
        public void bankingDetailsSectionShouldBeVisible() {
            waitAndCheckIsElementVisible(BANKING_DETAILS_SECTION);
            softAssert.assertTrue(
                    isElementPresent(BANKING_DETAILS_SECTION),
                    "Banking details section not visible");
            log.info("Banking details section is visible");
        }

        @And("bank name should be displayed")
        public void bankNameShouldBeDisplayed() {
            waitAndCheckIsElementVisible(BANK_NAME_LABEL);
            String bankName = waitAndGetNonEmptyText(BANK_NAME_VALUE, 10);
            softAssert.assertFalse(
                    bankName == null || bankName.trim().isEmpty(),
                    "Bank name is empty");
            log.info("Bank name displayed: {}", bankName);
        }

        @And("beneficiary name should be displayed")
        public void beneficiaryNameShouldBeDisplayed() {
            waitAndCheckIsElementVisible(BENEFICIARY_NAME_LABEL);
            String beneficiary = waitAndGetNonEmptyText(BENEFICIARY_NAME_VALUE, 10);
            softAssert.assertFalse(
                    beneficiary == null || beneficiary.trim().isEmpty(),
                    "Beneficiary name is empty");
            log.info("Beneficiary name displayed: {}", beneficiary);
        }

        @And("account number should be displayed")
        public void accountNumberShouldBeDisplayed() {
            waitAndCheckIsElementVisible(ACCOUNT_NUMBER_LABEL);
            String accountNo = waitAndGetNonEmptyText(ACCOUNT_NUMBER_VALUE, 10);
            softAssert.assertFalse(
                    accountNo == null || accountNo.trim().isEmpty(),
                    "Account number is empty");
            log.info("Account number displayed: {}", accountNo);
        }

        @And("ifsc code should be displayed")
        public void ifscCodeShouldBeDisplayed() {
            waitAndCheckIsElementVisible(IFSC_CODE_LABEL);
            String ifsc = waitAndGetNonEmptyText(IFSC_CODE_VALUE, 10);
            softAssert.assertFalse(
                    ifsc == null || ifsc.trim().isEmpty(),
                    "IFSC code is empty");
            log.info("IFSC code displayed: {}", ifsc);
        }

        // ─────────────────────────────────────────────────────────────────────────
        // Navigation Tabs
        // ─────────────────────────────────────────────────────────────────────────

        @Then("profile details tab should be active")
        public void profileDetailsTabShouldBeActive() {
            softAssert.assertTrue(
                    isElementPresent(PROFILE_DETAILS_TAB),
                    "Profile details tab is not active");
            log.info("Profile details tab is active");
        }

        @And("addresses tab should be visible")
        public void addressesTabShouldBeVisible() {
            softAssert.assertTrue(
                    isElementPresent(ADDRESSES_TAB),
                    "Addresses tab not visible");
            log.info("Addresses tab is visible");
        }

        @And("bank account details tab should be visible")
        public void bankAccountDetailsTabShouldBeVisible() {
            softAssert.assertTrue(
                    isElementPresent(BANK_ACCOUNT_DETAILS_TAB),
                    "Bank account details tab not visible");
            log.info("Bank account details tab is visible");
        }

        // ─────────────────────────────────────────────────────────────────────────
        // Helpers
        // ─────────────────────────────────────────────────────────────────────────

        private void scrollToElement(String locatorKey) {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));
            try {
                By by = byFor(locatorKey);
                // Wait for presence first
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));

                // Try several scrolling methods
                try {
                    js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
                } catch (Exception e1) {
                    try {
                        js.executeScript("arguments[0].scrollIntoView(true);", element);
                    } catch (Exception e2) {
                        js.executeScript("window.scrollTo(0, arguments[0].offsetTop - 100);", element);
                    }
                }

                sleep(1500); // More time to settle
                log.info("Successfully scrolled to: {}", locatorKey);
            } catch (Exception e) {
                log.error("Failed to scroll to element: {} | Error: {}", locatorKey, e.getMessage());
                // Don't throw to allow test to continue if possible, maybe it's already in view
            }
        }

        private void scrollToElementAndClick(String locatorKey) {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));
            try {
                By by = byFor(locatorKey);
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
                js.executeScript("arguments[0].scrollIntoView({block:'center', inline:'nearest'});", element);
                wait.until(ExpectedConditions.visibilityOf(element));
                wait.until(ExpectedConditions.elementToBeClickable(element));
                try {
                    element.click();
                } catch (Exception e) {
                    log.warn("Standard click failed for {}, attempting JS click", locatorKey);
                    js.executeScript("arguments[0].click();", element);
                }
                log.info("Clicked element: {}", locatorKey);
            } catch (Exception e) {
                log.error("Failed to click element: {} | Error: {}", locatorKey, e.getMessage());
                throw new RuntimeException("Element not clickable: " + locatorKey, e);
            }
        }

        private By byFor(String key) {
            String raw = locatorReader.getXpath("elements." + key + ".en_US.web");
            if (raw == null) raw = locatorReader.getXpath(key);
            if (raw != null && raw.startsWith("css="))
                return By.cssSelector(raw.substring(4));
            // Handle pipe-separated fallback XPaths
            if (raw != null && raw.contains(" | "))
                raw = raw.split(" \\| ")[0].trim();
            return By.xpath(raw != null ? raw : "//" + key);
        }

    private String waitAndGetNonEmptyText(String locatorKey, int timeoutInSeconds) {
        try {
            WebElement el = new WebDriverWait(Grid.driver(), Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.presenceOfElementLocated(byFor(locatorKey)));

            // Try getText() first
            String text = el.getText();
            if (text != null && !text.trim().isEmpty()) return text.trim();

            // Try innerText via JS
            text = (String) js.executeScript("return arguments[0].innerText;", el);
            if (text != null && !text.trim().isEmpty()) return text.trim();

            // Try textContent via JS
            text = (String) js.executeScript("return arguments[0].textContent;", el);
            if (text != null && !text.trim().isEmpty()) return text.trim();

            // Try value attribute
            text = el.getAttribute("value");
            if (text != null && !text.trim().isEmpty()) return text.trim();

            return "";
        } catch (Exception e) {
            log.warn("Could not get text for: {} | {}", locatorKey, e.getMessage());
            return "";
        }
    }}
