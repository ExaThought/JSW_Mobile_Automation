@regression @PDPValidation
  Feature: Check PDP page

    Background:
      Given Go to session "ccp"
      And ccp login with "testautomation01@yopmail.com" and "Jsw@2025"
      And user close payment due pop up

    Scenario: Verify reset in pdp page.
      When move the mouse to Mild steel category
      And move to Hot rolled category
      And click on HR Sheet product
      And open HR Sheet plp "1" product
      And select thickness "2.5" width "1500" length "2500" quantity "50" for HR Sheet
      And click one reset button in pdp page
      Then check Add to cart is disabled

    Scenario: Verify the Cascading in PDP  page.
      When move the mouse to Mild steel category
      And move to Hot rolled category
      And click on HR Sheet product
      And open HR Sheet plp "1" product
      And select thickness "2.5"
      Then check Add to cart is disabled
      And select width "1500"
      Then check Add to cart is disabled
      And select length "2500"
      Then check Add to cart is disabled

    Scenario: Verify user able to enter quantity more than 9999 in PDP page.
      When move the mouse to Mild steel category
      And move to Hot rolled category
      And click on HR Coil product
      And open HR Coil plp "2" product
      And select thickness "4" width "1250" quantity "10000" for HR Coil
      And add to cart
      And click view cart
      Then check quantity enter below 10000 in PDP page
      And Delete the line item from cart

    Scenario: Brand name should not be empty in PDP page.
      When move the mouse to Mild steel category
      And move to Hot rolled category
      And click on HR Sheet product
      And open HR Sheet plp "1" product
      Then check brand name is not empty in PDP page
