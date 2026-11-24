
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

    private final By greenButton = By.xpath(
            "//button[normalize-space()='Start loading green']"
    );

    private final By greenBlueButton = By.xpath(
            "//button[normalize-space()='Start loading green and blue']"
    );

    private final By loadingGreen = By.xpath(
            "//*[normalize-space()='Loading green...']"
    );

    private final By loadingBlue = By.xpath(
            "//*[normalize-space()='Loading blue...']"
    );

    private final By greenLoaded = By.xpath(
            "//*[normalize-space()='Green Loaded']"
    );

    @BeforeEach
    public void openPage() {
        driver = BootcampUtils.initializeChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/examples/loading_color");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    private boolean isDisplayed(By locator) {
        List<WebElement> elements = driver.findElements(locator);

        for (WebElement element : elements) {
            if (element.isDisplayed()) {
                return true;
            }
        }

        return false;
    }

    @Test
    public void loadGreenSleep() throws Exception {
        driver.findElement(greenButton).click();

        Thread.sleep(1000);

        assertFalse(isDisplayed(greenButton));
        assertTrue(isDisplayed(loadingGreen));

        Thread.sleep(10000);

        assertFalse(isDisplayed(loadingGreen));
        assertTrue(isDisplayed(greenLoaded));
    }

    @Test
    public void loadGreenImplicit() throws Exception {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(greenButton).click();

        driver.findElement(loadingGreen);

        assertFalse(isDisplayed(greenButton));
        assertTrue(isDisplayed(loadingGreen));

        driver.findElement(greenLoaded);

        assertFalse(isDisplayed(greenButton));
        assertFalse(isDisplayed(loadingGreen));
        assertTrue(isDisplayed(greenLoaded));
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(10)
        );

        driver.findElement(greenButton).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(greenButton));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loadingGreen));

        assertFalse(isDisplayed(greenButton));
        assertTrue(isDisplayed(loadingGreen));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingGreen));
        wait.until(ExpectedConditions.visibilityOfElementLocated(greenLoaded));

        assertFalse(isDisplayed(greenButton));
        assertFalse(isDisplayed(loadingGreen));
        assertTrue(isDisplayed(greenLoaded));
    }

    @Test
    public void loadGreenAndBlueBonus() {

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(15)
        );

        By greenFinishedWaiting = By.xpath(
                "//*[contains(normalize-space(.), 'Green finished waiting for blue')]"
        );

        By finalSuccess = By.xpath(
                "//*[contains(normalize-space(.), 'Green') " +
                        "and contains(normalize-space(.), 'Blue') " +
                        "and contains(normalize-space(.), 'Loaded')]"
        );

        // 0. Wait until the green and blue button is clickable
        wait.until(ExpectedConditions.elementToBeClickable(greenBlueButton));

        // 1. Click Start loading green and blue
        driver.findElement(greenBlueButton).click();

        // 2. Wait until green loading appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(loadingGreen));

        assertTrue(isDisplayed(loadingGreen));
        assertFalse(isDisplayed(greenBlueButton));

        // 3. Wait until blue loading appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(loadingBlue));

        assertTrue(isDisplayed(loadingGreen));
        assertTrue(isDisplayed(loadingBlue));

        // 4. Wait until green finishes while blue is still loading
        wait.until(ExpectedConditions.visibilityOfElementLocated(greenFinishedWaiting));

        assertTrue(isDisplayed(loadingBlue));

        // 5. Wait until blue loading disappears and both colors are loaded
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingBlue));

        wait.until(ExpectedConditions.visibilityOfElementLocated(finalSuccess));

        assertFalse(isDisplayed(loadingGreen));
        assertFalse(isDisplayed(loadingBlue));
        assertTrue(isDisplayed(finalSuccess));
    }
}
