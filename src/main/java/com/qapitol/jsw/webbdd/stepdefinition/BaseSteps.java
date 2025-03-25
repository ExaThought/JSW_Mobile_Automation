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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.logging.Level;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.testng.Assert.assertEquals;

public abstract class BaseSteps extends AbstractWebPage {
    int maxAttempts = 5;

    // static Locaters
    private static String loader = "loader";
    public static String CLEAR_SEARCH = "clearSearch";
    public static String PRE_LOADER = "preLoader";
    public static String WINDOW_KEYS = "windowKeys";
    public static String WINDOW_VALUES = "windowValues";


    public static final String ORDER_ID = "Order id";
    public static final String DOCUMENT_TYPE = "Document type";
    public static final String DOCUMENT_ID = "Document id";
    public static final String DOCUMENT_NUMBER = "Document number";

    //Validation values
    public static final String CREATOR_ID_DOC = "Creator ID - Documents";

    public <T> T getPojoFromContextData(String key, Class<T> t) {
        OrderImpl orderCreation = new OrderImpl();
        return orderCreation.getPojoFromContextData(key, t);
    }

    public <T> void setPojoToContextData(String key, T t) {
        OrderImpl orderCreation = new OrderImpl();
        orderCreation.setPojoToContextData(key, t);
    }

    /**
     * This a migration function which converts JUnit assertion to the TestNg function.
     * <p>
     * The expected and actual data types which are supported are same as the TestNg assert.
     *
     * @param message
     * @param expected
     * @param actual
     * @param <T>
     */
    public <T> void migrateToTestNgAssertEquals(String message, T expected, T actual) {
        assertEquals(actual, expected, message);
    }

    public void migrateToTestNgAssertEquals(String message, double expected, double actual, double delta) {
        assertEquals(actual, expected, delta, message);
    }

    public void migrateToTestNgAssertEquals(String message, float expected, float actual, float delta) {
        assertEquals(actual, expected, delta, message);
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
                    if (isElementPresent("searchList")) {
                        log.info("The search list in the right panel is present.");
                        jsClick("searchCheckbox", searchLabel);
                        //Wait until the search input box is present
                        if (waitAndCheckIsElementPresent("searchInput", searchLabel)) {
                            //Set the text in the text box
                            waitAndSetText("searchInput", searchLabel, searchValue);
                            //Check if dorop down is present
                            if (isElementPresent("searchIndexFirst")) {
                                //Select the option which contains the search value
                                waitAndClickOn("searchDropDownSelectOption", searchValue, searchValue);
                                //In case of multiple options in drop down if the default drop down is shown we
                                // need to close it via off element tap
                                if (isElementPresent("searchIndexFirst")) {
                                    tap("searchButton");
                                    //Closes the any drop down list
                                }
                            }

                            //Check if right panel is opened and if open close it.
                            boolean isRightPanelOpened = isElementPresent("rightPanel");
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
            By windowKeyBy = getByFromLocator(getLocator(WINDOW_KEYS));
            By windowValueBy = getByFromLocator(getLocator(WINDOW_VALUES));
            String key = windowElement.findElement(windowKeyBy).getText();
            String value = windowElement.findElement(windowValueBy).getText();
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

    public static String formatDecimal(double number) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            return decimalFormat.format(number);
        } catch (NumberFormatException e) {
            log.info("Invalid number format or the number is already in the expected format");
            return String.valueOf(number);
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

    public static String convertDateFormat(String originalDate) {
        // Create a SimpleDateFormat for parsing the original date
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Parse the original date string into a Date object
        Date date = null;
        try {
            date = inputFormat.parse(originalDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // Create a SimpleDateFormat for the desired output format
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy");

        // Format the date into the desired format and return it
        return outputFormat.format(date);
    }

    public String getOrderSubstring(String input) {
        String[] parts = input.split("-");
        return parts[1];
    }

    public String getCurrentTimeStamp() {
        return String.valueOf(new java.sql.Timestamp(System.currentTimeMillis()).getTime());
    }

    public double toDouble(String input, double defaultValue) {
        if (input == null || input.isEmpty()) {
            return defaultValue;
        }

        // Remove any non-numeric characters except '.'
        String sanitizedInput = input.replaceAll("[^0-9.]", "");

        // Handle cases where sanitized input becomes empty
        if (sanitizedInput.isEmpty() || sanitizedInput.equals(".")) {
            return defaultValue;
        }

        try {
            return Math.round(Double.parseDouble(sanitizedInput) * 100.0) / 100.0;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
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
            log.log(Level.FINE, "Get all downloaded files", e);
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

    public String getLedgerStatementKey(Map<String, String> tableData) {
        String ledgerStatementKey;
        if (tableData != null) {
            ledgerStatementKey = tableData.get(DOCUMENT_ID).concat("|").concat(tableData.get(CREATOR_ID_DOC)).concat("|").concat(tableData.get(DOCUMENT_TYPE));
        } else {
            log.info("Ledger Statement Key Value is Null");
            return null;
        }
        log.info("Ledger Statement Key Value --> " + ledgerStatementKey);
        return ledgerStatementKey;
    }
}
