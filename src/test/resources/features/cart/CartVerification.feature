@regression @CartVerification @productionSanity
Feature: Check Cart page and its functionality

  Background:
    Given Go to session "ccp"
    And ccp login with configured user
    And empty the cart via API

  Scenario: Verify empty cart page
    When click cart icon
    Then check the empty cart page display with message "Your cart is empty"
    And the empty cart illustration should be displayed
    And the Explore products section should show these categories with images:
      | MS Hot rolled    |
      | MS Cold Rolled   |
      | Cement           |
      | Coated Steel     |
      | Wire rods        |
      | Structural steel |
      | Bitumen          |
    And the View all button should be displayed under Explore products
