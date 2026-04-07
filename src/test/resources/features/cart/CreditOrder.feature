@creditOrder
Feature: Check CCP credit order details

  Background:
    Given Go to session "ccp"
    And ccp login with "testpre_prod12as@gmail.com" and "Jsw@2026"
    And user close payment due pop up

  Scenario: Verify  navigation of  order summary page validation
    And user clicks on My orders button
    And user navigate to Delivered orders page
    And user clicks on View details button
    Then verify order summary page is displayed

  Scenario: Validation for order summary page details
    And user clicks on My orders button
    And user navigate to Delivered orders page
    And user clicks on View details button
    Then verify order delivered details are displayed

  Scenario: Validation for  view order documents page details
    And user clicks on My orders button
    And user navigate to Delivered orders page
    And user clicks on View details button
    And user clicks on View order documents button



