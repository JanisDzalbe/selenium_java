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
//         TODO:
//        enter a number under "Number"
        WebElement numberInput = driver.findElement(By.id("number"));
        numberInput.clear();
        String number = "123";
        numberInput.sendKeys(number);
//        check that button is not clickable "Clear Result"
        WebElement clearButton = driver.findElement(By.id("clear_result_button_number"));
        assertFalse(clearButton.isEnabled(), "Clear Result button should be disabled initially");
//        check that text is not displayed
        WebElement resultText = driver.findElement(By.id("result_number"));
        assertFalse(resultText.isDisplayed(), "Result text should not be visible initially");
//        click on "Result" button
        WebElement resultButton = driver.findElement(By.id("result_button_number"));
        resultButton.click();
//        check that text is displayed
        assertTrue(resultText.isDisplayed(), "Result text should be visible after clicking 'Result'");
//        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
        String expectedMessage = "You entered number: \"" + number + "\"";
        assertEquals(expectedMessage, resultText.getText(), "Displayed message is incorrect");
//        check that the button "Clear Result" is clickable now
        assertTrue(clearButton.isEnabled(), "'Clear Result' button should be enabled after result is shown");
//        click on "Clear Result"
        clearButton.click();
//        check that the text is now (""), but it is not displayed
        assertFalse(resultText.isDisplayed(), "Result text should be hidden after clearing");
    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//        check current url is base_url
        assertEquals(base_url, driver.getCurrentUrl(), "Initial URL should match base_url");
//        click on "This is a link to Homepage"
        WebElement link = driver.findElement(By.linkText("This is a link to Homepage"));
        link.click();
        Thread.sleep(1000);
//        check that current url is not base_url
        String newUrl = driver.getCurrentUrl();
        assertNotEquals(base_url, newUrl, "URL should change after clicking the homepage link");
//        verify that current url is homepage
        String expectedHomeUrl = "https://acctabootcamp.github.io/site/";
        assertEquals(expectedHomeUrl, newUrl, "Did not redirect to the correct homepage URL");
    }
}
