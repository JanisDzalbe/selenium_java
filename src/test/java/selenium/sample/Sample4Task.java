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

    @BeforeEach
    public void startingTests() throws Exception {
        driver = BootcampUtils.initializeChromeDriver();
        driver.get(base_url);
    }

    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void enterNumber() throws Exception {
        String numberToEnter = "42";

        WebElement numberInput = driver.findElement(By.id("number"));
        WebElement resultButton = driver.findElement(By.id("result_button_number"));
        WebElement clearResultButton = driver.findElement(By.id("clear_result_button_number"));
        WebElement resultText = driver.findElement(By.id("result_number"));

        // clear any pre-filled value, then enter a number
        numberInput.clear();
        numberInput.sendKeys(numberToEnter);

        // check that "Clear Result" button is NOT clickable
        assertFalse(clearResultButton.isEnabled(), "Clear Result button should be disabled");

        // check that result text is NOT displayed
        assertFalse(resultText.isDisplayed(), "Result text should not be displayed yet");

        // click on "Result" button
        resultButton.click();

        // check that result text is displayed
        assertTrue(resultText.isDisplayed(), "Result text should be displayed after clicking Result");

        // check that the correct text appears (note the quotes around the number)
        assertEquals("You entered number: \"" + numberToEnter + "\"", resultText.getText());

        // check that "Clear Result" is now clickable
        assertTrue(clearResultButton.isEnabled(), "Clear Result button should now be enabled");

        // click on "Clear Result"
        clearResultButton.click();

        // check that the text is now "" but not displayed
        assertEquals("", resultText.getText());
        assertFalse(resultText.isDisplayed(), "Result text should be hidden after clearing");
    }

    @Test
    public void clickOnLink() throws Exception {
        // check current url is base_url
        assertEquals(base_url, driver.getCurrentUrl());

        // click on "This is a link to Homepage"
        WebElement link = driver.findElement(By.linkText("This is a link to Homepage"));
        link.click();

        // check that current url is not base_url
        String currentUrl = driver.getCurrentUrl();
        assertNotEquals(base_url, currentUrl);

        // verify that current url is homepage
        String homepageUrl = "https://janisdzalbe.github.io/example-site/";
        assertEquals(homepageUrl, currentUrl);
    }
}