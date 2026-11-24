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
        /*
        // 1) find the button by its exact text and click it
        driver.findElement(By.xpath("//*[text()='Start loading green']")).click();

        // 2) the button is gone, loading text is shown
        assertEquals(0, driver.findElements(By.xpath("//*[text()='Start loading green']")).size());
        assertEquals("Loading green...", driver.findElement(By.xpath("//*[text()='Loading green...']")).getText());

        // wait a fixed time until loading is finished (we need >=5 sec)
        Thread.sleep(6000);

        // 3) button and loading text are gone, success text is shown
        assertEquals(0, driver.findElements(By.xpath("//*[text()='Start loading green']")).size());
        assertEquals(0, driver.findElements(By.xpath("//*[text()='Loading green...']")).size());
        assertEquals("Green Loaded", driver.findElement(By.xpath("//*[text()='Green Loaded']")).getText());
        */

        driver.findElement(By.id("start_green")).click();
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());

        Thread.sleep(5000);
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
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
        /*
        // implicit wait - wait up to 6 seconds until the element appears
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

        // 1) click the button
        driver.findElement(By.xpath("//*[text()='Start loading green']")).click();

        // 2) loading text is shown
        assertEquals("Loading green...", driver.findElement(By.xpath("//*[text()='Loading green...']")).getText());

        // 3) findElement waits here until "Green Loaded" appears (up to 10 seconds)
        assertEquals("Green Loaded", driver.findElement(By.xpath("//*[text()='Green Loaded']")).getText());

        // turn the implicit wait off, otherwise every "0 elements" check below would wait 10 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        assertEquals(0, driver.findElements(By.xpath("//*[text()='Start loading green']")).size());
        assertEquals(0, driver.findElements(By.xpath("//*[text()='Loading green...']")).size());
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
        /*
        // explicit wait - we wait up to 10 seconds for a concrete condition
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));

        // 1) click the button
        driver.findElement(By.xpath("//*[text()='Start loading green']")).click();

        // 2) wait until loading text is visible and the button is not visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Loading green...']")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[text()='Start loading green']")));
        assertEquals("Loading green...", driver.findElement(By.xpath("//*[text()='Loading green...']")).getText());

        // 3) wait until success text is visible and loading text is gone
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Green Loaded']")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[text()='Loading green...']")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[text()='Start loading green']")));
        assertEquals("Green Loaded", driver.findElement(By.xpath("//*[text()='Green Loaded']")).getText());
        */
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.findElement(By.id("start_green")).click();
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green")));
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
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

        // wait up to 20 seconds for each condition
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // 0) wait until the button is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("start_green_and_blue")));

        // 1) click the button
        driver.findElement(By.id("start_green_and_blue")).click();

        // 2) button is gone
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_without_blue")));

        // 3) button is gone, green and blue are loading together
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_with_blue")));
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());

        // 4) green loading is gone, green success is seen, blue is still loading
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_with_blue")));
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green_with_blue")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green_with_blue")).isDisplayed());

        // 5) all loading texts and the button are gone, success is seen
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_green_and_blue")));
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green_with_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_blue_without_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("finish_green_and_blue")).isDisplayed());

    }
}