Feature: Cancel
  Scenario: Cancel A ride
    Given I have booked a ride with 3 seats
    When I cancel the ride
    Then there should be 4 available seats
    And pickup status is Cancelled

  Scenario: Cancel a trip
    Given I have an active LiveTrip
    When I cancel the trip
    Then the LiveTrip status should be cancelled.

