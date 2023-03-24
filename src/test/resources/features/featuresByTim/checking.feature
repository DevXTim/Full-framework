Feature: Checking accounts

  Background:
    Given sends request to create user with following fields:
      | firstName | lastName      | dob        | gender | title |
      | user1     | user1lastname | 01/01/2000 | M      | Mr.   |

  @newUser @Smoke @UI
  Scenario: Create new checking account UI
    And authorizes in the browser
    And goes to New Checking page
    And user is able to see all related labels
    When user fills the information for new checking account
    And user clicks on submit button
    Then user should see the confirmation message

  @newUser @UI
  Scenario Outline: Check transfers between two checking accounts
    Given creates new checking account with following details:
      | accountName  | accountTypeCode | openingDeposit | ownerTypeCode |
      | Checking One | SCK             | 50             | IND           |
    And creates new checking account with following details:
      | accountName  | accountTypeCode | openingDeposit | ownerTypeCode |
      | Checking Two | SCK             | 50             | IND           |
    And authorizes in the browser
    When user clicks on Transfer Between Accounts option
    And user is redirected to Internal Transfer page
    And user select from "<fromAccount>"
    And user select to "<toAccount>"
    And user enters transfer "<transferAmount>"
    And user verifies that amount is less than available balance
    When user clicks on submit button on Internal Transfer page
    And user is redirected to view checking accounts
    And user scrolls down until the transaction table is visible
    Then verify that "<fromAccount>" balance is "<senderBalanceAfterTransfer>"
    And verify that "<toAccount>" balance is "<receiverBalanceAfterTransfer>"
    Then user verifies "<transferAmount>" and "<receiverBalanceAfterTransfer>" in transaction table
    Examples:
      | fromAccount  | toAccount    | transferAmount | senderBalanceAfterTransfer | receiverBalanceAfterTransfer |
      | Checking One | Checking Two | 38.50          | 11.50                      | 88.50                        |
      | Checking One | Checking Two | 27.85          | 22.15                      | 77.85                        |