@mCartValidation  @msanity
Feature: Validate Cart Page

  Background:
    Given user open the msite
    Then the home page should load successfully
    And the CCP logo should be visible
    And user clicks on login floating button on mobile
    And user enters email "navjotautomation@yopmail.com"
    When user submits login
    And user enters password "Jsw@2026"
    When user clicks on proceed to login
    When user clicks on catalogue menu
    And click on "MS Hot rolled"
    And click on "Sheets" product on msite
    Then capture product name from PDP
    And select thickness "1.6"
    And enter width "1500"
    And enter length "3000"
    And enter quantity "50"
    And click M-site add to cart


  @CartLineItem
  Scenario: Verify cart line item details
    When user clicks on view cart
    Then product name should match PDP
    And selected attributes should match PDP
    And quantity should match entered quantity
    And price per unit should be displayed


  @CartQuantityUpdate
  Scenario: Verify quantity update updates subtotal
    When user clicks on view cart
    And click edit on mobile cart
    And user edits all attributes in cart


  @EmptyCart
  Scenario: Verify empty cart state
    When user clicks on view cart
    When user deletes all cart items
    Then empty cart message should be displayed
    And no line items should be visible
