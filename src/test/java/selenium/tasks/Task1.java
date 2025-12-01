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

    private WebElement numberInput() {
        return driver.findElement(By.id("numb"));
    }

    private WebElement submitButton() {
        return driver.findElement(By.xpath("//button[text()='Submit']"));
    }

    private WebElement errorText() {
        return driver.findElement(By.id("ch1_error"));
    }

    @Test
    public void errorOnText() {
//        TODO
//         enter a text instead of a number, check that correct error is shown
        numberInput().clear();
        numberInput().sendKeys("test");
        submitButton().click();
        assertEquals("Please enter a number", errorText().getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown
        numberInput().clear();
        numberInput().sendKeys("20");
        submitButton().click();
        assertEquals("Number is too small", errorText().getText());
    }

    @Test
    public void errorOnNumberTooBig() {
//        TODO
//         enter number which is too big (above 100), check that correct error is shown
        numberInput().clear();
        numberInput().sendKeys("120");
        submitButton().click();
        assertEquals("Number is too big", errorText().getText());
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is shown and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables
        int inputNumber = 64;
        String expectedAlert = "Square root of " + inputNumber + " is 8.00";

        numberInput().clear();
        numberInput().sendKeys(String.valueOf(inputNumber));

        submitButton().click();

        Alert alert = driver.switchTo().alert();
        String actualAlertText = alert.getText();
        assertEquals(expectedAlert, actualAlertText);

        alert.accept();
        assertEquals("", errorText().getText());
    }
}
