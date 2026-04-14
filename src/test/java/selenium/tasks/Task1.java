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
        WebElement inputField = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));
        WebElement errorMessage = driver.findElement(By.id("ch1_error"));

        inputField.sendKeys("a");
        submitButton.click();
        assertTrue(errorMessage.isDisplayed());
        assertEquals("Please enter a number", errorMessage.getText());

//        TODO
//         enter a text instead of a number, check that correct error is shown
    }

    @Test
    public void errorOnNumberTooSmall() {
        WebElement inputField = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));
        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        int number = 49;

        inputField.sendKeys(Integer.toString(number));
        submitButton.click();
        assertTrue(errorMessage.isDisplayed());
        assertEquals("Number is too small", errorMessage.getText());

//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown
    }

    @Test
    public void errorOnNumberTooBig() {
        WebElement inputField = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));
        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        int number = 101;

        inputField.sendKeys(Integer.toString(number));
        submitButton.click();
        assertTrue(errorMessage.isDisplayed());
        assertEquals("Number is too big", errorMessage.getText());

//        TODO
//         enter number which is too big (above 100), check that correct error is shown
    }

    @Test
    public void correctSquareRoot() {
        WebElement inputField = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));
        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        int number = 100;
        String answer = "Square root of 100 is 10.00";

        inputField.sendKeys(Integer.toString(number));
        submitButton.click();
        Alert alert = driver.switchTo().alert();
        assertEquals(answer, alert.getText());
        alert.accept();
        assertFalse(errorMessage.isDisplayed());

//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is shown and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables
    }
}
