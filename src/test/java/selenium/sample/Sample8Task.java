package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
        assertEquals("rgba(255, 221, 221, 1)",driver.findElement(By.xpath("/html/body/div[3]/div[1]/div")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 204, 1)",driver.findElement(By.xpath("/html/body/div[3]/div[2]/div")).getCssValue("background-color"));
//        check h1 element font-size 64px
        assertEquals("64px",driver.findElement(By.className("w3-jumbo")).getCssValue("font-size"));
    }
}
