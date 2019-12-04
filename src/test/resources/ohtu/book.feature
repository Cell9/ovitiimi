Feature: books can be browsed and posted

  Scenario: user can navigate to new recommendation site
    Given user is at the main page
    When Lisää lukuvinkki is clicked
    Then Lisää lukuvinkki is shown
  Scenario: user can create new books
    Given user is at the main page
    When a new kirja is created
    Then the new kirja is shown
  Scenario: user gets a success notification after adding a book
    Given user is at the main page
    When a new kirja is created
    Then a success notification is shown
  Scenario: user can't add a book without a title
    Given user is at the main page
    When a faulty kirja is created
    Then an error notification for missing title is shown
  Scenario: user can't add a book without an author
    Given user is at the main page
    When a faulty kirja is created
    Then an error notification for missing author is shown