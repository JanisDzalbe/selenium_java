package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

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
//          check that value of second button is "This is also a button"
        int expectedNumberOfElements = 5;
        int actualNumberOfElements = driver.findElements(By.className("test")).size();

        assertEquals(expectedNumberOfElements, actualNumberOfElements);

        String expectedButtonValue = "This is also a button";
        String actualButtonValue = driver.findElement(By.name("randomButton2")).getDomAttribute("value");

        assertEquals(expectedButtonValue, actualButtonValue);
    }

    @Test
    public void assertTrueTask() throws Exception {
//         TODO:
//          check that it is True that value of second button is
//          "this is Also a Button" if you ignore Caps Locks
//          fail with custom error message:
        String expectedButtonValue = "this is Also a Button";
        String actualButtonValue = driver.findElement(By.name("randomButton2")).getDomAttribute("value");

        assertTrue(actualButtonValue.equalsIgnoreCase(expectedButtonValue),"Button value is incorrect");
    }

    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//          check that it is False that value of second button is "This is a button"
        String expectedButtonValue = "This is a button";
        String actualButtonValue = driver.findElement(By.name("randomButton2")).getDomAttribute("value");

        assertFalse(actualButtonValue.equals(expectedButtonValue));
    }

    @Test
    public void failTask() throws Exception {
//        TODO:
//         check that none of items with class "test"
//         contain number 190
        for (WebElement element : driver.findElements(By.className("test"))) {
            String actualText = element.getText();

            if (actualText.contains("190")) {
                fail("Element contains number 190");
            }
        }

    }
}
