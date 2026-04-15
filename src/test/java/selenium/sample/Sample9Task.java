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
//
//  1) click button
        WebElement button = driver.findElement(By.id("start_green"));
        button.click();

//  wait for loading to appear
        Thread.sleep(500);

//  2) button should NOT be visible
        assertFalse(button.isDisplayed());

//  check loading text
        WebElement loading = driver.findElement(By.id("loading_green"));
        assertTrue(loading.isDisplayed());
        assertTrue(loading.getText().contains("Loading"));
        assertTrue(loading.getText().contains("green"));

//  wait for final state
        Thread.sleep(4000);

//  3) loading disappears, success appears
        WebElement result = driver.findElement(By.id("finish_green"));
        assertTrue(result.isDisplayed());
        assertEquals("Green Loaded", result.getText());
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
//
//  set implicit wait
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));

//  1) click button
        WebElement button = driver.findElement(By.id("start_green"));
        button.click();

//  2) button should NOT be visible
        assertFalse(button.isDisplayed());

//  loading should be visible
        WebElement loading = driver.findElement(By.id("loading_green"));
        assertTrue(loading.isDisplayed());
        assertTrue(loading.getText().contains("Loading"));
        assertTrue(loading.getText().contains("green"));

//  3) wait for success (implicit wait will handle appearance)
        WebElement result = driver.findElement(By.id("finish_green"));

        assertTrue(result.isDisplayed());
        assertEquals("Green Loaded", result.getText());
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

        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));

        // 1) click button
        WebElement button = driver.findElement(By.id("start_green"));
        button.click();

        // 2) wait until loading appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green")));

        // button should NOT be visible
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());

        // loading should be visible
        WebElement loading = driver.findElement(By.id("loading_green"));
        assertTrue(loading.getText().contains("Loading"));
        assertTrue(loading.getText().contains("green"));

        // 3) wait until loading disappears
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading_green")));

        // wait until success appears
        WebElement result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("finish_green"))
        );

        assertEquals("Green Loaded", result.getText());
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