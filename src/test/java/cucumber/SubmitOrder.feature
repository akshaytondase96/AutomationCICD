@tag
Feature: Purchase the ordern from E-commerce website.
  I want to use this template for my feature file

Background:
Given I landed on E-commerce page.
  @tag2
  Scenario Outline: Positive test of submitting the order.
    Given Logged with username <name> and password <password>
    When Add the product <productName> to cart.
    And Check out  <productName> and submit the order
    Then I verify the "THANKYOU FOR THE ORDER." msg is displayed on confirmation page

    Examples:
      | name                                             | password                          |productName        |
      | aks96@gmail.com                        |     Akshay@5572               | ZARA COAT 3        |   
                                                                                     
