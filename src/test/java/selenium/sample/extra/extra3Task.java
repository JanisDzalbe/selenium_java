package selenium.sample.extra;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;
import org.openqa.selenium.support.Color;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class extra3Task {
    WebDriver driver;

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        //open page:
        driver.get("https://janisdzalbe.github.io/example-site/examples/po");
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void styleChecks() throws Exception {
        WebElement header = driver.findElement(By.id("header"));
        WebElement underHeader = driver.findElement(By.xpath("//div[h1]"));
        WebElement h1 = driver.findElement(By.tagName("h1"));
        Color black = Color.fromString("rgba(0, 0, 0, 0)");
        Color lightGray = Color.fromString("#f1f1f1");

        assertEquals(black, Color.fromString(header.getCssValue("background-color")));
        assertEquals(lightGray, Color.fromString(underHeader.getCssValue("background-color")));
        assertEquals("64px", h1.getCssValue("font-size"));

//       TODO:
//        check the background of top 2 sections
//        rgba(255, 221, 221, 1);  ???? don't understand this part
//        check h1 element font-size 64px
    }
}
