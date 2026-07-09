@bankDetails @regression
Feature: Validate Bank Account Details Section

  Background:
    Given Go to session "ccp"
    And ccp login with "v_nirmal.kumar@jsw.in" and "Welcome@1234#"
#    And user close payment due pop up


  Scenario: Navigate to Bank account details tab
    And user clicks on Account Drop Down
    And user clicks on Profile option
    And user clicks on Bank account details tab


  Scenario: Validate GSTIN Name and Account holder name in Bank details
    And user clicks on Account Drop Down
    And user clicks on Profile option
    And user clicks on Bank account details tab
    Then user should see GSTIN Name in Bank details
    Then user should see Account holder name in Bank details


  Scenario: Validate Bank account number and IFSC code in Bank details
    And user clicks on Account Drop Down
    And user clicks on Profile option
    And user clicks on Bank account details tab
    Then user should see Bank account number in Bank details
    Then user should see IFSC code in Bank details


  Scenario: Validate Replace bank account navigation
    And user clicks on Account Drop Down
    And user clicks on Profile option
    And user clicks on Bank account details tab
    When user clicks on Replace bank account option
    Then user should see Enter bank account information page


