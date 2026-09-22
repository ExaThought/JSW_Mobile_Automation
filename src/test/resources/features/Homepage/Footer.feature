@footer @regression
Feature: Validate Footer links and navigation

  Background:
    Given Go to session "ccp"
    And ccp login with configured user

  Scenario Outline: Verify footer link navigation - <linkName>
    Given the footer link "<linkName>" should be visible for web
    When the user clicks on the footer link "<linkName>" for web
    Then the user should be redirected to the corresponding page for web "<TextValue>"

    Examples:
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