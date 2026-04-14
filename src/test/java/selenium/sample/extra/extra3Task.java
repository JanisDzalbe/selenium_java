package selenium.sample.extra;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import selenium.utility.BootcampUtils;

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
//       TODO:
//        check the background of top 2 sections
//        rgba(255, 221, 221, 1);
//        check h1 element font-size 64px
        WebElement sectionLeft = driver.findElement(By.id("red_box"));
        WebElement sectionRight = driver.findElement(By.id("yellow_box"));
        WebElement h1 = driver.findElement(By.className("w3-jumbo"));

        assertEquals(Color.fromString("rgba(255, 221, 221, 1)"), Color.fromString(sectionLeft.getCssValue("background-color")));
        assertEquals(Color.fromString("rgba(255, 255, 204, 1)"), Color.fromString(sectionRight.getCssValue("background-color")));
        assertEquals("64px", h1.getCssValue("font-size"));

    }
}
