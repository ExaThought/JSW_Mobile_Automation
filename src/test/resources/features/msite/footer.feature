@footer
Feature: Verify footer elements and navigation on home page

  Background:
    Given user open the msite
    Then the home page should load successfully
    And the CCP logo should be visible

  # -------------------- Buy Online Section --------------------
  Scenario: Verify Buy Online section links in footer
    Then the "Buy online" section should be visible in the footer
    And the footer link "Mild steel" should be visible
    And the footer link "Structural Steel" should be visible
    And the footer link "TMT" should be visible
    And the footer link "Cement" should be visible

  # -------------------- JSW One MSME Section --------------------
  Scenario: Verify JSW One MSME section links in footer
    Then the "JSW One MSME" section should be visible in the footer
    And the footer link "About us" should be visible
    And the footer link "Blogs" should be visible
    And the footer link "Site map" should be visible
    And the footer link "JSW One TMT" should be visible

  # -------------------- Support Section --------------------
  Scenario: Verify Support section details in footer
    Then the "Support" section should be visible in the footer
    And the support phone number should be visible
    And the support email address should be visible
    And the footer link "Help support" should be visible

  # -------------------- Policy Section --------------------
  Scenario: Verify Policy section links in footer
    Then the "Policy" section should be visible in the footer
    And the footer link "Terms & conditions" should be visible
    And the footer link "Privacy policy" should be visible
    And the footer link "Return Policy" should be visible

  # -------------------- Footer Navigation --------------------
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
