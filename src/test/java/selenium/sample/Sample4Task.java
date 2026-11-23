
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

    String base_url =
            "https://janisdzalbe.github.io/example-site/examples/actions";

    // Runs before each test
    @BeforeEach
    public void startingTests() throws Exception {

        driver = BootcampUtils.initializeChromeDriver();

        driver.get(base_url);
    }

    // Runs after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    // Test 1: Enter a number and check the result
    @Test
    public void enterNumber() throws Exception {

        // Find elements
        WebElement numberInput = driver.findElement(By.id("number"));
        WebElement resultButton =
                driver.findElement(By.id("result_button_number"));
        WebElement clearButton =
                driver.findElement(By.id("clear_result_button_number"));
        WebElement resultText =
                driver.findElement(By.id("result_number"));

        // Enter a number
        String number = "123";
        numberInput.clear();
        numberInput.sendKeys(number);

        // Check initial state
        assertFalse(clearButton.isEnabled());
        assertFalse(resultText.isDisplayed());

        // Click Result
        resultButton.click();

        // Check result is displayed
        assertTrue(resultText.isDisplayed());

        // Check correct result text
        assertEquals(
                "You entered number: \"" + number + "\"",
                resultText.getText()
        );

        // Check Clear Result button is enabled
        assertTrue(clearButton.isEnabled());

        // Click Clear Result
        clearButton.click();

        // Check result is empty and hidden
        assertEquals("", resultText.getText());
        assertFalse(resultText.isDisplayed());
    }

    // Test 2: Click the homepage link
    @Test
    public void clickOnLink() throws Exception {

        // Check current URL
        assertEquals(base_url, driver.getCurrentUrl());

        // Click homepage link
        driver.findElement(
                By.linkText("This is a link to Homepage")
        ).click();

        // Check URL has changed
        assertFalse(driver.getCurrentUrl().equals(base_url));

        // Verify homepage URL
        assertEquals(
                "https://janisdzalbe.github.io/example-site/",
                driver.getCurrentUrl()
        );
    }
}
