@registration @regression @registrationRegression
Feature: Check CCP registration

  Background:
    Given Go to session "ccp"
    And user open the CCP web portal
    And enter the email for the "newRegistration" account on registration
    Then new user continue to Registration

  @sanity @registrationNegative
  Scenario Outline: Next stays disabled for registration details - <case>
    And enter full name "<fullName>" mobile number "<mobile>" and gst "<gstin>"
    Then next button should be disabled

    Examples:
      | case                  | fullName      | mobile     | gstin           |
      | blank name            |               | 9876543210 | 24AAACG8896M1ZX |
      | blank mobile          | Indresh Kumar |            | 24AAACG8896M1ZX |
      | blank GSTIN           | Indresh Kumar | 9876543210 |                 |
      | short mobile          | Indresh Kumar | 12345      | 24AAACG8896M1ZX |
      | malformed GSTIN       | Indresh Kumar | 9876543210 | INVALIDGSTIN    |
      | letters in mobile     | Indresh Kumar | 98765abcde | 24AAACG8896M1ZX |
      | mobile leading zero   | Indresh Kumar | 0876543210 | 24AAACG8896M1ZX |
      | all fields blank      |               |            |                 |

  @sanity @registrationNegative
  Scenario: Invalid phone number shows a field level error
    And enter full name "Indresh Kumar" mobile number "12345" and gst "24AAACG8896M1ZX"
    Then user should not able to proceed with phone number error
    And next button should be disabled

  @sanity @registrationNegative
  Scenario: Invalid GSTIN shows a field level error
    And enter full name "Indresh Kumar" mobile number "9876543210" and gst "INVALIDGSTIN"
    Then user should not able to proceed with GSTIN error
    And next button should be disabled

  Scenario: Verify Registration for existing mobile number
    And enter full name "John Doe" with the existing mobile number and gst "24AAACG8896M1ZX"
    And User click on Next Button
    Then User verifies the error message

  Scenario: Verify Registration for existing GSTIN number
    And enter full name "John Doe" mobile number "7123687236" and the existing gst
    Then next button should be disabled
    Then User verifies the error message
