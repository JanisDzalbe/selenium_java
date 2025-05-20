package selenium.sample;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

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
//         TODO:
//        check the background of top 2 sections
//        rgba(255, 221, 221, 1);
        WebElement section01 = driver.findElement(By.className("w3-pale-red"));
        WebElement section02 = driver.findElement(By.className("w3-pale-yellow"));

        assertEquals("rgba(255, 221, 221, 1)", section01.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 204, 1)", section02.getCssValue("background-color"));

//        check h1 element font-size 64px
        WebElement h1 = driver.findElement(By.tagName("h1"));
        assertEquals("64px", h1.getCssValue("font-size"));
    }
}
