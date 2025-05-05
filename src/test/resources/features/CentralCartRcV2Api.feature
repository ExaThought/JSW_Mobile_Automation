
Feature: Check central cart RC v2 Api

  @testCaseId=id-001 @dataFile="centralCartRcV2.yaml" @Test
  Scenario: Check RC v2 API for MSME from WEBSITE
    Given Request the Api for MSME from WEBSITE
#    Then Delete the Cart
    #Check cart_identifier
    # cart_type": "ENQUIRY_CART",
    #"cart_journey_type": "REQUIREMENTS_CART_V2",
    #"store_front": "MSME",
    #"device_type": "WEBSITE"

  @testCaseId=id-002 @dataFile="centralCartRcV2.yaml"
  Scenario: Check RC V2 Api for MSME from MOBILE
    Given  Request the Api for MSME from MOBILE
    #Check cart_identifier
    # cart_type: "ENQUIRY_CART",
    #"cart_journey_type": "REQUIREMENTS_CART_V2",
    #"store_front": "MSME",
    #"device_type": "MOBILE"

  @testCaseId=id-003 @dataFile="centralCartRcV2.yaml"
  Scenario: Check RC V2 Api for CONSTRUCT from WEBSITE
    Given Request the Api for CONSTRUCT from WEBSITE
    #Check cart_identifier
    # cart_type: "ENQUIRY_CART",
    #"cart_journey_type": "REQUIREMENTS_CART_V2",
    #"store_front": "CONSTRUCT",
    #"device_type": "WEBSITE"

  @testCaseId=id-004 @dataFile="centralCartRcV2.yaml"
  Scenario: Check RC V2 Api for CONSTRUCT from MOBILE
    Given Request the Api for CONSTRUCT from MOBILE
    #Check cart_identifier
    # cart_type: "ENQUIRY_CART",
    #"cart_journey_type": "REQUIREMENTS_CART_V2",
    #"store_front": "CONSTRUCT",
    #"device_type": "MOBILE"

  @testCaseId=id-005 @dataFile="centralCartRcV2.yaml"
  Scenario: Check for Self pick-up
    Given Request API and check delivery type in response
    #delivery_type: "SELF_PICKUP"