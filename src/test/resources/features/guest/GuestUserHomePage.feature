@regression @GuestUser
  Feature: Check Guest User Home Page

    Scenario: Check Register button
      Given user open the CCP portal as guest user
      When user clicks on Register button
      Then user should be navigated to Registration page

    Scenario: Check Active order in Guest user home page
      Given user open the CCP portal as guest user
      Then Active order section should not be displayed

    Scenario: Check Ledger section in Guest user home page
      When user give direct "ledgerUrl"
      Then user should be prompted to Login or Register

    Scenario: Check Guest can able to open Ledger Page without login
      When user give direct "ledgerUrl"
      Then user should be prompted to Login or Register

    Scenario: Check Guest user can able to open cart page  without login
      When user give direct "cartUrl"
      Then user should be prompted to Login or Register

    Scenario: Check Guest user can able to open Order page  without login
      When user give direct "orderPageUrl"
      Then user should be prompted to Login or Register

    Scenario: Check Guest user can able to open Profile page  without login
      When user give direct "profileUrl"
      Then user should be prompted to Login or Register

    Scenario: Check Guest user can able to open material Tracking page  without login
      When user give direct "materialTrackingUrl"
      Then user should be prompted to Login or Register

    Scenario: Check Guest user can able to see MY_REQUIREMENT
      Given user open the CCP portal as guest user
      Then user should not able to see "MY_REQUIREMENT" section

    Scenario: Check Guest user can able to see MY_ORDERS
      Given user open the CCP portal as guest user
      Then user should not able to see "MY_ORDERS" section

    Scenario: Check You tube video in Guest user home page
      Given user open the CCP portal as guest user
      Then You tube video should be displayed in home page and able to play

    Scenario: Check blog's in Guest user home page
      Given user open the CCP portal as guest user
      Then Blogs section should be displayed in home page and user able to open any blog



