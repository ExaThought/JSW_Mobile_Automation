@regression @sanity @orderCreation
Feature: Check SLC/SLO Flow

  @testCaseId=id-001 @dataFile="sfslcslo.yaml"
  Scenario: Check Distribute cash and carry order
    When Request and create an cashAndCarry distributer cart
    And Open the CCP website and login "testnewurl@yopmail.com" and "Jsw@2025"
    And Open the cart and verify the cart details
    And Create an order and verify the order summery page
    And open the ERP and verify all the data


  @testCaseId=id-001 @dataFile="sfslcslo.yaml"
  Scenario: Check Distribute cash and carry Advance order
    When Request and create an cashAndCarryAdvance distributer cart
    And Open the CCP website and login "testnewurl@yopmail.com" and "Jsw@2025"
    And Open the cart and verify the cart details
    And Create an order and verify the order summery page
    And open the ERP and verify all the data


  @testCaseId=id-001 @dataFile="sfslcslo.yaml"
  Scenario: Check Distribute credit order
    When Request and create an credit distributer cart
    And Open the CCP website and login "testnewurl@yopmail.com" and "Jsw@2025"
    And Open the cart and verify the cart details
    And Create an order and verify the order summery page
    And open the ERP and verify all the data

  @testCaseId=id-002 @dataFile="sfslcslo.yaml"
  Scenario: Check Plant Supply (Direct) Cash and Carry WithAdvance order
    When Request and create an cash and carry withAdvance direct cart
    And Open the CCP website and login "testnewurl@yopmail.com" and "Jsw@2025"
    And Open the cart and verify the cart details
    And Create an order and verify the order summery page
    And open the ERP and verify all the data

  @testCaseId=id-003 @dataFile="sfslcslo.yaml"
  Scenario: Check Plant Supply (Direct) Cash and Carry ZeroAdvance order
    When Request and create an cash and carry ZeroAdvance direct cart
    And Open the CCP website and login "testnewurl@yopmail.com" and "Jsw@2025"
    And Open the cart and verify the cart details
    And Create an order and verify the order summery page
    And open the ERP and verify all the data

   @testCaseId=id-002 @dataFile="sfslcslo.yaml"
  Scenario: Check Plant Supply (Direct) Credit order
    When Request and create an Credit direct cart
    And Open the CCP website and login "testnewurl@yopmail.com" and "Jsw@2025"
    And Open the cart and verify the cart details
    And Create an order and verify the order summery page
    And open the ERP and verify all the data