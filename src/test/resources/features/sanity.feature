Feature: Zoho ledger module sanity suite verification on Zoho creator

  Background:
    Given Setup the environment for zoho login

  @testCaseId=id-001 @dataFile="ledger.yaml"
  Scenario:Cashback Ledger Entry Validation on ZC [Cashback Issued, Cashback TDS, Cashback Payout]
    When Create a "QP_DISTRIBUTED_CASH_AND_CARRY_CASHBACK" type order with SLO API
    Then validate the order details for "DISTRIBUTED" and "Cash & Carry - Advance"
    Then Confirm the order using JOMS API
    Then Create and Release DO
    Then Create shipment
    Then Update shipment & make shipment invoice
    Then Post invoice to Zoho
    Then Validate invoice amount and invoice entry under invoice on Zoho Creator
    Then Get cashback amount and quantity details
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

  @testCaseId=id-002 @dataFile="ledger.yaml" @smoke
  Scenario: Validate the customer ledger should be Frozen when customer has Credit Overdue
    When Create a "QP_DISTRIBUTED_CREDIT_ORDER" type order with SLO API
#    Then validate the order details for "DISTRIBUTED" and "Credit"
#    Then Confirm the order using JOMS API
#    Then Create and Release DO
#    Then Create shipment
#    Then Update shipment & make shipment invoice
#    Then Post invoice to Zoho
#    Then Get zoho book invoice ID and validate under invoice on Zoho Creator
#    Given Login to zoho books
#    Then Navigate to Invoices landing page of zoho books with book invoice id
#    Then click on edit and change the Invoice Date and Due Date
#    Then click on Record Payment to pay the amount
#    Then click on edit and change the Invoice Date and Due Date
#    Then click on payment tab and delete the payment for this invoice
#    Then validate the zoho books invoice status as "Overdue"
#    Then Navigate to Credit module tab and search the credit program and validate the freeze status as "Frozen" for Credit program "Intellect31220241"
#    Then Navigate to ledger summary tab, click on Retry and validate the freeze status as "Frozen" for "INTELLECT DESIGN" customer