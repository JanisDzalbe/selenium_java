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
//        TODO
//         enter a text instead of a number, check that correct error is shown

        WebElement inputField = driver.findElement(By.id("numb"));
        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        WebElement button = driver.findElement(By.className("w3-btn"));

        inputField.sendKeys("text");
        button.click();

        assertEquals("Please enter a number", errorMessage.getText());

    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown

        WebElement inputField = driver.findElement(By.id("numb"));
        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        WebElement button = driver.findElement(By.className("w3-btn"));

        inputField.sendKeys("49");
        button.click();

        assertEquals("Number is too small", errorMessage.getText());
    }

    @Test
    public void errorOnNumberTooBig() {
//        TODO
//         enter number which is too big (above 100), check that correct error is shown

        WebElement inputField = driver.findElement(By.id("numb"));
        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        WebElement button = driver.findElement(By.className("w3-btn"));

        inputField.sendKeys("101");
        button.click();

        assertEquals("Number is too big", errorMessage.getText());
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is shown and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables

        WebElement inputField = driver.findElement(By.id("numb"));
        WebElement button = driver.findElement(By.className("w3-btn"));

        int inputVar = 75;
        double result = Math.round(Math.sqrt(inputVar) * 100) / 100.0;

        inputField.sendKeys(String.valueOf(inputVar));
        button.click();

        Alert alert = driver.switchTo().alert();
        String expectedMessage = "Square root of " + inputVar + " is " + result;

        assertEquals(expectedMessage, alert.getText());

        alert.accept();


//      not a good option:
        /*
        inputField.sendKeys("75");

        String inputVar = inputField.getDomProperty("value");

        int  inputVarNum = Integer.parseInt(inputVar);
        double result = 0;

        if (inputVarNum >= 50 && inputVarNum <= 100){
            result =  Math.round(Math.sqrt(inputVarNum)*100)/100.0;
        }

        button.click();
        Alert alert = driver.switchTo().alert();

        assertEquals("Square root of " + inputVarNum + " is " + result, alert.getText());

        alert.accept();
        */


    }
}
