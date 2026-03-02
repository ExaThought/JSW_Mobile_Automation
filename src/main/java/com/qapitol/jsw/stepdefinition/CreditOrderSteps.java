package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.pageFactory.CategoryPages;
import com.qapitol.jsw.utils.CustomUtil;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CreditOrderSteps extends AbstractWebPage {

    private static final String MY_ORDERS_TAB = "myOrdersTab";
    private static final String DELIVERED_ORDER_TAB = "deliveredOrderTab";
    private static final String VIEW_DETAILS_BUTTON = "viewDetailsButton";
    private static final String VIEW_ORDER_DOCUMENTS_BUTTON = "viewOrderDocumentsButton";

    SessionSteps sessionSteps=new SessionSteps();
    @And("user clicks on My orders button")
    public void clicks_on_my_orders_tab() {

        waitAndCheckIsElementVisible(MY_ORDERS_TAB);
        waitAndClickOn(MY_ORDERS_TAB);
    }
    @And("user navigate to Delivered orders page")
    public void user_navigates_to_delivered_orders_page() {

        waitAndCheckIsElementVisible(DELIVERED_ORDER_TAB);
        waitAndClickOn(DELIVERED_ORDER_TAB);

    }


    @And("user clicks on View details button")
    public void user_clicks_on_view_details_button() {

        waitAndCheckIsElementVisible(VIEW_DETAILS_BUTTON);
        waitAndClickOn(VIEW_DETAILS_BUTTON);
    }

    @Then("verify order summary page is displayed")
    public void verify_order_summary_page_is_displayed() {

        waitAndCheckIsElementVisible("orderSummaryText");

        String actualText = waitAndGetText("orderSummaryText");

        Assert.assertEquals(actualText.trim(), "Order summary",
                "Order summary text is not present");

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
    }



    @And("user clicks on View order documents button")
    public void user_clicks_on_view_order_documents_button() {
        sleep(3000);
        WebElement locater= Grid.driver().findElement(CategoryPages.VIEW_ORDER_DOCUMENTS_BTN);
        //confirm order CTA
        CustomUtil.scrollingToViewAndClickUsingJS(locater);
        waitAndCheckIsElementVisible(VIEW_ORDER_DOCUMENTS_BUTTON);



        waitAndClickOn(VIEW_ORDER_DOCUMENTS_BUTTON);
    }


}
