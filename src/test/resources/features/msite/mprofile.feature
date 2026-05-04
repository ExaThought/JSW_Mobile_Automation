@MobileProfile @profile
Feature: Mobile Profile Page Validation

  Background:
    Given user open the msite
    Then the home page should load successfully
    And the CCP logo should be visible
    And user clicks on login floating button on mobile
    And user enters email with configured msite user
    When user submits login
    And user enters password with configured msite user
    When user clicks on proceed to login
    When user clicks on profile icon in header
    And user clicks on profile details option
    And user closes payment reminder popup if visible


  @ProfilePageLoad @msanity
  Scenario: Verify profile page loads after clicking profile icon
    Then profile details page should load successfully

  @ProfilePersonalDetails @msanity @test
  Scenario: Verify personal details are displayed on profile page
    When user scrolls to personal details section
    Then personal details section should be visible
    And user name should be displayed
    And phone number should be displayed
    And email address should be displayed

  @ProfileCompanyDetails @msanity
  Scenario: Verify company details are displayed on profile page
    When user scrolls to company details section
    Then company details section should be visible
    And company name should be displayed
    And company address should be displayed
    And company gstin should be displayed
    And company pan should be displayed

  @ProfileBankingDetails @msanity
  Scenario: Verify banking details are displayed on profile page
    When user scrolls to banking details section
    Then banking details section should be visible
    And bank name should be displayed
    And beneficiary name should be displayed
    And account number should be displayed
    And ifsc code should be displayed

  @ProfileNavTabs @msanity
  Scenario: Verify profile navigation tabs are visible
    Then profile details tab should be active
    And addresses tab should be visible
    And bank account details tab should be visible