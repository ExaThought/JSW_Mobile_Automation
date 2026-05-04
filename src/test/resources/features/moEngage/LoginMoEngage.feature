
Feature: check Mo-engage event for login

  Scenario: Capture Mo-engage event for login
    When User open the website
    And click login with Email
    And Enter Email-ID with configured user
    And Enter the Password with configured user
    Then Check Mo-Engage event is capture for login
