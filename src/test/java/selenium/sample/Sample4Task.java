package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import java.security.Key;

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
        WebElement numberInput = driver.findElement(By.id("number"));
        WebElement resultButton = driver.findElement(By.id("result_button_number"));
        WebElement clearButton = driver.findElement(By.id("clear_result_button_number"));
        WebElement resultText = driver.findElement(By.id("result_number"));
        int number = 6;
        //TODO:
        //enter a number under "Number"
        numberInput.clear();
        numberInput.sendKeys(Integer.toString(number));
        //check that button is not clickable "Clear Result"
        assertFalse(clearButton.isEnabled());
        //check that text is not displayed
        assertFalse(resultText.isDisplayed());
        //click on "Result" button
        resultButton.click();
        //check that text is displayed
        assertTrue(resultText.isDisplayed());
        //check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
        assertEquals("You entered number: \"" +number +"\"", resultText.getText());
        //check that the button "Clear Result" is clickable now
        assertTrue(clearButton.isEnabled());
        //click on "Clear Result"
        clearButton.click();
        //check that the text is now (""), but it is not displayed
        assertEquals("", resultText.getText());
        assertFalse(resultText.isDisplayed());
    }

    @Test
    public void clickOnLink() throws Exception {

        //TODO:
        //check current url is base_url
        assertEquals(base_url, driver.getCurrentUrl());
       // click on "This is a link to Homepage"
        driver.findElement(By.id("homepage_link")).click();
        //check that current url is not base_url
        assertNotEquals(base_url, driver.getCurrentUrl());
        //verify that current url is homepage
        assertEquals("https://janisdzalbe.github.io/example-site/", driver.getCurrentUrl());
    }
}
