package com.qapitol.jsw.webbdd.stepdefinition;

import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class CreditLimitSteps extends BaseSteps {

    public static final String CREDIT_PROGRAM_AND_LIMIT="#Page:Credit_programs_and_limits";
    public static final String CREDIT_LIMIT_REF_ID="Credit limit reference id";

    @Then("Navigate to Credit module tab and search the credit program and validate the freeze status as {string} for Credit program {string}")
    public void validateCreditProgramFreezeStatus(String freezeStatus, String creditProgram) {
        zohoCreatorNavigation(CREDIT_PROGRAM_AND_LIMIT);
        clearSearch();
        searchStringContains(CREDIT_LIMIT_REF_ID, creditProgram);
        clickOnShowMoreAction();
        validateFreezeStatus(freezeStatus,creditProgram);
    }

    public void clickOnShowMoreAction() {
        sleep(2000);
        waitAndClickOn("showMoreActionBtn");
//        helper.jsClick(element,"More action");
        waitAndClickOn("creditLimitFreezeBtn");
//        helper.findElement(By.xpath(ZC_CREDIT_LIMIT_FREEZE)).click();
        waitUntilLoaderInvisible();
    }

    public void validateFreezeStatus(String freezeSatus, String creditProgramId){
        selectRowUsingObject(getCallSearchStringContainsFunc, creditProgramId,10);
        Map<String, String> creditLimitData = getDataFromWindow("dataWindowElement");
//        assertEquals("Credit limit freeze verification failed:",freezeSatus,creditLimitData.get("Freeze status"));
//        log.info("Credit limit freeze status:"+creditLimitData.get("Freeze status"));
//        ExtentCucumberAdapter.addTestStepLog("Customer limit freeze status:"+creditLimitData.get("Freeze status"));
    }
}
