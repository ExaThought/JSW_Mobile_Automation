package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.apitest.pojos.order.customerDetails.CustomerDetailsResponse;
import com.qapitol.jsw.utils.ApiUtils;
import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;

import static com.qapitol.jsw.utils.ApiUtils.getCustomerDetails;
import static com.qapitol.jsw.utils.Hooks.softAssert;

public class PaymentDueSteps extends AbstractWebPage {

    // ===== CCP HOME PAGE ELEMENT KEYS =====
    public static final String CCP_HOME_PAGE_PAYMENT_DUE_POPUP = "ccpHomePagePaymentDuePopup";
    public static final String CCP_HOME_PAGE_PAYMENT_DUE_TITLE = "ccpHomePagePaymentDueTitle";
    public static final String CCP_HOME_PAGE_PAYMENT_DUE_AMOUNT = "ccpHomePagePaymentDueAmount";
    public static final String CCP_HOME_PAGE_PAYMENT_DUE_VIEW_DETAILS_BUTTON = "ccpHomePagePaymentDueViewDetailsButton";

    // ===== LEDGER / PAYMENT DUE PAGE ELEMENT KEYS =====
    public static final String CCP_LEDGER_PAYMENT_DUE_SECTION = "ccpLedgerPaymentDueSection";
    public static final String CCP_LEDGER_PAYMENT_DUE_AMOUNT = "ccpLedgerPaymentDueAmount";
    public static final String CCP_LEDGER_PAY_NOW_BUTTON = "ccpLedgerPayNowButton";
    public static final String CCP_HOME_PAGE_MY_REQUIREMENTS_LINK = "ccpHomePageMyRequirementsLink";
    public static final String CCP_HOME_PAGE_PAYMENT_DUE_CLOSE_BUTTON = "ccpHomePagePaymentDueCloseButton";
    public static final String CCP_HOME_PAGE_PAYMENT_DUE_FLOATER = "ccpHomePagePaymentDueFloater";
    public static final String CCP_HOME_PAGE_MY_ORDERS_LINK = "ccpHomePageMyOrdersLink";
    public static final String CCP_HOME_PAGE_PAYMENT_DUE_FLOATER_VIEW_DETAILS_BUTTON = "ccpHomePagePaymentDueFloaterViewDetailsButton";
    public static final String CCP_HOME_PAGE_BUY_AGAIN_LINK = "ccpHomePageBuyAgainLink";
    public static final String CCP_HOME_PAGE_CART_LINK = "ccpHomePageCartLink";


    @Then("user verify payment due pop up")
    public void userVerifyPaymentDuePopUp() {

        // Wait for popup
        waitAndCheckIsElementVisible(CCP_HOME_PAGE_PAYMENT_DUE_POPUP);

        softAssert.assertTrue(
                isElementPresent(CCP_HOME_PAGE_PAYMENT_DUE_POPUP),
                "Payment reminder popup is not displayed"
        );

        // Wait for title explicitly
        waitAndCheckIsElementVisible(CCP_HOME_PAGE_PAYMENT_DUE_TITLE);

        String paymentTitle = waitAndGetText(CCP_HOME_PAGE_PAYMENT_DUE_TITLE);
        softAssert.assertEquals(
                paymentTitle.trim(),
                "Payment reminder",
                "Payment reminder title text is incorrect"
        );

        String paymentAmount = waitAndGetText(CCP_HOME_PAGE_PAYMENT_DUE_AMOUNT);
        softAssert.assertTrue(
                paymentAmount.trim().startsWith("₹"),
                "Payment amount does not start with ₹"
        );

        softAssert.assertTrue(
                isElementPresent(CCP_HOME_PAGE_PAYMENT_DUE_VIEW_DETAILS_BUTTON),
                "'View details & pay' button is not displayed"
        );


    }

    @Then("user clicks on view details and pay button")
    public void userClicksOnViewDetailsAndPayButton() {

        waitAndCheckIsElementVisible(CCP_HOME_PAGE_PAYMENT_DUE_VIEW_DETAILS_BUTTON);
        waitAndClickOn(CCP_HOME_PAGE_PAYMENT_DUE_VIEW_DETAILS_BUTTON);
    }

    @Then("user is navigated to payment due page")
    public void userIsNavigatedToPaymentDuePage() {

        waitAndCheckIsElementVisible(CCP_LEDGER_PAYMENT_DUE_SECTION);

        softAssert.assertTrue(
                isElementPresent(CCP_LEDGER_PAYMENT_DUE_SECTION),
                "User is not navigated to Payment Due (Ledger) page"
        );
    }

