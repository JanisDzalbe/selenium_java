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
        WebElement numField = driver.findElement(By.id("numb"));
        WebElement submitBtn = driver.findElement(By.className("w3-orange"));
        WebElement error = driver.findElement(By.id("ch1_error"));

        numField.sendKeys("aa");
        submitBtn.click();

        assertEquals("Please enter a number", error.getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown
        WebElement numField = driver.findElement(By.id("numb"));
        WebElement submitBtn = driver.findElement(By.className("w3-orange"));
        WebElement error = driver.findElement(By.id("ch1_error"));

        numField.sendKeys("1");
        submitBtn.click();

        assertEquals("Number is too small", error.getText());
    }

    @Test
    public void errorOnNumberTooBig() {
//        TODO
//         enter number which is too big (above 100), check that correct error is shown
        WebElement numField = driver.findElement(By.id("numb"));
        WebElement submitBtn = driver.findElement(By.className("w3-orange"));
        WebElement error = driver.findElement(By.id("ch1_error"));

        numField.sendKeys("9001");
        submitBtn.click();

        assertEquals("Number is too big", error.getText());
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is shown and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables
        WebElement numField = driver.findElement(By.id("numb"));
        WebElement submitBtn = driver.findElement(By.className("w3-orange"));
        WebElement error = driver.findElement(By.id("ch1_error"));

        String input = "67";
        numField.sendKeys(input);
        submitBtn.click();

        Alert answerAlert =  driver.switchTo().alert();
        String answer = "8.19";
        assertTrue(answerAlert.getText().contains(answer));
        answerAlert.accept();

        assertFalse(error.isDisplayed());
    }
}
