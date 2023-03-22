Feature: Login feature

  @Smoke @UI
  Scenario: Verify user is able to login with valid credentials
    Given user enters valid "test321@gmail.com" and "Nn123123"
    When user clicks on Sign In button
    Then verify user is successfully logged in to their account

  @API @Smoke
  Scenario: Create a user via api
    When sends request to create user with following fields:
      | firstName | lastName      | dob        | gender | title |
      | user1     | user1lastname | 01/01/2000 | M      | Mr.   |
    Then validate user is created
