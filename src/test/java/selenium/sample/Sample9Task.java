package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.utility.BootcampUtils;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Sample9Task {
    WebDriver driver;

    private final By greenButton = By.xpath("//*[text()='Start loading green']");
    private final By greenLoaded = By.xpath("//*[contains(.,'Green Loaded')]");
    private final By greenBlueButton = By.xpath("//*[text()='Start loading green and blue']");

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

    // ---------- helpers ----------
    private boolean isPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    private boolean isVisible(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
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
        driver.findElement(greenButton).click();

        Thread.sleep(6000);

        assertTrue(isVisible(greenLoaded));
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

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(greenButton).click();

        WebElement success = driver.findElement(greenLoaded);
        assertTrue(success.isDisplayed());
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

        driver.findElement(greenButton).click();

        WebElement success = wait.until(
                ExpectedConditions.visibilityOfElementLocated(greenLoaded)
        );
        wait.until(ExpectedConditions.invisibilityOfElementLocated(greenButton));

        assertTrue(success.isDisplayed());

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement button = wait.until(
                ExpectedConditions.visibilityOfElementLocated(greenBlueButton)
        );

        button.click();

        By finalStatusLocator = By.xpath(
                "//*[contains(.,'Green') and contains(.,'Blue') and contains(.,'Loaded')]"
        );

        WebElement finalStatus = wait.until(
                ExpectedConditions.visibilityOfElementLocated(finalStatusLocator)
        );

        assertTrue(finalStatus.isDisplayed());
    }

}