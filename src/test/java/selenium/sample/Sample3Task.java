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

        assertEquals(5, driver.findElements(By.className("test")).size());

        /*
        int expectedNumberOfElements = 5;
        int actualNumberOfElements = driver.findElements(By.className("test")).size();
        assertEquals(expectedNumberOfElements, actualNumberOfElements);
        */

//          check that value of second button is "This is also a button"

        assertEquals("This is also a button", driver.findElement(By.id("buttonId")).getDomAttribute("value"));

        /*
        String expectedValueSecButton = "This is also a button";
        String actualValueSecButton = driver.findElement(By.id("buttonId")).getDomAttribute("value");
        assertEquals(expectedValueSecButton, actualValueSecButton);
        */
    }

    @Test
    public void assertTrueTask() throws Exception {

        String actualValueSecButton = driver.findElement(By.id("buttonId")).getDomAttribute("value");

//         TODO:
//          check that it is True that value of second button is
//          "this is Also a Button" if you ignore Caps Locks
//          fail with custom error message:


        try {
            assertTrue(actualValueSecButton.equalsIgnoreCase("this is Also a Button"));
        } catch (AssertionError e) {
            System.err.println("Comparison failed");
        }

        // OR
        /*
        assertTrue(driver.findElement(By.id("buttonId")).getDomAttribute("value").equalsIgnoreCase("this is Also a Button"), "Comparison failed");
        */


    }

    @Test
    public void assertFalseTask() throws Exception {

//         TODO:
//          check that it is False that value of second button is "This is a button"

        assertFalse(driver.findElement(By.id("buttonId")).getDomAttribute("value").equals("This is a button"));

        /*
        String actualValueSecButton = driver.findElement(By.id("buttonId")).getDomAttribute("value");
        assertFalse(actualValueSecButton.equals("This is a button"));
        */
    }

    @Test
    public void failTask() throws Exception {

        List<WebElement> allElementsWithClass = driver.findElements(By.className("test"));

//        TODO:
//         check that none of items with class "test"
//         contain number 190

        for (WebElement elementWithClass : allElementsWithClass) {
            if (elementWithClass.getText().contains("190")) {
                fail();
            }
        }


        /* Not a good option to check:

        for (WebElement elementWithClass : allElementsWithClass) {

            assertFalse(elementWithClass.getText().contains("190"));

        }
         */

    }
}
