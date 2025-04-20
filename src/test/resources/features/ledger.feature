Feature: Zoho ledger module sanity suite verification on Zoho creator

  @testCaseId=id-007 @dataFile="ledger.yaml" @rahul #Rapid
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

  @testCaseId=id-007 @dataFile="ledger.yaml" @rahul #Rapid
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


  @testCaseId=id-010 @dataFile="ledger.yaml" @100PercentCredit #Intellect
   Scenario: Credit Invoice Ledger Entry validations [100% Credit Order]
#     Given Get Credit Limit Details for Customer Before Order creation
#     When Create order with SLO API
#     Then validate the order details
#     Then Confirm the order using JOMS API
#     Then Get Credit Limit Details for Customer After Order Confirmed
#     Then Create and Release DO
#     Then Create shipment
#     Then Update shipment and post Invoice to Zoho
     Given Go to session "zoho_creator"
     Then Validate invoice amount and invoice entry under invoice on Zoho Creator
     Then Validate the account receivables entry under ledger statement on Zoho Creator
     Then Validate the ledger entry for Credit Order under new ledger statement on Zoho Creator
     And Get Credit Limit Details for Customer After Order Invoiced


  @testCaseId=id-011 @dataFile="ledger.yaml" @CashAndCredit #Xoriant
  Scenario: Credit Invoice Ledger Entry validations [Cash and Credit Order]
    Given Get Credit Limit Details for Customer Before Order creation
    When Create order with SLO API
    Then validate the order details
    Then Confirm the order using JOMS API
    Then Get Credit Limit Details for Customer After Order Confirmed
    Then Create and Release DO
    Then Create shipment
    Then Update shipment and post Invoice to Zoho
    Given Go to session "zoho_creator"
    Then Validate invoice amount and invoice entry under invoice on Zoho Creator
    Then Validate the account receivables entry under ledger statement on Zoho Creator
    Then Validate the ledger entry for Credit Order Knocked Off Amount under new ledger statement
    Then Validate the ledger entry for Credit Order under new ledger statement on Zoho Creator
    And Get Credit Limit Details for Customer After Order Invoiced

  @testCaseId=id-008 @dataFile="ledger.yaml"  @Early_KnockOffTC_31 #Intellect
  Scenario: Validate the early knock off functionality for a customer in Zoho Creator
    Given Go to session "zoho_creator"
    When Navigate to customer ledger in ledger statement
    Then Search with customer's GSTIN "27AABCF9355R1ZT"
    Then Initiate the early knock off and confirm
    Then Validate AR not due and Usable ledger balance before and knock off

  @testCaseId=id-012 @dataFile="ledger.yaml" @IncomingPaymentKnockOffCreditInvoice #Xoriant
  Scenario: Validate the Incoming Payment knock off against credit invoice in ledger statement on Zoho Creator for Distributed credit & cash type order
    Given Get the incoming payment pending amount details before order creation in ledger statement for GSTIN "27AAACX0146P2Z0"
    Given Get Credit Limit Details for Customer Before Order creation
#    When Get Credit Limit Details for Customer Before Order creation for Credit Program "Xoriant2024"
#    Then Create a "QP_DISTRIBUTED_CASHAndCREDIT_ORDER_XORIANT" type order with SLO API
    Then validate the order details
    Then Confirm the order using JOMS API
    Then Create and Release DO
    Then Create shipment
    Then Update shipment and post Invoice to Zoho
    Then Validate invoice amount and invoice entry under invoice on Zoho Creator
    Then Validate the ledger entry for Credit Order Knocked Off Amount under new ledger statement
    Then validate the ledger entry for incoming payment after order invoice under new ledger statement for GSTIN "27AAACX0146P2Z0"

  @testCaseId=id-013 @dataFile="ledger.yaml" @CashAndCreditOrderForPlantSupply #Intellect
  Scenario: Validate the Credit Invoice entry in ledger statement on Zoho Creator for the Plant Supply credit & cash type order
    Given Get Credit Limit Details for Customer Before Order creation
#    When Create a "QP_PLANT_SUPPLY_CREDIT_ORDER" type order with SLO API
#    Then validate the order details for "PLANT SUPPLY" and "Credit"
    Then Confirm the order using JOMS API
    Then Create and Release DO
    Then Create shipment
    Then Update shipment and post Invoice to Zoho
    Then Validate invoice amount and invoice entry under invoice on Zoho Creator
    Then Validate the account receivables entry under ledger statement on Zoho Creator
    Then Validate the ledger entry for Credit Order Knocked Off Amount under new ledger statement
    Then Get Credit Limit Details for Customer After Order Invoiced

   @testCaseId=id-014 @dataFile="ledger.yaml" @Do_RejectionCashAndCarryAdv #Unico
  Scenario: Validate the Blocked and Usable ledger balance in ledger summary after DO reject
    When Check customer ledger balance before in ledger summary for "UNICO CONNECT PRIVATE LIMITED" customer
#    When Create a "DISTRIBUTED_CASH_AND_CARRY_20%ADV_ORDER" type order with SLO API
#    Then validate the order details for "DISTRIBUTED" and "Cash & Carry - Advance"
    Then validate the ledger balance after order placed in ledger summary for "UNICO CONNECT PRIVATE LIMITED" customer
    And Confirm the order using JOMS API
    Then Create DO
    Then validate the ledger balance after DO created in ledger summary for "UNICO CONNECT PRIVATE LIMITED" customer
    Then Reject DO
    Then validate the ledger balance after DO rejected in ledger summary for "UNICO CONNECT PRIVATE LIMITED" customer


  @testCaseId=id-015 @dataFile="ledger.yaml" @Do_RejectionCreditByPassCashAndCredit #Unico
  Scenario: Validate the Blocked limit and Usable limit in Credit limit after DO reject
    When Check customer ledger balance before in ledger summary for "UNICO CONNECT PRIVATE LIMITED" customer
#    When Create a "DISTRIBUTED_20%ADV80%CREDIT_ORDER" type order with SLO API
#    Then validate the order details for "DISTRIBUTED" and "Credit"
    Then Confirm the order using JOMS API
    Then Create DO
    Then validate the ledger balance after DO created for credit bypass in ledger summary for "UNICO CONNECT PRIVATE LIMITED" customer
    Then Reject DO
    Then validate the ledger balance after DO rejected in ledger summary for "UNICO CONNECT PRIVATE LIMITED " customer





















