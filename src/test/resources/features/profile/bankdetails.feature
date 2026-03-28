@bankdetails
Feature: Validate Bank Account Details Section

  Background:
    Given Go to session "ccp"
    And ccp login with "testmysore@yopmail.com" and "Jsw@2026"
    And user close payment due pop up


  Scenario: Navigate to Bank account details tab
    And user clicks on customer account menu bar
    And user able to navigate on customer Profile section
    And user clicks on Bank account details tab

  Scenario: Validate GSTIN Name and Account holder name in Bank details
    And user clicks on customer account menu bar
    And user able to navigate on customer Profile section
    And user clicks on Bank account details tab
    Then user should see GSTIN Name in Bank details
    Then user should see Account holder name in Bank details


  Scenario: Validate Bank account number and IFSC code in Bank details
    And user clicks on customer account menu bar
    And user able to navigate on customer Profile section
    And user clicks on Bank account details tab
    Then user should see Bank account number in Bank details
    Then user should see IFSC code in Bank details


  Scenario: Validate Replace bank account navigation
    And user clicks on customer account menu
    And user able to navigate on customer Profile section
    And user clicks on Bank account details tab
    When user clicks on Replace bank account option
    Then user should see Enter bank account information page

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

