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
        driver.findElement(By.id("numb")).sendKeys("hi");
        driver.findElement(By.className("w3-btn")).click();
        assertTrue(driver.findElement(By.id("ch1_error")).isDisplayed());

    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown
        String smallNumbers= "10";
        String expectedError = "Number is too small";

        driver.findElement(By.id("numb")).sendKeys(smallNumbers);
        driver.findElement(By.className("w3-btn")).click();
        assertTrue(driver.findElement(By.id("ch1_error")).isDisplayed());
        assertEquals(expectedError,driver.findElement(By.id("ch1_error")).getText());

    }

    @Test
    public void errorOnNumberTooBig() {
//        TODO
//         enter number which is too big (above 100), check that correct error is shown
        String BigNumbers= "10";
        String expectedError = "Number is too big";

        driver.findElement(By.id("numb")).sendKeys(BigNumbers);
        driver.findElement(By.className("w3-btn")).click();
        assertTrue(driver.findElement(By.id("ch1_error")).isDisplayed());
        assertEquals(expectedError,driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is shown and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables

        String validInputNumber = "99";
        String expectedSqrtResult = "9.95";

        driver.findElement(By.id("numb")).sendKeys(validInputNumber);
        driver.findElement(By.className("w3-btn")).click();
        Alert alert = driver.switchTo().alert();
        assertTrue(alert.getText().contains(expectedSqrtResult),"The square root is not correct as expected");
        alert.accept();

        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());


    }
}
