@regression  @sanity @sloOrderCreation
Feature: Check SLO Flow

  @testCaseId=id-007 @dataFile="sfslcslo.yaml"
  Scenario: SLO - Check Distribute cash and carry order
    When Request and create a cashAndCarry distributer cart
    Then open the ERP and verify all the SFSlo order data

  @testCaseId=id-008 @dataFile="sfslcslo.yaml"
  Scenario: SLO - Check Distribute cash and carry Advance order
    When Request and create a cashAndCarryAdvance distributer cart
    Then open the ERP and verify all the SFSlo order data

  @testCaseId=id-009 @dataFile="sfslcslo.yaml"
  Scenario: SLO - Check Distribute credit order
    When Request and create a credit distributor cart
    Then open the ERP and verify all the SFSlo order data

  @testCaseId=id-010 @dataFile="sfslcslo.yaml"
  Scenario: SLO - Check Plant Supply (Direct) Cash and Carry WithAdvance order
    When Request and create a cash and carry with Advance direct cart
    Then open the ERP and verify all the SFSlo order data

  @testCaseId=id-011 @dataFile="sfslcslo.yaml"
  Scenario: SLO - Check Plant Supply (Direct) Cash and Carry ZeroAdvance order
    When Request and create a cash and carry Zero Advance direct cart
    Then open the ERP and verify all the SFSlo order data

  @testCaseId=id-012 @dataFile="sfslcslo.yaml"
  Scenario: SLO - Check Plant Supply (Direct) Credit order
    When Request and create a Credit direct cart
    Then open the ERP and verify all the SFSlo order data
