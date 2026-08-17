package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.apitest.apis.impl.CtSlcSlocreationImpl;
import com.qapitol.jsw.apitest.pojos.order.ctSlcSloResponse.CtSlcSloRes;
import com.qapitol.jsw.utils.AllureCustomLogReporter;
import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.jsw.utils.Token;
import com.qapitol.jsw.utils.UserConfig;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;


import java.io.IOException;

import static com.qapitol.jsw.apitest.apis.impl.CtSlcSlocreationImpl.*;
import static com.qapitol.jsw.utils.Hooks.*;
import static com.qapitol.sauron.web.WebActionListener.log;


public class SfSlcSloSteps extends AbstractWebPage {

    public static final String CART_PAGE = "cartPage";
    public static final String ERP_PAYMENTYPE = "ErpPaymentType";
    public static final String CCP_CART_LINEITEMS_CASHBACK = "cartLineItemCashBack";
    public static final String CCP_CART_LINE_ITEMS_PRODUCT_AMOUNT = "cartLineItemProductAmount";
    public static final String CCP_CART_LINE_ITEM_ATTRIBUTES = "cartLineItemAttributes";
    public static final String CCP_CART_LINE_ITEM_QUANTITY = "cartLineItemQuantity";
    public static final String CCP_CART_SHIP_TO = "cartShipTo";
    public static final String CCP_CART_BILL_TO = "cartBillTo";
    public static final String CCP_ORDER_NUMBER = "ccpOrderNumber";
    public static final String CCP_CREATE_ORDER_CTA = "ccpCreateOrderCTA";
    public static final String ERP_ORDER_Number = "erpOrderNumber";
    public static final String ERP_ORDER_CATEGORY = "erpOrderCategory";
    public static final String ERP_MMID = "erpMMID";
    public static final String ERP_SKU_DETAILS = "erpSKUDetails";
    public static final String ERP_CASHBACK = "erpCashBack";
    public static final String ERP_ORDER_PAYMENT_TYPE = "erpOrderPaymentType";
    public static final String ERP_ORDER_QUANTITY = "erpOrderQuantity";
    public static final String ERP_TOTAL_AMOUNT = "erpTotalAmount";
    public static final String ERP_DELIVERY_TYPE = "erpDeliveryType";
    CtSlcSloRes response;
    String ccpOrderNumber;
    String orderType;
    CtSlcSlocreationImpl ctSlcSlocreation;

    @When("Request and create an cashAndCarry distributer cart")
    public void request_and_create_an_cashAndCarry_distributer_cart() throws IOException {
        ctSlcSlocreation = new CtSlcSlocreationImpl();
        orderType="CT_SLC_SLO_CASH_AND_CARRY";
        response = ctSlcSlocreation.getSlcSloResponse(orderType);
    }
    @When("Request and create an cashAndCarryAdvance distributer cart")
    public void request_and_create_an_cashAndCarryAdvance_distributer_cart() throws IOException {
        ctSlcSlocreation = new CtSlcSlocreationImpl();
        orderType="CT_SLC_CASH_AND_CARRY_ADVANCE";
        response=ctSlcSlocreation.getSlcSloResponse(orderType);
    }
    @When("Request and create an credit distributer cart")
    public void request_and_create_an_credit_distributer_cart() throws IOException {
        ctSlcSlocreation = new CtSlcSlocreationImpl();
        orderType="CT_SLC_CREDIT";
        response=ctSlcSlocreation.getSlcSloResponse(orderType);
    }
    @When("Request and create an cash and carry withAdvance direct cart")
    public void request_and_create_an_cash_and_carry_withAdvance_direct_cart() throws IOException {
        ctSlcSlocreation = new CtSlcSlocreationImpl();
        orderType="CT_SLC_CASH_AND_CARRY_ADVANCE";
        response=ctSlcSlocreation.getSlcSloResponse(orderType);
    }
    @When("Request and create an cash and carry ZeroAdvance direct cart")
    public void request_and_create_an_cash_and_carry_ZeroAdvance_direct_cart() throws IOException {
        ctSlcSlocreation = new CtSlcSlocreationImpl();
        orderType="CT_SLC_CASH_AND_CARRY_ADVANCE";
        response=ctSlcSlocreation.getSlcSloResponse(orderType);
    }
    @When("Request and create an Credit direct cart")
    public void request_and_create_an_credit_direct_cart() throws IOException {
        ctSlcSlocreation = new CtSlcSlocreationImpl();
        orderType="CT_SLC_CREDIT";
        response=ctSlcSlocreation.getSlcSloResponse(orderType);
    }


