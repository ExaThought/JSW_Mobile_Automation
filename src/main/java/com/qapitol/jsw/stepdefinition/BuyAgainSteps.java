package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.CustomUtil;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.appium.java_client.AppiumBy;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.qapitol.jsw.stepdefinition.SessionSteps.*;
import static com.qapitol.jsw.utils.CustomUtil.*;
import static com.qapitol.jsw.utils.CustomUtil.jsClick;
import static com.qapitol.jsw.utils.Hooks.softAssert;

public class BuyAgainSteps extends AbstractWebPage {

    public static final String CCP_HOME_PAGE_BUY_AGAIN_LINK = "//a[@href=\"/buy-again\"]";
    public static final String CCP_HOME_PAGE_ORDERS_BUTTON = "ccpHomePageOrdersButton";
    public static final String CCP_MY_ORDERS_PAGE_TITLE = "ccpMyOrdersPageTitle";
    public static final String CCP_HOME_PAGE_LEDGER_BUTTON = "ccpHomePageLedgerButton";
    public static final String CCP_LEDGER_PAGE_TITLE = "ccpLedgerPageTitle";
    public static final String CCP_BUY_AGAIN_CHECKBOX = "ccpBuyAgainCheckbox";
    public static final String CCP_BUY_AGAIN_ADD_TO_CART_BUTTON = "ccpBuyAgainAddToCartButton";
    public static final String CCP_BUY_AGAIN_ADD_TO_CART_COUNT = "//p[contains(normalize-space(),'item(s)')]";
    public static final String CCP_HOME_PAGE_MATERIAL_TRACKING_BUTTON = "//a[@href=\"/track-material\"]";
    public static final String CCP_MATERIAL_TRACKING_PAGE_TITLE = "//div[text()='Active and recent orders']";
    public static final String CCP_HOME_PAGE_PAYMENT_DUE_CLOSE_BUTTON = "ccpHomePagePaymentDueCloseButton";
    public static final String CCP_HOME_PAGE_PAYMENT_DUE_FLOATER = "ccpHomePagePaymentDueFloater";
    public static final String CCP_HOME_PAGE_PAYMENT_DUE_FLOATER_VIEW_DETAILS_BUTTON = "ccpHomePagePaymentDueFloaterViewDetailsButton";
    public static final String LEDGER_BTN = "//a[@href=\"/ledger\"]";
    public static final String CCP_HOME_LEDGER = "(//span[text()='Ledger'])[1]";
    public static final String BUYAGAIN_ITEM_TEXTBOX = "(//input[@data-slot=\"input\"])[1]";
    public static final String BUYAGAIN_MULTIPLE_ITEM_TEXTBOX = "(//input[@data-slot=\"input\"])[2]";
    public static final String ADD_TO_CART = "//button[text()='Add to cart']";
    public static final String ALL_BTN = "//button[contains(text(),'All')]";
    public static final String ACTIVE_BTN="//button[contains(text(),'Active')]";
    public static final String DELIVERED_BTN="//button[contains(text(),'Delivered')]";
    public static final String CANCELLED_BTN = "//button[contains(text(),'Cancelled')]";
    public static final String DOCUMENT_DATE = "//span[text()='Document date']";
    public static final String DUE_DATE = "//span[text()='Due date']";
    public static final String DOC_TYPE = "//span[text()='Document type']";
    public static final String ORDER_ID="//span[text()='Order ID']";
    public static final String DOC_ID = "//span[text()='Document ID']";
    public static final String DEBIT = "//span[text()='Debit']";
    public static final String CREDIT = "//span[text()='Credit']";
    public static final String DUE_STATUS = "//span[text()='Due status']";
    public static final String FINANCIAL_DOC_BTN = "//button[contains(text(), 'Financial documents')]";
    public static final String FINANCIAL_TITLE =
            "//h2[normalize-space()='Financial documents'] | //div[normalize-space()='Financial documents']";
    public static final String PAYMENT_INST = "//button[contains(text(),'Payment instructions')]";
    public static final String PAYMENT_DUE = "//span[contains(text(),'Payments due now')]";
    public static final String DUE_LATER = "//span[contains(text(),'Due later')]";
    public static final String USABLE_LEDGER_BALANCE = "//span[normalize-space()='Usable ledger balance']";
    public static final String PAYMENT_INST_POPUP = "//*[contains(text(),'Payment instructions')]";
    public static final String FINANCIAL_DOC = "//a[@href='/financial-documents']";
    public static final String ALL_DOCUMENT  = "//span[contains(text(),'All Documents')]";
    public static final String CURRENT_MONTH = "//span[contains(text(),'Current month')]";
    public static final String FROM_DATE = "//input[@id=\"from-date\"]";
    public static final String TO_DATE = "//input[@id=\"to-date\"]";
    public static final String REQUEST_BTN = "//span[text()='Request ']";
    public static final String FINANCIAL_DOC_DROPDOWN = "//span[contains(text(),' All Documents')]/../following-sibling::div/div";
    public static final String SHIPMENT_REPORT = "//span[contains(text(),'Shipment report')]";
    public static final String SHIPMENT_POPUP_TITLE = "//div[text()='Shipment report']";
    public static final String FROM_DATE_TEXRBOX = "//input[@id=\"shipment-report-start-date\"]";
    public static final String END_DATE_TEXTBOX = "//input[@id=\"shipment-report-end-date\"]";
    public static final String DOWNLOAD_BTN = "//span[text()='Download']";
    public static final String SKU_DETAILS = "//p[contains(text(),'SKU details')]";
    public static final String QTY="//p[contains(text(),'Qty')]";
    public static final String AMOUNT = "//p[contains(text(),'Amount')]";
    public static final String IN_PRODUCTION = "//p[contains(text(),'In production')]";
    public static final String INVENTORY_READY = "//p[contains(text(),'Inventory ready')]";
    public static final String IN_TRANSIT="//p[contains(text(),'In transit')]";
    public static final String DELIVERED = "//p[contains(text(),'Delivered')]";
    public static final String SKU_NAME = "(//div//h6)[1]";
    public static final String SKU_DETAILS_DATA = "((//div//h6)[1]/../../../following-sibling::td)[%s]";
    public static final String VIEW_ALL = "//h2[text()='Buy again']/following-sibling::button";
    public static List<String> nameList=new ArrayList<>();

