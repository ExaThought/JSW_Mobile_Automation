@productionSanity @prodSafe @regression
Feature: Production sanity - critical business functionality

  # Every scenario here is STRICTLY READ-ONLY and carries @prodSafe, which is what
  # ProdSafetyHooks requires before a scenario may run with -Denv=prod. Nothing below
  # creates, modifies or deletes an order, cart, address, account or password.
  #
  # The registration scenario is the one to watch: it fills the form to prove client-side
  # validation blocks submission, and deliberately stops before Next is ever enabled.

  # The campaign popup step runs FIRST on every page that has one, before anything reads the
  # page. An interstitial covering the viewport intercepts clicks and hides the header and
  # footer, so a shell assertion made underneath it fails for the wrong reason. It never fails
  # on absence - no campaign running is the normal case.
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

  # The PDP had no coverage at all - the listing scenarios above only prove the cards LINK to a
  # product detail page, nothing opened one. Added so the campaign popup check has a PDP to run
  # on, and it earns its place anyway: a listing full of cards whose detail pages are broken is
  # a catalogue that cannot be sold from.
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

  # Dismissed twice on purpose: once on the guest homepage, where the popup can block the Login
  # button, and again after authenticating, because a campaign aimed at signed-in customers is a
  # different popup that only appears once there is a session.
  @P0
  Scenario: A real customer can sign in and see their account
    Given Go to session "ccp"
    And the campaign popup is dismissed if it is shown
    And ccp login with configured user
    And the campaign popup is dismissed if it is shown
    Then the account menu is available in the header

  # Read-only: a GET of the signed-in customer's own profile, no write path anywhere in it.
  # Worth a P0 because this is the failure the UI cannot show you - the middleware can answer
  # 200 with the wrong customer's profile, or stop answering entirely, and the site keeps
  # rendering. Endpoint comes from middlewareUrl, so this runs unchanged on qa and preprod.
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
