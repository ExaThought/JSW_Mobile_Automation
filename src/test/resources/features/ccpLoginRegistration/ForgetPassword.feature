@forgetPassword @regression
Feature: Check forget password

  Background:
    Given Go to session "ccp"

  @sanity
  Scenario: Check forget password with Invalid email ID
    And enter invalid email id "sr@.js"
    Then user should not able to  proceed to login with  proper error message
    Then continue button should be disabled for user

  @sanity
  Scenario: Check forget password with valid email ID
    And User enter valid email "testpre_prod12as@gmail.com"
    And user click on the continue button
    And user click on forget password link
    And user click on submit button
    Then user should see forget password link sent message