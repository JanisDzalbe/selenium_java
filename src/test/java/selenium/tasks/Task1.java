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
        WebElement button = driver.findElement(By.xpath("//button[text()='Submit']"));
        WebElement error = driver.findElement(By.id("ch1_error"));

        input.clear();
        input.sendKeys("surendar");

        button.click();

        assertTrue(error.isDisplayed());
        assertEquals("Please enter a number", error.getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown

        WebElement input = driver.findElement(By.id("numb"));
        WebElement button = driver.findElement(By.xpath("//button[text()='Submit']"));
        WebElement error = driver.findElement(By.id("ch1_error"));

        input.clear();
        input.sendKeys("25");

        button.click();

        assertTrue(error.isDisplayed());
        assertEquals("Number is too small", error.getText());

    }

    @Test
    public void errorOnNumberTooBig() {
//        TODO
//         enter number which is too big (above 100), check that correct error is shown

        WebElement input = driver.findElement(By.id("numb"));
        WebElement button = driver.findElement(By.xpath("//button[text()='Submit']"));
        WebElement error = driver.findElement(By.id("ch1_error"));

        input.clear();
        input.sendKeys("125");

        button.click();

        assertTrue(error.isDisplayed());
        assertEquals("Number is too big", error.getText());
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is shown and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables

        WebElement input = driver.findElement(By.id("numb"));
        WebElement button = driver.findElement(By.xpath("//button[text()='Submit']"));
        WebElement error = driver.findElement(By.id("ch1_error"));

        String number = "81";
        double expected = Math.sqrt(Integer.parseInt(number));

        input.clear();
        input.sendKeys(number);

        button.click();

        // alert validation
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        assertTrue(alertText.contains("Square root of " + number));
        assertTrue(alertText.contains(String.format("%.2f", expected)));

        alert.accept();
    }
}
