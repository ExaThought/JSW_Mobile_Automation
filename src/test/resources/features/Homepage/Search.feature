@search @regression @productionSanity @prodSafe
Feature: Validate Search functionality and navigation

  Background:
    Given Go to session "ccp"

  Scenario: Verify presence and visibility of search bar
    Given user is on the application as a "Logged In" user
    Then user should see search bar on home page for web

  Scenario Outline: Validate search suggestions for valid and partial keywords
    Given user is on the application as a "Logged In" user
    When user enters "<keyword>" in search bar for web
    Then search results should be displayed for "<keyword>" for web

    Examples:
      | keyword |
      | cement  |
      | cem     |

  Scenario: Validate search suggestions are not shown for a non-matching keyword
    Given user is on the application as a "Logged In" user
    When user enters "xyz123@@" in search bar for web
    Then no search suggestions should be displayed for "xyz123@@" for web

  Scenario: Verify search keyword retention in search bar
    Given user is on the application as a "Logged In" user
    When user enters "cement" in search bar for web
    Then search keyword "cement" should be retained in search bar for web

  Scenario: Validate search auto-suggestions
    Given user is on the application as a "Logged In" user
    When user enters "cem" in search bar for web
    Then auto suggestions should be displayed for web
    And auto suggestion list should not be empty for web

  Scenario: Verify search results UI and product details
    Given user is on the application as a "Logged In" user
    When user enters "cement" in search bar for web
    Then product cards should be visible for web
    And each product card should have name, image for web

  Scenario: Validate navigation from search results to PDP
    Given user is on the application as a "Logged In" user
    When user enters "cement" in search bar for web
    And user clicks on a product from search results for web
    Then user should be navigated to product detail page for web

  Scenario: Validate navigation from search results to PLP and then PDP
    Given user is on the application as a "Logged In" user
    When user enters "cement" in search bar for web
    And user clicks on view all products for web
    Then user should be navigated to product listing page for web
    When user selects a product from PLP for web
    Then user should be navigated to product detail page for web

  Scenario: Validate the search results page reflects the submitted keyword
    Given user is on the application as a "Logged In" user
    When user searches for "cement" from the header for web
    Then the search results page should be displayed for "cement" for web
    And the search result count should reference "cement" for web
    And every search result should be relevant to "cement" for web

  # Search must be case-insensitive - the same query in a different case is the same query.
  Scenario Outline: Validate search is case-insensitive
    Given user is on the application as a "Logged In" user
    When user searches for "<keyword>" from the header for web
    Then the search results page should be displayed for "<keyword>" for web
    And every search result should be relevant to "cement" for web

    Examples:
      | keyword |
      | cement  |
      | CEMENT  |
      | Cement  |

  Scenario: A guest can search and reach a product detail page
    Given user is on the application as a "Logged Out" user
    Then user should see search bar on home page for web
    When user searches for "cement" from the header for web
    Then the search results page should be displayed for "cement" for web
    And every search result should be relevant to "cement" for web
    When user selects a product from PLP for web
    Then user should be navigated to product detail page for web

  # Re-enabled. This was previously commented out as FAILS BY DESIGN: on QA,
  # /product-list?text=<no-match> rendered "Something went wrong! We are unable to load this
  # page at the moment." instead of an empty state, reproduced with two non-matching keywords.
  #
  # The fix was NOT independently confirmed when re-enabling. It was re-enabled alongside a
  # locator change to the literal 'No products found', which implies someone saw the empty
  # state render - but nothing in the change records that check, so treat it as unverified
  # until this has passed a full QA run.
  #
  # If it fails, read WHICH assertion failed before calling it flaky - the step distinguishes
  # the two cases deliberately:
  #   "rendered the generic error page (Something went wrong!)"
  #        -> the product defect is back, or was never fixed. The expectation here is the
  #           CORRECT one; do not relax it to make the test green, the error page is the bug.
  #   "No 'no results' message was shown"
  #        -> the empty state DID render, with copy plpNoResultsMessage does not match.
  #           Widen the locator in searchsteps.yaml; do not weaken the step.
  Scenario: Validate a non-matching search shows a no-results message rather than an error page
    Given user is on the application as a "Logged In" user
    When user searches for "qqqwwweee" from the header for web
    Then the search results page should be displayed for "qqqwwweee" for web
    And the search results page should show a no-results message for "qqqwwweee" for web
