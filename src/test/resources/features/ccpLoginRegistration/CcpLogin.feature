@login @regression @loginRegression
Feature: Check Login

  Background:
    Given Go to session "ccp"

  @sanity @emailLogin @emailLoginWithValidCredentials
  Scenario: Check Login with valid email ID and password
    And enter valid email "v_nirmal.kumar@jsw.in"
    And user click on continue button
    And enter valid password "Welcome@1234#"
    Then user should able to login



  @sanity @emailLogin @emailLoginWithInvalidCredentials
  Scenario: Check login with invalid email ID and valid password.
    And enter invalid email "sr@.js"
    Then user should not able to  proceed to login with error message
    Then continue button should be disabled

  @sanity @emailLogin @emailLoginWithInvalidCredentials
  Scenario: Check Login with valid email ID and Invalid password
    And enter valid email "v_nirmal.kumar@jsw.in"
    And user click on continue button
    And enter invalid password "Jsw@2029"
    Then user should not able to login with error message

    @loginAndLogout
  Scenario: Check CCP Login and Logout functionality
    And enter valid email "v_nirmal.kumar@jsw.in"
    And user click on continue button
    And enter valid password "Welcome@1234#"
    Then user should able to login
    Then User click on logout button

