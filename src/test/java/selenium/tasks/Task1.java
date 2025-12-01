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

    @Test
    public void errorOnText(){
//        TODO
//         enter a text instead of a number, check that correct error is shown
        WebElement textInput = driver.findElement(By.id("numb"));
        textInput.clear();
        textInput.sendKeys("aa");
        WebElement submitButton = driver.findElement(By.className("w3-btn"));
        submitButton.click();
        WebElement error = driver.findElement(By.id("ch1_error"));
        assertEquals("Please enter a number", error.getText());

    }

    @Test
    public void errorOnNumberTooSmall(){
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown
        WebElement smallNumInput = driver.findElement(By.id("numb"));
        smallNumInput.clear();
        smallNumInput.sendKeys("2");
        WebElement submitButton = driver.findElement(By.className("w3-btn"));
        submitButton.click();
        WebElement error = driver.findElement(By.id("ch1_error"));
        assertEquals("Number is too small", error.getText());

    }

    @Test
    public void errorOnNumberTooBig(){
//        TODO
//         enter number which is too big (above 100), check that correct error is shown
        WebElement bigNumInput = driver.findElement(By.id("numb"));
        bigNumInput.clear();
        bigNumInput.sendKeys("2000");
        WebElement submitButton = driver.findElement(By.className("w3-btn"));
        submitButton.click();
        WebElement error = driver.findElement(By.id("ch1_error"));
        assertEquals("Number is too big", error.getText());
    }

    @Test
    public void correctSquareRoot(){
//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is shown and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables

        int inputNumber = 70;
        WebElement numInput = driver.findElement(By.id("numb"));
        numInput.clear();
        numInput.sendKeys(String.valueOf(inputNumber));

        WebElement submitButton = driver.findElement(By.className("w3-btn"));
        submitButton.click();

        double sqrt = Math.sqrt(inputNumber);
        String sqrtRounded = String.format("%.2f", sqrt);

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        assertEquals("Square root of " + inputNumber + " is " + sqrtRounded, alert.getText());

        alert.accept();







    }
}
