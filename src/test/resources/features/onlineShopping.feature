@Assignment
Feature: Online Shopping

  @Scenario1
  Scenario: Verify that user cannot log in with valid but not registered email
    Given user goes to the home page of title "Demo Site"
    And user goes to the women page
    When user selects the filter by rating option
    Then user saves the first product name and price of the list page
    When user click the first product of the list page
    Then verify product details are same on the product list and detail page
    When user adds item to the cart of quantity "2"
    And user clicks the view cart button
    Then verify product details are same on the product detail and cart page
    When user changes the address as following data
      | country | state       | city   | pinCode |
      | India   | Maharashtra | Mumbai | 400072  |
    Then user clicks the proceed to checkout button