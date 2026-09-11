@blogs @regression
Feature: Validate Blog Section

  Background:
    Given Go to session "ccp"

  Scenario: Validate logged user can navigate to blog
    Given user is on the application as a "Logged In" user
    And user clicks on Account Drop Down
    Then user should see Blogs section title in homepage
    And user should see the vlogs page title

  @productionSanity @prodSafe
  Scenario: Validate non logged user can navigate to blog
    Given user is on the application as a "Logged Out" user
    Then user should see Blogs section title in homepage
    And user should see the vlogs page title
