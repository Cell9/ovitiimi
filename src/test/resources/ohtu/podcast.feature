Feature: podcasts can be browsed and posted

  Scenario: user can navigate to the podcast page
    Given user is at the main page
    When Podcastit is clicked
    Then Podcastit is shown
  Scenario: user can create new podcasts
    Given user is at the podcastit page
    When a new podcast is created
    Then the new podcast is shown
  Scenario: user gets a success notification after adding a podcast
    Given user is at the podcastit page
    When a new podcast is created
    Then a success notification is shown
  Scenario: user can't add a podcast without a title
    Given user is at the podcastit page
    When a faulty podcast is created
    Then an error notification for missing podcast title is shown
  Scenario: user can't add a podcast without an author
    Given user is at the podcastit page
    When a faulty podcast is created
    Then an error notification for missing podcast author is shown

