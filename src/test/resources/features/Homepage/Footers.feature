@footer @regression
Feature: Validate Footer links and navigation

  Background:
    Given Go to session "ccp"
    And ccp login with "testpre_prod12as@gmail.com" and "Jsw@2026"
    And user close payment due pop up

  Scenario: Verify Buy Online section links
    Then the "buyOnlineSection" section should be visible in the footer for web
    And the footer link "Mild steel" should be visible for web
    And the footer link "Structural Steel" should be visible for web
    And the footer link "TMT" should be visible for web
    And the footer link "Cement" should be visible for web

  Scenario: Verify JSW One MSME section links
    Then the "msmeSection" section should be visible in the footer for web
    And the footer link "About us" should be visible for web
    And the footer link "Blogs" should be visible for web
    And the footer link "Site map" should be visible for web
    And the footer link "JSW One TMT" should be visible for web

  Scenario: Verify Support section details
    Then the "supportSection" section should be visible in the footer for web
    And the support phone number should be visible for web
    And the support email address should be visible for web
    And the footer link "Help support" should be visible for web

  Scenario: Verify Policy section links
    Then the "policySection" section should be visible in the footer for web
    And the footer link "Terms & conditions" should be visible for web
    And the footer link "Privacy policy" should be visible for web
    And the footer link "Return Policy" should be visible for web

  Scenario Outline: Verify footer link navigation
    Given the footer link "<linkName>" should be visible for web
    When the user clicks on the footer link "<linkName>" for web
    Then the user should be redirected to the corresponding page for web

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
