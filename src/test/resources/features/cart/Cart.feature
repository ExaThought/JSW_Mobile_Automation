@regression @cart @sanity
Feature: Check Add-To-Cart

  @addToCart @checkout
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

  @editCarAndUpdateQuantity
  Scenario: check edit line item from cart page
    Given Go to session "ccp" "kl@hj.com" "Abinash@123"
    Then close the payment reminder pop-up
    When user open HR coil pdp
    And select all the attribute and add to cart
    Then check user able to add the product to cart page
    Then validate the user is able to edit the the cart page and update the quantity or attribute
    Then account logout

  @deleteLineItem
  Scenario: check delete line item in cart page
    Given Go to session "ccp" "kl@hj.com" "Abinash@123"
    Then close the payment reminder pop-up
    When user open HR coil pdp
    And select all the attribute and add to cart
    Then check user able to add the product to cart page
    And user will add cement product to cart
    And user will add Stainless Steel product to cart
    Then check user able to delete line item from cart
    And calculate the number of line items in the cart
    Then account logout

    @cementProduct
  Scenario: check adding cement product to cart
    Given Go to session "ccp" "kl@hj.com" "Abinash@123"
    Then close the payment reminder pop-up
    And user will add cement product to cart
      Then account logout


    @StainlessSteelProduct
  Scenario: check adding Stainless Steel product to cart
    Given Go to session "ccp" "kl@hj.com" "Abinash@123"
    Then close the payment reminder pop-up
    And user will add Stainless Steel product to cart
      Then account logout

