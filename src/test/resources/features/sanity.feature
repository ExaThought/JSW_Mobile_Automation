Feature: Zoho ledger module sanity suite verification on Zoho creator

  @testCaseId=id-003 @dataFile="ledger.yaml" @sanity
  Scenario:Advance Invoice Ledger Entry Validation on Zoho Creator
#    When Create order with SLO API
    Then validate the order details
    Then Confirm the order using JOMS API
    Then Create and Release DO
    Then Create shipment
    Then Update shipment & make shipment invoice
    Then Post invoice to Zoho
    Given Go to session "zoho_creator"
    Then Validate invoice amount and invoice entry under invoice on Zoho Creator
    Then Get cashback amount and quantity details
    Then validate the ledger entry, knocked off amount and clearing status on Zoho Creator

  @testCaseId=id-001 @dataFile="ledger.yaml" @smoke
  Scenario:Cashback Ledger Entry Validation on ZC [Cashback Issued, Cashback TDS, Cashback Payout]
    Given Go to session "zoho_creator"
    Then Validate ledger statement with Document type as Cashback Issued on Zoho Creator
    Then Validate ledger statement with Document type as Cashback TDS on Zoho Creator
    Then Get the "Cashback" payout amount
    Then Navigate to pending payout tab and approve the "Cashback" payout
    Then Navigate to approved payout tab and do bank export in Deutsche bank format for "Cashback" payout
    Then Navigate to export payout tab and export in XLSX format
    Then Open excel file add Status,UTR and Instrument Id and save as Export payouts - Deutsche bank_Updated.xlsx
    Then Navigate to Import payout status DB bank and upload the saved excel file
    And Select the entry and click on update status button
    Then Navigate to Historical Payout tab and validate the entry for "Cashback" payout
    Then Validate ledger statement with Document type as Cashback Payout

  @testCaseId=id-002 @dataFile="ledger.yaml" @sanit
  Scenario: Validate the customer ledger should be Frozen when customer has Credit Overdue
    When Create order with SLO API
    Then validate the order details
    Then Confirm the order using JOMS API
    Then Create and Release DO
    Then Create shipment
    Then Update shipment & make shipment invoice
    Then Post invoice to Zoho
    Given Go to session "zoho_creator"
    Then Validate invoice amount and invoice entry under invoice on Zoho Creator
    Given Go to session "zoho_books"
    Then Navigate to Invoices landing page of zoho books with book invoice id
    Then click on edit and change the Invoice Date and Due Date
    Then click on Record Payment to pay the amount
    Then click on edit and change the Invoice Date and Due Date
    Then click on payment tab and delete the payment for this invoice
    Then validate the zoho books invoice status as "Overdue"
    Given Go to session "zoho_creator"
    Then Navigate to Credit module tab and search the credit program and validate the freeze status as "Frozen" for Credit program "Intellect31220241"
    Then Navigate to ledger summary tab, click on Retry and validate the freeze status as "Frozen" for "INTELLECT DESIGN" customer
