package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1 {
    WebDriver driver;
    WebElement inputBox, errorElement, submitButton;
    int smallNumber = 3, bigNumber = 345, correctNumber = 67;

    @BeforeEach
    public void openPage() {
        driver = BootcampUtils.initializeChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
        inputBox = driver.findElement(By.id("numb"));
        errorElement = driver.findElement(By.id("ch1_error"));
        submitButton = driver.findElement(By.className("w3-btn"));
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void errorOnText() {
//        TODO
//         enter a text instead of a number, check that correct error is shown
        inputBox.sendKeys("asdf");
        submitButton.click();
        assertEquals("Please enter a number", errorElement.getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown
        inputBox.sendKeys(String.format("%d", smallNumber));
        submitButton.click();
        assertEquals("Number is too small", errorElement.getText());
    }

    @Test
    public void errorOnNumberTooBig() {
//        TODO
//         enter number which is too big (above 100), check that correct error is shown
        inputBox.sendKeys(String.format("%d", bigNumber));
        submitButton.click();
        assertEquals("Number is too big", errorElement.getText());
    }

    @Test
    public void correctSquareRoot() {

//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
        inputBox.sendKeys(String.format("%d", correctNumber));
//         and check that no error is shown and that square root is calculated correctly
        submitButton.click();
        Alert alert = driver.switchTo().alert();
        assertEquals(String.format("Square root of %d is %.2f", correctNumber, Math.sqrt(correctNumber)), alert.getText());
        alert.accept();

        assertEquals("", errorElement.getText());
//         NOTE: input and assertion values have to be defined as variables
    }
}
