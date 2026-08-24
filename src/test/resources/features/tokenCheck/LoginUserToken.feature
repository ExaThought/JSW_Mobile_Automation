@regression @tokenCheckLogin
Feature: Check Token for all the page

  Background:
    Given Go to session "ccp"
    And ccp login with configured user
    And user close payment due pop up

  #  Scenario: Verify token in home page
  #    When user set expired token in local storage
  #    And user refresh the page
  #    Then verify the user is logged out
  #    And user navigate to login page and again login with configured user
  #    Then verify user is able to login successfully

  #  Scenario: Verify Expired token in cart page
  #    When user open the cart page
  #    When user set expired token in local storage
  #    And user refresh the page
  #    Then verify the user is logged out
  #    And user navigate to login page and again login with configured user
  #    Then verify user is able to login successfully

  #  Scenario: Verify Expired token in my requirement page
  #    When user open the my requirement page
  #    When user set expired token in local storage
  #    And user refresh the page
  #    Then verify the user is logged out
  #    And user navigate to login page and again login with configured user
  #    Then verify user is able to login successfully

  #  Scenario: Verify Expired token in my order page
  #    When user open the my order page
  #    When user set expired token in local storage
  #    And user refresh the page
  #    Then verify the user is logged out
  #    And user navigate to login page and again login with configured user
  #    Then verify user is able to login successfully

  #  Scenario: Verify Expired token in order summary page
  #    When user open the order summary page
  #    When user set expired token in local storage
  #    And user refresh the page
  #    Then verify the user is logged out
  #    And user navigate to login page and again login with configured user
  #    Then verify user is able to login successfully

  #  Scenario: Verify Expired token in profile page
  #    And user clicks on customer account menu
  #    And user able to navigate  on  customer Profile
  #    When user set expired token in local storage
  #    And user refresh the page
  #    Then verify the user is logged out
  #    And user navigate to login page and again login with configured user
  #    Then verify user is able to login successfully

  #  Scenario: Verify Expired token in address page
  #    And user clicks on Testacco user menu
  #    And user clicks on Profile option
  #    And user clicks on Addresses tab
  #    When user set expired token in local storage
  #    And user refresh the page
  #    Then verify the user is logged out
  #    And user navigate to login page and again login with configured user
  #    Then verify user is able to login successfully

