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
        driver.get("https://acctabootcamp.github.io/site/tasks/enter_a_number");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void errorOnText() throws Exception {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        WebElement input = driver.findElement(By.id("numb"));
        input.clear();
        Thread.sleep(1000);
        String txt = "asdasdasd";
        input.sendKeys(txt);
        Thread.sleep(1000);
        WebElement submit = driver.findElement(By.tagName("button"));
        submit.click();
        Thread.sleep(1000);
        WebElement error = driver.findElement(By.id("ch1_error"));
        assertEquals("Please enter a number", error.getText());
        Thread.sleep(1000);
    }

    @Test
    public void errorOnNumberTooSmall() throws Exception {
//        BUG: if I enter number 49 or 42 no errors where seen
//        TODO
//        enter number which is too small (positive number below 50), check that correct error is seen
        WebElement input = driver.findElement(By.id("numb"));
        input.clear();
        Thread.sleep(1000);
        String txt = "20";
        input.sendKeys(txt);
        Thread.sleep(1000);
        WebElement submit = driver.findElement(By.tagName("button"));
        submit.click();
        Thread.sleep(1000);
        WebElement error = driver.findElement(By.id("ch1_error"));
        assertEquals("Number is too small", error.getText());
        Thread.sleep(1000);
    }

    @Test
    public void errorOnNumberTooBig() throws Exception  {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        WebElement input = driver.findElement(By.id("numb"));
        input.clear();
        Thread.sleep(1000);
        String txt = "121";
        input.sendKeys(txt);
        Thread.sleep(1000);
        WebElement submit = driver.findElement(By.tagName("button"));
        submit.click();
        Thread.sleep(1000);
        WebElement error = driver.findElement(By.id("ch1_error"));
        assertEquals("Number is too big", error.getText());
        Thread.sleep(1000);
    }

    @Test
    public void correctSquareRoot() throws Exception {
//        TODO
//        enter a number between 50 and 100 digit in the input, then press submit
//        and check that no error is seen and that square root is calculated correctly
//        NOTE: input value is hardcoded, but square root used in assertions should be calculated in code
        WebElement input = driver.findElement(By.id("numb"));
        input.clear();
        Thread.sleep(1000);
        String txt = "81";
        input.sendKeys(txt);
        Thread.sleep(1000);
        WebElement submit = driver.findElement(By.tagName("button"));
        submit.click();
        Thread.sleep(1000);
        Alert alert = driver.switchTo().alert();
        double sqrRoot = Math.sqrt(Integer.parseInt(txt));
        String popMsg = "Square root of " + txt + " is " + String.format("%.2f", sqrRoot);
        assertEquals(popMsg, alert.getText());
        alert.accept();
        Thread.sleep(1000);
    }
}
