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

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        //open page:
        driver.get("https://acctabootcamp.github.io/site/examples/locators");
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void assertEqualsTask() throws Exception {
        int expectedCount = 5;
        int actualCount = driver.findElements(By.className("test")).size();
        assertEquals(expectedCount, actualCount);

        String expectedValue = "This is also a button";
        String actualValue = driver.findElement(By.id("buttonId")).getText();
        assertEquals(expectedValue, actualValue);
//
//         TODO:
//         check how many element with class "test" there are on page (5)
//         check that value of second button is "This is also a button"
    }

    @Test
    public void assertTrueTask() throws Exception {
        String actualValue = driver.findElement(By.id("buttonId")).getText();
        String expectedValue = "this is Also a Button";
        assertTrue(actualValue.equalsIgnoreCase(expectedValue),
                "Expected second button text to match '" + expectedValue + "' ignoring case, but was: '" + actualValue + "'");
//         TODO:
//         check that it is True that value of second button is
//         "this is Also a Button" if you ignore Caps Locks
//         fail with custom error message:
    }

    @Test
    public void assertFalseTask() throws Exception {
        String actualValue = driver.findElement(By.name("randomButton1")).getText();
        String incorrectValue = "This is a button";
        assertFalse(actualValue.equals(incorrectValue),
                "Expected second button text NOT to be '" + incorrectValue + "', but it was.");
//         TODO:
//        check that it is False that value of second button is "This is a button"
    }

    @Test
    public void failTask() throws Exception {
//        TODO:
        List<WebElement> testElements = driver.findElements(By.className("test"));

        for (WebElement element : testElements) {
            String text = element.getText();
            if (text.contains("190")) {
                fail("Element with class 'test' contains the number 190. Offending text: '" + text + "'");
            }
//        check that none of items with class "test"
//        contain number 190
        }
    }
}
