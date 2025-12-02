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
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


public class Sample9Task {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeEach
    public void openPage() {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(12));
        // load web page
        driver.get("https://janisdzalbe.github.io/example-site/examples/loading_color");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void loadGreenSleep() throws Exception {
        WebElement startButton   = driver.findElement(By.id("start_green"));
        assertTrue(startButton.isDisplayed());
        //* 1) click on start loading green button
        startButton.click();
        Thread.sleep(500);
        //* 2) check that button does not appear,
        WebElement loadingGreen = driver.findElement(By.id("loading_green"));
        assertFalse(startButton.isDisplayed());
        assertTrue(loadingGreen.isDisplayed());


        Thread.sleep(6000);
        WebElement finishGreen  = driver.findElement(By.id("finish_green"));
        assertFalse(startButton.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());
        assertTrue(finishGreen.isDisplayed());
        assertEquals("Green Loaded", finishGreen.getText().trim());

    }

    @Test
    public void loadGreenImplicit() throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement startButton = driver.findElement(By.id("start_green"));
        assertTrue(startButton.isDisplayed());
        startButton.click();
        assertFalse(startButton.isDisplayed());
        WebElement loadingGreen = driver.findElement(By.id("loading_green"));
        assertTrue(loadingGreen.isDisplayed());
        WebElement finishGreen = driver.findElement(By.id("finish_green"));
        assertTrue(finishGreen.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());
        assertFalse(startButton.isDisplayed());

        //* 1) click on start loading green button
        //* 2) check that button does not appear,
        //* but loading text is seen instead   "Loading green..."
        //* 3) check that both button
        //* and loading text is not seen,
        //* success is seen instead "Green Loaded"
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement startButton = driver.findElement(By.id("start_green"));

        // click on start loading green button
        assertTrue(startButton.isDisplayed());
        startButton.click();
        WebElement loadingGreen = driver.findElement(By.id("loading_green"));
        wait.until(ExpectedConditions.invisibilityOf(startButton));
        wait.until(ExpectedConditions.visibilityOf(loadingGreen));
        assertFalse(startButton.isDisplayed());
        assertTrue(loadingGreen.isDisplayed());
        //assertFalse(finishGreen.isDisplayed());

        wait.until(ExpectedConditions.invisibilityOf(loadingGreen));
        WebElement finishGreen = driver.findElement(By.id("finish_green"));
        wait.until(ExpectedConditions.visibilityOf(finishGreen));
        assertFalse(startButton.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());
        assertTrue(finishGreen.isDisplayed());
        assertEquals("Green Loaded", finishGreen.getText().trim());

        //* 1) click on start loading green button
        //* 2) check that button does not appear,
        //* but loading text is seen instead   "Loading green..."
        //* 3) check that both button
        //* and loading text is not seen,
        //* success is seen instead "Green Loaded"
    }

    @Test
    public void loadGreenAndBlueBonus() {
        WebElement startBtn = driver.findElement(By.id("start_green_and_blue"));
        //* 0) wait until button to load green and blue appears
        wait.until(ExpectedConditions.visibilityOf(startBtn));
        assertTrue(startBtn.isDisplayed());
        //* 1) click on start loading green and blue button
        startBtn.click();
        //* 2) check that button does not appear, but loading text is seen instead for green
        wait.until(ExpectedConditions.invisibilityOf(startBtn));
        WebElement loadingGreen = driver.findElement(By.id("loading_green_with_blue"));
        WebElement loadingBlue = driver.findElement(By.id("loading_blue_without_green"));
        WebElement finishGreen = driver.findElement(By.id("finish_green"));
        WebElement finishBlue = driver.findElement(By.id("finish_green_and_blue"));
        assertTrue(loadingGreen.isDisplayed());
        assertFalse(loadingBlue.isDisplayed());
        assertFalse(finishGreen.isDisplayed());
        assertFalse(finishBlue.isDisplayed());
        //* 3) check that button does not appear, but loading text is seen instead for green and blue
        wait.until(ExpectedConditions.visibilityOf(loadingBlue));
        assertTrue(loadingGreen.isDisplayed());
        assertTrue(loadingBlue.isDisplayed());
        assertFalse(finishGreen.isDisplayed());
        assertFalse(finishBlue.isDisplayed());
        //* 4) check that button and loading green does not appear,
        //* but loading text is seen instead for blue and success for green is seen
        wait.until(ExpectedConditions.visibilityOf(finishGreen));
        assertTrue(finishGreen.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());
        assertTrue(loadingBlue.isDisplayed());
        assertFalse(finishBlue.isDisplayed());
        //* 5) check that both button and loading text is not seen, success is seen instead
        wait.until(ExpectedConditions.visibilityOf(finishBlue));
        assertTrue(finishBlue.isDisplayed());
        assertFalse(loadingBlue.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());
    }

}