@buy
Feature: validate Buy-again

  Background:
    Given Go to session "ccp"
    And ccp login with configured user
    And user close payment due pop up


  @sanity
  Scenario: Check and validate payment due floater pop up after navigate to My Buy again
    And user navigate to buy again page
    Then  verify payment due floater pop up for Buy-again

  @sanity
  Scenario: Check and validate redirection for order page
    And user navigate to order button
    Then verify order page title

  @sanity
  Scenario: Check and validate redirection for ledger page
    And user navigate to ledger button
    Then verify ledger page title

  @sanity
  Scenario: Check and validate redirection for material tracking  page
    And user navigate to Material tracking button
    Then verify Material tracking page deatils


  @sanity
  Scenario: Check and validate add to cart for Buy again
    And user navigate to buy again page
    And user click on checkbox for Add to cart
    Then verify items added to cart from Buy-again




