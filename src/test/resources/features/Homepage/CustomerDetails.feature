@customerdetails @regression
Feature: validate customer profile details

  Background:
    Given Go to session "ccp"
    And ccp login with "testautomation01@yopmail.com" and "Jsw@2025"
    And user close payment due pop up


  Scenario: Check and validate  navigation for  customer profile  page
    And user clicks on customer account menu
    Then user able to navigate  on  customer Profile



  Scenario: Validate Name is displayed in Profile details
    And user clicks on customer account menu
    And user able to navigate  on  customer Profile
    Then user should see Name field in Profile details


  Scenario: Validate Phone number is displayed in Profile details
    And user clicks on customer account menu
    And user able to navigate  on  customer Profile
    Then user should see Phone number field in Profile details


  Scenario: Validate Email address is displayed in Profile details
    And user clicks on customer account menu
    And user able to navigate  on  customer Profile
    Then user should see Email address field in Profile details
  @ts
  Scenario: Validate Company details section name and address
    And user clicks on customer account menu
    And user able to navigate  on  customer Profile
    Then user should see Company Name in Company details
    Then user should see Address in Company details

  @ts
  Scenario: Validate Company details section Gst and Pan
    And user clicks on customer account menu
    And user able to navigate  on  customer Profile
    Then user should see GSTIN in Company details
    Then user should see PAN in Company details
