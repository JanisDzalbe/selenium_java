package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.utility.BootcampUtils;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class Sample9Task {
    WebDriver driver;

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
        driver.findElement(By.id("start_green")).click();
//          * 2) check that button does not appear,
//          * but loading text is seen instead   "Loading green..."
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());
        assertEquals("Loading green...",driver.findElement(By.id("loading_green")).getText());

        Thread.sleep(6000);

//          * 3) check that both button
//          * and loading text is not seen,
//          * success is seen instead "Green Loaded"
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText());
    }

    @Test
    public void loadGreenImplicit() throws Exception {
//         TODO:
//          * 1) click on start loading green button
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("start_green")).click();

//          * 2) check that button does not appear,
//          * but loading text is seen instead   "Loading green..."
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());
        assertEquals("Loading green...", driver.findElement(By.id("loading_green")).getText());

//          * 3) check that both button
//          * and loading text is not seen,
//          * success is seen instead "Green Loaded"
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText());
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//         TODO:
//          * 1) click on start loading green button
        driver.findElement(By.id("start_green")).click();
//          * 2) check that button does not appear,
//          * but loading text is seen instead   "Loading green..."
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());
        assertEquals("Loading green...", driver.findElement(By.id("loading_green")).getText());

//          * 3) check that both button
//          * and loading text is not seen,
//          * success is seen instead "Green Loaded"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_green")));
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText());
    }

    @Test
    public void loadGreenAndBlueBonus() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//         TODO:
//          * 0) wait until button to load green and blue appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("start_green_and_blue")));
//          * 1) click on start loading green and blue button
        driver.findElement(By.id("start_green_and_blue")).click();
//          * 2) check that button does not appear, but loading text is seen instead for green
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_without_blue")));
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());
//          * 3) check that button does not appear, but loading text is seen instead for green and blue
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_with_blue")));
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green_with_blue")).isDisplayed());

//          * 4) check that button and loading green does not appear,
//          * but loading text is seen instead for blue and success for green is seen
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_blue_without_green")));
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());

        assertTrue(driver.findElement(By.id("loading_blue_without_green")).isDisplayed());

//          * 5) check that both button and loading text is not seen, success is seen instead
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_green_and_blue")));
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green_with_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_blue_without_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("finish_green_and_blue")).isDisplayed());
    }

}