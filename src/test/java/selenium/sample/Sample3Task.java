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

/**
 * Activity 3: Practicing JUnit 5 assertions (assertEquals, assertTrue, assertFalse, and fail)
 * on the locators example page.
 */
public class Sample3Task {
    WebDriver driver;

    /**
     * Initializes ChromeDriver and opens the locators example page before each test.
     *
     * @throws Exception if driver initialization fails
     */
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        // open page:
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
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
     * Verifies there are 5 elements with class "test" and that the second button's value
     * equals "This is also a button".
     *
     * @throws Exception if elements cannot be located
     */
    @Test
    public void assertEqualsTask() throws Exception {
        // TODO:
        //  check how many element with class "test" there are on page (5)
        int expectedNumberOfElements = 5;
        int actualNumberOfElements = driver.findElements(By.className("test")).size();
        assertEquals(expectedNumberOfElements, actualNumberOfElements);

        //  check that value of second button is "This is also a button"
        String expectedButtonValue = "This is also a button";
        String actualButtonValue = driver.findElement(By.name("randomButton2")).getDomAttribute("value");
        assertEquals(expectedButtonValue, actualButtonValue);
    }

    /**
     * Verifies the second button's value matches "this is Also a Button" ignoring case,
     * providing a custom error message if the assertion fails.
     *
     * @throws Exception if the button element cannot be located
     */
    @Test
    public void assertTrueTask() throws Exception {
        // TODO:
        //  check that it is True that value of second button is
        //  "this is Also a Button" if you ignore Caps Locks
        //  fail with custom error message:
        String secondButtonValue = driver.findElement(By.name("randomButton2")).getDomAttribute("value");
        assertTrue(
                secondButtonValue.equalsIgnoreCase("this is Also a Button"),
                "Expected second button value to match 'this is Also a Button' (ignoring case), but was: " + secondButtonValue
        );
    }

    /**
     * Verifies it is false that the second button's value equals "This is a button".
     *
     * @throws Exception if the button element cannot be located
     */
    @Test
    public void assertFalseTask() throws Exception {
        // TODO:
        //  check that it is False that value of second button is "This is a button"
        String secondButtonValue = driver.findElement(By.name("randomButton2")).getDomAttribute("value");
        assertFalse(secondButtonValue.equals("This is a button"));
    }

    /**
     * Loops through all elements with class "test" and fails the test if any item contains "190".
     *
     * @throws Exception if elements cannot be retrieved
     */
    @Test
    public void failTask() throws Exception {
        // TODO:
        //  check that none of items with class "test"
        //  contain number 190
        List<WebElement> testElements = driver.findElements(By.className("test"));
        for (WebElement element : testElements) {
            if (element.getText().contains("190")) {
                fail("Element with class 'test' unexpectedly contains '190': " + element.getText());
            }
        }
    }
}