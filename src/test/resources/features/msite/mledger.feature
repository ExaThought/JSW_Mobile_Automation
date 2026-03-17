@mLedger @regression @msanity
Feature: Validate Ledger Page on mSite

  Background:
    Given user open the msite
    Then the home page should load successfully
    And the CCP logo should be visible
    And user clicks on login floating button on mobile
    And user enters email "spinaroo@yopmail.com"
    When user submits login
    And user enters password "Jsw@2025"
    When user clicks on proceed to login
    And user navigates to ledger page

  @LedgerPageLoad
  Scenario: Verify Ledger page loads with all sections
    Then the ledger page should load successfully
    And payments due now section should be visible
    And due later section should be visible
    And usable ledger balance section should be visible
    And view and download ledger link should be visible

  @LedgerPaymentsDueNow
  Scenario: Verify Payments Due Now section and redirection
    Then payments due now section should be visible
    And payments due now amount should be displayed
    When user clicks on payments due now section
    Then user should be redirected to payments due now tab

  @LedgerUsableCreditLimit
  Scenario: Verify Usable Credit Limit section
    Then usable credit limit section should be visible
    And usable credit limit amount should be displayed
    When user clicks on usable credit limit section
    Then usable credit limit details page should load successfully


  @LedgerDueLater
  Scenario: Verify Due Later section and redirection
    Then due later section should be visible
    And due later amount should be displayed
    When user clicks on due later section
    Then user should be redirected to due later tab

  @LedgerUsableBalance
  Scenario: Verify Usable Ledger Balance section
    Then usable ledger balance section should be visible
    And usable ledger balance amount should be displayed
    And view and download ledger link should be visible

  @LedgerViewDownload
  Scenario: Verify View and Download Ledger link navigates correctly
    When user clicks on view and download ledger link
    Then ledger details page should load successfully

  @LedgerStatementDownload
  Scenario: Verify Ledger statement download functionality
    When user clicks on usable ledger balance section
    And user selects date range from "January" "1" "2026" to "March" "15" "2026"
    When user clicks on download statement button
    Then a success toast message should appear for download

  @LedgerStatementEmptyState
  Scenario: Verify Ledger statement empty state message
    When user clicks on usable ledger balance section
    And user selects date range from "March" "1" "2026" to "March" "1" "2026"
    Then no transactions message should be visible


  @LedgerDateValidation
  Scenario: Verify Ledger statement download date validation
    When user clicks on usable ledger balance section
    When user clicks on ledger download button without selecting date
    Then user should see select date range toast message

  @LedgerOtherActions
  Scenario: Verify Other Actions section is visible
    Then other actions section should be visible
    And payment instructions option should be visible
    And financial documents option should be visible
    And help and support option should be visible

  @LedgerPaymentInstructions
  Scenario: Verify Payment Instructions navigation
    When user clicks on payment instructions
    Then payment instructions page should load successfully

  @LedgerFinancialDocuments
  Scenario: Verify Financial Documents navigation
    When user clicks on financial documents
    Then financial documents page should load successfully

  @FinancialDocumentsRequest
  Scenario: Verify Financial Documents request and download functionality
    When user clicks on financial documents
    And financial documents page should load successfully
    And user selects "All documents" document type
    And user selects "Last 3 months" date range
    And user clicks on request documents button
    Then requested documents should be preparing for download
    Then requested documents list should be displayed
    When user clicks on download document button

  @FinancialDocumentsEmptyState
  Scenario: Verify Financial Documents empty state message
    When user clicks on financial documents
    And financial documents page should load successfully
    And user selects "Credit note" document type
    And user clicks on request documents button
    Then a toast message should appear for documents not available

  @FinancialDocumentsRequestError
  Scenario: Verify Financial Documents request error handling
    When user clicks on financial documents
    And financial documents page should load successfully
    And user selects "All documents" document type
    And user clicks on request documents button
    Then a toast message should appear for documents not available

  @LedgerBottomNav
  Scenario: Verify Ledger bottom navigation tab is active
    Then the ledger tab should be active in bottom navigation
    And home tab should be visible in bottom navigation
    And catalogue tab should be visible in bottom navigation
    And cart tab should be visible in bottom navigation
    And order tab should be visible in bottom navigation
