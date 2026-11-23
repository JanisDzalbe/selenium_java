package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.utility.BootcampUtils;


import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class Task1 {
    WebDriver driver;

    @BeforeEach
    public void openPage() {

        //String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        //System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = BootcampUtils.initializeChromeDriver();
        //driver = new ChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void errorOnText() {
        WebElement input = driver.findElement(By.id("numb"));
        input.clear();
        input.sendKeys("Text instead of num");
        driver.findElement(By.cssSelector("button[onclick='numberValidation()']")).click();
        String error = driver.findElement(By.id("ch1_error")).getText();
        assertEquals("Please enter a number", error);

    }

    @Test
    public void errorOnNumberTooSmall() {
        WebElement input = driver.findElement(By.id("numb"));
        input.clear();
        input.sendKeys("23");
        driver.findElement(By.cssSelector("button[onclick='numberValidation()']")).click();
        String error = driver.findElement(By.id("ch1_error")).getText();
        assertEquals("Number is too small", error);
    }

    @Test
    public void errorOnNumberTooBig() {
        WebElement input = driver.findElement(By.id("numb"));
        input.clear();
        input.sendKeys("222");
        driver.findElement(By.cssSelector("button[onclick='numberValidation()']")).click();
        String error = driver.findElement(By.id("ch1_error")).getText();
        assertEquals("Number is too big", error);
    }

    @Test
    public void correctSquareRoot() {
        String inputd = "64";
        WebElement input = driver.findElement(By.id("numb"));
        input.clear();
        input.sendKeys(inputd);
        String expectedResult = "Square root of 64 is 8.00";
        driver.findElement(By.cssSelector("button[onclick='numberValidation()']")).click();
        Alert alert = driver.switchTo().alert();
        String actualResult = alert.getText();
        alert.accept();
        assertEquals(expectedResult, actualResult);
        assertEquals("", driver.findElement(By.id("ch1_error")).getText());
    }
}
