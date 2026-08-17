@blog 
Feature: Validate Blog Section

  Background:
    Given Go to session "ccp"
    And ccp login with configured user

  Scenario: Validate logged user can navigate to blog
    And user clicks on Account Drop Down
    Then user should see Blogs section title in homepage
    Then user should see the vlogs page title


  Scenario: Validate non logged user can navigate to blog
    And user clicks on Account Drop Down
    Then user should see Blogs section title in homepage
    Then user should see the vlogs page title