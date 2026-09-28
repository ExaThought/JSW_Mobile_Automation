@multiUser @invitationRoles @roleFinanceAccount @regression
Feature: Invite an additional user with only the Finance & account management role

  Background:
    Given Go to session "ccp"

  @roleFinanceAccount @testCaseId=multiuser-profile-001 @dataFile="multiUserProfile.yaml"
  Scenario: Invite an additional user with only the Finance & account management role
    And the primary user enters the email for the "primaryUser" account
    And the primary user clicks on continue
    And the primary user enters the password for the "primaryUser" account
    And the primary user should be logged in
  # Start from an empty cart, so what the additional user sees in it is not left over from
  # an earlier run.
    Given empty the cart via API
    When click cart icon
    Then check the empty cart page display with message "Your cart is empty"
    And navigate to the User and permissions tab
    Given the additional user to be invited does not exist in the system
    When the primary user invites the additional user with the roles:
      | Finance & account management |
    Then the invitation should be sent to the additional user
    And the additional user should be listed with the "Invited" status
    And the additional user should be assigned exactly the roles:
      | Finance & account management |

  # --- Accept the invitation as the invited user ---------------------------------------------
  # Appended to this scenario rather than split into a second one: the invited address is
  # generated per scenario and lives in ScenarioContext, so a separate Scenario would have no way
  # to know which generated @maildrop.cc address to open.
    And the invited user closes the primary user window and opens a clean window
    And the invited user opens the mailbox for the invited address
    And the invited user refreshes the mailbox
    And the invited user opens the first email
    And the invited user scrolls to the Accept invite button in the email
    When the invited user clicks Accept invite
  # The accept-invite page re-states the roles the primary user granted. Asserting them HERE is
  # what proves the choice survived the invitation: the Users & permissions row is written by the
  # same form that set it, while this screen renders what the server actually stored.
    Then the invited user should land on the accept invite page
    And the accept invite page should show exactly the granted roles:
      | Finance & account management |
    And the invited user scrolls to the Continue button
    When the invited user clicks Continue
  # The OTP is read out of the app's own /otp/send response by NetworkCapture - no SMS gateway.
    Then the OTP popup should show the mobile number given at invitation
    And the invited user enters the intercepted OTP
  # Scroll to Verify BEFORE the consents: the checkboxes sit just above it in the same
  # scroll panel, so this brings them into view too - clicking them near the panel edge is
  # what put the terms checkbox under the modal backdrop.
    And the invited user scrolls to the Verify button
    And the invited user accepts the WhatsApp and terms consents
    And the invited user clicks Verify
    And the browser returns to the CCP portal
  # Still signed in AS the newly activated user - check what they see of their own account.
    And the additional user opens Account and then Profile details
  # Personal details are asserted against the INVITATION values held in ScenarioContext - this run
  # created the invitee, so it already knows the truth and needs no data file.
    Then the customer profile page should be displayed
    And the "Personal details" section should be displayed on the profile page
    And the profile should show the name given at invitation
    And the profile should show the email given at invitation
    And the profile should show the phone number given at invitation
    And the displayed email address should be well formed
    And the displayed phone number should be well formed
  # Company details are INHERITED from the primary account, so they come from the environment
  # scoped data file bound by @testCaseId / @dataFile above. Reuses CustomerDetailSteps.
    And the "Company details" section should be displayed on the profile page
    And validate Company Name field value in Company details
    And validate Company address field value in Company details
    And validate GSTIN field value in Company details
    And validate PAN field value in Company details
    And the displayed GSTIN should be a valid GST number
    And the displayed PAN should be a valid PAN
    And the displayed GSTIN should contain the displayed PAN
    And the account dropdown should not offer Users and permissions
    And the additional user logs out
  # Back as the primary user, to confirm acceptance changed the row from Invited to Active.
    And the primary user enters the email for the "primaryUser" account
    And the primary user clicks on continue
    And the primary user enters the password for the "primaryUser" account
    And the primary user should be logged in
    And navigate to the User and permissions tab
    Then the additional user should be listed with the "Active" status

  # --- The additional user now signs in for themselves, by phone + OTP ------------------------
  # Proves the account is genuinely usable, not merely listed as Active on somebody else's screen.
    And the primary user logs out
    And the page is refreshed after logout
    When the additional user signs in with their phone number
  # The OTP is intercepted exactly as at acceptance - from the app's own /otp/send response. The
  # capture shim is re-installed inside the step above, because the refresh wiped the earlier one.
    And the additional user enters the OTP received on login
    And the additional user clicks Verify to sign in
  # Their own profile, checked again - this time reached through a session they created.
    And the additional user opens Account and then Profile details
    Then the customer profile page should be displayed
    And the "Personal details" section should be displayed on the profile page
    And the profile should show the name given at invitation
    And the profile should show the email given at invitation
    And the profile should show the phone number given at invitation
    And the displayed email address should be well formed
    And the displayed phone number should be well formed
    And the additional user returns to the homepage
  # Without Order & materials management the additional user cannot buy on the account, so even
  # a full configuration must leave Add to cart disabled.
    When move the mouse to Mild steel category and to sub category Hot rolled
    And click on HR Sheet product
    And User lands on PLP
    And click on the product for "HR Sheet" category
    Then check Add to cart is disabled
    And the PDP should show the no permission to add to cart alert
