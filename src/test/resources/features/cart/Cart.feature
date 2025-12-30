@regression
Feature: Check Add-To-Cart

  Background:
    When user open the portal and login with valid credentials "testautomation01@yopmail.com" "Jsw@2025"

     @addToCart @checkout
  Scenario: check PDP validation and Add-to-cart
      When user open HR coil pdp
      And select all the attribute and add to cart
      Then check user able to add the product to cart page
      And user will add cement product to cart
#      And user will add Stainless Steel product to cart
      Then check user able to delete line item from cart
      And check user able to add the product to cart without pricing
      And user checkout the RCV2

  @editCart
  Scenario: check edit line item from cart page
      When user open HR coil pdp
      And select all the attribute and add to cart
      Then check user able to add the product to cart page

  @addToCart
  Scenario: check HR coil Add-to-cart
    When user open HR coil pdp
    And select all the attribute and add to cart
    Then check user able to add the product to cart page
    Then check user able to delete line item from cart

    @addToCart
  Scenario: check cement product add to cart and delete line item
    And user will add cement product to cart
    Then check user able to delete line item from cart

      @addToCart
  Scenario: Check MILD steel product add to cart and delete line item
    And check user able to add the product to cart without pricing
    Then check user able to delete line item from cart