    // ================= STEPS =================


    @And("user close the payment due pop up")
    public void userClosePaymentDuePopUp() {
        try {
            waitAndCheckIsElementVisible(CCP_HOME_PAGE_PAYMENT_DUE_CLOSE_BUTTON);
            waitAndClickOn(CCP_HOME_PAGE_PAYMENT_DUE_CLOSE_BUTTON);
        } catch (Exception e) {
            log.info("No payment due popup displayed");
        }
    }

    @And("user navigate to buy again page")
    public void userNavigateToBuyAgainPage() {

        clickButton(By.xpath(CCP_HOME_PAGE_BUY_AGAIN_LINK));
        log.info("Buy again button clicked");
        sleep(100);
        if(isDisplayed(By.xpath(MANAGE_POPUP_CLOSE)))
        {
            clickButton(By.xpath(MANAGE_POPUP_CLOSE));
        }
    }

    @And("Buy Again should not be displayed")
    public void buyAgainShouldnotBeDisplayed() {
        try {
            log.info("Verifying that Buy Again section is NOT displayed.");

            boolean isDisplayed = isDisplayed(By.xpath(CCP_HOME_PAGE_BUY_AGAIN_LINK));

            softAssert.assertFalse(
                    isDisplayed,
                    "Buy Again section is displayed but it should NOT be visible."
            );

            log.info("Buy Again section is not displayed as expected.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying Buy Again visibility: " + e.getMessage());
            softAssert.fail("Exception while verifying Buy Again visibility: " + e.getMessage());
        }
    }

    @Then("verify payment due floater pop up for Buy-again")
    public void verifyPaymentDueFloaterPopUpForBuyAgain() {

        waitAndCheckIsElementVisible(CCP_HOME_PAGE_PAYMENT_DUE_FLOATER);

        softAssert.assertTrue(
                isElementPresent(CCP_HOME_PAGE_PAYMENT_DUE_FLOATER),
                "Payment due floater is not displayed on Buy Again page"
        );

        softAssert.assertTrue(
                isElementPresent(CCP_HOME_PAGE_PAYMENT_DUE_FLOATER_VIEW_DETAILS_BUTTON),
                "'View details & pay' button is not displayed on Buy Again page"
        );

    }

    @And("user navigate to order button")
    public void userNavigateToOrderButton() {

        By ordersButton = By.xpath("//div[normalize-space()='Orders']");

        scrollToElement(ordersButton, 10);

        waitAndCheckIsElementClickable(CCP_HOME_PAGE_ORDERS_BUTTON);
        waitAndClickOn(CCP_HOME_PAGE_ORDERS_BUTTON);

    }
    @Then("verify order page title")
    public void verifyOrderPageTitle() {

        waitAndCheckIsElementVisible(CCP_MY_ORDERS_PAGE_TITLE);
        log.info("Verifying that the My Orders page title is displayed.");
        softAssert.assertTrue(
                isElementPresent(CCP_MY_ORDERS_PAGE_TITLE),
                "My Orders page title is not displayed"
        );

    }
    @And("user navigate to ledger button")
    public void userNavigateToLedgerButton() {

        clickButton(By.xpath(LEDGER_BTN));
    }




