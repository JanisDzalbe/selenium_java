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
//       TODO
//        enter a text instead of a number, check that correct error is seen
        WebElement numbForm = driver.findElement(By.id("numb"));
        numbForm.sendKeys("text");
        driver.findElement(By.className("w3-btn")).click();

        assertEquals("Please enter a number", driver.findElement(By.className("error")).getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        BUG: if I enter number 49 or 42 no errors where seen
//       TODO
//        enter number which is too small (positive number below 50), check that correct error is seen
        WebElement numbForm = driver.findElement(By.id("numb"));
        WebElement error = driver.findElement(By.className("error"));
        WebElement sumbitButton = driver.findElement(By.className("w3-btn"));
        String expectedError = "Number is too small";

        numbForm.clear();
        numbForm.sendKeys("48");
        sumbitButton.click();
        assertEquals(expectedError, error.getText());
        numbForm.clear();
        numbForm.sendKeys("1");
        sumbitButton.click();
        assertEquals(expectedError, error.getText());
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//       TODO
//        enter number which is too big (above 100), check that correct error is seen
        WebElement numbForm = driver.findElement(By.id("numb"));
        WebElement error = driver.findElement(By.className("error"));
        WebElement sumbitButton = driver.findElement(By.className("w3-btn"));
        String expectedError = "Number is too big";

        numbForm.clear();
        numbForm.sendKeys("101");
        sumbitButton.click();
        assertEquals("Number is too big", error.getText());
        numbForm.clear();
        numbForm.sendKeys("1000");
        sumbitButton.click();
        assertEquals(expectedError, error.getText());
    }

    @Test
    public void correctSquareRoot() {
//       TODO
//        enter a number between 50 and 100 digit in the input, then press submit
//        and check that no error is seen and that square root is calculated correctly
//       NOTE: input value is hardcoded, but square root used in assertions should be calculated in code
        WebElement numbForm = driver.findElement(By.id("numb"));
        WebElement error = driver.findElement(By.className("error"));
        WebElement sumbitButton = driver.findElement(By.className("w3-btn"));

        numbForm.clear();
        numbForm.sendKeys("100");
        sumbitButton.click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of 100 is " + String.format(Locale.US, "%.2f", Math.sqrt(100)), alert.getText());
        alert.accept();
        assertFalse(error.isDisplayed());

        numbForm.clear();
        numbForm.sendKeys("50");
        sumbitButton.click();
        alert = driver.switchTo().alert();
        assertEquals("Square root of 50 is " + String.format(Locale.US, "%.2f", Math.sqrt(50)), alert.getText());
        alert.accept();
        assertFalse(error.isDisplayed());

        numbForm.clear();
        numbForm.sendKeys("64");
        sumbitButton.click();
        alert = driver.switchTo().alert();
        assertEquals("Square root of 64 is " + String.format(Locale.US, "%.2f", Math.sqrt(64)), alert.getText());
        alert.accept();
        assertFalse(error.isDisplayed());

        numbForm.clear();
        numbForm.sendKeys("75");
        sumbitButton.click();
        alert = driver.switchTo().alert();
        assertEquals("Square root of 75 is " + String.format(Locale.US, "%.2f", Math.sqrt(75)), alert.getText());
        alert.accept();
        assertFalse(error.isDisplayed());
    }
}
