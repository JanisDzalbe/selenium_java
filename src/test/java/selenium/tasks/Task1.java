package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;



import java.io.File;
import java.util.List;
import java.util.Locale;

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

        WebElement input = driver.findElement(By.id("numb"));
        WebElement submit = driver.findElement(By.xpath("//button[text()='Submit']"));

        input.sendKeys("hello");
        submit.click();

        // Check the error text shown
        WebElement error = driver.findElement(By.id("ch1_error"));
        assertEquals("Please enter a number", error.getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        BUG: if I enter number 49 or 42 no errors where seen
//        TODO
//        enter number which is too small (positive number below 50), check that correct error is seen

        WebElement input = driver.findElement(By.id("numb"));
        WebElement submit = driver.findElement(By.xpath("//button[text()='Submit']"));

        input.sendKeys("42");
        submit.click();

        Alert alert = driver.switchTo().alert();
        assertEquals("Sorry you have asked the wrong answer", alert.getText());
        alert.accept();
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen

        WebElement input = driver.findElement(By.id("numb"));
        WebElement submit = driver.findElement(By.xpath("//button[text()='Submit']"));

        input.sendKeys("666");
        submit.click();

        try {
            driver.switchTo().alert();
            fail("BUG: Alert was shown for input '666', which should only show an inline error.");
        } catch (NoAlertPresentException expected) {
        }
    }



    @Test
    public void correctSquareRoot() {
//        TODO
//        enter a number between 50 and 100 digit in the input, then press submit
//        and check that no error is seen and that square root is calculated correctly
//        NOTE: input value is hardcoded, but square root used in assertions should be calculated in code

        WebElement input = driver.findElement(By.id("numb"));
        WebElement submit = driver.findElement(By.xpath("//button[text()='Submit']"));

        int inputValue = 64;
        input.sendKeys(String.valueOf(inputValue));
        submit.click();

        Alert alert = driver.switchTo().alert();
        double expectedSqrt = Math.sqrt(inputValue);
        String expectedAlertText = String.format(Locale.US, "Square root of %d is %.2f", inputValue, expectedSqrt);
        assertEquals(expectedAlertText, alert.getText());
        alert.accept();

        List<WebElement> errors = driver.findElements(By.id("chl_error"));
        assertTrue(errors.isEmpty() || errors.get(0).getText().isBlank());

    }
}
