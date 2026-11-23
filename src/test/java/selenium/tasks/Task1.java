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
        driver.findElement(By.id("numb"));
        String sendKeyOne = "Hello";
        driver.findElement(By.tagName("button")).click();
        String elementTextOnPage = driver.findElement(By.tagName("body")).getText();
        assertTrue(elementTextOnPage.contains("Please enter a number"));
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown
        String sendKeyOne = "12";
        driver.findElement(By.id("numb")).sendKeys(sendKeyOne);
        driver.findElement(By.tagName("button")).click();
        String elementTextOnPage = driver.findElement(By.id("ch1_error")).getText();
        System.out.println("Original text: " + elementTextOnPage);
        assertTrue(elementTextOnPage.contains("Number is too small"));
    }

    @Test
    public void errorOnNumberTooBig() {
//        TODO
//         enter number which is too big (above 100), check that correct error is shown

        String sendKeyOne = "101";
        driver.findElement(By.id("numb")).sendKeys(sendKeyOne);
        driver.findElement(By.tagName("button")).click();
        String elementTextOnPage = driver.findElement(By.id("ch1_error")).getText();
        System.out.println("Original text: " + elementTextOnPage);
        assertTrue(elementTextOnPage.contains("Number is too big"));
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is shown and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables
        String inputNumber = "99";
        WebElement number = driver.findElement(By.id("numb"));
        number.sendKeys(inputNumber);
        driver.findElement(By.tagName("button")).click();
        Alert alert = driver.switchTo().alert();
        String actualResult = alert.getText();
        assertEquals("Square root of 99 is 9.95",actualResult);
        alert.accept();

        //String elementTextOnPage = driver.findElement(By.tagName("body")).getText();
        //assertEquals(true, elementTextOnPage.contains("Square root of 81 is 9.00"));
    }
}
