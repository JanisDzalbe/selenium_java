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
        // Elements based on REAL PAGE
        WebElement numberInput = driver.findElement(By.id("number"));
        WebElement resultButton = driver.findElement(By.id("result_number"));
        WebElement clearButton = driver.findElement(By.id("clear_number"));
        WebElement resultText = driver.findElement(By.id("number_result"));

        // 1. Enter a number
        numberInput.sendKeys("5");
        // 2. Clear button should be disabled
        assertFalse(clearButton.isEnabled());
        // 3. Text should not be displayed yet
        assertFalse(resultText.isDisplayed());
        // 4. Click "Result"
        resultButton.click();
        // 5. Text should now be displayed
        assertTrue(resultText.isDisplayed());
        // 6. Text should be correct
        assertEquals("You entered number: 5", resultText.getText());
        // 7. Clear button should now be enabled
        assertTrue(clearButton.isEnabled());
        // 8. Click "Clear Result"
        clearButton.click();
        // 9. Text should be empty AND hidden
        assertEquals("", resultText.getText());
        assertFalse(resultText.isDisplayed());
    }

    @Test
    public void clickOnLink() throws Exception {
        // 1. Check we are on base_url
        assertEquals(base_url, driver.getCurrentUrl());
        // 2. Click link to homepage
        driver.findElement(By.linkText("This is a link to Homepage")).click();
        // 3. URL must change
        assertNotEquals(base_url, driver.getCurrentUrl());
        // 4. URL must now be homepage
        assertEquals("https://janisdzalbe.github.io/example-site/", driver.getCurrentUrl());
    }
}
