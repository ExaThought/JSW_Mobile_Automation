

Feature: Validate All Tabs under My Account Drop Down

  Background:
    Given Go to session "ccp"



  Scenario: Validate Profile details under My Account details
    And ccp login with configured user
    And user clicks on Account Drop Down
    Then User select MyOrder tab under My Account
    Then verify order page title
    And Verify the order status

  Scenario:Validate Ledger under My Account details
    And ccp login with configured user
    And user clicks on Account Drop Down
    Then User select Ledger tab under My Account
    Then verify ledger page title
    Then verify Financial Doc, Payment instruction is displayed

  Scenario: Validate Ledger under My Account details if user is able to redirection to financial document page from ledger page
    And ccp login with configured user
    And user clicks on Account Drop Down
    Then User select Ledger tab under My Account
    Then verify ledger page title
    Then verify Financial Doc, Payment instruction is displayed
    And user click on Financial document button
    Then verify the Financial document title

  Scenario:Validate Tracking Material under My Account details
    And ccp login with configured user
    And user clicks on Account Drop Down
    Then User select Tracking Material tab under My Account
    Then verify Material tracking page details

  Scenario:Validate Tracking Material table under My Account details
    And ccp login with configured user
    And user clicks on Account Drop Down
    Then User select Tracking Material tab under My Account
    Then User verifies the material tracking table

  Scenario:Validate Financial Document under My Account details
    And ccp login with configured user
    And user clicks on Account Drop Down
    And User select  Financial Documents tab under My Account
    Then verify All document drop down,current month, from, to date


  Scenario: Validate My Requirement tab under My Account Drop Down
    And ccp login with configured user
    And user clicks on Account Drop Down
    Then User select  My Requirement tab under My Account
    Then User verifies the My Requirement page

  Scenario: Validate User Permission tab under My Account Drop Down
    And ccp login with configured user
    And user clicks on Account Drop Down
    Then User select  User and Permission tab under My Account
    Then User verifies  User and Permission page

  @base
  Scenario: Validate Secondary user creation under Finance & Accounting Manager department
    And ccp login with "kang@yopmail.com" and "Jsw@2027"
    And user clicks on Account Drop Down
    Then User select  User and Permission tab under My Account
    Then User verifies  User and Permission page
    And User clicks the Add User button
    And User Adds secondary user under Finance & Accounting Manager
    And User deletes the secondary user

  @base
  Scenario: Validate Secondary user creation under Order & materials management department
    And ccp login with "kang@yopmail.com" and "Jsw@2027"
    And user clicks on Account Drop Down
    Then User select  User and Permission tab under My Account
    Then User verifies  User and Permission page
    And User clicks the Add User button
    And User Adds secondary user under Order & materials management department
    And User deletes the secondary user
  @base
  Scenario: Validate Secondary user creation with existing mobile number
    And ccp login with "kang@yopmail.com" and "Jsw@2027"
    And user clicks on Account Drop Down
    Then User select  User and Permission tab under My Account
    Then User verifies  User and Permission page
    And User clicks the Add User button
    And User Adds secondary user under Order & materials management department with existing mobile number "9875876455"


  @base
  Scenario: Validate add departments of Secondary user
    And ccp login with "kang@yopmail.com" and "Jsw@2027"
    And user clicks on Account Drop Down
    Then User select  User and Permission tab under My Account
    Then User verifies  User and Permission page
    And User clicks the Add User button
    And User Adds secondary user under Finance & Accounting Manager
    And User add Operations Manager
    And User verifies Operations Manager departments

  @base
  Scenario: Validate add Permission of Secondary user
    And ccp login with "kang@yopmail.com" and "Jsw@2027"
    And user clicks on Account Drop Down
    Then User select  User and Permission tab under My Account
    Then User verifies  User and Permission page
    And User clicks the Add User button
    And User Adds secondary user under Finance & Accounting Manager
    And User add OrderAndMaterialDept permission
    And User verifies new permission
    And User deletes the secondary user



  @base
  Scenario: Validate secondary user name updation
    And ccp login with "kang@yopmail.com" and "Jsw@2027"
    And user clicks on Account Drop Down
    Then User select  User and Permission tab under My Account
    Then User verifies  User and Permission page
    And User clicks the Add User button
    And User Adds secondary user under Order & materials management department
    And User edits secondary user name "Demo"
    And User verifies the edit name
    And User deletes the secondary user












