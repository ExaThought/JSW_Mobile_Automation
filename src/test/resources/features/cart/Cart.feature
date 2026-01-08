@regression
Feature: Check Add-To-Cart

  Background:
    Given Go to session "ccp"
    And ccp login with "testautomation01@yopmail.com" and "Jsw@2025"
    And user close payment due pop up

  @addToCart @checkout
  Scenario: check PDP validation and Add-to-cart
#      And user will add Stainless Steel product to cart
    Then check user able to delete line item from cart
    And user checkout the RCV2

  @HRSheet
  Scenario: Verify HR Sheet Product Add to Cart
    When move the mouse to Mild steel category
    And move to Hot rolled category
    And click on HR Sheet product
    And open HR Sheet pdp
    And select all the HR Sheet attribute
    And add to cart
    And click view cart
    Then Delete the line item from cart

  @HRCoil
  Scenario: verify HR Coil Product Add to Cart
    When move the mouse to Mild steel category
    And move to Hot rolled category
    And click on HR Coil product
    And open HR Coil pdp
    And select all the HR Coil attribute
    And add to cart
    And click view cart
    Then Delete the line item from cart

  @CRCoil
  Scenario: Verify CR Coil Product Add to Cart
    When move the mouse to Mild steel category
    And move to Cold rolled category
    And click on CR Coil product
    And open CR Coil pdp
    And select all the CR Coil attribute
    And add to cart
    And click view cart
    Then Delete the line item from cart

  @CRSheet
  Scenario: Verify CR Sheet Product Add to Cart
    When move the mouse to Mild steel category
    And move to Cold rolled category
    And click on CR Sheet product
    And open CR Sheet pdp
    And select all the CR Sheet attribute
    And add to cart
    And click view cart
    Then Delete the line item from cart

  @Cement
  Scenario: Verify Cement Product Add to Cart
    When click on Cement product
    And open Cement pdp
    And select all the Cement attribute
    And add to cart
    And click view cart
    Then Delete the line item from cart

    @ColourCoated
  Scenario: Verify Colour Coated Sheet Product Add to Cart
    When move the mouse to Mild steel category
    And click to Colour Coated category
    And open Colour Coated pdp
    And select all the Colour Coated Sheet attribute
    And add to cart
    And click view cart
    Then Delete the line item from cart


