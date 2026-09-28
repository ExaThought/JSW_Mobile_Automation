@multiUser @primaryUserLogin @regression
Feature: Multi-user account - primary user login

  Background:
    Given Go to session "ccp"

  @sanity @primaryUserLoginValid
  Scenario: Primary user can log in with the configured multi-user credentials
    And the primary user enters the email for the "primaryUser" account
    And the primary user clicks on continue
    And the primary user enters the password for the "primaryUser" account
    Then the primary user password field should be masked
    And the primary user should be logged in
    And navigate to the User and permissions tab
    Then user should see the Primary user tag on the "primaryUser" account

  @primaryUserSession
  Scenario: Primary user stays logged in after a page refresh
    And the primary user enters the email for the "primaryUser" account
    And the primary user clicks on continue
    And the primary user enters the password for the "primaryUser" account
    And the primary user should be logged in
    Then the primary user should still be logged in after a page refresh

  @primaryUserLogout
  Scenario: Primary user can log out
    And the primary user enters the email for the "primaryUser" account
    And the primary user clicks on continue
    And the primary user enters the password for the "primaryUser" account
    And the primary user should be logged in
    Then the primary user logs out

  @primaryUserLoginInvalid
  Scenario: Primary user login is rejected with the wrong password
    And the primary user enters the email for the "primaryUser" account
    And the primary user clicks on continue
    And the primary user enters the wrong password for the "primaryUser" account
    Then the primary user should not be able to log in with an error message
    And the primary user should be logged out
