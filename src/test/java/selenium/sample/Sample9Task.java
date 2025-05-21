package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
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

        WebElement greenButton = driver.findElement(By.id("startLoadingGreen"));
        greenButton.click();
        Thread.sleep(5000);

        assertFalse(greenButton.isDisplayed());

        WebElement loadingGreen = driver.findElement(By.id("loadingGreen"));
        assertTrue(loadingGreen.isDisplayed());
        assertEquals("Loading green...", loadingGreen.getText());

        Thread.sleep(5000);

        assertFalse(greenButton.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());

        WebElement greenSuccess = driver.findElement(By.id("greenSuccess"));
        assertTrue(greenSuccess.isDisplayed());
        assertEquals("Green Loaded", greenSuccess.getText());
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

        WebElement greenButton = driver.findElement(By.id("startLoadingGreen"));
        greenButton.click();

        assertFalse(greenButton.isDisplayed());

        WebElement loadingGreen = driver.findElement(By.id("loadingGreen"));
        assertTrue(loadingGreen.isDisplayed());
        assertEquals("Loading green...", loadingGreen.getText());

        WebElement greenSuccess = driver.findElement(By.id("greenSuccess"));
        assertTrue(greenSuccess.isDisplayed());
        assertEquals("Green Loaded", greenSuccess.getText());
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement greenButton = driver.findElement(By.id("startLoadingGreen"));
        greenButton.click();

        assertFalse(greenButton.isDisplayed());

        WebElement loadingGreen = driver.findElement(By.id("loadingGreen"));
        assertTrue(loadingGreen.isDisplayed());
        assertEquals("Loading green...", loadingGreen.getText());

        WebElement greenSuccess = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("greenSuccess")));
        assertEquals("Green Loaded", greenSuccess.getText());
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
        wait.until(ExpectedConditions.elementToBeClickable(By.id("startLoadingGreenAndBlue")));

        driver.findElement(By.id("startLoadingGreenAndBlue")).click();

        assertFalse(driver.findElement(By.id("startLoadingGreenAndBlue")).isDisplayed());
        assertTrue(driver.findElement(By.id("loadingGreenWithoutBlue")).isDisplayed());
        assertEquals("Loading green...", driver.findElement(By.id("loadingGreenWithoutBlue")).getText());

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loadingGreenWithBlue")));
        assertFalse(driver.findElement(By.id("startLoadingGreenAndBlue")).isDisplayed());
        assertTrue(driver.findElement(By.id("loadingGreenWithBlue")).isDisplayed());
        assertEquals("Loading blue...", driver.findElement(By.id("loadingGreenWithBlue")).getText());

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingGreenWithoutBlue")));
        assertFalse(driver.findElement(By.id("startLoadingGreenAndBlue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loadingGreenWithoutBlue")).isDisplayed());

        wait.until(ExpectedConditions.presenceOfElementLocated((By.id("finishLoadingGreenWithBlue"))));
        assertFalse(driver.findElement(By.id("startLoadingGreenAndBlue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loadingGreenWithoutBlue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loadingGreenWithBlue")).isDisplayed());
        assertTrue(driver.findElement(By.id("finishLoadingGreenWithBlue")).isDisplayed());
        assertEquals("Green and Blue Loaded",driver.findElement(By.id("finishLoadingGreenWithBlue")).getText());

    }
}