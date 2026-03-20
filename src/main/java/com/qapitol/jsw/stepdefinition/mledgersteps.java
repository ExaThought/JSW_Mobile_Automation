package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.LocatorReader;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.openqa.selenium.support.ui.Select;

import static com.qapitol.jsw.utils.Hooks.softAssert;

public class mledgersteps extends AbstractWebPage {

    private static final Logger log = LoggerFactory.getLogger(mledgersteps.class);
    private final LocatorReader locatorReader = new LocatorReader("locators/mledgersteps.yaml");
    private final JavascriptExecutor js = (JavascriptExecutor) Grid.driver();

    // ── Locator constants ──────────────────────────────────────────────────
    private static final String LEDGER_TAB = "ledgerTab";
    private static final String HOME_TAB = "homeTab";
    private static final String CATALOGUE_TAB = "catalogueTab";
    private static final String CART_TAB = "cartTab";
    private static final String ORDER_TAB = "orderTab";
    private static final String PAYMENT_REMINDER_CLOSE_BTN = "paymentReminderCloseBtn";
    private static final String LEDGER_PAGE_TITLE = "ledgerPageTitle";
    private static final String PAYMENTS_DUE_NOW_SECTION = "paymentsDueNowSection";
    private static final String PAYMENTS_DUE_NOW_LABEL = "paymentsDueNowLabel";
    private static final String PAYMENTS_DUE_NOW_AMOUNT = "paymentsDueNowAmount";
    private static final String DUE_LATER_SECTION = "dueLaterSection";
    private static final String DUE_LATER_LABEL = "dueLaterLabel";
    private static final String DUE_LATER_AMOUNT = "dueLaterAmount";
    private static final String USABLE_LEDGER_BALANCE_SECTION = "usableLedgerBalanceSection";
    private static final String USABLE_LEDGER_BALANCE_LABEL = "usableLedgerBalanceLabel";
    private static final String USABLE_LEDGER_BALANCE_AMOUNT = "usableLedgerBalanceAmount";
    private static final String VIEW_DOWNLOAD_LEDGER_LINK = "viewDownloadLedgerLink";
    private static final String OTHER_ACTIONS_SECTION = "otherActionsSection";
    private static final String OTHER_ACTIONS_LABEL = "otherActionsLabel";
    private static final String PAYMENT_INSTRUCTIONS_OPTION = "paymentInstructionsOption";
    private static final String FINANCIAL_DOCUMENTS_OPTION = "financialDocumentsOption";
    private static final String HELP_AND_SUPPORT_OPTION = "helpAndSupportOption";
    private static final String DATE_RANGE_OPTION = "dateRangeOption";
    private static final String USABLE_CREDIT_LIMIT_SECTION = "usableCreditLimitSection";
    private static final String USABLE_CREDIT_LIMIT_LABEL = "usableCreditLimitLabel";
    private static final String USABLE_CREDIT_LIMIT_AMOUNT = "usableCreditLimitAmount";
    private static final String NO_TRANSACTIONS_MESSAGE = "noTransactionsMessage";

    // Ledger statement specific locators
    private static final String LEDGER_STATEMENT_TAB = "ledgerStatementTab";
    private static final String DATE_PICKER_FROM = "datePickerFrom";
    private static final String DATE_PICKER_TO = "datePickerTo";
    private static final String CALENDAR_PREV_MONTH_BTN = "calendarPrevMonthBtn";
    private static final String CALENDAR_DAY = "calendarDay";
    private static final String DOWNLOAD_STATEMENT_BTN = "downloadStatementBtn";
    private static final String STATEMENT_DATA_LIST = "statementDataList";
    private static final String DOWNLOAD_SUCCESS_TOAST = "downloadSuccessToast";

    // Financial documents specific locators
    private static final String DOCUMENT_TYPE_DROPDOWN = "documentTypeDropdown";
    private static final String DOCUMENT_TYPE_OPTION = "documentTypeOption";
    private static final String REQUEST_DOCUMENTS_BTN = "requestDocumentsBtn";
    private static final String PREPARING_FILES_TOAST = "preparingFilesToast";
    private static final String REQUESTED_DOCUMENTS_LIST = "requestedDocumentsList";
    private static final String DOWNLOAD_DOCUMENT_BTN = "downloadDocumentBtn";
    private static final String DOWNLOAD_MONTH_BTN = "dateTypeDropdown";
    private static final String DOCUMENTS_NOT_AVAILABLE_TOAST = "documentsNotAvailableToast";
    private static final String LEDGER_STATEMENT_FROM = "ledgerStatementFrom";
    private static final String LEDGER_STATEMENT_TO = "ledgerStatementTo";

    // Flatpickr calendar locators
    private static final String FLATPICKR_CALENDAR_OPEN = "flatpickrCalendarOpen";
    private static final String FLATPICKR_MONTH_DROPDOWN = "flatpickrMonthDropdown";
    private static final String FLATPICKR_DAY_BY_LABEL = "flatpickrDayByLabel";
    private static final String FLATPICKR_YEAR_INPUT = "flatpickrYearInput";
    private static final String FLATPICKR_PREV_MONTH_BTN = "flatpickrPrevMonthBtn";
    private static final String FLATPICKR_NEXT_MONTH_BTN = "flatpickrNextMonthBtn";
    private static final String FLATPICKR_CURRENT_MONTH_DAYS = "flatpickrCurrentMonthDays";
    private static final String FLATPICKR_ALL_MONTH_SELECTS = "flatpickrAllMonthSelects";
    private static final String FROM_DATE_INPUT = "fromDateInput";
    private static final String TO_DATE_INPUT = "toDateInput";
    private static final String FROM_DATE_INPUT_ALL = "fromDateInputAll";
    private static final String TO_DATE_INPUT_ALL = "toDateInputAll";
    private static final String LEDGER_PAGE_LOAD_CHECK = "ledgerPageLoadCheck";
    private static final String PAYMENT_INSTRUCTIONS_PAGE_HEADER = "paymentInstructionsPageHeader";
    private static final String FINANCIAL_DOCS_CONTAINER = "financialDocsContainer";
    private static final String TOAST_MESSAGE = "toastMessage";
    private static final String VALIDATION_MESSAGE = "validationMessage";