    @And("Open the cart and verify the cart details")
    public void open_the_cart_and_verify_the_cart_details() throws InterruptedException {

        waitAndCheckIsElementPresent(CART_PAGE);
        waitAndCheckIsElementVisible(CART_PAGE);
        waitAndClickOn(CART_PAGE);//Open Cart Page

        // Guard first: if the SLC cart was not created for the logged-in customer the cart is
        // empty, and every line-item locator below would time out with a misleading
        // "element not found" instead of naming the real cause.
        if (!waitAndCheckIsElementPresent(CCP_CART_LINE_ITEMS_PRODUCT_AMOUNT)) {
            String reason = "Cart has no line items after creating the " + orderType + " cart via API."
                    + " The cart was created for customerId '" + requestCustomerId + "'"
                    + " but the browser is logged in as '" + UserConfig.getEmail() + "'."
                    + " Verify both resolve to the same customer.";
            log.info(reason);
            Allure.addAttachment("Empty cart", reason);
            softAssert.fail(reason);
            return;
        }

        //Validate Line Item
        softAssert.assertEquals(safeGetText(CCP_CART_LINE_ITEMS_PRODUCT_AMOUNT), formatLineItemTotalAmount,
                "Failing in line Item Total Amount");
        softAssert.assertEquals(safeGetText(CCP_CART_LINEITEMS_CASHBACK), "₹" + (int) requestedCashBack + "/MT",
                "Failing in line Item CashBack");
        softAssert.assertFalse(isBlank(safeGetText(CCP_CART_LINE_ITEM_QUANTITY)),
                "Line item quantity is not present in the cart");

        // Attributes are asserted for presence and for the values the payload actually carries.
        // An exact full-string comparison is not possible yet: these payloads have WIDTH_MM and
        // THICKNESS_MM but no LENGTH_MM, so requestLength is null.
        String cartAttributes = safeGetText(CCP_CART_LINE_ITEM_ATTRIBUTES);
        softAssert.assertFalse(isBlank(cartAttributes), "Line item attributes are not present in the cart");
        if (!isBlank(cartAttributes)) {
            if (requestThickness != null) {
                softAssert.assertTrue(cartAttributes.contains(requestThickness),
                        "Thickness '" + requestThickness + "' missing from cart attributes '" + cartAttributes + "'");
            }
            if (requestWidth != null) {
                softAssert.assertTrue(cartAttributes.contains(requestWidth),
                        "Width '" + requestWidth + "' missing from cart attributes '" + cartAttributes + "'");
            }
        }
        softAssert.assertFalse(isBlank(safeGetText(CCP_CART_SHIP_TO)), "Ship To is not present in the cart");
        softAssert.assertFalse(isBlank(safeGetText(CCP_CART_BILL_TO)), "Bill To is not present in the cart");

        waitAndCheckIsElementPresent(CCP_CREATE_ORDER_CTA);
        waitAndCheckIsElementVisible(CCP_CREATE_ORDER_CTA);
        waitAndClickOn(CCP_CREATE_ORDER_CTA);

    }

