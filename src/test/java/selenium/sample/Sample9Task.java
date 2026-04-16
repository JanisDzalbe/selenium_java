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

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Sample9Task {

    WebDriver driver;

    @BeforeEach
    public void openPage() {
        driver = BootcampUtils.initializeEdgeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/examples/loading_color");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void loadGreenSleep() throws Exception {

        driver.findElement(By.id("start_green")).click();

        Thread.sleep(1500);

        WebElement container = driver.findElement(By.id("green_loader"));
        assertTrue(container.getText().contains("Loading green"));

        Thread.sleep(3000);

        container = driver.findElement(By.id("green_loader"));
        assertTrue(container.getText().contains("Green Loaded"));
    }

    @Test
    public void loadGreenImplicit() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.id("start_green")).click();

        // loading appears
        driver.findElement(By.id("loading_green"));

        // success appears
        driver.findElement(By.id("finish_green"));
    }

    @Test
    public void loadGreenExplicitWait() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("start_green")).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("start_green")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green")));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading_green")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_green")));
    }

    @Test
    public void loadGreenAndBlueBonus() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        By button = By.id("start_green_and_blue");

        wait.until(ExpectedConditions.elementToBeClickable(button));
        driver.findElement(button).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(button));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_without_blue")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_with_blue")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_blue_without_green")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_green_and_blue")));
    }
}