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
    WebElement inputField = driver.findElement(By.id("numb"));
    WebElement submitButton = driver.findElement(By.className("w3-orange"));
    //send input
    inputField.sendKeys("text");
    submitButton.click();
    // expected output at check
    String expectedOutput = "Please enter a number";
    assertEquals(expectedOutput, driver.findElement(By.id("ch1_error")).getText());
  }

  @Test
  public void errorOnNumberTooSmall() {
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown
    WebElement inputField = driver.findElement(By.id("numb"));
    WebElement submitButton = driver.findElement(By.className("w3-orange"));
    //Send input
    inputField.sendKeys("42");
    submitButton.click();

    // expected string and check
    String expectedOutput = "Number is too small";
    assertEquals(expectedOutput, driver.findElement(By.id("ch1_error")).getText());
  }

  @Test
  public void errorOnNumberTooBig() {
//        TODO
//         enter number which is too big (above 100), check that correct error is shown
    WebElement inputField = driver.findElement(By.id("numb"));
    WebElement submitButton = driver.findElement(By.className("w3-orange"));
    //Send input and click
    inputField.sendKeys("420");
    submitButton.click();

    // expected string and check
    String expectedOutput = "Number is too big";
    assertEquals(expectedOutput, driver.findElement(By.id("ch1_error")).getText());
  }

  @Test
  public void correctSquareRoot() {
//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is shown and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables

    // input number
    String numberStringg = "50";

    //Elements inputField "numb" and submit button "w3-orange"
    WebElement inputField = driver.findElement(By.id("numb"));
    WebElement submitButton = driver.findElement(By.className("w3-orange"));

    //send input to inputField
    inputField.sendKeys(numberStringg);
    submitButton.click();

    double SqRoot = Math.sqrt(Integer.parseInt(numberStringg));  //Calculate Square root

    Alert alert1 = driver.switchTo().alert(); //Alert itself
    String alertText = alert1.getText();  //Alert msg text

    //Formated expected field with numberStrign and squareroot
    String ExpectedString = String.format("Square root of %s is %.2f", numberStringg, SqRoot);

    //check result in alert
    assertEquals(ExpectedString, alertText);
    alert1.accept();
    //check if element is nto displayed
    assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());

  }
}
