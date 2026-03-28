@logout @msanity


Feature: Logout functionality

  As a logged-in user
  I want to logout from the application

  Background:
    Given user open the msite
    Then the home page should load successfully
    And the CCP logo should be visible
    When user clicks on login button
    And user enters email "testautomation01@yopmail.com"
    When user submits login
    And user enters password "Jsw@2025"
    When user clicks on proceed to login
    Then user should be logged in successfully via email

    @test01
  Scenario: Successful logout from profile page
    When user clicks on profile icon
    When user clicks on logout button and redirects to home page
    Then the home page should load successfully
