
@tag
Feature: Purchase the ordern from E-commerce website.
  I want to use this template for my feature file

  @ErrorValidations
  Scenario Outline: Title of your scenario outline
    Given I landed on E-commerce page.
    When Logged with username <name> and password <password>
    Then "Incorrect email or password." message is displayed.

   Examples: 
      | name                                             | password                          |
      | aks96@gmail.com                        |     Akshay@55722             | 
