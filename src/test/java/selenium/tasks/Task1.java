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
        // Find the input field on the website and store the found element in a variable named numInput.
        WebElement input = driver.findElement(By.id("numb"));

        // Enter text instead of a number into the input field
        input.sendKeys("Hello");

        // Click Submit
        driver.findElement(By.className("w3-orange")).click();

        // Get error message after clicked on Submit button
        String actualResult = driver.findElement(By.id("Please enter a number")).getText();

        // Verify error message that it is as expected
        assertEquals("Please enter a number", actualResult);
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown
        // Find the input field on the website and store the found element in a variable named numInput.
        WebElement numInput = driver.findElement(By.id("numb"));
        // Find input field and click
        // driver.findElement(By.id("numb")).click();

        // Enter a positive number below 50 into the input field
        numInput.sendKeys("25");
        // Find and Click Submit button
        driver.findElement(By.className("w3-orange")).click();

        // Get(find) error message
        String actualResult = driver.findElement(By.id("ch1_error")).getText();

        // Verify error message
        assertEquals("Number is too small", actualResult);
    }


    @Test
    public void errorOnNumberTooBig() {
//        TODO
//         enter number which is too big (above 100), check that correct error is shown

        // Find the input field on the website and store the found element in a variable named numInput.
        WebElement numInput = driver.findElement(By.id("numb"));

        // No need to doubleClick into the input field, because sendKeys already have a path via WebElement and knows
        // where you enter the number
        // driver.findElement(By.id("numb")).click();

        // Enter a number above 100 into the input field
        numInput.sendKeys("101");
        // Find and click Submit button
        driver.findElement(By.className("w3-orange")).click();
        // Find the error message
        String actualResult = driver.findElement(By.id("ch1_error")).getText();
        // Verify the error message
        assertEquals("Number is too big", actualResult);
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is shown and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables

        // Find the input field on the website and store the found element in a variable named numInput.
        WebElement numInput = driver.findElement(By.id("numb"));
        // Enter number into the input field
        numInput.sendKeys("55");
        // Find and Click Submit
        driver.findElement(By.className("w3-orange")).click();
        // Switch to Alert (pop-up message with the square root result)
        Alert alert = driver.switchTo().alert();
        // Get actual text from alert
        String actualResult = alert.getText();
        // Verify calculated result
        assertEquals("Square root of 55 is 7.42", actualResult);
        // Close alert
        alert.accept();
    }
}