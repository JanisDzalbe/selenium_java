package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.decorators.WebDriverDecorator;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.utility.BootcampUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class Sample9Task {
    WebDriver driver;
    private static WebDriverWait wait;
    static long startTime;

    @BeforeEach
    public void openPage() {
        // Initialize driver
        driver = new ChromeDriver();
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
        WebElement greenButton = driver.findElement(By.id("start_green"));
        greenButton.click();

        assertFalse(greenButton.isDisplayed());

        WebElement loadingGreen = driver.findElement(By.id("loading_green"));
        assertTrue(loadingGreen.isDisplayed());
        assertEquals("Loading green...", loadingGreen.getText());

        Thread.sleep(5000);

        assertFalse(greenButton.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());

        WebElement finishGreen = driver.findElement(By.id("finish_green"));
        assertTrue(finishGreen.isDisplayed());
        assertEquals("Green Loaded", finishGreen.getText());
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

        WebElement greenButton = driver.findElement(By.id("start_green"));
        greenButton.click();

        assertFalse(greenButton.isDisplayed());

        WebElement loadingGreen = driver.findElement(By.id("loading_green"));
        assertTrue(loadingGreen.isDisplayed());
        assertEquals("Loading green...", loadingGreen.getText());

        WebElement finishGreen = driver.findElement(By.id("finish_green"));
        assertTrue(finishGreen.isDisplayed());

        assertFalse(greenButton.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());

        assertEquals("Green Loaded", finishGreen.getText());

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

        WebElement greenButton = driver.findElement(By.id("start_green"));
        greenButton.click();

        assertFalse(greenButton.isDisplayed());

        WebElement loadingGreen = driver.findElement(By.id("loading_green"));
        assertTrue(loadingGreen.isDisplayed());
        assertEquals("Loading green...", loadingGreen.getText());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green")));

        WebElement finishGreen = driver.findElement(By.id("finish_green"));
        assertTrue(finishGreen.isDisplayed());

        assertFalse(greenButton.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());

        assertEquals("Green Loaded", finishGreen.getText());
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