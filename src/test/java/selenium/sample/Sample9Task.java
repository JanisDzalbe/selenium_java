package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.utility.BootcampUtils;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class Sample9Task {
    WebDriver driver;

    @BeforeEach
    public void openPage() {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        // load web page
        driver.get("https://acctabootcamp.github.io/site/examples/loading_color");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void loadGreenSleep() throws Exception {
//         TODO:
//         * 1) click on start loading green button

//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."

//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"


        WebElement startButton = driver.findElement(By.id("start_green"));
        startButton.click();
        boolean buttonVisible = driver.findElement(By.id("start_green")).isDisplayed();
        assertFalse(buttonVisible);
        WebElement loading = driver.findElement(By.id("loading_green"));
        assertTrue(loading.isDisplayed());
        String loadingText = loading.getText();
        assertEquals("Loading green...", loadingText);
        Thread.sleep(5000);
        boolean buttonGone = driver.findElement(By.id("start_green")).isDisplayed();
        assertFalse(buttonGone);
        boolean loadingGone = driver.findElement(By.id("loading_green")).isDisplayed();
        assertFalse(loadingGone);
        WebElement finish = driver.findElement(By.id("finish_green"));
        assertTrue(finish.isDisplayed());
        String result = finish.getText();
        assertEquals("Green Loaded", result);
    }


    @Test
    public void loadGreenImplicit() throws Exception {
//         TODO:
//         * 1) click on start loading green button
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"


        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.id("start_green")).click();
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());
        assertEquals("Loading green...", driver.findElement(By.id("loading_green")).getText());
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText());
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
//         TODO:
//         * 1) click on start loading green button
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"

        var wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.ignoring(StaleElementReferenceException.class);
        WebElement greenButton = driver.findElement(By.id("start_green"));
        greenButton.click();
        boolean isGreenButtonVisible = driver.findElement(By.id("start_green")).isDisplayed();
        assertFalse(isGreenButtonVisible);
        WebElement greenLoading = driver.findElement(By.id("loading_green"));
        assertTrue(greenLoading.isDisplayed());
        assertEquals("Loading green...", greenLoading.getText());
        By finishSelector = By.id("finish_green");
        wait.until(ExpectedConditions.presenceOfElementLocated(finishSelector));
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        WebElement greenFinished = driver.findElement(finishSelector);
        assertTrue(greenFinished.isDisplayed());
        assertEquals("Green Loaded", greenFinished.getText());
    }

    @Test
    public void loadGreenAndBlueBonus() {
        /* TODO:
         * 0) wait until button to load green and blue appears
         * 1) click on start loading green and blue button
         * 2) check that button does not appear, but loading text is seen instead for green
         * 3) check that button does not appear, but loading text is seen instead for green and blue
         * 4) check that button and loading green does not appear,
         * 		but loading text is seen instead for blue and success for green is seen
         * 5) check that both button and loading text is not seen, success is seen instead
         */

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.ignoring(StaleElementReferenceException.class);

        By startButton = By.id("start_green_and_blue");
        wait.until(ExpectedConditions.visibilityOfElementLocated(startButton));

        driver.findElement(startButton).click();
        By greenOnlyLoader = By.id("loading_green_without_blue");
        wait.until(ExpectedConditions.visibilityOfElementLocated(greenOnlyLoader));
        assertFalse(driver.findElement(startButton).isDisplayed());

        By greenAndBlueLoader = By.id("loading_green_with_blue");
        wait.until(ExpectedConditions.visibilityOfElementLocated(greenAndBlueLoader));
        assertTrue(driver.findElement(greenOnlyLoader).isDisplayed());
        assertTrue(driver.findElement(greenAndBlueLoader).isDisplayed());
        By finishStatus = By.id("finish_green_and_blue");
        wait.until(ExpectedConditions.visibilityOfElementLocated(finishStatus));
        assertFalse(driver.findElement(greenOnlyLoader).isDisplayed());
        assertFalse(driver.findElement(greenAndBlueLoader).isDisplayed());

        String statusText = driver.findElement(finishStatus).getText().replaceAll("\\s+", " ").trim().toLowerCase();

        assertTrue(statusText.contains("green"));
        assertTrue(statusText.contains("blue"));
        assertTrue(statusText.contains("loaded"));
    }

}