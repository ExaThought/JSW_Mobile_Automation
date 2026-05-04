@ledger @regression
Feature: Validate ledger details

  Background:
    Given Go to session "ccp"
    And ccp login with configured user
    And user close payment due pop up



  Scenario: Check and validate redirection for ledger page
    When user navigate to ledger button
    Then verify ledger page title



  Scenario: Validate payment due section in ledger page
    When user navigate to ledger button
    Then user should see payment due label
    And user should see payment due amount
    And user should see pay now button



  Scenario: Validate warning icon in payment due section
    When user navigate to ledger button
    Then user should see warning icon



  Scenario: Validate overdue invoice warning message
    When user navigate to ledger button
    Then user should see overdue invoice warning message



  Scenario: Validate transacting entity dropdown
    When user navigate to ledger button
    Then user should see transacting entity dropdown



  Scenario: Validate ledger navigation buttons
    When user navigate to ledger button
    Then user should see financial documents button
    And user should see payment instructions button
    And user should see help and support button


