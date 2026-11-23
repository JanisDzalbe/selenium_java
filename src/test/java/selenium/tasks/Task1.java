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
        // enter a text instead of a number, check that correct error is shown
        String input = "text";
        String expectedError = "Please enter a number";

        submitNumber(input);

        assertEquals(expectedError, driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
        // enter number which is too small (positive number below 50), check that correct error is shown
        String input = "49";
        String expectedError = "Number is too small";

        submitNumber(input);

        assertEquals(expectedError, driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooBig() {
        // enter number which is too big (above 100), check that correct error is shown
        String input = "101";
        String expectedError = "Number is too big";

        submitNumber(input);

        assertEquals(expectedError, driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void correctSquareRoot() {
        // enter a number between 50 and 100 digit in the input, then press submit
        // and check that no error is shown and that square root is calculated correctly
        String input = "55";
        String expectedSquareRoot = "7.42";
        String expectedAlertText = "Square root of " + input + " is " + expectedSquareRoot;
        String expectedError = "";

        submitNumber(input);

        Alert alert = driver.switchTo().alert();
        assertEquals(expectedAlertText, alert.getText());
        alert.accept();

        assertEquals(expectedError, driver.findElement(By.id("ch1_error")).getText());
    }

    private void submitNumber(String value) {
        WebElement numberInput = driver.findElement(By.id("numb"));
        numberInput.clear();
        numberInput.sendKeys(value);
        driver.findElement(By.className("w3-orange")).click();
    }
}
