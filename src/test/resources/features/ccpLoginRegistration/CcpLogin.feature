@login @regression @loginRegression
Feature: Check Login

  @sanity @emailLogin @emailLoginWithValidCredentials
  Scenario: Check with valid credentials
    Given user open the CCP portal
    And enter valid email "testnewurl@yopmail.com"
    And enter valid password "Jsw@2025"
    Then user should able to login

   @sanity @emailLogin @emailLoginWithInvalidCredentials
  Scenario: Check for invalid credentials
    Given user open the CCP portal
    And enter invalid email "testnewurl@yopmail.com"
    And enter invalid password "Jsw@2029"
    Then user should not able to login with error message

  @addToCart @checkout @sanity
  Scenario: check PDP validation and Add-to-cart
    Given Go to session "ccp" "testautomation01@yopmail.com" "Jsw@2025"
    When user open HR coil pdp
    And select all the attribute and add to cart
    Then check user able to add the product to cart page
    And user will add cement product to cart
    And user will add Stainless Steel product to cart
    Then check user able to delete line item from cart
    And check user able to add the product to cart without pricing
    And user checkout the RCV2

  @editCart @sanity
  Scenario: check edit line item from cart page
  Given user open the CCP portal
    And enter valid email "testnewurl@yopmail.com"
    And enter valid password "Jsw@2025"
    When user open HR coil pdp
    And select all the attribute and add to cart
    Then check user able to add the product to cart page
