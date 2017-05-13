Feature: Notification
  Scenario: checking whether to show notification
    Given that the expiry date of WOF is in 30 days
    When I check whether to show a notification for WOF
    Then a notification should be shown

  Scenario: checking whether to show notification
    Given that the expiry date of WOF is in 9 days
    When I check whether to show a notification for WOF
    Then a notification should be shown

  Scenario: checking whether to show notification 2
    Given that the expiry date of WOF is in 35 days
    When I check whether to show a notification for WOF
    Then a notification should not be shown

  Scenario: checking whether to show notification 3
    Given that the expiry date of Rego is in 30 days
    When I check whether to show a notification for rego
    Then a notification should be shown

  Scenario: checking whether to show notification 4
    Given that the expiry date of Rego is in 35 days
    When I check whether to show a notification for rego
    Then a notification should not be shown

  Scenario: checking whether to show notification 5
    Given that the expiry date of Rego is in 30 days
    When I check whether to show a notification for rego twice
    Then a notification should not be shown

  Scenario: checking whether to show notification 6
    Given that the expiry date of Licence is in 30 days
    When I check whether to show a notification for license
    Then a notification should be shown

  Scenario: checking whether to show notification 7
    Given that the expiry date of Licence is in 35 days
    When I check whether to show a notification for license
    Then a notification should not be shown

  Scenario: checking whether to show notification 8
    Given that the expiry date of Licence is in 30 days
    When I check whether to show a notification for license
    And I check whether to show a notification for license
    Then a notification should not be shown
