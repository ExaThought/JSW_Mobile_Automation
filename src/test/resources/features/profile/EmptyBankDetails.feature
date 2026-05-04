@regression @EmptyBankDetails

Feature: Verify empty bank details in profile

  Background:
    Given Go to session "ccp"
    And ccp login with configured user
    And user close payment due pop up

  Scenario: Check bank details design when user did not add bank details
    And user clicks on customer account menu
    And user able to navigate on customer Profile section
    And user clicks on Bank account details tab
    Then verify bank details title

  Scenario: Check add bank account CTA button
    And user clicks on customer account menu
    And user able to navigate on customer Profile section
    And user clicks on Bank account details tab
    And click on CTA button
    Then Verify the pop-up
