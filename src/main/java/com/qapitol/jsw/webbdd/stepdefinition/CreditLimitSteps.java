package com.qapitol.jsw.webbdd.stepdefinition;

import com.qapitol.jsw.apitest.apis.impl.OrderFlowImpl;
import com.qapitol.jsw.apitest.pojos.order.getCreditDetails.CustomerCreditInfoSRO;
import com.qapitol.jsw.apitest.pojos.order.getCreditDetails.GetCreditDetailsResp;
import com.qapitol.jsw.utils.AllureCustomLogReporter;
import com.qapitol.jsw.utils.CurrencyUtils;
import com.qapitol.sauron.data.ContextData;
import com.qapitol.sauron.data.Data;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.Map;

import static org.testng.Assert.assertEquals;

public class CreditLimitSteps extends BaseSteps {

    public static final String CREDIT_PROGRAM_AND_LIMIT = "#Page:Credit_programs_and_limits";
    public static final String CREDIT_LIMIT_REF_ID = "Credit limit reference id";
    public static CustomerCreditInfoSRO creditDetailsResponseBefore;

    @Then("Navigate to Credit module tab and search the credit program and validate the freeze status as {string} for Credit program {string}")
    public void validateCreditProgramFreezeStatus(String freezeStatus, String creditProgram) {
        zohoCreatorNavigation(CREDIT_PROGRAM_AND_LIMIT);
        clearSearch();
        searchStringContains(CREDIT_LIMIT_REF_ID, creditProgram);
        clickOnShowMoreAction();
        validateFreezeStatus(freezeStatus, creditProgram);
        pageRefresh();
    }

    public void clickOnShowMoreAction() {
        sleep(2000);
        tap("showMoreActionBtn");
        tap("creditLimitFreezeBtn");
        waitUntilLoaderInvisible();
    }

    public void validateFreezeStatus(String freezeSatus, String creditProgramId) {
        selectRowUsingObject(getCallSearchStringContainsFunc, creditProgramId, 10);
        Map<String, String> creditLimitData = getDataFromWindow("dataWindowElement");
        assertEquals(freezeSatus, creditLimitData.get("Freeze status"), "Credit limit freeze verification failed:");
        log.info("Credit limit freeze status:" + creditLimitData.get("Freeze status"));
        pageRefresh();
        AllureCustomLogReporter.addTestStepLog("Customer limit freeze status:" + creditLimitData.get("Freeze status"));
    }

    @Given("Get Credit Limit Details for Customer Before Order creation")
    public void getCreditLimitDetailsForCustomerBeforeOrder() {
        creditDetailsResponseBefore =getCreditDetails();
        ContextData.addData("creditDays", creditDetailsResponseBefore.getCreditDueDay());
        AllureCustomLogReporter.addTestStepLog("Customer Credit Details Before Order Creation For Customer--> " + creditDetailsResponseBefore);
    }

    public CustomerCreditInfoSRO getCreditDetails() {
        OrderFlowImpl orderFlow = new OrderFlowImpl();
        GetCreditDetailsResp getCreditDetailsResp = orderFlow.getCreditDetailsUsingPan();
        String creditRefId = (String) Data.get().get("creditRefId");
        CustomerCreditInfoSRO customerCreditInfoSRO=null;
        for (int i = 0; i < getCreditDetailsResp.getCustomerCreditInfoSROS().size(); i++) {
            if (creditRefId.equals(getCreditDetailsResp.getCustomerCreditInfoSROS().get(i).getCreditLimitRefId().trim())) {
                customerCreditInfoSRO= getCreditDetailsResp.getCustomerCreditInfoSROS().get(i);
                break;
            }
        }
        return customerCreditInfoSRO;
    }

