package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Sample1Task {
    WebDriver driver;

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        //open page:
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void errorOnText() throws Exception {
//        TODO: enter text, check that correct error is shown
        driver.findElement(By.id("numb")).sendKeys("Ilya");
        driver.findElement(By.className("w3-btn")).click();
        assertEquals("Please enter a number", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooSmall() throws Exception {
//        TODO: enter number which is too small, check that correct error is shown
        driver.findElement(By.id("numb")).sendKeys("49");
        driver.findElement(By.className("w3-btn")).click();
        assertEquals("Number is too small", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooBig() throws Exception {
//        TODO: enter number which is too big, check that correct error is shown
        driver.findElement(By.id("numb")).sendKeys("101");
        driver.findElement(By.className("w3-btn")).click();
        assertEquals("Number is too big", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void correctSquareRoot() throws Exception {
//        TODO: enter a number between 50 and 100 digit in the input, then and press submit and
//        check that square root is calculated correctly and check that no error is shown
        driver.findElement(By.id("numb")).sendKeys("64");
        driver.findElement(By.className("w3-btn")).click();

        // the result is shown in an alert, reading its text and close it
        String alertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        assertEquals("Square root of 64 is 8.00", alertText);

        // check that error is empty
        assertEquals("", driver.findElement(By.id("ch1_error")).getText());
    }
}