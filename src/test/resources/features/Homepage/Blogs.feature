@blog
Feature: Validate Blog and Youtube video sections


  Background:
    Given Go to session "ccp"
    And ccp login with configured user
    And user close payment due pop up

  Scenario: Validate logged user can navigate to blog
    Then user should see Blogs section title in homepage
    And user navigate to blog
    Then user should see the vlogs page title

  Scenario: Validate logged user can open youtube video
    Then user should see youtube video icon
    And user click on youtube video icon
    Then user should be redirected to youtube page

  # Non logged user scenarios
  Scenario: Validate non logged user can navigate to blog
    Given Go to session "ccp"
    Then user should see Blogs section title in homepage
    And user navigate to blog
    Then user should see the vlogs page title

  Scenario: Validate non logged user can open youtube video
    Given Go to session "ccp"
    Then user should see youtube video icon
    And user click on youtube video icon
    Then user should be redirected to youtube page




