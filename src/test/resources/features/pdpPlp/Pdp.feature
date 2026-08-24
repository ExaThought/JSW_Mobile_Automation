@regression @PDPValidation
Feature: Check PDP page

  Background:
    Given Go to session "ccp"
    And ccp login with configured user
    And user close payment due pop up

  Scenario: Verify reset in pdp page.
    When move the mouse to Mild steel category and to sub category Hot rolled
    And click on HR Sheet product
    And click on first product
    And select thickness, width, length, quantity "50" for HR Sheet
    And click on reset button in pdp page
    Then check Add to cart is disabled

  Scenario: Verify the Cascading in PDP  page.
    When move the mouse to Mild steel category and to sub category Hot rolled
    And click on HR Sheet product
    And click on first product
    And select thickness for pdp "2.5"
    Then check Add to cart is disabled
    And select width "1500"
    Then check Add to cart is disabled
    And select length "2500"
    Then check Add to cart is disabled

  Scenario Outline: Verify the PDP quantity field accepts only 4 digits
    When click cart icon
    Then check the empty cart page display with message "Your cart is empty"
    When move the mouse to Mild steel category and to sub category Hot rolled
    And click on HR Coil product
    And click on first product
    And select thickness, width, quantity "<enteredQuantity>" for HR Coil
    And add to cart
    And click view cart
    Then quantity field should treat only first 4 digits "<expectedQuantity>" as input in PDP page

    Examples:
      | enteredQuantity | expectedQuantity |
      | 1000            | 1000             |
      | 9999            | 9999             |
      | 12345           | 1234             |
      | 99999           | 9999             |

  Scenario: Brand name should not be empty in PDP page.
    When move the mouse to Mild steel category and to sub category Hot rolled
    And click on HR Sheet product
    And click on first product
    Then check brand name is not empty in PDP page

  Scenario: Verify when entering quantity = 0
    When move the mouse to Mild steel category and to sub category Hot rolled
    And click on HR Coil product
    And click on first product
    And select thickness, width, quantity "0" for HR Coil
    Then check Add to cart is disabled

  Scenario: Verify Add to Cart enabled state after all attributes selected
    When move the mouse to Mild steel category and to sub category Hot rolled
    And click on HR Coil product
    And click on first product
    And select thickness, width, quantity "100" for HR Coil
    Then check Add to cart is enabled

  Scenario: Verify product description is not empty
    When move the mouse to Mild steel category and to sub category Hot rolled
    And click on HR Coil product
    And click on first product
    Then check product description is not empty in PDP page