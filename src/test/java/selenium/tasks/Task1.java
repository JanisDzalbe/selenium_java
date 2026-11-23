package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    public void errorOnText() {
        //Test for non numeric input
//        TODO
        driver.findElement(By.id("numb")).sendKeys("fds");
        driver.findElement(By.className("w3-orange")).click();

        assertEquals("Please enter a number", driver.findElement(By.id("ch1_error")).getText());
//         enter a text instead of a number, check that correct error is shown
    }

    @Test
    public void errorOnNumberTooSmall() {
        //Test for numeric input less than specified boundrie
//        TODO
        driver.findElement(By.id("numb")).sendKeys("30");
        driver.findElement(By.className("w3-orange")).click();

        assertEquals("Number is too small", driver.findElement(By.id("ch1_error")).getText());
//         enter number which is too small (positive number below 50), check that correct error is shown
    }

    @Test
    public void errorOnNumberTooBig() {
        //Test for numeric input more than specified boundrie
//        TODO
        driver.findElement(By.id("numb")).sendKeys("103");
        driver.findElement(By.className("w3-orange")).click();

        assertEquals("Number is too big", driver.findElement(By.id("ch1_error")).getText());

//         enter number which is too big (above 100), check that correct error is shown
    }

    @Test
    public void correctSquareRoot() {

        //Test for

//        TODO
        String inputNumber = "56";
        String expectedResult = "Square root of 56 is 7.48";

        WebElement inputNumberElement = driver.findElement(By.id("numb"));
        WebElement submitButton = driver.findElement(By.className("w3-orange"));
        WebElement errorShown = driver.findElement(By.id("ch1_error"));
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is shown and that square root is calculated correctly
        inputNumberElement.sendKeys(inputNumber);
        submitButton.click();

        // checking and closing the popup alert first
        assertEquals(expectedResult, driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();

        // Checking no error text appeared on the page
        assertEquals("", errorShown.getText());

//         NOTE: input and assertion values have to be defined as variables
    }
}

