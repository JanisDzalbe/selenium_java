package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.ChangeToFileExtension;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1 {

    WebDriver driver;

    @BeforeEach
    public void openPage() {
        String libWithDriversLocation =
                System.getProperty("user.dir") + File.separator + "lib" + File.separator;

        System.setProperty(
                "webdriver.chrome.driver",
                libWithDriversLocation + "chromedriver" + new ChangeToFileExtension().extension()
        );

        driver = new ChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    // helpers
    private WebElement numberInput() {
        return driver.findElement(By.id("numb"));
    }

    private WebElement submitButton() {
        return driver.findElement(By.cssSelector("button[onclick='numberValidation()']"));
    }

    private WebElement errorMessage() {
        return driver.findElement(By.id("ch1_error"));
    }

    private WebElement resultMessage() {
        return driver.findElement(By.id("result"));
    }

    @Test
    public void errorOnText() {
        String value = "abc";

        numberInput().clear();
        numberInput().sendKeys(value);
        submitButton().click();

        assertEquals("Please enter a number", errorMessage().getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
        String value = "20";

        numberInput().clear();
        numberInput().sendKeys(value);
        submitButton().click();

        assertEquals("Number is too small", errorMessage().getText());
    }

    @Test
    public void errorOnNumberTooBig() {
        String value = "150";

        numberInput().clear();
        numberInput().sendKeys(value);
        submitButton().click();

        assertEquals("Number is too big", errorMessage().getText());
    }

    @Test
    public void correctSquareRoot() {
        String value = "64";

        numberInput().clear();
        numberInput().sendKeys(value);
        submitButton().click();

        // Switch to alert
        var alert = driver.switchTo().alert();

        assertEquals("Square root of 64 is 8.00", alert.getText());

        alert.accept();
    }
}