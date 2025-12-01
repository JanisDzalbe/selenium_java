package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Alert;

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
        System.setProperty("webdriver.chrome.driver",
                libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());

        driver = new ChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void errorOnText() {
        WebElement input = driver.findElement(By.id("numb"));
        WebElement submit = driver.findElement(By.cssSelector("button[onclick='numberValidation()']"));
        WebElement error = driver.findElement(By.id("ch1_error"));

        input.sendKeys("hello");
        submit.click();

        assertEquals("Please enter a number", error.getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
        WebElement input = driver.findElement(By.id("numb"));
        WebElement submit = driver.findElement(By.cssSelector("button[onclick='numberValidation()']"));
        WebElement error = driver.findElement(By.id("ch1_error"));

        input.sendKeys("25");
        submit.click();

        assertEquals("Number is too small", error.getText());
    }

    @Test
    public void errorOnNumberTooBig() {
        WebElement input = driver.findElement(By.id("numb"));
        WebElement submit = driver.findElement(By.cssSelector("button[onclick='numberValidation()']"));
        WebElement error = driver.findElement(By.id("ch1_error"));

        input.sendKeys("150");
        submit.click();

        assertEquals("Number is too big", error.getText());
    }

    @Test
    public void correctSquareRoot() {
        WebElement input = driver.findElement(By.id("numb"));
        WebElement submit = driver.findElement(By.tagName("button"));
        WebElement error = driver.findElement(By.id("ch1_error"));

        int number = 64;
        String expectedResult = "Square root of 64 is 8.00";

        input.sendKeys(String.valueOf(number));
        submit.click();

        // handle alert
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();

        // check alert text
        assertEquals(expectedResult, alertText);

        // error should now be empty
        assertEquals("", error.getText());
    }
}
