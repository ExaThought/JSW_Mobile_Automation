package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.ConfigUtil;
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

import java.time.Duration;

import static com.qapitol.jsw.utils.Hooks.softAssert;

public class BuyAgainSteps extends AbstractWebPage {

    // ===== ELEMENT KEYS =====
    public static final String CCP_HOME_PAGE_LOGIN_BUTTON = "ccpHomePageLoginButton";
    public static final String CCP_HOME_PAGE_LOGIN_VIA_OTP = "ccpHomePageLoginViaOtp";
    public static final String CCP_HOME_PAGE_BUY_AGAIN_LINK = "ccpHomePageBuyAgainLink";
    public static final String CCP_HOME_PAGE_ORDERS_BUTTON = "ccpHomePageOrdersButton";
    public static final String CCP_MY_ORDERS_PAGE_TITLE = "ccpMyOrdersPageTitle";
    public static final String CCP_HOME_PAGE_LEDGER_BUTTON = "ccpHomePageLedgerButton";
    public static final String CCP_LEDGER_PAGE_TITLE = "ccpLedgerPageTitle";
    public static final String CCP_BUY_AGAIN_CHECKBOX = "ccpBuyAgainCheckbox";
    public static final String CCP_BUY_AGAIN_ADD_TO_CART_BUTTON = "ccpBuyAgainAddToCartButton";
    public static final String CCP_BUY_AGAIN_ADD_TO_CART_COUNT = "ccpBuyAgainAddToCartCount";

    public static final String CCP_HOME_PAGE_MATERIAL_TRACKING_BUTTON =
            "ccpHomePageMaterialTrackingButton";

    public static final String CCP_MATERIAL_TRACKING_PAGE_TITLE =
            "ccpMaterialTrackingPageTitle";



    public static final String CCP_HOME_PAGE_PAYMENT_DUE_CLOSE_BUTTON = "ccpHomePagePaymentDueCloseButton";
    public static final String CCP_HOME_PAGE_PAYMENT_DUE_FLOATER = "ccpHomePagePaymentDueFloater";
    public static final String CCP_HOME_PAGE_PAYMENT_DUE_FLOATER_VIEW_DETAILS_BUTTON =
            "ccpHomePagePaymentDueFloaterViewDetailsButton";

    // ===== LOGIN ELEMENTS =====
    public static final String CCP_LOGIN_EMAIL = "ccpHomePageLoginEmail";
    public static final String CCP_LOGIN_PASSWORD = "ccpHomePageLoginPassword";
    public static final String CCP_LOGIN_CTA = "ccpHomePageLoginCTA";

    // ================= STEPS =================

    @When("user open the CCP portal and login with valid credentials {string} {string}")
    public void userOpenTheCCPPortalAndLoginWithValidCredentials(String username, String password) {

        Grid.driver().get(ConfigUtil.getSecretValue("ccpPortalUrl"));
        Grid.driver().manage().window().maximize();

        defocusSearchOverlay();

        // Click Login CTA
        waitAndCheckIsElementVisible(CCP_HOME_PAGE_LOGIN_BUTTON);
        waitAndClickOn(CCP_HOME_PAGE_LOGIN_BUTTON);

        // Login via Email
        waitAndCheckIsElementVisible(CCP_HOME_PAGE_LOGIN_VIA_OTP);
        waitAndClickOn(CCP_HOME_PAGE_LOGIN_VIA_OTP);

        // Enter Email
        waitAndCheckIsElementVisible(CCP_LOGIN_EMAIL);
        waitAndSetText(CCP_LOGIN_EMAIL, username);

        // Continue
        waitAndCheckIsElementVisible(CCP_LOGIN_CTA);
        waitAndClickOn(CCP_LOGIN_CTA);

        // Enter Password
        waitAndCheckIsElementVisible(CCP_LOGIN_PASSWORD);
        waitAndSetText(CCP_LOGIN_PASSWORD, password);

        // Final Login
        waitAndCheckIsElementVisible(CCP_LOGIN_CTA);
        waitAndClickOn(CCP_LOGIN_CTA);

        // Close Payment Due popup if present
        try {
            waitAndCheckIsElementVisible(CCP_HOME_PAGE_PAYMENT_DUE_CLOSE_BUTTON);
            waitAndClickOn(CCP_HOME_PAGE_PAYMENT_DUE_CLOSE_BUTTON);
        } catch (Exception e) {
            log.info("No Payment Due Popup displayed for given user.");
        }
    }

    @And("user close the payment due pop up")
    public void userClosePaymentDuePopUp() {
        try {
            waitAndCheckIsElementVisible(CCP_HOME_PAGE_PAYMENT_DUE_CLOSE_BUTTON);
            waitAndClickOn(CCP_HOME_PAGE_PAYMENT_DUE_CLOSE_BUTTON);
        } catch (Exception e) {
            log.info("No payment due popup displayed");
        }
    }

    @And("user navigate to buy again page")
    public void userNavigateToBuyAgainPage() {

        waitAndCheckIsElementVisible(CCP_HOME_PAGE_BUY_AGAIN_LINK);
        waitAndClickOn(CCP_HOME_PAGE_BUY_AGAIN_LINK);
    }

