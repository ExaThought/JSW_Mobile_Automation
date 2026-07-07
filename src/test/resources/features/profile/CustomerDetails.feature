@customerDetails @regression
Feature: validate customer profile details

  Background:
    Given Go to session "ccp"
    And ccp login with configured user
#    And user close payment due pop up


  Scenario: Check and validate  navigation for  customer profile  page
    And user clicks on Account Drop Down
    And user clicks on Profile option

  @testCaseId=id-001 @dataFile="customerDetails.yaml"
  Scenario: Validate Name is displayed in Profile details
    And user clicks on Account Drop Down
    And user clicks on Profile option
    Then validate Name field value in Profile details

  @testCaseId=id-001 @dataFile="customerDetails.yaml"
  Scenario: Validate Phone number is displayed in Profile details
    And user clicks on Account Drop Down
    And user clicks on Profile option
    Then validate Phone number field value in Profile details

  @testCaseId=id-001 @dataFile="customerDetails.yaml"
  Scenario: Validate Email address is displayed in Profile details
    And user clicks on Account Drop Down
    And user clicks on Profile option
    Then validate Email address field value in Profile details

  @testCaseId=id-001 @dataFile="customerDetails.yaml"
  Scenario: Validate Company details section name and address
    And user clicks on Account Drop Down
    And user clicks on Profile option
    Then validate Company Name field value in Company details
    Then validate Company address field value in Company details

  @testCaseId=id-001 @dataFile="customerDetails.yaml"
  Scenario: Validate Company details section Gst and Pan
    And user clicks on Account Drop Down
    And user clicks on Profile option
    Then validate GSTIN field value in Company details
    Then validate PAN field value in Company details
