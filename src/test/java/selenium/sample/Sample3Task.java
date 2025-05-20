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
        int size = elements.size();

        WebElement btn2 = driver.findElement(By.name("randomButton2"));
        String value = btn2.getAttribute("value");

        assertAll(
                () -> assertEquals(5, size, "Expected 5 elements with 'test' class on page"),
                () -> assertEquals("This is also a button", value, "Expected 'This is also a button' value on second button")
        );
    }

    @Test
    public void assertTrueTask() throws Exception {
//         TODO:
//         check that it is True that value of second button is
//         "this is Also a Button" if you ignore Caps Locks
//         fail with custom error message:
        WebElement btn2 = driver.findElement(By.name("randomButton2"));
        String btn2Value = btn2.getAttribute("value");
        String expValue = "this is Also a BUTTON";

        assertTrue(btn2Value.equalsIgnoreCase(expValue),
                "Checks if value is equal ignoring lower and upper case letters: " + btn2Value + " != " + expValue);
    }

    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//        check that it is False that value of second button is "This is a button"
        WebElement btn2 = driver.findElement(By.name("randomButton2"));
        String btn2Value = btn2.getAttribute("value");

        assertFalse(btn2Value.equals("This is a button"), "Values should be different");
    }

    @Test
    public void failTask() throws Exception {
//        TODO:
//        check that none of items with class "test"
//        contain number 190
        List<WebElement> elements = driver.findElements(By.className("test"));

        for (WebElement el : elements) {
            String txt = el.getText();
            String num = "190";
            assertFalse(txt.contains(num), "Strings should not contain this number: " + num);
        }
    }
}
