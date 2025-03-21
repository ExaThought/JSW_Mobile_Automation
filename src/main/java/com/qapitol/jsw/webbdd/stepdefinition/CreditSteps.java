package com.qapitol.jsw.webbdd.stepdefinition;

import com.qapitol.sauron.common.configuration.Config;
import com.qapitol.sauron.core.Grid;
import io.cucumber.java.en.Given;

public class CreditSteps extends BaseSteps {
    @Given("Login to zoho books")
    public void zohoBooksLogin() throws Exception {
        createZohoBooksSession();
//        DriverHelper.switchToTab(driver, 1); //To switch again to the new window->books (index can be pass accordingly)
        log.info("Navigated and Logged in to Zoho books ");
//        ExtentCucumberAdapter.addTestStepLog("Navigated and Logged in to Zoho books ");
        closeAlert();
    }

    public void createZohoBooksSession() throws Exception {
        log.info("================ Zoho Books Launch ====================");

        Grid.setSessionName("zoho_books");
//        JavascriptExecutor js = Grid.driver();
//        js.executeScript("window.open('');");

        navigate(Config.getConfigProperty("booksUrl"));
//        switchToTab(driver,1);
//        driver.get(Utils.getGlobalValue("booksUrl"));
        waitForLoaderToDisappear();
        log.info("Current title in the new tab " + Grid.driver().getTitle());

    }

    public void closeAlert() {
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        try {
            sleep(5000);
            alertDismiss();
//            Alert alert = driver.switchTo().alert();
//            alert.dismiss();
            log.info("Alert was closed successfully.");
        } catch (Exception e) {
            log.info("No alert found.");
        }
    }
}
