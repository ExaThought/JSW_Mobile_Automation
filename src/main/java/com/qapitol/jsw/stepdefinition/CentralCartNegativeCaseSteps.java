package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.apitest.apis.impl.CentralCartNegativeImpl;
import com.qapitol.jsw.apitest.pojos.order.centralCartAddToCartResponse.AddToCartRes;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

import static com.qapitol.jsw.utils.CustomUtills.softAssert;

public class CentralCartNegativeCaseSteps {

    AddToCartRes cartResponseCode;
    CentralCartNegativeImpl cartRequest = new CentralCartNegativeImpl();

    @When("Check {string} with {string} combination")
    public void check_Cart_Identifier(String cartType, String cartJourneyType) throws IOException {
        cartResponseCode = cartRequest.getCartTypeAndCartJourneyTypeResponse(cartType, cartJourneyType);
        softAssert.assertEquals(cartResponseCode.getError().getCode(), 1000, cartType + " And " + cartJourneyType + " combination is working please check");
    }

    @And("Check Wrong CartType {string} with correct CartJourneyType {string}")
    public void check_Wrong_CartType_With_Correct_CartJourneyType(String cartType, String cartJourneyType) throws IOException {
        cartResponseCode = cartRequest.getCartTypeAndCartJourneyTypeResponse(cartType, cartJourneyType);
        softAssert.assertEquals(cartResponseCode.getError().getCode(), 1000, cartType + " And " + cartJourneyType + "Check Wrong CartType with correct CartJourneyType is failing ");
    }

    @And("Check correct CartType {string} with wrong CartJourneyType {string}")
    public void Check_correct_CartType_With_wrong_CartJourneyType(String cartType, String cartJourneyType) throws IOException {
        cartResponseCode = cartRequest.getCartTypeAndCartJourneyTypeResponse(cartType, cartJourneyType);
        softAssert.assertEquals(cartResponseCode.getError().getCode(), 1000, cartType + " And " + cartJourneyType + " Check correct CartType with correct CartJourneyType is failing ");
    }

    @Then("Check with wrong StoreFront {string}")
    public void check_with_wrong_StoreFront(String storeFront) throws IOException {
        cartResponseCode = cartRequest.getResponseForWrongStoreFront(storeFront);
        softAssert.assertEquals(cartResponseCode.getError().getCode(), 1000, "Wrong StoreFront is working please check");
    }

    @Then("Check without deliveryType and wrong delivery type {string}")
    public void check_without_deliveryType_and_wrong_deliveryType(String wrongDeliveryType) throws IOException {
        cartResponseCode=cartRequest.getDeliveryTypeResponse(wrongDeliveryType);
        softAssert.assertEquals(cartResponseCode.getError().getCode(),1000,"Without DeliveryType and Wrong Delivery Type is working please check");
        softAssert.assertAll();
    }
    @Then("check with Invalid ProductMMID {string}")
    public void check_With_Invalid_ProductMMID(String wrongProductMMID) throws IOException {
     cartResponseCode=cartRequest.getWrongProductMMIDResponse(wrongProductMMID);
     softAssert.assertEquals(cartResponseCode.getError().getCode(),400,"With Invalid ProductMMID is working please check");
     softAssert.assertAll();
    }
}

