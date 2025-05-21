package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
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
        driver.findElement(By.id("start_green")).click();

        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());

        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());

        Thread.sleep(10000);

        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText());
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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("start_green")).click();

        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());

        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());

        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
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
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(10)).ignoring(StaleElementReferenceException.class);
        driver.findElement(By.id("start_green")).click();

        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());

        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green")));

        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText());
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
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(10)).ignoring(StaleElementReferenceException.class);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("start_green_and_blue")));
        driver.findElement(By.id("start_green_and_blue")).click();

        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());

        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_with_blue")));
        assertTrue(driver.findElement(By.id("loading_green_with_blue")).isDisplayed());

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading_green_without_blue")));
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_blue_without_green")).isDisplayed());
        assertEquals("Green finished waiting for blue", driver.findElement(By.id("loading_blue_without_green")).getText());

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading_blue_without_green")));
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green_with_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_blue_without_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("finish_green_and_blue")).isDisplayed());
        assertEquals("Green and Blue Loaded", driver.findElement(By.id("finish_green_and_blue")).getText());
    }

}