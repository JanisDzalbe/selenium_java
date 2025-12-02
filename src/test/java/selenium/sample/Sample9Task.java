package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.utility.BootcampUtils;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
//          * 2) check that button does not appear,
//          * but loading text is seen instead   "Loading green..."
//          * 3) check that both button
//          * and loading text is not seen,
//          * success is seen instead "Green Loaded"

        driver.findElement(By.id("start_green")).click();
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());
        Thread.sleep(6000);
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());

    }

    @Test
    public void loadGreenImplicit() throws Exception {
//         TODO:
//          * 1) click on start loading green button
//          * 2) check that button does not appear,
//          * but loading text is seen instead   "Loading green..."
//          * 3) check that both button
//          * and loading text is not seen,
//          * success is seen instead "Green Loaded"
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.id("start_green")).click();
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());

        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
//         TODO:
//          * 1) click on start loading green button
//          * 2) check that button does not appear,
//          * but loading text is seen instead   "Loading green..."
//          * 3) check that both button
//          * and loading text is not seen,
//          * success is seen instead "Green Loaded"
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("start_green")).click();
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green")));

        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("start_green_and_blue")));
        driver.findElement(By.id("start_green_and_blue")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_without_blue")));
        assertEquals("Loading green...", driver.findElement(By.id("loading_green_without_blue")).getText());

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_with_blue")));
        assertEquals("Loading blue...", driver.findElement(By.id("loading_green_with_blue")).getText());

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_blue_without_green")));
        assertEquals("Green finished waiting for blue", driver.findElement(By.id("loading_blue_without_green")).getText());
        assertEquals("Loading blue...", driver.findElement(By.id("loading_green_with_blue")).getText());

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_green_and_blue")));
        assertEquals("Green and Blue Loaded", driver.findElement(By.id("finish_green_and_blue")).getText());

        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green_with_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());

    }

}