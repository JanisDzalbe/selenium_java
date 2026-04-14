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
import java.text.DecimalFormat;

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
        WebElement inputNumber =driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-btn"));
        WebElement errorText = driver.findElement(By.id("ch1_error"));
        inputNumber.sendKeys("abc");
        submitButton.click();
        assertTrue(errorText.isDisplayed());
        assertEquals("Please enter a number", errorText.getText());

    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown
        WebElement inputNumber =driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-btn"));
        WebElement errorText = driver.findElement(By.id("ch1_error"));
        inputNumber.sendKeys("33");
        submitButton.click();
        assertTrue(errorText.isDisplayed());
        assertEquals("Number is too small", errorText.getText());
    }

    @Test
    public void errorOnNumberTooBig() {
//        TODO
//         enter number which is too big (above 100), check that correct error is shown
        WebElement inputNumber =driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-btn"));
        WebElement errorText = driver.findElement(By.id("ch1_error"));
        inputNumber.sendKeys("300");
        submitButton.click();
        assertTrue(errorText.isDisplayed());
        assertEquals("Number is too big", errorText.getText());
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is shown and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables
        int inputText = 64;
        WebElement inputNumber =driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-btn"));
        WebElement errorText = driver.findElement(By.id("ch1_error"));
        inputNumber.sendKeys(String.valueOf(inputText));
        submitButton.click();
        Alert alert = driver.switchTo().alert();
        double squareText = Math.sqrt(inputText);
        DecimalFormat df = new DecimalFormat("#.00");
        String expectedAlertText = "Square root of " + inputText + " is " + df.format(squareText);
        assertEquals(expectedAlertText, alert.getText());
        assertFalse(errorText.isDisplayed());

    }
}
