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
        WebElement input = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit']"));
        WebElement errorText = driver.findElement(By.id("ch1_error"));

        input.clear();
        input.sendKeys("abc");
        submitButton.click();

        assertEquals("Please enter a number", errorText.getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
        WebElement input = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit']"));
        WebElement errorText = driver.findElement(By.id("ch1_error"));

        input.clear();
        input.sendKeys("25");
        submitButton.click();

        assertEquals("Number is too small", errorText.getText());
    }

    @Test
    public void errorOnNumberTooBig() {
        WebElement input = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit']"));
        WebElement errorText = driver.findElement(By.id("ch1_error"));

        input.clear();
        input.sendKeys("150");
        submitButton.click();

        assertEquals("Number is too big", errorText.getText());
    }

    @Test
    public void correctSquareRoot() {
        // input and assertion values defined as variables
        int inputNumber = 64;
        String expectedAlertText = "Square root of " + inputNumber + " is 8.00";

        WebElement input = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit']"));

        input.clear();
        input.sendKeys(String.valueOf(inputNumber));
        submitButton.click();

        // the result shows in a JS alert, not on the page
        Alert alert = driver.switchTo().alert();
        String actualAlertText = alert.getText();
        System.out.println("Alert text: " + actualAlertText);
        assertEquals(expectedAlertText, actualAlertText);
        alert.accept();

        // also check that no error is shown on the page
        WebElement errorText = driver.findElement(By.id("ch1_error"));
        assertEquals("", errorText.getText());
    }
}