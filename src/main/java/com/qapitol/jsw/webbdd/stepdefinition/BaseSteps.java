package com.qapitol.jsw.webbdd.stepdefinition;

import com.qapitol.jsw.apitest.apis.impl.OrderImpl;
import com.qapitol.jsw.utils.CurrencyUtils;
import com.qapitol.sauron.common.configuration.Config;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.NoSuchElementException;
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
    public static final String ZOHO_BOOKS_ID = "Zoho BooksID";
    public static final String ADVANCE_INVOICE = "Advance invoice";
    public static final String BASE_AMOUNT = "Base amount";
    public static final String GST_AMOUNT = "GST amount";
    public static final String INVOICE_NUMBER = "Invoice Number";
    public static final String AMOUNT = "Amount";
    public static final String GSTIN = "GSTIN";
    public static final String PENDING = "Pending";
    public static final String SUCCESS = "Success";
    public static final String OPEN = "Open";


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
            boolean preLoaderNotDisplayed = waitUntilElementAttributeContains(getLocator(PRE_LOADER), "style", "display: none;", NotFoundException.class, 30);
            log.info("waited until Pre loader is gone or until timeout. preLoaderNotDisplayed:" + preLoaderNotDisplayed);
        }
        log.exiting();
    }

    /**
     * Dismisses the alert
     */
    public void closeAlert() {
        try {
            sleep(5000);
            alertDismiss();
            log.info("Alert was closed successfully.");
        } catch (Exception e) {
            log.info("No alert found.");
        }
    }

    /**
     * Navigate to Zoho creator with the tab name specified
     *
     * @param tabName the name of the tab/module name
     */
    protected void zohoCreatorNavigation(String tabName) {
        Grid.setSessionName("zoho_creator");
        navigate(Config.getConfigProperty("creatorUrl") + tabName);
    }

    /**
     * This switches to the zho_books session and navigates to Zoho books url
     */
    protected void navigateToZohoBooksSession() {
        log.info("================ Zoho Books Launch ====================");

        Grid.setSessionName("zoho_books");
        Grid.driver().get(Config.getConfigProperty("booksUrl"));
        waitForLoaderToDisappear();
        log.info("Current title in the new tab " + Grid.driver().getTitle());
    }

    /**
     * Refreshes the page and clears any search filters already present.
     */
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

    /**
     * Uses the javascript executor to scroll to the target element and click on it.
     *
     * @param locator key of the locator from the locators yaml file
     */
    protected void jsClick(String... locator) {
        for (int i = 0; i < maxAttempts; i++) {
            try {
                waitAndCheckIsElementVisible(locator);
                WebElement webElement = locateElement(locator);
                Grid.driver().executeScript("arguments[0].scrollIntoView(true);", webElement);
                Grid.driver().executeScript("arguments[0].click();", webElement);
                break;
            } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
                log.log(Level.FINE, "jsclick Error", e);
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
                            waitAndSetText("searchInput", searchLabel, searchValue +Keys.TAB+Keys.ENTER);
                            //Check if drop down is present
//                            if (isElementPresent("searchIndexFirst")) {
//                                //Select the option which contains the search value
//                                waitAndClickOn("searchDropDownSelectOption", searchValue, searchValue);
//                                //In case of multiple options in drop down if the default drop down is shown we
//                                // need to close it via off element tap
//                                if (isElementPresent("searchIndexFirst")) {
//                                    tap("searchButton");
//                                    //Closes any drop down list
//                                }
//                            }

                            //Check if right panel is opened and if open close it.
//                            boolean isRightPanelOpened = isElementPresent("rightPanel");
//                            if (isRightPanelOpened) {
//                                tap("searchButton");
//                            } else {
//                                log.info("Do nothing as right panel is closed.");
//                            }
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

    /**
     * Function to search for the row with a specific text without using the search filtering
     *
     * @param searchText The text to search for in the filter list
     * @param maxTry     the number of times to retry (based on the server producing the rows)
     */
    public void selectRowUsingObject(String searchText, int maxTry) {
        selectRowUsingObject(null, searchText, maxTry);
    }

    protected Function<String, Void> getCallSearchStringContainsFunc = searchText -> {
        searchStringContains(DOCUMENT_NUMBER, searchText);
        return null;
    };

    public int getTotalRowsInTableExcludingHeader(){
        int totalRowCount = 0;
        try {
            totalRowCount = locateElements("totalRows").size();
        } catch (Exception e){
            log.log(Level.FINE, "Error in fetching total rows", e);
        }
        return totalRowCount;
    }

    /**
     * Selects a single row until it is visible by refreshing it. The element visibility depends on the
     * server producing the row. It is expected to have only a single row in the table.
     *
     * @param callSearchStringContains The function to execute the search filtering.
     * @param searchText               The text to search for in the filter list
     * @param maxTry                   the number of times to retry (based on the server producing the rows)
     */
    public void selectRowUsingObject(Function<String, Void> callSearchStringContains, String searchText, int maxTry) {
        sleep(999);
        String selectRowLocKey = "selectRow";

        for (int i = 0; i < maxTry; i++) {
            if (waitAndCheckIsElementPresent(selectRowLocKey, searchText)) {
                assertEquals(getTotalRowsInTableExcludingHeader(), 1, "No order or multiple orders found.");
                waitAndClickOn(selectRowLocKey, searchText);
                log.info("selectRowUsingObject has broken");
                break;
            } else {
                log.info("selectRowUsingObject is looping.....");
                sleep(5000);
                pageRefresh();
                if (nonNull(callSearchStringContains)) {
                    callSearchStringContains.apply(searchText);
                }
                waitForLoaderToDisappear();
            }
        }
    }

    public Map<String, String> getDataFromWindow() {
        return getDataFromWindow("dataWindowElement");
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

    /**
     * CLoses the right panel by clicking on the 'X' icon
     */
    public void popUpClose() {
        waitAndClickOn("popupClose");
    }

    /**
     * Formats the string number to closes 2nd degree
     *
     * @param number
     * @return
     */
    public String formatDecimal(String number) {
        try {
            return CurrencyUtils.getNewInstance().getDecimalFormatterDecimal().format(Double.parseDouble(number));
        } catch (NumberFormatException e) {
            log.info("Invalid number format or the number is already in the expected format");
            return number;
        }
    }

    public static String formatDecimal(double number) {
        try {
            return CurrencyUtils.getNewInstance().getDecimalFormatterDecimal().format(number);
        } catch (NumberFormatException e) {
            log.info("Invalid number format or the number is already in the expected format");
            return String.valueOf(number);
        }
    }

    public String formatDecimalWithComma(String number) {
        try {
            return CurrencyUtils.getNewInstance().getDecimalFormatterCommaDecimal().format(Double.parseDouble(number));
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

    /**
     * Download files from the browser and store them into /<project_dir>/download_dir.
     * The directory will be created if it does not exist.
     *
     * @return provides a list of absolute file paths of the downloaded files.
     */
    protected List<String> getAllDownloadedFiles() {
        log.entering();
        List<String> downloadedFiles = new ArrayList<>();
        List<String> files = (Grid.driver()).getDownloadableFiles();
        Path targetDirectory = Paths.get(System.getProperty("user.dir"), "download_dir");
        try {
            if (!targetDirectory.toFile().exists()) {
                Files.createDirectories(targetDirectory);
            }
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

    /**
     * Hover & Click on a web element to bypass stale or element click interception issues.
     * This will Hover & click basis on Selenium actions API.
     * It retries the click if there is a stale element exception.
     * The number of retries are at 5.
     *
     * @param locator The key of the locator from the locator yaml file
     */
    public void tap(String... locator) {
        for (int i = 0; i < maxAttempts; i++) {
            try {
                if (waitAndCheckIsElementPresent(locator)) {
                    Actions actions = new Actions(Grid.driver());
                    List<WebElement> webElements = locateElements(locator);
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

    /**
     * Hover on a web element to bypass stale or element click interception issues.
     * This will hover basis on Selenium actions API.
     * It retries the hover if there is a stale element exception.
     * The number of retries are at 5.
     *
     * @param locator The key of the locator from the locator yaml file
     */
    public void hover(String... locator) {
        for (int i = 0; i < maxAttempts; i++) {
            try {
                if (waitAndCheckIsElementPresent(locator)) {
                    Actions actions = new Actions(Grid.driver());
                    WebElement webElement = locateElement(locator);
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

    public static String replaceComma(String value) {
        return value.replace(",", "");
    }

    /**
     * This method being used to fetch any row data in Zoho creator as a map by passing any unique cell value
     *
     * @param cellValue Unique cell value to pass to identify the row
     * @return Map<String, String> of entire row data as key value pair.
     */
    public Map<String, String> getRowDataAsMap(String cellValue) {

        Map<String, String> tableData = new HashMap<>();
        sleep(999);
        WebElement table = locateElement("baseTable1");;
        if (isTableVisible(table)) {
            By headerCell = getByFromLocator(getLocator("headerCells"));
            List<WebElement> headerCells = table.findElements(headerCell);
            log.info("Header Size--->"+ headerCells.size());

            WebElement row = findRowWithCellValue(table, cellValue);
            if (row == null) {
                log.info("Row with value '" + cellValue + "' not found.");
                return null;
            }
            By rowCell = getByFromLocator(getLocator("rowCells"));
            List<WebElement> rowCells = row.findElements(rowCell);
            log.info("Row cells size-->"+rowCells.size());
            if (headerCells.size() == rowCells.size()) {
                for (int i = 0; i < headerCells.size(); i++) {

                    WebElement table2 = locateElement("baseTable2");
                    List<WebElement> headerElements = table2.findElements(headerCell);
                    String headerText = headerElements.get(i).getAttribute("displayname");
                    String rowData = rowCells.get(i).getText().trim();
                    tableData.put(headerText, rowData);
                }
            } else {
                log.info("The number of headers does not match the number of cells in the matched row.");
            }
        }
        log.info("Table Data Map Containing Cell Value - "+cellValue +" are--> "+tableData);

        return tableData;
    }

    private WebElement findRowWithCellValue(WebElement table, String cellValue) {

        int rowCount = 0;
        List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));
        log.info("Number of rows ---> "+rows.size());
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                if (cell.getText().trim().contains(cellValue)) {
                    rowCount++;
                    if (rowCount>1){
                        return rows.get(0);
                    }
                    log.info("row ---> "+ row + " And Cell --->"+ cell);
                    return row;  // Return the row that contains the search value
                }
            }
        }

        return null;  // Return null if no matching row is found
    }

    public boolean isTableVisible(WebElement table) {
        try {
            //to be checked
            waitUntilElementIsVisible("baseTable1", NoSuchElementException.class);

            log.info("===Table is visible===");
            return table.isDisplayed();
        } catch (TimeoutException e) {
            log.info("Table not visible within the specified timeout");
            return false;
        }
    }

    public Map<String, String> retryIfTableDataIsNull(Map<String, String> map, String cellValue){
        Map<String, String> tableData=null;
        int maxRetries = 2;
        int attempt = 0;

        while (attempt <= maxRetries) {
            if(map == null){
                pageRefresh();
                tableData = getRowDataAsMap(cellValue);
            }else {
                log.info("Table data is not null. Proceeding with validation.");
                break; // Exit the loop if the object is not null
            }
            attempt++;
        }
        return tableData;
    }
    public static Float getGSTAmount(Float baseAmount, int rate) {
        Float gstAmount;
        gstAmount = baseAmount * rate / 100;
        log.info("GST Amount --> " + gstAmount);
        return gstAmount;
    }
    public void sortTable(String columnName, String sortBy) {
        pageRefresh();
        tap("columnName", columnName);
        waitAndCheckIsElementPresent("sortPopup");
        waitAndClickOn("sortBy", sortBy);
        waitForFooterLoaderToDisappear();
    }
    public void waitForFooterLoaderToDisappear() { //this below methods is already there in basesteps
        log.entering();
        //Checks if the loader animation is present for 1 seconds and if present will wait for upto 10 seconds to close
        WebElement loaderElement;
        try {
            loaderElement = locateElement("footerLoader");
        } catch (NotFoundException e) {
            loaderElement = null;
        }
        if (nonNull(loaderElement)) {
            boolean isNotPresent = waitUntilElementIsNotPresent(getLocator("footerLoader"), 10);
            log.info("waited until footer loader is gone or until timeout. IsNotPresent:" + isNotPresent);
        } else {
            log.info("Did not find footer loader so going ahead.");
        }
        log.exiting();
    }

}
