package selenium.sample.extra;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

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
//       TODO:
//        check the background of top 2 sections
        WebElement sec1 = driver.findElement(By.id("red_box"));
        WebElement sec2 = driver.findElement(By.id("yellow_box")) ;
        //        rgba(255, 221, 221, 1);
        assertEquals("rgba(255, 221, 221, 1)", sec1.getCssValue("background-color"));
        assertEquals("rgba(255, 221, 221, 1)", sec2.getCssValue("background-color"));


//        check h1 element font-size 64px
        assertEquals("64px", driver.findElement(By.tagName("h1")).getCssValue("font-size"));
    }
}
