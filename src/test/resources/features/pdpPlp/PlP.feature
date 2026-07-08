

@regression
Feature: Check PlP filter


  Background:
    Given Go to session "ccp"
    And ccp login with configured user


    Scenario: Verify the Category filter
      And User navigate to HomePage
      When move the mouse to Mild steel category
      And move to Hot rolled category
      And click on HR Sheet product
      And User selects the first category
      Then User verifies category


    Scenario: Verify the Grade filter
      And User navigate to HomePage
      When move the mouse to Mild steel category
      And move to Hot rolled category
      And click on HR Sheet product
      And User select the Grade
      Then User verifies Grade


  Scenario: Verify the Brand filter
    And User navigate to HomePage
    When move the mouse to Mild steel category
    And move to Hot rolled category
    And click on HR Sheet product
    And User select the Brand
    Then User verifies Brand


  Scenario: Verify the Brand filter
    And User navigate to HomePage
    When move the mouse to Mild steel category
    And move to Hot rolled category
    And click on HR Sheet product
    And User select the Sub grade
    Then User verifies Sub Grade


  Scenario: verify with multiple filter
    And User navigate to HomePage
    When move the mouse to Mild steel category
    And move to Hot rolled category
    And click on HR Sheet product
    And User select the Grade
    Then User verifies Grade
    And User select the Brand
    Then User verifies Brand
    And User select the Sub grade
    Then User verifies Sub Grade


  Scenario: Verify filter in Cement product
    And User navigate to HomePage
    When click on Cement product
    And User select the Brand
    Then User verifies Brand


  Scenario: Verify filters in TMT
    When click on TMT product
    And User select the Brand
    Then User verifies Brand


  Scenario:  Verify multiple filters in TMT
    When click on TMT product
    And User select the Brand
    Then User verifies Brand
    And User select the Sub grade
    Then User verifies Sub Grade




