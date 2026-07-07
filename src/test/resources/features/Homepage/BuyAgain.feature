@buym
Feature: validate Buy-again

  Background:
    Given Go to session "ccp"



  @sanity
  Scenario: Check and validate redirection for order page
    And ccp login with configured user
    And user clicks on My orders button
    Then verify order page title
    And Verify the order status




  Scenario: Check and validate redirection for Financial Document page
    And ccp login with configured user
    And user navigate to Financial Document
    Then verify the Financial document title
    Then verify All document drop down,current month, from, to date


  Scenario: Check and validate Financial Document type
    And ccp login with configured user
    And user navigate to Financial Document
    Then verify the Financial document title
    Then Verify the financial document type


  @sanity
  Scenario: Check and validate redirection for material tracking  page
    And ccp login with configured user
    And user navigate to Material tracking button
    Then verify Material tracking page details

  @sanity
  Scenario: Check and validate Shipment Report in material tracking  page
    And ccp login with configured user
    And user navigate to Material tracking button
    Then verify Material tracking page details
    Then user click on Shipment Report button
    Then User enters the date and clicked on download button


  @sanity
  Scenario: Check and validate Shipment Report table in material tracking  page
    And ccp login with configured user
    And user navigate to Material tracking button
    Then verify Material tracking page details
    Then User verifies the material tracking table


  @sanity
  Scenario: Check and validate add to cart for Buy again
    And ccp login with configured user
    And user navigate to buy again page
    And user click on checkbox for Add to cart
    When click cart icon
    Then verify "1" items added to cart from Buy-again


  @sanity
  Scenario: Check and validate add to cart with multiple items for Buy again
    And ccp login with configured user
    And user navigate to buy again page
    And user selects multiple items and click Add to cart
    When click cart icon
    Then verify "2" items added to cart from Buy-again



  Scenario: validate Buy Again for user with no order history.
    Given ccp login with "v_suhas.gr@jsw.in" and "Jsw@2026"
    And Buy Again should not be displayed


  Scenario: Validate the 'View Again' option in the Buy Again section on the Home Page
    And ccp login with configured user
    Then user click on view all in Buy Again section
    And User navigate to Bug Again page


  Scenario: Verify Category Image Visibility on Home Page
    And ccp login with configured user
    Then Verify the category image


  Scenario: Category Name Validation
    And ccp login with configured user
    Then Verify the category name

  Scenario: Category Navigation
    And ccp login with configured user
    Then verify user is redirects to corresponding PLP page from Browse by categories




  Scenario: Verify Top Brands Section
    And ccp login with configured user
    Then Verify the Brand image


  Scenario: Verify the Displayed Brand Name
    And ccp login with configured user
    Then Verify the brand name



  Scenario: Verify Brand PLP Page Redirection
    And ccp login with configured user
    Then verify user is redirects to corresponding brand PLP page.



  Scenario: Verify View All Brand CTA
    And ccp login with configured user
    Then user click on view all brand
    Then User navigate to All Brand

  Scenario: Verify popular category
    And ccp login with configured user
    Then Verify the popular category image


  Scenario: Verify Master Class for MSME YouTube Links
    And ccp login with configured user
    Then Verify youtube links for Master class for MSME section















