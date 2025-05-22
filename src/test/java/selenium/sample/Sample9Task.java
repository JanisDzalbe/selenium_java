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
//         TODO:
//         * 1) click on start loading green button
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        WebElement startBtn = driver.findElement(By.cssSelector("#start_green"));
        startBtn.click();

        assertFalse(startBtn.isDisplayed());
        WebElement loadingTxt = driver.findElement(By.cssSelector("#loading_green"));
        assertTrue(loadingTxt.isDisplayed());
        assertEquals("Loading green...", loadingTxt.getText());

        Thread.sleep(5000);

        assertFalse(startBtn.isDisplayed());
        assertFalse(loadingTxt.isDisplayed());
        WebElement finishTxt = driver.findElement(By.cssSelector("#finish_green"));
        assertTrue(finishTxt.isDisplayed());
        assertEquals("Green Loaded", finishTxt.getText());
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

        WebElement startBtn = driver.findElement(By.cssSelector("#start_green"));
        startBtn.click();

        assertFalse(startBtn.isDisplayed());
        WebElement loadingTxt = driver.findElement(By.cssSelector("#loading_green"));
        assertTrue(loadingTxt.isDisplayed());
        assertEquals("Loading green...", loadingTxt.getText());

        WebElement finishTxt = driver.findElement(By.cssSelector("#finish_green"));
        assertTrue(finishTxt.isDisplayed());
        assertFalse(startBtn.isDisplayed());
        assertFalse(loadingTxt.isDisplayed());
        assertEquals("Green Loaded", finishTxt.getText());
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
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(5)).ignoring(StaleElementReferenceException.class);

        WebElement startBtn = driver.findElement(By.cssSelector("#start_green"));
        startBtn.click();

        assertFalse(startBtn.isDisplayed());
        WebElement loadingTxt = driver.findElement(By.cssSelector("#loading_green"));
        assertTrue(loadingTxt.isDisplayed());
        assertEquals("Loading green...", loadingTxt.getText());

        WebElement finishTxt = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#finish_green")));

        assertTrue(finishTxt.isDisplayed());
        assertFalse(startBtn.isDisplayed());
        assertFalse(loadingTxt.isDisplayed());
        assertEquals("Green Loaded", finishTxt.getText());
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
    }

}