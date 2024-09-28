Feature: User Login

  @WebLogin
  Scenario: User is on the login page
    Given the user navigates to the login page
    Given the user has valid credentials
    When the user enters the username
    And fills the password field
    And clicks on login button
    Then clicks on Admin Tab
    Then He should be able to sees Users records
    Given the user clicks on add users button
    And fills in all the user details
    And clicks on save
    Then number of records should be increased by one
    Then created user can be searched for
    And can be deleted after confirming the delete
    Then the records should be reduced by one
