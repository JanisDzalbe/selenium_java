package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebElement;
import java.util.List;
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
//         TODO: check how many element with class "test" there are on page (5)
        int expectedNumberOfElements = 5;
        int actualNumberOfElements = driver.findElements(By.className("test")).size();
        assertEquals(expectedNumberOfElements, actualNumberOfElements);

        // TODO: check that value of second button is "This is also a button"
        String expectedValue = "This is also a button";
        String actualValue = driver.findElement(By.name("randomButton2")).getDomAttribute("value"); // "This is also a button"
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void assertTrueTask() throws Exception {
//         TODO:
//          check that it is True that value of second button is
//          "this is Also a Button" if you ignore Caps Locks
//          fail with custom error message:
        String ExpectedResult = "this is Also a Button";
        String actualButtonValue = driver.findElement(By.name("randomButton2")).getDomAttribute("value");
        assertTrue(
                actualButtonValue.equalsIgnoreCase(ExpectedResult),
                "Error message: Expected button value to equal 'this is Also a Button' (case-insensitive), but got: " + actualButtonValue
        );
    }

    @Test
    public void assertFalseTask() throws Exception {
//         TODO: check that it is False that value of second button is "This is a button"
        String expectedValue = "This is a Button";
        String actualButtonValue = driver.findElement(By.name("randomButton2")).getDomAttribute("value");
        assertFalse(actualButtonValue.equals(expectedValue),
                "Error message: Button value should not be 'This is a button'"
        );
        assertNotEquals(expectedValue, actualButtonValue,
                "Error message: Button value should not be 'This is a button'"
        );
    }

    @Test
    public void failTask() throws Exception {
//        TODO:
//         check that none of items with class "test"
//         contain number 190
        List<WebElement> testElements = driver.findElements(By.className("test"));
        String numberNotContains = "190";
        for (WebElement element : testElements) {
            if (element.getText().contains(numberNotContains)) {
                fail("Found '190' in a 'test' element, but none should contain it. Text was: " + element.getText());
            }
        }
    }
}
