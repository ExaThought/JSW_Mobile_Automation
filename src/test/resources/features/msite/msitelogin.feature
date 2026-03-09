@mlogin
Feature: Check Login

  Background:
    Given user open the msite
    Then the home page should load successfully
    And the CCP logo should be visible
    When user clicks on login button


  Scenario: Check Login with valid email ID and password
    And user enters email "testautomation01@yopmail.com"
    When user submits login
    And user enters password "Jsw@2025"
    When user clicks on proceed to login
    Then user should be logged in successfully via email


  Scenario: Login with valid email and password
    And user enters email "testnewurl@yopmail.com"
    When user submits login
    And user enters password "Jsw@2025"
    When user clicks on proceed to login
    Then login error message should be displayed


  Scenario: Login with valid email and invalid password
    And user enters email "v_nirmal.kumar@jsw.in"
    When user submits login
    And user enters password "Jsw@2029"
    When user clicks on proceed to login
    Then login error message should be displayed

  Scenario: Login with invalid email
    And user enters email "sr@.js"
    Then invalid email error message should be displayed
    And continue button should be disabled on msite