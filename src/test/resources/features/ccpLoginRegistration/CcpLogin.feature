@login @regression @loginRegression
Feature: Check Login

  @sanity @emailLogin @emailLoginWithValidCredentials
  Scenario: Check with valid credentials
    Given user open the CCP portal
    And enter valid email "testnewurl@yopmail.com"
    And enter valid password "Jsw@2025"
    Then user should able to login

   @sanity @emailLogin @emailLoginWithInvalidCredentials
  Scenario: Check for invalid credentials
    Given user open the CCP portal
    And enter invalid email "testnewurl@yopmail.com"
    And enter invalid password "Jsw@2029"
    Then user should not able to login with error message
