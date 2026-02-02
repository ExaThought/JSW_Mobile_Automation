
Feature: Check Profile page

  Background:
    Given Go to session "ccp"
    And ccp login with "testautomation01@yopmail.com" and "Jsw@2025"
    And user close payment due pop up

  Scenario: Verify user can access profile page
    When user click profile
    Then user should re-direct to profile page