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
//        TODO
//         enter a text instead of a number, check that correct error is shown
        WebElement inputText = driver.findElement(By.id("numb"));
        WebElement errorMsg = driver.findElement(By.id("ch1_error"));
        WebElement submitButton = driver.findElement(By.xpath("//*[text()='Submit']"));

        inputText.sendKeys("test");
        submitButton.click();
        assertTrue(errorMsg.isDisplayed());
        assertEquals("Please enter a number", errorMsg.getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown
        WebElement inputText = driver.findElement(By.id("numb"));
        WebElement errorMsg = driver.findElement(By.id("ch1_error"));
        WebElement submitButton = driver.findElement(By.xpath("//*[text()='Submit']"));
        inputText.sendKeys("45");
        submitButton.click();
        assertTrue(errorMsg.isDisplayed());
        assertEquals("Number is too small", errorMsg.getText());
    }

    @Test
    public void errorOnNumberTooBig() {
//        TODO
//         enter number which is too big (above 100), check that correct error is shown
        WebElement inputText = driver.findElement(By.id("numb"));
        WebElement errorMsg = driver.findElement(By.id("ch1_error"));
        WebElement submitButton = driver.findElement(By.xpath("//*[text()='Submit']"));
        inputText.sendKeys("110");
        submitButton.click();
        assertTrue(errorMsg.isDisplayed());
        assertEquals("Number is too big", errorMsg.getText());
    }

    @Test
    public void correctSquareRoot(){
        WebElement inputText = driver.findElement(By.id("numb"));
        WebElement errorMsg = driver.findElement(By.id("ch1_error"));
        WebElement submitButton = driver.findElement(By.xpath("//*[text()='Submit']"));
        int testNumber = 60;
//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
        inputText.sendKeys(Integer.toString(testNumber));
        submitButton.click();
//         and check that no error is shown and that square root is calculated correctly
        Alert alert = driver.switchTo().alert();
        String alertMsg= alert.getText();
        double result = Math.round(Math.sqrt(testNumber) * 100.0) / 100.0;
        assertEquals("Square root of "+testNumber+" is "+ result, alertMsg);
        alert.accept();
        assertEquals("", errorMsg.getText());
//         NOTE: input and assertion values have to be defined as variables
    }
}
