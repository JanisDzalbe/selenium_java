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
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

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
//        TODO
//        enter a text instead of a number, check that correct error is seen
        WebElement numInput = driver.findElement(By.id("numb"));
        WebElement errMessage = driver.findElement(By.id("ch1_error"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));

        numInput.clear();
        numInput.sendKeys("Abc");

        submitButton.click();
        assertEquals("Please enter a number", errMessage.getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        BUG: if I enter number 49 or 42 no errors where seen
//        TODO
//        enter number which is too small (positive number below 50), check that correct error is seen
        WebElement numInput = driver.findElement(By.id("numb"));
        WebElement errMessage = driver.findElement(By.id("ch1_error"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));

        numInput.clear();
        numInput.sendKeys("2");

        submitButton.click();
        assertEquals("Number is too small", errMessage.getText());
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        WebElement numInput = driver.findElement(By.id("numb"));
        WebElement errMessage = driver.findElement(By.id("ch1_error"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));

        numInput.clear();
        numInput.sendKeys("101");

        submitButton.click();
        assertEquals("Number is too big", errMessage.getText());
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//        enter a number between 50 and 100 digit in the input, then press submit
//        and check that no error is seen and that square root is calculated correctly
//        NOTE: input value is hardcoded, but square root used in assertions should be calculated in code
        WebElement numInput = driver.findElement(By.id("numb"));
        WebElement errMessage = driver.findElement(By.id("ch1_error"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));

        int inputNumber = 81;
        String formatted = String.format(Locale.US, "%.2f", Math.pow(inputNumber, 0.5));

        numInput.clear();
        numInput.sendKeys(String.valueOf(inputNumber));
        submitButton.click();
        Alert alert = driver.switchTo().alert();

        assertEquals("Square root of "+ inputNumber +" is " + formatted, alert.getText());
        alert.accept();

        assertEquals("", errMessage.getText()); // no error

    }
}
