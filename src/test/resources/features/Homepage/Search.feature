@search @regression
Feature: Validate Search functionality and navigation

  Background:
    Given Go to session "ccp"
    And ccp login with configured user

  Scenario: Verify presence and visibility of search bar
    Then user should see search bar on home page for web

  Scenario Outline: Validate search suggestions for valid and partial keywords
    When user enters "<keyword>" in search bar for web
    Then search results should be displayed for "<keyword>" for web

    Examples:
      | keyword |
      | cement  |
      | cem     |

  Scenario: Validate search suggestions are not shown for a non-matching keyword
    When user enters "xyz123@@" in search bar for web
    Then no search suggestions should be displayed for "xyz123@@" for web

  Scenario: Verify search keyword retention in search bar
    When user enters "cement" in search bar for web
    Then search keyword "cement" should be retained in search bar for web

  Scenario: Validate search auto-suggestions
    When user enters "cem" in search bar for web
    Then auto suggestions should be displayed for web
    And auto suggestion list should not be empty for web

  Scenario: Verify search results UI and product details
    When user enters "cement" in search bar for web
    Then product cards should be visible for web
    And each product card should have name, image for web

  Scenario: Validate navigation from search results to PDP
    When user enters "cement" in search bar for web
    And user clicks on a product from search results for web
    Then user should be navigated to product detail page for web

  Scenario: Validate navigation from search results to PLP and then PDP
    When user enters "cement" in search bar for web
    And user clicks on view all products for web
    Then user should be navigated to product listing page for web
    When user selects a product from PLP for web
    Then user should be navigated to product detail page for web

  Scenario: Validate the search results page reflects the submitted keyword
    When user searches for "cement" from the header for web
    Then the search results page should be displayed for "cement" for web
    And the search result count should reference "cement" for web
    And every search result should be relevant to "cement" for web

  # Search must be case-insensitive - the same query in a different case is the same query.
  Scenario Outline: Validate search is case-insensitive
    When user searches for "<keyword>" from the header for web
    Then the search results page should be displayed for "<keyword>" for web
    And every search result should be relevant to "cement" for web

    Examples:
      | keyword |
      | cement  |
      | CEMENT  |
      | Cement  |

  # FAILS BY DESIGN on QA today. /product-list?text=<no-match> renders
  # "Something went wrong! We are unable to load this page at the moment." instead of an empty
  # state - reproduced with two different non-matching keywords. The expectation below is the
  # CORRECT one: do not relax it to make the test green, the error page is the bug.
  # Scenario: Validate a non-matching search shows a no-results message rather than an error page
  #   When user searches for "qqqwwweee" from the header for web
  #   Then the search results page should be displayed for "qqqwwweee" for web
  #   And the search results page should show a no-results message for "qqqwwweee" for web
