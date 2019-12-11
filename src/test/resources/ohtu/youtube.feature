Feature: Youtube videos can be browsed and posted

  Scenario: user can create new youtubes
    Given user is at the main page
    When a new youtube is created
    Then a new youtube is shown
  Scenario: user gets a success notification after adding a youtube
    Given user is at the main page
    When a new youtube is created
    Then a success notification is shown
  # Scenario: user can't add a podcast without a title
  #   Given user is at the main page
  #   When a faulty youtube is created
  #   Then an error notification for missing youtube title is shown
  Scenario: user can't add a youtube without an author
    Given user is at the main page
    When a faulty youtube is created
    Then an error notification for missing youtube author is shown
  # Scenario: user can't add a youtube without an url
  #   Given user is at the main page
  #   When a faulty youtube is created
  #   Then an error notification for missing youtube url is shown    
  # Scenario: user can't add a youtube without a description
  #   Given user is at the main page
  #   When a faulty youtube is created
  #   Then an error notification for missing youtube description is shown
