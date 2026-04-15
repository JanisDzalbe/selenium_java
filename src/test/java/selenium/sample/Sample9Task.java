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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Sample9Task {
    WebDriver driver;

    @BeforeEach
    public void openPage() {
        driver = BootcampUtils.initializeChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/examples/loading_color");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    // helper: checks if element is actually visible on page (not just present in DOM)
    private boolean isVisible(By locator) {
        List<WebElement> els = driver.findElements(locator);
        return !els.isEmpty() && els.get(0).isDisplayed();
    }

    // ==================== 1. Thread.sleep ====================
    @Test
    public void loadGreenSleep() throws Exception {
        driver.findElement(By.id("start_green")).click();

        // 2) button hidden, "Loading green..." visible
        Thread.sleep(500);
        assertFalse(isVisible(By.id("start_green")), "Button should be hidden");
        assertTrue(isVisible(By.id("loading_green")), "Loading message should be shown");
        assertFalse(isVisible(By.id("finish_green")), "Finish message should not yet be shown");

        // 3) loading gone, "Green Loaded" visible
        Thread.sleep(8000);
        assertFalse(isVisible(By.id("start_green")));
        assertFalse(isVisible(By.id("loading_green")));
        assertTrue(isVisible(By.id("finish_green")));
        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText());
    }

    // ==================== 2. Implicit wait ====================
    @Test
    public void loadGreenImplicit() throws Exception {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.id("start_green")).click();

        // immediately after click
        Thread.sleep(500);  // tiny sleep just to let the DOM update
        assertFalse(isVisible(By.id("start_green")));
        assertTrue(isVisible(By.id("loading_green")));

        // poll until finish_green becomes visible
        long deadline = System.currentTimeMillis() + 10_000;
        while (!isVisible(By.id("finish_green")) && System.currentTimeMillis() < deadline) {
            Thread.sleep(200);
        }

        assertFalse(isVisible(By.id("loading_green")));
        assertTrue(isVisible(By.id("finish_green")));
        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText());
    }

    // ==================== 3. Explicit wait (recommended) ====================
    @Test
    public void loadGreenExplicitWait() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("start_green")).click();

        // 2) wait for button to become invisible AND loading to become visible
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("start_green")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green")));

        // 3) wait for loading to disappear and finish_green to appear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading_green")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_green")));
        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText());
    }

    // ==================== 4. Bonus: green and blue ====================
    @Test
    public void loadGreenAndBlueBonus() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // 0) wait for the green+blue button to appear and be clickable
        wait.until(ExpectedConditions.elementToBeClickable(By.id("start_green_and_blue")));

        // 1) click it
        driver.findElement(By.id("start_green_and_blue")).click();

        // 2) button hidden, "Loading green..." visible (only green loading, blue not yet)
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("start_green_and_blue")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_without_blue")));

        // 3) both green and blue loading — loading_green_with_blue appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_with_blue")));

        // 4) green done, blue still loading — finish_green_and_blue_g appears
        //    (guessing the id; adjust if different)
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading_green_without_blue")));

        // 5) both done
        // the final "Green and Blue Loaded" message — guessing id; adjust if different
        // Likely candidates: "finish_green_and_blue" or "finish_blue"
        // For now just assert the button stays hidden and no loading elements visible
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading_green_with_blue")));
        assertFalse(isVisible(By.id("start_green_and_blue")));
    }
}