    // ─────────────────────────────────────────────────────────────────────────
    // Navigation
    // ─────────────────────────────────────────────────────────────────────────

    @And("user navigates to ledger page")
    public void navigateToLedgerPage() {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));

            // Try closing payment reminder popup if visible
            try {
                List<WebElement> closeBtns = Grid.driver().findElements(byFor(PAYMENT_REMINDER_CLOSE_BTN));
                for (WebElement btn : closeBtns) {
                    if (btn.isDisplayed()) {
                        log.info("Payment reminder popup detected. Attempting to click close button.");
                        js.executeScript("arguments[0].click();", btn);
                        WebDriverWait shortWait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(3));
                        shortWait.until(ExpectedConditions.invisibilityOf(btn));
                        log.info("Payment reminder popup closed successfully.");
                        break;
                    }
                }
            } catch (Exception e) {
                log.info("No payment reminder popup to close: " + e.getMessage());
            }

            // Try clicking the Ledger bottom nav tab first
            List<WebElement> ledgerTabs = Grid.driver().findElements(byFor(LEDGER_TAB));
            if (!ledgerTabs.isEmpty() && ledgerTabs.get(0).isDisplayed()) {
                try {
                    waitAndClickOn(LEDGER_TAB);
                } catch (Exception clickEx) {
                    log.warn("Standard click on LEDGER_TAB failed, attempting JavaScript click", clickEx);
                    js.executeScript("arguments[0].click();", ledgerTabs.get(0));
                }
                log.info("Navigated to Ledger via bottom nav tab");
            } else {
                // Direct URL navigation as fallback
                Grid.driver().get("https://qa-ssr.msme.jswone.in/ledger?tab=usable-ledger-balance");
                log.info("Navigated to Ledger via direct URL");
            }
            wait.until(ExpectedConditions.urlContains("ledger"));
        } catch (Exception e) {
            log.error("Failed to navigate to Ledger page");
            throw new RuntimeException("Failed to navigate to Ledger page", e);
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Page Load Validation
    // ─────────────────────────────────────────────────────────────────────────

    @Then("the ledger page should load successfully")
    public void ledgerPageShouldLoadSuccessfully() {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));
            // Verify at least one key ledger element is present
            wait.until(ExpectedConditions.visibilityOfElementLocated(byFor(LEDGER_PAGE_LOAD_CHECK)));
            log.info("Ledger page loaded successfully");
        } catch (Exception e) {
            log.error("Ledger page did not load successfully");
            throw new RuntimeException("Ledger page did not load", e);
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Payments Due Now
    // ─────────────────────────────────────────────────────────────────────────

    @Then("payments due now section should be visible")
    public void paymentsDueNowSectionShouldBeVisible() {
        try {
            waitAndCheckIsElementClickable(PAYMENTS_DUE_NOW_LABEL);
            Assert.assertTrue(
                    isElementVisible(PAYMENTS_DUE_NOW_LABEL),
                    "Payments Due Now section is not visible");
            log.info("Payments Due Now section is visible");
        } catch (Exception e) {
            log.error("Payments Due Now section not visible");
            throw new RuntimeException("Payments Due Now section not visible", e);
        }
    }

    @When("user clicks on ledger download button without selecting date")
    public void clickDownloadWithoutDate() {

        waitAndCheckIsElementVisible(DOWNLOAD_STATEMENT_BTN);
        waitAndClickOn(DOWNLOAD_STATEMENT_BTN);
    }

    @Then("user should see select date range toast message")
    public void validateSelectDateRangeToast() {

        waitAndCheckIsElementVisible("selectDateRangeToast");

        String toastMsg = waitAndGetText("selectDateRangeToast");

        softAssert.assertEquals(
                toastMsg.trim(),
                "Please select date range",
                "Incorrect toast message"
        );
    }

    @Then("payments due now amount should be displayed")
    public void paymentsDueNowAmountShouldBeDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
            // The amount may show "-" when 0 or a numeric value — both are valid displayed
            // states
            WebElement label = wait.until(ExpectedConditions.visibilityOfElementLocated(byFor(PAYMENTS_DUE_NOW_LABEL)));
            Assert.assertNotNull(label, "Payments Due Now label not found");
            log.info("Payments Due Now amount area is displayed");
        } catch (Exception e) {
            log.error("Payments Due Now amount not displayed");
            throw new RuntimeException("Payments Due Now amount not displayed", e);
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Due Later
    // ─────────────────────────────────────────────────────────────────────────

    @Then("due later section should be visible")
    public void dueLaterSectionShouldBeVisible() {
        try {
            waitAndCheckIsElementClickable(DUE_LATER_LABEL);
            Assert.assertTrue(
                    isElementVisible(DUE_LATER_LABEL),
                    "Due Later section is not visible");
            log.info("Due Later section is visible");
        } catch (Exception e) {
            log.error("Due Later section not visible");
            throw new RuntimeException("Due Later section not visible", e);
        }
    }

    @Then("a toast message should appear for documents not available")
    public void toastMessageDocumentsNotAvailable() {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    byFor(DOCUMENTS_NOT_AVAILABLE_TOAST)));
            Assert.assertTrue(
                    toast.getText().contains("Documents not available"),
                    "Toast text did not match. Actual: " + toast.getText());
            log.info("Toast appeared: {}", toast.getText());
        } catch (Exception e) {
            log.error("Documents not available toast did not appear");
            throw new RuntimeException("Documents not available toast did not appear", e);
        }
    }

    @Then("due later amount should be displayed")
    public void dueLaterAmountShouldBeDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
            WebElement label = wait.until(ExpectedConditions.visibilityOfElementLocated(byFor(DUE_LATER_LABEL)));
            Assert.assertNotNull(label, "Due Later label not found");
            log.info("Due Later amount area is displayed");
        } catch (Exception e) {
            log.error("Due Later amount not displayed");
            throw new RuntimeException("Due Later amount not displayed", e);
        }
    }

    @And("user selects date range from {string} {string} {string} to {string} {string} {string}")
    public void userSelectsDateRange(String fromMonth, String fromDay, String fromYear,
                                     String toMonth,   String toDay,   String toYear) {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));

            // ── FROM ──────────────────────────────────────────────────────────
            WebElement fromInput = Grid.driver().findElement(
                    By.cssSelector("input[name='fromDate']"));
            js.executeScript("arguments[0].click();", fromInput);
            sleep(800);

            selectFlatpickrMonthByName(fromMonth);
            sleep(400);
            clickFlatpickrDayByLabel(fromMonth + " " + fromDay + ", " + fromYear);
            sleep(600);

            // ── TO ────────────────────────────────────────────────────────────
            WebElement toInput = Grid.driver().findElement(
                    By.cssSelector("input[name='toDate']"));
            js.executeScript("arguments[0].click();", toInput);
            sleep(800);

            selectFlatpickrMonthByName(toMonth);
            sleep(400);
            clickFlatpickrDayByLabel(toMonth + " " + toDay + ", " + toYear);
            sleep(600);

            // ── Verify ────────────────────────────────────────────────────────
            String fromVal = fromInput.getAttribute("value");
            String toVal   = toInput.getAttribute("value");
            //Assert.assertFalse(fromVal == null || fromVal.isEmpty(), "From date not set");
            //Assert.assertFalse(toVal   == null || toVal.isEmpty(),   "To date not set");
            log.info("Date range set — From: {} | To: {}", fromVal, toVal);

        } catch (Exception e) {
            log.error("Failed to select date range: {}", e.getMessage());
            throw new RuntimeException("Failed to select date range", e);
        }
    }

    /**
     * Selects month in open flatpickr by visible name e.g. "January"
     */
    private void selectFlatpickrMonthByName(String monthName) {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
            WebElement monthSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(".flatpickr-calendar.open select.flatpickr-monthDropdown-months")));
            new Select(monthSelect).selectByVisibleText(monthName);
            log.info("Selected month: {}", monthName);
        } catch (Exception e) {
            throw new RuntimeException("Failed to select month: " + monthName, e);
        }
    }

    /**
     * Clicks day in open flatpickr by aria-label e.g. "January 1, 2026"
     */
    private void clickFlatpickrDayByLabel(String ariaLabel) {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
            WebElement day = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector(".flatpickr-calendar.open .flatpickr-day[aria-label='" + ariaLabel + "']")));
            js.executeScript("arguments[0].click();", day);
            log.info("Clicked day: {}", ariaLabel);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click day: " + ariaLabel, e);
        }
    }

    @Then("usable credit limit section should be visible")
    public void usableCreditLimitSectionShouldBeVisible() {
        try {
            waitAndCheckIsElementClickable(USABLE_CREDIT_LIMIT_LABEL);

            Assert.assertTrue(
                    isElementVisible(USABLE_CREDIT_LIMIT_LABEL),
                    "Usable Credit Limit section not visible");

            log.info("Usable Credit Limit section is visible");

        } catch (Exception e) {
            log.error("Usable Credit Limit section not visible");
            throw new RuntimeException("Usable Credit Limit section not visible", e);
        }
    }

    @Then("usable credit limit amount should be displayed")
    public void usableCreditLimitAmountShouldBeDisplayed() {
        try {
            List<WebElement> elements = Grid.driver().findElements(byFor(USABLE_CREDIT_LIMIT_LABEL));

            Assert.assertFalse(elements.isEmpty(),
                    "Usable Credit Limit amount not displayed");

            log.info("Usable Credit Limit amount displayed");

        } catch (Exception e) {
            log.error("Usable Credit Limit amount not displayed");
            throw new RuntimeException("Usable Credit Limit amount not displayed", e);
        }
    }

    @When("user clicks on usable credit limit section")
    public void userClicksOnUsableCreditLimitSection() {

        scrollToElementAndClick(USABLE_CREDIT_LIMIT_SECTION);

        log.info("Clicked on Usable Credit Limit section");
    }

    @Then("usable credit limit details page should load successfully")
    public void usableCreditLimitDetailsPageShouldLoadSuccessfully() {

        try {

            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("usable-credit-limit"),
                    ExpectedConditions.urlContains("credit")));

            String currentUrl = Grid.driver().getCurrentUrl();

            Assert.assertTrue(
                    currentUrl.contains("usable-credit-limit") ||
                            currentUrl.contains("credit"),
                    "Usable Credit Limit page did not load. URL: " + currentUrl);

            log.info("Usable Credit Limit page loaded successfully: {}", currentUrl);

        } catch (Exception e) {

            log.error("Usable Credit Limit page did not load");

            throw new RuntimeException("Usable Credit Limit page did not load", e);
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Usable Ledger Balance
    // ─────────────────────────────────────────────────────────────────────────

    @Then("usable ledger balance section should be visible")
    public void usableLedgerBalanceSectionShouldBeVisible() {
        try {
            waitAndCheckIsElementClickable(USABLE_LEDGER_BALANCE_LABEL);
            Assert.assertTrue(
                    isElementVisible(USABLE_LEDGER_BALANCE_LABEL),
                    "Usable Ledger Balance section is not visible");
            log.info("Usable Ledger Balance section is visible");
        } catch (Exception e) {
            log.error("Usable Ledger Balance section not visible");
            throw new RuntimeException("Usable Ledger Balance section not visible", e);
        }
    }

    @Then("usable ledger balance amount should be displayed")
    public void usableLedgerBalanceAmountShouldBeDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
            WebElement label = wait
                    .until(ExpectedConditions.visibilityOfElementLocated(byFor(USABLE_LEDGER_BALANCE_LABEL)));
            Assert.assertNotNull(label, "Usable Ledger Balance label not found");
            log.info("Usable Ledger Balance amount area is displayed");
        } catch (Exception e) {
            log.error("Usable Ledger Balance amount not displayed");
            throw new RuntimeException("Usable Ledger Balance amount not displayed", e);
        }
    }

    @Then("view and download ledger link should be visible")
    public void viewAndDownloadLedgerLinkShouldBeVisible() {
        try {
            waitAndCheckIsElementClickable(VIEW_DOWNLOAD_LEDGER_LINK);
            Assert.assertTrue(
                    isElementVisible(VIEW_DOWNLOAD_LEDGER_LINK),
                    "View & Download Ledger link is not visible");
            log.info("View & Download Ledger link is visible");
        } catch (Exception e) {
            log.error("View & Download Ledger link not visible");
            throw new RuntimeException("View & Download Ledger link not visible", e);
        }
    }

    @When("user clicks on view and download ledger link")
    public void userClicksOnViewAndDownloadLedgerLink() {
        scrollToElementAndClick(VIEW_DOWNLOAD_LEDGER_LINK);
        log.info("Clicked on View & Download Ledger link");
    }

    @Then("ledger details page should load successfully")
    public void ledgerDetailsPageShouldLoadSuccessfully() {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains("ledger"));

            String currentUrl = Grid.driver().getCurrentUrl();
            Assert.assertTrue(
                    currentUrl.contains("ledger"),
                    "URL does not contain 'ledger' after clicking view link. Actual URL: " + currentUrl);
            log.info("Ledger details page loaded. URL: {}", currentUrl);
        } catch (Exception e) {
            log.error("Ledger details page did not load");
            throw new RuntimeException("Ledger details page did not load", e);
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Ledger Statement Download Flow
    // ─────────────────────────────────────────────────────────────────────────

    @When("user clicks on usable ledger balance section")
    public void userClicksOnUsableLedgerBalanceSection() {
        scrollToElementAndClick(USABLE_LEDGER_BALANCE_SECTION);
        log.info("Clicked on Usable Ledger Balance section");
    }

    @And("navigates to the ledger statement tab")
    public void navigatesToTheLedgerStatementTab() {
        scrollToElementAndClick(LEDGER_STATEMENT_TAB);
        log.info("Navigated to Ledger Statement tab");
    }

    @And("user selects {string} date range")
    public void userSelectsDateRange(String dateRange) {
        scrollToElementAndClick(DOWNLOAD_MONTH_BTN);
        scrollToElementAndClickParam(DATE_RANGE_OPTION, dateRange);
        log.info("Selected date range: {}", dateRange);
    }

    @And("user selects date range from {string} to {string} from calendar")
    public void userSelectsDateRangeFromCalendar(String fromDateStr, String toDateStr) {
        try {
            LocalDate fromDate = LocalDate.parse(fromDateStr);
            LocalDate toDate = LocalDate.parse(toDateStr);

            log.info("Selecting From: {} → To: {}", fromDate, toDate);

            // ── FROM date ──────────────────────────────────────────────────
            // Find both possible name variants for fromDate, or the Ledger ID
            List<WebElement> fromInputs = Grid.driver().findElements(byFor(FROM_DATE_INPUT_ALL));
            WebElement fromInput = fromInputs.stream().filter(WebElement::isDisplayed).findFirst()
                    .orElseThrow(() -> new RuntimeException("From date input not found"));

            js.executeScript("arguments[0].click();", fromInput);

            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(byFor(FLATPICKR_CALENDAR_OPEN)));

            selectFlatpickrMonth(fromDate.getMonthValue() - 1);

            clickFlatpickrDay(fromDate);

            // ── TO date ────────────────────────────────────────────────────
            List<WebElement> toInputs = Grid.driver().findElements(byFor(TO_DATE_INPUT_ALL));
            WebElement toInput = toInputs.stream().filter(WebElement::isDisplayed).findFirst()
                    .orElseThrow(() -> new RuntimeException("To date input not found"));

            js.executeScript("arguments[0].click();", toInput);

            wait.until(ExpectedConditions.visibilityOfElementLocated(byFor(FLATPICKR_CALENDAR_OPEN)));

            selectFlatpickrMonth(toDate.getMonthValue() - 1);

            clickFlatpickrDay(toDate);

            log.info("Date range set — From: {} | To: {}", fromDateStr, toDateStr);

        } catch (Exception e) {
            log.error("Failed to select date range: {}", e.getMessage());
            throw new RuntimeException("Failed to select date range via Calendar", e);
        }
    }

    @And("selects a date range for the past 3 months")
    public void selectsADateRangeForThePast3Months() {
        try {
            LocalDate today = LocalDate.now();
            LocalDate threeMonthsAgo = today.minusMonths(3);

            log.info("Selecting From: {} → To: {}", threeMonthsAgo, today);

            // ── FROM date ──────────────────────────────────────────────────
            WebElement fromInput = Grid.driver().findElement(byFor(FROM_DATE_INPUT));
            js.executeScript("arguments[0].click();", fromInput);

            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(byFor(FLATPICKR_CALENDAR_OPEN)));

            // Select month in FROM calendar (0-based: Jan=0, Feb=1, Mar=2...)
            selectFlatpickrMonth(threeMonthsAgo.getMonthValue() - 1);

            // Click the day using aria-label e.g. "January 12, 2026"
            clickFlatpickrDay(threeMonthsAgo);

            // ── TO date ────────────────────────────────────────────────────
            WebElement toInput = Grid.driver().findElement(byFor(TO_DATE_INPUT));
            js.executeScript("arguments[0].click();", toInput);

            wait.until(ExpectedConditions.visibilityOfElementLocated(byFor(FLATPICKR_CALENDAR_OPEN)));

            // Select month in TO calendar
            selectFlatpickrMonth(today.getMonthValue() - 1);

            // Click today's day
            clickFlatpickrDay(today);

            // ── Verify values were set ─────────────────────────────────────
            String fromVal = fromInput.getAttribute("value");
            String toVal = toInput.getAttribute("value");

            Assert.assertFalse(fromVal == null || fromVal.isEmpty(),
                    "From date input is empty after selection");
            Assert.assertFalse(toVal == null || toVal.isEmpty(),
                    "To date input is empty after selection");

            log.info("Date range set — From: {} | To: {}", fromVal, toVal);

        } catch (Exception e) {
            log.error("Failed to select date range: {}", e.getMessage());
            throw new RuntimeException("Failed to select date range via Calendar", e);
        }
    }

    /**
     * Navigates the currently open flatpickr calendar to the target year + month.
     * Uses the prev/next arrow buttons to move month by month.
     */
    private void navigateFlatpickrToMonth(int targetYear, int targetMonth) {
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));

        // Wait for calendar to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(byFor(FLATPICKR_CALENDAR_OPEN)));

        for (int attempts = 0; attempts < 24; attempts++) {
            // Current month shown (0-based select value)
            WebElement monthSelect = Grid.driver().findElement(byFor(FLATPICKR_MONTH_DROPDOWN));
            int currentMonth = Integer.parseInt(monthSelect.getAttribute("value")) + 1; // convert to 1-based

            // Current year shown
            WebElement yearInput = Grid.driver().findElement(byFor(FLATPICKR_YEAR_INPUT));
            int currentYear = Integer.parseInt(yearInput.getAttribute("value"));

            if (currentYear == targetYear && currentMonth == targetMonth) {
                log.info("Calendar is on target month: {}/{}", targetMonth, targetYear);
                return; // already on the right month
            }

            // Decide direction — go prev or next
            boolean goBack = (currentYear > targetYear)
                    || (currentYear == targetYear && currentMonth > targetMonth);

            if (goBack) {
                WebElement prevBtn = Grid.driver().findElement(byFor(FLATPICKR_PREV_MONTH_BTN));
                js.executeScript("arguments[0].click();", prevBtn);
            } else {
                WebElement nextBtn = Grid.driver().findElement(byFor(FLATPICKR_NEXT_MONTH_BTN));
                js.executeScript("arguments[0].click();", nextBtn);
            }

            // Using a short wait here to ensure DOM updates
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.numberOfElementsToBe(byFor(FLATPICKR_MONTH_DROPDOWN), 1),
                    ExpectedConditions.presenceOfElementLocated(byFor(FLATPICKR_MONTH_DROPDOWN))));
        }

        throw new RuntimeException(
                "Could not navigate flatpickr to " + targetMonth + "/" + targetYear + " after 24 attempts");
    }

    private void selectFlatpickrMonth(int monthIndex) {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));

            // Wait for the open calendar's month dropdown
            WebElement monthSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    byFor(FLATPICKR_MONTH_DROPDOWN)));

            Select select = new Select(monthSelect);
            select.selectByValue(String.valueOf(monthIndex));

            log.info("Selected month index {} in flatpickr", monthIndex);
        } catch (Exception e) {
            log.error("Failed to select month in flatpickr: {}", e.getMessage());
            throw new RuntimeException("Failed to select month in flatpickr", e);
        }
    }

    private void clickFlatpickrDay(LocalDate date) {
        try {
            // Build aria-label exactly as flatpickr renders it e.g. "January 12, 2026"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy",
                    java.util.Locale.ENGLISH);
            String ariaLabel = date.format(formatter);

            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));

            // Find the day span in the open calendar by aria-label
            WebElement day = wait.until(ExpectedConditions.elementToBeClickable(
                    byFor(FLATPICKR_DAY_BY_LABEL, ariaLabel)));

            js.executeScript("arguments[0].click();", day);
            log.info("Clicked flatpickr day: {}", ariaLabel);

        } catch (Exception e) {
            log.error("Failed to click day {} in flatpickr: {}", date, e.getMessage());
            throw new RuntimeException("Failed to click day in flatpickr: " + date, e);
        }
    }

    /**
     * Clicks a specific day number in the currently open flatpickr calendar.
     * Skips days from previous/next month (flatpickr marks them with
     * prevMonthDay/nextMonthDay).
     */
    private void clickCalendarDay(int day) {
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(byFor(FLATPICKR_CALENDAR_OPEN)));

        // Get all day spans that belong to the current month (not prev/next month
        // overflow)
        List<WebElement> days = Grid.driver().findElements(byFor(FLATPICKR_CURRENT_MONTH_DAYS));

        for (WebElement dayEl : days) {
            String ariaLabel = dayEl.getAttribute("aria-label"); // e.g. "March 12, 2026"
            String text = dayEl.getText().trim();

            if (text.equals(String.valueOf(day))) {
                js.executeScript("arguments[0].click();", dayEl);
                log.info("Clicked calendar day: {} (aria-label: {})", day, ariaLabel);
                return;
            }
        }

        throw new RuntimeException("Day " + day + " not found in open flatpickr calendar");
    }

    // Helper: selects the visible flatpickr month dropdown option by 0-based index
    private void selectMonthInCalendar(int monthIndex) {
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
        try {
            // Find visible month select(s) and pick the first displayed one
            List<WebElement> selects = Grid.driver()
                    .findElements(byFor(FLATPICKR_ALL_MONTH_SELECTS));
            WebElement visibleSelect = selects.stream().filter(WebElement::isDisplayed).findFirst()
                    .orElseThrow(() -> new RuntimeException("Month dropdown not found"));

            // Use Selenium Select to pick by value first (flatpickr uses 0-based values),
            // fallback to visible text
            Select select = new Select(visibleSelect);
            String value = String.valueOf(monthIndex);
            try {
                select.selectByValue(value);
            } catch (Exception ex) {
                // Fallback: select by visible month name
                select.selectByVisibleText(monthIndexToName(monthIndex));
            }
            log.info("Selected month {} in calendar (index {})", monthIndexToName(monthIndex), monthIndex);
        } catch (Exception e) {
            log.error("Failed to select month in calendar: {}", e.getMessage());
            throw new RuntimeException("Failed to select month in calendar", e);
        }
    }

    // Helper: convert 0-based month index to English month name used by the
    // dropdown
    private String monthIndexToName(int monthIndex) {
        switch (monthIndex) {
            case 0:
                return "January";
            case 1:
                return "February";
            case 2:
                return "March";
            case 3:
                return "April";
            case 4:
                return "May";
            case 5:
                return "June";
            case 6:
                return "July";
            case 7:
                return "August";
            case 8:
                return "September";
            case 9:
                return "October";
            case 10:
                return "November";
            case 11:
                return "December";
            default:
                return String.valueOf(monthIndex);
        }
    }

    @Then("ledger statement data should be displayed")
    public void ledgerStatementDataShouldBeDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(byFor(STATEMENT_DATA_LIST)));
            log.info("Ledger statement data is displayed on screen");
        } catch (Exception e) {
            log.error("Ledger statement data not displayed");
            throw new RuntimeException("Ledger statement data not displayed", e);
        }
    }

    @When("user clicks on download statement button")
    public void userClicksOnDownloadStatementButton() {
        scrollToElementAndClick(DOWNLOAD_STATEMENT_BTN);
        log.info("Clicked on Download statement button");
    }

    @Then("a success toast message should appear for download")
    public void aSuccessToastMessageShouldAppearForDownload() {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(byFor(DOWNLOAD_SUCCESS_TOAST)));
            Assert.assertTrue(toast.getText().contains("successfully") || toast.getText().contains("download"),
                    "Toast message did not match expected success text");
            log.info("Success toast message appeared: {}", toast.getText());
        } catch (Exception e) {
            log.error("Download success toast message did not appear");
            throw new RuntimeException("Download success toast message did not appear", e);
        }
    }

    @Then("a error toast message should appear for download")
    public void aErrorToastMessageShouldAppearForDownload() {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(byFor("errorToast")));
            log.info("Error toast message appeared for download: {}", toast.getText());
        } catch (Exception e) {
            log.error("Error toast message did not appear for download");
            throw new RuntimeException("Error toast message did not appear for download", e);
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Financial Documents Flow
    // ─────────────────────────────────────────────────────────────────────────
    @And("user selects {string} document type")
    public void userSelectsDocumentType(String docType) {
        scrollToElementAndClick(DOCUMENT_TYPE_DROPDOWN);
        scrollToElementAndClickParam(DOCUMENT_TYPE_OPTION, docType);
        log.info("Selected document type: {}", docType);
    }

    @And("user clicks on request documents button")
    public void userClicksOnRequestDocumentsButton() {
        scrollToElementAndClick(REQUEST_DOCUMENTS_BTN);
        log.info("Clicked on Request documents button");
    }

    @Then("requested documents should be preparing for download")
    public void requestedDocumentsShouldBePreparingForDownload() {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(byFor(PREPARING_FILES_TOAST)));
            log.info("Preparing files for download toast appeared: {}", toast.getText());

            // Wait for it to disappear, meaning processing is done
            wait.until(ExpectedConditions.invisibilityOfElementLocated(byFor(PREPARING_FILES_TOAST)));
            log.info("Preparing files loader disappeared");
        } catch (Exception e) {
            log.warn("Preparing files loader did not appear or disappear as expected, continuing anyway.", e);
        }
    }

    @Then("requested documents list should be displayed")
    public void requestedDocumentsListShouldBeDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(byFor(REQUESTED_DOCUMENTS_LIST)));
            log.info("Requested documents list is displayed on screen");
        } catch (Exception e) {
            log.error("Requested documents list not displayed");
            throw new RuntimeException("Requested documents list not displayed", e);
        }
    }

    @When("user clicks on download document button")
    public void userClicksOnDownloadDocumentButton() {
        scrollToElementAndClick(DOWNLOAD_DOCUMENT_BTN);
        log.info("Clicked on Download document button");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Other Actions
    // ─────────────────────────────────────────────────────────────────────────

    @Then("other actions section should be visible")
    public void otherActionsSectionShouldBeVisible() {
        try {
            waitAndCheckIsElementClickable(OTHER_ACTIONS_LABEL);
            Assert.assertTrue(
                    isElementVisible(OTHER_ACTIONS_LABEL),
                    "Other Actions section is not visible");
            log.info("Other Actions section is visible");
        } catch (Exception e) {
            log.error("Other Actions section not visible");
            throw new RuntimeException("Other Actions section not visible", e);
        }
    }

    @Then("payment instructions option should be visible")
    public void paymentInstructionsOptionShouldBeVisible() {
        try {
            waitAndCheckIsElementClickable(PAYMENT_INSTRUCTIONS_OPTION);
            Assert.assertTrue(
                    isElementVisible(PAYMENT_INSTRUCTIONS_OPTION),
                    "Payment Instructions option is not visible");
            log.info("Payment Instructions option is visible");
        } catch (Exception e) {
            log.error("Payment Instructions option not visible");
            throw new RuntimeException("Payment Instructions option not visible", e);
        }
    }

    @Then("financial documents option should be visible")
    public void financialDocumentsOptionShouldBeVisible() {
        try {
            waitAndCheckIsElementClickable(FINANCIAL_DOCUMENTS_OPTION);
            Assert.assertTrue(
                    isElementVisible(FINANCIAL_DOCUMENTS_OPTION),
                    "Financial Documents option is not visible");
            log.info("Financial Documents option is visible");
        } catch (Exception e) {
            log.error("Financial Documents option not visible");
            throw new RuntimeException("Financial Documents option not visible", e);
        }
    }

    @Then("help and support option should be visible")
    public void helpAndSupportOptionShouldBeVisible() {
        try {
            waitAndCheckIsElementClickable(HELP_AND_SUPPORT_OPTION);
            Assert.assertTrue(
                    isElementVisible(HELP_AND_SUPPORT_OPTION),
                    "Help & Support option is not visible");
            log.info("Help & Support option is visible");
        } catch (Exception e) {
            log.error("Help & Support option not visible");
            throw new RuntimeException("Help & Support option not visible", e);
        }
    }

    @When("user clicks on payment instructions")
    public void userClicksOnPaymentInstructions() {
        scrollToElementAndClick(PAYMENT_INSTRUCTIONS_OPTION);
        log.info("Clicked on Payment Instructions");
    }

    @When("user clicks on payments due now section")
    public void userClicksOnPaymentsDueNowSection() {
        scrollToElementAndClick(PAYMENTS_DUE_NOW_SECTION);
        log.info("Clicked on Payments Due Now section");
    }

    @Then("user should be redirected to payments due now tab")
    @Then("payments due now details page should load successfully")
    public void paymentsDueNowDetailsPageShouldLoadSuccessfully() {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("due-now"),
                    ExpectedConditions.urlContains("payments")));

            String currentUrl = Grid.driver().getCurrentUrl();

            Assert.assertTrue(
                    currentUrl.contains("due-now") || currentUrl.contains("payments"),
                    "Payments Due Now page did not open. Current URL: " + currentUrl);

            log.info("Payments Due Now details page loaded successfully: {}", currentUrl);

        } catch (Exception e) {
            log.error("Payments Due Now page did not load");
            throw new RuntimeException("Payments Due Now page did not load", e);
        }
    }

    @When("user clicks on due later section")
    public void userClicksOnDueLaterSection() {
        scrollToElementAndClick(DUE_LATER_SECTION);
        log.info("Clicked on Due Later section");
    }

    @Then("user should be redirected to due later tab")
    @Then("due later details page should load successfully")
    public void dueLaterDetailsPageShouldLoadSuccessfully() {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("due-later"),
                    ExpectedConditions.urlContains("ledger")));

            String currentUrl = Grid.driver().getCurrentUrl();

            Assert.assertTrue(
                    currentUrl.contains("due-later") || currentUrl.contains("ledger"),
                    "Due Later page did not open. Current URL: " + currentUrl);

            log.info("Due Later details page loaded successfully: {}", currentUrl);

        } catch (Exception e) {
            log.error("Due Later page did not load");
            throw new RuntimeException("Due Later page did not load", e);
        }
    }

    @Then("payment instructions page should load successfully")
    public void paymentInstructionsPageShouldLoadSuccessfully() {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(byFor(PAYMENT_INSTRUCTIONS_PAGE_HEADER)));
            log.info("Payment Instructions page loaded");
        } catch (Exception e) {
            log.error("Payment Instructions page did not load");
            throw new RuntimeException("Payment Instructions page did not load", e);
        }
    }

    @When("user clicks on financial documents")
    public void userClicksOnFinancialDocuments() {
        scrollToElementAndClick(FINANCIAL_DOCUMENTS_OPTION);
        log.info("Clicked on Financial Documents");
    }

    @Then("financial documents page should load successfully")
    public void financialDocumentsPageShouldLoadSuccessfully() {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
            // Use the specific container class visible in the DOM
            wait.until(ExpectedConditions.presenceOfElementLocated(byFor(FINANCIAL_DOCS_CONTAINER)));
            log.info("Financial Documents page loaded");
        } catch (Exception e) {
            log.error("Financial Documents page did not load");
            throw new RuntimeException("Financial Documents page did not load", e);
        }
    }

    @Then("a message {string} should be visible")
    public void aMessageShouldBeVisible(String message) {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));

            // Convert to lowercase for case-insensitive matching
            String lowerMessage = message.toLowerCase();

            // Use YAML-defined toast message locator with parameterized XPath
            wait.until(ExpectedConditions.presenceOfElementLocated(byFor(TOAST_MESSAGE, lowerMessage)));

            log.info("Message visible: {}", message);
        } catch (Exception e) {
            log.error("Message not visible: {}", message);
            throw new RuntimeException("Message not visible: " + message, e);
        }
    }

    @Then("no transactions message should be visible")
    public void noTransactionsMessageShouldBeVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(byFor(NO_TRANSACTIONS_MESSAGE)));
            log.info("No transactions message is visible");
        } catch (Exception e) {
            log.error("No transactions message not visible");
            throw new RuntimeException("No transactions message not visible", e);
        }
    }

    @Then("an error toast message should appear")
    public void anErrorToastMessageShouldAppear() {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(byFor("errorToast")));
            log.info("Error toast message appeared: {}", toast.getText());
        } catch (Exception e) {
            log.error("Error toast message did not appear");
            throw new RuntimeException("Error toast message did not appear", e);
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Bottom Navigation Validation
    // ─────────────────────────────────────────────────────────────────────────

    @Then("a validation message {string} should be visible")
    public void aValidationMessageShouldBeVisible(String message) {
        try {
            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(byFor(VALIDATION_MESSAGE, message)));
            log.info("Validation message visible: {}", message);
        } catch (Exception e) {
            log.error("Validation message not visible: {}", message);
            throw new RuntimeException("Validation message not visible: " + message, e);
        }
    }

    @Then("the ledger tab should be active in bottom navigation")
    public void ledgerTabShouldBeActiveInBottomNavigation() {
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(byFor(LEDGER_TAB)));
            log.info("Ledger tab is present in bottom navigation");
        } catch (Exception e) {
            log.error("Ledger tab not found in bottom navigation");
            throw new RuntimeException("Ledger tab not found in bottom navigation", e);
        }
    }

    @Then("home tab should be visible in bottom navigation")
    public void homeTabShouldBeVisibleInBottomNavigation() {
        try {
            Assert.assertTrue(isElementVisible(HOME_TAB), "Home tab not visible in bottom navigation");
            log.info("Home tab is visible in bottom navigation");
        } catch (Exception e) {
            log.error("Home tab not visible");
            throw new RuntimeException("Home tab not visible in bottom navigation", e);
        }
    }

    @Then("catalogue tab should be visible in bottom navigation")
    public void catalogueTabShouldBeVisibleInBottomNavigation() {
        try {
            Assert.assertTrue(isElementVisible(CATALOGUE_TAB), "Catalogue tab not visible in bottom navigation");
            log.info("Catalogue tab is visible in bottom navigation");
        } catch (Exception e) {
            log.error("Catalogue tab not visible");
            throw new RuntimeException("Catalogue tab not visible in bottom navigation", e);
        }
    }

    @Then("cart tab should be visible in bottom navigation")
    public void cartTabShouldBeVisibleInBottomNavigation() {
        try {
            Assert.assertTrue(isElementVisible(CART_TAB), "Cart tab not visible in bottom navigation");
            log.info("Cart tab is visible in bottom navigation");
        } catch (Exception e) {
            log.error("Cart tab not visible");
            throw new RuntimeException("Cart tab not visible in bottom navigation", e);
        }
    }

    @Then("order tab should be visible in bottom navigation")
    public void orderTabShouldBeVisibleInBottomNavigation() {
        try {
            Assert.assertTrue(isElementVisible(ORDER_TAB), "Order tab not visible in bottom navigation");
            log.info("Order tab is visible in bottom navigation");
        } catch (Exception e) {
            log.error("Order tab not visible");
            throw new RuntimeException("Order tab not visible in bottom navigation", e);
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Helper
    // ─────────────────────────────────────────────────────────────────────────

    private void scrollToElementAndClick(String locatorKey) {
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));
        try {
            By by = byFor(locatorKey);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));

            // Scroll into view
            ((JavascriptExecutor) Grid.driver()).executeScript(
                    "arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
                    element);

            // Wait until visible and clickable
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));

            try {
                element.click();
            } catch (Exception e) {
                log.warn("Standard click failed for {}, attempting JS click", locatorKey);
                ((JavascriptExecutor) Grid.driver()).executeScript("arguments[0].click();", element);
            }
            log.info("Successfully clicked on ledger element: {}", locatorKey);
        } catch (Exception e) {
            log.error("Failed to click on ledger element: {} | Error: {}", locatorKey, e.getMessage());
            throw new RuntimeException("Element not clickable: " + locatorKey, e);
        }
    }

    private void scrollToElementAndClickParam(String locatorKey, String param) {
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));
        try {
            By by = byFor(locatorKey, param);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));

            // Scroll into view
            ((JavascriptExecutor) Grid.driver()).executeScript(
                    "arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
                    element);

            // Wait until visible and clickable
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));

            try {
                element.click();
            } catch (Exception e) {
                log.warn("Standard click failed for {}({}), attempting JS click", locatorKey, param);
                ((JavascriptExecutor) Grid.driver()).executeScript("arguments[0].click();", element);
            }
            log.info("Successfully clicked on ledger element: {}({})", locatorKey, param);
        } catch (Exception e) {
            log.error("Failed to click on ledger element: {}({}) | Error: {}", locatorKey, param, e.getMessage());
            throw new RuntimeException("Element not clickable: " + locatorKey, e);
        }
    }

    private boolean isElementVisible(String locatorKey) {
        try {
            List<WebElement> elements = Grid.driver().findElements(byFor(locatorKey));
            return !elements.isEmpty() && elements.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }

    private By byFor(String key) {
        String raw = locatorReader.getXpath("elements." + key + ".en_US.web");
        if (raw == null)
            raw = locatorReader.getXpath(key);
        if (raw != null && raw.startsWith("css="))
            return By.cssSelector(raw.substring(4));
        return By.xpath(raw != null ? raw : "//" + key);
    }

    private By byFor(String key, String param) {
        String raw = locatorReader.getXpath("elements." + key + ".en_US.web");
        if (raw == null)
            raw = locatorReader.getXpath(key);
        if (raw != null)
            raw = raw.replace("{0}", param);
        if (raw != null && raw.startsWith("css="))
            return By.cssSelector(raw.substring(4));
        return By.xpath(raw != null ? raw : "//" + key);
    }
}
