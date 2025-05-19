package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1 {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        // Create a wait object with 10 seconds timeout
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://acctabootcamp.github.io/site/tasks/enter_a_number");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void errorOnText() {
        // Enter text instead of a number
        WebElement numberInput = driver.findElement(By.id("number"));
        numberInput.sendKeys("abc");

        // Click the submit button
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        // Wait for the error message to appear
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));

        // Check that the correct error message is displayed
        String errorText = errorMessage.getText();
        System.out.println("Error message for text input: " + errorText);
        assertEquals("Please enter a valid number", errorText);
    }

    @Test
    public void errorOnNumberTooSmall() {
        // BUG noted: entering 49 or 42 doesn't trigger errors when it should

        // Enter a number that's too small (below 50)
        WebElement numberInput = driver.findElement(By.id("number"));
        numberInput.sendKeys("49");

        // Click the submit button
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        // Wait for the error message to appear
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));

        // Check that the correct error message is displayed
        String errorText = errorMessage.getText();
        System.out.println("Error message for small number: " + errorText);
        assertEquals("Number must be at least 50", errorText);
    }

    @Test
    public void errorOnNumberTooBig() {
        // BUG noted: entering 666 doesn't trigger errors when it should

        // Enter a number that's too big (above 100)
        WebElement numberInput = driver.findElement(By.id("number"));
        numberInput.sendKeys("101");

        // Click the submit button
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        // Wait for the error message to appear
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));

        // Check that the correct error message is displayed
        String errorText = errorMessage.getText();
        System.out.println("Error message for large number: " + errorText);
        assertEquals("Number must be at most 100", errorText);
    }

    @Test
    public void correctSquareRoot() {
        // Enter a valid number between 50 and 100
        int testNumber = 64;

        WebElement numberInput = driver.findElement(By.id("number"));
        numberInput.sendKeys(String.valueOf(testNumber));

        // Click the submit button
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        // Wait for the result to appear
        WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result")));

        // Calculate the expected square root
        double expectedSqrt = Math.sqrt(testNumber);

        // Get the actual result and compare
        String resultText = resultElement.getText();
        System.out.println("Square root result: " + resultText);

        // Extract the numeric value from the result text
        String numericResult = resultText.replaceAll("[^0-9.]", "");
        double actualSqrt = Double.parseDouble(numericResult);

        // Check that no error is displayed
        WebElement errorElement = driver.findElement(By.id("error"));
        assertTrue(errorElement.getText().isEmpty(), "Error message should be empty for valid input");

        // Check the square root calculation
        assertEquals(expectedSqrt, actualSqrt, 0.001, "Square root calculation incorrect");

        // Alternative assertion method for verifying the full result message
        String expectedResultText = "Square root of " + testNumber + " is " + expectedSqrt;
        assertTrue(resultText.contains(String.valueOf(testNumber)), "Result should contain the input number");
        assertTrue(resultText.contains(String.valueOf(expectedSqrt).substring(0, 4)), "Result should contain the square root value");
    }
}