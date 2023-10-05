Feature: How much could I borrow Page

  Scenario Outline: Positive case: Check with different inputs
    Given User launch browser
    And opens URL "https://www.anz.com.au/personal/home-loans/calculators-tools/much-borrow/"
    When user enter the annualIncome as "<annualIncome>", otherIncome as "<otherIncome>", expenses as "<expenses>", otherLoanRepayments as "<otherLoanRepayments>", creditCardLimit as "<creditCardLimit>"
    And click on the button Work out on how much i can borrow
    Then User gets the borrowing estimate "$422,000"
    And user clicks on start over
    When user enter the annualIncome as "<annualIncome>", otherIncome as "<otherIncome>", expenses as "<expenses>", otherLoanRepayments as "<otherLoanRepayments>", creditCardLimit as "<creditCardLimit>"
    And click on the button Work out on how much i can borrow
    Then User gets an error message borrowing estimate not possible

    Examples: 
      | annualIncome | otherIncome | expenses | otherLoanRepayments | creditCardLimit |
      |        80000 |       10000 |      500 |                 100 |           10000 |
      |            0 |           0 |        1 |                   0 |               0 |
  #Scenario: Check with different inputs
    #Given User launch browser
    #And opens URL "https://www.anz.com.au/personal/home-loans/calculators-tools/much-borrow/"
    #When user enter the annualIncome as "80000", otherIncome as "10000", expenses as "500", otherLoanRepayments as "100", creditCardLimit as "10000"
    #And click on the button Work out on how much i can borrow
    #Then User gets the borrowing estimate "$422,000"
    #And user clicks on start over
    #When user enter the annualIncome as "0", otherIncome as "0", expenses as "1", otherLoanRepayments as "0", creditCardLimit as "0"
    #And click on the button Work out on how much i can borrow
    #Then User gets an error message borrowing estimate not possible
