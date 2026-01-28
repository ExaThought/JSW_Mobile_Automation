@home_sections
Feature: Validate Home Page Sections

  As a mobile user
  I want to validate key sections on the home page
  So that I can ensure content, headings, and CTAs are displayed correctly

  Background:
    Given user open the msite
    Then the home page should load successfully
    And the CCP logo should be visible

  Scenario Outline: Validate section "<section>"

    And the section  "<section>" should be visible
    Then the section should have content


    Examples:
      | section             |
      | From our customers  |
      | MSME Masterclass    |
