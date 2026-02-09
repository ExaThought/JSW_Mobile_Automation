@ccpActiveOrder
Feature: Check CCP order diffrent status

  @testCaseId=id-001 @dataFile="ccpErpSync.yaml"
  Scenario: Verify Order Confirmation from ccp and copy order number
    When create cash and carry cart from SF for active order
        Given Go to session "ccp"
    And ccp login with "jayadevgowda10@yopmail.com" and "Jsw@2025"
    Then user close payment due pop up
    And open the cart page and create an order


  Scenario: Verify navigation of active order details page
    Given Go to session "ccp"
    And ccp login with "jayadevgowda10@yopmail.com" and "Jsw@2025"
    And user navigate to order button
    And user clicks on Active order tab
    Then verify payment details persent on page after navigation


  Scenario: Verify Order Confirmation from ccp and copy order number
    Given Go to session "ccp"
    And ccp login with "jayadevgowda10@yopmail.com" and "Jsw@2025"
    And user navigate to order button
    And user clicks on Active order tab
    Then verify created order number is present in active orders


  Scenario: Verify navigation for delivered page for order
    Given Go to session "ccp"
    And ccp login with "jayadevgowda10@yopmail.com" and "Jsw@2025"
    And user navigate to order button
    And user navigates to Delivered orders page
    Then verify payment details persent on page after navigation


  Scenario: Verify navigation for cancelled page for order
    Given Go to session "ccp"
    And ccp login with "jayadevgowda10@yopmail.com" and "Jsw@2025"
    And user navigate to order button
    And user navigates to Cancelled orders page
    Then verify payment details persent on page after navigation

  @tes
  Scenario: Verify navigation for all order page
    Given Go to session "ccp"
    And ccp login with "jayadevgowda10@yopmail.com" and "Jsw@2025"
    And user navigate to order button
    And user clicks on All orders tab
    Then verify payment details persent on page after navigation