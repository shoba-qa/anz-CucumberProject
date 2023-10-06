package stepDefinitions;

import java.time.Duration;
import java.util.ResourceBundle;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.manager.SeleniumManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.BorrowPage;

public class Steps {
	public static Logger logger = LoggerFactory.getLogger(Steps.class);
	WebDriver driver;
	BorrowPage borrowPage;
	
	SoftAssert softAssert;
	

	public ResourceBundle rb; // for reading properties file
	public String br; // to store browser name

	@Before
	public void setup() {
		logger.info("setting up chrome browser...");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		borrowPage = new BorrowPage(driver);
		softAssert = new SoftAssert();
	}

	@After
	public void tearDown() {
		logger.info("closing browser...");
		softAssert.assertAll();
		// driver.quit();
	}

	@Given("User launch browser")
	public void user_launch_browser() {
//		driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@Given("opens URL {string}")
	public void opens_url(String url) {

		driver.get(url);
		driver.manage().window().maximize();

	}

	@When("user enter the annualIncome as {string}, otherIncome as {string}, expenses as {string}, otherLoanRepayments as {string}, creditCardLimit as {string}")
	public void inputDetails(String annualIncome, String otherIncome, String expenses, String otherLoanRepayments,
			String creditCardLimit) {
//		Bpage.setApplicationType();
		borrowPage.setDependants();
		borrowPage.setPropertyType();
		borrowPage.setAnnIncome(annualIncome);
		borrowPage.setOtherIncome(otherIncome);
		borrowPage.setMonExp(expenses);
		borrowPage.setMonRepay();
		borrowPage.setOtherRepay(otherLoanRepayments);
		borrowPage.setcommittments();
		borrowPage.setcredit(creditCardLimit);
	}

	@When("click on the button Work out on how much i can borrow")
	public void click_how_much_i_can_borrow() {
		borrowPage.calculatebtn();

	}

//	@Then("User gets the borrowing estimate {string}")
//	public void user_gets_the_borrowing_estimate(String expectedAmount) throws InterruptedException {
//		Thread.sleep(5000);
//		Assert.assertEquals(expectedAmount, borrowPage.result());
//	}

	@Then("calculated value is as same as the expected value {string}")
	public void calculated_value_is_as_same_as_the_expected_value_$(String expectedValue) throws InterruptedException {
		Thread.sleep(5000);
		softAssert.assertEquals(expectedValue, borrowPage.result());
	}

	@Then("on clicking start over button the form resets")
	public void on_clicking_start_over_link_the_form_resets() {
		borrowPage.startoverbtn();
		softAssert.assertEquals("", borrowPage.getEleAnnualIncome().getText());
		softAssert.assertEquals("", borrowPage.getEleCurrentHomeLoanRepayment().getText());
		softAssert.assertEquals("", borrowPage.getEleMonthlyCommittments().getText());
		softAssert.assertEquals("", borrowPage.getEleMonthlyExpenses().getText());
		softAssert.assertEquals("", borrowPage.getEleOtherIncome().getText());
		softAssert.assertEquals("", borrowPage.getEleOtherLoanRepayments().getText());
		softAssert.assertEquals("", borrowPage.getEleTotalCreditCardsLimit().getText());
		
	}

		@Then("User gets an error message borrowing estimate not possible")
	public void user_gets_a_message_borrowing_estimate_not_possible() {
			softAssert.assertEquals(
				"Based on the details you've entered, we're unable to give you an estimate of your borrowing power with this calculator. For questions, call us on 1800 100 641.",
				borrowPage.message());
	}

	@Then("User gets the message displayed")
	public void user_gets_the_message_displyed() {

	}

	@Then("Validate the message displayed")
	public void validate_the_message_displayed() {

	}
}
