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
        List<WebElement> tests = driver.findElements(By.className("test"));
        int actualNumberOfElements = tests.size();
        assertEquals(5, actualNumberOfElements,
                "Expected 5 elements with class 'test', but was " + actualNumberOfElements);

        WebElement secondButton = driver.findElement(By.name("randomButton2"));
        String actualValue = secondButton.getAttribute("value");
        assertEquals("This is also a button", actualValue,
                "Second button text is incorrect");
    }

    @Test
    public void assertTrueTask() throws Exception {
        WebElement secondButton = driver.findElement(By.name("randomButton2"));
        String actualValue = secondButton.getAttribute("value");

        boolean equalsIgnoreCase =
                actualValue.equalsIgnoreCase("this is Also a Button");

        assertTrue(equalsIgnoreCase,
                "Expected second button text (ignoring case) to be 'this is Also a Button', but was: " + actualValue);
    }

    @Test
    public void assertFalseTask() throws Exception {
        WebElement secondButton = driver.findElement(By.name("randomButton2"));
        String actualValue = secondButton.getAttribute("value");

        boolean isWrongText = actualValue.equals("This is a button");

        assertFalse(isWrongText,
                "Second button text should NOT be exactly 'This is a button'");
    }

    @Test
    public void failTask() throws Exception {
        List<WebElement> tests = driver.findElements(By.className("test"));

        boolean has190 = false;
        for (WebElement element : tests) {
            String text = element.getText();
            if (text.contains("190")) {
                has190 = true;
                break;
            }
        }

        assertFalse(has190,
                "Some element with class 'test' contained '190', but it should not");
    }
}