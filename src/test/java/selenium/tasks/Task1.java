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
        WebElement input = driver.findElement(By.tagName("input"));
        input.sendKeys("abc");
        driver.findElement(By.tagName("button")).click();

        WebElement error = driver.findElement(By.className("error"));
        assertTrue(error.isDisplayed(), "Error message should be visible");
        assertFalse(error.getText().isEmpty(), "Error message should not be empty");
    }

    @Test
    public void errorOnNumberTooSmall() {
        WebElement input = driver.findElement(By.tagName("input"));
        input.sendKeys("10");
        driver.findElement(By.tagName("button")).click();

        WebElement error = driver.findElement(By.className("error"));
        assertTrue(error.isDisplayed(), "Error message should be visible for number too small");
        assertFalse(error.getText().isEmpty(), "Error message should not be empty");
    }

    @Test
    public void errorOnNumberTooBig() {
        WebElement input = driver.findElement(By.tagName("input"));
        input.sendKeys("200");
        driver.findElement(By.tagName("button")).click();

        WebElement error = driver.findElement(By.className("error"));
        assertTrue(error.isDisplayed(), "Error message should be visible for number too big");
        assertFalse(error.getText().isEmpty(), "Error message should not be empty");
    }

    @Test
    public void correctSquareRoot() {
        int inputNumber = 64;
        double expectedSquareRoot = Math.round(Math.sqrt(inputNumber) * 100.0) / 100.0;
        // For 64, expectedSquareRoot = 8.0, displayed as "8.00"
        String expectedText = String.format("Square root of %d is %.2f", inputNumber, expectedSquareRoot);

        WebElement input = driver.findElement(By.tagName("input"));
        input.sendKeys(String.valueOf(inputNumber));
        driver.findElement(By.tagName("button")).click();


        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        assertEquals(expectedText, alertText,
                "Alert should show the correct square root result");

        alert.accept(); // dismiss the alert
    }
}