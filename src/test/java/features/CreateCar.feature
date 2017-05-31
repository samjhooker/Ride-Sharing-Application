Feature: CreateCar
  Scenario: a successful car is created
    Given correct car paramaters are defined
    When a car is created using the parameters
    Then the car should exist

  Scenario: an unsuccessful car is created
    Given incorrect seats car paramaters are defined
    When a car is created using the parameters
    Then the car should be null

  Scenario: an unsuccessful car is created
    Given incorrect efficiency car paramaters are defined
    When a car is created using the parameters
    Then the car should be null

  Scenario: an unsuccessful car is created
    Given incorrect year car paramaters are defined
    When a car is created using the parameters
    Then the car should be null