    @Then("verify payment due floater pop up for Buy-again")
    public void verifyPaymentDueFloaterPopUpForBuyAgain() {

        waitAndCheckIsElementVisible(CCP_HOME_PAGE_PAYMENT_DUE_FLOATER);

        softAssert.assertTrue(
                isElementPresent(CCP_HOME_PAGE_PAYMENT_DUE_FLOATER),
                "Payment due floater is not displayed on Buy Again page"
        );

        softAssert.assertTrue(
                isElementPresent(CCP_HOME_PAGE_PAYMENT_DUE_FLOATER_VIEW_DETAILS_BUTTON),
                "'View details & pay' button is not displayed on Buy Again page"
        );

        softAssert.assertAll();
    }
    public void defocusSearchOverlay() {
        try {
            Grid.driver().findElement(By.tagName("body")).click();
        } catch (Exception ignored) {
        }
    }

    @And("user navigate to order button")
    public void userNavigateToOrderButton() {

        By ordersButton = By.xpath("//div[normalize-space()='Orders']");

        scrollToElement(ordersButton, 10);

        waitAndCheckIsElementClickable(CCP_HOME_PAGE_ORDERS_BUTTON);
        waitAndClickOn(CCP_HOME_PAGE_ORDERS_BUTTON);

    }
    @Then("verify order page title")
    public void verifyOrderPageTitle() {

        waitAndCheckIsElementVisible(CCP_MY_ORDERS_PAGE_TITLE);

        softAssert.assertTrue(
                isElementPresent(CCP_MY_ORDERS_PAGE_TITLE),
                "My Orders page title is not displayed"
        );

        softAssert.assertAll();
    }
    @And("user navigate to ledger button")
    public void userNavigateToLedgerButton() {

        By ledgerButton =
                By.xpath("//div[normalize-space()='Ledger']/ancestor::div[contains(@class,'show-widget-boxes')]");

        scrollToElement(ledgerButton, 10);

        waitAndCheckIsElementClickable(CCP_HOME_PAGE_LEDGER_BUTTON);
        waitAndClickOn(CCP_HOME_PAGE_LEDGER_BUTTON);
    }




    @Then("verify ledger page title")
    public void verifyLedgerPageTitle() {

        waitAndCheckIsElementVisible(CCP_LEDGER_PAGE_TITLE);

        softAssert.assertTrue(
                isElementPresent(CCP_LEDGER_PAGE_TITLE),
                "Ledger page title is not displayed"
        );

        softAssert.assertAll();
    }
    @And("user navigate to Material tracking button")
    public void userNavigateToMaterialTrackingButton() {

        By materialTrackingButton =
                By.xpath("//div[normalize-space()='Track Materials']");

        scrollToElement(materialTrackingButton, 10);

        waitAndCheckIsElementClickable(CCP_HOME_PAGE_MATERIAL_TRACKING_BUTTON);
        waitAndClickOn(CCP_HOME_PAGE_MATERIAL_TRACKING_BUTTON);
    }

    @Then("verify Material tracking page deatils")
    public void verifyMaterialTrackingPageDetails() {

        waitAndCheckIsElementVisible(CCP_MATERIAL_TRACKING_PAGE_TITLE);

        softAssert.assertTrue(
                isElementPresent(CCP_MATERIAL_TRACKING_PAGE_TITLE),
                "Material Tracking page title is not displayed"
        );

        softAssert.assertAll();
    }

    @And("user click on checkbox for Add to cart")
    public void userClickOnCheckboxForAddToCart() {

        // Scroll to checkbox
        By checkbox = By.xpath("//input[@id='buyAgainSelection']");
        scrollToElement(checkbox, 10);

        // Click checkbox using JS (checkbox is often non-interactable)
        WebElement checkboxElement = getWebElement(CCP_BUY_AGAIN_CHECKBOX);
        ((JavascriptExecutor) Grid.driver())
                .executeScript("arguments[0].click();", checkboxElement);

        // Wait for Add to cart button to appear
        waitAndCheckIsElementVisible(CCP_BUY_AGAIN_ADD_TO_CART_BUTTON);

        // Click Add to cart
        waitAndCheckIsElementClickable(CCP_BUY_AGAIN_ADD_TO_CART_BUTTON);
        waitAndClickOn(CCP_BUY_AGAIN_ADD_TO_CART_BUTTON);
    }
    @Then("verify items added to cart from Buy-again")
    public void verifyItemsAddedToCartFromBuyAgain() {

        waitAndCheckIsElementVisible(CCP_BUY_AGAIN_ADD_TO_CART_COUNT);

        String buttonText =
                getWebElement(CCP_BUY_AGAIN_ADD_TO_CART_COUNT).getText();

        softAssert.assertTrue(
                buttonText.contains("(1)"),
                "Item count not updated on Add to cart button"
        );

        softAssert.assertAll();
    }

    public void scrollToElement(By locator, int timeoutInSeconds) {

        WebElement element = new WebDriverWait(Grid.driver(), Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(locator));

        ((JavascriptExecutor) Grid.driver())
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
                        element
                );
    }

}