    @Then("verify ledger page title")
    public void verifyLedgerPageTitle() {
        log.info("User click on ledger tab");
        waitAndCheckIsElementVisible(CCP_LEDGER_PAGE_TITLE);

        softAssert.assertTrue(
                isElementPresent(CCP_LEDGER_PAGE_TITLE),
                "Ledger page title is not displayed"
        );

    }
    @And("user navigate to Material tracking button")
    public void userNavigateToMaterialTrackingButton() {
        try {
            log.info("Navigating to the Material Tracking page.");

            waits(By.xpath(CCP_HOME_PAGE_MATERIAL_TRACKING_BUTTON));
            clickButton(By.xpath(CCP_HOME_PAGE_MATERIAL_TRACKING_BUTTON));

            log.info("Successfully navigated to the Material Tracking page.");

        } catch (Exception e) {
            log.info("Exception occurred while navigating to the Material Tracking page."+e.getMessage());
            softAssert.fail("Failed to navigate to the Material Tracking page: " + e.getMessage());
        }
    }

    @Then("verify Material tracking page details")
    public void verifyMaterialTrackingPageDetails() {
        try {
            log.info("Verifying the Material Tracking page is displayed.");

            waits(By.xpath(CCP_MATERIAL_TRACKING_PAGE_TITLE));

            softAssert.assertTrue(
                    isDisplayed(By.xpath(CCP_MATERIAL_TRACKING_PAGE_TITLE)),
                    "Material Tracking page title is not displayed"
            );

            log.info("Material Tracking page title is displayed successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying Material Tracking page: " + e.getMessage());
            softAssert.fail("Exception occurred while verifying Material Tracking page: " + e.getMessage());
        }
    }

    @And("user click on checkbox for Add to cart")
    public void userClickOnCheckboxForAddToCart() {
        try {
            log.info("Entering quantity and clicking Add to Cart.");

            waits(By.xpath(BUYAGAIN_ITEM_TEXTBOX));

            enterText(By.xpath(BUYAGAIN_ITEM_TEXTBOX), "1");
            log.info("Entered quantity: 1");

            waits(By.xpath(ADD_TO_CART));
            clickButton(By.xpath(ADD_TO_CART));

            log.info("Clicked on Add to Cart button successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while clicking Add to Cart: " + e.getMessage());
            softAssert.fail("Failed to click Add to Cart: " + e.getMessage());
        }
    }

    @And("user selects multiple items and click Add to cart")
    public void userselectsmultipleitemsandclickAddtocart() {
        try {
            log.info("Selecting multiple items and adding them to cart.");

            // First item
            waits(By.xpath(BUYAGAIN_ITEM_TEXTBOX));
            jsClick(By.xpath(BUYAGAIN_ITEM_TEXTBOX));
            enterText(By.xpath(BUYAGAIN_ITEM_TEXTBOX), "1");
            log.info("Entered quantity 1 for first item.");

            Grid.driver().findElement(By.xpath(BUYAGAIN_ITEM_TEXTBOX)).sendKeys(Keys.ENTER);

            clickButton(By.xpath(ADD_TO_CART));
            log.info("Clicked Add to Cart for first item.");

            // Second item
            sleep(1000);
            waits(By.xpath(BUYAGAIN_MULTIPLE_ITEM_TEXTBOX));
            jsClick(By.xpath(BUYAGAIN_MULTIPLE_ITEM_TEXTBOX));

            enterText(By.xpath(BUYAGAIN_MULTIPLE_ITEM_TEXTBOX), "1");
            log.info("Entered quantity 1 for second item.");

            clickButton(By.xpath(ADD_TO_CART));
            log.info("Clicked Add to Cart for second item.");

            log.info("Successfully added multiple items to cart.");

        } catch (Exception e) {
            log.info("Exception occurred while adding multiple items to cart: " + e.getMessage());
            softAssert.fail("Failed while adding multiple items to cart: " + e.getMessage());
        }
    }
    @Then("verify {string} items added to cart from Buy-again")
    public void verifyItemsAddedToCartFromBuyAgain(String item) {
        try {
            log.info("Verifying items added to cart from Buy-Again section. Expected count: " + item);

            waits(By.xpath(CCP_BUY_AGAIN_ADD_TO_CART_COUNT));

            String actualText = CustomUtil.getText(By.xpath(CCP_BUY_AGAIN_ADD_TO_CART_COUNT));
            String actualCount = String.valueOf(CustomUtil.getItemCount(actualText));

            log.info("Actual cart count text: " + actualText);
            log.info("Parsed cart count: " + actualCount);

            softAssert.assertEquals(
                    actualCount,
                    item,
                    "Item count not updated correctly on Add to Cart button."
            );

            log.info("Cart item count verified successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying items added to cart: " + e.getMessage());
            softAssert.fail("Exception while verifying cart item count: " + e.getMessage());
        }
    }

