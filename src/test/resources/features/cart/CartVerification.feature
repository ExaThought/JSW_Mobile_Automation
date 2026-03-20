@regression
Feature: Check Cart page and its functionality

  Background:
    Given Go to session "ccp"
    And ccp login with "testpre_prod12as@gmail.com" and "Jsw@2026"
      And user close payment due pop up


  Scenario: Verify empty cart page
    When click cart icon
    Then check the empty cart page display with message "Your cart is empty"