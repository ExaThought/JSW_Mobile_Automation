@address @regression
Feature: validate Address

  Background:
    Given Go to session "ccp"
    And ccp login with configured user


  @sanity
  Scenario: Check and validate  navigation for  Address page
    And user clicks on Account Drop Down
    And user clicks on Profile option
    And user clicks on Addresses tab
    Then customer navigate company name jsw test address


  @sanity @add
  Scenario: Check and validate  add Address for customer
    And user clicks on Account Drop Down
    And user clicks on Profile option
    And user clicks on Addresses tab
    And customer clicks on Add address button
    And customer enter company name
    And customer enter address line one "varanasi Uttar pradesh"
    And customer enter pin code "221208"
    And customer enter city "varanasi"
    Then customer clicks on Save changes button
    Then validate customer added address

  @sanity
  Scenario: Check and validate  edit  Address for customer
    And user clicks on Account Drop Down
    And user clicks on Profile option
    And user clicks on Addresses tab
    And customer clicks on Add address button
    And customer enter company name
    And customer enter address line one "varanasi Uttar pradesh"
    And customer enter pin code "221208"
    And customer enter city "varanasi"
    Then customer clicks on Save changes button
    Then validate customer added address
    Then customer clicks edit for jsw test address
    Then customer clicks on Save changes button
    Then validate customer added address

  @sanity
  Scenario: Check and validate  cancel Delete  Address for customer
    And user clicks on Account Drop Down
    And user clicks on Profile option
    And user clicks on Addresses tab
    And customer clicks on Add address button
    And customer enter company name
    And customer enter address line one "varanasi Uttar pradesh"
    And customer enter pin code "221208"
    And customer enter city "varanasi"
    Then customer clicks on Save changes button
    Then customer clicks delete for jsw test address
    Then customer clicks cancel delete for jsw test address
    Then validate customer added address

  @sanity
  Scenario: Check and validate  Delete  Address for customer
    And user clicks on Account Drop Down
    And user clicks on Profile option
    And user clicks on Addresses tab
    And customer clicks on Add address button
    And customer enter company name
    And customer enter address line one "varanasi Uttar pradesh"
    And customer enter pin code "221208"
    And customer enter city "varanasi"
    Then customer clicks on Save changes button
    Then validate customer added address
    Then customer navigate company name jsw test address
    Then customer clicks delete for jsw test address
    Then customer clicks proceed button for delete jsw test address
    Then validate customer added address is not visible

  @sanity
  Scenario: Check and validate add Address with blank company name
    And user clicks on Account Drop Down
    And user clicks on Profile option
    And user clicks on Addresses tab
    And customer clicks on Add address button
    And customer enter company name blank
    Then customer should not be able to save address


  @sanity
  Scenario: Validate Save button disabled when Address line 1 is blank
    And user clicks on Account Drop Down
    And user clicks on Profile option
    And user clicks on Addresses tab
    And customer clicks on Add address button
    And customer enter company name
    And customer enter address line one ""
    And customer enter pin code "560001"
    And customer enter city "Bangalore"
    Then customer should not be able to save address


  @sanity
  Scenario: Validate Save button disabled when Pincode is blank
    And user clicks on Account Drop Down
    And user clicks on Profile option
    And user clicks on Addresses tab
    And customer clicks on Add address button
    And customer enter company name
    And customer enter address line one "Test address"
    And customer enter pin code ""
    And customer enter city "Bangalore"
    Then customer should not be able to save address


  @sanity
  Scenario: Validate Save button disabled when City is blank
    And user clicks on Account Drop Down
    And user clicks on Profile option
    And user clicks on Addresses tab
    And customer clicks on Add address button
    And customer enter company name
    And customer enter address line one "Test address"
    And customer enter pin code "560001"
    And customer enter city ""
    Then customer should not be able to save address



    
  Scenario: verify shipping address should not have set as default option
    And user clicks on Account Drop Down
    And user clicks on Profile option
    And user clicks on Addresses tab
    Then validate set as default option should not be visible for shipping address







