@regression  @sanity @sloOrderCreation
Feature: Check SLO Flow

  @testCaseId=id-005 @dataFile="sfslcslo.yaml"
  Scenario: Check Distribute cash and carry order
    When Request and create a cashAndCarry distributer cart
    Then open the ERP and verify all the SFSlo order data

  @testCaseId=id-005 @dataFile="sfslcslo.yaml"
  Scenario: Check Distribute cash and carry Advance order
    When Request and create a cashAndCarryAdvance distributer cart
    Then open the ERP and verify all the SFSlo order data

  @testCaseId=id-005 @dataFile="sfslcslo.yaml"
  Scenario: Check Distribute credit order
    When Request and create a credit distributor cart
    Then open the ERP and verify all the SFSlo order data

  @testCaseId=id-006 @dataFile="sfslcslo.yaml"
  Scenario: Check Plant Supply (Direct) Cash and Carry WithAdvance order
    When Request and create a cash and carry with Advance direct cart
    Then open the ERP and verify all the SFSlo order data

  @testCaseId=id-007 @dataFile="sfslcslo.yaml"
  Scenario: Check Plant Supply (Direct) Cash and Carry ZeroAdvance order
    When Request and create a cash and carry Zero Advance direct cart
    Then open the ERP and verify all the SFSlo order data

  @testCaseId=id-008 @dataFile="sfslcslo.yaml"
  Scenario: Check Plant Supply (Direct) Credit order
    When Request and create a Credit direct cart
    Then open the ERP and verify all the SFSlo order data