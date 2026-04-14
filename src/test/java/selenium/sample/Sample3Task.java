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

    @BeforeEach
    public void startingTests() throws Exception {
        driver = BootcampUtils.initializeChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void assertEqualsTask() throws Exception {
        // check how many elements with class "test" there are on page (5)
        List<WebElement> testElements = driver.findElements(By.className("test"));
        assertEquals(5, testElements.size());

        // check that value of second button is "This is also a button"
        WebElement secondButton = driver.findElement(By.name("randomButton2"));
        assertEquals("This is also a button", secondButton.getAttribute("value"));
    }

    @Test
    public void assertTrueTask() throws Exception {
        // check that it is True that value of second button is
        // "this is Also a Button" if you ignore Caps Lock
        WebElement secondButton = driver.findElement(By.name("randomButton2"));
        String value = secondButton.getAttribute("value");

        assertTrue(
                value.equalsIgnoreCase("this is Also a Button"),
                "Expected button value to equal 'this is Also a Button' (case-insensitive), but was: " + value
        );
    }

    @Test
    public void assertFalseTask() throws Exception {
        // check that it is False that value of second button is "This is a button"
        WebElement secondButton = driver.findElement(By.name("randomButton2"));
        String value = secondButton.getAttribute("value");

        assertFalse(value.equals("This is a button"));
    }

    @Test
    public void failTask() throws Exception {
        // check that none of the items with class "test" contain number 190
        List<WebElement> testElements = driver.findElements(By.className("test"));

        for (WebElement el : testElements) {
            String text = el.getText();
            if (text.contains("190")) {
                fail("Element with class 'test' contains number 190: " + text);
            }
        }
    }
}