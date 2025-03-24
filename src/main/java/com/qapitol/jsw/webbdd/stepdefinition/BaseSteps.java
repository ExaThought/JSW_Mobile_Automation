package com.qapitol.jsw.webbdd.stepdefinition;

import com.qapitol.jsw.apitest.apis.impl.OrderImpl;
import com.qapitol.sauron.common.configuration.Config;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public abstract class BaseSteps extends AbstractWebPage {
    int maxTimeout = 20;
    int maxAttempts = 5;
    int minPolling = 2;

    // Locaters
    private static String loader = "loader";
    public static String CLEAR_SEARCH = "clearSearch";
    public static String PRE_LOADER = "preLoader";
    public static String WINDOW_KEYS = ".//div[@class=' record-label-name']";
    public static String WINDOW_VALUES = ".//div[contains(@class,'record-summary-data')]";


    public static final String ORDER_ID = "Order id";
    public static final String DOCUMENT_TYPE = "Document type";
    public static final String DOCUMENT_ID = "Document id";
    public static final String DOCUMENT_NUMBER = "Document number";

    public <T> T getPojoFromContextData(String key, Class<T> t) {
        OrderImpl orderCreation = new OrderImpl();
        return orderCreation.getPojoFromContextData(key, t);
    }

    public <T> void setPojoToContextData(String key, T t) {
        OrderImpl orderCreation = new OrderImpl();
        orderCreation.setPojoToContextData(key, t);
    }

    public void waitForLoaderToDisappear() {
        log.entering();
        //Checks if the loader animation is present for 1 seconds and if present will wait for upto 10 seconds to close
        WebElement loaderElement;
        try {
            loaderElement = locateElement(loader);
        } catch (NotFoundException e) {
            loaderElement = null;
        }
        if (nonNull(loaderElement)) {
            boolean isNotPresent = waitUntilElementIsNotPresent(getLocator(loader), 10);
            log.info("waited until loader is gone or until timeout. IsNotPresent:" + isNotPresent);
        } else {
            log.info("Did not find loader so going ahead.");
        }
        log.exiting();
    }

    public void waitUntilLoaderInvisible() {
        log.entering();
        String displayValue = waitAndGetAttributeV3("style", PRE_LOADER);
        if (StringUtils.isNotEmpty(displayValue) && displayValue.contains("display: none;")) {
            log.info("No need to wait for pre loader as attribute has value:" + displayValue);
        } else {
            boolean preLoaderNotDisplayed = waitUntilElementAttributeContains(getLocator(PRE_LOADER), "style", "display: none;", NotFoundException.class, 20);
            log.info("waited until Pre loader is gone or until timeout. preLoaderNotDisplayed:" + preLoaderNotDisplayed);
        }
        log.exiting();
    }

    protected void zohoCreatorNavigation(String tabName) {
        navigate(Config.getConfigProperty("creatorUrl") + tabName);
    }

    protected void clearFilter() {
        pageRefresh();
        clearSearch();
    }

    /**
     * This function is used to clear the pre-filled  search fields or already filtered inputs in any Page of Zoho creator
     */
    protected void clearSearch() {
        log.entering();
        String msg = "search is blank : no preselected field is available to clear";
        try {
            WebElement elementCheck = this.locateElement(CLEAR_SEARCH);
            if (nonNull(elementCheck)) {
                List<WebElement> clearSearchElements = this.locateElements(CLEAR_SEARCH);
                if (isNull(clearSearchElements)) {
                    log.info(msg);
                    return;
                }
                if (clearSearchElements.isEmpty()) {
                    log.info(msg);
                    return;
                }
                for (int i = 0; i < clearSearchElements.size(); i++) {
//                refreshAndClick(CLEAR_SEARCH);
                    tap(CLEAR_SEARCH);
                }
            } else {
                log.info(msg);
            }
        } catch (Exception e) {
            log.info(msg);
        }
        log.exiting();
    }

    protected void refreshAndClick(String locater) {
        pageRefresh();
        for (int i = 0; i < maxAttempts; i++) {
            try {
                waitAndClickOn(1, locater);
                //jsClick(w, "");
                break;
            } catch (TimeoutException | ElementClickInterceptedException | StaleElementReferenceException e) {
//                sleep(1000);
            }
        }
    }

    protected void jsClick(String... locater) {
        for (int i = 0; i < maxAttempts; i++) {
            try {
                waitAndCheckIsElementVisible(locater);
                WebElement webElement = locateElement(locater);
                Grid.driver().executeScript("arguments[0].scrollIntoView(true);", webElement);
                Grid.driver().executeScript("arguments[0].click();", webElement);
                break;
            } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
                log.log(Level.FINE, "jsclick Error", e);
//                log.info(e.getMessage());
            }
        }
    }

    /**
     * This function is used to search any data in Zoho creator with some input value, like the "Order number" with order id
     *
     * @param searchLabel It represents the search field name.
     * @param searchValue It represents the search value we need to use to search.
     */
    public void searchStringContains(String searchLabel, String searchValue) {
        log.entering();
        waitUntilLoaderInvisible();
        if (waitAndCheckIsElementVisible("searchIcon")) {
            log.info("searchIcon is visible");
            if (waitAndCheckIsElementClickable("searchIcon")) {
                log.info("searchIcon is clickable");
                tap("searchIcon");
                log.info("searchIcon is tapped");
                log.info("Waiting for right panel to open...");
                boolean isRightPanelShown = waitUntilElementAttributeContains(getLocator("rightPanel"), "style", "display: block;", NotFoundException.class, 5);
                if (isRightPanelShown) {
                    log.info("Right panel has opened.");
                    //Wait and verify if search List is visible
                    if (waitAndCheckIsElementPresent(2, "searchList")) {
                        log.info("The search list in the right panel is present.");
//                        sleep(300);
                        jsClick("searchCheckbox", searchLabel);
                        //          clickUsingJS(locateElement("searchCheckbox", searchLabel));
                        if (waitAndCheckIsElementPresent("searchInput", searchLabel)) {
//                            waitAndSetText("searchInput", searchLabel, searchValue + Keys.TAB + Keys.ENTER);
                            waitAndSetText("searchInput", searchLabel, searchValue);
                            waitAndClickOn(2, "searchIndexFirst");

                            boolean isRightPanelOpened = waitAndCheckIsElementPresent("rightPanel");
                            if (isRightPanelOpened) {
                                tap("searchButton");
                            } else {
                                log.info("Do nothing as right panel is closed.");
                            }
                        } else {
                            throw new RuntimeException("search Input is not visible. So unable to enter value.");
                        }
                    } else {
                        throw new RuntimeException("Search list is not visible");
                    }
                } else {
                    log.info("Right panel has not opened.");
                }
            } else {
                throw new RuntimeException("Search icon not clickable");
            }
        } else {
            throw new RuntimeException("Unable to find Search Icon");
        }
        log.exiting();
    }


    public void selectRowUsingObject(String searchText, int maxTry) {
        selectRowUsingObject(null, searchText, maxTry);
    }

    protected Function<String, Void> getCallSearchStringContainsFunc = searchText -> {
        searchStringContains(DOCUMENT_NUMBER, searchText);
        return null;
    };

    public void selectRowUsingObject(Function<String, Void> callSearchStringContains, String searchText, int maxTry) {
        sleep(999);
        String selectRowLocKey = "selectRow";

        for (int i = 0; i < maxTry; i++) {
            WebElement webElement = locateElement(selectRowLocKey, searchText);
            if (nonNull(webElement)) {
                waitAndClickOn(selectRowLocKey, searchText);
                break;
            } else {
                sleep(5000);
                pageRefresh();
                if (nonNull(callSearchStringContains)) {
                    callSearchStringContains.apply(searchText);
                }
                waitForLoaderToDisappear();
            }
        }
    }

    public Map<String, String> getDataFromWindow(String locaterKey) {
        sleep(999);
        Map<String, String> windowMap = new HashMap<>();
        waitAndCheckIsElementPresent(locaterKey);
        List<WebElement> windowElements = locateElements("dataWindow");
        log.info("Window Element Size = " + windowElements.size() + " window elements --> " + windowElements);
        for (WebElement windowElement : windowElements) {
            String key = windowElement.findElement(By.xpath(WINDOW_KEYS)).getText();
            String value = windowElement.findElement(By.xpath(WINDOW_VALUES)).getText();
            windowMap.put(key, value);
        }
        log.info("Window map--> " + windowMap);
        return windowMap;
    }

    public void popUpClose() {
       // sleep(1000);
        waitAndClickOn("popupClose");
    }

    public String formatDecimal(String number) {
        try {
            double value = Double.parseDouble(number);
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            return decimalFormat.format(value);
        } catch (NumberFormatException e) {
            log.info("Invalid number format or the number is already in the expected format");
            return number;
        }
    }

    public String formatDecimalWithComma(String number) {
        try {
            double value = Double.parseDouble(number);
            DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
            return decimalFormat.format(value);
        } catch (NumberFormatException e) {
            log.info("Invalid number format or the number is already in the expected format");
            return number;
        }
    }

    public String getOrderSubstring(String input) {
        String[] parts = input.split("-");
        return parts[1];
    }

    public String convertPaisaToRupees(int paisa) {

        double rupees = paisa / 100.0;
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(rupees);
    }

    public Double getTDSAmount(Double baseAmount, int rate) {
        Double tdsAmount = baseAmount * rate / 100;
        tdsAmount = Math.ceil(tdsAmount);
        log.info("TDS Amount --> " + tdsAmount);
//        ExtentCucumberAdapter.addTestStepLog("TDS Amount --> " + tdsAmount);
        return tdsAmount;
    }

    public String getCurrentTimeStamp() {
        return String.valueOf(new java.sql.Timestamp(System.currentTimeMillis()).getTime());
    }

    protected List<String> getAllDownloadedFiles() {
        log.entering();
        List<String> downloadedFiles = new ArrayList<>();
        List<String> files = (Grid.driver()).getDownloadableFiles();
        Path targetDirectory = Paths.get(System.getProperty("user.dir"), "download_dir");
        try {
            for (String file : files) {
                Grid.driver().downloadFile(file, targetDirectory);
                Path downloadedAbsFilePath = Paths.get(targetDirectory.toFile().toString(), file);
                downloadedFiles.add(downloadedAbsFilePath.toFile().getAbsolutePath());
                double fileSize = (double) Files.size(downloadedAbsFilePath) / 1024;
                log.info(String.format("Downloaded %s of size %s", downloadedAbsFilePath.toFile().getAbsolutePath(), fileSize));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sleep(5000);
        log.exiting(downloadedFiles);
        return downloadedFiles;
    }

    public void tap(String... locater) {
        for (int i = 0; i < maxAttempts; i++) {
            try {
                if (waitAndCheckIsElementPresent(locater)) {
                    Actions actions = new Actions(Grid.driver());
                    List<WebElement> webElements = locateElements(locater);
                    for (WebElement webElement : webElements) {
                        actions.moveToElement(webElement).click().perform();
                    }
                }
                break;
            } catch (StaleElementReferenceException e) {
                log.info(e.getMessage());
            }
        }
    }

    public void hover(String... locater) {
        for (int i = 0; i < maxAttempts; i++) {
            try {
                if (waitAndCheckIsElementPresent(locater)) {
                    Actions actions = new Actions(Grid.driver());
                    WebElement webElement = locateElement(locater);
                    actions.moveToElement(webElement).perform();
                }
                break;
            } catch (StaleElementReferenceException e) {
                log.info(e.getMessage());
            }
        }
    }
}
