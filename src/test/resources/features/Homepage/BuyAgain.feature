@buy @regression
Feature: validate Buy-again

  Background:
    When user open the CCP portal and login with valid credentials "testnewaccount01@yopmail.com" "Jsw@2025"


  @sanity
  Scenario: Check and validate payment due floater pop up after navigate to My Buy again
    When user close the payment due pop up
    And user navigate to buy again page
    Then  verify payment due floater pop up for Buy-again
    Then account logout


  @sanity
  Scenario: Check and validate redirection for order page
    When user close the payment due pop up
    And user navigate to order button
    Then verify order page title
    Then account logout


  @sanity
  Scenario: Check and validate redirection for ledger page
    When user close the payment due pop up
    And user navigate to ledger button
    Then verify ledger page title
    Then account logout

  @sanity
  Scenario: Check and validate redirection for material tracking  page
    When user close the payment due pop up
    And user navigate to Material tracking button
    Then verify Material tracking page deatils
    Then account logout


  @sanity
  Scenario: Check and validate add to cart for Buy again
    When user close the payment due pop up
    And user navigate to buy again page
    And user click on checkbox for Add to cart
    Then verify items added to cart from Buy-again
    Then account logout



