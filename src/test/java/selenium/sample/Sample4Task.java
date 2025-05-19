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
    String base_url = "https://acctabootcamp.github.io/site/examples/actions";

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
//       TODO:
//        enter a number under "Number"
//        check that button is not clickable "Clear Result"
//        check that text is not displayed
//        click on "Result" button
//        check that text is displayed
//        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
//        check that the button "Clear Result" is clickable now
//        click on "Clear Result"
//        check that the text is now (""), but it is not displayed
        WebElement numberForm = driver.findElement(org.openqa.selenium.By.id("number"));
        WebElement resultButton = driver.findElement(org.openqa.selenium.By.id("result_button_number"));
        WebElement clearButton = driver.findElement(org.openqa.selenium.By.id("clear_result_button_number"));
        WebElement resultText = driver.findElement(org.openqa.selenium.By.id("result_number"));


        numberForm.clear();
        numberForm.sendKeys("123");

        assertFalse(clearButton.isEnabled(), "Clear button should not be enabled");

        assertFalse(resultText.isDisplayed(), "Result button should not be displayed");
        resultButton.click();

        assertTrue(resultText.isDisplayed());
        assertEquals("You entered number: \"123\"", resultText.getText(), "Entered number should be displayed" );

        assertTrue(clearButton.isEnabled(), "Clear button should be enabled");
        clearButton.click();

        assertEquals("",resultText.getText(), "Text should be empty now");
        assertFalse(resultText.isDisplayed());
    }

    @Test
    public void clickOnLink() throws Exception {
//       TODO:
//        check current url is base_url
//        click on "This is a link to Homepage"
//        check that current url is not base_url
//        verify that current url is homepage
        assertEquals(base_url, driver.getCurrentUrl());
        driver.findElement(By.linkText("This is a link to Homepage")).click();
        assertNotEquals(base_url, driver.getCurrentUrl());
        assertEquals("https://acctabootcamp.github.io/site/", driver.getCurrentUrl());
    }
}
