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
        WebElement numInput        = driver.findElement(By.id("number"));
        WebElement numResultButton = driver.findElement(By.id("result_button_number"));
        WebElement numClearButton  = driver.findElement(By.id("clear_result_button_number"));
        WebElement numResultText   = driver.findElement(By.id("result_number"));

        // Clear input and enter number
        numInput.clear();
        numInput.sendKeys("10");

        // Check that "Clear Result" button is NOT clickable
        assertFalse(numClearButton.isEnabled());

        // Check that result text is NOT displayed
        assertFalse(numResultText.isDisplayed());

        // Click "Result" button
        numResultButton.click();

        // Check that result text IS now displayed
        assertTrue(numResultText.isDisplayed());

        // Check the correct text appears (page wraps input in quotes)
        assertEquals("You entered number: \"10\"", numResultText.getText());

        // Check that "Clear Result" button IS now clickable
        assertTrue(numClearButton.isEnabled());

        // Click "Clear Result"
        numClearButton.click();

        // Check that text is now empty and NOT displayed
        assertEquals("", numResultText.getText());
        assertFalse(numResultText.isDisplayed());
    }

    @Test
    public void clickOnLink() throws Exception {
        // Check current URL is base_url
        assertEquals(base_url, driver.getCurrentUrl());

        // Click on "This is a link to Homepage"
        driver.findElement(By.linkText("This is a link to Homepage")).click();

        // Check that current URL is NOT base_url
        assertFalse(driver.getCurrentUrl().equals(base_url));

        // Verify current URL is the homepage
        assertEquals("https://janisdzalbe.github.io/example-site/", driver.getCurrentUrl());
    }
}