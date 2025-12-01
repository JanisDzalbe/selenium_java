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
    public void assertEqualsTask() {
        List<WebElement> testElements = driver.findElements(By.cssSelector("p.test"));
        assertEquals(5, testElements.size(), "Number of <p> elements with class 'test' is incorrect");

    }

    @Test
    public void assertTrueTask() {
        assertTrue(driver.findElement(By.id("ButtonId")).getDomAttribute("value").equalsIgnoreCase("This is Also a button"),"custom error");
    }


    @Test
    public void assertFalseTask() {
        List<WebElement> testElements = driver.findElements(By.className("test"));
        String secondButtonText = testElements.get(1).getText();

        assertFalse(secondButtonText.equals("This is a button"),
                "Second button text should not be 'This is a button'");
    }

    @Test
    public void failTask() {
        List<WebElement> testElements = driver.findElements(By.className("test"));
        for (WebElement el : testElements) {
            if (el.getText().contains("190")) {
                fail("An element contains 190: " + el.getText());
            }
        }
    }
}
