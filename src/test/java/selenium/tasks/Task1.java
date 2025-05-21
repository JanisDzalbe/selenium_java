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

        WebElement input = driver.findElement(By.id("numb"));
        WebElement submit = driver.findElement(By.className("w3-btn"));
        WebElement error = driver.findElement(By.id("ch1_error"));

        input.clear();
        input.sendKeys("heeeeellooooo");
        submit.click();

        assertTrue(error.isDisplayed());
        assertEquals("Please enter a number", error.getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        BUG: if I enter number 49 or 42 no errors where seen
//        TODO
//        enter number which is too small (positive number below 50), check that correct error is seen

        WebElement input = driver.findElement(By.id("numb"));
        WebElement submit = driver.findElement(By.className("w3-btn"));
        WebElement error = driver.findElement(By.id("ch1_error"));

        input.clear();
        input.sendKeys("42");
        submit.click();

        Alert alert = driver.switchTo().alert();
        assertEquals("Sorry you have asked the wrong answer", alert.getText());
        alert.accept();

        input.clear();
        input.sendKeys("14");
        submit.click();

        assertTrue(error.isDisplayed());
        assertEquals("Number is too small", error.getText().trim());
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen

        WebElement input = driver.findElement(By.id("numb"));
        WebElement submit = driver.findElement(By.className("w3-btn"));
        WebElement error = driver.findElement(By.id("ch1_error"));

        input.clear();
        input.sendKeys("666");
        submit.click();

        assertFalse(error.isDisplayed());

        input.clear();
        input.sendKeys("1000");
        submit.click();

        assertEquals("Number is too big", error.getText().trim());
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//        enter a number between 50 and 100 digit in the input, then press submit
//        and check that no error is seen and that square root is calculated correctly
//        NOTE: input value is hardcoded, but square root used in assertions should be calculated in code

        int inputValue = 81;
        WebElement input = driver.findElement(By.id("numb"));
        WebElement submit = driver.findElement(By.className("w3-btn"));
        WebElement error = driver.findElement(By.id("ch1_error"));

        input.clear();
        input.sendKeys(String.valueOf(inputValue));
        submit.click();

        Alert alert = driver.switchTo().alert();

        assertEquals(String.format("Square root of %d is %.2f", inputValue, Math.sqrt(inputValue)), alert.getText());
        alert.accept();

    }
}
