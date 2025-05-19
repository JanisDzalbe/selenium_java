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
        WebElement input = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));
        WebElement result = driver.findElement(By.id("ch1_error"));

        input.clear();
        input.sendKeys("Ruslan");
        submitButton.click();

        assertTrue(result.isDisplayed());
        assertEquals("Please enter a number", result.getText());
//        TODO
//        enter a text instead of a number, check that correct error is seen
    }

    @Test
    public void errorOnNumberTooSmall() {
        WebElement input = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));
        WebElement result = driver.findElement(By.id("ch1_error"));

        input.clear();
        input.sendKeys("29");
        submitButton.click();

        assertTrue(result.isDisplayed());
        assertEquals("Number is too small", result.getText());
//        BUG: if I enter number 49 or 42 no errors where seen
//        TODO
//        enter number which is too small (positive number below 50), check that correct error is seen
    }

    @Test
    public void errorOnNumberTooBig() {
        WebElement input = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));
        WebElement result = driver.findElement(By.id("ch1_error"));

        input.clear();
        input.sendKeys("665");
        submitButton.click();

        assertTrue(result.isDisplayed());
        assertEquals("Number is too big", result.getText());

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
    }

    @Test
    public void correctSquareRoot() {
        WebElement input = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));


        int number = 64;
        double expectedSquareRoot = Math.sqrt(number);
        //I spent so much time trying to format 8,00 to 8.00, I didn't understand why separator is like that
        String expectedMessage = "Square root of " + number + " is " + String.format(Locale.US,"%.2f", expectedSquareRoot);

        input.clear();
        input.sendKeys(String.valueOf(number));
        submitButton.click();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        assertEquals(expectedMessage, alertText);
        alert.accept();


//        TODO
//        enter a number between 50 and 100 digit in the input, then press submit
//        and check that no error is seen and that square root is calculated correctly
//        NOTE: input value is hardcoded, but square root used in assertions should be calculated in code
    }
}
