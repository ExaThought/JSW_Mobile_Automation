@paymentDue @regression
Feature: Check payment due pop up and payment due floater pop up

  @sanity @emailLogin @emailLoginWithValidCredentials @test01
  Scenario: Check and validate payment due pop up after login
    Given user open the CCP Webportal
    And enter user email "testnewurl@yopmail.com"
    And user click on continue
    And user enter valid password "Jsw@2025"
    Then user successfully login to ccp portal
    Then user verify payment due pop up

  @sanity @emailLogin @emailLoginWithValidCredentials
  Scenario: Check and validate payment due pop up after login
    Given user open the CCP Webportal
    And enter user email "testnewurl@yopmail.com"
    And user click on continue
    And user enter valid password "Jsw@2025"
    Then user successfully login to ccp portal
    Then user verify payment due pop up
    Then user clicks on view details and pay button
    Then user is navigated to payment due page
    And user validates payment due details

  @sanity @emailLogin @emailLoginWithValidCredentials
  Scenario: Check and validate payment due floater pop up after navigate to My requirements
    Given user open the CCP Webportal
    And enter user email "testnewurl@yopmail.com"
    And user click on continue
    And user enter valid password "Jsw@2025"
    Then user successfully login to ccp portal
    And user close payment due pop up
    And user navigate to my reqirement
    Then  verify payment due floater pop up

  @sanity @emailLogin @emailLoginWithValidCredentials
  Scenario: Check and validate payment due floater pop up after navigate to My Buy again
    Given user open the CCP Webportal
    And enter user email "testnewurl@yopmail.com"
    And user click on continue
    And user enter valid password "Jsw@2025"
    Then user successfully login to ccp portal
    And user close payment due pop up
    And user navigate to buy again
    Then  verify payment due floater pop up

  @sanity @emailLogin @emailLoginWithValidCredentials
  Scenario: Check and validate payment due floater pop up after navigate to My Buy again
    Given user open the CCP Webportal
    And enter user email "testnewurl@yopmail.com"
    And user click on continue
    And user enter valid password "Jsw@2025"
    Then user successfully login to ccp portal
    And user close payment due pop up
    And user navigate to cart details
    Then  verify payment due floater pop up

  @sanity @emailLogin @emailLoginWithValidCredentials
  Scenario: Verify no payment due pop up on My Orders page
    Given user open the CCP Webportal
    And enter user email "testnewurl@yopmail.com"
    And user click on continue
    And user enter valid password "Jsw@2025"
    Then user successfully login to ccp portal
    And user close payment due pop up
    And user navigate to my orders
    Then verify no payment due pop up on my orders page


