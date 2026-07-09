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
      | linkName           |  | TextValue        |
      | Mild steel         |  | steel            |
      | Structural Steel   |  | structural-steel |
      | TMT                |  | TMT              |
      | Cement             |  | Cement           |
      | About us           |  | about-us         |
      | Blogs              |  | blogs            |
      | Site map           |  | sitemap          |
      | one helix          |  | one-helix        |
      | Help & support     |  | help-support     |
      | Terms & conditions |  | terms-conditions |
      | Privacy policy     |  | privacy-policy   |
      | Return Policy      |  | return-policy    |
