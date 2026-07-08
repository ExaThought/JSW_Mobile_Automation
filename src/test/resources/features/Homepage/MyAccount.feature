

Feature: Validate All Tabs under My Account Drop Down

  Background:
    Given Go to session "ccp"
    And ccp login with configured user


  Scenario: Validate Profile details under My Account details
    And user clicks on Account Drop Down
    Then User select MyOrder tab under My Account
    Then verify order page title
    And Verify the order status

  Scenario:Validate Ledger under My Account details
    And user clicks on Account Drop Down
    Then User select Ledger tab under My Account
    Then verify ledger page title
    Then verify Financial Doc, Payment instruction is displayed

  Scenario: Validate Ledger under My Account details if user is able to redirection to financial document page from ledger page
    And user clicks on Account Drop Down
    Then User select Ledger tab under My Account
    Then verify ledger page title
    Then verify Financial Doc, Payment instruction is displayed
    And user click on Financial document button
    Then verify the Financial document title

  Scenario:Validate Tracking Material under My Account details
    And user clicks on Account Drop Down
    Then User select Tracking Material tab under My Account
    Then verify Material tracking page details

  Scenario:Validate Tracking Material table under My Account details
    And user clicks on Account Drop Down
    Then User select Tracking Material tab under My Account
    Then User verifies the material tracking table

  Scenario:Validate Financial Document under My Account details
    And user clicks on Account Drop Down
    And User select  Financial Documents tab under My Account
    Then verify All document drop down,current month, from, to date


  Scenario: Validate My Requirement tab under My Account Drop Down
    And user clicks on Account Drop Down
    Then User select  My Requirement tab under My Account
    Then User verifies the My Requirement page







