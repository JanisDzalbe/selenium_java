package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Sample8Task {
    WebDriver driver;

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        //open page:
        driver.get("https://acctabootcamp.github.io/site/examples/po");
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void styleChecks() throws Exception {

        WebElement paleRed = driver.findElement(By.cssSelector(".w3-container.w3-pale-red"));
        WebElement paleYellow = driver.findElement(By.cssSelector(".w3-container.w3-pale-yellow"));

        assertEquals("rgba(255, 221, 221, 1)", paleRed.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 204, 1)", paleYellow.getCssValue("background-color"));

        WebElement h1 = driver.findElement(By.cssSelector("h1.w3-jumbo"));
        assertEquals("64px", h1.getCssValue("font-size"));
//         TODO:
//        check the background of top 2 sections
//        rgba(255, 221, 221, 1);
//        check h1 element font-size 64px
    }
}
