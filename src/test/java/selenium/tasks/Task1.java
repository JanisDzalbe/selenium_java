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
        WebElement numberInput = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));
        WebElement errorText = driver.findElement(By.id("ch1_error"));

        numberInput.clear();
        numberInput.sendKeys("Anastasija");
        submitButton.click();
        assertEquals("Please enter a number", errorText.getText());

    }

    @Test
    public void errorOnNumberTooSmall() throws Exception {
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown
        WebElement numberInput = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));
        WebElement errorText = driver.findElement(By.id("ch1_error"));

        numberInput.clear();
        numberInput.sendKeys("49");
        submitButton.click();
        Thread.sleep(1000);
        System.out.println(errorText.getText());
        assertEquals("Number is too small", errorText.getText());
    }

    @Test
    public void errorOnNumberTooBig() throws Exception {
//        TODO
//         enter number which is too big (above 100), check that correct error is shown
        WebElement numberInput = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));
        WebElement errorText = driver.findElement(By.id("ch1_error"));

        numberInput.clear();
        numberInput.sendKeys("101");
        submitButton.click();
        Thread.sleep(1000);
        System.out.println(errorText.getText());
        assertEquals("Number is too big", errorText.getText());

    }

    @Test
    public void correctSquareRoot() throws Exception {
//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is shown and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables
        String number = "81";
        String expectedAlertText = "Square root of 81 is 9.00";

        WebElement numberInput = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));
        WebElement errorText = driver.findElement(By.id("ch1_error"));

        numberInput.clear();
        numberInput.sendKeys(number);
        submitButton.click();
        Thread.sleep(1000);

        Alert alert = driver.switchTo().alert();
        assertEquals(expectedAlertText, alert.getText());
        alert.accept();

        assertEquals("", errorText.getText());

    }
}
