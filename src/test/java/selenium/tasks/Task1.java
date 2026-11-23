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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Individual Task 1: Testing number validation errors and square root calculation
 * on the "Enter a number" page.
 */
public class Task1 {
    WebDriver driver;

    /**
     * Initializes ChromeDriver and opens the "Enter a number" task page before each test.
     */
    @BeforeEach
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    /**
     * Closes the browser after each test.
     */
    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    /**
     * Enters non-numeric text into the input, clicks Submit, and verifies the error message.
     */
    @Test
    public void errorOnText() {
        // TODO
        //  enter a text instead of a number, check that correct error is shown
        driver.findElement(By.id("numb")).sendKeys("abc");
        driver.findElement(By.className("w3-orange")).click();

        assertEquals("Please enter a number", driver.findElement(By.id("ch1_error")).getText());
    }

    /**
     * Enters a positive number below 50, clicks Submit, and verifies the "too small" error message.
     */
    @Test
    public void errorOnNumberTooSmall() {
        // TODO
        //  enter number which is too small (positive number below 50), check that correct error is shown
        driver.findElement(By.id("numb")).sendKeys("49");
        driver.findElement(By.className("w3-orange")).click();

        assertEquals("Number is too small", driver.findElement(By.id("ch1_error")).getText());
    }

    /**
     * Enters a number above 100, clicks Submit, and verifies the "too big" error message.
     */
    @Test
    public void errorOnNumberTooBig() {
        // TODO
        //  enter number which is too big (above 100), check that correct error is shown
        driver.findElement(By.id("numb")).sendKeys("101");
        driver.findElement(By.className("w3-orange")).click();

        assertEquals("Number is too big", driver.findElement(By.id("ch1_error")).getText());
    }

    /**
     * Enters a valid number between 50 and 100, submits, verifies the calculated square root
     * in the alert popup, and confirms no error message is shown on the page.
     */
    @Test
    public void correctSquareRoot() {
        // TODO
        //  enter a number between 50 and 100 digit in the input, then press submit
        //  and check that no error is shown and that square root is calculated correctly
        //  NOTE: input and assertion values have to be defined as variables
        String inputNumber = "50";
        String expectedSquareRoot = "7.07";
        String expectedAlertText = "Square root of " + inputNumber + " is " + expectedSquareRoot;

        driver.findElement(By.id("numb")).sendKeys(inputNumber);
        driver.findElement(By.className("w3-orange")).click();

        Alert alert = driver.switchTo().alert();
        assertEquals(expectedAlertText, alert.getText());
        alert.accept();

        WebElement errorElement = driver.findElement(By.id("ch1_error"));
        assertFalse(errorElement.isDisplayed());
        assertEquals("", errorElement.getText());
    }
}