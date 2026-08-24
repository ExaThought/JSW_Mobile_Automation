@login @regression @loginRegression
Feature: Check Login

  Background:
    Given Go to session "ccp"

  @sanity @emailLogin @emailLoginWithValidCredentials @loginAndLogout
  Scenario: Check CCP login and logout with valid credentials
    And enter the email for the "login" account
    And user click on continue button
    And enter the password for the "login" account
    Then the password should be masked
    And user should able to login
    And the user should still be logged in after a page refresh
    Then User click on logout button

  @sanity @emailLogin @emailLoginWithInvalidCredentials
  Scenario: Check login is rejected for a valid email with the wrong password
    And enter the email for the "login" account
    And user click on continue button
    And enter the wrong password for the "login" account
    Then user should not able to login with error message
    And user should be logged out

  @sanity @emailLogin @emailLoginWithInvalidCredentials
  Scenario Outline: Continue stays disabled for an invalid email - <case>
    And enter invalid email "<email>"
    Then continue button should be disabled

    Examples:
      | case             | email            |
      | malformed domain | sr@.js           |
      | blank            |                  |
      | missing domain   | user@            |
      | missing at sign  | userexample.com  |
      | missing mailbox  | @example.com     |
      | double at sign   | user@@example.com|
      | trailing dot     | user@example.    |
      | spaces inside    | user name@ex.com |

  @sanity @emailLogin @emailLoginWithInvalidCredentials
  Scenario: Check a malformed email shows a field level error
    And enter invalid email "sr@.js"
    Then user should not able to  proceed to login with error message
