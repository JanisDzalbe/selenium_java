package selenium.tasks;

import com.sun.source.doctree.DocTypeTree;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Alert;
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
//        TODO
//         enter a text instead of a number, check that correct error is shown
        WebElement numberput = driver.findElement(By.id("numb"));
        WebElement meserror = driver.findElement(By.id("ch1_error"));
        WebElement subbutton = driver.findElement(By.className("w3-btn"));

        numberput.clear();
        numberput.sendKeys("edvards");
        subbutton.click();

        assertEquals("Please enter a number", meserror.getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown
        WebElement numberput = driver.findElement(By.id("numb"));
        WebElement meserror = driver.findElement(By.id("ch1_error"));
        WebElement subbutton = driver.findElement(By.className("w3-btn"));

        numberput.clear();
        numberput.sendKeys("19");
        subbutton.click();

        assertEquals("Number is too small", meserror.getText());
    }

    @Test
    public void errorOnNumberTooBig() {
//        TODO
//         enter number which is too big (above 100), check that correct error is shown
        WebElement numberput = driver.findElement(By.id("numb"));
        WebElement meserror = driver.findElement(By.id("ch1_error"));
        WebElement subbutton = driver.findElement(By.className("w3-btn"));

        numberput.clear();
        numberput.sendKeys("228");
        subbutton.click();

        assertEquals("Number is too big", meserror.getText());
    }

    @Test
    public void correctSquareRoot() {
        WebElement numberput = driver.findElement(By.id("numb"));
        WebElement subbutton = driver.findElement(By.className("w3-btn"));

        numberput.clear();
        numberput.sendKeys("81");
        subbutton.click();

        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of 81 is 9.00", alert.getText());
        alert.accept();
    }
}
