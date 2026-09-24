@multiUser @invitationRoles @rolePromotionalNotifications @regression
Feature: Invite an additional user with only the Promotional notifications role

  ONE scenario, on purpose. Each combination of Roles & permissions has its own feature file so a
  single run invites exactly one user per combination - never two users carrying the same roles,
  and never the same user twice. The invitee is minted fresh by CustomUtil.generateEmail(), which
  is timestamp-based, so a later run invites a different unique address with this same combination.

  The invite step drives ALL THREE toggles, not just the ones named below: the roles this file
  omits are switched OFF explicitly, so a role arriving by form default would fail the assertion
  rather than pass unnoticed.

  Background:
    Given Go to session "ccp"

  @rolePromotionalNotifications @testCaseId=multiuser-profile-001 @dataFile="multiUserProfile.yaml"
  Scenario: Invite an additional user with only the Promotional notifications role
    And the primary user enters the email for the "primaryUser" account
    And the primary user clicks on continue
    And the primary user enters the password for the "primaryUser" account
    And the primary user should be logged in
    And navigate to the User and permissions tab
    Given the additional user to be invited does not exist in the system
    When the primary user invites the additional user with the roles:
      | Promotional notifications |
    Then the Notifications section should ask to enable Manage orders or Manage finances
    And the Add user dialog should stay open and the additional user should not be added