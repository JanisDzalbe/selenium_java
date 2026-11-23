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
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        WebElement input = driver.findElement(By.id("numb"));
        input.sendKeys("abc");
        WebElement Submit= driver.findElement(By.cssSelector("button.w3-orange"));
        Submit.click();

        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        assertTrue(errorMessage.isDisplayed());
        assertEquals("Please enter a number", errorMessage.getText());

    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown
        WebElement input = driver.findElement(By.id("numb"));
        input.sendKeys("25");
        WebElement Submit= driver.findElement(By.cssSelector("button.w3-orange"));
        Submit.click();
        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        assertTrue(errorMessage.isDisplayed());
        assertEquals("Number is too small", errorMessage.getText());

    }

    @Test
    public void errorOnNumberTooBig() {
//        TODO
//         enter number which is too big (above 100), check that correct error is shown
        WebElement input = driver.findElement(By.id("numb"));
        input.sendKeys("101");
        WebElement Submit= driver.findElement(By.cssSelector("button.w3-orange"));
        Submit.click();
        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        assertTrue(errorMessage.isDisplayed());
        assertEquals("Number is too big", errorMessage.getText());
    }

    @Test
    public void correctSquareRoot() {

//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
        String inputNumber = "55";
        WebElement input = driver.findElement(By.id("numb"));
        input.clear();
        input.sendKeys(inputNumber);

        String roundedSqrt = String.format(Locale.US, "%.2f", Math.sqrt(Double.parseDouble(inputNumber)));
        String expectedAlertText = "Square root of " + inputNumber + " is " + roundedSqrt;

        WebElement submitButton = driver.findElement(By.cssSelector("button.w3-orange"));
        submitButton.click();
//         and check that no error is shown and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables
        Alert alert = driver.switchTo().alert();
        String actualAlertText = alert.getText();
        System.out.println("Alert text: " + actualAlertText);

        assertEquals(expectedAlertText, actualAlertText);
        alert.accept();

        String errorText = driver.findElement(By.id("ch1_error")).getText();
        assertEquals("", errorText);
    }
}
