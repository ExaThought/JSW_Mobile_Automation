@login @regression @loginRegression @test01
Feature: Check Login

  @sanity @emailLogin @emailLoginWithValidCredentials @test01
  Scenario: Check Login with valid email ID and password
    Given user open the CCP portal
    And enter valid email "testnewurl@yopmail.com"
    And user click on continue button
    And enter valid password "Jsw@2025"
    Then user should able to login

   @sanity @emailLogin @emailLoginWithInvalidCredentials
  Scenario: Check Login with Invalid email ID and password
    Given user open the CCP portal
    And enter invalid email "testnewurl@yopmail.com"
     And user click on continue button
    And enter invalid password "Jsw@2029"
    Then user should not able to login with error message


  @sanity @emailLogin @emailLoginWithInvalidCredentials
  Scenario: Check login with invalid email ID and valid password.
    Given user open the CCP portal
    And enter invalid email "sr@.js"
    Then user should not able to  proceed to login with error message
    Then continue button should be disabled

  @sanity @emailLogin @emailLoginWithInvalidCredentials
  Scenario: Check Login with valid email ID and Invalid password
    Given user open the CCP portal
    And enter valid email "v_nirmal.kumar@jsw.in"
    And user click on continue button
    And enter invalid password "Jsw@2029"
    Then user should not able to login with error message
