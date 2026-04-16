package selenium.sample.extra;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.ColorSamplePage;
import selenium.utility.BootcampUtils;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class extra4Task {
    static WebDriver driver;
    static ColorSamplePage colorPage;

    @BeforeEach
    public void openPage() {
        driver = BootcampUtils.initializeChromeDriver();
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
        colorPage.clickStartLoadingGreen();
        assertFalse(colorPage.isStartButtonVisible(), "Start button should be hidden");
        assertTrue(colorPage.isLoadingTextVisible(), "Loading text should be visible");
        assertEquals("Loading green...", colorPage.getLoadingText());
        assertTrue(colorPage.isSuccessMessageVisible(), "Success message 'Green Loaded' should appear");
        assertFalse(colorPage.isLoadingTextVisible(), "Loading text should disappear after finish");
        assertEquals("Green Loaded", colorPage.getSuccessMessageText());
    }
}