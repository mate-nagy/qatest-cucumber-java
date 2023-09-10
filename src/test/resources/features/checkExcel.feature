Feature: Check excel file

  Scenario: Check excel file
    Given File with name "purchase_orders.xls" exist
    Then The header have next headers
      | Buyer | Buyer | Buyer External ID | Sales Order |
    And There are more than 10 lines in file