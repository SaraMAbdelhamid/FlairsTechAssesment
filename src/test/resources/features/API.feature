Feature: API Tests to create and delete a user

  @APILogin
  Scenario: User using apis is able to create and delete a user
    Given the user navigates to the login page to acquire token and session cookies
    Then the user has valid session cookies and token
    When the user logins using the credentials and acquired token
    Then access should be granted
    And user should be able to fetch all candidates
    And should be able to create a new candidate
    Then He should be able to delete the created candidate using its id


