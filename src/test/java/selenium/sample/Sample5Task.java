package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.utility.BootcampUtils;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Sample5Task {
    WebDriver driver;
    WebDriverWait wait;

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //open page:
        driver.get("https://acctabootcamp.github.io/site/examples/alerts_popups");
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void goToAlertedPageViaButton() throws Exception {
        // click on "To go to alerted page press Ok. Or stay here" button
        WebElement alertButton = driver.findElement(By.id("alertButton"));
        alertButton.click();

        // switch to alert
        Alert firstAlert = wait.until(ExpectedConditions.alertIsPresent());
        assertEquals("Do you want to go to alerted_page.html?", firstAlert.getText(),
                "First alert text should match expected message");

        // click OK on first alert
        firstAlert.accept();

        // switch to second alert
        Alert secondAlert = wait.until(ExpectedConditions.alertIsPresent());

        // verify alert text
        String secondAlertText = secondAlert.getText();
        assertEquals("Redirecting to alerted_page.html", secondAlertText,
                "Second alert text should match expected message");

        // click OK on second alert
        secondAlert.accept();

        // verify that the correct page is opened
        wait.until(ExpectedConditions.titleIs("Alerted page"));

        // additional verification that we're on the correct page
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.endsWith("alerted_page.html"),
                "URL should end with alerted_page.html");

        // verify content on the alerted page
        WebElement pageHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        assertEquals("Alerted page", pageHeader.getText(),
                "Page header should match expected text");
    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
        // store the initial URL to verify we stay on the same page
        String initialUrl = driver.getCurrentUrl();

        // click on "To go to alerted page press Ok. Or stay here" button
        WebElement alertButton = driver.findElement(By.id("alertButton"));
        alertButton.click();

        // switch to alert
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        assertEquals("Do you want to go to alerted_page.html?", alert.getText(),
                "Alert text should match expected message");

        // click cancel
        alert.dismiss();

        // verify we're still on the same page
        assertEquals(initialUrl, driver.getCurrentUrl(),
                "URL should remain unchanged after canceling the alert");

        // verify the text on page (that we stayed on the original page)
        WebElement pageHeader = driver.findElement(By.tagName("h1"));
        assertEquals("Alerts and Popups", pageHeader.getText(),
                "Page header should remain the same after canceling alert");

        // verify that the specific alert result message is displayed
        WebElement alertResult = driver.findElement(By.id("alertResult"));
        assertEquals("You clicked Cancel!", alertResult.getText(),
                "Alert result message should indicate Cancel was clicked");
    }
}