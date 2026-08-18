@footer @regression
Feature: Validate Footer links and navigation

  Background:
    Given Go to session "ccp"
    And ccp login with configured user

  Scenario Outline: Verify footer link navigation
    Given the footer link "<linkName>" should be visible for web
    When the user clicks on the footer link "<linkName>" for web
    Then the user should be redirected to the corresponding page for web "<TextValue>"

    Examples:
      | linkName            |  | TextValue        |
      | Mild steel          |  | steel            |
      | Structural Steel    |  | structural-steel |
      | TMT                 |  | TMT              |
      | Cement              |  | Cement           |
      | About us            |  | about-us         |
      | Investor relations  |  | about-us         |
      | Blogs               |  | blogs            |
      | Site map            |  | sitemap          |
      | one helix           |  | one-helix        |
      | Help & support      |  | help-support     |
      | Terms & conditions  |  | terms-conditions |
      | Privacy policy      |  | privacy-policy   |
      | Return Policy       |  | return-policy    |

  Scenario Outline: Verify social media icon navigation
    When the user clicks on social icon "<platform>"
    Then the user should be redirected to the correct social page "<expectedDomain>"

    Examples:
      | platform  | expectedDomain |
      | linkedin  | linkedin       |
      | instagram | instagram      |
      | facebook  | facebook       |
      | youtube   | youtube        |

  Scenario Outline: Verify app store badge navigation
    When the user clicks on app icon "<appName>"
    Then the user should be redirected to the correct app store "<store>"

    Examples:
      | appName     | store     |
      | google play | playstore |
      | app store   | appstore  |
