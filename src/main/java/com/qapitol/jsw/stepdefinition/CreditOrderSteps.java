package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.pageFactory.CategoryPages;
import com.qapitol.jsw.utils.CustomUtil;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.time.Duration;

import static com.qapitol.jsw.pageFactory.CategoryPages.VIEW_ORDER_DOCUMENTS_BTN;
import static com.qapitol.jsw.utils.CustomUtil.*;

public class CreditOrderSteps extends AbstractWebPage {

    private static final String MY_ORDERS_TAB = "myOrdersTab";
    private static final String DELIVERED_ORDER_TAB = "deliveredOrderTab";
    private static final By VIEW_DETAILS_BUTTON = By.xpath("(//span[text()='Order number:']/../following-sibling::a/div)[1]");
    private static final String VIEW_ORDER_DOCUMENTS_BUTTON = "viewOrderDocumentsButton";
    private static final String VIEW_ORDER_DOCUMENT="//a[@title=\"View order documents\"]";


    SessionSteps sessionSteps=new SessionSteps();
    @And("user clicks on My orders button")
    public void clicks_on_my_orders_tab() {
        log.info("Landing in home page");
        refreshAndWaitForPageLoad();
        waitAndCheckIsElementVisible(MY_ORDERS_TAB);
        waitAndClickOn(MY_ORDERS_TAB);
        log.info("My order tab ic clicked");
    }
    @And("user navigate to Delivered orders page")
    public void user_navigates_to_delivered_orders_page() {

        waitAndCheckIsElementVisible(DELIVERED_ORDER_TAB);
        waitAndClickOn(DELIVERED_ORDER_TAB);
        log.info("Delivery order tab is clicked");

    }


    @And("user clicks on View details button")
    public void user_clicks_on_view_details_button() {



        sleep(2000);
        moveToAction(VIEW_DETAILS_BUTTON);
        //clickButton(VIEW_DETAILS_BUTTON);
        log.info("view details button is clicked");
    }

    @Then("verify order summary page is displayed")
    public void verify_order_summary_page_is_displayed() {


        new WebDriverWait(Grid.driver(), Duration.ofMillis(1000)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VIEW_ORDER_DOCUMENT)));
        WebElement viewOrderDoc = Grid.driver().findElement(By.xpath(VIEW_ORDER_DOCUMENT));
        // String actualText = viewOrderDoc.getText();

        Assert.assertTrue(viewOrderDoc.isDisplayed());
        log.info("Order summary page");

    }
    @Then("verify order delivered details are displayed")
    public void verify_order_delivered_details_are_displayed() {

        // Verify main delivery message
        waitAndCheckIsElementVisible("orderDeliveredText");
        Assert.assertEquals(waitAndGetText("orderDeliveredText").trim(),
                "Your order is delivered",
                "Order delivered message mismatch");

        // Verify Order details header
        waitAndCheckIsElementVisible("orderDetailsHeader");
        Assert.assertEquals(waitAndGetText("orderDetailsHeader").trim(),
                "Order details",
                "Order details header mismatch");

        // Verify Order number is present
        waitAndCheckIsElementVisible("orderNumberValue");
        String orderNumber = waitAndGetText("orderNumberValue").trim();
        Assert.assertFalse(orderNumber.isEmpty(),
                "Order number is empty");

        // Verify Order status = Delivered
        waitAndCheckIsElementVisible("orderStatusValue");
        String orderStatus = waitAndGetText("orderStatusValue").trim();
        Assert.assertEquals(orderStatus,
                "Delivered",
                "Order status is not Delivered");

        log.info("Verify Order status,number,header,delivered, summary page");
    }



    @And("user clicks on View order documents button")
    public void user_clicks_on_view_order_documents_button() {
        sleep(3000);
        new WebDriverWait(Grid.driver(), Duration.ofMillis(1000)).until(ExpectedConditions.visibilityOfElementLocated(VIEW_ORDER_DOCUMENTS_BTN));
        WebElement locater= Grid.driver().findElement(VIEW_ORDER_DOCUMENTS_BTN);

        moveToAction(VIEW_ORDER_DOCUMENTS_BTN);
        sleep(3000);
        jsClick(VIEW_ORDER_DOCUMENTS_BTN);
        log.info("View order document button");
    }


}
