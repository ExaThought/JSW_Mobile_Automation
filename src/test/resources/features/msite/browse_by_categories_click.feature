Feature: Browse by categories - tile navigation

  @categoryTileClick
  Scenario Outline: Validate category tile navigation
    Given user open the msite
    Then the home page should load successfully
    Then the CCP logo should be visible
    And the category tile "<category>" should be visible
    Then the category tile "<category>" should be clickable
    When the user clicks on category tile "<category>"
    Then the user should be redirected to the correct category page

    Examples:
      | category           |
      | Cement             |
      | Bitumen            |
      | MS Cold Rolled     |
      | TMT                |
      | Coated Steel       |
      | Structural Steel   |
      | MS Hot Rolled      |
      | SS Hot Rolled      |
