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
//         TODO:
//        check the background of top 2 sections
//        rgba(255, 221, 221, 1);
//        check h1 element font-size 64px

        WebElement section1 = driver.findElement(By.cssSelector("body > div:nth-child(3) > div:nth-child(1) > div"));
        assertEquals("rgba(255, 221, 221, 1)", section1.getCssValue("background-color"));

        WebElement section2 = driver.findElement(By.cssSelector("body > div:nth-child(3) > div:nth-child(2) > div"));
        assertEquals("rgba(255, 255, 204, 1)", section2.getCssValue("background-color"));

        WebElement h1 = driver.findElement(By.cssSelector("body > div.w3-light-grey.w3-padding.w3-margin-bottom.w3-center > h1"));
        assertEquals("64px", h1.getCssValue("font-size"));
    }
}
