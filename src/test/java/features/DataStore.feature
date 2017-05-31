Feature: DataStore
  Scenario: Saving Data
    Given a Car is created
    And a Stop is created
    And a Route is created
    And a Trip is created
    And a LiveTrip is Created
    When I clear data
    And when i load data
    Then there should be a Car
    And there should be a Stop
    And there should be a Route
    And there should be a Trip
    And there should be a LiveTrip
