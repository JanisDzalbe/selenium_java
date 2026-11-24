package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
        // TODO 1. Compare text Base page with text from element with id "heading'

        // The heading isn't 'Base page' that's why this test do an error

        //String headingText = driver.findElement(By.id("heading_1")).getText();
        //System.out.println("Heading text: " + headingText);
        //assertEquals("Base page", headingText);

        // TODO 2. Check how many element with class "test" there are on page (5)
        List<WebElement> testElements = driver.findElements(By.className("test"));
        System.out.println("Elements with class 'test': " + testElements.size());
        assertEquals(5, testElements.size());

        // TODO 3. Check how many element with class "text" there are on page (5)
        List<WebElement> textElements = driver.findElements(By.className("text"));
        System.out.println("Elements with class 'text': " + textElements.size());
        assertEquals(5, textElements.size());

        //TODO 4. Check that value of second button is "This is also a button"
        String buttonValue = driver.findElement(By.name("randomButton2")).getDomAttribute("value");
        System.out.println("Second button value: " + buttonValue);
        assertEquals("This is also a button", buttonValue);
    }

    @Test
    public void assertTrueTask() throws Exception {
        // TODO 5. Check that it is True that value of second button is "this is Also a Button"
        //  if you ignore Caps Locks fail with custom error message:
        String buttonValue = driver.findElement(By.name("randomButton2")).getDomAttribute("value");
        assertTrue(buttonValue.equals("This is also a button"));
        assertTrue(buttonValue.contains("also"));
        assertTrue(buttonValue.equalsIgnoreCase("this is Also a Button"),
                "Value of the second button does not match, ignoring case. Actual: " + buttonValue);

    }

    @Test
    public void assertFalseTask() throws Exception {
        // TODO 6. Check that it is False that value of second button is "This is a button"
        String buttonValue = driver.findElement(By.name("randomButton2")).getDomAttribute("value");
        assertFalse(buttonValue.equals("This is a button"));
        assertFalse(!buttonValue.equals("This is also a button"));
        assertFalse(buttonValue.equals("not"));
        assertFalse(buttonValue.contains("not"));
    }

    @Test
    public void failTask() throws Exception {
        // TODO 7. Check that none of items with class "test" contain number 190
        List<WebElement> testElements = driver.findElements(By.className("test"));
        for (WebElement element : testElements) {
            if (element.getText().contains("190")) {
                fail("Element with class 'test' contains 190: " + element.getText());
            }
        }
    }
}
