package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        driver.findElement(By.id("start_green")).click();

        assertFalse (driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue (driver.findElement(By.id("loading_green")).isDisplayed());
        assertEquals ("Loading green...", driver.findElement(By.id("loading_green")).getText());

        Thread.sleep(5000);

        assertFalse (driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertTrue (driver.findElement(By.id("finish_green")).isDisplayed());
        assertEquals ("Green Loaded", driver.findElement(By.id("finish_green")).getText());
//         TODO:
//         * 1) click on start loading green button
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
    }

    @Test
    public void loadGreenImplicit() throws Exception {
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.findElement(By.id("start_green")).click();

        assertFalse (driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue (driver.findElement(By.id("loading_green")).isDisplayed());
        assertEquals ("Loading green...", driver.findElement(By.id("loading_green")).getText());

        assertTrue (driver.findElement(By.id("finish_green")).isDisplayed());

        assertFalse (driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertEquals ("Green Loaded", driver.findElement(By.id("finish_green")).getText());
//         TODO:
//         * 1) click on start loading green button
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(5)).ignoring(StaleElementReferenceException.class);
        driver.findElement(By.id("start_green")).click();

        assertFalse (driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue (driver.findElement(By.id("loading_green")).isDisplayed());
        assertEquals ("Loading green...", driver.findElement(By.id("loading_green")).getText());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green")));

        assertFalse (driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertTrue (driver.findElement(By.id("finish_green")).isDisplayed());
        assertEquals ("Green Loaded", driver.findElement(By.id("finish_green")).getText());

//         TODO:
//         * 1) click on start loading green button
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
    }

    @Test
    public void loadGreenAndBlueBonus() {
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(5)).ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("start_green_and_blue")));
        driver.findElement(By.id("start_green_and_blue")).click();



        assertFalse (driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertTrue (driver.findElement(By.id("loading_green_without_blue")).isDisplayed());
        assertEquals ("Loading green...", driver.findElement(By.id("loading_green_without_blue")).getText());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green")));

        assertFalse (driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertTrue (driver.findElement(By.id("finish_green")).isDisplayed());
        assertEquals ("Green Loaded", driver.findElement(By.id("finish_green")).getText());



        /* TODO:
         * 0) wait until button to load green and blue appears
         * 1) click on start loading green and blue button
         * 2) check that button does not appear, but loading text is seen instead for green
         * 3) check that button does not appear, but loading text is seen instead for green and blue
         * 4) check that button and loading green does not appear,
         * 		but loading text is seen instead for blue and success for green is seen
         * 5) check that both button and loading text is not seen, success is seen instead
         */
    }

}