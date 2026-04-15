package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.utility.BootcampUtils;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Sample9Task {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void openPage() {
        driver = BootcampUtils.initializeChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://janisdzalbe.github.io/example-site/examples/loading_color");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }


    private WebElement getVisibleElement(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        for (WebElement el : elements) {
            if (el.isDisplayed()) {
                return el;
            }
        }
        throw new RuntimeException("No visible element found: " + locator);
    }

    @Test
    public void loadGreenSleep() throws Exception {

        // click button
        getVisibleElement(By.id("start_green")).click();

        // wait until loading appears
        wait.until(d -> getVisibleElement(By.id("loading_green")).isDisplayed());

        // assert loading visible
        assertTrue(getVisibleElement(By.id("loading_green")).isDisplayed());

        // wait until loading disappears
        wait.until(d ->
                driver.findElements(By.id("loading_green"))
                        .stream()
                        .noneMatch(WebElement::isDisplayed)
        );

        // assert loading gone
        boolean loadingVisible = driver.findElements(By.id("loading_green"))
                .stream()
                .anyMatch(WebElement::isDisplayed);

        assertFalse(loadingVisible);

        // assert success visible
        assertTrue(getVisibleElement(By.id("finish_green")).isDisplayed());
    }

    @Test
    public void loadGreenImplicit() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        getVisibleElement(By.id("start_green")).click();

        WebElement success = getVisibleElement(By.id("finish_green"));

        assertTrue(success.isDisplayed());
    }

    @Test
    public void loadGreenExplicitWait() {

        // click visible button
        getVisibleElement(By.id("start_green")).click();

        // SAFE wait (no exceptions)
        wait.until(d ->
                d.findElements(By.id("finish_green"))
                        .stream()
                        .anyMatch(WebElement::isDisplayed)
        );

        // assert
        assertTrue(getVisibleElement(By.id("finish_green")).isDisplayed());
    }

    @Test
    public void loadGreenAndBlueBonus() {

        // Start green first
        getVisibleElement(By.id("start_green")).click();

        // Wait until green+blue button appears
        wait.until(d ->
                d.findElements(By.id("start_green_and_blue"))
                        .stream()
                        .anyMatch(WebElement::isDisplayed)
        );

        //  Click green+blue
        getVisibleElement(By.id("start_green_and_blue")).click();

        //  Wait for final result (SAFE — no exception)
        wait.until(d ->
                d.findElements(By.id("finish_green_and_blue"))
                        .stream()
                        .anyMatch(WebElement::isDisplayed)
        );

        //  Assert
        assertTrue(getVisibleElement(By.id("finish_green_and_blue")).isDisplayed());
    }
}