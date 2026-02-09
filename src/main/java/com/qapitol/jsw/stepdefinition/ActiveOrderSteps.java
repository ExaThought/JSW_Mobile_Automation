package com.qapitol.jsw.stepdefinition;


import com.qapitol.jsw.apitest.apis.impl.CtSlcSlocreationImpl;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.io.IOException;
import java.time.Duration;


public class ActiveOrderSteps extends AbstractWebPage {


    public static final String CART_PAGE = "cartPage";
    public static final String CCP_ORDER_NUMBER = "ccpOrderNumber";
    public static final String CCP_CREATE_ORDER_CTA = "ccpCreateOrderCTA";
    public static final String CCP_MY_ORDER_PAGE="ccpMyOrderPage";
    public static final String ORDER_CONFIRMATION ="orderConfirmStatus";
    private final String CONTINUE_BUTTON = "continueToLoginButton";
    private static final String DELIVERED_ORDER_TAB = "deliveredOrderTab";
    private static final String CANCELLED_ORDER_TAB = "cancelledOrderTab";
    private static final String ALL_ORDER_TAB = "allOrderTab";



    SessionSteps sessionSteps=new SessionSteps();


    String ccpOrderNumber;

    @When("create cash and carry cart from SF for active order")
    public void create_cash_and_carry_cart_from_sf_ccp() throws IOException {
        //Create SLC
        CtSlcSlocreationImpl ctSlcSloCreation=new CtSlcSlocreationImpl();
        String orderType="CT_SLC_SLO_CASH_AND_CARRY";
        ctSlcSloCreation.getSlcSloResponse(orderType);
    }

    @And("open the cart page and create an order")
    public void open_the_cart_page_and_create_order_ccp(){
        //open cart page
        waitAndCheckIsElementVisible(CART_PAGE);
        waitAndClickOn(CART_PAGE);
        //confirm order CTA

        // Wait properly for Confirm Order button
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));

        WebElement confirmBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//button[contains(text(),'Confirm order')]")
                )
        );

        // Scroll above sticky navbar
        ((JavascriptExecutor) Grid.driver()).executeScript(
                "arguments[0].scrollIntoView(true); window.scrollBy(0,-200);",
                confirmBtn
        );

        try {
            confirmBtn.click();
        } catch (Exception e) {
            // JS fallback (avoids intercept)
            ((JavascriptExecutor) Grid.driver()).executeScript(
                    "arguments[0].click();",
                    confirmBtn
            );
        }

        sleep(3000);
        //Copy order number
        ccpOrderNumber = waitAndGetText(CCP_ORDER_NUMBER);
    }
    @And("user clicks on Active order tab")
    public void user_clicks_on_active_order_tab() {

        waitAndCheckIsElementVisible("activeOrderTab");
        waitAndClickOn("activeOrderTab");

    }

    @Then("verify payment details persent on page after navigation")
    public void verify_order_placed_text_is_present() {

        waitAndCheckIsElementVisible("orderPlacedText");

        String actualText = waitAndGetText("orderPlacedText");

        Assert.assertEquals(actualText.trim(), "Payment details",
                "Order placed text is not present");

    }

    @Then("verify created order number is present in active orders")
    public void verify_created_order_number_is_present_in_active_orders() {

        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(20));

        By orderNumberLocator = By.xpath(
                "//span[normalize-space()='Order number:']/following-sibling::span"
        );

        String activeOrderNumber = wait.until(driver -> {
            try {
                String text = driver.findElement(orderNumberLocator).getText().trim();
                return text.isEmpty() ? null : text;
            } catch (StaleElementReferenceException e) {
                return null; // retry automatically
            }
        });

        System.out.println("Created Order Number: " + ccpOrderNumber);
        System.out.println("Active Page Order Number: " + activeOrderNumber);

        Assert.assertEquals(activeOrderNumber, ccpOrderNumber,
                "Order number does not match in Active Orders page");
    }

    @When("user navigates to Delivered orders page")
    public void the_user_navigates_to_delivered_orders_page() {

        waitAndCheckIsElementVisible(DELIVERED_ORDER_TAB);
        waitAndClickOn(DELIVERED_ORDER_TAB);

    }

    @When("user navigates to Cancelled orders page")
    public void the_user_navigates_to_cancelled_orders_page() {

        waitAndCheckIsElementVisible(CANCELLED_ORDER_TAB);
        waitAndClickOn(CANCELLED_ORDER_TAB);

        System.out.println("Successfully navigated to Cancelled Orders page");
    }
    @When("user clicks on All orders tab")
    public void the_user_clicks_on_all_orders_tab() {

        waitAndCheckIsElementVisible(ALL_ORDER_TAB);
        waitAndClickOn(ALL_ORDER_TAB);

        System.out.println("Clicked on All Orders tab");
    }




}