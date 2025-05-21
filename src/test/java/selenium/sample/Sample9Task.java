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
        WebElement startGreen = driver.findElement(By.id("start_green"));
        startGreen.click();

//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
        assertFalse(startGreen.isDisplayed());

        WebElement loadingText = driver.findElement(By.xpath("//*[@id='loading_green']"));
        assertTrue(loadingText.isDisplayed());
        assertEquals("Loading green...", loadingText.getText());

//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        Thread.sleep(5000);
        assertFalse(startGreen.isDisplayed());
        assertFalse(loadingText.isDisplayed());

        WebElement finishGreen = driver.findElement(By.id("finish_green"));
        assertTrue(finishGreen.isDisplayed());
        assertEquals("Green Loaded", finishGreen.getText());
    }

    @Test
    public void loadGreenImplicit() throws Exception {
//         TODO:
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//         * 1) click on start loading green button
        WebElement startGreen = driver.findElement(By.id("start_green"));
        startGreen.click();

//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
        assertFalse(startGreen.isDisplayed());

        WebElement loadingText = driver.findElement(By.xpath("//*[@id='loading_green']"));
        assertTrue(loadingText.isDisplayed());
        assertEquals("Loading green...", loadingText.getText());

//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        WebElement finishGreen = driver.findElement(By.id("finish_green"));
        assertTrue(finishGreen.isDisplayed());

        assertFalse(startGreen.isDisplayed());
        assertFalse(loadingText.isDisplayed());

        assertEquals("Green Loaded", finishGreen.getText());
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
//         TODO:
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(10)).ignoring(StaleElementReferenceException.class);

        //* 1) click on start loading green button
        WebElement startGreen = driver.findElement(By.id("start_green"));
        startGreen.click();

//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
        assertFalse(startGreen.isDisplayed());

        WebElement loadingText = driver.findElement(By.xpath("//*[@id='loading_green']"));
        assertTrue(loadingText.isDisplayed());
        assertEquals("Loading green...", loadingText.getText());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green")));

//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        assertFalse(startGreen.isDisplayed());
        assertFalse(loadingText.isDisplayed());

        WebElement finishGreen = driver.findElement(By.id("finish_green"));
        assertTrue(finishGreen.isDisplayed());
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
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(10)).ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("start_green_and_blue")));

        WebElement greenBlueButton = driver.findElement(By.id("start_green_and_blue"));

        greenBlueButton.click();

        assertFalse(greenBlueButton.isDisplayed());

        WebElement loadingGreen = driver.findElement(By.id("loading_green_without_blue"));
        WebElement loadingBlue = driver.findElement(By.id("loading_green_without_blue"));

        assertTrue(loadingGreen.isDisplayed());

        wait.until(ExpectedConditions.visibilityOf(loadingBlue));

        assertFalse(greenBlueButton.isDisplayed());
        assertTrue(loadingGreen.isDisplayed());
        assertTrue(loadingBlue.isDisplayed());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loading_blue_without_green")));

        WebElement loadingBlueWithoutGreen = driver.findElement(By.id("loading_blue_without_green"));

        assertFalse(greenBlueButton.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());
        assertFalse(loadingBlue.isDisplayed());
        assertTrue(loadingBlueWithoutGreen.isDisplayed());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green_and_blue")));

        WebElement finish = driver.findElement(By.id("finish_green_and_blue"));

        assertFalse(greenBlueButton.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());
        assertFalse(loadingBlue.isDisplayed());
        assertTrue(finish.isDisplayed());

    }

}