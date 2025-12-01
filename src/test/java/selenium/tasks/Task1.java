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
        WebElement numberInput = driver.findElement(By.id("numb"));
        WebElement ch1 = driver.findElement(By.id("ch1_error"));
        WebElement submitButton = driver.findElement(By.className("w3-btn"));

        numberInput.clear();
        numberInput.sendKeys("a");

        submitButton.click();

        assertEquals("Please enter a number", ch1.getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown

        WebElement numberInput = driver.findElement(By.id("numb"));
        WebElement ch1 = driver.findElement(By.id("ch1_error"));
        WebElement submitButton = driver.findElement(By.className("w3-btn"));

        numberInput.clear();
        numberInput.sendKeys("1");

        submitButton.click();

        assertEquals("Number is too small", ch1.getText());
    }

    @Test
    public void errorOnNumberTooBig() {
//        TODO
//         enter number which is too big (above 100), check that correct error is shown

        WebElement numberInput = driver.findElement(By.id("numb"));
        WebElement ch1 = driver.findElement(By.id("ch1_error"));
        WebElement submitButton = driver.findElement(By.className("w3-btn"));

        numberInput.clear();
        numberInput.sendKeys("105");

        submitButton.click();

        assertEquals("Number is too big", ch1.getText());
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is shown and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables

        WebElement numberInput = driver.findElement(By.id("numb"));
        WebElement ch1 = driver.findElement(By.id("ch1_error"));
        WebElement submitButton = driver.findElement(By.className("w3-btn"));

        int inputValue = 65;
        
        numberInput.clear();
        numberInput.sendKeys(String.valueOf(inputValue));

        submitButton.click();

        Alert alert = driver.switchTo().alert();

        String squareRoot = String.format("%.2f", Math.sqrt(inputValue));
        String expectedAlertText = "Square root of " + inputValue + " is " + squareRoot;

        assertEquals(expectedAlertText, alert.getText());

        alert.accept();

        assertFalse(ch1.isDisplayed());
    }
}
