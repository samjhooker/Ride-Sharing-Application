Feature: CreatingALiveTrip
  Scenario: creating a repeated ride everyday
    Given the Date is today
    And a trip repeats everyday
    And the expiry date is in 6 days
    When I create a repeated ride
    Then there should only be 7 LiveTrips created

  Scenario: creating a repeated ride everyday 2
    Given the Date is today
    And a trip repeats everyday
    And the expiry date is in 0 days
    When I create a repeated ride
    Then there should only be 1 LiveTrips created

  Scenario: creating a repeated ride everyday 3
    Given the Date is today
    And a trip repeats everyday
    And the expiry date is in 99 days
    When I create a repeated ride
    Then there should only be 100 LiveTrips created

  Scenario: creating a repeated ride on one day
    Given the Date is today
    And a trip repeats on 1 day
    And the expiry date is in 6 days
    When I create a repeated ride
    Then there should only be 1 LiveTrips created

  Scenario: creating a repeated ride on one day 2
    Given the Date is today
    And a trip repeats on 1 day
    And the expiry date is in 364 days
    When I create a repeated ride
    Then there should only be 52 LiveTrips created