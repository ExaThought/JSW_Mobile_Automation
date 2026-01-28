@carousal
Feature: From Our Customers Carousel

  As a user
  I want to verify the "From our customers" carousel
  So that all slides are visible and navigable

  Background:
    Given user open the msite
    Then the home page should load successfully
    And the CCP logo should be visible

  Scenario: Verify all slides in the first carousel are visible
    Then the first homepage carousel should have visible slides

  Scenario: Click every slide in the first carousel and verify navigation
    When the user clicks on every slide in the first homepage carousel