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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Activity 9: Practicing Thread.sleep, implicit waits, and explicit waits
 * on dynamically loading page elements.
 */
public class Sample9Task {
    WebDriver driver;

    /**
     * Initializes ChromeDriver and opens the Color Loading example page before each test.
     */
    @BeforeEach
    public void openPage() {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        // load web page
        driver.get("https://janisdzalbe.github.io/example-site/examples/loading_color");
    }

    /**
     * Closes the browser after each test.
     */
    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    /**
     * Uses Thread.sleep to wait for the green loader to finish and verifies each loading stage.
     *
     * @throws Exception if the sleep is interrupted or elements cannot be found
     */
    @Test
    public void loadGreenSleep() throws Exception {
//         TODO:
//          * 1) click on start loading green button
        WebElement startGreenButton = driver.findElement(By.id("start_green"));
        startGreenButton.click();

//          * 2) check that button does not appear,
//          * but loading text is seen instead   "Loading green..."
        assertFalse(startGreenButton.isDisplayed());
        WebElement loadingGreen = driver.findElement(By.id("loading_green"));
        assertTrue(loadingGreen.isDisplayed());
        assertEquals("Loading green...", loadingGreen.getText());

//          * 3) check that both button
//          * and loading text is not seen,
//          * success is seen instead "Green Loaded"
        Thread.sleep(5000);
        assertFalse(startGreenButton.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());
        WebElement finishGreen = driver.findElement(By.id("finish_green"));
        assertTrue(finishGreen.isDisplayed());
        assertEquals("Green Loaded", finishGreen.getText());
    }

    /**
     * Uses an implicit wait to wait for the green loader to finish and verifies each loading stage.
     *
     * @throws Exception if elements cannot be found within the implicit timeout
     */
    @Test
    public void loadGreenImplicit() throws Exception {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//         TODO:
//          * 1) click on start loading green button
        WebElement startGreenButton = driver.findElement(By.id("start_green"));
        startGreenButton.click();

//          * 2) check that button does not appear,
//          * but loading text is seen instead   "Loading green..."
        assertFalse(startGreenButton.isDisplayed());
        WebElement loadingGreen = driver.findElement(By.id("loading_green"));
        assertTrue(loadingGreen.isDisplayed());
        assertEquals("Loading green...", loadingGreen.getText());

//          * 3) check that both button
//          * and loading text is not seen,
//          * success is seen instead "Green Loaded"
        WebElement finishGreen = driver.findElement(By.id("finish_green"));
        assertFalse(startGreenButton.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());
        assertTrue(finishGreen.isDisplayed());
        assertEquals("Green Loaded", finishGreen.getText());
    }

    /**
     * Uses an explicit WebDriverWait to wait for the green loader to finish and verifies each loading stage.
     *
     * @throws Exception if elements do not appear within the explicit timeout
     */
    @Test
    public void loadGreenExplicitWait() throws Exception {
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(10))
                .ignoring(StaleElementReferenceException.class);

//         TODO:
//          * 1) click on start loading green button
        WebElement startGreenButton = driver.findElement(By.id("start_green"));
        startGreenButton.click();

//          * 2) check that button does not appear,
//          * but loading text is seen instead   "Loading green..."
        assertFalse(startGreenButton.isDisplayed());
        WebElement loadingGreen = driver.findElement(By.id("loading_green"));
        assertTrue(loadingGreen.isDisplayed());
        assertEquals("Loading green...", loadingGreen.getText());

//          * 3) check that both button
//          * and loading text is not seen,
//          * success is seen instead "Green Loaded"
        WebElement finishGreen = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("finish_green"))
        );
        assertFalse(startGreenButton.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());
        assertTrue(finishGreen.isDisplayed());
        assertEquals("Green Loaded", finishGreen.getText());
    }

    /**
     * Uses explicit waits to track all multi-stage states of the "Start loading green and blue" loader.
     */
    @Test
    public void loadGreenAndBlueBonus() {
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(10))
                .ignoring(StaleElementReferenceException.class);

//         TODO:
//          * 0) wait until button to load green and blue appears
        WebElement startGreenAndBlueButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("start_green_and_blue"))
        );

//          * 1) click on start loading green and blue button
        startGreenAndBlueButton.click();

//          * 2) check that button does not appear, but loading text is seen instead for green
        assertFalse(startGreenAndBlueButton.isDisplayed());
        WebElement loadingGreen = driver.findElement(By.id("loading_green_without_blue"));
        assertTrue(loadingGreen.isDisplayed());
        assertEquals("Loading green...", loadingGreen.getText());

//          * 3) check that button does not appear, but loading text is seen instead for green and blue
        WebElement loadingBlue = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_with_blue"))
        );
        assertFalse(startGreenAndBlueButton.isDisplayed());
        assertTrue(loadingGreen.isDisplayed());
        assertEquals("Loading green...", loadingGreen.getText());
        assertTrue(loadingBlue.isDisplayed());
        assertEquals("Loading blue...", loadingBlue.getText());

//          * 4) check that button and loading green does not appear,
//          * but loading text is seen instead for blue and success for green is seen
        WebElement greenFinished = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("loading_blue_without_green"))
        );
        assertFalse(startGreenAndBlueButton.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());
        assertTrue(loadingBlue.isDisplayed());
        assertEquals("Loading blue...", loadingBlue.getText());
        assertTrue(greenFinished.isDisplayed());
        assertEquals("Green finished waiting for blue", greenFinished.getText());

//          * 5) check that both button and loading text is not seen, success is seen instead
        WebElement finishGreenAndBlue = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("finish_green_and_blue"))
        );
        assertFalse(startGreenAndBlueButton.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());
        assertFalse(loadingBlue.isDisplayed());
        assertFalse(greenFinished.isDisplayed());
        assertTrue(finishGreenAndBlue.isDisplayed());
        assertEquals("Green and Blue Loaded", finishGreenAndBlue.getText());
    }
}