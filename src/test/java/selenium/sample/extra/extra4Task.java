package selenium.sample.extra;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.ColorSamplePage;
import selenium.utility.BootcampUtils;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class extra4Task {
    static WebDriver driver;
    static ColorSamplePage colorPage;

    @BeforeEach
    public void openPage() {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        // Set timeout and open page
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://janisdzalbe.github.io/example-site/examples/loading_color");
        colorPage = PageFactory.initElements(driver, ColorSamplePage.class);
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void loadGreenSleep() throws Exception {
//         TODO:
//          Use page object ColorSamplePage
//          * 1) click on start loading green button
            driver.findElement(By.id("start_green")).click();
//          * 2) check that button does not appear,
            assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
//          * but loading text is seen instead   "Loading green..."
            assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());
            Thread.sleep(10000);
//          * 3) check that both button
//          * and loading text is not seen,
//          * success is seen instead "Green Loaded"
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
    }

}