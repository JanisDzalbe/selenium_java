package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.utility.BootcampUtils;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class Sample4Task {
    WebDriver driver;
    WebDriverWait wait;
    String base_url = "https://acctabootcamp.github.io/site/examples/actions";

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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
        // enter a number under "Number"
        WebElement numberInput = driver.findElement(By.id("number"));
        int testNumber = 42;
        numberInput.sendKeys(String.valueOf(testNumber));

        // check that button "Clear Result" is not clickable
        WebElement clearResultButton = driver.findElement(By.id("clearResult"));
        assertFalse(clearResultButton.isEnabled(), "Clear Result button should not be clickable initially");

        // check that result text is not displayed
        WebElement resultText = driver.findElement(By.id("result"));
        assertFalse(resultText.isDisplayed(), "Result text should not be displayed initially");

        // click on "Result" button
        WebElement resultButton = driver.findElement(By.id("showResult"));
        resultButton.click();

        // wait for and check that text is displayed
        wait.until(ExpectedConditions.visibilityOf(resultText));
        assertTrue(resultText.isDisplayed(), "Result text should be displayed after clicking Result button");

        // check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
        String expectedText = "You entered number: " + testNumber;
        assertEquals(expectedText, resultText.getText(), "Result text should match the entered number");

        // check that the button "Clear Result" is clickable now
        wait.until(ExpectedConditions.elementToBeClickable(clearResultButton));
        assertTrue(clearResultButton.isEnabled(), "Clear Result button should be clickable after showing result");

        // click on "Clear Result"
        clearResultButton.click();

        // check that the text is now (""), but it is not displayed
        wait.until(ExpectedConditions.invisibilityOf(resultText));
        assertFalse(resultText.isDisplayed(), "Result text should not be displayed after clicking Clear Result");
        assertEquals("", resultText.getText(), "Result text should be empty after clicking Clear Result");
    }

    @Test
    public void clickOnLink() throws Exception {
        // check current URL is base_url
        assertEquals(base_url, driver.getCurrentUrl(), "Starting URL should match base_url");

        // click on "This is a link to Homepage"
        WebElement homeLink = driver.findElement(By.linkText("This is a link to Homepage"));
        homeLink.click();

        // wait for the navigation to complete
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(base_url)));

        // check that current URL is not base_url
        assertNotEquals(base_url, driver.getCurrentUrl(), "URL should change after clicking the link");

        // verify that current URL is homepage
        String expectedHomepageUrl = "https://acctabootcamp.github.io/site/";
        assertEquals(expectedHomepageUrl, driver.getCurrentUrl(), "URL should be the homepage after clicking the link");
    }
}