    public void scrollToElement(By locator, int timeoutInSeconds) {

        WebElement element = new WebDriverWait(Grid.driver(), Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(locator));

        ((JavascriptExecutor) Grid.driver())
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
                        element
                );
    }

    @And("Verify the order status")
    public void verifyTheOrderStatus() {
        try {
            log.info("Verifying the Order Status section.");

            waits(By.xpath(ALL_BTN));

            log.info("Verifying 'All' button is displayed.");
            softAssert.assertTrue(isDisplayed(By.xpath(ALL_BTN)),
                    "'All' button is not displayed.");

            log.info("'All' button is displayed.");

            log.info("Verifying 'Active' button is displayed.");
            softAssert.assertTrue(isDisplayed(By.xpath(ACTIVE_BTN)),
                    "'Active' button is not displayed.");

            log.info("'Active' button is displayed.");

            log.info("Verifying 'Cancelled' button is displayed.");
            softAssert.assertTrue(isDisplayed(By.xpath(CANCELLED_BTN)),
                    "'Cancelled' button is not displayed.");

            log.info("'Cancelled' button is displayed.");

            log.info("Verifying 'Delivered' button is displayed.");
            softAssert.assertTrue(isDisplayed(By.xpath(DELIVERED_BTN)),
                    "'Delivered' button is not displayed.");

            log.info("'Delivered' button is displayed.");

            log.info("Successfully verified all order status buttons.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying order status."+ e.getMessage());
            softAssert.fail("Exception occurred while verifying order status: " + e.getMessage());
        }
    }

    @Then("verify Financial Doc, Payment instruction is displayed")
    public void verifyLedgerTableIsDisplayed() {
        try {
            log.info("Verifying Financial Document and Payment Instruction section.");

            waits(By.xpath(PAYMENT_INST));

            log.info("Verifying 'Payment Instruction' is displayed.");
            softAssert.assertTrue(isDisplayed(By.xpath(PAYMENT_INST)),
                    "Payment Instruction is not displayed.");
            log.info("'Payment Instruction' is displayed.");

            log.info("Verifying 'Financial Document' button is displayed.");
            softAssert.assertTrue(isDisplayed(By.xpath(FINANCIAL_DOC_BTN)),
                    "Financial Document button is not displayed.");
            log.info("'Financial Document' button is displayed.");

            log.info("Verifying 'Payment Due' is displayed.");
            softAssert.assertTrue(isDisplayed(By.xpath(PAYMENT_DUE)),
                    "Payment Due is not displayed.");
            log.info("'Payment Due' is displayed.");

            log.info("Verifying 'Due Later' is displayed.");
            softAssert.assertTrue(isDisplayed(By.xpath(DUE_LATER)),
                    "Due Later is not displayed.");
            log.info("'Due Later' is displayed.");

            log.info("Verifying 'Usable Ledger Balance' is displayed.");
            softAssert.assertTrue(isDisplayed(By.xpath(USABLE_LEDGER_BALANCE)),
                    "Usable Ledger Balance is not displayed.");
            log.info("'Usable Ledger Balance' is displayed.");

            log.info("Successfully verified all Financial Document and Payment Instruction elements.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying Financial Document and Payment Instruction elements."+ e.getMessage());
            softAssert.fail("Exception while verifying Financial Document and Payment Instruction elements: " + e.getMessage());
        }
    }
    @And("user click on Financial document button")
    public void userClickOnFinancialDocumentButton() {
        clickButton(By.xpath(FINANCIAL_DOC_BTN));
    }

    @Then("verify the Financial document title")
    public void verifyTheFinancialDocumentTitle() {
        try {
            log.info("Verifying the Financial Document title is displayed.");
            waitForElementPresent(FINANCIAL_TITLE, 10);

            softAssert.assertTrue(
                    isDisplayed(By.xpath(FINANCIAL_TITLE)),
                    "Financial Document title is not displayed."
            );

            log.info("Financial Document title is displayed successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying the Financial Document title."+ e.getMessage());
            softAssert.fail("Exception occurred while verifying the Financial Document title: " + e.getMessage());
        }
    }

    @Then("User click on payment instruction button")
    public void userClickOnPaymentInstructionButton() {
        log.info("User click on payment inst popup");
        clickButton(By.xpath(PAYMENT_INST_POPUP));
    }


    @Then("Verify the payment instruction popup")
    public void verifyThePaymentInstructionPopup() {
        if(isDisplayed(By.xpath(PAYMENT_INST_POPUP)))
        {
            softAssert.assertTrue(true);
        }else {
            softAssert.assertTrue(false);
        }
    }

    @And("user navigate to Financial Document")
    public void userNavigateToFinancialDocument() {
        try {
            log.info("Navigating to the Financial Document page.");

            waits(By.xpath(FINANCIAL_DOC));
            clickButton(By.xpath(FINANCIAL_DOC));

            log.info("Successfully navigated to the Financial Document page.");

        } catch (Exception e) {
            log.info("Exception occurred while navigating to the Financial Document page."+ e.getMessage());
            softAssert.fail("Failed to navigate to the Financial Document page: " + e.getMessage());
        }
    }

    @Then("verify All document drop down,current month, from, to date")
    public void verifyAllDocumentDropDownCurrentMonthFromToDate() {
        try {
            log.info("Verifying All Document dropdown, Current Month, From Date, To Date, and Request button.");

            waits(By.xpath(ALL_DOCUMENT));

            log.info("Verifying 'All Document' dropdown is displayed.");
            softAssert.assertTrue(
                    isDisplayed(By.xpath(ALL_DOCUMENT)),
                    "'All Document' dropdown is not displayed."
            );

            log.info("'All Document' dropdown is displayed.");

            log.info("Verifying 'Current Month' is displayed.");
            softAssert.assertTrue(
                    isDisplayed(By.xpath(CURRENT_MONTH)),
                    "'Current Month' is not displayed."
            );

            log.info("'Current Month' is displayed.");

            log.info("Verifying 'From Date' is displayed.");
            softAssert.assertTrue(
                    isDisplayed(By.xpath(FROM_DATE)),
                    "'From Date' is not displayed."
            );

            log.info("'From Date' is displayed.");

            log.info("Verifying 'To Date' is displayed.");
            softAssert.assertTrue(
                    isDisplayed(By.xpath(TO_DATE)),
                    "'To Date' is not displayed."
            );

            log.info("'To Date' is displayed.");

            log.info("Verifying 'Request' button is displayed.");
            softAssert.assertTrue(
                    isDisplayed(By.xpath(REQUEST_BTN)),
                    "'Request' button is not displayed."
            );

            log.info("'Request' button is displayed.");
            log.info("Successfully verified all Financial Document filter elements.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying Financial Document filter elements." +e.getMessage());
            softAssert.fail("Exception occurred while verifying Financial Document filter elements: " + e.getMessage());
        }
    }

    @Then("Verify the financial document type")
    public void verifyTheFinancialDocumentType() {
        try {
            log.info("Verifying the Financial Document Type dropdown values.");

            waits(By.xpath(ALL_DOCUMENT));
            clickButton(AppiumBy.xpath(ALL_DOCUMENT));

            waits(By.xpath(FINANCIAL_DOC_DROPDOWN));

            List<String> expectedValues = Arrays.asList(
                    "All documents",
                    "Credit note",
                    "Debit note",
                    "Invoice"
            );

            List<WebElement> actualOptions = Grid.driver().findElements(By.xpath(FINANCIAL_DOC_DROPDOWN));

            for (WebElement option : actualOptions) {
                String actualValue = getText(option).trim();

                log.info("Verifying dropdown option: "+ actualValue);

                softAssert.assertTrue(
                        expectedValues.contains(actualValue),
                        "Unexpected dropdown value found: " + actualValue
                );
            }

            log.info("Successfully verified Financial Document Type dropdown values.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying Financial Document Type dropdown values."+ e.getMessage());
            softAssert.fail("Exception occurred while verifying Financial Document Type dropdown values: " + e.getMessage());
        }
    }

    @Then("user click on Shipment Report button")
    public void userClickOnShipmentReportButton() {
        clickButton(By.xpath(SHIPMENT_REPORT));
        log.info("Shipment report button clicked");
    }

    @Then("User enters the date and clicked on download button")
    public void userEntersTheDateAndClikedOnDownloadButton() {
        try {
            log.info("Verifying shipment popup is displayed before entering dates.");

            softAssert.assertTrue(
                    isDisplayed(By.xpath(SHIPMENT_POPUP_TITLE)),
                    "Shipment popup title is not displayed."
            );

            log.info("Shipment popup is displayed. Proceeding with date entry.");

            waits(By.xpath(FROM_DATE_TEXRBOX));

            WebElement fromDate = Grid.driver().findElement(By.xpath(FROM_DATE_TEXRBOX));
            WebElement toDate = Grid.driver().findElement(By.xpath(END_DATE_TEXTBOX));

            String fromDateValue = getDateMonthsBack(2);
            String toDateValue = getCurrentDate();

            log.info("Entering From Date: " + fromDateValue);
            log.info("Entering To Date: " + toDateValue);

            setValueJS(fromDate, fromDateValue);
            setValueJS(toDate, toDateValue);

            log.info("Clicking Download button.");
            clickButton(By.xpath(DOWNLOAD_BTN));

            log.info("Download button clicked successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while entering dates and clicking download: " + e.getMessage());
            softAssert.fail("Exception occurred while downloading: " + e.getMessage());
        }
    }

    @Then("User verifies the material tracking table")
    public void userVerifiesTheMaterialTrackingTable() {
        try {
            log.info("Verifying Material Tracking table columns.");

            waits(By.xpath(SKU_DETAILS));

            softAssert.assertTrue(isDisplayed(By.xpath(SKU_DETAILS)),
                    "SKU Details column is not displayed.");

            softAssert.assertTrue(isDisplayed(By.xpath(QTY)),
                    "Quantity column is not displayed.");

            softAssert.assertTrue(isDisplayed(By.xpath(AMOUNT)),
                    "Amount column is not displayed.");

            softAssert.assertTrue(isDisplayed(By.xpath(IN_PRODUCTION)),
                    "In Production column is not displayed.");

            softAssert.assertTrue(isDisplayed(By.xpath(INVENTORY_READY)),
                    "Inventory Ready column is not displayed.");

            softAssert.assertTrue(isDisplayed(By.xpath(IN_TRANSIT)),
                    "In Transit column is not displayed.");

            softAssert.assertTrue(isDisplayed(By.xpath(DELIVERED)),
                    "Delivered column is not displayed.");

            log.info("All Material Tracking table columns are displayed successfully.");

            log.info("Verifying SKU row data.");

            if (isDisplayed(By.xpath(SKU_NAME))) {

                int i = 1;

                while (i <= 6) {

                    boolean isDataDisplayed = isDisplayed(getDynamicXpath(SKU_DETAILS_DATA, i));

                    softAssert.assertTrue(isDataDisplayed,
                            "SKU data not displayed at index: " + i);

                    log.info("Verified SKU data at index: " + i);

                    i++;
                }
            }

            log.info("SKU data verification completed successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying Material Tracking table: " + e.getMessage());
            softAssert.fail("Exception in Material Tracking table verification: " + e.getMessage());
        }
    }

    @Then("user click on view all in Buy Again section")
    public void userClickOnViewAllInBuyAgainSection() {
        clickButton(By.xpath(VIEW_ALL));
        log.info("user clicked view all button");
    }

    @And("User navigate to Bug Again page")
    public void userNavigateToBugAgainPage() {
        try {
            log.info("Navigating to Buy Again page.");

            // Close popup if present
            if (isDisplayed(By.xpath(MANAGE_POPUP_CLOSE))) {
                log.info("Manage popup is displayed. Closing it.");
                clickButton(By.xpath(MANAGE_POPUP_CLOSE));
            } else {
                log.info("Manage popup is not displayed.");
            }

            waits(By.xpath(BUYAGAIN_ITEM_TEXTBOX));

            softAssert.assertTrue(
                    isDisplayed(By.xpath(BUYAGAIN_ITEM_TEXTBOX)),
                    "User did not land on Buy Again page."
            );

            log.info("User successfully landed on Buy Again page.");

        } catch (Exception e) {
            log.info("Exception occurred while navigating to Buy Again page: " + e.getMessage());
            softAssert.fail("Exception while navigating to Buy Again page: " + e.getMessage());
        }
    }

    @Then("Verify the category image")
    public void verifyTheCategoryImage() {
        try {
            log.info("Verifying category images are displayed.");

            waits(By.xpath(CATEGORY_IMG));

            List<WebElement> images = Grid.driver().findElements(By.xpath(CATEGORY_IMG));

            softAssert.assertTrue(images.size() > 0, "No category images found on the page.");

            for (int i = 0; i < images.size(); i++) {
                boolean displayed = images.get(i).isDisplayed();

                softAssert.assertTrue(
                        displayed,
                        "Category image not displayed at index: " + i
                );

                log.info("Category image verified at index: " + i);
            }

            log.info("All category images are displayed successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying category images: " + e.getMessage());
            softAssert.fail("Exception while verifying category images: " + e.getMessage());
        }
    }

    @Then("Verify the category name")
    public void verifyTheCategoryName() {
        try {
            log.info("Verifying category names are displayed.");

            waits(By.xpath(CATEGORY_NAME));

            List<WebElement> categoryNames =
                    Grid.driver().findElements(By.xpath(CATEGORY_NAME));

            softAssert.assertTrue(
                    categoryNames.size() > 0,
                    "No category names found on the page."
            );

            for (int i = 0; i < categoryNames.size(); i++) {

                boolean isDisplayed = categoryNames.get(i).isDisplayed();

                softAssert.assertTrue(
                        isDisplayed,
                        "Category name not displayed at index: " + i
                );

                log.info("Verified category name at index: " + i);
            }

            log.info("All category names are displayed successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying category names: " + e.getMessage());
            softAssert.fail("Exception while verifying category names: " + e.getMessage());
        }
    }

    @Then("Verify the brand name")
    public void verifyTheBrandName() {
        try {
            log.info("Verifying Brand names are displayed.");

            waits(By.xpath(TOP_BRAND_NAME));

            List<WebElement> brandNames =
                    Grid.driver().findElements(By.xpath(TOP_BRAND_NAME));

            softAssert.assertTrue(
                    brandNames.size() > 0,
                    "No brand names found on the page."
            );

            for (int i = 0; i < brandNames.size(); i++) {

                boolean isDisplayed = brandNames.get(i).isDisplayed();

                softAssert.assertTrue(
                        isDisplayed,
                        "Brand name not displayed at index: " + i
                );

                log.info("Verified brand name at index: " + i);
            }

            log.info("All brand names are displayed successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying brand names: " + e.getMessage());
            softAssert.fail("Exception while verifying brand names: " + e.getMessage());
        }
    }
    @Then("verify user is redirects to corresponding PLP page from Browse by categories")
    public void verifyUserIsRedirectedToCorrespondingPLPPAGE() {
        try {
            log.info("Verifying navigation to corresponding PLP pages from Browse by categories.");

            waits(By.xpath(CATEGORY_NAME));

            List<WebElement> names = Grid.driver().findElements(By.xpath(CATEGORY_NAME));

            List<String> nameList = names.stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());

            log.info("Category list found: " + nameList);

            String categoryXpath =
                    "//h2[text()='Browse by categories']/../following-sibling::div//p[normalize-space()='%s']";

            for (String category : nameList) {

                log.info("Clicking category: " + category);

                By categoryLocator = By.xpath(String.format(categoryXpath, category));

                waits(categoryLocator);
                clickButton(categoryLocator);

                By plpLocator = By.xpath(String.format(PLP_NAME, category));

                boolean isPlpDisplayed = isDisplayed(plpLocator);

                softAssert.assertTrue(
                        isPlpDisplayed,
                        "PLP page not displayed for category: " + category
                );

                log.info("PLP page verified for category: " + category);

                clickButton(By.xpath(HOME_PAGE_LINK));

                waits(By.xpath(CATEGORY_NAME));
            }

            log.info("Successfully verified all category PLP navigations.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying PLP navigation: " + e.getMessage());
            softAssert.fail("Exception in PLP navigation verification: " + e.getMessage());
        }
    }

    @Then("verify user is redirects to corresponding brand PLP page.")
    public void verifyUserIsRedirectedToCorrespondingBrandPLPPAGE() {
        try {
            log.info("Verifying navigation to corresponding Brand PLP pages.");

            waits(By.xpath(TOP_BRAND_NAME));

            List<WebElement> names = Grid.driver().findElements(By.xpath(TOP_BRAND_NAME));

            List<String> nameList = names.stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());

            log.info("Top brand list found: " + nameList);

            String brandXpath =
                    "//h2[text()='Top Brands']/../following-sibling::div//p[normalize-space()='%s']";

            for (String brand : nameList) {

                log.info("Clicking brand: " + brand);

                By brandLocator = By.xpath(String.format(brandXpath, brand));

                waits(brandLocator);
                clickButton(brandLocator);

                By plpLocator = By.xpath(String.format(PLP_NAME, brand));

                boolean isPlpDisplayed = isDisplayed(plpLocator);

                softAssert.assertTrue(
                        isPlpDisplayed,
                        "PLP page not displayed for brand: " + brand
                );

                log.info("PLP page verified for brand: " + brand);

                clickButton(By.xpath(HOME_PAGE_LINK));

                waits(By.xpath(TOP_BRAND_NAME));
            }

            log.info("Successfully verified all brand PLP navigations.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying brand PLP navigation: " + e.getMessage());
            softAssert.fail("Exception in brand PLP navigation: " + e.getMessage());
        }
    }

    @Then("user click on view all category")
    public void userClickOnViewAllCategory() {
        waits(By.xpath(CATEGORY_NAME));
        List<WebElement> names = Grid.driver().findElements(By.xpath(CATEGORY_NAME));

        nameList = names.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        clickButton(By.xpath(CATEGORY_VIEW_ALL));

    }

    @Then("user click on view all brand")
    public void userClickOnViewAllbrand() {
        try {
            log.info("Fetching top brand names before clicking 'View All'.");

            waits(By.xpath(TOP_BRAND_NAME));

            List<WebElement> names = Grid.driver().findElements(By.xpath(TOP_BRAND_NAME));

            List<String> nameList = names.stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());

            log.info("Top brand list captured: " + nameList);

            log.info("Clicking on View All brands button.");
            waits(By.xpath(BRAND_VIEW_ALL));
            clickButton(By.xpath(BRAND_VIEW_ALL));

            log.info("Successfully clicked on View All brands.");

        } catch (Exception e) {
            log.info("Exception occurred while clicking View All brands: " + e.getMessage());
            softAssert.fail("Exception in View All brands action: " + e.getMessage());
        }
    }

    @Then("User navigate to All category")
    public void userNavigateToAllCategory() {

        softAssert.assertTrue(isDisplayed(By.xpath(ALL_CATEGORY)));
        waits(By.xpath(CTAEGORY_NAME_LIST));
        List<WebElement> cnames = Grid.driver().findElements(By.xpath(CTAEGORY_NAME_LIST));

        List<String> cnameList = cnames.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        log.info(""+cnameList+"  "+nameList);
        if(cnameList.equals(nameList))
        {
            softAssert.assertTrue(true);
        }else {
            softAssert.assertTrue(false);
        }

    }

    @Then("User navigate to All Brand")
    public void userNavigateToAllBrand() {
        try {
            log.info("Verifying 'All Brand' section is displayed.");

            waits(By.xpath(ALL_BRAND));

            boolean isAllBrandDisplayed = isDisplayed(By.xpath(ALL_BRAND));

            softAssert.assertTrue(
                    isAllBrandDisplayed,
                    "'All Brand' section is not displayed."
            );

            log.info("'All Brand' section is displayed successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying All Brand section: " + e.getMessage());
            softAssert.fail("Exception while verifying All Brand section: " + e.getMessage());
        }
    }


    @Then("Verify the Brand image")
    public void verifyTheBrandImage() {
        try {
            log.info("Verifying Brand images are displayed.");

            waits(By.xpath(POPULAR_CATEGORY_IMG));

            List<WebElement> images =
                    Grid.driver().findElements(By.xpath(POPULAR_CATEGORY_IMG));

            softAssert.assertTrue(
                    images.size() > 0,
                    "No brand images found on the page."
            );

            for (int i = 0; i < images.size(); i++) {

                boolean displayed = images.get(i).isDisplayed();

                softAssert.assertTrue(
                        displayed,
                        "Brand image not displayed at index: " + i
                );

                log.info("Verified brand image at index: " + i);
            }

            log.info("All brand images are displayed successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying brand images: " + e.getMessage());
            softAssert.fail("Exception while verifying brand images: " + e.getMessage());
        }
    }

    @Then("Verify the popular category image")
    public void verifyThePopularCategoryImage() {
        waits(By.xpath(TOP_BRAND_IMG));
        try {

            List<WebElement> images = Grid.driver().findElements(By.xpath(TOP_BRAND_IMG));

            for (WebElement image : images) {
                if (!image.isDisplayed()) {
                    softAssert.assertTrue(false);
                }
            }
            softAssert.assertTrue(true);

        } catch (Exception e) {
            softAssert.assertTrue(false);
        }
    }

    @Then("User navigate to Popular category")
    public void userNavigateToPopularCategory() {

        waits(By.xpath(POPULAR_VIEW_ALL_LINK_NAME));
        List<WebElement> names = Grid.driver().findElements(By.xpath(POPULAR_VIEW_ALL_LINK_NAME));
        List<String> nameList = names.stream()
                .map(WebElement::getText)
                .filter(text -> !text.isEmpty())
                .collect(Collectors.toList());
        log.info(""+nameList);
        waits(By.xpath(POPULAR_VIEW_ALL_LINK));
        List<WebElement> links = Grid.driver().findElements(By.xpath(POPULAR_VIEW_ALL_LINK));
        for(WebElement element:links)
        {
            moveToAction(element);
            waits(By.xpath("//span[contains(text(),'Found')]/preceding-sibling::h1"));
            log.info(""+getText(By.xpath("//span[contains(text(),'Found')]/preceding-sibling::h1")));
            if(nameList.contains(getText(By.xpath("//span[contains(text(),'Found')]/preceding-sibling::h1"))))
            {
                softAssert.assertTrue(true);
                sleep(500);
                clickButton(By.xpath(HOME_PAGE_LINK));
                sleep(1000);
            }else
            {
                softAssert.assertTrue(false);
            }
        }

    }

    @Then("Verify youtube links for Master class for MSME section")
    public void verifyYoutubeLinksForMasterClassForMSMESection() {
        try {
            log.info("Verifying YouTube links in Master Class for MSME section.");

            waits(By.xpath(MASTER_CLASS_MSME_LINKS));

            List<WebElement> links =
                    Grid.driver().findElements(By.xpath(MASTER_CLASS_MSME_LINKS));

            softAssert.assertTrue(
                    links.size() > 0,
                    "No YouTube links found in Master Class for MSME section."
            );

            for (int i = 0; i < links.size(); i++) {

                boolean displayed = links.get(i).isDisplayed();

                softAssert.assertTrue(
                        displayed,
                        "YouTube link not displayed at index: " + i
                );

                log.info("Verified YouTube link at index: " + i);
            }

            log.info("All YouTube links in Master Class for MSME section are displayed successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying YouTube links: " + e.getMessage());
            softAssert.fail("Exception while verifying YouTube links: " + e.getMessage());
        }
    }

    @Then("Verify From Our Customer section links")
    public void verifyFromOurCustomerSectionLinks() {
        waits(By.xpath(FORM_OUR_CUSTOMER));
        List<WebElement> links = Grid.driver().findElements(By.xpath(FORM_OUR_CUSTOMER));
        for(WebElement element : links)
        {
            if (isDisplayed(element))
            {
                softAssert.assertTrue(true);
            }else {
                softAssert.assertTrue(false);
            }
            sleep(200);
        }
    }
}
