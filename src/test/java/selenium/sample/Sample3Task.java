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
//          check that value of second button is "This is also a button"
        List<WebElement> elemList = driver.findElements(By.className("test"));
        assertEquals(5, elemList.size());
        List<WebElement> buttons = driver.findElements(By.name("randomButton2"));
        assertEquals("This is also a button", buttons.get(0).getAttribute("value"));
    }

    @Test
    public void assertTrueTask() throws Exception {
//         TODO:
//          check that it is True that value of second button is
//          "this is Also a Button" if you ignore Caps Locks
//          fail with custom error message:
        WebElement button = driver.findElement(By.name("randomButton2"));
        String buttonValue = button.getAttribute("value");
        assertTrue(
                buttonValue.equalsIgnoreCase("this is Also a Button"),
                "Error: the value of the second button does not match the expected text (ignoring case)."
        );
    }

    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//          check that it is False that value of second button is "This is a button"

        assertFalse(driver.findElement(By.id("buttonId")).getDomAttribute("value").equalsIgnoreCase("this is a Button"));
    }

    @Test
    public void failTask() throws Exception {
//        TODO:
//         check that none of items with class "test"
//         contain number 190
        List<WebElement> elemList = driver.findElements(By.className("test"));
        for(WebElement elem : elemList){
            if(elem.getText().contains("190")){
                fail();
            }
        }
    }
}
