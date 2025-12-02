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
//          * 1) click on start loading green button
        WebElement greenButton = driver.findElement(By.id("start_green"));
        greenButton.click();
//          * 2) check that button does not appear,
//          * but loading text is seen instead   "Loading green..."
        assertFalse(greenButton.isDisplayed());
        WebElement loadingGreenText = driver.findElement(By.id("loading_green"));
        assertEquals("Loading green...", loadingGreenText.getText());
//          * 3) check that both button
//          * and loading text is not seen,
//          * success is seen instead "Green Loaded"
        Thread.sleep(7000);
        assertFalse(greenButton.isDisplayed());
        assertFalse(loadingGreenText.isDisplayed());
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
    }

    @Test
    public void loadGreenImplicit() throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//          * 1) click on start loading green button
        driver.findElement(By.id("start_green")).click();
//          * 2) check that button does not appear,
//          * but loading text is seen instead   "Loading green..."
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());
//          * 3) check that both button
//          * and loading text is not seen,
//          * success is seen instead "Green Loaded"
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//          * 1) click on start loading green button
        driver.findElement(By.id("start_green")).click();
//          * 2) check that button does not appear,
//          * but loading text is seen instead   "Loading green..."
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());
//          * 3) check that both button
//          * and loading text is not seen,
//          * success is seen instead "Green Loaded"
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green")));
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
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
    }

}