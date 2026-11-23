
package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1 {

    WebDriver driver;

    @BeforeEach
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir")
                + File.separator + "lib" + File.separator;

        System.setProperty(
                "webdriver.chrome.driver",
                libWithDriversLocation + "chromedriver"
                        + new selenium.ChangeToFileExtension().extension()
        );

        driver = new ChromeDriver();

        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void errorOnText() {

        driver.findElement(By.id("numb")).sendKeys("hello");

        driver.findElement(By.xpath("//button[text()='Submit']")).click();

        String actualError = driver.findElement(By.id("ch1_error")).getText();

        assertEquals("Please enter a number", actualError);
    }

    @Test
    public void errorOnNumberTooSmall() {

        driver.findElement(By.id("numb")).sendKeys("25");

        driver.findElement(By.xpath("//button[text()='Submit']")).click();

        String actualError = driver.findElement(By.id("ch1_error")).getText();

        assertEquals("Number is too small", actualError);
    }

    @Test
    public void errorOnNumberTooBig() {

        driver.findElement(By.id("numb")).sendKeys("125");

        driver.findElement(By.xpath("//button[text()='Submit']")).click();

        String actualError = driver.findElement(By.id("ch1_error")).getText();

        assertEquals("Number is too big", actualError);
    }

    @Test
    public void correctSquareRoot() {

        String number = "81";
        String expectedResult = "Square root of 81 is 9.00";

        driver.findElement(By.id("numb")).sendKeys(number);

        driver.findElement(By.xpath("//button[text()='Submit']")).click();

        Alert alert = driver.switchTo().alert();

        assertEquals(expectedResult, alert.getText());

        alert.accept();

        String actualError = driver.findElement(By.id("ch1_error")).getText();

        assertEquals("", actualError);
    }
}
