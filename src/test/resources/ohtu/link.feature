Feature: links can be browsed and posted

  Scenario: user can navigate to the links page
    Given user is at the main page
    When Nettilähteet is clicked
    Then Nettilähteet is shown
  Scenario: user can create new links
    Given user is at the nettilähteet page
    When a new nettilähde is created
    Then the new nettilähde is shown
  Scenario: user can create new links without html-scheme
    Given user is at the nettilähteet page
    When a new nettilähde without html-scheme is created
    Then the new nettilähde is shown