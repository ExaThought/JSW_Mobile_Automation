@mlogout @msanity


Feature: Logout functionality

  Background:
    Given user open the msite
    Then the home page should load successfully
    And the CCP logo should be visible
    When user clicks on login button
    And user enters email with configured msite user
    When user submits login
    And user enters password with configured msite user
    When user clicks on proceed to login
    Then user should be logged in successfully via email

  Scenario: Successful logout from profile page
    When user clicks on profile icon
    When user clicks on logout button and redirects to home page
    Then the home page should load successfully
