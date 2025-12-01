package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

public class Task1 {

    WebDriver driver;
    WebDriverWait wait;

    private WebElement inputField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("numb")));
    }

    private WebElement submitButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='button']")));
    }

    private WebElement errorMessage() {
        return driver.findElement(By.id("ch1_error"));
    }

    @BeforeEach
    public void openPage() {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number"); // <-- correct URL
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void errorOnText() {
        inputField().sendKeys("gg");
        submitButton().click();

        assertEquals("Please enter a number", errorMessage().getText());
        assertTrue(errorMessage().isDisplayed());
    }

    @Test
    public void errorOnNumberTooSmall() {
        inputField().sendKeys("1");
        submitButton().click();

        assertEquals("Number is too small", errorMessage().getText());
        assertTrue(errorMessage().isDisplayed());
    }

    @Test
    public void errorOnNumberTooBig() {
        inputField().sendKeys("10000");
        submitButton().click();

        assertEquals("Number is too big", errorMessage().getText());
        assertTrue(errorMessage().isDisplayed());
    }

    @Test
    public void correctSquareRoot() {
        int value = 64;
        double expected = Math.sqrt(value);

        inputField().sendKeys(String.valueOf(value));
        submitButton().click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();   // "Square root of 64 is 8"

        String[] parts = alertText.split(" is ");
        double actual = Double.parseDouble(parts[1]);

        assertEquals(expected, actual, 0.01);

        alert.accept();

        assertFalse(errorMessage().isDisplayed());
    }
}
