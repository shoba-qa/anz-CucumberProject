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

	public ResourceBundle rb; // for reading properties file
	public String br; // to store browser name

	@Before
	public void setup() {
		logger.info("setting up chrome browser...");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		borrowPage = new BorrowPage(driver);
	}

	@After
	public void tearDown() {
		logger.info("closing browser...");
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

	@When("user enter the details in each input box")
	public void user_enter_the_details_in_each_input_box() {

//		Bpage.setApplicationType();
		borrowPage.setDependants();
		borrowPage.setPropertyType();
		borrowPage.setAnnIncome();
		borrowPage.setOtherIncome();
		borrowPage.setMonExp();
		borrowPage.setMonRepay();
		borrowPage.setOtherRepay();
		borrowPage.setcommittments();
		borrowPage.setcredit();

	}

	@When("user enter the annualIncome as {string}, otherIncome as {string}, expenses as {string}, otherLoanRepayments as {string}, creditCardLimit as {string}")
	public void user_enter_the_annual_income_as_other_income_as_expenses_as_other_loan_repayments_as_credit_card_limit_as(
			String annualIncome, String otherIncome, String expenses, String otherLoanRepayments,
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
	public void click_on_the_button_work_out_on_how_much_i_can_borrow() {
		borrowPage.calculatebtn();

	}

	
	@Then("User gets the borrowing estimate {string}")
	public void user_gets_the_borrowing_estimate(String expectedAmount) throws InterruptedException {
		Thread.sleep(5000);
		Assert.assertEquals(expectedAmount, borrowPage.result());
	}

	@Then("user clicks on start over")
	public void user_clicks_on_start_over() {
	    borrowPage.startoverbtn();
	}

	@Then("User gets an error message borrowing estimate not possible")
	public void user_gets_a_message_borrowing_estimate_not_possible() {
			Assert.assertEquals("Based on the details you've entered, we're unable to give you an estimate of your borrowing power with this calculator. For questions, call us on 1800 100 641.", borrowPage.message());
	}
}
