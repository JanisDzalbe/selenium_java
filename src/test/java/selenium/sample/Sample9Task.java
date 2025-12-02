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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Sample9Task {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void openPage() {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        // load web page
        driver.get("https://janisdzalbe.github.io/example-site/examples/loading_color");

        // explicit wait instance for explicit-wait tests
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    // helper: is element visible to the user?
    private boolean isElementVisible(By locator) {
        try {
            WebElement el = driver.findElement(locator);
            return el.isDisplayed();
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

        By greenButton  = By.xpath("//*[normalize-space()='Start loading green']");
        By greenLoading = By.xpath("//*[normalize-space()='Loading green...']");
        By greenSuccess = By.xpath("//*[normalize-space()='Green Loaded']");

        // 1) click on start loading green button
        driver.findElement(greenButton).click();

        // give time for transition from button -> loading
        Thread.sleep(2000);

        // 2) button should not appear, loading text should be seen
        assertFalse(isElementVisible(greenButton));
        assertTrue(isElementVisible(greenLoading));

        // give time for transition from loading -> success
        Thread.sleep(8000);

        // 3) button and loading should not be seen, success should be seen
        assertFalse(isElementVisible(greenButton));
        assertFalse(isElementVisible(greenLoading));
        assertTrue(isElementVisible(greenSuccess));
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

        By greenButton  = By.xpath("//*[normalize-space()='Start loading green']");
        By greenLoading = By.xpath("//*[normalize-space()='Loading green...']");
        By greenSuccess = By.xpath("//*[normalize-space()='Green Loaded']");

        // set implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // 1) click on start loading green button
        driver.findElement(greenButton).click();

        // 2) button should not appear, loading text should be seen
        // implicit wait will help when the loading element appears
        assertFalse(isElementVisible(greenButton));
        assertTrue(isElementVisible(greenLoading));

        // 3) finally, success should be seen, button and loading should not
        assertTrue(isElementVisible(greenSuccess));
        assertFalse(isElementVisible(greenButton));
        assertFalse(isElementVisible(greenLoading));
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

        By greenButton  = By.xpath("//*[normalize-space()='Start loading green']");
        By greenLoading = By.xpath("//*[normalize-space()='Loading green...']");
        By greenSuccess = By.xpath("//*[normalize-space()='Green Loaded']");

        // 1) click on start loading green button
        driver.findElement(greenButton).click();

        // 2) wait for button to become invisible and loading to be visible
        wait.until(ExpectedConditions.invisibilityOfElementLocated(greenButton));
        wait.until(ExpectedConditions.visibilityOfElementLocated(greenLoading));

        assertFalse(isElementVisible(greenButton));
        assertTrue(isElementVisible(greenLoading));

        // 3) wait for loading to become invisible and success to be visible
        wait.until(ExpectedConditions.invisibilityOfElementLocated(greenLoading));
        wait.until(ExpectedConditions.visibilityOfElementLocated(greenSuccess));

        assertFalse(isElementVisible(greenButton));
        assertFalse(isElementVisible(greenLoading));
        assertTrue(isElementVisible(greenSuccess));
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

        By greenBlueButton = By.xpath("//*[normalize-space()='Start loading green and blue']");

        // These text values follow the same pattern as the green one.
        // If the actual page uses slightly different wording, just adjust the strings.
        By greenLoading = By.xpath("//*[normalize-space()='Loading green...']");
        By blueLoading  = By.xpath("//*[normalize-space()='Loading blue...']");
        By greenSuccess = By.xpath("//*[normalize-space()='Green Loaded']");
        By blueSuccess  = By.xpath("//*[normalize-space()='Blue Loaded']");

        // 0) wait until button to load green and blue appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(greenBlueButton));

        // 1) click on start loading green and blue button
        driver.findElement(greenBlueButton).click();

        // 2) button should not appear, loading text is seen for green
        wait.until(ExpectedConditions.invisibilityOfElementLocated(greenBlueButton));
        wait.until(ExpectedConditions.visibilityOfElementLocated(greenLoading));

        assertFalse(isElementVisible(greenBlueButton));
        assertTrue(isElementVisible(greenLoading));

        // 3) button should not appear, loading text seen for green AND blue
        wait.until(ExpectedConditions.visibilityOfElementLocated(blueLoading));

        assertFalse(isElementVisible(greenBlueButton));
        assertTrue(isElementVisible(greenLoading));
        assertTrue(isElementVisible(blueLoading));

        // 4) button and green loading do not appear,
        //    but blue loading is seen and green success is seen
        wait.until(ExpectedConditions.visibilityOfElementLocated(greenSuccess));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(greenLoading));

        assertFalse(isElementVisible(greenBlueButton));
        assertFalse(isElementVisible(greenLoading));
        assertTrue(isElementVisible(blueLoading));
        assertTrue(isElementVisible(greenSuccess));

        // 5) both button and loading texts are not seen, only success texts
        wait.until(ExpectedConditions.visibilityOfElementLocated(blueSuccess));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(blueLoading));

        assertFalse(isElementVisible(greenBlueButton));
        assertFalse(isElementVisible(greenLoading));
        assertFalse(isElementVisible(blueLoading));
        assertTrue(isElementVisible(greenSuccess));
        assertTrue(isElementVisible(blueSuccess));
    }

}
