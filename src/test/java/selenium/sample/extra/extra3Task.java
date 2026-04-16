package selenium.sample.extra;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class extra3Task {
    WebDriver driver;

    @BeforeEach
    public void startingTests() throws Exception {
        driver = BootcampUtils.initializeChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/examples/po");
    }

    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void styleChecks() throws Exception {
        WebElement redBox = driver.findElement(By.id("red_box"));
        assertEquals("rgba(255, 221, 221, 1)", redBox.getCssValue("background-color"));

        WebElement yellowBox = driver.findElement(By.id("yellow_box"));
        assertEquals("rgba(255, 255, 204, 1)", yellowBox.getCssValue("background-color"));

        // 3. Check h1 element font-size 64px
        WebElement h1 = driver.findElement(By.tagName("h1"));
        assertEquals("64px", h1.getCssValue("font-size"));
    }
}