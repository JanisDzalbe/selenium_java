package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Activity 5: Practicing Selenium browser alert and confirmation popup handling
 * (switching to alerts, accepting, dismissing, and verifying alert/page text).
 */
public class Sample5Task {
    WebDriver driver;

    /**
     * Initializes ChromeDriver and opens the alerts and popups example page before each test.
     *
     * @throws Exception if driver initialization fails
     */
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        // open page:
        driver.get("https://janisdzalbe.github.io/example-site/examples/alerts_popups");
    }

    /**
     * Closes the browser after each test.
     *
     * @throws Exception if browser teardown fails
     */
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    /**
     * Clicks the blue button, accepts the first confirmation popup to navigate to the alerted page,
     * verifies and accepts the second alert, and confirms the alerted page opened correctly.
     *
     * @throws Exception if an element or alert cannot be found
     */
    @Test
    public void goToAlertedPageViaButton() throws Exception {
        // TODO:
        //  click on "To go to alerted page press Ok. Or stay here" button
        driver.findElement(By.className("w3-blue")).click();

        //  switch to alert
        Alert firstAlert = driver.switchTo().alert();

        //  click ok
        firstAlert.accept();

        //  switch to second alert
        Alert secondAlert = driver.switchTo().alert();

        //  verify alert text
        assertEquals("Booooooooo!", secondAlert.getText());

        //  click ok on second alert
        secondAlert.accept();

        //  verify that the correct page is opened
        assertEquals("https://janisdzalbe.github.io/example-site/examples/alerted_page", driver.getCurrentUrl());
    }

    /**
     * Clicks the blue button, dismisses the confirmation popup by clicking Cancel,
     * and verifies the message displayed on the current page.
     *
     * @throws Exception if an element or alert cannot be found
     */
    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
        // TODO:
        //  click on "To go to alerted page press Ok. Or stay here" button
        driver.findElement(By.className("w3-blue")).click();

        //  switch to alert
        Alert alert = driver.switchTo().alert();

        //  click cancel
        alert.dismiss();

        //  verify the text on page
        assertEquals("So you desided to say? Good!", driver.findElement(By.id("textForAlerts")).getText());
    }
}