package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

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
    }

    @Test
    public void assertTrueTask() throws Exception {
//         TODO:
//         check that it is True that value of second button is
//         "this is Also a Button" if you ignore Caps Locks
//         fail with custom error message:

        List<WebElement> testElements = driver.findElements(By.className("test"));
        assertEquals(5, testElements.size(), "Expected 5 elements with class 'test'");

        WebElement secondButton = driver.findElement(By.name("randomButton2"));
        assertEquals("This is also a button", secondButton.getAttribute("value"),
                "Button value does not match expected text");
    }

    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//        check that it is False that value of second button is "This is a button"

        WebElement secondButton = driver.findElement(By.name("randomButton2"));
        String actualValue = secondButton.getAttribute("value");

        assertFalse(actualValue.equals("This is a button"),
                "Value incorrectly matches 'This is a button'");
    }

    @Test
    public void failTask() throws Exception {
//        TODO:
//        check that none of items with class "test"
//        contain number 190

        List<WebElement> testElements = driver.findElements(By.className("test"));

        for (WebElement el : testElements) {
            if (el.getText().contains("190")) {
                fail("Found an element with class 'test' that contains the number 190: " + el.getText());
            }
    }
}
}
