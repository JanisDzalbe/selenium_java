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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        numberInput.clear();
        numberInput.sendKeys("pssss");

        WebElement submitButton = driver.findElement(By.className("w3-orange"));
        submitButton.click();

        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        assertTrue(errorMessage.isDisplayed());
        String expectedOutput = "Please enter a number";
        assertEquals(expectedOutput,driver.findElement(By.id("ch1_error")).getText());
//        TODO
//         enter a text instead of a number, check that correct error is shown
    }

    @Test
    public void errorOnNumberTooSmall() {
        WebElement numberInput = driver.findElement(By.id("numb"));
        numberInput.clear();
        numberInput.sendKeys("1");

        WebElement submitButton = driver.findElement(By.className("w3-orange"));
        submitButton.click();

        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        assertTrue(errorMessage.isDisplayed());
        String expectedOutput = "Number is too small";
        assertEquals(expectedOutput,driver.findElement(By.id("ch1_error")).getText());
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown
    }

    @Test
    public void errorOnNumberTooBig() {
        WebElement numberInput = driver.findElement(By.id("numb"));
        numberInput.clear();
        numberInput.sendKeys("150");

        WebElement submitButton = driver.findElement(By.className("w3-orange"));
        submitButton.click();

        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        assertTrue(errorMessage.isDisplayed());
        String expectedOutput = "Number is too big";
        assertEquals(expectedOutput,driver.findElement(By.id("ch1_error")).getText());
//        TODO
//         enter number which is too big (above 100), check that correct error is shown
    }

    @Test
    public void correctSquareRoot() {
        WebElement numberInput = driver.findElement(By.id("numb"));
        numberInput.clear();
        numberInput.sendKeys("150");

        WebElement submitButton = driver.findElement(By.className("w3-orange"));
        submitButton.click();

        //...

//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is shown and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables
    }
}
