@regression @cart @sanity
Feature: Check Add-To-Cart


  @addToCart @checkout @sanitynew
  Scenario: check PDP validation and Add-to-cart
    Given Go to session "ccp" "kl@hj.com" "Abinash@123"
    Then close the payment reminder pop-up
    When user open HR coil pdp
    And select all the attribute and add to cart
    Then check user able to add the product to cart page
    And user will add cement product to cart
    And user will add Stainless Steel product to cart
    Then check user able to delete line item from cart
    And check user able to add the product to cart without pricing
    And calculate the number of line items in the cart
    And user checkout the RCV2
    Then account logout

  @editCart
  Scenario: check edit line item from cart page
    Given Go to session "ccp" "testautomation01@yopmail.com" "Jsw@2025"
    When user open HR coil pdp
    And select all the attribute and add to cart
    Then check user able to add the product to cart page
    Then account logout

  @deleteLineItem
  Scenario: check delete line item in cart page
    Given Go to session "ccp" "testautomation01@yopmail.com" "Jsw@2025"
    When user open HR coil pdp
    And select all the attribute and add to cart
    Then check user able to add the product to cart page
    And user will add cement product to cart
    And user will add Stainless Steel product to cart
    Then check user able to delete line item from cart
    Then account logout

  Scenario: check adding cement product to cart
    Given Go to session "ccp" "testautomation01@yopmail.com" "Jsw@2025"
    And user will add cement product to cart

  Scenario: check adding Stainless Steel product to cart
    Given Go to session "ccp" "testautomation01@yopmail.com" "Jsw@2025"
    And user will add Stainless Steel product to cart

