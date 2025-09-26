package com.qapitol.jsw.stepdefinition;

import com.fasterxml.jackson.databind.JsonNode;
import com.qapitol.jsw.apitest.apis.impl.CartRCV2ApiImp;
import com.qapitol.jsw.apitest.pojos.order.appTesting.AppResponse;
import com.qapitol.jsw.apitest.pojos.order.rcv2CartSizeResponse.Rcv2CartSize;
import com.qapitol.jsw.apitest.pojos.order.rcv2Response.LineItem;
import com.qapitol.jsw.apitest.pojos.order.rcv2Response.Rcv2Response;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.qapitol.jsw.utils.CustomUtills.softAssert;

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
        firstRequestQuantity = addToCartResponse.getData().getLineItemList().get(0).getQuantity().getPrimaryUom().getValue();
    }

    @Then("response should be 200 and able to see all data in response")
    public void response_should_be_200_and_able_to_see_all_date_in_response() {
        softAssert.assertEquals(addToCartResponse.getStatus(), "OK");
    }

    @And("check the update when adding the same product with identical attributes to the cart again")
    public void check_the_update_when_adding_the_same_product_with_identical_attributes_to_the_cart_again() throws IOException {
        payload = "RCV2_ADD_TO_CART";
        addToCartResponse = cartRCV2ApiImp.getAddToCartResponse(payload);
    }

    @Then("check the quantity and line item")
    public void check_the_quantity_and_line_item() {
        Double updatedQuantity = firstRequestQuantity * 2;
        softAssert.assertEquals(updatedQuantity, addToCartResponse.getData().getLineItemList().get(0).getQuantity().getPrimaryUom().getValue());
        softAssert.assertEquals(addToCartResponse.getData().getLineItemList().size(), 1);
    }

    @And("check for sheets product and verify the primary and secondary uom and quantity")
    public void check_for_sheets_product_and_verify_the_primary_and_secondary_uom_and_quantity() throws IOException {
        payload="RCV2_ADD_TO_CART_SHEETS";
        addToCartResponse = cartRCV2ApiImp.getAddToCartResponse(payload);
        softAssert.assertAll();
    }
    @And("check for TMT product and verify the primary and secondary uom and quantity")
    public void check_for_tmt_product_and_verify_the_primary_and_secondary_uom_and_quantity() throws IOException {
        payload="RCV2_ADD_TO_CART_TMT";
        addToCartResponse = cartRCV2ApiImp.getAddToCartResponse(payload);
    }
    @And("check for Cement product and verify the primary and secondary uom and quantity")
    public void check_for_cement_product_and_verify_the_primary_and_secondary_uom_and_quantity() throws IOException {
        payload="RCV2_ADD_TO_CART_CEMENT";
        addToCartResponse = cartRCV2ApiImp.getAddToCartResponse(payload);
    }
    @And("check for StainlessSteel product and verify the primary and secondary uom and quantity")
    public void check_for_stainless_steel_product_and_verify_the_primary_and_secondary_uom_and_quantity() throws IOException {
        payload="RCV2_ADD_TO_CART_STAINLESS_STEEL";
        addToCartResponse = cartRCV2ApiImp.getAddToCartResponse(payload);
    }
    @When("I send an add-to-cart request with an invalid MMID")
    public void i_send_an_add_to_cart_request_with_an_invalid_mmid() throws IOException {
        payload = "RCV2_ADD_TO_CART";
        addToCartResponse = cartRCV2ApiImp.getAddToCartResponse(payload);
    }
    @Then("the system should reject the line item and not add it to the cart")
    public void the_system_should_reject_the_line_item_and_not_add_it_to_the_cart(){
       int addToCartResponseForInvalidMMMID=addToCartResponse.getError().getCode();
       softAssert.assertEquals(addToCartResponseForInvalidMMMID,400);
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
    }
    @And("delete one line item from cart")
    public void delete_one_line_item_from_cart() throws IOException {
        payload="RCV2_DELETE_LINE_ITEM";
        deleteLineItemResponse =cartRCV2ApiImp.getDeleteLineItemResponse(payload,ctLineItemId);
    }
    @Then("check how many line item present in get cart")
    public void check_how_many_line_item_present_in_get_cart(){
        int postDeleteLineItemCount= deleteLineItemResponse.getData().getLineItemList().size();
        softAssert.assertEquals(postDeleteLineItemCount,1);
        softAssert.assertAll();
    }
    @And("delete complete cart")
    public void delete_complete_cart(){
        deleteCartResponse=cartRCV2ApiImp.getDeleteCartResponse();
        softAssert.assertEquals(deleteCartResponse.getData(),"Cart Deleted Successfully");
    }
    @Then("check the get cart")
    public void check_the_get_cart(){
        getCartResponse=cartRCV2ApiImp.getCartResponse();
        softAssert.assertEquals(getCartResponse.getData(),null);

    }
    @And("update attribute and quantity for one line item")
    public void update_attribute_and_quantity_for_one_line_item() throws IOException {
        payload="RCV2_UPDATE_LINE_ITEM";
        updateLineItemResponse = cartRCV2ApiImp.getUpdateLineItemResponse(payload, addToCartResponse);
    }
    @Then("verify the both line item")
    public void verify_the_both_line_item(){
       double updatedLineItemResponse= updateLineItemResponse.getData().getLineItemList().get(0).getQuantity().getPrimaryUom().getValue();
       double expectedUpdateLineItem=60.0;
       softAssert.assertEquals(updatedLineItemResponse,expectedUpdateLineItem);
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
        softAssert.assertAll();
    }
    @When("request and get response for app Api")
    public void request_and_get_response_for_app_api() throws IOException {
        AppResponse response=   cartRCV2ApiImp.getAppApiResponse();
        System.out.println(response.getBusinessName());
    }

}
