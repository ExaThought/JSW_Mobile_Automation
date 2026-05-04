@search @regression
Feature: Validate Search functionality and navigation

  Background:
    Given Go to session "ccp"
    And ccp login with "testpre_prod12as@gmail.com" and "Jsw@2026"
    And user close payment due pop up

  @search
  Scenario: Verify presence and visibility of search bar
    Then user should see search bar on home page for web

  @search
  Scenario Outline: Validate search results for valid, partial and invalid keywords
    When user enters "<keyword>" in search bar for web
    Then search results should be displayed for "<resultType>" for web

    Examples:
      | keyword   | resultType |
      | cement    | valid      |
      | cem       | valid      |
      | xyz123@@  | invalid    |

  @search
  Scenario: Verify search keyword retention in search bar
    When user enters "cement" in search bar for web
    Then search keyword "cement" should be retained in search bar for web

  @search
  Scenario: Validate search auto-suggestions
    When user enters "cem" in search bar for web
    Then auto suggestions should be displayed for web
    And auto suggestion list should not be empty for web

  @search
  Scenario: Verify search results UI and product details
    When user enters "cement" in search bar for web
    Then product cards should be visible for web
    And each product card should have name, image for web

  @search
  Scenario: Validate navigation from search results to PDP
    When user enters "cement" in search bar for web
    And user clicks on a product from search results for web
    Then user should be navigated to product detail page for web

  @search
  Scenario: Validate navigation from search results to PLP and then PDP
    When user enters "cement" in search bar for web
    And user clicks on view all products for web
    Then user should be navigated to product listing page for web
    When user selects a product from PLP for web
    Then user should be navigated to product detail page for web
