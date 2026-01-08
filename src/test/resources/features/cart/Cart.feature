Feature: Check Add-To-Cart

  Background:
    Given Go to session "ccp"
    And ccp login with "testautomation01@yopmail.com" and "Jsw@2025"
    And user close payment due pop up

  @addToCart @checkout
  Scenario: check PDP validation and Add-to-cart
      When user open HR coil pdp
      And select all the attribute and add to cart
      Then check user able to add the product to cart page
      And user will add cement product to cart
#      And user will add Stainless Steel product to cart
      Then check user able to delete line item from cart
      And check user able to add the product to cart without pricing
      And user checkout the RCV2

  @test01
  Scenario: Verify HR Sheet Product Add to Cart
    When move the mouse to Mild steel category
    And move to Hot rolled category
    And click on HR Sheet product
    And open HR Sheet pdp
    And select all the HR Sheet attribute
    And add to cart
    And click view cart
    Then Delete the line item from cart

  Scenario: verify HR Coil Product Add to Cart
    When move the mouse to Mild steel category
    And move to Hot rolled category
    And click on HR Coil product
    And open HR Coil pdp
    And select all the HR Coil attribute
    And add to cart
    And click view cart
    Then Delete the line item from cart

  Scenario: Verify CR Coil Product Add to Cart
    When move the mouse to Mild steel category
    And move to Cold rolled category
    And click on CR Coil product
    And open CR Coil pdp
    And select all the CR Coil attribute
    And add to cart
    And click view cart
    Then Delete the line item from cart

