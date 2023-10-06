package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class BorrowPage extends BasePage {

	public BorrowPage(WebDriver driver) {
		super(driver);
	}

	// Elements on the Page

	@FindBy(xpath = "//div[@class='borrow_question'][1]/ul[@class='borrowquestion_answer clearfix']/li/*[@class='btn' and @for='application_type_single']")
	WebElement eleAppTypeSingle;

	@FindBy(xpath = "//div[@class='borrow__question__answer borrow__question__answer--select']/select[@title='Number of dependants']")
	WebElement eleDependants;

	@FindBy(xpath = "//input[@type='radio' and @id='borrow_type_home']")
	WebElement eleProperty;

	@FindBy(xpath = "//input[@type='text' and @aria-describedby='q2q1i1 q2q1i2']")
	WebElement eleAnnualIncome;

	@FindBy(xpath = "//input[@type='text' and @aria-describedby='q2q2i1 q2q2i2']")
	WebElement eleOtherIncome;

	@FindBy(xpath = "//input[@id='expenses']")
	WebElement eleMonthlyExpenses;

	@FindBy(xpath = "//input[@id='homeloans']")
	WebElement eleCurrentHomeLoanRepayment;

	@FindBy(xpath = "//input[@id='otherloans']")
	WebElement eleOtherLoanRepayments;

	@FindBy(xpath = "//input[@type='text' and @aria-describedby='q3q4i1 q3q4i2']")
	WebElement eleMonthlyCommittments;

	@FindBy(id = "credit")
	WebElement eleTotalCreditCardsLimit;

	@FindBy(id = "btnBorrowCalculater")
	WebElement calcButton;

	@FindBy(id = "borrowResultTextAmount")
	WebElement resultCalc;

//Actions
	public void setApplicationType() {
		eleAppTypeSingle.click();

	}

	public void setDependants() {
		Select depEle = new Select(eleDependants);
		depEle.selectByIndex(0);

	}

	public void setPropertyType() {
		eleProperty.click();

	}

	public void setAnnIncome() {
		eleAnnualIncome.sendKeys("80000");

	}

	public void setOtherIncome() {
		eleOtherIncome.sendKeys("10000");

	}

	public void setMonExp() {
		eleMonthlyExpenses.sendKeys("500");
	}

	public void setMonRepay() {
		eleCurrentHomeLoanRepayment.sendKeys("0");
	}

	public void setOtherRepay() {
		eleOtherLoanRepayments.sendKeys("100");
	}

	public void setcommittments() {
		eleMonthlyCommittments.sendKeys("0");
	}

	public void setcredit() {
		eleTotalCreditCardsLimit.sendKeys("10000");
	}

	public void calculatebtn() {
		calcButton.click();

	}

	public String result() {
		return resultCalc.getText();
	}

	// 2. Clicking the ‘start over’ button clears the form.

	@FindBy(xpath = "//div[@class='result__restart']/button[@class='start-over']")
	WebElement btnstrover;

	public void startoverbtn() {
		btnstrover.click();
	}
	

	@FindBy(xpath = "//div[@class='borrow__error__text']")
	WebElement errorMsg;

	public String message() {
		return errorMsg.getText();
	}
	

	public void setAnnIncome(String annualIncome) {
		eleAnnualIncome.sendKeys(annualIncome);
	}

	public void setOtherIncome(String otherIncome2) {
		eleOtherIncome.sendKeys(otherIncome2);

	}

	public void setMonExp(String expenses) {
		eleMonthlyExpenses.sendKeys(expenses);
	}

	public void setOtherRepay(String otherLoanRepayments) {
		eleOtherLoanRepayments.sendKeys(otherLoanRepayments);
	}

	public void setcredit(String creditCardLimit) {
		eleTotalCreditCardsLimit.sendKeys(creditCardLimit);

	}

	public WebElement getEleAnnualIncome() {
		return eleAnnualIncome;
	}

	public void setEleAnnualIncome(WebElement eleAnnualIncome) {
		this.eleAnnualIncome = eleAnnualIncome;
	}

	public WebElement getEleOtherIncome() {
		return eleOtherIncome;
	}


	public WebElement getEleMonthlyExpenses() {
		return eleMonthlyExpenses;
	}


	public WebElement getEleCurrentHomeLoanRepayment() {
		return eleCurrentHomeLoanRepayment;
	}

	public void setEleCurrentHomeLoanRepayment(WebElement eleCurrentHomeLoanRepayment) {
		this.eleCurrentHomeLoanRepayment = eleCurrentHomeLoanRepayment;
	}

	public WebElement getEleOtherLoanRepayments() {
		return eleOtherLoanRepayments;
	}


	public WebElement getEleMonthlyCommittments() {
		return eleMonthlyCommittments;
	}


	public WebElement getEleTotalCreditCardsLimit() {
		return eleTotalCreditCardsLimit;
	}

	
	

}
