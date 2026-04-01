@EditAndDeleteCart @regression
Feature: Check Edit and Delete line item from Cart

  Background:
    Given Go to session "ccp"
    And ccp login with "preprodautomation01@yopmail.com" and "Jsw@2026"
    And user close payment due pop up

  @HRSheetEditAndDeleteCart @test01
  Scenario: Verify HR Sheet Product Add to Cart
    When move the mouse to Mild steel category
    And move to Hot rolled category
    And click on HR Sheet product
    And open HR Sheet plp "1" product
    And select thickness "2.5" width "1250" length "1500" quantity "50" for HR Sheet
    And add to cart
    And click view cart
    Then verify the Attribute values for HR Sheet in cart page
    And click edit
    And update Thickness to "1.6" dropdown
    And update Width to "1500" dropdown
    And update Length to "7250" dropdown
    And update Quantity "75"
    And Save edit changes
    And verify the updated Attribute values for HR Sheet in cart page
    Then Delete the line item from cart

  @HRCoilEditAndDeleteCart
  Scenario: verify HR Coil Product Add to Cart
    When move the mouse to Mild steel category
    And move to Hot rolled category
    And click on HR Coil product
    And open HR Coil plp "2" product
    And select thickness "4" width "1250" quantity "50" for HR Coil
    And add to cart
    And click view cart
    Then Verify the Attribute values for HR Coil in cart page
    And click edit
    And update Thickness to "3" dropdown
    And update Width to "1500" dropdown
    And update Quantity "75"
    And Save edit changes
    And Verify the updated Attribute values for HR Coil in cart page
    Then Delete the line item from cart

  @CRCoilEditAndDeleteCart
  Scenario: Verify CR Coil Product Add to Cart
    When move the mouse to Mild steel category
    And move to Cold rolled category
    And click on CR Coil product
    And open CR Coil pdp "1" product
    And select thickness "2" width "1250" quantity "50" for CR Coil
    And add to cart
    And click view cart
    Then verify the Attribute values for CR coil in cart page
    And click edit
    And update Thickness to "3" dropdown
    And update Width to "2" dropdown
    And update Quantity "75"
    And Save edit changes
    And verify the updated Attribute values for CR coil in cart page
    Then Delete the line item from cart

  @CRSheetEditAndDeleteCart
  Scenario: Verify CR Sheet Product Add to Cart
    When move the mouse to Mild steel category
    And move to Cold rolled category
    And click on CR Sheet product
    And open CR Sheet pdp
    And select thickness "2" width "1250" length "2500" quantity "50" for CR Sheet
    And add to cart
    And click view cart
    Then verify the Attribute values for CR Sheet in cart page
    And click edit
    And update Thickness to "3" dropdown
    And update Width to "2" dropdown
    And update Length to "1" dropdown
    And update Quantity "75"
    And Save edit changes
    And verify the updated Attribute values for CR Sheet in cart page
    Then Delete the line item from cart

  @CementEditAndDeleteCart
  Scenario: Verify Cement Product Add to Cart
    When click on Cement product
    And open Cement pdp "1" product
    And enter quantity "250" for Cement
    And add to cart
    And click view cart
    Then verify the Attribute values for Cement in cart page
    And click edit
    And update Quantity "75"
    And Save edit changes
    Then verify the updated Attribute values for Cement in cart page
    Then Delete the line item from cart

  @CoatedSteelEditAndDeleteCart
  Scenario: Verify Coated Steel Sheet Product Add to Cart
    When move the mouse to Mild steel category
    And click to Coated steel category
    And click on Coated steel sheet product
    And open Coated steel sheet pdp "1" product
    And select thickness "2" width "1250" length "2500" coating grade "120" spangle type "Regular" quantity "50" for Colour Coated Sheet
    And add to cart
    And click view cart
    Then verify the Attribute values for Coated Steel Sheet in cart page
    And click edit
    And Save edit changes
    Then Delete the line item from cart

  @WireEditAndDeleteCart
  Scenario: Verify wire product add to cart
    When move the mouse to Mild steel category
    And click on Wire product
    And open Wire pdp
    And select diameter "10" quantity "50" for Wire
    And add to cart
    And click view cart
    Then verify the Attribute values for Wire in cart page
    And click edit
    And update Diameter to "12"
    And update Quantity "75"
    And Save edit changes
    And verify the updated Attribute values for Wire in cart page
    Then Delete the line item from cart

  @TMTEditAndDeleteCart
  Scenario: Verify TMT product add to cart
    When click on TMT product
    And click on TMT plp "3" product
    And select diameter "10" form "Straight" quantity "50" for TMT
    And add to cart
    And click view cart
    Then verify the Attribute values for TMT in cart page
    And click edit
    And update Diameter to "8"
    And update Form to "Straight"
    And update Quantity "75"
    And Save edit changes
    And verify the updated Attribute values for TMT in cart page
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





