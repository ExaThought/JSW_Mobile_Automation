@footer @regression
Feature: Validate Footer links and navigation

  Background:
    Given Go to session "ccp"
    And ccp login with configured user

  Scenario Outline: Verify footer link navigation - <linkName>
    Given the footer link "<linkName>" should be visible for web
    When the user clicks on the footer link "<linkName>" for web
    Then the user should be redirected to the corresponding page for web "<TextValue>"

    # The two footers differ, so each environment has its own rows. Rows tagged for the other
    # environment are reported as skipped (Hooks.skipIfForAnotherEnv).
    # TextValue only has to be CONTAINED in the landing URL.
    @qaOnly
    Examples: QA - https://qa-ssr.msme.jswone.in/
      | linkName           | TextValue                     |
      | Mild steel         | /category/mild-steel          |
      | Structural Steel   | /category/structural-steel    |
      | TMT                | /category/TMT-Bar-steel       |
      | Cement             | /category/cement              |
      | About us           | /corporate                    |
      | Investor relations | /corporate                    |
      | Blogs              | /blogs                        |
      | Site map           | /sitemap                      |
      | one helix          | /lp/one-helix-pipes-and-tubes |
      | Help & support     | /help-support                 |
      | Terms & conditions | /terms-conditions             |
      | Privacy policy     | /privacy-policy               |
      | Return Policy      | /return-policy                |

    # Pre-prod has no "About us" / "Investor relations" (replaced by "JSW One Corporate"), names
    # the One Helix links in full, and adds "One Helix HR sheets". Its Mild steel link points at
    # /category/steel?cid=<id>, which redirects to /category/mild-steel?cid=<id> - the value
    # below is where it LANDS.
    @preprodOnly
    Examples: Pre-prod - https://preprod.jswonemsme.com/
      | linkName                  | TextValue                     |
      | Mild steel                | /category/mild-steel          |
      | Structural Steel          | /category/structural-steel    |
      | TMT                       | /category/TMT-Bar-steel       |
      | Cement                    | /category/cement              |
      | JSW One Corporate         | /corporate                    |
      | One Helix Pipes and Tubes | /lp/one-helix-pipes-and-tubes |
      | One Helix HR sheets       | /lp/one-helix-hr-sheets       |
      | Blogs                     | /blogs                        |
      | Site map                  | /sitemap                      |
      | Help & support            | /help-support                 |
      | Terms & conditions        | /terms-conditions             |
      | Privacy policy            | /privacy-policy               |
      | Return Policy             | /return-policy                |

  Scenario: Verify footer support details and branding are visible
    Then the "Support" section should be visible in the footer for web
    And the "Policy" section should be visible in the footer for web
    And the support phone number should be visible for web
    And the support email address should be visible for web
    And the "footerLogo" should be visible in the footer for web
    And the "bankingPartnerLogo" should be visible in the footer for web


@productionSanity
  Scenario Outline: Verify social media icon navigation - <platform>
    When the user clicks on social icon "<platform>"
    Then the user should be redirected to the correct social page "<expectedDomain>"

    Examples:
      | platform  | expectedDomain |
      | youtube   | youtube        |
      | linkedin  | linkedin       |
      | instagram | instagram      |
      | facebook  | facebook       |
  
@productionSanity
  Scenario Outline: Verify app store badge navigation - <appName>
    When the user clicks on app icon "<appName>"
    Then the user should be redirected to the correct app store "<store>"

    Examples:
      | appName     | store     |
      | google play | playstore |
      | app store   | appstore  |