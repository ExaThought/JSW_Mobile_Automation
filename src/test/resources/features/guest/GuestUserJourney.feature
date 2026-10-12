@regression @GuestUserJourney
Feature: Guest user journeys

  Scenario: Check closing the delivery PIN code dialog leaves Select location unchanged
    Given user open the CCP portal as guest user
    When the guest user clicks on Select location near the search bar
    Then the delivery PIN code dialog should be displayed
    When the guest user closes the delivery PIN code dialog
    Then the delivery PIN code dialog should be closed and Select location should still be shown

  Scenario: Check entering a PIN code sets the delivery location in the header
    Given user open the CCP portal as guest user
    When the guest user clicks on Select location near the search bar
    Then the delivery PIN code dialog should be displayed
    When the guest user enters "400051" in the PIN code field and clicks Check
    Then the delivery PIN code dialog should be closed and the header should show "Mumbai cit.. 400051"
