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
        // 1. Click Start loading green button
        WebElement startLoadingGreenButton = driver.findElement(By.xpath("//*[text()=\"Start loading green\"]"));
        startLoadingGreenButton.click();

        // 2. Check button is hidden and loading text is displayed
        assertFalse(startLoadingGreenButton.isDisplayed());

        WebElement loadingGreen = driver.findElement(By.id("loading_green"));

        assertTrue(loadingGreen.isDisplayed());
        assertEquals("Loading green...", loadingGreen.getText());

        // Wait 5 seconds
        Thread.sleep(5000);

        // 3. Check button and loading text are hidden
        assertFalse(startLoadingGreenButton.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());

        // Check success message
        WebElement greenLoaded = driver.findElement(By.id("finish_green"));

        assertTrue(greenLoaded.isDisplayed());
        assertEquals("Green Loaded", greenLoaded.getText());
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
        // Set implicit wait to 6 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

        // 1. Click Start loading green
        WebElement startLoadingGreenButton = driver.findElement(By.xpath("//*[text()=\"Start loading green\"]"));
        startLoadingGreenButton.click();

        // 2. Check button is hidden and loading text is displayed
        assertFalse(startLoadingGreenButton.isDisplayed());

        WebElement loadingGreen = driver.findElement(By.id("loading_green"));

        assertTrue(loadingGreen.isDisplayed());
        assertEquals("Loading green...", loadingGreen.getText());

        // 3. Wait for the new success element to appear
        WebElement greenLoaded = driver.findElement(By.id("finish_green"));

        // Check button and loading text are hidden
        assertFalse(startLoadingGreenButton.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());

        // Check success message
        assertTrue(greenLoaded.isDisplayed());
        assertEquals("Green Loaded", greenLoaded.getText());
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
        // Create explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));

        // 1. Click Start loading green
        WebElement startLoadingGreenButton = driver.findElement(By.xpath("//*[text()=\"Start loading green\"]"));
        startLoadingGreenButton.click();

        // 2. Check button is hidden
        assertFalse(startLoadingGreenButton.isDisplayed());

        // Wait until loading text is visible
        WebElement loadingGreen = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("loading_green")));

        assertTrue(loadingGreen.isDisplayed());
        assertEquals("Loading green...", loadingGreen.getText());

        // 3. Wait until loading text disappears
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.id("loading_green")));

        // Wait until success message is visible
        WebElement greenLoaded = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("finish_green")));

        // Verify final state
        assertFalse(startLoadingGreenButton.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());

        assertTrue(greenLoaded.isDisplayed());
        assertEquals("Green Loaded", greenLoaded.getText());

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));

        // 0. Wait for green and blue button to appear
        WebElement greenBlueButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("start_green_and_blue")));

        // 1. Click the button
        greenBlueButton.click();

        // 2. Button is hidden and green is loading
        assertFalse(greenBlueButton.isDisplayed());

        WebElement loadingGreen = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("loading_green_without_blue")));

        assertEquals("Loading green...", loadingGreen.getText());

        // 3. Wait for blue loading message to appear
        WebElement loadingBlue = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("loading_green_with_blue")));

        // Both green and blue are loading
        assertFalse(greenBlueButton.isDisplayed());
        assertTrue(loadingGreen.isDisplayed());
        assertTrue(loadingBlue.isDisplayed());

        assertEquals("Loading blue...", loadingBlue.getText());

        // 4. Wait until green loading finishes
        WebElement greenFinished = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("loading_blue_without_green")));

        // Green loading disappears, blue is still loading
        assertFalse(loadingGreen.isDisplayed());
        assertTrue(loadingBlue.isDisplayed());

        assertEquals("Green finished waiting for blue",
                greenFinished.getText());

        // 5. Wait for final success message
        WebElement finalResult = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("finish_green_and_blue")));

        // Check button and loading messages are hidden
        assertFalse(greenBlueButton.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());
        assertFalse(loadingBlue.isDisplayed());
        assertFalse(greenFinished.isDisplayed());

        // Verify success
        assertTrue(finalResult.isDisplayed());
        assertEquals("Green and Blue Loaded", finalResult.getText());
    }
}
