
Feature: Check Add-To-Cart

  Background:
    When user open the portal and login with valid credentials "testautomation01@yopmail.com" "Jsw@2025"

     @addToCart @checkout @sanity
  Scenario: check PDP validation and Add-to-cart
      When user open HR coil pdp
      And select all the attribute and add to cart
      Then check user able to add the product to cart page
      And user will add cement product to cart
      And user will add Stainless Steel product to cart
      Then check user able to delete line item from cart
      And check user able to add the product to cart without pricing
      And user checkout the RCV2

  @editCart @sanity
  Scenario: check edit line item from cart page
      When user open HR coil pdp
      And select all the attribute and add to cart
      Then check user able to add the product to cart page

    @deleteLineItem @sanity
  Scenario: check delete line item in cart page
    When user open HR coil pdp
    And select all the attribute and add to cart
    Then check user able to add the product to cart page
    And user will add cement product to cart
    And user will add Stainless Steel product to cart
    Then check user able to delete line item from cart
