Feature: Check SLC/SLO Flow

  @Test @testCaseId=id-001 @dataFile="SfSlcSlo.yaml"
  Scenario: Check Distributer cash and carry order
    When Request and create and cashAndCarry distributer cart
    And Open the CCP website and login "testautomation01@yopmail.com" and "Jsw@2025"
    And Open the cart and verify the cart details
    And Create an order and verify the order summery page
    And open the ERP and verify all the data
#    And Create an shipment and upload the invoice
#    Then Open the CCP and verify the shipment details