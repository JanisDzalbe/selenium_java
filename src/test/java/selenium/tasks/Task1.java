package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        String path = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver",
                path + "chromedriver" + new selenium.ChangeToFileExtension().extension());

        driver = new ChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    private WebElement getSubmitButton() {
        return driver.findElement(By.tagName("button"));
    }

    @Test
    public void errorOnText() {

        WebElement input = driver.findElement(By.id("numb"));
        WebElement error = driver.findElement(By.id("ch1_error"));

        input.sendKeys("abc");
        getSubmitButton().click();

        assertTrue(error.getText().contains("number"));
    }

    @Test
    public void errorOnNumberTooSmall() {

        WebElement input = driver.findElement(By.id("numb"));
        WebElement error = driver.findElement(By.id("ch1_error"));
        WebElement button = driver.findElement(By.tagName("button"));

        input.clear();
        input.sendKeys("40"); // < 50

        button.click();

        String errorText = error.getText().trim();

        assertTrue(errorText.equalsIgnoreCase("Number is too small"));
    }

    @Test
    public void errorOnNumberTooBig() {

        WebElement input = driver.findElement(By.id("numb"));
        WebElement error = driver.findElement(By.id("ch1_error"));
        WebElement button = driver.findElement(By.tagName("button"));

        input.clear();
        input.sendKeys("150");

        button.click();

        String errorText = error.getText().trim();

        assertTrue(errorText.equalsIgnoreCase("Number is too big"));
    }

    @Test
    public void correctSquareRoot() {

        WebElement input = driver.findElement(By.id("numb"));

        int number = 64;
        double expected = Math.round(Math.sqrt(number) * 100.0) / 100.0;

        input.sendKeys(String.valueOf(number));
        getSubmitButton().click();

        String alertText = driver.switchTo().alert().getText();
        assertTrue(alertText.contains(String.valueOf(expected)));
        driver.switchTo().alert().accept();
    }
}