package selenium.sample.extra;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.utility.BootcampUtils;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class extra1Task {
    WebDriver driver;
    String base_url = "https://acctabootcamp.github.io/site/examples/act";
    String new_url = "https://acctabootcamp.github.io/site/examples/link1";
    String poUrl = "https://acctabootcamp.github.io/site/examples/po";
    String po1Url = "https://acctabootcamp.github.io/site/examples/po1";

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

        driver.get(poUrl);
        driver.findElement(By.cssSelector("div.w3-pale-red p a")).click();
        assertEquals(po1Url, driver.getCurrentUrl());
        driver.navigate().back();
        assertEquals(poUrl, driver.getCurrentUrl());
    }

    @Test
    public void navigateForward() throws Exception {

//        TODO
//        open page with url "https://acctabootcamp.github.io/site/examples/po"
//        click "More > " for the top left element
//        using driver navigation go back to "https://acctabootcamp.github.io/site/examples/po"
//        using driver navigation go forward to "https://acctabootcamp.github.io/site/examples/po1"
//        check that the page now is "https://acctabootcamp.github.io/site/examples/po1"
        driver.get(poUrl);
        driver.findElement(By.cssSelector("div.w3-pale-red p a")).click();
        assertEquals(po1Url, driver.getCurrentUrl());
        driver.navigate().back();
        assertEquals(poUrl, driver.getCurrentUrl());
        driver.navigate().forward();
        assertEquals(po1Url, driver.getCurrentUrl());
    }

    @Test
    public void refresh() throws Exception {
//        TODO
//        open page "https://acctabootcamp.github.io/site/examples/act"
//        click on "Show" button in 'Button' section
//        check that text "I am here!" is seen
//        refresh page
//        check that text "I am here!" is not seen

        //I believe the element doesn't load instantly, maybe because of redirects from /act to /actions, not sure.
        // Either way, adding a wait seems to fix it - https://www.selenium.dev/documentation/webdriver/waits/#implicit-waits
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        //Also, I would define the show_me as a variable, but it gets "stale" after refresh. TODO -> ask about this
        assertFalse(driver.findElement(By.id("show_me")).isDisplayed());
        driver.findElement(By.id("show_text")).click();
        assertTrue(driver.findElement(By.id("show_me")).isDisplayed());
        driver.navigate().refresh();
        assertFalse(driver.findElement(By.id("show_me")).isDisplayed());
    }
}
