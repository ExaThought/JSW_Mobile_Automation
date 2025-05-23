Feature: Check All Negative Test Cases For Central Cart

  @testCaseId=id-001 @dataFile="centralCartRcV2.yaml"
  Scenario: Check for Cart identifier
    When Check "ENQUIRY_CART" with "SALES_LEAD_CART" combination
    When Check "ORDER_CART" with "REQUIREMENTS_CART_V2" combination
    When Check "ENQUIRY_CART" with "SALES_LEAD_ORDER" combination
    And Check Wrong CartType "ORDER_CARt" with correct CartJourneyType "SALES_LEAD_CART"
    And Check Wrong CartType "ENQUIRY_CARt" with correct CartJourneyType "REQUIREMENTS_CART_V2"
    And Check correct CartType "ORDER_CART" with wrong CartJourneyType "SALES_LEAD_CARt"
    And Check correct CartType "ENQUIRY_CART" with wrong CartJourneyType "REQUIREMENTS_CART"
    Then Check with wrong StoreFront "NULL"
    Then Check without deliveryType and wrong delivery type "ABCD"
    Then check with Invalid ProductMMID "1000-1000"


