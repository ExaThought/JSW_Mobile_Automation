package com.qapitol.jsw.stepdefinition;

import com.fasterxml.jackson.databind.JsonNode;
import com.qapitol.jsw.apitest.apis.impl.CartRCV2ApiImp;
import com.qapitol.jsw.apitest.pojos.order.appTesting.AppResponse;
import com.qapitol.jsw.apitest.pojos.order.rcv2CartSizeResponse.Rcv2CartSize;
import com.qapitol.jsw.apitest.pojos.order.rcv2Response.LineItem;
import com.qapitol.jsw.apitest.pojos.order.rcv2Response.PrimaryUom;
import com.qapitol.jsw.apitest.pojos.order.rcv2Response.Rcv2Response;
import com.qapitol.jsw.utils.AllureCustomLogReporter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import static com.qapitol.jsw.utils.Hooks.softAssert;
import org.testng.Assert;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import static com.qapitol.sauron.web.WebActionListener.log;

public class CartApiSteps {

    private Double firstRequestQuantity;
    private String payload;
    private  List<String>ctLineItemId;
    


    private Rcv2Response addToCartResponse;
    private Rcv2Response getCartResponse;
    private Rcv2Response deleteLineItemResponse;
    private Rcv2Response deleteCartResponse;
    private Rcv2Response updateLineItemResponse;
    private Rcv2CartSize rcv2CartSizeResponse;
    
    CartRCV2ApiImp cartRCV2ApiImp = new CartRCV2ApiImp();

    @When("we request add to cart Api for primary Quantity MT")
    public void we_request_addToCart_Api_for_primary_quantity_mt() throws IOException {
        payload = "RCV2_ADD_TO_CART";
        addToCartResponse = cartRCV2ApiImp.getAddToCartResponse(payload);
        if(Objects.equals(addToCartResponse.getStatus(), "OK"))
        {
            log.info("Item added in the cart successfully");
            AllureCustomLogReporter.addTestStepLog("Item added in the cart successfully");
        }
        firstRequestQuantity = addToCartResponse.getData().getLineItemList().get(0).getQuantity().getPrimaryUom().getValue();
    }

    @Then("response should be 200 and able to see all data in response")
    public void response_should_be_200_and_able_to_see_all_date_in_response() {
        softAssert.assertEquals(addToCartResponse.getStatus(), "OK");
        log.info("Add to cart API response is:"+" "+addToCartResponse.getStatus());
        AllureCustomLogReporter.addTestStepLog("Add to cart API response is:"+" "+addToCartResponse.getStatus());
    }

    @And("check the update when adding the same product with identical attributes to the cart again")
    public void check_the_update_when_adding_the_same_product_with_identical_attributes_to_the_cart_again() throws IOException {
        payload = "RCV2_ADD_TO_CART";
        addToCartResponse = cartRCV2ApiImp.getAddToCartResponse(payload);
        log.info("Add to cart API response is:"+" "+addToCartResponse.getStatus());
        log.info("Item added in the cart successfully with item name:"+" "+ addToCartResponse.getData().getLineItemList().get(0).getSku());
        AllureCustomLogReporter.addTestStepLog("Item added in the cart successfully with item name:"+" "+ addToCartResponse.getData().getLineItemList().get(0).getSku());
        log.info("Same product added successfully");
    }

    @Then("check the quantity and line item")
    public void check_the_quantity_and_line_item() {
        Double updatedQuantity = firstRequestQuantity * 2;
        softAssert.assertEquals(updatedQuantity, addToCartResponse.getData().getLineItemList().get(0).getQuantity().getPrimaryUom().getValue());
        softAssert.assertEquals(addToCartResponse.getData().getLineItemList().size(), 1);
        log.info("Line item quantity is :"+" "+addToCartResponse.getData().getLineItemList().get(0).getQuantity().getPrimaryUom().getValue());
        AllureCustomLogReporter.addTestStepLog("Line item quantity is :"+" "+addToCartResponse.getData().getLineItemList().get(0).getQuantity().getPrimaryUom().getValue());
        log.info("No of line item in the cart is:"+" "+addToCartResponse.getData().getLineItemList().size());
        AllureCustomLogReporter.addTestStepLog("Line item quantity is :"+" "+addToCartResponse.getData().getLineItemList().size());

    }

