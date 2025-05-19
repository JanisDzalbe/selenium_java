package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

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
        int expectedNumberOfTestElements = 5;
        Assertions.assertEquals(expectedNumberOfTestElements, driver.findElements(By.className("test")).size());
        // Not sure how to find buttons, using input here although it's probably poor practice if the webpage has other input fields
        Assertions.assertEquals("This is also a button", driver.findElements(By.tagName("input")).get(1).getDomAttribute("value"));
    }

    @Test
    public void assertTrueTask() throws Exception {
//         TODO:
//         check that it is True that value of second button is
//         "this is Also a Button" if you ignore Caps Locks
//         fail with custom error message:

        String expectedValue = "this is Also a Button";
        String actual = driver.findElements(By.tagName("input")).get(1).getDomAttribute("value");
        // Would probbaly use assertEquals, but oh well
//        Assertions.assertTrue(expectedValue.equals(actual));
        Assertions.assertTrue(actual.equalsIgnoreCase(expectedValue), "The messages are equal, but capitalization may be incorrect");

    }

    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//        check that it is False that value of second button is "This is a button"
        Assertions.assertFalse(driver.findElements(By.tagName("input")).get(1).getDomAttribute("value").equals("This is a button"));
    }

    @Test
    public void failTask() throws Exception {
//        TODO:
//        check that none of items with class "test"
//        contain number 190
        List<WebElement> elements = driver.findElements(By.className("test"));
        for(WebElement element : elements) {
            if(element.getText().contains("190")) {
                Assertions.fail();
            }
        }
    }
}
