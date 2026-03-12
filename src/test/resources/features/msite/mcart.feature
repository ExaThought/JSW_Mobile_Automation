@mCart @regression @msanity

Feature: Check Add-To-Cart


  Background:
    Given user open the msite
    Then the home page should load successfully
    And the CCP logo should be visible
    And user clicks on login floating button on mobile
    And user enters email "navjotautomation@yopmail.com"
    When user submits login
    And user enters password "Jsw@2026"
    When user clicks on proceed to login
    When user clicks on catalogue menu

  @HRSheet @test01
  Scenario: Verify HR Sheet Product Add to Cart
    And click on "MS Hot rolled"
    And click on "Sheets" product on msite
    Then capture product name from PDP
    And select thickness "1.6"
    And enter width "1500"
    And enter length "3000"
    And enter quantity "50"
    And click M-site add to cart
    When user clicks on view cart
    Then product name should match PDP
    And selected attributes should match PDP
    And quantity should match entered quantity
    When user deletes all cart items

  @HRCoil
  Scenario: verify HR Coil Product Add to Cart
    And click on "MS Hot rolled"
    And click on "Coils" product on msite
    Then capture product name from PDP
    And select thickness "1.6"
    And enter width "1250"
    And enter quantity "50"
    And click M-site add to cart
    When user clicks on view cart
    Then product name should match PDP
    And selected attributes should match PDP
    And quantity should match entered quantity
    When user deletes all cart items

  @CRCoil
  Scenario: Verify CR Coil Product Add to Cart
    And click on "MS Cold rolled"
    And click on "Coils" product on msite
    Then capture product name from PDP
    And select thickness "0.5"
    And enter width "1250"
    And enter quantity "50"
    And click M-site add to cart
    When user clicks on view cart
    Then product name should match PDP
    And selected attributes should match PDP
    And quantity should match entered quantity
    When user deletes all cart items


  @CRSheet
  Scenario: Verify CR Sheet Product Add to Cart
    And click on "MS Cold rolled"
    And click on "Sheets" product on msite
    Then capture product name from PDP
    And select thickness "0.8"
    And enter width "1250"
    And enter length "1930"
    And enter quantity "50"
    And click M-site add to cart
    When user clicks on view cart
    Then product name should match PDP
    And selected attributes should match PDP
    And quantity should match entered quantity
    When user deletes all cart items

  @Cement
  Scenario: Verify Cement Product Add to Cart
    When user clicks on catalogue menu
    And click on "Cement"
    And click on "JSW Ordinary Portland Cement 43" product on msite
    Then capture product name from PDP
    And enter quantity "100"
    And click M-site add to cart
    When user clicks on view cart
    Then product name should match PDP
    And selected attributes should match PDP
    And quantity should match entered quantity
    When user deletes all cart items


  @CoatedSteel
  Scenario: Verify Coated Steel Product Add to Cart
    When scroll down to "MS Cold rolled" section
    And click on "Coated Steel"
    And click on "JSW Steel" product on msite
    Then capture product name from PDP
    And select thickness "3"
    And enter width "1250"
    And enter length "2500"
    And select coating grade "80 GSM"
    And select spangle type "Regular"
    And enter quantity "50"
    And click M-site add to cart
    When user clicks on view cart
    Then product name should match PDP
    And selected attributes should match PDP
    And quantity should match entered quantity
    When user deletes all cart items


  @WireRods
  Scenario: Verify Wire Rods Product Add to Cart
    When scroll down to "MS Cold rolled" section
    And click on "Wire rods"
    And click on "JSW Steel Wire Rod" product on msite
    Then capture product name from PDP
    And select diameter "5.5"
    And enter quantity "10"
    And click M-site add to cart
    When user clicks on view cart
    Then product name should match PDP
    And selected attributes should match PDP
    And quantity should match entered quantity
    When user deletes all cart items



  @channel
  Scenario: Verify Channel Product Add to Cart
    When scroll down to "MS Cold rolled" section
    And click on "Structural steel"
    And click on "Channels" product on msite
    Then capture product name from PDP
    And select Flange Width "80"
    And enter Web Depth "250"
    And enter sectional weigth "30"
    And enter cutlength "12000"
    And enter quantity "20"
    And click M-site add to cart
    When user clicks on view cart
    Then product name should match PDP
    And selected attributes should match PDP
    And quantity should match entered quantity
    When user deletes all cart items



  @bitumen
  Scenario: Verify Bitumen Product Add to Cart
    When scroll down to "Cement" section
    And click on "Bitumen"
    And click on "Standard IS" product on msite
    Then capture product name from PDP
    And enter quantity "5"
    And click M-site add to cart
    When user clicks on view cart
    Then product name should match PDP
    And selected attributes should match PDP
    And quantity should match entered quantity
    When user deletes all cart items



  @TMT
  Scenario: Verify TMT Product Add to Cart
    When scroll down to "Structural steel" section
    And click on "TMT"
    And click on "TMT" product on msite
    And click on ubend
    Then capture product name from PDP
    And enter TMT quantity "100"
    And click M-site add to cart
    When user clicks on view cart
    Then product name should match PDP
    And selected attributes should match PDP
    And quantity should match entered quantity
    When user deletes all cart items




