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
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;


import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

        String text = "abc";
        WebElement numInput = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));
        String expectedMessage = "Please enter a number";

        numInput.sendKeys(text);
        submitButton.click();
        assertEquals(expectedMessage, driver.findElement(By.className("error")).getText());

    }

    @Test
    public void errorOnNumberTooSmall() {
//        BUG: if I enter number 49 or 42 no errors where seen
//        TODO
//        enter number which is too small (positive number below 50), check that correct error is seen

        String number1 = "7";
        String number2 = "37";
        WebElement numInput = driver.findElement(By.id("numb"));
        WebElement errorMessage = driver.findElement(By.className("error"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));
        String expectedMessage = "Number is too small";

        numInput.clear();
        numInput.sendKeys(number1);
        submitButton.click();
        assertEquals(expectedMessage, errorMessage.getText());
        numInput.clear();
        numInput.sendKeys(number2);
        submitButton.click();
        assertEquals(expectedMessage, errorMessage.getText());
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen

        String number1 = "121";
        String number2 = "1024";
        WebElement numInput = driver.findElement(By.id("numb"));
        WebElement errorMessage = driver.findElement(By.className("error"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));
        String expectedMessage = "Number is too big";

        numInput.clear();
        numInput.sendKeys(number1);
        submitButton.click();
        assertEquals(expectedMessage, errorMessage.getText());
        numInput.clear();
        numInput.sendKeys(number2);
        submitButton.click();
        assertEquals(expectedMessage, errorMessage.getText());
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//        enter a number between 50 and 100 digit in the input, then press submit
//        and check that no error is seen and that square root is calculated correctly
//        NOTE: input value is hardcoded, but square root used in assertions should be calculated in code

        String number = "81";
        double numberValue = Double.parseDouble(number);
        double expectedSqrt = Math.sqrt(numberValue);
        String expectedText = String.format("Square root of " + number + " is %.2f", expectedSqrt);

        WebElement numInput = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));

        numInput.sendKeys(number);
        submitButton.click();
        Alert alert = driver.switchTo().alert();
        assertEquals(expectedText, alert.getText());
        alert.accept();
    }
}
