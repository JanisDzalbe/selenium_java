package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1 {
    WebDriver driver;

    @BeforeEach
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://acctabootcamp.github.io/site/tasks/enter_a_number");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void errorOnText() {

        String text = "test";
        String expectedMessage = "Please enter a number";

        driver.findElement(By.id("numb")).sendKeys(text);
        driver.findElement(By.className("w3-orange")).click();

        assertEquals(expectedMessage, driver.findElement(By.id("ch1_error")).getText());

//        TODO
//        enter a text instead of a number, check that correct error is seen
    }

    @Test
    public void errorOnNumberTooSmall() {

        String text = "17";
        String expectedMessage = "Number is too small";

        driver.findElement(By.id("numb")).sendKeys(text);
        driver.findElement(By.className("w3-orange")).click();

        assertEquals(expectedMessage, driver.findElement(By.id("ch1_error")).getText());

//        BUG: if I enter number 49 or 42 no errors where seen
//        TODO
//        enter number which is too small (positive number below 50), check that correct error is seen
    }

    @Test
    public void errorOnNumberTooBig() {

        String text = "117";
        String expectedMessage = "Number is too big";

        driver.findElement(By.id("numb")).sendKeys(text);
        driver.findElement(By.className("w3-orange")).click();

        assertEquals(expectedMessage, driver.findElement(By.id("ch1_error")).getText());

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
    }

    @Test
    public void correctSquareRoot() {

        String text = "56.25";
        double calcResult = Math.sqrt(Double.parseDouble(text));
        DecimalFormat df = new DecimalFormat("#.00");
        String formattedNumber = df.format(calcResult);
        String fullMessageText = "Square root of " + text + " is " + formattedNumber;

        driver.findElement(By.id("numb")).sendKeys(text);
        driver.findElement(By.className("w3-orange")).click();

        assertEquals(fullMessageText, driver.switchTo().alert().getText());

//        TODO
//        enter a number between 50 and 100 digit in the input, then press submit
//        and check that no error is seen and that square root is calculated correctly
//        NOTE: input value is hardcoded, but square root used in assertions should be calculated in code
    }
}
