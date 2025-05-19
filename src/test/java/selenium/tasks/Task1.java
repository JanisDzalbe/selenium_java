package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Alert;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1 {
    WebDriver driver;

    @BeforeEach
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://acctabootcamp.github.io/site/tasks/enter_a_number");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void errorOnText() {
        WebElement numInput = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-btn"));
        WebElement resultText = driver.findElement(By.id("ch1_error"));

        numInput.clear();
        numInput.sendKeys("okj");
        submitButton.click();
        assertEquals("Please enter a number", resultText.getText());

//        TODO
//        enter a text instead of a number, check that correct error is seen
    }

    @Test
    public void errorOnNumberTooSmall() {
        WebElement numInput = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-btn"));
        WebElement resultText = driver.findElement(By.id("ch1_error"));

        numInput.clear();
        numInput.sendKeys("29");
        submitButton.click();
        assertEquals("Number is too small", resultText.getText());

//        BUG: if I enter number 49 or 42 no errors where seen
//        TODO
//        enter number which is too small (positive number below 50), check that correct error is seen
    }

    @Test
    public void errorOnNumberTooBig() {
        WebElement numInput = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-btn"));
        WebElement resultText = driver.findElement(By.id("ch1_error"));

        numInput.clear();
        numInput.sendKeys("101");
        submitButton.click();
        assertEquals("Number is too big", resultText.getText());

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
    }

    @Test
    public void correctSquareRoot() {
        WebElement numInput = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-btn"));
        WebElement errorText = driver.findElement(By.id("ch1_error"));

        numInput.clear();
        numInput.sendKeys("88");
        assertEquals("", errorText.getText());
        submitButton.click();

        Alert alert = driver.switchTo().alert();

        double input = 88;
        double sqrt = Math.sqrt(input);
        String expected = String.format("Square root of %.0f is %.2f", input, sqrt);

        assertEquals(expected, alert.getText());
        driver.switchTo().alert().accept();

//        TODO
//        enter a number between 50 and 100 digit in the input, then press submit
//        and check that no error is seen and that square root is calculated correctly
//        NOTE: input value is hardcoded, but square root used in assertions should be calculated in code
    }
}
