@customerDetails @regression
Feature: validate customer profile details

  Background:
    Given Go to session "ccp"
    And ccp login with configured user
    And user clicks on Account Drop Down
    And user clicks on Profile option

  @testCaseId=id-001 @dataFile="customerDetails.yaml"
  Scenario: Validate personal details against the customer record
    Then the customer profile page should be displayed
    And the "Personal details" section should be displayed on the profile page
    And validate Name field value in Profile details
    And validate Phone number field value in Profile details
    And validate Email address field value in Profile details
    And the displayed email address should be well formed
    And the displayed phone number should be well formed

  @testCaseId=id-001 @dataFile="customerDetails.yaml"
  Scenario: Validate company details against the customer record
    Then the "Company details" section should be displayed on the profile page
    And validate Company Name field value in Company details
    And validate Company address field value in Company details
    And validate GSTIN field value in Company details
    And validate PAN field value in Company details
    And the displayed GSTIN should be a valid GST number
    And the displayed PAN should be a valid PAN
    And the displayed GSTIN should contain the displayed PAN
