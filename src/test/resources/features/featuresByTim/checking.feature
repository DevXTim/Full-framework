Feature: Checking accounts

  @API @Smoke
  Scenario: Create new checking account via api
    When sends request to create user with following fields:
      | firstName | lastName      | dob        | gender | title |
      | user1     | user1lastname | 01/01/2000 | M      | Mr.   |
    And starts browser and authorizes
    And goes to New Checking page
    And user is able to see all related labels
    When user fills the information for new checking account
    And user clicks on submit button
    Then user should see the confirmation message