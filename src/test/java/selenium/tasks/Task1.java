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

    @Test
    public void errorOnText() {
        WebElement numberToPut = driver.findElement(By.id("numb"));
        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        WebElement submitButton = driver.findElement(By.className("w3-btn"));

        numberToPut.clear();
        numberToPut.sendKeys("asd");
        submitButton.click();

        assertEquals("Please enter a number", errorMessage.getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
        WebElement numberToPut = driver.findElement(By.id("numb"));
        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        WebElement submitButton = driver.findElement(By.className("w3-btn"));

        numberToPut.clear();
        numberToPut.sendKeys("10");
        submitButton.click();

        assertEquals("Number is too small", errorMessage.getText());


    }

    @Test
    public void errorOnNumberTooBig() {
        WebElement numberToPut = driver.findElement(By.id("numb"));
        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        WebElement submitButton = driver.findElement(By.className("w3-btn"));

        numberToPut.clear();
        numberToPut.sendKeys("200");
        submitButton.click();

        assertEquals("Number is too big", errorMessage.getText());
    }

    @Test
    public void correctSquareRoot() {
        WebElement numberToPut = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-btn"));
        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        String squareRootExpectedResult = "8.00";

        numberToPut.clear();
        numberToPut.sendKeys("64");
        submitButton.click();


        Alert alert = driver.switchTo().alert();

//        There are different ways to check this out. However, I have used the easiest one just for testing purposes.
//        I have also thought about another way. It is possible to work with assertEqauls to check the correct number,
//        but need to cut off a part of the alert's string to check the actual number, which is actually taking longer and more codes' lines.
        assertTrue(alert.getText().contains(squareRootExpectedResult));
        alert.accept();

        assertFalse(errorMessage.isDisplayed());
    }
}
