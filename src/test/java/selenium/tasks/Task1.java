package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

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
        driver.findElement(By.id("numb")).sendKeys("abc");
        driver.findElement(By.className("w3-btn")).click();
        String error = driver.findElement(By.id("ch1_error")).getText();
        assertEquals("Please enter a number", error);

    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown
        driver.findElement(By.id("numb")).sendKeys("6");
        driver.findElement(By.className("w3-btn")).click();
        String error = driver.findElement(By.id("ch1_error")).getText();
        assertEquals("Number is too small", error);
    }

    @Test
    public void errorOnNumberTooBig() {
//        TODO
//         enter number which is too big (above 100), check that correct error is shown
        driver.findElement(By.id("numb")).sendKeys("105");
        driver.findElement(By.className("w3-btn")).click();
        String error = driver.findElement(By.id("ch1_error")).getText();
        assertEquals("Number is too big", error);
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is shown and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables
        String input = "81";
        String expectedError = "";
        String expectedResult = "Square root of 81 is 9.00";

        driver.findElement(By.id("numb")).sendKeys(input);
        driver.findElement(By.className("w3-btn")).click();

        String result = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        assertEquals(expectedResult, result);

        String error = driver.findElement(By.id("ch1_error")).getText();
        assertEquals(expectedError, error);


    }
}
