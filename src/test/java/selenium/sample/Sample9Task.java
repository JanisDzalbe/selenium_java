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

import static org.junit.jupiter.api.Assertions.*;

public class Sample9Task {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void openPage() {
        driver = BootcampUtils.initializeChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://janisdzalbe.github.io/example-site/examples/loading_color");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void loadGreenSleep() throws Exception {
        driver.findElement(By.id("start_green")).click();
        Thread.sleep(5000);
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
    }

    @Test
    public void loadGreenImplicit() throws Exception {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("start_green")).click();
        WebElement successMessage = driver.findElement(By.id("finish_green"));
        assertTrue(successMessage.isDisplayed());
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
        WebElement startBtn = driver.findElement(By.id("start_green"));
        startBtn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_green")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("start_green")));
    }

    @Test
    public void loadGreenAndBlueBonus() {
        WebElement startBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("start_green_and_blue")));
        startBtn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_without_blue")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_with_blue")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_blue_without_green")));
        WebElement finalResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_green_and_blue")));

        assertTrue(finalResult.getText().contains("Loaded"));
    }
}