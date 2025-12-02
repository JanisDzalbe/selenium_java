package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class Sample9Task {
    WebDriver driver;

    @BeforeEach
    public void openPage() {
        // Initialize driver
        driver = BootcampUtils.initializeDriver();

        // load web page
        driver.get("https://janisdzalbe.github.io/example-site/examples/loading_color");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    private boolean isVisible(By locator) {
        java.util.List<WebElement> list = driver.findElements(locator);
        return !list.isEmpty() && list.get(0).isDisplayed();
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
        By greenButton = By.xpath("//button[normalize-space()='Start loading green']");
        By greenLoading = By.xpath("//*[normalize-space()='Loading green...']");
        By greenLoaded = By.xpath("//*[normalize-space()='Green Loaded']");

        // 1) click on start loading green button
        driver.findElement(greenButton).click();

        // немножко ждём, чтобы DOM успел поменяться
        Thread.sleep(1000);

        // 2) check that button does not appear, but loading text is seen instead
        assertFalse(isVisible(greenButton), "Green button should be hidden after click");
        assertTrue(isVisible(greenLoading), "Loading text for green should be visible");

        // 3) ждём пока всё догрузится
        Thread.sleep(4000);

        // button и loading не видны, success виден
        assertFalse(isVisible(greenButton), "Green button should still be hidden");
        assertFalse(isVisible(greenLoading), "Loading text for green should be hidden");
        assertTrue(isVisible(greenLoaded), "Success text 'Green Loaded' should be visible");
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
        By greenButton = By.xpath("//button[normalize-space()='Start loading green']");
        By greenLoading = By.xpath("//*[normalize-space()='Loading green...']");
        By greenLoaded = By.xpath("//*[normalize-space()='Green Loaded']");

        // включаем implicit wait для всех findElement
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // 1) click on start loading green button
        driver.findElement(greenButton).click();

        // 2) button исчез, "Loading green..." видно
        assertFalse(isVisible(greenButton), "Green button should be hidden after click");
        assertTrue(isVisible(greenLoading), "Loading text for green should be visible");

        // 3) ждём появления "Green Loaded" (implicit подождёт до 5 сек)
        WebElement loaded = driver.findElement(greenLoaded);
        assertEquals("Green Loaded", loaded.getText());

        // проверяем финальное состояние
        assertFalse(isVisible(greenButton), "Green button should be hidden");
        assertFalse(isVisible(greenLoading), "Loading text for green should be hidden");
        assertTrue(isVisible(greenLoaded), "Success text 'Green Loaded' should be visible");
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
        By greenButton = By.xpath("//button[normalize-space()='Start loading green']");
        By greenLoading = By.xpath("//*[normalize-space()='Loading green...']");
        By greenLoaded = By.xpath("//*[normalize-space()='Green Loaded']");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1) click on start loading green button
        driver.findElement(greenButton).click();

        // 2) ждём, когда появится "Loading green..."
        wait.until(ExpectedConditions.visibilityOfElementLocated(greenLoading));
        assertFalse(isVisible(greenButton), "Green button should be hidden after click");
        assertTrue(isVisible(greenLoading), "Loading text for green should be visible");

        // 3) ждём, когда появится "Green Loaded" и исчезнет loading
        wait.until(ExpectedConditions.visibilityOfElementLocated(greenLoaded));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(greenLoading));

        assertFalse(isVisible(greenButton), "Green button should be hidden");
        assertFalse(isVisible(greenLoading), "Loading text for green should be hidden");
        assertTrue(isVisible(greenLoaded), "Success text 'Green Loaded' should be visible");

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
        // локаторы по id из твоего скрина
        By startGreenAndBlueBtn = By.id("start_green_and_blue");
        By loadingGreenWithoutBlue = By.id("loading_green_without_blue");
        By loadingGreenWithBlue = By.id("loading_green_with_blue");
        By loadingBlueWithoutGreen = By.id("loading_blue_without_green");
        By finishGreenAndBlue = By.id("finish_green_and_blue");

        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));

        // 0) wait until the “start green and blue” button appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(startGreenAndBlueBtn));

// 1) click the “start green and blue” button
        driver.findElement(startGreenAndBlueBtn).click();

// 2) the button disappears, only the green loading text should become visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(loadingGreenWithoutBlue));

        Assertions.assertFalse(
                isVisible(startGreenAndBlueBtn),
                "Button 'start_green_and_blue' should disappear"
        );
        Assertions.assertTrue(
                isVisible(loadingGreenWithoutBlue),
                "'loading_green_without_blue' must be visible"
        );
        Assertions.assertFalse(
                isVisible(loadingGreenWithBlue),
                "'loading_green_with_blue' should not be visible yet"
        );
        Assertions.assertFalse(
                isVisible(loadingBlueWithoutGreen),
                "'loading_blue_without_green' should not be visible yet"
        );
        Assertions.assertFalse(
                isVisible(finishGreenAndBlue),
                "Final status should not be visible yet"
        );

// 3) the button is still hidden, loading text for both green and blue should appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(loadingGreenWithBlue));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loadingBlueWithoutGreen));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingGreenWithoutBlue));

        Assertions.assertFalse(isVisible(startGreenAndBlueBtn));
        Assertions.assertTrue(isVisible(loadingGreenWithBlue),
                "'loading_green_with_blue' must be visible");
        Assertions.assertTrue(isVisible(loadingBlueWithoutGreen),
                "'loading_blue_without_green' must be visible");
        Assertions.assertFalse(isVisible(loadingGreenWithoutBlue));
        Assertions.assertFalse(isVisible(finishGreenAndBlue));

// 4) the button and the green loading text disappear,
//    blue loading text remains visible, and green success text appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(finishGreenAndBlue));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingGreenWithBlue));

        Assertions.assertFalse(isVisible(startGreenAndBlueBtn));
        Assertions.assertFalse(isVisible(loadingGreenWithoutBlue));
        Assertions.assertFalse(isVisible(loadingGreenWithBlue));
        Assertions.assertTrue(isVisible(finishGreenAndBlue),
                "Green success message must be visible");

// 5) the button and all loading texts disappear, only the final success message remains visible
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingBlueWithoutGreen));

        Assertions.assertFalse(isVisible(startGreenAndBlueBtn));
        Assertions.assertFalse(isVisible(loadingGreenWithoutBlue));
        Assertions.assertFalse(isVisible(loadingGreenWithBlue));
        Assertions.assertFalse(isVisible(loadingBlueWithoutGreen));
        Assertions.assertTrue(isVisible(finishGreenAndBlue),
                "Final success status must be visible once both green and blue are loaded");
    }
}