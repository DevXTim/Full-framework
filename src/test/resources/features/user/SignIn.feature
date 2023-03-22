Feature: Sign In Feature

  @Smoke @UI
  Scenario: Verify user is able to login with valid credentials
    Given user enters valid "test321@gmail.com" and "Nn123123"
    When user clicks on Sign In button
    Then verify user is successfully logged in to their account

