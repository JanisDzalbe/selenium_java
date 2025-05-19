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
//         check that value of second button is "This is also a button"

        List<WebElement> elements = driver.findElements(By.className("test"));
        assertEquals(5, elements.size());

        String actual = driver.findElement(By.id("buttonId")).getAttribute("value");
        assertEquals("This is also a button", actual);
    }

    @Test
    public void assertTrueTask() throws Exception {
//         TODO:
//         check that it is True that value of second button is
//         "this is Also a Button" if you ignore Caps Locks
//         fail with custom error message:
        String expected = "this is Also a Button";
        String actual = driver.findElement(By.id("buttonId")).getAttribute("value");

        assertTrue(actual.equalsIgnoreCase(expected), "Custom Error mesage");
    }

    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//        check that it is False that value of second button is "This is a button"
        String actual = driver.findElement(By.id("buttonId")).getAttribute("value");
        assertFalse(actual.equalsIgnoreCase("This is a Button"));
    }

    @Test
    public void failTask() throws Exception {
//        TODO:
//        check that none of items with class "test"
//        contain number 190
        List<WebElement> elements = driver.findElements(By.className("test"));

        for (WebElement testElement: elements){
            if (testElement.getText().contains("190")){
                fail();
            }
        }

    }
}
