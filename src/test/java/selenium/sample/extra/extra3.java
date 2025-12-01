package selenium.sample.extra;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class extra3 {
    WebDriver driver;

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeDriver();

        //open page:
        driver.get("https://janisdzalbe.github.io/example-site/examples/styles");
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void styleChecks() throws Exception {
        WebElement h1 = driver.findElement(By.xpath("//h1"));
        assertEquals("block", h1.getCssValue("display"));
        assertEquals("rgba(0, 0, 0, 1)", h1.getCssValue("color"));
        assertEquals("64px", h1.getCssValue("font-size"));
        assertEquals("rgba(0, 0, 0, 0)", h1.getCssValue("background-color"));

        WebElement div_h1 = driver.findElement(By.xpath("//div[h1]"));
        assertEquals("rgba(241, 241, 241, 1)", div_h1.getCssValue("background-color"));
    }
}
