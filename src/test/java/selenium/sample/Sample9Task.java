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
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Sample9Task {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeEach
    public void openPage() {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        // load web page
        driver.get("https://janisdzalbe.github.io/example-site/examples/loading_color");
        wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // initialize wait
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

        assertEquals("Loading green...", driver.findElement(By.id("loading_green")).getText()); //check that button doesnt appear

        Thread.sleep(5000); //waiting for it to load

        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText()); //check success text
        assertEquals(false, driver.findElement(By.id("start_green")).isDisplayed());    //check that start green not visible
        assertEquals(false, driver.findElement(By.id("loading_green")).isDisplayed());  //check that loading msg not visible
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
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.findElement(By.id("start_green")).click();

        assertEquals("Loading green...", driver.findElement(By.id("loading_green")).getText());

        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText());

        assertEquals(false, driver.findElement(By.id("start_green")).isDisplayed());    //check that nothing but success seen
        assertEquals(false, driver.findElement(By.id("loading_green")).isDisplayed());
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
        driver.findElement(By.id("start_green")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green")));
        assertEquals("Loading green...", driver.findElement(By.id("loading_green")).getText());

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_green")));   //waiting for success text
        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText());

        assertEquals(false, driver.findElement(By.id("start_green")).isDisplayed());    //check that nothing but success seen
        assertEquals(false, driver.findElement(By.id("loading_green")).isDisplayed());
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("start_green_and_blue")));

        driver.findElement(By.id("start_green_and_blue")).click();

    //check that loading text for green appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_without_blue")));
        assertEquals("Loading green...", driver.findElement(By.id("loading_green_without_blue")).getText());

    // check that loading text for blue appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_with_blue")));
        assertEquals("Loading blue...", driver.findElement(By.id("loading_green_with_blue")).getText());

    // check that green finished and blue still loading
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_blue_without_green")));
        assertEquals("Green finished waiting for blue", driver.findElement(By.id("loading_blue_without_green")).getText());
        assertEquals("Loading blue...", driver.findElement(By.id("loading_green_with_blue")).getText());

    // check final blue and green success
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_green_and_blue")));
        assertEquals("Green and Blue Loaded", driver.findElement(By.id("finish_green_and_blue")).getText());

    // check button and loading gone
        assertEquals(false, driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertEquals(false, driver.findElement(By.id("loading_green_with_blue")).isDisplayed());
        assertEquals(false, driver.findElement(By.id("loading_green_without_blue")).isDisplayed());
    }

}