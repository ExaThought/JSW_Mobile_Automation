package com.qapitol.jsw.utils;

import com.qapitol.sauron.core.Grid;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.logging.Level;

import static com.qapitol.sauron.core.actions.ActionListener.logger;

public class CustomUtil {
    public static void scrollingToViewAndClickUsingJS(WebElement locator) {
        try {
            JavascriptExecutor js = Grid.driver();
            js.executeScript("arguments[0].scrollIntoView(true);", locator);
        } catch (Exception e) {
            logger.log(Level.FINEST, "clickUsingJS", e);
        }
    }

    public static void waits(WebElement locator, WebDriver driver) {
        WebDriverWait wait=new WebDriverWait(driver,java.time.Duration.ofSeconds(10));
       wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public static void waitUntilElementDisappears(By locator) {
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void moveToElement(By locator){
        Actions actions = new Actions(Grid.driver());
    }



}