    /**
     * Reads an element's text without aborting the scenario when the element is missing.
     *
     * waitAndGetText throws a TimeoutException when the locator does not resolve, and because
     * that happens while evaluating the argument of softAssert.assertEquals(...) the soft assert
     * never registers - the whole scenario dies on the first stale locator. Returning null
     * instead lets every remaining assertion run and report.
     */
    private String safeGetText(String locatorKey) {
        try {
            if (!waitAndCheckIsElementPresent(locatorKey)) {
                log.info("Element not present in the cart for locator key: " + locatorKey);
                return null;
            }
            return waitAndGetText(locatorKey);
        } catch (Exception e) {
            log.info("Could not read text for locator key '" + locatorKey + "': " + e.getMessage());
            return null;
        }
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    /**
     * Strips currency symbols, separators and units, e.g. "₹1,78,180.00" -> 178180.0.
     *
     * @return the parsed value, or {@code Double.NaN} when the text is missing or unparsable,
     *         so the soft assert reports a mismatch instead of throwing.
     */
    private static double parseAmount(String text) {
        if (isBlank(text)) {
            return Double.NaN;
        }
        String digits = text.replaceAll("[^\\d.]", "");
        try {
            return digits.isEmpty() ? Double.NaN : Double.parseDouble(digits);
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }

    /**
     * Reads the leading number out of a quantity cell, e.g. "15.0 MT" -> 15.0.
     *
     * @return the parsed value, or {@code Double.NaN} when the text is missing or unparsable.
     */
    private static double parseQuantity(String text) {
        if (isBlank(text)) {
            return Double.NaN;
        }
        try {
            return Double.parseDouble(text.trim().split("\\s+")[0].replaceAll("[^\\d.]", ""));
        } catch (Exception e) {
            return Double.NaN;
        }
    }

    @And("Create an order and verify the order summery page")
    public void create_an_order_and_verify_the_order_summery_page() {

        sleep(3000);
        ccpOrderNumber = waitAndGetText(CCP_ORDER_NUMBER);
        AllureCustomLogReporter.addTestStepLog(requestOrderType+" "+orderType+" order number="+ccpOrderNumber);
    }
    @When("Request and create an cashAndCarry distributor order")
    public void request_and_create_an_cashAndCarry_distributor_order() throws IOException {
        ctSlcSlocreation = new CtSlcSlocreationImpl();
        orderType="CT_SLC_SLO_CASH_AND_CARRY";
        response = ctSlcSlocreation.getSlcSloResponse(orderType);
        ccpOrderNumber=String.valueOf(response.getOrderNumber());
    }
    @When("Request and create an cashAndCarryAdvance distributor order")
    public void request_and_create_an_cashAndCarryAdvance_distributor_order() throws IOException {
        ctSlcSlocreation = new CtSlcSlocreationImpl();
        orderType="CT_SLC_CASH_AND_CARRY_ADVANCE";
        response = ctSlcSlocreation.getSlcSloResponse(orderType);
        ccpOrderNumber=String.valueOf(response.getOrderNumber());
    }
    @When("Request and create an credit distributor order")
    public void request_and_create_an_credit_distributor_order() throws IOException {
        ctSlcSlocreation = new CtSlcSlocreationImpl();
        orderType="CT_SLC_CREDIT";
        response = ctSlcSlocreation.getSlcSloResponse(orderType);
        ccpOrderNumber=String.valueOf(response.getOrderNumber());
    }
    @When("Request and create an cash and carry withAdvance direct order")
    public void request_and_create_an_cash_and_carry_withAdvance_direct_order() throws IOException {
        ctSlcSlocreation = new CtSlcSlocreationImpl();
        orderType="CT_SLC_CASH_AND_CARRY_ADVANCE";
        response = ctSlcSlocreation.getSlcSloResponse(orderType);
        ccpOrderNumber=String.valueOf(response.getOrderNumber());
    }
    @When("Request and create an cash and carry ZeroAdvance direct order")
    public void request_and_create_an_cash_and_carry_zeroAdvance_direct_order() throws IOException {
        ctSlcSlocreation = new CtSlcSlocreationImpl();
        orderType="CT_SLC_CASH_AND_CARRY_ADVANCE";
        response = ctSlcSlocreation.getSlcSloResponse(orderType);
        ccpOrderNumber=String.valueOf(response.getOrderNumber());
    }
    @When("Request and create an Credit direct order")
    public void request_and_create_an_credit_direct_order() throws IOException {
        ctSlcSlocreation = new CtSlcSlocreationImpl();
        orderType="CT_SLC_CREDIT";
        response = ctSlcSlocreation.getSlcSloResponse(orderType);
        ccpOrderNumber=String.valueOf(response.getOrderNumber());
    }

    @And("open the ERP and verify all the SFSlc order data")
    public void open_the_ERP_and_verify_all_the_SFSlc_order_data() {
        //Open the ERP
        Grid.driver().get(Token.getErpLoginUrl());
        Grid.driver().manage().window().maximize();
        sleep(5000);

        //Open the Order in ERP
        Grid.driver().get(ConfigUtil.getSecretValue("omsUrl") + "order-list/" + ccpOrderNumber);

        //Total Amount validation
        softAssert.assertEquals(parseAmount(safeGetText(ERP_TOTAL_AMOUNT)), requestTotalAmount,
                "Failing in Total Amount in ERP");

        //Qauntity validation
        softAssert.assertEquals(parseQuantity(safeGetText(ERP_ORDER_QUANTITY)), requestQuantity,
                "Failing in Order Quantity in ERP");

       //cashback Validation
        softAssert.assertEquals(parseAmount(safeGetText(ERP_CASHBACK)), requestedCashBack,
                "Failing in CashBack in ERP");

        //Nodel and Non nodel Validation
        String erpOrderPaymentType = safeGetText(ERP_ORDER_PAYMENT_TYPE);
        softAssert.assertTrue(!isBlank(erpOrderPaymentType)
                        && requestNodelorNonNodel != null
                        && requestNodelorNonNodel.contains(erpOrderPaymentType.trim()),
                "Failing in Order Payment Type in ERP - expected '" + requestNodelorNonNodel
                        + "' to contain '" + erpOrderPaymentType + "'");

        //Other validation
        softAssert.assertEquals(safeGetText(ERP_ORDER_Number), ccpOrderNumber, "Failing Order Number in ERP");
        softAssert.assertEquals(safeGetText(ERP_PAYMENTYPE), requestPaymentType, "Failing Payment Type in ERP");
        softAssert.assertEquals(safeGetText(ERP_ORDER_CATEGORY),
                requestOrderType == null ? null : requestOrderType.toUpperCase(), " Failing in Order Type in ERP");
        softAssert.assertFalse(isBlank(safeGetText(ERP_MMID)), "Failing in MMID in ERP");
        softAssert.assertFalse(isBlank(safeGetText(ERP_SKU_DETAILS)), "Failing in SKU Details in ERP");
        softAssert.assertEquals(safeGetText(ERP_DELIVERY_TYPE), requestDeliveryType, "Failing in Delivery Type in ERP");
        Grid.driver().get(ConfigUtil.getSecretValue("ccpPortalUrl"));
    }
    @And("open the ERP and verify all the SFSlo order data")
    public void open_the_erp_and_verify_all_the_SfSlo_order_data(){
        //Open the ERP
        Grid.driver().get(Token.getErpLoginUrl());
        Grid.driver().manage().window().maximize();
        sleep(5000);

        //Open the Order in ERP
        ccpOrderNumber = ccpOrderNumber;
        Grid.driver().get(ConfigUtil.getSecretValue("omsUrl") + "order-list/" + ccpOrderNumber);

        //Total Amount validation
        String erpTotalAmount = waitAndGetText(ERP_TOTAL_AMOUNT);
        String cleanUpErpTotalAmount = erpTotalAmount.replaceAll("[^\\d.]", "");
//        softAssert.assertEquals(Double.parseDouble(cleanUpErpTotalAmount), requestTotalAmount, "Failing in Total Amount in ERP");

        //Qauntity validation
        String erpOrderQuantity = waitAndGetText(ERP_ORDER_QUANTITY);
        String cleanUpErpOrderQuantity = erpOrderQuantity.split(" ")[0];
        softAssert.assertEquals(Double.parseDouble(cleanUpErpOrderQuantity), requestQuantity, "Failing in Order Quantity in ERP");

        //cashback Validation
        String erpCashBack = waitAndGetText(ERP_CASHBACK);
        String cleanUpErpCashBack = erpCashBack.replaceAll("[^\\d.]", "");
        softAssert.assertEquals(Double.parseDouble(cleanUpErpCashBack), requestedCashBack, "Failing in CashBack in ERP");
        //Nodel and Non nodel Validation
        softAssert.assertTrue(requestNodelorNonNodel.contains(waitAndGetText(ERP_ORDER_PAYMENT_TYPE)), "Failing in Order Payment Type in ERP");

        //Other validation
        softAssert.assertEquals(waitAndGetText(ERP_ORDER_Number), ccpOrderNumber, "Failing Order Number in ERP");
        softAssert.assertEquals(waitAndGetText(ERP_PAYMENTYPE), requestPaymentType, "Failing Payment Type in ERP");
        softAssert.assertEquals(waitAndGetText(ERP_ORDER_CATEGORY), requestOrderType.toUpperCase(), " Failing in Order Type in ERP");
        softAssert.assertNotNull(waitAndGetText(ERP_MMID), "Failing in MMID in ERP");
        softAssert.assertNotNull(waitAndGetText(ERP_SKU_DETAILS), "Failing in SKU Details in ERP");
        softAssert.assertEquals(waitAndGetText(ERP_DELIVERY_TYPE), requestDeliveryType, "Failing in Delivery Type in ERP");
    }
}
