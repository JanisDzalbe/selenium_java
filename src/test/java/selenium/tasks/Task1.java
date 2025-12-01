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
import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        WebElement textInput = driver.findElement(By.id("numb"));
        textInput.sendKeys("hello");
        driver.findElement(By.className("w3-btn")).click();

        assertEquals("Please enter a number", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown
        WebElement numbInput = driver.findElement(By.id("numb"));
        numbInput.sendKeys("1");
        driver.findElement(By.className("w3-btn")).click();

        assertEquals("Number is too small", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooBig() {
//        TODO
//         enter number which is too big (above 100), check that correct error is shown
        WebElement numbInput = driver.findElement(By.id("numb"));
        numbInput.sendKeys("230");
        driver.findElement(By.className("w3-btn")).click();

        assertEquals("Number is too big", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is shown and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables
        double inputValue = 81;
        double expectedResult = Math.sqrt(inputValue);
        DecimalFormat df = new DecimalFormat("#.00");
        String actualResult = df.format(expectedResult);
        String expectedText = "Square root of " + inputValue + " is " + actualResult;

        WebElement numbInput = driver.findElement(By.id("numb"));
        numbInput.sendKeys(String.valueOf(inputValue));
        driver.findElement(By.className("w3-btn")).click();

        Alert alert = driver.switchTo().alert();

        String actualText =alert.getText();

        assertEquals(expectedText, actualText);

        alert.accept();
    }
}
