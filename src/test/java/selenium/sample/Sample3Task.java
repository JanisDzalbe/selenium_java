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

        String actualValue = driver.findElement(By.name("randomButton2")).getAttribute("value");
        assertEquals("This is also a button", actualValue);
//         TODO:
//         check how many element with class "test" there are on page (5)
//         check that value of second button is "This is also a button"
    }

    @Test
    public void assertTrueTask() throws Exception {
        String actualValue = driver.findElement(By.name("randomButton2")).getAttribute("value");
        assertTrue(actualValue.equalsIgnoreCase("this is Also a Button"), "Button text does not match");
//         TODO:
//         check that it is True that value of second button is
//         "this is Also a Button" if you ignore Caps Locks
//         fail with custom error message:
    }

    @Test
    public void assertFalseTask() throws Exception {
        String actualValue = driver.findElement(By.name("randomButton2")).getAttribute("value");
        assertFalse(actualValue.equals("This is a button"));
//         TODO:
//        check that it is False that value of second button is "This is a button"
    }

    @Test
    public void failTask() throws Exception {
        List<WebElement> testElements = driver.findElements(By.className("test"));
        for (WebElement el : testElements) {
            if (el.getText().contains("190")) {
                fail("An element with class 'test' contains the number 190");
            }
        }
//        TODO:
//        check that none of items with class "test"
//        contain number 190
    }
}
