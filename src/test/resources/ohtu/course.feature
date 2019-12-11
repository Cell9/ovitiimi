Feature: courses can be browsed and posted

  Scenario: user can navigate to new courses site
    Given user is at the main page
    When Kurssit is clicked
    Then Lisää uusi kurssi is shown
  Scenario: user can create new courses
    Given user is at the main page
    When a new course is created
    Then the new course is shown
   Scenario: user can't add a course without a name
     Given user is at the main page
     When a new course is created without a name
     Then an error notification for missing course name is shown