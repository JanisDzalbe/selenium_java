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
//         TODO:
//         check how many element with class "test" there are on page (5)
        List<WebElement> testElementsWithClass = driver.findElements(By.className("test"));
        assertEquals(5, testElementsWithClass.size());
//         check that value of second button is "This is also a button"
        assertEquals("This is also a button", driver.findElement(By.name("randomButton2")).getAttribute("value"));
    }

    @Test
    public void assertTrueTask() throws Exception {
//         TODO:
//         check that it is True that value of second button is
        String expected = "this is Also a Button";
//         "this is Also a Button" if you ignore Caps Locks
        String actual = driver.findElement(By.name("randomButton2")).getAttribute("value");
//         fail with custom error message:
        assertTrue(actual.equalsIgnoreCase(expected));
    }

    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//        check that it is False that value of second button is "This is a button"
        String expected = "this is a Button";
        String actual = driver.findElement(By.name("randomButton2")).getAttribute("value");
        assertFalse(actual.equalsIgnoreCase(expected));
    }

    @Test
    public void failTask() throws Exception {
//        TODO:
//        check that none of items with class "test"
        List<WebElement> allElements = driver.findElements(By.className("test"));
        for (WebElement testElement : allElements) {
            if (testElement.getText().contains("190")) {
                fail();
            }
        }
//        contain number 190
    }
}
