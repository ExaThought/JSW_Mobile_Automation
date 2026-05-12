@mviewAllNavigation @msanity

Feature: View All navigation for categories on MSME QA mobile site

  Background:
    Given user open the msite
    Then the home page should load successfully
    And the CCP logo should be visible

  Scenario Outline: Verify category navigation on tap
    Then the View All CTA should be visible for "<category>"
    And the View All CTA should be clickable for "<category>"
    When the user clicks on View All for "<category>"
    Then the user should be redirected to the correct PLP for "<category>"


    Examples:
      | category         |
      | MS Hot Rolled  |


