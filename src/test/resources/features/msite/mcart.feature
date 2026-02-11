@mCart @regression
Feature: Check Add-To-Cart


  Background:
    Given user open the msite
    Then the home page should load successfully
    And the CCP logo should be visible
    And user clicks on login floating button on mobile
    And user enters email "jayadev.yl@jsw.in"
    When user submits login
    And user enters password "Jsw@2025"
    When user clicks on proceed to login

#  @addToCart @checkout
#  Scenario: check PDP validation and Add-to-cart
#    And user will add Stainless Steel product to cart
#    Then check user able to delete line item from cart
#    And user checkout the RCV2

  @HRSheet
  Scenario: Verify HR Sheet Product Add to Cart
    When user clicks on catalogue menu
    And user clicks Hot rolled category
    And click on HR Sheet product on msite
    And select thickness "3" width "1250" length "2500" quantity "50" for HR Sheet on msite
    And add to cart on mobile
    And click view cart on mobile


  @HRCoil @test
  Scenario: verify HR Coil Product Add to Cart
    When user clicks on catalogue menu
    And user clicks Hot rolled category
    And click on HR Coil product on msite
    And select thickness "4" width "1250" quantity "50" for HR Coil on msite
    And add to cart
    And click view cart


  @CRCoil
  Scenario: Verify CR Coil Product Add to Cart
    When user clicks on catalogue menu
    And user clicks Cold rolled category
    And click on CR Coil product
    And open CR Coil pdp
    And select thickness "2" width "1250" quantity "50" for CR Coil
    And add to cart
    And click view cart


  @CRSheet
  Scenario: Verify CR Sheet Product Add to Cart
    When user clicks on catalogue menu
    And user clicks Cold rolled category
    And click on CR Sheet product
    And open CR Sheet pdp
    And select thickness "2" width "1250" length "2500" quantity "50" for CR Sheet
    And add to cart
    And click view cart


  @Cement
  Scenario: Verify Cement Product Add to Cart
    When click on Cement product
    And open Cement pdp
    And enter quantity "250" for Cement
    And add to cart
    And click view cart


  @CoatedSteel
  Scenario: Verify Coated Steel Sheet Product Add to Cart
    When move the mouse to Mild steel category
    And click to Coated steel category
    And click on Coated steel sheet product
    And open Coated steel sheet pdp
    And select thickness "2" width "1250" length "2500" coating grade "120" spangle type "Regular" quantity "50" for Colour Coated Sheet
    And add to cart
    And click view cart


  @Wire
  Scenario: Verify wire product add to cart
    When move the mouse to Mild steel category
    And click on Wire product
    And open Wire pdp
    And select all the Wire attribute
    And add to cart
    And click view cart

  @TMT
  Scenario: Verify TMT product add to cart
    When click on TMT product
    And click on TMT plp "1" product
    And select diameter "10" form "Straight" quantity "50" for TMT
    And add to cart
    And click view cart
