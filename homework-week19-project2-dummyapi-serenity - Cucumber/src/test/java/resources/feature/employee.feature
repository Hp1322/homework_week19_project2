Feature: Testing different request on the employee

  Scenario: I get all employees from application
    Given I am on homepage of application of employee
    When I send Get request to list endpoint of employee
    Then I must get back a valid status code 200 of employee

  Scenario: I create employee from application
    Given I am on homepage of application of employee
    When I send Post request to list endpoint of employee
    Then I must get back a valid status code 200 of employee

  Scenario: I update employee from application
    Given I am on homepage of application of employee
    When I send Put request to list endpoint of employee
    Then I must get back a valid status code 200 of employee

  Scenario: I delete employee from application
    Given I am on homepage of application of employee
    When I send Delete request to list endpoint of employee
    Then I must get back a valid status code 200 of employee
    And I validate if employee is deleted

    @Test
  Scenario: I verify following data response
    Given I am on homepage of application of employee
    When I send Get request to list endpoint of employee
    Then I validate total records 24
    And I validate id "24"
    And I validate first employee_name "Doris Wilder"
    And I validate message "Successfully! All records has been fetched."
    And I validate status "success"
    And I validate employee_salary "86000"
    And I validate employee_age "61"
    And I validate employee_name "Jena Gaines"

