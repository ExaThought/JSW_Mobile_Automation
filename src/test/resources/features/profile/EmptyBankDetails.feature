@regression @EmptyBankDetails

Feature: Verify empty bank details in profile

  Background:
    Given Go to session "ccp"
    And ccp login with "emailnoti@yopmail.com" and "Jsw@2025"
#    And user close payment due pop up

  Scenario: Check bank details design when user did not add bank details
    And user clicks on Account Drop Down
    And user clicks on Profile option
    And user clicks on Bank account details tab
    Then verify bank details title

  Scenario: Check add bank account CTA button
    And user clicks on Account Drop Down
    And user clicks on Profile option
    And user clicks on Bank account details tab
    And click on CTA button
    Then Verify the pop-up