    @And("check for sheets product and verify the primary and secondary uom and quantity")
    public void check_for_sheets_product_and_verify_the_primary_and_secondary_uom_and_quantity() throws IOException {
        payload="RCV2_ADD_TO_CART_SHEETS";
        addToCartResponse = cartRCV2ApiImp.getAddToCartResponse(payload);
        log.info("Item added in the cart successfully with item name:"+" "+ addToCartResponse.getData().getLineItemList().get(0).getSku());
        AllureCustomLogReporter.addTestStepLog("Item added in the cart successfully with item name:"+" "+ addToCartResponse.getData().getLineItemList().get(0).getSku());
        Assert.assertEquals(addToCartResponse.getData().getLineItemList().get(0).getQuantity().getPrimaryUom().getUnit(),"MT");
        log.info("Primary UOM is:"+" "+ addToCartResponse.getData().getLineItemList().get(0).getQuantity().getPrimaryUom().getUnit());
        AllureCustomLogReporter.addTestStepLog("Primary UOM is:"+" "+ addToCartResponse.getData().getLineItemList().get(0).getQuantity().getPrimaryUom().getUnit());
    }
    @And("check for TMT product and verify the primary and secondary uom and quantity")
    public void check_for_tmt_product_and_verify_the_primary_and_secondary_uom_and_quantity() throws IOException {
        payload="RCV2_ADD_TO_CART_TMT";
        addToCartResponse = cartRCV2ApiImp.getAddToCartResponse(payload);
        log.info("Item added in the cart successfully with item name:"+" "+ addToCartResponse.getData().getLineItemList().get(0).getSku());
        AllureCustomLogReporter.addTestStepLog("Item added in the cart successfully with item name:"+" "+ addToCartResponse.getData().getLineItemList().get(0).getSku());
        Assert.assertEquals(addToCartResponse.getData().getLineItemList().get(0).getQuantity().getPrimaryUom().getUnit(),"MT");
        log.info("Primary UOM is:"+" "+ addToCartResponse.getData().getLineItemList().get(0).getQuantity().getPrimaryUom().getUnit());
        AllureCustomLogReporter.addTestStepLog("Primary UOM is:"+" "+ addToCartResponse.getData().getLineItemList().get(0).getQuantity().getPrimaryUom().getUnit());
    }
    @And("check for Cement product and verify the primary and secondary uom and quantity")
    public void check_for_cement_product_and_verify_the_primary_and_secondary_uom_and_quantity() throws IOException {
        payload="RCV2_ADD_TO_CART_CEMENT";
        addToCartResponse = cartRCV2ApiImp.getAddToCartResponse(payload);
        log.info("Item added in the cart successfully with item name:"+" "+ addToCartResponse.getData().getLineItemList().get(2).getSku());
        AllureCustomLogReporter.addTestStepLog("Item added in the cart successfully with item name:"+" "+ addToCartResponse.getData().getLineItemList().get(2).getSku());
        Assert.assertEquals(addToCartResponse.getData().getLineItemList().get(2).getQuantity().getPrimaryUom().getLabel(),"bags");
        log.info("Primary UOM is:"+" "+ addToCartResponse.getData().getLineItemList().get(2).getQuantity().getPrimaryUom().getLabel());
        AllureCustomLogReporter.addTestStepLog("Primary UOM is:"+" "+ addToCartResponse.getData().getLineItemList().get(2).getQuantity().getPrimaryUom().getLabel());
    }
    @And("check for StainlessSteel product and verify the primary and secondary uom and quantity")
    public void check_for_stainless_steel_product_and_verify_the_primary_and_secondary_uom_and_quantity() throws IOException {
        payload="RCV2_ADD_TO_CART_STAINLESS_STEEL";
        addToCartResponse = cartRCV2ApiImp.getAddToCartResponse(payload);
        log.info("Item added in the cart successfully with item name:"+" "+ addToCartResponse.getData().getLineItemList().get(0).getSku());
        AllureCustomLogReporter.addTestStepLog("Item added in the cart successfully with item name:"+" "+ addToCartResponse.getData().getLineItemList().get(0).getSku());
        softAssert.assertEquals(addToCartResponse.getData().getLineItemList().get(0).getQuantity().getPrimaryUom().getUnit(),"KG");
        log.info("Primary UOM is:"+" "+ addToCartResponse.getData().getLineItemList().get(0).getQuantity().getPrimaryUom().getUnit());
        AllureCustomLogReporter.addTestStepLog("Primary UOM is:"+" "+ addToCartResponse.getData().getLineItemList().get(0).getQuantity().getPrimaryUom().getUnit());
    }
    @When("I send an add-to-cart request with an invalid quantity value")
    public void i_send_an_add_to_cart_request_with_an_invalid_quantity_value() throws IOException {
        payload = "RCV2_ADD_TO_CART";
        addToCartResponse = cartRCV2ApiImp.getAddToCartResponse(payload);
    }
    @Then("the system should reject the line item and not add it to the cart")
    public void the_system_should_reject_the_line_item_and_not_add_it_to_the_cart(){
       int addToCartResponseForInvalidMMMID=addToCartResponse.getError().getCode();
       Assert.assertEquals(addToCartResponseForInvalidMMMID,400,"Expected HTTP status code 400");
        String errorMessage = addToCartResponse.getError().getMessages().toString();
        Assert.assertTrue(errorMessage.contains("Quantity 0 is not allowed"), "Expected error message to mention invalid quantity: " + errorMessage);
        log.info("Error message is" +" "+errorMessage);
        AllureCustomLogReporter.addTestStepLog("Error message is" +" "+errorMessage.contains("Quantity 0 is not allowed"));
    }

