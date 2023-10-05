package testRunner;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.asynchttpclient.util.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.PathMatcher;
import java.sql.SQLOutput;
import java.time.Duration;
import java.util.*;
import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) throws IOException, InvalidFormatException
    {


        WebDriver driver = new ChromeDriver();
        driver.get("https://www.anz.com.au/personal/home-loans/calculators-tools/much-borrow/");
        WebElement e = driver.findElement(By.xpath("//div[@class='borrow__question'][1]/ul[@class='borrow__question__answer clearfix']/li/*[@class='btn' and @for='application_type_single']"));
        e.click();

        String dependentsXpath = "//select[@title='Number of dependants']";
        new Select(driver.findElement(By.xpath(dependentsXpath))).selectByIndex(0);

        String propertyType = "//input[@type='radio' and @id='borrow_type_home']";
        driver.findElement(By.xpath(propertyType)).click();

        String annualIncome = "//input[@type='text' and @aria-describedby='q2q1i1 q2q1i2']";
        driver.findElement(By.xpath(annualIncome)).sendKeys("80000");

        String annualOtherIncome = "//input[@type='text' and @aria-describedby='q2q2i1 q2q2i2']";
        driver.findElement(By.xpath(annualOtherIncome)).sendKeys("10000");

        String livingExpenses = "//input[@type='text' and @id='expenses']";
        driver.findElement(By.xpath(livingExpenses)).sendKeys("500");

        String currentHomeLoanRepayments = "//input[@type='text' and @id='homeloans']";
        driver.findElement(By.xpath(currentHomeLoanRepayments)).sendKeys("0");

        String otherLoanRepayments = "//input[@type='text' and @id='otherloans']";
        driver.findElement(By.xpath(otherLoanRepayments)).sendKeys("100");

        String otherCommitments = "//input[@type='text' and @aria-describedby='q3q4i1 q3q4i2']";
        driver.findElement(By.xpath(otherCommitments)).sendKeys("0");

        String totalCreditCardLimits = "//input[@type='text' and @id='credit']";
        driver.findElement(By.xpath(totalCreditCardLimits)).sendKeys("10000");

        String workOutButton = "//button[@class='btn btn--action btn--borrow__calculate']";
        driver.findElement(By.xpath(workOutButton)).click();

        String calculatedAmount = "//span[@id='borrowResultTextAmount']";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        System.out.println(driver.findElement(By.xpath(calculatedAmount)).getText());
        assert("$479,000".equalsIgnoreCase(driver.findElement(By.xpath(calculatedAmount)).getText()));


//        f.	living expenses $500,
//        g.	current home loan repayments $0,
//        h.	other loan repayments $100,
//        i.	other commitments $0
//        j.	and total credit card limits $10,000


        //ul[@class='borrow__question__answer clearfix' and @aria-labelledby='q1q3']/input[@type='radio' and @id='borrow_type_home']

//li/*[@class='btn' and @for='borrow_type_home']

//        writeToXl();
//        readFromExcel();
    }

    public static void readFromExcel() throws IOException {
        FileInputStream file = new FileInputStream("test_data.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook(file);

        XSSFSheet sheet = workbook.getSheet("Sheet1");  //workbook.getSheetAt(0);

        int totalrows = sheet.getLastRowNum();
        int totalcells = sheet.getRow(1).getLastCellNum();

        System.out.println("Number of rows:" + totalrows); //5
        System.out.println("Number of cells:" + totalcells);  //4

        for (int r = 0; r <= totalrows; r++) {
            XSSFRow currentRow = sheet.getRow(r);
            for (int c = 0; c < totalcells; c++) {
                String value = currentRow.getCell(c).toString();
                System.out.print(value + "      ");
            }
            System.out.println();
        }

    }

    public static void writeToXl() throws IOException, InvalidFormatException {
        XSSFWorkbook excelFile = new XSSFWorkbook(new FileInputStream("data.xlsx"));
        XSSFSheet sheet = excelFile.getSheet("Sheet1");
        sheet.createRow(3).createCell(0).setCellValue("TC_4_Fromm_code");
        FileOutputStream out = new FileOutputStream("data.xlsx");
        excelFile.write(out);
        out.close();
        excelFile.close();
    }




}