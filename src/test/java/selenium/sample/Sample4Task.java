package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.*;

public class Sample4Task {
  WebDriver driver;
  String base_url = "https://janisdzalbe.github.io/example-site/examples/actions";

  // method which is being run before each test
  @BeforeEach
  public void startingTests() throws Exception {
    // Initialize driver
    driver = BootcampUtils.initializeChromeDriver();

    //open page:
    driver.get(base_url);
  }

  // method which is being run after each test
  @AfterEach
  public void endingTests() throws Exception {
    driver.quit();
  }

  @Test
  public void enterNumber() throws Exception {
//         TODO:
//          enter a number under "Number"
//          check that button is not clickable "Clear Result"
//          check that text is not displayed
//          click on "Result" button
//          check that text is displayed
//          check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
//          check that the button "Clear Result" is clickable now
//          click on "Clear Result"
//          check that the text is now (" "), but it is not displayed
    String inputNumber = "50";
    WebElement number = driver.findElement(By.id("number"));
    number.clear();
    number.sendKeys(inputNumber);
    WebElement clearResultFieldNumber = driver.findElement(By.id("clear_result_button_number"));

    assertFalse(clearResultFieldNumber.isEnabled(), "Clear result Field is enabled");
    WebElement ResultNumberText = driver.findElement(By.id("result_number"));
    assertFalse(ResultNumberText.isDisplayed(), "Result text is displayed");

    WebElement ResultBtn = driver.findElement(By.id("result_button_number"));
    ResultBtn.click();
    assertTrue(ResultNumberText.isDisplayed(), "Result text is not displayed");
    String expectedString = "You entered number: \"" + inputNumber + "\"";

    assertEquals(expectedString, ResultNumberText.getText(), "The string is not equal to expected");
    assertTrue(clearResultFieldNumber.isEnabled(), "button is disabled");
    clearResultFieldNumber.click();
    assertAll(
            () -> assertFalse(ResultNumberText.isDisplayed(), "Result field is displayed"),
            () -> assertEquals("", ResultNumberText.getText(), "Text field is not empty")
    );

  }

  @Test
  public void clickOnLink() throws Exception {
//         TODO:
//          check current url is base_url
//          click on "This is a link to Homepage"
//          check that current url is not base_url
//          verify that current url is homepage
    assertEquals(base_url, driver.getCurrentUrl());
    driver.findElement(By.id("homepage_link")).click();
    assertNotEquals(base_url, driver.getCurrentUrl());

    String homepageUrl = "https://janisdzalbe.github.io/example-site/";
    assertEquals(homepageUrl, driver.getCurrentUrl());
  }
}
