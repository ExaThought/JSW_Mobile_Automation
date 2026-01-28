@mregistration
Feature: Check CCP registration on MWeb

  Background:
    Given user open the msite
    Then the home page should load successfully
    And the CCP logo should be visible
    When user clicks on login button

  Scenario Outline: Check invalid email registration
    And user enters email "<email>"
    Then user should not be able to proceed to Registration with error message
    And continue button should be disabled on msite

    Examples:
      | email    |
      | test@.js |

  Scenario Outline: Check positive registration and validation
    And user enters email "<email>"
    When user clicks on proceed to login
    Then new user continue to Registration on msite
    And enter full name "<fullName>" mobile number "<mobile>" and gst "<gst>" on msite
    Then next button should be enabled on msite

    Examples:
      | email            | fullName | mobile      | gst              |
      | user@example.com | Test CCP | 9876543210  | 29ABCDE1234F1Z5  |


  Scenario Outline: Validate registration negative cases
    And user enters email "<email>"
    When user clicks on proceed to login
    Then new user continue to Registration on msite
    And enter full name "<fullName>" mobile number "<mobile>" and gst "<gst>" on msite
    Then next button should be disabled on msite

    Examples:
      | email            | fullName | mobile     | gst              |
      | user@example.com | 123  | 9876543210 | 29ABCDE1234F1Z5  |
      | user@example.com | Test CCP | Invalid    | 29ABCDE1234F1Z5  |
      | user@example.com | Test CCP | 9876543210 | Invalid          |


  Scenario Outline: Check invalid phone number and GSTIN validations
    And user enters email "<email>"
    When user clicks on proceed to login
    Then new user continue to Registration on msite
    And enter full name "<fullName>" mobile number "<mobile>" and gst "<gst>" on msite
    Then user should not be able to proceed with "<errorType>" error
    And next button should be disabled on msite

    Examples:
      | email                       | fullName | mobile     | gst            | errorType    |
      | user@example.com| Test CCP | 12345      | 29ABCDE1234F1Z5 | phone number |
      | user@example.com | Test CCP | 9876543210 | INVALIDGSTIN    | GSTIN        |
