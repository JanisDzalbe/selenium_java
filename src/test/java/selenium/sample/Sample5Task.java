package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        // Click the button
        driver.findElement(By.xpath(
                "//button[contains(text(), 'To go to alerted page')]"
        )).click();

        // Switch to the first alert and accept
        Alert firstAlert = driver.switchTo().alert();
        firstAlert.accept();

        // Switch to the second alert
        Alert secondAlert = driver.switchTo().alert();

        // Verify second alert text
        assertEquals("Booooooooo!", secondAlert.getText());

        // Accept second alert
        secondAlert.accept();

        // Verify the correct page is opened
        assertEquals(
                "This page is alerted",
                driver.findElement(By.id("heading")).getText()
        );

        assertEquals(
                "https://janisdzalbe.github.io/example-site/examples/alerted_page",
                driver.getCurrentUrl()
        );
    }


    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {

        driver.findElement(By.xpath(
                "//button[contains(text(), 'To go to alerted page')]"
        )).click();

        Alert alert = driver.switchTo().alert();
        alert.dismiss();

        System.out.println(driver.findElement(By.tagName("body")).getText());
    }

}

