package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.utility.BootcampUtils;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Sample9Task {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void openPage() {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        // create explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.ignoring(StaleElementReferenceException.class);

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

        // wait until start button appears
        Thread.sleep(3000);

        // click on start loading green button
        driver.findElement(By.id("start_green")).click();

        // short wait so loading block appears
        Thread.sleep(1000);

        // check that button is not seen
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());

        // check that loading text is seen
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).getText().contains("Loading green..."));

        // wait until green loading finishes
        Thread.sleep(5000);

        // check that button is not seen
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());

        // check that loading text is not seen
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());

        // check that success is seen
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("finish_green")).getText().contains("Green Loaded"));
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

        // use implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // click on start loading green button after it appears
        driver.findElement(By.id("start_green")).click();

        // loading text should appear
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).getText().contains("Loading green..."));

        // wait a bit for finish state
        Thread.sleep(5000);

        // check final state
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("finish_green")).getText().contains("Green Loaded"));
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

        // wait until start button becomes visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("start_green")));

        // click on start loading green button
        driver.findElement(By.id("start_green")).click();

        // check that button is hidden
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("start_green")));

        // check that loading text is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green")));
        assertTrue(driver.findElement(By.id("loading_green")).getText().contains("Loading green..."));

        // wait until loading disappears
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading_green")));

        // check that success is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_green")));
        assertTrue(driver.findElement(By.id("finish_green")).getText().contains("Green Loaded"));
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

        // 0) wait until button appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("start_green_and_blue")));

        // 1) click on start loading green and blue button
        driver.findElement(By.id("start_green_and_blue")).click();

        // 2) button hidden, green loading is visible
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("start_green_and_blue")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_without_blue")));
        assertTrue(driver.findElement(By.id("loading_green_without_blue")).getText().contains("Loading green..."));

        // 3) green and blue loading is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_with_blue")));
        assertTrue(driver.findElement(By.id("loading_green_with_blue")).getText().contains("Loading blue..."));

        // 4) green-only loading hidden, blue waiting visible, green success visible
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading_green_without_blue")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_blue_without_green")));
        assertTrue(driver.findElement(By.id("loading_blue_without_green")).getText().contains("Green finished waiting for blue"));
        assertTrue(driver.findElement(By.id("loading_blue_without_green")).isDisplayed());

        // 5) final success visible
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading_green_with_blue")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading_blue_without_green")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_green_and_blue")));
        assertTrue(driver.findElement(By.id("finish_green_and_blue")).getText().contains("Green"));
        assertTrue(driver.findElement(By.id("finish_green_and_blue")).getText().contains("Blue"));
        assertTrue(driver.findElement(By.id("finish_green_and_blue")).getText().contains("Loaded"));
    }
}