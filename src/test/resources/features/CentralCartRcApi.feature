@Test
Feature: Check central cart RC v2 ADD-to-Cart Api

  @testCaseId=id-001 @dataFile="centralCartRcV2.yaml"
  Scenario: Check Central cart RC v2 API for MSME from WEBSITE
    Given Request the Central Cart Api for MSME from WEBSITE
#    Then Delete the Cart
    #Check cart_identifier
    # cart_type": "ENQUIRY_CART",
    #"cart_journey_type": "REQUIREMENTS_CART_V2",
    #"store_front": "MSME",
    #"device_type": "WEBSITE"

  @testCaseId=id-002 @dataFile="centralCartRcV2.yaml"
  Scenario: Check Central Cart RC V2 Api for MSME from MOBILE
    Given  Request the Central Cart Api for MSME from MOBILE
    #Check cart_identifier
    # cart_type: "ENQUIRY_CART",
    #"cart_journey_type": "REQUIREMENTS_CART_V2",
    #"store_front": "MSME",
    #"device_type": "MOBILE"

  @testCaseId=id-003 @dataFile="centralCartRcV2.yaml"
  Scenario: Check Central Cart RC V2 Api for CONSTRUCT from WEBSITE
    Given Request the Central Cart Api for CONSTRUCT from WEBSITE
    #Check cart_identifier
    # cart_type: "ENQUIRY_CART",
    #"cart_journey_type": "REQUIREMENTS_CART_V2",
    #"store_front": "CONSTRUCT",
    #"device_type": "WEBSITE"

  @testCaseId=id-004 @dataFile="centralCartRcV2.yaml"
  Scenario: Check Central Cart RC V2 Api for CONSTRUCT from MOBILE
    Given Request the Central Cart Api for CONSTRUCT from MOBILE
    #Check cart_identifier
    # cart_type: "ENQUIRY_CART",
    #"cart_journey_type": "REQUIREMENTS_CART_V2",
    #"store_front": "CONSTRUCT",
    #"device_type": "MOBILE"


  @testCaseId=id-005 @dataFile="centralCartRcV2.yaml"
  Scenario: Check for Self pick-up for Central Cart
    Given Request Central Cart API and check delivery type in response
#    #delivery_type: "SELF_PICKUP"