Feature: links can be browsed and posted

#  Scenario: user can navigate to new recommendation site
#    Given user is at the main page
#    When Lisää lukuvinkki is clicked
#    Then Lisää lukuvinkki is shown
  Scenario: user can create new links
    Given user is at the main page
    When a new nettilähde is created
    Then the new nettilähde is shown
  Scenario: user can create new links without html-scheme
    Given user is at the main page
    When a new nettilähde without html-scheme is created
    Then the new nettilähde is shown