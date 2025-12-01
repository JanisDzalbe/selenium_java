package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;

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
        int expectedNumberOfElements = 5;
        int actualNumberOfElements = driver.findElements(By.className("test")).size();
        assertEquals(expectedNumberOfElements, actualNumberOfElements);

        String expectedValue = "This is also a button";
        String actualValue = driver.findElement(By.id("buttonId")).getAttribute("value");
        assertEquals(expectedValue, actualValue);

    }

    @Test
    public void assertTrueTask() throws Exception {
//         TODO:
//          check that it is True that value of second button is
//          "this is Also a Button" if you ignore Caps Locks
//          fail with custom error message:
        String expectedValue = "this is Also a Button";
        String actualValue = driver.findElement(By.id("buttonId")).getAttribute("value");
        assertTrue(actualValue.equalsIgnoreCase("this is Also a Button"),"button value doesn't match (ignoring caps)");

    }

    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//          check that it is False that value of second button is "This is a button"
        String expectedValue = "This is a button";
        String actualValue = driver.findElement(By.id("buttonId")).getAttribute("value");
        assertFalse(actualValue.equals(expectedValue)); //check that actual isn't equal to expected - its not "this is a button"
    }

    @Test
    public void failTask() throws Exception {
//        TODO:
//         check that none of items with class "test"
//         contain number 190
        var elements = driver.findElements(By.className("test"));
        boolean found190 = false;

        for (var element : elements) {
            if (element.getText().contains("190")) {
                found190 = true;
                break;
            }
        }
        assertFalse(found190, "found an element containing 190");
    }
}
