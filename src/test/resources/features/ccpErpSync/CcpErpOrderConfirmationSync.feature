@ccpErpSyncRegression
Feature: Check CCP-ERP order confirmation Sync

  @orderConfirmation @cashAndCarryOrderConfirmation
  @testCaseId=id-001 @dataFile="ccpErpSync.yaml"
  Scenario: Verify Order Confirmation for cash and carry.
    When create cash and carry cart from SF
    And login to ccp and create an order "jayadevgowda10@yopmail.com" and "Jsw@2025"
    Then user close payment due pop up
    And open the cart page and create order
    And logs into ERP and opens the order details page
    And confirms the order in ERP and verify in CCP

  @orderConfirmation @cashAndCarryAdvanceOrderConfirmation @sanity
  @testCaseId=id-002 @dataFile="ccpErpSync.yaml" @regression
  Scenario: Verify Order Confirmation for cash and carry Advance.
    When create cash and carry cart advance from SF
    And login to ccp and create an order "jayadevgowda10@yopmail.com" and "Jsw@2025"
    Then user close payment due pop up
    And open the cart page and create order
    And logs into ERP and opens the order details page
    And confirms the order in ERP and verify in CCP




