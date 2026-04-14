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

        // open page:
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        // close browser
        driver.quit();
    }

    @Test
    public void assertEqualsTask() throws Exception {
//         TODO:
//          check how many element with class "test" there are on page (5)
//          check that value of second button is "This is also a button"

        // get number of elements with class "test"
        int actualCount = driver.findElements(By.className("test")).size();

        // verify count equals 5
        assertEquals(5, actualCount);

        // get value attribute of second button
        String actualValue = driver.findElement(By.name("randomButton2")).getDomAttribute("value");

        // verify button text
        assertEquals("This is also a button", actualValue);
    }

    @Test
    public void assertTrueTask() throws Exception {
//         TODO:
//          check that it is True that value of second button is
//          "this is Also a Button" if you ignore Caps Locks
//          fail with custom error message:

        // get button value
        String value = driver.findElement(By.name("randomButton2")).getDomAttribute("value");

        // verify text ignoring case
        assertTrue(
                value.equalsIgnoreCase("this is Also a Button"),
                "Button text does not match ignoring case"
        );
    }

    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//          check that it is False that value of second button is "This is a button"

        // get button value
        String value = driver.findElement(By.name("randomButton2")).getDomAttribute("value");

        // verify incorrect value is false
        assertFalse(value.equals("This is a button"));
    }

    @Test
    public void failTask() throws Exception {
//        TODO:
//         check that none of items with class "test"
//         contain number 190

        // loop through all elements with class "test"
        for (var el : driver.findElements(By.className("test"))) {

            // check if text contains forbidden number
            if (el.getText().contains("190")) {

                // fail test with message
                fail("Found forbidden number 190 in text: " + el.getText());
            }
        }
    }
}