    @Then("user validates payment due details")
    public void userValidatesPaymentDueDetails() {

        String amount = waitAndGetText(CCP_LEDGER_PAYMENT_DUE_AMOUNT);
        softAssert.assertTrue(
                amount.trim().startsWith("₹"),
                "Payment due amount is incorrect"
        );

        softAssert.assertTrue(
                isElementPresent(CCP_LEDGER_PAY_NOW_BUTTON),
                "'Pay now' button is not displayed on Payment Due page"
        );

    }

    @And("user close payment due pop up")
    public void userClosePaymentDuePopUp() {

        try {
            //Get Customer GSTN
            SessionSteps sessionStep = new SessionSteps();
            String customerGSTN = sessionStep.getCustomerDetail().getGstIn();

            ApiUtils paymentDueResponse = new ApiUtils();
            double paymentDue = paymentDueResponse.getPaymentDueResponse(customerGSTN).getData().getTotalDueAmount();
            if (paymentDue != 0.0) {
                // Wait for the close button and click it
                try {
                    //Check payment due is present for given account
                    waitAndCheckIsElementVisible(CCP_HOME_PAGE_PAYMENT_DUE_CLOSE_BUTTON);
                    waitAndCheckIsElementClickable(CCP_HOME_PAGE_PAYMENT_DUE_CLOSE_BUTTON);
                    waitAndClickOn(CCP_HOME_PAGE_PAYMENT_DUE_CLOSE_BUTTON);
                    log.info("payment due pop up successfully closed for customer with payment due:" + customerGSTN);
                } catch (Exception e) {
                    log.info("payment due pop up is not closed for customer with payment due:" + customerGSTN);
                    Allure.addAttachment("Payment Due Pop Up Not Closed", "Payment due pop up is not closed for customer with payment due:" + customerGSTN);
                }
            } else {
                if (isElementPresent(CCP_HOME_PAGE_PAYMENT_DUE_CLOSE_BUTTON)) {
                    log.info("payment due pop up is present for customer with no payment due:" + customerGSTN);
                    Allure.addAttachment("Payment Due Pop Up Present", "Payment due pop up is present for customer with no payment due:" + customerGSTN);
                }
            }
        } catch (Exception e) {
            log.info("Payment due API or something is failing, please do check it");
        }
    }

    @And("user navigate to my reqirement")
    public void userNavigateToMyRequirement() {
        // Wait for the link to be visible and click
        waitAndCheckIsElementVisible(CCP_HOME_PAGE_MY_REQUIREMENTS_LINK);
        waitAndClickOn(CCP_HOME_PAGE_MY_REQUIREMENTS_LINK);
    }

    @Then("verify payment due floater pop up")
    public void verifyPaymentDuePopUp() {
        // Wait for floater
        waitAndCheckIsElementVisible(CCP_HOME_PAGE_PAYMENT_DUE_FLOATER);

        softAssert.assertTrue(
                isElementPresent(CCP_HOME_PAGE_PAYMENT_DUE_FLOATER),
                "Payment reminder floater is not displayed"
        );

        softAssert.assertTrue(
                isElementPresent(CCP_HOME_PAGE_PAYMENT_DUE_FLOATER_VIEW_DETAILS_BUTTON),
                "'View details & pay' button is not displayed in floater");
    }

    @And("user navigate to buy again")
    public void userNavigateToBuyAgain() {

        // Wait for the link to be visible and click
        waitAndCheckIsElementVisible(CCP_HOME_PAGE_BUY_AGAIN_LINK);
        waitAndClickOn(CCP_HOME_PAGE_BUY_AGAIN_LINK);
    }

    @And("user navigate to cart details")
    public void userNavigateToCart() {

        // Wait for cart link to be visible and click
        waitAndCheckIsElementVisible(CCP_HOME_PAGE_CART_LINK);
        waitAndClickOn(CCP_HOME_PAGE_CART_LINK);
    }

    @And("user navigate to my orders")
    public void userNavigateToMyOrders() {
        // Wait for My Orders link to be visible and click
        waitAndCheckIsElementVisible(CCP_HOME_PAGE_MY_ORDERS_LINK);
        waitAndClickOn(CCP_HOME_PAGE_MY_ORDERS_LINK);
    }

    @Then("verify no payment due pop up on my orders page")
    public void verifyNoPaymentDuePopUpOnMyOrdersPage() {
        // Use soft assert to check popup is not present
        softAssert.assertFalse(
                isElementPresent(CCP_HOME_PAGE_PAYMENT_DUE_POPUP),
                "Payment reminder popup should NOT be displayed on My Orders page"
        );

        softAssert.assertFalse(
                isElementPresent(CCP_HOME_PAGE_PAYMENT_DUE_VIEW_DETAILS_BUTTON),
                "'View details & pay' button should NOT be displayed on My Orders page"
        );

        softAssert.assertFalse(
                isElementPresent(CCP_HOME_PAGE_PAYMENT_DUE_FLOATER),
                "Payment reminder floater should NOT be displayed on My Orders page"
        );


    }


}

