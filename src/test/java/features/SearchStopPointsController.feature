Feature: Booking
  Scenario: Booking a ride
    Given that there are 4 seats on a LiveTrip
    When I book a LiveTrip
    Then there should only be 3 seats on the LiveTrip

  Scenario: Booking a ride 2
    Given that there are 100 seats on a LiveTrip
    When I book a LiveTrip
    Then there should only be 99 seats on the LiveTrip
