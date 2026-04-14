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

    private WebElement getSubmitButton() { return driver.findElement(By.cssSelector(".w3-btn.w3-orange.w3-margin")); }

    @Test
    public void errorOnText() {
//        TODO: enter a text instead of a number, check that correct error is shown
        WebElement numInput = driver.findElement(By.id("numb"));
        numInput.sendKeys("h");
        getSubmitButton().click();
        assertEquals("Please enter a number", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO: enter number which is too small (positive number below 50), check that correct error is shown
        WebElement numInput = driver.findElement(By.id("numb"));
        numInput.sendKeys("1");
        getSubmitButton().click();
        assertEquals("Number is too small", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooBig() {
//        TODO: enter number which is too big (above 100), check that correct error is shown
        WebElement numInput = driver.findElement(By.id("numb"));
        numInput.sendKeys("101");
        getSubmitButton().click();
        assertEquals("Number is too big", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is shown and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables
        int inputNumber = 64;
        String expectedResult = "Square root of 64 is 8.00";

        WebElement numInput = driver.findElement(By.id("numb"));
        numInput.sendKeys(String.valueOf(inputNumber));
        getSubmitButton().click();

        Alert alert = driver.switchTo().alert();
        assertEquals(expectedResult, alert.getText());
        alert.accept();
    }
}
