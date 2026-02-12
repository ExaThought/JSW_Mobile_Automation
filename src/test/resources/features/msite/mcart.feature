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
    When user clicks on catalogue menu

  @HRSheet @test01
  Scenario: Verify HR Sheet Product Add to Cart
    And click on "MS Hot rolled"
    And click on "Sheets" product on msite
    And select thickness "1.6"
    And enter length "2500"
    And enter width "1250"
    And enter quantity "50"
    And click M-site add to cart

  @HRCoil
  Scenario: verify HR Coil Product Add to Cart
    And click on "MS Hot rolled"
    And click on "Coils" product on msite
    And select thickness "4.0"
    And enter width "1250"
    And enter quantity "50"
    And click M-site add to cart

  @CRCoil
  Scenario: Verify CR Coil Product Add to Cart
    And click on "MS Cold rolled"
    And click on "Coils" product on msite
    And select thickness "0.5"
    And enter width "1250"
    And enter quantity "50"
    And click M-site add to cart


  @CRSheet
  Scenario: Verify CR Sheet Product Add to Cart
    And click on "MS Cold rolled"
    And click on "Sheets" product on msite
    And open CR Sheet pdp
    And select thickness "0.8"
    And enter width "1250"
    And enter length "2500"
    And enter quantity "50"
    And click M-site add to cart

  @Cement
  Scenario: Verify Cement Product Add to Cart
    When user clicks on catalogue menu
    And click on "Cement"
    And click on "JSW Ordinary Portland Cement 43" product on msite
    And enter quantity "100"
    And click M-site add to cart



