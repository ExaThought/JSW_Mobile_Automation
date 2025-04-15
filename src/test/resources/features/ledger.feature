Feature: Zoho ledger module sanity suite verification on Zoho creator

  @testCaseId=id-007 @dataFile="ledger.yaml" @rahul
  Scenario: Validate the customer ledger should be Frozen when customer has Credit Overdue
    When Create order with SLO API
    Then validate the order details
    Then Confirm the order using JOMS API
    Then Create and Release DO
    Then Create shipment
    Then Update shipment and post Invoice to Zoho
    Given Go to session "zoho_creator"
    Then Validate invoice amount and invoice entry under invoice on Zoho Creator
    Given Go to session "zoho_books"
    Then Navigate to Invoices landing page of zoho books with book invoice id
    Then click on edit and change the Invoice Date and Due Date
    Then click on edit and change the Invoice Date and Due Date
    Then validate the zoho books invoice status as "Overdue"
    Given Go to session "zoho_creator"
    Then Navigate to Credit module tab and search the credit program and validate the freeze status as "Frozen" for Credit program "Rapid2025"
    Then Navigate to ledger summary tab, click on Retry and validate the freeze status as "Frozen" for "RAPID FLEET" customer

   @dataFile="ledger.yaml" @rahull
  Scenario: Validate the customer ledger should be Unfrozen when customer has Paid Credit Overdue
#    Given Go to session "zoho_creator"
    Then Create Incoming Payment In Zoho
    Given Go to session "zoho_books"
    Then Navigate to Invoices landing page of zoho books with book invoice id
    Then Refresh the zoho books page
    Then validate the zoho books invoice status as "Paid"
    Given Go to session "zoho_creator"
    Then Navigate to Credit module tab and search the credit program and validate the freeze status as "Unfrozen" for Credit program "Rapid2025"
    Then Navigate to ledger summary tab, click on Retry and validate the freeze status as "Unfrozen" for "RAPID FLEET" customer

  @testCaseId=id-008 @dataFile="ledger.yaml"  @Early_KnockOffTC_31
  Scenario: Validate the early knock off functionality for a customer in Zoho Creator
    Given Go to session "zoho_creator"
    When Navigate to customer ledger in ledger statement
    Then Search with customer's GSTIN "27AABCF9355R1ZT"
    Then Initiate the early knock off and confirm
    Then Validate AR not due and Usable ledger balance before and knock off
















