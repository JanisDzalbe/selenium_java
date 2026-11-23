package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import selenium.utility.BootcampUtils;

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
            String number = "42";
            var numberInput = driver.findElement(By.id("number"));
            numberInput.clear();
            numberInput.sendKeys(number);

//          check that button is not clickable "Clear Result"
             var clearButton = driver.findElement(By.id("clear_result_button_number"));
            assertFalse(clearButton.isEnabled());

//          check that text is not displayed
             var resultText = driver.findElement(By.id("result_number"));
            assertFalse(resultText.isDisplayed());

//          click on "Result" button
            driver.findElement(By.id("result_button_number")).click();

//          check that text is displayed
            assertTrue(resultText.isDisplayed());

//          check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
            assertEquals("You entered number: \"" + number + "\"",resultText.getText());

//          check that the button "Clear Result" is clickable now
            assertTrue(clearButton.isDisplayed());
            assertTrue(clearButton.isEnabled());

//          click on "Clear Result"
             clearButton.click();
//          check that the text is now (""), but it is not displayed
            assertEquals("", resultText.getText());
            assertFalse(resultText.isDisplayed());
    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//          check current url is base_url
            assertEquals(base_url, driver.getCurrentUrl());

//          click on "This is a link to Homepage"
            driver.findElement(By.linkText("This is a link to Homepage"))
            .click();
//          check that current url is not base_url
            String currentUrl = driver.getCurrentUrl();
            assertNotEquals(base_url, currentUrl);
//          verify that current url is homepage
            assertEquals("https://janisdzalbe.github.io/example-site/",currentUrl);
    }
}
