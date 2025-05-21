package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.pages.ColorSamplePage;
import selenium.utility.BootcampUtils;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class Sample10Task {
    static WebDriver driver;
    static ColorSamplePage colorPage;

    @BeforeEach
    public void openPage() {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        // Set timeout and open page
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://acctabootcamp.github.io/site/examples/loading_color");
        colorPage = PageFactory.initElements(driver, ColorSamplePage.class);
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void loadGreenSleep() throws Exception {
//         TODO:
//         Use page object ColorSamplePage
//         * 1) click on start loading green button
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"

        colorPage.ClickGreenStartButton();

        assertFalse(colorPage.isGreenButtonVisible(), "Start button should disappear");
        assertTrue(colorPage.isGreenLoadingVisible(), "Loading label should appear");
        assertEquals("Loading green...", colorPage.getGreenLoadingText(), "Expected loading text mismatch");
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver -> !colorPage.isGreenLoadingVisible());

        assertFalse(colorPage.isGreenButtonVisible(), "Start button should still be gone");
        assertFalse(colorPage.isGreenLoadingVisible(), "Loading label should be gone");

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_green")));

        String successText = colorPage.getGreenSuccessText().toLowerCase().trim();
        assertAll("Success message should include both 'green' and 'loaded'",
                () -> assertTrue(successText.contains("green"), "Success message should contain 'green'"),
                () -> assertTrue(successText.contains("loaded"), "Success message should contain 'loaded'")
        );
    }
}