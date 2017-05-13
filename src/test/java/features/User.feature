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