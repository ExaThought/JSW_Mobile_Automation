package com.qapitol.jsw.utils;

import com.qapitol.sauron.core.Grid;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;


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
}
