package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.utility.BootcampUtils;
import org.openqa.selenium.WebElement;

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

        // driver — controls the browser.
        // findElement() — searches for a single element.
        // By.id("start_green") — searches for an element by its id attribute.
        // click() — clicks on the found button.
        // Click on Start Loading Green button.
        driver.findElement(By.id("start_green")).click();

        // Wait for loading to start.
        Thread.sleep(1000);

        // Check that the button is not displayed.
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());

        // Check that loading text is displayed.
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());

        // Wait for loading to finish.
        Thread.sleep(6000);

        // Check that the button is not displayed.
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());

        // Check that loading text is not displayed.
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());

        // Check that success message is displayed.
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());

        // Check success message text.
        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText());

        /* TEACHERS WAY
        driver.findElement(By.id("start_green")).click();
    assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
    assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());

    Thread.sleep(5000);

    assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
    assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
    assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
}
         */

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

        // Set implicit wait to 10 seconds.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Click on Start Loading Green button.
        driver.findElement(By.id("start_green")).click();

        // Check that the button is not displayed.
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());

        // Check that loading text is displayed.
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());

        // Check that loading text is correct.
        assertEquals("Loading green...", driver.findElement(By.id("loading_green")).getText());

        // Check that the button is not displayed.
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());

        // Check that loading text is not displayed.
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());

        // Check that success message is displayed.
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());

        // Check success message text.
        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText());

        /* TEACHERS WAY
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    driver.findElement(By.id("start_green")).click();
    assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
    assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());

    assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
    assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
    assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
}
         */
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
        // Create explicit wait with a maximum timeout of 10 seconds.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click on Start Loading Green button.
        driver.findElement(By.id("start_green")).click();

        // Wait until the button is not visible.
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("start_green")));

        // Wait until loading text is visible.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green")));

        // Check loading text.
        assertEquals("Loading green...", driver.findElement(By.id("loading_green")).getText());

        // Wait until loading text is not visible.
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading_green")));

        // Wait until success message is visible.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_green")));

        // Check success message.
        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText());
    }


    @Test
    public void loadGreenAndBlueBonus() {
        // https://janisdzalbe.github.io/example-site/examples/loading_color
//         TODO:
//          * 0) wait until button to load green and blue appears
//          * 1) click on start loading green and blue button
//          * 2) check that button does not appear, but loading text is seen instead for green
//          * 3) check that button does not appear, but loading text is seen instead for green and blue
//          * 4) check that button and loading green does not appear,
//          * but loading text is seen instead for blue and success for green is seen
//          * 5) check that both button and loading text is not seen, success is seen instead

            // Create explicit wait.
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // 0. Wait until Start Green and Blue button appears.
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("start_green_and_blue")));

            // 1. Click Start Green and Blue button.
            driver.findElement(By.id("start_green_and_blue")).click();

            // 2. Wait until Green loading text appears.
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_without_blue")));

            // Check that button is not visible.
            assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());

            // Check that Green loading text is visible.
            assertTrue(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());

            // 3. Wait until Green and Blue loading text appears.
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_with_blue")));

            // Check that button is not visible.
            assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());

            // Check that Green and Blue loading text is visible.
            assertTrue(driver.findElement(By.id("loading_green_with_blue")).isDisplayed());

            // 4. Wait until Blue loading appears without Green.
             wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("loading_blue_without_green")));

            // Wait until Green and Blue loading text disappears.
             wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.id("loading_green_with_blue")));

            // Check that button is not visible.
             assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());

            // 5. Wait until Blue loading finishes.
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading_blue_without_green")));

            // Wait until Green and Blue success message appears.
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_green_and_blue")));

            // Check that button is not visible.
            assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());

            // Check that Green and Blue loading text is not visible.
            assertFalse(driver.findElement(By.id("loading_green_with_blue")).isDisplayed());

            // Check that Blue loading text is not visible.
            assertFalse(driver.findElement(By.id("loading_blue_without_green")).isDisplayed());

            // Check that success message is visible.
            assertTrue(driver.findElement(By.id("finish_green_and_blue")).isDisplayed());
        }
    }
