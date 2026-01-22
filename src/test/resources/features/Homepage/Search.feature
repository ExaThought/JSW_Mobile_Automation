@search @regression
Feature: Validate Search functionality and navigation

  Background:
    Given Go to session "ccp"
    And ccp login with "testautomation01@yopmail.com" and "Jsw@2025"
    And user close payment due pop up

  @search
  Scenario: Verify presence and visibility of search bar
    Then user should see search bar on home page

  @search
  Scenario Outline: Validate search results for valid, partial and invalid keywords
    When user enters "<keyword>" in search bar
    Then search results should be displayed for "<resultType>"

    Examples:
      | keyword   | resultType |
      | cement    | valid      |
      | cem       | valid      |
      | xyz123@@ | invalid    |

  @search
  Scenario: Verify search keyword retention in search bar
    When user enters "cement" in search bar
    Then search keyword "cement" should be retained in search bar

  @search
  Scenario: Validate search auto-suggestions
    When user enters "cem" in search bar
    Then auto suggestions should be displayed
    And auto suggestion list should not be empty

  @search
  Scenario: Verify search results UI and product details
    When user enters "cement" in search bar
    Then product cards should be visible
    And each product card should have name, image

  @search
  Scenario: Validate navigation from search results to PDP
    When user enters "cement" in search bar
    And user clicks on a product from search results
    Then user should be navigated to product detail page

  @search
  Scenario: Validate navigation from search results to PLP and then PDP
    When user enters "cement" in search bar
    And user clicks on view all products
    Then user should be navigated to product listing page
    When user selects a product from PLP
    Then user should be navigated to product detail page
