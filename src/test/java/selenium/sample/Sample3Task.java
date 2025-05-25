package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import java.awt.*;
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
        int expectedNumberOfElements = 5;
        int actualNumberOfElements = driver.findElements(By.className("test")).size();
        assertEquals(expectedNumberOfElements, actualNumberOfElements);
        System.out.println("Number of elements with class 'test': " + actualNumberOfElements);

//         check that value of second button is "This is also a button"
        String expected = "This is also a button";
        String actual = driver.findElement(By.id("buttonId")).getAttribute("value");
        assertEquals(expected, actual);
        System.out.println("Value of second button is : " + actual);
    }

    @Test
    public void assertTrueTask() throws Exception {
//         TODO:
//         check that it is True that value of second button is
//         "this is Also a Button" if you ignore Caps Locks
        List<WebElement> buttons = driver.findElements(By.cssSelector("input[type='button']"));
        String secondButtonValue = buttons.get(1).getAttribute("value");

        if (secondButtonValue.equalsIgnoreCase("this is Also a Button")) {
            System.out.println("Condition matched! Second button value: " + secondButtonValue);
            assertTrue(true);
        }

//         fail with custom error message:
        else {
            System.out.println("Condition did NOT match! Second button value: " + secondButtonValue);
            assertTrue(false);
        }
    }

    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//        check that it is False that value of second button is "This is a button"
        List<WebElement> buttons = driver.findElements(By.cssSelector("input[type='button']"));
        String secondButtonValue = buttons.get(1).getAttribute("value");

        if (!(secondButtonValue.equalsIgnoreCase("This is a button"))) {
            System.out.println("Value did NOT match! Actual: " + secondButtonValue);
            assertFalse(true);
        }
        else {
            System.out.println("Value matched!");
            assertFalse(false);
        }
    }

    @Test
    public void failTask() throws Exception {
//        TODO:
//        check that none of items with class "test"
//        contain number 190
        List<WebElement> allElementsWithClass = driver.findElements(By.className("Test"));
        for (WebElement elementWithClass : allElementsWithClass) {
            String text = elementWithClass.getText();

            if (text.contains("190")) {
                fail("Element contains '190': " + text);
            }
            System.out.println(text);
        }
    }
}
