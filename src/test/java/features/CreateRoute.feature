Feature: CreateRoute
  Scenario: create successful route
    Given selectedStops is not empty and a route name is given
    When a new Route is created
    Then a new route should be not null

  Scenario: create unsuccessful route 1
    Given selectedStops is empty and a route name is given
    When a new Route is created
    Then a new route should be null

  Scenario: create unsuccessful route 2
    Given selectedStops is not empty and a route name is not given
    When a new Route is created
    Then a new route should be null

