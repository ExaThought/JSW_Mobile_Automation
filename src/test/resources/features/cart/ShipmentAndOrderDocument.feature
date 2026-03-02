@shipment @regression
Feature: Check invoice document and shipment page details

  Background:
    Given Go to session "ccp"
    And ccp login with "v_nirmal.kumar@jsw.in" and "Welcome@1234#"
    And user close payment due pop up


  Scenario: Validation for invoice  documents for  deliverd  order
    And user clicks on My orders button
    And user navigate to Delivered orders page
    And user clicks on View details button
    And user clicks on View order documents button
    Then verify invoice for shipment order is displayed
