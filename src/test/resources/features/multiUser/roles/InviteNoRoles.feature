@multiUser @invitationRoles @roleNone @regression
Feature: Invite an additional user with no roles or permissions

  ONE scenario, on purpose. Each combination of Roles & permissions has its own feature file so a
  single run invites exactly one user per combination - never two users carrying the same roles,
  and never the same user twice. The invitee is minted fresh by CustomUtil.generateEmail(), which
  is timestamp-based, so a later run invites a different unique address with this same combination.

  This is the EMPTY combination, and the only one of the eight that is not a data table: Gherkin
  has no way to write a table with no rows, so the invite step for it is named rather than
  parameterised. All three toggles are still driven explicitly - to off - so a role granted by a
  form default fails here rather than passing unnoticed.

  WHAT THIS PROVES: the form refuses to submit without at least one role. Pressing Add user with
  every toggle off shows "Enable at least one from 'Manage orders' or 'Manage finances'" in the
  Notifications section, the dialog stays open and nobody is added - so the scenario ends there.
  There is no invitation to accept, no invitee to sign in as and nothing to add to a cart.

  Background:
    Given Go to session "ccp"

  @roleNone @testCaseId=multiuser-profile-001 @dataFile="multiUserProfile.yaml"
  Scenario: Invite an additional user without any roles or permissions
    And the primary user enters the email for the "primaryUser" account
    And the primary user clicks on continue
    And the primary user enters the password for the "primaryUser" account
    And the primary user should be logged in
    And navigate to the User and permissions tab
    Given the additional user to be invited does not exist in the system
    When the primary user invites the additional user with no roles
    Then the Notifications section should ask to enable Manage orders or Manage finances
    And the Add user dialog should stay open and the additional user should not be added
