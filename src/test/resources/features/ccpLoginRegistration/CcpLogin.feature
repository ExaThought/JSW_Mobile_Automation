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

  @sanity @mobileLogin @mobileLoginWithOtp @loginAndLogout
  Scenario: Check CCP login via mobile number with OTP and logout
    And enter the mobile number "8981112313"
    And user clicks continue to get the OTP
    And enter the OTP captured from the construct otp API
    And user clicks continue to verify the OTP
    Then User click on logout button

  @sanity @mobileLogin @mobileLoginUnregistered
  Scenario: Check an unregistered mobile number is taken to registration after OTP verification
    And enter a randomly generated unregistered mobile number
    And user clicks continue to get the OTP
    And enter the OTP captured from the construct otp API
    And user clicks continue on the OTP screen
    Then the user should be redirected to the registration page

  @sanity @mobileLogin @mobileLoginWithWrongOtp
  Scenario: Check mobile login is rejected when the entered OTP is not the one sent
    And enter a randomly generated mobile number
    And user clicks continue to get the OTP
    And enter an OTP different from the captured one
    And user clicks continue on the OTP screen
    Then the incorrect OTP error message should be shown

  @sanity @mobileLogin @mobileLoginWithInvalidNumber
  Scenario Outline: Check an invalid mobile number is rejected - <case>
    And enter the mobile number "<mobile>"
    And user tries to continue with the mobile number
    Then the mobile number error message should be "<message>"

    Examples:
      | case          | mobile     | message                           |
      | 9 digits      | 987654321  | Please enter a valid phone number |
      | 5 digits      | 98765      | Please enter a valid phone number |
      | 1 digit       | 9          | Please enter a valid phone number |
      | starts with 5 | 5123456789 | Invalid Mobile Number             |
      | starts with 0 | 0123456789 | Invalid Mobile Number             |
      | starts with 1 | 1234567890 | Invalid Mobile Number             |

  @sanity @mobileLogin @mobileLoginLedger
  Scenario: Validate ledger page opens through direct url for user logged in by mobile number and OTP
    And enter the mobile number "8981112313"
    And user clicks continue to get the OTP
    And enter the OTP captured from the construct otp API
    And user clicks continue to verify the OTP
    Then user should be logged in
    And user opens ledger page through direct url
    Then verify user is on ledger page
    Then verify ledger page title
    Then verify Financial Doc, Payment instruction is displayed
    Then verify Help & Support button is displayed and clickable

  @sanity @emailLogin @logoutBackButton
  Scenario: Check the browser back button does not return to a logged-in screen after logout
    And enter the email for the "login" account
    And user click on continue button
    And enter the password for the "login" account
    And user should able to login
    And user opens Profile details from the Account menu
    Then the profile should show the details of the "login" account
    And User click on logout button
    When user presses the browser back button
    And the Login or Register page should be displayed

  @sanity @loginAbandoned
  Scenario: Check login_abandoned fires when the guest closes the login dialog
    When the guest user clicks the header Login button
    And the guest user closes the login dialog
    Then the login_abandoned event should fire for the closed login dialog

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
