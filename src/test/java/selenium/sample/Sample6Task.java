
package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Sample6Task {

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
    public void findElementByXPath() throws Exception {

        // Heading 2 text
        assertEquals("Heading 2 text",
                driver.findElement(By.xpath("//h2[normalize-space()='Heading 2 text']")).getText());

        // Test Text 1
        assertEquals("Test Text 1",
                driver.findElement(By.xpath("//*[normalize-space()='Test Text 1']")).getText());

        // Test Text 2
        assertEquals("Test Text 2",
                driver.findElement(By.xpath("//*[normalize-space()='Test Text 2']")).getText());

        // Test Text 3
        assertEquals("Test Text 3",
                driver.findElement(By.xpath("//*[normalize-space()='Test Text 3']")).getText());

        // Test Text 4
        assertEquals("Test Text 4",
                driver.findElement(By.xpath("//*[normalize-space()='Test Text 4']")).getText());

        // Test Text 5
        assertEquals("Test Text 5",
                driver.findElement(By.xpath("//*[normalize-space()='Test Text 5']")).getText());

        // This is also a button
        assertEquals("This is also a button",
                driver.findElement(By.xpath("//input[@value='This is also a button']"))
                        .getAttribute("value"));
    }

    @Test
    public void findElementByCssName() throws Exception {

        // Heading 2 text
        assertEquals("Heading 2 text",
                findByCssText("h2", "Heading 2 text").getText());

        // Test Text 1
        assertEquals("Test Text 1",
                findByCssText("p", "Test Text 1").getText());

        // Test Text 2
        assertEquals("Test Text 2",
                findByCssText("p", "Test Text 2").getText());

        // Test Text 3
        assertEquals("Test Text 3",
                findByCssText("p", "Test Text 3").getText());

        // This is also a button
        assertEquals("This is also a button",
                driver.findElement(By.cssSelector("input[value='This is also a button']"))
                        .getAttribute("value"));
    }

    // Helper method to find an element using CSS and match its text
    private WebElement findByCssText(String selector, String expectedText) {

        return driver.findElements(By.cssSelector(selector))
                .stream()
                .filter(element -> element.getText().trim().equals(expectedText))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Element not found: " + expectedText));
    }
}
