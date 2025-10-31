package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.AllureCustomLogReporter;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;


public class BaseSteps extends AbstractWebPage {
    public static final int LONG_WAIT = 5000;
    public static final int AVG_WAIT = 2000;
    public static final int SHORT_WAIT = 1000;

    protected void clearAndEnterValue(String locator, String value) {
        waitAndCheckIsElementPresent(locator);
        WebElement field = Grid.driver().findElement(By.xpath(locator));
        try {
            field.clear();
        } catch (Exception e) {
            field.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        }
        field.sendKeys(value);
        log.info("Entered value: '" + value + "' in element: " + locator);
        AllureCustomLogReporter.addTestStepLog("Entered value: " + value + " in element: " + locator);
    }



}


