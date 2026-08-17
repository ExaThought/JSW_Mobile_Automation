@regression @tokenCheckGuestUser
  Feature: Check Token for Guest user

    Background:
      Given Go to session "ccp"

    Scenario: Verify token in home page
      When user set expired token in local storage
      And user refresh the page
      Then verify the user is navigate to Guest home page

    Scenario: Verify Expired token in HR Sheet PLP page
      When move the mouse to Mild steel category and to sub category Hot rolled
      And click on HR Sheet product
      When user set expired token in local storage
      And user refresh the page
      Then verify the user is navigate to Guest home page

    Scenario: Verify Expired token in HR Coil PLP page
      When move the mouse to Mild steel category and to sub category Hot rolled
      And click on HR Coil product
      When user set expired token in local storage
      And user refresh the page
      Then verify the user is navigate to Guest home page

    Scenario: Verify Expired token in CR Coil PLP page
      When move the mouse to Mild steel category and to sub category Cold rolled
      And click on "CR Coil" product
      When user set expired token in local storage
      And user refresh the page
      Then verify the user is navigate to Guest home page

    Scenario: Verify Expired token in CR Sheet PLP page
      When move the mouse to Mild steel category and to sub category Cold rolled
      And click on "CR Sheet" product
      When user set expired token in local storage
      And user refresh the page
      Then verify the user is navigate to Guest home page

    Scenario: verify Expired token in cement PLP page
      When click on Cement product
      When user set expired token in local storage
      And user refresh the page
      Then verify the user is navigate to Guest home page

    Scenario: Verify Expired token in Coated steel PLP page
      When move the mouse to Mild steel category and to sub category Coated steel
      And click on Galvanized sheet product
      When user set expired token in local storage
      And user refresh the page
      Then verify the user is navigate to Guest home page

    Scenario: Verify Expired token in Wire PLP page
      When move the mouse to "Mild steel" category
      And click on wire product
      When user set expired token in local storage
      And user refresh the page
      Then verify the user is navigate to Guest home page

    Scenario: Verify Expired token in TMT PLP page
      When click on TMT product
      When user set expired token in local storage
      And user refresh the page
      Then verify the user is navigate to Guest home page

    Scenario: Verify Expired token in HR Sheet PDP page
      When move the mouse to Mild steel category and to sub category Hot rolled
      And click on HR Sheet product
      And click on first product
      When user set expired token in local storage
      And user refresh the page
      Then verify the user is navigate to Guest home page

    Scenario: Verify Expired token in HR Coil PDP page
      When move the mouse to Mild steel category and to sub category Hot rolled
      And click on HR Coil product
      And click on first product
      When user set expired token in local storage
      And user refresh the page
      Then verify the user is navigate to Guest home page

    Scenario: Verify Expired token in CR Coil PDP page
      When move the mouse to Mild steel category and to sub category Cold rolled
      And click on "CR Coil" product
      And click on first product
      When user set expired token in local storage
      And user refresh the page
      Then verify the user is navigate to Guest home page

    Scenario: Verify Expired token in CR Sheet PDP page
      When move the mouse to Mild steel category and to sub category Cold rolled
      And click on "CR Sheet" product
      And click on first product
      When user set expired token in local storage
      And user refresh the page
      Then verify the user is navigate to Guest home page

    Scenario: verify Expired token in cement PDP page
      When click on Cement product
      And click on first product
      When user set expired token in local storage
      And user refresh the page
      Then verify the user is navigate to Guest home page

    Scenario: Verify Expired token in Coated steel PDP page
      When move the mouse to Mild steel category and to sub category Coated steel
      And click on Galvanized sheet product
      And click on first product
      When user set expired token in local storage
      And user refresh the page
      Then verify the user is navigate to Guest home page

    Scenario: Verify Expired token in Wire PDP page
      When move the mouse to "Mild steel" category
      And click on wire product
      And click on first product
      When user set expired token in local storage
      And user refresh the page
      Then verify the user is navigate to Guest home page

    Scenario: Verify Expired token in TMT PDP page
      When click on TMT product
     And click on first product
      When user set expired token in local storage
      And user refresh the page
      Then verify the user is navigate to Guest home page

    Scenario: Verify Expired token in search result page
      When user search for "tmt"
      When user set expired token in local storage
      And user refresh the page
      Then verify the user is navigate to Guest home page