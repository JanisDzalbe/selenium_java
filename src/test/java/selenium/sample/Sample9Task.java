package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
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

        WebElement greenButton = driver.findElement(By.id("start_green"));
        greenButton.click();
        assertFalse(greenButton.isDisplayed());

        WebElement loadingText = driver.findElement(By.id("loading_green"));
        assertTrue(loadingText.isDisplayed());
        assertEquals("Loading green...", loadingText.getText());

        Thread.sleep(10000);
        assertFalse(loadingText.isDisplayed());

        WebElement finishText = driver.findElement(By.id("finish_green"));
        assertTrue(finishText.isDisplayed());
        assertEquals("Green Loaded", finishText.getText());

//         TODO:
//          * 1) click on start loading green button
//          * 2) check that button does not appear,
//          * but loading text is seen instead   "Loading green..."
//          * 3) check that both button
//          * and loading text is not seen,
//          * success is seen instead "Green Loaded"
    }

    @Test
    public void loadGreenImplicit() throws Exception {

        WebElement greenButton = driver.findElement(By.id("start_green"));
        greenButton.click();
        assertFalse(greenButton.isDisplayed());

        WebElement loadingText = driver.findElement(By.id("loading_green"));
        assertTrue(loadingText.isDisplayed());
        assertTrue(loadingText.getText().contains("Loading green..."));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement finishText = driver.findElement(By.id("finish_green"));
        assertFalse(loadingText.isDisplayed());
        assertTrue(finishText.isDisplayed());
        assertEquals("Green Loaded", finishText.getText());

//         TODO:
//          * 1) click on start loading green button
//          * 2) check that button does not appear,
//          * but loading text is seen instead   "Loading green..."
//          * 3) check that both button
//          * and loading text is not seen,
//          * success is seen instead "Green Loaded"
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {

        WebElement greenButton = driver.findElement(By.id("start_green"));
        greenButton.click();
        assertFalse(greenButton.isDisplayed());

        WebElement loadingText = driver.findElement(By.id("loading_green"));
        assertTrue(loadingText.isDisplayed());
        assertTrue(loadingText.getText().contains("Loading green..."));

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> d.findElement(By.id("finish_green")).isDisplayed());
        assertFalse(loadingText.isDisplayed());

        WebElement finishText = driver.findElement(By.id("finish_green"));
        assertTrue(finishText.isDisplayed());
        assertEquals("Green Loaded", finishText.getText());

//         TODO:
//          * 1) click on start loading green button
//          * 2) check that button does not appear,
//          * but loading text is seen instead   "Loading green..."
//          * 3) check that both button
//          * and loading text is not seen,
//          * success is seen instead "Green Loaded"
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