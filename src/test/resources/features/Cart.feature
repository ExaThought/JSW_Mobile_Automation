@regression
Feature: Check Add-To-Cart and PDP

  Background:
    When user open the portal and login with valid credentials "testnewurl@yopmail.com" "Jsw@2025"

  Scenario: check PDP validation and Add-to-cart
      When user open HR coil pdp
      And select all the attribute and add to cart
      Then check user able to add the product to cart page
      And user will add cement product to cart
      And user will add Stainless Steel product to cart
      Then check user able to delete line item from cart
      And user checkout the RCV2
      Then verify the opportunity in SF "bhushan.sawant@jsw.in.prod.uat" "Pass@123"




