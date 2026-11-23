package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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


        String expected = "This is also a button";
        String actual = driver.findElement(By.id("buttonId")).getDomAttribute("value");


        assertEquals(expected, actual);
    }

    @Test
    public void assertTrueTask() throws Exception {
//         TODO:
//          check that it is True that value of second button is
//          "this is Also a Button" if you ignore Caps Locks
//          fail with custom error message:
        //ignored casing, assert true with custom error msg
        String expected = "this is Also a Button";
        String actual = driver.findElement(By.name("randomButton2")).getDomAttribute("value");

        assertTrue(actual.equalsIgnoreCase(expected),
                "Wrong!!");



}

    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//          check that it is False that value of second button is "This is a button"

        String expected = "This is a button";
        String actual = driver.findElement(By.id("buttonId")).getDomAttribute("value");

        assertFalse(actual.equals(expected));
        assertFalse(driver.findElement(By.id("buttonId"))
                .getAttribute("value").equals(expected));



        //assert false dom attribute
    }

    @Test
    public void failTask() throws Exception {
//        TODO:
//         check that none of items with class "test"
//         contain number 190
        //get all elements with class, find elements , itteration assertion none of them is 190, regular assertion
        for (int i = 0; i < driver.findElements(By.className("test")).size(); i++) {
            String actual = driver.findElements(By.className("test")).get(i).getText();
            String expected = "190";

            assertEquals(false, actual.contains(expected));
        }
    }

    }

