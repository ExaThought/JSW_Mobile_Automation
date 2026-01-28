@cus
Feature: validate Buy-again

  Background:
    Given Go to session "ccp"
    And ccp login with "testautomation01@yopmail.com" and "Jsw@2025"
    And user close payment due pop up

  @sanity
  Scenario: Check and validate  navigation for  customer profile  page
    And user clicks on customer account menu
    Then user able to navigate  on  customer Profile


  @sanity
  Scenario: Validate Name is displayed in Profile details
    And user clicks on customer account menu
    And user able to navigate  on  customer Profile
    Then user should see Name field in Profile details

  @sanity
  Scenario: Validate Phone number is displayed in Profile details
    And user clicks on customer account menu
    And user able to navigate  on  customer Profile
    Then user should see Phone number field in Profile details

  @sanity
  Scenario: Validate Email address is displayed in Profile details
    And user clicks on customer account menu
    And user able to navigate  on  customer Profile
    Then user should see Email address field in Profile details
