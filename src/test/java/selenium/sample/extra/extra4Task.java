package selenium.sample.extra;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.ColorSamplePage;
import selenium.utility.BootcampUtils;

import java.time.Duration;

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
        colorPage.clickStartLoadingGreen();

        colorPage.loadingButtonIsNotVisible();
        colorPage.loadingGreenTextIsVisible();

        Thread.sleep(5000);

        colorPage.loadingButtonIsNotVisible();
        colorPage.loadingGreenTextIsNotVisible();
        colorPage.loadingGreenTextHasCompleted();
    }

}