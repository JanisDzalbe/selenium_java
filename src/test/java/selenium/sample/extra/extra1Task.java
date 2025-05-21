package selenium.sample.extra;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.utility.BootcampUtils;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class extra1Task {
    WebDriver driver;
    String base_url = "https://acctabootcamp.github.io/site/examples/act";
    String new_url = "https://acctabootcamp.github.io/site/examples/link1";

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        //open page:
        driver.get(base_url);
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void navigateBack() throws Exception {
//        TODO
//        open page with url "https://acctabootcamp.github.io/site/examples/po"
//        click "More > " for the top left element
//        check that the url now "https://acctabootcamp.github.io/site/examples/po1"
//        using driver navigation go back to "https://acctabootcamp.github.io/site/examples/po"
//        check that the page now is "https://acctabootcamp.github.io/site/examples/po"
        String startingUrl = "https://acctabootcamp.github.io/site/examples/po";
        String destinationUrl = "https://acctabootcamp.github.io/site/examples/po1";

        driver.get(startingUrl);

        driver.findElement(By.cssSelector(".w3-pale-red a[href='po1']")).click();
        assertEquals(destinationUrl, driver.getCurrentUrl());

        driver.navigate().back();
        assertEquals(startingUrl, driver.getCurrentUrl());
    }

    @Test
    public void navigateForward() throws Exception {
//        TODO
//        open page with url "https://acctabootcamp.github.io/site/examples/po"
//        click "More > " for the top left element
//        using driver navigation go back to "https://acctabootcamp.github.io/site/examples/po"
//        using driver navigation go forward to "https://acctabootcamp.github.io/site/examples/po1"
//        check that the page now is "https://acctabootcamp.github.io/site/examples/po1"
        String startingUrl = "https://acctabootcamp.github.io/site/examples/po";
        String destinationUrl = "https://acctabootcamp.github.io/site/examples/po1";

        driver.get(startingUrl);

        driver.findElement(By.cssSelector(".w3-pale-red a[href='po1']")).click();
        assertEquals(destinationUrl, driver.getCurrentUrl());

        driver.navigate().back();
        assertEquals(startingUrl, driver.getCurrentUrl());

        driver.navigate().forward();
        assertEquals(destinationUrl, driver.getCurrentUrl());
    }

    @Test
    public void refresh() throws Exception {
//        TODO
//        open page "https://acctabootcamp.github.io/site/examples/act"
//        click on "Show" button in 'Button' section
//        check that text "I am here!" is seen
//        refresh page
//        check that text "I am here!" is not seen
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(10)).ignoring(StaleElementReferenceException.class);
        driver.get("https://acctabootcamp.github.io/site/examples/act");

        wait.until(driver -> driver.findElement(By.id("show_text")).isDisplayed());
        driver.findElement(By.id("show_text")).click();
        assertTrue(driver.findElement(By.id("show_me")).isDisplayed());
        assertEquals("I am here!", driver.findElement(By.id("show_me")).getText());

        driver.navigate().refresh();
        assertFalse(driver.findElement(By.id("show_me")).isDisplayed());
    }
}
