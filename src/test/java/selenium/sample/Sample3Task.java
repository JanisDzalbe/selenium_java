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
        int expectedSize = 5;
        int actualSize = driver.findElements(By.className("test")).size();
        assertEquals(expectedSize, actualSize);

//          check that value of second button is "This is also a button"

        WebElement secondButton = driver.findElement(By.name("randomButton2"));
        String expectedValue = "This is also a button";
        String actualValue = secondButton.getAttribute("value");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void assertTrueTask() throws Exception {


//         TODO:
//          check that it is True that value of second button is
//          "this is Also a Button" if you ignore Caps Locks
//          fail with custom error message:
        WebElement secondButton = driver.findElement(By.name("randomButton2"));
        String buttonValue = secondButton.getAttribute("value");

        assertTrue(
                buttonValue.equalsIgnoreCase("this is Also a Button"),
                "Button text should match ignoring case"
        );
    }

    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//          check that it is False that value of second button is "This is a button"
        WebElement secondButton = driver.findElement(By.name("randomButton2"));
        String buttonValue = secondButton.getAttribute("value");
        assertFalse(buttonValue.equals("This is a button"));
    }

    @Test
    public void failTask() throws Exception {
//        TODO:
//         check that none of items with class "test"
//         contain number 190
        List<WebElement> testItems = driver.findElements(By.className("test"));
        for (WebElement item : testItems) {
            String text = item.getText();
            if (text.contains("190")) {
                fail("Found 190 in text: " + text);
            }
        }
    }
}
