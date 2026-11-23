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
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void assertEqualsTask() throws Exception {
//         TODO:
//          check how many element with class "test" there are on page (5)
//          check that value of second button is "This is also a button"
        int actualCount = driver.findElements(By.className("test")).size();
        assertEquals(5, actualCount);

        List<WebElement> button = driver.findElements(By.tagName("button"));
        String secondButtonText = button.get(1).getText();
        assertEquals("This is also a button", secondButtonText);
    }

    @Test
    public void assertTrueTask() throws Exception {
//         TODO:
//          check that it is True that value of second button is
//          "this is Also a Button" if you ignore Caps Locks
//          fail with custom error message:
        List<WebElement> button = driver.findElements(By.tagName("button"));
        String secondButtonText = button.get(1).getText();
        assertTrue(secondButtonText.equalsIgnoreCase("this is Also a Button"),
                "Second button text did not match 'this is Also a Button' (ignoring case)");
    }

    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//          check that it is False that value of second button is "This is a button"
        List<WebElement> button = driver.findElements(By.tagName("button"));
        String secondButtonText = button.get(1).getText();
        assertFalse(secondButtonText.equals("This is a button"));
    }

    @Test
    public void failTask() throws Exception {
//        TODO:
//         check that none of items with class "test"
//         contain number 190
        List<WebElement> testElements = driver.findElements(By.className("test"));
        for (int i = 0; i < testElements.size(); i++) {
            if (testElements.get(i).getText().contains("190")) {
                fail("Element at index " + i + " contains 190: " + testElements.get(i).getText());
            }

        }
    }
}