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
//          check how many element with class "test" there are on page (5)
        int expectedNumberOfElements = 5;
        int actualNumberOfElements = driver.findElements(By.className("test")).size();
        assertEquals(expectedNumberOfElements, actualNumberOfElements);
//          check that value of second button is "This is also a button"
        WebElement element = driver.findElement(By.name("randomButton2"));
        assertEquals("This is also a button", element.getDomAttribute("value"));
    }

    @Test
    public void assertTrueTask() throws Exception {
//          check that it is True that value of second button is
//          "this is Also a Button" if you ignore Caps Locks
//          fail with custom error message:
        WebElement element = driver.findElement(By.name("randomButton2"));
        assertTrue(element.getDomAttribute("value").equalsIgnoreCase("This is also a button"), "Custom error message");
    }

    @Test
    public void assertFalseTask() throws Exception {
//          check that it is False that value of second button is "This is a button"
        WebElement element = driver.findElement(By.name("randomButton2"));
        assertFalse(element.getDomAttribute("value").equalsIgnoreCase("This is a button"));
    }

    @Test
    public void failTask() throws Exception {
//         check that none of items with class "test"
//         contain number 190
        List<WebElement> elementList =  driver.findElements(By.className("test"));
        for (WebElement element : elementList){
            if (element.getText().contains("190")) fail();
        }
    }
}
