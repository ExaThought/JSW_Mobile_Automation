@productionSanity @prodSafe @regression
Feature: Production sanity - critical business functionality


  @P0
  Scenario Outline: Critical page loads without error - <page>
    When a visitor opens the "<page>" page
    Then the campaign popup is dismissed if it is shown
    And the page renders the site shell without an error state
    And the page title is not empty

    Examples:
      | page                |
      | home                |
      | mildSteelCategory   |
      | blogs               |
      | helpSupport         |
      | sitemap             |

  @P0
  Scenario: An unknown URL shows the branded 404 page
    When a visitor opens an address that does not exist
    Then the branded not-found page is shown
    And the page renders the site shell without an error state

  @P0
  Scenario Outline: The product category listing has real, sellable products - <category>
    When a visitor opens the "<category>" page
    Then the campaign popup is dismissed if it is shown
    And the listing heading names the category
    And at least "<minimum>" product cards are shown, each with a name and an image
    And every product card links to a product detail page

    Examples:
      | category            | minimum |
      | mildSteelCategory   | 6       |
      | tmtCategory         | 1       |

  @P0
  Scenario: A product detail page opens from the listing
    When a visitor opens the "mildSteelCategory" page
    And the campaign popup is dismissed if it is shown
    And a visitor opens the first product on the listing
    Then the campaign popup is dismissed if it is shown
    And the page renders the site shell without an error state
    And the page title is not empty

  @P0
  Scenario Outline: Real-world search terms return results - <term>
    When a visitor searches production for "<term>"
    Then the search results page lists at least "1" product card
    And every search result is relevant to "<term>"

    Examples:
      | term       |
      | TMT        |
      | HR Coil    |
      | Cement     |

  @P0
  Scenario: A real customer can sign in and see their account
    Given Go to session "ccp"
    And the campaign popup is dismissed if it is shown
    And ccp login with configured user
    And the campaign popup is dismissed if it is shown
    Then the account menu is available in the header

  @P0
  Scenario: The customer profile API answers for the signed-in account
    When we request the customer profile Api
    Then customer profile Api should respond with status code 200
    And customer profile response should contain a valid customer profile
    And the customer profile belongs to the signed-in account

  @P0
  Scenario: Registration form validates without submitting
    When a visitor opens the registration form
    And the visitor enters an invalid mobile number and GSTIN
    Then the registration form refuses to submit
