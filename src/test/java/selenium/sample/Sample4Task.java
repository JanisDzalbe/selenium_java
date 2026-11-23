package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Activity 4: Practicing Selenium element interactions (clear, sendKeys, click)
 * and element state assertions (isEnabled, isDisplayed, and URL verification).
 */
public class Sample4Task {
    WebDriver driver;
    String base_url = "https://janisdzalbe.github.io/example-site/examples/actions";

    /**
     * Initializes ChromeDriver and opens the actions example page before each test.
     *
     * @throws Exception if driver initialization fails
     */
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        // open page:
        driver.get(base_url);
    }

    /**
     * Closes the browser after each test.
     *
     * @throws Exception if browser teardown fails
     */
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    /**
     * Enters a number into the Number field, verifies button states and displayed result text,
     * then clears the result and confirms the text is empty and hidden again.
     *
     * @throws Exception if any element cannot be located or interacted with
     */
    @Test
    public void enterNumber() throws Exception {
        WebElement numberInput = driver.findElement(By.id("number"));
        WebElement resultButton = driver.findElement(By.id("result_button_number"));
        WebElement clearResultButton = driver.findElement(By.id("clear_result_button_number"));
        WebElement resultText = driver.findElement(By.id("result_number"));
        String numberToEnter = "42";

        // TODO:
        //  enter a number under "Number"
        numberInput.clear();
        numberInput.sendKeys(numberToEnter);

        //  check that button is not clickable "Clear Result"
        assertFalse(clearResultButton.isEnabled());

        //  check that text is not displayed
        assertFalse(resultText.isDisplayed());

        //  click on "Result" button
        resultButton.click();

        //  check that text is displayed
        assertTrue(resultText.isDisplayed());

        //  check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
        assertEquals("You entered number: \"" + numberToEnter + "\"", resultText.getText());

        //  check that the button "Clear Result" is clickable now
        assertTrue(clearResultButton.isEnabled());

        //  click on "Clear Result"
        clearResultButton.click();

        //  check that the text is now (""), but it is not displayed
        assertEquals("", resultText.getText());
        assertFalse(resultText.isDisplayed());
    }

    /**
     * Verifies the starting URL, clicks the homepage link, and confirms navigation
     * away from base_url to the homepage URL.
     *
     * @throws Exception if the homepage link cannot be located
     */
    @Test
    public void clickOnLink() throws Exception {
        // TODO:
        //  check current url is base_url
        assertEquals(base_url, driver.getCurrentUrl());

        //  click on "This is a link to Homepage"
        driver.findElement(By.id("homepage_link")).click();

        //  check that current url is not base_url
        assertFalse(driver.getCurrentUrl().equals(base_url));

        //  verify that current url is homepage
        assertEquals("https://janisdzalbe.github.io/example-site/", driver.getCurrentUrl());
    }
}