@AddToCartAndCreateOpportunity @regression
Feature: Check Add-To-Cart and Create opportunity from Cart

  Background:
    Given Go to session "ccp"
    And ccp login with configured user
    And empty the cart via API

  @HRSheets
  Scenario: Verify HR Sheet Product Add to Cart
    When click cart icon
    Then check the empty cart page display with message "Your cart is empty"
    And User navigate to HomePage
    When move the mouse to Mild steel category and to sub category Hot rolled
    And click on HR Sheet product
    And click on first product
    And select thickness, width, length, quantity "50" for HR Sheet
    And add to cart
    And click view cart
    Then verify the Attribute values for HR Sheet in cart page with cart count "1"


  @HRCoil
  Scenario: Verify HR Coil Product Add to Cart
    When click cart icon
    Then check the empty cart page display with message "Your cart is empty"
    And User navigate to HomePage
    When move the mouse to Mild steel category and to sub category Hot rolled
    And click on HR Coil product
    And click on first product
    And select thickness, width, quantity "50" for HR Coil
    And add to cart
    And click view cart
    Then Verify the Attribute values for HR Coil in cart page with cart count "1"


# This Scenario is failing on QA Because of Invalid Data
  @CRCoil
  Scenario: Verify CR Coil Product Add to Cart
    When click cart icon
    Then check the empty cart page display with message "Your cart is empty"
    And User navigate to HomePage
    When move the mouse to Mild steel category and to sub category Cold rolled
    And click on CR Coil product
    And click on first product
    And select thickness, width, quantity "50" for CR Coil
    And add to cart
    And click view cart
    Then verify the Attribute values for CR coil in cart page


  @CRSheet
  Scenario: Verify CR Sheet Product Add to Cart
    When click cart icon
    Then check the empty cart page display with message "Your cart is empty"
    And User navigate to HomePage
    When move the mouse to Mild steel category and to sub category Cold rolled
    And click on CR Sheet product
    And click on first product
    And select thickness, width, length, quantity "50" for CR Sheet
    And add to cart
    And click view cart
    Then verify the Attribute values for CR Sheet in cart page


  @Cement
  Scenario: Verify Cement Product Add to Cart
    When click cart icon
    Then check the empty cart page display with message "Your cart is empty"
    And User navigate to HomePage
    When click on Cement product
    And click on first product
    And enter quantity "250" for Cement
    And add to cart
    And click view cart
    Then verify the Attribute values for Cement in cart page


  @CoatedSteel
  Scenario: Verify Coated Steel Sheet Product Add to Cart
    When move the mouse to Mild steel category
    And click on Coated steel category
    And click on first product
    And select thickness, width, length and quantity "50" for Coated Sheet
    And add to cart
    And click view cart
    Then verify the Attribute values for Coated Steel Sheet in cart page


  @Wire
  Scenario: Verify Wire Product Add to Cart
    When click cart icon
    Then check the empty cart page display with message "Your cart is empty"
    When move the mouse to Mild steel category
    And click on Wire product
    And click on first product
    And click on reset button in pdp page
    And select diameter and quantity "50" for Wire
    And add to cart
    And click view cart
    Then verify the Attribute values for Wire in cart page


  @TMT
  Scenario: Verify TMT Product Add to Cart
    When click cart icon
    Then check the empty cart page display with message "Your cart is empty"
    When click on TMT product
    And click on first product
    And select diameter, form, quantity "50" for TMT
    And add to cart
    And click view cart
    Then verify the Attribute values for TMT in cart page


  @AddSameProductTwice
  Scenario: Add same product twice and verify quantity merge or duplicate handling
    When click cart icon
    Then check the empty cart page display with message "Your cart is empty"
    And User navigate to HomePage
    When move the mouse to Mild steel category and to sub category Hot rolled
    And click on HR Coil product
    And click on first product
    And select thickness, width, quantity "50" for HR Coil
    And add to cart
    And click view cart
    Then Verify the Attribute values for HR Coil in cart page with cart count "1"
    When move the mouse to Mild steel category and to sub category Hot rolled
    And click on HR Coil product
    And click on first product
    And select thickness, width, quantity "50" for HR Coil
    And add to cart
    And click view cart
    Then Verify the doubled quantity for HR Coil in cart page with cart count "1"


  @multiSKUCart
  Scenario: Check multi product add to cart and delete one by one from cart
    When click cart icon
    Then check the empty cart page display with message "Your cart is empty"
    And User navigate to HomePage
    When move the mouse to Mild steel category and to sub category Hot rolled
    And click on HR Coil product
    And click on first product
    And select thickness, width, quantity "50" for HR Coil
    And add to cart
    And click view cart
    Then Verify the Attribute values for HR Coil in cart page with cart count "1"
    When move the mouse to Mild steel category and to sub category Hot rolled
    And click on HR Sheet product
    And click on first product
    And select thickness, width, length, quantity "50" for HR Sheet
    And add to cart
    And click view cart
    Then verify the Attribute values for HR Sheet in cart page with cart count "2"
    When click on TMT product
    And click on first product
    And select diameter, form, quantity "50" for TMT
    And add to cart
    And click view cart
    Then verify the Attribute values for TMT in cart page with cart count "3"
    Then Verify the cart page SKU and cart icon count and delete one by one


  @createOpportunity
  Scenario: Create an opportunity by submitting the cart requirement
    When click cart icon
    Then check the empty cart page display with message "Your cart is empty"
    And User navigate to HomePage
    When move the mouse to Mild steel category and to sub category Hot rolled
    And click on HR Coil product
    And click on first product
    And select thickness, width, quantity "50" for HR Coil
    And add to cart
    And click view cart
    Then Verify the Attribute values for HR Coil in cart page with cart count "1"
    And submit the requirement to create an opportunity
    Then verify the opportunity is created with a valid requirement id


  @deleteLineItem
  Scenario: Verify deleting the only line item empties the cart
    When click cart icon
    Then check the empty cart page display with message "Your cart is empty"
    And User navigate to HomePage
    When move the mouse to Mild steel category and to sub category Hot rolled
    And click on HR Coil product
    And click on first product
    And select thickness, width, quantity "50" for HR Coil
    And add to cart
    And click view cart
    Then Verify the Attribute values for HR Coil in cart page with cart count "1"
    Then Delete the line item from cart
    Then check the empty cart page display with message "Your cart is empty"


  @cartPersistence
  Scenario: Verify the cart retains items after a page refresh
    When click cart icon
    Then check the empty cart page display with message "Your cart is empty"
    And User navigate to HomePage
    When move the mouse to Mild steel category and to sub category Hot rolled
    And click on HR Coil product
    And click on first product
    And select thickness, width, quantity "50" for HR Coil
    And add to cart
    And click view cart
    Then Verify the Attribute values for HR Coil in cart page with cart count "1"
    Then cart should retain the items after page refresh