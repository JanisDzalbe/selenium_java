package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.utility.BootcampUtils;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Sample5Task {
    WebDriver driver;

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        //open page:
        driver.get("https://janisdzalbe.github.io/example-site/examples/alerts_popups");
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void goToAlertedPageViaButton() throws Exception {
        // click on "To go to alerted page press Ok. Or stay here" button
        driver.findElement(By.className("w3-blue")).click();

        // switch to alert
        Alert alert = driver.switchTo().alert();

        // click ok
        alert.accept();

        // switch to second alert (it appears only when the new page loads, so wait for it)
        Alert secondAlert = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());

        // verify alert text
        assertEquals("Booooooooo!", secondAlert.getText());

        // click ok on second alert
        secondAlert.accept();

        // verify that the correct page is opened
        assertEquals("https://janisdzalbe.github.io/example-site/examples/alerted_page", driver.getCurrentUrl());
        assertEquals("This page is alerted", driver.findElement(By.id("heading")).getText());
    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
        // click on "To go to alerted page press Ok. Or stay here" button
        driver.findElement(By.className("w3-blue")).click();

        // switch to alert
        Alert alert = driver.switchTo().alert();

        // click cancel
        alert.dismiss();

        // verify the text on page
        assertEquals("So you desided to say? Good!", driver.findElement(By.id("textForAlerts")).getText());
    }
}
