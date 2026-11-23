package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        driver.findElement(By.id("numb")).sendKeys("morning");
        driver.findElement(By.className("w3-orange")).click();
        String actualError = driver.findElement(By.id("ch1_error")).getText();
        assertEquals("Please enter a number", actualError);
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown
        driver.findElement(By.id("numb")).sendKeys("49");
        driver.findElement(By.className("w3-orange")).click();
        String actualError = driver.findElement(By.id("ch1_error")).getText();
        assertEquals("Number is too small", actualError);
    }

    @Test
    public void errorOnNumberTooBig() {
//        TODO
//         enter number which is too big (above 100), check that correct error is shown
        driver.findElement(By.id("numb")).sendKeys("101");
        driver.findElement(By.className("w3-orange")).click();
        String actualError = driver.findElement(By.id("ch1_error")).getText();
        assertEquals("Number is too big", actualError);
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is shown and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables
        String number = "64";
        String expectedResult = "Square root of 64 is 8.00";
        driver.findElement(By.id("numb")).sendKeys(number);
        driver.findElement(By.className("w3-orange")).click();
        Alert resultAlert = driver.switchTo().alert();
        String actualResult = resultAlert.getText();
        assertEquals(expectedResult, actualResult);
        resultAlert.accept();
    }
}
