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
//import static sun.jvm.hotspot.oops.CellTypeState.value;

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
        List<WebElement> testElements = driver.findElements(By.className("test"));
        System.out.println(testElements.size());
        assertEquals(5, testElements.size() );
        //assertEquals("This is also a button", driver.findElement(By.id("buttonId"))).getDomAttribute("value");
    }

    @Test
    public void assertTrueTask() throws Exception {
//         TODO:
//          check that it is True that value of second button is
//          "this is Also a Button" if you ignore Caps Locks
//          fail with custom error message:

        String elementTextOnPage = driver.findElement(By.cssSelector(".unbelievable")).getText();

        assertTrue(driver.findElement(By.id("buttonId")).getDomAttribute("value").equalsIgnoreCase("this is Also a Button"),"Text is not matching");
        //assertTrue(driver.findElement(By.id("buttonId"))).getDomAttribute("value");
        // assertTrue("Expecting the element with class 'unbelievable' to have text 'ajshdka'", elementTextOnPage.contains("ajshdka"));
        //assertTrue(elementTextOnPage.equalsIgnoreCase("this is Also a Button"),"Text is not matching");
        //assertTrue(elementTextOnPage.contains("wrong text"));
        // pass:
        assertTrue(true);
        // fail:
        //assertTrue(false);
    }

    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//          check that it is False that value of second button is "This is a button"
        String elementTextOnPage = driver.findElement(By.name("randomButton2")).getText();
        assertFalse(elementTextOnPage.equals("This is a button"));
        assertFalse(elementTextOnPage.contains("wrong text"));
        // fail:
        // assertFalse(true);
        // pass:
        assertFalse(false);
    }

    @Test
    public void failTask() throws Exception {
//        TODO:
//         check that none of items with class "test"
//         contain number 190
        List<WebElement> testElements = driver.findElements(By.className("test"));
       for (WebElement element : testElements) {
            if (element.getText().contains("190")) {
                fail("Element contains number 190");
            }
        }
            }
    }


