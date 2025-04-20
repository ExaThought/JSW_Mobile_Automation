package com.qapitol.jsw.webbdd.stepdefinition;

import com.qapitol.jsw.utils.AllureCustomLogReporter;
import com.qapitol.sauron.logging.SauronLogger;
import com.qapitol.sauron.logging.SimpleLogger;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.Map;

import static com.qapitol.jsw.utils.CurrencyUtils.formatAmountWithRupeeAndCommas;
import static com.qapitol.jsw.utils.CurrencyUtils.formatLedgerBalance;

public class CustomerLedgerSteps extends BaseSteps {
    private final SimpleLogger log = SauronLogger.getLogger();

    public static final String ZERO_WITH_RUPEE = "₹0.00";


    public static final String CUSTOMER_LEDGER_PATH ="/#Page:Customer_ledger_index";

    @Then("Navigate to customer ledger in ledger statement")
    public void navigateToCustomerLedgerInLedgerStatement(){
        zohoCreatorNavigation(CUSTOMER_LEDGER_PATH);
    }

    @Then("Search with customer's GSTIN {string}")
    public void searchWithCustomerSGSTIN(String gstinNo) {
        clickOnDropdownAndEnterGSTIN(gstinNo);
        ledgerDetails();
    }

    @Then("Initiate the early knock off and confirm")
    public void initiateTheEarlyKnockOffAndConfirm() {
        initiateKnockOffAndConfirm();
    }
    @Then("Validate AR not due and Usable ledger balance before and knock off")
    public void validateARNotDueAndUsableLedgerBalanceBeforeAndKnockOff() {
        validateLedgerDetailsAfterEarlyKnockOff();
    }

    public void clickOnDropdownAndEnterGSTIN(String gstinNo) {
        sleep(2000);
        tap("selectDropDownBtn");
//        sleep(2000);
        setTextAndClickEnter("selectInputTextBox",gstinNo);
        log.info("Clicked on dropdown and entered customer's GSTIN No "+gstinNo);
        AllureCustomLogReporter.addTestStepLog("Clicked on dropdown and entered customer's GSTIN No" +gstinNo);

    }

    public void ledgerDetails() {
        sleep(2000);
//        String text = locateElement("testPath").getText();
        waitAndCheckIsElementPresent("testPath");
        WebElement element = locateElement("testPath");
        String value=element.getText();
        String[] parts = splitText(value);
        String customerName = getCustomerName(parts);
        String gstin = getGSTIN(parts);
        log.info("Customer's name is:" +customerName);
        log.info("Customer's GSTIN is:"+gstin);
    }
    public String[] splitText(String text) {
        return text.split("\\|");
    }
    public String getCustomerName(String[] parts) {
        if (parts.length == 2) {
            return parts[0].trim();
        } else {
            return "Invalid input format";
        }
    }
    public String getGSTIN(String[] parts) {
        if (parts.length == 2) {
            return parts[1].trim();
        } else {
            return "Invalid input format";
        }
    }
    public void initiateKnockOffAndConfirm() {
        tap("earlyKnockOffBtn");
        sleep(2000);
        tap("confirmBtn");
        sleep(2000);
        pageRefresh();
    }
    public void validateLedgerDetailsAfterEarlyKnockOff() {
        SoftAssert softAssert = new SoftAssert();
        String ARNotDueAmount=waitAndGetText("getAmountXpath","AR not due:");
        String usableLedgerBalance=waitAndGetText("getAmountXpath","Usable ledger balance");
        log.info("Before early knock off , AR not due amount was:" + ARNotDueAmount);
        double ARNotDueAmountNew = formatLedgerBalance(ARNotDueAmount);
        double usableLedgerBalanceNew1 = formatLedgerBalance(usableLedgerBalance);
        log.info("Before early knock off, Usable ledger balance was:" + formatAmountWithRupeeAndCommas(usableLedgerBalanceNew1));
        double usableLedgerBalResult = usableLedgerBalanceNew1 - ARNotDueAmountNew;
        String finalUsableBalance =formatAmountWithRupeeAndCommas(usableLedgerBalResult);
        softAssert.assertEquals("AR not due verification failed",ZERO_WITH_RUPEE,ARNotDueAmount);
        softAssert.assertEquals("Usable ledger balance verification failed",finalUsableBalance,formatAmountWithRupeeAndCommas(Double.parseDouble(String.valueOf(usableLedgerBalanceNew1))));
        log.info("After early knock off , AR not due amount is:" + ZERO_WITH_RUPEE);
        log.info("After early knock off , Usable ledger amount is:" + finalUsableBalance);
        log.info("Final usable ledger balance: " + finalUsableBalance);
        log.info("Early knock off completed");
        AllureCustomLogReporter.addTestStepLog("Before early knock off AR not due amount was :" + ARNotDueAmount);
        AllureCustomLogReporter.addTestStepLog("After early knock off AR not due amount is :" + ZERO_WITH_RUPEE);
        AllureCustomLogReporter.addTestStepLog("Before early knock off Usable ledger amount was :" + formatAmountWithRupeeAndCommas(usableLedgerBalanceNew1));
        AllureCustomLogReporter.addTestStepLog("After early knock off Usable ledger amount is :" + finalUsableBalance);


    }
}
