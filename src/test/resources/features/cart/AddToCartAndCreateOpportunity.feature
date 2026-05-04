@AddToCartAndCreateOpportunity @regression
Feature: Check Add-To-Cart and Create opportunity from Cart

  Background:
    Given Go to session "ccp"
    And ccp login with configured user
    And user close payment due pop up

#  @addToCart @checkout
#  Scenario: check PDP validation and Add-to-cart
#    And user will add Stainless Steel product to cart
#    Then check user able to delete line item from cart
#    And user checkout the RCV2

  @HRSheet
  Scenario: Verify HR Sheet Product Add to Cart
    When move the mouse to Mild steel category
    And move to Hot rolled category
    And click on HR Sheet product
    And open HR Sheet plp "1" product
    And select thickness "2.5" width "1500" length "2500" quantity "50" for HR Sheet
    And add to cart
    And click view cart
    Then verify the Attribute values for HR Sheet in cart page
    Then Delete the line item from cart

  @HRCoil
  Scenario: verify HR Coil Product Add to Cart
    When move the mouse to Mild steel category
    And move to Hot rolled category
    And click on HR Coil product
    And open HR Coil plp "1" product
    And click on reset button in pdp page
    And select thickness "2.5" width "1250" quantity "50" for HR Coil
    And add to cart
    And click view cart
    Then Verify the Attribute values for HR Coil in cart page
    Then Delete the line item from cart

  Scenario: Add same product twice and verify quantity merge or duplicate handling
    When move the mouse to Mild steel category
    And move to Hot rolled category
    And click on HR Coil product
    And open HR Coil plp "1" product
    And click on reset button in pdp page
    And select thickness "2.5" width "1250" quantity "50" for HR Coil
    And add to cart
    And click view cart
    Then Verify the Attribute values for HR Coil in cart page
    When move the mouse to Mild steel category
    And move to Hot rolled category
    And click on HR Coil product
    And open HR Coil plp "1" product
    And click on reset button in pdp page
    And select same attribute values
    And add to cart
    And click view cart
    Then Verify the Attribute values for HR Coil in cart page
    Then Delete the line item from cart

  @multiSKUCart
  Scenario: Check multi product add to cart and delete one by one from cart
    When move the mouse to Mild steel category
    And move to Hot rolled category
    And click on HR Coil product
    And open HR Coil plp "1" product
    And click on reset button in pdp page
    And select thickness "2.5" width "1250" quantity "50" for HR Coil
    And add to cart
    And click view cart
    Then Verify the Attribute values for HR Coil in cart page
    When move the mouse to Mild steel category
    And move to Hot rolled category
    And click on HR Sheet product
    And open HR Sheet plp "1" product
    And select thickness "2.5" width "1500" length "2500" quantity "50" for HR Sheet
    And add to cart
    And click view cart
    Then verify the Attribute values for HR Sheet in cart page
    When click on TMT product
    And click on TMT plp "1" product
    And click on reset button in pdp page
    And select diameter "10" form "Straight" quantity "50" for TMT
    And add to cart
    And click view cart
    Then verify the Attribute values for TMT in cart page
    Then Verify the cart page SKU and cart icon count and delete one by one


  @CRCoil
  Scenario: Verify CR Coil Product Add to Cart
    When move the mouse to Mild steel category
    And move to Cold rolled category
    And click on CR Coil product
    And open CR Coil pdp "1" product
    And click on reset button in pdp page
    And select thickness "0.5" width "1250" quantity "50" for CR Coil
    And add to cart
    And click view cart
    Then verify the Attribute values for CR coil in cart page
    Then Delete the line item from cart

  @CRSheet
  Scenario: Verify CR Sheet Product Add to Cart
    When move the mouse to Mild steel category
    And move to Cold rolled category
    And click on CR Sheet product
    And open CR Sheet pdp
    And click on reset button in pdp page
    And select thickness "0.8" width "1250" length "1930" quantity "50" for CR Sheet
    And add to cart
    And click view cart
    Then verify the Attribute values for CR Sheet in cart page
    Then Delete the line item from cart

  @Cement
  Scenario: Verify Cement Product Add to Cart
    When click on Cement product
    And open Cement pdp "2" product
    And enter quantity "250" for Cement
    And add to cart
    And click view cart
    Then verify the Attribute values for Cement in cart page
    Then Delete the line item from cart

  @CoatedSteel
  Scenario: Verify Coated Steel Sheet Product Add to Cart
    When move the mouse to Mild steel category
    And click to Coated steel category
    And click on Coated steel sheet product
    And open Coated steel sheet pdp "1" product
    And click on reset button in pdp page
    And select thickness "3" width "1250" length "2500" coating grade "120" spangle type "Regular" quantity "50" for Colour Coated Sheet
    And add to cart
    And click view cart
    Then verify the Attribute values for Coated Steel Sheet in cart page
    Then Delete the line item from cart

  @Wire
  Scenario: Verify wire product add to cart
    When move the mouse to Mild steel category
    And click on Wire product
    And open Wire pdp
    And select diameter "10" quantity "50" for Wire
    And add to cart
    And click view cart
    Then verify the Attribute values for Wire in cart page
    Then Delete the line item from cart

  @TMT
  Scenario: Verify TMT product add to cart
    When click on TMT product
    And click on TMT plp "1" product
    And select diameter "10" form "Straight" quantity "50" for TMT
    And add to cart
    And click view cart
    Then verify the Attribute values for TMT in cart page
    Then Delete the line item from cart

  #  @ColourCoated
#  Scenario: Verify Colour Coated Sheet Product Add to Cart
#    When move the mouse to Mild steel category
#    And click to Colour Coated category
#    And open Colour Coated pdp
#    And select thickness "2" width "1250" length "2500" coating grade "120" spangle type "Regular" quantity "50" for Colour Coated Sheet
#    And add to cart
#    And click view cart
#    Then Delete the line item from cart
#
#
#  @Angles
#  Scenario: Verify Angles product add to cart
#    When move the mouse to Structural category
#    And click on Angles product
#    And open Angles pdp
#    And select all the Angles attribute
#    And add to cart
#    And click view cart
#    Then Delete the line item from cart
#
#  @Channels
#  Scenario: Verify Channels product add to cart
#    When move the mouse to Structural category
#    And click on Channels product
#    And open Channels pdp
#    And select all the Channels attribute
#    And add to cart
#    And click view cart
#    Then Delete the line item from cart

    #Product not available currently
#  @BeamsAndColumns
#  Scenario: Verify Beams & Columns product add to cart
#    When move the mouse to Structural category
#    And click on Beams & Columns product
#    And open Beams & Columns pdp
#    And select all the Beams & Columns attribute
#    And add to cart
#    And click view cart
#    Then Delete the line item from cart





