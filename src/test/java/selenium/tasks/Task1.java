package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.management.StringValueExp;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class Task1 {
    WebDriver driver;

    @BeforeEach
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://acctabootcamp.github.io/site/tasks/enter_a_number");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void errorOnText() {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        WebElement numberInput = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-btn"));
        WebElement errorText = driver.findElement(By.id("ch1_error"));

        numberInput.clear();
        numberInput.sendKeys("abc");
        submitButton.click();

        assertTrue(errorText.isDisplayed());
        assertEquals("Please enter a number", errorText.getText());

    }

    @Test
    public void errorOnNumberTooSmall42() {
//        BUG: if I enter number 49 or 42 no errors where seen
//        TODO
//        enter number which is too small (positive number below 50), check that correct error is seen

        WebElement numberInput = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-btn"));

            numberInput.clear();
            numberInput.sendKeys("42");
            submitButton.click();

            try {
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();
                assertEquals("Sorry you have asked the wrong answer", alert.getText());
                alert.accept();
            }catch (NoAlertPresentException ignored) {}
    }
    @Test
    public void errorOnNumberTooSmall49() {
//        BUG: if I enter number 49 or 42 no errors where seen
//        TODO
//        enter number which is too small (positive number below 50), check that correct error is seen

        WebElement numberInput = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-btn"));

        numberInput.clear();
        numberInput.sendKeys("49");
        submitButton.click();

        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of 49 is 7.00", alert.getText());
        alert.accept();
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//        enter a number between 50 and 100 digit in the input, then press submit
//        and check that no error is seen and that square root is calculated correctly
//        NOTE: input value is hardcoded, but square root used in assertions should be calculated in code
        int inputValue = 73;
        WebElement numberInput = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-btn"));

        numberInput.clear();
        numberInput.sendKeys(String.valueOf(inputValue));
        submitButton.click();

        /*Alert alert = driver.switchTo().alert();
        String actualText = alert.getText();
        System.out.println("Alert text: " + actualText);*/

        Alert alert = driver.switchTo().alert();

        double sqrtValue = Math.sqrt(inputValue);

        String expectedAlertText = String.format("Square root of %d is %.2f", inputValue, sqrtValue);
        assertEquals(expectedAlertText, alert.getText());
        alert.accept();

    }
}
