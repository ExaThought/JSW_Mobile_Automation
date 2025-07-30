@ccpErpSyncRegression @regression
Feature: Check CCP-ERP Sync

  @sanity
  @testCaseId=id-001 @dataFile="ccpErpSync.yaml"
  Scenario: Verify Single Shipment Order Processing
    When the user creates an cart from SF
    And login to ccp and create an order "testnewurl@yopmail.com" and "Jsw@2025"
    And logs into ERP and opens the order details page
#    And confirms the order
#    And creates and releases the DO
#    And creates the shipment
#    And marks it as ready for dispatch
#    And uploads the invoice and all other required files
#    Then the user logs into CCP and opens the order summary page
#    And opens the shipment page and verifies all the data