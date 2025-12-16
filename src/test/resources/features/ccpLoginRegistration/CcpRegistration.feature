@registration @regression @registrationRegression
Feature: Check CCP registration

  @sanity @validRegistration
  Scenario: Check invalid email registration
    Given open the CCP registration portal
    And enter new invalid email id "test@jsw."
    Then user should not able to proceed to Registration with error message
    And continue button should be disable

  @sanity @validRegistration
  Scenario: Check positive registration and validation
    Given open the CCP registration portal
    And enter new valid email id "indresh.kumarallen@gmail.com"
    Then new user continue to Registration
    And enter full name "Indresh Kumar" mobile number "9876543210" and gst "29ABCDE1234F1Z5"
    Then next button should be disabled
    When user clicks on terms and conditions checkbox
    Then next button should be enabled

  @sanity @registrationNegative
  Scenario: Blank full name
    Given open the CCP registration portal
    And enter new valid email id "user@example.com"
    Then new user continue to Registration
    And enter full name "" mobile number "9876543210" and gst "29ABCDE1234F1Z5"
    Then next button should be disabled

  @sanity @registrationNegative
  Scenario: Blank mobile number
    Given open the CCP registration portal
    And enter new valid email id "user@example.com"
    Then new user continue to Registration
    And enter full name "Indresh Kumar" mobile number "" and gst "29ABCDE1234F1Z5"
    Then next button should be disabled

  @sanity @registrationNegative
  Scenario: Blank GSTIN
    Given open the CCP registration portal
    And enter new valid email id "user@example.com"
    Then new user continue to Registration
    And enter full name "Indresh Kumar" mobile number "9876543210" and gst ""
    Then next button should be disabled

  @sanity @validRegistration
  Scenario: Check invalid phone number validation
    Given open the CCP registration portal
    And enter new valid email id "indresh.kumarallen@gmail.com"
    Then new user continue to Registration
    And enter full name "Indresh Kumar" mobile number "12345" and gst "29ABCDE1234F1Z5"
    Then user should not able to proceed with phone number error
    And next button should be disabled

  @sanity @validRegistration
  Scenario: Check invalid GSTIN validation
    Given open the CCP registration portal
    And enter new valid email id "indresh.kumarallen@gmail.com"
    Then new user continue to Registration
    And enter full name "Indresh Kumar" mobile number "9876543210" and gst "INVALIDGSTIN"
    Then user should not able to proceed with GSTIN error
    And next button should be disabled





