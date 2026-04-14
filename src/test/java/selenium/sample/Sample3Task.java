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

    @BeforeEach
    public void startingTests() throws Exception {
        driver = BootcampUtils.initializeChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    @AfterEach
    public void endingTests() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void assertEqualsTask() {

        //checks the number of elements with class "test"
        int size = driver.findElements(By.className("test")).size();
        assertEquals(5, size);

        //checks value of second button
        String value = driver.findElement(By.name("randomButton2"))
                .getDomAttribute("value");

        assertEquals("This is also a button", value);
    }

    @Test
    public void assertTrueTask() {

        String value = driver.findElement(By.name("randomButton2"))
                .getDomAttribute("value");
        //ignore case
        assertTrue(value.equalsIgnoreCase("this is Also a Button"),
                "Value is not matching ignoring case!");
    }

    @Test
    public void assertFalseTask() {

        String value = driver.findElement(By.name("randomButton2"))
                .getDomAttribute("value");

        //is not equal to wrong text
        assertFalse(value.equals("This is a button"));
    }

    @Test
    public void failTask() {

        List<WebElement> elements = driver.findElements(By.className("test"));

        for (WebElement el : elements) {
            String text = el.getText();

            if (text.contains("190")) {
                fail("Element contains forbidden number 190: " + text);
            }
        }
    }
}
