@home @msanity

Feature: Check home page

  Background:
    Given user open the msite
    Then the home page should load successfully
    And the CCP logo should be visible


  Scenario: Verify CCP logo presence and navigation
    Then the CCP logo should be visible
    When the user clicks on the CCP logo
    Then the user should be redirected to the CCP home page


  Scenario: Verify Help icon or link presence and navigation
    Then the Help icon or link should be visible
    When the user clicks on the Help icon or link
    Then the user should be redirected to the Help or Support page

  Scenario: Verify Login CTA presence and navigation
    Then the Login CTA should be visible and enabled
    When the user clicks on the Login CTA
    Then the user should be redirected to the Login page




