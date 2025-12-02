package selenium.sample;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import selenium.utility.BootcampUtils;

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

        driver.findElement(By.id("start_green")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green")));

        WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_green")));

        String successText = success.getText().replace("\n", " ").trim();
        Assertions.assertTrue(successText.contains("Green") && successText.contains("Loaded"), "Expected success text to contain 'Green Loaded' but was: " + successText);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading_green")));
    }



    @Test
    public void loadGreenImplicit() throws Exception {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(By.id("start_green")).click();

        Assertions.assertFalse(driver.findElement(By.id("start_green")).isDisplayed());

        WebElement loading = driver.findElement(By.id("loading_green"));
        Assertions.assertTrue(loading.isDisplayed());

        Thread.sleep(3000);

        WebElement success = driver.findElement(By.id("finish_green"));
        Assertions.assertTrue(success.isDisplayed());

        Assertions.assertFalse(loading.isDisplayed(), "Loading should no longer be visible.");
    }



    @Test
    public void loadGreenExplicitWait() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement startButton = driver.findElement(By.id("start_green"));
        startButton.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("start_green")));

        WebElement loading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green")));

        String loadingText = loading.getText().replace("\n", " ").trim();
        Assertions.assertTrue(loadingText.contains("Loading") && loadingText.contains("green"), "Expected loading text 'Loading green...', but got: " + loadingText);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading_green")));

        WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_green")));

        String successText = success.getText().replace("\n", " ").trim();
        Assertions.assertTrue(successText.contains("Green") && successText.contains("Loaded"), "Expected success text 'Green Loaded', but got: " + successText);
    }


    @Test
    public void loadGreenAndBlueBonus() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement startButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("start_green_and_blue")));

        startButton.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("start_green_and_blue")));

        WebElement loadingGreen = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_without_blue")));
        Assertions.assertTrue(loadingGreen.getText().contains("Loading"), "Expected loading green message");

        WebElement loadingGreenBlue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_with_blue")));
        Assertions.assertTrue(loadingGreenBlue.getText().contains("Loading"), "Expected green+blue loading message");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading_green_without_blue")));

        WebElement loadingBlueOnly = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_blue_without_green")));
        Assertions.assertTrue(loadingBlueOnly.getText().contains("finished waiting for"), "Expected blue still loading message");


        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading_blue_without_green")));

        WebElement successBoth = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_green_and_blue")));
        Assertions.assertTrue(successBoth.getText().contains("Green") && successBoth.getText().contains("Blue"),
                "Expected final success: Green and Blue loaded");
    }


}