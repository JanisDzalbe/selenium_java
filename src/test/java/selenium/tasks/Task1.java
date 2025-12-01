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
        WebElement numberInput = driver.findElement(By.id("numb"));
        driver.findElement(By.className("w3-btn w3-orange w3-margin")).click();
        WebElement resultText = driver.findElement(By.id("ch1_error"));

        numberInput.sendKeys("7");

        assertEquals("Please enter a number", resultText.getText());
//        TODO
//         enter a text instead of a number, check that correct error is shown
    }

    @Test
    public void errorOnNumberTooSmall() {
        WebElement numberInput = driver.findElement(By.id("numb"));
        driver.findElement(By.className("w3-btn w3-orange w3-margin")).click();
        WebElement resultText = driver.findElement(By.id("ch1_error"));
        numberInput.sendKeys("7");

        assertEquals("Number is too small", resultText.getText());
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown
    }

    @Test
    public void errorOnNumberTooBig() {
        WebElement numberInput = driver.findElement(By.id("numb"));
        driver.findElement(By.className("w3-btn w3-orange w3-margin")).click();
        WebElement resultText = driver.findElement(By.id("ch1_error"));
        numberInput.sendKeys("55");

        assertEquals("Number is too big", resultText.getText());
//        TODO
//         enter number which is too big (above 100), check that correct error is shown
    }

    @Test
    public void correctSquareRoot() {

        WebElement numberInput = driver.findElement(By.id("numb"));
        numberInput.sendKeys("64");
        driver.findElement(By.className("w3-btn w3-orange w3-margin")).click();
        Alert firstAlert = driver.switchTo().alert();
        String inputValue = numberInput.getAttribute("value");
        double num = Double.parseDouble(inputValue);
        double result = Math.sqrt(num);
        String expectedText = "Square root of " + num + " is " + result;
        assertEquals(expectedText, firstAlert.getText());

        firstAlert.accept();


//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is shown and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables
    }
}
