@Test
Feature: Check CRUD operation for central cart RC v2 ADD-to-Cart Api

  @testCaseId=id-001 @dataFile="centralCartRcV2.yaml"
  Scenario: Check Central cart RC v2 API for MSME from WEBSITE
    When Request and get response for Central Cart Api for MSME from WEBSITE
    And  Check Get Cart Size API for Rc
    And  Check Fetch Cart API for Rc
    And  Check Update Line Item Quantity Api for Rc
    And  Check Delete Line Item APi for Rc
    And  Check Delete CartId Api for Rc
    Then Check MongoBD Cart Status After Cart Delete for Rc

  @testCaseId=id-002 @dataFile="centralCartRcV2.yaml"
  Scenario: Check Central Cart RC V2 Api for MSME from MOBILE
    When Request and get response for Central Cart Api for MSME from MOBILE
    And  Check Get Cart Size API for Rc
    And  Check Fetch Cart API for Rc
    And  Check Update Line Item Quantity Api for Rc
    And  Check Delete Line Item APi for Rc
    And  Check Delete CartId Api for Rc
    Then Check MongoBD Cart Status After Cart Delete for Rc

  @testCaseId=id-003 @dataFile="centralCartRcV2.yaml"
  Scenario: Check Central Cart RC V2 Api for CONSTRUCT from WEBSITE
    When Request and get response for Central Cart Api for CONSTRUCT from WEBSITE
    And  Check Get Cart Size API for Rc
    And  Check Fetch Cart API for Rc
    And  Check Update Line Item Quantity Api for Rc
    And  Check Delete Line Item APi for Rc
    And  Check Delete CartId Api for Rc
    Then Check MongoBD Cart Status After Cart Delete for Rc

  @testCaseId=id-004 @dataFile="centralCartRcV2.yaml"
  Scenario: Check Central Cart RC V2 Api for CONSTRUCT from MOBILE
    When Request and get response for Central Cart Api for CONSTRUCT from MOBILE
    And  Check Get Cart Size API for Rc
    And  Check Fetch Cart API for Rc
    And  Check Update Line Item Quantity Api for Rc
    And  Check Delete Line Item APi for Rc
    And  Check Delete CartId Api for Rc
    Then Check MongoBD Cart Status After Cart Delete for Rc

  @testCaseId=id-005 @dataFile="centralCartRcV2.yaml"
  Scenario: Check for Self pick-up for Central Cart Rc Api
    Given Request Central Cart API and check delivery type in response

  @testCaseId=id-001 @dataFile="centralCartRcV2.yaml"
  Scenario: Check CRUD operation for mobile and device combination
    Given Request and Get Api Response in "WEBSITE"
    And Update line item quantity in "MOBILE"
    Then Delete line item and delete cart in "WEBSITE"
