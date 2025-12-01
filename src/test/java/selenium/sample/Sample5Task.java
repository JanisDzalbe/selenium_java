package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.*;

public class Sample5Task {
    WebDriver driver;

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        // open page:
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
        driver.findElement(
                By.xpath("//button[contains(.,'To go to alerted page press Ok. Or stay here')]")
        ).click();

        // switch to alert
        Alert firstAlert = driver.switchTo().alert();

        // click ok
        firstAlert.accept();

        // switch to second alert
        Alert secondAlert = driver.switchTo().alert();

        // verify alert text
        assertEquals("Booooooooo!", secondAlert.getText());

        // click ok on second alert
        secondAlert.accept();

        // verify that the correct page is opened
        assertEquals("https://janisdzalbe.github.io/example-site/examples/al_p",
                driver.getCurrentUrl());
        assertEquals("This page is alerted",
                driver.findElement(By.id("heading")).getText());
    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
        // click on "To go to alerted page press Ok. Or stay here" button
        driver.findElement(
                By.xpath("//button[contains(.,'To go to alerted page press Ok. Or stay here')]")
        ).click();

        // switch to alert
        Alert firstAlert = driver.switchTo().alert();

        // click cancel
        firstAlert.dismiss();

        // verify the text on page
        String textOnPage = driver.findElement(By.id("textForAlerts")).getText();
        assertFalse(textOnPage.isEmpty());

        // still on the same page
        assertEquals("https://janisdzalbe.github.io/example-site/examples/alerts_popups",
                driver.getCurrentUrl());
    }
}
