package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.Locale;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;

public class Task1 {
    WebDriver driver;
    WebElement input;
    WebElement submit;
    WebElement errorMessage;
    @BeforeEach
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://acctabootcamp.github.io/site/tasks/enter_a_number");
        input = driver.findElement(By.id("numb"));
        submit = driver.findElement(By.className("w3-orange"));
        errorMessage = driver.findElement(By.id("ch1_error"));
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void errorOnText() throws InterruptedException {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        input.sendKeys("Test text not a number");
        submit.click();
        assertTrue(errorMessage.getText().equals("Please enter a number"));
    }

    @Test
    public void errorOnNumberTooSmall() throws InterruptedException {
//        BUG: if I enter number 49 or 42 no errors where seen
//        TODO
//        enter number which is too small (positive number below 50), check that correct error is seen
        input.sendKeys("48");
        submit.click();
        assertTrue(errorMessage.getText().equals("Number is too small"));
    }

    @Test
    public void errorOnNumberTooBig() {
//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        input.sendKeys("101");
        submit.click();
        assertTrue(errorMessage.getText().equals("Number is too big"));
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//        enter a number between 50 and 100 digit in the input, then press submit
//        and check that no error is seen and that square root is calculated correctly
//        NOTE: input value is hardcoded, but square root used in assertions should be calculated in code
        int num = 64;
        input.sendKeys(Integer.toString(num));
        submit.click();
        assertEquals("Square root of "+num+" is "+ String.format(Locale.US, "%.2f", sqrt(num)), driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        assertFalse(errorMessage.isDisplayed());
    }
}
