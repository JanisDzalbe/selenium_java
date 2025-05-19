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
//        check that button is not clickable "Clear Result"
//        check that text is not displayed
//        click on "Result" button
//        check that text is displayed
//        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
//        check that the button "Clear Result" is clickable now
//        click on "Clear Result"
//        check that the text is now (""), but it is not displayed
        WebElement numberInput = driver.findElement(By.id("number"));
        WebElement resultButton = driver.findElement(By.id("result_button_number"));
        WebElement clearResultButton = driver.findElement(By.id("clear_result_button_number"));
        WebElement resultText = driver.findElement(By.id("result_number"));

        String inputValue = "7";
        numberInput.clear();
        numberInput.sendKeys(inputValue);

        assertFalse(clearResultButton.isEnabled(), "Clear Result button should be disabled initially");
        assertFalse(resultText.isDisplayed(), "Result text should not be displayed initially");

        resultButton.click();

        assertTrue(resultText.isDisplayed(), "Result text should be displayed after clicking Result");

        String expectedText = "You entered number: \"" + inputValue + "\"";
        assertEquals(expectedText, resultText.getText(), "Displayed result text is incorrect");
        assertTrue(clearResultButton.isEnabled(), "Clear Result button should now be enabled");

        clearResultButton.click();

        assertEquals("", resultText.getText(), "Result text should be empty after clicking Clear Result");
        assertFalse(resultText.isDisplayed(), "Result text should be hidden after clicking Clear Result");
    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//        check current url is base_url
//        click on "This is a link to Homepage"
//        check that current url is not base_url
//        verify that current url is homepage
        assertEquals(base_url, driver.getCurrentUrl(), "Current url is not the base url");

        driver.findElement(By.linkText("This is a link to Homepage")).click();
        assertNotEquals(base_url, driver.getCurrentUrl(), "Current url is the base url");

        String pageHeader = driver.findElement(By.tagName("h1")).getText();
        assertEquals("This is a home page", pageHeader, "Current url is not the homepage");
        assertEquals("https://acctabootcamp.github.io/site/", driver.getCurrentUrl(), "Current url is not the homepage" );
    }
}
