@header @regression
Feature: Validate Home Page Header

  Background:
    Given Go to session "ccp"

  Scenario: Verify header contents for a guest user
    Given user is on the application as a "Logged Out" user
    Then the JSW One logo should be displayed in the header
    And the header should display the primary navigation menu items
    And the header search should be displayed in the header
    And the location selector should be displayed in the header
    And the cart icon should be displayed in the header
    And the header should display the Login call-to-action
    And the account menu should not be displayed in the header

  Scenario: Verify header contents for a logged-in user
    Given user is on the application as a "Logged In" user
    Then the JSW One logo should be displayed in the header
    And the header should display the primary navigation menu items
    And the header search should be displayed in the header
    And the location selector should be displayed in the header
    And the cart icon should be displayed in the header
    And the account menu should be displayed in the header
    And the Login call-to-action should not be displayed in the header
    When user hovers over the "Mild Steel" navigation menu in the header
    Then the mega-menu panel should be displayed with sub-categories

  Scenario Outline: Verify header navigation menu redirects to the correct listing page
    Given user is on the application as a "Logged Out" user
    When user clicks on the "<menuItem>" navigation menu in the header
    Then user should be redirected to the "<expectedPath>" listing page

    Examples:
      | menuItem         | expectedPath               |
      | Mild Steel       | /category/mild-steel       |
      | Structural steel | /category/structural-steel |
      | Cement           | /category/cement           |
      | TMT              | /category/TMT-Bar-steel    |
      | Bitumen          | /category/bitumen          |
      | Blogs            | /blogs                     |

  Scenario Outline: Verify JSW One logo returns the user to the home page
    Given user is on the application as a "<userType>" user
    Then the JSW One logo should be displayed in the header
    When user navigates away from the home page
    And user clicks on the header logo
    Then user should be navigated to the home page

    Examples:
      | userType   |
      | Logged In  |
      | Logged Out |
