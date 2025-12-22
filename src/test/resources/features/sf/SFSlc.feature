@regression  @sanity @slcOrderCreation
Feature: Check SLC Flow

 @testCaseId=id-001 @dataFile="sfslcslo.yaml" @test01
  Scenario: Check Distribute cash and carry order
    When Request and create an cashAndCarry distributer cart
   Given Go to session "ccp" "testautomation01@yopmail.com" "Jsw@2025"
   Then user close payment due pop up
   And Open the cart and verify the cart details
    And Create an order and verify the order summery page
    And open the ERP and verify all the SFSlc order data

  @testCaseId=id-001 @dataFile="sfslcslo.yaml"
  Scenario: Check Distribute cash and carry Advance order
    When Request and create an cashAndCarryAdvance distributer cart
    Given Go to session "ccp" "testautomation01@yopmail.com" "Jsw@2025"
    Then user close payment due pop up
    And Open the cart and verify the cart details
    And Create an order and verify the order summery page
    And open the ERP and verify all the SFSlc order data

  @testCaseId=id-001 @dataFile="sfslcslo.yaml"
  Scenario: Check Distribute credit order
    When Request and create an credit distributer cart
    Given Go to session "ccp" "testautomation01@yopmail.com" "Jsw@2025"
    Then user close payment due pop up
    And Open the cart and verify the cart details
    And Create an order and verify the order summery page
    And open the ERP and verify all the SFSlc order data

  @testCaseId=id-002 @dataFile="sfslcslo.yaml"
  Scenario: Check Plant Supply (Direct) Cash and Carry WithAdvance order
    When Request and create an cash and carry withAdvance direct cart
    Given Go to session "ccp" "testautomation01@yopmail.com" "Jsw@2025"
    Then user close payment due pop up
    And Open the cart and verify the cart details
    And Create an order and verify the order summery page
    And open the ERP and verify all the SFSlc order data

  @testCaseId=id-003 @dataFile="sfslcslo.yaml"
  Scenario: Check Plant Supply (Direct) Cash and Carry ZeroAdvance order
    When Request and create an cash and carry ZeroAdvance direct cart
    Given Go to session "ccp" "testautomation01@yopmail.com" "Jsw@2025"
    Then user close payment due pop up
    And Open the cart and verify the cart details
    And Create an order and verify the order summery page
    And open the ERP and verify all the SFSlc order data

   @testCaseId=id-002 @dataFile="sfslcslo.yaml"
  Scenario: Check Plant Supply (Direct) Credit order
    When Request and create an Credit direct cart
     Given Go to session "ccp" "testautomation01@yopmail.com" "Jsw@2025"
     Then user close payment due pop up
    And Open the cart and verify the cart details
    And Create an order and verify the order summery page
    And open the ERP and verify all the SFSlc order data