    @Then("Get Credit Limit Details for Customer After Order Confirmed")
    public void getCreditLimitDetailsForCustomerAfterOrderConfirmed() throws Exception {
//        SeleniumHelper.staticWait(2000);
        CustomerCreditInfoSRO creditDetailsResponseAfter = getCreditDetails();
        double creditBlockedBefore = creditDetailsResponseBefore.getCreditBlocked();
        double creditAvailableBefore = creditDetailsResponseBefore.getCreditAvailable();
        double creditUsableBefore = creditDetailsResponseBefore.getCreditUsable();
        double creditUtilisedBefore = creditDetailsResponseBefore.getCreditUtilised();

        double creditBlockedAfter = creditDetailsResponseAfter.getCreditBlocked();
        double creditAvailableAfter = creditDetailsResponseAfter.getCreditAvailable();
        double creditUsableAfter = creditDetailsResponseAfter.getCreditUsable();
        double creditUtilisedAfter = creditDetailsResponseAfter.getCreditUtilised();

        String creditAmount = CurrencyUtils.getNewInstance().convertPaisaToRupees((int)ContextData.getDataFromKey("orderAmount") - (int)ContextData.getDataFromKey("advanceAmountInPaisa"));
        Assert.assertEquals("Credit Instrument is not matched", creditDetailsResponseAfter.getCreditInstrument(), creditDetailsResponseBefore.getCreditInstrument());
        Assert.assertEquals("Credit Blocked is not matched", formatDecimal(String.valueOf(creditBlockedAfter - creditBlockedBefore)), creditAmount);
        Assert.assertEquals("Credit Available is not matched", String.valueOf(creditAvailableBefore), String.valueOf(creditAvailableAfter));
        Assert.assertEquals("Credit Usable is not matched", formatDecimal(String.valueOf((double) creditUsableBefore - (double) creditUsableAfter)), creditAmount);
        Assert.assertEquals("Credit Utilised is not matched", String.valueOf(creditUtilisedBefore), String.valueOf(creditUtilisedAfter));
        AllureCustomLogReporter.addTestStepLog("Credit Blocked Amount After Order Confirmed: " + creditBlockedAfter + "\n and creditUsable: " + creditUsableAfter);
    }
    @Then("Get Credit Limit Details for Customer After Order Invoiced")
    public void getCreditLimitDetailsForCustomerAfterOrder() {
        CustomerCreditInfoSRO creditDetailsResponseAfterInvoiced = getCreditDetails();
        String invoiceAmount = CurrencyUtils.getNewInstance().convertPaisaToRupees((int)ContextData.getDataFromKey("orderAmount"));
        double creditBlockedBefore = creditDetailsResponseBefore.getCreditBlocked();
        double creditAvailableBefore = creditDetailsResponseBefore.getCreditAvailable();
        double creditUsableBefore = creditDetailsResponseBefore.getCreditUsable();
        double creditUtilisedBefore = creditDetailsResponseBefore.getCreditUtilised();

        double creditBlockedAfter = creditDetailsResponseAfterInvoiced.getCreditBlocked();
        double creditAvailableAfter = creditDetailsResponseAfterInvoiced.getCreditAvailable();
        double creditUsableAfter = creditDetailsResponseAfterInvoiced.getCreditUsable();
        double creditUtilisedAfter = creditDetailsResponseAfterInvoiced.getCreditUtilised();

        String creditInvoicedAmount = formatDecimal(String.valueOf(Double.parseDouble(invoiceAmount) - Double.parseDouble((String)ContextData.getDataFromKey("advanceAmount"))));
        Assert.assertEquals("Credit Instrument is not matched", creditDetailsResponseBefore.getCreditInstrument(), creditDetailsResponseAfterInvoiced.getCreditInstrument());
        Assert.assertEquals("Credit Blocked is not matched", String.valueOf(creditBlockedAfter), String.valueOf(creditBlockedBefore));
        Assert.assertEquals("Credit Available is not matched", formatDecimal(String.valueOf(creditAvailableBefore - creditAvailableAfter)), creditInvoicedAmount);
        Assert.assertEquals("Credit Usable is not matched", formatDecimal(String.valueOf((double) creditUsableBefore - (double) creditUsableAfter)), creditInvoicedAmount);
        Assert.assertEquals("Credit Utilised is not matched", formatDecimal(String.valueOf(creditUtilisedAfter - creditUtilisedBefore)), creditInvoicedAmount);
        AllureCustomLogReporter.addTestStepLog("Credit Blocked Amount After Invoiced " + creditBlockedAfter + "\n And creditUsable: " + creditUsableAfter + " And Credit Utilised: " + creditUtilisedAfter);
    }



}

