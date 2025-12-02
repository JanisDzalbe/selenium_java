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
//         enter a text instead of a number, check that correct error is shown
        WebElement inputField = driver.findElement(By.id("numb"));
        WebElement errorText = driver.findElement(By.id("ch1_error"));
        WebElement submitButton = driver.findElement(By.tagName("button"));

        String input = "asd";
        inputField.sendKeys(input);
        submitButton.click();

        assertEquals("Please enter a number", errorText.getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//         enter number which is too small (positive number below 50), check that correct error is shown
        WebElement inputField = driver.findElement(By.id("numb"));
        WebElement errorText = driver.findElement(By.id("ch1_error"));
        WebElement submitButton = driver.findElement(By.tagName("button"));

        String input = "49";
        inputField.sendKeys(input);
        submitButton.click();

        assertEquals("Number is too small", errorText.getText());
    }

    @Test
    public void errorOnNumberTooBig() {
//         enter number which is too big (above 100), check that correct error is shown
        WebElement inputField = driver.findElement(By.id("numb"));
        WebElement errorText = driver.findElement(By.id("ch1_error"));
        WebElement submitButton = driver.findElement(By.tagName("button"));

        String input = "100.0001";
        inputField.sendKeys(input);
        submitButton.click();

        assertEquals("Number is too big", errorText.getText());
    }

    @Test
    public void correctSquareRoot() {
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is shown and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables
        WebElement inputField = driver.findElement(By.id("numb"));
        WebElement errorText = driver.findElement(By.id("ch1_error"));
        WebElement submitButton = driver.findElement(By.tagName("button"));

        String input = "76";
        inputField.sendKeys(input);
        submitButton.click();

        Alert alert = driver.switchTo().alert();
        double sqrt = Math.sqrt(Double.parseDouble(input));
        System.out.printf("Calculated: %s, Rounded: %.2f", sqrt, sqrt);

        String alertAssertion = String.format("Square root of %s is %.2f", input, sqrt);
        assertEquals(alertAssertion, alert.getText());
        alert.accept();

        assertFalse(errorText.isDisplayed());
    }
}
