Feature: How much could I borrow Page

  @sanity
  Scenario Outline: Check with the given inputs
    Given User launch browser
    And opens URL "https://www.anz.com.au/personal/home-loans/calculators-tools/much-borrow/"
    When user enter the annualIncome as "<annualIncome>", otherIncome as "<otherIncome>", expenses as "<expenses>", otherLoanRepayments as "<otherLoanRepayments>", creditCardLimit as "<creditCardLimit>"
    And click on the button Work out on how much i can borrow
    Then calculated value is as same as the expected value "$479,000"
    And on clicking start over button the form resets

    Examples: 
      | annualIncome | otherIncome | expenses | otherLoanRepayments | creditCardLimit |
      |        80000 |       10000 |      500 |                 100 |           10000 |

  @sanity
  Scenario Outline: Check with input where livivngExpenses are $1
    Given User launch browser
    And opens URL "https://www.anz.com.au/personal/home-loans/calculators-tools/much-borrow/"
    When user enter the annualIncome as "<annualIncome>", otherIncome as "<otherIncome>", expenses as "<expenses>", otherLoanRepayments as "<otherLoanRepayments>", creditCardLimit as "<creditCardLimit>"
    And click on the button Work out on how much i can borrow
    Then User gets the message displayed
    And Validate the message displayed

    Examples: 
      | annualIncome | otherIncome | expenses | otherLoanRepayments | creditCardLimit |
      |            0 |           0 |        1 |                   0 |               0 |
