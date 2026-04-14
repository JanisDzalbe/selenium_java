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
        driver.quit();
    }

    @Test
    public void assertEqualsTask() throws Exception {
        // check number of elements with class "test"
        int expectedSize = 5;
        int actualSize = driver.findElements(By.className("test")).size();
        assertEquals(expectedSize, actualSize);

        // check value of second button
        String expectedValue = "This is also a button";
        String actualValue = driver.findElement(By.name("randomButton2"))
                .getDomAttribute("value");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void assertTrueTask() throws Exception {
        String actualValue = driver.findElement(By.name("randomButton2"))
                .getDomAttribute("value");

        // ignore case
        assertTrue(
                actualValue.equalsIgnoreCase("this is Also a Button"),
                "Button text does not match (ignoring case)"
        );
    }

    @Test
    public void assertFalseTask() throws Exception {
        String actualValue = driver.findElement(By.name("randomButton2"))
                .getDomAttribute("value");

        assertFalse(actualValue.equals("This is a button"));
    }

    @Test
    public void failTask() throws Exception {
        List<WebElement> elements = driver.findElements(By.className("test"));

        for (WebElement element : elements) {
            if (element.getText().contains("190")) {
                fail("Found unexpected number 190 in text: " + element.getText());
            }
        }
    }
}