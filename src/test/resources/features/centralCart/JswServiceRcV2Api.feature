@centralCart
Feature: Check central cart RC v2 ADD-to-Cart Api

  @testCaseId=id-001 @dataFile="centralCartRcV2.yaml"
  Scenario: Check RC v2 API for MSME from WEBSITE
    Given Request the Api for MSME from WEBSITE

  @testCaseId=id-002 @dataFile="centralCartRcV2.yaml"
  Scenario: Check RC V2 Api for MSME from MOBILE
    Given  Request the Api for MSME from MOBILE

  @testCaseId=id-003 @dataFile="centralCartRcV2.yaml"
  Scenario: Check JSW Service RC V2 Api for CONSTRUCT from WEBSITE
    Given Request the Api for CONSTRUCT from WEBSITE

  @testCaseId=id-004 @dataFile="centralCartRcV2.yaml"
  Scenario: Check RC V2 Api for CONSTRUCT from MOBILE
    Given Request the Api for CONSTRUCT from MOBILE


  @testCaseId=id-005 @dataFile="centralCartRcV2.yaml"
  Scenario: Check for Self pick-up
    Given Request API and check delivery type in response


