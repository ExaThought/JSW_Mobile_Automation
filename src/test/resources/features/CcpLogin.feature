@login @regression
Feature: Check Login

  @sanity
  Scenario: Check with valid credentials
    Given user open the CCP portal
    And enter valid email "testnewurl@yopmail.com"
    And enter valid password "Jsw@2025"
    Then user should able to login