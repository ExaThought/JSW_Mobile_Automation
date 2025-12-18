@regressionApi @rcv2Api @regression
Feature: Check RCV2 Cart Api

  Background:
    Given delete complete cart

  @testCaseId=id-001 @dataFile="rcv2.yaml"
  Scenario: Check Add-to-cart Api
    When we request add to cart Api for primary Quantity MT
    Then response should be 200 and able to see all data in response
    And check the update when adding the same product with identical attributes to the cart again
    Then check the quantity and line item
    And check for sheets product and verify the primary and secondary uom and quantity
    And check for TMT product and verify the primary and secondary uom and quantity
    And check for Cement product and verify the primary and secondary uom and quantity
    And check for StainlessSteel product and verify the primary and secondary uom and quantity


  @testCaseId=id-005 @dataFile="rcv2.yaml"
  Scenario: Check negative Test cases for Add-to-cart Api
    When I send an add-to-cart request with an invalid quantity value
    Then the system should reject the line item and not add it to the cart

  @testCaseId=id-002 @dataFile="rcv2.yaml"
  Scenario: Check delete line item and Delete cart Api
    When we add multi line item to cart
    And delete one line item from cart
    Then check how many line item present in get cart
    And delete complete cart
    Then check the get cart

  @testCaseId=id-003 @dataFile="rcv2.yaml" @new1
  Scenario: Check update line item Api
    When we add multi line item to cart
    And update attribute and quantity for one line item
    Then verify the both line item

  @testCaseId=id-004 @dataFile="rcv2.yaml"
  Scenario: Check get and cart size Api
    When we add multi line item to cart
    Then check cart size Api
    Then verify get cart Api


    Scenario: testing api api
      When request and get response for app Api





