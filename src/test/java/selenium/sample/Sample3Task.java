package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import java.util.Optional;

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
         int count= driver.findElements(By.className("test")).size();
        assertEquals(5, count);
          String text =  driver.findElement(By.id("buttonId")).getAttribute("value");
        assertEquals("This is also a button", text);
    }

    @Test
    public void assertTrueTask() throws Exception {
//         TODO:
//          check that it is True that value of second button is
//          "this is Also a Button" if you ignore Caps Locks
//          fail with custom error message:
        String text = driver.findElement(By.id("buttonId")).getAttribute("value");
        assertTrue(
                text.equalsIgnoreCase("this is also a button"),
                "Button text is not correct!"
        );
    }

    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//          check that it is False that value of second button is "This is a button"
        String text = driver.findElement(By.id("buttonId")).getAttribute("value");

        assertFalse(
                text.equals("This is a button"),
                "Second button should NOT be 'This is a button'"
        );
    }

    @Test
    public void failTask() throws Exception {
//        TODO:
//         check that none of items with class "test"
//         contain number 190
        Thread.sleep(1000);
        driver.findElements(By.className("test")).forEach(e-> System.out.println(e.getText()));
       java.util.Optional<WebElement> element = driver.findElements(By.className("test")).stream().filter(e->
                e.getText().contains("190")).findAny();
       assertTrue(element.isEmpty());

    }
}
