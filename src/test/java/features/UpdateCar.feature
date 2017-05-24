Feature: UpdateCar
  Scenario: Car updating and notifications are sent
    Given a livetrip exists with one booked pickup
    And the current notifications are stored
    When the update function is called and the LitersPer100km are changed
    Then cars paramaters are updated

