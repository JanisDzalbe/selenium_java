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
        int expectedResultForTestsClasses = 5;
        int actualResultForTestsClasses = driver.findElements(By.className("test")).size();

        String expectedResultForButton = "This is also a button";
        String actualResultForButton = driver.findElement(By.name("randomButton2")).getDomAttribute("value");

        assertEquals(expectedResultForTestsClasses, actualResultForTestsClasses);
        assertEquals(expectedResultForButton, actualResultForButton);
    }

    @Test
    public void assertTrueTask() throws Exception {
        String customExpectedResult = "this is Also a Button";
        String elementTextOnPage = driver.findElement(By.name("randomButton2")).getDomAttribute("value");
        assertTrue(elementTextOnPage.equalsIgnoreCase(customExpectedResult), "There is a mistake here btw");
    }

    @Test
    public void assertFalseTask() throws Exception {
        String customExpectedResult = "This is a Button";
        String elementTextOnPage = driver.findElement(By.name("randomButton2")).getDomAttribute("value");
        assertFalse(elementTextOnPage.equals(customExpectedResult), "There is a mistake here btw");
        assertNotEquals(customExpectedResult, elementTextOnPage, "There is a mistake here btw");
    }

    @Test
    public void failTask() throws Exception {
        List<WebElement> webElements = driver.findElements(By.className("test"));
        String numberNotToBeContained = "190";
        for (WebElement webElement : webElements) {
            if (webElement.getText().contains("190")) {
                fail(); 
            }
        }
    }
}
