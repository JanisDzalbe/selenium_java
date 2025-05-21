package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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
import static selenium.sample.Sample9.wait;

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

        WebElement loadGreenButton = driver.findElement(By.id("start_green"));
        loadGreenButton.click();

        assertFalse(loadGreenButton.isDisplayed());
        WebElement loadingGreenText = driver.findElement(By.id("loading_green"));
        assertTrue(loadingGreenText.isDisplayed());

        Thread.sleep(5000);

        WebElement greenLoaded = driver.findElement(By.id("finish_green"));
        assertFalse(loadGreenButton.isDisplayed());
        assertFalse(loadingGreenText.isDisplayed());
        assertTrue(greenLoaded.isDisplayed());


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

        WebElement loadGreenButton = driver.findElement(By.id("start_green"));
        loadGreenButton.click();

        assertFalse(loadGreenButton.isDisplayed());
        WebElement loadingGreenText = driver.findElement(By.id("loading_green"));
        assertTrue(loadingGreenText.isDisplayed());

        WebElement greenLoaded = driver.findElement(By.id("finish_green"));
        assertFalse(loadGreenButton.isDisplayed());
        assertFalse(loadingGreenText.isDisplayed());
        assertTrue(greenLoaded.isDisplayed());
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

        WebElement loadGreenButton = driver.findElement(By.id("start_green"));
        loadGreenButton.click();

        assertFalse(loadGreenButton.isDisplayed());
        WebElement loadingGreenText = driver.findElement(By.id("loading_green"));
        assertTrue(loadingGreenText.isDisplayed());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green")));

        WebElement greenLoaded = driver.findElement(By.id("finish_green"));
        assertFalse(loadGreenButton.isDisplayed());
        assertFalse(loadingGreenText.isDisplayed());
        assertTrue(greenLoaded.isDisplayed());


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
        WebElement loadGreenAndBlueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("start_green_and_blue")));

        WebElement loadGreenButton = driver.findElement(By.id("start_green"));
        loadGreenButton.click();
        loadGreenAndBlueButton.click();


        assertFalse(loadGreenButton.isDisplayed());
        WebElement loadingGreenText = driver.findElement(By.id("loading_green"));
        assertTrue(loadingGreenText.isDisplayed());

        assertFalse(loadGreenAndBlueButton.isDisplayed());
        WebElement loadingGreenAndBlueText = driver.findElement(By.id("loading_green_without_blue"));
        assertTrue(loadingGreenAndBlueText.isDisplayed());

        WebElement greenLoadedBlueWaiting = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_blue_without_green")));

        assertFalse(loadGreenAndBlueButton.isDisplayed());
        assertFalse(loadingGreenAndBlueText.isDisplayed());
        assertTrue(greenLoadedBlueWaiting.isDisplayed());

        WebElement greenLoaded = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_green")));
        assertFalse(loadGreenButton.isDisplayed());
        assertFalse(loadingGreenText.isDisplayed());
        assertTrue(greenLoaded.isDisplayed());

        WebElement greenAndBleuLoaded = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_green_and_blue")));

        assertFalse(loadGreenAndBlueButton.isDisplayed());
        assertFalse(loadingGreenAndBlueText.isDisplayed());
        assertTrue(greenAndBleuLoaded.isDisplayed());
    }

}