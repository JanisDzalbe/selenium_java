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
        int expectedNumberOfTestElements = 5;
        int actualNumberOfTestElements = driver.findElements(By.className("test")).size();
        assertEquals(expectedNumberOfTestElements, actualNumberOfTestElements);

        List<WebElement> buttons = driver.findElements(By.cssSelector("button, input[type='button']"));
        WebElement secondButton = buttons.get(1);
        String secondButtonText = secondButton.getText();
        if (secondButtonText == null || secondButtonText.isEmpty()) {
            secondButtonText = secondButton.getDomAttribute("value");
        }
        assertEquals("This is a also a button", secondButtonText);
    }

    @Test
    public void assertTrueTask() throws Exception {
//         TODO:
//          check that it is True that value of second button is
//          "this is Also a Button" if you ignore Caps Locks
//          fail with custom error message:

        List<WebElement> buttons = driver.findElements(By.cssSelector("button, input[type='button']"));
        WebElement secondButton = buttons.get(1);

        String secondButtonText = secondButton.getText();
        if (secondButtonText == null || secondButtonText.isEmpty()) {
            secondButtonText = secondButton.getAttribute("value");
        }

        assertTrue(
                secondButtonText.equalsIgnoreCase("this is Also a Button"),
                "Second button text is not equal to 'this is Also a Button' ignoring case"
        );
    }

    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//          check that it is False that value of second button is "This is a button"

        List<WebElement> buttons = driver.findElements(By.cssSelector("button, input[type='button']"));
        WebElement secondButton = buttons.get(1);

        String secondButtonText = secondButton.getText();
        if (secondButtonText == null || secondButtonText.isEmpty()) {
            secondButtonText = secondButton.getAttribute("value");
        }

        assertFalse(
                secondButtonText.equals("This is a button"),
                "Second button SHOULD NOT be exactly 'This is a button'"
        );
    }

    @Test
    public void failTask() throws Exception {
//        TODO:
//         check that none of items with class "test"
//         contain number 190
        List<WebElement> testItems = driver.findElements(By.className("test"));

        for (WebElement item : testItems) {
            String text = item.getText();
            if (text != null && text.contains("190")) {
                // if we ever find 190, we fail with custom error message
                fail("Found forbidden number '190' in element text: " + text);
            }
        }
    }
}
