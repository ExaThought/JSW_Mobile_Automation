@forgetPassword @regression
Feature: Check forget password

  Background:
    Given Go to session "ccp"
    And user open the CCP web portal

  @sanity
  Scenario Outline: Continue stays disabled on forget password for an invalid email - <case>
    And enter invalid email id "<email>"
    Then continue button should be disabled for user

    Examples:
      | case             | email           |
      | malformed domain | sr@.js          |
      | blank            |                 |
      | missing domain   | user@           |
      | missing at sign  | userexample.com |

  @sanity
  Scenario: Forget password gives the same confirmation for an already registered email
    And enter the email for the "registered" account on forget password
    And user click on the continue button
    And user click on forget password link
    And user click on submit button
    Then user should see forget password link sent message