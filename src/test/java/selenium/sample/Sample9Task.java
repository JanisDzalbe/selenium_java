package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        WebElement loadingGreenButton = driver.findElement(By.id("start_green"));

        loadingGreenButton.click();
        assertFalse(loadingGreenButton.isDisplayed());
        WebElement loadingGreenText = driver.findElement(By.id("loading_green"));
        assertTrue(loadingGreenText.isDisplayed());
        assertEquals("Loading green...", loadingGreenText.getText());


        Thread.sleep(6000);
        WebElement finishGreenText = driver.findElement(By.id("finish_green"));
        assertFalse(loadingGreenButton.isDisplayed());
        assertFalse(loadingGreenText.isDisplayed());
        assertTrue(finishGreenText.isDisplayed());
    }

    @Test
    public void loadGreenImplicit() throws Exception {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

        driver.findElement(By.id("start_green")).click();

        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());

        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
        WebElement loadingGreenButton = driver.findElement(By.id("start_green"));

        loadingGreenButton.click();
        assertFalse(loadingGreenButton.isDisplayed());

        WebElement loadingGreenText = driver.findElement(By.id("loading_green"));
        assertTrue(loadingGreenText.isDisplayed());
        assertEquals("Loading green...", loadingGreenText.getText());

        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(6)).ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green")));

        WebElement finishGreenText = driver.findElement(By.id("finish_green"));
        assertFalse(loadingGreenButton.isDisplayed());
        assertFalse(loadingGreenText.isDisplayed());
        assertTrue(finishGreenText.isDisplayed());
    }

    @Test
    public void loadGreenAndBlueBonus() throws Exception {
        Thread.sleep(4000);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

        assertTrue(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        driver.findElement(By.id("start_green_and_blue")).click();

        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());

        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green_with_blue")).isDisplayed());

        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertTrue(driver.findElement(By.id("finish_green_and_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_blue_without_green")).isDisplayed());

        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green_with_blue")).isDisplayed());
        assertTrue(driver.findElement(By.id("finish_green_and_blue")).isDisplayed());
    }
}