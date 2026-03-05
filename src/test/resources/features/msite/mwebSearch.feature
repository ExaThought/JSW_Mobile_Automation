@msearch @msanity

Feature: Verify search coming  on home page

  Background:
    Given user open the msite
    Then the home page should load successfully
    And the CCP logo should be visible

  @search
  Scenario: Verify presence and visibility of search bar
    Then user should see search bar on home page

  @search @fail
  Scenario Outline: Validate search results for valid, partial and invalid keywords
    When user enters "<keyword>" in search bar
    Then search results should be displayed for "<resultType>"

    Examples:
      | keyword     | resultType |
      | Cement      | valid      |
      | Cem         | partial    |
      | xyz123@@    | invalid    |

  @search
  Scenario: Verify search keyword retention in search bar
    When user enters "Cement" in search bar
    Then search keyword "Cement" should be retained in search bar

  @search
  Scenario: Validate search input and auto-suggestions
    When user enters "cem" in search bar
    Then auto suggestions should be displayed
    And auto suggestion list should not be empty

  @search
  Scenario: Verify search results page UI and product details
    When user enters "cement" in search bar
    And user submits the search
    Then product cards should be visible
    And each product card should have name, image

  @search
  Scenario: Validate navigation from search results to PDP
    When user enters "cement" in search bar
    And user clicks on a product from search results
    Then user should be navigated to product detail page

  @search
  Scenario: Validate navigation from search results to PLP page and then to PDP
    When user enters "cement" in search bar
    And user submits the search
    Then user should be navigated to product listing page
    When user selects a product from PLP
    Then user should be navigated to product detail page


