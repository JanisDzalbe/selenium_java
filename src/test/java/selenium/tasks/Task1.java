package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class Task1 {
    WebDriver driver;

    @BeforeEach
    public void openPage() {
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
        WebElement fieldNumb = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));
        WebElement errorMes = driver.findElement(By.id("ch1_error"));

        fieldNumb.clear();
        fieldNumb.sendKeys("seven");
        submitButton.click();
        assertTrue(errorMes.isDisplayed());
        assertEquals("Please enter a number",errorMes.getText());
        }

    @Test
    public void errorOnNumberTooSmall() {
//        BUG: if I enter number 49 or 42 no errors where seen
//        TODO
//        enter number which is too small (positive number below 50), check that correct error is seen
        WebElement fieldNumb = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));
        WebElement errorMes = driver.findElement(By.id("ch1_error"));

        fieldNumb.clear();
        fieldNumb.sendKeys("45");
        submitButton.click();
        assertTrue(errorMes.isDisplayed());
        assertEquals("Number is too small",errorMes.getText());
        }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        WebElement fieldNumb = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));
        WebElement errorMes = driver.findElement(By.id("ch1_error"));

        fieldNumb.clear();
        fieldNumb.sendKeys("337");
        submitButton.click();
        assertTrue(errorMes.isDisplayed());
        assertEquals("Number is too big",errorMes.getText());
        }

    @Test
    public void correctSquareRoot() {
//        TODO
//        +enter a number between 50 and 100 digit in the input, then press submit
//        and check that no error is seen and that square root is calculated correctly
//        NOTE: input value is hardcoded, but square root used in assertions should be calculated in code
        WebElement fieldNumb = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));
        WebElement errorMes = driver.findElement(By.id("ch1_error"));


        int inputNumber = 81;
        double numberSq = Math.sqrt(inputNumber);
        String formatted = String.format("%.2f", numberSq);
        String allertExp = "Square root of " + inputNumber + " is " + formatted;

        fieldNumb.clear();
        fieldNumb.sendKeys(String.valueOf(inputNumber));
        submitButton.click();
        Alert alert = driver.switchTo().alert();
        String allertText = alert.getText();
        assertEquals(allertExp, allertText);
        alert.accept();
        assertEquals("",errorMes.getText());
    }
}
