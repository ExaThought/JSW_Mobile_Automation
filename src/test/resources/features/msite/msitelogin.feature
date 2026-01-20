@login
Feature: Check Login


  @test
  Scenario: Check Login with valid email ID and password
    Given user open the msite
    Then the home page should load successfully
    And the CCP logo should be visible
    When  user waits for login button
    And enter valid email for msite"testnewurl@yopmail.com"
    And user click on continue button on msite
    And enter valid password for msite "Jsw@2025"
    Then user should able to login


  Scenario: Check Login with Invalid email ID and password
    And enter invalid email "testnewurl@yopmail.com"
    And user click on continue button
    And enter invalid password "Jsw@2029"
    Then user should not able to login with error message



  Scenario: Check login with invalid email ID and valid password.
    And enter invalid email "sr@.js"
    Then user should not able to  proceed to login with error message
    Then continue button should be disabled


  Scenario: Check Login with valid email ID and Invalid password
    And enter valid email "v_nirmal.kumar@jsw.in"
    And user click on continue button
    And enter invalid password "Jsw@2029"
    Then user should not able to login with error message
