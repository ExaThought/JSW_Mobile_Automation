@regressionApi @customerProfileApi
Feature: Check Experience Middleware Customer Profile Api (v3)

  # GET {middlewareUrl}xp-mi/public/v3/customer-profile
  # The environment is driven by the `middlewareUrl` property, so these scenarios run against:
  #   preprod -> https://experience-middleware.preprod.jswonemsme.com/xp-mi/public/v3/customer-profile
  #   qa      -> https://experience-middleware.qa.jswone.in/xp-mi/public/v3/customer-profile
  # The access_token header is minted by Token.getCCPToken().

  @testCaseId=cp-001
  Scenario: Verify customer profile Api returns a successful response
    When we request the customer profile Api
    Then customer profile Api should respond with status code 200

  @testCaseId=cp-002
  Scenario: Verify customer profile Api returns the customer profile payload
    When we request the customer profile Api through the middleware client
    Then customer profile response should contain a valid customer profile

  @testCaseId=cp-003
  Scenario: Verify customer profile Api rejects an expired access token
    When we request the customer profile Api with an expired token
    Then customer profile Api should respond with status code 401
