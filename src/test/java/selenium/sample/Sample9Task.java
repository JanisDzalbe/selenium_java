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
    private WebDriverWait wait;

    @BeforeEach
    public void openPage() {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();
        wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(10))
                .ignoring(StaleElementReferenceException.class);

        // load web page
        driver.get("https://janisdzalbe.github.io/example-site/examples/loading_color");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void loadGreenSleep() throws Exception {
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(By.id("start_green")).click();

        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());

        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
        driver.findElement(By.id("start_green")).click();

        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_green")));

        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
    }
    @Test
    public void loadGreenAndBlueBonus() {

        //waits until button appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("start_green_and_blue")
        ));

        //clicks button
        driver.findElement(By.id("start_green_and_blue")).click();

        //green starts loading
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("loading_green_without_blue")
        ));

        assertTrue(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());

        //green + blue loading
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("loading_green_with_blue")
        ));

        assertTrue(driver.findElement(By.id("loading_green_with_blue")).isDisplayed());

        //blue continues loading, green finished
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("loading_blue_without_green")
        ));

        assertTrue(driver.findElement(By.id("loading_blue_without_green")).isDisplayed());

        //final success
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("finish_green_and_blue")
        ));
        assertTrue(driver.findElement(By.id("finish_green_and_blue")).isDisplayed());
    }

}