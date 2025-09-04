@regression @cart
Feature: Check Add-To-Cart


  @addToCart @checkout @sanity
  Scenario: check PDP validation and Add-to-cart
    When check login page
    Given Go to session "ccp" "testautomation01@yopmail.com" "Jsw@2025"
    When user open HR coil pdp
    And select all the attribute and add to cart
    Then check user able to add the product to cart page
#    And user will add cement product to cart
    And user will add Stainless Steel product to cart
    Then check user able to delete line item from cart
    And check user able to add the product to cart without pricing
    And user checkout the RCV2
    Then account logout

  @editCart @sanity
  Scenario: check edit line item from cart page
    When check login page
    Given Go to session "ccp" "testautomation01@yopmail.com" "Jsw@2025"
    When user open HR coil pdp
    And select all the attribute and add to cart
    Then check user able to add the product to cart page
    Then account logout

  @deleteLineItem @sanity
  Scenario: check delete line item in cart page
    When check login page
    Given Go to session "ccp" "testautomation01@yopmail.com" "Jsw@2025"
    When user open HR coil pdp
    And select all the attribute and add to cart
    Then check user able to add the product to cart page
    And user will add cement product to cart
    And user will add Stainless Steel product to cart
    Then check user able to delete line item from cart
    Then account logout
