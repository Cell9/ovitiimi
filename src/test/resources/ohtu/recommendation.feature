Feature: Recommendations can be added listed and filtered

  Scenario: user can navigate to new recommendation site
    Given user is at the main page
    When Lisää lukuvinkki is clicked
    Then Lisää lukuvinkki is shown
  Scenario: user can filter recommendations
    Given user is at the main page
    And a new kirja is created
    When new book is filtered out
    Then new book is not shown

