package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        driver.findElement(By.id("start_green")).click();

        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertEquals("Loading green...",
                driver.findElement(By.id("loading_green")).getText());

        Thread.sleep(5000);

        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertEquals("Green Loaded",
                driver.findElement(By.id("finish_green")).getText());


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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));

        driver.findElement(By.id("start_green")).click();
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertEquals("Loading green...",
                driver.findElement(By.id("loading_green")).getText());

        driver.findElement(By.id("finish_green"));

        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertEquals("Green Loaded",
                driver.findElement(By.id("finish_green")).getText());

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("start_green")).click();

        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertEquals("Loading green...",
                driver.findElement(By.id("loading_green")).getText());

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_green")));

        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertEquals("Green Loaded",
                driver.findElement(By.id("finish_green")).getText());
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