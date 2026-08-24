package com.qapitol.jsw.stepdefinition;

import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.Then;

import static com.qapitol.jsw.utils.Hooks.softAssert;

public class LedgerSteps extends AbstractWebPage {

    private static final String PAYMENT_DUE_LABEL = "paymentDueLabel";
    private static final String PAYMENT_DUE_AMOUNT = "paymentDueAmount";
    private static final String PAY_NOW_BUTTON = "payNowButton";
    private static final String WARNING_ICON = "warningIcon";

    private static final String OVERDUE_MESSAGE = "overdueMessage";

    private static final String TRANSACTING_ENTITY_DROPDOWN = "transactingEntityDropdown";

    private static final String FINANCIAL_DOCUMENTS_BUTTON = "financialDocumentsButton";
    private static final String PAYMENT_INSTRUCTIONS_BUTTON = "paymentInstructionsButton";
    private static final String HELP_SUPPORT_BUTTON = "helpSupportButton";

    @Then("user should see payment due label")
    public void validatePaymentDueLabel() {
        String paymentDueLabel = waitAndGetText(PAYMENT_DUE_LABEL);
        softAssert.assertFalse(paymentDueLabel.isEmpty(), "Payment due label is empty");
    }

    @Then("user should see payment due amount")
    public void validatePaymentDueAmount() {
        String paymentAmount = waitAndGetText(PAYMENT_DUE_AMOUNT);
        softAssert.assertFalse(paymentAmount.isEmpty(), "Payment due amount is empty");
    }

    @Then("user should see pay now button")
    public void validatePayNowButton() {
        softAssert.assertTrue(isElementPresent(PAY_NOW_BUTTON), "Pay Now button is not visible");
    }

    @Then("user should see warning icon")
    public void validateWarningIcon() {
        softAssert.assertTrue(isElementPresent(WARNING_ICON), "Warning icon is not visible");
    }

    @Then("user should see overdue invoice warning message")
    public void validateOverdueInvoiceMessage() {
        String message = waitAndGetText(OVERDUE_MESSAGE);
        softAssert.assertFalse(message.isEmpty(), "Overdue invoice warning message is empty");
    }

    @Then("user should see transacting entity dropdown")
    public void validateTransactingEntityDropdown() {
        softAssert.assertTrue(isElementPresent(TRANSACTING_ENTITY_DROPDOWN), "Transacting entity dropdown is not visible");
    }

    @Then("user should see financial documents button")
    public void validateFinancialDocumentsButton() {
        softAssert.assertTrue(isElementPresent(FINANCIAL_DOCUMENTS_BUTTON), "Financial documents button not visible");
    }

    @Then("user should see payment instructions button")
    public void validatePaymentInstructionsButton() {
        softAssert.assertTrue(isElementPresent(PAYMENT_INSTRUCTIONS_BUTTON), "Payment instructions button not visible");
    }

    @Then("user should see help and support button")
    public void validateHelpSupportButton() {
        softAssert.assertTrue(isElementPresent(HELP_SUPPORT_BUTTON), "Help & Support button not visible");
    }




}