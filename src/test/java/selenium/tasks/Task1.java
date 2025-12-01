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
//        TODO
//         enter a text instead of a number, check that correct error is shown
        WebElement inputArea = driver.findElement(By.className("w3-input"));
        String inputNumber = "abc";
        inputArea.sendKeys(inputNumber);
        driver.findElement(By.className("w3-btn")).click();
        driver.findElement(By.id("ch1_error")).isDisplayed();
        assertEquals("Please enter a number", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown
        WebElement inputArea = driver.findElement(By.className("w3-input"));
        String inputNumber = "15";
        inputArea.sendKeys(inputNumber);
        driver.findElement(By.className("w3-btn")).click();
        driver.findElement(By.id("ch1_error")).isDisplayed();
        assertEquals("Number is too small", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooBig() {
//        TODO
//         enter number which is too big (above 100), check that correct error is shown
        WebElement inputArea = driver.findElement(By.className("w3-input"));
        String inputNumber = "9999999999999999999999999";
        inputArea.sendKeys(inputNumber);
        driver.findElement(By.className("w3-btn")).click();
        driver.findElement(By.id("ch1_error")).isDisplayed();
        assertEquals("Number is too big", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is shown and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables
        WebElement inputArea = driver.findElement(By.className("w3-input"));
        WebElement errorMsg = driver.findElement(By.id("ch1_error"));
        String inputNumber = "88";
        double squareRoot = Math.sqrt(Double.parseDouble(inputNumber));

        inputArea.sendKeys(inputNumber);
        driver.findElement(By.className("w3-btn")).click();

        Alert alert = driver.switchTo().alert();
        String expectedAlertMsg = String.format(Locale.US,
                "Square root of %s is %.2f", inputNumber, squareRoot);
        String actualAlertMsg = alert.getText();

        assertEquals(expectedAlertMsg, actualAlertMsg);
        alert.accept();
        assertFalse(errorMsg.isDisplayed());
    }
}
