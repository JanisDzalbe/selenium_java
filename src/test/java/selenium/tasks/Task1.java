package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;


import java.io.File;

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

        WebElement numberEnterArea = driver.findElement(By.id("numb"));
        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        WebElement buttonSubmit = driver.findElement(By.cssSelector("button"));


        numberEnterArea.sendKeys("aaa");
        buttonSubmit.click();

        assertEquals("Please enter a number", errorMessage.getText() );

    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown


        WebElement numberEnterArea = driver.findElement(By.id("numb"));
        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        WebElement buttonSubmit = driver.findElement(By.cssSelector("button"));


        numberEnterArea.sendKeys("5");
        buttonSubmit.click();
        assertEquals("Number is too small", errorMessage.getText() );

//        numberEnterArea.clear();
//        numberEnterArea.sendKeys("-1");
//        buttonSubmit.click();
//        assertEquals("Number is too small", errorMessage.getText() );
//
//        numberEnterArea.clear();
//        numberEnterArea.sendKeys("49");
//        buttonSubmit.click();
//        assertEquals("Number is too small", errorMessage.getText() );

        //  assertTrue(errorMessage.getText().equals("Number is too small"));

    }

    @Test
    public void errorOnNumberTooBig() {
//        TODO
//         enter number which is too big (above 100), check that correct error is shown

        WebElement numberEnterArea = driver.findElement(By.id("numb"));
        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        WebElement buttonSubmit = driver.findElement(By.cssSelector("button"));


        numberEnterArea.sendKeys("500");
        buttonSubmit.click();

        assertTrue(errorMessage.getText().equals("Number is too big"));
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//         enter a number between 50 and 100 digit in the input,
//         then press submit
//         and check that no error is shown
//         and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables

        WebElement numberEnterArea = driver.findElement(By.id("numb"));
        WebElement buttonSubmit = driver.findElement(By.cssSelector("button"));
        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        String textMessage = "Square root of 60 is 7.75";
        String inputKeys = "60";

        numberEnterArea.clear();
        numberEnterArea.sendKeys(inputKeys);
        buttonSubmit.click();


        Alert alert1 = driver.switchTo().alert();

        assertEquals(textMessage, alert1.getText());
    }
}
