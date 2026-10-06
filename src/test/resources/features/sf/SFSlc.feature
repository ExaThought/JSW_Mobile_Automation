@regression  @sanity @slcOrderCreation
Feature: Check SLC Flow

 @testCaseId=id-001 @dataFile="sfslcslo.yaml"
  Scenario: SLC - Check Distribute cash and carry order
    When Request and create a cashAndCarry distributer cart
    Given Go to session "ccp"
    And ccp login with configured user
    Then user close payment due pop up
    And Open the cart and verify the cart details
    And Create an order and verify the order summery page
    And open the ERP and verify all the SFSlc order data

  @testCaseId=id-002 @dataFile="sfslcslo.yaml"
  Scenario: SLC - Check Distribute cash and carry Advance order
    When Request and create a cashAndCarryAdvance distributer cart
    Given Go to session "ccp"
    And ccp login with configured user
    Then user close payment due pop up
    And Open the cart and verify the cart details
    And Create an order and verify the order summery page
    And open the ERP and verify all the SFSlc order data

  @testCaseId=id-003 @dataFile="sfslcslo.yaml"
  Scenario: SLC - Check Distribute credit order
    When Request and create a credit distributor cart
    Given Go to session "ccp"
    And ccp login with configured user
    Then user close payment due pop up
    And Open the cart and verify the cart details
    And Create an order and verify the order summery page
    And open the ERP and verify all the SFSlc order data

  @testCaseId=id-004 @dataFile="sfslcslo.yaml"
  Scenario: SLC - Check Plant Supply (Direct) Cash and Carry WithAdvance order
    When Request and create a cash and carry with Advance direct cart
    Given Go to session "ccp"
    And ccp login with configured user
    Then user close payment due pop up
    And Open the cart and verify the cart details
    And Create an order and verify the order summery page
    And open the ERP and verify all the SFSlc order data

  @testCaseId=id-005 @dataFile="sfslcslo.yaml"
  Scenario: SLC - Check Plant Supply (Direct) Cash and Carry ZeroAdvance order
    When Request and create a cash and carry Zero Advance direct cart
    Given Go to session "ccp"
    And ccp login with configured user
    Then user close payment due pop up
    And Open the cart and verify the cart details
    And Create an order and verify the order summery page
    And open the ERP and verify all the SFSlc order data

  @testCaseId=id-006 @dataFile="sfslcslo.yaml"
  Scenario: SLC - Check Plant Supply (Direct) Credit order
    When Request and create a Credit direct cart
    Given Go to session "ccp"
    And ccp login with configured user
    Then user close payment due pop up
    And Open the cart and verify the cart details
    And Create an order and verify the order summery page
    And open the ERP and verify all the SFSlc order data
