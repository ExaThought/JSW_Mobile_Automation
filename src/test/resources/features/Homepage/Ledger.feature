@ledger @regression
Feature: Validate ledger details

  Background:
    Given Go to session "ccp"
    And ccp login with configured user




  @sanity
  Scenario: Check and validate redirection for ledger page
    And user navigate to ledger button
    Then verify ledger page title
    Then verify Financial Doc, Payment instruction is displayed


  Scenario: Check and validate redirection to financial document page from ledger page
    And user navigate to ledger button
    Then verify ledger page title
    Then verify Financial Doc, Payment instruction is displayed
    And user click on Financial document button
    Then verify the Financial document title


  Scenario: Check and validate payment instruction in ledger page
    And user navigate to ledger button
    Then verify ledger page title
    Then verify Financial Doc, Payment instruction is displayed
    Then User click on payment instruction button
    Then Verify the payment instruction popup

