@ccpActiveOrder
Feature: Check CCP order creation from sf to ccp




  Scenario: Verify navigation of active order details page
    Given Go to session "ccp"
    And ccp login with configured user
    And user clicks on My orders button
    And user clicks on Active order tab
    Then verify payment details present on page after navigation


  Scenario: Verify navigation for delivered page for order
    Given Go to session "ccp"
    And ccp login with configured user
    And user clicks on My orders button
    And user navigates to Delivered orders page
    Then verify payment details present on page after navigation


  Scenario: Verify navigation for cancelled page for order
    Given Go to session "ccp"
    And ccp login with configured user
    And user clicks on My orders button
    And user navigates to Cancelled orders page
    Then verify payment details present on page after navigation
    Then check order cancelled tag is present for cancelled order


  Scenario: Verify navigation for all order page
    Given Go to session "ccp"
    And ccp login with configured user
    And user clicks on My orders button
    And user clicks on All orders tab
    Then verify payment details present on page after navigation