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
  Scenario: user can filter recommendations by type
    Given user is at the main page
    And a new kirja is created
    When youtube type is filtered
    Then new book is not shown
  Scenario: user can filter recommendations by type and tag
    Given user is at the main page
    And a new kirja is created
    When book type and tag are filtered
    Then new book is not shown
  Scenario: user can filter recommendations by type or tag
    Given user is at the main page
    And a new kirja is created
    When book type or tag are filtered
    Then new book is not shown
  Scenario: user can edit recommendations
    Given user is at the main page
    When a new kirja is created
    And a new course is created
    And the book is clicked
    And muokkaa tietoja is clicked
    And tag is added
    Then edits are shown

