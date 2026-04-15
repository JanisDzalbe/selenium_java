package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Alert;
import selenium.utility.BootcampUtils;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class Task1 {
    WebDriver driver;

    @BeforeEach
    public void openPage() {
        driver = BootcampUtils.initializeEdgeDriver();
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
        WebElement input = driver.findElement(By.id("numb"));
        WebElement submit = driver.findElement(By.className("w3-orange"));
        WebElement error = driver.findElement(By.id("ch1_error"));

        String textInput = "notANumberText";
        String expectedError = "Please enter a number";

        input.sendKeys(textInput);
        submit.click();

        assertTrue(error.isDisplayed());
        assertEquals(expectedError, error.getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown
        WebElement input = driver.findElement(By.id("numb"));
        WebElement submit = driver.findElement(By.className("w3-orange"));
        WebElement error = driver.findElement(By.id("ch1_error"));

        String number = "25";
        String expectedError = "Number is too small";

        input.sendKeys(number);
        submit.click();

        assertTrue(error.isDisplayed());
        assertEquals(expectedError, error.getText());
    }

    @Test
    public void errorOnNumberTooBig() {
//        TODO
//         enter number which is too big (above 100), check that correct error is shown
        WebElement input = driver.findElement(By.id("numb"));
        WebElement submit = driver.findElement(By.className("w3-orange"));
        WebElement error = driver.findElement(By.id("ch1_error"));

        String number = "120";
        String expectedError = "Number is too big";

        input.sendKeys(number);
        submit.click();

        assertTrue(error.isDisplayed());
        assertEquals(expectedError, error.getText());
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is shown and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables
        WebElement input = driver.findElement(By.id("numb"));
        WebElement submit = driver.findElement(By.className("w3-orange"));

        int number = 80;
        double sqrt = Math.sqrt(number);

        String expectedText = "Square root of " + number + " is " + String.format("%.2f", sqrt);

        input.sendKeys(String.valueOf(number));

        WebElement error = driver.findElement(By.id("ch1_error"));
        assertFalse(error.isDisplayed());

        submit.click();

        Alert alert = driver.switchTo().alert();
        String actualText = alert.getText();

        assertEquals(expectedText, actualText);

        alert.accept();
    }
}