# This scenario is and will be commented until the OTP handling is solved
#   Scenario:Verify Expired token in bank details page
#     And user clicks on customer account menu
#     And user able to navigate on customer Profile section
#     And user clicks on Bank account details tab
#     When user set expired token in local storage
#     And user refresh the page
#     Then verify the user is logged out
#     And user navigate to login page and again login with configured user
#     Then verify user is able to login successfully

  #  @tokenCheck1
  #  Scenario: Verify Expired token in HR Sheet PLP page
  #    When move the mouse to Mild steel category and to sub category Hot rolled
  #    And click on HR Sheet product
  #    When user set expired token in local storage
  #    And user refresh the page
  #    Then verify the user is logged out
  #    And user navigate to login page and again login with configured user
  #    Then verify user is able to login successfully

  #  Scenario: Verify Expired token in HR Coil PLP page
  #    When move the mouse to Mild steel category and to sub category Hot rolled
  #    And click on HR Coil product
  #    When user set expired token in local storage
  #    And user refresh the page
  #    Then verify the user is logged out
  #    And user navigate to login page and again login with configured user
  #    Then verify user is able to login successfully

  #  Scenario: Verify Expired token in CR Coil PLP page
  #    When move the mouse to Mild steel category and to sub category Cold rolled
  #    And click on CR Coil product
  #    When user set expired token in local storage
  #    And user refresh the page
  #    Then verify the user is logged out
  #    And user navigate to login page and again login with configured user
  #    Then verify user is able to login successfully

  #  Scenario: Verify Expired token in CR Sheet PLP page
  #    When move the mouse to Mild steel category and to sub category Cold rolled
  #    And click on CR Sheet product
  #    When user set expired token in local storage
  #    And user refresh the page
  #    Then verify the user is logged out
  #    And user navigate to login page and again login with configured user
  #    Then verify user is able to login successfully

  #  Scenario: verify Expired token in cement PLP page
  #    When click on Cement product
  #    When user set expired token in local storage
  #    And user refresh the page
  #    Then verify the user is logged out
  #    And user navigate to login page and again login with configured user
  #    Then verify user is able to login successfully

  #  Scenario: Verify Expired token in Coated steel PLP page
  #    When move the mouse to "Mild steel" category
  #    And click to Coated steel category
  #    When user set expired token in local storage
  #    And user refresh the page
  #    Then verify the user is logged out
  #    And user navigate to login page and again login with configured user
  #    Then verify user is able to login successfully

  # Scenario: Verify Expired token in Wire PLP page
  #   When move the mouse to "Mild steel" category
  #   And click on wire product
  #   When user set expired token in local storage
  #   And user refresh the page
  #   Then verify the user is logged out
  #   And user navigate to login page and again login with configured user
  #   Then verify user is able to login successfully

  #  Scenario: Verify Expired token in TMT PLP page
  #    When click on TMT product
  #    When user set expired token in local storage
  #    And user refresh the page
  #    Then verify the user is logged out
  #    And user navigate to login page and again login with configured user
  #    Then verify user is able to login successfully

  #  Scenario: Verify Expired token in HR Sheet PDP page
  #    When move the mouse to Mild steel category and to sub category Hot rolled
  #    And click on HR Sheet product
  #    And click on first product
  #    When user set expired token in local storage
  #    And user refresh the page
  #    Then verify the user is logged out
  #    And user navigate to login page and again login with configured user
  #    Then verify user is able to login successfully

  #  Scenario: Verify Expired token in HR Coil PDP page
  #    When move the mouse to Mild steel category and to sub category Hot rolled
  #    And click on HR Coil product
  #    And click on first product
  #    When user set expired token in local storage
  #    And user refresh the page
  #    Then verify the user is logged out
  #    And user navigate to login page and again login with configured user
  #    Then verify user is able to login successfully

  #  Scenario: Verify Expired token in CR Coil PDP page
  #    When move the mouse to Mild steel category and to sub category Cold rolled
  #    And click on CR Coil product
  #    And click on first product
  #    When user set expired token in local storage
  #    And user refresh the page
  #    Then verify the user is logged out
  #    And user navigate to login page and again login with configured user
  #    Then verify user is able to login successfully

  #  Scenario: Verify Expired token in CR Sheet PDP page
  #    When move the mouse to Mild steel category and to sub category Cold rolled
  #    And click on CR Sheet product
  #    And click on first product
  #    When user set expired token in local storage
  #    And user refresh the page
  #    Then verify the user is logged out
  #    And user navigate to login page and again login with configured user
  #    Then verify user is able to login successfully

  #  Scenario: verify Expired token in cement PDP page
  #    When click on Cement product
  #    And click on first product
  #    When user set expired token in local storage
  #    And user refresh the page
  #    Then verify the user is logged out
  #    And user navigate to login page and again login with configured user
  #    Then verify user is able to login successfully

   Scenario: Verify Expired token in Coated steel PDP page
     When move the mouse to Mild steel category and to sub category Coated steel
     And click on Galvanized sheet product
     And click on first product
     When user set expired token in local storage
     And user refresh the page
     Then verify the user is logged out
     And user navigate to login page and again login with configured user
     Then verify user is able to login successfully

  #  Scenario: Verify Expired token in Wire PDP page
  #    When move the mouse to "Mild steel" category
  #    And click on wire product
  #    And click on first product
  #    When user set expired token in local storage
  #    And user refresh the page
  #    Then verify the user is logged out
  #    And user navigate to login page and again login with configured user
  #    Then verify user is able to login successfully

  #  Scenario: Verify Expired token in TMT PDP page
  #    When click on TMT product
  #    And click on first product
  #    When user set expired token in local storage
  #    And user refresh the page
  #    Then verify the user is logged out
  #    And user navigate to login page and again login with configured user
  #    Then verify user is able to login successfully

  #  Scenario: Verify Expired token in search result page
  #    When user search for "tmt"
  #    When user set expired token in local storage
  #    And user refresh the page
  #    Then verify the user is logged out
  #    And user navigate to login page and again login with configured user
  #    Then verify user is able to login successfully

  #  Scenario: Verify Expired token in Ledger page
  #    When user open the ledger page
  #    When user set expired token in local storage
  #    And user refresh the page
  #    Then verify the user is logged out
  #    And user navigate to login page and again login with configured user
  #    Then verify user is able to login successfully

  #  Scenario: verify Expired token in Track Material  page
  #    When user open the Track Material page
  #    When user set expired token in local storage
  #    And user refresh the page
  #    Then verify the user is logged out
  #    And user navigate to login page and again login with configured user
  #    Then verify user is able to login successfully