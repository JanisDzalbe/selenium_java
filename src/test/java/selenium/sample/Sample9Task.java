package selenium.sample;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.utility.BootcampUtils;

public class Sample9Task {
    WebDriver driver;

    private static final By start_green = By.id("start_green");
    private static final By loading_green = By.id("loading_green");
    private static final By finish_green = By.id("finish_green");

    @BeforeEach
    public void openPage() {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        // load web page
        driver.get("https://janisdzalbe.github.io/example-site/examples/loading_color");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void loadGreenSleep() throws Exception {
//         TODO:
//          * 1) click on start loading green button
             driver.findElement(start_green).click();

//          * 2) check that button does not appear,
            assertFalse(driver.findElement(start_green).isDisplayed());
            assertEquals("Loading green...", driver.findElement(loading_green).getText());

//          * but loading text is seen instead   "Loading green..."
//          * 3) check that both button
            Thread.sleep(10000);

            assertFalse(driver.findElement(start_green).isDisplayed());
            assertFalse(driver.findElement(loading_green).isDisplayed());
            assertEquals("Green Loaded", driver.findElement(finish_green).getText());

//          * and loading text is not seen,
//          * success is seen instead "Green Loaded"
    }

    @Test
    public void loadGreenImplicit() throws Exception {
//         TODO:
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//          * 1) click on start loading green button
             driver.findElement(start_green).click();

//          * 2) check that button does not appear,
//          * but loading text is seen instead   "Loading green..."
            assertFalse(driver.findElement(start_green).isDisplayed());
            assertEquals("Loading green...", driver.findElement(loading_green).getText());

//          * 3) check that both button
//          * and loading text is not seen,
//          * success is seen instead "Green Loaded"
            assertEquals("Green Loaded", driver.findElement(finish_green).getText());
            assertFalse(driver.findElement(start_green).isDisplayed());
            assertFalse(driver.findElement(loading_green).isDisplayed());

    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
//         TODO:
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//          * 1) click on start loading green button
            driver.findElement(start_green).click();
//          * 2) check that button does not appear,
//          * but loading text is seen instead   "Loading green..."
            assertEquals("Loading green...", wait.until(ExpectedConditions.visibilityOfElementLocated(loading_green)).getText());
            assertFalse(driver.findElement(start_green).isDisplayed());

//          * 3) check that both button
//          * and loading text is not seen,
//          * success is seen instead "Green Loaded"|
            assertEquals("Green Loaded", wait.until(ExpectedConditions.visibilityOfElementLocated(finish_green)).getText());
            assertFalse(driver.findElement(start_green).isDisplayed());
            assertFalse(driver.findElement(loading_green).isDisplayed());
    }

    @Test
    public void loadGreenAndBlueBonus() {
//         TODO:
//          * 0) wait until button to load green and blue appears
//          * 1) click on start loading green and blue button
//          * 2) check that button does not appear, but loading text is seen instead for green
//          * 3) check that button does not appear, but loading text is seen instead for green and blue
//          * 4) check that button and loading green does not appear,
//          * but loading text is seen instead for blue and success for green is seen
//          * 5) check that both button and loading text is not seen, success is seen instead
    }

}