package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.ApiUtils;
import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.qapitol.jsw.stepdefinition.BuyAgainSteps.*;
import static com.qapitol.jsw.utils.CustomUtil.*;
import static com.qapitol.jsw.utils.Hooks.softAssert;

/** Desktop ledger page steps. The msite ledger lives in {@code mledgersteps}. */
public class LedgerSteps extends AbstractWebPage {

    private static final String HELP_AND_SUPPORT_BTN = "//button[normalize-space()='Help & Support']";
    private static final String LEDGER_PATH = "/ledger";
    private static final String FINANCIAL_DOCUMENTS_PATH = "/financial-documents";
    /** A row's Order ID button, filled with that row's orderNumber from the API response. */
    private static final String ORDER_ID_BUTTON = "//button[normalize-space()=%s]";
    private static final String LOAD_MORE_BUTTON = "//button[@data-test-id='ledger-table-load-more-button']";
    /** Safety cap: 603 transactions at even 10 a page is 61 clicks. */
    private static final int MAX_LOAD_MORE_CLICKS = 80;
    private static final int MAX_SCROLL_STEPS = 40;
    private static final int SCROLL_STEP_PX = 400;
    /** How long to look for the transacting entity dropdown before treating the ledger as JOPL only. */
    private static final int DROPDOWN_WAIT_SECONDS = 15;
    private static final String TRANSACTING_ENTITY_SELECT =
            "//select[@data-test-id='ledger-filter-select-transacting-entity']";
    /** API entityName / appName to the option shown in the ledger's transacting entity filter. */
    private static final Map<String, String> TRANSACTING_ENTITIES = Map.of(
            "JOPL", "JSW One Platforms Limited",
            "JODL", "JSW One Distribution Limited");
    private static final String USABLE_CREDIT_LIMIT_LABEL = "//*[contains(text(),'Usable credit limit')]";
    /** customerCreditInfoSROS field -> the label its amount is printed next to in a credit line card. */
    private static final Map<String, String> CREDIT_LINE_FIELDS = new java.util.LinkedHashMap<>();
    static {
        CREDIT_LINE_FIELDS.put("creditSanctioned", "Sanctioned limit");
        CREDIT_LINE_FIELDS.put("creditBlocked", "Limit blocked");
        CREDIT_LINE_FIELDS.put("creditUtilised", "Limit utilised");
        CREDIT_LINE_FIELDS.put("creditUsable", "Usable limit");
    }
    /** "Closing <span>balance</span>" label on the Usable ledger balance tab. */
    private static final String CLOSING_BALANCE =
            "//span[contains(text(),'Closing') and span[normalize-space()='balance']]";
    private static final String CLOSING_BALANCE_AMOUNT = "./span[contains(text(),'₹')]";
    /** Enough half-width steps to cross even a very wide table. */
    private static final int MAX_HORIZONTAL_STEPS = 20;
    /** Nearest ancestor that scrolls horizontally (overflow-x auto/scroll and wider than it shows). */
    private static final String FIND_HORIZONTAL_SCROLLER =
            "var el = arguments[0].parentElement;"
                    + " while (el && el !== document.body) {"
                    + "   var o = getComputedStyle(el).overflowX;"
                    + "   if ((o === 'auto' || o === 'scroll') && el.scrollWidth > el.clientWidth) { return el; }"
                    + "   el = el.parentElement;"
                    + " }"
                    + " return null;";
    /** Rupee amount cells inside a row; the last one is the pending amount. */
    private static final String PENDING_AMOUNT_IN_ROW = ".//*[contains(text(),'₹')]";
    private static final Pattern RUPEE_AMOUNT = Pattern.compile("₹\\s*([0-9][0-9,]*(?:\\.[0-9]+)?)");
    /** Ancestor levels to climb from a document number looking for its row. */
    private static final int MAX_ROW_DEPTH = 8;
    private static final List<DateTimeFormatter> DUE_DATE_FORMATS = List.of(
            DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("dd MMM, yyyy", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    /** The OMS due payments response fetched by this thread's scenario. */
    private static final ThreadLocal<JsonPath> DUE_PAYMENTS = new ThreadLocal<>();
    /** The ledger service transactions response fetched by this thread's scenario. */
    private static final ThreadLocal<JsonPath> LEDGER_TRANSACTIONS = new ThreadLocal<>();
    /** Transacting entity being validated by a per-entity step, for failure messages. */
    private static final ThreadLocal<String> CURRENT_ENTITY = new ThreadLocal<>();
    /** Load more clicks that timed out in the table currently being loaded, for later failure messages. */
    private static final ThreadLocal<List<String>> LOAD_MORE_TIMEOUTS = ThreadLocal.withInitial(ArrayList::new);
    /** The OMS credit info response fetched by this thread's scenario. */
    private static final ThreadLocal<com.fasterxml.jackson.databind.JsonNode> CREDIT_INFO = new ThreadLocal<>();

    @Then("verify Help & Support button is displayed and clickable")
    public void verifyHelpAndSupportButtonIsDisplayedAndClickable() {
        By helpAndSupport = By.xpath(HELP_AND_SUPPORT_BTN);
        log.info("Verifying 'Help & Support' button is displayed.");
        // The visible copy only: the header/footer render hidden 0x0 duplicates of their buttons.
        WebElement button;
        try {
            button = firstVisible(helpAndSupport);
        } catch (TimeoutException e) {
            attachTimeoutEvidence("Help & Support not displayed",
                    "No visible Help & Support button within 15s at " + Grid.driver().getCurrentUrl() + ".");
            softAssert.fail("Help & Support button is not displayed.");
            return;
        }

        log.info("Verifying 'Help & Support' button is clickable.");
        try {
            new WebDriverWait(Grid.driver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(button));
        } catch (TimeoutException e) {
            attachTimeoutEvidence("Help & Support not clickable",
                    "Help & Support is visible but did not become clickable within 10s (enabled="
                            + enabledState(button) + ") at " + Grid.driver().getCurrentUrl() + ".");
            softAssert.fail("Help & Support button is not clickable within 10 seconds.");
        }
    }

    @Then("verify user is on ledger page")
    public void verifyUserIsOnLedgerPage() {
        waitForPath(LEDGER_PATH);
        softAssert.assertTrue(isOnPath(LEDGER_PATH),
                "User is not on the ledger page. Current path: " + currentPath());
    }

    @Then("verify user is redirected to financial documents page")
    public void verifyUserIsRedirectedToFinancialDocumentsPage() {
        waitForPath(FINANCIAL_DOCUMENTS_PATH);
        softAssert.assertTrue(isOnPath(FINANCIAL_DOCUMENTS_PATH),
                "User is not redirected to the financial documents page. Current path: " + currentPath());
    }

    @And("user navigates back to ledger page")
    public void userNavigatesBackToLedgerPage() {
        log.info("Navigating back from financial documents to ledger.");
        Grid.driver().navigate().back();
        waitForPath(LEDGER_PATH);
    }

    @And("user opens ledger page through direct url")
    public void userOpensLedgerPageThroughDirectUrl() {
        String ledgerUrl = ConfigUtil.resolveOrThrow("ledgerUrl");
        log.info("Opening ledger page directly: " + ledgerUrl);
        Grid.driver().get(ledgerUrl);
        isAnyVisible(By.xpath(PAYMENT_INST));
        // A guest is bounced from the ledger to the home page ("/?forbidden=1" on QA); log where
        // the browser actually landed so the report shows it.
        log.info("After opening the ledger url the browser is on " + Grid.driver().getCurrentUrl());
    }

    @And("user refreshes the ledger page")
    public void userRefreshesTheLedgerPage() {
        log.info("Refreshing ledger page.");
        refreshAndWaitForPageLoad();
        isAnyVisible(By.xpath(PAYMENT_INST));
    }

    @When("user fetches due payments from OMS for GSTIN {string} with entityName {string}, dueInDays {string} and dueLater {string}")
    public void userFetchesDuePaymentsFromOms(String gstin, String entityName, String dueInDays, String dueLater) {
        requireLoggedInGstin(gstin);
        Response response = ApiUtils.fetchDuePayments(gstin, entityName, Integer.parseInt(dueInDays),
                Boolean.parseBoolean(dueLater));
        if (response.getStatusCode() != 200) {
            throw new IllegalStateException("OMS customer-due-payments-list returned HTTP "
                    + response.getStatusCode() + " for GSTIN " + ApiUtils.maskId(gstin) + ": " + ApiUtils.maskIdsInJson(response.asString()));
        }
        DUE_PAYMENTS.set(exactJson(response));
        assertNoDuplicateRecords("GET " + ConfigUtil.resolveOrThrow("omsBaseUrl")
                        + "joms/api/internal/v1/customer-due-payments-list/" + ApiUtils.maskId(gstin) + "?entityName=" + entityName
                        + "&dueInDays=" + dueInDays + "&limit=10000&offset=0&dueLater=" + dueLater,
                response.asString(), "data", "details");
        log.info("OMS due payments for " + ApiUtils.maskId(gstin) + ": totalAmount=" + DUE_PAYMENTS.get().get("data.totalAmount")
                + ", entries=" + response.jsonPath().getList("data.details").size());
    }

    @When("user fetches ledger transactions from ledger service for GSTIN {string} with appName {string}")
    public void userFetchesLedgerTransactionsFromLedgerService(String gstin, String appName) {
        requireLoggedInGstin(gstin);
        Response response = ApiUtils.fetchLedgerTransactions(gstin, appName);
        if (response.getStatusCode() != 200) {
            throw new IllegalStateException("Ledger service fetch-ledger-transaction returned HTTP "
                    + response.getStatusCode() + " for GSTIN " + ApiUtils.maskId(gstin) + ": " + ApiUtils.maskIdsInJson(response.asString()));
        }
        LEDGER_TRANSACTIONS.set(exactJson(response));
        assertNoDuplicateRecords("GET " + ConfigUtil.resolveOrThrow("omsBaseUrl")
                        + "ledger-service/api/internal/v1/fetch-ledger-transaction?gstin=" + ApiUtils.maskId(gstin) + "&appName=" + appName,
                response.asString(), "data", "transactionData");
        log.info("Ledger transactions for " + ApiUtils.maskId(gstin) + ": balanceAmount=" + LEDGER_TRANSACTIONS.get().get("data.balanceAmount")
                + ", totalCount=" + response.jsonPath().get("data.totalCount"));
    }

    /**
     * Picks the ledger's transacting entity so the page shows the same entity the API was
     * queried for. {@code entity} is the API's entityName / appName: JOPL or JODL.
     */
    @And("user selects transacting entity {string} on ledger page")
    public void userSelectsTransactingEntityOnLedgerPage(String entity) {
        selectTransactingEntity(entity);
    }

    /**
     * Payments due now / Due later against OMS, for every transacting entity the ledger offers,
     * for the logged-in customer's GSTIN. Nothing is fetched until the ledger page is open: the
     * entities to check depend on it.
     */
    @Then("verify {string} entries from API are displayed on ledger page for each transacting entity with dueInDays {string} and dueLater {string}")
    public void verifyDueEntriesForEachTransactingEntity(String section, String dueInDays, String dueLater) {
        String gstin = loggedInGstin();
        for (String entity : transactingEntitiesOnPage()) {
            CURRENT_ENTITY.set(entity);
            try {
                log.info("=== " + section + " for " + entity + " ===");
                selectEntityIfOffered(entity);
                openDueSection(section);
                userFetchesDuePaymentsFromOms(gstin, entity, dueInDays, dueLater);
                verifyEntriesFromApiAreDisplayedOnLedgerPage(section + " [" + entity + "]");
            } finally {
                CURRENT_ENTITY.remove();
            }
        }
    }

    /**
     * Usable ledger balance and the latest transactions, for every transacting entity the ledger
     * offers, for the logged-in customer's GSTIN.
     */
    @Then("verify usable ledger balance and latest {string} ledger transactions from API are displayed on ledger page for each transacting entity")
    public void verifyUsableLedgerBalanceForEachTransactingEntity(String count) {
        String gstin = loggedInGstin();
        for (String entity : transactingEntitiesOnPage()) {
            CURRENT_ENTITY.set(entity);
            try {
                log.info("=== Usable ledger balance for " + entity + " ===");
                selectEntityIfOffered(entity);
                userOpensUsableLedgerBalanceSectionOnLedgerPage();
                userFetchesLedgerTransactionsFromLedgerService(gstin, entity);
                verifyUsableLedgerBalanceFromApiIsDisplayedOnLedgerPage();
                verifyLatestLedgerTransactionsFromApiAreDisplayedOnLedgerPage(count);
            } finally {
                CURRENT_ENTITY.remove();
            }
        }
    }

    /**
     * JOPL then JODL when the ledger has the transacting entity dropdown; otherwise the
     * customer transacts with JOPL only and the page shows it without a choice.
     */
    private List<String> transactingEntitiesOnPage() {
        boolean dropdown = transactingEntityDropdownWithin(DROPDOWN_WAIT_SECONDS);
        List<String> entities = dropdown ? List.of("JOPL", "JODL") : List.of("JOPL");
        log.info("Transacting entity dropdown " + (dropdown ? "present" : "not present")
                + "; validating " + entities + ".");
        return entities;
    }

    /** Selects {@code entity} when the dropdown is there; the single-entity ledger has nothing to select. */
    private void selectEntityIfOffered(String entity) {
        if (transactingEntityDropdownWithin(0)) {
            selectTransactingEntity(entity);
        }
    }

    /**
     * Whether the transacting entity dropdown is on the page, waiting at most {@code seconds}.
     *
     * <p>The session's 10s implicit wait is switched off for this: with it on, every lookup that
     * finds nothing waits 10s itself, so a poll meant to take 15s took minutes on a ledger with no
     * dropdown. Restored afterwards, as in MultiUserSteps.isVisibleNow.
     */
    private static boolean transactingEntityDropdownWithin(int seconds) {
        org.openqa.selenium.WebDriver driver = Grid.driver();
        By dropdown = By.xpath(TRANSACTING_ENTITY_SELECT);
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        try {
            if (seconds <= 0) {
                return !driver.findElements(dropdown).isEmpty();
            }
            new WebDriverWait(driver, Duration.ofSeconds(seconds))
                    .until(ExpectedConditions.presenceOfElementLocated(dropdown));
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(com.qapitol.jsw.config.SessionBuilder.IMPLICIT_WAIT);
        }
    }

    private void openDueSection(String section) {
        if (section.equalsIgnoreCase("Due later")) {
            userOpensDueLaterSectionOnLedgerPage();
        } else if (section.equalsIgnoreCase("Payments due now")) {
            userOpensPaymentsDueNowSectionOnLedgerPage();
        } else {
            throw new IllegalArgumentException("Unknown due section '" + section
                    + "'. Use \"Payments due now\" or \"Due later\".");
        }
    }

    /**
     * Fails the scenario - softly, so every UI check still runs - when the list at
     * {@code listPath} in an API response holds records that are identical field by field.
     * Compared as JSON trees, so key order does not matter, with decimals kept exact.
     */
    private static void assertNoDuplicateRecords(String endpoint, String body, String... listPath) {
        com.fasterxml.jackson.databind.JsonNode list;
        try {
            list = new com.fasterxml.jackson.databind.ObjectMapper()
                    .enable(com.fasterxml.jackson.databind.DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
                    .readTree(body);
        } catch (java.io.IOException e) {
            softAssert.fail(entityTag() + "Could not read the response of " + endpoint + " to check it for duplicate"
                    + " records: " + e.getMessage());
            return;
        }
        for (String key : listPath) {
            list = list.path(key);
        }
        if (!list.isArray()) {
            return;
        }

        Map<com.fasterxml.jackson.databind.JsonNode, List<Integer>> positions = new java.util.LinkedHashMap<>();
        for (int i = 0; i < list.size(); i++) {
            positions.computeIfAbsent(list.get(i), record -> new ArrayList<>()).add(i);
        }
        List<String> duplicates = new ArrayList<>();
        int extraRecords = 0;
        for (Map.Entry<com.fasterxml.jackson.databind.JsonNode, List<Integer>> entry : positions.entrySet()) {
            if (entry.getValue().size() > 1) {
                extraRecords += entry.getValue().size() - 1;
                String record = entry.getKey().toString();
                duplicates.add(entry.getValue().size() + "x at index " + entry.getValue() + ": "
                        + (record.length() > 600 ? record.substring(0, 600) + "..." : record));
            }
        }
        String path = String.join(".", listPath);
        if (duplicates.isEmpty()) {
            log.info("No duplicate records in " + path + " (" + list.size() + " records) from " + endpoint);
            return;
        }
        String message = entityTag() + "There are " + extraRecords + " duplicate record(s) in the API response of "
                + endpoint + " - " + duplicates.size() + " record(s) repeated in " + path + " (" + list.size()
                + " records):\n  "
                + String.join("\n  ", duplicates);
        log.info(message);
        softAssert.fail(message);
    }

    /** One-shot UI duplicate row check, for a table that has no Load more (e.g. Credit lines). */
    private void assertNoDuplicateRowsOnUi(String tableName, String anyCellXpath) {
        UiRowTracker rows = new UiRowTracker(tableName, anyCellXpath);
        rows.snapshot();
        rows.report();
    }

    /**
     * Finds duplicate rows across every fold of a ledger table, not just the rows still on the
     * page at the end.
     *
     * <p>A table can grow three ways as Load more is clicked and the page scrolls: it appends
     * (earlier rows stay), it replaces (the previous batch is swapped out) or it virtualises
     * (only rows near the viewport exist). So the table is read at every step, and each row
     * element is tagged ({@code data-qa-row}) the first time it is read. A tagged element still
     * on the page is the same row; an untagged one was just rendered. That tells apart two cases
     * content alone cannot:
     *
     * <ul>
     *   <li>Load more replaced the batch and the new batch holds a copy of a row from the one it
     *       replaced - the original's element is gone, so the copy is a new occurrence;</li>
     *   <li>Load more served the same batch again - every row is a new element with content that
     *       was just on screen, so every row is a duplicate.</li>
     * </ul>
     *
     * <p>A page that re-creates the elements of rows it keeps (a re-render) is not double
     * counted: after Load more, a new reading that holds every vanished row's content plus more
     * is the same rows re-rendered with the next batch added; on a scroll, where no batch loads,
     * a re-created row always matches the row that vanished.
     *
     * <p>Cells are read by textContent in one script call: getText() drops cells scrolled out of
     * view horizontally, which would make rows differing only in those columns look the same.
     */
    private class UiRowTracker {
        /** Tags each record row once, and returns [tag, justTagged, cells] for every record row. */
        private static final String READ_ROWS =
                "var t = arguments[0], trs = t.tBodies.length ? t.querySelectorAll('tbody tr') : t.querySelectorAll('tr');"
                        + " window.__qaRowSeq = window.__qaRowSeq || 0; var out = [];"
                        + " Array.from(trs).forEach(function (tr) {"
                        + "   var cells = Array.from(tr.querySelectorAll('td')).map(function (td) {"
                        + "     return td.textContent.replace(/\\s+/g, ' ').trim(); });"
                        + "   if (cells.length < 2 || cells.every(function (c) { return c === ''; })) { return; }"
                        + "   var tag = tr.getAttribute('data-qa-row'), fresh = false;"
                        + "   if (!tag) { tag = String(++window.__qaRowSeq); tr.setAttribute('data-qa-row', tag); fresh = true; }"
                        + "   out.push([tag, fresh, cells]); });"
                        + " return out;";

        private final String tableName;
        private final String anyCellXpath;
        private WebElement table;
        private int tableIndex = -1;
        private int fold = 1;
        private int readings;
        /** Tag -> content of the row elements at the previous reading. */
        private Map<String, List<String>> previous = new java.util.LinkedHashMap<>();
        /** Content -> where each occurrence was first seen. */
        private final Map<List<String>, List<String>> seen = new java.util.LinkedHashMap<>();
        private final List<String> notes = new ArrayList<>();

        UiRowTracker(String tableName, String anyCellXpath) {
            this.tableName = tableName;
            this.anyCellXpath = anyCellXpath;
            List<WebElement> cell = Grid.driver().findElements(By.xpath(anyCellXpath));
            if (!cell.isEmpty()) {
                List<WebElement> found = cell.get(0).findElements(By.xpath("./ancestor::table[1]"));
                if (!found.isEmpty()) {
                    table = found.get(0);
                    tableIndex = Grid.driver().findElements(By.xpath("//table")).indexOf(table);
                }
            }
        }

        /** A reading after scrolling, or the first one: no new batch has loaded. */
        void snapshot() {
            read(false);
        }

        /** A reading right after Load more: starts the next fold. */
        void snapshotAfterLoadMore() {
            fold++;
            read(true);
        }

        private void read(boolean afterLoadMore) {
            List<List<Object>> rows = readRows();
            if (rows == null) {
                return;
            }
            readings++;

            Map<String, List<String>> current = new java.util.LinkedHashMap<>();
            List<int[]> newAt = new ArrayList<>();          // indexes into rows of newly rendered rows
            boolean anyKept = false;
            for (int i = 0; i < rows.size(); i++) {
                String tag = String.valueOf(rows.get(i).get(0));
                @SuppressWarnings("unchecked")
                List<String> cells = (List<String>) rows.get(i).get(2);
                current.put(tag, cells);
                List<String> before = previous.get(tag);
                if (before != null && before.equals(cells)) {
                    anyKept = true;                           // same element, same row: still on screen
                } else {
                    newAt.add(new int[]{i});                  // new element, or a recycled one with new content
                }
            }

            // Content of the rows whose elements left the page since the previous reading.
            Map<List<String>, Integer> vanished = new java.util.HashMap<>();
            for (Map.Entry<String, List<String>> row : previous.entrySet()) {
                if (!row.getValue().equals(current.get(row.getKey()))) {
                    vanished.merge(row.getValue(), 1, Integer::sum);
                }
            }

            boolean matchVanished;
            if (!afterLoadMore) {
                matchVanished = true;                         // no batch loaded: a re-created row is the same row
            } else if (anyKept || previous.isEmpty()) {
                matchVanished = false;                        // appended: every new row is a new record
            } else {
                // Every earlier element is gone. Holding every vanished row plus more means the rows
                // were re-rendered with the next batch added; otherwise the batch was replaced.
                Map<List<String>, Integer> fresh = new java.util.HashMap<>();
                for (int[] at : newAt) {
                    @SuppressWarnings("unchecked")
                    List<String> cells = (List<String>) rows.get(at[0]).get(2);
                    fresh.merge(cells, 1, Integer::sum);
                }
                boolean holdsAllVanished = vanished.entrySet().stream()
                        .allMatch(v -> fresh.getOrDefault(v.getKey(), 0) >= v.getValue());
                matchVanished = holdsAllVanished && newAt.size() > previous.size();
                if (holdsAllVanished && newAt.size() == previous.size()) {
                    notes.add("Load more at fold " + fold + " rendered exactly the rows of fold " + (fold - 1)
                            + " again (" + newAt.size() + " rows) - the same page was served twice.");
                }
            }

            for (int[] at : newAt) {
                @SuppressWarnings("unchecked")
                List<String> cells = (List<String>) rows.get(at[0]).get(2);
                if (matchVanished) {
                    Integer left = vanished.get(cells);
                    if (left != null && left > 0) {
                        vanished.put(cells, left - 1);        // the vanished row, re-created
                        continue;
                    }
                }
                seen.computeIfAbsent(cells, row -> new ArrayList<>()).add("fold " + fold + " row " + (at[0] + 1));
            }
            previous = current;
        }

        /** Soft-fails listing how many duplicate rows there are and what each one holds. */
        void report() {
            if (readings == 0) {
                log.info("No table found for " + tableName + "; UI duplicate row check skipped.");
                return;
            }
            List<String> duplicates = new ArrayList<>();
            int extraRows = 0;
            int records = 0;
            for (Map.Entry<List<String>, List<String>> entry : seen.entrySet()) {
                int times = entry.getValue().size();
                records += times;
                if (times > 1) {
                    extraRows += times - 1;
                    duplicates.add(times + "x at " + entry.getValue() + ": " + String.join(" | ", entry.getKey()));
                }
            }
            if (duplicates.isEmpty()) {
                log.info("No duplicate rows rendered in the " + tableName + " table (" + records + " rows across "
                        + fold + " fold(s), " + readings + " readings).");
                return;
            }
            String message = entityTag() + "There are " + extraRows + " duplicate row(s) rendered in the " + tableName
                    + " table on the ledger page - " + duplicates.size() + " record(s) repeated (" + records
                    + " rows across " + fold + " fold(s)):\n  " + String.join("\n  ", duplicates)
                    + (notes.isEmpty() ? "" : "\n  " + String.join("\n  ", notes));
            log.info(message);
            softAssert.fail(message);
        }

        /**
         * [tag, justTagged, cells] for every record row, or null when the table is gone. The table
         * found at the start is reused while attached; when a re-render replaced it, the table at
         * the same position on the page is used, then the table around a known cell.
         */
        @SuppressWarnings("unchecked")
        private List<List<Object>> readRows() {
            WebElement target = attachedTable();
            if (target == null) {
                return null;
            }
            return (List<List<Object>>) ((JavascriptExecutor) Grid.driver()).executeScript(READ_ROWS, target);
        }

        private WebElement attachedTable() {
            if (table != null) {
                try {
                    table.isDisplayed();
                    return table;
                } catch (org.openqa.selenium.StaleElementReferenceException e) {
                    table = null;
                }
            }
            List<WebElement> tables = Grid.driver().findElements(By.xpath("//table"));
            if (tableIndex >= 0 && tableIndex < tables.size()) {
                table = tables.get(tableIndex);
                return table;
            }
            List<WebElement> cell = Grid.driver().findElements(By.xpath(anyCellXpath));
            if (!cell.isEmpty()) {
                List<WebElement> found = cell.get(0).findElements(By.xpath("./ancestor::table[1]"));
                if (!found.isEmpty()) {
                    table = found.get(0);
                    return table;
                }
            }
            return null;
        }
    }

    /** "[JODL] " while a per-entity check runs, so a failure names the entity; "" otherwise. */
    private static String entityTag() {
        String entity = CURRENT_ENTITY.get();
        return entity == null ? "" : "[" + entity + "] ";
    }

    private void selectTransactingEntity(String entity) {
        String label = TRANSACTING_ENTITIES.get(entity.trim().toUpperCase(Locale.ENGLISH));
        if (label == null) {
            throw new IllegalArgumentException("Unknown transacting entity '" + entity + "'. Use one of "
                    + TRANSACTING_ENTITIES.keySet() + " in the Examples table.");
        }
        By dropdown = By.xpath(TRANSACTING_ENTITY_SELECT);
        WebElement select = new WebDriverWait(Grid.driver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(dropdown));
        scrollIntoView(select);

        Select entitySelect = new Select(select);
        String current = entitySelect.getFirstSelectedOption().getText().trim();
        if (current.equals(label)) {
            log.info("Transacting entity already '" + label + "' (" + entity + ").");
            return;
        }
        int textBefore = tableTextLength();
        select.click();
        entitySelect.selectByVisibleText(label);
        log.info("Selected transacting entity '" + label + "' (" + entity + ").");

        // The page reloads its figures for the new entity; wait for that before reading them.
        try {
            new WebDriverWait(Grid.driver(), Duration.ofSeconds(10))
                    .ignoring(org.openqa.selenium.StaleElementReferenceException.class)
                    .until(driver -> tableTextLength() != textBefore);
        } catch (TimeoutException e) {
            // Not a failure on its own, but the checks that follow may then read the previous
            // entity's figures - so a mismatch there is really this. Capture the page now.
            attachTimeoutEvidence("Ledger did not reload after selecting " + label,
                    "Ledger page text did not change within 10s after selecting " + label
                            + "; later checks may be reading the previously selected entity.");
        }
        pauseMillis(1000);

        String selected = new Select(Grid.driver().findElement(dropdown)).getFirstSelectedOption().getText().trim();
        softAssert.assertEquals(selected, label, "[" + entity + "] Transacting entity was not selected on the ledger page.");
    }

    /** Credit info for the logged-in customer, by the GSTIN on their own customer record. */
    @When("user fetches credit info from OMS for the logged in customer")
    public void userFetchesCreditInfoFromOmsForTheLoggedInCustomer() throws java.io.IOException {
        userFetchesCreditInfoFromOmsForGstin(loggedInGstin());
    }

    /**
     * Credit info for a GSTIN, which must be the logged-in customer's. The PAN is characters
     * 3-12 of the GSTIN; the call authenticates with the OMS token (see
     * {@link ApiUtils#fetchCreditInfoByPan}).
     */
    @When("user fetches credit info from OMS for GSTIN {string}")
    public void userFetchesCreditInfoFromOmsForGstin(String gstin) throws java.io.IOException {
        requireLoggedInGstin(gstin);
        String pan = gstin.substring(2, 12);
        Response response = ApiUtils.fetchCreditInfoByPan(pan, gstin);
        if (response.getStatusCode() != 200) {
            throw new IllegalStateException("OMS getCreditInfoByPan returned HTTP " + response.getStatusCode()
                    + " for PAN " + ApiUtils.maskId(pan) + " / GSTIN " + ApiUtils.maskId(gstin) + ". A 401 means the OMS token has expired:"
                    + " refresh omsAuthToken (OMS_TOKEN) or configure the OMS login. Body: " + ApiUtils.maskIdsInJson(response.asString()));
        }
        // BigDecimal so a crore-sized limit keeps its paise (see exactJson).
        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper()
                .enable(com.fasterxml.jackson.databind.DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
        CREDIT_INFO.set(mapper.readTree(response.asString()));
        assertNoDuplicateRecords("GET " + ConfigUtil.resolveOrThrow("omsBaseUrl")
                        + "joms/api/external/v2/getCreditInfoByPan?pan=" + ApiUtils.maskId(pan) + "&gstin=" + ApiUtils.maskId(gstin),
                response.asString(), "customerCreditInfoSROS");
        log.info("OMS credit info for " + ApiUtils.maskId(gstin) + ": " + ApiUtils.maskIdsInJson(response.asString()));
    }

    /**
     * The credit info API takes only PAN and GSTIN - the limit is the same for every transacting
     * entity - so this checks whichever entity the page is showing against the one figure.
     */
    @Then("verify usable credit limit from API is displayed on ledger page")
    public void verifyUsableCreditLimitFromApiIsDisplayedOnLedgerPage() {
        com.fasterxml.jackson.databind.JsonNode creditInfo = CREDIT_INFO.get();
        if (creditInfo == null) {
            throw new IllegalStateException("No OMS credit info response for this scenario. Add the"
                    + " 'user fetches credit info from OMS ...' step before this one.");
        }
        List<String> counted = new ArrayList<>();
        java.math.BigDecimal usable = usableCreditLimit(creditInfo, counted);
        double limit = usable.doubleValue();
        log.info("Usable credit limit from API = " + usable.toPlainString() + " from " + counted);

        if (!isAnyVisible(By.xpath(USABLE_CREDIT_LIMIT_LABEL))) {
            softAssert.fail("Usable credit limit " + USABLE_CREDIT_LIMIT_LABEL + " is not displayed on the ledger page.");
            return;
        }
        WebElement label = firstVisible(By.xpath(USABLE_CREDIT_LIMIT_LABEL));
        scrollIntoView(label);
        String shown = amountNear(label);
        String entity = selectedTransactingEntity();
        log.info("Usable credit limit shown for " + entity + ": " + shown);
        softAssert.assertTrue(rupeeAmounts(shown).stream().anyMatch(a -> sameAmount(a, limit)),
                "Usable credit limit ₹" + plain(limit) + " from API (sum of " + counted + ") does not match the"
                        + " Usable credit limit shown for " + entity + ": " + shown);
    }

    /**
     * Every credit line the customer can see ({@code visibleToCustomer: true}) against the page:
     * the card for its creditType, and in that card each limit next to its own label.
     */
    @Then("verify customer visible credit lines from API are displayed on ledger page")
    public void verifyCustomerVisibleCreditLinesFromApiAreDisplayedOnLedgerPage() {
        clickFirstVisible(By.xpath(USABLE_CREDIT_LIMIT_LABEL));
        com.fasterxml.jackson.databind.JsonNode creditInfo = CREDIT_INFO.get();
        if (creditInfo == null) {
            throw new IllegalStateException("No OMS credit info response for this scenario. Add the"
                    + " 'user fetches credit info from OMS ...' step before this one.");
        }
        List<com.fasterxml.jackson.databind.JsonNode> visible = new ArrayList<>();
        for (com.fasterxml.jackson.databind.JsonNode line : creditInfo.path("customerCreditInfoSROS")) {
            if (line.path("visibleToCustomer").asBoolean(false)) {
                visible.add(line);
            }
        }
        if (visible.isEmpty()) {
            softAssert.fail("OMS credit info has no credit line with visibleToCustomer=true, so there is"
                    + " nothing to compare on the ledger page. Check the test data for the GSTIN.");
            return;
        }
        List<String> creditTypes = new ArrayList<>();
        for (com.fasterxml.jackson.databind.JsonNode line : visible) {
            creditTypes.add(line.path("creditType").asText());
        }
        if (!waitForElementPresent(creditTypeXpath(creditTypes.get(0)), 10)) {
            List<WebElement> label = Grid.driver().findElements(By.xpath(USABLE_CREDIT_LIMIT_LABEL)).stream()
                    .filter(LedgerSteps::isOnScreen).collect(java.util.stream.Collectors.toList());
            if (!label.isEmpty()) {
                scrollIntoView(label.get(0));
                try {
                    clickButton(label.get(0));
                    log.info("Credit lines not shown; clicked Usable credit limit to open the breakdown.");
                } catch (org.openqa.selenium.ElementClickInterceptedException e) {
                    log.info("Usable credit limit click intercepted, using a JS click: " + firstLine(e.getMessage()));
                    jsClick(label.get(0));
                } catch (TimeoutException | org.openqa.selenium.ElementNotInteractableException e) {
                    // A speculative click to open a breakdown: not clickable means there is none to open.
                    // Not clicked through - if the lines are really missing, the checks below say so.
                    log.info("Usable credit limit is not clickable, so no breakdown was opened: "
                            + firstLine(e.getMessage()));
                }
                waitForElementPresent(creditTypeXpath(creditTypes.get(0)), 10);
            }
        }
        log.info("Credit lines visible to customer, by creditType: " + creditTypes);
        assertNoDuplicateRowsOnUi("Credit lines", creditTypeXpath(creditTypes.get(0)));

        for (com.fasterxml.jackson.databind.JsonNode line : visible) {
            verifyCreditLine(line, creditTypes);
        }
    }

    /**
     * One credit line: the table row whose credit type cell is {@code //td[text()='<creditType>']}.
     * Each limit is read from the cell under its column header, scrolling the table right from
     * the credit type cell until that cell is in view. Two visible lines can share a creditType,
     * so every such row is tried and the one whose Usable limit is this line's creditUsable wins.
     * A layout without a table falls back to reading the amount after each label in the card.
     */
    private void verifyCreditLine(com.fasterxml.jackson.databind.JsonNode line, List<String> allCreditTypes) {
        String creditType = line.path("creditType").asText();
        String label = creditType + " (" + line.path("creditInstrument").asText() + " #" + line.path("id").asText() + ")";
        List<WebElement> matches = Grid.driver().findElements(By.xpath(creditTypeXpath(creditType)));
        if (matches.isEmpty()) {
            softAssert.fail("Credit line " + label + " (visibleToCustomer) from API is not displayed on the ledger page.");
            return;
        }
        double usable = line.path("creditUsable").decimalValue().doubleValue();
        String usableLabel = CREDIT_LINE_FIELDS.get("creditUsable");

        Map<String, String> rowCells = null;
        for (WebElement typeCell : matches) {
            Map<String, String> candidate = readCreditRow(typeCell);
            if (candidate == null) {
                continue;
            }
            if (rowCells == null) {
                rowCells = candidate;
            }
            Double shownUsable = cellAmount(candidate.get(usableLabel));
            if (shownUsable != null && sameAmount(shownUsable, usable)) {
                rowCells = candidate;
                break;
            }
        }

        if (rowCells != null) {
            log.info("Credit line row for " + label + ": " + rowCells);
            for (Map.Entry<String, String> field : CREDIT_LINE_FIELDS.entrySet()) {
                assertCellShown(rowCells, line, field.getKey(), field.getValue(), label);
            }
            return;
        }

        // No table around the credit type: read the amount after each label in the card instead.
        String cardText = null;
        for (WebElement match : matches) {
            scrollIntoView(match);
            String candidate = creditLineCardText(match, creditType, allCreditTypes);
            if (cardText == null) {
                cardText = candidate;
            }
            Double shownUsable = amountAfterLabel(candidate, usableLabel);
            if (shownUsable != null && sameAmount(shownUsable, usable)) {
                cardText = candidate;
                break;
            }
        }
        log.info("Credit line card for " + label + ": " + cardText);
        for (Map.Entry<String, String> field : CREDIT_LINE_FIELDS.entrySet()) {
            assertLimitShown(cardText, line, field.getKey(), field.getValue(), label);
        }
    }

    /**
     * The row's cell text for each limit label, keyed by label, read column by column after
     * scrolling each cell into view horizontally from {@code typeCell}. Null when the credit type
     * is not in a table row or the table has no header for the limits.
     */
    private static Map<String, String> readCreditRow(WebElement typeCell) {
        List<WebElement> rows = typeCell.findElements(By.xpath("./ancestor::tr[1]"));
        List<WebElement> tables = typeCell.findElements(By.xpath("./ancestor::table[1]"));
        if (rows.isEmpty() || tables.isEmpty()) {
            return null;
        }
        List<WebElement> headers = tables.get(0).findElements(By.xpath(".//thead//tr[1]/*[self::th or self::td]"));
        if (headers.isEmpty()) {
            headers = tables.get(0).findElements(By.xpath(".//tr[1]/th"));
        }
        List<WebElement> cells = rows.get(0).findElements(By.xpath("./*[self::td or self::th]"));

        Map<String, String> byLabel = new java.util.LinkedHashMap<>();
        centreInView(typeCell);
        for (String pageLabel : CREDIT_LINE_FIELDS.values()) {
            int column = headerIndex(headers, pageLabel);
            if (column < 0 || column >= cells.size()) {
                continue;
            }
            WebElement cell = cells.get(column);
            scrollHorizontallyUntilVisible(typeCell, cell);
            String text = cell.getText();
            if (text == null || text.isBlank()) {
                text = cell.getAttribute("textContent");
            }
            byLabel.put(pageLabel, normalized(text));
        }
        return byLabel.isEmpty() ? null : byLabel;
    }

    private static int headerIndex(List<WebElement> headers, String pageLabel) {
        String wanted = pageLabel.toLowerCase(Locale.ENGLISH);
        for (int i = 0; i < headers.size(); i++) {
            if (normalized(headers.get(i).getAttribute("textContent")).toLowerCase(Locale.ENGLISH).contains(wanted)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Scrolls the table's horizontal scroller, starting from {@code anchor} (the credit type
     * cell), a step at a time towards {@code cell} until the cell sits inside the scroller.
     */
    private static void scrollHorizontallyUntilVisible(WebElement anchor, WebElement cell) {
        JavascriptExecutor js = (JavascriptExecutor) Grid.driver();
        WebElement scroller = (WebElement) js.executeScript(FIND_HORIZONTAL_SCROLLER, anchor);
        if (scroller == null) {
            js.executeScript("arguments[0].scrollIntoView({block:'nearest', inline:'center', behavior:'instant'});", cell);
            return;
        }
        String offset = "var c = arguments[0].getBoundingClientRect(), s = arguments[1].getBoundingClientRect();"
                + " if (c.left < s.left) return -1; if (c.right > s.right) return 1; return 0;";
        for (int step = 0; step < MAX_HORIZONTAL_STEPS; step++) {
            long direction = ((Number) js.executeScript(offset, cell, scroller)).longValue();
            if (direction == 0) {
                return;
            }
            js.executeScript("var s = arguments[0]; s.scrollLeft += arguments[1] * Math.max(80, s.clientWidth * 0.5);",
                    scroller, direction);
            pauseMillis(150);
        }
        js.executeScript("arguments[0].scrollIntoView({block:'nearest', inline:'center', behavior:'instant'});", cell);
    }

    /** The cell under {@code pageLabel} must show the API's {@code field}. */
    private static void assertCellShown(Map<String, String> rowCells, com.fasterxml.jackson.databind.JsonNode line,
                                        String field, String pageLabel, String label) {
        if (!line.path(field).isNumber()) {
            return;
        }
        double expected = line.path(field).decimalValue().doubleValue();
        if (!rowCells.containsKey(pageLabel)) {
            softAssert.fail("No '" + pageLabel + "' column found for credit line " + label + ". Row: " + rowCells);
            return;
        }
        Double shown = cellAmount(rowCells.get(pageLabel));
        if (shown == null) {
            softAssert.fail("'" + pageLabel + "' shows no amount for credit line " + label + ". Row: " + rowCells);
            return;
        }
        softAssert.assertTrue(sameAmount(shown, expected),
                "'" + pageLabel + "' for credit line " + label + " shows ₹" + plain(shown) + " but API " + field
                        + " is ₹" + plain(expected) + ". Row: " + rowCells);
    }

    /** A cell's amount: its ₹ value, else a bare number, else 0 for a dash; null when blank. */
    private static Double cellAmount(String text) {
        if (text == null || text.isBlank()) {
            return null;
        }
        List<Double> amounts = rupeeAmounts(text);
        if (!amounts.isEmpty()) {
            return amounts.get(0);
        }
        Matcher bare = Pattern.compile("[0-9][0-9,]*(?:\\.[0-9]+)?").matcher(text);
        if (bare.find()) {
            return Double.parseDouble(bare.group().replace(",", ""));
        }
        String trimmed = text.trim();
        return trimmed.equals("-") || trimmed.equals("—") ? 0.0 : null;
    }

    /** The amount printed after {@code pageLabel} in the card must be the API's {@code field}. */
    private static void assertLimitShown(String cardText, com.fasterxml.jackson.databind.JsonNode line,
                                         String field, String pageLabel, String label) {
        if (!line.path(field).isNumber()) {
            return;
        }
        double expected = line.path(field).decimalValue().doubleValue();
        Double shown = amountAfterLabel(cardText, pageLabel);
        if (shown == null) {
            softAssert.fail("'" + pageLabel + "' is not displayed with an amount for credit line " + label
                    + ". Card text: " + cardText);
            return;
        }
        softAssert.assertTrue(sameAmount(shown, expected),
                "'" + pageLabel + "' for credit line " + label + " shows ₹" + plain(shown) + " but API " + field
                        + " is ₹" + plain(expected) + ". Card text: " + cardText);
    }

    /** First ₹ amount after {@code pageLabel} (case-insensitive) in {@code text}, or null. */
    private static Double amountAfterLabel(String text, String pageLabel) {
        int at = text.toLowerCase(Locale.ENGLISH).indexOf(pageLabel.toLowerCase(Locale.ENGLISH));
        if (at < 0) {
            return null;
        }
        List<Double> after = rupeeAmounts(text.substring(at + pageLabel.length()));
        return after.isEmpty() ? null : after.get(0);
    }

    /**
     * Climbs from the credit type to the smallest ancestor carrying all the limit labels, stopping
     * before one that also holds another credit line's card - that is the list, not the card.
     */
    private static String creditLineCardText(WebElement typeCell, String creditType, List<String> allCreditTypes) {
        WebElement current = typeCell;
        for (int level = 0; level < MAX_ROW_DEPTH; level++) {
            WebElement parent = current.findElement(By.xpath(".."));
            String text = normalized(parent.getAttribute("textContent"));
            long sameType = allCreditTypes.stream().filter(creditType::equals).count();
            boolean holdsOtherCard = occurrences(text, creditType) > 1
                    || allCreditTypes.stream().anyMatch(other -> !other.equals(creditType)
                            && !creditType.contains(other) && text.contains(other))
                    || (sameType > 1 && occurrences(text.toLowerCase(Locale.ENGLISH),
                            CREDIT_LINE_FIELDS.get("creditUsable").toLowerCase(Locale.ENGLISH)) > 1);
            if (holdsOtherCard) {
                break;
            }
            current = parent;
            String lower = text.toLowerCase(Locale.ENGLISH);
            if (CREDIT_LINE_FIELDS.values().stream().allMatch(l -> lower.contains(l.toLowerCase(Locale.ENGLISH)))) {
                break;
            }
        }
        return normalized(current.getAttribute("textContent"));
    }

    /** The credit type cell of a credit line row, e.g. //td[text()='Letter of credit']. */
    private static String creditTypeXpath(String creditType) {
        String literal = xpathLiteral(creditType);
        return "//td[text()=" + literal + "] | //td[normalize-space(text())=" + literal + "]";
    }

    /** The transacting entity the ledger page is showing, for messages; "the default entity" if none. */
    private static String selectedTransactingEntity() {
        if (!transactingEntityDropdownWithin(0)) {
            return "the default entity";
        }
        return new Select(Grid.driver().findElement(By.xpath(TRANSACTING_ENTITY_SELECT)))
                .getFirstSelectedOption().getText().trim();
    }

    /**
     * Sum of {@code creditUsable} over the credit lines the customer can see and use:
     * visibleToCustomer, active (status) and not blocked. This is how OMS arrives at
     * totalUsableCreditLimit in customer-balance-and-due-summary - checked on preprod, where the
     * two visible lines 12,340,000 + 901,642.54 give its 13,241,642.54 and the five hidden
     * lines are left out.
     */
    private static java.math.BigDecimal usableCreditLimit(com.fasterxml.jackson.databind.JsonNode creditInfo,
                                                          List<String> counted) {
        java.math.BigDecimal total = java.math.BigDecimal.ZERO;
        for (com.fasterxml.jackson.databind.JsonNode line : creditInfo.path("customerCreditInfoSROS")) {
            boolean usableByCustomer = line.path("visibleToCustomer").asBoolean(false)
                    && line.path("status").asBoolean(false)
                    && !line.path("isCreditBlocked").asBoolean(false);
            if (usableByCustomer && line.path("creditUsable").isNumber()) {
                total = total.add(line.path("creditUsable").decimalValue());
                counted.add(line.path("creditInstrument").asText() + " #" + line.path("id").asText()
                        + " = " + line.path("creditUsable").decimalValue().toPlainString());
            }
        }
        return total;
    }

    /** The ₹ amount belonging to a label: in its own text, else its parent's, else its grandparent's. */
    private static String amountNear(WebElement label) {
        WebElement current = label;
        for (int level = 0; level < 3; level++) {
            String text = normalized(current.getAttribute("textContent"));
            if (!rupeeAmounts(text).isEmpty()) {
                return text;
            }
            current = current.findElement(By.xpath(".."));
        }
        return normalized(current.getAttribute("textContent"));
    }

    @And("user opens Usable ledger balance section on ledger page")
    public void userOpensUsableLedgerBalanceSectionOnLedgerPage() {
        log.info("Opening Usable ledger balance section on ledger page.");
        clickFirstVisible(By.xpath(USABLE_LEDGER_BALANCE));
    }

    @Then("verify usable ledger balance from API is displayed on ledger page")
    public void verifyUsableLedgerBalanceFromApiIsDisplayedOnLedgerPage() {
        double balance = toAmount(ledgerTransactions().get("data.balanceAmount"));
        if (!waitForElementPresent(CLOSING_BALANCE, 15)) {
            softAssert.fail(entityTag() + "Closing balance " + CLOSING_BALANCE + " is not displayed on the ledger page.");
            return;
        }
        WebElement closingBalance = firstVisible(By.xpath(CLOSING_BALANCE));
        scrollIntoView(closingBalance);
        softAssert.assertTrue(isDisplayed(closingBalance), entityTag() + "Closing balance is not displayed on the ledger page.");

        String shown = closingBalanceText(closingBalance);
        log.info("Closing balance shown: " + shown + " | API balanceAmount: " + plain(balance));
        softAssert.assertTrue(rupeeAmounts(shown).stream().anyMatch(a -> sameAmount(a, balance)),
                entityTag() + "Usable ledger balance ₹" + plain(balance) + " from API does not match the Closing balance shown: " + shown);
    }

    /**
     * The amount next to "Closing balance": its own ₹ child if it has one, else the label's
     * text, else its parent's. textContent, not getText(): "balance" is hidden below the md
     * breakpoint and getText() drops hidden text.
     */
    private static String closingBalanceText(WebElement closingBalance) {
        List<WebElement> amount = closingBalance.findElements(By.xpath(CLOSING_BALANCE_AMOUNT));
        if (!amount.isEmpty()) {
            return normalized(amount.get(0).getAttribute("textContent"));
        }
        String own = normalized(closingBalance.getAttribute("textContent"));
        if (!rupeeAmounts(own).isEmpty()) {
            return own;
        }
        return normalized(closingBalance.findElement(By.xpath("..")).getAttribute("textContent"));
    }

    private static String normalized(String text) {
        return text == null ? "" : text.replaceAll("\\s+", " ").trim();
    }

    /**
     * The API returns transactions newest first, the same order as the UI table, so the first
     * {@code count} are the ones on screen without paging.
     */
    @Then("verify latest {string} ledger transactions from API are displayed on ledger page")
    public void verifyLatestLedgerTransactionsFromApiAreDisplayedOnLedgerPage(String count) {
        List<Map<String, Object>> transactions = ledgerTransactions().getList("data.transactionData");
        if (transactions == null || transactions.isEmpty()) {
            softAssert.fail(entityTag() + "Ledger service returned no transactions for this customer, so there is nothing"
                    + " to compare on the ledger page. Check the test data for the GSTIN.");
            return;
        }
        List<Map<String, Object>> latest =
                transactions.subList(0, Math.min(Integer.parseInt(count), transactions.size()));

        List<String> documentIds = new ArrayList<>();
        for (Map<String, Object> transaction : latest) {
            documentIds.add(String.valueOf(transaction.get("documentId")));
        }
        waitForElementPresent(documentXpath(documentIds.get(0)), 15);
        UiRowTracker uiRows = new UiRowTracker("Usable ledger balance transactions", documentXpath(documentIds.get(0)));
        loadAllLedgerRows(orderNumbersOf(latest), uiRows);
        uiRows.report();

        for (Map<String, Object> transaction : latest) {
            verifyTransaction(transaction, documentIds);
        }
    }

    private void verifyTransaction(Map<String, Object> transaction, List<String> allDocumentIds) {
        String documentId = String.valueOf(transaction.get("documentId"));
        String orderNumber = String.valueOf(transaction.get("orderNumber"));
        double debit = toAmount(transaction.get("debitAmount"));
        double credit = toAmount(transaction.get("creditAmount"));
        double amount = debit != 0 ? debit : credit;

        List<WebElement> matches = Grid.driver().findElements(By.xpath(documentXpath(documentId)));
        if (matches.isEmpty()) {
            softAssert.fail(entityTag() + "Ledger transaction " + documentId + " from API is not displayed on the ledger page."
                    + loadMoreTimeoutHint());
            return;
        }
        scrollToOrderButton(orderNumber);
        scrollIntoView(matches.get(0));
        WebElement row = rowFor(matches.get(0), documentId, orderNumber, amount, allDocumentIds);
        scrollToOrderButtonIn(row, orderNumber);
        String rowText = readRowScrollingToPendingAmount(row, orderNumber);
        log.info("Ledger transaction row for " + documentId + ": " + rowText.replace('\n', ' '));

        if (amount != 0) {
            softAssert.assertTrue(rupeeAmounts(rowText).stream().anyMatch(a -> sameAmount(a, amount)),
                    entityTag() + (debit != 0 ? "Debit" : "Credit") + " ₹" + amount + " for ledger transaction " + documentId
                            + " is not displayed in its row. Row text: " + rowText);
        }
        assertRowContains(rowText, transaction.get("documentType"), "Document type", documentId);
        // The Due status column shows the transaction's clearingStatus ("Cleared" / "Open").
        assertRowContains(rowText, transaction.get("clearingStatus"), "Due status", documentId);

        LocalDate documentDate = toDate(transaction.get("documentDate"));
        if (documentDate != null) {
            softAssert.assertTrue(containsDate(rowText, documentDate),
                    entityTag() + "Document date " + documentDate + " for ledger transaction " + documentId
                            + " is not displayed in its row. Row text: " + rowText);
        }
    }

    /** Skips blank values - the API sends "" or null for columns the UI leaves empty. */
    private static void assertRowContains(String rowText, Object value, String column, String documentId) {
        String expected = value == null ? "" : String.valueOf(value).trim();
        if (expected.isEmpty()) {
            return;
        }
        softAssert.assertTrue(rowText.toLowerCase(Locale.ENGLISH).contains(expected.toLowerCase(Locale.ENGLISH)),
                entityTag() + column + " '" + expected + "' for ledger transaction " + documentId
                        + " is not displayed in its row. Row text: " + rowText);
    }

    private static JsonPath ledgerTransactions() {
        JsonPath api = LEDGER_TRANSACTIONS.get();
        if (api == null) {
            throw new IllegalStateException("No ledger service response for this scenario. Add the"
                    + " 'user fetches ledger transactions from ledger service ...' step before this one.");
        }
        return api;
    }

    /**
     * The logged-in customer's GSTIN, from their own customer record. The API scenarios query by
     * it rather than a GSTIN in the feature: it is then always the account the page shows, on
     * every environment, with nothing to keep in step when the ledger account changes.
     */
    private static String loggedInGstin() {
        String gstin = new SessionSteps().getCustomerDetail().getGstIn();
        if (gstin == null || gstin.isBlank()) {
            throw new IllegalStateException("The logged-in customer " + SessionSteps.userEmail()
                    + " has no GSTIN on their customer record, so the ledger APIs cannot be queried for them.");
        }
        log.info("Querying the ledger APIs for the logged-in customer's GSTIN " + ApiUtils.maskId(gstin) + ".");
        return gstin;
    }

    /**
     * The APIs are queried by GSTIN but the UI shows the logged-in customer; if the two differ
     * the comparison is meaningless and every row check fails for the wrong reason.
     */
    private static void requireLoggedInGstin(String gstin) {
        String loggedInGstin = new SessionSteps().getCustomerDetail().getGstIn();
        if (!gstin.equalsIgnoreCase(loggedInGstin)) {
            throw new IllegalStateException("GSTIN " + ApiUtils.maskId(gstin) + " in the feature does not belong to the"
                    + " logged-in user " + SessionSteps.userEmail() + " (GSTIN " + ApiUtils.maskId(loggedInGstin) + ")."
                    + " Use that user's GSTIN in the Examples table, or change ledger.user.email.");
        }
    }

    /** Opens "Due later", or "Due in next 3 days" on a ledger that shows that instead. */
    @And("user opens Due later section on ledger page")
    public void userOpensDueLaterSectionOnLedgerPage() {
        String section = upcomingDueSectionXpath();
        if (section == null) {
            throw new IllegalStateException("Neither 'Due later' nor 'Due in next 3 days' is on the ledger"
                    + " page at " + Grid.driver().getCurrentUrl() + ", so there is no upcoming-dues section to open.");
        }
        log.info("Opening " + upcomingDueSectionName(section) + " section on ledger page.");
        clickFirstVisible(By.xpath(section));
    }

    @And("user opens Payments due now section on ledger page")
    public void userOpensPaymentsDueNowSectionOnLedgerPage() {
        log.info("Opening Payments due now section on ledger page.");
        clickFirstVisible(By.xpath(PAYMENT_DUE));
    }

    /** {@code section} only labels the failure messages, e.g. "Due later" or "Payments due now". */
    @Then("verify {string} entries from API are displayed on ledger page")
    public void verifyEntriesFromApiAreDisplayedOnLedgerPage(String section) {
        JsonPath api = DUE_PAYMENTS.get();
        if (api == null) {
            throw new IllegalStateException("No OMS due payments response for this scenario. Add the"
                    + " 'user fetches due payments from OMS ...' step before this one.");
        }
        List<Map<String, Object>> details = api.getList("data.details");
        if (details == null || details.isEmpty()) {
            softAssert.fail("OMS returned no " + section + " entries for this customer, so there is nothing"
                    + " to compare on the ledger page. Check the test data for the GSTIN.");
            return;
        }

        List<String> documentNumbers = new ArrayList<>();
        for (Map<String, Object> entry : details) {
            documentNumbers.add(String.valueOf(entry.get("documentNumber")));
        }
        waitForElementPresent(documentXpath(documentNumbers.get(0)), 15);
        // section already carries the entity, e.g. "Due later [JODL]"; entityTag() adds it again otherwise.
        UiRowTracker uiRows = new UiRowTracker(section.replaceAll("\\s*\\[[A-Z]+\\]$", ""),
                documentXpath(documentNumbers.get(0)));
        loadAllLedgerRows(orderNumbersOf(details), uiRows);
        uiRows.report();

        for (Map<String, Object> entry : details) {
            verifyEntry(section, entry, documentNumbers);
        }

        double totalAmount = toAmount(api.get("data.totalAmount"));
        String pageText = Grid.driver().findElement(By.tagName("body")).getText();
        softAssert.assertTrue(rupeeAmounts(pageText).stream().anyMatch(a -> sameAmount(a, totalAmount)),
                section + " total ₹" + totalAmount + " from API is not displayed on the ledger page.");
    }

    /**
     * One API entry against the UI. A document number can own several entries (an order has a
     * confirmation and a dispatch payment), so every row showing the number is tried and the
     * one carrying this entry's amount is the match.
     */
    private void verifyEntry(String section, Map<String, Object> entry, List<String> allDocumentNumbers) {
        String documentNumber = String.valueOf(entry.get("documentNumber"));
        String orderNumber = String.valueOf(entry.get("orderNumber"));
        double amount = toAmount(entry.get("amount"));
        List<WebElement> matches = Grid.driver().findElements(By.xpath(documentXpath(documentNumber)));
        if (matches.isEmpty()) {
            softAssert.fail(section + " document " + documentNumber + " from API is not displayed on the ledger page."
                    + loadMoreTimeoutHint());
            return;
        }

        scrollToOrderButton(orderNumber);
        String rowText = null;
        for (WebElement match : matches) {
            scrollIntoView(match);
            WebElement row = rowFor(match, documentNumber, orderNumber, amount, allDocumentNumbers);
            scrollToOrderButtonIn(row, orderNumber);
            String candidate = readRowScrollingToPendingAmount(row, orderNumber);
            if (rowText == null) {
                rowText = candidate;
            }
            if (rupeeAmounts(candidate).stream().anyMatch(a -> sameAmount(a, amount))) {
                rowText = candidate;
                break;
            }
        }
        log.info(section + " row for " + documentNumber + ": " + rowText.replace('\n', ' '));
        softAssert.assertTrue(rupeeAmounts(rowText).stream().anyMatch(a -> sameAmount(a, amount)),
                "Amount ₹" + amount + " for " + section + " document " + documentNumber
                        + " is not displayed in its row. Row text: " + rowText);

        LocalDate dueDate = toDate(entry.get("dueDate"));
        int overdueDays = entry.get("overdueDays") instanceof Number
                ? ((Number) entry.get("overdueDays")).intValue() : 0;
        if (dueDate != null) {
            softAssert.assertTrue(containsDate(rowText, dueDate) || showsOverdueDays(rowText, overdueDays),
                    "Due date " + dueDate + (overdueDays > 0 ? " / " + overdueDays + " days overdue" : "")
                            + " for " + section + " document " + documentNumber
                            + " is not displayed in its row. Row text: " + rowText);
        }
    }

    private static String orderIdButtonXpath(String orderNumber) {
        return String.format(ORDER_ID_BUTTON, xpathLiteral(orderNumber));
    }

    /** Distinct order numbers from the API rows, in response order. */
    private static List<String> orderNumbersOf(List<Map<String, Object>> rows) {
        List<String> orderNumbers = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            Object orderNumber = row.get("orderNumber");
            if (orderNumber != null && !String.valueOf(orderNumber).isBlank()
                    && !orderNumbers.contains(String.valueOf(orderNumber))) {
                orderNumbers.add(String.valueOf(orderNumber));
            }
        }
        return orderNumbers;
    }

    /**
     * Scrolls horizontally and vertically to the first Order ID button for {@code orderNumber}.
     * Returns false when no such button is on the page yet (not loaded, or not a button).
     */
    private boolean scrollToOrderButton(String orderNumber) {
        List<WebElement> buttons = Grid.driver().findElements(By.xpath(orderIdButtonXpath(orderNumber)));
        if (buttons.isEmpty()) {
            return false;
        }
        centreInView(buttons.get(0));
        return true;
    }

    /** Aligns the table on this row's own Order ID button - an order can span several rows. */
    private static void scrollToOrderButtonIn(WebElement row, String orderNumber) {
        List<WebElement> buttons = row.findElements(By.xpath("." + orderIdButtonXpath(orderNumber)));
        if (!buttons.isEmpty()) {
            centreInView(buttons.get(0));
        }
    }

    private static void centreInView(WebElement element) {
        ((JavascriptExecutor) Grid.driver()).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'center', behavior:'instant'});", element);
    }

    /**
     * The table shows one page of rows and a Load more button. Scrolls through the rows one by
     * one by their Order ID buttons (orderNumber from the API response), and once the rows on
     * the page are exhausted scrolls down to Load more, clicks it and carries on - until the
     * button is gone, so every row the API returned can be found.
     *
     * <p>{@code uiRows} reads the table at the start, after every scroll and after every Load
     * more, so rows that leave the page later (replaced or virtualised) are still checked for
     * duplicates.
     */
    private void loadAllLedgerRows(List<String> orderNumbers, UiRowTracker uiRows) {
        By loadMore = By.xpath(LOAD_MORE_BUTTON);
        int scrolledTo = 0;
        LOAD_MORE_TIMEOUTS.get().clear();
        uiRows.snapshot();
        for (int click = 1; click <= MAX_LOAD_MORE_CLICKS; click++) {
            while (scrolledTo < orderNumbers.size() && scrollToOrderButton(orderNumbers.get(scrolledTo))) {
                scrolledTo++;
                uiRows.snapshot();
            }
            WebElement button = visibleLoadMoreButton(loadMore);
            if (button == null) {
                uiRows.snapshot();
                log.info("Load more button not present; all ledger rows are loaded after " + (click - 1)
                        + " click(s). Scrolled to " + scrolledTo + " of " + orderNumbers.size() + " order(s).");
                return;
            }
            scrollDownUntilInViewport(button);
            int rowsBefore = tableTextLength();
            try {
                clickButton(button);
            } catch (org.openqa.selenium.ElementClickInterceptedException e) {
                // Only an overlay (sticky header, toast) covering the button is clicked through. A
                // disabled or hidden button throws something else and fails the scenario as it should.
                log.info("Load more click intercepted, using a JS click: " + firstLine(e.getMessage()));
                jsClick(button);
            }
            log.info("Clicked Load more (" + click + ") after scrolling to " + scrolledTo + " order(s).");
            waitForMoreRows(loadMore, rowsBefore, click);
            uiRows.snapshotAfterLoadMore();
        }
        softAssert.fail(entityTag() + "Load more button was still present after " + MAX_LOAD_MORE_CLICKS
                + " clicks; the ledger table may not be loading further rows." + loadMoreTimeoutHint());
    }

    private static WebElement visibleLoadMoreButton(By loadMore) {
        for (WebElement button : Grid.driver().findElements(loadMore)) {
            try {
                if (button.isDisplayed() && button.isEnabled()) {
                    return button;
                }
            } catch (org.openqa.selenium.StaleElementReferenceException ignored) {
            }
        }
        return null;
    }

    /** Scrolls the page down a step at a time until {@code element} is inside the viewport. */
    private static void scrollDownUntilInViewport(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Grid.driver();
        String inViewport = "var r = arguments[0].getBoundingClientRect();"
                + " return r.top >= 0 && r.bottom <= (window.innerHeight || document.documentElement.clientHeight);";
        for (int step = 0; step < MAX_SCROLL_STEPS; step++) {
            if (Boolean.TRUE.equals(js.executeScript(inViewport, element))) {
                return;
            }
            js.executeScript("window.scrollBy(0, " + SCROLL_STEP_PX + ");");
            pauseMillis(200);
        }
        // Button sits inside a scrolling container the window scroll does not move.
        js.executeScript("arguments[0].scrollIntoView({block:'center', inline:'nearest', behavior:'instant'});", element);
    }

    /**
     * Not {@code sleep} or {@code pause}: AbstractWebPage inherits instance methods of both
     * names, which IntelliJ binds to and then cannot call from a static method.
     */
    private static void pauseMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Waits for the click to take effect: the table grows, or the button goes away.
     *
     * <p>A timeout does not stop the loop - it clicks again, bounded by MAX_LOAD_MORE_CLICKS - but
     * it is recorded, logged and attached with a screenshot, and named in any "not displayed"
     * failure that follows, so a table that loaded late is reported as that rather than as rows
     * missing for no stated reason.
     */
    private static void waitForMoreRows(By loadMore, int textLengthBefore, int click) {
        try {
            // A page still re-rendering keeps the wait polling; a dead session fails it at once.
            new WebDriverWait(Grid.driver(), Duration.ofSeconds(15))
                    .ignoring(org.openqa.selenium.StaleElementReferenceException.class)
                    .until(driver -> tableTextLength() > textLengthBefore || visibleLoadMoreButton(loadMore) == null);
        } catch (TimeoutException e) {
            String note = "Load more click " + click + ": the table did not grow within 15s (page text length "
                    + textLengthBefore + " before, " + tableTextLength() + " now; Load more button "
                    + (visibleLoadMoreButton(loadMore) == null ? "gone" : "still visible") + ").";
            LOAD_MORE_TIMEOUTS.get().add(note);
            log.info(entityTag() + note);
            io.qameta.allure.Allure.addAttachment(entityTag() + "Load more timed out (click " + click + ")",
                    "text/plain", note, ".txt");
            attachScreenshot(entityTag() + "Ledger table when Load more click " + click + " timed out");
        }
    }

    /**
     * " Load more timed out N time(s) ... " for a failure message when the current table's load
     * had timeouts, so a missing row points at the late load; "" otherwise.
     */
    private static String loadMoreTimeoutHint() {
        List<String> timeouts = LOAD_MORE_TIMEOUTS.get();
        return timeouts.isEmpty() ? "" : " Load more timed out " + timeouts.size() + " time(s) while loading this"
                + " table, so the row may simply not have loaded: " + String.join(" ", timeouts);
    }

    /** isEnabled() for a note; a stale element must not turn a soft failure into an error. */
    private static String enabledState(WebElement element) {
        try {
            return String.valueOf(element.isEnabled());
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            return "unknown (element re-rendered)";
        }
    }

    /**
     * Evidence for a wait that timed out, captured when it happens: a log line, a text note and a
     * screenshot in the Allure report. The failure screenshot Hooks takes comes at the END of the
     * scenario, by when the page has usually moved on from what timed out.
     *
     * <p>Not every caller is a failure: after an entity switch the ledger can legitimately render
     * identical text, so a passing scenario may carry this note too - which is why that note says
     * later checks "may be" reading the previous entity, not that they are.
     */
    private static void attachTimeoutEvidence(String title, String note) {
        log.info(entityTag() + note);
        io.qameta.allure.Allure.addAttachment(entityTag() + title, "text/plain", note, ".txt");
        attachScreenshot(entityTag() + title);
    }

    /** Screenshot into the report; a screenshot that cannot be taken never fails the step. */
    private static void attachScreenshot(String name) {
        try {
            byte[] png = ((org.openqa.selenium.TakesScreenshot) Grid.driver())
                    .getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
            io.qameta.allure.Allure.addAttachment(name, "image/png", new java.io.ByteArrayInputStream(png), ".png");
        } catch (org.openqa.selenium.WebDriverException e) {
            log.info("Could not attach a screenshot (" + name + "): " + firstLine(e.getMessage()));
        }
    }

    /** Displayed with a real size - not one of the hidden 0x0 duplicates the page renders. */
    private static boolean isOnScreen(WebElement element) {
        try {
            return element.isDisplayed() && element.getSize().getWidth() > 0 && element.getSize().getHeight() > 0;
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            return false;
        }
    }

    /** First line of a Selenium message - the rest is build and capability noise. */
    private static String firstLine(String message) {
        if (message == null) {
            return "";
        }
        int end = message.indexOf('\n');
        return (end < 0 ? message : message.substring(0, end)).trim();
    }

    /**
     * Length of the page's visible text - a cheap "did the table change" probe.
     *
     * <p>Only a stale {@code <body>} is retried: the page re-rendered between finding it and
     * reading it, so look again. Anything else - a dead session, an unreachable browser - is
     * thrown rather than reported as 0, which made a broken driver look like "no change" and
     * waited out the full timeout before failing somewhere unrelated.
     */
    private static int tableTextLength() {
        org.openqa.selenium.StaleElementReferenceException lastStale = null;
        for (int attempt = 1; attempt <= 3; attempt++) {
            try {
                return Grid.driver().findElement(By.tagName("body")).getText().length();
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                lastStale = e;
                pauseMillis(200);
            }
        }
        throw lastStale;
    }

    /**
     * Row text with every column included - Due status and the pending amount sit to the right
     * of the viewport, and Selenium leaves cells scrolled out of view out of getText().
     *
     * <p>Starts from the row's Order ID button and scrolls the table right a step at a time,
     * reading the row at each position, until the right edge; then brings the pending amount
     * cell into view for a last read. The text of every position is kept.
     */
    private static String readRowScrollingToPendingAmount(WebElement row, String orderNumber) {
        JavascriptExecutor js = (JavascriptExecutor) Grid.driver();
        List<String> seen = new ArrayList<>();
        addIfNew(seen, row.getText());

        List<WebElement> orderButtons = row.findElements(By.xpath("." + orderIdButtonXpath(orderNumber)));
        WebElement anchor = orderButtons.isEmpty() ? row : orderButtons.get(0);
        centreInView(anchor);
        addIfNew(seen, row.getText());

        WebElement scroller = (WebElement) js.executeScript(FIND_HORIZONTAL_SCROLLER, anchor);
        if (scroller != null) {
            for (int step = 0; step < MAX_HORIZONTAL_STEPS; step++) {
                Boolean atRightEdge = (Boolean) js.executeScript(
                        "var s = arguments[0]; return s.scrollLeft + s.clientWidth >= s.scrollWidth - 1;", scroller);
                if (Boolean.TRUE.equals(atRightEdge)) {
                    break;
                }
                js.executeScript("var s = arguments[0]; s.scrollLeft += Math.max(80, s.clientWidth * 0.5);", scroller);
                pauseMillis(150);
                addIfNew(seen, row.getText());
            }
        }

        List<WebElement> amountCells = row.findElements(By.xpath(PENDING_AMOUNT_IN_ROW));
        if (!amountCells.isEmpty()) {
            js.executeScript("arguments[0].scrollIntoView({block:'nearest', inline:'center', behavior:'instant'});",
                    amountCells.get(amountCells.size() - 1));
            addIfNew(seen, row.getText());
        }
        return String.join("\n", seen);
    }

    private static void addIfNew(List<String> seen, String text) {
        if (text != null && !text.isBlank() && !seen.contains(text)) {
            seen.add(text);
        }
    }

    private static void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) Grid.driver()).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'nearest', behavior:'instant'});", element);
    }

    /**
     * Climbs from the document number to the smallest ancestor that shows its amount, stopping
     * before an ancestor that holds another row - that is the list, not the row. When the amount
     * is scrolled out of view the climb ends at the whole row, which is what gets scrolled.
     *
     * <p>An order payment's document number IS its order number, so its own row shows the
     * number twice (Document ID and Order ID columns); only more than that means a second row.
     */
    private static WebElement rowFor(WebElement documentCell, String ownNumber, String orderNumber,
                                     double amount, List<String> allDocumentNumbers) {
        int perRow = ownNumber.equals(orderNumber) ? 2 : 1;
        WebElement current = documentCell;
        for (int level = 0; level < MAX_ROW_DEPTH; level++) {
            WebElement parent = current.findElement(By.xpath(".."));
            String text = parent.getText();
            boolean holdsOtherRow = occurrences(text, ownNumber) > perRow || allDocumentNumbers.stream()
                    .anyMatch(other -> !other.equals(ownNumber) && !other.equals(orderNumber)
                            && text.contains(other));
            if (holdsOtherRow) {
                break;
            }
            current = parent;
            if (rupeeAmounts(text).stream().anyMatch(a -> sameAmount(a, amount))) {
                break;
            }
        }
        return current;
    }

    private static int occurrences(String text, String value) {
        int count = 0;
        for (int i = text.indexOf(value); i >= 0; i = text.indexOf(value, i + value.length())) {
            count++;
        }
        return count;
    }

    private static String documentXpath(String documentNumber) {
        return "//*[normalize-space(text())=" + xpathLiteral(documentNumber) + "]";
    }

    private static List<Double> rupeeAmounts(String text) {
        List<Double> amounts = new ArrayList<>();
        Matcher matcher = RUPEE_AMOUNT.matcher(text);
        while (matcher.find()) {
            amounts.add(Double.parseDouble(matcher.group(1).replace(",", "")));
        }
        return amounts;
    }

    /**
     * The response parsed with decimals as BigDecimal. JsonPath's default is Float, which keeps
     * only ~7 significant digits: balanceAmount 25102667.90 came back as 2.5102668E7 (25102668)
     * and could never match the ₹2,51,02,667.90 on screen.
     *
     * <p>{@code io.restassured.path.json.config.JsonPathConfig}, not the
     * {@code io.restassured.config} one - that is the type {@link JsonPath#using} takes, and
     * IntelliJ rejects the other.
     */
    private static JsonPath exactJson(Response response) {
        return new JsonPath(response.asString())
                .using(new JsonPathConfig(JsonPathConfig.NumberReturnType.BIG_DECIMAL));
    }

    /** 25102667.9 as "25102667.90" rather than a double's "2.51026679E7", for messages. */
    private static String plain(double amount) {
        return java.math.BigDecimal.valueOf(amount).setScale(2, java.math.RoundingMode.HALF_UP).toPlainString();
    }

    /** API amounts arrive as BigDecimal (see {@link #exactJson}) or Integer for whole numbers. */
    private static double toAmount(Object value) {
        return new java.math.BigDecimal(String.valueOf(value)).doubleValue();
    }

    private static boolean sameAmount(double a, double b) {
        return Math.abs(a - b) < 0.01;
    }

    /** OMS and the ledger service send dates as [year, month, day] or [year, month, day, hour, minute]. */
    private static LocalDate toDate(Object dueDate) {
        if (dueDate instanceof List && ((List<?>) dueDate).size() >= 3) {
            List<?> parts = (List<?>) dueDate;
            return LocalDate.of(((Number) parts.get(0)).intValue(), ((Number) parts.get(1)).intValue(),
                    ((Number) parts.get(2)).intValue());
        }
        return null;
    }

    private static boolean containsDate(String text, LocalDate date) {
        // The UI writes September as "Sept"; Java's MMM gives "Sep".
        String lower = text.toLowerCase(Locale.ENGLISH).replace("sept", "sep");
        return DUE_DATE_FORMATS.stream()
                .anyMatch(format -> lower.contains(date.format(format).toLowerCase(Locale.ENGLISH)));
    }

    /** Overdue rows may show "Overdue by 96 days" in place of the date. */
    private static boolean showsOverdueDays(String text, int overdueDays) {
        return overdueDays > 0
                && Pattern.compile("\\b" + overdueDays + "\\s*days?\\b", Pattern.CASE_INSENSITIVE).matcher(text).find();
    }

    /** True on {@code path} itself or one of its sub-tabs, e.g. /ledger/payments-due-now. */
    private static boolean isOnPath(String path) {
        String current = currentPath();
        return current.equals(path) || current.startsWith(path + "/");
    }

    /** Waits up to 10s for the SPA route to settle; the assertion that follows reports a miss. */
    private void waitForPath(String path) {
        try {
            new WebDriverWait(Grid.driver(), Duration.ofSeconds(10))
                    .until(driver -> isOnPath(path));
        } catch (TimeoutException e) {
            // "user navigates back to ledger page" asserts nothing itself, so without this a failed
            // back-navigation would only show up as unrelated failures further on.
            attachTimeoutEvidence("Page did not reach " + path,
                    "Path did not become " + path + " within 10s; current: " + currentPath() + ".");
        }
    }
}
