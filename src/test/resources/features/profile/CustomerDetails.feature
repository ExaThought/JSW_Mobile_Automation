@customerDetails @regression
Feature: validate customer profile details

  Background:
    Given Go to session "ccp"
    And ccp login with configured user
    And user close payment due pop up


  Scenario: Check and validate  navigation for  customer profile  page
    And user clicks on customer account menu
    Then user able to navigate  on  customer Profile

  @testCaseId=id-001 @dataFile="customerDetails.yaml"
  Scenario: Validate Name is displayed in Profile details
    And user clicks on customer account menu
    And user able to navigate  on  customer Profile
    Then validate Name field value in Profile details

  @testCaseId=id-001 @dataFile="customerDetails.yaml"
  Scenario: Validate Phone number is displayed in Profile details
    And user clicks on customer account menu
    And user able to navigate  on  customer Profile
    Then validate Phone number field value in Profile details

  @testCaseId=id-001 @dataFile="customerDetails.yaml"
  Scenario: Validate Email address is displayed in Profile details
    And user clicks on customer account menu
    And user able to navigate  on  customer Profile
    Then validate Email address field value in Profile details

  @testCaseId=id-001 @dataFile="customerDetails.yaml"
  Scenario: Validate Company details section name and address
    And user clicks on customer account menu
    And user able to navigate  on  customer Profile
    Then validate Company Name field value in Company details
    Then validate Company address field value in Company details

  @testCaseId=id-001 @dataFile="customerDetails.yaml"
  Scenario: Validate Company details section Gst and Pan
    And user clicks on customer account menu
    And user able to navigate  on  customer Profile
    Then validate GSTIN field value in Company details
    Then validate PAN field value in Company details
