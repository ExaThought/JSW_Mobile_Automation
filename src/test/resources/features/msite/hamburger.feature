@hamburger @msanity @profile
Feature: Hamburger Menu Navigation Validation

  Background:
    Given user open the msite
    Then the home page should load successfully
    And the CCP logo should be visible
    And user clicks on login floating button on mobile
    And user enters email with configured msite user
    When user submits login
    And user enters password with configured msite user
    When user clicks on proceed to login
    And user closes payment reminder popup if visible

  @HamburgerVisibility @msanity
  Scenario: Verify hamburger menu options visibility
    When user opens hamburger menu
    Then all hamburger menu options should be visible and clickable

  @NavigateProfile @msanity
  Scenario: Navigate to Profile details page
    When user opens hamburger menu
    And user clicks on "Profile details"
    Then user should be navigated to "profile" page
    And the "profile" page title should be displayed

  @NavigateAddresses @msanity
  Scenario: Navigate to Saved addresses page
    When user opens hamburger menu
    And user clicks on "Saved addresses"
    Then user should be navigated to "addresses" page
    And the "addresses" page title should be displayed

  @NavigateBank @msanity
  Scenario: Navigate to Bank account details page
    When user opens hamburger menu
    And user clicks on "Bank account details"
    Then user should be navigated to "bank" page
    And the "bank" page title should be displayed

  @NavigateBlogs @msanity
  Scenario: Navigate to Blogs page
    When user opens hamburger menu
    And user clicks on "Blogs"
    Then user should be navigated to "blogs" page
    And the "blogs" page title should be displayed

  @NavigateHelp @msanity
  Scenario: Navigate to Help & support page
    When user opens hamburger menu
    And user clicks on "Help & support"
    Then user should be navigated to "help" page
    And the "help" page title should be displayed

  @NavigateTerms @msanity
  Scenario: Navigate to Terms & conditions page
    When user opens hamburger menu
    And user clicks on "JSW One policies"
    And user clicks on "Terms & conditions"
    Then user should be navigated to "terms" page
    And the "terms" page title should be displayed

  @NavigatePrivacy @msanity
  Scenario: Navigate to Privacy policy page
    When user opens hamburger menu
    And user clicks on "JSW One policies"
    And user clicks on "Privacy policy"
    Then user should be navigated to "privacy" page
    And the "privacy" page title should be displayed

  @NavigateReturn @msanity
  Scenario: Navigate to Return policy page
    When user opens hamburger menu
    And user clicks on "JSW One policies"
    And user clicks on "Return policy"
    Then user should be navigated to "return" page
    And the "return" page title should be displayed