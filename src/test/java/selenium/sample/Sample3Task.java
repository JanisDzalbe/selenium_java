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
        // check how many elements with class "test" there are on page (5)
        int expectedCount = 5;
        int actualCount = driver.findElements(By.className("test")).size();
        assertEquals(expectedCount, actualCount);

        // check that value of second button is "This is also a button"
        String expectedButtonText = "This is also a button";
        String actualButtonText = driver.findElement(By.name("randomButton2"))
                .getAttribute("value");
        assertEquals(expectedButtonText, actualButtonText);
    }

    @Test
    public void assertTrueTask() throws Exception {
        // value of second button
        String buttonValue = driver.findElement(By.name("randomButton2"))
                .getAttribute("value");

        // check that ignoring case it equals "this is Also a Button"
        assertTrue(
                buttonValue.equalsIgnoreCase("this is Also a Button"),
                "Button text is not equal ignoring case"
        );
    }

    @Test
    public void assertFalseTask() throws Exception {
        String buttonValue = driver.findElement(By.name("randomButton2"))
                .getAttribute("value");

        // check that it is FALSE that value of second button is "This is a button"
        assertFalse(buttonValue.equals("This is a button"));
    }

    @Test
    public void failTask() throws Exception {
        // check that none of items with class "test" contain number 190
        List<WebElement> items = driver.findElements(By.className("test"));

        for (WebElement item : items) {
            if (item.getText().contains("190")) {
                fail("Found forbidden number 190 in text: " + item.getText());
            }
        }
    }
}
