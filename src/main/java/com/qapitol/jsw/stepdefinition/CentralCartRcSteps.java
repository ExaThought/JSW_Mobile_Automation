package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.apitest.apis.impl.CentralCartRcImpl;
import com.qapitol.jsw.apitest.apis.impl.CombinationTest;
import com.qapitol.jsw.apitest.pojos.order.centralCartAddToCartResponse.AddToCartRes;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

import static com.qapitol.jsw.utils.Hooks.softAssert;

public class CentralCartRcSteps {
    AddToCartRes centralCartRcResponse;
    CombinationTest combinationTest = new CombinationTest();
    CentralCartRcImpl centralCartRc = new CentralCartRcImpl(); //creating Object

    @When("Request and get response for Central Cart Api for MSME from WEBSITE")
    public void request_And_Get_Response_For_Central_Cart_Api_For_MSME_From_Website() throws IOException {
        centralCartRcResponse = centralCartRc.getCentralCartRcAddToCartResponse();// Getting the response and checking the response
        centralCartRc.nonNullValidationForResponse();//NonNull validation for response
        centralCartRc.validateRCApi();// RCV2 validation
    }

    @And("Check Get Cart Size API for Rc")
    public void check_Get_Cart_Size_Api_For_Rc() throws IOException {
        centralCartRc.getCartSize();// Check the Cart Size
    }

    @And("Check Fetch Cart API for Rc")
    public void check_Fetch_Cart_Api_For_Rc() throws IOException {
        centralCartRc.fetchRcCart();// Fetch the cart
    }

    @And("Check Update Line Item Quantity Api for Rc")
    public void check_Update_Line_Item_Quantity_Api_For_Rc() throws IOException {
        centralCartRc.updateLineItemQuantity();//Update Quantity
    }

    @And("Check Delete Line Item APi for Rc")
    public void check_Delete_Line_Item_Api_For_Rc() throws IOException {
        centralCartRc.deleteLineItem();//Delete Line Item Api
    }

    @And("Check Delete CartId Api for Rc")
    public void check_Delete_CartId_Api_For_Rc() throws IOException {
        centralCartRc.deleteCartID();//Delete the cart
    }

    @Then("Check MongoBD Cart Status After Cart Delete for Rc")
    public void check_MongoDB_Cart_Status_After_Cart_Delete_For_Rc() {
        centralCartRc.checkMongDoDB();// Verify cart status in MongoDB
    }

    @When("Request and get response for Central Cart Api for MSME from MOBILE")
    public void request_And_Get_Response_For_Central_Cart_Api_For_MSME_From_Mobile() throws IOException {
        centralCartRcResponse = centralCartRc.getCentralCartRcAddToCartResponse();// Getting the response and checking the response
        centralCartRc.nonNullValidationForResponse();//NonNull validation for response
        centralCartRc.validateRCApi();// RCV2 validation
    }

    @When("Request and get response for Central Cart Api for CONSTRUCT from WEBSITE")
    public void request_And_Get_Response_For_Central_Cart_Api_For_Construct_From_Website() throws IOException {
        centralCartRcResponse = centralCartRc.getCentralCartRcAddToCartResponse();// Getting the response and checking the response
        centralCartRc.nonNullValidationForResponse();//NonNull validation for response
        centralCartRc.validateRCApi();// RCV2 validation
    }

    @When("Request and get response for Central Cart Api for CONSTRUCT from MOBILE")
    public void request_And_Get_Response_For_Central_Cart_Api_For_Construct_From_Mobile() throws IOException {
        centralCartRcResponse = centralCartRc.getCentralCartRcAddToCartResponse();// Getting the response and checking the response
        centralCartRc.nonNullValidationForResponse();//NonNull validation for response
        centralCartRc.validateRCApi();// RCV2 validation
    }

    @Given("Request Central Cart API and check delivery type in response")
    public void request_Central_Cart_Api_And_Check_Delivery_Type_In_Response() throws IOException {
        centralCartRcResponse = centralCartRc.getCentralCartRcAddToCartResponse();// Getting the response and checking the response
        String responseDeliveryType = centralCartRcResponse.getData().getShippingDetails().getDeliveryType();
        Assert.assertEquals(responseDeliveryType, "SELF_PICKUP");
        centralCartRc.deleteCartID();
    }

    @Given("Request and Get Api Response in {string}")
    public void request_And_Get_Api_Response_In_Website(String deviceType) throws IOException {
        combinationTest.getAddToCartResponse(deviceType);
    }
    @And("Update line item quantity in {string}")
    public void update_line_item_quantity_in_Mobile(String DeviceType) throws IOException {
      combinationTest.getUpdateTheLineItemResponse(DeviceType);
    }
    @Then("Delete line item and delete cart in {string}")
    public void delete_line_item_and_delete_cart_in_Website(String deviceType) throws IOException {
       combinationTest.getDeleteCartResponse(deviceType);
       softAssert.assertAll();
    }
}
