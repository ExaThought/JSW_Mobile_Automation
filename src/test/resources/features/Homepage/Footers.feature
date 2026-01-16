@footer @regression
Feature: Validate Footer links and navigation

  Background:
    Given Go to session "ccp"
    And ccp login with "testautomation01@yopmail.com" and "Jsw@2025"
    And user close payment due pop up

  Scenario: Verify Buy Online section links
    Then the "buyOnlineSection" section should be visible in the footer
    And the footer link "Mild steel" should be visible
    And the footer link "Structural Steel" should be visible
    And the footer link "TMT" should be visible
    And the footer link "Cement" should be visible

  Scenario: Verify JSW One MSME section links
    Then the "msmeSection" section should be visible in the footer
    And the footer link "About us" should be visible
    And the footer link "Blogs" should be visible
    And the footer link "Site map" should be visible
    And the footer link "JSW One TMT" should be visible

  Scenario: Verify Support section details
    Then the "supportSection" section should be visible in the footer
    And the support phone number should be visible
    And the support email address should be visible
    And the footer link "Help support" should be visible

  Scenario: Verify Policy section links
    Then the "policySection" section should be visible in the footer
    And the footer link "Terms & conditions" should be visible
    And the footer link "Privacy policy" should be visible
    And the footer link "Return Policy" should be visible
   @t
  Scenario Outline: Verify footer link navigation
    Given the footer link "<linkName>" should be visible
    When the user clicks on the footer link "<linkName>"
    Then the user should be redirected to the corresponding page

    Examples:
      | linkName           |
      | Mild steel         |
      | Structural Steel   |
      | TMT                |
      | Cement             |
      | About us           |
      | Blogs              |
      | Site map           |
      | JSW One TMT        |
      | Help support       |
      | Terms & conditions |
      | Privacy policy     |
      | Return Policy      |
