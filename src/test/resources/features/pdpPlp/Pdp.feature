@regression @PDPValidation
Feature: Check PDP page

  Background:
    Given Go to session "ccp"
    And ccp login with configured user
    And user close payment due pop up

  Scenario: Verify reset in pdp page.
    When move the mouse to Mild steel category and to sub category Cold rolled
    And click on CR Sheet product
    And User lands on PLP
    And click on first product
    And select thickness, width, length, quantity "50" for CR Sheet
    And click on reset button in pdp page
    Then check Add to cart is disabled

  Scenario: Verify the Cascading in PDP  page.
    When move the mouse to Mild steel category and to sub category Hot rolled
    And click on HR Sheet product
    And User lands on PLP
    And click on first product
    And select thickness for pdp "2.5"
    Then check Add to cart is disabled
    And select width "1500"
    Then check Add to cart is disabled
    And select length "2500"
    Then check Add to cart is disabled

  Scenario Outline: Verify the PDP quantity field accepts only 4 digits - <enteredQuantity>
    When click cart icon
    Then check the empty cart page display with message "Your cart is empty"
    When move the mouse to Mild steel category and to sub category Hot rolled
    And click on HR Sheet product
    And User lands on PLP
    And click on first product
    And select thickness, width, length, quantity "<enteredQuantity>" for HR Sheet
    Then quantity field should treat only first 4 digits "<expectedQuantity>" as input in PDP page

    Examples:
      | enteredQuantity | expectedQuantity |
      | 1000            | 1000             |
      | 9999            | 9999             |
      | 12345           | 1234             |
      | 99999           | 9999             |

  @productionSanity @prodSafe
  Scenario: Brand name should not be empty in PDP page.
    When move the mouse to Mild steel category and to sub category Hot rolled
    And click on HR Coil product
    And User lands on PLP
    And click on first product
    Then check brand name is not empty in PDP page

  Scenario: Verify when entering quantity = 0
    When move the mouse to Mild steel category and to sub category Hot rolled
    And click on HR Sheet product
    And User lands on PLP
    And click on first product
    And select thickness, width, length, quantity "100" for HR Sheet
    Then check Add to cart is enabled
    When enter quantity "0" in PDP without verifying the committed value
    Then check Add to cart is disabled

  @productionSanity @prodSafe
  Scenario: Verify Add to Cart enabled state after all attributes selected
    When move the mouse to Mild steel category and to sub category Hot rolled
    And click on HR Sheet product
    And User lands on PLP
    And click on first product
    And select thickness, width, length, quantity "100" for HR Sheet
    Then check Add to cart is enabled

  @productionSanity @prodSafe
  Scenario: Verify product description is not empty
    When move the mouse to Mild steel category and to sub category Hot rolled
    And click on HR Coil product
    And User lands on PLP
    And click on first product
    Then check product description is not empty in PDP page