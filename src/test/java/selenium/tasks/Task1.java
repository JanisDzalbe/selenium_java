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
import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
      WebElement inputField=driver.findElement(By.id("numb"));
      WebElement submitButton = driver.findElement(By.className("w3-orange"));
      inputField.sendKeys("text");
      submitButton.click();
      String expectedOutput="Please enter a number";
      assertEquals(expectedOutput,driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown
      WebElement inputField=driver.findElement(By.id("numb"));
      WebElement submitButton = driver.findElement(By.className("w3-orange"));
      inputField.sendKeys("42");
      submitButton.click();
      String expectedOutput="Number is too small";
      assertEquals(expectedOutput,driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooBig() {
//        TODO
//         enter number which is too big (above 100), check that correct error is shown
      WebElement inputField=driver.findElement(By.id("numb"));
      WebElement submitButton = driver.findElement(By.className("w3-orange"));
      inputField.sendKeys("420");
      submitButton.click();
      String expectedOutput="Number is too big";
      assertEquals(expectedOutput,driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is shown and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables
      String numberStringg="50";
      WebElement inputField=driver.findElement(By.id("numb"));
      WebElement submitButton = driver.findElement(By.className("w3-orange"));
      inputField.sendKeys(numberStringg);
      submitButton.click();

      double SqRoot=Math.sqrt(Integer.parseInt(numberStringg));

      Alert alert1=driver.switchTo().alert();
      String alertText=alert1.getText();
      String ExpectedString= String.format("Square root of %s is %.2f",numberStringg, SqRoot);

      assertEquals(ExpectedString,alertText);
      alert1.accept();
      assertFalse( driver.findElement(By.id("ch1_error")).isDisplayed());

    }
}
