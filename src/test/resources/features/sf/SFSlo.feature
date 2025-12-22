@regression  @sanity @sloOrderCreation
Feature: Check SLO Flow

  @testCaseId=id-004 @dataFile="sfslcslo.yaml"
  Scenario: Check Distribute cash and carry order
    When Request and create an cashAndCarry distributor order
    And open the ERP and verify all the SFSlo order data

  @testCaseId=id-004 @dataFile="sfslcslo.yaml"
  Scenario: Check Distribute cash and carry Advance order
    When Request and create an cashAndCarryAdvance distributor order
    And open the ERP and verify all the SFSlo order data

  @testCaseId=id-004 @dataFile="sfslcslo.yaml"
  Scenario: Check Distribute credit order
    When Request and create an credit distributor order
    And open the ERP and verify all the SFSlo order data

  @testCaseId=id-005 @dataFile="sfslcslo.yaml"
  Scenario: Check Plant Supply (Direct) Cash and Carry WithAdvance order
    When Request and create an cash and carry withAdvance direct order
    And open the ERP and verify all the SFSlo order data

  @testCaseId=id-006 @dataFile="sfslcslo.yaml"
  Scenario: Check Plant Supply (Direct) Cash and Carry ZeroAdvance order
    When Request and create an cash and carry ZeroAdvance direct order
    And open the ERP and verify all the SFSlo order data

  @testCaseId=id-005 @dataFile="sfslcslo.yaml"
  Scenario: Check Plant Supply (Direct) Credit order
    When Request and create an Credit direct order
    And open the ERP and verify all the SFSlo order data