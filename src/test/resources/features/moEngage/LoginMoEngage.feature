@regression
Feature: check Mo-engage event for login

  @test
  Scenario: Capture Mo-engage event for login
    When User open the website
    And click login with Email
    And Enter Email-ID "testautomation01@yopmail.com"
    And Enter the Password "Jsw@2025"
    Then Check Mo-Engage event is capture for login