    @When("we add multi line item to cart")
    public void we_add_multi_line_item_to_cart() throws IOException {
        payload = "RCV2_ADD_TO_CART";
        cartRCV2ApiImp.getAddToCartResponse(payload);
        payload="RCV2_ADD_TO_CART_SHEETS";
        addToCartResponse = cartRCV2ApiImp.getAddToCartResponse(payload);
        List<LineItem>lineItemId= addToCartResponse.getData().getLineItemList();
        ctLineItemId= new ArrayList<>();
        for(LineItem a:lineItemId){
            ctLineItemId.add(a.getLineItemIdentifier().getCtLineItemId());
        }
        log.info("Multiple item added in to cart successfully:"+" "+addToCartResponse.getData().getLineItemList().get(0).getName()+addToCartResponse.getData().getLineItemList().get(1).getName());
        AllureCustomLogReporter.addTestStepLog("Multiple item added in to cart successfully:"+" "+addToCartResponse.getData().getLineItemList().get(0).getName()+addToCartResponse.getData().getLineItemList().get(1).getName());
    }
    @And("delete one line item from cart")
    public void delete_one_line_item_from_cart() throws IOException {
        payload="RCV2_DELETE_LINE_ITEM";
        deleteLineItemResponse =cartRCV2ApiImp.getDeleteLineItemResponse(payload,ctLineItemId);
        ctLineItemId.remove(0);
        Assert.assertEquals(deleteLineItemResponse.getStatus(),"OK","Unable to delete cart");
        log.info("Item deleted successfully");
    }
    @Then("check how many line item present in get cart")
    public void check_how_many_line_item_present_in_get_cart(){
        int postDeleteLineItemCount= deleteLineItemResponse.getData().getLineItemList().size();
        Assert.assertEquals(postDeleteLineItemCount,1,"Item count didn't match");
        log.info("Size of the cart after deletion is:"+" "+deleteLineItemResponse.getData().getLineItemList().size());
    }
    @And("delete complete cart")
    public void delete_complete_cart() throws IOException {
//        deleteCartResponse=cartRCV2ApiImp.getDeleteCartResponse();
//        softAssert.assertEquals(deleteCartResponse.getData(),"Cart Deleted Successfully");
//        log.info("Cart completely deleted successfully");
//        payload="RCV2_DELETE_LINE_ITEM_2";
        deleteLineItemResponse =cartRCV2ApiImp.getDeleteLineItemResponse(payload,ctLineItemId);
        Assert.assertEquals(deleteLineItemResponse.getStatus(),"OK","Unable to delete cart");
        log.info("Cart completely deleted successfully");
    }
    @Then("check the get cart")
    public void check_the_get_cart(){
        getCartResponse=cartRCV2ApiImp.getCartResponse();
        softAssert.assertEquals(getCartResponse.getData(),null,"Cart didn't match");
        log.info("Cart validation completed");
        AllureCustomLogReporter.addTestStepLog("Cart validation completed");
    }
    @And("update attribute and quantity for one line item")
    public void update_attribute_and_quantity_for_one_line_item() throws IOException {
        payload="RCV2_UPDATE_LINE_ITEM";
        updateLineItemResponse = cartRCV2ApiImp.getUpdateLineItemResponse(payload, addToCartResponse);
        Assert.assertEquals(updateLineItemResponse.getStatus(),"OK","Attribute updation failed");
        log.info("Attribute updated successfully");
        AllureCustomLogReporter.addTestStepLog("Attribute updation failed" +" "+updateLineItemResponse.getStatus());
    }
    @Then("verify the both line item")
    public void verify_the_both_line_item(){
       double updatedLineItemResponse= updateLineItemResponse.getData().getLineItemList().get(0).getQuantity().getPrimaryUom().getValue();
       double expectedUpdateLineItem=60.0;
       softAssert.assertEquals(updatedLineItemResponse,expectedUpdateLineItem);
       log.info("Line items are :"+" "+updatedLineItemResponse);
       log.info("Line items are :"+" "+expectedUpdateLineItem);
       AllureCustomLogReporter.addTestStepLog("Line item details are:"+" "+expectedUpdateLineItem+" "+updatedLineItemResponse);


    }
    @Then("check cart size Api")
    public void check_cart_size_api(){
        rcv2CartSizeResponse=cartRCV2ApiImp.getCartSizeResponse();
        int actualCartSize=rcv2CartSizeResponse.getData().getEnquiryCart().getLineItemCount();
        int expectedCartSize=ctLineItemId.size();
        softAssert.assertEquals(actualCartSize,expectedCartSize);
    }
    @Then("verify get cart Api")
    public void verify_get_cart_api(){

        getCartResponse=cartRCV2ApiImp.getCartResponse();
        ObjectMapper mapper = new ObjectMapper();
        // Convert responses to JsonNode
        JsonNode getCartNode = mapper.valueToTree(getCartResponse);
        JsonNode addToCartNode = mapper.valueToTree(addToCartResponse);

        // verify line Item
        JsonNode getCartLineItems = getCartNode.path("data").path("lineItemList");
        JsonNode addToCartLineItems = addToCartNode.path("data").path("lineItemList");
        softAssert.assertEquals(getCartLineItems,addToCartLineItems);

        //Verify cartID
        String getCartId=getCartNode.path("data").path("identifier").get("id").asText();
        String addToCartId=addToCartNode.path("data").path("identifier").get("id").asText();
        softAssert.assertEquals(getCartId,addToCartId);

    }
    @When("request and get response for app Api")
    public void request_and_get_response_for_app_api() throws IOException {
        AppResponse response=   cartRCV2ApiImp.getAppApiResponse();
        System.out.println(response.getBusinessName());
    }

    private void verifyProductUOM(String keyword, String expectedPrimaryLabel, String expectedSecondaryLabel) {
        List<LineItem> lineItems = addToCartResponse.getData().getLineItemList();

        boolean found = false;
        for (LineItem item : lineItems) {
            String sku = item.getSku();
            if (sku.contains(keyword)) {

                PrimaryUom primaryUom = item.getQuantity().getPrimaryUom();
                PrimaryUom secondaryUom = (PrimaryUom) item.getQuantity().getSecondaryUom();

                String primaryLabel = primaryUom.getLabel();
                String secondaryLabel = (secondaryUom != null) ? secondaryUom.getLabel() : "null";

                log.info("Validating SKU:"+" " + sku);
                Assert.assertEquals(primaryLabel, expectedPrimaryLabel, "Primary UOM mismatch for " + sku);
                Assert.assertEquals(secondaryLabel, expectedSecondaryLabel, "Secondary UOM mismatch for " + sku);

                log.info("✅ " + keyword + " UOMs validated successfully.");
                return;
            }
        }

        if (!found) {
            Assert.fail("No SKU found containing keyword: " + keyword);
        }
    }



}