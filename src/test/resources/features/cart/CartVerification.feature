@regression @CartVerification
Feature: Check Cart page and its functionality

  Background:
    Given Go to session "ccp"
    And ccp login with configured user
      And user close payment due pop up


  Scenario: Verify empty cart page
    When click cart icon
    Then check the empty cart page display with message "Your cart is empty"