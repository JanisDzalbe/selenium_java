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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Sample9Task {
    static final By START_GREEN = By.id("start_green");
    static final By LOADING_GREEN = By.id("loading_green");
    static final By FINISH_GREEN = By.id("finish_green");
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
        // 1) click on start loading green button
        driver.findElement(START_GREEN).click();

        // 2) check that button does not appear, but loading text is seen instead "Loading green..."
        assertFalse(driver.findElement(START_GREEN).isDisplayed());
        assertTrue(driver.findElement(LOADING_GREEN).isDisplayed());
        assertEquals("Loading green...", driver.findElement(LOADING_GREEN).getText());

        // 3) check that both button and loading text is not seen, success is seen instead "Green Loaded"
        Thread.sleep(6000);
        assertFalse(driver.findElement(START_GREEN).isDisplayed());
        assertFalse(driver.findElement(LOADING_GREEN).isDisplayed());
        assertTrue(driver.findElement(FINISH_GREEN).isDisplayed());
        assertEquals("Green Loaded", driver.findElement(FINISH_GREEN).getText());
    }

    @Test
    public void loadGreenImplicit() throws Exception {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // 1) click on start loading green button
        driver.findElement(START_GREEN).click();

        // 2) check that button does not appear, but loading text is seen instead "Loading green..."
        assertFalse(driver.findElement(START_GREEN).isDisplayed());
        assertTrue(driver.findElement(LOADING_GREEN).isDisplayed());
        assertEquals("Loading green...", driver.findElement(LOADING_GREEN).getText());

        // 3) check that both button and loading text is not seen, success is seen instead "Green Loaded"
        driver.findElement(FINISH_GREEN); // waits up to 10 seconds for the element to appear
        assertFalse(driver.findElement(START_GREEN).isDisplayed());
        assertFalse(driver.findElement(LOADING_GREEN).isDisplayed());
        assertTrue(driver.findElement(FINISH_GREEN).isDisplayed());
        assertEquals("Green Loaded", driver.findElement(FINISH_GREEN).getText());
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1) click on start loading green button
        driver.findElement(START_GREEN).click();

        // 2) check that button does not appear, but loading text is seen instead "Loading green..."
        assertFalse(driver.findElement(START_GREEN).isDisplayed());
        assertTrue(driver.findElement(LOADING_GREEN).isDisplayed());
        assertEquals("Loading green...", driver.findElement(LOADING_GREEN).getText());

        // 3) check that both button and loading text is not seen, success is seen instead "Green Loaded"
        wait.until(ExpectedConditions.visibilityOfElementLocated(FINISH_GREEN));
        assertFalse(driver.findElement(START_GREEN).isDisplayed());
        assertFalse(driver.findElement(LOADING_GREEN).isDisplayed());
        assertTrue(driver.findElement(FINISH_GREEN).isDisplayed());
        assertEquals("Green Loaded", driver.findElement(FINISH_GREEN).getText());
    }

    @Test
    public void loadGreenAndBlueBonus() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By startButton = By.id("start_green_and_blue");
        By loadingGreen = By.id("loading_green_without_blue");
        By loadingBlue = By.id("loading_green_with_blue");
        By greenLoaded = By.id("loading_blue_without_green");
        By finish = By.id("finish_green_and_blue");

        // 0) wait until button to load green and blue appears
        wait.until(ExpectedConditions.elementToBeClickable(startButton));

        // 1) click on start loading green and blue button
        driver.findElement(startButton).click();

        // 2) check that button does not appear, but loading text is seen instead for green
        wait.until(ExpectedConditions.visibilityOfElementLocated(loadingGreen));
        assertFalse(driver.findElement(startButton).isDisplayed());
        assertEquals("Loading green...", driver.findElement(loadingGreen).getText());
        assertTrue(driver.findElements(loadingBlue).isEmpty());

        // 3) check that button does not appear, but loading text is seen instead for green and blue
        wait.until(ExpectedConditions.visibilityOfElementLocated(loadingBlue));
        assertFalse(driver.findElement(startButton).isDisplayed());
        assertEquals("Loading green...", driver.findElement(loadingGreen).getText());
        assertEquals("Loading blue...", driver.findElement(loadingBlue).getText());

        // 4) check that button and loading green does not appear,
        // but loading text is seen instead for blue and success for green is seen
        wait.until(ExpectedConditions.visibilityOfElementLocated(greenLoaded));
        assertFalse(driver.findElement(startButton).isDisplayed());
        assertFalse(driver.findElement(loadingGreen).isDisplayed());
        assertEquals("Loading blue...", driver.findElement(loadingBlue).getText());
        assertEquals("Green finished waiting for blue", driver.findElement(greenLoaded).getText());

        // 5) check that both button and loading text is not seen, success is seen instead
        wait.until(ExpectedConditions.visibilityOfElementLocated(finish));
        assertFalse(driver.findElement(startButton).isDisplayed());
        assertFalse(driver.findElement(loadingGreen).isDisplayed());
        assertFalse(driver.findElement(loadingBlue).isDisplayed());
        assertFalse(driver.findElement(greenLoaded).isDisplayed());
        assertEquals("Green and Blue Loaded", driver.findElement(finish).getText());
    }

}