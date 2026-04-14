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
        List<WebElement> elements = driver.findElements(By.className("test"));
        assertEquals(5, elements.size());
        List<WebElement> buttons = driver.findElements(By.tagName("input"));
        assertEquals("This is also a button", buttons.get(1).getDomAttribute("value"));
    }

    @Test
    public void assertTrueTask() throws Exception {
        List<WebElement> buttons = driver.findElements(By.tagName("input"));
        assertTrue(2 <= buttons.size());
        String secondButtonValue = buttons.get(1).getDomAttribute("value");
        assertNotNull(secondButtonValue);
        assertTrue(secondButtonValue.equalsIgnoreCase("this is Also a Button"),
                "This is custom error message");
    }

    @Test
    public void assertFalseTask() throws Exception {
        List<WebElement> buttons = driver.findElements(By.tagName("input"));
        assertTrue(2 <= buttons.size());
        String secondButtonValue = buttons.get(1).getDomAttribute("value");
        assertNotNull(secondButtonValue);
        assertFalse(secondButtonValue.equals("This is a button"));
        // assertNotEquals("This is a button", secondButtonValue);
    }

    @Test
    public void failTask() throws Exception {
        List<WebElement> elements = driver.findElements(By.className("test"));
        for (WebElement e : elements) {
            if (e.getText().contains("190")) {
                fail("Element containing 190 found");
            }
        }
        // assertTrue(elements.stream().noneMatch((e) -> e.getText().contains("190")));
    }
}
