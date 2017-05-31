Feature: User
  Scenario: Verify Password
    Given the password is "abcd"
    When the password "abcd" is verified
    Then the password should be verified

  Scenario: Verify Password Incorrect
    Given the password is "abcd"
    When the password "abcde" is verified
    Then the password should not be verified

  Scenario: Verify email
    Given a user object exists
    When the email "abcd@uclive.ac.nz" is verified
    Then the email should be verified

  Scenario: Verify email
    Given a user object exists
    When the email "abcd@canterbury.ac.nz" is verified
    Then the email should be verified

  Scenario: Verify email
    Given a user object exists
    When the email "abcd@cAnterBury.ac.nz" is verified
    Then the email should be verified

  Scenario: Verify email fail
    Given a user object exists
    When the email "abcd@ara.ac.nz" is verified
    Then the email should not be verified

  Scenario: Register Account
    Given A correct set of registration paramaters
    When a user is created
    Then the user should not be null

  Scenario: Register Account 2
    Given An incomplete set of registration paramaters
    When a user is created
    Then the user is null

  Scenario: Register Account 3
    Given A correct set of registration paramaters with non-matching passwords
    When a user is created
    Then the user is null

  Scenario: login
    Given An account exists with password "apple" and username "apple"
    When Login function is called with password "apple" and username "apple"
    Then the user should not be null

  Scenario: login fail
    Given An account exists with password "apple" and username "apple"
    When Login function is called with password "notapple" and username "apple"
    Then the user is null

  Scenario: update user
    Given An account exists with password "apple" and username "apple"
    When the user is updated and email is changed to "hello@live.com"
    Then the new email is "hello@live.com"

  Scenario: update user password
    Given An account exists with password "apple" and username "apple"
    When the user is updated and password is changed to "pswd"
    Then the new password is "pswd"