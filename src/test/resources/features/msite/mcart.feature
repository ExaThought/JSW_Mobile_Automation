@mCart @regression
Feature: Check Add-To-Cart


  Background:
    Given user open the msite
    Then the home page should load successfully
    And the CCP logo should be visible
    And user clicks on login floating button on mobile
    And user enters email "testmysore@yopmail.com"
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
    And select thickness "1.6"
    And enter length "2500"
    And enter width "1250"
    And enter quantity "50"
    And click M-site add to cart


  @HRCoil
  Scenario: verify HR Coil Product Add to Cart
    When user clicks on catalogue menu
    And user clicks Hot rolled category
    And click on HR Coil product on msite
    And select thickness "4.0"
    And enter width "1250"
    And enter quantity "50"
    And click M-site add to cart



  @CRCoil @test01
  Scenario: Verify CR Coil Product Add to Cart
    When user clicks on catalogue menu
    And user clicks Cold rolled category
    And click on CR "Coils" product on msite
    And select thickness "0.5"
    And enter width "1250"
    And enter quantity "50"
    And click M-site add to cart


  @CRSheet
  Scenario: Verify CR Sheet Product Add to Cart
    When user clicks on catalogue menu
    And user clicks Cold rolled category
    And click on CR Sheet product
    And open CR Sheet pdp
    And select thickness "2" width "1250" length "2500" quantity "50" for CR Sheet
    And enter length "2500"
    And enter width "1250"
    And enter quantity "50"
    And click M-site add to cart
    And click view cart


  @Cement
  Scenario: Verify Cement Product Add to Cart
    When click on Cement product
    And open Cement pdp "1" product
    And enter quantity "250" for Cement
    And click M-site add to cart
    And click view cart


  @CoatedSteel
  Scenario: Verify Coated Steel Sheet Product Add to Cart
    When move the mouse to Mild steel category
    And click to Coated steel category
    And click on Coated steel sheet product
    And open Coated steel sheet pdp "1" product
    And select thickness "2" width "1250" length "2500" coating grade "120" spangle type "Regular" quantity "50" for Colour Coated Sheet
    And click M-site add to cart
    And click view cart


  @Wire
  Scenario: Verify wire product add to cart
    When move the mouse to Mild steel category
    And click on Wire product
    And open Wire pdp
    And select diameter "10" quantity "50" for Wire
    And click M-site add to cart
    And click view cart

  @TMT
  Scenario: Verify TMT product add to cart
    When click on TMT product
    And click on TMT plp "1" product
    And select diameter "10" form "Straight" quantity "50" for TMT
    And click M-site add to cart
    And click view cart
