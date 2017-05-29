Feature: MapUtils
  Scenario: calculating a price
    Given a trip is 3km and the fuel efficiency is 5 liters per 100kms
    When The price is calculated
    Then the price should be $2.5

  Scenario: calculating a price2
      Given a trip is 10km and the fuel efficiency is 4 liters per 100kms
      When The price is calculated
      Then the price should be $7

  Scenario: calculating a price3
        Given a trip is 10km and the fuel efficiency is 0 liters per 100kms
        When The price is calculated
        Then the price should be $2

  Scenario: distance to UC
    Given the latitude is -43.471088 and the longitude is 172.607315
    When the distacne between uni is calculated
    Then the distance is 6.2km
