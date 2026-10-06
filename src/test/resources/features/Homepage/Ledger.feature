# Deliberately NOT @prodSafe: the API-comparison scenarios call OMS and the ledger service with
# credentials global_prod.properties does not have, and ProdSafetyHooks refuses (fails) every
# untagged scenario on -Denv=prod. Add @prodSafe only together with prod API settings.
@ledger @regression
Feature: Validate ledger details

  Background:
    Given Go to session "ccp"
    And ccp login with configured user

  @sanity
  Scenario: Validate ledger page UI, navigation and actions
    And user clicks on Account Drop Down
    Then User select Ledger tab under My Account
    Then verify user is on ledger page
    Then verify ledger page title
    Then verify Financial Doc, Payment instruction is displayed
    Then verify Help & Support button is displayed and clickable
    And user click on Financial document button
    Then verify user is redirected to financial documents page
    Then verify the Financial document title
    And user navigates back to ledger page
    Then verify ledger page title
    Then verify Financial Doc, Payment instruction is displayed
    Then User click on payment instruction button
    Then Verify the payment instruction popup


  Scenario: Validate ledger page opens through direct url for logged in user
    And user opens ledger page through direct url
    Then verify user is on ledger page
    Then verify ledger page title
    Then verify Financial Doc, Payment instruction is displayed
    Then verify Help & Support button is displayed and clickable


  Scenario: Validate ledger page retains its details after page refresh
    And user clicks on Account Drop Down
    Then User select Ledger tab under My Account
    Then verify ledger page title
    And user refreshes the ledger page
    Then verify user is on ledger page
    Then verify ledger page title
    Then verify Financial Doc, Payment instruction is displayed

  Scenario Outline: Validate Payments due now entries on ledger page match the OMS due payments API
    And user clicks on Account Drop Down
    Then User select Ledger tab under My Account
    Then verify ledger page title
    Then verify "Payments due now" entries from API are displayed on ledger page for each transacting entity with dueInDays "<dueInDays>" and dueLater "<dueLater>"

    Examples:
      | dueInDays | dueLater |
      | 0         | false    |


  Scenario Outline: Validate Due later entries on ledger page match the OMS due payments API
    And user clicks on Account Drop Down
    Then User select Ledger tab under My Account
    Then verify ledger page title
    Then verify "Due later" entries from API are displayed on ledger page for each transacting entity with dueInDays "<dueInDays>" and dueLater "<dueLater>"

    Examples:
      | dueInDays | dueLater |
      | 3         | true     |


  Scenario Outline: Validate Usable ledger balance and transactions on ledger page match the ledger service API
    And user clicks on Account Drop Down
    Then User select Ledger tab under My Account
    Then verify ledger page title
    Then verify usable ledger balance and latest "<transactionCount>" ledger transactions from API are displayed on ledger page for each transacting entity

    Examples:
      | transactionCount |
      | 20               |


  Scenario: Validate Usable credit limit on ledger page matches the OMS credit info API
    When user fetches credit info from OMS for the logged in customer
    And user clicks on Account Drop Down
    Then User select Ledger tab under My Account
    Then verify ledger page title
    Then verify usable credit limit from API is displayed on ledger page
    Then verify customer visible credit lines from API are displayed on ledger page
