package selenium.sample;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import selenium.utility.BootcampUtils;

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
    int actualCount = driver.findElements(By.className("test")).size();
    assertEquals(5, actualCount);

    String buttonValue = driver.findElement(By.name("randomButton2"))
            .getAttribute("value");
    assertEquals("This is also a button", buttonValue);
    }

    @Test
    public void assertTrueTask() throws Exception {
//         TODO:
//          check that it is True that value of second button is
//          "this is Also a Button" if you ignore Caps Locks
//          fail with custom error message:
            String buttonValue = driver.findElement(By.name("randomButton2")).getAttribute("value");
            assertTrue(buttonValue.equalsIgnoreCase("THIS IS ALSO A BUTTON"),"The second button has an unexpected value: " + buttonValue);
        }

    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//          check that it is False that value of second button is "This is a button"
            String buttonValue = driver.findElement(By.name("randomButton2")).getAttribute("value");
            assertFalse(buttonValue.equals("This is a button"),"The second button has an unexpected value: " + buttonValue);
    }

    @Test
    public void failTask() throws Exception {
//        TODO:
//         check that none of items with class "test"
//         contain number 190
        List<WebElement> testElements = driver.findElements(By.className("test"));
        for (WebElement element : testElements) {
            String text = element.getText();
            assertFalse(text.contains("190"), "Element with class 'test' contains the number 190: " + text);
        }
    }
}
