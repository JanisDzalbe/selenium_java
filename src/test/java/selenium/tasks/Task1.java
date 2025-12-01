package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class Task1 {
    WebDriver driver;

    @BeforeEach
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void errorOnText() {
//        TODO
//         enter a text instead of a number, check that correct error is shown
        WebElement input = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='button']")); //we identify button by type
        WebElement errorMessage = driver.findElement(By.id("ch1_error"));

        input.sendKeys("test"); //we enter text
        submitButton.click();

        String expectedError = "Please enter a number"; //check error message
        assertEquals(expectedError, errorMessage.getText());
        assertTrue(errorMessage.isDisplayed());
        //assertTrue(errorMessage.isDisplayed()); //check if error is shown but we have to check if CORRECT error shows up
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown

        WebElement input = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='button']")); //we identify button by type
        WebElement errorMessage = driver.findElement(By.id("ch1_error"));

        input.sendKeys("10"); //we enter text
        submitButton.click();

        String expectedError = "Number is too small"; //check error message
        assertEquals(expectedError, errorMessage.getText());
        assertTrue(errorMessage.isDisplayed());

        //assertTrue(errorMessage.isDisplayed()); //check if error is shown but we have to check if CORRECT error shows up
    }

    @Test
    public void errorOnNumberTooBig() {
//        TODO
//         enter number which is too big (above 100), check that correct error is shown

        WebElement input = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='button']")); //we identify button by type
        WebElement errorMessage = driver.findElement(By.id("ch1_error"));

        input.sendKeys("101"); //we enter text
        submitButton.click();

        String expectedError = "Number is too big"; //check error message
        assertEquals(expectedError, errorMessage.getText());
        assertTrue(errorMessage.isDisplayed());

        //assertTrue(errorMessage.isDisplayed()); //check if error is shown but we have to check if CORRECT error shows up
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is shown and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables
        WebElement input = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='button']")); //we identify button by type
        WebElement errorMessage = driver.findElement(By.id("ch1_error"));

        double inputNumber = 66;      //number we will input

        double expectedSquareRoot = Math.sqrt(inputNumber); // result we expect

        input.sendKeys(String.valueOf((int)inputNumber));
        submitButton.click();

        Alert alert = driver.switchTo().alert(); //switch to alert for result
        String alertText = alert.getText();     //grab alert text

        String[] parts = alertText.split(" is "); //modify string we got from alert - we only grab what comes after "is", which is the squareroot
        double actualSquareRoot = Double.parseDouble(parts[parts.length - 1]);      //convert what we read to numbers, our actual sqrt

        assertEquals(expectedSquareRoot, actualSquareRoot, 0.01); // Check that square root is calculated correctly

        // Accept the alert
        alert.accept();

        assertFalse(errorMessage.isDisplayed()); //after alert closes, check that no error is shown
    }
}
