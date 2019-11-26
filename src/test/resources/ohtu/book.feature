Feature: books can be browsed and posted

  Scenario: user can navigate to the books page
    Given user is at the main page
    When Kirjat is clicked
    Then Kirjat is shown
  Scenario: user can create new links
    Given user is at the kirjat page
    When a new kirja is created
    Then the new kirja is shown