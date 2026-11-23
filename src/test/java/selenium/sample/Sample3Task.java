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

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
        int count = driver.findElements(By.className("test")).size();
        assertEquals(5, count);

        // Check that value of second button is "This is also a button"
        // String buttonValue = driver.findElement(By.id("buttonId")).getAttribute("value");

        System.out.println(driver.findElement(By.name("randomButton2")).getDomAttribute("value"));

        // Teachers way to accomplish this task:

        // assertEquals(5, driver.findElements(By.className("test")).size());
        // assertEquals("This is also a button", driver.findElement((By.id("buttonId"))).getDomAttribute("value));

    }

    @Test
    public void assertTrueTask() throws Exception {
//         TODO:
//          check that it is True that value of second button is
//          "this is Also a Button" if you ignore Caps Locks
//          fail with custom error message:

        // Get the value of the second button
        // You can use either randomButton or buttonId, because those are two different HTML-attributes
        // of the same element: This is also a button
        String buttonValue = driver.findElement(By.name("randomButton2")).getDomAttribute("value");

        assertTrue(buttonValue.equalsIgnoreCase("this is Also a button"),
                "Button value is incorrect!");
        assertTrue(buttonValue.equalsIgnoreCase("thIS is ALSO a BUTTON"));
        // pass:

        /* Teachers way to accomplish this task:
         assertTrue(driver.findElement((By.id("buttonId"))).getDomAttribute(("value").equalsIgnoreCase(("this is Also a button," +
                "wrong button text"));

         assertTrue()

         */
    }


    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//          check that it is False that value of second button is "This is a button"
        // Find the second button and get its value attribute
        // You can use either randomButton or buttonId, because those are two different HTML-attributes
        // of the same element: This is also a button
        String buttonValue = driver.findElement(By.name("randomButton2"))
                .getDomAttribute("value");
        // Verify that the button value is NOT "This is a button"
        assertFalse(buttonValue.equals("This is a button"));

        // fail:
        // assertFalse(true);
        // pass:
        //  assertFalse(false);

        // Teachers way to accomplish this task:
        //  assertFalse(driver.findElement((By.id("buttonId"))).getDomAttribute("value").equals("This is button"));
        // assertFalse(driver.findElement((By.id("buttonId"))).getText().equals("This is button")); // WRONG
    }

    @Test
    public void failTask() throws Exception {
//        TODO:
//         check that none of items with class "test"
//         contain number 190
        // Find all elements with class "test"
        List<WebElement> elements = driver.findElements(By.className("test"));

        // Check every element in the list
        for (WebElement element : elements) {
            //Verify that the element does not contain "190"
            assertFalse(element.getText().contains("190"));

            /* Teachers way

             getText doesn't work, getLast works
             if (elements.getText().equals("190")) {
             fail();

             */

        }

    }
}

