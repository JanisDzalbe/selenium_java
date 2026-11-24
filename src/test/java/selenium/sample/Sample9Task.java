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
            driver.findElement(By.id("start_green")).click();
//          * 2) check that button does not appear,
            assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());
            assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
//          * but loading text is seen instead   "Loading green..."

            Thread.sleep(10000);
//          * 3) check that both button
            assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
            assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
            assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
//          * and loading text is not seen,
//          * success is seen instead "Green Loaded"
    }

    @Test
    public void loadGreenImplicit() throws Exception {
//         TODO:
        WebElement startGreen = driver.findElement(By.id("start_green"));



//          * 1) click on start loading green button
        startGreen.click();
//          * 2) check that button does not appear,
        assertFalse(startGreen.isDisplayed());
//          * but loading text is seen instead   "Loading green..."
        WebElement loadingGreen = driver.findElement(By.id("loading_green"));
        assertTrue(loadingGreen.isDisplayed());
        assertEquals("Loading green...", loadingGreen.getText());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//          * 3) check that both button
        WebElement finishGreen = driver.findElement(By.id("finish_green"));
        assertTrue(finishGreen.isDisplayed());
        assertFalse(startGreen.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
//         TODO:
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement startGreen = driver.findElement(By.id("start_green"));
//          * 1) click on start loading green button
            startGreen.click();
//          * 2) check that button does not appear,
        assertFalse(startGreen.isDisplayed());
//          * but loading text is seen instead   "Loading green..."
        WebElement loadingGreen = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green")));
        assertTrue(loadingGreen.isDisplayed());
        assertEquals("Loading green...", loadingGreen.getText());
//          * 3) check that both button
        //          * and loading text is not seen,
//          * success is seen instead "Green Loaded"
        WebElement finishGreen = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_green")));
        assertTrue(finishGreen.isDisplayed());
        assertFalse(startGreen.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());

    }

    @Test
    public void loadGreenAndBlueBonus() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//         TODO:
//          * 0) wait until button to load green and blue appears
            WebElement startBlueGreen = wait.until(ExpectedConditions.elementToBeClickable(By.id("start_green_and_blue")));
//          * 1) click on start loading green and blue button
            startBlueGreen.click();
//          * 2) check that button does not appear, but loading text is seen instead for green
             assertFalse(startBlueGreen.isDisplayed());
             WebElement loadingGreenOnly = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_without_blue")));
             assertTrue(loadingGreenOnly.isDisplayed());
//          * 3) check that button does not appear, but loading text is seen instead for green and blue
            WebElement loadingGreenAndBlue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_with_blue")));
            assertFalse(startBlueGreen.isDisplayed());
            assertTrue(loadingGreenAndBlue.isDisplayed());
//          * 4) check that button and loading green does not appear,
//          * but loading text is seen instead for blue and success for green is seen
            WebElement loadingBlueOnly = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_blue_without_green")));
            assertFalse(startBlueGreen.isDisplayed());
            assertFalse(loadingGreenOnly.isDisplayed());
            assertTrue(wait.until(ExpectedConditions.invisibilityOf(loadingGreenAndBlue)));
            assertTrue(loadingBlueOnly.isDisplayed());
//          * 5) check that both button and loading text is not seen, success is seen instead
            WebElement finishGreenAndBlue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_green_and_blue")));
            assertFalse(startBlueGreen.isDisplayed());
            assertFalse(loadingBlueOnly.isDisplayed());
            assertTrue(finishGreenAndBlue.isDisplayed());
    }

}