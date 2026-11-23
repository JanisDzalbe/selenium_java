package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class Sample3Task {
    WebDriver driver;

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        //open page:
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void assertEqualsTask() throws Exception {
        // check how many element with class "test" there are on page (5)
        int expectedNumberOfElements = 5;
        int actualNumberOfElements = driver.findElements(By.className("test")).size();
        assertEquals(expectedNumberOfElements, actualNumberOfElements);

        // check that value of second button is "This is also a button"
        String expectedValue = "This is also a button";
        String actualValue = driver.findElement(By.id("buttonId")).getDomAttribute("value");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void assertTrueTask() throws Exception {
        // check that it is True that value of second button is
        // "this is Also a Button" if you ignore Caps Locks
        // fail with custom error message
        String expectedValue = "this is Also a Button";
        String actualValue = driver.findElement(By.id("buttonId")).getDomAttribute("value");
        assertTrue(expectedValue.equalsIgnoreCase(actualValue),
                "Expected '" + expectedValue + "' (ignoring case), but was '" + actualValue + "'");
    }

    @Test
    public void assertFalseTask() throws Exception {
        // check that it is False that value of second button is "This is a button"
        String actualValue = driver.findElement(By.id("buttonId")).getDomAttribute("value");
        assertFalse("This is a button".equals(actualValue));
    }

    @Test
    public void failTask() throws Exception {
        // check that none of items with class "test"
        // contain number 190
        for (WebElement element : driver.findElements(By.className("test"))) {
            if (element.getText().contains("190")) {
                fail("Element with class 'test' contains number 190: " + element.getText());
            }
        }
    }
}
