
package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Sample3Task {

    WebDriver driver;

    // Runs before each test
    @BeforeEach
    public void startingTests() throws Exception {

        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        // Open page
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    // Runs after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    // 1. Check values using assertEquals
    @Test
    public void assertEqualsTask() throws Exception {

        // Find all elements with class "test"
        List<WebElement> elements = driver.findElements(By.className("test"));

        // Check that there are 5 elements
        assertEquals(5, elements.size());

        // Find the second button
        WebElement button = driver.findElement(By.name("randomButton2"));

        // Check its value
        assertEquals("This is also a button", button.getAttribute("value"));
    }

    // 2. Check condition using assertTrue
    @Test
    public void assertTrueTask() throws Exception {

        // Find the second button
        WebElement button = driver.findElement(By.name("randomButton2"));

        String buttonValue = button.getAttribute("value");

        // Check that the value matches, ignoring uppercase/lowercase
        assertTrue(
                buttonValue.equalsIgnoreCase("this is Also a Button"),
                "Button value does not match!"
        );
    }

    // 3. Check condition using assertFalse
    @Test
    public void assertFalseTask() throws Exception {

        // Find the second button
        WebElement button = driver.findElement(By.name("randomButton2"));

        String buttonValue = button.getAttribute("value");

        // Check that the value is NOT "This is a button"
        assertFalse(buttonValue.equals("This is a button"));
    }

    // 4. Check that none of the elements contain 190
    @Test
    public void failTask() throws Exception {

        // Find all elements with class "test"
        List<WebElement> elements = driver.findElements(By.className("test"));

        // Check every element
        for (WebElement element : elements) {

            if (element.getText().contains("190")) {
                fail("An element contains the forbidden number 190!");
            }
        }
    }
}
